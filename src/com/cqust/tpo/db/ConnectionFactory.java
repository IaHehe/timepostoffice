package com.cqust.tpo.db;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取数据库连接的工厂类
 * @author 邹东军
 * @date 2016/09/25
 */
public class ConnectionFactory {
	
	private static Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
	
	/*
	 * 设置数据库驱动
	 */
	private static String driver = "";
	
	/*
	 * 设置数据库的URL
	 */
	private static String dbURL = "";
	
	/*
	 * 设置数据库用户名
	 */
	private static String user = "";
	
	/*
	 * 设置数据库用户密码
	 */
	private static String password = "";
	
	/**
	 * 获取配置文件db.properties中的jdbc驱动,url信息
	 * 和数据库名、密码
	 */
	static {
		Properties prop = new Properties();
		
		//方法链
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		
		try{
			//从输入流中的数据加载成键值对
			prop.load(is);
		} catch(IOException e)
		{
			System.out.println("加载配置文件出错");
		}
		
		//获取driver值
		driver = prop.getProperty("driver");
		//获取URL值
		dbURL = prop.getProperty("url");
		//获取user值
		user = prop.getProperty("user");
		//获取password值
		password = prop.getProperty("password");
	}

	/**
	 * 获取数据连接
	 * @return conn 返回数据连接Connection对象
	 */
	public static Connection getConnection(){
		Connection conn = null;
		
		try{
			//LOG.info("数据库配置信息："+"driver="+driver+"dbURL="+dbURL+"user="+user+"password"+password);
			System.out.println("数据库配置信息："+"driver="+driver+"dbURL="+dbURL+"user="+user+"password"+password);
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL,user,password);
		} catch (ClassNotFoundException e) {
			System.out.println(" No class "+driver + " found error");
			e.printStackTrace();
		}catch (SQLException e)
		{
			System.out.println("Failed to get connection :"+e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
}
