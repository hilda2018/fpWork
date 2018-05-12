package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("d_userrole")
public class Duserrole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户角色关联ID
     */
    @Id(auto = false)
    private String id;

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 角色ID
     */
    private String roleid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

}
