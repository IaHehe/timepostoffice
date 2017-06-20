package com.cqust.tpo.models;

import java.util.Date;

/**表示用户所写的反馈信息
 * 宋光磊
 * 2016.9.25
 */ 
public class FeedBack {

	private int feedBackID;//反馈信息的ID，唯一对应
	private String feedBackContent;//反馈信息的内容
	private Date feedBackDate;//反馈信息的时间
	private String feedBackState;//反馈信息的状态，有已查看和未查看两种

	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedBack(int feedBackID, String feedBackContent, Date feedBackDate,
			String feedBackState) {
		super();
		this.feedBackID = feedBackID;
		this.feedBackContent = feedBackContent;
		this.feedBackDate = feedBackDate;
		this.feedBackState = feedBackState;
	}

	public int getFeedBackID() {
		return feedBackID;
	}

	public void setFeedBackID(int feedBackID) {
		this.feedBackID = feedBackID;
	}

	public String getFeedBackContent() {
		return feedBackContent;
	}

	public void setFeedBackContent(String feedBackContent) {
		this.feedBackContent = feedBackContent;
	}

	public Date getFeedBackDate() {
		return feedBackDate;
	}

	public void setFeedBackDate(Date feedBackDate) {
		this.feedBackDate = feedBackDate;
	}

	public String getFeedBackState() {
		return feedBackState;
	}

	
	public void setFeedBackState(String feedBackState) {
		this.feedBackState = feedBackState;
	}
	
	@Override
	public String toString() {
		return "FeedBack [feedBackID=" + feedBackID + ", feedBackContent="
				+ feedBackContent + ", feedBackDate=" + feedBackDate
				+ ", feedBackState=" + feedBackState + "]";
	}
}
