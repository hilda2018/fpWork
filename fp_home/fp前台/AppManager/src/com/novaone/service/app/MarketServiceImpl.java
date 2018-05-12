package com.novaone.service.app;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.struts2.ServletActionContext;

import com.google.common.collect.Lists;
import com.nova.frame.collect.Maps;
import com.nova.frame.dao.pager.Page;
import com.nova.frame.model.ObjectId;
import com.nova.frame.utils.DateUtils;
import com.nova.frame.utils.JdbcUtils;
import com.nova.frame.utils.SecurityUtils;
import com.novaone.constants.Constants;
import com.novaone.model.Attention;
import com.novaone.model.Cominfo;
import com.novaone.model.Country;
import com.novaone.model.CountryProduct;
import com.novaone.model.Currency;
import com.novaone.model.CustomsInfo;
import com.novaone.model.DUserMenu;
import com.novaone.model.Marketquotation;
import com.novaone.model.Mqpicture;
import com.novaone.model.Product;
import com.novaone.model.Template;
import com.novaone.model.User;
import com.novaone.model.Version;
import com.novaone.utils.BeanUtils;
import com.novaone.utils.ChinaInitial;
import com.novaone.utils.DateUtil;
import com.novaone.utils.JsonUtils;
import com.novaone.utils.Md5Encoder;
import com.novaone.utils.RequestParameters;
public class MarketServiceImpl implements MarketService {
	public static  Log logger=LogFactory.getLog(MarketServiceImpl.class);
	//登录改成用户名登录2016-05-25
	private  static final String findByCodeAndPwd="select b.*, a.parentid ,c.companycnname primarycncompany,c.companyenname primaryencompany "
			+ " from d_user a "
			+ " join users b on a.id=b.userid "
			+ " left join users c on a.parentid=c.userid "
			+ " where a.code=? and a.password=? and b.isapp=? ";
	/**根据用户id获取登录用户信息*/
	private  static final String getUserById="select b.*, a.parentid ,c.companycnname primarycncompany,c.companyenname primaryencompany "
			+ " from d_user a "
			+ " join users b on a.id=b.userid "
			+ " left join users c on a.parentid=c.userid "
			+ " where a.id=? ";
	private static final String checkLoginName="select count(1) from users where phonenum=? and userid != ? ";
	@Override
	public User findByCodeAndPwd(String name, String pwd) {
		List<User> user=JdbcUtils.query(User.class, findByCodeAndPwd, name,pwd,"是");
		if(null !=user && !user.isEmpty()){
			return user.get(0);
		}
		return null;
	}
	@Override
	public User getUserById(String id) {
		User user = JdbcUtils.get(User.class, getUserById, id);
		return user;
	}
	@Override
	public User getUserbylogin(String userid) {
		String sql = "select b.* from d_user a left join users b on a.parentid = b.userid where a.id =? ";
		List<User> users = JdbcUtils.query(User.class, sql, userid);
		if(null != users && !users.isEmpty()){
			return users.get(0);
		}
		return null;
	}
	@Override
	public boolean checkLoginName(String name,String userid) {
		Long c=JdbcUtils.count(checkLoginName, name,userid);
		return c>0;
	}
	@Override
	public boolean addCustomsInfo(CustomsInfo info,String userid){
		try {
			//根据对象是否有id来判断,是执行add还是update操作
			if(BeanUtils.isEmpty(info.getCustomsinfoid())){
				info.setCustomsinfoid(ObjectId.StringId());
				info.setCurrentdate(DateUtils.getDate2LStr(new Date()));
				info.setType(Constants.TYPE_0);
				info.setUserid(userid);
				
				info.setRootuserid(this.getRootUserbySubUserid(userid));
				info.setIndate(DateUtils.getDate2SStr(new Date()));
				info.setModifytime(DateUtils.getDate2LStr(new Date()));
				info.setModifyuser(userid);
				
				JdbcUtils.insert(info);	
			}else{
				//执行update
				info.setType(Constants.TYPE_0);
				
				info.setModifytime(DateUtils.getDate2LStr(new Date()));
				info.setModifyuser(userid);
				
				JdbcUtils.updateNotNull(info);
			}
			return true;
		} catch (Exception e) {
			logger.error("添加或修改常用联系人异常", e);
			return false;
		}
	}
	@Override
	public boolean deleteCustomsInfo(String ids) {
		String sql = "delete from customsinfo where customsinfoid in ("+ids+")";
		return JdbcUtils.execute(sql) > 0;
	}
	//modify by chenzj 2016-08-30 
	//增加图片处理
	@Override
	public boolean add(List<Marketquotation> mqlist) {
		try {
			JdbcUtils.beginTransaction();
			List<Mqpicture> piclist=null;
			//在多品名多规格每条数据都插入图片
			for(Marketquotation mq:mqlist){
				JdbcUtils.insert(mq);
				//获取此记录下关联的图片
				piclist=mq.getPiclist();
				//图片相关的信息插入到图片表
				this.updateMqpicture(piclist, mq.getMqId());
			}
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			logger.error("价格行情添加发生异常!", e);
			JdbcUtils.rollbackTransaction();
			return false;
		}
	}
	@Override
	public boolean saveMarList4Web(List<Marketquotation> addList,	List<Marketquotation> updateList) {
		try {
			JdbcUtils.beginTransaction();
			if(null != addList && !addList.isEmpty()){
				JdbcUtils.insertIsNull(addList);
			}
			if(null !=updateList && !updateList.isEmpty()){
				for(Marketquotation mq:updateList){
					JdbcUtils.updateEntity(mq);
				}
			}
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			logger.error("价格行情网页端记录保存异常", e);
			return false;
		}
	}
	@Override
	public boolean delete(String id) {
		try {
			//删除市场行情记录信息
			String sql="delete from marketquotation where mq_id in("+id+")";
			//删除行情记录图片信息
			String delete = "delete from mqpicture where mquotationid in("+id+")";
			JdbcUtils.execute(sql);
			JdbcUtils.execute(delete);
			return true;
		} catch (Exception e) {
			logger.error("市场行情记录删除异常!", e);
			return false;
		}
	}

	@Override
	public boolean modify(List<Marketquotation> mq) {
		try {
			JdbcUtils.beginTransaction();
			for(Marketquotation mar:mq){
				JdbcUtils.updateEntity(mar);
			}
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			logger.error("市场行情数据修改异常", e);
			JdbcUtils.rollbackTransaction();
			return false;
		}
	}
	@Override
	public boolean modify4App(List<Marketquotation> mq,String type) {
		try {
			JdbcUtils.beginTransaction();
			if(!"copy-input".equals(type)){
				//记录先做删除操作
				this.delete("'"+mq.get(0).getMqId()+"'");
			}
			//记录增加操作
			for(Marketquotation mar:mq){
				mar.setMqId(ObjectId.StringId());
				JdbcUtils.insert(mar);
				//对图片进行操作(先删除以前的图片,再次重新插入)
				List<Mqpicture> piclist = mar.getPiclist();
				this.updateMqpicture(piclist, mar.getMqId());
			}
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			logger.error("市场行情数据修改异常", e);
			JdbcUtils.rollbackTransaction();
			return false;
		}
	}
		/**
		 * 更新记录图片信息
		 * @方法名称:updateMqpicture
		 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
		 * @param piclist
		 * @param mqId 
		 * void
		 * @exception 
		 * @author:陈自军
		 * @创建日期:2016年8月30日-下午2:42:49
		 */
	public void updateMqpicture(List<Mqpicture> piclist,String mqId){
		//图片相关的信息插入到图片表
		if(!BeanUtils.isEmpty(piclist)){
			for(Mqpicture pic:piclist){
				pic.setMqpictureid(ObjectId.StringId());
				if(BeanUtils.isEmpty(pic.getCurrentdate())){
					pic.setCurrentdate(DateUtils.getDate2LStr(new Date()));
				}
				pic.setMquotationid(mqId);
			 }
			JdbcUtils.insert(piclist);
			}
	}

