package com.cqust.tpo.service.impl;

import java.util.List;

import com.cqust.tpo.dao.IManagerDao;
import com.cqust.tpo.dao.impl.ManagerDaoImpl;
import com.cqust.tpo.models.Manager;
import com.cqust.tpo.service.IManagerService;

public class ManagerServiceImpl implements IManagerService {

	private IManagerDao managerDao = new ManagerDaoImpl();
	@Override
	public boolean insert(Manager vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Manager vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Manager get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Manager> list() {
		try {
			return managerDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Manager> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkManager(Manager manager) {
		Manager m = managerDao.findManagerByAccount(manager.getManagerAccount());
		boolean flag = m.getManagerPassword().equals(manager.getManagerPassword());
		if(flag) {
			return true;
		}
		return false;
	}

	@Override
	public Manager get(String account) {
		return managerDao.findManagerByAccount(account);
	}

}
