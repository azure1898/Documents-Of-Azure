<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>位置信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            jQuery.validator.addMethod("checkSpecialCharacters", function(value, element,params) {
	            var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
	            return this.optional(element) || (patrn.test(value));
	        }, "请输入中文，英文或数字");
            $("#inputForm").validate({
//                 onfocusout:false,
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                rules : {
                    moduleCode : {
                        remote : {
                            type:"POST",
                               url:"${ctx}/adver/adverPosition/checkOpenScreenFlag",
                               data:{
                                    openScreenFlag:function(){
                                        if ($("#openScreenFlag:checked").length == 1) {
                                            return "1";
                                        } else {
                                            return "";
                                        }
                                    },
                                    oldOpenScreenFlag:'${adverPosition.openScreenFlag}',
                                    moduleCode:function(){return $("input[name='moduleCode']:checked").val();},
                                    oldModuleCode:'${adverPosition.moduleCode}'
                               } 
                           }
                    },
                    positionName : {
                        required : true,
                        checkSpecialCharacters : true,
                        remote : {
                        	type:"POST",
                               url:"${ctx}/adver/adverPosition/checkPositionName",
                               data:{
                                   positionName : function(){return $("#positionName").val();},
                            	   oldPositionName : '${adverPosition.positionName}',
                            	   moduleCode : function(){return $("input[name='moduleCode']:checked").val();},
                            	   oldModuleCode : '${adverPosition.moduleCode}'
                               } 
                        } 
                    }    					
                },
                messages : {
                    moduleCode : {
                        required : "请选择产品线",
                        remote : "选中的产品线已存在开屏位置"
                    },
                    positionName : {
                        required : "请填写位置名称",
                        remote : "当前产品线中已存在与此位置名称相同的位置"
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
            
            // 产品线默认选中第一个
            if(($("#id").val() == null || $("#id").val() == "") && $("input[name='moduleCode']").length > 0){
                $("input[name='moduleCode']").eq(0).attr("checked","true");
            }
            
            $("#openScreenFlag").click(function(){
                $.each($("input[name='moduleCode']"),function(index,value,array){
                    $(value).removeData("previousValue");
                });
            });
            
            $("input[name='moduleCode']").click(function(){
                // “重复开屏”提示
                var msgOpenScreen = "选中的产品线已存在开屏位置";
                // “重复位置名称”提示
                var msgPositionName = "当前产品线中已存在与此位置名称相同的位置";
                
				// 清除位置名称的校验缓存
                $("#positionName").removeData("previousValue");
                // 手动清除“重复开屏”提示
                var $labelError = $("input[name='moduleCode']").eq(0).parent().parent().children("label.error");
                if ($labelError.size() != 0) {
                    $.each($labelError,function(index,value,array){
                   		if ($(value).text() == msgOpenScreen) {
                        	$(value).remove();
                   		}
                    });
                }
                // 手动清除“重复位置名称”提示
                $labelError = $("#positionName").parent().children("label.error");
                if ($labelError.size() != 0) {
                    $.each($labelError,function(index,value,array){
                   		if ($(value).text() == msgPositionName) {
                        	$(value).remove();
                   		}
                    });
                }
            });
            
            $("#openScreenFlag").change(function(){
                var msgOpenScreen = "选中的产品线已存在开屏位置";
                if ($("input[name='openScreenFlag']:checked").size() == 0) {
	             	// 手动清除“重复开屏”提示
	                var $labelError = $("input[name='moduleCode']").eq(0).parent().parent().children("label.error");
	                if ($labelError.size() != 0) {
	                    $.each($labelError,function(index,value,array){
	                   		if ($(value).text() == msgOpenScreen) {
	                        	$(value).remove();
	                   		}
	                    });
	                }
                }
            });
        });
        
        
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/adver/adverPosition/form?id=${adverPosition.id}">位置<shiro:hasPermission name="adver:adverPosition:edit">${not empty adverPosition.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="adver:adverPosition:edit">查看</shiro:lacksPermission></a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="adverPosition" action="${ctx}/adver/adverPosition/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>        
        <div class="control-group">
            <label class="control-label">产品线：</label>
            <div class="controls">
                <form:radiobuttons path="moduleCode" items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" onclick=""/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">位置名称：</label>
            <div class="controls">
                <form:input path="positionName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否开屏：</label>
            <div class="controls">
                <span>
                    <c:choose>
                        <c:when test="${not empty adverPosition.openScreenFlag}">
                            <input id="openScreenFlag" name="openScreenFlag" type="checkbox" checked/>
                        </c:when>
                        <c:otherwise>
                            <input id="openScreenFlag" name="openScreenFlag" type="checkbox" />
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="adver:adverPosition:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <a id="btnCancel" href="${ctx}/adver/adverPosition/" class="btn btn-success"> 返 回</a>
        </div>
    </form:form>
</body>
</html>