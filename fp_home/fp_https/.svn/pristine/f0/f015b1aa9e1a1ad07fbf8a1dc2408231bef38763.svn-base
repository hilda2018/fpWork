package com.fresh.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.fresh.common.base.action.BaseAction;
import com.fresh.dao.InformationDao;
import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.fresh.service.InformationService;
import com.google.inject.Inject;

/**
 * 资讯
 * 
 * @author lip
 * @date 2015-08-06
 */
@Action("information")
@ParentPackage("json-default")
@Results({@Result(name = "queryIndexNews", type = "json"),
		@Result(name = "queryDetails", type = "json"),
		@Result(name = "queryCount", type = "json")})
public class InformationAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InformationService informationService;

	/**
	 * 获取首页的新闻
	 */
	public void queryIndexNews(){
		Boolean isInteger =chkInteger(request.getParameter("pagesize"));
		Boolean isIntegerStart =chkInteger(request.getParameter("pageindex"));
		Integer pagesizeInfo=0;
		Integer pagesizeStart=0;
		if (isInteger==true){
			pagesizeInfo= Integer.parseInt(request.getParameter("pagesize"));
		}
		if (isIntegerStart==true){
			pagesizeStart=Integer.parseInt(request.getParameter("pageindex"));
		}
		PrintWriter out = null;
		try{
			List<Information> list = informationService.queryIndexNews(pagesizeStart,pagesizeInfo);
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			//logger.error("获取提单类型信息queryLadingBillTypeList，用户信息报错:"+e.getMessage());
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取资讯的详细信息
	 */
	public void queryDetails(){
		PrintWriter out = null;
		try{
			String id=request.getParameter("id");
			List<Information> list = informationService.queryDetails(id);
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			//logger.error("获取提单类型信息queryLadingBillTypeList，用户信息报错:"+e.getMessage());
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取资讯的总数
	 */
	public void queryCount(){
		PrintWriter out = null;
		try{
			List<ResultCount> list = informationService.queryCount();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			//logger.error("获取提单类型信息queryLadingBillTypeList，用户信息报错:"+e.getMessage());
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
}
