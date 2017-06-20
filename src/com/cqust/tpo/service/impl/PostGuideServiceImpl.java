package com.cqust.tpo.service.impl;

import java.util.List;

import com.cqust.tpo.dao.IPostGuideDao;
import com.cqust.tpo.dao.impl.PostGuideDaoImpl;
import com.cqust.tpo.models.LetterPaperOrder;
import com.cqust.tpo.models.PostGuide;
import com.cqust.tpo.service.IPostGuideService;


public class PostGuideServiceImpl implements IPostGuideService{

	private IPostGuideDao postguideDao = new PostGuideDaoImpl();
	
	@Override
	public void insert(PostGuide vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PostGuide vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostGuide get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PostGuide> list() {
		try {
			List<PostGuide> PostGuideList = postguideDao.findAll();
			return PostGuideList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PostGuide> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PostGuide> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}





}
