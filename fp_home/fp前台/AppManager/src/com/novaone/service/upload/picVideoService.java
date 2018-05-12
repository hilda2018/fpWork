package com.novaone.service.upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
public class picVideoService {

private static final Log log = LogFactory.getLog(picVideoService.class);
	


	/* (non-Javadoc)
	 * @see com.zhiqu.framework.service.Service#save(java.lang.Object)
	 */
	public void save(HttpServletRequest request, Object entity) throws Exception {
		Timestamp time = new Timestamp(new Date().getTime());
		
		//存储图片
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();  
		//图片名称
		String imgName = wrapper.getFileNames(wrapper.getParameter("fileObjName"))[0]; 
		//图片上传文件流
		File imgFile = wrapper.getFiles(wrapper.getParameter("fileObjName"))[0];
		FileInputStream imageFileInputStream = new FileInputStream(imgFile);
			//ftp上传
			String usrName=request.getParameter("username");
			FtpService ftp=new FtpService();
			//TODO 目录改为缓存比较好
			String pathFile=ftp.getImagePath();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");  
			Date dt=new Date();  
			String date=sdf.format(dt); 
					String uString=imgFile.getName();
					if(uString!=null&&!uString.equals("")){
					uString=uString.split("\\.")[1];
					String filename=usrName+"_"+date+"."+uString;
					InputStream streamIn = imageFileInputStream;
					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
					mapftp.put(pathFile+filename, streamIn);
					//videoAdActionForm.getVideoAd().setVaPic("http://" + ftp.getFtpAddr() + "/" +ftp.getPojName()+ "/" + pathFile + filename);
					ftp.upload(mapftp);
				}
			
		
		//存储广告jar包
//		Upfile ufApp = videoAdActionForm.getUpfileApp();
//		if (ufApp != null&&ufApp.getFilelist().length>0){
//			FormFile[] file=ufApp.getFilelist();
//			//ftp上传
//			FtpService ftp=new FtpService();
//			
//			String pathFile=ftp.getAdjarPath();
//			String date=DateUtil.dateToStr(new Date(), 5);
//			
//			for(int i=0;i<file.length;i++){
//				if(file[i]!=null&&file[i].getFileSize()>0){
//					String uString=file[i].getFileName();
//					if(uString==null||uString.equals(""))continue;
//					uString=uString.split("\\.")[1];
//					String filename=date+"_"+i+"."+uString;
//					InputStream streamIn = file[i].getInputStream();
//					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
//					mapftp.put(pathFile+filename, streamIn);
//					videoAdActionForm.getVideoAd().setVaJar("http://" + ftp.getFtpAddr()+ "/"+ftp.getPojName()+"/" + pathFile + filename);
//					ftp.upload(mapftp);
//				}
//			}
//		}
		
		//存储广告展示文件
//		Upfile udAdzip = videoAdActionForm.getUpfileAdzip();
//		if (udAdzip != null&&udAdzip.getFilelist().length>0){
//			FormFile[] file=udAdzip.getFilelist();
//			//ftp上传
//			FtpService ftp=new FtpService();
//			
//			String pathFile=ftp.getAdfilePath();
//			String date=DateUtil.dateToStr(new Date(), 5);
//			
//			for(int i=0;i<file.length;i++){
//				if(file[i]!=null&&file[i].getFileSize()>0){
//					String uString=file[i].getFileName();
//					if(uString==null||uString.equals(""))continue;
//					uString=uString.split("\\.")[1];
//					String filename=date+"_"+i+"."+uString;
//					InputStream streamIn = file[i].getInputStream();
//					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
//					mapftp.put(pathFile+filename, streamIn);
//					videoAdActionForm.getVideoAd().setVaFile("http://" + ftp.getFtpAddr()+ "/"+ftp.getPojName()+"/" + pathFile + filename);
//					ftp.upload(mapftp);
//				}
//			}
//		}
				
//		if(entity instanceof VideoAd){
//			((VideoAd) entity).setSaveTime(time);
//			((VideoAd) entity).setUpdateTime(time);
//			((VideoAd) entity).setIsDelete("0");
//			
//			videoAdDao.save((VideoAd)entity);
//		}else{
//			log.error("%%%%%保存VideoAd时传入了错误对象"+ClassNameGetter.getClassName(entity)+"%%%%%");
//		}
	}

