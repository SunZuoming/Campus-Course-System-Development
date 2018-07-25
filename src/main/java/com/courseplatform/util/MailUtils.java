package com.courseplatform.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;


//邮件发送工具类

public class MailUtils {
	/***
	 * 发邮件的方法
	 * @param to 邮件发给谁
	 * @param code 邮件的激活码
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String to,String code) throws Exception{
		//1.创建连接对象。连接到邮件服务器
		Properties props=new Properties();
		
		//QQ邮箱
      // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
		//props.setProperty("host",value);//发送的服务器地址
		// 开启debug调试，以便在控制台查看
		props.setProperty("mail.debug", "true"); 
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(props);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com","2947456141", "trrnbkqwqkqwdcfc");
	
		/*
		//本地服务器
		Session session=Session.getInstance(props,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("service@store.com","111");
			}
		});
		*/
		
		//byxfhiqfwvdedeab
		//System.out.println("SDASD"); 
		//2.创建邮件对象
		Message message=new MimeMessage(session);
		//2.1设置发件人
		//message.setFrom(new InternetAddress("service@store.com"));
		//2.1设置发件人QQ
	    message.setFrom(new InternetAddress("2947456141@qq.com"));
		//2.2设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3设置邮件的主题
		message.setSubject("来自课程网站的激活邮件");
		//2.4设置邮件的正文
		message.setContent("<h1>来自课程网站的激活邮件,邮件激活请点击以下连接：</h><h3><a href="
				+ "'http://localhost:8080/coursePlatform/teacher/ActiveEmail.do?userCode="+code+"'>http://localhost:8080/teacher/ActiveEmail.do?userCode="+code+""
						+ "</a></h3>","text/html;charset=UTF-8");
		//System.out.println("SDASD"); 
		//3.发送一封激活邮件
	    //Transport.send(message);
		
		//qq邮箱
	
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		
		//System.out.println("SDASD01"); 
		
	}
	
	//找回密码
	public static void searchSendMail(String to,String password) throws Exception{
		//1.创建连接对象。连接到邮件服务器
		Properties props=new Properties();
		
		//QQ邮箱
      // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
		//props.setProperty("host",value);//发送的服务器地址
		// 开启debug调试，以便在控制台查看
		props.setProperty("mail.debug", "true"); 
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(props);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com","2947456141", "trrnbkqwqkqwdcfc");
		
		
		/*
		//本地服务器
		Session session=Session.getInstance(props,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("service@store.com","111");
			}
		});
		*/
		//byxfhiqfwvdedeab
		//System.out.println("SDASD"); 
		//2.创建邮件对象
		Message message=new MimeMessage(session);
		//2.1设置发件人
		//message.setFrom(new InternetAddress("service@store.com"));
		//2.1设置发件人QQ
	    message.setFrom(new InternetAddress("2947456141@qq.com"));
		//2.2设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3设置邮件的主题
		message.setSubject("来自校园课程通网站");
		//2.4设置邮件的正文
		message.setContent("<h1>您的密码：</h><h3>"+password+""
						+ "</h3>","text/html;charset=UTF-8");
		//System.out.println("SDASD"); 
		//3.发送一封激活邮件
	    //Transport.send(message);
		
		//qq邮箱
		
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		
		System.out.println("SDASD02"); 
		
	}
	
	//作业提醒
	public static void homeworkSendMail(String to) throws Exception{
		//1.创建连接对象。连接到邮件服务器
		Properties props=new Properties();
		
		//QQ邮箱
      // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
		//props.setProperty("host",value);//发送的服务器地址
		// 开启debug调试，以便在控制台查看
		props.setProperty("mail.debug", "true"); 
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(props);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com","2947456141", "trrnbkqwqkqwdcfc");
		
		
		/*
		//本地服务器
		Session session=Session.getInstance(props,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("service@store.com","111");
			}
		});
		*/
		//byxfhiqfwvdedeab
		//System.out.println("SDASD"); 
		//2.创建邮件对象
		Message message=new MimeMessage(session);
		//2.1设置发件人
		//message.setFrom(new InternetAddress("service@store.com"));
		//2.1设置发件人QQ
	    message.setFrom(new InternetAddress("2947456141@qq.com"));
		//2.2设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3设置邮件的主题
		message.setSubject("来自校园课程通网站");
		//2.4设置邮件的正文
		message.setContent("<h1>您选择的课程已发布新作业,请查看！</h>","text/html;charset=UTF-8");
		//System.out.println("SDASD"); 
		//3.发送一封激活邮件
	    //Transport.send(message);
		
		//qq邮箱
		
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		
		//System.out.println("SDASD02"); 
		
	}

 
}

