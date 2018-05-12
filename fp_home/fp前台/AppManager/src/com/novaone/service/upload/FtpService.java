package com.novaone.service.upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.novaone.common.OcrCommonInfo;
public class FtpService {

	 private Logger log = Logger.getLogger(FtpService.class); 
	    private FTPClient ftpClient;
	    private String ftpAddr;
	    private Integer ftpPort;
	    private String ftpUser;
	    private String ftpPswd;
	    private String pojName;
	    private String imagePath;
	    private String imageMiddlePath;
	    private String imageSmallPath;
	    private String imageBigPath;
	    private String imageCustomPath;
	    private String adjarPath;
	    private String adfilePath;
	    private String appfilePath;
	    
	   

		private static final String PROP_FILEPATH = "config.properties";
	    private static final String PROP_FTPADDR = "ftpService.ftpAddr";
	    private static final String PROP_FTPPORT = "ftpService.ftpPort";
	    private static final String PROP_FTPUSER = "ftpService.ftpUser";
	    private static final String PROP_FTPPSWD = "ftpService.ftpPswd";
	    private static final String PROP_POJNAME = "ftpService.pojName";
	    private static final String PROP_IMAGEPATH = "imageUploadService.imageDir";
	    private static final String PROP_IMAGEPATH_MIDDLE = "imageUploadService.imageMiddleDir";
	    private static final String PROP_IMAGEPATH_SMALL = "imageUploadService.imageSmallDir";
	    private static final String PROP_IMAGEPATH_Big = "imageUploadService.imageBigDir";
	    private static final String PROP_IMAGEPATH_Custom = "imageUploadService.imageCustomDir";
	    private static final String PROP_ADJARPATH = "adUploadService.jarLargeDir";
	    private static final String PROP_ADFILEPATH = "adUploadService.fileLargeDir";
	    private static final String PROP_APPFILEPATH = "adUploadService.appFileDir";
	    
	    public FtpService() {
	        Properties properties = new Properties();
	        try {
	        	InputStream inputStream=FtpService.class.getClassLoader().getResourceAsStream(PROP_FILEPATH);
	            properties.load(inputStream);
	            inputStream.close();
	            this.ftpAddr = (String) properties.get(PROP_FTPADDR);
	            this.ftpPort = Integer.parseInt((String) properties.get(PROP_FTPPORT));
	            this.ftpUser = (String) properties.get(PROP_FTPUSER);
	            this.ftpPswd = (String) properties.get(PROP_FTPPSWD);
	            this.pojName = (String) properties.get(PROP_POJNAME);
	            this.imagePath = (String) properties.get(PROP_IMAGEPATH);
	            this.imageMiddlePath = (String) properties.get(PROP_IMAGEPATH_MIDDLE);
	            this.imageSmallPath = (String) properties.get(PROP_IMAGEPATH_SMALL);
	            this.imageBigPath = (String) properties.get(PROP_IMAGEPATH_Big);
	            this.imageCustomPath = (String) properties.get(PROP_IMAGEPATH_Custom);
	            this.adjarPath = (String) properties.get(PROP_ADJARPATH);
	            this.adfilePath = (String) properties.get(PROP_ADFILEPATH);
	            this.appfilePath = (String) properties.get(PROP_APPFILEPATH);
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        }
	    }

	  

	    /**
	     * 连接ftp服务器并登录，如果已连接则直接返回成功
	     * @return 处理结果
	     */
	    private boolean connect() {
	        //没有ftpClient，则创建ftpClient对象
	        if(ftpClient == null){
	            log.debug("----创建FTP实例----");
	            ftpClient = new FTPClient();
	        }
	        //连接ftp服务器，登录用户
	        if(!ftpClient.isConnected() || !ftpClient.isAvailable()){
	            boolean result = false;
	            try {
	                ftpClient.connect(ftpAddr, ftpPort);
	                //判断是否连接成功
	                int reply = ftpClient.getReplyCode();    
	                if(!FTPReply.isPositiveCompletion(reply)){    
	                    ftpClient.disconnect();
	                    log.error("----FTP连接被拒绝----");
	                    return false;    
	                }
	                log.debug("----FTP连接成功----");
	                //连接成功，则登录用户
	                result = ftpClient.login(ftpUser, ftpPswd);
	                if(!result){
	                    return false;
	                }
	                log.info("----FTP登录成功----");
	                //设置文件传输方式为二进制
	                try {
	                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	                    } catch (IOException e) {
	                        log.error(e.getMessage(), e);
	                    }
	            } catch (Exception e) {
	                log.error(e.getMessage(), e);
	                return false;
	            } finally {
	                if(!result && ftpClient.isConnected()){
	                    try {
	                        ftpClient.disconnect();
	                    } catch (IOException e) {
	                        log.error(e.getMessage(), e);
	                        return false;
	                    }
	                }
	            }
	        }
	        //连接并登录成功
	        return true;    
	    }
	    
