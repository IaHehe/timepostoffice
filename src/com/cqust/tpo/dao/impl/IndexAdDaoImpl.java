package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cqust.tpo.dao.IIndexAdDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.IndexAd;

public class IndexAdDaoImpl implements IIndexAdDao{

	@Override
	public boolean doInsert(IndexAd vo) throws Exception {
		int indexAdId = ((IndexAd)vo).getIndexAdId();
		String indexAdTitle = ((IndexAd)vo).getIndexAdTitle();
		String indexAdContent = ((IndexAd)vo).getIndexAdContent();
		
		IndexAd indexAd = new IndexAd();
		
		String sql = "insert into tp_indexad" + "(indexAdTitle, indexAdContent)" + "values('"+ indexAdTitle +"', '"+ indexAdContent +"')";
		
		boolean stmt = ControlDB.executeUpdate(sql);
		return stmt;
	}

	@Override
	public boolean doUpdate(IndexAd vo) throws Exception {
		int indexAdId = ((IndexAd)vo).getIndexAdId();
		String indexAdTitle = ((IndexAd)vo).getIndexAdTitle();
		String indexAdContent = ((IndexAd)vo).getIndexAdContent();
		
		IndexAd indexAd = new IndexAd();
		
		String sql = "update tp_indexad set" + "(indexAdTitle, indexAdContent)" + "values('"+ indexAdTitle +"', '"+ indexAdContent +"')";
		
		boolean stmt = ControlDB.executeUpdate(sql);
		return stmt;
 	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public IndexAd findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndexAd> findAll() throws Exception {
		String sql = "SELECT * FROM tp_indexad";
		ResultSet rs = ControlDB.executeQuery(sql);
		
		List<IndexAd> list = new ArrayList<>();

		while (rs.next()) {
			IndexAd indexAd = new IndexAd();
			indexAd.setIndexAdId(rs.getInt("indexAdId"));
			indexAd.setIndexAdTitle(rs.getString("indexAdTitle"));
			indexAd.setIndexAdContent(rs.getString("indexAdContent"));
			indexAd.setIndexAdTime(rs.getDate("indexAdTime"));
			list.add(indexAd);
		}

		CloseDB.close(rs);

		return list;
	}

	@Override
	public List<IndexAd> findAll(int firstResult, int maxResult)
			throws Exception {
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
