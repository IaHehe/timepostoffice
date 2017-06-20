package com.cqust.tpo.dao;

import java.util.Date;

import com.cqust.tpo.models.User;

public interface IUserDao extends IDAO<Integer,User>{
	/**
	 * 根据用户的注册邮箱获取用户对象
	 * @param userEmail
	 * @return
	 */
	public User findUserByEmail(String userEmail);
	
	/**
	 * 更新用户是否可以写信的状态
	 * @param user
	 * @param forbid
	 * @return
	 */
	public boolean doUpdate(User user,String forbid);
	
	/**
	 * 当用户登录时，更新登录的时间
	 * @param lastTime
	 * @return
	 */
	public boolean doUpdate(User user,Date lastTime);
}