	    /**
	     * 中断ftp服务器
	     * @return 处理结果
	     */
	    public void disConnect() {
	        //没有ftpClient，则创建ftpClient对象
	        if(ftpClient != null){
	            try {
	                log.info("----FTP断开连接----");
	                ftpClient.disconnect();
	            } catch (IOException e) {
	                log.error(e.getMessage(), e);
	            }
	            ftpClient = null;
	        }
	    }
	    
	    /**
	     * 上传文件，传入流未关闭
	     * @param map 键为文件名，如果有目录如imageLarge/xxxx.jpg；值为文件流
	     * @return
	     */
	    public boolean upload(Map<String, InputStream> map){
	        //连接ftp
	        boolean r = this.connect();
	        if(!r){
	            log.error("----FTP连接失败----");
	            return false;
	        }
	        //已完成上传的文件列表
	        HashSet<String> doneList = new HashSet<String>();
	        boolean result = false;
	        try {
	            for(Entry<String, InputStream> entry : map.entrySet()){
	                String fn = entry.getKey();
	                InputStream fin = entry.getValue();
	                ftpClient.setControlEncoding("GBK");
	                ftpClient.enterLocalPassiveMode();
	                result = ftpClient.storeFile(new String(fn.getBytes("GBK"),"iso-8859-1"), fin);
	                if(!result){
	                    log.error(String.format("----FTP文件[%s]上传失败----", fn));
	                    return false;
	                }
	            }
	        } catch(IOException e) {
	            //出异常则取消原来上传的文件
	             log.error(e.getMessage(), e);
	            for(String fn : doneList){
	                try {
	                    ftpClient.deleteFile(fn);
	                } catch (IOException e1) {
	                    log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	                }
	            }
	            return false;
	        }
	        //不成功则取消原来上传的文件
	        if(!result){
	            for(String fn : doneList){
	                try {
	                    boolean rr = ftpClient.deleteFile(fn);
	                    if(!rr){
	                        log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	                    }
	                } catch (IOException e1) {
	                    log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	                }
	            }
	            return false;
	        }
	        this.disConnect();
	        return true;
	    }
	    
	    /**
	     * 删除文件
	     * @param list 要删除的文件名列表，如有目录则名上带目录名如imageLarge/xxxx.jpg
	     */
	    public void delete(List<String> list){
	        //空的话，不进行任务操作
	        if(list.size() == 0) {
	            return;
	        }
	        //连接ftp
	        boolean r = this.connect();
	        if(!r){
	            for(String fn : list) {
	                log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	            }
	            return;
	        }
	        for(String fn : list){
	            try {
	                boolean rr = ftpClient.deleteFile(fn);
	                if(!rr){
	                    log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	                }
	            } catch (IOException e1) {
	                log.error(String.format("---ftp文件[%s]，删除失败---", fn));
	            }
	        }
	        this.disConnect();
	    }
	    
