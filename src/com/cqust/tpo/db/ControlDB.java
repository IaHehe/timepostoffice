package com.cqust.tpo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ControlDB该类用于执行对数据库的操作
 * @author 邹东军
 * @date 2016/09/25
 */
public class ControlDB {
	
	/**
	 * 执行Select语句
	 * 
	 * @param  sql
	 * @return 
	 * @throws Exception
	 */
	
	public static ResultSet executeQuery(String sql){
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("执行查询SQL语句出错"+e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}

	
	/**
	 * 执行Insert、Update、Delete语句
	 * @param sql
	 * @throws Exception
	 */
	public static boolean executeUpdate(String sql){
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			int flag = stmt.executeUpdate(sql);//executeUpdate为执行sql指令影响的行数，错误返回-1,
			if(flag == 1){
				return true;
			}
		} catch (SQLException ex) {
			System.err.println("执行SQL语句出错："+ex.getMessage());
			ex.printStackTrace();
		}finally {
			CloseDB.close(stmt,conn);
		}
		return false;
	}
}
