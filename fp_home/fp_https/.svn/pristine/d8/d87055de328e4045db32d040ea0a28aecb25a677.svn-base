package com.fresh.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import jodd.io.FileUtil;
import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;
import jodd.mail.att.ByteArrayAttachment;
import jodd.util.MimeTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MailUtils {

	private static String userName = "daniel@xjyzjd.com";
	private static String password = "daniel@xjyzjd.com";
	private static String forgetTemplate = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在{0}提交找回密码请求，请点击下面的链接修改用户{1}的密码:<br><a href=\"{2}\">{3}</a></body></html>";
	private static String inquireTemplate = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在{0}收到一条询盘回复，请点击下面的链接查看:<a href=\"{1}\">{2}</a></body></html>";
	private static String authenticationTemplate = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此邮件为新疆机电网邮箱验证邮件，如非本人请求，请忽略，如是被人请求请点击下面的链接查看:<a href=\"{0}\">{1}</a></body></html>";
	private static String enquiries = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在新疆机电网（xjjdw.com）订制的免费询盘邮件服务，系统提醒您：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您有一条新的询盘消息，请点击以下链接查看：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{1}\">{1}</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static String contactShopData = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您收到新疆机电网（xjjdw.com）用户给您的留言。系统提醒您：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您有一条新的用户留言，请点击以下链接查看：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{1}\">{1}</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static String orderTemplate = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在新疆机电网（xjjdw.com）订制的免费订单邮件服务，系统提醒您：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您有一条新的订单消息，请点击以下链接查看：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{1}\">{1}</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static String sendEmailCaptchaData = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您收到新疆机电网（xjjdw.com）邮箱验证信息。系统提醒您：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您有一条来自新疆机电网的邮箱验证信息，请点击以下链进行验证：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{0}\">{0}</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static String contactShopMeData = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{0}&nbsp;&nbsp;您在新疆机电网（www.xjjdw.com）的留言：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;留言内容：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{2}" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{1}\">查看更多商家信息请点击这里...</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static String replyToComment = "<html><body><img src=\"cid:logo.png\"><p>尊敬的用户，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在新疆机电网（www.xjjdw.com）的留言已被回复：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您留言的内容：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{3}" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回复内容：" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{2}" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{0}\">查看更多商家信息请点击这里...</a>" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{0}" +
										"<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(若不能点击该链接，请将该链接复制并粘贴到您的浏览器地址栏内)" +
										"</body></html>";
	private static Log logger = LogFactory.getLog(MailUtils.class);
	/**
	 * 构造函数
	 * @param userName
	 * @param password
	 */
	public MailUtils(String userName,String password){
		MailUtils.userName = userName;
		MailUtils.password = password;
	}
	
	/**
	 * 发送邮件
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	private static boolean sendMail(ServletContext request,int type, String toUser,String object,Object[] params) throws IOException{
		
		Email email = Email.create().from("service@xjjdw.com").to(toUser).subject(object);
		
		String contentStr = "";
		
		switch(type){
		  case 1:contentStr = MessageFormat.format(forgetTemplate,params);break;
		  case 2:contentStr = MessageFormat.format(inquireTemplate,params);break;
		  case 3:contentStr = MessageFormat.format(authenticationTemplate,params);break;
		  case 4:contentStr = MessageFormat.format(enquiries,params);break;
		  case 5:contentStr = MessageFormat.format(orderTemplate,params);break;
		  case 6:contentStr = MessageFormat.format(contactShopData,params);break;
		  case 7:contentStr = MessageFormat.format(sendEmailCaptchaData,params);break;
		  case 8:contentStr = MessageFormat.format(contactShopMeData,params);break;
		  case 9:contentStr = MessageFormat.format(replyToComment,params);break;
		}
		
		EmailMessage htmlMessage = new EmailMessage(contentStr, MimeTypes.MIME_TEXT_HTML);
		
		EmailAttachment embeddedAttachment =
	        new ByteArrayAttachment(
	            FileUtil.readBytes(request.getRealPath("/")+"/library/img/logo.png"), "image/png", "logo.png", "logo.png");
	    embeddedAttachment.setEmbeddedMessage(htmlMessage);
	    email.attach(embeddedAttachment);
	    
		email.addMessage(htmlMessage);
		SmtpServer smtpServer = new SmtpServer("smtp.easeye.com.cn", new SimpleAuthenticator(MailUtils.userName, MailUtils.password));
		SendMailSession session = smtpServer.createSession();
	    session.open();
	    session.sendMail(email);
	    session.close();
		return true;
	}
	/**
	 * 发送订单邮件
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	public static boolean sendOrderMail(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		try {
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			MailUtils.sendMail(request, 5, toUser, object, new Object[]{df.format(new Date()),url,content});
			return true;
		} catch (IOException e) {
			logger.error("订单生成邮件发送失败:"+e.getMessage());
			return false;
		}
	}
	/**
	 * 发送询盘邮件
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	public static boolean sendInquireMail(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		try {
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			MailUtils.sendMail(request, 4, toUser, object, new Object[]{df.format(new Date()),url,content});
			return true;
		} catch (IOException e) {
			logger.error("询盘提醒邮件发送失败:"+e.getMessage());
			return false;
		}
	}
	/**
	 * 发送找回密码
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	public static boolean sendFindPasswordMail(ServletContext request,String toUser,String object,String content,String modifeidUser){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 try {
			MailUtils.sendMail(request, 1, toUser, object, new Object[]{df.format(new Date()),modifeidUser,content,content});
			return true;
		} catch (IOException e) {
			logger.error("找回密码邮件发送失败:"+e.getMessage());
			return false;
		}
		 
	}
	/**
	 * 发送求购信息
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	public static boolean sendBuyInfoMail(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		 try {
			    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				MailUtils.sendMail(request, 2, toUser, object, new Object[]{df.format(new Date()),url,content});
				return true;
			} catch (IOException e) {
				logger.error("求购邮件发送失败:"+e.getMessage());
				return false;
			}
	}
	
	/**
	 * 邮箱认证
	 * @param request
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 */
	public static boolean sendAuthenticationMail(ServletContext request,String toUser,String object,String url,String content){
		    try {
				MailUtils.sendMail(request, 3, toUser, object, new Object[]{url,content});
				return true;
			} catch (IOException e) {
				logger.error("邮箱认证邮件发送失败:"+e.getMessage());
				return false;
			}
	}
	public static boolean contactShop(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		 try {
			 if(toUser==null){
				 return false;
			 }else{
			    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				MailUtils.sendMail(request, 6, toUser, object, new Object[]{df.format(new Date()),url,content});
				return true;
			 }
			} catch (IOException e) {
				logger.error("联系商铺邮件发送失败:"+e.getMessage());
				return false;
			}
	}
	public static boolean contactShopMe(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			MailUtils.sendMail(request, 8, toUser, object, new Object[]{df.format(new Date()),url,content});
			return true;
		} catch (IOException e) {
			logger.error("商铺留言提醒邮件发送失败:"+e.getMessage());
			return false;
		}
	}
	/**
	 * 邮箱验证
	 * @param request
	 * @param toUser
	 * @param object
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static boolean sendEmailCaptcha(ServletContext request,String toUser,String object,String url,String content) throws IOException{
		try {
			MailUtils.sendMail(request,7, toUser, object, new Object[]{url,content});
			return true;
		} catch (IOException e) {
			logger.error("邮箱认证邮件发送失败:"+e.getMessage());
			return false;
		}
	}
}
