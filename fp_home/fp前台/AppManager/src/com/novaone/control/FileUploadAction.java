package com.novaone.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.nova.frame.collect.Lists;
import com.novaone.constants.Constants;
import com.novaone.utils.BeanUtils;
import com.novaone.utils.JsonUtils;
import com.novaone.utils.img.ImageHepler;

@SuppressWarnings("serial")
@Action("fileuploadaction")
@Results({@Result(name = "uploadpage", location = "upload/upload.jsp"),
    @Result(name = "success", location = "upload/success.jsp"),
    @Result(name = "input", location = "upload/error.jsp"),
    @Result(name = "result", type = "json", params = {"root", "resultInfo"}),
    @Result(name = "ajaxreturnjson", type = "json", params = {"root", "message"})})
public class FileUploadAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(FileUploadAction.class);
	
	/*
	 * 上传文件的存储的临时文件：
	 * E:\\TOOLS\\apache-tomcat-6.0.35\\work\\Catalina\\localhost\\itcast1105_struts\\upload__5fee1dc7_13ad3d1835b__8000_00000000.tmp
	 */
	private List<File> uploadImage;
	
	//上传文件的类型：text/plain
	private List<String> uploadImageContentType;
	
	//上传文件的真实名称
	private List<String> uploadImageFileName;
	
	private Map<String,Object> dataMap = new HashMap<String,Object>();
	
	
	public String page(){
		return request.getParameter("p");
	}
	/**
	 * 只用于图片上传
	 * @方法名称:jcsaveFile
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞ 
	 * void
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月9日-下午12:34:33
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcsaveFile4Img修改为jcsaveFileImg
	 */
	public void jcsaveFileImg(){
		try {
	    String file_uploadpath = Constants.FILE_UPLOADPATH;
			//需要上传的文件集合
		List<File> files = uploadImage;
		List<String> filePath = new ArrayList<String>();
		//所有图片类型
		List<String> imgext = Lists.newArrayList("BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg",
				"png", "PNG", "JPEG", "WBMP", "GIF", "gif");
		if(BeanUtils.isNotEmpty(files)){
			for(int i=0,len=files.size();i<len;i++){
				//上传文件拓展名
				String ext = uploadImageFileName.get(i).substring(uploadImageFileName.get(i).lastIndexOf(".")+1);
				//上传文件路径
				String path = System.getProperty("user.dir").replace("bin", "webapps"+file_uploadpath+ext.toUpperCase());
				String resultImageName = this.getNewFeilName();
				//上传格式为图片
				if(imgext.contains(ext)){
					//生成大中小图片
					ImageHepler.generateImgesForThreeSize(new FileInputStream(uploadImage.get(i)), path, resultImageName);
					filePath.add(file_uploadpath+ext.toUpperCase()+System.getProperty("file.separator")+resultImageName);
				}
			}
			dataMap.put("filePath",filePath );
			dataMap.put("result", true);
			logger.info("市场行情APP图片上传成功!");
		}else{
			dataMap.put("filePath","" );
			dataMap.put("result", true);
		}
		} catch (Exception e) {
			dataMap.put("result", false);
			dataMap.put("filePath","" );
			logger.error("价格App上传附件异常!", e);
		}finally{
			//删除临时文件
			if(BeanUtils.isNotEmpty(uploadImage)){
				for(File file:uploadImage){
					file.delete();
				}
			}
		}
		this.returnInfo(JsonUtils.toJson(dataMap));
	}
	/**
	 * 附件上传
	 * @方法名称:jcsaveFile4All
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞ 
	 * void
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年11月9日-下午12:38:14
	 * @修改人 张猛
	 * @修改时间 2017.03.15
	 * @修改内容 方法名jcsaveFile4All修改为jcsaveFileAll
	 */
	public void jcsaveFileAll(){
		Boolean flag = false;
		try {
	    String file_uploadpath = Constants.FILE_UPLOADPATH;
			//需要上传的文件集合
		List<File> files = uploadImage;
		List<String> filePath = new ArrayList<String>();
		if(BeanUtils.isNotEmpty(files)){
			for(int i=0,len=files.size();i<len;i++){
				//上传文件拓展名
				String ext = uploadImageFileName.get(i).substring(uploadImageFileName.get(i).lastIndexOf(".")+1);
				//上传文件路径
				String path = System.getProperty("user.dir").replace("bin", "webapps"+file_uploadpath+ext.toUpperCase());
				String finalfilename = this.getNewFeilName()+"."+ext;
				File file = new File(path, finalfilename);
					//复制文件
				 FileUtils.copyFile(uploadImage.get(i), file);
				filePath.add(file_uploadpath+ext.toUpperCase()+System.getProperty("file.separator")+finalfilename);
				flag = true;
			}
			dataMap.put("filePath",filePath );
			dataMap.put("result", flag);
		}else{
			dataMap.put("filePath","" );
			dataMap.put("result", true);
		}
		} catch (Exception e) {
			dataMap.put("result", false);
			dataMap.put("filePath","" );
			logger.error("价格App上传附件异常!", e);
		}finally{
			//删除临时文件
			if(BeanUtils.isNotEmpty(uploadImage)){
				for(File file:uploadImage){
					file.delete();
				}
			}
		}
		this.returnInfo(JsonUtils.toJson(dataMap));
	}
	
	/**
	 * 获得新文件名
	 * @return
	 */
	private String getNewFeilName(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return sdFormat.format(date) + new Random().nextInt(1000);
	}
	/**
	 * action朝前台返回值
	 * @param result
	 */
	private void returnInfo(String result) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(result);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	/**
	 * uploadImage
	 *
	 * @return  the uploadImage
	 * @since   1.0.0
	 */
	
	public List<File> getUploadImage() {
		return uploadImage;
	}
	/**
	 * @param uploadImage the uploadImage to set
	 */
	public void setUploadImage(List<File> uploadImage) {
		this.uploadImage = uploadImage;
	}
	/**
	 * uploadImageContentType
	 *
	 * @return  the uploadImageContentType
	 * @since   1.0.0
	 */
	
	public List<String> getUploadImageContentType() {
		return uploadImageContentType;
	}
	/**
	 * @param uploadImageContentType the uploadImageContentType to set
	 */
	public void setUploadImageContentType(List<String> uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}
	/**
	 * uploadImageFileName
	 *
	 * @return  the uploadImageFileName
	 * @since   1.0.0
	 */
	
	public List<String> getUploadImageFileName() {
		return uploadImageFileName;
	}
	/**
	 * @param uploadImageFileName the uploadImageFileName to set
	 */
	public void setUploadImageFileName(List<String> uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
}
