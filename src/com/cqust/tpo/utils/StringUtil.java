/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: package-info.java <br>
 * 包名: com.cqust.tpo.utils <br>
 * 创建时间: 2016年9月27日 上午9:08:03 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/** 
 * 类名: StringUtil <br>
 * 描述: 字符串处理工具类 <br>
 * 创建时间: 2016年9月27日 上午9:53:01 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class StringUtil{
	
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 判断是否为空或者“”
	 * true代表不为空或者“”
	 * </p>
	 * @param str 要检测的字符串
	 * @return 不是“”或者null。
	 */
	public static boolean isNotNullAndEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 判断是否为空或者“”
	 * true代表为空或者“”
	 * </p>
	 * @param str 要检测的字符串
	 * @return 是null或者“”  返回  true  ； 不是“”或者null  返回  false
	 */
	public static boolean isNullOrEmpty(String str){
		return (str == null || str.equals("")) ? true  :  false;
	}
	
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 将调度表信息打包成String形式返回。
	 * </p> 
	 * @param scheduler 调度表信息
	 * @return 字符串形式的调度表信息
	 */
	public static  String schedulerToString(Scheduler scheduler){
		if(scheduler == null){
			return "null";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("id:");
		try {
			scheduler.getSchedulerInstanceId();
		} catch (SchedulerException e) {
			sb.append("exception");
		}
		sb.append("    name:");
		try {
			sb.append(scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			sb.append("exception");
		}
		sb.append("    isStarted:");
		try{
			sb.append(scheduler.isStarted());
		}catch(SchedulerException e){
			sb.append("exception");
		}
		sb.append("    isShutdown:");
		try{
			sb.append(scheduler.isShutdown());
		}catch(SchedulerException e){
			sb.append("exception");
		}
		return sb.toString();
	}
}
