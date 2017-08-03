<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物业结算信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnCheck").click(function() {
		        var ids = $("#checkedId").val();
		        if (ids == "") {
		            alertx("请选择批量结算的结算单");
		            return;
		        } else {
		            var href = "${ctx}/balance/propertyBalance/batchBalance?ids=" + ids;
		            return confirmx('确认要结算？结算后这批结算单将成为已结算状态', href);
		        }
		    });
		    $("#btnExport").click(function() {
		        top.$.jBox.confirm("确认要导出结算申请单吗？", "系统提示",
		        function(v, h, f) {
		            if (v == 'ok') {
		                $("#searchForm").prop("action", "${ctx}/balance/propertyBalance/export");
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
		/*
		 * 全选
		 */
		function batchBalance(obj){
		    var checked = obj.checked;
		    if (!checked) {
		        $("#checkedId").val("");
		    }
		    var checkBoxs = $('input[name="itemCheckbox"]');
		    for (var i = 0; i < checkBoxs.length; i++) {
		        checkBoxs[i].checked = checked;
		        if (checkBoxs[i].checked) {
		            var checkedId = $("#checkedId").val() + checkBoxs[i].value + ",";
		            $("#checkedId").val(checkedId);
		        }
		    } 
		}
		/*
		 * 单选
		 */
		function itemCheck(obj) {
		    $("#checkedId").val("");
		    var checkBoxs = $('input[name="itemCheckbox"]');
		    for (var i = 0; i < checkBoxs.length; i++) {
		        if (checkBoxs[i].checked) {
		            var checkedId = $("#checkedId").val() + checkBoxs[i].value + ",";
		            $("#checkedId").val(checkedId);
		        }
		    }
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
        <li><a href="${ctx}/balance/businessBalance/">商家结算信息列表</a></li>
        <li class="active"><a href="${ctx}/balance/propertyBalance/">物业结算信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="propertyBalance" action="${ctx}/balance/propertyBalance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li>
                <input name="balanceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${propertyBalance.balanceStartTime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </li>
            <li>
                <input name="balanceEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${propertyBalance.balanceEndTime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </li>
			<li>
				<form:select path="propertyCompanyId" class="input-medium">
					<form:option value="" label="全部物业"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
            <li>
                <form:select path="checkState" class="input-medium">
                    <form:option value="" label="核对状态"/>
                    <form:options items="${fns:getDictList('check_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li>
                <form:select path="balanceState" class="input-medium">
                    <form:option value="" label="结算状态"/>
                    <form:options items="${fns:getDictList('balance_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li>
                <form:input path="companyName" placeholder="物业名称" htmlEscape="false" maxlength="64" class="input-medium"/>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
		<!-- 操作按钮 start -->
		<div style="margin: 10px">
			<div class="div-inline">
				<input type="checkbox" onclick="batchBalance(this)"
					title="支持申请结算的记录前会出现勾选框；已结算的结算单不能重复申请结算；待核对的结算单需核对后才能申请结算">全选
			</div>
			<div class="div-inline">
				<shiro:hasPermission name="balance:propertyBalance:edit">
					<a class="btn btn-primary" id="btnCheck" href="#"><i
						class="icon-plus icon-custom"></i> 批量结算</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="balance:propertyBalance:view">
					<a id="btnExport" href="#" class="btn btn-primary"><i
						class="icon-edit icon-custom"></i> 导出结算申请单</a>
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
                <th>城市</th>
				<th>物业名称</th>
				<th>结算周期</th>
				<th>结算状态</th>
				<th>订单金额</th>
				<th>平台优惠</th>
				<th>扣点金额</th>
				<th>应付金额</th>
				<shiro:hasPermission name="balance:propertyBalanceDetail:view">
                <th>操作</th>
                </shiro:hasPermission>
                <shiro:hasPermission name="balance:propertyBalance:edit">
                <th>核对状态</th>
                </shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="propertyBalance" varStatus="status">
			<tr>
			    <td>
                    <c:if test="${propertyBalance.balanceState==0 && propertyBalance.checkState !=0}">
                       <input type="checkbox" name="itemCheckbox"
                          onclick="itemCheck(this)" value="${propertyBalance.id}">
                    </c:if>
                        ${status.count}
                    </td>
                <td>${propertyBalance.cityName}</td>
				<td>
					${propertyBalance.companyName}
				</td>
				<td>
					<fmt:formatDate value="${propertyBalance.balanceStartTime}" pattern="yyyy-MM-dd"/>
				    ~
					<fmt:formatDate value="${propertyBalance.balanceEndTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(propertyBalance.balanceState, 'check_state', '')}
				</td>
				<td>
					<fmt:formatNumber type="currency">${propertyBalance.orderMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber type="currency">${propertyBalance.couponMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber type="currency">${propertyBalance.deductionMoney}</fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber type="currency">${propertyBalance.payMoney}</fmt:formatNumber>
				</td>
				<shiro:hasPermission name="balance:propertyBalanceDetail:view">
				<td>
    				<a href="${ctx}/balance/propertyBalanceDetail/list?propertyBalanceId=${propertyBalance.id}&balanceStartTime=<fmt:formatDate
                           value='${propertyBalance.balanceStartTime}' pattern='yyyy-MM-dd' />&balanceEndTime=<fmt:formatDate
                           value='${propertyBalance.balanceEndTime}' pattern='yyyy-MM-dd' />">结算单明细</a>
				</td>
				</shiro:hasPermission>
                <shiro:hasPermission name="balance:propertyBalance:edit">
                <td>
                    <a href="${ctx}/balance/propertyBalance/check?id=${propertyBalance.id}">
                        ${fns:getDictLabel(propertyBalance.checkState, 'checkState', '')}
                    </a>
                </td>
                </shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
    <p align="center">
        <span class="help-inline"> 
          <font color="red">
		                  结算金额=订单金额+平台优惠-扣点金额<br/>
		                  订单金额=物业费用--平台优惠
          </font>
        </span>
    </p>
</body>
</html>