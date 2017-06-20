package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cqust.tpo.dao.IAssistantDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.Assistant;

public class AssistantDaoImpl implements IAssistantDao{

	@Override
	public boolean doInsert(Assistant vo) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String assistantTime = sdf.format(vo.getAssistantTime());
		String sql = "insert into jianhuiyang_"
				+ "tp_assistant(assistantTitle,assistantContent,assistantTime)"
				+ " values('"+vo.getAssistantTitle()+"','"+vo.getAssistantContent()+"','"+assistantTime+"')";
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public boolean doUpdate(Assistant vo) throws Exception {
		String sql = "update tp_assistant set assistantTitle='"+vo.getAssistantTitle()
				+"',assistantContent='"+vo.getAssistantContent()
				+ "' where assistantId = " + vo.getAssistantId();
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		String sql = "delete from tp_assistant where assistantId=" + id;
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public Assistant findById(Integer id) throws Exception {
		String sql = "select * from tp_assistant where assistantId = "+id;
		ResultSet rs = ControlDB.executeQuery(sql);
		Assistant assistant = new Assistant();
		
		while(rs.next()) {
			assistant.setAssistantId(rs.getInt("assistantId"));
			assistant.setAssistantTitle(rs.getString("assistantTitle"));
			assistant.setAssistantContent(rs.getString("assistantContent"));
			
			assistant.setAssistantTime(rs.getDate("assistantTime"));
		}
		CloseDB.close(rs);
		return assistant;
	}

	@Override
	public List<Assistant> findAll() throws Exception {
		List<Assistant> assistants = new ArrayList<Assistant>();
		String sql = "select * from tp_assistant";
		ResultSet rs = ControlDB.executeQuery(sql);
		
		while(rs.next()) {
			Assistant a = new Assistant();
			a.setAssistantId(rs.getInt("assistantId"));
			a.setAssistantContent(rs.getString("assistantContent"));
			a.setAssistantTitle(rs.getString("assistantTitle"));
			a.setAssistantTime(rs.getTimestamp("assistantTime"));
			
			assistants.add(a);
		}
		
		return assistants;
	}

	@Override
	public List<Assistant> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		AssistantDaoImpl a = new AssistantDaoImpl();
		List<Assistant> list = a.findAll();
		for(Assistant s : list) {
			System.out.println(s);
		}
	}

}
