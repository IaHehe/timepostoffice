/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TimeScheduling.java <br>
 * 包名: com.cqust.tpo.utils.quartz <br>
 * 创建时间: 2016年9月27日 上午9:12:11 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.quartz;

import org.quartz.Scheduler;

/** 
 * 类名: TimeScheduling <br>
 * 描述: 时间调度类. <br>
 * 设计为单例模式
 * 创建时间: 2016年9月27日 上午9:12:11 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TimeScheduling {

	/** 
	 * scheduler : 时间调度表
	 */
	private static Scheduler scheduler = null;
	
	/**
	 * @return 获取  scheduler 值/对象
	 */
	public static Scheduler getScheduler() {
		return scheduler;
	}

	/**
	 * 仅当scheduler为空时，才可以设置值，否则不可以设置
	 * 原因：为了保证在应用程序在运行时仅有一个Scheduler对象。
	 * @param scheduler 设置 scheduler 值/对象
	 */
	static void setScheduler(Scheduler scheduler) {
//		特别注意，应该使用类的变量判断空，而不是参数
		if(TimeScheduling.scheduler == null){
			TimeScheduling.scheduler = scheduler;
		}
	}
	
	
}
