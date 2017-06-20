package com.cqust.tpo.models;

import java.util.Date;

/**
 * 帮助信息实体对象
 * 
 * @author 王爽
 * @date 2016/9/26
 */
public class Assistant {
	private int assistantId;// 帮助信息id
	private String assistantTitle; // 帮助信息标题
	private String assistantContent; // 帮助信息内容
	private Date assistantTime; // 帮助信息创建时间

	public Assistant() {
		super();
	}

	public Assistant(String assistantTitle, String assistantContent, Date assistantTime) {
		this.assistantTitle = assistantTitle;
		this.assistantContent = assistantContent;
		this.assistantTime = assistantTime;
	}

	public int getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(int assistantId) {
		this.assistantId = assistantId;
	}

	public String getAssistantTitle() {
		return assistantTitle;
	}

	public void setAssistantTitle(String assistantTitle) {
		this.assistantTitle = assistantTitle;
	}

	public String getAssistantContent() {
		return assistantContent;
	}

	public void setAssistantContent(String assistantContent) {
		this.assistantContent = assistantContent;
	}

	public Date getAssistantTime() {
		return assistantTime;
	}

	public void setAssistantTime(Date assistantTime) {
		this.assistantTime = assistantTime;
	}

	@Override
	public String toString() {
		return "Assistant [assistantId=" + assistantId + ", assistantTitle=" + assistantTitle + ", assistantContent="
				+ assistantContent + ", assistantTime=" + assistantTime + "]";
	}

}
