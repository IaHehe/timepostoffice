/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailUtil.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月27日 下午10:45:46 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;


import com.cqust.tpo.utils.StringUtil;

/** 
 * 类名: MailUtil <br>
 * 描述: 邮件工具包.<br>
 * 创建时间: 2016年9月27日 下午10:45:46 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailUtil {

	/**
	 * <b>方法作用 </b><br><p>
	 * 创建邮件并发送（时光信使用）.<br>
	 * 创建一封邮件，而后发送出去，使用给定的邮件信息。
	 * 规则：
	 * 内容不可以为null,或者"",直接返回false
	 * </p>
	 * @param sign_prefix 邮箱发送地址前面那个name，可能是设置的默认值，也可能是自己的邮箱地址
	 * @param to 收信地址email格式
	 * @param mailFile  页面图片，html格式的
	 * @param subject 邮件主题
	 * @param content 邮件正文内容
	 * @return 是否成功发送。只要能发出去就是成功。可能对方邮件服务器将邮件作为垃圾邮件隔离或者解析问题显示图片部分不友好。
	 */
	public static boolean createTimeLetter(String sign_prefix, String to, MailFile mailFile, String subject, String content  ){
		if(StringUtil.isNullOrEmpty(sign_prefix)
				|| StringUtil.isNullOrEmpty(to)
				|| StringUtil.isNullOrEmpty(subject)
				|| StringUtil.isNullOrEmpty(content)){
			return false;
		}
		try {
			MailFactory mailFactory = new MailFactory();
			mailFactory.reload();
			MailBaseInfo mailBaseInfo = new MailBaseInfo(mailFactory);
//			System.out.println(mailBaseInfo.toString());
			
			MailMessage mailMessage = null;
			if(mailFile == null || mailFile.getFileId() == null 
					|| "".equals(mailFile.getFileId()) 
					|| mailFile.getFile() == null || !mailFile.getFile().exists()){
				
				mailMessage = new MailMessage(false);
			}else{
				mailMessage = new MailMessage(true);
			}
			
			mailBaseInfo.setFromname(sign_prefix);
			Mail mail = new Mail();
			
			mail.setMailBasicInfo(mailBaseInfo);
			mail.setMailMessage(mailMessage);
			
//		初始化MailBaseInfo
//		mailBaseInfo.setTo("matengyu@sohu.com");
			mailBaseInfo.setTo(to);
//		初始化MailMessage
			mailMessage.setContent(content,mailFile);
			
//		mailMessage.
			
			mailMessage.setSubject(subject);
			
			mail.sendMail();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
