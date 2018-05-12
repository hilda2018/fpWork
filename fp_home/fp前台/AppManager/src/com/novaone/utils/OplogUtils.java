package com.novaone.utils;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nova.frame.model.ObjectId;
import com.nova.frame.utils.DateUtils;
import com.nova.frame.utils.JdbcUtils;
import com.novaone.model.Oplog;
/**
 * 操作日志工具类
 *  [ 项目名 ]  : App
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年6月15日 下午2:21:26
 */
public class OplogUtils {
	private  static  Log logger=LogFactory.getLog(OplogUtils.class);
	/**
	 * 添加操作日志工具类
	 * @param userid 登录用户id
	 * @param opfield 操作字段
	 * @param system 系统类型 生鲜港APP:0 市场行情,1 :开单
	 */
	public static void addOplog(String userid, String opfield, Integer system ){
		Oplog oplog = new Oplog(userid, opfield, system);
		oplog.setOpdate(DateUtils.getDate2LStr(new Date()));
		oplog.setOplogid(ObjectId.StringId());
		try {
			JdbcUtils.insert(oplog);
		} catch (Exception e) {
			logger.error(oplog.getOpfield()+"添加日志错误", e);
		}
	}
}
