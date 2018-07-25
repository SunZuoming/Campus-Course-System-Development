package com.courseplatform.service;

public interface SendEmailService {

	/** 
     * @Description:普通文本发邮件形式 
     * @param subject 主题 
     * @param content 正文 
     * @param toMail 收件人邮箱 
     */  
	public void sendSimpleMail(String subject,String content,String toMail);
	
	/** 
     * @Description:html发邮件形式 
     * @param subject 主题 
     * @param content 正文 
     * @param toMail 收件人邮箱 
     */  
	public void sendHtmlMail(String subject,String content,String toMail);
	
	/** 
     * @Description: 带图片发邮件形式 
     * @param subject 主题 
     * @param content 正文 
     * @param toMail 收件人邮箱 
     * @param picturePath 图片路径 
     */  
	public void sendPictureMail(String subject,String content,String toMail,String picturePath);
	
	
	/** 
     * @Description:带附件发邮件形式 
     * @param subject 标题 
     * @param content 正文 
     * @param toMail 收件人邮箱 
     * @param accessoryPath 附件路径 
     * @param accessoryName 附件名 
     */  
	public void sendMailTakeAccessory(String subject,String content,String toMail,String accessoryPath,String accessoryName);
	
}
