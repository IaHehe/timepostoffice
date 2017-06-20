/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MyAuthenticator.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午4:54:11 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/** 
 * 类名: MyAuthenticator <br>
 * 描述: 验证器，身份认证 <br>
 * 创建时间: 2016年9月29日 上午4:54:11 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MyAuthenticator extends Authenticator {
	/**
	 * <b>工作原理</b><br/><p>
	 * 构造方法，传入用户名和密码
	 * </p> 
	 */
	public MyAuthenticator(String username, String password) {
		this.username = username;this.password = password;
	}
	
	/** 
	 * username : 用户名
	 */
	private String username;
	/** 
	 * password : 用户密码
	 */
	private String password;
	
	/** (non-Javadoc)
	 * 返回一个密码认证器。
	 * 覆盖了父类抽象的方法。
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 * @return
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
