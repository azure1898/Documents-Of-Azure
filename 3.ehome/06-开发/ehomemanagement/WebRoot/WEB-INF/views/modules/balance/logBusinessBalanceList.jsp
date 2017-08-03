<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算操作日志管理</title>
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
		<li class="active"><a href="${ctx}/balance/logBusinessBalance/">商户结算操作日志列表</a></li>
		<shiro:hasPermission name="balance:logBusinessBalance:edit"><li><a href="${ctx}/balance/logBusinessBalance/form">商户结算操作日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="logBusinessBalance" action="${ctx}/balance/logBusinessBalance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>操作类型：</label>
				<form:select path="operationType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>商户ID：</label>
				<form:input path="businessInfoId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>结算周期开始时间：</label>
				<input name="balanceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${logBusinessBalance.balanceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结算周期结束时间：</label>
				<input name="balanceEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${logBusinessBalance.balanceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作类型</th>
				<th>批次号</th>
				<th>商户结算信息表ID</th>
				<th>商户ID</th>
				<th>结算周期</th>
				<th>结算周期开始时间</th>
				<th>结算周期结束时间</th>
				<th>结算模式</th>
				<th>结算状态</th>
				<th>核对状态</th>
				<shiro:hasPermission name="balance:logBusinessBalance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="logBusinessBalance">
			<tr>
				<td><a href="${ctx}/balance/logBusinessBalance/form?id=${logBusinessBalance.id}">
					${fns:getDictLabel(logBusinessBalance.operationType, '', '')}
				</a></td>
				<td>
					${logBusinessBalance.batchNo}
				</td>
				<td>
					${logBusinessBalance.businessBalanceId}
				</td>
				<td>
					${logBusinessBalance.businessInfoId}
				</td>
				<td>
					${logBusinessBalance.balanceCycle}
				</td>
				<td>
					<fmt:formatDate value="${logBusinessBalance.balanceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${logBusinessBalance.balanceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${logBusinessBalance.balanceModel}
				</td>
				<td>
					${logBusinessBalance.balanceState}
				</td>
				<td>
					${logBusinessBalance.checkState}
				</td>
				<shiro:hasPermission name="balance:logBusinessBalance:edit"><td>
    				<a href="${ctx}/balance/logBusinessBalance/form?id=${logBusinessBalance.id}">修改</a>
					<a href="${ctx}/balance/logBusinessBalance/delete?id=${logBusinessBalance.id}" onclick="return confirmx('确认要删除该商户结算操作日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>