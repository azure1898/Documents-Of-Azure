<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>退款信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        //$("#name").focus();
        $("#inputForm").validate({
            submitHandler : function(form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer : "#messageBox",
            errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
        <li><a href="${ctx}/order/orderRefundInfo/">退款信息列表</a></li>
        <li class="active"><a href="${ctx}/order/orderRefundInfo/form?id=${orderRefundInfo.id}">退款信息<shiro:hasPermission name="order:orderRefundInfo:edit">${not empty orderRefundInfo.id?'修改':'添加'}</shiro:hasPermission>
                <shiro:lacksPermission name="order:orderRefundInfo:edit">查看</shiro:lacksPermission></a></li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="orderRefundInfo" action="${ctx}/order/orderRefundInfo/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">商家id：</label>
            <div class="controls">
                <form:input path="businessInfoId" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">订单号：</label>
            <div class="controls">
                <form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">订单类型:0商品类 1服务类 2 课程培训类 3场地预约类 4团购类：</label>
            <div class="controls">
                <form:input path="orderType" htmlEscape="false" maxlength="1" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">订单支付方式：</label>
            <div class="controls">
                <form:input path="payType" htmlEscape="false" maxlength="1" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">订单金额：</label>
            <div class="controls">
                <form:input path="orderMoney" htmlEscape="false" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">终端类型(0 Android 1 IOS 2 商家后台)：</label>
            <div class="controls">
                <form:input path="type" htmlEscape="false" maxlength="1" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模块管理ID：</label>
            <div class="controls">
                <form:select path="moduleManageId" class="input-xlarge ">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品模式：0商品购买 1服务预约 2课程购买 3场地预约：</label>
            <div class="controls">
                <form:input path="prodType" htmlEscape="false" maxlength="64" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">退款金额：</label>
            <div class="controls">
                <form:input path="refundMoney" htmlEscape="false" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">退款单号：</label>
            <div class="controls">
                <form:input path="refundNo" htmlEscape="false" maxlength="64" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">退款类型(0微信1支付宝2平台钱包)：</label>
            <div class="controls">
                <form:input path="refundType" htmlEscape="false" maxlength="1" class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否退款:0否1是：</label>
            <div class="controls">
                <form:select path="refundState" class="input-xlarge ">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">退款完成时间：</label>
            <div class="controls">
                <input name="refundOverTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${orderRefundInfo.refundOverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">备注：</label>
            <div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " />
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="order:orderRefundInfo:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>