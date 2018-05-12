package com.fresh.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.fresh.common.base.bean.ImageBean;
import com.nova.service.ImageUploadService;

/**
 * 图片上传工具类
 * @author dell-pc
 *
 */
public class ImageUploadUtil {

	private static Log logger = LogFactory.getLog(ImageUploadUtil.class);
	private static  String imgUrl ;//;"http://localhost:9080/imageService?wsdl";
	static{
		Properties prop = new Properties();
		InputStream in = ImageUploadUtil.class.getResourceAsStream("/system.properties");
		try {
			prop.load(in);
			imgUrl = prop.getProperty("uploadImgUrl").trim();
		} catch (IOException e) {
			logger.error("读取图片服务器路径异常，异常信息为："+e.getMessage());
		}
	}
	
	
	/**
     * 上传图片至图片服务器
     */
    public static String uploadImageToImageServer(File file, String memberId, String type, String imageName, String imageContentType) {
        ImageBean resume = new ImageBean();
        resume.setCandidateName(memberId);
        resume.setResumeFileType(imageContentType);
        DataSource source = new FileDataSource(file);
        resume.setResume(new DataHandler(source));
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ImageUploadService.class);
        factory.setAddress(imgUrl);
        ImageUploadService client = (ImageUploadService) factory.create();
        try {
            return client.uploadImage(resume, memberId, Integer.parseInt(type), imageName);
        } catch (Exception e) {
            logger.error("上传图片至图片服务器失败：" + e.getMessage());
            return "";
        }
    }
    
    /**
     * 删除服务器上的图片
     * @param memberId 用户 id
     * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它)
     * @param imageName 图片名称
     * @return
     */
    public static String deleteImagesOfImageServer(String memberId, String type, String imageName){
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ImageUploadService.class);
        factory.setAddress(imgUrl);
        ImageUploadService client = (ImageUploadService) factory.create();
        try {
            return client.deleteImage(memberId, Integer.parseInt(type), imageName);
        } catch (Exception e) {
            logger.error("图片服务器删除图片失败：" + e.getMessage());
            return "false";
        }
    }
    
    /**
	 * 根据用户ID及上传类型获得上传文件的路径
	 * @param memberId memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @return
	 */
    public static String getUploadFilePath(String memberId,String type){
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ImageUploadService.class);
        factory.setAddress(imgUrl);
        ImageUploadService client = (ImageUploadService) factory.create();
        try {
            return client.getUploadFilePath(memberId, Integer.parseInt(type));
        } catch (Exception e) {
            logger.error("获得上传文件路径失败：" + e.getMessage());
            return "";
        }
    }
    
    /**
	 * 获得指定目录下的所有文件名
	 * @param memberId memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Hashtable> getUploadFileListOfPath(String memberId, String type){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ImageUploadService.class);
        factory.setAddress(imgUrl);
        ImageUploadService client = (ImageUploadService) factory.create();
        try {
            return client.getUploadFileListOfPath(memberId, Integer.parseInt(type));
        } catch (Exception e) {
            logger.error("获得指定目录下的所有文件名失败：" + e.getMessage());
            return null;
        }
	}

}