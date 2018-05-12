package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fresh.dao.AccountDao;
import com.fresh.model.ApplyUsers;
import com.fresh.model.DUsers;
import com.fresh.model.Duserrole;
import com.fresh.model.Role;
import com.fresh.model.UserRegis;
import com.fresh.model.Users;
import com.nova.frame.utils.JdbcUtils;

public class AccountDaoImpl implements AccountDao  {

	@Override
	public Boolean chkEmail(String emailStr) {
		List<Users> usersList = new ArrayList<Users>();
		String sql="select userid from users where email='" + emailStr + "'";
		usersList = JdbcUtils.query(Users.class, sql);
		if(usersList.size()>0){
		  return false;
		}else{
		  return true;
		}
	}
	
	/**
	 * 验证用户名
	 */
	public Boolean chkUser(String username){
		List<Users> usersList = new ArrayList<Users>();
		String sql="select userid from users where username='" + username + "'";
		usersList = JdbcUtils.query(Users.class, sql);
		if(usersList.size()>0){
		  return false;
		}else{
		  return true;
		}
	}

	@Override
	public void regisUsers(String userid,String username, String password, String email,
			String servicetype,String company) {
		List<UserRegis> usersList = new ArrayList<UserRegis>();
		
		UserRegis users = new UserRegis();
		users.setuserid(userid);
		users.setusername(username);
		users.setpassword(password);
		users.setemail(email);
		users.setCompanycnname(company);
		users.setCompanyenname(company);
		users.setbelongmodule(Integer.parseInt(servicetype));
		usersList.add(users);
		
		JdbcUtils.insert(usersList);
	}
	
	
	@Override
	public void regisDUsers(String userid,String username, String password) {
		List<DUsers> usersList = new ArrayList<DUsers>();
		
		DUsers users = new DUsers();
		users.setId(userid);
		users.setCode(username);
		users.setName(username);
		users.setPassword(password);
		users.setIsusing("N");
		usersList.add(users);
		
		JdbcUtils.insert(usersList);
	}
	
	@Override
	public void regisUserRole(String id,String userid,String servicetype) {
		List<Duserrole> duserrole= new ArrayList<Duserrole>();
		
		String sql="select id from d_role d where d.code=" + servicetype;
		List<Duserrole> list = JdbcUtils.query(Duserrole.class, sql);
        String roleid = list.get(0).getId();
		
		Duserrole drole= new Duserrole();
        drole.setId(id);
        drole.setUserid(userid);
        drole.setRoleid(roleid);
		duserrole.add(drole);
		
		JdbcUtils.insert(duserrole);
	}

	@Override
	public List<UserRegis> login(String username, String password) {
		List<UserRegis> list = new ArrayList<UserRegis>();
		String sql="select d.id as userid,d.code as username,d.password,u.email from d_user d left join   dusers du on d.id=du.usersid " +
				" left join users u  on du.duserid=u.userid where d.code='" + username
				+"' and d.password='" + password + "'";
		list = JdbcUtils.query(UserRegis.class, sql);
		
		return list;
	}
	
	@Override
	public List<UserRegis> queryUserList(String username) {
		List<UserRegis> list = new ArrayList<UserRegis>();
		String sql="SELECT d.id AS userid,d.code AS username,d.password,u.email FROM d_user d LEFT JOIN dusers dus ON d.id=dus.duserid LEFT JOIN users u ON u.userid=dus.usersid AND dus.`isuse`='Y' where code='" + username + "'";
		list = JdbcUtils.query(UserRegis.class, sql);
		
		return list;
	}

	@Override
	public Boolean updatePwd(String newPassword,
			String username) {
		int result = JdbcUtils.execute("update users set password=? where username=?",newPassword, username);
		if(result>0){
		  return true;
		}else{
		  return false;
		}
	}
	
	/**
	 * 通过用户名获取Email
	 */
	 public String getEmailByName(String username){
		List<Users> usersList = new ArrayList<Users>();
		String sql="select email from users where username='" + username + "'";
		usersList = JdbcUtils.query(Users.class, sql);
		if(usersList.size()>0){
		  return usersList.get(0).getemail();
		}else{
			return "";
		}
	 }
	 
	 
	 /**
	 * 通过用户ID获取用户角色
	 */
	  public List<Role> getRoleListByID(String userid){
		  List<Role> roleList = new ArrayList<Role>();
		  String sql="select dr.id as roleid,dr.code as rolecode, dr.name as rolename "
				 + "from users u " 
				 + "inner join (select id, case parentid when '0' then id else parentid end as parentid from d_user) du on du.id=u.userid "
				 + "inner join d_userrole d on d.userid=du.parentid  "
				 + "inner join d_role dr on d.roleid =dr.id "
				 + "where u.UserID='" + userid + "'";
		  roleList = JdbcUtils.query(Role.class, sql);
		  return roleList;
	  }

	@Override
	public DUsers checkIsusing(String usernameStr) {
		String sql="select * from d_user where code=?";
		List<DUsers> duList=new ArrayList<DUsers>();
		duList=JdbcUtils.query(DUsers.class, sql,usernameStr);
		if(duList!=null&&duList.size()>0){
			return duList.get(0);
		}
		return null;
	}

	@Override
	public Boolean chkEmailForApply(String emailStr) {
		List<ApplyUsers> usersList = new ArrayList<ApplyUsers>();
		String sql="select applyid from apply_users where applyemail='" + emailStr + "'";
		usersList = JdbcUtils.query(ApplyUsers.class, sql);
		if(usersList.size()>0){
		  return false;
		}else{
		  return true;
		}
	}

	@Override
	public Boolean chkUserNameForApply(String usernameStr) {
		// TODO Auto-generated method stub
		List<ApplyUsers> usersList = new ArrayList<ApplyUsers>();
		String sql="select applyid from apply_users where applyname='" + usernameStr + "'";
		usersList = JdbcUtils.query(ApplyUsers.class, sql);
		if(usersList.size()>0){
		  return false;
		}else{
		  return true;
		}
	}

	@Override
	public void applyUsers(String applyIdStr,String usernameStr, String passwordStr, String emailStr, String servicetypeStr,
			String company) {
		// TODO Auto-generated method stub
		List<ApplyUsers> usersList = new ArrayList<ApplyUsers>();
		ApplyUsers applyUsers = new ApplyUsers();
		applyUsers.setApplyid(applyIdStr);
		applyUsers.setApplypwd(passwordStr);
		applyUsers.setApplyemail(emailStr);
		applyUsers.setApplyname(usernameStr);
		applyUsers.setApplyservicetype(servicetypeStr);
		applyUsers.setApplycompany(company);
		applyUsers.setIsused(0);
		applyUsers.setApplydate(new Date());
		usersList.add(applyUsers);
		JdbcUtils.insert(usersList);
	
	}

}
