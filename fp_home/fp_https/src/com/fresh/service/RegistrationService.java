package com.fresh.service;

import java.util.concurrent.ConcurrentHashMap;

import com.fresh.service.impl.RegistrationServiceImpl;
import com.google.inject.ImplementedBy;

import net.sf.json.JSONArray;

/**
 * 
 * 项目名称：fresh_port_shop_cn    
 * 类名称：RegistrationService    
 * 类描述：    注册
 * 创建人：xiadecheng    
 * 创建时间：2017年10月26日 下午1:52:54    
 * 修改人：xiadecheng    
 * 修改时间：2017年10月26日 下午1:52:54    
 * 修改备注：    
 * @version     
 *
 */
@ImplementedBy(RegistrationServiceImpl.class)
public interface RegistrationService {

    /**
     * 
     * saveRegistrationInfo -  保存注册信息
     * @param insArray
     * @return
     * @return  ConcurrentHashMap<String,Object>  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午1:53:03
     *
     */
    public ConcurrentHashMap<String, Object> saveRegistrationInfo(JSONArray insArray);

    /**
     * 
     * queryAllCountry -  查询所有国家
     * @return
     * @return  String  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午2:23:30
     *
     */
    public String queryAllCountry();

    /**
     * 
     * checkUsername -  校验username是否重复
     * @return
     * @return  String  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月27日 下午8:26:26
     *
     */
    public String checkUsername(String username);

    /**
     * 
     * checkUserCompany(这里用一句话描述这个方法的作用)
     * TODO(查询往来单位使用数量)    
     * @param companyname
     * @return
     * @return String   
     * @Exception 异常对象    
     * 创建人：zhangguangchen   
     * 创建时间：2017-11-16 下午5:34:20   
     * 修改人：  
     * 修改时间：  
     * 修改备注：
     */
    String checkUserCompany(String companyname);

}
