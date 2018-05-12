package com.fresh.dao.impl;

import java.util.List;

import com.fresh.dao.RegistrationDao;
import com.fresh.model.DRole;
import com.fresh.model.DUser;
import com.fresh.model.DUserss;
import com.fresh.model.Duserrole;
import com.fresh.model.RegistrationDTO;
import com.fresh.model.User;
import com.fresh.model.UsersBelong;
import com.nova.frame.utils.JdbcUtils;

public class RegistrationDaoImpl implements RegistrationDao {
    @Override
    public List<RegistrationDTO> queryAllCountry() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" co.countryid as countryid, ");
        sql.append(" co.countryenname as countryenname ");
        sql.append(" from country co order by co.countryenname asc ");
        return JdbcUtils.query(RegistrationDTO.class, sql.toString());
    }

    @Override
    public Long queryUserCount(RegistrationDTO registration) {
        String usernameSql = " select count(*) from d_user d where d.code = '" + registration.getUsername() + "' ";
        Long usernameCount = JdbcUtils.count(usernameSql);
        // String companySql = " select count(*) from users u where
        // u.CompanyENName = '" + registration.getCompanyname()
        // + "' ";
        // Long companyCount = JdbcUtils.count(companySql);
        return usernameCount;

    }

    @Override
    public Long queryCompanyUserCount(RegistrationDTO registration) {
        String usernameSql = " select count(*) from users u left join dusers dus on u.userid=dus.usersid left join d_user d on dus.duserid=d.id where d.isuserinsert='1' and  convertStr(u.companyenname) = '"
                + companynameUpperStrTrim(registration.getCompanyname()) + "' ";
        Long usernameCount = JdbcUtils.count(usernameSql);
        // String companySql = " select count(*) from users u where
        // u.CompanyENName = '" + registration.getCompanyname()
        // + "' ";
        // Long companyCount = JdbcUtils.count(companySql);
        return usernameCount;

    }

    @Override
    public DRole queryDroleByCode(String code) {
        String sql = "select * from d_role d where d.code = ?";
        return JdbcUtils.get(DRole.class, sql, code);
    }

    @Override
    public void saveDuser(DUser dUser) {
        JdbcUtils.insert(dUser);
    }

    @Override
    public void saveUser(User user) {
        JdbcUtils.insert(user);
    }

    @Override
    public void saveDusers(DUserss dusers) {
        JdbcUtils.insert(dusers);
    }

    @Override
    public void saveUsersBelong(UsersBelong usersBelong) {
        JdbcUtils.insert(usersBelong);
    }

    @Override
    public void saveDuserrole(Duserrole role) {
        JdbcUtils.insert(role);
    }

    /**
     * companynameUpperStrTrim(这里用一句话描述这个方法的作用)
     * TODO(将往来单位数据清除掉特殊符号)    
     * @param str
     * @return
     * @return String   
     * @Exception 异常对象    
     * 创建人：zhangguangchen   
     * 创建时间：2017-11-17 上午10:03:36   
     * 修改人：  
     * 修改时间：  
     * 修改备注：
     */
    private String companynameUpperStrTrim(String str) {
        str = str.trim();

        str = str.replace(" ", "");
        str = str.replace(" ", "");
        str = str.replace(".", "");
        str = str.replace(",", "");
        str = str.replaceFirst(AsciiToStr("160"), "");
        // 回车
        str = str.replaceFirst(AsciiToStr("13"), "");
        // 换行
        str = str.replaceFirst(AsciiToStr("10"), "");
        // 空格
        str = str.replaceFirst(AsciiToStr("32"), "");
        str = str.toUpperCase();
        return str;
    }

    /**
     * AsciiToStr(这里用一句话描述这个方法的作用)
     * TODO(根据Ascii替换数据)    
     * @param assic
     * @return
     * @return String   
     * @Exception 异常对象    
     * 创建人：zhangguangchen   
     * 创建时间：2017-11-17 上午10:06:03   
     * 修改人：  
     * 修改时间：  
     * 修改备注：
     */
    private String AsciiToStr(String assic) {// ASCII转换为字符串
        StringBuffer sb = new StringBuffer();
        return sb.append((char) Integer.parseInt(assic)).toString();
    }

}
