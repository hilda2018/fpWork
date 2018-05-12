package com.fresh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import com.nova.frame.logging.Log;
import com.nova.frame.logging.Logs;

/**
 * @Description Lob工具类
 * @author <a href="mailto:liguotao@novacloud.com">ligt</a>
 * @date 2013-12-15上午09:53:44
 * @version V1.0
 */
public class LobUtils {

	private final static Log logger = Logs.get();
	/**
	 * @Description 将数据库中的Blob类型转换成Java String类型
	 * @param blob
	 * @return String
	 * @throws IOException
	 */
	public static String convertBlobToString(Blob blob) {
		StringBuffer sb = new StringBuffer();

		try {
			if (blob == null || blob.length() == 0) {
				return "";
			} else {
				char[] buffer = new char[1024];
				int i = 0;

				BufferedReader bf = new BufferedReader(new InputStreamReader(
						blob.getBinaryStream()));
				while ((i = bf.read(buffer)) != -1) {
					sb.append(buffer, 0, i);
				}
			}
		} catch (SQLException e) {
			logger.error("convertBlobToString异常"+e.getMessage());
		} catch (IOException e) {
			logger.error("convertBlobToString异常"+e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * @Description 将数据库中的Clob类型转换成Java String类型
	 * @param clob
	 * @return String
	 * @throws IOException
	 */
	public static String convertClobToString(Clob clob) {
		StringBuffer sb = new StringBuffer();

		try {
			if (clob == null || clob.length() == 0) {
				return "";
			} else {
				char[] buffer = new char[1024];
				int i = 0;

				BufferedReader bf = new BufferedReader(clob
						.getCharacterStream());
				while ((i = bf.read(buffer)) != -1) {
					sb.append(buffer, 0, i);
				}
			}
		} catch (SQLException e) {
			logger.error("convertClobToString异常"+e.getMessage());
		} catch (IOException e) {
			logger.error("convertClobToString异常"+e.getMessage());
		}

		return sb.toString();
	}

}