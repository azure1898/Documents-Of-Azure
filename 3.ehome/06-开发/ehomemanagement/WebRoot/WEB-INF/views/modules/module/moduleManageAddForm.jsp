<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模块管理管理</title>
	<meta name="decorator" content="default"/>
<%-- 	<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" /> --%>
<%-- 		    <script src="${ctxStatic}/common/singlefileUpload.js"></script> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			jQuery.validator.addMethod("checkSpecialCharacters", function(value, element,params) {
			    var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
			    return this.optional(element) || (patrn.test(value));
			}, "请输入中文，英文或数字");
			$("#inputForm").validate({
				ignore:[],
				submitHandler: function(form){
					if (setImagePreview()) {
						loading('正在提交，请稍等...');
						form.submit();
					}
				},
				rules : {
					moduleName : {
    					required : true,
    					checkSpecialCharacters : true,
						remote : {
							type:"POST",
				               url:"${ctx}/module/moduleManage/checkModuleName",
				               data:{
				            	   modulename:function(){return $("#moduleName").val();},
						           oldModulename:'${moduleManage.moduleName}'
				               } 
				              } 
				         }
				},
				messages : {
					moduleName : {
						remote : "模块名称已存在",
						required : "请输入模块名称"
					},
					moduleType : {
						required : "请选择模块类别"
					},
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
			
			// 删除模块类别“主导航”选项
			$("#moduleType1").parent().remove();
			
			// 增大单选框宽度
			$.each($("input[name='moduleType']"),function(index,value,array){
				$(value).next().css("width","90px");
			});
			
			// 模块类别默认选中生活
			if($("input[name='moduleType']:checked").length == 0){
				$("input[name='moduleType']").eq($("input[name='moduleType']").length-1).attr("checked","true");
			}
			
			$("input[name='moduleType']").change(function(){
	    		var msgModuleTypey = "请选择模块类别";
	    		 // 手动清除模块类别必填提示
	    	    var $labelError = $("input[name='moduleType']").parent().parent().children("label.error");
	    	    if ($labelError.size() != 0) {
	    	        $.each($labelError,function(index,value,array){
	    	       		if ($(value).text() == msgModuleTypey) {
	    	            	$(value).remove();
	    	       		}
	    	        });
	    	    }
    		});
		});
		
		function synchroModuleicon(elem){
			setImagePreview();
			$("#selectedFile").val($(elem).val());
			
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
		<li class="active"><a href="${ctx}/module/moduleManage/form?id=${moduleManage.id}">模块管理<shiro:hasPermission name="module:moduleManage:edit">${not empty moduleManage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="module:moduleManage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="moduleManage" action="${ctx}/module/moduleManage/save" method="post" enctype="multipart/form-data"  class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模块名称：</label>
			<div class="controls">
				<form:input path="moduleName" htmlEscape="false" maxlength="64" class="required moduleName"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模块类别：</label>
			<div class="controls">
				<form:radiobuttons path="moduleType" items="${fns:getDictList('module_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required moduleType"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传图标：</label>
			<div class="control-group" id="localImag">
				<div class="controls">
					<input type="file" name="file" id="btn_file" onchange="synchroModuleicon(this)">
					<input type="hidden" id="selectedFile" name="selectedFile" value="${moduleManage.moduleIcon}" class="required">
					<c:choose>
						<c:when test="${not empty moduleManage.moduleIcon}">
							<img id="imgPreview" src="${moduleManage.moduleIcon}" alt="图标" style="width:45px; height:45px;" onclick="openBrowse();" />
						</c:when>
						<c:otherwise>
							<img id="imgPreview" src="" onclick="openBrowse();" style="display:none; width:45px; height:45px;"/>
						</c:otherwise>
					</c:choose>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
<!-- 				<div id="localImag"> -->
<!--                 	<div class="controls"> -->
<!--                     <div class="img-box full" id="imgArea"> -->
<!--                         <section class=" img-section"> -->
<!--                             <div class="z_photo upimg-div clear" style="width: 150px;"> -->
<%--                                 <c:if test="${moduleManage.moduleIcon != '' && moduleManage.moduleIcon !=null}"> --%>
<!--                                     <section class="up-section fl"> -->
<!--                                         <span class="up-span"></span> -->
<%--                                         <img src="${ctxStatic}/images/a7.png" class="close-upimg"> <img src="${moduleManage.moduleIcon}" class="up-img"> --%>
<!--                                     </section> -->
<%--                                 </c:if> --%>
<%--                                 <section class="z_file fl" <c:if test="${moduleManage.moduleIcon != '' && moduleManage.moduleIcon !=null}">style="display: none;"</c:if>> --%>
<%--                                     <img src="${ctxStatic}/images/a10.png" class="add-img"> --%>
<!--                                     <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" /> -->
<!--                                 </section> -->
<!--                             </div> -->
<!--                         </section> -->
<!--                     </div> -->
<!--                     <aside class="mask works-mask"> -->
<!--                         <div class="mask-content" id="imgAside"> -->
<!--                             <p class="del-p ">您确定要删除图片吗？</p> -->
<!--                             <p class="check-p"> -->
<!--                                 <span class="del-com wsdel-ok">确定</span> -->
<!--                                 <span class="wsdel-no">取消</span> -->
<!--                             </p> -->
<!--                         </div> -->
<!--                     </aside> -->
<!--                 </div> -->
<!--             </div> -->
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="module:moduleManage:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/module/moduleManage/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>

</body>
</html>