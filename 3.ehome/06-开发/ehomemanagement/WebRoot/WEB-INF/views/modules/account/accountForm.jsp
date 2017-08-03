<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li><a href="${ctx}/account/account/">用户列表</a></li>
		<li class="active"><a href="${ctx}/account/account/form?id=${account.id}">用户<shiro:hasPermission name="account:account:edit">${not empty account.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="account:account:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="account" action="${ctx}/account/account/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户浏览过的最后一个楼盘的ID：</label>
			<div class="controls">
				<form:input path="villageInfoId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账号/手机号：</label>
			<div class="controls">
				<form:input path="phoneNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				<form:input path="pwd" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<form:input path="photo" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:input path="sex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身高：</label>
			<div class="controls">
				<form:input path="height" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年份：</label>
			<div class="controls">
				<form:input path="birthYear" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">体重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端来源：</label>
			<div class="controls">
				<form:select path="terminalSource" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('terminal_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="certifyState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('certify_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册时间：</label>
			<div class="controls">
				<input name="registDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${account.registDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否配置手环：</label>
			<div class="controls">
				<form:select path="bindBraceletFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bind_bracelet_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手环名称：</label>
			<div class="controls">
				<form:input path="braceletName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">钱包余额：</label>
			<div class="controls">
				<form:input path="walletBalance" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手环序列号：</label>
			<div class="controls">
				<form:input path="braceletNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">钱包本金：</label>
			<div class="controls">
				<form:input path="walletPrincipal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人脸识别采集状态：</label>
			<div class="controls">
				<form:select path="faceRecognitionFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('face_recognition_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">钱包赠送：</label>
			<div class="controls">
				<form:input path="walletPresent" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用状态：</label>
			<div class="controls">
				<form:select path="useState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('use_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<shiro:hasPermission name="account:account:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/account/account/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>