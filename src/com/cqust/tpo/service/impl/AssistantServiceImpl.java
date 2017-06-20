package com.cqust.tpo.service.impl;

import java.util.List;

import com.cqust.tpo.dao.IAssistantDao;
import com.cqust.tpo.dao.impl.AssistantDaoImpl;
import com.cqust.tpo.models.Assistant;
import com.cqust.tpo.service.IAssistantService;

public class AssistantServiceImpl implements IAssistantService{

	private IAssistantDao assistantDao = new AssistantDaoImpl();
	
	@Override
	public boolean insert(Assistant vo) {
		boolean flag = false;
		try {
			flag = assistantDao.doInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(Assistant vo) {
		boolean flag = false;
		try {
			flag = assistantDao.doUpdate(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag = false;
		try {
			flag = assistantDao.doDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Assistant get(Integer id) {
		Assistant assistant = new Assistant();
		try {
			assistant = assistantDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assistant;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public List<Assistant> list() {
		try {
			return assistantDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Assistant> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

}
