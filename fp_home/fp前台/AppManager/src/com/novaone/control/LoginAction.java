/*
 * @(#)LoginController.java   2015-02-12
 * 
 * Copyright (c) 2015 济南易贸创想软件有限公司
 */
package com.novaone.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.google.inject.Inject;
import com.nova.frame.utils.SecurityUtils;
import com.novaone.common.NovaSession;
import com.novaone.common.baseclasses.BaseAction;
import com.novaone.common.exception.NovaException;
import com.novaone.common.initdb.SqlCollection;
import com.novaone.common.util.JSONProcessor;
import com.novaone.common.util.SelectSqlParser;
import com.novaone.constants.NovaCommonState;
import com.novaone.constants.SqlParserState;
import com.novaone.dao.db.DBParserAccessDao;
import com.novaone.model.DUser;
import com.novaone.model.db.DataRow;
import com.novaone.model.db.DataTable;
import com.novaone.model.system.Org;
import com.novaone.model.system.Post;
import com.novaone.model.system.Role;
import com.novaone.service.system.OrgService;
import com.novaone.service.user.UserService;
import com.novaone.utils.Md5Encoder;

/**
 * 重写登录action取消登录时判断角色是否存在
 *
 * @Title：LoginAction.java
 * @Package：com.novaone.control
 * @author 张猛
 * @data：
 * @version：V3.0 2016-04-14 新建
 * @修改后台密码登录方式,只允许子账号登录 用MD5解密
 * @modify 2016-05-19 by chenzijun
 */
@Action("loginOverride")
@Results({
        @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"}),
})
public class LoginAction extends BaseAction {

