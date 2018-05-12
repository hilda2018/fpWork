package com.novaone.model;

import java.util.Map;

/**
 * Action返回封装实体类
 * 
 * @类编号:
 * @类名称:ResultInfo
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:06:07
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class ResultInfo {

    // 操作结果
    private boolean result = false;
    // 操作结果信息
    private String msg;
    // 操作结果数据
    private Map<String, Object> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
