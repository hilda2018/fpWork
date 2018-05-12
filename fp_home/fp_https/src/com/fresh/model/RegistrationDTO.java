package com.fresh.model;

import java.io.Serializable;

/**
 * 
 * 项目名称：fresh_port_shop_cn    
 * 类名称：RegistrationDTO    
 * 类描述：    注册
 * 创建人：xiadecheng    
 * 创建时间：2017年10月26日 下午1:57:49    
 * 修改人：xiadecheng    
 * 修改时间：2017年10月26日 下午1:57:49    
 * 修改备注：    
 * @version     
 *
 */
public class RegistrationDTO implements Serializable {
    /**    
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
     *    
     * @since Ver 1.1    
     */

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String confirmpassword;

    private String companyname;

    private String country;

    private String contactperson;

    private String email;

    private String countryid;

    private String countryenname;

    private String companytype;

    private String phonenum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    public String getCountryenname() {
        return countryenname;
    }

    public void setCountryenname(String countryenname) {
        this.countryenname = countryenname;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
