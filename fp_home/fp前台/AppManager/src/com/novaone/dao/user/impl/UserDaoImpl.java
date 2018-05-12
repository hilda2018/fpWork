package com.novaone.dao.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.inject.Inject;
import com.nova.frame.utils.JdbcUtils;
import com.novaone.constants.NovaCommonState;
import com.novaone.dao.db.DBParserAccessDao;
import com.novaone.dao.user.UserDao;
import com.novaone.model.CompanyTag;
import com.novaone.model.Country;
import com.novaone.model.CountryArea;
import com.novaone.model.DUser;
import com.novaone.model.ServiceType;
import com.novaone.model.User;
import com.novaone.model.db.DataRow;
import com.novaone.model.db.DataTable;
import com.novaone.model.db.ValueType;



public class UserDaoImpl implements UserDao{

	@Inject
    private DBParserAccessDao dbParserAccessDao;

	public User getUserInfo(String userID) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select u.userid,u.username,u.realcnname,u.realenname,u.password,u.zipcode,u.telcountry,u.telarea,u.telnum,u.faxcountry,u.faxarea,u.faxnum,u.email,u.skype,");
		sql.append(" u.wechat,u.whatsapp,u.facebook,u.twitter,u.linkin,s.servicetypecnname,s.servicetypeenname,u.companycnname,u.companyenname,");
		sql.append(" u.companyabbreviation,u.addresscn,u.addressen,u.website,u.logo,u.content,u.currentdate,u.belongmodule,c.countrycnname,");
		sql.append(" u.country,u.countryarea,u.servicetype,");
		sql.append(" u.buyerproduct,u.buyerarea,");
		sql.append(" c.countryenname,ca.countryareacnname,ca.countryareaenname from  users u ");
		sql.append(" left join servicetype s on s.servicetypeid=u.servicetype");
		sql.append(" left join country c on c.countryid=u.country");
		sql.append(" left join countryarea ca on ca.countryareaid=u.countryarea");
		sql.append(" where u.userid=?");
        return JdbcUtils.get(User.class, sql.toString(), userID);
	}

	@Override
	public int editUser(User user) {
		
		 Integer count = JdbcUtils.updateNotNull(user);
		 
		 System.out.println("count="+count);
	        return count;
	}

	@Override
	public List<Country> getCountry() {
		List<Country> countryList = new ArrayList<Country>();
		String sql=" select * from country order by countryenname asc";
		countryList=JdbcUtils.query(Country.class,sql);
		return countryList;
	}

	@Override
	public List<CountryArea> getCountryArea() {
		List<CountryArea> countryAreaList = new ArrayList<CountryArea>();
		String sql=" select * from countryarea";
		countryAreaList=JdbcUtils.query(CountryArea.class,sql);
		return countryAreaList;
	}

	@Override
	public List<ServiceType> getServiceType() {
		List<ServiceType> sList = new ArrayList<ServiceType>();
		String sql=" select * from servicetype";
		sList=JdbcUtils.query(ServiceType.class,sql);
		return sList;
	}

	@Override
	public List<CompanyTag> getCompanyTag(String userID) {
		List<CompanyTag> sList = new ArrayList<CompanyTag>();
		String sql=" select * from companytag where userid=?";
		sList=JdbcUtils.query(CompanyTag.class,sql,userID);
		return sList;
	}

	@Override
	public List<User> queryUserListByType(String type) {
		String sql = "select u.* from d_role dr, d_userrole du, users u where dr.id = du.roleid and dr.code = ? and u.userid = du.userid";
		List<User> users = JdbcUtils.query(User.class, sql, type);
		return users;
	}

	@Override
	public String getPwd(String userID) {
		String pwd="";
		String sql = "select password from users where userid=?";
		pwd = JdbcUtils.get(String.class, sql, userID);
		return pwd;
	}

	@Override
	public int changePassword(String newPwd, String userID) {
		String sql = "update users set password=? where userid=?";
		return JdbcUtils.execute(sql,newPwd, userID);
	}

	@Override
	public List<User> findUserByMoudle(int moudle) {
		String sql = "select * from users u where u.belongmodule = ?";
		return JdbcUtils.query(User.class, sql,moudle);
	}
	
	@Override
	public User findUserByUserid(String userid) {
		String sql = "select * from users u where userid = ?";
		return (JdbcUtils.query(User.class, sql,userid)).get(0);
	}

	@Override
	public HashMap<String, Object> getMenu(String userId) throws Exception {
		  DataTable dt = null;
	        JSONArray menuItems = null;
	        HashMap<String, Object> params = new HashMap<String, Object>();
	        HashMap<String, Object> resultHash = new HashMap<String, Object>();
	        String sql="";
	        params.put("userid", userId);
	        String sqlParent="select * from d_user where id=?";
	        DUser du=JdbcUtils.get(DUser.class, sqlParent, userId);
	        //子账号管理菜单显示问题.
	        if(du!=null&&"0".equalsIgnoreCase(du.getParentid())){
	        	sql = "select m.id as id, m.name as name, m.icon as icon, m.actionexp as actionexp, m.parentid as parentid  "
		                + "from sys_menu m where exists "
		                + "(select * from d_rolemenu rm left outer join d_userrole ur on ur.roleid = rm.roleid "
		                + "where  m.id = rm.menuid and rm.isenable = 'Y' and m.isapp ='N' and ur.userid = " + NovaCommonState.PARM_PRE_FIX + "userid) "
		                + "order by m.code asc ";
	        }else {
		/*	sql="select m.id as id, m.name as name, m.icon as icon, m.actionexp as actionexp, m.parentid as parentid "
		                + "from sys_menu m where exists "
		                + "(select * from d_usermenu rm  "
		                + "where  m.id = rm.menuid and rm.isenable = 'Y'  and rm.userid = " + NovaCommonState.PARM_PRE_FIX + "userid) "
		                + "order by m.code asc ";*/
	        	//只显示授权的APP后台管理菜单
				sql="select m.id as id, m.name as name, m.icon as icon, m.actionexp as actionexp, m.parentid as parentid "
		                + "from sys_menu m where exists "
		                + "(select * from d_usermenu rm  "
		                + "where  m.id = rm.menuid and rm.isenable = 'Y'  and m.isapp='N'  and rm.userid = " + NovaCommonState.PARM_PRE_FIX + "userid) "
		                + "order by m.code asc ";
			}
	        
	        String[] fieldNames = {"id", "name", "icon", "actionexp", "parentid"};
	        ValueType[] fieldTypes = {ValueType.String, ValueType.String, ValueType.String, ValueType.String, ValueType.String};

	        //查询数据库获得菜单数据
	        dt = dbParserAccessDao.getMultiLineValues(sql, params, fieldNames, fieldTypes);

	        menuItems = getMenuItems(null, dt.getRows());
	        resultHash.put("menuItems", menuItems);
	        //返回json数据
	        return NovaCommonState.createJsonResult(resultHash);
	}
	 private JSONArray getMenuItems(String parentMenuItemId, List<DataRow> allRows) {
	        List<DataRow> childRows = new ArrayList<DataRow>();
	        for (int i = 0; i < allRows.size(); i++) {
	            DataRow row = allRows.get(i);
	            if (parentMenuItemId == null) {
	                if (row.getStringValue("parentid") == null) {
	                    childRows.add(row);
	                }
	            } else if (parentMenuItemId.equals(row.getStringValue("parentid"))) {
	                childRows.add(row);
	            }
	        }

	        if (childRows.size() > 0) {
	            for (DataRow row : childRows) {
	                allRows.remove(row);
	            }

	            JSONArray menuItems = new JSONArray();
	            for (DataRow row : childRows) {
	                JSONObject menuItem = getMenuItem(row);
	                JSONArray childItems = getMenuItems(row.getStringValue("id"), allRows);
	                if (childItems != null) {
	                    menuItem.put("children", childItems);
	                    menuItem.put("state", "open");
	                }

	                menuItems.add(menuItem);
	            }
	            return menuItems;
	        } else {
	            return null;
	        }
	    }

	    /**
	     * 组建树菜单
	     *
	     * @param row
	     * @return
	     */
	    private JSONObject getMenuItem(DataRow row) {
	        JSONObject menuItem = new JSONObject();
	        JSONObject attrObj = new JSONObject();

	        menuItem.put("text", row.getStringValue("name"));
	        menuItem.put("id", row.getStringValue("id"));

	        attrObj.put("parentid", row.getStringValue("parentid"));
	        attrObj.put("icon", row.getStringValue("icon"));
	        attrObj.put("actionexp", row.getStringValue("actionexp"));
	        menuItem.put("attributes", attrObj);
	        return menuItem;
	    }
}
