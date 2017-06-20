/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailMessage.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午3:09:24 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


/** 
 * 类名: MailMessage <br>
 * 描述: 保存邮件的最重要的信息. <br>
 * 包括邮件主题，邮件正文，以及附件。
 * 创建时间: 2016年9月29日 上午3:09:24 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailMessage {

	/** 
	 * subject : 邮件的主题 
	 */
	private String subject;

	/** 
	 * content : 邮件的正文部分 
	 */
	private String content;

	/** 
	 * attachments : 邮件文件的集合.
	 */
	private List<MailFile> attachments = null;

	/** 
	 * mimeMultipart : mime类型的多部分邮件
	 */
	private MimeMultipart mimeMultipart = new MimeMultipart("mixed");

	/** 
	 * mailSchedume : 邮件模式，是时光信还是验证 
	 */
	private MailSchedema mailSchedume = MailSchedema.TimeLetterType;
	
	
	/**
	 * <b>工作原理</b><br><p>
	 * 网页格式有附件时，使用true，没有附件时用false
	 * </p>
	 * @param related  是否使用related方式联系
	 */
	public MailMessage(boolean related){
//		if(!related)
//			mimeMultipart = new MimeMultipart ();
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 添加一个邮件文件到邮件文件集合
	 * </p>
	 * @param mailFile 邮件文件
	 * @return 是否成功添加到集合中。
	 */
	private boolean addMailFile(MailFile mailFile){
		if(attachments == null)
			attachments = new ArrayList<MailFile>();
		if(mailFile == null)
			return false;
		attachments.add(mailFile);
		return true;
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 处理list<MailFile>，邮件文件集合。
	 * 将这些附件添加到MimeMulpart中。
	 * </p>
	 * @see #handleAttachFile(boolean)
	 */
	public void handleAttachFile(){
		handleAttachFile(true);
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 处理list<MailFile>，邮件文件集合。
	 * 将这些附件添加到MimeMulpart中。
	 * </p>
	 * @param related 是否链接图片。为true时，链接；为false时，不链接。默认链接，true
	 */
	public void handleAttachFile(boolean related){
		if(attachments == null || attachments.size() <= 0)
			return ;
		if(!related)
			mimeMultipart = new MimeMultipart();
		for(MailFile mf : attachments){
			BodyPart attachBodyPart = new MimeBodyPart();// 普通附件  
			FileDataSource fds = new FileDataSource(mf.getFile());  
			try {
				attachBodyPart.setDataHandler(new DataHandler(fds));
				attachBodyPart.setFileName("=?GBK?B?"  
						+ new sun.misc.BASE64Encoder().encode(fds.getName().getBytes())  
						+ "?=");// 解决附件名中文乱码  
				mimeMultipart.addBodyPart(attachBodyPart);
			} catch (MessagingException e) {
				e.printStackTrace();
			}  
		}
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 根据现有信息组装MimeMultipart并返回
	 * </p> 
	 * @param related 是否链接图片  为true时，链接；为false时，不链接。默认链接，true
	 * @return 邮件对象
	 */
	public MimeMultipart updateAndReturn(boolean related){
		handleAttachFile(related);
		return mimeMultipart;
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 根据现有信息组装MimeMultipart并返回
	 * </p> 
	 * @return 邮件对象
	 */
	public MimeMultipart updateAndReturn(){
		return updateAndReturn(true);
	}
	
	
	
	/**
	 * @return 获取  subject 值/对象
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return 获取  content 值/对象
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return 获取  list 值/对象
	 */
	public List<MailFile> getAttachments() {
		return attachments;
	}

	/**
	 * @param subject 设置 subject 值/对象
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 设置正文文本内容
	 * @param content 文本内容
	 */
	public void setContent(String content) {
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		try {
			mimeBodyPart.setContent(content, "text/html;charset=utf-8");
			mimeMultipart.addBodyPart(mimeBodyPart);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 设置正文文本内容，将图片连接到网页上
	 * @param content 文本内容
	 * @param mailFile 邮件文件对象
	 */
	public void setContent(String content, MailFile mailFile) {
		
//		MimeBodyPart imgBodyPart = new MimeBodyPart(); // 附件图标
//		imgBodyPart.setDataHandler(new DataHandler(fileds));
//		imgBodyPart.setFileName("button.gif");
//		imgBodyPart.setHeader("Content-ID", "<IMG1></IMG1>");// 在html中使用该图片方法src="cid:IMG1"
//		MimeBodyPart mimeBodyPart = new MimeBodyPart();
//		try {
//			mimeBodyPart.setContent(content, "text/html;charset=utf-8");
//			if(mailFile == null || !mailFile.getFile().exists())
//				return;
//			MimeBodyPart messageBodyPart = new MimeBodyPart();
//		byte[] bytes = readFile(mailFile.getFile());
//		ByteArrayDataSource fileds = new ByteArrayDataSource(bytes,
//				"application/octet-stream");
//			messageBodyPart.setDataHandler(new DataHandler(fileds));
//			messageBodyPart.setFileName(mailFile.getFile().getName());
//			messageBodyPart.setHeader("Content-ID", mailFile.getFileId());
//			
//			mimeMultipart.addBodyPart(mimeBodyPart);
//			mimeMultipart.addBodyPart(messageBodyPart);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		try {
			mimeBodyPart.setContent(content, "text/html;charset=utf8");
			mimeMultipart.addBodyPart(mimeBodyPart);
			if(mailFile == null || !mailFile.getFile().exists())
				return;
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(mailFile.getFile());
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setFileName(mailFile.getFile().getName());
			messageBodyPart.setHeader("Content-ID", mailFile.getFileId());
//			messageBodyPart.setHeader("Content-ID", wrap(mailFile.getFileId()));
			
			mimeMultipart.addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * <b>方法作用 </b><br><p>
	 * 把传入的文本包装成标签格式
	 * </p>
	 * 例：
	 * 输入：IMG1
	 * 输出：<IMG1></IMG1>
	 * @param fileId 文本
	 * @return 标签格式的字符串
	 */
	private String wrap(String fileId) {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(fileId).append("></").append(fileId).append(">");
		return sb.toString();
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 参考#readFile(File)
	 * </p>
	 * @see #readFile(File)
	 * @param file 文件对象
	 * @return 字节数组
	 */
	public static byte[] readFile(String file) {  
		return readFile(new File(file)) ;
	}
	/** 
     * 读取文件 
     *  
     * @param file 
     *            文件路径 
     * @return 返回二进制数组 
     */  
   	public static byte[] readFile(File file){
        FileInputStream fis = null;  
        ByteArrayOutputStream bos = null;  
        try {  
            fis = new FileInputStream(file);  
            bos = new ByteArrayOutputStream();  
            int bytesRead;  
            byte buffer[] = new byte[1024 * 1024];  
            while ((bytesRead = fis.read(buffer)) != -1) {  
                bos.write(buffer, 0, bytesRead);  
                Arrays.fill(buffer, (byte) 0);  
            }  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        } finally {  
            try {  
                if (bos != null)  
                    bos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return bos.toByteArray();  
    }  

	/**
	 * @param list 设置 list 值/对象
	 */
	public void setAttachments(List<MailFile> attachments) {
		this.attachments = attachments;
	}


}
