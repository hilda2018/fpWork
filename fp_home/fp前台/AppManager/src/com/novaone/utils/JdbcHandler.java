package com.novaone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nova.frame.utils.JdbcUtils;

public final class JdbcHandler<T> {
	public List<T> executeQuery(String sql,List<Object> params,JdbcHandler.ResultProcessor<T> resultProcessor) {
		Connection connection = JdbcUtils.getConnection();
		PreparedStatement pst = null;
		List<T> result = new ArrayList<T>();
		ResultSet resultSet = null;
		try {
			pst = connection.prepareStatement(sql);
			for(int i=0;i<params.size();i++) {
				pst.setObject(i+1, params.get(i));
			}
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				T item = resultProcessor.getResultItem(resultSet);
				result.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResouces(resultSet, pst, connection);
		}
		return result;
	}
	
	
	public void closeResouces(ResultSet resultSet,PreparedStatement pst,Connection connection) {
		try {
			
			if(resultSet != null) {
				resultSet.close();
			}
			if(pst != null) {
				pst.close();
			}
			if(connection != null) {
				connection.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Object uniqueResult(String sql, List<Object> paramList) {
		JdbcHandler<Object> handler = new JdbcHandler<Object>();
		List<Object> result =  handler.executeQuery(sql, paramList, new ResultProcessor<Object>() {
			@Override
			public Object getResultItem(ResultSet resultSet) {
				Object result = null;
				try {
					result = resultSet.getObject(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return result;
			}
		});
		
		if(!CollectionsUtils.isEmpty(result)) {
			return result.get(0);
		}
		
		return null;
	}
	
	public interface ResultProcessor<T> {
		public T getResultItem(ResultSet row);
	}
}
