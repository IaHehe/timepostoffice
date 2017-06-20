package com.cqust.tpo.models;

import java.util.Date;

/**
 * 纸信邮寄指南实体对象
 * 
 * @author 杨秀仕
 * @date 2016/09/26
 */

public class PostGuide {
	private int postGuideId; // 指南id
	private String postGuideTitle; // 指南标题
	private String postGuideContent; // 指南内容
	private Date postGuideTime; // 指南创建时间

	public PostGuide() {

	}

	public PostGuide(int postGuideId, String postGuideTitle, String postGuideContent, Date postGuideTime) {
		this.postGuideId = postGuideId;
		this.postGuideTitle = postGuideTitle;
		this.postGuideContent = postGuideContent;
		this.postGuideTime = postGuideTime;
	}

	public int getPostGuideId() {
		return postGuideId;
	}

	public void setPostGuideId(int postGuideId) {
		this.postGuideId = postGuideId;
	}

	public String getPostGuideTitle() {
		return postGuideTitle;
	}

	public void setPostGuideTitle(String postGuideTitle) {
		this.postGuideTitle = postGuideTitle;
	}

	public String getPostGuideContent() {
		return postGuideContent;
	}

	public void setPostGuideContent(String postGuideContent) {
		this.postGuideContent = postGuideContent;
	}

	public Date getPostGuideTime() {
		return postGuideTime;
	}

	public void setPostGuideTime(Date postGuideTime) {
		this.postGuideTime = postGuideTime;
	}

	@Override
	public String toString() {
		return "PostGuide [postGuideId=" + postGuideId + ", postGuideTitle=" + postGuideTitle + ", postGuideContent="
				+ postGuideContent + ", postGuideTime=" + postGuideTime + "]";
	}
}
