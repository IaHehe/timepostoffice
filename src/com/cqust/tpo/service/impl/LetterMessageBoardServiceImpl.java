package com.cqust.tpo.service.impl;

import java.util.List;

import com.cqust.tpo.dao.ILetterMessageBoardDao;
import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.dao.impl.LetterMessageBoardDaoImpl;
import com.cqust.tpo.dao.impl.TimeLetterDaoImpl;
import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.ILetterMessageBoardService;

/**
 * @author DongJiJun
 *
 */
public class LetterMessageBoardServiceImpl implements ILetterMessageBoardService{
	
	private ILetterMessageBoardDao lettermessageboardDao = new LetterMessageBoardDaoImpl();
	private ITimeLetterDao timeletterDao = new TimeLetterDaoImpl();
	@Override
	public void insert(LetterMessageBoard vo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(LetterMessageBoard vo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public LetterMessageBoard get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<LetterMessageBoard> list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LetterMessageBoard> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LetterMessageBoard> list(int timeletterId) {
		// TODO Auto-generated method stub
		try {
			List<LetterMessageBoard> LetterMessageBoard = lettermessageboardDao.findByTLid(timeletterId);
			return LetterMessageBoard;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}






}
