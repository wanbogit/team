package com.wb.struts2.service;

import com.wb.struts2.impl.LoginImpl;

public class LoginFactory {

	public static LoginInterface getInstance(){
		return new LoginImpl();
	}
}
