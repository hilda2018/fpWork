package com.novaone.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.google.inject.Inject;
import com.novaone.jwt.Jwt;
import com.novaone.service.app.MarketService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 
 * 利用JWT token 签名验证用户登录信息
 * @类编号:
 * @类名称:PriceLoginInterceptor
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月25日 下午2:04:14
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class PriceLoginInterceptor extends MethodFilterInterceptor {
	 private static final long serialVersionUID =1L;
	 private static final Log logger = LogFactory.getLog(PriceLoginInterceptor.class);
	 @Inject
		MarketService marketservice;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
	//	HttpSession httpSession = (HttpSession) request.getSession();
		//取得请求相关的ActionContext实例 
		ActionContext ctx = invocation.getInvocationContext(); 
		request=(HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		//Map<String,Object> session=ctx.getSession();
		logger.info("=actionName==="+ invocation.getInvocationContext().getName());
		//获取token签名信息
		String token = request.getParameter("token");
		//校验token (String)resultMap.get("state") 
		Map<String, Object> resultMap = Jwt.validToken(token);
		boolean flag = "VALID".equalsIgnoreCase((String)resultMap.get("state"));
		  //取出名为manager的Session属性 
		if(!flag){
		//	if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // 如果是Ajax请求
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
				// token签名失效
				resp.setContentType("application/json;charset=UTF-8");
				PrintWriter out = null;
				try {
					out = resp.getWriter();
					//登录超时,请重新登录!
					out.println("{\"msg\":\"\\u767B\\u5F55\\u8D85\\u65F6,\\u8BF7\\u91CD\\u65B0\\u767B\\u5F55\",\"result\":false,\"expired\":true}");
				} catch (IOException e) {
				} finally {
					if (out != null) {
						out.close();
					}
				}
				return null;
		}else{
			//通知struts验证通过.
			return invocation.invoke();	
		}
	}
}
