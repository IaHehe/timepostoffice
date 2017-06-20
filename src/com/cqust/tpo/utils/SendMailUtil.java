package com.cqust.tpo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送一封普通邮件
 * 
 * @author 邹东军
 *
 */
public class SendMailUtil {

	public static void MailUtil(String to,String sessionId) throws MessagingException, UnsupportedEncodingException {// to
																									// 表示收件人电子邮箱
		// 发件人电子邮箱
		String from = "mty2015@126.com";

		// 指定发送邮件的主机为 smtp.qq.com
		String host = "smtp.126.com"; // 邮件服务器

		// 设置端口号
		int port = 25;

		// 用户密码认证
		String auth = "true";
		// 获取系统属性
		Properties prop = System.getProperties();

		prop.put("mail.transport.protocol", "smtp"); // 指定协议
		prop.put("mail.smtp.host", host); // 主机
		prop.put("mail.smtp.port", port); // 端口
		prop.put("mail.smtp.auth", auth); // 用户密码认证
		// prop.put("mail.debug", "true"); // 调试模式

		// SSL加密方式 --- QQ邮件服务器的端口号为 465
//		prop.setProperty("mail.smtp.ssl.enable", "true");

		// 1. 创建一个邮件的会话
		Session session = Session.getInstance(prop);
		// 2. 创建邮件体对象 (整封邮件对象)
		MimeMessage message = new MimeMessage(session);
		// 3. 设置邮件体参数:
		// 3.1 标题
		message.setSubject("时光邮局  注册验证验证邮件");
		// 3.2 邮件发送时间
		message.setSentDate(new Date());
		// 3.3 发件人
		message.setFrom(new InternetAddress(from, "时光邮局"));
		// 3.4 接收人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		// 3.5内容
		// message.setText("你好，已经发送成功！ 正文...."); // 简单纯文本邮件
		// 邮件中含有超链接
		// message.setText("<a href='#'>百度</a>");
		String txt = "<a href='http://localhost:8080/timepostoffice/user/vlidate?sid="+sessionId+"'>时光邮局注册验证链接</a>";
		System.out.println("SendMailUtil----邮件回调地址: "+txt);
		message.setContent(txt,"text/html;charset=UTF-8");

		message.saveChanges(); // 保存邮件(可选)

		// 4. 发送
		Transport trans = session.getTransport("smtp");
		trans.connect(host, from, "lingxiu126$");//最后一个为客户端授权码
		// 发送邮件
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}
	
	public static void main(String[] args) {
		try {
			MailUtil("jundongzou@163.com", "0dfd2fdfweg222255h56e5g5e5g2s3d3f6hf9");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
