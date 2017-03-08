<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="com.struts.entity.UserAccount" %>
<%@ page  import="java.util.List" %>

<jsp:include page="../top.jsp"></jsp:include>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">用户查询条件</h3>
  </div>
  <div class="panel-body">
    <form action="user!findUser" class="form-inline">
    	<div class="form-group">
    		<label>用户ID:</label><input class="form-control" name="userAccount.userId" type="text">
    	</div>
    	<div class="form-group">
    		<label>用户账号:</label><input class="form-control" name="userAccount.accountNo" type="text">
    	</div>
    	<div class="form-group">
    		<label>密码:</label><input class="form-control" name="userAccount.password" type="text">
    	</div>
    	<input type="hidden" name="page.pageSize" value="2">
    	<input type="hidden" name="page.pageNo" value="1">
    	<button type="submit" class="btn btn-info">查询</button>
    	<a class="btn btn-success" href="user!editUser">新增</a>
    </form>
  </div>
</div>
这里展示user列表
<table class="table table-bordered table-striped">
	<tr>
		<td>ID</td>
		<td>用户名</td>
		<td>密码</td>
		<td>操作</td>
	</tr>
	
<%
List<UserAccount> ul = (List<UserAccount>)request.getAttribute("ul");
if(ul!=null && ul.size()>0){
	for(UserAccount u:ul){
%>
	<tr>
		<td><%=u.getUserId() %></td>
		<td><%=u.getAccountNo() %></td>
		<td><%=u.getPassword() %></td>
		<td width="20%"><a class="btn btn-warning btn-sm" href="user!editUser?userId=<%=u.getUserId() %>">修改</a>
		 <a class="btn btn-danger btn-sm" href="user!editUser?userId=<%=u.getUserId() %>">删除</a></td>
	</tr>
<%
	}
}
%>
</table>
<nav aria-label="...">
</nav>
<jsp:include page="../bottom.jsp"></jsp:include>