<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>对账单管理</title>
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
		<li class="active"><a href="${ctx}/balance/downloadBill/">对账单列表</a></li>
		<shiro:hasPermission name="balance:downloadBill:edit"><li><a href="${ctx}/balance/downloadBill/form">对账单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="downloadBill" action="${ctx}/balance/downloadBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户订单号：</label>
				<form:input path="outTradeNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>交易类型：</label>
				<form:select path="tradeType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>交易时间</th>
				<th>设备号</th>
				<th>微信订单号</th>
				<th>商户订单号</th>
				<th>交易类型</th>
				<th>付款银行</th>
				<shiro:hasPermission name="balance:downloadBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="downloadBill">
			<tr>
				<td><a href="${ctx}/balance/downloadBill/form?id=${downloadBill.id}">
					<fmt:formatDate value="${downloadBill.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${downloadBill.deviceInfo}
				</td>
				<td>
					${downloadBill.transactionId}
				</td>
				<td>
					${downloadBill.outTradeNo}
				</td>
				<td>
					${fns:getDictLabel(downloadBill.tradeType, '', '')}
				</td>
				<td>
					${fns:getDictLabel(downloadBill.bankType, '', '')}
				</td>
				<shiro:hasPermission name="balance:downloadBill:edit"><td>
    				<a href="${ctx}/balance/downloadBill/form?id=${downloadBill.id}">修改</a>
					<a href="${ctx}/balance/downloadBill/delete?id=${downloadBill.id}" onclick="return confirmx('确认要删除该对账单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>