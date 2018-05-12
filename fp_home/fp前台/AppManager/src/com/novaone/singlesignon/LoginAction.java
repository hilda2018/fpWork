/*
 * @(#)LoginController.java   2015-02-12
 * 
 * Copyright (c) 2015 济南易贸创想软件有限公司
 */
package com.novaone.singlesignon;

//~--- non-JDK imports --------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jasig.cas.client.authentication.AttributePrincipal;

import com.google.inject.Inject;
import com.novaone.common.CommonInfo;
import com.novaone.common.NovaSession;
import com.novaone.common.baseclasses.BaseAction;
import com.novaone.common.exception.NovaException;
import com.novaone.common.initdb.SqlCollection;
import com.novaone.common.util.JSONProcessor;
import com.novaone.common.util.SelectSqlParser;
import com.novaone.constants.NovaCommonState;
import com.novaone.constants.SqlParserState;
import com.novaone.dao.db.DBParserAccessDao;
import com.novaone.model.db.DataRow;
import com.novaone.model.db.DataTable;
import com.novaone.model.system.Org;
import com.novaone.model.system.Role;
import com.novaone.service.db.DBParserAccessService;
import com.novaone.service.system.UserService;

/**
 * 登录action
 *
 * @Title：LoginController.java
 * @Package：com.novaone.control
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @data：
 * @version：V2.0 2015-02-12 10:02 新建
 */
@Action("masterlogin")
@Results({
        @Result(name = "loginpage", location = "platform/sys/login.jsp"),
        @Result(name = "logout", type = "redirectAction", location = "jump!page.dhtml?p=login_page"),
        @Result(name = "mainframe", type = "redirectAction", location = "jump!page.dhtml?p=mainframpage"),
        
        @Result(name = "toppage", location = "top.jsp"),
        @Result(name = "returnloginaction", type = "json", params = {"root", "returnMsg"}),
        @Result(name = "framepage", type = "redirectAction", location = "login!mainFramePage.dhtml"),
        @Result(name = "loginerror", type = "redirectAction", location = "login!loginError.dhtml?returnMsg=${returnMsg}&user.loginName={user}"),
        @Result(name = "notloggedin", location = "platform/sys/jump.jsp"),//用户没有登录
        @Result(name = "leftTree", location = "leftTree.jsp"),

        @Result(name = "tree", type = "json", params = {"root", "treeNodeList"}),
        @Result(name = "ajaxsave", type = "json", params = {"contentType", "text/html"}),//, "root", "returnMsg"
        @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"}),
        @Result(name = "ajaxflag", type = "json", params = {"root", "flag"})
})
public class LoginAction extends BaseAction {

