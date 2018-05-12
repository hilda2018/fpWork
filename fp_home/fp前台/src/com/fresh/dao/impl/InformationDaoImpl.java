package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.InformationDao;
import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.nova.frame.utils.JdbcUtils;

public class InformationDaoImpl implements InformationDao {
	/**
	 * 获取首页资讯信息
	 */
	public List<Information> queryIndexNews(Integer pagestart,Integer pagesize){
		List<Information> list = new ArrayList<Information>();
		String sql="SELECT * FROM fpdb.information order by InformationDate desc ";
		if (pagesize!=0){
			sql +="limit " + pagestart + "," + pagesize;
		}
		list = JdbcUtils.query(Information.class, sql);
		return list;
	}
	
	/**
	 * 获取资讯详细信息
	 */
	public List<Information> queryDetails(String id){
		List<Information> list = new ArrayList<Information>();
		String sql="SELECT * FROM fpdb.information where informationid=" + id;
		list = JdbcUtils.query(Information.class, sql);
		return list;
	}
}
