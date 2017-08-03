<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>结算单打印</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
    $("#btuElemPrint").click(function(){
    	var headstr = "<html><head><title></title></head><body>"; 
    	var footstr = "</body></html>"; 
    	var newstr = document.all.item("printArea").innerHTML; 
    	var oldstr = document.body.innerHTML; 
    	document.body.innerHTML = headstr+newstr+footstr; 
    	window.print(); 
    	document.body.innerHTML = oldstr; 
    	return false;
    }); 
});
</script>
<style type="text/css">
.div-inline {
	display: inline;
	padding-right: 50px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/balance/businessBalance/">结算单打印</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="businessBalance"
		action="${ctx}/balance/businessBalance/" method="post"
		class="breadcrumb form-search">
		<!-- 操作按钮 start -->
		<div style="margin: 10px">
			<div class="div-inline">
				<shiro:hasPermission name="balance:businessBalance:view">
					<a id="btuElemPrint" href="#" class="btn btn-primary"><i
						class="icon-trash icon-custom"></i> 打印</a>
				</shiro:hasPermission>
			</div>
		</div>
		<!-- 操作按钮 end -->
	</form:form>
	<sys:message content="${message}" />
	<div id="printArea">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th colspan="8">${title}</th>
				</tr>
				<tr>
					<th>序号</th>
					<th>商家名称</th>
					<th>开户名称</th>
					<th>开户银行</th>
					<th>银行账号</th>
					<th>所在城市</th>
					<th>应付金额</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${businessBalanceList}" var="businessBalance"
					varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${businessBalance.businessName}</td>
						<td>${businessBalance.accountName}</td>
						<td>${businessBalance.depositBank}</td>
						<td>${businessBalance.bankAccount}</td>
						<td>${businessBalance.cityName}</td>
						<td><fmt:formatNumber type="currency">${businessBalance.payMoney}</fmt:formatNumber>
						</td>
						<td>${businessBalance.remark}</td>
					</tr>
				</c:forEach>
				<tr>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><fmt:formatNumber type="currency">${sumPayMoney}</fmt:formatNumber></td>
					<td></td>
				</tr>
				<tr>
				    <td colspan="8">
				        <br/>
                        <br/>
                        <br/>
                        <br/>
				    </td>
				</tr>
	            <tr>
	                <td></td>
	                <td>业务部门签字：</td>
	                <td></td>
	                <td>财务部门签字：</td>
                    <td></td>
	                <td>领导签字：</td>
                    <td colspan="2"></td>
	            </tr>
			</tbody>
		</table>
	</div>
</body>
</html>