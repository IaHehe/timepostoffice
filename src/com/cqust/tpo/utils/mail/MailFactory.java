/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailFactory.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月28日 下午11:20:31 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

/** 
 * 类名: MailFactory <br>
 * 描述: 邮件工厂 .<br>
 * 主要任务：
 * 1.从文件中读取配置信息存入本实例
 * 2.保存一个属性集合
 * 3.创建一个带有文件配置信息的MailBaseInfo对象返回
 * 创建时间: 2016年9月28日 下午11:20:31 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailFactory {
//	* 1.从文件中读取配置信息存入本实例
//	* 2.保存一个属性集合
//	* 3.创建一个带有文件配置信息的MailBaseInfo对象返回
	/** 
	 * pros : 存储邮件基本配置信息
	 */
	Properties pros = new Properties();

	/**
	 * <b>方法作用 </b><br><p>
	 * 使用默认src/mail.properties文件信息加载。
	 * </p>
	 * @see #reload(File)
	 * @return 成功与否
	 */
	public boolean reload(){
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		File file = new File("mail.properties");
		return reload(file);
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 从给定的文件中获取数据，并保存到pros中
	 * </p>
	 * @param file 文件实例
	 * @return 成功与否。如果file为null或者说file不存在，或者触发了致命的异常，返回null
	 */
	public boolean reload(File file){
//		if(file == null){
//			System.out.println("---->" + file);
//			System.out.println("属性文件有问题，不存在");
//			return false;
//		}
//		Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")
//		if(file == null || !file.exists()){
//			System.out.println("---->" + file);
//			System.out.println("属性文件有问题，不存在");
//			return false;
//		}
//		Reader fileReader = null;
//		BufferedReader bufferedReader = null;
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
			return true;
		} catch (FileNotFoundException e1) {
			System.out.println("读取配置文件时有异常");
			e1.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println("读取配置文件时有异常");
			e.printStackTrace();
			return false;
		} finally {
			System.out.println(pros);
			System.out.println("读取mail配置文件完成。");
		}
	}
}
