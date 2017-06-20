package com.cqust.tpo.dao.impl;
/***********************************************************************
 * Module:  FeedBackDaoImpl.java
 * Author:  DELL
 * Purpose: Defines the Class FeedBackDaoImpl
 ***********************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cqust.tpo.dao.IFeedBackDao;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.FeedBack;
import com.cqust.tpo.models.PostGuide;

/** 在线反馈Dao实现类
 * 
 * @pdOid 7452cb4c-fc00-4efb-baaa-28423eb59216 */
public class FeedBackDaoImpl implements IFeedBackDao {
   /** @pdRoleInfo migr=no name=ConotrolDB assc=association44 mult=0..1 */
   private ControlDB conotrolDB;
   /** @pdRoleInfo migr=no name=feedback assc=association45 mult=0..1 */
   private FeedBack feedback;
@Override
public boolean doInsert(Object vo) throws Exception {
	// TODO Auto-generated method stub
	java.sql.Date fbd=new java.sql.Date(((FeedBack)vo).getFeedBackDate().getTime());
	String fbc=((FeedBack)vo).getFeedBackContent();
	String fbs=((FeedBack)vo).getFeedBackState();
	boolean n=ControlDB.executeUpdate("INSERT INTO tp_feedback(feedbackContent,feedbackTime,feedbackState) VALUES('"+fbc+"','"+fbd+"','"+fbs+"')");
	return n;
}
@Override
public boolean doUpdate(Object vo) throws Exception {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean doDelete(Object id) throws Exception {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Object findById(Object id) throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List findAll() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs=ControlDB.executeQuery("SELECT * FROM tp_feedback");
	List<FeedBack> list=new ArrayList<>();
	try {
		while(rs.next())
		{
			FeedBack g=new FeedBack();
			g.setFeedBackContent(rs.getString("feedbackContent"));
			g.setFeedBackID(rs.getInt("feedbackId"));
			g.setFeedBackDate(rs.getDate("feedbackTime"));
			g.setFeedBackState(rs.getString("feedbackState"));
			list.add(g);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
@Override
public List findAll(int firstResult, int maxResult) throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public int getAllCount() throws Exception {
	// TODO Auto-generated method stub
	return 0;
}

}