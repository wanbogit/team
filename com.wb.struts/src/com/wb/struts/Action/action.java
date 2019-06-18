package com.wb.struts.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import net.sf.json.JSONArray;



public class action {

		private String uname;
		private String pwd;
		private String insert;
		private String id;
		private String name;
		private String remark;
		private JSONArray list;
		
		
		public JSONArray getList() {
			return list;
		}


		public void setList(JSONArray list) {
			this.list = list;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}


		public String getInsert() {
			return insert;
		}


		public void setInsert(String insert) {
			this.insert = insert;
		}


		public String getUname() {
			return uname;
		}


		public void setUname(String uname) {
			this.uname = uname;
		}


		public String getPwd() {
			return pwd;
		} 


		public void setPwd(String pwd) {
			this.pwd = pwd;
		}


		public String Login(){
			if(uname.equals("wb")&&pwd.equals("123"))
				return "success";
			return "error";
		}
		
		public String insert(){
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("msg", insert);
			return Action.SUCCESS;
		}
		
		public void select() throws IOException{
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			List<action> list=new ArrayList<action>();
			for(int i=0;i<10;i++){
				action ac=new action();
				ac.setId("1"+i);
				ac.setName("小李"+i);
				ac.setRemark("信息"+i);
				list.add(ac);
			}
			request.setAttribute("list", list);
			
			JSONArray json=JSONArray.fromObject(list);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();
		}
}