	/* (non-Javadoc)
	 * @see com.zhiqu.framework.service.Service#update(java.lang.Object)
	 */
	public void update(HttpServletRequest request,	Object entity) throws Exception {
		
		Timestamp time = new Timestamp(new Date().getTime());
		
		//存储图片
//		Upfile uf = videoAdActionForm.getUpfile();
//		if (uf != null&&uf.getFilelist().length>0){
//			FormFile[] file=uf.getFilelist();
//			//ftp上传
//			FtpService ftp=new FtpService();
//			//TODO 目录改为缓存比较好
//			String pathFile=ftp.getImagePath();
//			String date=DateUtil.dateToStr(new Date(), 5);
//			
//			//图片上传
//			for(int i=0;i<file.length;i++){
//				if(file[i]!=null&&file[i].getFileSize()>0){
//					String uString=file[i].getFileName();
//					if(uString==null||uString.equals(""))continue;
//					String[] strName = uString.split("\\.");
//					if(strName.length>1){
//						uString=strName[strName.length-1];
//					}
//					String filename=date+"_"+i+"."+uString;
//					InputStream streamIn = file[i].getInputStream();
//					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
//					mapftp.put(pathFile+filename, streamIn);
//					videoAdActionForm.getVideoAd().setVaPic("http://" + ftp.getFtpAddr() + "/" +ftp.getPojName()+ "/" + pathFile + filename);
//					ftp.upload(mapftp);
//				}
//			}
//		}
		
		//存储广告jar包
//		Upfile ufApp = videoAdActionForm.getUpfileApp();
//		if (ufApp != null&&ufApp.getFilelist().length>0){
//			FormFile[] file=ufApp.getFilelist();
//			//ftp上传
//			FtpService ftp=new FtpService();
//			
//			String pathFile=ftp.getAdjarPath();
//			String date=DateUtil.dateToStr(new Date(), 5);
//			
//			for(int i=0;i<file.length;i++){
//				if(file[i]!=null&&file[i].getFileSize()>0){
//					String uString=file[i].getFileName();
//					if(uString==null||uString.equals(""))continue;
//					String[] strName = uString.split("\\.");
//					if(strName.length>1){
//						uString=strName[strName.length-1];
//					}
//					String filename=date+"_"+i+"."+uString;
//					InputStream streamIn = file[i].getInputStream();
//					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
//					mapftp.put(pathFile+filename, streamIn);
//					videoAdActionForm.getVideoAd().setVaJar("http://" + ftp.getFtpAddr()+ "/"+ftp.getPojName()+"/" + pathFile + filename);
//					ftp.upload(mapftp);
//				}
//			}
//		}
		
		//存储广告展示文件
//		Upfile udAdzip = videoAdActionForm.getUpfileAdzip();
//		if (udAdzip != null&&udAdzip.getFilelist().length>0){
//			FormFile[] file=udAdzip.getFilelist();
//			//ftp上传
//			FtpService ftp=new FtpService();
//			
//			String pathFile=ftp.getAdfilePath();
//			String date=DateUtil.dateToStr(new Date(), 5);
//			
//			for(int i=0;i<file.length;i++){
//				if(file[i]!=null&&file[i].getFileSize()>0){
//					String uString=file[i].getFileName();
//					if(uString==null||uString.equals(""))continue;
//					String[] strName = uString.split("\\.");
//					if(strName.length>1){
//						uString=strName[strName.length-1];
//					}
//					String filename=date+"_"+i+"."+uString;
//					InputStream streamIn = file[i].getInputStream();
//					Map<String, InputStream> mapftp=new HashMap<String, InputStream>();
//					mapftp.put(pathFile+filename, streamIn);
//					videoAdActionForm.getVideoAd().setVaFile("http://" + ftp.getFtpAddr()+ "/"+ftp.getPojName()+"/" + pathFile + filename);
//					ftp.upload(mapftp);
//				}
//			}
//		}
				
//		if(entity instanceof VideoAd){
//			((VideoAd) entity).setUpdateTime(time);
//			videoAdDao.update((VideoAd)entity);
//		}
//		else{
//			log.error("%%%%%上传失败%%%%%");
//		}
	
	}



	/**
	 * 用于批量删除时接收多个对象的id
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List findByIds(Object value)throws Exception {
		List<Object> list = new ArrayList<Object>();
		String values = value.toString();
		String[] valueSet = values.split(",");
		for(int i=0;i<valueSet.length;i++)
		{
			if(!valueSet[i].equals("")&&valueSet[i]!=null){
			list.add(this.findById(Integer.parseInt(valueSet[i])));
			}
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.zhiqu.framework.service.Service#findAll()
	 */
	public List findAll() throws Exception {
		return null;
	}

	
	
	/**
	 * @comm 根据id查询
	 * @author jiangfuming
	 * @date 2015-2-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object findById(Integer id) throws Exception {
		return null;
	}

	
}
