<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>财务结算</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	
});
function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">财务结算</li>
	</ul>
	<form:form id="searchForm" modelAttribute="businessBalance"
		action="${ctx}/balance/businessBalance/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><input name="balanceStartTime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${businessBalance.balanceStartTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</li>
			<li><input name="balanceEndTime" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${businessBalance.balanceEndTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<input type="hidden" id="checkedId" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>计算周期</th>
				<th>在线支付金额</th>
				<th>在线支付扣点</th>
				<th>结算金额</th>
				<th>结算状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${page.list}" var="businessBalance"
				varStatus="status">
				<tr>
					<td>
                        ${status.count}
                    </td>
					<td><fmt:formatDate
							value="${businessBalance.balanceStartTime}" pattern="yyyy-MM-dd" />
						~ <fmt:formatDate value="${businessBalance.balanceEndTime}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatNumber type="currency">${businessBalance.orderMoney}</fmt:formatNumber>
					</td>
					<td><fmt:formatNumber type="currency">${businessBalance.deductionMoney}</fmt:formatNumber>
					</td>
					<td><fmt:formatNumber type="currency">${businessBalance.payMoney}</fmt:formatNumber>
					</td>
					<td>
					    ${fns:getDictLabel(businessBalance.checkState, 'checkState', '')}
					</td>
					<td><a
						href="${ctx}/balance/businessBalanceDetail?businessBalanceId=${businessBalance.id}">
							查看结算单明细
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>