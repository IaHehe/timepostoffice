package com.cqust.tpo.models;

import java.util.Date;

/**
 * 时光信实体类
 * @author 邹东军
 * @date 2016/09/26
 *
 */
public class TimeLetter {
	private int timeLetterId; // 时光信id
	private String timeLetterTitle; // 时光信标题
	private String timeLetterContent; // 时光信内容
	private Date letterSendTime; // 时光信要发送给收信人的时间
	private String letterRecipent; // 时光信收信人
	private Date createTime; // 注册用户创建时光信的时间
	private String isOpen; // 时光信是否公开
	private int attentionNumber; // 时光信关注量
	private int clickNumber; // 时光信点赞量
	private String timeLetterImg; // 时光信图片
	private String isRecommend; // 时光信是否推荐到首页
	private String isDisplay; // 是否禁止时光信显示到前台页面
	private String isTop; // 是否允许上排行榜
	private String timeLetterState; // 时光信状态
	private String isDelete; // 用户是否删除时光信
	
	private User user;

	/**
	 * @return 获取  user 值/对象
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user 设置 user 值/对象
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public TimeLetter() {
		super();
	}

	public TimeLetter(String timeLetterTitle, String timeLetterContent, Date letterSendTime, String letterRecipent,
			Date createTime, String isOpen, int attentionNumber, int clickNumber, String timeLetterImg,
			String isRecommend, String isDisplay, String isTop, String timeLetterState, String isDelete) {
		this.timeLetterTitle = timeLetterTitle;
		this.timeLetterContent = timeLetterContent;
		this.letterSendTime = letterSendTime;
		this.letterRecipent = letterRecipent;
		this.createTime = createTime;
		this.isOpen = isOpen;
		this.attentionNumber = attentionNumber;
		this.clickNumber = clickNumber;
		this.timeLetterImg = timeLetterImg;
		this.isRecommend = isRecommend;
		this.isDisplay = isDisplay;
		this.isTop = isTop;
		this.timeLetterState = timeLetterState;
		this.isDelete = isDelete;
	}

	public int getTimeLetterId() {
		return timeLetterId;
	}

	public void setTimeLetterId(int timeLetterid) {
		this.timeLetterId = timeLetterid;
	}

	public String getTimeLetterTitle() {
		return timeLetterTitle;
	}

	public void setTimeLetterTitle(String timeLetterTitle) {
		this.timeLetterTitle = timeLetterTitle;
	}

	public String getTimeLetterContent() {
		return timeLetterContent;
	}

	public void setTimeLetterContent(String timeLetterContent) {
		this.timeLetterContent = timeLetterContent;
	}

	public Date getLetterSendTime() {
		return letterSendTime;
	}

	public void setLetterSendTime(Date letterSendTime) {
		this.letterSendTime = letterSendTime;
	}

	public String getLetterRecipent() {
		return letterRecipent;
	}

	public void setLetterRecipent(String letterRecipent) {
		this.letterRecipent = letterRecipent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public int getAttentionNumber() {
		return attentionNumber;
	}

	public void setAttentionNumber(int attentionNumber) {
		this.attentionNumber = attentionNumber;
	}

	public int getClickNumber() {
		return clickNumber;
	}

	public void setClickNumber(int clickNumber) {
		this.clickNumber = clickNumber;
	}

	public String getTimeLetterImg() {
		return timeLetterImg;
	}

	public void setTimeLetterImg(String timeLetterImg) {
		this.timeLetterImg = timeLetterImg;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getTimeLetterState() {
		return timeLetterState;
	}

	public void setTimeLetterState(String timeLetterState) {
		this.timeLetterState = timeLetterState;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "TimeLetter [timeLetterId=" + timeLetterId + ", timeLetterTitle=" + timeLetterTitle
				+ ", timeLetterContent=" + timeLetterContent + ", letterSendTime=" + letterSendTime
				+ ", letterRecipent=" + letterRecipent + ", createTime=" + createTime + ", isOpen=" + isOpen
				+ ", attentionNumber=" + attentionNumber + ", clickNumber=" + clickNumber + ", timeLetterImg="
				+ timeLetterImg + ", isRecommend=" + isRecommend + ", isDisplay=" + isDisplay + ", isTop=" + isTop
				+ ", timeLetterState=" + timeLetterState + ", isDelete=" + isDelete + "]";
	}

}
