package com.wb.struts2.basic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接类
 * 
 * @author BS 2018-4-2
 */
public class DBconn {

	private String Drivername;
	private String url;
	private String username;
	private String pwd;
	public String password;

	/**
	 * 数据库连接类的构造方法，在构造数据库对象时从配置文件中获取连接参数，并注册驱动.
	 */
	public DBconn() {
		Properties pro = new Properties();
		try {
			pro.load(DBconn.class.getClassLoader().getResourceAsStream(
					"conn.ini"));
			Drivername = pro.getProperty("Drivername");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			pwd = pro.getProperty("pwd");
			password= pro.getProperty("password");
			Class.forName(Drivername);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接对象
	 * 
	 * @return 连接对象
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭Connection，Statement，ResultSet
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public void DBclose(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {
			if (conn != null)
				conn.close();
			if (ps != null)
				conn.close();
			if (rs != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
