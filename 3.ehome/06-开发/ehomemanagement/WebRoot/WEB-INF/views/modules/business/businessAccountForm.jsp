<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    request.setCharacterEncoding("UTF-8");
    String businessinfoId = request.getParameter("businessinfoId");
    String prodType = request.getParameter("prodType");
%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	var prodType="<%=prodType%>";
    $(document).ready(function() {
        var roleIdList="";
        if(prodType.indexOf("0")>=0){//商品
            roleIdList+="2,"
        }if(prodType.indexOf("1")>=0){//服务
            roleIdList+="3,"
        }if(prodType.indexOf("2")>=0){//课程
            roleIdList+="4,"
        }if(prodType.indexOf("3")>=0){//场地
            roleIdList+="5,"
        }
        console.log(prodType.indexOf("0"));
        console.log(prodType.indexOf("1"));
        console.log(prodType.indexOf("2"));
        console.log(prodType.indexOf("3"));
        console.log(roleIdList);
        $("#roleIdList").val(roleIdList);
        $("#no").focus();
        jQuery.validator.addMethod("isPhone", function(value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
            var tel = /^\d{3,4}-?\d{7,9}$/;
            if (/-/.test(value)) {
                return this.optional(element) || tel.test(value);
            } else {
                return this.optional(element) || (length == 11 && mobile.test(value));
            }
        }, "请正确填写您的联系电话");
        $("#inputForm").validate({
            rules : {
                loginName : {
                    required : true,
                    remote : "${ctx}/sys/user/checkLoginName2?oldLoginName="+ encodeURIComponent('${user.loginName}')
                },
                mobile:{
                    required: true,
                    minlength : 11,
                    isPhone : true
                }
                
            },
            messages : {
                loginName : {
                    required : "请输入账号名",
                    remote : "账号名已存在"
                },
                newPassword : {
                    required : "请输入账号密码"
                },
                confirmNewPassword : {
                    required : "请输入确认密码",
                    equalTo : "请输入与上面相同的密码"
                },
                name : {
                    required : "请输入姓名"
                },
                mobile : {
                    required : "请输入联系电话",
                    isPhone:"请正确填写您的联系电话"
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
    });
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li> <span><a href="${ctx}/business/businessInfo">商户管理 </a>> 
        <a href="${ctx}/business/businessAccount?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>" >管理账户</a>
        > ${not empty user.id?'编辑':'新增'}账号
        </span>
        </li>
    </ul>
    <form:form id="inputForm" modelAttribute="user" action="${ctx}/business/businessAccount/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <input name="role.id" value="<%=prodType%>" type="hidden"/>
        <form:hidden path="userType" value="2" />
        <form:hidden path="businessinfoId" />
        <sys:message content="${message}" />
        <div class="control-group" style="display: none">
            <label class="control-label">归属公司:</label>
            <div class="controls">
                <sys:treeselect id="company" name="company.id" value="1" labelName="company.name" labelValue="${user.company.name}" title="公司" url="/sys/office/treeData?type=1" cssClass="required" />
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">归属部门:</label>
            <div class="controls">
                <sys:treeselect id="office" name="office.id" value="1" labelName="office.name" labelValue="${user.office.name}" title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true" />
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">工号:</label>
            <div class="controls">
                <form:input path="no" htmlEscape="false" value="0002" maxlength="50" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">用户状态:</label>
            <div class="controls">
                <input name="loginFlag" value="${not empty user.id?user.loginFlag:1}" htmlEscape="false" maxlength="100" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">账号名:</label>
            <div class="controls">
                <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                <form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">账号密码:</label>
            <div class="controls">
                <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}" />
                <c:if test="${empty user.id}">
                    <span class="help-inline"><font color="red">*</font> </span>
                </c:if>
                <%--  <c:if test="${not empty user.id}">
                    <span class="help-inline">若不修改密码，请留空。</span>
                </c:if> --%>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">确认密码:</label>
            <div class="controls">
                <input id="confirmNewPassword" class="${not empty user.id?'':'required'} " name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" />
                <c:if test="${empty user.id}">
                    <span class="help-inline"><font color="red">*</font> </span>
                </c:if>
                <c:if test="${not empty user.id}">
                    <span class="help-inline">若不修改密码，请留空。</span>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">姓名:</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="20" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系电话:</label>
            <div class="controls">
                <form:input path="mobile" htmlEscape="false" maxlength="11" class="required"  />
                 <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" style="display: none"><!--  -->
            <label class="control-label">用户角色:</label>
            <div class="controls">
                <input id="roleIdList" name="roleIdList" value="b093d09a5ecd4fc1b55ca4f0cea71b57"> 
            <%--     <form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required" /> --%>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="sys:user:edit">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>