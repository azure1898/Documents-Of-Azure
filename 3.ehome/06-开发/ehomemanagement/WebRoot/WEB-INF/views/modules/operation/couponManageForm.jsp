<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>优惠券管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        fillPro(); // 加载全部省市区数据
        $("#btnExport").click(function() {
            top.$.jBox.confirm("确认要下载用户数据模板吗？", "系统提示",
                function(v, h, f) {
                    if (v == 'ok') {
                        window.location.href="${ctx}/operation/couponManage/export";
                        //$("#searchForm").submit();
                    }
                },
                {
                    buttonsFocus: 1
                });
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
        // 在编辑时，对各个字段进行数值回显
        $(function() {
            // 优惠券内容
            if ("${couponManage.couponType}" == "0") {
                $("#couponType1").prop("checked", true);
                $("#couponMoney1").val("${couponManage.couponMoney}");
                $("#couponMoney1").prop("disabled", "");
                $("#couponMoney2").prop("disabled", true);
                $("#upperLimitMoney").prop("disabled", true);
            } else if ("${couponManage.couponType}" == "1") {
                $("#couponType2").prop("checked", true);
                $("#couponMoney2").val("${couponManage.couponMoney}");
                $("#couponMoney1").prop("disabled", true);
                $("#couponMoney2").prop("disabled", "");
                $("#upperLimitMoney").prop("disabled", "");
            }

            // 使用范围
            if ("${couponManage.useScope}" != "" || "${couponManage.useScope}" != "0") {
                $("#useObject").show();
            }
            // 有效期
            if("${couponManage.validityType}"=="0"){
            	$("#validityDays").prop("disabled", true);
            	$("#validityStartTime").prop("disabled", "");
            	$("#validityEndTime").prop("disabled", "");
            } else {
                $("#validityDays").prop("disabled", "");
                $("#validityStartTime").prop("disabled", true);
                $("#validityEndTime").prop("disabled", true);
            }
            // 领取方式
            if ("${couponManage.receiveType}" == "0") {
                $("#receiveType10").show();
            } else if ("${couponManage.receiveType}" == "1") {
                $("#receiveType20").show();
            } else if ("${couponManage.receiveType}" == "2") {
                $("#receiveType30").show();
            }
            // 推送方式
            if ("${couponManage.pushObjType}" == "0" || "${couponManage.pushObjType}" == "1") {
                $("#receiveType31").show();
            } else if ("${couponManage.pushObjType}" == "2" || "${couponManage.pushObjType}" == "3") {
                $("#receiveType31").show();
                $("#receiveType32").show();
            } else if ("${couponManage.pushObjType}" == "4") {
                $("#receiveType33").show();
            }

            var orderType = $("input[name='orderType']");
            var orderList = "${couponManage.orderType}";
            var orderArr = orderList.split(",");
            if (orderList.length != 0) {
                for (var i = 0; i < orderType.length; i++) {
                    for (var j = 0; j < orderArr.length; j++) {
                        if (orderType[i].value == orderArr[j]) {
                            //回显选中的值
                            $("#" + orderType[i].id).prop("checked", true);
                        }
                    }
                }
            }
        });

        //页面提交时验证
        jQuery.validator.addMethod("compareDate", function(value, element, params) {
            var startDate = jQuery(params).val();
            if(startDate!=null&&startDate!="") {
            	endDate = value;
	            /*
	                                     火狐不支持日期转换，转换后会出现invalid date错误，因此去掉日期转换代码
	            startDate = new Date(Date.parse(startDate.replace("-", "/")));  
	            endDate = new Date(Date.parse(value.replace("-", "/")));  
	            alert("转换后的开始时间"+startDate);
	            alert("转换后的结束时间"+endDate); */
	            return startDate < endDate;
            } else {
            	return true;
            }
            
        });
        jQuery.validator.addMethod("goodName", function(value, element,params) {
            var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
            return this.optional(element) || (patrn.test(value));
        });
        // 选择必填
        jQuery.validator.addMethod("requiredCase",function(value, element, params){
        	// var caseElement = jQuery(params[0]).val();
        	var eleVal = $("input[name="+params[0]+"]:checked").val();
            if(eleVal==params[1]){
            	if(value.length>=1) {
            		return true
            	} else {
            		return false;
            	}
            } else {
            	return true;
            }
        });
        // 校验上传的文件格式
        jQuery.validator.addMethod("checkFormat", function(value, element,params) {
            var filepath = jQuery(params[0]).val();// input type="file" 元素

            //获得上传文件名
            var fileArr=filepath.split("\\");
            var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
            var filetype=fileTArr[fileTArr.length-1];

            //切割出后缀文件名
            if(params[1].indexOf(filetype)==-1){
                return false;
            } else {
                return true;
            }
        });
        // 百分数校验
        jQuery.validator.addMethod("percent",function(value, element, params){
        	//var ele = jQuery(params[0]).val();
        	var eleVal = $("input[name="+params[0]+"]:checked").val();
        	if(eleVal == params[1]){
        		if(value > 99 || value < 1) {
        			return false;
        		} else {
        			return true;
        		}
        	} else {
        		   return true;	
        	}
        });
        // 根据隐藏域lastId，判断excel文件是否完成检测
        jQuery.validator.addMethod("checkLastId",function(value, element,param){
        	var eleVal = jQuery(param).val();
        	if(eleVal == null|| eleVal==""){
        		return false;
        	} else {
        		return true;
        	}
        });
        $("#inputForm").validate({
            rules : {
            	villageInfoId:{ // 楼盘名称
            		required: true
            	},
            	couponName:{ // 优惠券名称
                    required: true,
                    maxlength: 20,
                    goodName: true
                },
                couponType:{// 0 固定金额券 1 折扣券
                	required: true
                },
                couponMoney:{// 优惠金额
                    required: true,
                    percent: ['couponType','1'],
                    number: true,
                    maxlength: 9,
                    min: 0.01
                },
                upperLimitMoney:{// 优惠上限
                	number: true,
                    maxlength: 9,
                    min: 0.01
                },
                useRule:{ // 使用条件 0 无限制 1 满多少可用
                	required:true
                },
                fullUseMoney:{ // 满**可用
                    required:true,
                    number: true,
                    maxlength: 9,
                    min: 0.01
                },
                grantType:{ // 发行总量 0 不限量  1 限量张
                	required:true
                },
                limitedNum:{ // 限量 张
                    required: true,
                    number: true,
                    digits:true,
                    maxlength: 9,
                    min: 1
                },
                useScope:{ // 使用范围
                	required:true
                },
                useObject:{ // 选择服务品类,选择服务商家
                    required:true
                },
                shareFlag:{ // 优惠同享
                	required:true
                },
                validityType:{ // 有效期类型
                	required:true
                },
                validityStartTime:{ // 开始有效期
                    requiredCase:['validityType','0']
                },
                validityEndTime:{ // 结束有效期
                	requiredCase:['validityType','0'],
                    compareDate:'#validityStartTime'
                },
                validityDays:{ // 有效天数
                	required: true,
                    number: true,
                    digits: true,
                    maxlength: 9,
                    min: 1
                },
                receiveType:{ // 领取方式
                	required:true
                },
                receiveRule:{ // 领取规则
                	required: true
                },
                giveRule:{ // 赠送规则
                    required:true
                },
                fullGiveRule:{ // 满**元，赠送1张
                    required: true,
                    number: true,
                    maxlength: 9,
                    min: 1
                },
                pushObjType:{ // 推送对象
                	required: true
                },
                timeScope:{ // 时间范围
                	required: true
                },
                timeScopeStartTime:{ // 开始时间
                    requiredCase: ['timeScope','3']
                },
                timeScopeEndTime:{ // 结束时间
                    requiredCase: ['timeScope','3'],
                    compareDate: "#timeScopeStartTime"
                },
                orderType:{ // 订单类型
                	required:true
                },
                userDescribes:{ // 用户描述
                    required: true,
                	maxlength: 500,
                    goodName: true
                },
                excelFile:{
                	required: true,
                	checkFormat: ['#excelFile','xls|xlsx'],
                	checkLastId: "#lastId"
                },
                activeStartTime:{ // 活动起始时间
                	required:true
                },
                activeEndTime:{ // 活动结束时间
                	required:true,
                	compareDate:"#activeStartTime"
                }
            },
            messages : {
                villageInfoId:{ // 楼盘名称
                    required: '请选择楼盘'
                },
                couponName:{ // 优惠券名称
                    required: '请输入优惠券名称',
                    maxlength: '优惠券名称在20个字以内',
                    goodName: '优惠券名称必须是汉字英文数字'
                },
                couponType:{// 0 固定金额券 1 折扣券
                    required: true
                },
                couponMoney:{// 优惠金额
                    required: '请输入优惠券内容',
                    percent: '请输入1-99的数字',
                    number: '请填写数字',
                    maxlength: '长度不能超过9个字符',
                    min: '必须大于0.01'
                },
                upperLimitMoney:{// 优惠上限
                	number: '请填写数字',
                    maxlength: '长度不能超过9个字符',
                    min: '必须大于0.01元'
                },
                useRule:{ // 使用条件 0 无限制 1 满多少可用
                    required:'请输入使用条件'
                },
                fullUseMoney:{ // 满**可用
                    required:'满额可用金额必须填写',
                    number: '请填写数字',
                    maxlength: '长度不能超过9个字符',
                    min: '必须大于0.01元'
                },
                grantType:{ // 发行总量 0 不限量  1 限量张
                    required:'请输入发放总量'
                },
                limitedNum:{ // 限量 张
                    required: '限量张数必须填写',
                    number: '请填写数字',
                    digits: '只能填写整数',
                    maxlength: '长度不能超过9个字符',
                    min: '必须大于1张'
                },
                useScope:{ // 使用范围
                    required: '请选择使用范围'
                },
                useObject:{ // 选择服务品类,选择服务商家
                    required:'请选择使用范围'
                },
                shareFlag:{ // 优惠同享
                    required:'请选择优惠同享方式'
                },
                validityType:{ // 有效期类型
                    required: '请选择有效期类型'
                },
                validityStartTime:{ // 开始有效期
                    requiredCase:'请选择开始有效期'
                },
                validityEndTime:{ // 结束有效期
                    requiredCase:'请选择结束有效期',
                    compareDate: '开始时间必须小于结束时间'
                },
                validityDays:{ // 有效天数
                    required: '请输入有效期天数',
                    number: '请填写数字',
                    digits: '只能填写整数',
                    maxlength: '长度不能超过9个字符',
                    min: '有效期天数需大于1'
                },
                receiveType:{ // 领取方式
                    required: '请选择领取方式'
                },
                receiveRule:{ // 领取规则
                    required: '请选择领取规则'
                },
                giveRule:{ //赠送规则
                    required: '请选择赠送规则'
                },
                fullGiveRule:{ // 满**元，赠送1张
                    required: '请填写满额赠送张数',
                    number: '请填写数字',
                    digits: '只能填写整数',
                    maxlength: '长度不能超过9个字符',
                    min: '必须大于1元'
                },
                pushObjType:{ // 推送对象
                    required: '请填写推送对象'
                },
                timeScope:{ // 时间范围
                	required:'请填写时间范围'
                },
                timeScopeStartTime:{ // 开始时间
                    requiredCase: '请填写开始时间'
                },
                timeScopeEndTime:{ // 结束时间
                    requiredCase: '请填写结束时间',
                    compareDate: '开始时间必须小于结束时间'
                },
                orderType:{ // 订单类型
                    required: '请填写订单类型'
                },
                userDescribes:{ // 用户描述
                    required: "请填写用户描述",
                	maxlength: '长度不能超过500个字符',
                    goodName: '必须是汉字英文数字'
                },
                excelFile:{
                	required: '请上传excel文件，内容含有两列：手机号，注册时间',
                	checkFormat: '只能上传xls，xlsx格式文件，内容含有两列：手机号，注册时间',
                	checkLastId: '请先点击【导入用户数据】上传用户列表，再进行保存操作'
                },
                activeStartTime:{ // 活动起始时间
                    required: '请选择活动起始时间'
                },
                activeEndTime:{ // 活动结束时间
                    required: '请选择活动结束时间',
                    compareDate: '活动起始时间必须小于活动结束时间'
                }
            },
            submitHandler : function(form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer : "#messageBox",
            errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
    });
</script>
<style type="text/css">
/* 设置file的input框样式为透明 */
/* .inputstyle {
	width: 144px;
	height: 41px;
	cursor: pointer;
	font-size: 30px;
	outline: medium none;
	position: absolute;
	filter: alpha(opacity = 0);
	-moz-opacity: 0;
	opacity: 0;
	left: 500px;
	top: 2px;
}
input[type="file"]{ width: 285px;} */
</style>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <a href="${ctx}/operation/couponManage/">优惠券管理列表</a>
        </li>
        <li class="active">
            <a href="${ctx}/operation/couponManage/form?id=${couponManage.id}">优惠券管理<shiro:hasPermission name="operation:couponManage:edit">${not empty couponManage.id?'修改':'添加'}</shiro:hasPermission> <shiro:lacksPermission name="operation:couponManage:edit">查看</shiro:lacksPermission></a>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="couponManage" action="${ctx}/operation/couponManage/save" method="post" class="form-horizontal" enctype="multipart/form-data">
        <form:hidden path="id" />
        <form:hidden path="lastId" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">选择楼盘：</label>
            <div class="controls">
                <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
                <select id="addrcity" name="addrCity" style="width: 120px" onchange="changeVillage()">
                    <option value="">全部城市</option>
                </select>
                <select id="addrarea" name="addrArea" style="display: none;">
                    <option value="">全部区域</option>
                </select>
                <select id="addrVillage" name="villageInfoId" style="width: 120px">
                    <option value="">全部楼盘</option>
                </select>
                <input type="text" class="hide" id="hidProId" value="${couponManage.addrPro}">
                <input type="text" class="hide" id="hidCityId" value="${couponManage.addrCity}">
                <input type="text" class="hide" id="hidAreaId" value="">
                <input type="text" class="hide" id="hidVillageId" value="${couponManage.villageInfoId}">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">优惠券名称：</label>
            <div class="controls">
                <form:input path="couponName" htmlEscape="false" maxlength="64" class="input-xlarge " />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">优惠券内容：</label>
            <div class="controls">
                <span><input id="couponType1" name="couponType" onclick="showCouponMoneyUnit()" type="radio" value="0" /> <label for="couponType1">固定金额券</label></span>
                <input id="couponMoney1" name="couponMoney" class="input-medium" type="text" min="0" />
                <label>元</label> <span><input id="couponType2" name="couponType" onclick="showCouponMoneyUnit()" type="radio" value="1" /> <label for="couponType2">折扣券</label></span>
                <input id="couponMoney2" name="couponMoney" class="input-medium " min="0" type="text" />
                <label>%</label> ，优惠上限：
                <form:input path="upperLimitMoney" htmlEscape="false" class="input-small" />
                <label>元</label><span class="help-inline">当优惠内容为“固定金额券”时，请输入优惠券减免的金额；当优惠内容为“折扣券”时，请输入折扣（1~99），如：打9折，就输入90；<br /> “折扣券”可设定“优惠上限”，请输入上限金额，不填写，表示无上限。 <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">使用条件：</label>
            <div class="controls">
                <form:radiobuttons path="useRule" items="${fns:getDictList('use_rule')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showFullUseMoney()" />
                ， 满
                <c:choose>
                    <c:when test="${couponManage.useRule==1}">
                        <form:input path="fullUseMoney" htmlEscape="false" class="input-small " />
                    </c:when>
                    <c:otherwise>
                        <form:input path="fullUseMoney" htmlEscape="false" class="input-small " disabled="true" />
                    </c:otherwise>
                </c:choose>
                元，可用
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">发放总量：</label>
            <div class="controls">
                <form:radiobuttons path="grantType" items="${fns:getDictList('grant_type')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showLimitedNum()" />
                ，限量
                <c:choose>
                    <c:when test="${couponManage.grantType==1}">
                        <form:input path="limitedNum" htmlEscape="false" class="input-small" min="0" />
                    </c:when>
                    <c:otherwise>
                        <form:input path="limitedNum" htmlEscape="false" class="input-small" min="0" disabled="true" />
                    </c:otherwise>
                </c:choose>
                张
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">使用范围：</label>
            <div class="controls">
                <form:radiobuttons path="useScope" items="${fns:getDictList('use_scope')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showUserObject()" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="useObject1" <c:if test="${couponManage.useScope!=1 }">style="display:none"</c:if>>
            <label class="control-label">选择服务品类：</label>
            <div class="controls">
                <form:select path="useObject" class="input-xlarge " id="userObject11">
                    <form:option value="" label="" />
                    <form:options items="${allLifeModule}" itemLabel="moduleName" itemValue="id" htmlEscape="false" />
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="useObject2" <c:if test="${couponManage.useScope!=2 }">style="display:none"</c:if>>
            <label class="control-label">选择服务商家：</label>
            <div class="controls">
                <form:select path="useObject" class="input-xlarge " id="userObject21">
                    <form:option value="" label="" />
                    <form:options items="${allBusinessInfo}" itemLabel="BusinessName" itemValue="id" htmlEscape="false" />
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">优惠同享：</label>
            <div class="controls">
                <form:radiobuttons path="shareFlag" items="${fns:getDictList('share_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">有效期：</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${couponManage.validityType==0}">
                        <input id="validityType1" name="validityType" onclick="showValidityType()" type="radio" value="0" checked />
                    </c:when>
                    <c:otherwise>
                        <input id="validityType1" name="validityType" onclick="showValidityType()" type="radio" value="0" />
                    </c:otherwise>
                </c:choose>

                <input id="validityStartTime" name="validityStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value='${couponManage.validityStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                至
                <input id="validityEndTime" name="validityEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate compareValidityTime" value="<fmt:formatDate value='${couponManage.validityEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                可用
                <c:choose>
                    <c:when test="${couponManage.validityType==1}">
                        <input id="validityType2" name="validityType" onclick="showValidityType()" type="radio" value="1" checked />
                    </c:when>
                    <c:otherwise>
                        <input id="validityType2" name="validityType" onclick="showValidityType()" type="radio" value="1" />
                    </c:otherwise>
                </c:choose>
                自领取之日起
                <form:input path="validityDays" htmlEscape="false" maxlength="11" class="input-medium" min="0" />
                天内有效
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">领取方式：</label>
            <div class="controls">
                <form:radiobuttons path="receiveType" items="${fns:getDictList('receive_type')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showReceiveType()" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="receiveType10" <c:if test="${couponManage.receiveType!=0}">style="display: none" </c:if>>
            <label class="control-label">领取规则：</label>
            <div class="controls">
                <form:radiobuttons path="receiveRule" items="${fns:getDictList('receive_rule')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="receiveType20" <c:if test="${couponManage.receiveType!=1}">style="display: none"</c:if>>
            <label class="control-label">赠送规则：</label>
            <div class="controls">
                <form:radiobuttons path="giveRule" items="${fns:getDictList('give_rule')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showGiveRule()" />
                ， 满
                <form:input path="fullGiveRule" htmlEscape="false" class="input-small" min="0" />
                元，赠送1张
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="receiveType30" <c:if test="${couponManage.receiveType!=2}">style="display: none"</c:if>>
            <label class="control-label">推送对象：</label>
            <div class="controls">
                <form:select path="pushObjType" class="input-medium" onChange="showPushObj()">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('push_obj_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
                <span class="help-inline">（默认按照指定用户特征，每名用户推送一张优惠券）<font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group" id="receiveType31" style="display: none">
            <label class="control-label">时间范围：</label>
            <div class="controls">
                <form:radiobuttons path="timeScope" items="${fns:getDictList('time_scope')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="showTimeScope()" />
                <input id="timeScopeStartTime" name="timeScopeStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value='${couponManage.timeScopeStartTime}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                至
                <input id="timeScopeEndTime" name="timeScopeEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value='${couponManage.timeScopeEndTime}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="receiveType32" style="display: none">
            <label class="control-label">订单类型：</label>
            <div class="controls">
                <form:checkboxes path="orderType" items="${allLifeModule}" itemLabel="moduleName" itemValue="id" htmlEscape="false" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" id="receiveType33" style="display: none">
            <label class="control-label">用户描述：</label>
            <div class="controls">
                <form:textarea path="userDescribes" htmlEscape="false" rows="4" cols="50" maxlength="2000" class="input-xlarge " placeholder="请描述导入用户特征" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
            <div id="importBox" class="hide"></div>
            <div class="controls">
                <shiro:hasPermission name="operation:couponManage:edit">
                    <input id="excelFile" name="excelFile" type="file" class="inputstyle1" accept="" />
                    <span class="help-inline">仅支持“xls”或“xlsx”格式文件！<font color="red">*</font></span>
                    <br />
                    <a id="importFile" class="btn btn-primary" href="#" onclick="importUser()"><i class="icon-plus icon-custom"></i> 导入用户数据 </a>
                    <a class="btn btn-primary" href="#" onclick="viewImportUser()"><i class="icon-eye-open icon-custom"></i> 查看已导入用户</a>
                </shiro:hasPermission>
                    <a id="btnExport" class="btn btn-primary" href="#"><i class="icon-plus icon-custom"></i> 用户数据模板 </a>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">活动起始时间：</label>
            <div class="controls">
                <input id="activeStartTime" name="activeStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value='${couponManage.activeStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*活动结束时间：</label>
            <div class="controls">
                <input id="activeEndTime" name="activeEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate compareActiveTime" value="<fmt:formatDate value='${couponManage.activeEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="operation:couponManage:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
    <script type="text/javascript">
    /* 优惠券内容 */
    function showCouponMoneyUnit() {
        if ($("#couponType1").is(":checked")) {
            $("#couponMoney1").prop("disabled", "");
            $("#couponMoney2").prop("disabled", true);
            $("#upperLimitMoney").prop("disabled", true);
        } else {
            $("#couponMoney1").prop("disabled", "true");
            $("#couponMoney2").prop("disabled", "");
            $("#upperLimitMoney").prop("disabled", "");
        }
    }

    /* 使用条件 */
    function showFullUseMoney() {
        if ($("#useRule2").is(":checked")) {
            $("#fullUseMoney").prop("disabled", "");
        } else {
            $("#fullUseMoney").prop("disabled", true);
        }
    }
    /* 发放总量 */
    function showLimitedNum() {
        if ($("#grantType1").is(":checked")) {
            $("#limitedNum").prop("disabled", true);
        } else {
            $("#limitedNum").prop("disabled", "");
        }
    }
    /* 使用范围 */
    function showUserObject() {
        if ($("#useScope1").is(":checked")) {
            $("#useObject1").hide();
            $("#useObject2").hide();
        } else if ($("#useScope2").is(":checked")) {
            $("#useObject1").show();
            $("#useObject2").hide();
        } else {
            $("#useObject1").hide();
            $("#useObject2").show();
        }
    }

    /* 有效期 */
    function showValidityType() {
        if ($("#validityType1").is(":checked")) {
            $("#validityStartTime").prop("disabled", "");
            $("#validityEndTime").prop("disabled", "");
            $("#validityDays").prop("disabled", true);
        } else {
            $("#validityStartTime").prop("disabled", true);
            $("#validityEndTime").prop("disabled", true);
            $("#validityDays").prop("disabled", "");
        }
    }

    /* 领取方式 */
    function showReceiveType() {
        if ($("#receiveType1").is(":checked")) {
            $("#receiveType10").show();
            $("#receiveType20").hide();
            $("#receiveType30").hide();
            $("#receiveType31").hide();
            $("#receiveType32").hide();
            $("#receiveType33").hide();
        } else if ($("#receiveType2").is(":checked")) {
            $("#receiveType10").hide();
            $("#receiveType20").show();
            $("#receiveType30").hide();
            $("#receiveType31").hide();
            $("#receiveType32").hide();
            $("#receiveType33").hide();
        } else {
            $("#receiveType10").hide();
            $("#receiveType20").hide();
            $("#receiveType30").show();
            $("#receiveType31").hide();
            $("#receiveType32").hide();
            $("#receiveType33").hide();
        }
    }

    /* 推送对象 */
    function showPushObj() {
        if ($("#pushObjType").find("option:selected").text() == '未下单用户' || $("#pushObjType").find("option:selected").text() == '下单用户') {
            $("#receiveType31").show();
            $("#receiveType32").show();
            $("#receiveType33").hide();
        } else if ($("#pushObjType").find("option:selected").text() == '自定义用户') {
            $("#receiveType31").hide();
            $("#receiveType32").hide();
            $("#receiveType33").show();
        } else {
            $("#receiveType31").show();
            $("#receiveType32").hide();
            $("#receiveType33").hide();
        }
    }

    /* 增送规则 */
    function showGiveRule() {
        if ($("#giveRule1").is(":checked")) {
            $("#fullGiveRule").prop("disabled", true);
        } else {
            $("#fullGiveRule").prop("disabled", "");
        }
    }

    /* 时间范围 */
    function showTimeScope() {
        if ($("#timeScope4").is(":checked")) {
            $("#timeScopeStartTime").prop("disabled", "");
            $("#timeScopeEndTime").prop("disabled", "");
        } else {
            $("#timeScopeStartTime").prop("disabled", true);
            $("#timeScopeEndTime").prop("disabled", true);
        }
    }

    /* 导入用户数据 */
    function importUser() {
        var fileName = $("#excelFile").val();
        var ext = fileName.substring(fileName.lastIndexOf("."));
        if (fileName == "" || fileName == undefined) {
            alertx("请先上传excel文件，格式支持.xls和.xlsx", closed);
        } else {
            ext = ext.toLowerCase();
            if (ext != ".xls" && ext != ".xlsx") {
                alertx("导入文件类型错误。只支持导入.xls或者.xlsx文件", closed);
            } else {
                /* $("#inputForm").prop("action","${ctx}/operation/couponManage/import");
                $("#inputForm").submit(); */
                $("#inputForm").ajaxSubmit({
                    type : 'post',
                    url : "${ctx}/operation/couponManage/import",
                    success : function(data) {
                        if (data.success) {
                            $("#lastId").val(data.lastId);
                            alertx("文件上传成功", closed);
                        } else {
                            alertx("文件上传出错:"+data.msg, closed)
                        }
                    }
                });
            }
        }

    }

    /* 查看已导入用户 */
    function viewImportUser() {
        var lastId = $("#lastId").val();
        if (lastId = null || lastId == "" || lastId == undefined) {
            alertx("请先导入用户数据，然后查看已导入用户", closed);
            return false
        } else {
            $.jBox.open("iframe:${ctx}/operation/couponManageUsers/list?couponManageId=" + $("#lastId").val(), "优惠券导入的用户", 800, 350, {
                buttons : {
                    "关闭" : true
                }
            });
        }
    }
</script>
</body>
</html>