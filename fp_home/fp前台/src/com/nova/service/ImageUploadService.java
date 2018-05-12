package com.nova.service;

import java.util.Hashtable;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.fresh.common.base.bean.ImageBean;


/**
 * 服务接口
 * @author chengql
 * @date 2013-11-13
 */
@WebService  
@javax.xml.ws.soap.MTOM
public interface  ImageUploadService {
	
	/**
	 * 上传图片
	 * @param image 图片模型
	 * @param memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @param imageName 图片名称
	 * @return
	 */
	@WebMethod  
    public String uploadImage(@WebParam(name = "image")ImageBean image,@WebParam(name = "memberId")String memberId,@WebParam(name = "type")int type,@WebParam(name = "imageName")String imageName);
	
	/**
	 * 删除图片
	 * @param memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @param imageName 图片名称
	 * @return
	 */
	@WebMethod 
	public String deleteImage(@WebParam(name = "memberId")String memberId,@WebParam(name = "type")int type,@WebParam(name = "imageName")String imageName);
	
	/**
	 * 根据用户ID及上传类型获得上传文件的路径
	 * @param memberId memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @return
	 */
	public String getUploadFilePath(@WebParam(name = "memberId")String memberId,@WebParam(name = "type")int type);
	
	/**
	 * 获得指定目录下的所有文件名
	 * @param memberId memberId 用户id
	 * @param type 类型(1:用户 2:产品 3:商铺 4:资质 5:品牌 6:新闻 7:其它 8:富文本)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Hashtable> getUploadFileListOfPath(@WebParam(name = "memberId")String memberId,@WebParam(name = "type")int type);
	
	
}

