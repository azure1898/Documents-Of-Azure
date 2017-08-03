<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户服务范围管理</title>
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
		<li><a href="${ctx}/business/businessServicescope/">商户服务范围列表</a></li>
		<li class="active"><a href="${ctx}/business/businessServicescope/form?id=${businessServicescope.id}">商户服务范围<shiro:hasPermission name="business:businessServicescope:edit">${not empty businessServicescope.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:businessServicescope:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessServicescope" action="${ctx}/business/businessServicescope/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商户基本信息ID：</label>
			<div class="controls">
				<form:select path="businessinfoId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${allBusinessInfo}" itemLabel="businessName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">楼盘信息ID：</label>
			<div class="controls">
				<form:select path="villageInfoId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${allVillageInfo}" itemLabel="villageName" itemValue="id" htmlEscape="false"/>
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
			<shiro:hasPermission name="business:businessServicescope:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>