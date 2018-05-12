package com.novaone.service.system;

import com.google.inject.ImplementedBy;
import com.novaone.common.NovaSession;
import com.novaone.common.baseclasses.ServiceInterface;

import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * 用户管理接口
 *
 * @Title: NovaProjects
 * @Package com.novaone.service.system
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-3 14:41 新建
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService extends ServiceInterface {
    /**
     * 登录
     * @param requetParam
     * @return
     * @throws Exception
     */
    public String login(String requetParam) throws Exception;

    /**
     * 退出
     * @param requetParam
     * @return
     */
    public String logout(String requetParam);

    /**
     * 更改密码
     * @return
     */
    public HashMap<String, Object> changePassword(NovaSession session, JSONObject requestObj) throws Exception;

    /**
     * 获得菜单
     * @param requestParam
     * @return
     */
    public String getMenu(String requestParam);

    public String getSysParam(String requestParam);
}
