package com.novaone.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.nova.frame.utils.JdbcUtils;
import com.nova.frame.utils.StringUtils;

public class CommonUtils {
	
	/**
	 * 判断传入的数据是否为空
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o)
    {
        if (o == null)
        {
            return true;
        }
        if (o.equals("null"))
        {
            return true;
        }
        if (o instanceof String)
        {
            if (((String)o).trim().length() == 0)
            {
                return true;
            }
        }
        else if (o instanceof Collection)
        {
            if (((Collection)o).isEmpty())
            {
                return true;
            }
        }
        else if (o instanceof List)
        {
            if (((List)o).isEmpty())
            {
                return true;
            }
            
        }
        else if (o.getClass().isArray())
        {
            if (((Object[])o).length == 0)
            {
                return true;
            }
        }
        else if (o instanceof Map)
        {
            if (((Map)o).isEmpty())
            {
                return true;
            }
        }
        return false;
    }
	
	public static String getUserRoleCode(String userID){
		String sql="select role.code from d_userrole left join d_role role on role.id=d_userrole.roleid where userid=?";
		 String role=JdbcUtils.get(String.class,sql,userID);
		// System.out.println("角色="+role);
		 if(role.length()>0){
			 return role;
		 }
		return "1000";
	}
}
