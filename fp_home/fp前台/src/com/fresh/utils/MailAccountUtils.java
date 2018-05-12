package com.fresh.utils;

import java.io.IOException;
import java.text.MessageFormat;


import org.apache.struts2.ServletActionContext;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;
import jodd.util.MimeTypes;

public class MailAccountUtils {
	
	private static String sendHTMLDataRegis = "<html><body><div style=\"padding: 20px 30px;font-size: 14px;\"> <h2>Welcome to the Fresh Port</h2> <br><br><br>"
			+"Dear User, <br><br><br>The verification code is [{0}]<br><br><br>In order to protect your account, please fill in the verification code in 30 minutes."
			+"If it is not your action, please ignore this message. <br><br><br>This message is issued by the system automatically, no need to reply</body></html>";
	
	/**
	 * 发送邮件
	 * @param toUser
	 * @param object
	 * @param content
	 * @return
	 * @throws IOException 
	 */
	public static String sendMail(String receptEmail,Integer typeInfo,String subjectInfo,String valueInfo){
		String emailName = SysConfigUtils.getConfigValue(ServletActionContext.getServletContext(),"emailUrl");
		String emailPwd = SysConfigUtils.getConfigValue(ServletActionContext.getServletContext(),"emailPwd");
		String SmtpServerStr = SysConfigUtils.getConfigValue(ServletActionContext.getServletContext(),"SmtpServerStr");
		
		String contentStr = "";
		
		switch(typeInfo){
			case 0:contentStr = MessageFormat.format(sendHTMLDataRegis,valueInfo);break;
			case 1:contentStr = MessageFormat.format(sendHTMLDataRegis,valueInfo);break;
		}
		
		Email email = Email.create().from(emailName).to(receptEmail).subject(subjectInfo);
		EmailMessage htmlMessage = new EmailMessage(contentStr, MimeTypes.MIME_TEXT_HTML);
		email.addMessage(htmlMessage);
		SmtpServer smtpServer = new SmtpServer(SmtpServerStr,new SimpleAuthenticator(emailName,emailPwd));
		SendMailSession session = smtpServer.createSession();
		try{
			session.open();
		    session.sendMail(email);
		    session.close();
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			return "1";
		}
	    
	}
}
