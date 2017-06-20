package com.cqust.tpo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cqust.tpo.dao.IIndexAdDao;
import com.cqust.tpo.dao.impl.IndexAdDaoImpl;
import com.cqust.tpo.models.IndexAd;
import com.cqust.tpo.service.IIndexAdService;

public class IndexAdServiceImpl implements IIndexAdService{
	
	private IIndexAdDao indexAdDao = new IndexAdDaoImpl();

	
	@Override
	public void insert(IndexAd vo) {
		indexAdDao = new IndexAdDaoImpl();
		
		try{
			boolean indexAd = indexAdDao.doInsert(vo);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void update(IndexAd vo) {
		indexAdDao = new IndexAdDaoImpl();
		
		try{
			boolean indexAd = indexAdDao.doUpdate(vo);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IndexAd get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IndexAd> list() {
		List<IndexAd> indexAd = new ArrayList<IndexAd>();
		try {
			indexAd = indexAdDao.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indexAd;
	}

	@Override
	public List<IndexAd> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}
}
