<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" />
	<script type="text/javascript">
	    //1到10位正整数 或 1到12位正浮点数（1到2位小数）
        var moneyPatrn=/^[1-9]\d{0,9}\.\d{1,2}|0\.\d{1,2}|[0-9]\d{0,9}$/;
        var integerPatrn="^[0-9]*[1-9][0-9]*$";

		$(document).ready(function() {
			//团购 详情
			KindEditor.ready(function(K) {
                var editor1 = K.create('textarea[name="groupPurcDetail"]', {
                cssPath : '${ctxStatic}/plugins/code/prettify.css',
                uploadJson :  "${ctx}/UploadFile.do",
                allowFileManager : false,
                themeType : 'simple',
                allowImageUpload:true,//允许上传图片
                afterChange : function() {
                	//手动清除团购 详情必填提示
            	    var msgGroupPurcDetail = "请输入团购详情";
            		//手动清除团购 详情必填提示
            	    var $labelError = $("#groupPurcDetail").parent().find("label.error");
            	    if ($labelError.size() != 0) {
            	        $.each($labelError,function(index,value,array){
            	       		if ($(value).text() == msgGroupPurcDetail) {
            	            	$(value).remove();
            	       		}
            	        });
            	    }
            	    
                    var limitNum = 6500;                   
                    if(this.count() > limitNum) {
                    	$(".word_message").show();
                    } else {
                    	$(".word_message").hide();
                    }
                },
                afterBlur: function(){this.sync();},
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
                });
                prettyPrint();
            });
			
            //使用详情
            KindEditor.ready(function(K) {
                var editor1 = K.create('textarea[name="useRule"]', {
                cssPath : '${ctxStatic}/plugins/code/prettify.css',
                uploadJson :  "${ctx}/UploadFile.do",
                allowFileManager : false,
                themeType : 'simple',
                allowImageUpload:true,//允许上传图片
                afterChange : function() {
                	//手动清除使用规则必填提示
            	    var msgBusinessId = "请输入使用规则";
            		//手动清除使用规则必填提示
            	    var $labelError = $("#useRule").parent().find("label.error");
            	    if ($labelError.size() != 0) {
            	        $.each($labelError,function(index,value,array){
            	       		if ($(value).text() == msgBusinessId) {
            	            	$(value).remove();
            	       		}
            	        });
            	    }
            	    
                    var limitNum = 6500;
                    if(this.count() > limitNum) {
                        $(".word_message1").show();
                    } else {
                    	$(".word_message1").hide();
                    }
                },
                afterBlur: function(){this.sync();},
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
                });
                prettyPrint();
            }); 
            
            //check团购图片必填
            jQuery.validator.addMethod("checkImg", function(value, element,params) {          	
                var imgArea = $("#imgArea").children().find(".up-section").val();
                if(imgArea != ''){
                    return false;
                }else{
                    return true;
                }
    	    }, "请上传团购图片");
            
            //check团购详情必填
            jQuery.validator.addMethod("checkGroupPurcDetail", function(value, element,params) {          	
            	if(KindEditor.instances[0].isEmpty()){
            		 return false;
            	}else{
            		return true;                 
                }
    	    }, "请输入团购详情");
            
            //check使用规则必填
            jQuery.validator.addMethod("checkUseRule", function(value, element,params) {
            	if(KindEditor.instances[1].isEmpty()){
            		return false;           		
            	}else{
            		return true; 
                }
    	    }, "请输入使用规则");
            
            //chec市场价金额格式
            jQuery.validator.addMethod("checkMarketMoney", function(value, element,params) {
            	if($("#marketMoney").val() != moneyPatrn.exec($("#marketMoney").val())){
            		return false;           		
            	}else{
            		return true; 
                }
    	    }, "输入的市场价金额格式不正确，请输入低于10位的数字或者低于2位的小数位");
            
            //chec市场价金额格式
            jQuery.validator.addMethod("checkGroupPurcMoney", function(value, element,params) {
            	if($("#groupPurcMoney").val() != moneyPatrn.exec($("#groupPurcMoney").val())){
            		return false;           		
            	}else{
            		return true; 
                }
    	    }, "输入的团购价金额格式不正确，请输入低于10位的数字或者低于2位的小数位");
            
            //check团购开始时间必填
            $.validator.addMethod("startTimesClass", function(value) {
            	return value!="";                
    	    }, "请输入团购开始时间");
          
            //check团购结束时间必填
            $.validator.addMethod("endTimesClass", function(value) {
            	return value!="";                
    	    }, "请输入团购结束时间");
          
            //check团购库存量必填
            $.validator.addMethod("stockNumClass", function(value) {
            	return value!=""  &&  value!=0;                
    	    }, "请输入库存量");
            
			$("#inputForm").validate({
				rules : {
			        businessinfoId : {
			            required : true
				    },
				    groupPurcName : {
			            required : true
				    },
				    checkImg:{
	                    checkImg:"params"
	                },
				    marketMoney : {
			            required : true,
			            min:0.01,
			            checkMarketMoney:"params"
				    },
				    groupPurcMoney : {
			            required : true,
			            min:0.01,
			            checkGroupPurcMoney:"params"
				    },
				    checkGroupPurcDetail : {
				    	checkGroupPurcDetail:"params"
				    },
				    validityStartTime : {
			            required : true
				    },
				    validityEndTime : {
			            required : true
				    },
				    useTime : {
			            required : true
				    },
				    checkUseRule : {
				    	checkUseRule : "params"
				    },
				},
				messages : {
				     businessinfoId : {
			            required : "请选择所属商家"
				    },
				    groupPurcName : {
			            required : "请输入团购名称"
				    },
				    checkImg:{
	                    checkImg:"请上传团购图片"
	                },
				    marketMoney : {
			            required : "请输入市场价",
			            min:"请输入市场价",
			            checkMarketMoney:"输入的市场价金额格式不正确，请输入低于10位的数字"
				    },
				    groupPurcMoney : {
			            required : "请输入团购价",
			            min:"请输入市场价",
			            checkGroupPurcMoney:"输入的团购价金额格式不正确，请输入低于10位的数字"
				    },
				    checkGroupPurcDetail : {
				    	checkGroupPurcDetail : "请输入团购详情"
				    },
				    validityStartTime : {
			            required : "请输入购团券有效期"
				    },
				    validityEndTime : {
			            required : "请输入购团券有效期"
				    },
				    useTime : {
			            required : "请输入使用时间"
				    },
				    checkUseRule : {
				    	checkUseRule : "请输入使用规则"
				    },
				},
				submitHandler: function(form){					
					if(KindEditor.instances[0].html().length > 6500 && KindEditor.instances[1].html().length > 6500){						
						$(".word_message").show();
						$(".word_message1").show();
					}else if(KindEditor.instances[0].html().length > 6500){
						$(".word_message").show();
					}else if(KindEditor.instances[1].html().length > 6500){
						$(".word_message1").show();
					}else{
						loading('正在提交，请稍等...');
						form.submit();								
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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/operation/groupPurchase/">团购管理列表</a></li>
		<li class="active"><a href="${ctx}/operation/groupPurchase/form?id=${groupPurchase.id}">团购管理<shiro:hasPermission name="operation:groupPurchase:edit">${not empty groupPurchase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="operation:groupPurchase:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="groupPurchase" action="${ctx}/operation/groupPurchase/save" method="post" enctype="multipart/form-data" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">*所属商家：</label>
			<div class="controls">
			   <select id="moduleId" name="moduleId" class="input-medium">
					<c:forEach items="${lifeModuleList}" var="module">
						<option value="${module.id}" ${groupPurchase.moduleId eq module.id ? 'selected="selected"' : ''}>${module.moduleName}</option>
					</c:forEach>
			   </select>
			   <select id="businessinfoId" name="businessinfoId" class="input-medium">
					<c:forEach items="${groupBusinessList}" var="business">
						<option value="${business.businessinfoId}" ${groupPurchase.businessinfoId eq business.businessinfoId ? 'selected="selected"' : ''}>${business.businessName}</option>
					</c:forEach>
			   </select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">*团购名称：</label>
			<div class="controls">
				<form:input path="groupPurcName" htmlEscape="false" maxlength="15" class="input-xlarge"/>
				<span>（建议标题在15个字以内）</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购图片：</label>
			<div id="localImag">
				<div class="controls">
					<div class="img-box full" id="imgArea">
					    <section class=" img-section">
					        <div class="z_photo upimg-div clear">
					           <c:if test="${groupPurchase.groupPurcPic != '' && groupPurchase.groupPurcPic !=null}">
					                <section class="up-section fl">
					                    <span class="up-span"></span>
					                    <img src="${ctxStatic}/images/a7.png" class="close-upimg">
					                    <img src="${groupPurchase.groupPurcPic}" class="up-img">
					                </section>
					           </c:if>
					           <section class="z_file fl" <c:if test="${groupPurchase.groupPurcPic != '' && groupPurchase.groupPurcPic !=null}">style="display: none;"</c:if>>
					               <img src="${ctxStatic}/images/a10.png" class="add-img">
					               <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp"/>
					           </section>
					         </div>
					     </section>
					</div>
					<aside class="mask works-mask" >
					    <div class="mask-content" id="imgAside">
					        <p class="del-p ">您确定要删除图片吗？</p>
					        <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
					    </div>
					</aside>
					<input id="checkImg" name="checkImg" value="" style="width: 0px;height: 0px;border: 0px;">
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">*市场价：</label>
			<div class="controls">
				<form:input path="marketMoney" id="marketMoney" htmlEscape="false" maxlength="10" class="input-medium number"/>&nbsp;元
				<input id="checkMarketMoney" name="checkMarketMoney" value="" style="width: 0px;height: 0px;border: 0px;">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购价：</label>
			<div class="controls">
				<form:input path="groupPurcMoney" id ="groupPurcMoney" htmlEscape="false" maxlength="10" class="input-medium number"/>&nbsp;元
				<input id="checkGroupPurcMoney" name="checkGroupPurcMoney" value="" style="width: 0px;height: 0px;border: 0px;">
			</div>
		</div>
		
		<div id="timeList">
			<div class="control-group" id="time0">
				<div class="control" id="item">
				    <label class="control-label">*团购开始时间：</label>
					<input name="groupPurchasetimeList[0].startTime" id="groupPurchasetimeList[0].startTime" type="text" readonly="readonly" maxlength="20" class="startTimesClass input-medium Wdate" style="margin-left: 20px;"
						value="<fmt:formatDate value="${groupPurchase.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,onpicking:function(dp){checkStartTime(dp,this);}});"/>&nbsp;&nbsp;
	
					&nbsp;&nbsp;&nbsp;*团购结束时间：&nbsp;&nbsp;
					<input name="groupPurchasetimeList[0].endTime" id="groupPurchasetimeList[0].endTime" type="text" readonly="readonly" maxlength="20" class="endTimesClass input-medium Wdate"
						value="<fmt:formatDate value="${groupPurchase.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,onpicking:function(dp){checkEndTime(dp,this);}});" />&nbsp;&nbsp;
					
					
					&nbsp;&nbsp;&nbsp;*库存量：&nbsp;&nbsp;
					<input name="groupPurchasetimeList[0].stockNum" id="groupPurchasetimeList[0].stockNum" value="${groupPurchase.stockNums}" maxlength="9" type="text" class="stockNumClass input-mini digits"/>&nbsp;件
					
					&nbsp;&nbsp;&nbsp;<a id="addRow" class="btn btn-default" onclick="addRow()"><i class="icon-plus"></i></a>
					<a class="btn btn-default" onclick="removeRow()"><i class="icon-remove"></i></a>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户限购数：</label>
			<div class="controls">
				<form:input path="restrictionNumber" htmlEscape="false" maxlength="9" class="input-medium digits"/>&nbsp;件
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家支持：</label>
			<div class="controls">
			    <form:checkboxes path="supportTypeList" items="${fns:getDictList('supportType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购详情：</label>
			<div class="controls">
				<form:textarea id="groupPurcDetail" path="groupPurcDetail" rows="4" style="width:1000px;height:300px" />
                <label for="groupPurcDetail" class="error word_message"  style="display:none;">超出最大长度，请适当缩减内容</label>
                <input id="checkGroupPurcDetail" name="checkGroupPurcDetail" value="" style="width: 0px;height: 0px;border: 0px;">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购券有效期：</label>
			<div class="controls" >
				<input name="validityStartTime" id="validityStartTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${groupPurchase.validityStartTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'validityEndTime\')}'});"/>&nbsp;至&nbsp;
				<input name="validityEndTime" id="validityEndTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${groupPurchase.validityEndTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'validityStartTime\')}'});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*使用时间：</label>
			<div class="controls">
				<form:input path="useTime" id="useTime" htmlEscape="false" maxlength="60" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*使用规则：</label>
			<div class="controls">
				<form:textarea id="useRule" path="useRule" rows="4" style="width:1000px;height:300px"/>
				<label for="useRule" class="error word_message1" style="display:none;">超出最大长度，请适当缩减内容</label>
				<input id="checkUseRule" name="checkUseRule" value="" style="width: 0px;height: 0px;border: 0px;">
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="operation:groupPurchase:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script src="${ctxStatic}/common/singlefileUpload.js"></script>
	<script type="text/javascript">
	//点击行追加的计数器
	var addCount = 1;
	
	//页面加载
	$(document).ready(function() {
		var id = '${groupPurchase.id}';
		//修改的场合
		if(id != null && id !=''){
			//绑定团购时间详情
			bindList();
		}		
	 });
	
	//团购时间详情绑定
	function bindList(){
		$.ajax({
			type : 'POST',
			url : '${ctx}/operation/groupPurchase/bindList',
			data : {
				groupPurchaseId:'${groupPurchase.id}'
    		 },
			dataType : 'json',
			success : function(data) {
				for(var  i=0;i<data.length-1;i++){
					addRow();
				}
				for(var  i=0;i<data.length;i++){					
					var startTime=data[i].startTime;
					var endTime=data[i].endTime;
					var stockNum=data[i].stockNum;

					$("input[name="+"'groupPurchasetimeList["+i+"].startTime']").val(startTime);
					$("input[name="+"'groupPurchasetimeList["+i+"].endTime']").val(endTime);
					$("input[name="+"'groupPurchasetimeList["+i+"].stockNum']").val(stockNum);
					
				}
			}
		})
	}
	
	//团购开始时间-增加一行
	function addRow(){
		var domRow='<div class="control-group" id="time'+addCount+'">';
		domRow+=$('#item').html();
		domRow+='</div>';
		$("#timeList").append($(domRow))
		
		$("#time"+addCount).find(".startTimesClass").attr("id","groupPurchasetimeList["+addCount+"].startTime");
		$("#time"+addCount).find(".startTimesClass").attr("name","groupPurchasetimeList["+addCount+"].startTime");
		
		$("#time"+addCount).find(".endTimesClass").attr("id","groupPurchasetimeList["+addCount+"].endTime");
		$("#time"+addCount).find(".endTimesClass").attr("name","groupPurchasetimeList["+addCount+"].endTime");
		
		$("#time"+addCount).find(".stockNumClass").attr("id","groupPurchasetimeList["+addCount+"].stockNum");
		$("#time"+addCount).find(".stockNumClass").attr("name","groupPurchasetimeList["+addCount+"].stockNum");
		addCount++;
	}
	//团购开始时间-删除一行
	function removeRow(){
    	var total=$("#timeList").children().size();
		//团购时间大于1行数据，可以删除
    	if(total > 1){
    		$("#timeList").children().eq(total-1).remove();
    	}else{
    		alertx("至少保留1个活动时段");
    	}
	    
	}
	
	
	//模块的change事件
	$("#moduleId").change(function(){
		//手动清除下拉框商家必填提示
		$("#businessinfoId").change();
				
		oldBusinessinfoId = $("#businessinfoId").val();
		//清空商家选项
    	$("#businessinfoId").empty();
    	$("#businessinfoId").append("<option value='' label=''></option>");
    	$("#businessinfoId").attr("value","");
    	
		//模块
		var moduleId=$("#moduleId").val();

		//商家下拉框的取值
		$.get("${ctx}/operation/groupPurchase/getModuleList"
				, {moduleId:moduleId},
			function (data,status) {
                if(status=="success"){
                	//追加商家选项
                    for (var i=0; i<data.length; i++){
                    	if(oldBusinessinfoId == data[i].businessinfoId){
                    		$("#businessinfoId").append("<option value='"+data[i].businessinfoId+"'selected='selected'>"+data[i].businessName+"</option>");
                    	}else{
                    		$("#businessinfoId").append("<option value='"+data[i].businessinfoId+"'>"+data[i].businessName+"</option>");
                    	}
                    	
                    }
                }
	    }); 
				
	});

	//团购开始时间的check
	function checkStartTime(dp,obj) {		
		 var name = $(obj).attr('name');		
		 var endname=name.split('.')[0]+".endTime";
		 var startTime = dp.cal.getNewDateStr(); 
		 var endTime = $("input[name='"+endname+"']").val();
		 var start=new Date(startTime.replace("-", "/").replace("-", "/"));
		 var end=new Date(endTime.replace("-", "/").replace("-", "/"));

		 var index=name.split('[')[1];
		 index=index.split(']')[0];
		 if(index > 0){
			 index = index -1;
			 var endTimePre=$("input[name='groupPurchasetimeList["+index+"].endTime']").val(); 
			 var endPre=new Date(endTimePre.replace("-", "/").replace("-", "/"));
			 if(start < endPre){
				 top.$.jBox.tip('团购开始时间应晚于上一段团购结束时间','error');
				 dp.cal.setNewDateStr(null);
			 }
		 }
 		 
		 if(end<start){  
			 top.$.jBox.tip("团购开始时间应早于团购结束时间",'error');
			 dp.cal.setNewDateStr(null);
	     }  
	};
	
	//团购结束时间的check
	function checkEndTime(dp,obj) {		
		 var name = $(obj).attr('name');		
		 var startname=name.split('.')[0]+".startTime";
		 var endTime = dp.cal.getNewDateStr(); 
		 var startTime = $("input[name='"+startname+"']").val();
		 var start=new Date(startTime.replace("-", "/").replace("-", "/"));
		 var end=new Date(endTime.replace("-", "/").replace("-", "/"));
		 
		 if(end<start){  
			 top.$.jBox.tip("团购结束时间应晚于团购开始时间",'error');
			 dp.cal.setNewDateStr(null);
	     }  
	};
	
	//商家的change事件
	$("#businessinfoId").change(function(){
		//手动清除下拉框商家必填提示
	    var msgBusinessId = "请选择所属商家";
		//手动清除所属商家必填提示
	    var $labelError = $("#businessinfoId").parent().find("label.error");
	    if ($labelError.size() != 0) {
	        $.each($labelError,function(index,value,array){
	       		if ($(value).text() == msgBusinessId) {
	            	$(value).remove();
	       		}
	        });
	    }
	});
	
	//清除图片必填消息
	$("#imgArea").click(function(){
		//手动清除团购图片必填提示
	    var msgImgArea = "请上传团购图片";
		//手动清除团购图片必填提示
	    var $labelError = $("#imgArea").parent().find("label.error");
	    if ($labelError.size() != 0) {
	        $.each($labelError,function(index,value,array){
	       		if ($(value).text() == msgImgArea) {
	            	$(value).remove();
	       		}
	        });
	    }
	});
	
	</script>
</body>
</html>