package com.cqust.tpo.service.impl;
/***********************************************************************
 * Module:  FeedBackServiceImpl.java
 * Author:  DELL
 * Purpose: Defines the Class FeedBackServiceImpl
 ***********************************************************************/

import java.util.*;

import com.cqust.tpo.dao.IFeedBackDao;
import com.cqust.tpo.dao.impl.FeedBackDaoImpl;
import com.cqust.tpo.service.IFeedBackService;

/** 在线反馈Service实现
 * 
 * @pdOid b70033f1-513d-4534-b6e9-44cf32d76a6b */
public class FeedBackServiceImpl implements IFeedBackService {
   /** @pdRoleInfo migr=no name=IFeedBackDao assc=association3 mult=0..1 */
   private IFeedBackDao iFeedBackDao;

@Override
public void insert(Object vo) {
	// TODO Auto-generated method stub
	iFeedBackDao=new FeedBackDaoImpl();
	try {
		iFeedBackDao.doInsert(vo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void update(Object vo) {
	// TODO Auto-generated method stub
	
}

@Override
public void delete(Object id) {
	// TODO Auto-generated method stub
	
}

@Override
public Object get(Object id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int count() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public List list() {
	// TODO Auto-generated method stub
	iFeedBackDao=new FeedBackDaoImpl();
	try {
		return iFeedBackDao.findAll();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

@Override
public List list(int firstResult, int maxResult) {
	// TODO Auto-generated method stub
	return null;
}

}