package com.struts.interceptors;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.struts.util.DBTools;

public class AuthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//action执行之前的操作过程
		HttpServletRequest requet = ServletActionContext.getRequest();
		Object ou = requet.getSession().getAttribute("username");
		if(ou==null||"".equals((String)ou)){
			Cookie[] cookies = requet.getCookies();
			String cUserName = "";
			String cPassword = "";
			for(Cookie c:cookies){
				if(c.getName().equals("username")){
					cUserName = c.getValue();
				}else if(c.getName().equals("password")){
					cPassword = c.getValue();
				}
			}
			if(!cUserName.equals("")&&!cPassword.equals("")){
				String sql = "select * from user_account where account_no='"+cUserName+"'";
				Connection c = DBTools.getConnection();
				ResultSet user = DBTools.execSql(c, sql);
				String pwd = "";
				while(user.next()){
					pwd = user.getString("password");
				}
				DBTools.closeConnection(c);
				if(cPassword.equals(pwd)){
					ServletActionContext.getRequest().getSession().setAttribute("username", cUserName);
					return invocation.invoke();
				}
			}
			return "gologin";
		}
		
		String result = invocation.invoke();
		//action执行之后的操作过程
		return result;
	}

}
