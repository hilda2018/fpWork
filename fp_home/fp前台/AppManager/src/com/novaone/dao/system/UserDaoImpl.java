package com.novaone.dao.system;

import com.nova.frame.utils.SecurityUtils;
import com.novaone.common.NovaSession;
import com.novaone.common.SystemParameter;
import com.novaone.constants.NovaCloudState;
import com.novaone.constants.NovaCommonState;
import com.novaone.core.GuiceInjectorContext;
import com.novaone.dao.db.DBParserAccessDao;
import com.novaone.dao.db.impl.BaseDataDaoImpl;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * 用户管理dao实现
 *
 * @Title: NovaProjects
 * @Package com.novaone.dao.system
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-23 9:38 新建
 */
public class UserDaoImpl extends BaseDataDaoImpl implements UserDao {
    private DBParserAccessDao dbParserAccess = GuiceInjectorContext.getInstance(DBParserAccessDao.class);

    @Override
    public String getPassword(String userId) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userId);
        String sql = "select u.password as password from d_user u where u.id = " + NovaCommonState.PARM_PRE_FIX + "userid";
        return (String)dbParserAccess.getSingleValue(sql, params);
    }

    @Override
    public void changePassword(HashMap<String, Object> updateParams) throws Exception {
        String updateSql = "update d_user set password = " + NovaCommonState.PARM_PRE_FIX + "password where id = " + NovaCommonState.PARM_PRE_FIX + "userid";
        dbParserAccess.execute(updateSql, updateParams);
    }

    @Override
    protected void beforeSave(NovaSession session, JSONObject requestObj) throws Exception{
        //插入到数据库
        JSONObject insertRowsObj = requestObj.getJSONObject("insert");
        //获得默认密码
        String defaultPwd = SystemParameter.getInstance().getValue(NovaCloudState.NOVAONE_DEFAULT_PASSWORD);
        int insertRowCount = insertRowsObj.size();
        Object[] insertRowIds = insertRowsObj.keySet().toArray();
        for(int i = 0;i < insertRowCount; i++){
            String insertRowId = (String)insertRowIds[i];
            JSONObject insertRowObj = insertRowsObj.getJSONObject(insertRowId);
            insertRowObj.put("password", SecurityUtils.novaEnCryption(defaultPwd));
        }
    }
}
