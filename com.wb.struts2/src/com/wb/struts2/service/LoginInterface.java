package com.wb.struts2.service;

import java.sql.SQLException;
import java.util.List;

import com.wb.struts2.model.LoginBean;

public interface LoginInterface {

	LoginBean login(LoginBean log) throws SQLException;
	int reset(LoginBean log);
	int insert(LoginBean log);
	int delete(LoginBean log);
	int update(LoginBean log);
	List<LoginBean> select() throws SQLException;
}
