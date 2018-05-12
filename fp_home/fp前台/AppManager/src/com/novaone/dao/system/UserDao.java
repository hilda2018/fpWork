package com.novaone.dao.system;

import java.util.HashMap;

import com.google.inject.ImplementedBy;

/**
 * 用户管理dao
 *
 * @Title: NovaProjects
 * @Package com.novaone.dao.system
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-23 9:38 新建
 */
@ImplementedBy(UserDaoImpl.class)
public interface UserDao {
    /**
     * 根据用户id获得此用户的当前密码
     * @param userId 用户id
     * @return
     */
    String getPassword(String userId) throws Exception;

    /**
     * 执行修改密码操作
     * @param updateParams 参数
     */
    void changePassword(HashMap<String, Object> updateParams) throws Exception;
}
