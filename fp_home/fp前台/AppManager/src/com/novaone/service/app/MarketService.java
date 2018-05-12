package com.novaone.service.app;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;
import com.nova.frame.dao.pager.Page;
import com.novaone.model.Attention;
import com.novaone.model.Cominfo;
import com.novaone.model.Country;
import com.novaone.model.CountryProduct;
import com.novaone.model.Currency;
import com.novaone.model.CustomsInfo;
import com.novaone.model.Marketquotation;
import com.novaone.model.Mqpicture;
import com.novaone.model.Product;
import com.novaone.model.Template;
import com.novaone.model.User;
import com.novaone.model.Version;
import com.novaone.utils.RequestParameters;

/**
 * 
 *  [ 项目名 ]  : App
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年3月17日 下午2:03:30
 */
@ImplementedBy(MarketServiceImpl.class)
public interface MarketService {
	/**
	 * 根据用户名和密码查找用户
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User findByCodeAndPwd(String name,String pwd);
	/**
	 * 根据登录用户id获取相关用户信息
	 * @方法名称:getUserById
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param id
	 * @return 
	 * User
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年12月20日-上午10:23:22
	 */
	public User getUserById(String id);
	/**
	 * 根据子账号的用户id 获取主账号的公司名称
	 * @param userid
	 * @return
	 */
	public User getUserbylogin(String userid);
	/**
	 * 注册检查用户名是否可用
	 * @param name
	 * @return true 用户名不可用 false 可用
	 */
	public boolean checkLoginName(String name,String userid);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean addCustomsInfo(CustomsInfo info,String userid);
	/**
	 * 删除常用联系人
	 * @方法名称:deleteCustomsInfo
	 * @内容摘要: 
	 * @param ids
	 * @return 
	 * boolean
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午4:08:12
	 */
	public boolean deleteCustomsInfo(String ids);
	/**
	 * 
	 * @方法名称:checkCustomsName
	 * @内容摘要: 常用联系人用户名称验证
	 * @param userid 用户id
	 * @param name 姓名
	 * @param customsinfoid 常用联系人id
	 * @return 
	 * boolean
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:14:34
	 */
	public boolean checkCustomsName(String userid,String name,String customsinfoid);
	/**
	 * 
	 * @方法名称:checkCustomsTel
	 * @内容摘要: 常用联系人手机号码验证
	 * @param userid 用户id
	 * @param tel 手机号码
	 * @param customsinfoid 常用联系人id
	 * @return 
	 * boolean
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月11日-下午3:15:03
	 */
	public boolean checkCustomsTel(String userid,String tel,String customsinfoid);
	/**
	 * 查找手机号码关联的子账号是否存在
	 * @param tel
	 * @return
	 */
	public boolean checkUser(String tel);
	/**
	 * 价格录入
	 * @param mq
	 * @return
	 */
	public boolean add(List<Marketquotation> mq);
	/**
	 * 记录删除
	 * @param id
	 * @return
	 */
	public boolean delete(String id);
	/**
	 * 记录修改--网页端修改
	 * @param mq
	 * @return
	 */
	public boolean modify(List<Marketquotation> mq);
	/**
	 * 记录修改--价格录入APP
	 * @param mq
	 * @param type 当为copy-input代表复制编辑的情形,其余情况为空或null即可
	 * @return
	 */
	public boolean modify4App(List<Marketquotation> mq,String type);
	/**
	 * 价格行情网页端保存
	 * @param addList
	 * @param updateList
	 * @return
	 */
	public boolean saveMarList4Web(List<Marketquotation> addList,List<Marketquotation> updateList);
	/**
	 * 查询记录详情
	 * @param id
	 * @return
	 */
	public Marketquotation detail(String id);
	/**
	 * 根据记录行情记录id获取其所对应的图片列表
	 * @方法名称:getPiclist
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param id
	 * @return 
	 * List<Mqpicture>
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月30日-下午3:02:41
	 */
	public List<Mqpicture> getPiclist(String id);
	/**
	 * 根据搜索条件查询显示类别(默认显示当天录入的数据)
	 * @param mq
	 * @return
	 */
	public List<Marketquotation> getDatas(Marketquotation mq,Page page);
	/**
	 * 价格查询
	 * @param mq
	 * @datenum 查询天数
	 * @return
	 */
	public List<Marketquotation> getDatas4Query(Marketquotation mq,Page page,Integer datenum);
	/**
	 * 获取总记录数
	 * @param mq
	 * @param page
	 * @return
	 */
	public int getDatasCount(Marketquotation mq);
	/**
	 * 根据搜索条件查询显示类别(默认显示当天录入的数据)
	 * @param mq
	 * @return
	 */
	public List<Marketquotation> getDatas4Web(Marketquotation mq,Page page,RequestParameters requestParameters);
	/**
	 * 根据搜索条件查询显示类别(默认显示当天录入的数据)
	 * @param mq
	 * @return
	 */
	public List<Marketquotation> getDatas4WebAll(Marketquotation mq,Page page,RequestParameters requestParameters);
	/**
	 * 根据搜索条件查询显示类别(默认显示当天录入的数据)
	 * @param mq
	 * @return
	 */
	public long getDatas4WebAllCount(Marketquotation mq,Page page,RequestParameters requestParameters);
	/**
	 * 根据类型进行排序
	 * @param fieldtype 排序类型
	 * @return
	 */

