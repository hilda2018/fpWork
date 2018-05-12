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
import com.fresh.model.SupplierProduct;
import com.fresh.model.Users;
import com.fresh.service.SellerService;
import com.google.inject.Inject;

@Action("Seller")
@ParentPackage("json-default")
@Results({@Result(name = "getSellerList", type = "json"),
	@Result(name = "getSellerCount", type = "json"),
	@Result(name = "getSupplierPrdImg", type = "json"),
	@Result(name = "getSupplierPrdInfo", type = "json")})
public class SellerAction extends BaseAction {

	@Inject
	private SellerService sellerService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获取供应商列表
	 */
	public void getSellerList(){
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
		String countryStr=request.getParameter("country");
		String productStr=request.getParameter("product");
		Integer startMonth = Integer.parseInt(request.getParameter("startMonth"));
		Integer startTen = Integer.parseInt(request.getParameter("startTen"));
		Integer endMonth = Integer.parseInt(request.getParameter("endMonth"));
		Integer endTen = Integer.parseInt(request.getParameter("endTen"));
		String companyNameStr=request.getParameter("companyName");
		PrintWriter out = null;
		try{
			List<Users> list = sellerService.getSellerList(pagesizeStart, pagesizeInfo, productStr, countryStr, startMonth, startTen, endMonth, endTen,companyNameStr);
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
	 * 获取供应商的总数
	 */
	public void getSellerCount(){
		PrintWriter out = null;
		String countryStr=request.getParameter("country");
		String productStr=request.getParameter("product");
		Integer startMonth = Integer.parseInt(request.getParameter("startMonth"));
		Integer startTen = Integer.parseInt(request.getParameter("startTen"));
		Integer endMonth = Integer.parseInt(request.getParameter("endMonth"));
		Integer endTen = Integer.parseInt(request.getParameter("endTen"));
		String companyNameStr=request.getParameter("companyName");
		try{
			List<ResultCount> list = sellerService.getSellerCount(productStr, countryStr, startMonth, startTen, endMonth, endTen,companyNameStr);
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
	 * 获取供应商产品图片
	 */
	public void getSupplierPrdImg(){
		PrintWriter out = null;
		String userIDStr=request.getParameter("userID");
		try{
			List<SupplierProduct> list = sellerService.getSupplierPrdImg(userIDStr);
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
	 * 获取供应商产品信息
	 */
	public void getSupplierPrdInfo(){
		PrintWriter out = null;
		String supplierProductIDStr=request.getParameter("supplierProductID");
		try{
			List<SupplierProduct> list = sellerService.getSupplierPrdInfo(supplierProductIDStr);
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
