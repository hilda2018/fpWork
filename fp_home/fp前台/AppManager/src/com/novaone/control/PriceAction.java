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

import com.google.inject.Inject;
import com.nova.frame.collect.Lists;
import com.nova.frame.collect.Maps;
import com.nova.frame.model.ObjectId;
import com.nova.frame.utils.DateUtils;
import com.novaone.constants.Constants;
import com.novaone.jwt.Jwt;
import com.novaone.model.Attention;
import com.novaone.model.Cominfo;
import com.novaone.model.Country;
import com.novaone.model.Currency;
import com.novaone.model.CustomsInfo;
import com.novaone.model.MQDetail;
import com.novaone.model.Marketquotation;
import com.novaone.model.Mqpicture;
import com.novaone.model.Product;
import com.novaone.model.Template;
import com.novaone.model.User;
import com.novaone.model.Version;
import com.novaone.service.app.MarketService;
import com.novaone.utils.BeanUtils;
import com.novaone.utils.ChinaInitial;
import com.novaone.utils.JsonUtils;
import com.novaone.utils.Md5Encoder;
import com.novaone.utils.OplogUtils;
import com.novaone.utils.PushUtil;
import com.novaone.utils.UUIDUtil;
import com.novaone.utils.ValidateUtil;
/**
 * 
 * 价格行情Action
 * @类编号:
 * @类名称:PriceAction
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月24日 下午4:32:20
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Action("priceaction")
@Results({@Result(name = "test01_parmwin_grid", location = "test/test01-parmWin-Grid.jsp"),
    @Result(name = "marketpage", location = "market/market.jsp"),
    @Result(name = "marketView", location = "market/marketView.jsp"),
    @Result(name = "result", type = "json", params = {"root", "resultInfo"}),
    @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"})})
public class PriceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final String RESULT="result";
	private static final Log logger = LogFactory.getLog(PriceAction.class);
	private String username;//用户名,常用联系人昵称
	private String password;//密码
	private String newpwd;//新密码
	@Inject
	private MarketService marketservice;
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private String markequotationText;//接收从前台传递过来的json信息
    private String jsonText;//接收从前台传递过来的json信息
    private Integer num;//查询数量
    private Integer fieldtype;//查询字段类型
    private String fieldvalue;//查询字段值
    private String id;//记录id ,当多条时用#进行分割
    private String queryname;//转发人姓名
    private String receiver;//转发人id,当多个人时已"#"分隔
    private Integer markettype;//市场类型
    private String productid;//产品id
    private String countryid;//国家id
    private String country;//国家
    private String product;//产品
    private String querytype = "0";//查询类型 "0":子账号/"1":主账号下所有子账号
    private Integer datenum;//收件夹显示日期天数
    private String sort;//排序字段
    private List<Cominfo> infolist=new ArrayList<Cominfo>();//常用信息统计列表
    private Integer currentPage=1;
    private Integer pageSize=10;
    private String date;//过滤日期
    private String cominfoid;//常用信息id,删除时用
    private String exporttype;//导出类型 EN/CN
    private String viewtype;//查看类型 分为person primary 以及all
    private String subUsers;
    private String customsinfoid;//常用联系人id
    private String token;//前台传递过来的token 验证信息 
    private String query;//查询字段
    private String status;//是否是收件箱中数据
    private String code;//菜单编码(权限用)
    private String type;//类型参数
    
    /**
     * 跳转页面使用<br>
     * 价格行情列表页面marketaction!novapage.action?p=marketpage<br>
     * @return
     */
    public String page() {
        return request.getParameter("p"); 
    }
    /**
     * 用户注册
     * @return
     */
	public String jzregister(){
		User user = JsonUtils.fromJson(jsonText, User.class);
		user.setUserid(ObjectId.StringId());
		user.setIsapp(Constants.ISAPP);
		//用户名、密码验证
		if(BeanUtils.isEmpty(user.getUsername()) || BeanUtils.isEmpty(user.getPassword()) || !ValidateUtil.isENG_NUM_(user.getUsername())){
			resultInfo.setMsg("注册账号或者密码不能为空且账号只能由英文字母、数字以及下划线构成!");
			resultInfo.setResult(false);
			return RESULT;
		}
		//手机号码验证
		if(!ValidateUtil.isMobile(user.getPhonenum())){
			resultInfo.setMsg("请输入11位有效手机号码!");
			resultInfo.setResult(false);
			return RESULT;
		}
		//真实姓名验证
		if(BeanUtils.isEmpty(user.getNickname()) || ValidateUtil.isLengOut(user.getNickname(), 20)){
			resultInfo.setMsg("真实姓名不能为空且最大长度不大于20个字符!");
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
	 * 用户信息信息修改
	 * @方法名称:jzUserModify
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年9月7日-下午4:41:28
	 */
	public String jzUserModify(){
		//TODO
		return null;
	}
	
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public String jzlogin(){
		//password=SecurityUtils.novaEnCryption(password);
		password=Md5Encoder.encodePassword(password+"");
		User user=marketservice.findByCodeAndPwd(username, password);	
		if(user != null){
			//生成token 验证信息
			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("uid", user.getUserid());// 用户id
			payload.put("iat", date.getTime());// 生成时间:当前
			payload.put("parentid", user.getParentid());//当前登录用户主账号id
			payload.put("ext", date.getTime() + 1000 * 60 * 60 * 24 * 30L);// 过期时间一个月
			String token = Jwt.createToken(payload);
			resultInfo.setResult(true);
			resultInfo.setMsg("登录成功!");
			dataMap.put("token", token);
			dataMap.put("user", user);
			resultInfo.setData(dataMap);
		}else
		{
			resultInfo.setResult(false);
			resultInfo.setMsg("用户名或密码不对,请重新登录!");
		}
		return RESULT;
	}
	/**
	 * 根据用户id获取用户信息
	 * @方法名称:jzgetUserById
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年12月20日-上午10:30:38
	 */
	public String jzgetUserById(){
		String id = this.getUserid(token);
		User user = marketservice.getUserById(id);
		dataMap.put("data", user);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
		return RESULT;
	}
	
	/**
	 * 根据token信息获取用户id
	 * @方法名称:getUserid
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param token
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月25日-下午3:11:44
	 */
	public String getUserid(String token){
		Map<String, Object> resultMap = Jwt.validToken(token);
		Map<String,String> hashmap = (Map<String, String>) resultMap.get("data");
		return hashmap.get("uid");
	}
	/**
	 * 根据token 获取当前登录用户主账号id
	 * @方法名称:getParentid
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param token
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月25日-下午4:03:41
	 */
	public String getParentid(String token){
		Map<String, Object> resultMap = Jwt.validToken(token);
		Map<String,String> hashmap = (Map<String, String>) resultMap.get("data");
		return hashmap.get("parentid");
	}
	/**
	 * 根据当前用户登录id获取markettype
	 * @方法名称:getMarkettype
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param userid
	 * @return 
	 * Integer
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月25日-下午4:09:23
	 */
	public Integer getMarkettype(String userid){
		User user = marketservice.getUserbyId(userid);
		return user.getMarkettype();
	}
	
	/**
	 * 检查手机号码是否重复
	 * @return
	 */
	public String jzcheckLoginName(){
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
	public String jzgetAuthority(){
		String userid = this.getUserid(token);
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
	public String jzloginout(){
			httpSession.removeAttribute("marketuser");
			resultInfo.setMsg("退出成功!");
			resultInfo.setResult(true);
			return RESULT;
	}
	/**
	 *价格录入
	 * @return
	 */
	public String jzadd(){
		//前台传递过来的json转对象
		Marketquotation mk=JsonUtils.fromJson(markequotationText.replaceAll("\"\"", "null"),Marketquotation.class);
		String userid = this.getUserid(token);
		//设置市场类型
		Integer markettype = this.getMarkettype(userid);
		mk.setMarkettype(markettype);
		
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
				//添加板数(和老版本兼容)
				if(BeanUtils.isNotEmpty(m.getPlatenum())){
					mk2.setPlatenum(m.getPlatenum());
				}
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
		mq.setEnteruserid(userid);
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
				//添加日志
				OplogUtils.addOplog(userid,"进行价格行情录入操作!", Integer.parseInt(Constants.TYPE_0));
			}else{
				resultInfo.setResult(false);
				resultInfo.setMsg("保存失败!");
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
			//this.getCominfo(Constants.FLAG_TYPE_1, mq.getProduct(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_2, mq.getVarieties(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_3, mq.getSpec(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_4, mq.getSupplier(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_5, mq.getVendor(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_6, mq.getBrand(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_7, mq.getWeight(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_8, mq.getPackagetype(),mq.getCountry(),mq.getProduct());
			this.getCominfo(Constants.FLAG_TYPE_9, mq.getQuality(),mq.getCountry(),mq.getProduct());
			//增加字段新旧 newold值
			this.getCominfo(Constants.FLAG_TYPE_12, mq.getNewold(), mq.getCountry(), mq.getProduct());
			//this.getCominfo(Constants.FLAG_TYPE_10, mq.getSailedescribe(),mq.getCountry(),mq.getProduct());
			//this.getCominfo4(Constants.FLAG_TYPE_11, mq.getPlatenum()+"",mq.getCountry(),mq.getProduct());
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
		String userid = this.getUserid(token);
		Cominfo com=new Cominfo();
		if(!BeanUtils.isEmpty(fieldvalue)){
			com.setCominfoid(ObjectId.StringId());
			com.setUsername(userid);
			com.setFieldtype(fieldtype);
			com.setFieldvalue(fieldvalue);
			com.setCountry(country);
			com.setProduct(product);
			infolist.add(com);
		}
	}
	/**
	 * 插入常用信息统计表
	 * @return
	 */
	public String jzinsertCominfo(){
		Cominfo cm = JsonUtils.fromJson(markequotationText, Cominfo.class);
		String userid = this.getUserid(token);
		List<Cominfo> info=new ArrayList<Cominfo>();
		cm.setCominfoid(ObjectId.StringId());
		cm.setUsername(userid);//常用信息统计表username存入当前用户id
		info.add(cm);
		if(BeanUtils.isEmpty(cm.getFieldvalue())){
			resultInfo.setMsg("添加信息不能为空!");
			resultInfo.setResult(false);
			return RESULT;
		}
		logger.error("常用统计表插入------");
		logger.error(cm.getFieldvalue().trim());
		Map<String,Object> map=marketservice.insertCominfo(info);

		if(map.get("flag") !=null && (Boolean)map.get("flag")){
			resultInfo.setMsg("数据添加成功!");
			resultInfo.setResult(true);
			resultInfo.setData(map);
			//添加操作日志
			OplogUtils.addOplog(userid, "添加常用信息操作,字段类型为:"+cm.getFieldtype()+"字段值为:"+cm.getFieldvalue(), Integer.parseInt(Constants.TYPE_0));
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
	public String jzdeleteCominfo(){
		boolean flag = marketservice.deleteCominfo(cominfoid);
		String userid = this.getUserid(token);
		if(flag){
			resultInfo.setMsg("删除成功!");
			resultInfo.setResult(true);
			OplogUtils.addOplog(userid,"删除用户常用信息数据,id为"+cominfoid, Integer.parseInt(Constants.TYPE_0));
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
	public String jzdetail(){
		Marketquotation mq=marketservice.detail(id);
		//获取图片信息
		List<Mqpicture> piclist = marketservice.getPiclist(id);
		mq.setPiclist(piclist);
		//对查询结果进行二次封装
		List<MQDetail> lists = Lists.newArrayList(
				new MQDetail(mq.getBrand(), mq.getVarieties(), mq.getSpec(), mq.getMinprice(), mq.getMaxprice(),mq.getPlatenum()));
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
	public String jzdelete(){
		//可以一次删除多条记录id之间以#分隔
		//String ids=this.getIds(id, "#");
		String ids = BeanUtils.getIdsAsString(id, "#");
		boolean flag=marketservice.delete(ids);
		String userid = this.getUserid(token);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("删除成功!");
			//添加操作日志
			OplogUtils.addOplog(userid, "价格行情记录删除,id为:"+id, Integer.parseInt(Constants.TYPE_0));
		}
		return RESULT;
	}

	/**
	 * 记录修改(由于多品牌录入update保存有问题,固记录先删除后增加)
	 * @param mq
	 * @return
	 */
	@Deprecated
	public String jzmodify(){
		Marketquotation mk=JsonUtils.fromJson(markequotationText.replaceAll("\"\"", "null"), Marketquotation.class);
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
				//板数
				mqlist.add(mk2);
			}
		}else{
			mqlist.add(mk);
		}
		boolean flag=marketservice.modify4App(mqlist,type);
		if(flag){
			resultInfo.setResult(true);
			resultInfo.setMsg("修改成功!");
			String userid = this.getUserid(token);
			OplogUtils.addOplog(userid,"价格行情记录修改操作", Integer.parseInt(Constants.TYPE_0));
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("修改失败!");
		}
		return RESULT;
	}
	/**
	 * 记录修改(由于多品牌录入update保存有问题,固记录先删除后增加)
	 * modify 2016-11-22 多品牌录入增加 板数
	 * @param mq
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzmodify4V2修改为jzmodifyUpdated
	 */
	public String jzmodifyUpdated(){
		Marketquotation mk=JsonUtils.fromJson(markequotationText.replaceAll("\"\"", "null"), Marketquotation.class);
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
				//板数
				mk2.setPlatenum(m.getPlatenum());
				
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
			String userid = this.getUserid(token);
			OplogUtils.addOplog(userid,"价格行情记录修改操作", Integer.parseInt(Constants.TYPE_0));
		}else{
			resultInfo.setResult(false);
			resultInfo.setMsg("修改失败!");
		}
		return RESULT;
	}
	/**
	 * 获取国家列表(通过产品id)
	 * @return
	 */
	@Deprecated
	public String jzgetCountryList(){
		List<Country> list=marketservice.getCountryList(productid);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	
	/**
	 * 获取品名列表
	 * 修改为从常用信息列表中获取品名信息
	 * @return
	 */
	@Deprecated
	public String jzgetProductList(){
		//List<Product> list=marketservice.getProductList();
		String userid = this.getUserid(token);
		List<Product> list = marketservice.getProductList(userid, Constants.FLAG_TYPE_1);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	/**
	 * 价格录入获取产品国家列表
	 * @方法名称:jzgetProductCountryList
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年9月8日-下午4:55:23
	 */
	public String jzgetProductCountryList(){
		String userid = this.getUserid(token);
		//获取获取用户关注的品名
		List<Attention> lists = marketservice.getAttentionList(userid);
		Map<String,List<Country>> map = Maps.newLinkedHashMap();
		List<Country> list = null;
		if(!BeanUtils.isEmpty(lists)){
			for(Attention att:lists){
				 list=marketservice.getCountryList(att.getProductid());
				 //key:productid#productname
				 if(!BeanUtils.isEmpty(list)){
					 map.put(att.getProductid()+"#"+att.getProductname(), list);
				 }
				
			}
		}
		dataMap.put("datas", map);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
		return RESULT;
	}
	
	
	
	/**
	 * action朝前台返回值
	 * @param result
	 */
	@Deprecated
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
	 * 字段类型0:国家;1:品名;2:品种;3:规格;4:销售商;5:供应商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数,12:新旧
	 */
	public String jzgetCominfoList(){
		String userid = this.getUserid(token);
		List<Cominfo> list=marketservice.getCominfoList(userid,num, fieldtype, fieldvalue,country,product);
			resultInfo.setResult(true);
			resultInfo.setMsg("数据返回成功");
			dataMap.put("datas", list);
			resultInfo.setData(dataMap);
		return RESULT;
	}
	
	/**
	 * 记录搜索
	 * @return
	 */
	@Deprecated
	public String jzgetDatas(){
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		String userid = this.getUserid(token);
		mq.setEnteruserid(userid);
		//设置市场类型
		mq.setMarkettype(this.getMarkettype(userid));
		
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
	 * 默认记录显示(默认显示昨天与今天的数据),点击更多的时候查询以前的数据.
	 * 记录按市场日期倒序排列
	 * modify 2016-04-22
	 * 记录按照最近使用时间倒序排序
	 * modify 2016-05-18
	 * @return
	 */
	@Deprecated
	public String jzgetDefaultDatas(){
		//Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		//在session中获取当前用户id
		String userid = this.getUserid(token);
		Marketquotation mq=new Marketquotation();
		mq.setEnteruserid(userid);
		mq.setEnterdate(DateUtils.getDate2SStr(DateUtils.getCurrentDate()));
		//设置市场类型
		mq.setMarkettype(this.getMarkettype(userid));
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
	@Deprecated
	public String jzgetDimDatas(){
		String userid = this.getUserid(token);
		Marketquotation mq=new Marketquotation();
		mq.setEnteruserid(userid);
		//设置市场类型
		mq.setMarkettype(this.getMarkettype(userid));
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
	 * 历史记录默认 查询 排序 显示
	 * @方法名称:jzgetDatas4New
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月12日-下午4:48:02
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetDatas4New修改为jzgetDatasUpdated
	 */
	public String jzgetDatasUpdated(){
		String userid = this.getUserid(token);
		Marketquotation mq=new Marketquotation();
		//过滤用户
		mq.setEnteruserid(userid);
		//设置市场类型
		mq.setMarkettype(this.getMarkettype(userid));
		//过滤日期
		mq.setMarketdate(BeanUtils.isEmpty(date) ? DateUtils.getDate2SStr(new Date()):date);
		//转发确认
		mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");
		
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> lists = marketservice.getDatas4New(mq, fieldvalue, novapage);
		
		resultInfo.setResult(true);
		resultInfo.setMsg("数据返回成功");
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		
		return RESULT;
	}
	/**
	 * 
	 * @方法名称:jzgetDatas4NewV2
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月14日-下午2:37:19
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetDatas4NewV2修改为jzgetDatasUpdatedNew
	 */
	public String jzgetDatasUpdatedNew(){
		String userid = this.getUserid(token);
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		//过滤用户
		mq.setEnteruserid(userid);
		//设置市场类型
		mq.setMarkettype(this.getMarkettype(userid));
		//过滤日期
		mq.setMarketdate(mq.getMarketdate());
		//转发确认
		mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");
		
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> lists = marketservice.getDatas4New(mq, fieldvalue, novapage);
		
		resultInfo.setResult(true);
		resultInfo.setMsg("数据返回成功");
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		
		return RESULT;
	}
	
	
	
	/**
	 * 记录转发(可以转发多条记录,同时转发给多个人)
	 * @return
	 */
	public String jzforwardData(){
		//记录id按照id#id#id的方式传递
		List<Marketquotation> marlist=new ArrayList<Marketquotation>();
		Marketquotation mar=null;
		List<Mqpicture> piclist = null;
		List<String> ids = BeanUtils.getIdsAsList(id, "#");
		if(BeanUtils.isEmpty(ids)){
			resultInfo.setMsg("请选择需要转发的记录!");
			resultInfo.setResult(false);
			return RESULT;
		}
		//获取所有需要转发的记录
		for(String id:ids){
			mar=marketservice.detail(id);
			piclist = marketservice.getPiclist(id);
			mar.setPiclist(piclist);
			marlist.add(mar);
		}
		//记录人id为被转发人id,转发人id

		List<Marketquotation> receiverlist=new ArrayList<Marketquotation>();
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
			 resultInfo.setMsg("数据转发成功!");
			 //消息推送
			 PushUtil.push_alias_message(Constants.APP_MSG, aliaseslist);
			 String userid = this.getUserid(token);
			 //添加日志
			 OplogUtils.addOplog(userid, "进行价格记录转发操作!", Integer.parseInt(Constants.TYPE_0));
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
		m2.setVarieties(ma.getVarieties());//品种
		m2.setSpec(ma.getSpec());//规格
		m2.setSupplier(ma.getSupplier());//供应商
		m2.setVendor(ma.getVendor());//销售商
		m2.setBrand(ma.getBrand());//品牌
		m2.setWeight(ma.getWeight());//重量
		m2.setTransport(ma.getTransport());//运输方式 0:海运;1:空运;2:陆运
		m2.setPackagetype(ma.getPackagetype());//包装
		m2.setMinprice(ma.getMinprice());//最低价格
		m2.setMaxprice(ma.getMaxprice());//最高价格 
		m2.setQuality(ma.getQuality());//品质
		m2.setSailedescribe(ma.getSailedescribe());//销售描述	
		m2.setPlatenum(ma.getPlatenum());//板数
		m2.setIstransmit(ma.getIstransmit());//是否转发 0:非转发;1:转发'
		m2.setTransmituser(ma.getTransmituser());//转发人
		m2.setCurrentdate(ma.getCurrentdate());//时间戳
		m2.setModifydate(ma.getModifydate());//修改时间
		m2.setStatus(ma.getStatus());//记录状态 0记录转发没有确认,1:记录转发已确认,记录录入默认状态
		m2.setCountryproductid(ma.getCountryproductid());//国家产品id设置
		//modify by chenzijun 2016-07-30
		m2.setCntrno(ma.getCntrno());//柜号
		m2.setFarm(ma.getFarm());//果园
		m2.setPackingplant(ma.getPackingplant());//包装厂
		m2.setCurrencyid(ma.getCurrencyid());//币别
		m2.setNewold(ma.getNewold());//新旧
		m2.setPiclist(ma.getPiclist());//图片信息
		return m2;
		
	}
	
	/**
	 * 记录复制(可以复制多条记录,复制"价格去掉,日期改为今天,录入人改为自己.)
	 * @return
	 */
	public String jzdataCopy(){
		//记录id按照id#id#id的方式传递
		List<Marketquotation> marlist=new ArrayList<Marketquotation>();
		Marketquotation mar=null;
		List<String> ids = BeanUtils.getIdsAsList(id, "#");
		//获取所有需要复制的记录信息
		for(String id:ids){
			mar=marketservice.detail(id);
			marlist.add(mar);
		}
		String userid = this.getUserid(token);
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
			//添加日志
			OplogUtils.addOplog(userid, "价格行情进行复制操作,操作id为:"+id, Integer.parseInt(Constants.TYPE_0));
		}
		return RESULT;
	}
	/**
	 * 
	 * @方法名称:jzaddCustomsInfo
	 * @内容摘要:添加常用联系人(新增和编辑)
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:06:51
	 */
	@Deprecated
	public String jzaddCustomsInfo(){
		CustomsInfo info=JsonUtils.fromJson(markequotationText, CustomsInfo.class);
		String userid = this.getUserid(token);
		
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
			//添加日志
			OplogUtils.addOplog(userid, "进行添加常用联系人操作,姓名为:"+info.getUsername(), Integer.parseInt(Constants.TYPE_0));
		}else{
			resultInfo.setMsg("保存失败!");
		}
		return RESULT;
	}
	/**
	 * 根据子账号id获取相关信息,生成常用联系人信息
	 * @方法名称:jzaddCustomsInfoNew
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月12日-上午10:03:46
	 */
	public String jzaddCustomsInfoNew(){
		//获取被添加联系人子账号信息
		User user = marketservice.getUserbyId(id);
		//当前登录用户id
		String userid = this.getUserid(token);
		//检查是否在常用联系人表中
	      boolean flag=marketservice.checkCustomsTel(userid, user.getPhonenum(),"");
	      
	  	if(flag){
			resultInfo.setMsg("此联系人已存在!");
			resultInfo.setResult(false);
			return RESULT;
	  	}
		
		CustomsInfo info = new CustomsInfo();
		info.setTel(user.getPhonenum());
		info.setUsername(username);//常用联系人昵称
		info.setCompany(user.getCompanycnname());
		info.setEmail(user.getEmail());
		// 获取姓名的首字母
		if(BeanUtils.isNotEmpty(username)){
			String companyStr=ChinaInitial.getPYIndexStr(username, true);
			info.setInitial(companyStr.substring(0, 1));
		}
		//保存常用联系人信息
		 boolean flag2=marketservice.addCustomsInfo(info,userid);
			if(flag2){
				resultInfo.setMsg("保存成功!");
				resultInfo.setResult(true);
				//添加日志
				OplogUtils.addOplog(userid, "进行添加常用联系人操作,昵称为:"+info.getUsername()+",电话为:"+info.getTel(), Integer.parseInt(Constants.TYPE_0));
			}else{
				resultInfo.setMsg("保存失败!");
			}
			return RESULT;
	}
	/**
	 * 修改常用联系人信息 ,例如:昵称 username
	 * @方法名称:jzmodifyCustomsInfo
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月14日-上午10:48:46
	 */
	public String jzmodifyCustomsInfo(){
		CustomsInfo info = new CustomsInfo();
		info.setCustomsinfoid(customsinfoid);
		info.setUsername(username);//对username进行修改
		// 获取姓名的首字母
		if(BeanUtils.isNotEmpty(username)){
			String companyStr=ChinaInitial.getPYIndexStr(username, true);
			info.setInitial(companyStr.substring(0, 1));
		}
		//当前登录用户id
		String userid = this.getUserid(token);
		
		boolean flag = marketservice.addCustomsInfo(info, userid);
		if(flag){
			resultInfo.setMsg(Constants.UPDATE_SUCCESS);
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg(Constants.UPDATE_FAILURE);
		}
		return RESULT;
	}
	
	
	
	/**
	 * 常用联系人删除
	 * @方法名称:jzdeleteCustomsInfo
	 * @内容摘要: 
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午4:06:59
	 */
	public String jzdeleteCustomsInfo(){
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
			String userid = this.getUserid(token);
			//添加操作日志
			OplogUtils.addOplog(userid, "常用联系人删除操作", Integer.parseInt(Constants.TYPE_0));
		}else{
			resultInfo.setMsg(Constants.DELETE_FAILURE);
		}
		return RESULT;
		
	}
	/**
	 * 
	 * @方法名称:jzcheckCustomsName
	 * @内容摘要: 检查常用联系人姓名是否重复
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:07:47
	 */
	public String jzcheckCustomsName(){
		String name=request.getParameter("name");//常用联系人名称
		String userid = this.getUserid(token);
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
	 * @方法名称:jzcheckCustomsTel
	 * @内容摘要: 检查常用联系人电话是否重复
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:08:09
	 */
	public String jzcheckCustomsTel(){
		String tel=request.getParameter("tel"); //常用联系人电话
		String userid = this.getUserid(token);
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
	public String jzgetForwardPerson(){
		String userid = this.getUserid(token);
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
	public String jzconfirm(){
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
	public String jzmessageBox(){
		String userid = this.getUserid(token);
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> lists=marketservice.messageBox(userid,sort,this.getMarkettype(userid), datenum, novapage);
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
	public String jzchangePwd(){
		String userid = this.getUserid(token);
		//获取登录用户用户名
		User olduser = marketservice.getUserbyId(userid);
		//校验密码是否正确
		User user=marketservice.findByCodeAndPwd(olduser.getUsername(), Md5Encoder.encodePassword(password));
		if(user==null){
			resultInfo.setMsg("原密码不对,请重新输入!");
			resultInfo.setResult(false);
			return RESULT;
		}
		boolean flag=marketservice.changePassword(user.getUserid(), Md5Encoder.encodePassword(newpwd));
		if(flag){
			resultInfo.setMsg("密码修改成功!");
			resultInfo.setResult(true);
			OplogUtils.addOplog(userid, "进行密码修改操作!", Integer.parseInt(Constants.TYPE_0));
		}else{
			resultInfo.setMsg("密码修改失败!");
		}
		return RESULT;
	}
	/**
	 * 添加关注信息
	 * @方法名称:jzaddAttention
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月16日-下午4:35:43
	 */
	public String jzaddAttention(){
		String userid = this.getUserid(token);
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
			OplogUtils.addOplog(userid, "进行添加品名关注操作!", Integer.parseInt(Constants.TYPE_0));
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
	public String jzgetAttentionList(){
		String userid = this.getUserid(token);
		List<Attention> lists = marketservice.getAttentionList(userid);
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据获取成功!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 用户产品关注查询列表
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetProductList4Attention修改为jzgetProductListUpdatedAttention
	 */
	public String jzgetProductListUpdatedAttention(){
		String userid = this.getUserid(token);
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
	public String jzaddTemplate(){
		String userid = this.getUserid(token);
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
			OplogUtils.addOplog(userid,"进行添加或修改用户模板操作", Integer.parseInt(Constants.TYPE_0));
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
	public String jzdeleteTemplate(){
		String ids = BeanUtils.getIdsAsString(id, ",");
		boolean flag = marketservice.deleteTemplate(ids);
		
		if(flag){
			resultInfo.setMsg("删除成功!");
			resultInfo.setResult(true);
			//添加日志操作
			String userid = this.getUserid(token);
			OplogUtils.addOplog(userid, "进行模板删除操作!", Integer.parseInt(Constants.TYPE_0));
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
	public String jzgetTemplateList(){
		String userid = this.getUserid(token);
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
	public String jzgetTemplateDetail(){
		String userid = this.getUserid(token);
		Template template = JsonUtils.fromJson(jsonText, Template.class);
		template.setUserid(userid);
		
		template = marketservice.getTemplateDetail(template);
		if(null == template){
			template = new Template();
			template.setVarieties(Constants.FLAG_Y);//默认为Y
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
			template.setPic(Constants.FLAG_Y);
			template.setNewold(Constants.FLAG_Y);
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
	public String jzgetTemplateDetailById(){
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
	public String jzgetDatasByDate(){
		String userid = this.getUserid(token);
		//String date=request.getParameter("date");
		//String status = request.getParameter("status");//是否是收件箱中数据
		
		Marketquotation mq = new Marketquotation();
		mq.setEnteruserid(userid);
		mq.setStatus(status);
		mq.setMarkettype(this.getMarkettype(userid));//市场类型
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
	 * @修改内容 方法名jzgetCountryProduct4Forward修改为jzgetCountryProductUpdatedForward
	 */
	public String jzgetCountryProductUpdatedForward(){
		String userid = this.getUserid(token);
		List<Map<String,Object>> lists = marketservice.getCountryProduct(userid, date,this.getMarkettype(userid));
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
	 * @修改内容 方法名jzgetSupplier4Forward修改为jzgetSupplierUpdatedForward
	 */
	public String jzgetSupplierUpdatedForward(){
		String userid = this.getUserid(token);
		List<Map<String,Object>> lists = marketservice.getSupplier4Forward(userid, date,this.getMarkettype(userid));
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
	 * @修改内容 方法名jzgetDatas4Forward修改为jzgetDatasUpdatedForward
	 */
	public String jzgetDatasUpdatedForward(){
		String userid = this.getUserid(token);
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
	public String jzgetCurrencyList(){
		List<Currency> lists = marketservice.getCurrencyList();
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg("数据成功返回!");
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * APP端价格查询 获取选定日期 特定用户产品信息
	 * @param userid
	 * @param date
	 * @return
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetProductList4Query修改为jzgetProductListUpdatedQuery
	 */
	public String jzgetProductListUpdatedQuery(){
		
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
	 * @修改内容 方法名jzgetCountryList4Query修改为jzgetCountryListUpdatedQuery
	 */
	public String jzgetCountryListUpdatedQuery(){

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
	 * @修改内容 方法名jzgetSupplier4Query修改为jzgetSupplierUpdatedQuery
	 */
	public String jzgetSupplierUpdatedQuery(){

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
	 * @修改内容 方法名jzgetDatas4Query修改为jzgetDatasUpdatedQuery
	 */
	public String jzgetDatasUpdatedQuery(){
		//markequotationText="{\"marketdate\":\"2016-08-01\"}";
		Marketquotation mq=JsonUtils.fromJson(markequotationText, Marketquotation.class);
		String userid=this.getSubUser();
		mq.setEnteruserid(userid);
		mq.setStatus(Constants.FLAG_ISTRANSMIT_1+"");//转发确认完数据
		novapage.setCurrentPage(currentPage);
		novapage.setPageSize(pageSize);
		List<Marketquotation> list=marketservice.getDatas4Query(mq, novapage,datenum);
		
		Map<String,List<Marketquotation>> maplist = marketservice.secondProcessNew(list);
		
		resultInfo.setResult(true);
		resultInfo.setMsg("数据返回成功");
		dataMap.put("datas", maplist);
		resultInfo.setData(dataMap);
		  return RESULT;
	}
	/**
	 * 根据记录id获取分享数据详情列表
	 * @方法名称:jzgetshareDatas
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月8日-下午2:49:10
	 */
	public String jzgetshareDatas(){
		//分享记录id以","隔开
		String ids = BeanUtils.getIdsAsString(id, ",");
		List<Marketquotation> lists = marketservice.getshareDatasList(ids);
		
		//二次封装
		Map<String,List<Marketquotation>> maplist = marketservice.secondProcessNew(lists);
		dataMap.put("datas", maplist);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
		
		return RESULT;
	}
	
	
	/**
	 * 获取当前app登录用户所属主账号的所有app子账号信息
	 * @param parentid
	 * @return
	 */
	public String getSubUser(){
		//当前登录用户id
		String userid = this.getUserid(token);
		//主账号id
		String parentid = this.getParentid(token);
		//"0":子账号/"1":主账号下所有子账号
		//根据所赋权限判断当前账号显示的数据
		String querytype = marketservice.getAuthority(userid, Constants.MENU_CODE_COMPANY) ? "1":"0";
		//子账号
		if("0".equals(querytype)){
			return "'"+userid+"'";
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
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetAppVersion4Android修改为jzgetAppVersionUpdatedAndroid
	 */
	public String jzgetAppVersionUpdatedAndroid(){
		Version version = marketservice.getAppVersion(Constants.OS_ANDROID);
		dataMap.put("datas", version);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	
	/**
	 * 
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jzgetAppVersion4ios修改为jzgetAppVersionUpdatedios
	 */
	public String jzgetAppVersionUpdatedios(){
		Version version = marketservice.getAppVersion(Constants.OS_IOS);
		dataMap.put("datas", version);
		resultInfo.setData(dataMap);
		resultInfo.setResult(true);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		return RESULT;
	}
	/**
	 * 常用联系人添加时查询子账号
	 * @方法名称:jzgetUserByQuery
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月26日-下午3:39:43
	 */
	public String jzgetUserByQuery(){
		String userid = this.getUserid(token);
		if(BeanUtils.isEmpty(query)){
			resultInfo.setMsg("查询条件不能为空!");
			resultInfo.setResult(false);
			return RESULT;
		}
		List<User> lists = marketservice.getUsersBySearch(query,userid);
		
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
		
		return RESULT;
		
	}
	/**
	 * 修改市场类型
	 * @方法名称:jzupdateMarkettype
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月12日-上午10:09:22
	 */
	public String jzupdateMarkettype(){
		String userid = this.getUserid(token);
		boolean flag = marketservice.updateMarkettype(userid, markettype);
		
		if(flag){
			resultInfo.setMsg(Constants.UPDATE_SUCCESS);
			resultInfo.setResult(true);
		}else{
			resultInfo.setMsg(Constants.UPDATE_FAILURE);
		}
		return RESULT;
	}
	/**
	 * 复制编辑列表获取
	 * @方法名称:jzgetDuplicateEntryDatas
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月22日-下午5:15:03
	 */
	public String jzgetDuplicateEntryDatas(){
		String userid = this.getUserid(token);
		List<Marketquotation> lists = marketservice
				.getDuplicateEntryDatas(userid, countryid, productid, pageSize, currentPage);
		
		dataMap.put("datas", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
		return RESULT;
	}
	/**
	 * 获取复制录入中国家品名列表
	 * @方法名称:jzgetDuplicateCountryProductInfo
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月25日-下午1:58:24
	 */
	public String jzgetDuplicateCountryProductInfo(){
		String userid = this.getUserid(token);
		//设置市场类型
		Integer markettype = this.getMarkettype(userid);
		List<Map<String,Object>> lists = marketservice.getDuplicateCountryProductInfo(userid, markettype);
		dataMap.put("data", lists);
		resultInfo.setData(dataMap);
		resultInfo.setMsg(Constants.RESULT_SUCCESS);
		resultInfo.setResult(true);
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

	public String getSubUsers() {
		return subUsers;
	}

	public void setSubUsers(String subUsers) {
		this.subUsers = subUsers;
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
	 * token
	 *
	 * @return  the token
	 * @since   1.0.0
	 */
	
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * query
	 *
	 * @return  the query
	 * @since   1.0.0
	 */
	
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * status
	 *
	 * @return  the status
	 * @since   1.0.0
	 */
	
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * code
	 *
	 * @return  the code
	 * @since   1.0.0
	 */
	
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * markettype
	 *
	 * @return  the markettype
	 * @since   1.0.0
	 */
	
	public Integer getMarkettype() {
		return markettype;
	}

	/**
	 * @param markettype the markettype to set
	 */
	public void setMarkettype(Integer markettype) {
		this.markettype = markettype;
	}
	/**
	 * countryid
	 *
	 * @return  the countryid
	 * @since   1.0.0
	 */
	
	public String getCountryid() {
		return countryid;
	}
	/**
	 * @param countryid the countryid to set
	 */
	public void setCountryid(String countryid) {
		this.countryid = countryid;
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