	public List<Marketquotation> getDimDatas(Marketquotation mq,String fieldtype,Page page);
	
	/**
	 * 页面初始化,默认加载当天数据
	 * @param mq
	 * @param page
	 * @param type; 查询类型 0 为默认查询 显示近2天的数据.1 显示2天前的数据.
	 * @return
	 */
	public List<Marketquotation> getDefaultDatas(Marketquotation mq,String type,Page page);
	
	/**
	 * 获取国家显示列表
	 * @return
	 */
	public List<Country> getCountryList(String productid);
	/**
	 * 获取品名列表
	 * @return
	 */
	public List<Product> getProductList();
	/**
	 * 关联常用信息统计表获取品名信息
	 * 字段类型0:国家;1:品名;2:品种;3:规格;4:销售商;5:供应商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数
	 * @param userid 用户id
	 * @param fieldtype 1
	 * @return
	 */
	public List<Product> getProductList(String userid,int fieldtype);
	/**
	 * 获取当前用户关注以及非关注品名列表
	 * @param userid
	 * @return
	 */
	public List<Product> getProductList(String userid);
	/**
	 * APP端价格查询 获取选定日期 特定用户产品信息
	 * @param userid
	 * @param date
	 * @return
	 */
	public List<Product> getProductList4Query(String userid,String date);
	/**
	 * APP端价格查询 获取选定日期 特定用户国家信息
	 * @param userid
	 * @param date
	 * @return
	 */
	public List<Country> getCountryList4Query(String userid,String date);
	/**
	 * APP端价格查询 获取选定日期 特定用户销售商信息
	 * @param userid
	 * @param date
	 * @return
	 */
	public List<Map<String, Object>> getSupplier4Query(String userid,String date);
	/**
	 * 根据条件获取特定类型 特定个数 的常用信息列表
	 * @param num 需要获取的个数
	 * @param fieldtype 字段类型
	 * @param fieldvalue 字段值 支持模糊查询
	 * @param country 国家
	 * @param product 品名
	 * @return
	 */
	public List<Cominfo> getCominfoList(String username,int num,int fieldtype,String fieldvalue,String country,String product );
	/**
	 * 根据条件获取特定类型 特定个数 的常用信息列表FOR WEB
	 * @方法名称:getCominfoList4Web
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param info
	 * @param num
	 * @return 
	 * List<Cominfo>
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月31日-下午4:01:05
	 */
	public List<Cominfo> getCominfoList4Web(Cominfo info,int num);
	/**
	 * 增加常用信息列表
	 * @param cominfolist //0:国家，1:品名，2:规格，3，规格，4:供应商，5:销售商，6:品牌，
	 * 7:重量，8:包装，9:品质，10:销售描述，11：板数
	 * @return
	 */
	public Map<String,Object> insertCominfo(List<Cominfo> cominfolist);
	/**
	 * 通过常用信息统计id删除相应记录
	 * @param cominfoid
	 * @return
	 */
	public boolean deleteCominfo(String cominfoid);
	/**
	 * 
	 * @param queryname转发人用户名
	 * @return
	 */
	public List<CustomsInfo> getForwardPerson(String queryname,String userid);
	/**
	 * 
	 * @param userid 登录用户id
	 * @param code 页面编号
	 * @return
	 */
	public boolean getAuthority(String userid,String code);
	
