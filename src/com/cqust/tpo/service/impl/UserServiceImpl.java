package com.cqust.tpo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.dao.impl.UserDaoImpl;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao = new UserDaoImpl();
	
	@Override
	public boolean insert(User vo) {
		try {
			userDao.doInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(User vo) {
		try {
			return userDao.doUpdate(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String userEmail) {
		return userDao.findUserByEmail(userEmail);
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> list() {
		try {
			return userDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUser(User user) {
		User tmp = new User();
		tmp = userDao.findUserByEmail(user.getUserEmail());
		if(tmp!= null) {
			if(user.getUserEmail().equals(tmp.getUserEmail()) 
					&& user.getUserPassword().equals(tmp.getUserPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> list(String searchTxt) {
		List<User> allUsers = list();
		
		List<User> searchUsers = new ArrayList<User>();

		for (User user : allUsers) {
			if (user.getUserName().contains(searchTxt) || user.getUserEmail().contains(searchTxt)) {
				searchUsers.add(user);
			}
		}
		return searchUsers;
	}

	@Override
	public boolean update(User user,String forbid) {
		return userDao.doUpdate(user, forbid);
	}

	@Override
	public boolean update(User user, Date date) {
		return userDao.doUpdate(user,date);
	}
}
