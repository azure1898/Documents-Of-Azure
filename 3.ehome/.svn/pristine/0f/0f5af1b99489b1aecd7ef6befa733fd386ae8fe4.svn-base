<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券导入的用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/couponManageUsers/">优惠券导入的用户列表</a></li>
		<shiro:hasPermission name="operation:couponManageUsers:edit"><li><a href="${ctx}/operation/couponManageUsers/form">优惠券导入的用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="couponManageUsers" action="${ctx}/operation/couponManageUsers/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>优惠券ID：</label>
				<form:input path="couponManageId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户账号</label>
				<form:input path="account" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>优惠券ID</th>
				<th>用户账号</th>
				<th>注册时间</th>
				<th>是否存在</th>
				<th>会员ID</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="operation:couponManageUsers:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="couponManageUsers">
			<tr>
				<td>
				    ${couponManageUsers.couponManageId}
				</td>
				<td>
					${couponManageUsers.account}
				</td>
                <td>
                    <fmt:formatDate value="${couponManageUsers.registerTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
                <td>
                    ${fns:getDictLabel(couponManageUsers.existFlag, 'exist_flag', '未知')}
                </td>
                <td>
                    ${couponManageUsers.accountId}
                </td>
				<td>
					<fmt:formatDate value="${couponManageUsers.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${couponManageUsers.remarks}
				</td>
				<shiro:hasPermission name="operation:couponManageUsers:edit"><td>
    				<a href="${ctx}/operation/couponManageUsers/form?id=${couponManageUsers.id}">修改</a>
					<a href="${ctx}/operation/couponManageUsers/delete?id=${couponManageUsers.id}" onclick="return confirmx('确认要删除该优惠券导入的用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>