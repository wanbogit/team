package com.wb.struts2.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wb.struts2.basic.BaseDAO;
import com.wb.struts2.model.LoginBean;
import com.wb.struts2.service.LoginInterface;

public class LoginImpl implements LoginInterface {

	@Override
	public LoginBean login(LoginBean log) throws SQLException {
		// TODO Auto-generated method stub
		BaseDAO bd =new BaseDAO();
		Object [] parmas={log.getName(),log.getPwd()};
		String sql="select * from T_Login where name=? and pwd=?";
		ResultSet rs = bd.select(sql, parmas);
		while (rs.next()) {
			LoginBean lb=new LoginBean();
			lb.setName(rs.getString("name"));
			lb.setPwd(rs.getString("pwd"));
			return lb;
		}
		return null;
	}

	@Override
	public int reset(LoginBean log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(LoginBean log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(LoginBean log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(LoginBean log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LoginBean> select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
