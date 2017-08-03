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
        // 校验图片必填
        function validateImgMust() {
        	alert("true");
        	var imgArea = $("#imgArea").children().find(".up-section").val();
        	if(imgArea !=''){
        		return true;
        	}else{
        		return false;
        	}
        }
        
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
            
			$("#inputForm").validate({
				rules : {				
					moduleId : {
			            required : true
				      },
			        businessinfoId : {
			            required : true
				    },
				    groupPurcName : {
			            required : true
				    },
				    imgArea : {
				    	required : true
				    },
				    marketMoney : {
			            required : true
				    },
				    groupPurcMoney : {
			            required : true
				    },
				    groupPurcDetail : {
			            required : true
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
				    useRule : {
			            required : true
				    },
				},
				messages : {
					moduleId : {
			            required : "请选择所属商家",
				     },
				     businessinfoId : {
			            required : "请选择所属商家"
				    },
				    groupPurcName : {
			            required : "请输入团购名称"
				    },
				    imgArea : {
				    	required : "请上传团购图片"
				    },
				    marketMoney : {
			            required : "请输入市场价"
				    },
				    groupPurcMoney : {
			            required : "请输入团购价"
				    },
				    groupPurcDetail : {
			            required : "请输入团购详情"
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
				    useRule : {
			            required : "请输入使用规则"
				    },
				},
				submitHandler: function(form){
					//团购详情
					var groupPurcDetail = $("#groupPurcDetail").val();
					//使用规则
					var useRule = $("#useRule").val();
					var imgArea = $("#imgArea").children().find(".up-section").val();
					
					if(KindEditor.instances[1].isEmpty()){
						$(".must_message1").show();
					}else if(KindEditor.instances[0].isEmpty()){
						$(".must_message").show();						
					}else if(imgArea !=''){
						alertx('请上传团购图片','error');
					}else if($("#marketMoney").val() != moneyPatrn.exec($("#marketMoney").val())){
						alertx("输入的市场价金额格式不正确，请重新输入");
					}else if($("#groupPurcMoney").val() != moneyPatrn.exec($("#groupPurcMoney").val())){
						alertx("输入的团购价金额格式不正确，请重新输入");
					}else if(KindEditor.instances[0].html().length > 6500){
						$(".word_message").show();
					}else if(KindEditor.instances[1].html().length > 6500){
						$(".word_message1").show();
					}else{
						/*check团购时间信息 */
						$.ajax({
							type : 'POST',
							url : '${ctx}/operation/groupPurchase/checkTimes',
							data : {
								    startTimes:getTime("startTimes"),
								    endTimes:getTime("endTimes"),
								    stockNums:getTime("stockNums")
			            	 },
							success : function(data) {
								if(data==''){
									loading('正在提交，请稍等...');
									form.submit();
								}else{
									top.$.jBox.tip(data,'error');
								}
							}
						})
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
		
		/* 传入下拉时间的name属性获取时间值 */
		function getTime(nameLab){
			var time="";
			$("input[name="+nameLab+"]").each(function (i) {
				var val = $(this).val();
				if(val == null || val ==''){
					val="-";
				}
				time+=val+",";
			});
			return time;
		}
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
					        <div class="z_photo upimg-div clear" style="width:150px;">
					           <c:if test="${groupPurchase.groupPurcPic != '' && groupPurchase.groupPurcPic !=null}">
					                <section class="up-section fl">
					                    <span class="up-span"></span>
					                    <img src="${ctxStatic}/images/a7.png" class="close-upimg">
					                    <img src="${groupPurchase.groupPurcPic}" class="up-img">
					                </section>
					           </c:if>
					           <section class="z_file fl" <c:if test="${groupPurchase.groupPurcPic != '' && groupPurchase.groupPurcPic !=null}">style="display: none;"</c:if>>
					               <img src="${ctxStatic}/images/a11.png" class="add-img">
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
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">*市场价：</label>
			<div class="controls">
				<form:input path="marketMoney" id="marketMoney" htmlEscape="false" maxlength="20" class="input-medium"/>&nbsp;元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购价：</label>
			<div class="controls">
				<form:input path="groupPurcMoney" id ="groupPurcMoney" htmlEscape="false" maxlength="20" class="input-medium"/>&nbsp;元
			</div>
		</div>
		
		<div id="timeList">
			<div class="control-group">
				<div class="control" id="item">
				    <label class="control-label">*团购开始时间：</label>
					<input name="startTimes" id="startTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" style="margin-left: 20px;"
						value="<fmt:formatDate value="${groupPurchase.startTimes}" pattern="yyyy-MM-dd HH"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',isShowClear:false});" />&nbsp;&nbsp;
					<!-- <select class="timelist" name="sHours" style="width:55px;">
						<c:forEach items="${selectList}" var="select">
							<option value="${select.timeKey}" ${0 eq select.timeKey ? 'selected="selected"' : ''}>${select.timeValue}</option>
						</c:forEach>
					</select>&nbsp;点 -->
					&nbsp;&nbsp;&nbsp;*团购结束时间：&nbsp;&nbsp;
					<input name="endTimes" id="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${groupPurchase.endTimes}" pattern="yyyy-MM-dd HH"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',isShowClear:false});"/>&nbsp;&nbsp;
					<!-- <select class="timelist" name="eHours" style="width:55px;">
						<c:forEach items="${selectList}" var="select">
							<option value="${select.timeKey}" ${0 eq select.timeKey ? 'selected="selected"' : ''}>${select.timeValue}</option>
						</c:forEach>
					</select>&nbsp;点-->
					
					&nbsp;&nbsp;&nbsp;*库存量&nbsp;&nbsp;
					<input id="stockNums" name="stockNums" value="${groupPurchase.stockNums}" maxlength="11" class="input-mini number required"/>&nbsp;件
					
					&nbsp;&nbsp;&nbsp;<a id="addRow" class="btn btn-default" onclick="addRow()"><i class="icon-plus"></i></a>
					<a class="btn btn-default" onclick="removeRow()"><i class="icon-remove"></i></a>
				</div>
			</div>	
		</div>
		<div class="control-group">
			<label class="control-label">用户限购数：</label>
			<div class="controls">
				<form:input path="restrictionNumber" htmlEscape="false" maxlength="11" class="input-medium number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家支持：</label>
			<div class="controls">
			    <form:checkboxes path="supportTypeList" items="${fns:getDictList('supportType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购详情</label>
			<div class="controls">
				<form:textarea id="groupPurcDetail" path="groupPurcDetail" rows="4" style="width:1000px;height:300px" />
				<label for="groupPurcDetail" class="error must_message"  style="display:none;">请输入团购详情</label>
                <label for="groupPurcDetail" class="error word_message"  style="display:none;">超出最大长度，请适当缩减内容</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*团购券有效期：</label>
			<div class="controls" >
				<input name="validityStartTime" id="validityStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${groupPurchase.validityStartTime}" pattern="yyyy-MM-dd HH"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',isShowClear:false,maxDate:'#F{$dp.$D(\'validityEndTime\')}'});"/>&nbsp;至&nbsp;
				<input name="validityEndTime" id="validityEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${groupPurchase.validityEndTime}" pattern="yyyy-MM-dd HH"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',isShowClear:false,minDate:'#F{$dp.$D(\'validityStartTime\')}'});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*使用时间：</label>
			<div class="controls">
				<form:input path="useTime" id="useTime" htmlEscape="false" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">*使用规则：</label>
			<div class="controls">
				<form:textarea id="useRule" path="useRule" rows="4" style="width:1000px;height:300px"/>
				<label for="useRule" class="error must_message1" style="display:none;">请输入使用规则</label>
				<label for="useRule" class="error word_message1" style="display:none;">超出最大长度，请适当缩减内容</label>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="operation:groupPurchase:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script src="${ctxStatic}/common/singlefileUpload.js"></script>
	<script type="text/javascript">
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
					var startTimes=data[i].startTime;
					var endTimes=data[i].endTime;
					var stockNums=data[i].stockNum;

					$("input[name=startTimes]").eq(i).val(startTimes.substr(0,13));
					$("input[name=endTimes]").eq(i).val(endTimes.substr(0,13));
					$("input[name=stockNums]").eq(i).val(stockNums);
					
				}
			}
		})
	}
	
	//团购开始时间-增加一行
	function addRow(){
		var domRow='<div class="control-group">';
		domRow+=$('#item').html();
		domRow+='</div>';
		$("#timeList").append($(domRow))
	}
	//团购开始时间-删除一行
	function removeRow(){
    	var total=$("#timeList").children().size();
		//团购时间大于1行数据，可以删除
    	if(total > 1){
    		$("#timeList").children().eq(total-1).remove();
    	}else{
    		top.$.jBox.tip('至少保留1个活动时段','error');
    	}
	    
	}
	
	//模块的change事件
	$("#moduleId").change(function(){
		
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
	</script>
</body>
</html>