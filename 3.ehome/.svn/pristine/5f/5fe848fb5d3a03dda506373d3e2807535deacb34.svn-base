<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/jquery-address/jsAddress.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#inputForm").validate({
            rules : {
                depositBank : {
                    required : true,
                },
                accountName : {
                    required : true
                },
                bankAccount : {
                    required : true
                }
            },
            messages : {
                depositBank : {
                    required : "请输入开户银行"
                },
                accountName : {
                    required : "请输入开户名称"
                },
                bankAccount : {
                    required : "请输入银行账号"
                }
            },
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
    })
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
        <li>
            <span><a href="${ctx}/business/businessInfo">商户管理 </a> > 银行账号 </span>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="businessInfo" action="${ctx}/business/businessInfo/saveBank" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <label class="control-label">${businessInfo.businessName}</label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开户银行：</label>
            <div class="controls">
                <form:input path="depositBank" htmlEscape="false" maxlength="32" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开户名称：</label>
            <div class="controls">
                <form:input path="accountName" htmlEscape="false" maxlength="32" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">银行账号：</label>
            <div class="controls">
                <form:input path="bankAccount" htmlEscape="false" maxlength="32" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="business:businessInfo:editBank">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>