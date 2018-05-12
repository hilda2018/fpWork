/**
 * 发布webservice服务
 */
package com.novaone.webservice.servlet;

import javax.servlet.ServletConfig;

import org.apache.cxf.Bus;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import com.novaone.webservice.CommonInvokeServiceImpl;

/**
 *  [ 项目名 ]  : App
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年4月19日 上午10:52:42 
 */
public class CXFNonSpringServletImpl extends CXFNonSpringServlet {
	
	private static final long serialVersionUID = 1L;
	
		@Override
		protected void loadBus(ServletConfig sc) {
			super.loadBus(sc);
			Bus bus=this.getBus();
			ServerFactoryBean factory=new ServerFactoryBean();
			factory.setBus(bus);
			factory.setServiceClass(CommonInvokeServiceImpl.class);
			factory.setAddress("/info");
			factory.create();
			
		}
		

}
