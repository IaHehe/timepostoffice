package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.User;
import com.mysql.jdbc.PreparedStatement;

public class UserDaoImpl implements IUserDao{

	@Override
	public boolean doInsert(User user) throws Exception {
		String sql = "insert into tp_users(userName,userEmail,userPassword,userImg)"
				+ " values('"+user.getUserName()+"','"+user.getUserEmail()+"','"
				+user.getUserPassword()+"','"+user.getUserImg()+"')";
		
		return ControlDB.executeUpdate(sql);
	}
	
	@Override
	public boolean doUpdate(User vo) throws Exception {
		String sql = 
				"update tp_users set userName='"+vo.getUserName()
					+"',userEmail='"+vo.getUserEmail()+"',userPassword='"
					+vo.getUserPassword()+"',userImg='"
					+vo.getUserImg()+"',isForbidWrite='"
					+vo.getIsForbidWrite()+"' where userId = "
					+vo.getUserId()+"";
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		return false;
		// TODO Auto-generated method stub
		
	}

	/** (non-Javadoc)
	 * 通过用户id查找此用户的信息
	 * 不存在返回null
	 * @see com.cqust.tpo.dao.IDAO#findById(java.lang.Object)
	 * @param id 用户ID值
	 * @return 用户实例，如果不存在或者有异常，返回null
	 * @throws Exception 数据库获取或者数据处理时异常
	 */
	@Override
	public User findById(Integer id) throws Exception {
		String sql = "SELECT userId,userName,userEmail,userPassword,userImg,createTime,lastTime,isForbidWrite FROM tp_users WHERE userid = '" + id + "'";
		ResultSet rs = ControlDB.executeQuery(sql);
		User user = null;
		try {
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserImg(rs.getString("userImg"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setIsForbidWrite(rs.getString("isForbidWrite"));
				user.setCreateTime(rs.getDate("createTime"));
				user.setLastTime(rs.getDate("lastTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return user;
	}

	@Override
	public User findUserByEmail(String userEmail) {
		String sql = "SELECT * FROM tp_users WHERE useremail = '" + userEmail + "'";
		ResultSet rs = ControlDB.executeQuery(sql);
		User user = new User();
		try {
			if(rs.next()) {
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserImg(rs.getString("userImg"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setIsForbidWrite(rs.getString("isForbidWrite"));
				user.setCreateTime(rs.getDate("createTime"));
				user.setLastTime(rs.getDate("lastTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return user;
	}
	
	@Override
	public List<User> findAll() throws Exception {
		String sql = "select * from tp_users";
		List<User> users = new ArrayList<User>();
		ResultSet rs = ControlDB.executeQuery(sql);
		while(rs.next()) {
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setUserEmail(rs.getString("userEmail"));
			u.setUserName(rs.getString("userName"));
			u.setUserImg(rs.getString("userImg"));
			u.setIsForbidWrite(rs.getString("isForbidWrite"));
			u.setCreateTime(rs.getDate("createTime"));
			u.setLastTime(rs.getDate("lastTime"));
			
			users.add(u);
		}
		return users;
	}

	@Override
	public List<User> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doUpdate(User user,String forbid) {
		String sql = "update tp_users set isforbidwrite = '"+forbid+"' where userid = '"+user.getUserId()+"'";
		
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public boolean doUpdate(User user,Date lastTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeTxt = sdf.format(lastTime);
		String sql = "update tp_users set lastTime = '"+timeTxt+"' where userId = " + user.getUserId();
		return ControlDB.executeUpdate(sql);
	}
}
