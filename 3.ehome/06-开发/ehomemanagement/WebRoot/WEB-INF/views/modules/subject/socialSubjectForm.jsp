<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>话题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		fillPro(); // 加载全部省市区数据
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
		<li class="active"><a href="${ctx}/subject/socialSubject/form?id=${socialSubject.id}">话题<shiro:hasPermission name="subject:socialSubject:edit">${not empty socialSubject.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="subject:socialSubject:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="socialSubject" action="${ctx}/subject/socialSubject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">话题名称：</label>
			<div class="controls">
				<form:input path="subname" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">是否推荐：</label>
            <div class="controls">
                <form:radiobuttons path="isrecommend" items="${fns:getDictList('Quota')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">*发布楼盘：</label>
            <div class="controls">
                <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
                <select id="addrcity" name="addrCity" style="width: 120px" onchange="changeVillage()">
                    <option value="">全部城市</option>
                </select>
                <select id="addrarea" name="addrArea" style="display: none;">
                    <option value="">全部区域</option>
                </select>
                <select id="addrVillage" name="villageInfoId" class="required" style="width: 120px">
                    <option value="">全部楼盘</option>
                </select>
                <input type="text" class="hide" id="hidProId" value="">
                <input type="text" class="hide" id="hidCityId" value="">
                <input type="text" class="hide" id="hidAreaId" value="">
                <input type="text" class="hide" id="hidVillageId" value="${couponManage.villageInfoId}">
            </div>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="subject:socialSubject:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>