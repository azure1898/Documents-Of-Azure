<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商家结算明细信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
		        top.$.jBox.confirm("确认要导出结算明细吗？", "系统提示",
		        function(v, h, f) {
		            if (v == 'ok') {
		                $("#searchForm").prop("action", "${ctx}/balance/businessBalanceDetail/export");
		                $("#searchForm").submit();
		            }
		        },
		        {
		            buttonsFocus: 1
		        });
		        top.$('.jbox-body .jbox-icon').css('top', '55px');
		    });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
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
		<li class="active"><a href="${ctx}/balance/businessBalanceDetail/">结算单明细</a></li>
		<shiro:hasPermission name="balance:businessBalanceDetail:edit"><li><a href="${ctx}/balance/businessBalanceDetail/form">商家结算明细信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessBalanceDetail"
		action="${ctx}/balance/businessBalanceDetail/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
        <input type="hidden" name="businessBalanceId"
            value="${businessBalanceDetail.businessBalanceId}" />
		<input type="hidden" name="balanceStartTime"
			value="<fmt:formatDate value='${businessBalanceDetail.balanceStartTime}' pattern='yyyy-MM-dd'/>" />
		<input type="hidden" name="balanceEndTime"
			value="<fmt:formatDate value='${businessBalanceDetail.balanceEndTime}' pattern='yyyy-MM-dd'/>" />
		<ul class="ul-form">
			<li><input name="balanceStartTimeShow" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value='${businessBalanceDetail.balanceStartTime}' pattern='yyyy-MM-dd'/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
				disabled /></li>
			<li><input name="balanceEndTimeShow" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value='${businessBalanceDetail.balanceEndTime}' pattern='yyyy-MM-dd'/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
				disabled /></li>
			<li><form:select path="payType" class="input-medium">
					<form:option value="" label="付款方式" />
					<form:options items="${fns:getDictList('pay_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li style="float: right"><a class="btn btn-primary"
				href="${ctx}/balance/businessBalance/"><i
					class="icon-eye-open icon-custom"></i> 返回</a></li>
			<li class="clearfix"></li>
		</ul>
		<!-- 操作按钮 start -->
		<div style="margin: 10px">
			<div class="div-inline">
				<shiro:hasPermission name="balance:businessBalanceDetail:view">
					<a id="btnExport" href="#" class="btn btn-primary"><i
						class="icon-edit icon-custom"></i> 导出结算明细</a>
				</shiro:hasPermission>
			</div>
	        <div class="div-inline">
	            <span class="help-inline"> <font color="red"> 订单金额： <fmt:formatNumber
	                        type="currency">${sumOrderMoney}</fmt:formatNumber> 元 平台优惠： <fmt:formatNumber
	                        type="currency">${sumCouponMoney}</fmt:formatNumber> 元 扣点金额： <fmt:formatNumber
	                        type="currency">${sumDeductionMoney}</fmt:formatNumber> 元 应付金额： <fmt:formatNumber
	                        type="currency">${sumPayMoney}</fmt:formatNumber> 元
	            </font></span>
	        </div>
		</div>
		<!-- 操作按钮 end -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>序号</th>
				<th>商户名称</th>
				<th>模块名称</th>
				<th>订单号/团购券号</th>
				<th>订单金额</th>
				<th>平台优惠</th>
				<th>扣点金额</th>
				<th>应付金额</th>
				<th>付款方式</th>
				<th>交易完成时间</th>
				<th>支付对账状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessBalanceDetail" varStatus="status">
			<tr>
			    <td>
                    ${status.count }
			    </td>
				<td>
					${businessBalanceDetail.businessInfoId}
				</td>
				<td>
					${businessBalanceDetail.moduleId}
				</td>
				<td>
					${businessBalanceDetail.orderNo}
				</td>
				<td>
					<fmt:formatNumber  type="currency">${businessBalanceDetail.orderMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber  type="currency">${businessBalanceDetail.couponMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber  type="currency">${businessBalanceDetail.deductionMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber  type="currency">${businessBalanceDetail.payMoney}</fmt:formatNumber>
				</td>
				<td>
					${fns:getDictLabel(businessBalanceDetail.payType, 'pay_type', '未知')}
				</td>
				<td>
					<fmt:formatDate value="${businessBalanceDetail.tradeCompleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(businessBalanceDetail.payCheckState, 'check_order_state', '未对账')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>