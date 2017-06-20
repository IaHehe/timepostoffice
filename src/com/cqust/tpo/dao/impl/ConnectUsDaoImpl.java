package com.cqust.tpo.dao.impl;
/***********************************************************************
 * Module:  ConnectUsDaoImpl.java
 * Author:  DELL
 * Purpose: Defines the Class ConnectUsDaoImpl
 ***********************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cqust.tpo.dao.IConnectUsDao;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.ContactUs;
import com.cqust.tpo.models.FeedBack;
import com.cqust.tpo.models.PostGuide;

/** 联系我们Dao实现
 * 
 * @pdOid a800f9f5-d151-4773-af86-03d013d7ae7b */
public class ConnectUsDaoImpl implements IConnectUsDao {

	@Override
	public boolean doInsert(Object vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Object vo) throws Exception {
		// TODO Auto-generated method stub
		int cuid=((ContactUs)vo).getContactUsId();
		String cue=((ContactUs)vo).getContactUsEmail();
		String cup=((ContactUs)vo).getContactUsPhone();
		String cua=((ContactUs)vo).getContactUsAddress();
		boolean n=ControlDB.executeUpdate("UPDATE tp_contactus SET contactUsEmail='"+cue+"',contactUsPhone='"+cup+"',contactUsAddress='"+cua+"' WHERE contactUsId="+cuid+"");
		return n;
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
		ResultSet rs=ControlDB.executeQuery("SELECT * FROM tp_contactus");
		List<ContactUs> list=new ArrayList<>();
		try {
			while(rs.next())
			{
				ContactUs g=new ContactUs();
				g.setContactUsAddress(rs.getString("contactUsAddress"));
				g.setContactUsId(rs.getInt("contactUsId"));
				g.setContactUsEmail(rs.getString("contactUsEmail"));
				g.setContactUsPhone(rs.getString("contactUsPhone"));
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