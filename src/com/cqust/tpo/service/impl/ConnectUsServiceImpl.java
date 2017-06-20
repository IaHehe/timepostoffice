package com.cqust.tpo.service.impl;
/***********************************************************************
 * Module:  ConnectUsServiceImpl.java
 * Author:  DELL
 * Purpose: Defines the Class ConnectUsServiceImpl
 ***********************************************************************/

import java.util.*;

import com.cqust.tpo.dao.IConnectUsDao;
import com.cqust.tpo.dao.impl.ConnectUsDaoImpl;
import com.cqust.tpo.service.IConnectUsService;

/** 联系我们Service实现
 * 
 * @pdOid 8e5d4221-7fba-4a72-847f-6355f2f86e7e */
public class ConnectUsServiceImpl implements IConnectUsService {
   /** @pdRoleInfo migr=no name=IConnectUsDao assc=association5 mult=0..1 */
   private IConnectUsDao iConnectUsDao;

@Override
public void insert(Object vo) {
	// TODO Auto-generated method stub
	
}

@Override
public void update(Object vo) {
	// TODO Auto-generated method stub
	iConnectUsDao=new ConnectUsDaoImpl();
	try {
		iConnectUsDao.doUpdate(vo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	iConnectUsDao=new ConnectUsDaoImpl();
	
	try {
		return iConnectUsDao.findAll();
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