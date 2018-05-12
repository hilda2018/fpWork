package com.fresh.control;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fresh.common.base.action.BaseAction;

/**
 * 测试用action
 * 
 * @author 于采兴
 * @version @version 2.0 2015-7-28
 */
@Action("test")
@Results({@Result(name = "test01_parmwin_grid", location = "test/test01-parmWin-Grid.jsp"),
        @Result(name = "test02-report-Grid", location = "test/test02-report-Grid.jsp"),
        @Result(name = "test03-list-Grid", location = "test/test03-list-Grid.jsp"),
        @Result(name = "test", location = "test/test.jsp"), @Result(name = "testpop", location = "test/testPop.jsp"),
        @Result(name = "testReportPage", location = "test/1.1.1.a.jsp"),
        @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"})})
public class TestAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 跳转页面使用
     * 
     * @return
     */
    public String testpage() {
        return request.getParameter("p");
    }
}
