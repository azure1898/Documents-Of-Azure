<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>楼盘信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$.validator.addMethod("checkProductLine",function(value,element,params){
// 					alert("checkProductLine");
// 					alert($("input[name='villageLineId']:checked").length);
					return $("input[name='villageLineId']:checked").length != 0;
			},"请选择产品线");
			$("#inputForm").validate({
				ignore:[],
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules : {
					checkProductLines : {
						checkProductLine:"param"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else if (element.is("input:hidden[name='checkProductLines']")) {
						error.appendTo($("#villageLineDiv"));
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			// 初始化产品线
			initProductLine();
			
			$("input[name='villageLineId']").change(function(){
    			var msgPropertyCompany = "请选择产品线";
				 // 手动清除产品线必填提示
			    var $labelError = $("input[name='villageLineId']").parent().parent().children("label.error");
			    if ($labelError.size() != 0) {
			        $.each($labelError,function(index,value,array){
			       		if ($(value).text() == msgPropertyCompany && ($("input[name='villageLineId']:checked").length != 0)) {
			            	$(value).remove();
			       		}
			        });
			    }
			});
		});
		
		function initProductLine(){
			// 包含所具有的产品线的字典值的字符串，以半角逗号分隔
			var productLines = $("#productLines").val();
			var strs= new Array();
			// 所具有的产品线
			strs=productLines.split(",");
			
			$.each($("#villageLineDiv").find("span"),function(index,value,array){
				// 格式初始化
				if (index != 0) { 
					$(value).prepend("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				
				// check初始化
				var productLine = $(value).find("input[name='villageLineId']").eq(0).val();
				for (i=0;i<strs.length ;i++ ) { 
					if (productLine == strs[i]) {
						$(value).find("input[name='villageLineId']").eq(0).attr("checked","checked");
						break;
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/village/villageInfo/formProductLine?id=${id}">关联产品线</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="villageInfo" action="${ctx}/village/villageInfo/saveProductLine" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="productLines" value="${productLines}"/>
		<input type="hidden" name="checkProductLines" value="${productLines}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品线：</label>
			<div id="villageLineDiv" class="controls">
				<form:checkboxes path="villageLineId" items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<font color="red">&nbsp;&nbsp;&nbsp;*</font>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="village:villageInfo:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/village/villageInfo/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>