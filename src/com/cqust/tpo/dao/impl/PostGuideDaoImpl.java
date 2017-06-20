package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cqust.tpo.dao.IPostGuideDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.PostGuide;

public class PostGuideDaoImpl implements IPostGuideDao{

	@Override
	public boolean doInsert(PostGuide vo) {
		// TODO Auto-generated method stub
		String pgt=vo.getPostGuideTitle();
		String pgc=vo.getPostGuideContent();
		java.sql.Date pgd=new java.sql.Date(vo.getPostGuideTime().getTime());
		boolean n=ControlDB.executeUpdate("INSERT INTO tp_postguide(postGuideTitle,postGuideContent,postGuideTime) VALUES('"+pgt+"','"+pgc+"','"+pgd+"')");
		return n;
	}

	@Override
	public boolean doUpdate(PostGuide vo) {
		// TODO Auto-generated method stub
		int pgid=vo.getPostGuideId();
		String pgt=vo.getPostGuideTitle();
		String pgc=vo.getPostGuideContent();
		java.sql.Date pgd=new java.sql.Date(vo.getPostGuideTime().getTime());
		boolean n=ControlDB.executeUpdate("UPDATE tp_postguide SET postGuideTitle='"+pgt+"',postGuideContent='"+pgc+"',postGuideTime='"+pgd+"' WHERE postGuideId="+pgid+"");
		return n;
	}
	@Override
	public boolean doDelete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		boolean n=ControlDB.executeUpdate("DELETE FROM tp_postguide WHERE postGuideId="+id+"");
		return n;
	}

	@Override
	public PostGuide findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
//向航延和宋光磊的冲突
//	向航延备份
//	@Override
//	public List<PostGuide> findAll() throws Exception {
//		List<PostGuide> li = new ArrayList();
//		String sql = "select * from tp_postguide where 1=1";
//		ResultSet rs = ControlDB.executeQuery(sql);	
//		try {
//			while (rs.next()) {
//				PostGuide pg = new PostGuide();
//				pg.setPostGuideId(rs.getInt("POSTGUIDEID"));
//				pg.setPostGuideTitle(rs.getString("POSTGUIDETITLE"));
//				pg.setPostGuideContent(rs.getString("POSTGUIDECONTENT"));
//				li.add(pg);
//			}
//			
//		} catch (Exception e) {
//			
//		} finally {
//			CloseDB.close(rs);
//		}
//		return li;
//	}
	
//	宋光磊的
	@Override
	public List<PostGuide> findAll() {// TODO Auto-generated
														// method stub
		ResultSet rs = ControlDB.executeQuery("SELECT * FROM tp_postguide");
		List<PostGuide> list = new ArrayList<>();
		try {
			while (rs.next()) {
				PostGuide g = new PostGuide();
				g.setPostGuideContent(rs.getString("postGuideContent"));
				g.setPostGuideId(rs.getInt("postGuideId"));
				g.setPostGuideTime(rs.getDate("postGuideTime"));
				g.setPostGuideTitle(rs.getString("postGuideTitle"));
				list.add(g);
			}
		} catch (Exception e) {
		} finally {
			CloseDB.close(rs);
		}
		return list;
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.dao.IPostGuideDao#findAll(int, int)
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@Override
	public List findAll(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.dao.IPostGuideDao#getAllCount()
	 * @return
	 */
	@Override
	public int getAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}


}
