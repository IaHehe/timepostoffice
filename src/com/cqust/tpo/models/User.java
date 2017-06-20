package com.cqust.tpo.models;

import java.util.Date;

/**
 * 用户实体对象
 * @author 邹东军
 * @date 2016/9/26
 */
public class User {

	private int userId; // 用户id
	private String userName;// 用户昵称
	private String userEmail;// 用户邮箱
	private String userPassword;// 用户密码
	private String userImg;// 用户头像
	private Date createTime;// 创建时间
	private Date lastTime;// 上次登录时间
	private String isForbidWrite;// 禁止用户写时光信

	public User() {

	}

	public User(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public User(String userName, String userEmail, String userPassword, String userImg) {
		this(userEmail, userPassword);
		this.userName = userName;
		this.userImg = userImg;
	}

	public User(String userName, String userEmail, String userPassword, String userImg, Date createTime, Date lastTime,
			String isForbidWrite) {
		this(userName, userEmail, userPassword, userImg);
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isForbidWrite = isForbidWrite;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getIsForbidWrite() {
		return isForbidWrite;
	}

	public void setIsForbidWrite(String isForbidWrite) {
		this.isForbidWrite = isForbidWrite;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userImg=" + userImg + ", createTime=" + createTime + ", lastTime=" + lastTime
				+ ", isForbidWrite=" + isForbidWrite + "]";
	}

}
