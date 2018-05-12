/**
 * 利用webService获取用户登录信息
 */
package com.novaone.webservice;

import com.novaone.model.DUser;

/**
 *  [ 项目名 ]  : App
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年4月19日 上午10:35:57 
 */
public interface CommonInvokeService {
	/**
	 * 根据用户名和密码获取用户对象
	 * @param username
	 * @param password
	 * @return
	 */
	public String getUser(String username,String password);
	

}
