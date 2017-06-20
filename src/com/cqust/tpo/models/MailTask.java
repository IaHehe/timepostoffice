/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailTask.java <br>
 * 包名: com.cqust.tpo.models <br>
 * 创建时间: 2016年9月27日 下午2:06:23 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.models;

import java.io.Serializable;
import java.util.Date;

/** 
 * 类名: MailTask <br>
 * 描述: 邮件任务. <br>
 * 邮件任务，定时发送邮件任务。包含时光新的编号和发送时间。
 * 创建时间: 2016年9月27日 下午2:06:23 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailTask implements Serializable{
	/** 
	 * serialVersionUID :序列化和逆序列化。
	 */
	private static final long serialVersionUID = 4770460174726786720L;

	/** 
	 * timeLetterId : 时光信ID，对应于数据库的ID号。
	 */
	private Integer timeLetterId;

	/** 
	 * sendTime : 发送时间，Date类型。
	 */
	private Date sendTime;

	
	/**
	 * <b>工作原理</b><br><p>
	 * 无参构造方法.构建一个空的邮件任务。
	 * </p> 
	 */
	public MailTask() {
		super();
	}


	/**
	 * <b>工作原理</b><br><p>
	 * 根据传入的邮件ID和发送时间构建一个邮件任务
	 * </p> 
	 * @param timeLetterId 邮件ID
	 * @param sendTime  邮件发送时间
	 */
	public MailTask(Integer timeLetterId, Date sendTime) {
		super();
		this.timeLetterId = timeLetterId;
		this.sendTime = sendTime;
	}


	/** (non-Javadoc)
	 * 重载方法.<br>
	 * 获得当前实例的字符串形式信息.
	 * 将当前信息组装成字符串而后返回.
	 * @see java.lang.Object#toString()
	 * @return 包含当前邮件任务的字符串
	 */
	@Override
	public String toString() {
		return "MailTask [timeLetterId=" + timeLetterId + ", sendTime=" + sendTime + "]";
	}


	/**
	 * @return 获取  timeLetterId 值/对象
	 */
	public Integer getTimeLetterId() {
		return timeLetterId;
	}


	/**
	 * @return 获取  sendTime 值/对象
	 */
	public Date getSendTime() {
		return sendTime;
	}


	/**
	 * @param timeLetterId 设置 timeLetterId 值/对象
	 */
	public void setTimeLetterId(Integer timeLetterId) {
		this.timeLetterId = timeLetterId;
	}


	/**
	 * @param sendTime 设置 sendTime 值/对象
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
	
}
