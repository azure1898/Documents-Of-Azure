<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物业结算明细信息管理</title>
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
		<li><a href="${ctx}/balance/propertyBalanceDetail/">物业结算明细信息列表</a></li>
		<li class="active"><a href="${ctx}/balance/propertyBalanceDetail/form?id=${propertyBalanceDetail.id}">物业结算明细信息<shiro:hasPermission name="balance:propertyBalanceDetail:edit">${not empty propertyBalanceDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="balance:propertyBalanceDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="propertyBalanceDetail" action="${ctx}/balance/propertyBalanceDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物业结算信息表ID：</label>
			<div class="controls">
				<form:select path="businessBalanceId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物业公司ID：</label>
			<div class="controls">
				<form:select path="propertyCompanyId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">楼盘ID：</label>
			<div class="controls">
				<form:select path="villageInfoId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模块ID：</label>
			<div class="controls">
				<form:select path="moduleId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<form:select path="orderNo" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品模式：0商品购买  1服务预约  2课程购买  3场地预约：</label>
			<div class="controls">
				<form:select path="prodType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('prod_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单号/团购券码：</label>
			<div class="controls">
				<form:input path="orderGroupNo" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单金额：</label>
			<div class="controls">
				<form:input path="orderMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台优惠金额：</label>
			<div class="controls">
				<form:input path="couponMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣点金额：</label>
			<div class="controls">
				<form:input path="deductionMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应付金额：</label>
			<div class="controls">
				<form:input path="payMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款方式：</label>
			<div class="controls">
				<form:select path="payType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付机构(0微信1支付宝2平台钱包)：</label>
			<div class="controls">
				<form:select path="payOrg" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_org')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易完成时间：</label>
			<div class="controls">
				<input name="tradeCompleteTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${propertyBalanceDetail.tradeCompleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付对账状态(修改状态时两边都要修改)：</label>
			<div class="controls">
				<form:select path="payCheckState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('check_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<shiro:hasPermission name="balance:propertyBalanceDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>