	public List<Map<String,Object>> getAuthority2New(String userid,String code);
	/**
	 * 收件夹 转发记录确认
	 * @param id
	 * @return
	 */
	public boolean confirm(String id);
	/**
	 * 收件夹记录显示(录入人显示为转发人姓名)
	 * @param datenum 显示天数
	 * @param sort排序字段
	 * @param markettype市场类型
	 * @param userid 用户id
	 * @return
	 */
	public List<Marketquotation> messageBox(String userid,String sort,Integer markettype,Integer datenum,Page page);
	/**
	 * 修改密码
	 * @param username
	 * @param password 原密码
	 * @newPwd 新密码
	 * @return
	 */
	public boolean changePassword(String userid,String newPwd);
	/**
	 * 获取界面上录入人的姓名(本机存储用户列表中有的优先显示)
	 * @param userid 当前用户id
	 * @param enteruserid 录入人id或者转发人id
	 * @return
	 */
	public String getEnterUserName(String userid,String enteruserid);
	/**
	 * 文件excel导出
	 * @param mqlist 数据源
	 * @param cellHeadArray 标题文件
	 */
	public void export4CN(List<Marketquotation> mqlist,String[] cellHeadArray);
	/**
	 * 文件excel导出
	 * @param mqlist 数据源
	 * @param cellHeadArray 标题文件
	 */
	public void export4EN(List<Marketquotation> mqlist,String[] cellHeadArray);
	/**
	 * 添加用户关注品名
	 * @param attentionlist
	 * @return
	 */
	public boolean addAttention(String userid,List<Attention> attentionlist);
	/**
	 * 获取当前用户关注的品名信息
	 * @param userid
	 * @return
	 */
	public List<Attention> getAttentionList(String userid);

	/**
	 * 用户添加模板
	 * @param template
	 * @return
	 */
	public boolean addTemplate(Template template);
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	public boolean deleteTemplate(String id);
	/**
	 * 获取模板列表
	 * @param userid
	 * @return
	 */
	public List<Template> getTemplateList(String userid);
	/**
	 * 通过模板信息获取模板明细
	 * @param template
	 * @return
	 */
	public Template getTemplateDetail(Template template);
	/**
	 * 通过模板信息获取模板明细,用来判断修改的时候是国家品名是否重复
	 * @param template
	 * @return
	 */
	public boolean  templateRepeat(Template template);
	/**
	 *  通过模板id获取模板明细
	 * @param id
	 * @return
	 */
	public Template getTemplateDetailById(String id);
	/**
	 * 获取用户国家品名列表
	 * @param userid
	 * @param date 查询日期
	 * @return
	 */
	public List<Map<String,Object>> getCountryProduct(String userid,String date,Integer markettype);
	/**
	 * 获取转发需要的销售商列表
	 * @param userid
	 * @param date
	 * @return
	 */
	public List<Map<String,Object>> getSupplier4Forward(String userid,String date,Integer markettype);
	/**
	 * 转发过滤查询列表
	 * @param mar
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<Marketquotation> getDatas4Forward(Marketquotation mar,int pageSize,int currentPage);
	/**
	 * 通过国家id以及产品id获取国家产品列表
	 * @param countryid
	 * @param productid
	 * @return
	 */
	public CountryProduct getCountryProduct(String  countryid,String productid);
	
