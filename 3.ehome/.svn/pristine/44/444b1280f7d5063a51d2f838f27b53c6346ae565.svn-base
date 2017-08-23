<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//新增
			if('${area.parent.id}' ==1){
				$("#areaName").val("");
			}
			
			//check上级区域必填
            jQuery.validator.addMethod("checkParent", function(value, element,params) {          	
                var type = $("#type").val();
                var areaName = $("#areaName").val();
                
                if(type == 3 || type == 4){
                	if(areaName ==''|| areaName==null || areaName=='undefined'){
                		return false;
                	}else{
                		return true;
                	}  
                }else{
                	return true;
                }
    	    }, "请输入上级区域");
			
			$("#inputForm").validate({
				onkeyup: false ,
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules : {
					checkParent:{
						checkParent:"params"
	                }
				},
				messages : {
					  name : {
				              required : "请输入区域名称"
					  },
					  checkParent:{
						  checkParent:"请输入上级区域"
		              }
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

		$(function(){
			//清除上级区域必填消息
			$("#areaName,#areaButton").click(function(){
				//手动清除上级区域必填提示
			    var msgParent= "请输入上级区域";
				//手动清除上级区域必填提示
			    var $labelError = $("#areaName").parents().find("label.error");
			    if ($labelError.size() != 0) {
			        $.each($labelError,function(index,value,array){
			       		if ($(value).text() == msgParent) {
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
      <li>
         <span class="common-breadcrumb">基础资料 &nbsp;>&nbsp;<a href="${ctx}/sys/area/">城市管理&nbsp;>&nbsp;</a><c:if test="${editFlag==1}">添加下级区域</c:if><c:if test="${editFlag!=1}">${not empty area.id?'编辑':'添加'}区域</c:if></span>
      </li>
    </ul>
	<form:form id="inputForm" modelAttribute="area" action="${ctx}/sys/area/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级区域:</label>
			<div class="controls">
			<c:choose>
			   <c:when test="${editFlag==1}">
				     <sys:treeselect id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
					     title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="false" disabled="disabled"/>
			   </c:when>
			   <c:otherwise>		   
				     <sys:treeselect id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
					     title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="false"/>
			   </c:otherwise>			   
			</c:choose>
			<input id="checkParent" name="checkParent" value="" style="width: 0px;height: 0px;border: 0px;">
			</div>
		</div>
		<div class="control-group">
		    
			<label class="control-label" ><span class="help-inline"><font color="red">*</font></span>区域名称:</label>
			<div class="controls">			    
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>				
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font></span>&nbsp;&nbsp;&nbsp;区域编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font></span>区域类型:</label>
			<div class="controls">
				<form:select path="type" id="type" class="input-large required" style="width:220px;">
					<form:options items="${fns:getDictList('sys_area_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:area:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>