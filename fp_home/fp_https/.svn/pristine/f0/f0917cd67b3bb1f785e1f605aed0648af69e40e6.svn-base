package com.fresh.utils;

import javax.servlet.http.HttpServletRequest;

import com.fresh.model.Member;
import com.nova.frame.logging.Log;
import com.nova.frame.logging.Logs;

/**
 * Session工具类
 * @author chengql
 * @date 2013-11-15
 */
public class SessionUtil {

	private final static Log logger = Logs.get();
	/**
	 * 获得会员信息
	 * @param request
	 * @param propertieName
	 * @return
	 */
	public static Member getMember(HttpServletRequest request){
		try{
			Member member = (Member)request.getSession().getAttribute("member");
			return member;
		}catch(Exception e){
			logger.error("Session获取会员信息异常"+e.getMessage());
			return null;
		}
	}
}