    private static Logger logger = Logger.getLogger(LoginAction.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private DBParserAccessDao dbParserAccessDao;
    @Inject
    private DBParserAccessService dbParserAccessService;
    @Inject
    private UserService userService;
    /*@Inject
    private OrgService orgService;*/

    /**返回ACTION登录信息**/
    private String RETURN_LOGIN_ACTION = "returnloginaction";
    /**用户没有登录**/
    private String NOT_LOGGED_IN = "notloggedin";
    /**登录出错**/
    private String LOGIN_ERROR = "loginerror";

    //private HttpServletRequest request = ServletActionContext.getRequest();
    //HttpSession httpSession = (HttpSession) request.getSession();

    /**
     * 登录
     * @return json数据
     */
    public String login() {
        SelectSqlParser getUserSqlParser = null;
        SelectSqlParser getRoleSqlParser = null;
//        SelectSqlParser getOrgSqlParser = null;
        HashMap<String, Object> userParams = new HashMap<String, Object>();

        try {
            //用户id、用户名、姓名和密码
            String userId, userCode, userName, password;
            //登录用户数据
            DataRow userRow = null;

            //String requestParam = request.getParameter("params");
            //logger.info(params);
            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
//            Map attributes = principal.getAttributes();
//            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
            //输入参数
            JSONObject requestObj = JSONProcessor.StrToJSON(params);
            userCode = (String) principal.getAttributes().get("userName");
//            principal.
            //密码加密
//            password = SecurityUtils.novaEnCryption(requestObj.getString("password"));

            //获得已初始化的数据
            getUserSqlParser = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_USER);

            userParams.put("code", userCode);
            DataTable userDt = dbParserAccessDao.getDtBySqlParser(getUserSqlParser, -1, -1, userParams, "", "");

            List<DataRow> userRows = userDt.getRows();

            //用户不存在
            if (userRows.size() == 0) {
                novaException = new NovaException("login", "不存在此用户.", null);
                message = novaException.getJsonData();

            } else {
                userRow = userRows.get(0);
                //如果密码错误
//                if (!password.equals(userRow.getStringValue("password"))) {
//                    novaException = new NovaException("login", "密码错误", null);
//                    message = novaException.getJsonData();
//                } else {
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
                   /* if(roleRows.size()==0){
                        novaException = new NovaException("login", "没有给此用户指定角色.", null);
                        message = novaException.getJsonData();
                    }else{*/
                        userCode = userRow.getStringValue("code");
                        userName = userRow.getStringValue("name");

                        //获得当前登录用户所拥有的部门
                        List<Org> orgList= this.getLogingUserOrgData(userParams);

                        //获得当前登录用户所拥有的角色
                        List<Role> roleList= this.getLogingUseRoleData(roleRows);

                       /* //获得当前登录用户所拥有的岗位信息
                        List<Post> postList = this.getLogingUserPostData(userParams);*/

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
//                        session.setPostList(postList);
                        //当前用户所属角色列表
                        session.setRoleList(roleList);
                        //当前用户所属部门及子部门
//                        session.setOrgIdsAndNames(orgService.getUserAllOrg(session.getOrgIdsOfList()));

                        message = NovaCommonState.createJsonResult(session.toHashMap());
                        return "mainframe";
                    }
//                }
//            }
            return NovaCommonState.STR_RETURN_JSON;
        } catch (Exception ex) {
//            LogsUtil.getInstance().log(ex);
            novaException = new NovaException("login", "登录错误", ex);
            message = novaException.getJsonData();
            return NovaCommonState.STR_RETURN_JSON;
        } finally {
        }
    }
    
    /**
     * 登录
     * @return json数据
     */
    public String loginJump() {
        SelectSqlParser getUserSqlParser = null;
        SelectSqlParser getRoleSqlParser = null;
//        SelectSqlParser getOrgSqlParser = null;
        HashMap<String, Object> userParams = new HashMap<String, Object>();

        try {
            //用户id、用户名、姓名和密码
            String userId, userCode, userName, password;
            //登录用户数据
            DataRow userRow = null;

      
            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
//           
            //输入参数
            JSONObject requestObj = JSONProcessor.StrToJSON(params);
//            userCode = principal.getName();
            userCode = (String) principal.getAttributes().get("userName");

            //获得已初始化的数据
            getUserSqlParser = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_USER);

            userParams.put("code", userCode);
            DataTable userDt = dbParserAccessDao.getDtBySqlParser(getUserSqlParser, -1, -1, userParams, "", "");

            List<DataRow> userRows = userDt.getRows();
            
            

            //用户不存在
            if (userRows.size() == 0) {
                novaException = new NovaException("login", "不存在此用户.", null);
                message = novaException.getJsonData();

            } else {
                userRow = userRows.get(0);
                //如果密码错误
//                if (!password.equals(userRow.getStringValue("password"))) {
//                    novaException = new NovaException("login", "密码错误", null);
//                    message = novaException.getJsonData();
//                } else {
                    //用户id
                    userId = userRow.getStringValue("id");
                    userName = userRow.getStringValue("name");
                    String parentid = userRow.getStringValue("parentid");
                    getRoleSqlParser = SqlCollection.getSelectObject(SqlParserState.SYS_LOGIN_GET_ROLE);

                    //设置查询条件
                    userParams = new HashMap<String, Object>();
                    userParams.put("userid", userId);

                    //查询出当前用户所拥有的角色信息
                    DataTable roleDt = dbParserAccessDao.getDtBySqlParser(getRoleSqlParser, -1, -1, userParams, "", "");

                        NovaSession session = new NovaSession(httpSession, false);
                        //id
                        session.setUserId(userId);
                        //用户名
                        session.setUserCode(userCode);
                        //姓名
                        session.setUserName(userName);
                        
                        String jumpurl = request.getParameter("jumpurl");
                        response.sendRedirect(jumpurl);
                        

                        
            }
            return NovaCommonState.STR_RETURN_JSON;
        } catch (Exception ex) {
//            LogsUtil.getInstance().log(ex);
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
     *//*
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
    }*/