    private static Logger logger = Logger.getLogger(LoginAction.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private DBParserAccessDao dbParserAccessDao;
    @Inject
    private OrgService orgService;
	@Inject
	private UserService dUserService;
	
    /**
     * 登录
     * @return json数据
     */
    public String login() {
        SelectSqlParser getUserSqlParser = null;
        SelectSqlParser getRoleSqlParser = null;
        HashMap<String, Object> userParams = new HashMap<String, Object>();

        try {
            //用户id、用户名、姓名和密码
            String userId, userCode, userName, password;
            //登录用户数据
            DataRow userRow = null;
            logger.info(params);

            //输入参数
            JSONObject requestObj = JSONProcessor.StrToJSON(params);
            userCode = requestObj.getString("code").trim();
            //判断登录用户是子账号还是主账号
            DUser duser=dUserService.getDUserbyCode(userCode);
            if(null !=duser && "0".equals(duser.getParentid())){
            	//主账号
            	// password = SecurityUtils.novaEnCryption(requestObj.getString("password"));
            	password=Md5Encoder.encodePassword(requestObj.getString("password"));
            }else{
                //子账号
                password=Md5Encoder.encodePassword(requestObj.getString("password"));
            }
            //获得已初始化的数据
            getUserSqlParser = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_USER);

            userParams.put("code", userCode);
            DataTable userDt = dbParserAccessDao.getDtBySqlParser(getUserSqlParser, -1, -1, userParams, "", "");

            List<DataRow> userRows = userDt.getRows();

            //用户不存在
            if (userRows.size() == 0) {
                novaException = new NovaException("login", "不存在此用户.", null);
                message = novaException.getJsonData();
                return NovaCommonState.STR_RETURN_JSON;
            } else {
                userRow = userRows.get(0);
                //如果密码错误
                if (!password.equals(userRow.getStringValue("password"))) {
                    novaException = new NovaException("login", "密码错误", null);
                    message = novaException.getJsonData();
                    return NovaCommonState.STR_RETURN_JSON;
                } else {
                    //用户id
                    userId = userRow.getStringValue("id");
                    getRoleSqlParser = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_ROLE);

                    //设置查询条件
                    userParams = new HashMap<String, Object>();
                    userParams.put("userid", userId);

                    //查询出当前用户所拥有的角色信息
                    DataTable roleDt = dbParserAccessDao.getDtBySqlParser(getRoleSqlParser, -1, -1, userParams, "", "");

                    //存放角色数据
                    List<DataRow> roleRows = roleDt.getRows();

                    //该用户无角色
//                    if(roleRows.size()==0){
//                        novaException = new NovaException("login", "没有给此用户指定角色.", null);
//                        message = novaException.getJsonData();
//                    }
                	userCode = userRow.getStringValue("code");
                    userName = userRow.getStringValue("name");

                    //获得当前登录用户所拥有的部门
                    List<Org> orgList= this.getLogingUserOrgData(userParams);

                    //获得当前登录用户所拥有的角色
                    List<Role> roleList= this.getLogingUseRoleData(roleRows);

                    //获得当前登录用户所拥有的岗位信息
                    List<Post> postList = this.getLogingUserPostData(userParams);

                    NovaSession session = new NovaSession(httpSession, false);
                    //id
                    session.setUserId(userId);
                    //用户名
                    session.setUserCode(userCode);
                    //姓名
                    session.setUserName(userName);
                    //当前用户所属部门列表
                    session.setOrgList(orgList);
                    //当前用户所属岗位列表
                    session.setPostList(postList);
                    //当前用户所属角色列表
                    session.setRoleList(roleList);
                    //当前用户所属部门及子部门
                    session.setOrgIds(orgService.getUserAllOrg(session.getOrgIdsOfList()));

                    message = NovaCommonState.createJsonResult(session.toHashMap());
                    return NovaCommonState.STR_RETURN_JSON;                
                    
                }
            }
//            return NovaCommonState.STR_RETURN_JSON;
        } catch (Exception ex) {
            ex.printStackTrace();
            novaException = new NovaException("login", "登录错误", ex);
            message = novaException.getJsonData();
            return NovaCommonState.STR_RETURN_JSON;
        } finally {
        }
    }

    /**
     * 获得当前登录用户所拥有的部门
     * @param userParams 查询条件
     * @return
     * @throws Exception
     */
    private List<Org> getLogingUserOrgData(HashMap<String, Object> userParams) throws Exception {
        SelectSqlParser getOrgSqlParser  = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_ORG);

        DataTable orgDt = dbParserAccessDao.getDtBySqlParser(getOrgSqlParser, -1, -1, userParams, "", "");
        List<DataRow> orgRows = orgDt.getRows();

        List<Org> orgList=new ArrayList<Org>();
        for(DataRow orgRow : orgRows){
            Org org = new Org();
            org.setId(orgRow.getStringValue("orgid"));
            org.setCode(orgRow.getStringValue("orgcode"));
            org.setName(orgRow.getStringValue("orgname"));
            orgList.add(org);
        }
        return orgList;
    }

    /**
     * 获得当前登录用户所拥有的角色
     * @param roleRows 角色数据
     * @return
     * @throws Exception
     */
    private List<Role> getLogingUseRoleData(List<DataRow> roleRows) throws Exception {
        List<Role> roleList=new ArrayList<Role>();
        for(DataRow roleRow : roleRows){
            Role r = new Role();
            r.setId(roleRow.getStringValue("roleid"));
            r.setCode(roleRow.getStringValue("rolecode"));
            r.setName(roleRow.getStringValue("rolename"));
            roleList.add(r);
        }
        return roleList;
    }

    /**
     * 获得当前登录用户所拥有的岗位信息
     * @param userParams 查询条件
     * @return
     * @throws Exception
     */
    private List<Post> getLogingUserPostData(HashMap<String, Object> userParams) throws Exception {
        SelectSqlParser getPostSqlParser  = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_POST);

        DataTable postDt = dbParserAccessDao.getDtBySqlParser(getPostSqlParser, -1, -1, userParams, "", "");
        List<DataRow> postRows = postDt.getRows();

        List<Post> postList = new ArrayList<Post>();
        for(DataRow postRow : postRows){
            Post post = new Post();
            post.setId(postRow.getStringValue("postid"));
            post.setCode(postRow.getStringValue("postcode"));
            post.setName(postRow.getStringValue("postname"));
            postList.add(post);
        }
        return postList;
    }

	/**
     * 获得权限内的功能菜单
     * @return json数据
     */
    public String getMenu(){
        try {
            NovaSession session = new NovaSession(httpSession, true);
            message = dUserService.getMenu(session.getUserId());
        } catch (Exception e) {
            novaException = new NovaException("getMenu", "获取菜单失败", e);
            message = novaException.getJsonData();
        }
        return NovaCommonState.STR_RETURN_JSON;
    }

}


