<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物业结算操作日志管理</title>
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
		<li class="active"><a href="${ctx}/balance/logPropertyBalance/">物业结算操作日志列表</a></li>
		<shiro:hasPermission name="balance:logPropertyBalance:edit"><li><a href="${ctx}/balance/logPropertyBalance/form">物业结算操作日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="logPropertyBalance" action="${ctx}/balance/logPropertyBalance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>结算周期开始时间：</label>
				<input name="balanceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${logPropertyBalance.balanceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结算周期结束时间：</label>
				<input name="balanceEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${logPropertyBalance.balanceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>核对状态：</label>
				<form:select path="checkState" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('checkState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>操作类型</th>
				<th>批次号</th>
				<th>物业结算信息表ID</th>
				<th>物业公司ID</th>
				<th>结算周期</th>
				<th>结算周期开始时间</th>
				<th>结算周期结束时间</th>
				<th>结算模式</th>
				<th>结算状态</th>
				<th>核对状态</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="balance:logPropertyBalance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="logPropertyBalance">
			<tr>
				<td><a href="${ctx}/balance/logPropertyBalance/form?id=${logPropertyBalance.id}">
					${fns:getDictLabel(logPropertyBalance.operationType, 'operation_type', '')}
				</a></td>
				<td>
					${logPropertyBalance.batchNo}
				</td>
				<td>
					${logPropertyBalance.propertyBalanceId}
				</td>
				<td>
					${logPropertyBalance.propertyCompanyId}
				</td>
				<td>
					${fns:getDictLabel(logPropertyBalance.balanceCycle, 'balancecycle', '')}
				</td>
				<td>
					<fmt:formatDate value="${logPropertyBalance.balanceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${logPropertyBalance.balanceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(logPropertyBalance.balanceModel, 'balancemodel', '')}
				</td>
				<td>
					${fns:getDictLabel(logPropertyBalance.balanceState, '', '')}
				</td>
				<td>
					${fns:getDictLabel(logPropertyBalance.checkState, 'checkState', '')}
				</td>
				<td>
					<fmt:formatDate value="${logPropertyBalance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${logPropertyBalance.remarks}
				</td>
				<shiro:hasPermission name="balance:logPropertyBalance:edit"><td>
    				<a href="${ctx}/balance/logPropertyBalance/form?id=${logPropertyBalance.id}">修改</a>
					<a href="${ctx}/balance/logPropertyBalance/delete?id=${logPropertyBalance.id}" onclick="return confirmx('确认要删除该物业结算操作日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>