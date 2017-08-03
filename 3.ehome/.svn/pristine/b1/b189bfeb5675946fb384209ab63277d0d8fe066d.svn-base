<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模块管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				ignore:[],
				submitHandler: function(form){
					if (setImagePreview()) {
						loading('正在提交，请稍等...');
						form.submit();
					}
				},
				messages : {
					selectedFile : {
						required : "请上传模块图标"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.appendTo(element.parent());
					}
				}
			});
		});
		
		function synchroModuleicon(elem){
			setImagePreview();
			$("#selectedFile").focus();
			$("#selectedFile").val($(elem).val());
            $("#selectedFile").blur();
            
        	 // 手动清除图标必须上传提示
            var msgBtnFile = "请上传模块图标";
            var $labelError = $(elem).parent().children("label.error");
            if ($labelError.size() != 0) {
                $.each($labelError,function(index,value,array){
               		if ($(value).text() == msgBtnFile) {
                    	$(value).remove();
               		}
                });
            }
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/module/moduleManage/form?id=${moduleManage.id}">模块管理<shiro:hasPermission name="module:moduleManage:edit">修改</shiro:hasPermission><shiro:lacksPermission name="module:moduleManage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="moduleManage" action="${ctx}/module/moduleManage/save" method="post" enctype="multipart/form-data" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模块名称：</label>
			<label class="control-label" style="text-align: left;">${moduleManage.moduleName}</label>
		</div>
		<div class="control-group">
			<label class="control-label">上传图标：</label>
			<div class="control-group" id="localImag">
				<div class="controls">
					<input type="file" name="file" id="btn_file" onchange="synchroModuleicon(this)">
					<input type="hidden" id="selectedFile" name="selectedFile" value="${moduleManage.moduleIcon}" class="required">
					<c:choose>
						<c:when test="${not empty moduleManage.moduleIcon}">
							<img id="imgPreview" src="${moduleManage.moduleIcon}" alt="图标" style="width:45px; height:45px;" onclick="openBrowse();"/>
						</c:when>
						<c:otherwise>
							<img id="imgPreview" src="" onclick="openBrowse();" style="display:none; width:45px; height:45px;"/>
						</c:otherwise>
					</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="module:moduleManage:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/module/moduleManage/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>