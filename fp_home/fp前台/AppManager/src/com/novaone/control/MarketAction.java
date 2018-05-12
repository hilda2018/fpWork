package com.novaone.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.nova.frame.collect.Lists;
import com.nova.frame.model.ObjectId;
import com.nova.frame.utils.DateUtils;
import com.novaone.common.NovaSession;
import com.novaone.constants.Constants;
import com.novaone.model.Attention;
import com.novaone.model.Cominfo;
import com.novaone.model.Country;
import com.novaone.model.CountryProduct;
import com.novaone.model.Currency;
import com.novaone.model.CustomsInfo;
import com.novaone.model.MQDetail;
import com.novaone.model.Marketquotation;
import com.novaone.model.Product;
import com.novaone.model.Template;
import com.novaone.model.User;
import com.novaone.model.Version;
import com.novaone.service.app.MarketService;
import com.novaone.utils.BeanUtils;
import com.novaone.utils.ChinaInitial;
import com.novaone.utils.JsonUtils;
import com.novaone.utils.Md5Encoder;
import com.novaone.utils.PushUtil;
import com.novaone.utils.RequestParameters;
import com.novaone.utils.UUIDUtil;
import com.novaone.utils.ValidateUtil;
/**
 * 
 * 市场行情action控制类
 * @类编号:
 * @类名称:MarketAction
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月1日 下午3:25:19
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Action("marketaction")
@Results({@Result(name = "test01_parmwin_grid", location = "test/test01-parmWin-Grid.jsp"),
    @Result(name = "marketpage", location = "market/market.jsp"),
    @Result(name = "marketView", location = "market/marketView.jsp"),
    @Result(name = "marketView_HS", location = "market/marketView_HS.jsp"),
    @Result(name = "marketAppVersion", location = "market/marketAppVersion.jsp"),
    @Result(name = "result", type = "json", params = {"root", "resultInfo"}),
    @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"})})
public class MarketAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final String RESULT="result";
	private static final Log logger = LogFactory.getLog(MarketAction.class);
	private String username;//用户名
	private String password;//密码
	private String newpwd;//新密码
	@Inject
	private MarketService marketservice;
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private String markequotationText;//接收从前台传递过来的json信息
    private String jsonText;//接收从前台传递过来的json信息
    private User duser;
    private Integer num;//查询数量
    private Integer fieldtype;//查询字段类型
    private String fieldvalue;//查询字段值
    private String id;//记录id ,当多条时用#进行分割
    private String queryname;//转发人姓名
    private String receiver;//转发人id,当多个人时已"#"分隔
    private Integer markettype;//市场类型
    private String productid;//产品id
    private String country;//国家
    private String product;//产品
    private String querytype;//查询类型 "0":子账号/"1":主账号下所有子账号
    private Integer datenum;//收件夹显示日期天数
    private String sort;//排序字段
    private List<Cominfo> infolist=new ArrayList<Cominfo>();//常用信息统计列表
    private Integer currentPage=1;
    private Integer pageSize=10;
    private String date;//过滤日期
    private String cominfoid;//常用信息id,删除时用
    private String exporttype;//导出类型 EN/CN
    private String viewtype;//查看类型 分为person primary 以及all
    private String customsinfoid;//常用联系人id
    //出参
    private String usertype;
    private String jsonValue;//页面跳转json
    private String type;//类型参数
    
    /**
     * 跳转页面使用<br>
     * 价格行情列表页面marketaction!novapage.action?p=marketpage<br>
     * @return
     */
    public String page() {
/*    	JSONObject item =null;
    	if(BeanUtils.isEmpty(request.getParameter("p"))){
    		jsonValue = jsonValue.replaceAll("@", "\"");
        	 item = JSONObject.fromObject(jsonValue);
    	}
    	String returnStr = BeanUtils.isEmpty(request.getParameter("p"))?item.getString("p"):request.getParameter("p");*/
    	if(!BeanUtils.isEmpty(this.getUserIdBySession())){
        	User user = marketservice.getUserbyId(this.getUserIdBySession());
        	if("0".equals(user.getParentid())){
        		usertype = Constants.USERTYPE_PRIMARY;
        	}else{
        		usertype = Constants.USERTYPE_PERSION;
        	}
    	}
        return request.getParameter("p"); 
    }
    /**价格录入页面跳转*/
    public String marketpage(){
    	if(!BeanUtils.isEmpty(this.getUserIdBySession())){
        	User user = marketservice.getUserbyId(this.getUserIdBySession());
        	if("0".equals(user.getParentid())){
        		usertype = Constants.USERTYPE_PRIMARY;
        	}else{
        		usertype = Constants.USERTYPE_PERSION;
        	}
    	}
    	System.out.println("=====usertype======="+usertype);
    	return "marketpage";
    }
    /**价格跟踪查询页面跳转*/
    public String marketView(){
    	try {
			NovaSession session = new NovaSession(httpSession, true);
		} catch (Exception e) {
			logger.error("获取用户信息失败!",e);
		}
    	return "marketView";
    }
    public String marketView_HS(){
     	return "marketView_HS";
    }
    /**价格app版本设置跳转*/
    public String marketAppVersion(){
    	try {
			NovaSession session = new NovaSession(httpSession, true);
		} catch (Exception e) {
			logger.error("获取用户信息失败!",e);
		}
    	return "marketAppVersion";
    }
    /**
     * 获取当前登录用户id
     * @方法名称:getUserIdBySession
     * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
     * @return 
     * String
     * @exception 
     * @author:陈自军
     * @创建日期:2016年9月2日-上午10:21:03
     */
    public String getUserIdBySession(){
    	try {
			NovaSession session = new NovaSession(httpSession, true);
			return session.getUserId();
		} catch (Exception e) {
			logger.error("获取登录用户信息出错!", e);
			return null;
		} 
    }
    /**
     * 用户注册
     * @return
     */
	public String jcregister(){
		User user = JsonUtils.fromJson(jsonText, User.class);
		user.setUserid(ObjectId.StringId());
		user.setIsapp(Constants.ISAPP);
		if(BeanUtils.isEmpty(user.getUsername()) || BeanUtils.isEmpty(user.getPassword()) || !ValidateUtil.isENG_NUM_(user.getUsername())){
			resultInfo.setMsg("注册账号或者密码不能为空且账号只能由英文字母、数字以及下划线构成!");
			resultInfo.setResult(false);
			return RESULT;
		}
		if(!ValidateUtil.isMobile(user.getPhonenum())){
			resultInfo.setMsg("请输入11位有效手机号码!");
			resultInfo.setResult(false);
			return RESULT;
		}
		boolean flag = marketservice.repeatUsername(user.getUsername());
		if(flag){
			resultInfo.setMsg("注册账号被占用!");
			resultInfo.setResult(false);
			return RESULT;
		}
		flag = marketservice.repeatPhonenum(user.getPhonenum());
		if(flag){
			resultInfo.setMsg("手机号码已被注册!");
			resultInfo.setResult(false);
			return RESULT;
		}
		 flag = marketservice.register(user);
		 if(flag){
			 	resultInfo.setMsg("注册成功!");
				resultInfo.setResult(true);
		 }else{
			 	resultInfo.setMsg("注册失败!");
				resultInfo.setResult(false);
		 }
		return RESULT;
	}
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public String jclogin(){
		//password=SecurityUtils.novaEnCryption(password);
		password=Md5Encoder.encodePassword(password);
		duser=marketservice.findByCodeAndPwd(username, password);	
		if(duser != null){
			resultInfo.setResult(true);
			resultInfo.setMsg("登录成功!");
			httpSession.setAttribute("marketuser", duser);
			User user = marketservice.getUserbylogin(duser.getUserid());
			duser.setPrimarycncompany(user.getCompanycnname());
			duser.setPrimaryencompany(user.getCompanyenname());
			dataMap.put("user", duser);
			resultInfo.setData(dataMap);
		}else
		{
			resultInfo.setResult(false);
			resultInfo.setMsg("用户名或密码不对,请重新登录!");
		}
		return RESULT;
	}
	/**
	 * 检查手机号码是否重复
	 * @return
	 */
	public String jccheckLoginName(){
		String userid=request.getParameter("userid");
		boolean flag=marketservice.checkLoginName(username,userid);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("手机号码已存在!");
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("手机号码可用!");
		}
		return RESULT;
	}
	/**
	 * 根据用户名以及页面编码,获取当前登录用户的访问权限
	 * @return
	 */
	public String jcgetAuthority(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		String code=request.getParameter("code");
	List<Map<String,Object>> lists=marketservice.getAuthority2New(userid, code);
			dataMap.put("datas", lists);
			resultInfo.setData(dataMap);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功!");
		return RESULT;
	}
	/**
	 * 用户退出
	 */
	public String jcloginout(){
			httpSession.removeAttribute("marketuser");
			resultInfo.setMsg("退出成功!");
			resultInfo.setResult(true);
			return RESULT;
	}
	/**
	 *价格录入
	 * @return
	 */
	public String jcadd(){
		//前台传递过来的json转对象
		Marketquotation mk=JsonUtils.fromJson(markequotationText,Marketquotation.class);
		duser=(User) httpSession.getAttribute("marketuser");
		List<Marketquotation> mqlist = Lists.newArrayList();
		//把传入的数据分成组装成集合
		if(!BeanUtils.isEmpty(mk.getMqdetail())){
			Marketquotation mk2=null;
			for(MQDetail m: mk.getMqdetail()){
				mk2 = this.copyProperties(mk);
				mk2.setBrand(m.getBrand());//品牌
				mk2.setVarieties(m.getVarieties());//品种
				mk2.setSpec(m.getSpec());//规格
				mk2.setMinprice(m.getMinprice());//最低价格
				mk2.setMaxprice(m.getMaxprice());//最高价格
				mqlist.add(mk2);
			}
		}else{
			mqlist.add(mk);
		}
		//清空infolist集合
		infolist=new ArrayList<Cominfo>();
		//多品牌录入时,合并id
		String mergeid = mqlist.size()>1 ? ObjectId.StringId() : null;
		for(Marketquotation mq:mqlist){
		mq.setMqId(ObjectId.StringId());
		mq.setCurrentdate(DateUtils.getDate2LStr(DateUtils.getCurrentDate()));
		mq.setModifydate(DateUtils.getDate2LStr(DateUtils.getCurrentDate()));//记录插入的时候录入时间和修改时间值相同
		mq.setEnteruserid(duser.getUserid());
		mq.setIstransmit(Constants.FLAG_ISTRANSMIT_0);
		mq.setEnterdate(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
		mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");//记录录入为1
		mq.setMergeid(mergeid);//合并id
		//默认人民币 
		if(BeanUtils.isEmpty(mq.getCurrencyid())){
			mq.setCurrencyid(Constants.CURRENCY_RMB);
		}
		//构建常用信息统计列表
		this.generateCominfo(mq);
		}
		boolean flag=marketservice.add(mqlist);
		//更新常用信息统计表信息
		marketservice.insertCominfo(infolist);
			if(flag){
				resultInfo.setResult(true);
				resultInfo.setMsg("保存成功!");
			}else{
				resultInfo.setResult(false);
				resultInfo.setMsg("保存失败!");
			}
			return RESULT;
		}
	/**
	 *价格录入(可以进行多条记录的操作)
	 * @return
	 */
	public String add(){
		List<Marketquotation> mqlist=JsonUtils.fromJson(markequotationText,
				new TypeToken<List<Marketquotation>>(){}.getType());
		infolist=new ArrayList<Cominfo>();
		List<Marketquotation> addList = new ArrayList<Marketquotation>();//保存增加列表
		List<Marketquotation> updateList = new ArrayList<Marketquotation>();//保存修改列表
		//获取session
		  try {
			NovaSession session = new NovaSession(httpSession, true);
			String userid = session.getUserId();
			for(Marketquotation mq:mqlist){
				//增加
				if(null ==mq.getMqId() ||"".equals(mq.getMqId())){
					mq.setMqId(ObjectId.StringId());
					mq.setCurrentdate(DateUtils.getDate2LStr(DateUtils.getCurrentDate()));
					mq.setModifydate(DateUtils.getDate2LStr(DateUtils.getCurrentDate()));//记录插入的时候录入时间和修改时间值相同
					mq.setEnteruserid(userid);
					mq.setIstransmit(Constants.FLAG_ISTRANSMIT_0);
					mq.setEnterdate(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
					mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");//记录录入为1
					if(BeanUtils.isEmpty(mq.getCurrencyid())){
						mq.setCurrencyid(Constants.CURRENCY_RMB);
					}
					//获取国家产品信息
					CountryProduct cp = marketservice.getCountryProduct(mq.getCountry(), mq.getProduct());
					mq.setCountryproductid(cp.getCountryproductid());
					addList.add(mq);
					this.generateCominfo(mq);
					marketservice.insertCominfo(infolist);
				}else{
					//修改
					mq.setModifydate(DateUtils.getDate2LStr(new Date()));
					//币别默认人民币
					if(BeanUtils.isEmpty(mq.getCurrencyid())){
						mq.setCurrencyid(Constants.CURRENCY_RMB);
					}
					//获取国家产品信息
					CountryProduct cp = marketservice.getCountryProduct(mq.getCountry(), mq.getProduct());
					mq.setCountryproductid(cp.getCountryproductid());
					updateList.add(mq);
					this.generateCominfo(mq);
					marketservice.insertCominfo(infolist);
				}
			}
			boolean flag = marketservice.saveMarList4Web(addList, updateList);
				if(flag){
					resultInfo.setResult(true);
					resultInfo.setMsg("保存成功!");
				}else{
					resultInfo.setResult(false);
					resultInfo.setMsg("保存失败!");
				}
		} catch (Exception e) {
			logger.error("网页端价格录入新增或修改异常!", e);
		} 
			return RESULT;
		}
	/**
	 * 根据价格记录并保存常用信息
	 * @param mq
	 */
		public void generateCominfo(Marketquotation mq){
			//构建常用信息统计列表
			//this.getCominfo(Constants.FLAG_TYPE_0, mq.getCountry());
			//this.getCominfo4PC(Constants.FLAG_TYPE_1, mq.getProduct(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_2, mq.getVarieties(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_3, mq.getSpec(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_4, mq.getSupplier(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_5, mq.getVendor(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_6, mq.getBrand(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_7, mq.getWeight(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_8, mq.getPackagetype(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_9, mq.getQuality(),mq.getCountry(),mq.getProduct());
			this.getCominfoPC(Constants.FLAG_TYPE_12, mq.getNewold(), mq.getCountry(), mq.getProduct());
			//this.getCominfo(Constants.FLAG_TYPE_10, mq.getSailedescribe(),mq.getCountry(),mq.getProduct());
			//this.getCominfo4PC(Constants.FLAG_TYPE_11, mq.getPlatenum()+"",mq.getCountry(),mq.getProduct());
	//更新常用信息统计表信息
		//	marketservice.insertCominfo(infolist);
		}
	
	
	/**
	 * 构建常用信息对象
	 * @param fieldtype
	 * @param fieldvalue
	 * @return
	 */
	public void getCominfo(Integer fieldtype,String fieldvalue,String country,String product){
		duser=(User) httpSession.getAttribute("marketuser");
		Cominfo com=new Cominfo();
		if(!BeanUtils.isEmpty(fieldvalue)){
			com.setCominfoid(ObjectId.StringId());
			com.setUsername(duser.getUserid());
			com.setFieldtype(fieldtype);
			com.setFieldvalue(fieldvalue);
			com.setCountry(country);
			com.setProduct(product);
			infolist.add(com);
		}
	}
	/**
	 * 构建常用信息对象
	 * @param fieldtype
	 * @param fieldvalue
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名getCominfo4PC修改为getCominfoPC
	 */
	public void getCominfoPC(Integer fieldtype,String fieldvalue,String country,String product){
		try {
			NovaSession session = new NovaSession(httpSession, true);
			String userid = session.getUserId();
			Cominfo com=new Cominfo();
			if(fieldvalue !=null && !"".equals(fieldvalue)){
				com.setCominfoid(ObjectId.StringId());
				com.setUsername(userid);
				com.setFieldtype(fieldtype);
				com.setFieldvalue(fieldvalue);
				com.setCountry(country);
				com.setProduct(product);
				infolist.add(com);
			}
		} catch (Exception e) {
			logger.error("价格行情网页端获取用户登录信息失败!", e);
		}
		
	}
	/**
	 * 插入常用信息统计表
	 * @return
	 */
	public String jcinsertCominfo(){
		Cominfo cm = JsonUtils.fromJson(markequotationText, Cominfo.class);
		duser=(User) httpSession.getAttribute("marketuser");
		List<Cominfo> info=new ArrayList<Cominfo>();
		cm.setCominfoid(ObjectId.StringId());
		cm.setUsername(duser.getUserid());//常用信息统计表username存入当前用户id
		info.add(cm);
		logger.error("常用统计表插入------");
		logger.error(cm.getFieldvalue().trim());
		//后台校验
	/*	if(!ValidateUtil.isSTR_ENG_CHA_NUM_SPACE(cm.getFieldvalue().trim())){
			resultInfo.setMsg("输入信息只能由数字、字母、空格以及汉字构成!");
			resultInfo.setResult(false);
			return RESULT;
		}*/
		Map<String,Object> map=marketservice.insertCominfo(info);

		if(map.get("flag") !=null && (Boolean)map.get("flag")){
			resultInfo.setMsg("数据添加成功!");
			resultInfo.setResult(true);
			resultInfo.setData(map);
		}else{
			if(null !=map.get("repeat") && (Boolean)map.get("repeat")){
				resultInfo.setMsg("此数据已存在,请不要重复添加!");
				resultInfo.setResult(false);
			}else{
				resultInfo.setMsg("数据添加失败!");
				resultInfo.setResult(false);
			}
		}
		return RESULT;
	}
	/**
	 * 常用信息统计表信息删除
	 * @return
	 */
	public String jcdeleteCominfo(){
		boolean flag = marketservice.deleteCominfo(cominfoid);
		if(flag){
			resultInfo.setMsg("删除成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("删除失败!");
			resultInfo.setResult(false);
		}
		return RESULT;
	}
	
	/**
	 * 查询记录详情
	 * @param id
	 * @return
	 */
	public String jcdetail(){
		Marketquotation mq=marketservice.detail(id);
		//对查询结果进行二次封装
		List<MQDetail> lists = Lists.newArrayList(
				new MQDetail(mq.getBrand(), mq.getVarieties(), mq.getSpec(), mq.getMinprice(), mq.getMaxprice()));
		mq.setMqdetail(lists);
		resultInfo.setResult(true);
		resultInfo.setMsg("数据返回成功!");
		dataMap.put("data", mq);
		resultInfo.setData(dataMap);
		return RESULT;	
	}
	/**
	 * 记录删除
	 * @param id
	 * @return
	 */
	public String jcdelete(){
		//可以一次删除多条记录id之间以#分隔
		//String ids=this.getIds(id, "#");
		String ids = BeanUtils.getIdsAsString(id, "#");
		boolean flag=marketservice.delete(ids);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("删除成功!");
		}
		return RESULT;
	}
	
	public String delete(){
		//可以一次删除多条记录id之间以,分隔
		String ids = BeanUtils.getIdsAsString(id, ",");
		boolean flag=marketservice.delete(ids);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("删除成功!");
		}else{
			resultInfo.setMsg("删除失败!");
		}
		return RESULT;
	}
	/**
	 * 记录修改(由于多品牌录入update保存有问题,固记录先删除后增加)
	 * @param mq
	 * @return
	 */
	public String jcmodify(){
		Marketquotation mk=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		mk.setModifydate(DateUtils.getDate2LStr(new Date()));
		mk.setCurrentdate(DateUtils.getDate2LStr(DateUtils.getCurrentDate()));
		//默认人民币币别
		if(BeanUtils.isEmpty(mk.getCurrencyid())){
			mk.setCurrencyid(Constants.CURRENCY_RMB);
		}
		List<Marketquotation> mqlist = Lists.newArrayList();
		//把传入的数据分成组装成集合
		if(!BeanUtils.isEmpty(mk.getMqdetail())){
			Marketquotation mk2=null;
			String mergeid = mk.getMqdetail().size()>1 ? ObjectId.StringId() : mk.getMergeid();
			for(MQDetail m: mk.getMqdetail()){
				mk2 = this.copyProperties(mk);
				mk2.setBrand(m.getBrand());//品牌
				mk2.setVarieties(m.getVarieties());//品种
				mk2.setSpec(m.getSpec());//规格
				mk2.setMinprice(m.getMinprice());//最低价格
				mk2.setMaxprice(m.getMaxprice());//最高价格
				mk2.setMergeid(mergeid);
				mqlist.add(mk2);
			}
		}else{
			mqlist.add(mk);
		}
		boolean flag=marketservice.modify4App(mqlist,type);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("修改成功!");
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("修改失败!");
		}
		return RESULT;
	}
	
		/**
	 * 记录修改
	 * @param mq
	 * @return
	 */
	public void modify(){
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		mq.setModifydate(DateUtils.getDate2LStr(new Date()));
		//获取国家产品信息
		CountryProduct cp = marketservice.getCountryProduct(mq.getCountry(), mq.getProduct());
		mq.setCountryproductid(cp.getCountryproductid());
		List<Marketquotation> updateList = new ArrayList<Marketquotation>();
		updateList.add(mq);
		boolean flag=marketservice.modify(updateList);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("修改成功!");
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("修改失败!");
		}
		this.returnInfo(JsonUtils.toJson(resultInfo));
	}
	/**
	 * 获取国家列表(通过产品id)
	 * @return
	 */
	public String jcgetCountryList(){
		List<Country> list=marketservice.getCountryList(productid);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	
	public void getCountryList(){
		List<Country> list=marketservice.getCountryList(productid);
		this.returnInfo(JsonUtils.toJson(list));
	}
	
	/**
	 * 获取品名列表
	 * 修改为从常用信息列表中获取品名信息
	 * @return
	 */
	public String jcgetProductList(){
		//List<Product> list=marketservice.getProductList();
		duser=(User) httpSession.getAttribute("marketuser");
		List<Product> list = marketservice.getProductList(duser.getUserid(), Constants.FLAG_TYPE_1);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	public void getProductList(){
		List<Product> list=marketservice.getProductList();
		this.returnInfo(JsonUtils.toJson(list)); 
	}
	/**
	 * action朝前台返回值
	 * @param result
	 */
	private void returnInfo(String result) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(result);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 根据条件获取特定类型 特定用户 特定个数  的常用信息列表,按照最近使用顺序倒序
	 * 字段类型0:国家;1:品名;2:品种;3:规格;4:销售商;5:供应商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数
	 */
	public String jcgetCominfoList(){
		duser=(User) httpSession.getAttribute("marketuser");
		List<Cominfo> list=marketservice.getCominfoList(duser.getUserid(),num, fieldtype, fieldvalue,country,product);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	/**
	 * 常用信息统计--网页版
	 */
	public void getCominfoList(){
		try {
			String userid = this.getUserIdBySession();
			Cominfo info = new Cominfo();
			info.setFieldtype(fieldtype);
			info.setFieldvalue(fieldvalue);
			info.setCountry(country);
			info.setProduct(product);
			if(Constants.USERTYPE_PERSION.equals(this.getUserType(userid))){
				//子账号
				info.setUsername(userid);
			}else{
				//主账号
				info.setParentid(userid);
			}
		//	List<Cominfo> list=marketservice.getCominfoList(userid,num, fieldtype, fieldvalue,country,product);
			List<Cominfo> list=marketservice.getCominfoList4Web(info, num);
			this.returnInfo(JsonUtils.toJson(list));
		} catch (Exception e) {
			logger.error("网页端获取常用统计信息异常!", e);
		}

	}
	/**
	 * 获取当前用户登录类型
	 * @方法名称:getUserType
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param userid
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月31日-下午5:39:15
	 */
	public String getUserType(String userid){
	 	User user = marketservice.getUserbyId(userid);
    	if("0".equals(user.getParentid())){
    		usertype = Constants.USERTYPE_PRIMARY;
    	}else{
    		usertype = Constants.USERTYPE_PERSION;
    	}
    	return usertype;
	}
	
	/**
	 * 记录搜索
	 * @return
	 */
	public String jcgetDatas(){
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		mq.setEnteruserid(userid);
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> list=marketservice.getDatas(mq, novapage);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		  return RESULT;
	}
	/**
	 * 根据查询条件获取列表--网页端
	 * 查看类型viewtype:person primary 以及all
	 */
	public void getDatas(){
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		List<Marketquotation> list=null;
		long count;
		//获取session
		 try {
			session = new NovaSession(request.getSession(), true);
			//分页数据
			RequestParameters requestParameters = RequestParameters.create(request);
			
			novapage.setCurrentPage(requestParameters.getPage());//当前页
			novapage.setPageSize(requestParameters.getRows());//每页显示多少条记录
			if(!"all".equals(viewtype)){
				String userid=session.getUserId();
				if(Constants.USERTYPE_PERSION.equals(viewtype)){
					//app用户录入
					mq.setEnteruserid(userid);
				}else{
					//主账号登录
					mq.setParentid(userid);
				}

				list=marketservice.getDatas4Web(mq, novapage, requestParameters);
				 count=marketservice.getDatasCount(mq);//总记录数
			}else{
				//查看全部信息
				 list=marketservice.getDatas4WebAll(mq, novapage, requestParameters);
				 count=marketservice.getDatas4WebAllCount(mq, novapage, requestParameters);
			}
			dataMap.put("total",count);//当前页记录
			dataMap.put("rows", list);
			returnInfo(JsonUtils.toJson(dataMap));
		} catch (Exception e) {
			logger.error("获取登录用户失败!", e);
		}
		
	}
	/**
	 * 默认记录显示(默认显示昨天与今天的数据),点击更多的时候查询以前的数据.
	 * 记录按市场日期倒序排列
	 * modify 2016-04-22
	 * 记录按照最近使用时间倒序排序
	 * modify 2016-05-18
	 * @return
	 */
	public String jcgetDefaultDatas(){
		//Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		//在session中获取当前用户id
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		Marketquotation mq=new Marketquotation();
		mq.setEnteruserid(userid);
		mq.setEnterdate(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
		mq.setMarkettype(markettype);
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> list=marketservice.getDefaultDatas(mq,querytype,novapage);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	/**
	 * 排序:按照销售商 国家 品名 品牌进行排序
	 * @param fieldvalue 排序字段
	 * @param date 过滤市场日期
	 * @modify 2016-08-18 增加过滤日期
	 * @return
	 */
	public String jcgetDimDatas(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		Marketquotation mq=new Marketquotation();
		mq.setEnteruserid(userid);
		mq.setMarkettype(markettype);
		mq.setMarketdate(date);//过滤日期
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> list=marketservice.getDimDatas(mq,fieldvalue, novapage);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	
	/**
	 * 记录转发(可以转发多条记录,同时转发给多个人)
	 * @return
	 */
	public String jcforwardData(){
		//记录id按照id#id#id的方式传递
		List<Marketquotation> marlist=new ArrayList<Marketquotation>();
//		duser=(User) httpSession.getAttribute("marketuser");
//		String userid=duser.getUserid();//当前登录用户的
		Marketquotation mar=null;
		//String[] ids=id.split("#");
		List<String> ids = BeanUtils.getIdsAsList(id, "#");
		if(BeanUtils.isEmpty(ids)){
			resultInfo.setMsg("请选择需要转发的记录!");
			resultInfo.setResult(false);
			return RESULT;
		}
		//获取所有需要转发的记录
		for(String id:ids){
			mar=marketservice.detail(id);
			marlist.add(mar);
		}
		//记录人id为被转发人id,转发人id

		List<Marketquotation> receiverlist=new ArrayList<Marketquotation>();
		//String [] receivers=receiver.split("#");
		List<String> receivers = BeanUtils.getIdsAsList(receiver, "#");
		List<String> aliaseslist = new ArrayList<String>();//消息推送别名列表
		//对转发人进行校验
		if(BeanUtils.isEmpty(receivers)){
			resultInfo.setMsg("请选择需要的转发人!");
			resultInfo.setResult(false);
			return RESULT;
		}
		Marketquotation ma=null;
		for(String re:receivers){
			 for(Marketquotation mq:marlist){
				 	ma=this.copyProperties(mq);//复制对象
				 	ma.setMqId(UUIDUtil.getUUID());
				 	ma.setTransmituser(mq.getEnteruserid());
				 	ma.setEnteruserid(re);
				 	ma.setIstransmit(Constants.FLAG_ISTRANSMIT_1);
				 	ma.setStatus("0");//转发为0.确认接收为1
				 	ma.setModifydate(DateUtils.getDate2LStr(new Date()));
					receiverlist.add(ma);
					aliaseslist.add(re.replaceAll("-", ""));//消息推送别名列表
			 }
		}
		//插入记录
		 boolean flag=marketservice.add(receiverlist);
		 if(flag){
			 resultInfo.setResult(true);
			 resultInfo.setMsg("数据返回成功!");
			 PushUtil.push_alias_message(Constants.APP_MSG, aliaseslist);
		 }
		return RESULT;
	}
	/**
	 * 复制对象
	 * @param ma
	 * @return
	 */
	public Marketquotation  copyProperties(Marketquotation ma){
		Marketquotation m2=new Marketquotation();
		m2.setMqId(ma.getMqId());//mqId;//市场行情ID
		m2.setEnteruserid(ma.getEnteruserid());//录入人
		m2.setEnterdate(ma.getEnterdate());//录入日期
		m2.setMarkettype(ma.getMarkettype());//市场类型 '0:辉展市场;1:江南市场'
		m2.setMarketdate(ma.getMarketdate());// marketdate;//市场日期
		m2.setCountry(ma.getCountry()); //country;//国家
		m2.setProduct(ma.getProduct());//product;//品名
		m2.setVarieties(ma.getVarieties());;//品种
		m2.setSpec(ma.getSpec());;//规格
		m2.setSupplier(ma.getSupplier());;//供应商
		m2.setVendor(ma.getVendor());;//销售商
		m2.setBrand(ma.getBrand());;//品牌
		m2.setWeight(ma.getWeight());;//重量
		m2.setTransport(ma.getTransport());//运输方式 0:海运;1:空运;2:陆运
		m2.setPackagetype(ma.getPackagetype());;//包装
		m2.setMinprice(ma.getMinprice());;//最低价格
		m2.setMaxprice(ma.getMaxprice());;//最高价格 
		m2.setQuality(ma.getQuality());;//品质
		m2.setSailedescribe(ma.getSailedescribe());;//销售描述	
		m2.setPlatenum(ma.getPlatenum());;//板数
		m2.setIstransmit(ma.getIstransmit());;//是否转发 0:非转发;1:转发'
		m2.setTransmituser(ma.getTransmituser());;//转发人
		m2.setCurrentdate(ma.getCurrentdate());//时间戳
		m2.setModifydate(ma.getModifydate());;//修改时间
		m2.setStatus(ma.getStatus());//记录状态 0记录转发没有确认,1:记录转发已确认,记录录入默认状态
		m2.setCountryproductid(ma.getCountryproductid());//国家产品id设置
		//modify by chenzijun 2016-07-30
		m2.setCntrno(ma.getCntrno());//柜号
		m2.setFarm(ma.getFarm());//果园
		m2.setPackingplant(ma.getPackingplant());//包装厂
		m2.setCurrencyid(ma.getCurrencyid());//币别
		m2.setNewold(ma.getNewold());
		return m2;
		
	}
	
	/**
	 * 记录复制(可以复制多条记录,复制"价格去掉,日期改为今天,录入人改为自己.)
	 * @return
	 */
	public String jcdataCopy(){
		//记录id按照id#id#id的方式传递
		List<Marketquotation> marlist=new ArrayList<Marketquotation>();
		Marketquotation mar=null;
		//String[] ids=id.split("#");
		List<String> ids = BeanUtils.getIdsAsList(id, "#");
		//获取所有需要复制的记录信息
		for(String id:ids){
			mar=marketservice.detail(id);
			marlist.add(mar);
		}
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		//清除复制记录的价格信息,录入日期
		for(Marketquotation m:marlist){
				m.setMqId(ObjectId.StringId());
				m.setEnteruserid(userid);
				m.setEnterdate(DateUtils.getDate2SStr(new Date()));
				m.setMarketdate(DateUtils.getDate2SStr(new Date()));
				m.setTransmituser(null);
				m.setCurrentdate(DateUtils.getDate2LStr(new Date()));
				m.setModifydate(DateUtils.getDate2LStr(new Date()));
				m.setPiclist(null);
				m.setStatus("1");
				m.setIstransmit(0);
				m.setMaxprice(null);
				m.setMinprice(null);
				//m.setCurrencyid(null);
				m.setSupplier(null);//清空销售商
				m.setMergeid(null);//清空合并id
		}
		boolean flag=marketservice.add(marlist);
		if(flag){
			resultInfo.setMsg("记录复制成功!");
			resultInfo.setResult(true);
		}
		return RESULT;
	}
	/**
	 * 记录复制--网页端
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名dataCopy4Web修改为dataCopyWeb
	 */
	public String dataCopyWeb(){
		try {
			NovaSession session = new NovaSession(httpSession, true);
			String userid = session.getUserId();
			//记录id按照id#id#id的方式传递
			List<Marketquotation> marlist=new ArrayList<Marketquotation>();
			Marketquotation mar=null;
			//String[] ids=id.split(",");
			List<String> ids = BeanUtils.getIdsAsList(id, ",");
			//获取所有需要复制的记录信息
			for(String id:ids){
				mar=marketservice.detail(id);
				marlist.add(mar);
			}
			//清除复制记录的价格信息,录入日期
			for(Marketquotation m:marlist){
					m.setMqId(ObjectId.StringId());
					m.setEnteruserid(userid);
					m.setEnterdate(DateUtils.getDate2SStr(new Date()));
					m.setMarketdate(DateUtils.getDate2SStr(new Date()));
					m.setTransmituser(null);
					m.setCurrentdate(DateUtils.getDate2LStr(new Date()));
					m.setModifydate(DateUtils.getDate2LStr(new Date()));
					m.setPiclist(null);
					m.setStatus("1");
					m.setIstransmit(0);
					m.setMaxprice(null);
					m.setMinprice(null);
					m.setSupplier(null);//清空销售商
					m.setMergeid(null);//清空合并id
			}
			boolean flag=marketservice.add(marlist);
			if(flag){
				resultInfo.setMsg("记录复制成功!");
				resultInfo.setResult(true);
			}else{
				resultInfo.setMsg("记录复制失败!");
			}
			return RESULT;
		} catch (Exception e) {
			logger.error("价格行情网页端记录复制获取用户信息失败!", e);
		}
		return null;
	}
	/**
	 * 
	 * @方法名称:jcaddCustomsInfo
	 * @内容摘要:添加常用联系人(新增和编辑)
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:06:51
	 */
	public String jcaddCustomsInfo(){
		CustomsInfo info=JsonUtils.fromJson(markequotationText, CustomsInfo.class);
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		
		//对公司名称做后台校验
		if(BeanUtils.isEmpty(info.getCompany())||info.getCompany().length()>30){
			resultInfo.setMsg("公司名称不能为空且最大长度不大于30个字符!");
			resultInfo.setResult(false);
			return RESULT;
		}
		//用户姓名后台校验
		String customsinfoid = info.getCustomsinfoid()==null ? "":info.getCustomsinfoid();
		if(BeanUtils.isEmpty(info.getUsername())||info.getUsername().length() > 20){
			resultInfo.setMsg("姓名不能为空且最大长度不大于20个字符!");
			resultInfo.setResult(false);
			return RESULT;
		}else{
			//对用户名进行重复验证
			boolean flag=marketservice.checkCustomsName(userid, info.getUsername(),customsinfoid);
			if(flag){
				resultInfo.setMsg("此常用联系人姓名已存在!");
				resultInfo.setResult(false);
				return RESULT;
			}
		}
		//对手机号码进行验证
		if(BeanUtils.isEmpty(info.getTel())){
			resultInfo.setMsg("手机号码不能为空!");
			resultInfo.setResult(false);
			return RESULT;
		}else{
		      boolean flag=marketservice.checkCustomsTel(userid, info.getTel(),customsinfoid);//检查是否在常用联系人表中
				if(flag){
					resultInfo.setMsg("此联系人已存在!");
					resultInfo.setResult(false);
					return RESULT;
				}else{
				boolean flag2=marketservice.checkUser(info.getTel());//检查是否在用户表中
					if(!flag2){
									resultInfo.setMsg("此手机号码关联的子账号不存在!");
									resultInfo.setResult(false);
									return RESULT;	
								}
					}
		}
		//电子邮件后台验证
		if(!BeanUtils.isEmpty(info.getEmail())){
			if(!ValidateUtil.isEmail(info.getEmail())){
				resultInfo.setMsg("电子邮件格式不正确!");
				resultInfo.setResult(false);
				return RESULT;
			}
		}
		//获取公司拼音首字母
		String companyStr=ChinaInitial.getPYIndexStr(info.getCompany().toUpperCase(), true);
		info.setInitial(companyStr.substring(0, 1));
		 boolean flag=marketservice.addCustomsInfo(info,userid);
		if(flag){
			resultInfo.setMsg("保存成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("保存失败!");
		}
		return RESULT;
	}
	/**
	 * 常用联系人删除
	 * @方法名称:jcdeleteCustomsInfo
	 * @内容摘要: 
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午4:06:59
	 */
	public String jcdeleteCustomsInfo(){
		String ids = BeanUtils.getIdsAsString(customsinfoid, ",");
		if(BeanUtils.isEmpty(ids)){
			resultInfo.setMsg("请选择需要删除的数据!");
			resultInfo.setResult(false);
			return RESULT;
		}
		boolean flag = marketservice.deleteCustomsInfo(ids);
		if(flag){
			resultInfo.setMsg(Constants.DELETE_SUCCESS);
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg(Constants.DELETE_FAILURE);
		}
		return RESULT;
		
	}
	/**
	 * 
	 * @方法名称:jccheckCustomsName
	 * @内容摘要: 检查常用联系人姓名是否重复
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:07:47
	 */
	public String jccheckCustomsName(){
		duser=(User) httpSession.getAttribute("marketuser");
		String name=request.getParameter("name");//常用联系人名称
		String userid=duser.getUserid();
		customsinfoid = customsinfoid==null ? "":customsinfoid;
		boolean flag=marketservice.checkCustomsName(userid, name,customsinfoid);
		if(flag){
			resultInfo.setMsg("此常用联系人姓名已存在!");
			resultInfo.setResult(false);
		}else{
			resultInfo.setMsg("此常用联系人姓名可用!");
			resultInfo.setResult(true);
		}
		return RESULT;
	}
	/**
	 * 
	 * @方法名称:jccheckCustomsTel
	 * @内容摘要: 检查常用联系人电话是否重复
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:08:09
	 */
	public String jccheckCustomsTel(){
		duser=(User) httpSession.getAttribute("marketuser");
		String tel=request.getParameter("tel"); //常用联系人电话
		String userid=duser.getUserid();
		customsinfoid = customsinfoid==null ? "":customsinfoid;
		boolean flag=marketservice.checkCustomsTel(userid, tel,customsinfoid);//检查是否在常用联系人表中
		if(flag){
				resultInfo.setMsg("此常用联系人手机号码已存在!");
				resultInfo.setResult(false);	
		}else{
			boolean flag2=marketservice.checkUser(tel);//检查是否在用户表中
			if(!flag2){
				resultInfo.setMsg("此手机号码关联的子账号不存在!");
				resultInfo.setResult(false);
			}else{
				resultInfo.setMsg("此手机号码可用!");
				resultInfo.setResult(true);
			}
		}
		return RESULT;
	}
	/**
	 * 
	 * @param queryname转发人用户名
	 * @return
	 */
	public String jcgetForwardPerson(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<CustomsInfo> list=marketservice.getForwardPerson(queryname,userid);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	/**
	 * 转发记录确认
	 * @return
	 */
	public String jcconfirm(){
		String ids = BeanUtils.getIdsAsString(id, "#");
		boolean flag=marketservice.confirm(ids);
		if(flag){
			resultInfo.setMsg("记录确认成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("记录确认失败!");
		}
		return RESULT;
	}
	/**
	 * 收件箱--显示近一个星期的数据.(未确认数据),以及按照销售商 国家 品名 品牌进行排序
	 * @return
	 */
	public String jcmessageBox(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> lists=marketservice.messageBox(userid,sort,markettype, datenum, novapage);
		resultInfo.setMsg("数据返回成功");
		resultInfo.setResult(true);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		return RESULT;
	}
	/**
	 * 修改密码
	 * @return
	 */
	public String jcchangePwd(){
		User user=marketservice.findByCodeAndPwd(username, Md5Encoder.encodePassword(password));
		if(user==null){
			resultInfo.setMsg("原密码不对,请重新输入!");
			resultInfo.setResult(false);
			return RESULT;
		}
		boolean flag=marketservice.changePassword(user.getUserid(), Md5Encoder.encodePassword(newpwd));
		if(flag){
			resultInfo.setMsg("密码修改成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("密码修改失败!");
		}
		return RESULT;
	}
	/**
	 * 导出excel表
	 * @return
	 */
	public void exportExcel(){
		List<Marketquotation> mqlist=new ArrayList<Marketquotation>();
		List<String> ids = BeanUtils.getIdsAsList(id, ",");
		//获取所有需要转发的记录
		for(String id:ids){
			Marketquotation mar=marketservice.detail(id);
			mqlist.add(mar);
		}
	    // 设置excel的数据头信息
	    String[] cellHeadArray_cn ={"周","日期","录入人","市场","国家","品名","运输方式","销售商","供应商","品牌","品种","规格",
	    		"重量","币别","价格","品质","销售描述","包装","包装周","包装厂","柜号","板数","果园","新旧"};
	    String[] cellHeadArray_en ={"Week ","Date","Input person","Market","Country","Product","Transportation","Seller","Supplier","Brands","Variety","Size",
	    		"Weight","Currency","Price","Quality","Sales description","Package","Packing week ","Packaging house","Container No","Pallet amount","Grower name","New and old"};
	    if("CN".equals(exporttype)){
			marketservice.export4CN(mqlist,cellHeadArray_cn);
	    }else{
			marketservice.export4EN(mqlist,cellHeadArray_en);
	    }

	}
	/**
	 * 添加关注信息
	 * @方法名称:jcaddAttention
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月16日-下午4:35:43
	 */
	public String jcaddAttention(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<String> ids = BeanUtils.getIdsAsList(id, ",");
		List<Attention> attentionlist = new ArrayList<Attention>();
		Attention att = null;
		for(String id:ids){
			att = new Attention();
			att.setId(ObjectId.StringId());
			att.setProductid(id);
			att.setUserid(userid);
			att.setCurrentdate(DateUtils.getDate2LStr(new Date()));
			attentionlist.add(att);
		}
		boolean flag=marketservice.addAttention(userid, attentionlist);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("保存成功!");
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("保存失败!");
		}
	return RESULT;	
	}
	/**
	 * 获取关注列表
	 * @return
	 */
	public String jcgetAttentionList(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<Attention> lists = marketservice.getAttentionList(userid);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据获取成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 获取关注列表
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名getAttentionList4Web修改为getAttentionListWeb
	 */
	public void getAttentionListWeb(){
		NovaSession session;
		try {
			session = new NovaSession(httpSession, true);
			String userid = session.getUserId();
			List<Attention> lists = marketservice.getAttentionList(userid);
			returnInfo(JsonUtils.toJson(lists));
		} catch (Exception e) {
			logger.error("获取当前登录用户信息异常", e);
		}
	}
	/**
	 * 用户产品关注查询列表
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetProductList4Attention修改为jcgetProductListAttention
	 */
	public String jcgetProductListAttention(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<Product> lists=marketservice.getProductList(userid);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据返回成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 添加用户模板
	 * @return
	 */
	public String jcaddTemplate(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		Template template = JsonUtils.fromJson(jsonText, Template.class);
		template.setUserid(userid);
		//国家品名重复判断
		boolean temp=marketservice.templateRepeat(template);
			if(temp){
				resultInfo.setMsg("此模板已经存在!");
				resultInfo.setResult(false);
				return RESULT;
			}
		boolean flag = marketservice.addTemplate(template);
		if(flag){
			resultInfo.setMsg("保存成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("保存失败!");
			resultInfo.setResult(false);
		}
		return RESULT;
	}
	/**
	 * 删除模板
	 * @return
	 */
	public String jcdeleteTemplate(){
		String id = request.getParameter("id");
		String ids = BeanUtils.getIdsAsString(id, ",");
		boolean flag = marketservice.deleteTemplate(ids);
		
		if(flag){
			resultInfo.setMsg("删除成功!");
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg("删除失败!");
			resultInfo.setResult(false);
		}
		return RESULT;
	}
	/**
	 * 获取用户添加模板列表
	 * @return
	 */
	public String jcgetTemplateList(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<Template> lists = marketservice.getTemplateList(userid);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据返回成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 用户通过国家 商品id获取模板详情
	 * @return
	 */
	public String jcgetTemplateDetail(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		Template template = JsonUtils.fromJson(jsonText, Template.class);
		template.setUserid(userid);
		
		template = marketservice.getTemplateDetail(template);
		if(null == template){
			template = new Template();
			template.setVarieties(Constants.FLAG_Y);
			template.setSpec(Constants.FLAG_Y);
			template.setSupplier(Constants.FLAG_Y);
			template.setVendor(Constants.FLAG_Y);
			template.setBrand(Constants.FLAG_Y);
			template.setWeight(Constants.FLAG_Y);
			template.setTransport(Constants.FLAG_Y);
			template.setPackagetype(Constants.FLAG_Y);
			template.setPrice(Constants.FLAG_Y);
			template.setQuality(Constants.FLAG_Y);
			template.setSailedescribe(Constants.FLAG_Y);
			template.setPlatenum(Constants.FLAG_Y);
			template.setCntrno(Constants.FLAG_Y);
			template.setFarm(Constants.FLAG_Y);
			template.setPackingplant(Constants.FLAG_Y);
		}
		
		dataMap.put("datas", template);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据返回成功!");
		resultInfo.setResult(true);
		return RESULT;
		
	}
	/**
	 * 通过id获取模板明细
	 * @return
	 */
	public String jcgetTemplateDetailById(){
		String id = request.getParameter("id");
		Template temp = marketservice.getTemplateDetailById(id);
		dataMap.put("data", temp);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据获取成功!");
		resultInfo.setResult(true);
		return RESULT;
		
	}
	/**
	 * 根据日期过滤数据
	 * @return
	 */
	public String jcgetDatasByDate(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		//String date=request.getParameter("date");
		String status = request.getParameter("status");//是否是收件箱中数据
		
		Marketquotation mq = new Marketquotation();
		mq.setEnteruserid(userid);
		mq.setStatus(status);
		mq.setMarkettype(markettype);//市场类型
		mq.setStartTime(date);
		mq.setEndTime(date);
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> lists= marketservice.getDatas(mq, novapage);
		
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据返回成功!");
		resultInfo.setResult(true);
		return RESULT;
		
	}
	/**
	 * 转发国家品名过滤
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetCountryProduct4Forward修改为jcgetCountryProductForward
	 */
	public String jcgetCountryProductForward(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<Map<String,Object>> lists = marketservice.getCountryProduct(userid, date,markettype);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据返回成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 转发:销售商过滤
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetSupplier4Forward修改为jcgetSupplierForward
	 */
	public String jcgetSupplierForward(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		List<Map<String,Object>> lists = marketservice.getSupplier4Forward(userid, date,markettype);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据获取成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 转发过滤列表
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetDatas4Forward修改为jcgetDatasForward
	 */
	public String jcgetDatasForward(){
		duser=(User) httpSession.getAttribute("marketuser");
		String userid=duser.getUserid();
		Marketquotation mar = JsonUtils.fromJson(markequotationText, Marketquotation.class);
		mar.setEnteruserid(userid);
		List<Marketquotation> lists = marketservice.getDatas4Forward(mar, pageSize, currentPage);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据获取成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 获取币别
	 * @return
	 */
	public String jcgetCurrencyList(){
		List<Currency> lists = marketservice.getCurrencyList();
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据成功返回!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 获取币别--网页端
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名getCurrencyList4Web修改为getCurrencyListWeb
	 */
	public void getCurrencyListWeb(){
		List<Currency> lists = marketservice.getCurrencyList();
		this.returnInfo(JsonUtils.toJson(lists));
		
	}
	/**
	 * 获取具有app用户的公司名称
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名getCompany4App修改为getCompanyApp
	 */
	public void getCompanyApp(){
		List<Map<String,Object>> lists = marketservice.getCompany4App();
		this.returnInfo(JsonUtils.toJson(lists));
	}
	/**
	 * 根据主账号id获取app子行号信息
	 * @param id
	 * @return
	 */
	public void getNickNameByCompany(){
		List<Map<String,Object>> lists = marketservice.getNickNameByCompany(id);
		this.returnInfo(JsonUtils.toJson(lists));
	}
	/**
	 * APP端价格查询 获取选定日期 特定用户产品信息
	 * @param userid
	 * @param date
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetProductList4Query修改为jcgetProductListQuery
	 */
	public String jcgetProductListQuery(){
		
		String userid = this.getSubUser();
		List<Product> lists = marketservice.getProductList4Query(userid, date);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	/**
	 * APP端价格查询 获取选定日期 特定用户国家信息
	 * @param userid
	 * @param date
	 * @param querytype"0":子账号/"1":主账号下所有子账号
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetCountryList4Query修改为jcgetCountryListQuery
	 */
	public String jcgetCountryListQuery(){

		String userid = this.getSubUser();
		List<Country> lists = marketservice.getCountryList4Query(userid, date);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	/**
	 * APP端价格查询 获取选定日期 特定用户销售商信息
	 * @param userid
	 * @param date
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetSupplier4Query修改为jcgetSupplierQuery
	 */
	public String jcgetSupplierQuery(){

		String userid = this.getSubUser();
		List<Map<String,Object>> lists = marketservice.getSupplier4Query(userid, date);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	/**
	 * 价格查询app端
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcgetDatas4Query修改为jcgetDatasQuery
	 */
	public String jcgetDatasQuery(){
		//markequotationText="{\"marketdate\":\"2016-08-01\"}";
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		String userid=this.getSubUser();
		mq.setEnteruserid(userid);
		mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");//转发确认完数据
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> list = marketservice.getDatas4Query(mq, novapage,datenum);
		//对查询的结果进行二次封装
		Map<String,Map<String,List<Marketquotation>>> lists = marketservice.secondProcess(list);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", lists);
			resultInfo.setData(dataMap);
		  return RESULT;
	}
	/**
	 * 获取当前app登录用户所属主账号的所有app子账号信息
	 * @param parentid
	 * @return
	 */
	public String getSubUser(){
		
		duser=(User) httpSession.getAttribute("marketuser");
		String parentid = duser.getParentid();
		//子账号
		if("0".equals(querytype)){
			return "'"+duser.getUserid()+"'";
		}
		//主账号
		 List<Map<String, Object>> lists = marketservice.getNickNameByCompany(parentid);
		 String id = "";
		for(Map<String, Object> p: lists){
			id += p.get("userid")+",";
		}
		id = "".equals(id) ? "":id.substring(0, id.length()-1);
		
		return BeanUtils.getIdsAsString(id, ",");
	}
	/**
	 * 获取app版本号
	 * @方法名称:getAppVersion
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月24日-上午10:01:21
	 */
	public String jcgetAppVersion(){
		Version version = marketservice.getAppVersion("");
		dataMap.put("datas", version);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	/**
	 * 获取app版本信息列表
	 * @方法名称:getAppVersionList
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞ 
	 * void
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月17日-下午1:51:29
	 */
	public void getAppVersionList(){
		List<Version> list = Lists.newArrayList();
		Version version =  marketservice.getAppVersion(Constants.OS_ANDROID);
		Version version2 =  marketservice.getAppVersion(Constants.OS_IOS);
		list.add(version);
		list.add(version2);
		//json数据返回前台
		this.returnInfo(JsonUtils.toJson(list));
	}
	/**
	 * 更新版本号
	 * @方法名称:updateAppVersion
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月17日-下午4:19:25
	 */
	public String updateAppVersion(){
		
		List<Version> list = JsonUtils.fromJson(jsonText,new TypeToken<List<Version>>(){}.getType());
		boolean flag = marketservice.updateVersion(list);
		if(flag){
			resultInfo.setMsg(Constants.UPDATE_SUCCESS);
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg(Constants.UPDATE_FAILURE);
		}
		return RESULT;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMarkequotationText() {
		return markequotationText;
	}
	public void setMarkequotationText(String markequotationText) {
		this.markequotationText = markequotationText;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Integer getMarkettype() {
		return markettype;
	}
	public void setMarkettype(Integer markettype) {
		this.markettype = markettype;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getQuerytype() {
		return querytype;
	}
	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}
	public Integer getDatenum() {
		return datenum;
	}
	public void setDatenum(Integer datenum) {
		this.datenum = datenum;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCominfoid() {
		return cominfoid;
	}

	public void setCominfoid(String cominfoid) {
		this.cominfoid = cominfoid;
	}

	public String getExporttype() {
		return exporttype;
	}

	public void setExporttype(String exporttype) {
		this.exporttype = exporttype;
	}

	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public static void main(String[] args) {
		MarketAction ma = new MarketAction();
		ma.jcgetDatasQuery();
	}

	/**
	 * customsinfoid
	 *
	 * @return  the customsinfoid
	 * @since   1.0.0
	 */
	
	public String getCustomsinfoid() {
		return customsinfoid;
	}

	/**
	 * @param customsinfoid the customsinfoid to set
	 */
	public void setCustomsinfoid(String customsinfoid) {
		this.customsinfoid = customsinfoid;
	}

	/**
	 * usertype
	 *
	 * @return  the usertype
	 * @since   1.0.0
	 */
	
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * jsonValue
	 *
	 * @return  the jsonValue
	 * @since   1.0.0
	 */
	
	public String getJsonValue() {
		return jsonValue;
	}

	/**
	 * @param jsonValue the jsonValue to set
	 */
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	/**
	 * type
	 *
	 * @return  the type
	 * @since   1.0.0
	 */
	
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
