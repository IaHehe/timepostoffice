package com.cqust.tpo.dao;

import com.cqust.tpo.models.Manager;

public interface IManagerDao extends IDAO<Integer, Manager> {

	/**
	 * 以管理员的账号获取Manager对象
	 * @param account
	 * @return
	 */
	public Manager findManagerByAccount(String account);
	
}
