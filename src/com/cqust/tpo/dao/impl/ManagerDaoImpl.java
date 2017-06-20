package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cqust.tpo.dao.IManagerDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.Manager;

public class ManagerDaoImpl implements IManagerDao{

	@Override
	public boolean doInsert(Manager vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Manager vo) throws Exception {
		
		return false;
	}

	@Override
	public boolean doDelete(Integer id)  {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Manager findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> findAll() throws Exception {
		String sql = "select * from tp_manager";
		List<Manager> managers = new ArrayList<Manager>();
		ResultSet rs = ControlDB.executeQuery(sql); //执行查询
		
		while(rs.next()) {
			Manager m = new Manager();
			m.setManagerId(rs.getInt("managerId"));
			m.setManagerAccount(rs.getString("managerAccount"));
			m.setManagerPassword(rs.getString("managerPassword"));
			
			managers.add(m);
		}
		return managers;
	}

	@Override
	public List<Manager> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Manager findManagerByAccount(String account) {
		String sql = "select * from tp_manager where manageraccount='"+account+"'";
		Manager m = new Manager();
		ResultSet rs = ControlDB.executeQuery(sql);
		try {
			if(rs.next()) {
				m.setManagerId(rs.getInt("managerId"));
				m.setManagerAccount(rs.getString("managerAccount"));
				m.setManagerPassword(rs.getString("managerPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return m;
	}

}
