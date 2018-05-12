package com.fresh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

/**
 * 文件上传管理公共类
 * 
 * @author Yin Haiping
 * 
 */
public class FileUploadUtil {
	/**
	 * 文件上传至服务器
	 * 
	 * @param filePath
	 *            类别
	 * @param fileSourceFileName
	 *            文件名称
	 * @throws IOException
	 * @return 路径
	 */
	public String fileUp(String fileSource, String filePath,
			String fileSourceFileName) throws IOException {
		String path = "/" + filePath.replaceAll("/", "") + "/";
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath(path));
		String filePathNew = path + fileSourceFileName;
		if (!folder.exists())
			folder.mkdirs();
		File outFile = new File(ServletActionContext.getServletContext()
				.getRealPath(filePathNew));
		FileOutputStream outStream = new FileOutputStream(outFile);
		FileInputStream inStream = new FileInputStream(fileSource);
		byte[] buffer = new byte[1024];
		int l = 0;
		while ((l = inStream.read(buffer)) > 0) {
			outStream.write(buffer, 0, l);
		}
		inStream.close();
		outStream.close();

		return filePathNew;
	}
}