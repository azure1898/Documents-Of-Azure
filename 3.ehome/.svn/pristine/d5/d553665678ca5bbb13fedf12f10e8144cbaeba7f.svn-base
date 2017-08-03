<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>楼盘信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			fillPro();
			$("#inputForm").validate({
    			onkeyup: false ,
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules : {
    				phoneNum : {
       					required : true,
        				isPhoneNumber : true
    				}
				},
				messages : {
					villageName : {
				        required : "请输入楼盘名称"
				    },
				    addrCity : {
					    required : "请选择所属城市"
					},
					companyInfoId : {
						required : "请选择所属公司"
					},
					propertyCompanyId : {
					    required : "请选择所属物业"
					},
					phoneNum : {
    					required : "请输入楼盘电话"
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
			
			$("#addrcity").change(function(){
    			var msgAddrCity = "请选择所属城市";
				 // 手动清除城市必填提示
			    var $labelError = $("#addrcity").parent().children("label.error");
			    if ($labelError.size() != 0) {
			        $.each($labelError,function(index,value,array){
			       		if ($(value).text() == msgAddrCity) {
			            	$(value).remove();
			       		}
			        });
			    }
			});
			
			$("#companyInfoId").change(function(){
    			var msgCompanyInfo = "请选择所属公司";
				 // 手动清除所属公司必填提示
			    var $labelError = $("#companyInfoId").parent().children("label.error");
			    if ($labelError.size() != 0) {
			        $.each($labelError,function(index,value,array){
			       		if ($(value).text() == msgCompanyInfo) {
			            	$(value).remove();
			       		}
			        });
			    }
			});
			
			$("#propertyCompanyId").change(function(){
    			var msgPropertyCompany = "请选择所属物业";
				 // 手动清除所属物业必填提示
			    var $labelError = $("#propertyCompanyId").parent().children("label.error");
			    if ($labelError.size() != 0) {
			        $.each($labelError,function(index,value,array){
			       		if ($(value).text() == msgPropertyCompany) {
			            	$(value).remove();
			       		}
			        });
			    }
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/village/villageInfo/form?id=${villageInfo.id}"><shiro:hasPermission name="village:villageInfo:edit">编辑</shiro:hasPermission><shiro:lacksPermission name="village:villageInfo:edit">查看</shiro:lacksPermission>楼盘</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="villageInfo" action="${ctx}/village/villageInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="text" class="hide" id="hidProId"  value="${villageInfo.addrPro}">
	 	<input type="text" class="hide" id="hidCityId" value="${villageInfo.addrCity}">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">楼盘名称：</label>
			<div class="controls">
				<form:input path="villageName" htmlEscape="false" maxlength="128" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属城市：</label>
			<div class="controls">
				    <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
				     	<option value="">全部省份</option>
				    </select>
				    <select id="addrcity" name="addrCity" style="width: 120px" class="required">
				     	<option value="">全部城市</option>
				    </select>
				    <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属公司：</label>
			<div class="controls">
				<form:select path="companyInfoId" class="input-medium required" >
<!-- 				选择项为数据库目前写死的公司名称 -->
<%-- 					<form:option value="" label="选择所属地产"/> --%>
					<form:options items="${companyInfoMap}" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属物业：</label>
			<div class="controls">
				<form:select path="propertyCompanyId" class="input-medium required">
<!-- 				选择项为数据库目前写死的物业名称 -->
<%-- 					<form:option value="" label="选择所属物业"/> --%>
					<form:options items="${propertyCompanyMap}" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">楼盘电话：</label>
			<div class="controls">
				<form:input path="phoneNum" htmlEscape="false" maxlength="18" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="village:villageInfo:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/village/villageInfo/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>