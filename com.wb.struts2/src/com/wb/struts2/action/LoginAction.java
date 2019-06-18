package com.wb.struts2.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.mail.iap.Response;
import com.wb.struts2.model.LoginBean;
import com.wb.struts2.service.LoginFactory;
import com.wb.struts2.service.LoginInterface;

public class LoginAction implements ModelDriven<LoginBean>{

	private LoginBean log=new LoginBean();
	@Override
	public LoginBean getModel() {
		// TODO Auto-generated method stub 
		return this.log;
	}

	public String Login() throws SQLException {
		LoginInterface li=LoginFactory.getInstance();
		LoginBean lg=new LoginBean();
		lg=li.login(log);
		if(lg!=null){
			return Action.SUCCESS;
		}
			/*HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("msg", "´íÎó!!!");*/
			ActionContext.getContext().getSession().put("msg", "´íÎó£¡£¡£¡");
		 	return Action .ERROR;
	}   
}
