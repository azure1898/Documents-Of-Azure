<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>服务管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/multiplefileUpload.css"
	type="text/css" />
<script type="text/javascript">
    $(document).ready(
            function() {
                //初始化KindEditor编辑器插件  
                KindEditor.ready(function(K) {
			    var editor1 = K.create('textarea[name="content"]', {
			        cssPath : '${ctxStatic}/plugins/code/prettify.css',
			        uploadJson :  "${ctx}/UploadFile.do",
			        allowFileManager : false,
			        themeType : 'simple',
			        allowImageUpload:true,//允许上传图片
			        afterChange : function() {
			            var limitNum = 65000;
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
                
                // 初始化限购数量的活性
                quotaNumDisabledSet(document.getElementById("quota"));
                
                $.validator.addMethod("checkMoney",function(value,element,params){
                	return validateMoney(value, 10, true, true);
                },"请输入格式正确的金额"); 
                $.validator.addMethod("checkInteger",function(value,element,params){
                    var patrn = /^[0-9]\d*$/;
                    // 格式校验
                    if (value != null && value != "" && value != patrn.exec(value)) {
                        return false;
                    }
                    // 临界值校验
                    var num = new Number(value);
                    if (num > 2147483647) {
                        return false;
                    }
                    return true;
                },"请输入一个正整数"); 
                $.validator.addMethod("checkSpecialChars", function(value, element,params) {
        	            var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
        	            return this.optional(element) || (patrn.test(value));
        	    }, "请输入中文，英文或数字");
                $("#inputForm").validate({
                    onkeyup: false,
                                    rules : {
                                        name : {
                                            required : true,
                                            checkSpecialChars : true,
                                            maxlength : 20
                                        },
                                        sortInfoId : {
                                            required : true
                                        },
                                        basePrice : {
                                            required : true,
                                            number : true,
                                            min : 0.01,
                                            checkMoney : "param"
                                        },
                                        benefitPrice : {
                                            number : true,
                                            min : 0.01,
                                            checkMoney : "param"
                                        },
                                        stock : {
                                            number : true,
                                            maxlength: 9,
                                            checkInteger : "param",
                                            min : 0,
                                        },
                                        quotaNum : {
                                            number : true,
                                            maxlength : 9,
                                            min : 1,
                                            checkInteger : "param"
                                        }
                                    },
                                    messages : {
                                        name : {
                                            required : "请输入服务名称（建议在20个字以内）",
                                            maxlength : "服务名称在20个字以内"
                                        },
                                        sortInfoId : {
                                            required : "请选择服务分类"
                                        },
                                        basePrice : {
                                            required : "请填写服务价格",
                                            number : "请填写数字",
                                            min : "服务价格应大于0",
                                            checkMoney : "请输入正确的服务价格"
                                        },
                                        benefitPrice : {
                                            number : "请填写数字",
                                            min : "优惠价格应大于0",
                                            checkMoney : "请输入正确的优惠价"
                                        },
                                        stock : {
                                            required : "请填写服务库存",
                                            number : "请填写数字",
                                            maxlength: "库存长度不能超过9"
                                        },
                                        quotaNum : {
                                            number : "请填写数字",
                                            maxlength: "限购数量长度不能超过9",
                                            min: "限购数量必须大于0"
                                        }
                                    },
                                    submitHandler : function(form) {
                                        if ($(".up-section").size() == 0) {
                                			alertx("未选择任何图片");
                                			return;
                                		}
                                	    if (KindEditor.instances[0].html().length > 65000) {
                                	        $(".word_message").show();
                                	        return;
                                	    }
                                        loading('正在提交，请稍等...');
                                        form.submit();
                                    },
                                    errorContainer : "#messageBox",
                                    errorPlacement : function(error, element) {
                                        $("#messageBox").text("输入有误，请先更正。");
                                        if (element.is(":checkbox")
                                                || element.is(":radio")
                                                || element.parent().is(
                                                        ".input-append")) {
                                            error.appendTo(element.parent()
                                                    .parent());
                                        } else if (element.is("input[name='quotaNum']")) {
                                            error.appendTo(element.parent());
                                        } else {
                                            error.insertAfter(element);
                                        }
                                    }
                                });
                
                // 手动清除服务分类必填提示
                $("#sortInfoId").change(function(){
                    var msgSortInfoId = "请选择服务分类";
                       var $labelError = $("#sortInfoId").parent().children("label.error");
                       if ($labelError.size() != 0) {
                           $.each($labelError,function(index,value,array){
                            if ($(value).text() == msgSortInfoId) {
                                $(value).remove();
                            }
                           });
                       } 
                   });
            });
    window.onload = function() {
        // 如果浏览器不支持HTML5则提示无法上传图片
        if (!window.applicationCache) {
            $("#imgArea").remove();
            $("#imgAside").remove();
            alertx("您当前的浏览器不支持图片上传");
        }
    }

    // 根据选择的单位不同设定不同的限购单位
    function chooseUnit(obj) {
        for (var i = 0; i < obj.options.length; i++) {
            if (obj.options[i].selected) {
                $('#Quota_unit')[0].innerHTML = obj.options[i].text;
                return;
            }
        }
    }

    // 根据选择的限购设定来决定限购数量的活性
    function quotaNumDisabledSet(obj) {
        // 1为限购，限购数量为活性
        if ("1" == obj.value) {
            $("#quotaNum")[0].disabled = false;
            $("#quotaNum").attr("class","input-small");
        } else {
            $("#quotaNum")[0].value = "";
            $("#quotaNum")[0].disabled = true;
            $("#quotaNum").attr("class","input-small");
            // 手动清除限购数量的提示信息
            var $labelError = $("#quotaNum").parent().children("label.error");
            if ($labelError.size() != 0) {
                $.each($labelError,function(index,value,array){
                   $(value).remove();
                });
            } 
        }
    }
    
    // 打开服务分类页签
    function openSortInfo(obj,refresh) {
    	  var $obj = $("a[href='${ctx}/service/serviceSortInfo']", window.parent.document);
    	  if ($obj == undefined) {
    	    	$obj = $(this);
    	  }
    	 addTabPage('服务分类','${ctx}/service/serviceSortInfo',undefined,$obj);
    	 $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left>div.tab_close>a', window.parent.document).html('<i class="fa fa-remove"></i>');
    	 $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left', window.parent.document).css('width','100px');
    	 $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left>div.tab_text', window.parent.document).css('width','87px');
         return false;
    }
    
 // 下拉菜单刷新
	    function sortInfoRefresh() {
	    	alert("1");
	    	var nowValue = $("#sortInfoId").val();
	    	$.ajax({
	    		type:'post',
	    		url: "${ctx}/service/serviceInfo/sortInfoRefresh",
	    		data:{},
	    		dataType: "json",
	    		success: function (data) {
	    			//清空
	    			$('#sortInfoId').html("");
	    			$('#sortInfoId').append("<option value='' ></option>");
	    			//读取json数据
	    			for (var i=0;i<data.length;i++){
	    				$('#sortInfoId').append("<option value='"+data.id+"' >"+data.name+"</option>");
	    			}
	    			$('#sortInfoId').val(nowValue);
	    		},
	    		error:function(data){
	    			
	    		}
	    	});
	    }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>服务管理 > ${not empty serviceInfo.id?'编辑':'新增'}服务</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="serviceInfo"
		action="${ctx}/service/serviceInfo/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" />
		<form:hidden id="sortItem" path="sortItem" htmlEscape="false" />
		<form:hidden id="sort" path="sort" htmlEscape="false" />
		<c:choose>
  			<c:when test="${msgType != null and msgType != ''}">
      		<sys:message content="${message}" type="${msgType}"/>
  		</c:when>
 		<c:otherwise>
      		<sys:message content="${message}"/>
  		</c:otherwise>
 		</c:choose>
		<div class="control-group">
			<label class="control-label">服务名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20"
					placeholder="请输入服务名称（建议在20个字以内）" class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${sortInfoList != null && sortInfoList.size() > 0}">
			<div class="control-group">
				<label class="control-label">服务分类：</label>
				<div class="controls">
					<form:select path="sortInfoId" class="input-xlarge required"  onkeydown="sortInfoRefresh()" onmousedown = "sortInfoRefresh()">
						<form:option value="" label="" />
						<form:options items="${sortInfoList}" itemLabel="name"
							itemValue="id" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span> 
					<a href="#" onclick="openSortInfo(this)" style="text-decoration:none">新增分类</a>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">轮播图片：</label> <span id="warnning"></span>
		</div>
<div class="img-box full" id="imgArea">
			<form:hidden path="delImageName" />
			<section class=" img-section">
				<div class="z_photo upimg-div clear">
					<c:forEach items="${imgUrls}" var="imgTemp">
						<section class="up-section fl">
							<span class="up-span"></span> <img
								src="${ctxStatic}/images/a7.png" class="close-upimg"> <img
								src="${imgTemp.imgUrl}" class="up-img"> <input
								type="hidden" class="filename" value="${imgTemp.myfileid}">
						</section>
					</c:forEach>
					<section class="z_file fl"
						<c:if test="${fn:length(imgUrls) gt 4}">style="display: none;"</c:if>>
						<img src="${ctxStatic}/images/a10.png" class="add-img"> <input
							type="file" name="file" id="file" class="file" value=""
							accept="image/jpg,image/jpeg,image/png,image/bmp"
							multiple="multiple" />
					</section>
				</div>
			</section>
		</div>
		<aside class="mask works-mask">
			<div class="mask-content" id="imgAside">
				<p class="del-p ">您确定要删除图片吗？</p>
				<p class="check-p">
					<span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
				</p>
			</div>
		</aside>

		<div class="control-group"	>
			<label class="control-label">服务价格：</label>
			<div class="controls">
				<form:input path="basePrice" placeholder="请填写服务原价" maxlength="13"
					htmlEscape="false" class="input-medium required" value="${fns:doubleFormat(serviceInfo.basePrice)}"/>
				<span class="help-inline"><font color="red">*</font> </span> 
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>优惠价：</span>
				<form:input path="benefitPrice" htmlEscape="false" placeholder="请填写优惠价格" maxlength="13"
					class="input-medium"  value="${fns:doubleFormat(serviceInfo.benefitPrice)}"/>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>库存：</span>
				<form:input path="stock" htmlEscape="false" maxlength="9" placeholder="请填写库存数量"
					class="input-medium required" />
				<span class="help-inline"><font color="red">*</font> </span> 
			</div>
			<div class="controls">
			服务价格、优惠价：建议整数位在10位以内，且不以0开头
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务单位：</label>
			<div class="controls">
				<c:choose>
					<c:when
						test="${businessUnitList != null && businessUnitList.size() > 0}">
						<form:select path="baseUnit" class="input-xlarge "
							onchange="chooseUnit(this)">
							<form:options items="${businessUnitList}" itemLabel="name"
								itemValue="id" htmlEscape="false" />
						</form:select>
					</c:when>
					<c:otherwise>
						<form:select path="baseUnit" class="input-xlarge "
							onchange="chooseUnit(this)">
							<form:options items="${fns:getDictList('goods_units')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务介绍：</label>
			<div class="controls">
				<form:textarea id="content" path="content" rows="4" maxlength="200"
					style="width:1000px;height:400px" class="input-xlarge" htmlEscape="false"/>
				<label for="content" class="error word_message" style="display: none;">超出最大长度，请适当缩减内容</label>
				<label for="content" class="error word_message_min" style="display: none;">请输入公告内容</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限购：</label>
			<div class="controls">
				<form:select path="quota" class="input-xlarge "
					onchange="quotaNumDisabledSet(this)">
					<form:options items="${fns:getDictList('purchasing_limitations')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span>&nbsp;</span>
				<form:input path="quotaNum" htmlEscape="false" maxlength="9" 
					class="input-small " disabled="true" />
				<span id="Quota_unit">件</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="recommend" class="input-xlarge ">
					<form:options items="${fns:getDictList('Quota')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-success" type="submit"
					value="保 存" />&nbsp;
            <a id="btnCancel" href="${ctx}/service/serviceInfo/list?sortItem=${serviceInfo.sortItem}&sort=${serviceInfo.sort}" class="btn btn-success"> 返 回</a>
		</div>
	</form:form>
	<script src="${ctxStatic}/common/multiplefileUpload.js"></script>
</body>
</html>