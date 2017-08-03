<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户分类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    jQuery.validator.addMethod("checkSpecialCharacters", function(value, element,params) {
		        var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
		        return this.optional(element) || (patrn.test(value));
		    }, "请输入中文，英文或数字");
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules : {
					categoryName : {
   						required : true,
   						checkSpecialCharacters : true,
//    						maxlength : 32,
						remote : {
							type:"POST",
				               url:"${ctx}/business/businessCategorydict/checkCategoryname",
				               data:{
				            		categoryname:function(){return $("#categoryName").val();},
									oldCategoryname:'${businessCategorydict.categoryName}'
				               } 
				         } 
				     },
				     categoryIntroduce : {
// 						maxlength : 32
				     }
				},
				messages : {
					categoryName : {
						remote : "商户分类名称已存在",
						required : "请输入分类名称"
					},
					prodType : {
						required : "请选择产品模式"
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
			
			$("input[name='prodType']").change(function(){
	    		var msgProdType = "请选择产品模式";
	    		 // 手动清除产品模式必填提示
	    	    var $labelError = $("input[name='prodType']").parent().parent().children("label.error");
	    	    if ($labelError.size() != 0) {
	    	        $.each($labelError,function(index,value,array){
	    	       		if ($(value).text() == msgProdType) {
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
		<li class="active"><a href="${ctx}/business/businessCategorydict/form?id=${businessCategorydict.id}">商户分类<shiro:hasPermission name="business:businessCategorydict:edit">${not empty businessCategorydict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:businessCategorydict:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessCategorydict" action="${ctx}/business/businessCategorydict/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}" type="error"/>		
		<div class="control-group">
			<label class="control-label">分类名称：</label>
			<div class="controls">
<!-- 			onChange="categorynameSynchro(this)"  -->
				<form:input path="categoryName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品模式：</label>
			<div class="controls">
				<form:radiobuttons path="prodType" items="${fns:getDictList('prod_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类介绍：</label>
			<div class="controls">
				<form:textarea path="categoryIntroduce" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="business:businessCategorydict:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/business/businessCategorydict/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>