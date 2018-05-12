/**
 * 
 */
package com.novaone.webservice;

import com.novaone.model.DUser;
import com.novaone.model.User;
import com.novaone.service.app.MarketService;
import com.novaone.service.app.MarketServiceImpl;
import com.novaone.utils.JsonUtils;

/**
 *  [ 项目名 ]  : App
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年4月19日 上午10:45:01 
 */
public class CommonInvokeServiceImpl implements CommonInvokeService {
	
	private MarketService marketservice=new MarketServiceImpl();
	/* (non-Javadoc)
	 * @see com.novaone.webservice.CommonInvokeService#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public String getUser(String username, String password) {
		User duser=marketservice.findByCodeAndPwd(username, password);
		String jsonString=JsonUtils.toJson(duser);
		return jsonString;
	}

}