	/**
	 * 市场行情app注册
	 * @param user//userid, username, password, companycnname, phonenum,isapp, nickname
	 * @return
	 */
	public boolean register(User user);
	/**
	 * 注册app用户时,检查用户名称是否重复
	 * @param username
	 * @return
	 */
	public boolean repeatUsername(String username);
	/**
	 * 检查子账号手机号码是否重复
	 * @param phonenum
	 * @return
	 */
	public boolean repeatPhonenum(String phonenum);
	/**
	 * 获取币别列表
	 * @return
	 */
	public List<Currency> getCurrencyList();
	/**
	 * 获取具有app用户的公司名称
	 * @return
	 */
	public List<Map<String,Object>> getCompany4App();
	/**
	 * 根据主账号id获取app子行号信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getNickNameByCompany(String id);
	/**
	 * 获取APP版本号
	 * @方法名称:getAppVersion
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param ostype //分为android ios
	 * @return 
	 * Version
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月24日-上午9:57:59
	 */
	public Version getAppVersion(String ostype);
	/**
	 * APP版本号修改
	 * @方法名称:updateVersion
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param version
	 * @return 
	 * boolean
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月17日-上午11:20:36
	 */
	public boolean updateVersion(List<Version> versionlist);
	/**
	 * 根据当前用户登录id获取用户信息
	 * @方法名称:getUserbyId
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @return 
	 * User
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月25日-下午4:10:49
	 */
	public User getUserbyId(String userid);
	/**
	 * 利用姓名或者手机号码查询子账号用户信息
	 * @方法名称:getUsersBySearch
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param query
	 * @param userid 当前用户id
	 * @return 
	 * List<User>
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月26日-下午3:27:08
	 */
	public List<User> getUsersBySearch(String query,String userid);
	/**
	 * 更新市场类型
	 * @方法名称:updateMarkettype
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param userid
	 * @param markettype
	 * @return 
	 * boolean
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月29日-下午3:35:58
	 */
	public boolean updateMarkettype(String userid,Integer markettype);
	/**
	 * 历史记录查询
	 * 	可以根据日期/销售商 品名 品牌 国家 /等方式进行帅选以及排序
	 * @方法名称:getDatas4New
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param mq 查询条件
	 * @param fieldvalue 排序字段
	 * @param page
	 * @return 
	 * List<Marketquotation>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月12日-下午4:29:30
	 */
	public List<Marketquotation> getDatas4New(Marketquotation mq,String fieldvalue,Page page);
	/**
	 * 价格查询中 对查询后的结果进行二次封装
	 * @方法名称:secondProcess
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param list
	 * @return 
	 * Map<String,Map<String,List<Marketquotation>>>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月14日-上午10:51:16
	 */
	public Map<String,Map<String,List<Marketquotation>>> secondProcess(List<Marketquotation> list);
	/**
	 * 价格查询中 对查询结果进行二次封装(新)
	 * @方法名称:secondProcessNew
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param list
	 * @return 
	 * Map<String,List<Marketquotation>>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年9月14日-上午10:58:38
	 */
	public Map<String,List<Marketquotation>> secondProcessNew(List<Marketquotation> list);
	/**
	 * 
	 * @方法名称:getshareDatasList
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param ids 分享记录id 多个以"," 隔开
	 * @return 
	 * List<Marketquotation>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月8日-下午2:53:23
	 */
	public List<Marketquotation> getshareDatasList(String ids);
	/**
	 * 复制录入列表
	 * @方法名称:getDuplicateEntryDatas
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param userid 用户id
	 * @param countryid 国家id
	 * @param productid 产品id
	 * @return 
	 * List<Marketquotation>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月22日-下午2:04:16
	 */
	public List<Marketquotation> getDuplicateEntryDatas(String userid,String countryid,String productid,int pageSize,int currentPage);
	/**
	 * 复制编辑 国家品名获取
	 * @方法名称:getDuplicateCountryProductInfo
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param userid
	 * @return 
	 * List<Map<String,Object>>
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月25日-下午1:45:42
	 */
	public List<Map<String,Object>>getDuplicateCountryProductInfo(String userid,Integer markettype);
	/**
	 * 根据子账号求顶级主账号
	 * @方法名称:getRootUserbySubUserid
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param subSuerid
	 * @return 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年12月8日-下午3:57:50
	 */
	public String getRootUserbySubUserid(String subSuerid);
	
	

}
