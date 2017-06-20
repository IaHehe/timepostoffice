/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: QuartzConstant.java <br>
 * 包名: com.cqust.tpo.utils.quartz <br>
 * 创建时间: 2016年9月27日 下午3:03:31 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.quartz;

/** 
 * 类名: QuartzConstant <br>
 * 描述: Quartz常量枚举类型. <br>
 * 包含触发器组名，触发器名字前缀，任务组名，任务名字前缀
 * 创建时间: 2016年9月27日 下午3:03:31 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public enum QuartzConstant {

	TRIGGER_GROUP_NAME("MAIL_TP"),
	TRIGGER_NAME_PREFIX("TNO_"),
	JOB_GROUP_NAME("MAIL_TP"),
	JOB_NAME_PREFIX("JNO_"),
	JOB_DETAIL_NAME("MAIL_JOB"),//邮件名字，存储在JobDetail
	JOB_DETAIL_GROUP_NAME("MAIL_GROUP"),//邮件任务所属组，存储在JobDetail
	JOB_DATA_TRIGGER_ID("JOB_ID");//触发器保存数据供任务执行时使用。时光信id，邮件任务的ID
	
	private String name;
	
	/**
	 * <b>工作原理</b><br><p>
	 * 枚举类型构造方法.
	 * </p>
	 * @param name 枚举类型值
	 */
	private QuartzConstant(String name){
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
