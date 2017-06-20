package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.cqust.tpo.dao.ILetterPaperPriceDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.LetterPaperPrice;

public class LetterPaperPriceDaoImpl implements ILetterPaperPriceDao{

	@Override
	public boolean doInsert(LetterPaperPrice vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(LetterPaperPrice vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
	@Override
	public LetterPaperPrice findById(Integer id) throws Exception {
		id =1 ;
		String sql = "select * from tp_letterpaperprice where letterPaperPriceId = 1";
		ResultSet rs = ControlDB.executeQuery(sql);
		LetterPaperPrice lpp = new LetterPaperPrice();
		try{
			while(rs.next()){
				lpp.setLetterPaperBasePrice(rs.getInt("LETTERPAPERBASEPRICE"));
				lpp.setLetterPaperDayPrice(rs.getDouble("LETTERPAPERDAYPRICE"));
				lpp.setLetterPageFreeDay(rs.getInt("LETTERPAGEFREEDAY"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB.close(rs);
		}
		return lpp;
	}

	
	
	
	
	@Override
	public List<LetterPaperPrice> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LetterPaperPrice> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
