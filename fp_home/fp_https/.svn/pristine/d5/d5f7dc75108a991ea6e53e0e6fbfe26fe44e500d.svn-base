package com.fresh.control;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fresh.common.base.action.BaseAction;
import com.fresh.model.Area;
import com.fresh.model.BottomLinks;
import com.fresh.model.Country;
import com.fresh.model.CustomsNews;
import com.fresh.model.Product;
import com.fresh.model.ServiceType;
import com.fresh.model.Users;
import com.fresh.model.UsersShow;
import com.fresh.service.BuyerService;
import com.fresh.service.SellerService;
import com.fresh.service.ServiceService;
import com.fresh.service.ShopBaseService;
import com.google.inject.Inject;

/**
 * 资讯
 * 
 * @author lip
 * @date 2015-08-11
 */
@Action("ShopBase")
@ParentPackage("json-default")
@Results({@Result(name = "getProduct", type = "json"),
		@Result(name = "getProductIndex", type = "json"),
		@Result(name = "getArea", type = "json"),
		@Result(name = "getBottomLinks", type = "json"),
		@Result(name = "getCountry", type = "json"),
		@Result(name = "getCountryIndex", type = "json"),
		@Result(name = "getServiceType", type = "json"),
		@Result(name = "getCustomsNews", type = "json"),
		@Result(name = "getBuyerIndex", type = "json"),
		@Result(name = "getSellerIndex", type = "json"),
		@Result(name = "getServiceIndex", type = "json"),
		@Result(name = "getUserShowByID", type = "json")})
public class ShopBaseAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Inject
	private ShopBaseService shopBaseService;
	
	@Inject
	private BuyerService buyerService;
	
	@Inject
	private SellerService sellerService;
	
	@Inject
	private ServiceService serviceService;
	
	/**
	 * 获取所有产品
	 */
	public void getProduct(){
		
		PrintWriter out = null;
		try{
			List<Product> list = shopBaseService.getProduct();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
		
	}
	
	
	/**
	 * 获取首页产品
	 */
	public void getProductIndex(){
		
		PrintWriter out = null;
		try{
			List<Product> list = shopBaseService.getProductIndex();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
		
	}
	
	
	/**
	 * 获取海关新闻
	 */
	public void getCustomsNews(){
		
		PrintWriter out = null;
		try{
			List<CustomsNews> list = shopBaseService.getCustomsNews();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
		
	}
	
	
	/**
	 * 获取所有地区
	 */
	public void getArea(){
		PrintWriter out = null;
		try{
			List<Area> list = shopBaseService.getArea();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取所有链接地址
	 */
	public void getBottomLinks(){
		PrintWriter out = null;
		try{
			List<BottomLinks> list = shopBaseService.getBottomLinks();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * 获取所有国家
	 */
	public void getCountry(){
		PrintWriter out = null;
		try{
			List<Country> list = shopBaseService.getCountry();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取首页国家
	 */
	public void getCountryIndex(){
		PrintWriter out = null;
		try{
			List<Country> list = shopBaseService.getCountryIndex();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取所有服务类型
	 */
	public void getServiceType(){
		PrintWriter out = null;
		try{
			List<ServiceType> list = shopBaseService.getServiceType();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取所有服务类型
	 */
	public void getUserShowByID(){
		PrintWriter out = null;
		try{
			String userID=request.getParameter("userID");
			List<UsersShow> list = shopBaseService.getUserShowByID(userID);
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}

	/**
	 * 获取首页采购商
	 */
	public void getBuyerIndex(){
		PrintWriter out = null;
		try{
			List<Users> list = buyerService.getBuyerListIndex();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * 获取首页供应商
	 */
	public void getSellerIndex(){
		PrintWriter out = null;
		try{
			List<Users> list = sellerService.getSellerListIndex();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * 获取首页服务商
	 */
	public void getServiceIndex(){
		PrintWriter out = null;
		try{
			List<Users> list = serviceService.getServiceListIndex();
			JSONArray curJson = JSONArray.fromObject(list);
			System.out.println(curJson);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(curJson.toString());
			out.flush();
		}catch (Exception e){
			
			e.printStackTrace();

		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
}
