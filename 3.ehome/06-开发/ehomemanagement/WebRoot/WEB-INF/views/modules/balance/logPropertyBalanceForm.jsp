<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物业结算操作日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/balance/logPropertyBalance/">物业结算操作日志列表</a></li>
		<li class="active"><a href="${ctx}/balance/logPropertyBalance/form?id=${logPropertyBalance.id}">物业结算操作日志<shiro:hasPermission name="balance:logPropertyBalance:edit">${not empty logPropertyBalance.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="balance:logPropertyBalance:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="logPropertyBalance" action="${ctx}/balance/logPropertyBalance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">操作类型：</label>
			<div class="controls">
				<form:select path="operationType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('operation_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批次号：</label>
			<div class="controls">
				<form:input path="batchNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物业结算信息表ID：</label>
			<div class="controls">
				<form:input path="propertyBalanceId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物业公司ID：</label>
			<div class="controls">
				<form:input path="propertyCompanyId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算周期：</label>
			<div class="controls">
				<form:select path="balanceCycle" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('balancecycle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算周期开始时间：</label>
			<div class="controls">
				<input name="balanceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${logPropertyBalance.balanceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算周期结束时间：</label>
			<div class="controls">
				<input name="balanceEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${logPropertyBalance.balanceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算模式：</label>
			<div class="controls">
				<form:select path="balanceModel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('balancemodel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算状态：</label>
			<div class="controls">
				<form:select path="balanceState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单金额：</label>
			<div class="controls">
				<form:input path="orderMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台优惠金额：</label>
			<div class="controls">
				<form:input path="couponMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣点金额：</label>
			<div class="controls">
				<form:input path="deductionMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应付金额：</label>
			<div class="controls">
				<form:input path="payMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">核对状态：</label>
			<div class="controls">
				<form:select path="checkState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('checkState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="balance:logPropertyBalance:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>