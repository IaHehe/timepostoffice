/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailBaseInfo.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午2:07:27 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;

import com.cqust.tpo.utils.StringUtil;

/** 
 * 类名: MailBaseInfo <br>
 * 描述: 存储邮件的基本信息.<br>
 * 创建时间: 2016年9月29日 上午2:07:27 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailBaseInfo {
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 获取Session.
	 * 包含配置信息的Session
	 * </p>
	 * @return Session实例
	 */
	public Session getSession(){
		return Session.getDefaultInstance(
				getProperties(),new MyAuthenticator(username, password));
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 根据mailFactory.pros,找到必要的属性值，组装Properties，以供getSession使用。
	 * </p>
	 * @return Properties  属性集合实例
	 */
	private Properties getProperties() {
		if(mailFactory == null)
			return null;
		Properties pros = mailFactory.pros;
		
		Properties result = new Properties();
//		获得必要字段
//		host("mail.smtp.host"),
//		port("mail.smtp.port"),
//		auth("mail.smtp.auth"),
//		scoketFactoryClass("mail.smtp.socketFactory.class"),
//		scoketFactoryClassPort("mail.smtp.socketFactory.port"),
//		debug("debug"),
//		username("username"),
//		password("password")
//		方法：传入MailConstant,properties,result,方法内从properties获取值而后添加到result中
		putToResult(MailConstant.host, pros, result);
		putToResult(MailConstant.port,  pros, result);
		putToResult(MailConstant.auth, pros, result);
		
		return result;
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 将MailConstant存放入结果集
	 * </p>
	 * @param mailconstant 实例
	 * @param props 数据源
	 * @param result 结果集保存
	 */
	private void putToResult(MailConstant mailconstant, Properties props, Properties result){
		String name = mailconstant.getName();
		String propertyValue = props.getProperty(mailconstant.getName());
		if(StringUtil.isNotNullAndEmpty(name) && StringUtil.isNotNullAndEmpty(propertyValue))
			result.put(name,propertyValue);
		else{
			System.out.println("putToResult  warn");
		}
	}
			
	/** 
	 * mailFactory : 邮件基本信息读取工具
	 */
	private MailFactory mailFactory = null;
	

	/** 
	 * host : 主机名，发送方服务器的主机名
	 */
	private String host;
	/** 
	 * port : 通信使用的端口号，服务器端
	 */
	private Integer port = 25;
	
	/** 
	 * auth : 认证与否，都要认证。
	 */
	private Boolean auth = true;
	
	/** 
	 * socketFactoryClass : socket工厂类。
	 */
	private String socketFactoryClass;
	
	/** 
	 * debug : 是否使用调试模式。默认不启用
	 */
	private Boolean debug = false;
	
	/** 
	 * username : 发送方用户名，账户名
	 */
	private String username;
	/** 
	 * password : 发送方密码
	 */
	private String password;
	
	/** 
	 * from : 邮件的发送方账号
	 */
	private String from ;
	
	/** 
	 * fromname : 邮件发送者姓名
	 */
	private String fromname;
	
	/** 
	 * to : 邮件的接收方信息
	 */
	private List<String> to;
	
	/** 
	 * cc : 邮件的抄送地址
	 */
	private List<String> cc;
	/** 
	 * bcc : 邮件的密送地址
	 */
	private List<String> bcc;
	
	/**
	 * <b>工作原理</b><br><p>
	 * 无参构造方法。
	 * </p> 
	 */
	public MailBaseInfo(){
	}
	/**
	 * <b>工作原理</b><br><p>
	 * 通过给定的邮件工厂建立一个Mail对象，也就是初始化各个字段信息
	 * </p>
	 * @param mailFactory 
	 */
	public MailBaseInfo(MailFactory mailFactory){
		setMailFactory(mailFactory);
	}
	
	
	
	

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "MailBaseInfo [mailFactory=" + mailFactory + ", host=" + host + ", port=" + port + ", auth=" + auth
				+ ", socketFactoryClass=" + socketFactoryClass + ", debug=" + debug + ", username=" + username
				+ ", password=" + password + ", from=" + from + ", fromname=" + fromname + ", to=" + to + ", cc=" + cc
				+ ", bcc=" + bcc + "]";
	}

	//	==============================================以下是=Getter/Setter    
	/**
	 * @return 获取  mailFactory 值/对象
	 */
	public MailFactory getMailFactory() {
		return mailFactory;
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 设置MailFactory工厂
	 * </p>
	 * @param mailFactory 邮件工厂
	 */
	public void setMailFactory(MailFactory mailFactory){
		this.mailFactory = mailFactory;
//		把邮件工厂内的数据填充到本实例中。
		String tmp = null;
		Properties props = mailFactory.pros;
		if(props == null || props.size() <= 0){
			System.out.println("mailFatctory props  is  null");
			return;
		}
		host = props.getProperty(MailConstant.host.getName());
		
		tmp = props.getProperty(MailConstant.port.getName());
		try {
			port = Integer.parseInt(tmp);
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		
		tmp = props.getProperty(MailConstant.auth.getName());
		try {
			auth = Boolean.parseBoolean(tmp);
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		
		socketFactoryClass = props.getProperty(MailConstant.scoketFactoryClass.getName());
		
		tmp = props.getProperty(MailConstant.debug.getName());
		try {
			debug = Boolean.parseBoolean(tmp);
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		
		username = props.getProperty(MailConstant.username.getName());
		password = props.getProperty(MailConstant.password.getName());
	
		from = props.getProperty(MailConstant.from.getName());
		fromname = props.getProperty(MailConstant.fromname.getName());
	}
	/**
	 * @return 获取  host 值/对象
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @return 获取  port 值/对象
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @return 获取  auth 值/对象
	 */
	public Boolean getAuth() {
		return auth;
	}
	/**
	 * @return 获取  socketFactoryClass 值/对象
	 */
	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}
	/**
	 * @return 获取  debug 值/对象
	 */
	public Boolean getDebug() {
		return debug;
	}
	/**
	 * @return 获取  username 值/对象
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return 获取  password 值/对象
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return 获取  from 值/对象
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @return 获取  to 值/对象
	 */
	public List<String> getTo() {
		return to;
	}
	/**
	 * @return 获取  cc 值/对象
	 */
	public List<String> getCc() {
		return cc;
	}
	/**
	 * @return 获取  bcc 值/对象
	 */
	public List<String> getBcc() {
		return bcc;
	}
	/**
	 * @param host 设置 host 值/对象
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @param port 设置 port 值/对象
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * @param auth 设置 auth 值/对象
	 */
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}
	/**
	 * @param socketFactoryClass 设置 socketFactoryClass 值/对象
	 */
	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
	}
	/**
	 * @param debug 设置 debug 值/对象
	 */
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	/**
	 * @param username 设置 username 值/对象
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password 设置 password 值/对象
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param from 设置 from 值/对象
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @param to 设置 to 值/对象
	 */
	public void setTo(List<String> to) {
		this.to = to;
	}
	/**
	 * @param cc 设置 cc 值/对象
	 */
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	/**
	 * @param bcc 设置 bcc 值/对象
	 */
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 添加一个收件人
	 * </p>
	 * @param string 邮箱地址
	 */
	public void setTo(String mailAddress) {
		to = new ArrayList<String>();
		to.add(mailAddress);		
	}

	/**
	 * @return 获取  fromname 值/对象
	 */
	public String getFromname() {
		return fromname;
	}

	/**
	 * @param fromname 设置 fromname 值/对象
	 */
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	
	
}
