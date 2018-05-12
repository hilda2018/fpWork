package com.novaone.service.system;

import com.google.inject.Inject;
import com.nova.frame.utils.SecurityUtils;
import com.novaone.common.exception.NovaException;
import com.novaone.common.NovaSession;
import com.novaone.dao.system.UserDao;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用户管理接口实现
 *
 * @Title: NovaProjects
 * @Package com.novaone.service.system
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-3 14:48 新建
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserService.class);

    @Inject
    private UserDao userDao;

    /**
     * session
     */
    private HttpSession httpSession;

    @Override
    public String login(String requetParam) throws Exception {
        return null;
    }

    @Override
    public String logout(String requetParam) {
        return null;
    }

    @Override
    public HashMap<String, Object> changePassword(NovaSession session, JSONObject requestObj) throws Exception {
        //需要传入的参数
        HashMap<String, Object> updateParams = null;
        //获得用户从前台输入的旧密码和新密码
        String oldpassword = SecurityUtils.novaEnCryption(requestObj.getString("oldpassword"));
        String newpassword = SecurityUtils.novaEnCryption(requestObj.getString("newpassword"));

        //用户id
        String userId = session.getUserId();
        //当前密码
        String dbUserPassword = userDao.getPassword(userId);

        //如果旧密码相同
        if(dbUserPassword.equals(oldpassword)){
            updateParams = new HashMap<String, Object>();
            updateParams.put("password", newpassword);
            updateParams.put("userid", userId);

            //修改密码，并返回信息
            userDao.changePassword(updateParams);
            HashMap<String, Object> resultHash = new HashMap<String, Object>();
            resultHash.put("info", "密码修改成功.");
            return resultHash;
        }

        NovaException novaException = new NovaException("ChangePassword", "原密码输入错误.", null);
        return novaException.getJsonData();
    }

    @Override
    public String getMenu(String requestParam) {
        return null;
    }

    @Override
    public String getSysParam(String requestParam) {
        return null;
    }

    @Override
    public void setHttpSession(HttpSession session) {
        this.httpSession = session;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
}
