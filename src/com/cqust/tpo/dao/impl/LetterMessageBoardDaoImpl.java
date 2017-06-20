package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cqust.tpo.dao.ILetterMessageBoardDao;
import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;

/**
 * @author DongJiJun
 *
 */
public class LetterMessageBoardDaoImpl implements ILetterMessageBoardDao{

	@Override
	public boolean doInsert(LetterMessageBoard vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(LetterMessageBoard vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LetterMessageBoard findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LetterMessageBoard> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LetterMessageBoard> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LetterMessageBoard> findByTLid(int timelettertId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LetterMessageBoard> li = new ArrayList<LetterMessageBoard>();
		String sql = "SELECT * FROM tp_lettermessgaeboard WHERE timeLetterId = '" + timelettertId + "'";
		ResultSet rs = ControlDB.executeQuery(sql);
		IUserDao userDao = new UserDaoImpl();
		ITimeLetterDao timeletterDao = new TimeLetterDaoImpl();
		LetterMessageBoard lmb = new LetterMessageBoard();
		try {
			while(rs.next()){
				int userId = rs.getInt("userId");
				int timeletterId = rs.getInt("timeLetterId");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(userId);
				System.out.println(timeletterId);
				lmb.setUser(userDao.findById(userId));
				lmb.setTimeletter(timeletterDao.findById(timeletterId));
				//lmb.setTimeletter(timeletterDao.findById(timeletterId));
				lmb.setMessageContent(rs.getString("messageContent"));
				lmb.setMessageCreateTime(rs.getDate("messageCreateTime"));
				lmb.setMessageRemarks(rs.getString("messageRemarks"));
				li.add(lmb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseDB.close(rs);
		}
		return li;
	}

	


}
