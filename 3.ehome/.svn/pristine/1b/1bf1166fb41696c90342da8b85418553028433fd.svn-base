<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var firstKindEditorLoadFlag = true;
		$(document).ready(function() {
    		// 初始化楼盘下拉列表
			fillPro();
    
			KindEditor.ready(function(K) {
			    var editor1 = K.create('textarea[name="noticeContent"]', {
			        cssPath : '${ctxStatic}/plugins/code/prettify.css',
			        uploadJson :  "${ctx}/UploadFile.do",
			        allowFileManager : false,
			        themeType : 'simple',
			        allowImageUpload:true,//允许上传图片
			        afterChange : function() {
			            var limitNum = 20000;
			            var minLength = 0;
			            if(this.count() > limitNum) {
			                $(".word_message").show();
			            } else {
			            	$(".word_message").hide();
			            }
			            
// 			            console.log("KindEditorLength：" + KindEditor.instances[0].html().length);
// 			            console.log("KindEditorHtml：" + KindEditor.instances[0].html());
			            if(!firstKindEditorLoadFlag && this.count() <= minLength) {
			                $(".word_message_min").show();
			            } else {
			            	$(".word_message_min").hide();
			            }
			            firstKindEditorLoadFlag = false;
			        },
			        afterBlur: function(){this.sync();},
			        items : [
			            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			            'insertunorderedlist', '|', 'emoticons', 'image', 'link']
			    });
			    prettyPrint();
			});
			
			// 初始化邻里圈复选框
			if ($("#hidSyncCircleFlag").val() == 1) {
  			   $("#syncCircleFlag").attr("checked",true);
			}
			
			$("#inputForm").validate({
   				onkeyup : false,
				submitHandler: function(form){
				    if (KindEditor.instances[0].html().length > 20000) {
				        $(".word_message").show();
				        return;
				    }
						console.log("KindEditorLength：" + KindEditor.instances[0].html().length);
				    if (KindEditor.instances[0].html().length <= 0) {
				        $(".word_message_min").show();
				        return;
				    }
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules : {
    				"noticeTitle" : {
        				  required : true,
      					  maxlength : 25
      				},
  				    "noticeAbstract" : {
  				         required : true,
        				 maxlength : 60
        			},
    			},
    			messages : {
    				"villageInfoId" : {
    					  required : "请选择楼盘"
    				},
   					"noticeTitle" : {
      					  required : "请输入公告标题",
      					  maxlength : "公告标题不能超过25个字"
      				},
  				    "noticeAbstract" : {
        				 required : "请输入摘要",
        				 maxlength : "摘要不能超过60个字"
        			}
    			},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else if (element.is("#noticeAbstract")) {
    					element.parent().children("span.help-inline").after(error);
					} else {
    					error.appendTo(element.parent());
					}
				}
			});
			
			$("#addrVillage").change(function(){
	    		var msgAddrVillage = "请选择楼盘";
	    		if ($("#addrVillage").val() != "") {
		    		 // 手动清除楼盘必选提示
		    	    var $labelError = $("#addrVillage").parent().children("label.error");
		    	    if ($labelError.size() != 0) {
		    	        $.each($labelError,function(index,value,array){
		    	       		if ($(value).text() == msgAddrVillage) {
		    	            	$(value).remove();
		    	       		}
		    	        });
		    	    }	
	    		}
    		});
    		
    		// 更改摘要字数显示
			$("#noticeAbstract").keyup(function(){
    			$("span#fontLength").text($("#noticeAbstract").val().length);
    		});
    
    		// 初始化摘要字数显示
			$("span#fontLength").text($("#noticeAbstract").val().length);
			initNoticeAbstract();
		});
		
		// 初始化摘要字数位置
		function initNoticeAbstract(){
   			$("span#fontLength").parent().css("left", cssPropertyCalc($("#noticeAbstract").css("width"), "70px", "-"));
   			$("span#fontLength").parent().css("top", "-".concat($("span#fontLength").parent().css("height")));
   			var areaPadding = cssPropertyCalc($("#noticeAbstract").css("padding-top"), $("#noticeAbstract").css("padding-bottom"), "+");
   			var areaBorder = cssPropertyCalc($("#noticeAbstract").css("border-top-width"), $("#noticeAbstract").css("border-bottom-width"), "+");
   			var areaHeight = cssPropertyCalc(cssPropertyCalc(areaPadding, areaBorder, "+"), $("#noticeAbstract").css("height"), "+");
//    			console.log("areaHeight=" + areaHeight);
   			$("#noticeAbstract").parent().css("height", areaHeight);
		}
		
		// css带单位尺寸计算
		function cssPropertyCalc(arg1, arg2, mode){
   			var patt = /\d+/;
    		var value1 = new String(patt.exec(arg1));
   			var value2 = new String(patt.exec(arg2));
//   			console.log("-------------------------------------cssPropertyCalc-----------------------------------");
//   			console.log("value1.length=" + value1.length);
   			var unit = arg1.substr(value1.length);
//     		console.log("arg1=" + arg1 + ", arg2=" + arg2);
//    			console.log("value1=" + value1 + ", value2=" + value2 + ", unit=" + unit);
   			var result = 0;
   			value1 = new Number(value1);
   			value2 = new Number(value2);
   			if (value1 != null && value1 != undefined && value2 != null && value2 != undefined) {
	    		if (mode == "+") {
	        		result = value1.valueOf() + value2.valueOf();
	    		} else if (mode == "-") {
	        		result = value1 - value2;
	    		} else if (mode == "*") {
	        		result = value1 * value2;
	    		} else if (mode == "/") {
	        		result = value1 / value2;
	    		}
   			}
//    			console.log("result=" + result);
//    			console.log("-------------------------------------cssPropertyCalc-----------------------------------");
   			return new String(result).concat(unit);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeManage/form?id=${noticeManage.id}"><shiro:hasPermission name="notice:noticeManage:edit">${not empty noticeManage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="notice:noticeManage:edit">查看</shiro:lacksPermission>公告</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="noticeManage" action="${ctx}/notice/noticeManage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="text" class="hide" id="hidProId"  value="${noticeManage.addrPro}">
        <input type="text" class="hide" id="hidCityId" value="${noticeManage.addrCity}">
        <input type="text" class="hide" id="hidAreaId" value="">
        <input type="text" class="hide" id="hidVillageId" value="${noticeManage.villageInfoId}">
        <input type="text" class="hide" id="hidSyncCircleFlag" value="${noticeManage.syncCircleFlag}">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">楼盘名称：</label>
			<div class="controls">
			<select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
							<option value="">全部省份</option>
						</select>
						<select id="addrcity" name="addrCity" style="width: 120px" onchange="changeVillage()">
								<option value="">全部城市</option>
						</select>
						<select id="addrarea" name="addrArea" style="width: 120px; display: none;">
								<option value="">全部区域</option>
						</select>
						<select id="addrVillage" name="villageInfoId" style="width: 120px" class="required">
								<option value="">全部楼盘</option>
						</select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告标题：</label>
			<div class="controls">
				<form:input path="noticeTitle" htmlEscape="false" maxlength="25" class="input-xlarge"/>
				（建议标题在25个字以内）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摘要：</label>
			<div class="controls">
				<form:textarea path="noticeAbstract" htmlEscape="false" rows="4" maxlength="60" class="input-xxlarge" style="resize: none;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<div style="width: 50px;height: 20px;position:relative;top: -20px;left: 400px;">
					(<span id="fontLength">0</span>/60)
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span><font color="#416F07">内容：</font> </label>
			<div class="controls">
				<form:textarea id="content" path="noticeContent" rows="4" maxlength="200" style="width:1000px;height:400px" class="input-xlarge required"/>
				<label for="content" class="custom-error word_message" style="display: none;">超出最大长度，请适当缩减内容</label>
				<label for="content" class="custom-error word_message_min" style="display: none;">请输入公告内容</label>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="checkbox" id="syncCircleFlag" name="syncCircleFlag"  class=""/>同步发到邻里圈
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="notice:noticeManage:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" href="${ctx}/notice/noticeManage/" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
</body>
</html>