	@Override
	public Marketquotation detail(String id) {
		//获取市场行情信息
		String sql="select a.*,c.countrycnname countryname,c.countryenname, f.nickname enterusername, "
				+ " d.productcnname productname ,d.productenname,e.currencycnname,e.currencyenname "
				+ " from marketquotation a "
				+ " join country c on  a.country=c.countryid "
				+ " join product d on a.product=d.productid "
				+ " left join currency e on a.currencyid=e.currencyid "
				+ " left join users f on a.enteruserid=f.userid"
				+ " where mq_id=?";
		Marketquotation mq =JdbcUtils.get(Marketquotation.class, sql,id);
		
		return mq;
	}
	@Override
	public List<Mqpicture> getPiclist(String id) {
		//获取记录所关联的图片
		String sql4pic="select * from mqpicture where mquotationid =? ";
		List<Mqpicture> piclist=JdbcUtils.query(Mqpicture.class, sql4pic,id);
		if(!BeanUtils.isEmpty(piclist)){
			return piclist;
		}
		return null;
	}
		@Override
		public List<Marketquotation> getDefaultDatas(Marketquotation mq,String type, Page page) {
			String sql="select a.*,b.nickname as enterusername,c.countrycnname countryname, d.productcnname productname "
					+ "from marketquotation a "
					+ "join users b on a.enteruserid=b.userid "
					+ "join country c on  a.country=c.countryid "
					+ "join product d on a.product=d.productid "
					+ " where 1=1 and a.enteruserid =? and a.markettype=? and a.status=? ";
			List<Object> params=new ArrayList<Object>();
			List<Marketquotation> list=null;
			params.add(mq.getEnteruserid());
			params.add(mq.getMarkettype());
			params.add("1");
			//时间默认为当天数据，数据按照品名、国家、销售商、市场归类排序
			//convert( name using gbk ) collate gbk_chinese_ci asc
			if("0".equals(type)){
				sql +=" and a.marketdate between ? and ? order by a.marketdate desc,"
						+ "convert( productname using gbk ) collate gbk_chinese_ci asc,"
						+ "convert( countryname using gbk ) collate gbk_chinese_ci asc,"
						+ "convert( supplier using gbk ) collate gbk_chinese_ci asc,"
						+ "markettype asc ";
				params.add(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
				params.add(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
				list=JdbcUtils.query(Marketquotation.class, sql, params.toArray());
				return list;
			}else if("1".equals(type)){
				sql +=" and a.marketdate <=? order by  a.marketdate desc,"
						+ "convert( productname using gbk ) collate gbk_chinese_ci asc,"
						+ "convert( countryname using gbk ) collate gbk_chinese_ci asc,"
						+ "convert( supplier using gbk ) collate gbk_chinese_ci asc,"
						+ "markettype asc ";
				params.add(DateUtils.getDate2SStr(DateUtils.getPastdayDate(1)));
				list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
				return list	;
			}
			return null;
		}
	@Override
	public List<Marketquotation> getDatas(Marketquotation mq,Page page) {
		String sql="select a.*,b.nickname as enterusername,c.countrycnname countryname, d.productcnname productname "
				+ " from  marketquotation a "
				+ "join users b on a.enteruserid=b.userid "
				+ "join country c on  a.country=c.countryid "
				+ "join product d on a.product=d.productid "
				+ "where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql+=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql+=" and a.product = ?";
			params.add(mq.getProduct());
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties = ?";
			params.add(mq.getVarieties());
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec = ?";
			params.add(mq.getSpec());
		}
		//供应商 supplier
		if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
			sql +=" and a.supplier = ?";
			params.add(mq.getSupplier());
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand = ?";
			params.add(mq.getBrand());
		}
		//销售商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor = ?";
			params.add(mq.getVendor());
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ? ";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =? ";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=? ";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ?";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ?";
			params.add(mq.getMaxprice());
		}
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =?";
			params.add(mq.getStatus());
		}
		//时间倒序
		sql += " order by a.marketdate desc,a.currentdate desc ";
		List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		return list;
	}
	@Override
	public List<Marketquotation> getDatas4New(Marketquotation mq, String fieldvalue,Page page) {
		String sql = "select a.* from marketquotation_view a where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//对国家品名 销售商 模糊查询,数据性质:自己/全部
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql +=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql +=" and a.product = ?";
			params.add(mq.getProduct());
		}
		if(BeanUtils.isNotEmpty(mq.getProductname())){
			sql +=" and a.productname like ? ";
			params.add("%"+mq.getProductname().trim()+"%");
		}
		if(BeanUtils.isNotEmpty(mq.getCountryname())){
			sql +=" and a.countryname like ? ";
			params.add("%"+mq.getCountryname().trim()+"%");
		}
		//数据状态 :个人
		if(BeanUtils.isNotEmpty(mq.getDatastate()) && Constants.DATASTATE_1.equals(mq.getDatastate())){
			sql +=" and a.transmituser is  null ";
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties =? ";
			params.add(mq.getVarieties());
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec = ?";
			params.add(mq.getSpec());
		}
		//销售商 supplier
		if(BeanUtils.isNotEmpty(mq.getSupplier())){
			sql +=" and a.supplier like ? ";
			params.add("%"+mq.getSupplier().trim()+"%");
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand = ?";
			params.add(mq.getBrand());
		}
		//供应商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor = ?";
			params.add(mq.getVendor());
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ? ";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =? ";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=? ";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//单时间点查询
		if(!BeanUtils.isEmpty(mq.getMarketdate())){
			sql +=" and a.marketdate=? ";
			params.add(mq.getMarketdate());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ? ";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ? ";
			params.add(mq.getMaxprice());
		}
		//转发确认与否
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =? ";
			params.add(mq.getStatus());
		}
		//按照选择字段进行排序
		if(!BeanUtils.isEmpty(fieldvalue)){
			sql +=" order by convert (a."+fieldvalue+" using gbk) collate  gbk_chinese_ci asc,a.marketdate desc";
		}else{
			//时间倒序
			sql += " order by a.marketdate desc,a.currentdate desc ";	
		}
		List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		return list;
		
		
	}
		@Override
		public List<Marketquotation> getDatas4Query(Marketquotation mq, Page page,Integer datenum) {
			String sql="select a.*,b.nickname as enterusername,c.countrycnname countryname, d.productcnname productname "
					+ "from  marketquotation a "
					+ "join users b on a.enteruserid=b.userid "
					+ "join country c on  a.country=c.countryid "
					+ "join product d on a.product=d.productid "
					+ "where 1=1 and a.markettype is not null ";
			List<Object> params=new ArrayList<Object>();
			if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
				sql+=" and a.country = ?";
				params.add(mq.getCountry());
			}
			if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
				sql+=" and a.product = ?";
				params.add(mq.getProduct());
			}
			//品种
			if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
				sql +=" and a.varieties = ?";
				params.add(mq.getVarieties());
			}
			//规格 spec
			if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
				sql +=" and a.spec = ?";
				params.add(mq.getSpec());
			}
			//销售商 supplier
			if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
				sql +=" and a.supplier = ?";
				params.add(mq.getSupplier());
			}
			//品牌 brand
			if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
				sql +=" and a.brand = ?";
				params.add(mq.getBrand());
			}
			//供应商 vendor
			if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
				sql +=" and a.vendor = ?";
				params.add(mq.getVendor());
			}
			//板数 platenum
			if(mq.getPlatenum() !=null){
				sql +=" and a.platenum = ?";
				params.add(mq.getPlatenum());
			}
			//运输方式 transport
			if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
				sql +=" and a.transport =?";
				params.add(mq.getTransport());
			}
			//当前用户enteruserid
			if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
				sql +=" and a.enteruserid in ("+mq.getEnteruserid()+") ";
			}
			//市场类型 markettype
			if(mq.getMarkettype() !=null){
				sql +=" and a.markettype=?";
				params.add(mq.getMarkettype());
			}
			//获取前几天数据
			if(BeanUtils.isNotEmpty(datenum) && BeanUtils.isNotEmpty(mq.getMarketdate())){
				String enddate = mq.getMarketdate();
				String startdate = DateUtils.getDate2SStr(DateUtil.addDate(DateUtils.getStr2SDate(enddate), -2));
				sql +=" and a.marketdate between ? and ? ";
				params.add(startdate);
				params.add(enddate);
			
			}else{
				//市场日期
				if(!BeanUtils.isEmpty(mq.getMarketdate())){
					sql +=" and a.marketdate = ?";
					params.add(mq.getMarketdate());
				}
			}
			
			
			//价格 minprice maxprice 
			if(mq.getMinprice() !=null){
				sql +=" and a.minprice >= ?";
				params.add(mq.getMinprice());
			}
			if(mq.getMaxprice() !=null){
				sql += " and a.maxprice <= ?";
				params.add(mq.getMaxprice());
			}
			if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
				sql +=" and a.status =?";
				params.add(mq.getStatus());
			}
			//时间倒序 convert( enterusername using gbk ) collate gbk_chinese_ci asc
			sql += "order by convert( productname using gbk ) collate gbk_chinese_ci asc,"
					+ "convert( countryname using gbk ) collate gbk_chinese_ci asc,"
					+ "a.markettype asc,"
					+ " convert( a.supplier using gbk ) collate gbk_chinese_ci asc,"
					+ "convert( enterusername using gbk ) collate gbk_chinese_ci asc, currentdate desc ";
			List<Marketquotation> list = JdbcUtils.query(Marketquotation.class, sql, params.toArray());
			return list;
		}
		@Override
		public List<Marketquotation> getshareDatasList(String ids) {
			String sql ="select a.* from marketquotation_view a where 1=1 and a.mq_id in ("+ids+") ";
			//记录排序
			sql += "order by convert( a.productname using gbk ) collate gbk_chinese_ci asc,"
					+ "convert( a.countryname using gbk ) collate gbk_chinese_ci asc,"
					+ "a.markettype asc,"
					+ " convert( a.supplier using gbk ) collate gbk_chinese_ci asc,"
					+ "convert( a.enterusername using gbk ) collate gbk_chinese_ci asc, a.currentdate desc ";
			
			return JdbcUtils.query(Marketquotation.class, sql);
		}
		
		@Override
		public Map<String,Map<String,List<Marketquotation>>> secondProcess(List<Marketquotation> list){
			//对查询结果进行二次封装
			Map<String,Map<String,List<Marketquotation>>> maplist = new LinkedHashMap<String,Map<String,List<Marketquotation>>>();
			List<Marketquotation> temp=null;
			Map<String,List<Marketquotation>> tempmap=null;
			if(!BeanUtils.isEmpty(list)){
				String key="";
				String key_1="";
				for(Marketquotation m:list){
					temp = new ArrayList<Marketquotation>();
					tempmap = new LinkedHashMap<String,List<Marketquotation>>();
					key = m.getCountryname()+""+m.getProductname();
					key_1 = m.getMarkettype()==0 ? "辉展市场":"江南市场";
						if(!maplist.containsKey(key)){
							//对tempmap赋值
							if(!tempmap.containsKey(key_1)){
								temp.add(m);
								tempmap.put(key_1, temp);
							}else{
								temp = tempmap.get(key_1);
								temp.add(m);
								tempmap.put(key_1, temp);
							}
							//对maplist赋值
							maplist.put(key, tempmap);
						}else{
							tempmap = maplist.get(key);
							if(!tempmap.containsKey(key_1)){
								temp.add(m);
								tempmap.put(key_1, temp);
							}else{
								temp = tempmap.get(key_1);
								temp.add(m);
								tempmap.put(key_1, temp);
							}
							//对maplist赋值
							maplist.put(key, tempmap);
						}
			
				}
			}
			return maplist;
		}
		@Override
		public Map<String, List<Marketquotation>> secondProcessNew(
				List<Marketquotation> list) {
			Map<String,List<Marketquotation>> maplist = Maps.newLinkedHashMap();
			List<Marketquotation> temp=null;
			if(BeanUtils.isNotEmpty(list)){
				String key="";
				String markettype="";
				for(Marketquotation m:list ){
					markettype = m.getMarkettype()==0 ? "辉展市场":"江南市场";
					key = m.getCountryname()+" "+m.getProductname()+"#"+markettype;
					if(maplist.containsKey(key)){
						temp = maplist.get(key);
						temp.add(m);
						maplist.put(key, temp);
					}else{
						temp = new ArrayList<Marketquotation>();
						temp.add(m);
						maplist.put(key, temp);
					}
				}
				return maplist;
			}
			return null;
		}
		
	/**
	 * 查询列表中增加主账号id 
	 */
	@Override
	public List<Marketquotation> getDatas4Web(Marketquotation mq, Page page,
			RequestParameters requestParameters) {
		String sql="select a.*,b.nickname enterusername,c.countrycnname countryname,countryenname, "
				+ "d.productcnname productname,d.productenname ,e.currencycnname,e.currencyenname,f.parentid "
				+ " from  marketquotation a "
				+ " join users b on a.enteruserid=b.userid "
				+ " join country c on  a.country=c.countryid "
				+ " join product d on a.product=d.productid "
				+ " left join currency e on a.currencyid=e.currencyid "
				+ " join d_user f on a.enteruserid=f.id "
				+ " where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//主账号id
		if(!BeanUtils.isEmpty(mq.getParentid())){
			sql +=" and f.parentid=? ";
			params.add(mq.getParentid());
		}
		//子账号id
		if(!BeanUtils.isEmpty(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql+=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql+=" and a.product = ?";
			params.add(mq.getProduct());
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties = ?";
			params.add(mq.getVarieties());
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec = ?";
			params.add(mq.getSpec());
		}
		//供应商 supplier
		if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
			sql +=" and a.supplier = ?";
			params.add(mq.getSupplier());
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand = ?";
			params.add(mq.getBrand());
		}
		//销售商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor = ?";
			params.add(mq.getVendor());
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ?";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =?";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=?";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ?";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ?";
			params.add(mq.getMaxprice());
		}
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =?";
			params.add(mq.getStatus());
		}
		String sortname = requestParameters.getSort();//获取排序字段
		String sortorder = requestParameters.getOrder();//获取排序方式
		if(null != sortname && !"".equals(sortname)){
			sql += " order by a."+sortname+" "+sortorder+",a.currentdate desc ";
		}else{
			//时间倒序
			sql += " order by a.marketdate desc,a.currentdate desc ";
		}
		List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		return list;
	}
	/**
	 * 查询列表中增加主账号id 
	 */
	@Override
	public List<Marketquotation> getDatas4WebAll(Marketquotation mq, Page page,
			RequestParameters requestParameters) {
		String sql="select a.*,b.nickname enterusername,c.countrycnname countryname,countryenname, "
				+ "d.productcnname productname,d.productenname ,e.currencycnname,e.currencyenname,f.parentid "
				+ " from  marketquotation a "
				+ " join users b on a.enteruserid=b.userid "
				+ " join country c on  a.country=c.countryid "
				+ " join product d on a.product=d.productid "
				+ " left join currency e on a.currencyid=e.currencyid "
				+ " join d_user f on a.enteruserid=f.id "
				+ " where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//主账号id
		if(!BeanUtils.isEmpty(mq.getParentid())){
			sql +=" and f.parentid=? ";
			params.add(mq.getParentid());
		}
		//子账号id
		if(!BeanUtils.isEmpty(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql+=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql+=" and a.product = ?";
			params.add(mq.getProduct());
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties like ?";
			params.add("%"+mq.getVarieties()+"%");
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec like ?";
			params.add("%"+mq.getSpec()+"%");
		}
		//供应商 supplier
		if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
			sql +=" and a.supplier like ?";
			params.add("%"+mq.getSupplier()+"%");
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand like ?";
			params.add("%"+mq.getBrand()+"%");
		}
		//销售商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor like ?";
			params.add("%"+mq.getVendor()+"");
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ?";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =?";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=?";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ?";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ?";
			params.add(mq.getMaxprice());
		}
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =?";
			params.add(mq.getStatus());
		}
		String sortname = requestParameters.getSort();//获取排序字段
		String sortorder = requestParameters.getOrder();//获取排序方式
		if(null != sortname && !"".equals(sortname)){
			sql += " order by "+sortname+" "+sortorder+","
					+ "marketdate desc,parentid asc,"
					+ "convert( enterusername using gbk ) collate gbk_chinese_ci asc,"
					+ "markettype asc,"
					+ "convert( countryname using gbk ) collate gbk_chinese_ci asc,"
					+ "convert( productname using gbk ) collate gbk_chinese_ci asc,currentdate desc ";
		}else{
			//时间 公司名称 市场 国家 品名 排序
			sql += " order by a.marketdate desc,f.parentid asc,"
					+ "convert( enterusername using gbk ) collate gbk_chinese_ci asc,"
					+ "a.markettype asc, "
					+ "convert( countryname using gbk ) collate gbk_chinese_ci asc,"
					+ " convert( productname using gbk ) collate gbk_chinese_ci asc,a.currentdate desc ";
		}
		List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		return list;
	}
	@Override
	public long getDatas4WebAllCount(Marketquotation mq, Page page,
			RequestParameters requestParameters) {
		String sql="select count(1) "
				+ " from  marketquotation a "
				+ " join users b on a.enteruserid=b.userid "
				+ " join country c on  a.country=c.countryid "
				+ " join product d on a.product=d.productid "
				+ " left join currency e on a.currencyid=e.currencyid "
				+ " join d_user f on a.enteruserid=f.id "
				+ " where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//主账号id
		if(!BeanUtils.isEmpty(mq.getParentid())){
			sql +=" and f.parentid=? ";
			params.add(mq.getParentid());
		}
		//子账号id
		if(!BeanUtils.isEmpty(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql+=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql+=" and a.product = ?";
			params.add(mq.getProduct());
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties like ?";
			params.add("%"+mq.getVarieties()+"%");
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec like ?";
			params.add("%"+mq.getSpec()+"%");
		}
		//供应商 supplier
		if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
			sql +=" and a.supplier like ?";
			params.add("%"+mq.getSupplier()+"%");
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand like ?";
			params.add("%"+mq.getBrand()+"%");
		}
		//销售商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor like ?";
			params.add("%"+mq.getVendor()+"");
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ?";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =?";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=?";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ?";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ?";
			params.add(mq.getMaxprice());
		}
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =?";
			params.add(mq.getStatus());
		}
