<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户服务范围管理</title>
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
		<li class="active"><a href="${ctx}/business/businessServicescope/">商户服务范围列表</a></li>
		<shiro:hasPermission name="business:businessServicescope:edit"><li><a href="${ctx}/business/businessServicescope/form">商户服务范围添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessServicescope" action="${ctx}/business/businessServicescope/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户基本信息ID：</label>
				<form:select path="businessinfoId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${allBusinessInfo}" itemLabel="businessName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>楼盘信息ID：</label>
				<form:select path="villageInfoId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${allVillageInfo}" itemLabel="villageName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户基本信息ID</th>
				<th>楼盘信息ID</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="business:businessServicescope:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessServicescope">
			<tr>
				<td><a href="${ctx}/business/businessServicescope/form?id=${businessServicescope.id}">
					${fns:getDictLabel(businessServicescope.businessinfoId, '', '')}
				</a></td>
				<td>
					${fns:getDictLabel(businessServicescope.villageInfoId, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${businessServicescope.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${businessServicescope.remarks}
				</td>
				<shiro:hasPermission name="business:businessServicescope:edit"><td>
    				<a href="${ctx}/business/businessServicescope/form?id=${businessServicescope.id}">修改</a>
					<a href="${ctx}/business/businessServicescope/delete?id=${businessServicescope.id}" onclick="return confirmx('确认要删除该商户服务范围吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>