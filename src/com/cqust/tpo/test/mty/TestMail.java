/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TestMail.java <br>
 * 包名: com.cqust.tpo.test.mty <br>
 * 创建时间: 2016年9月29日 上午10:17:59 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.test.mty;

import java.io.File;

import com.cqust.tpo.utils.mail.Mail;
import com.cqust.tpo.utils.mail.MailBaseInfo;
import com.cqust.tpo.utils.mail.MailFactory;
import com.cqust.tpo.utils.mail.MailFile;
import com.cqust.tpo.utils.mail.MailMessage;
import com.cqust.tpo.utils.mail.MailUtil;

/** 
 * 类名: TestMail <br>
 * 描述: 测试邮件封装 <br>
 * 创建时间: 2016年9月29日 上午10:17:59 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TestMail {
	public static void main(String[] args) {
//		MailUtil工具测试
		MailFile mailFile = new MailFile();
		mailFile.setFile(new File("src/com/cqust/tpo/utils/mail/90.jpg"));
		mailFile.setFileId("imss");
//		boolean flag = MailUtil.createTimeLetter("me","1842468951@qq.com",mailFile, "nnn","<div>akdjfkasfksk</div>   <img src=\'cid:imss\' />");
//		boolean flag = MailUtil.createTimeLetter("me","matengyu@sohu.com",mailFile, "nnn","<div>akdjfkasfksk</div>   <img src=\'cid:imss\' alt=\'ioio\' />");
		boolean flag = false;
		flag = MailUtil.createTimeLetter("me","1842468951@qq.com",null, "nnn333","kskfj");
		System.out.println(flag);
		flag = MailUtil.createTimeLetter("me","1842468951@qq.com",mailFile, "nnn444","");
		System.out.println(flag);
//		单步测试
//		handle();
		
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 
	 * </p>
	 */
	private static void handle() {
		
		MailFactory mailFactory = new MailFactory();
		mailFactory.reload();
		MailBaseInfo mailBaseInfo = new MailBaseInfo(mailFactory);
		System.out.println(mailBaseInfo.toString());
		
		MailMessage mailMessage = new MailMessage(true);
		
		
		Mail mail = new Mail();
		
		mail.setMailBasicInfo(mailBaseInfo);
		mail.setMailMessage(mailMessage);
		
//		初始化MailBaseInfo
//		mailBaseInfo.setTo("matengyu@sohu.com");
		mailBaseInfo.setTo("1842468951@qq.com");
//		初始化MailMessage
		MailFile mailFile = new MailFile();
		mailFile.setFile(new File("src/com/cqust/tpo/utils/mail/90.jpg"));
		mailFile.setFileId("imgmt");
		mailMessage.setContent("<h1>java  mail</h1>cid:imgmt",mailFile);
		
//		mailMessage.
		
		mailMessage.setSubject("生命在于运动，程序在于测试  5");
		
		mail.sendMail();		
	}
}
