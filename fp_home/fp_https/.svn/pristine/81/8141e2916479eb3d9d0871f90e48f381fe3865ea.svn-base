package com.fresh.control;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fresh.common.base.action.BaseAction;
import com.fresh.model.ResultCount;
import com.fresh.model.Users;
import com.fresh.service.BuyerService;
import com.google.inject.Inject;

@Action("Buyer")
@ParentPackage("json-default")
@Results({@Result(name = "getBuyerList", type = "json"),
	@Result(name = "getBuyerCount", type = "json")})
public class BuyerAction extends BaseAction {

	@Inject
	private BuyerService buyerService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 获取采购商列表
	 */
	public void getBuyerList(){
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
		String productStr=request.getParameter("product");
		String areaStr=request.getParameter("area");
		String companyNameStr=request.getParameter("companyName");
		
		PrintWriter out = null;
		try{
			List<Users> list = buyerService.getBuyerList(pagesizeStart, pagesizeInfo, productStr, areaStr,companyNameStr);
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
	 * 获取采购商的总数
	 */
	public void getBuyerCount(){
		PrintWriter out = null;
		try{
			String productStr=request.getParameter("product");
			String areaStr=request.getParameter("area");
			String companyNameStr=request.getParameter("companyName");
			List<ResultCount> list = buyerService.queryCount(productStr, areaStr,companyNameStr);
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
