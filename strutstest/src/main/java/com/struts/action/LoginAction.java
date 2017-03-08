package com.struts.action;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.struts.util.DBTools;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().setAttribute("project", "登陆注册的项目");
		return "success";
	}
	
	public String doLogin() throws Exception{
		String u = "aaa";
		String p = "ppp";
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String fu = request.getParameter("username");
//		String fp = request.getParameter("password");
		String sql = "select * from user_account where account_no='"+username+"'";
		Connection c = DBTools.getConnection();
		ResultSet user = DBTools.execSql(c, sql);
		
		String pwd = "";
		while(user.next()){
			pwd = user.getString("password");
		}
		DBTools.closeConnection(c);
		if(password.equals(pwd)){
			ServletActionContext.getRequest().getSession().setAttribute("username", username);
			Cookie un = new Cookie("username",username);
			Cookie pd = new Cookie("password",password);
			un.setMaxAge(60*60*24*7);
			pd.setMaxAge(60*60*24*7);
			ServletActionContext.getResponse().addCookie(un);
			ServletActionContext.getResponse().addCookie(pd);
			return "success";
		}else{
			message = "登陆失败请重新登陆";
			return "error";
		}
	}
}