/*		String sortname = requestParameters.getSort();//获取排序字段
		String sortorder = requestParameters.getOrder();//获取排序方式
		if(null != sortname && !"".equals(sortname)){
			sql += " order by a."+sortname+" "+sortorder+",a.currentdate desc ";
		}else{
			//时间倒序
			sql += " order by a.marketdate desc,a.currentdate desc ";
		}*/
		return JdbcUtils.count( sql, params.toArray());
	}
	@Override
	public int getDatasCount(Marketquotation mq) {
		String sql="select count(1) from  marketquotation a join users b on a.enteruserid=b.userid where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(mq.getCountry() !=null && !"".equals(mq.getCountry())){
			sql+=" and a.country = ?";
			params.add(mq.getCountry());
		}
		if(mq.getProduct() !=null && !"".equals(mq.getProduct())){
			sql+=" and a.product = ?";
			params.add(mq.getProduct());
		}
		//品种
		if(mq.getVarieties() != null && !"".equalsIgnoreCase(mq.getVarieties() )){
			sql +=" and a.varieties = ?";
			params.add(mq.getVarieties());
		}
		//规格 spec
		if(mq.getSpec() !=null && !"".equalsIgnoreCase(mq.getSpec())){
			sql +=" and a.spec = ?";
			params.add(mq.getSpec());
		}
		//供应商 supplier
		if(mq.getSupplier() !=null && !"".equalsIgnoreCase(mq.getSupplier())){
			sql +=" and a.supplier =? ";
			params.add(mq.getSupplier());
		}
		//品牌 brand
		if(mq.getBrand() !=null && !"".equalsIgnoreCase(mq.getBrand())){
			sql +=" and a.brand = ?";
			params.add(mq.getBrand());
		}
		//销售商 vendor
		if(mq.getVendor() !=null && !"".equalsIgnoreCase(mq.getVendor())){
			sql +=" and a.vendor =? ";
			params.add(mq.getVendor());
		}
		//板数 platenum
		if(mq.getPlatenum() !=null){
			sql +=" and a.platenum = ?";
			params.add(mq.getPlatenum());
		}
		//运输方式 transport
		if(mq.getTransport() !=null && !"-1".equals(mq.getTransport()+"")){
			sql +=" and a.transport =?";
			params.add(mq.getTransport());
		}
		//当前用户enteruserid
		if(mq.getEnteruserid() !=null && !"".equals(mq.getEnteruserid())){
			sql +=" and a.enteruserid=? ";
			params.add(mq.getEnteruserid());
		}
		//市场类型 markettype
		if(mq.getMarkettype() !=null){
			sql +=" and a.markettype=?";
			params.add(mq.getMarkettype());
		}
		//时间范围 marketdate
		if(mq.getStartTime() !=null && !"".equals(mq.getStartTime())){
			sql +=" and a.marketdate >= ?";
			params.add(mq.getStartTime());
		}
		if(mq.getEndTime() !=null && !"".equals(mq.getEndTime())){
			sql +=" and a.marketdate <= ?";
			params.add(mq.getEndTime());
		}
		//价格 minprice maxprice 
		if(mq.getMinprice() !=null){
			sql +=" and a.minprice >= ?";
			params.add(mq.getMinprice());
		}
		if(mq.getMaxprice() !=null){
			sql += " and a.maxprice <= ?";
			params.add(mq.getMaxprice());
		}
		if(mq.getStatus() !=null && !"".equals(mq.getStatus())){
			sql +=" and a.status =?";
			params.add(mq.getStatus());
		}
		//时间倒序
		sql += " order by a.marketdate desc ";
	//	List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		Long count=JdbcUtils.count(sql, params.toArray());
		return count.intValue();
	}
	
	@Override
	public List<Marketquotation> getDimDatas(Marketquotation mq,String value, Page page) {
		String sql="select a.*,b.nickname as enterusername,c.countrycnname countryname, d.productcnname productname "
				+ "from marketquotation a "
				+ "join users b on a.enteruserid=b.userid "
				+ "join country c on  a.country=c.countryid "
				+ "join product d on a.product=d.productid "
				+ "where 1=1 and a.enteruserid =? and a.markettype=?  and a.status=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(mq.getEnteruserid());
		params.add(mq.getMarkettype());
		params.add("1");
		//根据市场日期进行过滤
		if(!BeanUtils.isEmpty(mq.getMarketdate())){
			sql +=" and a.marketdate=? ";
			params.add(mq.getMarketdate());
		}
		//CONVERT (productname USING gbk) COLLATE  gbk_chinese_ci ASC
		sql +=" order by CONVERT (a."+value+" USING gbk) COLLATE  gbk_chinese_ci asc,a.marketdate desc ";
		List<Marketquotation> list=JdbcUtils.query_limit(Marketquotation.class, sql,page, params.toArray());
		return list;
	}
	@Override
	public List<Country> getCountryList(String productid) {
		//国家按照中文首字母排序a-z
		//String sql="select * from country ORDER BY CONVERT (countryname USING gbk) COLLATE  gbk_chinese_ci ASC ";
		String sql ="select  a.countryproductid,b.countryid,b.countrycode,b.countrycnname as countryname "
				+ "from countryproduct a "
				+ "join country b on a.countryid=b.countryid "
				+ "where a.productid=? ORDER BY CONVERT (b.countrycnname USING gbk) COLLATE  gbk_chinese_ci ASC ";
		return JdbcUtils.query(Country.class, sql,productid);
	}

	@Override
	public List<Product> getProductList() {
		//品名按照中文首字母排序 a-z
	String sql ="select productid,productcode,productcnname as productname,currentdate from product ORDER BY CONVERT (productcnname USING gbk) COLLATE  gbk_chinese_ci ASC";
		return JdbcUtils.query(Product.class, sql);
	}
	@Override
	public List<Product> getProductList(String userid, int fieldtype) {
		String sql="select b.productid,b.productcnname productname,b.productcode from cominfo a join product b "
				+ "on a.fieldvalue=b.productid where a.fieldvalue=? and a.username=? order by a.lastdate desc";
		List<Product> lists = JdbcUtils.query(Product.class, sql, fieldtype,userid);
		if(null !=lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	@Override
	/**
	 * 字段类型0:国家;1:品名;2:品种;3:规格;4:销售商;5:供应商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数
	 * 5销售商 supplier
	 * 6品牌 brand
	 * 2品种 varieties
	 */
	public List<Cominfo> getCominfoList(String username,int num, int fieldtype,String fieldvalue,String country,String product) {
		String sql="select * from cominfo where 1=1 and fieldtype=? and username=? "; //and fieldvalue like ? order by times desc limit ? ";
		List<Object> params=new ArrayList<Object>();
		//销售商(直接选择商品销售信息,不需要和国家产品关联)
		params.add(fieldtype);
		params.add(username);
		if(fieldvalue !=null && !"".equalsIgnoreCase(fieldvalue)){
			sql +=" and fieldvalue like ? ";
			params.add("%"+fieldvalue+"%");
		}
		if(fieldtype == Constants.FLAG_TYPE_4){
			sql +=" order by lastdate desc limit ? ";
		}else if(fieldtype == Constants.FLAG_TYPE_2 || fieldtype==Constants.FLAG_TYPE_6){
			sql +=" and country=? and product=? ";
			params.add(country);
			params.add(product);
			sql +=" order by initial asc limit ? ";
		}else{
			sql +="and country=? and product=? ";
			params.add(country);
			params.add(product);
			sql +=" order by lastdate desc limit ? ";
		}
		params.add(num);
		List<Cominfo> list=JdbcUtils.query(Cominfo.class, sql, params.toArray());
		return list;
	}
	@Override
	public List<Cominfo> getCominfoList4Web(Cominfo info, int num) {
		String sql="select  distinct a.fieldvalue from cominfo a join d_user b on a.username=b.id  "
				+ "where 1=1 and a.fieldtype=?"; //and fieldvalue like ? order by times desc limit ? ";
		List<Object> params=new ArrayList<Object>();
		//销售商(直接选择商品销售信息,不需要和国家产品关联)
		params.add(info.getFieldtype());
		if(!BeanUtils.isEmpty(info.getUsername())){
			sql +=" and a.username=? ";
			params.add(info.getUsername());
		}
		if(!BeanUtils.isEmpty(info.getParentid())){
			sql +=" and b.parentid=? ";
			params.add(info.getParentid());
		}
		if(!BeanUtils.isEmpty(info.getFieldvalue())){
			sql +=" and fieldvalue like ? ";
			params.add("%"+info.getFieldvalue()+"%");
		}
		if(info.getFieldtype() == Constants.FLAG_TYPE_4){
			sql +=" order by lastdate desc limit ? ";
		}else if(info.getFieldtype()  == Constants.FLAG_TYPE_2 || info.getFieldtype() ==Constants.FLAG_TYPE_6){
			sql +=" and country=? and product=? ";
			params.add(info.getCountry());
			params.add(info.getProduct());
			sql +=" order by initial asc limit ? ";
		}else{
			sql +="and country=? and product=? ";
			params.add(info.getCountry());
			params.add(info.getProduct());
			sql +=" order by lastdate desc limit ? ";
		}
		params.add(num);
		List<Cominfo> list=JdbcUtils.query(Cominfo.class, sql, params.toArray());
		return list;
	}

	@Override
	public Map<String,Object> insertCominfo(List<Cominfo> cominfolist) {
		//判断表中是否有记录,若无insert插入记录times+1
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			JdbcUtils.beginTransaction();
			String querySql="select count(1) from cominfo where username=? and country=? and product=? and fieldtype=? and fieldvalue=? ";
			String querySql4="select count(1) from cominfo where username=? and fieldtype=? and fieldvalue=?";
			String insertSql="insert into cominfo(cominfoid,username,country,product,fieldtype,fieldvalue,times,currentdate,lastdate,initial)values(?,?,?,?,?,?,?,now(),now(),?)";
			String updateSql="update cominfo set times=times+1,lastdate=now() where username=?  and fieldtype=? and fieldvalue=? ";//非国家品名更新
			String updateSql2="update cominfo set times=times+1,lastdate=now() where username=?  and fieldtype=? and fieldvalue=? and country=? and product=? ";//带国家品名更新
			long num=0L;
			for(Cominfo c:cominfolist){
				if(null == c.getFieldvalue() || "".equals(c.getFieldvalue().trim())){
					continue;
				}
				//销售商
				if(c.getFieldtype()==Constants.FLAG_TYPE_4 || c.getFieldtype() == Constants.FLAG_TYPE_1 ){
					num=JdbcUtils.count(querySql4, c.getUsername(),c.getFieldtype(),c.getFieldvalue());
				}else{
					num=JdbcUtils.count(querySql, c.getUsername(),c.getCountry(),c.getProduct(),c.getFieldtype(),c.getFieldvalue());
				}
				if(num>0){
					//更新操作
					if(c.getFieldtype()==Constants.FLAG_TYPE_4 || c.getFieldtype() == Constants.FLAG_TYPE_1 ){
						JdbcUtils.execute(updateSql, c.getUsername(),c.getFieldtype(),c.getFieldvalue());//销售商 不用根据国家品名过滤
					}else{
						JdbcUtils.execute(updateSql2, c.getUsername(),c.getFieldtype(),c.getFieldvalue(),c.getCountry(),c.getProduct());
					}
					map.put("repeat", true);
				}else{
					//做插入操作增加首字母插入
					String initial="";
					if(null != c.getFieldvalue() && !"".equals(c.getFieldvalue())){
						 initial=ChinaInitial.getPYIndexStr(c.getFieldvalue(), true);
						 initial=initial.substring(0, 1);
					}
					JdbcUtils.execute(insertSql, c.getCominfoid(),c.getUsername(),c.getCountry(),c.getProduct(),c.getFieldtype(),c.getFieldvalue(),1,initial);
					map.put("flag", true);
					map.put("fieldvalue",c.getFieldvalue());
				}
			}
			JdbcUtils.commitTransaction();
			return map;
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			logger.error("价格行情添加用户常用信息发生异常", e);
			return null;
		}
		
	}
	
		@Override
		public boolean deleteCominfo(String cominfoid) {
			try {
				String sql ="delete from cominfo where cominfoid=? ";
				JdbcUtils.execute(sql, cominfoid);
				return true;
			} catch (Exception e) {
				logger.error("删除常用信息统计表信息异常", e);
				return false;
			}
		}
	@Override
	/**
	 * 整合到生鲜港后telnum替换成phonenum
	 */
	public List<CustomsInfo> getForwardPerson(String queryname,String userid) {
		//中文名字排序
		//String sql="select * from customsinfo where username like ?  ORDER BY CONVERT (username USING gbk) COLLATE  gbk_chinese_ci ASC";
		String sql="select a.*,b.userid as id,b.nickname as realname  from customsinfo a "
				+ "join users b on a.tel=b.phonenum "
				+ "where 1=1 and a.userid=? and a.type=? ";
		if(queryname !=null && !"".equals(queryname)){
			sql +=" and a.company like ? or  a.username like ?";
		}
		sql +=" order by a.initial asc";
		return JdbcUtils.query(CustomsInfo.class, sql,userid,Constants.TYPE_0, "%"+queryname+"%","%"+queryname+"%");
	}
	@Override
	public boolean getAuthority(String userid, String code) {
/*		String sql =" select m.id as id, m.name as name,m.code as code, m.icon as icon, m.actionexp as actionexp, m.parentid as "+
						"parentid from sys_menu m where exists (select * from d_rolemenu rm left outer join d_userrole "+
						"ur on ur.roleid = rm.roleid where m.id = rm.menuid and rm.isenable = 'Y' and ur.userid = ?) and code=? ";*/
		String sql="select m.id as id, m.name as name,m.code as code, m.icon as icon, m.actionexp as actionexp, m.parentid as "+
						"parentid from sys_menu m where exists (select * from d_usermenu rm where m.id = rm.menuid and "+
						"rm.isenable = 'Y' and rm.userid = ? ) and code=? ";
			List<Map<String,Object>> lists=JdbcUtils.queryMap(sql, userid,code);
			if(null != lists && !lists.isEmpty()){
				return true;
			}
			return false;
	}
	@Override
	public List<Map<String, Object>> getAuthority2New(String userid, String code) {
		//根据code值获取当前菜单id
		String getparentid="select id from sys_menu where code=? ";
		String sql="select m.code as menucode,m.name as menuname, dum.isenable as isenable from d_usermenu dum left outer join d_user u "
				+ "on dum.userid = u.id left outer join sys_menu m on dum.menuid = m.id "
				+ "where ( dum.userid =?) and m.parentid = ?";
		String parentid=JdbcUtils.getMap(getparentid, code).get("id")+"";
		if(!"".equals(parentid)){
			List<Map<String,Object>> lists=JdbcUtils.queryMap(sql, userid,parentid);
			return lists;
		}
		return null;
	}
	@Override
	public boolean checkCustomsName(String userid,String name,String customsinfoid) {
		String sql="select count(1) from customsinfo where userid=? and username=? and type=? and customsinfoid !=? ";
		return JdbcUtils.count(sql, userid,name,Constants.TYPE_0,customsinfoid)>0;
	}
	@Override
	public boolean checkCustomsTel(String userid,String tel, String customsinfoid) {
		String sql="select count(1) from customsinfo where userid=? and tel=? and type=? and customsinfoid !=? ";
		return JdbcUtils.count(sql, userid,tel,Constants.TYPE_0,customsinfoid) > 0;//表示号码不可用
	}
	@Override
	public boolean checkUser(String tel) {
		String sql2="select count(1) from users where phonenum=?";
		return JdbcUtils.count(sql2, tel)>0;//标示存在此用户账号
	}
	@Override
	public boolean confirm(String id) {
		String sql="update marketquotation set status=?,modifydate=now() where mq_id in ("+id+") ";
		return JdbcUtils.execute(sql,"1") > 0;
	}
	@Override
	public List<Marketquotation> messageBox(String userid,String sort,Integer markettype,Integer datenum,Page page) {
		String sql="select a.*,b.nickname as enterusername,c.countrycnname countryname, d.productcnname productname "
				+ " from marketquotation a "
				+ " join users b on a.transmituser=b.userid "
				+ " join country c on  a.country=c.countryid "
				+ " join product d on a.product=d.productid "
				+" where 1=1 and a.enteruserid =? and a.markettype=? and a.status=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(markettype);
		params.add(Constants.FLAG_ISTRANSMIT_0);
		//查询天数
		if(datenum>0){
			sql +=" and a.currentdate >= ?";
			params.add(DateUtils.getDate2SStr(DateUtils.getPastdayDate(datenum)));
		}
		//按照提条件进行排序
		if(sort !=null && !"".equals(sort)){
			sql +=" order by CONVERT (a."+sort+" USING gbk) COLLATE  gbk_chinese_ci asc,a.marketdate desc ";
		}else{
			//没有查询条件
			sql +=" order by a.currentdate desc";
		}
		//modify 20160923 应客户要求收件箱去掉分页
		//List<Marketquotation> lists=JdbcUtils.query_limit(Marketquotation.class, sql, page, params.toArray());
		List<Marketquotation> lists = JdbcUtils.query(Marketquotation.class, sql, params.toArray());
		if(null !=lists && !lists.isEmpty()){
			//获取转发人在常用信息统计表中的名称
			String username=null;
			for(Marketquotation m:lists){
				username=this.getEnterUserName(userid, m.getTransmituser());
				if( username !=null){
					m.setEnterusername(username);
				}
			}
			return lists;
		}
		return null;
	}
	@Override
	public boolean changePassword(String userid,String newPwd) {
		try {
		String updateUser="update users set password=? where userid=?";
		String d_user="update d_user set password=? where id=? ";
			JdbcUtils.beginTransaction();
			JdbcUtils.execute(updateUser,newPwd,userid);
			JdbcUtils.execute(d_user,newPwd,userid);
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			logger.error("修改用户密码发生异常!", e);
			JdbcUtils.rollbackTransaction();
			return false;
		}
	}
@Override
public String getEnterUserName(String userid, String enteruserid) {
	//根据录入人id获取当前用户录入人命名(常用列表中存在的)
	String sql="select b.username from users a join customsinfo b on a.phonenum=b.tel where b.userid=? and b.type=? and a.userid=?";
	//String sql2="select username from users where UserID=?";
	Map<String,Object> map=JdbcUtils.getMap(sql, userid,Constants.TYPE_0,enteruserid);
	if(null !=map && null !=map.get("username") && !"".equals(map.get("username"))){
		return map.get("username")+"";
	}
	return null;
}
@Override
public void export4CN(List<Marketquotation> mqlist ,String[] cellHeadArray){
	  // 第一步，创建一个webbook，对应一个Excel文件
    HSSFWorkbook wb = new HSSFWorkbook();
    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
    HSSFSheet sheet = wb.createSheet("市场行情表");
    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    HSSFRow row = sheet.createRow(0);
    // 第四步，创建单元格，并设置值表头 设置表头居中
    HSSFCellStyle headStyle = wb.createCellStyle();
    row.setHeight((short) 600);
    // 创建一个居中格式
    headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 蓝色背景色
    headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

    HSSFFont font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    headStyle.setFont(font);

    // 创建String单元格，并设置值表头 设置表头居中
    HSSFCellStyle dataStyle = wb.createCellStyle();
    // 创建一个居中格式
    dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

    // 创建日期单元格，并设置值表头 设置表头居中
    HSSFDataFormat format = wb.createDataFormat();
    HSSFCellStyle dateStyle = wb.createCellStyle();
    // 创建一个居中格式
    dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));

    // 创建Float单元格
    HSSFCellStyle floatStyle = wb.createCellStyle();
    floatStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
    floatStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

    HSSFCell cell = null;
    //设置文件标题
    for (int i = 0; i < cellHeadArray.length; i++) {
    	sheet.setColumnWidth(i, 4000);
        cell = row.createCell(i);
        cell.setCellValue(cellHeadArray[i]);
        cell.setCellStyle(headStyle);
    }
    //设置数据
    if(null !=mqlist && !mqlist.isEmpty()){
    	int len= mqlist.size();
    	for(int i=0; i< len; i++ ){
    		Marketquotation mq=mqlist.get(i);
    		//因为第0行已经设置标题了,所以从行2开始写入数据
    		row=sheet.createRow(i+1);
    		//周
    		cell = row.createCell(0);
    		cell.setCellValue(mq.getMarketdate()==null? "":this.getWeekOfYear(mq.getMarketdate()));
    		cell.setCellStyle(dataStyle);
    		//日期
    		cell = row.createCell(1);
    		cell.setCellValue(mq.getMarketdate());
    		cell.setCellStyle(dataStyle);
    		//录入人
    		cell = row.createCell(2);
    		cell.setCellValue(mq.getEnterusername());
    		cell.setCellStyle(dataStyle);
    		//市场类型
    		cell = row.createCell(3);
    		String markettype=mq.getMarkettype()+"";
    		//0:辉展市场;1:江南市场
    		if("0".equals(markettype)){
    			markettype="辉展市场";
    		}else if("1".equals(markettype)){
    			markettype = "江南市场";
    		}else{
    			markettype="";
    		}
    		cell.setCellValue(markettype);
    		cell.setCellStyle(dataStyle);
    		//国家
    		cell = row.createCell(4);
    		cell.setCellValue(mq.getCountryname());
    		cell.setCellStyle(dataStyle);
    		//品名
    		cell = row.createCell(5);
    		cell.setCellValue(mq.getProductname());
    		cell.setCellStyle(dataStyle);
    		//运输方式
    		cell = row.createCell(6);
    		String transport="";
    		transport = mq.getTransport()==null ? "":mq.getTransport()+"";
    		if("0".equals(transport)){
    			transport="海运";
    		}else if("1".equals(transport)){
    			transport="空运";
    		}else if("2".equals(transport)){
    			transport="陆运";
    		}
    		cell.setCellValue(transport);
    		cell.setCellStyle(dataStyle);
    		//销售商
    		cell =row.createCell(7);
    		cell.setCellValue(mq.getSupplier());
    		cell.setCellStyle(dataStyle);
    		//供应商
    		cell = row.createCell(8);
    		cell.setCellValue(mq.getVendor());
    		cell.setCellStyle(dataStyle);
    		//品牌
    		cell = row.createCell(9);
    		cell.setCellValue(mq.getBrand());
    		cell.setCellStyle(dataStyle);
    		//品种
    		cell = row.createCell(10);
    		cell.setCellValue(mq.getVarieties());
    		cell.setCellStyle(dataStyle);
    		//规格
    		cell = row.createCell(11);
    		cell.setCellValue(mq.getSpec());
    		cell.setCellStyle(dataStyle);
    		//重量
    		cell = row.createCell(12);
    		cell.setCellValue(mq.getWeight());
    		cell.setCellStyle(dataStyle);
    		//币别
    		cell = row.createCell(13);
    		cell.setCellValue(mq.getCurrencycnname());
    		cell.setCellStyle(dataStyle);
    		//价格
    		cell = row.createCell(14);
    		String minprice=mq.getMinprice()==null ?"":mq.getMinprice()+"";
    		String maxprice=mq.getMaxprice()==null ? "":mq.getMaxprice()+"";
    		cell.setCellValue(minprice+"-"+maxprice);
    		cell.setCellStyle(dataStyle);
    		//品质
    		cell = row.createCell(15);
    		cell.setCellValue(mq.getQuality());
    		cell.setCellStyle(dataStyle);
    		//销售描述
    		cell = row.createCell(16);
    		cell.setCellValue(mq.getSailedescribe());
    		cell.setCellStyle(dataStyle);
    		//包装
    		cell = row.createCell(17);
    		cell.setCellValue(mq.getPackagetype());
    		cell.setCellStyle(dataStyle);
    		//包装周
    		cell = row.createCell(18);
    		cell.setCellValue("");
    		cell.setCellStyle(dataStyle);
    		//包装厂
    		cell = row.createCell(19);
    		cell.setCellValue(mq.getPackingplant());
    		cell.setCellStyle(dataStyle);
    		//柜号
    		cell = row.createCell(20);
    		cell.setCellValue(mq.getCntrno());
    		cell.setCellStyle(dataStyle);
    		//板数
    		cell = row.createCell(21);
    		String platenum=mq.getPlatenum()==null ? "":mq.getPlatenum()+"";
    		cell.setCellValue(platenum);
    		cell.setCellStyle(dataStyle);
    		//农场
    		cell = row.createCell(22);
    		cell.setCellValue(mq.getFarm());
    		cell.setCellStyle(dataStyle);
    		//新旧
    		cell = row.createCell(23);
    		cell.setCellValue(mq.getNewold());
    		cell.setCellStyle(dataStyle);
    	}
    }
    
    HttpServletResponse response = null;// 创建一个HttpServletResponse对象
    OutputStream out = null;// 创建一个输出流对象
    try {
        response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
        out = response.getOutputStream();//

        response.setHeader("Content-disposition", "attachment; filename=Market"+DateUtils.getDate2All(new Date())+".xls");// filename是下载的xls的名，建议最好用英文
        response.setContentType("application/msexcel;charset=utf-8");// 设置类型
        response.setHeader("Pragma", "No-cache");// 设置头
        response.setHeader("Cache-Control", "no-cache");// 设置头
        response.setDateHeader("Expires", 0);// 设置日期头
        wb.write(out);
        out.flush();
        wb.write(out);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }  
}
@Override
public void export4EN(List<Marketquotation> mqlist ,String[] cellHeadArray){
	  // 第一步，创建一个webbook，对应一个Excel文件
    HSSFWorkbook wb = new HSSFWorkbook();
    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
    HSSFSheet sheet = wb.createSheet("market");
    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    HSSFRow row = sheet.createRow(0);
    // 第四步，创建单元格，并设置值表头 设置表头居中
    HSSFCellStyle headStyle = wb.createCellStyle();
    row.setHeight((short) 600);
    // 创建一个居中格式
    headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 蓝色背景色
    headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

    HSSFFont font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    headStyle.setFont(font);

    // 创建String单元格，并设置值表头 设置表头居中
    HSSFCellStyle dataStyle = wb.createCellStyle();
    // 创建一个居中格式
    dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

    // 创建日期单元格，并设置值表头 设置表头居中
    HSSFDataFormat format = wb.createDataFormat();
    HSSFCellStyle dateStyle = wb.createCellStyle();
    // 创建一个居中格式
    dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));

    // 创建Float单元格
    HSSFCellStyle floatStyle = wb.createCellStyle();
    floatStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
    floatStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

    HSSFCell cell = null;
    //设置文件标题
    for (int i = 0; i < cellHeadArray.length; i++) {
     	sheet.setColumnWidth(i, 4000);
        cell = row.createCell(i);
        cell.setCellValue(cellHeadArray[i]);
        cell.setCellStyle(headStyle);
    }
    //设置数据
    if(null !=mqlist && !mqlist.isEmpty()){
    	int len= mqlist.size();
    	for(int i=0; i< len; i++ ){
    		Marketquotation mq=mqlist.get(i);
    		//因为第0行已经设置标题了,所以从行2开始写入数据
    		row=sheet.createRow(i+1);
    		//周数
    		cell = row.createCell(0);
    		cell.setCellValue(mq.getMarketdate()==null? "":this.getWeekOfYear(mq.getMarketdate()));
    		cell.setCellStyle(dataStyle);
    		//日期
    		cell = row.createCell(1);
    		cell.setCellValue(mq.getMarketdate());
    		cell.setCellStyle(dataStyle);
    		//录入人 modify by chenzj 2016-08-31
    		cell = row.createCell(2);
    		cell.setCellValue(mq.getEnterusername());
    		cell.setCellStyle(dataStyle);
    		//市场类型
    		cell = row.createCell(3);
    		String markettype=mq.getMarkettype()+"";
    		//0:辉展市场;1:江南市场
    		if("0".equals(markettype)){
    			markettype="huizhan";
    		}else if("1".equals(markettype)){
    			markettype = "jiangnan";
    		}else{
    			markettype="";
    		}
    		cell.setCellValue(markettype);
    		cell.setCellStyle(dataStyle);
    		//国家
    		cell = row.createCell(4);
    		cell.setCellValue(mq.getCountryenname());
    		cell.setCellStyle(dataStyle);
    		//品名
    		cell = row.createCell(5);
    		cell.setCellValue(mq.getProductenname());
    		cell.setCellStyle(dataStyle);
    		//运输方式
    		cell = row.createCell(6);
    		String transport="";
    		transport = mq.getTransport()==null ? "":mq.getTransport()+"";
    		if("0".equals(transport)){
    			transport="sea";
    		}else if("1".equals(transport)){
    			transport="air";
    		}else if("2".equals(transport)){
    			transport="truck";
    		}
    		cell.setCellValue(transport);
    		cell.setCellStyle(dataStyle);
    		//销售商
    		cell =row.createCell(7);
    		cell.setCellValue(mq.getSupplier());
    		cell.setCellStyle(dataStyle);
    		//供应商
    		cell = row.createCell(8);
    		cell.setCellValue(mq.getVendor());
    		cell.setCellStyle(dataStyle);
    		//品牌
    		cell = row.createCell(9);
    		cell.setCellValue(mq.getBrand());
    		cell.setCellStyle(dataStyle);
    		//品种
    		cell = row.createCell(10);
    		cell.setCellValue(mq.getVarieties());
    		cell.setCellStyle(dataStyle);
    		//规格
    		cell = row.createCell(11);
    		cell.setCellValue(mq.getSpec());
    		cell.setCellStyle(dataStyle);
    		//重量
    		cell = row.createCell(12);
    		cell.setCellValue(mq.getWeight());
    		cell.setCellStyle(dataStyle);
    		//币别
    		cell = row.createCell(13);
    		cell.setCellValue(mq.getCurrencyenname());
    		cell.setCellStyle(dataStyle);
    		//价格
    		cell = row.createCell(14);
    		String minprice=mq.getMinprice()==null ?"":mq.getMinprice()+"";
    		String maxprice=mq.getMaxprice()==null ? "":mq.getMaxprice()+"";
    		cell.setCellValue(minprice+"-"+maxprice);
    		cell.setCellStyle(dataStyle);
    		//品质
    		cell = row.createCell(15);
    		cell.setCellValue(mq.getQuality());
    		cell.setCellStyle(dataStyle);
    		//销售描述
    		cell = row.createCell(16);
    		cell.setCellValue(mq.getSailedescribe());
    		cell.setCellStyle(dataStyle);
    		//包装
    		cell = row.createCell(17);
    		cell.setCellValue(mq.getPackagetype());
    		cell.setCellStyle(dataStyle);
    		//包装周
    		//包装
    		cell = row.createCell(18);
    		cell.setCellValue("");
    		cell.setCellStyle(dataStyle);
    		//包装厂
    		cell = row.createCell(19);
    		cell.setCellValue(mq.getPackingplant());
    		cell.setCellStyle(dataStyle);
    		//柜号
    		cell = row.createCell(20);
    		cell.setCellValue(mq.getCntrno());
    		cell.setCellStyle(dataStyle);
    		//板数
    		cell = row.createCell(21);
    		String platenum=mq.getPlatenum()==null?"":mq.getPlatenum()+"";
    		cell.setCellValue(platenum);
    		cell.setCellStyle(dataStyle);
    		//农场
    		cell = row.createCell(22);
    		cell.setCellValue(mq.getFarm());
    		cell.setCellStyle(dataStyle);
    		//新旧
    		cell = row.createCell(23);
    		cell.setCellValue(mq.getNewold());
    		cell.setCellStyle(dataStyle);
    	}
    }
    
    HttpServletResponse response = null;// 创建一个HttpServletResponse对象
    OutputStream out = null;// 创建一个输出流对象
    try {
        response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
        out = response.getOutputStream();//

        response.setHeader("Content-disposition", "attachment; filename=Market"+DateUtils.getDate2All(new Date())+".xls");// filename是下载的xls的名，建议最好用英文
        response.setContentType("application/msexcel;charset=utf-8");// 设置类型
        response.setHeader("Pragma", "No-cache");// 设置头
        response.setHeader("Cache-Control", "no-cache");// 设置头
        response.setDateHeader("Expires", 0);// 设置日期头
        wb.write(out);
        out.flush();
        wb.write(out);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }  
}
	@Override
	public boolean addAttention(String userid,List<Attention> attentionlist) {
		try {
			JdbcUtils.beginTransaction();
			//删除本用户下所有的用户关注
			String sql="delete from attention where userid=?";
			JdbcUtils.execute(sql, userid);
			//添加用户所有选择的关注
			JdbcUtils.insertIsNull(attentionlist);
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			logger.error("市场行情用户添加品名关注失败!", e);
			return false;
		}
	}
	
	@Override
	public List<Attention> getAttentionList(String userid) {
		String sql="select a.id,a.productid,b.productcnname productname from attention a join product b on a.productid=b.ProductID "
				+ "where a.userid=? order by CONVERT (b.productcnname USING gbk) COLLATE  gbk_chinese_ci asc";
		List<Attention> lists=JdbcUtils.query(Attention.class, sql,userid);
		String initial="";//首字母
		if(null !=lists && !lists.isEmpty()){
			for(Attention att:lists){
				if(null !=att.getProductname() && !"".equals(att.getProductname())){
					initial = ChinaInitial.getPYIndexStr(att.getProductname(), true);
				}
				att.setInitial(initial.substring(0, 1));
			}
			return lists;
		}
		return null;
	}
	@Override
	public List<Product> getProductList(String userid) {
		String sql="select a.productid ,a.productcode,a.productcnname productname,IF(userid is not null,'Y','N') as status "
				+ "from product a left join attention b on a.ProductID=b.productid and b.userid=? "
				+ "order by CONVERT (a.productcnname USING gbk) COLLATE  gbk_chinese_ci asc";
		List<Product> lists=JdbcUtils.query(Product.class, sql, userid);
		if(null !=lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	@Override
	public boolean addTemplate(Template template) {
		try {
			if(null != template.getId() && !"".equals(template.getId())){	
				//JdbcUtils.updateEntity(template);
			JdbcUtils.updateNotNull(template);
			}else{
				template.setId(ObjectId.StringId());
			//	template.setCurrentdate(new Date());//创建时间
				JdbcUtils.insert(template);
			}
			return true;
		} catch (Exception e) {
			logger.error("市场行情用户添加模板报错!", e);
			return false;
		}
	}
	
	@Override
	public boolean deleteTemplate(String id) {
		try {
			String sql ="delete from template where id in ("+id+") ";
			JdbcUtils.execute(sql);
			return true;
		} catch (Exception e) {
			logger.error("删除用户模板异常!", e);
		}
		return false;
	}
	@Override
	public List<Template> getTemplateList(String userid) {
		String sql="select a.*,b.productcnname productname,c.countrycnname countryname from template a "
						+"	left join product b on a.productid=b.productid "
						+"	left join country c on a.countryid=c.countryid "
						+"	where a.userid=?  order by convert( c.countrycnname using gbk ) collate gbk_chinese_ci asc,"
						+ "convert( b.productcnname using gbk ) collate gbk_chinese_ci asc";
		List<Template>  lists = JdbcUtils.query(Template.class, sql, userid);
		if(null != lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	
	@Override
	public Template getTemplateDetail(Template template) {
		String sql="select a.*,b.productcnname productname,c.countrycnname countryname from template a "
				+"	left join product b on a.productid=b.productid "
				+"	left join country c on a.countryid=c.countryid "
				+ "where a.userid=? and a.countryid=? and a.productid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(template.getUserid());
		params.add(template.getCountryid());
		params.add(template.getProductid());
		return JdbcUtils.get(Template.class, sql, params.toArray());
	}
	@Override
	public boolean templateRepeat(Template template) {
		boolean flag = false;
		String sql="select count(1) from template a "
				+ "where a.userid=? and a.countryid=? and a.productid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(template.getUserid());
		params.add(template.getCountryid());
		params.add(template.getProductid());
		if(null != template.getId()){
			sql +="and a.id != ? ";
			params.add(template.getId());
		}else{
			sql +="and a.id is not null ";
		}
		flag = JdbcUtils.count(sql, params.toArray()) > 0;
		return flag ;
	}
	@Override
	public Template getTemplateDetailById(String id) {
		String sql="select a.*,b.productcnname productname,c.countrycnname countryname from template a "
				+"	left join product b on a.productid=b.productid "
				+"	left join country c on a.countryid=c.countryid "
				+ "where a.id=? ";
		return	JdbcUtils.get(Template.class, sql, id);
	}
	@Override
	public List<Map<String, Object>> getCountryProduct(String userid,String date,Integer markettype) {
		String sql="select distinct a.countryproductid,concat_ws('/',b.countrycnname,c.productcnname) countryproductname "
						+ "from marketquotation a "
						+"	join country b on a.country=b.countryid"
						+"	join product c on a.product=c.productid "
						+ "where 1=1 and a.enteruserid=? and a.marketdate=? and a.status=? and a.markettype=? and a.countryproductid is not null "
						+"	order by convert( b.countrycnname using gbk ) collate gbk_chinese_ci asc";
		List<Map<String, Object>> lists = JdbcUtils.queryMap(sql, userid,date,Constants.FLAG_ISTRANSMIT_1+"",markettype);
		if(null !=lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getDuplicateCountryProductInfo(
			String userid,Integer markettype) {
		String sql="select distinct a.countryproductid,concat_ws('',b.countrycnname,c.productcnname) countryproductname,"
				+ " a.country countryid,a.product productid "
				+ "from marketquotation a "
				+"	join country b on a.country=b.countryid"
				+"	join product c on a.product=c.productid "
				+ "where 1=1 and a.enteruserid=? and a.status=? and a.markettype=? and a.countryproductid is not null "
				+"	order by convert( b.countrycnname using gbk ) collate gbk_chinese_ci asc";
		List<Object> params = Lists.newArrayList();
		params.add(userid);
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		params.add(markettype);
		List<Map<String,Object>> lists = JdbcUtils.queryMap(sql, params.toArray());
		return lists;
	}
	@Override
	public List<Country> getCountryList4Query(String userid, String date) {
		String sql = "select distinct b.* "
						+"	from marketquotation a "
						+"	join country b on a.country=b.countryid"
						+"	where 1=1 and a.enteruserid in ("+userid+") and a.marketdate=? and a.status=? and a.countryproductid is not null "
						+"	order by convert( b.countrycnname using gbk ) collate gbk_chinese_ci asc ";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		List<Country> lists = JdbcUtils.query(Country.class, sql, params.toArray());
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public List<Product> getProductList4Query(String userid, String date) {
		String sql = "select distinct b.* "
				+"	from marketquotation a "
				+"	join product b on a.product=b.productid"
				+"	where 1=1 and a.enteruserid in ("+userid+") and a.marketdate=? and a.status=? and a.countryproductid is not null "
				+"	order by convert( b.productcnname using gbk ) collate gbk_chinese_ci asc ";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		List<Product> lists = JdbcUtils.query(Product.class, sql, params.toArray());
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getSupplier4Query(String userid,String date) {
		String sql="select distinct a.supplier from marketquotation a "
				+ " where 1 = 1 and a.supplier is not null and a.supplier != '' and a.enteruserid  in ("+userid+") and a.marketdate =? and a.status=? "
				+ " order by convert (a.supplier using gbk) collate gbk_chinese_ci asc";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		List<Map<String, Object>> lists = JdbcUtils.queryMap(sql, params.toArray());
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getSupplier4Forward(String userid,	String date,Integer markettype) {
		String sql="select distinct a.supplier from marketquotation a "
				+ "where 1 = 1 and a.supplier is not null and a.supplier != '' and a.enteruserid =? and a.marketdate =? and a.status=?  and a.markettype=? "
				+ "order by convert (a.supplier using gbk) collate gbk_chinese_ci asc";
		List<Map<String, Object>> lists = JdbcUtils.queryMap(sql, userid,date,Constants.FLAG_ISTRANSMIT_1+"",markettype);
		if(null != lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	/**
	 * 
	 * @param id 例如 01#02#03
	 * @return 返回 '01','02','03'
	 */
	public String getIds(String id,String splitRex){
		String [] ids=id.split(splitRex);
		String idsJoin="";
		for(String i:ids){
			idsJoin +="'"+i+"',";
		}
		return idsJoin.substring(0, idsJoin.length()-1);
	}
	@Override
	public List<Marketquotation> getDatas4Forward(Marketquotation mar,int pageSize,int currentPage) {
		String sql="select a.*, c.productcnname productname,b.countrycnname countryname "
				+ "from marketquotation a "
				+"	join country b on a.country=b.countryid "
				+"	join product c on a.product=c.productid "
				+ "where 1=1 and a.enteruserid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(mar.getEnteruserid());
		
		if(null !=mar.getCountryproductid() && !"".equals(mar.getCountryproductid())){
			sql +=" and a.countryproductid in("+this.getIds(mar.getCountryproductid(), ",")+")";
			//params.add(mar.getCountryproductid());
		}
		if(null != mar.getSupplier() && !"".equals(mar.getSupplier())){
			sql +=" and a.supplier in("+this.getIds(mar.getSupplier(), ",")+")";
			//params.add(mar.getSupplier());
		}
		if(null != mar.getMarketdate() && !"".equals(mar.getMarketdate())){
			sql +=" and a.marketdate=? ";
			params.add(mar.getMarketdate());
		}
		if(null !=mar.getMarkettype()){
			sql +=" and a.markettype=?";
			params.add(mar.getMarkettype());
		}
		sql +=" and a.status=? order by a.modifydate desc ";
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		
		List<Marketquotation> lists = JdbcUtils.query_limit(Marketquotation.class, sql, 
				pageSize, currentPage, params.toArray());
		if(null !=lists && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	@Override
	public CountryProduct getCountryProduct(String countryid, String productid) {
		String sql="select t.countryproductid,t.countryid,t.productid from "
				+ "countryproduct t where t.countryid=? and t.productid=? and isdel=? ";
		List<CountryProduct> cplist = JdbcUtils.query(CountryProduct.class, sql,countryid,productid,"1");//1默认
		if(!BeanUtils.isEmpty(cplist)){
			return cplist.get(0);
		}
		return null;
	}
	@Override
	public boolean register(User user) {
		try {
			JdbcUtils.beginTransaction();
			String pwd_md5 = Md5Encoder.encodePassword(user.getPassword());
			user.setPassword(pwd_md5);
			String primary="insert into d_user (id, code, name, password, isusing, parentid) values(?,?,?,?,?,?)";
			String primaryId=ObjectId.StringId();//主账号id
			//生成主账号
			JdbcUtils.execute(primary,primaryId,user.getUsername()+Constants.JOIN_P,
					user.getUsername()+Constants.JOIN_P,pwd_md5,"Y","0");
			//子账号插入users表password用md5加密
			JdbcUtils.insert(user);
			//主账号插入users表
			String primary4users="insert into users(userid,username,password,companycnname)values(?,?,?,?)";
			
			JdbcUtils.execute(primary4users,primaryId,user.getUsername()+Constants.JOIN_P,pwd_md5,user.getCompanycnname());
			//插入子账号d_user表表中数据.
			JdbcUtils.execute(primary,user.getUserid(),user.getUsername(),user.getUsername(),
					pwd_md5,"Y",primaryId);
			//给用分配角色
			String userrole="insert into d_userrole(id, userid, roleid) values(?,?,?)";
			JdbcUtils.execute(userrole, ObjectId.StringId(),primaryId,Constants.ROLEID);
			//给用户赋予菜单
			String getMenu=" select distinct(drm.menuid) as menuid from d_rolemenu drm "
					+ "where drm.roleid in( select distinct roleid from d_userrole where userid=? ) and drm.isenable='Y'";
			
			List<Map<String,Object>> lists = JdbcUtils.queryMap(getMenu, primaryId);
			this.insertUserMenu(lists, user.getUserid());
			
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			logger.error("市场行情用户注册失败!", e);
			return false;
		}
	}
	
	public void insertUserMenu(List<Map<String,Object>> lists,String userid) throws Exception{
		List<DUserMenu> menulist = new ArrayList<DUserMenu>();
		DUserMenu dmenu = null;
		for(Map<String,Object>  map :lists){
			dmenu = new DUserMenu(ObjectId.StringId(), map.get("menuid")+"", userid, "Y");
			menulist.add(dmenu);
		}
		JdbcUtils.insertIsNull(menulist);
		
	}
	@Override
	public boolean repeatUsername(String username) {
		String sql ="select count(1) from users where username=?";
		return JdbcUtils.count(sql, username) > 0;
	}
	@Override
	public boolean repeatPhonenum(String phonenum) {
		String sql = " select count(1) from users where phonenum=? ";
		return JdbcUtils.count(sql, phonenum) > 0;
	}
	@Override
	public List<Currency> getCurrencyList() {
		String sql  ="select * from currency ";
		List<Currency> lists = JdbcUtils.query(Currency.class, sql);
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getCompany4App() {
		String sql="select userid,companycnname,companyenname from users "
				+ "where userid in(select distinct a.parentid from d_user a join users b on a.id = b.userid and b.isapp='是')";
		List<Map<String, Object>> lists = JdbcUtils.queryMap(sql);
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getNickNameByCompany(String id) {
		String sql ="select b.userid,b.nickname from d_user a join users b on a.id=b.userid where a.parentid=? and b.isapp=? ";
		List<Map<String, Object>> lists = JdbcUtils.queryMap(sql, id,"是");
		if(!BeanUtils.isEmpty(lists)){
			return lists;
		}
		return null;
	}
	@Override
	public Version getAppVersion(String ostype) {
		String sql = "select * from appversion where 1=1 ";
		List<Object> params = Lists.newArrayList();
		//android ios
		if(BeanUtils.isNotEmpty(ostype)){
			sql +=" and platform=?";
			params.add(ostype);
		}
		Version version = JdbcUtils.get(Version.class, sql,params.toArray());
		return version;
	}
	@Override
	public boolean updateVersion(List<Version> versionlist) {
		try {
			JdbcUtils.beginTransaction();
			for(Version ver:versionlist){
				JdbcUtils.updateEntity(ver);
			}
			JdbcUtils.commitTransaction();
			return true;
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			logger.error("网页端更新APP版本信息异常!", e);
			return false;
		}
	}
	
	@Override
	public User getUserbyId(String userid) {
		String sql = "select a.*,b.parentid from users a join d_user b on a.userid=b.id where a.userid=? ";
		User user = JdbcUtils.get(User.class, sql, userid);
		return user;
	}
	@Override
	public List<User> getUsersBySearch(String query,String userid) {
		String sql = "select userid,nickname,phonenum,email,companycnname "
				+ " from users where 1=1 and isapp=? and isdel=? and phonenum not in"
				+ " (select tel from customsinfo where userid=?  and type ='0')";
		List<String> params = Lists.newArrayList("是","N",userid);
		if(!BeanUtils.isEmpty(query)){
			sql +=" and ( nickname like ?  or phonenum like ? ) ";
			params.add("%"+query+"%");
			params.add("%"+query+"%");
		}
		List<User> lists = JdbcUtils.query(User.class, sql, params.toArray());
		return lists;
	}
	@Override
	public boolean updateMarkettype(String userid, Integer markettype) {
		try {
			String sql = "update users set markettype=? where userid=? ";
			JdbcUtils.execute(sql, markettype,userid);
			return true;
		} catch (Exception e) {
			logger.error("更新市场类型异常!", e);
			return false;
		}
	}
	/**
	 * 根据日期获取当前周数
	 * @方法名称:getWeekOfYear
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param date
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月29日-下午5:20:49
	 */
	public String getWeekOfYear(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		Calendar ca = Calendar.getInstance();
		try {
			ca.setTime(sdf.parse(date));
			int week = ca.get(Calendar.WEEK_OF_YEAR);
			return week+"";
		} catch (ParseException e) {
			return "";
		}
	}
	@Override
	//SELECT * FROM (SELECT * FROM posts ORDER BY dateline DESC) GROUP BY  tid ORDER BY dateline DESC LIMIT 10  
/*	select * from (select t.* from marketquotation_view t where 1=1 and t.enteruserid='c45d3918-d33e-4ded-a55c-c42fefa5a592' 
	and t.status='1' and t.markettype =0 
	 order by t.currentdate desc) a
	 group by a.countryname,a.productname,a.varieties,a.spec,a.weight,a.packagetype,a.transport order by a.currentdate desc ;*/
	public List<Marketquotation> getDuplicateEntryDatas(String userid,
			String countryid, String productid, int pageSize, int currentPage) {
		String sql = "select a.* from marketquotation_view a "
				+ " where 1=1 "
				+ " and a.enteruserid=? and a.status=? and a.markettype =? ";
		List<Object> params = Lists.newArrayList();
		params.add(userid);
		params.add(Constants.FLAG_ISTRANSMIT_1+"");
		//市场类型
		User user = this.getUserbyId(userid);
		params.add(user.getMarkettype());
		
		//查找此国家产品类型是否有对应的模板
		String sql2 = "select * from template where userid=? and productid=? and countryid=?";
		Template template = JdbcUtils.get(Template.class, sql2, userid,productid,countryid);
		String groupBy = "";//分组
		String order = " order by a.currentdate desc ";//排序
		if(BeanUtils.isEmpty(countryid) || BeanUtils.isEmpty(productid) || BeanUtils.isEmpty(template)){
			//根据国家产品id过滤
			if(BeanUtils.isNotEmpty(countryid) && BeanUtils.isNotEmpty(productid)){
				sql +=" and a.country=? and a.product=? ";
				params.add(countryid);
				params.add(productid);
			}
			groupBy = " group by t.countryname,t.productname,t.varieties,t.spec,t.weight,t.packagetype,t.transport "
					+ " order by t.currentdate desc";
		}else{
			//根据国家产品id过滤
			sql +=" and a.country=? and a.product=? ";
			params.add(countryid);
			params.add(productid);
			//用户根据模板进行筛选
			StringBuilder sb = new StringBuilder(" group by t.countryname,t.productname");
			if(BeanUtils.isNotEmpty(template.getVarieties())){
				sb.append(",t.varieties");
			}
			if(BeanUtils.isNotEmpty(template.getSpec())){
				sb.append(",t.spec");
			}
			if(BeanUtils.isNotEmpty(template.getWeight())){
				sb.append(",t.weight");
			}
			if(BeanUtils.isNotEmpty(template.getPackagetype())){
				sb.append(",t.packagetype");
			}
			if(BeanUtils.isNotEmpty(template.getTransport())){
				sb.append(",t.transport");
			}
			sb.append(" order by t.currentdate desc");
			
			groupBy = sb.toString();
		}
		sql = "select t.* from ("+sql+order+") t"+groupBy;
		
		List<Marketquotation>  lists = JdbcUtils.query_limit(Marketquotation.class, sql, pageSize, currentPage, params.toArray());
		return lists;
	}
	@Override
	public String getRootUserbySubUserid(String subSuerid) {
		String sql ="select getRootUserID(?) rootuserid ";
		Map<String,Object> map = JdbcUtils.getMap(sql, subSuerid);
		if(null !=map){
			return (String) map.get("rootuserid");
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(DateUtils.getDate2SStr(DateUtil.addDate(DateUtils.getStr2SDate("2016-10-24"), -2)));
		MarketServiceImpl impl = new MarketServiceImpl();
		List<Marketquotation> lists= impl.getDuplicateEntryDatas("0e45a82e-2fef-4c35-964b-934bad196e42", "", "", 100, 1);
		System.out.println(JsonUtils.toJson(lists));
	}
}
