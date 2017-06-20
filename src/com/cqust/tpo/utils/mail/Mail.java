/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: Mail.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午3:37:36 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/** 
 * 类名: Mail <br>
 * 描述: 邮件类 <br>
 * 创建时间: 2016年9月29日 上午3:37:36 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class Mail {

	/** 
	 * mailBasicInfo : 基本邮件信息
	 */
	private MailBaseInfo mailBasicInfo = null;
	
	/** 
	 * mailMessage : 保存邮件主要信息
	 */
	private MailMessage mailMessage = null;


	/**
	 * <b>方法作用 </b><br><p>
	 * 发送邮件。
	 * </p>
	 * @return
	 */
	public boolean sendMail(){
		MimeMessage mimeMessage = makeMail();
		if(mimeMessage == null){
			System.out.println("邮件有问题，未发送");
			return false;
		}
		try {
//			System.out.println("type:" + mimeMessage.getContentType());
				Transport.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("发送邮件失败，MSG:MessagingExcepiton-->" + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("发送邮件失败，MSG:" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 组装邮件,以供send调用
	 * </p>
	 */
	private MimeMessage makeMail(){
		MimeMessage mail = new MimeMessage(mailBasicInfo.getSession());
		try {
			String from = mailBasicInfo.getFrom();
			String fromname = mailBasicInfo.getFromname();
	        try {  
	        	fromname=MimeUtility.encodeText(fromname);  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }   
//			mail.setFrom(new InternetAddress(from));
			mail.setFrom(new InternetAddress(fromname+" <"+from+">"));
			List<String> to = mailBasicInfo.getTo();
			mail.setRecipients(RecipientType.TO, convertToInternetAddresses(to));
			List<String> cc = mailBasicInfo.getCc();
			if(cc!= null && cc.size() > 0){
				mail.setRecipients(RecipientType.CC, convertToInternetAddresses(cc));
			}
			List<String> bcc = mailBasicInfo.getBcc();
			if(bcc!= null && bcc.size() >0){
				mail.setRecipients(RecipientType.BCC, convertToInternetAddresses(bcc));
			}
			mail.setSubject(mailMessage.getSubject());
			mail.setContent(mailMessage.updateAndReturn());
			
//			mail.setFrom(new InternetAddress("店小二"+" <"+from+">"));  
			
			return mail;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 将邮件地址字符串格式  转化为网络地址  格式
	 * </p>
	 * 如果有地址不合规范或者list为空，返回null
	 * @param list 邮件地址集合
	 * @return 网络地址数组
	 */
	public InternetAddress[] convertToInternetAddresses(List<String> list){
		if(list==null)
			return null;
		if(list.size() == 0)
			return null;
		InternetAddress[] internetAddress = new InternetAddress[list.size()];
		int size = list.size();
		for (int i = 0; i < size; i++) {
			try {
				internetAddress[i] = new InternetAddress(list.get(i));
			} catch (AddressException e) {
				e.printStackTrace();
				System.out.println("地址        " + list.get(i) + "      解析有异常");
				return null;
			}
		}
		return internetAddress;
	}
	
	/**
	 * <b>工作原理</b><br><p>
	 * 无参构造方法
	 * </p> 
	 */
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return 获取  mailBasicInfo 值/对象
	 */
	public MailBaseInfo getMailBasicInfo() {
		return mailBasicInfo;
	}

	/**
	 * @return 获取  mailMessage 值/对象
	 */
	public MailMessage getMailMessage() {
		return mailMessage;
	}

	/**
	 * @param mailMessage 设置 mailMessage 值/对象
	 */
	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	/**
	 * @param mailBasicInfo 设置 mailBasicInfo 值/对象
	 */
	public void setMailBasicInfo(MailBaseInfo mailBasicInfo) {
		this.mailBasicInfo = mailBasicInfo;
	}
	
	
}
