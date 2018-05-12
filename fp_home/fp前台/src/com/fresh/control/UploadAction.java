package com.fresh.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;

import com.fresh.utils.ImageUploadUtil;
import com.fresh.utils.SysConfigUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 多文件上传控制器
 * 
 * @author dell-pc
 *
 */
@Action
@Results({
	@Result(name = "ajaxreturn", type = "json", params = { "root", "returnMsg" }),
	@Result(name = "ajaxreturnjson", type = "json", params = { "root", "message" })
})
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	/**请求对象*/
    protected HttpServletRequest request = ServletActionContext.getRequest();
    protected HttpServletResponse response = ServletActionContext.getResponse();
    
    /**文件流  **/
	private File uploadFile;
	private File imgFile;
	/**上传来的文件的名字**/
    private String uploadFileFileName;
    private String imgFileFileName;
    
    /**字段名称唯一性验证返回**/
	private Map<String, String> message;
	/**返回页面的信息*/
	private String returnMsg;
    
    
    /**上传后要存放的路径**/
    private String uploadFilePath;
    /**设置单个文件大小限制**/
    private String fileSizeLimit;
    /**用户 ID**/
    private String memberId;
    /**是否增加水印**/
    private String waterMark;

    /**
     * 上传图片
     */
    public void uploadFileOfAjax(){
		//文件保存目录URL
		String saveUrl = "";
		//原图文件名
		String srcImgName = "";
		//缩略图文件名
		String smallImgName = "";
		//中图文件名
		String middleImgName = "";

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();  
			
			//图片名称
			String imgName = wrapper.getFileNames(wrapper.getParameter("fileObjName"))[0]; 
			//图片上传文件流
			File imgFile = wrapper.getFiles(wrapper.getParameter("fileObjName"))[0];
			
			FileInputStream imageFileInputStream = new FileInputStream(imgFile);
			//将b转换为K
			int imageSize = imageFileInputStream.available() / 1024;
			
			message = new HashMap<String, String>();
			message.put("filesize", String.valueOf(imageSize));
			message.put("status", "false");
			message.put("info", "上传失败.");
			
			if (imageSize > getFileSizeLimitToK(fileSizeLimit)) {
				out.write("" + JSONObject.toJSONString(message));
			} else {
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
				String curTime = sdFormat.format(date) + new Random().nextInt(1000);
				
				//文件后缀
				//newsuffix = imgName.substring(imgName.lastIndexOf(".") + 1);
				
				saveUrl = ImageUploadUtil.uploadImageToImageServer(imgFile, memberId, uploadFilePath, curTime, imgName.substring(imgName.indexOf(".") + 1));
				
				//中图
				middleImgName = curTime + "_middle.jpg";
				//原图文件名
				srcImgName = curTime + "_big.jpg";
				//缩略图文件名
				smallImgName = curTime + "_small.jpg";
				
				
				message.put("status", "true");
				message.put("info", "上传成功.");
				message.put("srcImgUrl", "/"+saveUrl + srcImgName);
				message.put("smallImgUrl", "/"+saveUrl + smallImgName);
				message.put("middleImgUrl", "/"+saveUrl + middleImgName);
				message.put("fileName", srcImgName);
				
				out.write("" + JSONObject.toJSONString(message));
			}

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @Description 商品图片删除
	 * @return String
	 * @throws Exception
	 */
	public String deleteImg() {
		//原图文件名
		String srcImgName = request.getParameter("fileName");
		String memberId = request.getParameter("memberId");
		returnMsg = ImageUploadUtil.deleteImagesOfImageServer(memberId, uploadFilePath, srcImgName);
		return "ajaxreturn";
	}
	
	/**
	 * 实现文本编辑器的上传文件功能
	 */
	@SuppressWarnings("unchecked")
	public void uploadOfEditor(){
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			//上传完后文件存放位置
			String savePath = SysConfigUtils.getConfigValue(servletContext, "imageServer");
			//文件保存目录URL
			String saveUrl = SysConfigUtils.getConfigValue(servletContext, "imageServer");
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			
			if(null == memberId || "".equals(memberId)){
				out.println(getError("请传入当前用户ID(memberId)"));
				return;
			}
			
			//savePath += "/" + ImageUploadUtil.getUploadFilePath(memberId, 8);
			//saveUrl += "/" + ImageUploadUtil.getUploadFilePath(memberId, 8);
			
			//最大文件大小
			long maxSize = 1000000;
			if (!ServletFileUpload.isMultipartContent(request)) {
				out.println(getError("请选择文件。"));
				return;
			}
			
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if(!extMap.containsKey(dirName)){
				out.println(getError("目录名不正确。"));
				return;
			}
			
//			//创建文件夹
//			savePath += dirName + "/";
//			saveUrl += dirName + "/";
			
//			File saveDirFile = new File(savePath);
//			if (!saveDirFile.exists()) {
//				saveDirFile.mkdirs();
//			}
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			String ymd = sdf.format(new Date());
//			savePath += ymd + "/";
//			saveUrl += ymd + "/";
//			File dirFile = new File(savePath);
//			if (!dirFile.exists()) {
//				dirFile.mkdirs();
//			}
			
			// 检查扩展名
			String fileExt = imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1).toLowerCase();

			if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)){
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"+ extMap.get(dirName) + "格式。"));
				//out.println(getError(mm));
				return;
			}

			// 检查文件大小

			if (imgFile.length() > maxSize){
				out.println(getError("上传文件大小超过限制。"));
				return;
			}
			
			//检查目录
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			String dir = sdf.format(new Date());
			
			// 重构上传图片的名称
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newImgName = df.format(new Date());
			String str = ImageUploadUtil.uploadImageToImageServer(imgFile, memberId, "8", newImgName, "jpg");
			
			
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + str + newImgName + "_big.jpg");
			out.println(obj.toJSONString()); 
			out.flush();
			out.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * 实现文本编辑器的上传文件管理
	 */
	@SuppressWarnings("unchecked")
	public void uploadFileManager(){
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			//文件存放位置
			String savePath = SysConfigUtils.getConfigValue(servletContext, "imageServer");
			//文件保存目录URL
			String saveUrl = SysConfigUtils.getConfigValue(servletContext, "imageServer");
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String dirName = request.getParameter("dir");
			if (dirName != null) {
				if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
					out.println("目录名无效.");
					return;
				}
			}
			if(null == memberId || "".equals(memberId)){
				out.println(getError("请传入当前用户ID(memberId)"));
				return;
			}
			
			savePath += "/" + ImageUploadUtil.getUploadFilePath(memberId, "8");
			saveUrl += "/" + ImageUploadUtil.getUploadFilePath(memberId, "8");
			
			
			//根据path参数，设置各路径和URL
			String path = request.getParameter("path") != null ? request.getParameter("path") : "";
			String currentPath = savePath + path;
			String currentUrl = saveUrl + path;
			String currentDirPath = path;
			String moveupDirPath = "";
			if (!"".equals(path)) {
				String str = currentDirPath.substring(0, currentDirPath.length() - 1);
				moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
			}

			//排序形式，name or size or type
			String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
			
			//不允许使用..移动到上一级目录
			if (path.indexOf("..") >= 0) {
				out.println("禁止访问.");
				return;
			}
			//最后一个字符不是/
			if (!"".equals(path) && !path.endsWith("/")) {
				out.println("无效的参数.");
				return;
			}
			//目录不存在或不是目录
