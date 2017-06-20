/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailConstant.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午12:54:25 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

/** 
 * 类名: MailConstant <br>
 * 描述: Mail常量区. <br>
 * 存储邮件数据的元数据
 * 在将信息配置到JavaMail中使用
 * 创建时间: 2016年9月29日 上午12:54:25 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public enum MailConstant {

//	map.put("mail.smtp.host", "smtp.sina.com");
//    map.put("", "true");
//    map.put("", "javax.net.ssl.SSLSocketFactory");
//    map.put("mail.smtp.port", "465");
//    map.put("mail.smtp.socketFactory.port", "465");
	host("mail.smtp.host"),
	port("mail.smtp.port"),
	auth("mail.smtp.auth"),
	scoketFactoryClass("mail.smtp.socketFactory.class"),
	scoketFactoryClassPort("mail.smtp.socketFactory.port"),
	debug("debug"),
	username("username"),
	password("password"),
	from("from"),
	fromname("fromname")
	;
	
	
	
	/** 
	 * name : 保存值
	 */
	private String name;
	
	/**
	 * <b>工作原理</b><br><p>
	 * 枚举类型构造方法.
	 * </p>
	 * @param name 枚举类型值
	 */
	private MailConstant(String name){
		this.name = name;
	}

	/**
	 * @return 获取  name 值/对象
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 设置 name 值/对象
	 */
	public void setName(String name) {
		this.name = name;
	}
}