    /**
     * 获得权限内的功能菜单
     * @return json数据
     */
    public String getMenu(){
        try {
            NovaSession session = new NovaSession(httpSession, true);
            message = dbParserAccessService.getMenu(session.getUserId());
        } catch (Exception e) {
//            LogsUtil.getInstance().log(e);
            novaException = new NovaException("getMenu", "获取菜单失败", e);
            message = novaException.getJsonData();
        }
        return NovaCommonState.STR_RETURN_JSON;
    }

    /**
     * 判断用户是否已经登录
     * @return json数据
     */
    public String getSysParam(){
        try {
            NovaSession session = new NovaSession(httpSession, true);
            HashMap<String, Object> resultHash = new HashMap<String, Object>();
            resultHash.put("userid", session.getUserId());
            resultHash.put("usercode", session.getUserCode());
            resultHash.put("username", session.getUserName());
            message = NovaCommonState.createJsonResult(resultHash);
        } catch (Exception e) {
//            LogsUtil.getInstance().log(e);
            novaException = new NovaException("getSysParam", "获取系统参数失败", e);
            message = novaException.getJsonData();
        }
        return NovaCommonState.STR_RETURN_JSON;
    }

    /**
     * 退出
     *
     * @return json数据
     */
    public String logout() {
        try {
        	/*URL url = new URL("http://localhost:8080/logout");
            URLConnection urlcon = url.openConnection();
            InputStream is = urlcon.getInputStream();*/
            
            NovaSession session = new NovaSession(this.httpSession, false);
            session.invalidate();
        } catch (Exception ex) {
//            LogsUtil.getInstance().log(ex);
        }
        return "logout";
    }
    /**
     * 单点登录使用退出
     * @return
     */
    public String logoutTo() {
        try {
        	/*URL url = new URL("http://localhost:8080/logout");
            URLConnection urlcon = url.openConnection();
            InputStream is = urlcon.getInputStream();*/
        	 NovaSession session = new NovaSession(this.httpSession, false);
             session.invalidate();
             clearUser();
            response.sendRedirect(CommonInfo.SIGNLE_URL);
        } catch (Exception ex) {
//            LogsUtil.getInstance().log(ex);
        }
        return "logout";
    }

    /**
     * 更改密码
     * @return json数据
     */
    public String changePassword() {
        try {
            //接收前台传入的json串
            JSONObject requestObj = JSONProcessor.StrToJSON(params);
            NovaSession session = new NovaSession(httpSession, true);
            message =  NovaCommonState.createJsonResult(userService.changePassword(session, requestObj));
        } catch (Exception e) {
//            LogsUtil.getInstance().log(e);
            novaException = new NovaException("ChangePassword", "修改密码失败", e);
            message = novaException.getJsonData();
        }
        return NovaCommonState.STR_RETURN_JSON;
    }
    
    /**
	 * 清空登录信息
	 */
	public void clearUser(){
		try{
			Cookie userName =getCookieByName("userName");
			Cookie RoleInfo =getCookieByName("Role");
			Cookie userId =getCookieByName("userId");
			if (userName!=null){
				userName.setPath("/");
				userName.setMaxAge(0);  
                response.addCookie(userName);  
			}
			if (RoleInfo!=null){
				RoleInfo.setPath("/");
				RoleInfo.setMaxAge(0);  
                response.addCookie(RoleInfo);  
			}
			if (userId!=null){
				userId.setPath("/");
				userId.setMaxAge(0);  
                response.addCookie(userId);  
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public Cookie getCookieByName(String name){
        Map<String,Cookie> cookieMap = ReadCookieMap();
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
    
    /**
     * 将cookie封装到Map里面
     * @return
     */
    private Map<String,Cookie> ReadCookieMap(){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}