//			File currentPathFile = new File(currentPath);
//			if(!currentPathFile.isDirectory()){
//				out.println("目录不存在.");
//				return;
//			}			
			//遍历目录取的文件信息
			List<Hashtable> fileList = ImageUploadUtil.getUploadFileListOfPath(memberId, "8");
			//fileList = ImageUploadUtil.getUploadFileListOfPath(memberId, "8");
//			if(currentPathFile.listFiles() != null) {
//				for (File file : currentPathFile.listFiles()) {
//					Hashtable<String, Object> hash = new Hashtable<String, Object>();
//					String fileName = file.getName();
//					if(file.isDirectory()) {
//						hash.put("is_dir", true);
//						hash.put("has_file", (file.listFiles() != null));
//						hash.put("filesize", 0L);
//						hash.put("is_photo", false);
//						hash.put("filetype", "");
//					} else if(file.isFile()){
//						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//						hash.put("is_dir", false);
//						hash.put("has_file", false);
//						hash.put("filesize", file.length());
//						hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
//						hash.put("filetype", fileExt);
//					}
//					hash.put("filename", fileName);
//					hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
//					fileList.add(hash);
//				}
//			}			
			if ("size".equals(order)) {
				Collections.sort(fileList, new SizeComparator());
			} else if ("type".equals(order)) {
				Collections.sort(fileList, new TypeComparator());
			} else {
				Collections.sort(fileList, new NameComparator());
			}
			JSONObject result = new JSONObject();
			result.put("moveup_dir_path", moveupDirPath);
			result.put("current_dir_path", currentDirPath);
			result.put("current_url", currentUrl);
			result.put("total_count", fileList.size());
			result.put("file_list", fileList);

			response.setContentType("application/json; charset=UTF-8");
			out.println(result.toJSONString());			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * 根据限制的上传大小，转换为K
	 * @param fileSizeLimit
	 * @return
	 */
	private int getFileSizeLimitToK(String fileSizeLimit){
		String temp = fileSizeLimit.substring(fileSizeLimit.length() - 1);
		int size = Integer.parseInt(fileSizeLimit.substring(0, fileSizeLimit.length() - 1));
		if(temp.toUpperCase().equals("M")){
			//将M转换为K
			return size * 1024;
			
		}else if(temp.toUpperCase().equals("K")){
			return size;
			
		}else{
			//将B转换为K
			return size / 1024;
		}
	}
	
	/**
	 * 返回错误json信息
	 * @param message
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
    
    
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public File getImgFile() {
		return imgFile;
	}
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	
	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}
	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public String getFileSizeLimit() {
		return fileSizeLimit;
	}
	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getWaterMark() {
		return waterMark;
	}
	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}
	public Map<String, String> getMessage() {
		return message;
	}
	public void setMessage(Map<String, String> message) {
		this.message = message;
	}
	
	@SuppressWarnings("unchecked")
	class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	@SuppressWarnings("unchecked")
	class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
}
