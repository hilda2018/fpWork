package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.RegistrationDaoImpl;
import com.fresh.model.DRole;
import com.fresh.model.DUser;
import com.fresh.model.DUserss;
import com.fresh.model.Duserrole;
import com.fresh.model.RegistrationDTO;
import com.fresh.model.User;
import com.fresh.model.UsersBelong;
import com.google.inject.ImplementedBy;

/**
 * 
 * 项目名称：fresh_port_shop_cn    
 * 类名称：RegistrationDao    
 * 类描述：    注册
 * 创建人：xiadecheng    
 * 创建时间：2017年10月26日 下午2:09:19    
 * 修改人：xiadecheng    
 * 修改时间：2017年10月26日 下午2:09:19    
 * 修改备注：    
 * @version     
 *
 */
@ImplementedBy(RegistrationDaoImpl.class)
public interface RegistrationDao {

    /**
     * 
     * queryAllCountry -  查询所有国家
     * @return
     * @return  List<RegistrationDTO>  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午2:27:38
     *
     */
    public List<RegistrationDTO> queryAllCountry();

    /**
     * 
     * queryUserCount -  判断Username和Company Name是否重复
     * @param registration
     * @return
     * @return  Long  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午3:33:37
     *
     */
    public Long queryUserCount(RegistrationDTO registration);

    /**
     * 
     * queryDroleByCode -  根据角色编码查询角色
     * @param code
     * @return
     * @return  DRole  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:04:35
     *
     */
    public DRole queryDroleByCode(String code);

    /**
     * 
     * saveDuser -  保存Duser
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:19:51
     *
     */
    public void saveDuser(DUser dUser);

    /**
     * 
     * saveUser -  保存User
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:20:07
     *
     */
    public void saveUser(User user);

    /**
     * 
     * saveDusers -  保存Dusers
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:20:12
     *
     */
    public void saveDusers(DUserss dusers);

    /**
     * 
     * saveUsersBelong -  保存UusersBelong
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:20:16
     *
     */
    public void saveUsersBelong(UsersBelong usersBelong);

    /**
     * 
     * saveDrole -  保存DRole
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午5:20:21
     *
     */
    public void saveDuserrole(Duserrole role);

    /**
     * 
     * queryCompanyUserCount(查询往来单位是否存在)
     * TODO(这里描述这个方法适用条件，执行流程，使用方法，注意事项 – 可选)    
     * @param registration
     * @return
     * @return Long   
     * @Exception 异常对象    
     * 创建人：zhangguangchen   
     * 创建时间：2017-11-16 下午5:32:54   
     * 修改人：  
     * 修改时间：  
     * 修改备注：
     */
    Long queryCompanyUserCount(RegistrationDTO registration);

}
