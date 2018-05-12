package com.novaone.common;

import com.novaone.common.baseclasses.NovaSessionService;
import com.novaone.model.system.Org;
import com.novaone.model.system.Post;
import com.novaone.model.system.Role;
import net.sf.json.JSONArray;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 用户session
 */
public class NovaSession implements NovaSessionService {

    private HttpSession session;

    /**姓名**/
    private String userName;

    /**用户名**/
    private String userCode;

    /**用户 id**/
    private String userId;

    /**用户 部门列表**/
    private List<Org> orgList;

    /**用户所属部门**/
    private String orgIds;

    /**用户 角色列表**/
    private List<Role> roleList;

    /**用户 岗位列表**/
    private List<Post> postList;

    /**扩展属性**/
    private Object user;

    /**
     * 构造函数
     * @param httpSession httpSession
     * @param isInitSessionValue 是否session初始化
     * @throws Exception
     */
    public NovaSession(HttpSession httpSession, boolean isInitSessionValue) throws Exception {
        try {
            //如果要初始化
            if (isInitSessionValue) {
                //mc = wsContext.getMessageContext();
                session = httpSession;//(HttpSession) ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST)).getSession();
                this.userName = (String) session.getAttribute("userName");
                this.userCode = (String) session.getAttribute("userCode");
                this.userId = (String) session.getAttribute("userId");
                this.orgList = (List<Org>) session.getAttribute("orgList");
                this.postList = (List<Post>) session.getAttribute("postList");
                this.roleList = (List<Role>) session.getAttribute("roleList");
                this.orgIds =(String) session.getAttribute("orgIds");
                this.user = (Object) session.getAttribute("user");
            } else {
                session = httpSession;
                // (HttpSession) ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST)).getSession();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("can not get session values.");
        }
    }

    /**
     * 构造函数
     * @param session  httpSession
     * @throws Exception
     */
    public NovaSession(HttpSession session) throws Exception {
        try {
            this.userName = (String) session.getAttribute("userName");
            this.userCode = (String) session.getAttribute("userCode");
            this.userId = (String) session.getAttribute("userId");
            this.orgList = (List<Org>) session.getAttribute("orgList");
            this.roleList = (List<Role>) session.getAttribute("roleList");
            this.postList = (List<Post>) session.getAttribute("postList");
            this.orgIds =(String) session.getAttribute("orgIds");
            this.user = (Object) session.getAttribute("user");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("can not get session values.");
        }
    }

    /**
     * session销毁
     */
    public void invalidate() {
        session.invalidate();
        ServletContext context = session.getServletContext();
	    ServletContext context1 = context.getContext("/fresh_Port");
	    if(context1!=null) {
	    	 HttpSession session1 = (HttpSession)context1.getAttribute("session");
	    	 if(session1!=null) {
	    		 session1.invalidate();
	    	 }
	    	 context1.setAttribute("session", null);
	    }
    }

    /**
     * 获得已登录用户名
     * @return
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置登录用户名
     * @param userName
     */
    public void setUserName(String userName) {
        this.session.setAttribute("userName", userName);
        this.userName = userName;
    }

    /**
     * 获得已登录用户编码
     * @return
     */
    public String getUserCode() {
        return this.userCode;
    }

    /**
     * 设置登录用户编码
     * @param userCode
     */
    public void setUserCode(String userCode) {
        this.session.setAttribute("userCode", userCode);
        this.userCode = userCode;
    }

    /**
     * 获得已登录用户id
     * @return
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置登录用户id
     * @param userId
     */
    public void setUserId(String userId) {
        this.session.setAttribute("userId", userId);
        this.userId = userId;
    }

    /**
     * 判断用户是否已登录
     * @return
     */
    public boolean getIsOnline() {
        return session != null && session.getAttribute("userId") != null;
    }

    /**
     * 获得已登录用户部门列表
     * @return
     */
    public List<Org> getOrgList() {
        return this.orgList;
    }

    /**
     * 设置登录用户的部门列表
     * @param orgList
     */
    public void setOrgList(List<Org> orgList) {
        this.session.setAttribute("orgList", orgList);
        this.orgList = orgList;
    }

    /**
     * 设置登录用户的岗位列表
     * @param postList
     */
    public void setPostList(List<Post> postList) {
        this.session.setAttribute("postList", postList);
        this.postList = postList;
    }


    /**
     * 将用户所属的部门id，转换为字符串
     * @return
     */
    public String getOrgIdsOfList(){
        String flag = "";
        for(Org o:this.orgList){
            flag += o.getId() + ",";
        }
        flag = !"".equals(flag)?flag.substring(0,flag.length()-1):"";
        return flag;
    }

    /**
     * 获得已登录用户的角色列表
     * @return
     */
    public List<Role> getRoleList() {
        return this.roleList;
    }
    /**
     * 获得已登录用户的岗位列表
     * @return
     */
    public List<Post> getPostList() {
        return this.postList;
    }
    /**
     * 设置登录用户的角色列表
     * @param roleList
     */
    public void setRoleList(List<Role> roleList) {
        this.session.setAttribute("roleList", roleList);
        this.roleList = roleList;
    }

    /**
     * 将登录用户的用户id、用户名、用户编码、部门列表、角色列表转换成hashMap
     * @return
     */
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> sessionHash = new HashMap<String, Object>();
        sessionHash.put("code", "000");
        sessionHash.put("userId", this.getUserId());
        sessionHash.put("userName", this.getUserName());
        sessionHash.put("userCode", this.getUserCode());

        JSONArray orgArray = new JSONArray();
        for (Org org : this.getOrgList()) {
            HashMap<String, Object> orgObj = new HashMap<String, Object>();
            orgObj.put("id", org.getId());
            orgObj.put("code", org.getCode());
            orgObj.put("name", org.getName());
            orgArray.add(orgObj);
        }
        sessionHash.put("orgList", orgArray);

        JSONArray roleArray = new JSONArray();
        for (Role role : this.getRoleList()) {
            HashMap<String, Object> roleObj = new HashMap<String, Object>();
            roleObj.put("id", role.getId());
            roleObj.put("code", role.getCode());
            roleObj.put("name", role.getName());
            roleArray.add(roleObj);
        }
        sessionHash.put("roleList", roleArray);

        return sessionHash;
    }
    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.session.setAttribute("user", user);
        this.user = user;
    }

    public void setOrgIds(String orgIds) {
        this.session.setAttribute("orgIds", orgIds);
        this.orgIds = orgIds;
    }

    public String getOrgIds() {
        return orgIds;
    }
}
