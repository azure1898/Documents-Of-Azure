<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">编辑用户信息</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" action="user!saveUser" type="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <input name="userAccount.userId" value="${userAccount.userId }" class="form-control" id="inputEmail3" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword1" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-10">
      <input name="userAccount.accountNo" value="${userAccount.accountNo }" type="text" class="form-control" id="inputPassword1" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
      <input name="userAccount.password" value="${userAccount.password }" type="password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">保存</button>
    </div>
  </div>
</form>
  </div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>