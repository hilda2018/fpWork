package com.novaone.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.novaone.constants.Constants;


/**
 * 
 * 
 * @类编号:
 * @类名称:PushUtil
 * @内容摘要: 极光推送工具类
 * @author:陈自军
 * @创建日期:2016年8月3日 上午10:33:07
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class PushUtil {
	/**
	 *  1.首先最好是把极光官网java后台服务器的demo下载下来，里面有我们需要的jar包，以及example.
     *  2.极光推送的关键jpushClient = new JPushClient(masterSecret, appKey, 3);就是这个类。
     *  其中的参数需要我们从极光官网注册开发者，然后创建具体项目获取相应的两  
     *  个key值。其中appKey值就是我们手机端对应的key值
     *  3.极光推送给我们提供了很多种推送的方式，我们可以选择某一个平台进行推送（Android ,IOS ,Windows Phone），
     *  也可以全部推送；我们可以针对某个特别的用户进行推送（设置alisa），也可以针对特别的群体进行推送（设置tag），
     *  第三个参数是设置推送保留的时间，只要在有效时间内上线就可以收到推送信息
     *  4. 极光推送现在都用https连接，提交请求是post，获取数据为get
	 */
	
	
	public static final Logger logger =  LoggerFactory.getLogger(PushUtil.class);
	//key
	private String appKey =Constants.APP_KEY;
	//masterSecret
	private String masterSecret = Constants.APP_CRE;
	//Client request retry times
	private JPushClient  jpushclient = null;
	private static PushUtil pushUtil = null;
	/**
	 * 
	 * @方法名称:getInstance
	 * @内容摘要: 静态工厂方法
	 * @return 
	 * PushUtil
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月3日-上午11:03:14
	 */
	public static PushUtil getInstance(){
		if (pushUtil == null){
			pushUtil = new PushUtil();
		}
		return pushUtil;
	}
	//默认构造方法
	private PushUtil(){
	}
	/**
	 * 
	 * @方法名称:getJPushClient
	 * @内容摘要: 构建客户端
	 * @return 
	 * JPushClient
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月3日-上午11:06:54
	 */
	private   JPushClient getJPushClient(){
		jpushclient = new JPushClient(masterSecret, appKey);
		return jpushclient;
	}
	/**
	 * 
	 * @方法名称:buildPushObject_all_all_alert
	 * @内容摘要: 所有用户不区分平台
	 * @param content
	 * @return 
	 * PushPayload
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月4日-下午1:35:04
	 */
	public static PushPayload buildPushObject_all_all_alert(String content){
		return PushPayload.alertAll(content);
	}
	/**
	 * 
	 * @方法名称:buildPushObject_all_alias_alert
	 * @内容摘要: 面向别名用户推送
	 * @param content
	 * @param aliases
	 * @return 
	 * PushPayload
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月4日-下午1:34:33
	 */
	public static PushPayload buildPushObject_all_alias_alert(String content,Collection<String> aliases){
		  return PushPayload.newBuilder()  
	                .setPlatform(Platform.all())//设置接受的平台  
	                .setAudience(Audience.alias(aliases))//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到  
	                .setNotification(Notification.alert(content))
	                .setOptions(Options.newBuilder()  
                         .setApnsProduction(true)  // true生产环境推送 false 开发环境测试
                         .build())  
	                .build();  
	}
	/**
	 * 
	 * @方法名称:push_alias_message
	 * @内容摘要: 极光推送单用户推送
	 * @param content
	 * @param aliases 
	 * void
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月4日-下午1:34:01
	 */
	public static void  push_alias_message(String content,Collection<String> aliases){
		PushPayload payload=buildPushObject_all_alias_alert(Constants.APP_MSG, aliases);
		try {
			PushResult result =  PushUtil.getInstance().getJPushClient().sendPush(payload);
		} catch (APIConnectionException e) {
			  logger.error("Connection error. Should retry later. ");  
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ");  
			logger.info("HTTP Status: " + e.getStatus());  
			logger.info("Error Code: " + e.getErrorCode());  
			logger.info("Error Message: " + e.getErrorMessage());  
			logger.info("Msg ID: " + e.getMsgId());  
		}
		
	}
	/**
	 * 
	 * @方法名称:buildPushObject_android_tag_alertWithTitle
	 * @内容摘要: 面向安卓手机带标题推送
	 * @param content
	 * @param title
	 * @return 
	 * PushPayload
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月4日-下午1:35:56
	 */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String content,String title) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android())  
                .setAudience(Audience.all())  
                .setNotification(Notification.android(content, title, null))  
                .build();
    }  
    /**
     * 
     * @方法名称:buildPushObject_android_and_ios
     * @内容摘要: 面向安卓以及苹果系统推送
     * @return 
     * PushPayload
     * @exception 
     * @author:陈自军
     * @创建日期:2016年8月4日-下午1:37:51
     */
    public static PushPayload buildPushObject_android_and_ios() {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android_ios())  
                .setAudience(Audience.tag("tag1"))  
                .setNotification(Notification.newBuilder()  
                        .setAlert("alert content")  
                        .addPlatformNotification(AndroidNotification.newBuilder()  
                                .setTitle("Android Title").build())  
                        .addPlatformNotification(IosNotification.newBuilder()  
                                .incrBadge(1)  
                                .addExtra("extra_key", "extra_value").build())  
                        .build())  
                .build();  
    }  
      
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String content,String msg_content) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.ios())  
                .setAudience(Audience.tag_and("tag1", "tag_all"))  
                .setNotification(Notification.newBuilder()  
                        .addPlatformNotification(IosNotification.newBuilder()  
                                .setAlert(content)  
                                .setBadge(5)  
                                .setSound("happy")  
                                .addExtra("from", "JPush")  
                                .build())  
                        .build())  
                 .setMessage(Message.content(msg_content))  
                 .setOptions(Options.newBuilder()  
                         .setApnsProduction(true)  
                         .build())  
                 .build();  
    }  
      
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String msg_content) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android_ios())  
                .setAudience(Audience.newBuilder()  
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))  
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))  
                        .build())  
                .setMessage(Message.newBuilder()  
                        .setMsgContent(msg_content)  
                        .addExtra("from", "JPush")  
                        .build())  
                .build();  
    }  
	
	public static void main(String[] args) {
		JPushClient jc= PushUtil.getInstance().getJPushClient();
		List<String> lists = new ArrayList<>();
		String id="67fca53e-ac23-4718-97f4-dee1a057043b";
		String aliases =id.replaceAll("-", "");
		lists.add(aliases);
		lists.add("69af18cd22404bd4afe190e382501762");
		System.out.println(aliases);
	PushPayload payload=buildPushObject_all_alias_alert(Constants.APP_MSG, lists);
		try {
			PushResult result = jc.sendPush(payload);
			System.out.println(result+"=================================");
		} catch (APIConnectionException e) {
			   logger.error("Connection error. Should retry later. ");
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ");  
			logger.info("HTTP Status: " + e.getStatus());  
			logger.info("Error Code: " + e.getErrorCode());  
			logger.info("Error Message: " + e.getErrorMessage());  
			logger.info("Msg ID: " + e.getMsgId());  
		}
	}
	
	
	
	
	
	
	
	
}
