<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息修改-继续营业设置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
var isVali=0;
    $(document).ready(function() {
        // ----------因一期不做自定义单位，故隐藏这部分功能，做时请删除该部分代码---START------
        $("#customUnitFlag").parent().parent().hide()
        // ----------因一期不做自定义单位，故隐藏这部分功能，做时请删除该部分代码---END--------
        
        $("#shangpin").hide();//0如果选择的商家分类所对应的产品模式为“商品配买”，那么关联显示出此块内容
        $("#fuwu").hide();//1如果选择的商家分类所对应的产品模式为“服务预约”，那么关联显示出此块内容。
        $("#cunxiaoTemp").hide();//0如果选择的商家分类所对应的产品模式为“商品配买”，那么关联显示出此块内容
        getTimeList("30");
        /* 初始化服务预约方式为到店 */
        if ($("#serviceModel").val() == null || $("#serviceModel").val() == "") {
            $("input:radio[name='serviceModel'][value='1']").eq(0).attr("checked","checked");
        }
        /* 绑定到店上门 */
        bindServiceType($("input:radio[name='serviceModel']:checked").eq(0).val());
        /*配送方式  */
        bindDistributeType($("#distributeModel").val());
        /*满额起配金额 */
        if($("#fullDistributeFlag").val()==1){
            $("#fullDistributeFlag").attr("checked","checked")
        }else{
            $("#fullDistributeMoney").prop("disabled", true);
        }
        /* 满额免运费金额 */
        if($("#freeDistributeFlag").val()==1){
            $("#freeDistributeFlag").attr("checked","checked")
        }else{
            $("#freeDistributeMoney").prop("disabled", true);
        }
        /* 初始化单位信息 */
        bindUnit();
        /* 初始化活动信息 */
        bindSales();
        
        /* 初始化营业时间 */
        initBusinessState();
        
        // 自定义单位必填校验
        $.validator.addMethod("checkUnitName", function(value, element) {
            var allEmptyFlag = true;
            $.each($("input[name='unitName']"),function(index,value,array){
                if ($(value).val() != "") {
                    allEmptyFlag = false;
                    return;
                }
           	});
            return !allEmptyFlag;
        }, "必填信息");
     	// 自定义单位重复校验
        $.validator.addMethod("checkUnitNameDuplicate", function(value, element) {
            var $unitNames = $("input[name='unitName']");
            if ($unitNames.size() <= 1) {
                return true;
            }
            for (var i = 0; i < $unitNames.size(); i++) {
                for (var j = i + 1; j < $unitNames.size(); j++) {
                    if ($unitNames.eq(i).val() != "" && $unitNames.eq(j).val() != "" 
                            && $unitNames.eq(i).val() == $unitNames.eq(j).val()) {
                        return false;
                    }
                }
            }
            return true;
        }, "单位名称不可重复");
        $.validator.addMethod("compareValiTime", function(value, element) {
            var startTime = $("#stopBusinessBeginTime").val();
            var endTime = $("#stopBusinessEndTime").val();
            var reg = new RegExp('-', 'g');
            startTime = startTime.replace(reg, '/');//正则替换
            endTime = endTime.replace(reg, '/');
            startTime = new Date(parseInt(Date.parse(startTime), 10));
            endTime = new Date(parseInt(Date.parse(endTime), 10));
            if (startTime > endTime) {
                return false;
            } else {
                return true;
            }
        }, "预约暂停营业结束时间应该大于预约暂停营业起始时间");
        $("#inputForm").validate({
            rules : {
                serviceCharge : {
                    required : true,
                    min : 0,
                    max : 9999,
                    number : true
                },
                distributeCharge : {
                    required : true,
                    min : 0,
                    max : 9999,
                    number : true
                },
                fullDistributeMoney : {
                    min : 0,
                    max : 9999,
                    number : true
                },
                freeDistributeMoney : {
                    min : 0,
                    max : 9999,
                    number : true
                },
                money : {
                    min : 0,
                    number : true
                },
                benefitMoney : {
                    min : 0,
                    number : true
                },
                unitName : {
                    checkUnitName : true,
                    checkUnitNameDuplicate : true
                }
            },
            messages : {
                serviceCharge : {
                    required : "请填写上门服务费用"
                },
                distributeCharge : {
                    required : "请填写配送费用"
                },
                fullDistributeMoney : {
                    required : "请填写起送金额"
                },
                freeDistributeMoney : {
                    required : "请填写免运金额"
                },
            },
            submitHandler : function(form) {
                // 预约暂停时间校验
           		var nowDate = new Date();
                if (nowDate > getNewDate($("#stopBusinessEndTime").val())) {
                    top.$.jBox.tip('预约暂停营业截止时间不可早于当前时间，请重新填写或清空内容。','error');
                    return;
                }
                // 校验满减金额
                if (!checkPromoMoneyRequired() || !checkPromoMoney() || !checkPromoMoneyDuplicate()) {
                    return;
                }
                $.ajax({
                    type : 'POST',
                    url : '${ctx}/setup/businessInfo/getResult',
                    data : {
                            sHours:getTime("sHours"),
                            eHours:getTime("eHours"),
                            sMinutes:getTime("sMinutes"),
                            eMinutes:getTime("eMinutes")
                     },
                    success : function(data) {
                        if(data==''){
                            $.ajax({
                                type : 'POST',
                                url : '${ctx}/setup/businessInfo/getResult',
                                data : {
                                        sHours:getTime("sHours1"),
                                        eHours:getTime("eHours1"),
                                        sMinutes:getTime("sMinutes1"),
                                        eMinutes:getTime("eMinutes1")
                                 },
                                success : function(data) {
                                    if(data==''){
                                        loading('正在提交，请稍等...');
                                        form.submit();
                                    }else{
                                        top.$.jBox.tip('配送'+data,'error');
                                    }
                                }
                            })
                        }else{
                            top.$.jBox.tip('服务'+data,'error');
                        }
                    }
                })
            },
            errorContainer : "#messageBox",
            errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox")|| element.is(":radio")|| element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else if (element.is("#serviceCharge") || element.is("#distributeCharge") 
                        || element.is("#fullDistributeMoney") || element.is("#freeDistributeMoney")) {
                    error.insertBefore(element.next());
                } else if(element.is("input[name='unitName']")){
                    $("#unitList").append($(error));
                } else {
                    error.insertAfter(element);
                }
            }
        });
        $("input[name='balancemodel']").click(function() {
            if ($(this).val() == 0) {
                $("#lable").html("每笔订单收取订单金额");
                $("#unit").html("%")
            } else {
                $("#lable").html("每笔订单收取");
                $("#unit").html("元")
            }
        });
        var arr=$("#categoryIds").val().replace("[","").replace("]","").replace(/\s/g, "");//split(",")
        arr=arr.split(",")
        var isService=0;
        var isDistribute=0;
        for(var i=0;i<arr.length;i++){
            $("input[name='categoryIdList']").each(function(){
                if($(this).val()==arr[i]){
                    $(this).attr("checked","ture");
                    var pattren=$(this).attr("pattren");
                    if(pattren==0){
                        $("#shangpin").show();
                        $("#cunxiaoTemp").show();
                        isDistribute=1;
                    }else if(pattren==1){
                        $("#fuwu").show();
                        isService=1;
                    } 
                }
            })
        }
        
        /* 省市 服务范围 */
        $("#addrpro").change(function(){
            getVillageList();
        })
        $("#addrcity").change(function(){
            getVillageList();
        })
        var ids = "${businessInfo.villageIds}".split(",");
        
        $("input[name='villageIdList']").each(function () {
            console.log($(this).val());
        });
        /* 根据服务方式判断是否显示服务费用  上门显示*/
        $("input[name='serviceModel']").click(function () {
            bindServiceType($(this).val());
        });
        /* 根据配送类型判断是否显示配送费用 */
        $("input[name='distributeModel']").click(function () {
            bindDistributeType($(this).val());
        });
        
     	// 营业状态与暂停营业时间联动（当当前时间在暂停营业时间范围内时，选中正常营业状态时清空暂停营业时间）
        $("input[name='businessState']").change(function() {
            var businessStateValue = $("input[name='businessState']:checked").val();
            // 当前时间
            var nowDate = new Date();
            if ($("#stopBusinessBeginTime").val() != "" && $("#stopBusinessEndTime").val() != "" 
                    && businessStateValue == 1 && nowDate >= getNewDate($("#stopBusinessBeginTime").val()) && nowDate <= getNewDate($("#stopBusinessEndTime").val())) {
                $("#stopBusinessBeginTime").val("");
                $("#stopBusinessEndTime").val("");
				// 手动清除营业状态合法性提示信息
				var msgBusinessState = "当前时间在暂停营业起止时间段内，营业状态应为暂停营业";
				var $labelError = $("input[name='businessState']:checked").parent().parent().children("label.error");
				if ($labelError.size() != 0) {
				    $.each($labelError,function(index,value,array){
					     if ($(value).text() == msgBusinessState) {
					         $(value).remove();
					     }
				    });
				} 
            }
        });
        
     	// 暂停营业起始时间
        $("#stopBusinessBeginTime").blur(function() {
            clearBusinessStateValidate();
        });
        
     	// 暂停营业结束时间
        $("#stopBusinessEndTime").blur(function() {
            clearBusinessStateValidate();
        });
    });
    
 	// 手动清除营业状态合法性提示信息并清除校验缓存
    function clearBusinessStateValidate(){
        var businessStateValue = $("input[name='businessState']:checked").val();
        // 当前时间
        var nowDate = new Date();
        // 当前时间在暂停营业时间范围内，自动将营业状态设成暂停营业
        if ($("#stopBusinessBeginTime").val() != "" && $("#stopBusinessEndTime").val() != "" 
                && nowDate >= getNewDate($("#stopBusinessBeginTime").val()) && nowDate <= getNewDate($("#stopBusinessEndTime").val())) {
            $("input[name='businessState'][value='0']").attr("checked","checked");
        }
    }
 
    /* 满减金额必填校验 */
    function checkPromoMoneyRequired() {
        if ($("#promotionFlag").attr("checked") != "checked") {
            return true;
        }
        var $moneys = $("input[name='money']"); 
        var $benefitMoneys = $("input[name='benefitMoney']"); 
        var size = $moneys.size();
        var allEmptyFlag = true;
        for (var i = 0; i < size; i++) {
            if ($moneys.eq(i).val() != "" && $benefitMoneys.eq(i).val() != "") {
                allEmptyFlag = false;
                break;
            }
        }
        if (allEmptyFlag) {
            top.$.jBox.tip('满减金额不可为空','error');
        }
        return !allEmptyFlag;
    }
 
 	/* 校验满减金额两个文本框：前方满XX元文本框应大于后方减XX元文本框 */
    function checkPromoMoney() {
        if ($("#promotionFlag").attr("checked") != "checked") {
            return true;
        }
        var $moneys = $("input[name='money']"); 
        var $benefitMoneys = $("input[name='benefitMoney']"); 
        var size = $moneys.size();
        for (var i = 0; i < size; i++) {
            if ($moneys.eq(i).val() != "" && $benefitMoneys.eq(i).val() != "" 
                    && parseFloat($moneys.eq(i).val()) <= parseFloat($benefitMoneys.eq(i).val())) {
                top.$.jBox.tip('您输入的满减金额有误，请检查','error');
                return false;
            }
        }
        return true;
    }
 
    /* 满减金额重复性校验 */
    function checkPromoMoneyDuplicate() {
        if ($("#promotionFlag").attr("checked") != "checked") {
            return true;
        }
        var $moneys = $("input[name='money']"); 
        var $benefitMoneys = $("input[name='benefitMoney']"); 
        var size = $moneys.size();
        for (var i = 0; i < size; i++) {
            for (var j = i + 1; j < size; j++) {
	            if ($moneys.eq(i).val() != "" && $benefitMoneys.eq(i).val() != "" 
	                    && $moneys.eq(j).val() != "" && $benefitMoneys.eq(j).val() != ""
	                    && $moneys.eq(i).val() == $moneys.eq(j).val()) {
	                top.$.jBox.tip('您输入的满减金额重复，请检查','error');
	                return false;
	            }
            }
        }
        return true;
    }
    
    /* 初始化营业时间 */
    function initBusinessState() {
     	// 最终判定的营业状态
        var resultState; 
        // 营业状态：正常营业
        var normalState = "1";
     	// 营业状态：暂停营业
        var stopState = "0";
        var businessState = '${businessInfo.businessState}';
        var beginDate = $("#stopBusinessBeginTime").val();
        var endDate = $("#stopBusinessEndTime").val();
        if (beginDate != null && beginDate != "" && endDate != null && endDate != "") {
            beginDate = getNewDate(beginDate);
            endDate = getNewDate(endDate);
        } else {
        	// 未启用预约暂停营业起止时间，设定营业状态
        	$("input[name='businessState'][value='"+businessState+"']").attr("checked","checked");
        	return;
        }
     	// 当前时间
        var nowDate = new Date();
	    
	 	// 正常状态
	    if (businessState == normalState) {
	        // 当前时间在预约暂停营业时段中
	        if (nowDate >= beginDate && nowDate <= endDate) {
	            resultState = stopState;
	        } else {
	            resultState = normalState;
	        }
	        
	    // 暂停状态
	    } else if (businessState == stopState) {
	        // 当前时间在预约暂停营业结束时间后
			if (nowDate >= endDate) {
    			resultState = normalState;
	        } else {
	            resultState = stopState;
	        }
	    }
	    
	    // 设定营业状态
	    $("input[name='businessState'][value='"+resultState+"']").attr("checked","checked");
    }
 
    /* 配送方式 */
    function bindDistributeType(type){
        if(type==0){
            $("#distribute").show();
        }else{
            $("#distribute").hide();
        }
    };
    /* 绑定上门（1）、到店（0）应该展示的数据 */
    function bindServiceType(type){
        if(type==0){
            $("#shangmen").show();
            $(".servicesLable").html("上门")
        }else{
            $("#shangmen").hide();
            $(".servicesLable").html("到店")
        }
    };
    /*格式化空格 */
    function trim(s){
        return s.replace(/(^\s*)|(\s*$)/g, "");
    }
    function getTime(nameVal){
        var time="";
        $("select[name="+nameVal+"]").each(function (i) {
            var val = $(this).val();
            var node = i!=11 ? val + "," : val ;
            time+=val+",";
        });
        return time;
    }
    function valiTime1(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/getResult',
            data : {
                    sHours:getTime("sHours1"),
                       eHours:getTime("eHours1"),
                       sMinutes:getTime("sMinutes1"),
                       eMinutes:getTime("eMinutes1")
             },
            success : function(data) {
                if(data==''){
                    
                }else{
                    top.$.jBox.tip('配送'+data,'error');
                    return;
                }
            }
        })
    }
    function valiTime(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/getResult',
            data : {
                    sHours:getTime("sHours"),
                       eHours:getTime("eHours"),
                       sMinutes:getTime("sMinutes"),
                       eMinutes:getTime("eMinutes")
             },
            success : function(data) {
                if(data==''){
                    
                }else{
                    top.$.jBox.tip('服务'+data,'error');
                    isVali=1;
                    console.log(data)
                    return;
                }
            }
        })
    }
    
    function bindList(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindList',
            data : {
                timetype:$("#serviceModel").val(),
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addRow(null);
                }
                for(var  i=0;i<data.length;i++){
                    var sHours=data[i].beginHour;
                    var eHours=data[i].endHour;
                    var sMinutes=data[i].beginMinute;
                    var eMinutes=data[i].endMinute;
                    $("select[name=sHours]").eq(i).val(sHours);
                    $("select[name=eHours]").eq(i).val(eHours);
                    $("select[name=sMinutes]").eq(i).val(sMinutes);
                    $("select[name=eMinutes]").eq(i).val(eMinutes);
                }
            }
        })
    }
    
    function bindList1(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindList',
            data : {
                timetype:2,
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addRow1(null);
                }
                for(var  i=0;i<data.length;i++){
                    var sHours=data[i].beginHour;
                    var eHours=data[i].endHour;
                    var sMinutes=data[i].beginMinute;
                    var eMinutes=data[i].endMinute;
                    $("select[name=sHours1]").eq(i).val(sHours);
                    $("select[name=eHours1]").eq(i).val(eHours);
                    $("select[name=sMinutes1]").eq(i).val(sMinutes);
                    $("select[name=eMinutes1]").eq(i).val(eMinutes);
                }
            }
        })
    }
    
    function getTimeList(interval){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/getTimeList',
            data : {"interval" : interval},
            dataType : 'json',
            success : function(data) {
                var i = 0;
                var sHousrs='<select class="timelist" name="sHours">';
                var eHousrs='<select class="timelist" name="eHours">';
                var sHousrs1='<select class="timelist" name="sHours1">';
                var eHousrs1='<select class="timelist" name="eHours1">';
                var optionHours1='';
                for ( var each in data[0]) {
                    if(data[0][each]=="08"){
                        optionHours1+='<option selected="ture"  value="'+data[0][each]+'">'+data[0][each]+'</option>';
                    }else{
                        optionHours1+='<option value="'+data[0][each]+'">'+data[0][each]+'</option>';
                    }
                    i++;
                }
                var optionHours2='';
                for ( var each in data[0]) {
                    if(data[0][each]=="10"){
                        optionHours2+='<option selected="ture"  value="'+data[0][each]+'">'+data[0][each]+'</option>';
                    }else{
                        optionHours2+='<option value="'+data[0][each]+'">'+data[0][each]+'</option>';
                    }
                    i++;
                }
                var sMinutes='<select class="timelist" name="sMinutes">';
                var eMinutes='<select class="timelist" name="eMinutes">';
                var sMinutes1='<select class="timelist" name="sMinutes1">';
                var eMinutes1='<select class="timelist" name="eMinutes1">';
                var optionMinutes='';
                for ( var each in data[1]) {
                    optionMinutes+='<option  value="'+data[1][each]+'">'+data[1][each]+'</option>';
                    i++;
                }
                var selectEnd='</select>';
                var domRow='<div class="controls" id="item">';
                domRow+=sHousrs+optionHours1+selectEnd+sMinutes+optionMinutes+selectEnd;
                domRow+='至';
                domRow+=eHousrs+optionHours2+selectEnd+eMinutes+optionMinutes+selectEnd;
                domRow+='<a class="btn btn-default" onclick="addRow(this)"><i class="icon-plus"></i></a>';
                domRow+='</div>';
                $("#timeList").append($(domRow))
                
                var domRow1='<div class="controls" id="item1">';
                domRow1+=sHousrs1+optionHours1+selectEnd+sMinutes1+optionMinutes+selectEnd;
                domRow1+='至';
                domRow1+=eHousrs1+optionHours2+selectEnd+eMinutes1+optionMinutes+selectEnd;
                domRow1+='<a class="btn btn-default" onclick="addRow1(this)"><i class="icon-plus"></i></a>';
                domRow1+='</div>';
                $("#timeList1").append($(domRow1));
                bindList();
                bindList1();
            }
        })
    }
    
    /* 绑定单位信息数据 */
    function bindUnit(){
        // 启用自定义单位
        if($("#customUnitFlag").val()==1){
            $("#customUnitFlag").attr("checked","checked")
        }
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindUnit',
            data : {
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addUnitRow(null);
                }
                for(var  i=0;i<data.length;i++){
                    var name=data[i].name;
                    $("input[name=unitName]").eq(i).val(name);
                }
             	// 不启用自定义单位
                if($("#customUnitFlag").val()!=1){
                    $("input[name='unitName']").prop("disabled", true);
                    $("a.unitNameAdd").removeAttr("onClick");
                    $("a.unitNameAdd").attr("disabled","disabled");
                    $("a.unitNameDelete").removeAttr("onClick");
                    $("a.unitNameDelete").attr("disabled","disabled");
                }
            }
        })
    }
    /* 绑定活动信息数据 */
    function bindSales(){
        // 启用满减活动
        if($("#promotionFlag").val()==1){
            $("#promotionFlag").attr("checked","checked")
        }
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindSales',
            data : {
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addSalesRow(null);
                }
                for(var  i=0;i<data.length;i++){
                    var money=data[i].money;
                    var benefitMoney=data[i].benefitMoney;
                    $("input[name=money]").eq(i).val(money);
                    $("input[name=benefitMoney]").eq(i).val(benefitMoney);
                }
                // 不启用满减活动
                if($("#promotionFlag").val()!=1){
                    $("input[name='money']").prop("disabled", true);
                    $("input[name='benefitMoney']").prop("disabled", true);
                    $("a.moneyAdd").removeAttr("onClick");
                    $("a.moneyAdd").attr("disabled","disabled");
                    $("a.moneyDelete").removeAttr("onClick");
                    $("a.moneyDelete").attr("disabled","disabled");
                }
            }
        })
    }

