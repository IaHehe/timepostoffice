package com.cqust.tpo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * CloseDB用于关闭数据库连接、语句对象和结果集
 * @author 邹东军
 *
 */
public class CloseDB {
	    //关闭连接
		public static void close(Connection con)
		{
			try {
				if(con != null)
				{
					con.close();
				}
			}catch (Exception e)
			{
				System.out.println("关闭Connection异常");
			}
		}
		
		//关闭结果集
		public static void close(ResultSet rs)
		{
			try {
				if(rs != null)
				{
					rs.close();
				}
			}catch (Exception e)
			{
				System.out.println("关闭ResultSet异常");
			}
		}
		
		//关闭语句块
		public static void close(Statement st)
		{
			try{
				if(st != null)
				{
					st.close();
				}
			} catch (Exception e)
			{
				System.out.println("关闭Statement异常");
			}
		}
		
		//关闭语句块和连接
		public static void close(Statement stm,Connection con)
		{
			close(con);
			close(stm);
		}
		
		//关闭结果集、语句块和连接
		public static void close(ResultSet rs,Statement stm,Connection con)
		{
			close(rs);
			close(stm,con);
		}
}
