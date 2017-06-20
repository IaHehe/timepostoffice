package com.cqust.tpo.models;

import java.util.Date;

/**
 * 留言板实体对象
 * @author 张祥婷
 * @date 2016/09/26
 */
public class LetterMessageBoard {
	private int messsageId; // 时光信留言板id
	private String messageContent; // 时光信留言内容
	private Date messageCreateTime; // 时光信留言时间
	private String messageRemarks; // 时光信留言备注
	
	private User user;
	private TimeLetter timeletter;

	/**
	 * @return 获取  user 值/对象
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return 获取  timeletter 值/对象
	 */
	public TimeLetter getTimeletter() {
		return timeletter;
	}

	/**
	 * @param user 设置 user 值/对象
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param timeletter 设置 timeletter 值/对象
	 */
	public void setTimeletter(TimeLetter timeletter) {
		this.timeletter = timeletter;
	}

	public LetterMessageBoard() {
		super();
	}

	public LetterMessageBoard(String messageContent, Date messageCreateTime, String messageRemarks) {
		this.messageContent = messageContent;
		this.messageCreateTime = messageCreateTime;
		this.messageRemarks = messageRemarks;
	}

	public int getMesssageId() {
		return messsageId;
	}

	public void setMesssageId(int messsageId) {
		this.messsageId = messsageId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getMessageCreateTime() {
		return messageCreateTime;
	}

	public void setMessageCreateTime(Date messageCreateTime) {
		this.messageCreateTime = messageCreateTime;
	}

	public String getMessageRemarks() {
		return messageRemarks;
	}

	public void setMessageRemarks(String messageRemarks) {
		this.messageRemarks = messageRemarks;
	}

	@Override
	public String toString() {
		return "LetterMessageBoard [messsageId=" + messsageId + ", messageContent=" + messageContent
				+ ", messageCreateTime=" + messageCreateTime + ", messageRemarks=" + messageRemarks + "]";
	}

}