</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <span><a href="${ctx}/setup/businessInfo/">商家设置 ></a>营业设置</span>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="businessInfo" action="${ctx}/setup/businessInfo/updateBusiness" method="post" enctype="multipart/form-data" class="form-horizontal">
        <form:hidden id="businessinfoId" path="id" />
        <input id="distributeModel" type="hidden" value="${businessInfo.distributeModel}" />
        <input id="serviceModel" type="hidden" value="${businessInfo.serviceModel}" />
        <input id="villageIds" type="hidden" value="${businessInfo.villageIds}" />
        <input id="categoryIds" type="hidden"  value="${businessInfo.categoryIdList}" />
        <sys:message content="${message}" />
        <div class="control-group hide">
            <label class="control-label">商家分类:</label>
            <div class="controls">
                <%-- <form:checkboxes path="categoryIdList" disabled="true"  items="${allCategory}" itemLabel="categoryName" itemValue="id" htmlEscape="false" class="required" /> --%> 
                <c:forEach items="${allCategory}" var="category"  varStatus="status">
                    <input ${not empty businessInfo.id?'disabled="disabled" ':''}  name="categoryIdList"  type="checkbox" pattren="${category.prodType}"  value="${category.id}" >${category.categoryName}</input>
                </c:forEach>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" style="width: 700px">
            <span class="help-inline">正常营业前，需要设置好营业状态，暂停营业可以临时进行切换为暂停营业暂停营业中，普及e家将不显示您的商家信息和商品。</span>    
        </div>
        <div class="control-group" style="width: 700px">
            <label class="control-label">营业状态：</label>
            <div class="controls">
                <form:radiobuttons path="businessState" items="${fns:getDictList('businessstate')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
            	<span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" style="width: 700px">
            <label class="control-label">预约暂停营业起始时间：</label>
            <div class="controls">
                <input id="stopBusinessBeginTime" name="stopBusinessBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${businessInfo.stopBusinessBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <div class="control-group" style="width: 700px">
            <label class="control-label">预约暂停营业结束时间：</label>
            <div class="controls">
                <input id="stopBusinessEndTime" name="stopBusinessEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate compareValiTime" value="<fmt:formatDate value="${businessInfo.stopBusinessEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <!-- 服务设置 -->
        <div id="fuwu" style="background: #F0F0F0;width: 700px">
            <label style="font-size: 14px;font-weight: bold;margin: 10px">服务设置</label>
            <br>
            <div class="control-group">
                <label class="control-label">预约服务方式：</label>
                <div class="controls">
                    <form:radiobuttons path="serviceModel" items="${fns:getDictList('servicemodel')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><span class="servicesLable">上门</span>服务时段：</label>
                <div id="timeList">
                
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">服务时间间隔：</label>
                <div class="controls">
                    <form:select path="serviceTimeInterval" class="timelist">
                        <form:options items="${fns:getDictList('servicetimeinterval')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>分钟
                    <span class="help-inline"><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;（指：服务时间间隔） </span>
                </div>
            </div>
            <div id="shangmen">
                <div class="control-group">
                    <label class="control-label">上门服务费用：</label>
                    <div class="controls">
                        <form:input path="serviceCharge" htmlEscape="false" class="input-small required number"/>
                        元 <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">最短上门时间：</label>
                    <div class="controls">
						<form:select path="shortestTime" class="timelist">
	                        <form:options items="${fns:getDictList('shortest_arrive_time')}" itemLabel="label" itemValue="value" htmlEscape="false" />
	                    </form:select>分钟
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </div>
        </div>
        <!-- 配送设置 -->
        <div id="shangpin" style="background: #F0F0F0;width: 700px">
            <label style="font-size: 14px;font-weight: bold;margin: 10px">配送设置</label>
            <div class="control-group">
                <label class="control-label">商品配送方式：</label>
                <div class="controls">
                    <form:radiobuttons path="distributeModel" items="${fns:getDictList('distributemodel')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div id="distribute">
            <div class="control-group">
                <label class="control-label">上门配送时段：</label>
                <div id="timeList1">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">配送时间间隔：</label>
                <div class="controls">
                    <form:select path="distributeTimeInterval" class="timelist">
                        <form:options items="${fns:getDictList('servicetimeinterval')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>分钟          
                    <span class="help-inline"><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;（指：取送时间间隔） </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">最短送达时间：</label>
                <div class="controls">
						<form:select path="shortestArriveTime" class="timelist">
	                        <form:options items="${fns:getDictList('shortest_arrive_time')}" itemLabel="label" itemValue="value" htmlEscape="false" />
	                    </form:select>分钟
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group" >
                <label class="control-label">配送费用：</label>
                <div class="controls">
                    <form:input path="distributeCharge" htmlEscape="false" class="input-small required number" />元
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
                <div class="controls" style="margin-top: 5px">
                    <!--  满额起配金额-->
                    
                    <input id="fullDistributeFlag" value="${businessInfo.fullDistributeFlag}" type="checkbox" />
                    <input id="hidFullDistributeFlag" name="fullDistributeFlag" value="${businessInfo.fullDistributeFlag}" type="hidden" />
                    满<form:input path="fullDistributeMoney" disabled="ture" htmlEscape="false" class="input-small required number" />元起送
                    <span class="help-inline"><font color="red"></font></span>
                </div>
                <div class="controls" style="margin-top: 5px">
                    <!--满额免运费的金额  -->
                    <input id="freeDistributeFlag" type="checkbox" value="${businessInfo.freeDistributeFlag}"/>
                    <input  id="hidFreeDistributeFlag" name="freeDistributeFlag" type="hidden" value="${businessInfo.freeDistributeFlag}"/>
                    满<form:input path="freeDistributeMoney" htmlEscape="false" class="input-small required number" />元免运费
                    <span class="help-inline"><font color="red"></font></span>
                </div>
            </div>
            </div>
        </div>
        <div id="cunxiaoTemp" class="control-group"  style="background: #F0F0F0;width: 700px">

            <label style="font-size: 14px;font-weight: bold;margin: 10px">促销设置</label>
            <br>
            <div class="controls"  style="margin-left: 20px">
            <input id="promotionFlag"  type="checkbox" value="${businessInfo.promotionFlag}" />
            <input id="hidPromotionFlag" name="promotionFlag"  type="hidden" value="${businessInfo.promotionFlag}" />
            启用满减活动
            </div>
            <div class="controls"  id="prom" style="margin-top: 10px;margin-left: 30px">
                <!--  满减金额-->
                满<input name="money" type="text"  maxlength="5" class="input-small number"/>元
                减<input name="benefitMoney"  maxlength="5"  type="text" class="input-small number"/>元
                <a class="btn btn-default moneyAdd" onclick="addSalesRow(this)"><i class="icon-plus"></i></a>
                <span class="help-inline"><font color="red"></font></span>
            </div>
        </div>
        <div class="control-group"  style="background: #F0F0F0;width: 700px">
            <label style="font-size: 14px;font-weight: bold;margin: 10px">单位设置</label>
            <br>
            <div class="controls"   style="margin-left: 20px">
                <input id="customUnitFlag" value="${businessInfo.customUnitFlag}" type="checkbox" />
                <input name="customUnitFlag" id="hidCustomUnitFlag" value="${businessInfo.customUnitFlag}" type="hidden" />
                启用自定义单位
            </div>
            <div id="unitList" class="controls" style="margin-top: 10px;margin-left: 30px" >
                <!-- 单位设置-->
                单位名称：<input name="unitName" type="text" maxlength="10"  class="input-small"/>	
                <a class="btn btn-default unitNameAdd" onclick="addUnitRow(this)"><i class="icon-plus"></i></a>
            </div>
            
            <span class="help-inline">商家可根据营业场景需要自定义商品、服务产品模式的单位。</span>    
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="setup:businessInfo:services">
                <input id="btnSubmit" style="width:60px" class="commonbtn" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" style="width:60px" class="commonbtn" type="button" value="返 回" onclick="window.location.href='${ctx}/setup/businessInfo'"/>
        </div>
    </form:form>
    <script type="text/javascript">
    function addRow(elem){
        var domRow='<div class="controls">';
        domRow+=$('#item').html();
        domRow+='<a class="btn btn-default" onclick="removeRow(this)"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        if (elem == null) {
        	$("#timeList").append($(domRow));
        } else {
            $(elem).parent().after($(domRow));
        }
    }
    function removeRow(elem){
        $(elem).parent().remove();
    }
    function addRow1(elem){
        var domRow='<div class="controls">';
        domRow+=$('#item1').html();
        domRow+='<a class="btn btn-default" onclick="removeRow1(this)"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        if (elem == null) {
            $("#timeList1").append($(domRow));
        } else {
            $(elem).parent().after($(domRow));
        }
        
    }
    function removeRow1(elem){
        $(elem).parent().remove();
    }
    /*添加满减活动*/
    function addSalesRow(elem){
        var domRow='<div class="controls" style="margin-top: 10px;margin-left: 30px" >';
//         domRow+=$('#prom').html();
		domRow+='<!--  满额起配金额-->    满<input name="money" type="text"  maxlength="5" class="input-small number"/>元 ' 
			+'减<input name="benefitMoney"  maxlength="5"  type="text" class="input-small number"/>元 '
			+ '<a class="btn btn-default moneyAdd" onclick="addSalesRow(this)"><i class="icon-plus"></i></a>';
        domRow+='<a class="btn btn-default moneyDelete" onclick="removeSalesRow(this)"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        if (elem == null) {
            $("#prom").parent().append($(domRow));
        } else {
            $(elem).parent().after($(domRow));
        }
    }
    /*移除满减活动*/
    function removeSalesRow(elem){
        $(elem).parent().remove();
    }
    
    /*添加单位名称*/
    function addUnitRow(elem){
        var domRow='<div class="controls" style="margin-top: 10px;margin-left: 30px" >';
//         domRow+=$('#unitList').html();
		domRow+='<!-- 单位设置--> 单位名称：<input name="unitName" type="text" maxlength="10"  class="input-small"/> <a class="btn btn-default unitNameAdd" onclick="addUnitRow(this)"><i class="icon-plus"></i></a>';
        domRow+='<a class="btn btn-default unitNameDelete" onclick="removeUnitRow(this)"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        if (elem == null) {
            $("#unitList").parent().children("span.help-inline").before($(domRow));
        } else {
            $(elem).parent().after($(domRow));
        }
    }
    /*移除单位名称*/
    function removeUnitRow(elem){
         $(elem).parent().remove();
         // 清除重复校验缓存
         var $unitNames = $("input[name='unitName']");
         var duplicateFlag = false;
         for (var i = 0; i < $unitNames.size(); i++) {
             for (var j = i + 1; j < $unitNames.size(); j++) {
                 if ($unitNames.eq(i).val() != "" && $unitNames.eq(j).val() != "" 
                         && $unitNames.eq(i).val() == $unitNames.eq(j).val()) {
                     duplicateFlag = true;
                 }
             }
         }
         
         // 单位名称不重复，清除重复校验提示
         if (!duplicateFlag) {
             var msgUnitName = "单位名称不可重复";
             var $labelError = $("#unitList").children("label.error");
             if ($labelError.size() != 0) {
                 $.each($labelError,function(index,value,array){
	                  if ($(value).text() == msgUnitName) {
	                      $(value).remove();
	                  }
                 });
             }
         }
    }
    /* 满额起送 */
    $("#fullDistributeFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $("#hidFullDistributeFlag").val("1");
            $("#fullDistributeMoney").prop("disabled", "");
        }else{
            $("#hidFullDistributeFlag").val("0");
//             $("#fullDistributeMoney").val("");
            $("#fullDistributeMoney").prop("disabled", true);
            // 手动清除校验信息
            var $labelError = $("#fullDistributeMoney").parent().children("label.error");
            if ($labelError.size() != 0) {
                $.each($labelError,function(index,value,array){
                     $(value).remove();
                });
            } 
        }
    })
    /* 满额起送 */
    $("#freeDistributeFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $("#hidFreeDistributeFlag").val("1");
            $("#freeDistributeMoney").prop("disabled", "");
        }else{
            $("#hidFreeDistributeFlag").val("0");
//             $("#freeDistributeMoney").val("");
            $("#freeDistributeMoney").prop("disabled", true);
         	// 手动清除校验信息
            var $labelError = $("#freeDistributeMoney").parent().children("label.error");
            if ($labelError.size() != 0) {
                $.each($labelError,function(index,value,array){
                     $(value).remove();
                });
            } 
        }
    })
    /*是否启用满减活动  */
    $("#promotionFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $("#hidPromotionFlag").val("1");
            $("input[name='money']").prop("disabled", "");
            $("input[name='benefitMoney']").prop("disabled", "");
         	// 启用加行删行按钮
            $("a.moneyAdd").removeAttr("disabled");
            $("a.moneyAdd").attr("onClick","addSalesRow(this)")
            $("a.moneyDelete").removeAttr("disabled");
            $("a.moneyDelete").attr("onClick","removeSalesRow(this)")
        }else{
            $("#hidPromotionFlag").val("0");
            $("input[name='money']").prop("disabled", true);
            $("input[name='benefitMoney']").prop("disabled", true);
            // 禁用加行删行按钮
            $("a.moneyAdd").removeAttr("onClick");
            $("a.moneyAdd").attr("disabled","disabled");
            $("a.moneyDelete").removeAttr("onClick");
            $("a.moneyDelete").attr("disabled","disabled");
        }
    })
    /*是否启用自定义单位  */
    $("#customUnitFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $("#hidCustomUnitFlag").val("1");
            $("input[name='unitName']").prop("disabled", "");
         	// 启用加行删行按钮
            $("a.unitNameAdd").removeAttr("disabled");
   			$("a.unitNameAdd").attr("onClick","addUnitRow(this)")
   			$("a.unitNameDelete").removeAttr("disabled");
   			$("a.unitNameDelete").attr("onClick","removeUnitRow(this)")
        }else{
            $("#hidCustomUnitFlag").val("0");
            $("input[name='unitName']").prop("disabled", true);
         	// 禁用加行删行按钮
         	$("a.unitNameAdd").removeAttr("onClick");
			$("a.unitNameAdd").attr("disabled","disabled");
			$("a.unitNameDelete").removeAttr("onClick");
			$("a.unitNameDelete").attr("disabled","disabled");
        	// 手动清除营业状态合法性提示信息
            var $labelError = $("#unitList").children("label.error");
            if ($labelError.size() != 0) {
                $.each($labelError,function(index,value,array){
            	      $(value).remove();
                });
            } 
        }
    })
    
    </script>
</body>
</html>