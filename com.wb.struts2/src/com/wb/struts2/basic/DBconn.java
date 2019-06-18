package com.wb.struts2.basic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���ݿ�������
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
	 * ���ݿ�������Ĺ��췽�����ڹ������ݿ����ʱ�������ļ��л�ȡ���Ӳ�������ע������.
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
	 * ��ȡ���Ӷ���
	 * 
	 * @return ���Ӷ���
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
	 * �ر�Connection��Statement��ResultSet
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
