package com.novaone.model.system;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

/**
 * SysParam entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Table("sys_param")
public class SysParam implements java.io.Serializable {

    @Id(auto = false)
    private String paramId;
    private String paramName;
    private String paramValue;
    private String description;
    private String sortindex;

    // Constructors


    // Property accessors

    public String getParamId() {
        return this.paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSortindex() {
        return sortindex;
    }

    public void setSortindex(String sortindex) {
        this.sortindex = sortindex;
    }
}