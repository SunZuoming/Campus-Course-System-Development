package com.courseplatform.service.impl;

import java.io.File;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.courseplatform.service.SendEmailService;

@Service
public class SendEmailServiceImpl implements SendEmailService {
	
	private static Log log = LogFactory.getLog(SendEmailServiceImpl.class);
	
	@Resource 
    JavaMailSender mailSender;  
    @Resource  
    SimpleMailMessage simpleMailMessage;  

	 
    public void sendSimpleMail(String subject,String content,String toMail) {  
        simpleMailMessage.setSubject(subject);  
        simpleMailMessage.setText(content);  
        simpleMailMessage.setTo(toMail);  
        mailSender.send(simpleMailMessage);  
        log.info("邮件发送成功..");   
    }  
      
    
    public void sendHtmlMail(String subject,String content,String toMail) {  
        MimeMessage mailMessage = mailSender.createMimeMessage();   
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);   
        try {  
            messageHelper.setTo(toMail);  
            messageHelper.setSubject(subject);   
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);  
            mailSender.send(mailMessage);   
            log.info("邮件发送成功..");   
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }   
    }  
      
    
    public void sendPictureMail(String subject,String content,String toMail,String picturePath){  
        MimeMessage mailMessage = mailSender.createMimeMessage();   
        MimeMessageHelper messageHelper;  
        try {  
            messageHelper = new MimeMessageHelper(mailMessage,true);  
            messageHelper.setTo(toMail);  
            messageHelper.setSubject(subject);   
            //messageHelper.setText("<html><head></head><body><h1>" + content +"</h1><img src=/"+cid:aaa + /"/></body></html>",true);   
            FileSystemResource img = new FileSystemResource(new File(picturePath));   
            messageHelper.addInline("aaa",img);   
            //发送邮件   
            mailSender.send(mailMessage);  
            log.info("邮件发送成功..");   
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }   
    }  
      
   
    public void sendMailTakeAccessory(String subject,String content,String toMail,String accessoryPath,String accessoryName){  
        MimeMessage mailMessage = mailSender.createMimeMessage();   
        MimeMessageHelper messageHelper;  
        try {  
            messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");  
            messageHelper.setTo(toMail);  
            messageHelper.setSubject(subject);   
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);   
            FileSystemResource file = new FileSystemResource(new File(accessoryPath));   
            messageHelper.addAttachment(accessoryName,file);  
            mailSender.send(mailMessage);   
            log.info("邮件发送成功..");   
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }   
      
    }  
}