	    /**
	     * 得到文件列表,listFiles返回包含目录和文件，它返回的是一个FTPFile数组
	     * listNames()：只包含目录的字符串数组
	     * String[] fileNameArr = ftpClient.listNames(path); 
	     * @param path:服务器上的文件目录:/DF4
	     */
	    public List<String> getFileList(String path, String targetName) throws IOException {  
	    	boolean r = this.connect();
	        if(!r){
	            log.error("----FTP连接失败----");
	            return null;
	        }
	        ftpClient.enterLocalPassiveMode();
	        FTPFile[] ftpFiles= ftpClient.listFiles(path);  
	        List<String> retList = new ArrayList<String>();  
	        if (ftpFiles == null || ftpFiles.length == 0) {  
	            return retList;  
	        }  
	        for (FTPFile ftpFile : ftpFiles) {  
	            if (ftpFile.isFile()) {
	            	if(ftpFile.getName().indexOf(targetName)!=-1) {
	            		retList.add(ftpFile.getName());  
	            	}
	            }  
	        } 
	        this.disConnect();
	        return retList;  
	    }
	    /**
	     * 
	     * @Title: wildcardMatch 
	     * @Description: 过滤文件名
	     * @param pattern
	     * @param str
	     * @return
	     * @author zhaojiyan
	     * @throws
	     */
	    public static boolean wildcardMatch(String pattern, String str) {
	        int patternLength = pattern.length();
	        int strLength = str.length();
	        int strIndex = 0;
	        char ch;
	        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
	            ch = pattern.charAt(patternIndex);
	            if (ch == '*') {
	                //通配符星号*表示可以匹配任意多个字符
	                while (strIndex < strLength) {
	                    if (wildcardMatch(pattern.substring(patternIndex + 1),
	                            str.substring(strIndex))) {
	                        return true;
	                    }
	                    strIndex++;
	                }
	            } else if (ch == '?') {
	                //通配符问号?表示匹配任意一个字符
	                strIndex++;
	                if (strIndex > strLength) {
	                    //表示str中已经没有字符匹配?了。
	                    return false;
	                }
	            } else {
	                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
	                    return false;
	                }
	                strIndex++;
	            }
	        }
	        return (strIndex == strLength);
	    }
	 
	    
	    public boolean download(String remoteFileName, String localFileName) throws IOException {  
	        boolean flag = false;  
	        boolean r = this.connect();
	        if(!r){
	            log.error("----FTP连接失败----");
	            return false;
	        }
	        File outfile = new File(localFileName);  
	        OutputStream oStream = null;  
	        try {  
	            oStream = new FileOutputStream(outfile); 
	            ftpClient.enterLocalPassiveMode();
	            flag = ftpClient.retrieveFile(remoteFileName, oStream);  
	        } catch (IOException e) {  
	            flag = false;  
	            return flag;  
	        } finally {  
	            oStream.close();  
	        }  
	        this.disConnect();
	        return flag;  
	    }
	    
	    public String getAppfilePath() {
			return appfilePath;
		}

		public void setAppfilePath(String appfilePath) {
			this.appfilePath = appfilePath;
		}

		public String getPojName() {
			return pojName;
		}

		public void setPojName(String pojName) {
			this.pojName = pojName;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getAdjarPath() {
			return adjarPath;
		}

		public void setAdjarPath(String adjarPath) {
			this.adjarPath = adjarPath;
		}

		public String getAdfilePath() {
			return adfilePath;
		}

		public void setAdfilePath(String adfilePath) {
			this.adfilePath = adfilePath;
		}
	    public String getFtpAddr() {
	        return ftpAddr;
	    }

	    public void setFtpAddr(String ftpAddr) {
	        this.ftpAddr = ftpAddr;
	    }

	    public Integer getFtpPort() {
	        return ftpPort;
	    }

	    public void setFtpPort(Integer ftpPort) {
	        this.ftpPort = ftpPort;
	    }

	    public String getFtpUser() {
	        return ftpUser;
	    }

	    public void setFtpUser(String ftpUser) {
	        this.ftpUser = ftpUser;
	    }

	    public String getFtpPswd() {
	        return ftpPswd;
	    }

	    public void setFtpPswd(String ftpPswd) {
	        this.ftpPswd = ftpPswd;
	    }



		public String getImageMiddlePath() {
			return imageMiddlePath;
		}



		public void setImageMiddlePath(String imageMiddlePath) {
			this.imageMiddlePath = imageMiddlePath;
		}



		public String getImageSmallPath() {
			return imageSmallPath;
		}



		public void setImageSmallPath(String imageSmallPath) {
			this.imageSmallPath = imageSmallPath;
		}



		public String getImageBigPath() {
			return imageBigPath;
		}



		public void setImageBigPath(String imageBigPath) {
			this.imageBigPath = imageBigPath;
		}



		public String getImageCustomPath() {
			return imageCustomPath;
		}



		public void setImageCustomPath(String imageCustomPath) {
			this.imageCustomPath = imageCustomPath;
		}
		
		public static void main(String[] args) {
			FtpService ftp=new FtpService();
			ftp.setFtpAddr(OcrCommonInfo.OCRFTPADDR);//设置FTP的IP
			ftp.setFtpPort(OcrCommonInfo.OCRFTPPORT);//端口号
			ftp.setFtpUser(OcrCommonInfo.OCRFTPUSER);//用户名
			ftp.setFtpPswd(OcrCommonInfo.OCRFTPPSWD);//密码
			try {
//				List<String> fileList = ftp.getFileList("/Output/","42aebe9f-18c0-4384-b759-8a1db672dfb4_1447552432266");
				ftp.download("/Output/42aebe9f-18c0-4384-b759-8a1db672dfb4_1447552432266.jpg", "E:/novadata/.metadata/.me_tcat/webapps/fresh_port/ocrdownload/42aebe9f-18c0-4384-b759-8a1db672dfb4_1447552432266.jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
}
