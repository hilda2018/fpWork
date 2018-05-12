package com.novaone.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public class RequestParameters {
	
    private Map<String,Object> params = new HashMap<String, Object>();

    private int page = 1;

    private int rows = 10;

    private String sort;

    private String order = "desc";

    private int startIndex = 0;

    public int getStartIndex() {

        return (page-1)*rows;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }
    
    public String getSort(String sort) {
    	if(StringUtils.isEmpty(this.sort)) {
    		return sort;
    	}
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

	public static RequestParameters create(HttpServletRequest request){

        RequestParameters requestParameters = new RequestParameters();
        Map<String,Object> params = requestParameters.processParams(request.getParameterMap());
        Object page = params.get("page");
        Object rows = params.get("rows");
        Object sort = params.get("sort");
        Object order = params.get("order");


        if(page != null) {
        	requestParameters.setPage(Integer.parseInt(page.toString()));
            params.remove("page");
        }

        if(rows != null) {
        	requestParameters.setRows(Integer.parseInt(rows.toString()));
            params.remove("rows");
        }

        if(sort != null) {
        	requestParameters.setSort(sort.toString());
            params.remove("sort");
        }

        if(order != null) {
        	requestParameters.setOrder(order.toString());
            params.remove("order");
        }

        requestParameters.setParams(params);
        return requestParameters;
    }
    
    private Map<String,Object> processParams(Map<String, String[]> condition) {
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        Set<String> keys = condition.keySet();
        for(String key : keys) {
            if(condition.get(key) != null && condition.get(key).length>0) {
                if(!condition.get(key)[0].equals("")) {
                    conditionMap.put(key, condition.get(key)[0]);
                }
            }
        }
        return conditionMap;
    }

	public Map<String, Object> getParams() {
		return params;
	}
	
	public Object getParam(String key) {
		return params.get(key);
	}
}
