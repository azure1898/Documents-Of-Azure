<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息修改-继续营业设置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
var isVali=0;
    $(document).ready(function() {
        
        $("#shangpin").hide();//0如果选择的商家分类所对应的产品模式为“商品配买”，那么关联显示出此块内容
        $("#fuwu").hide();//1如果选择的商家分类所对应的产品模式为“服务预约”，那么关联显示出此块内容。
        getTimeList($("#serviceTimeInterval").val());
        /* 绑定到点上门 */
        bindServiceType($("#serviceModel").val());
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
        /* 启用满减活动 */
        if($("#promotionFlag").val()==1){
            $("#promotionFlag").attr("checked","checked")
        }else{
            $("input[name='money']").prop("disabled", true);
            $("input[name='benefitMoney']").prop("disabled", true);
        }
        /* 自定义单位 */
        if($("#customUnitFlag").val()==1){
            $("#customUnitFlag").attr("checked","checked")
        }else{
            $("input[name='unitName']").prop("disabled", true);
        }
        /* 初始化单位信息 */
        bindUnit();
        /* 初始化活动信息 */
        bindSales();
        $("#inputForm").validate({
            rules : {
                
            },
            messages : {
                
            },
            submitHandler : function(form) {
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
        $("#serviceTimeInterval").change(function(){
            $("#timeList").html("");
            getTimeList($(this).val());
        })
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
    });
    /* 配送方式 */
    function bindDistributeType(type){
        if(type==0){
            $("#distribute").show();
        }else{
            $("#distribute").hide();
        }
    };
    /* 绑定上门、到店应该展示的数据 */
    function bindServiceType(type){
        if(type==1){
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
                    addRow();
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
                    addRow1();
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
                domRow+='<a class="btn btn-default" onclick="addRow()"><i class="icon-plus"></i></a>';
                domRow+='</div>';
                $("#timeList").append($(domRow))
                
                var domRow1='<div class="controls" id="item1">';
                domRow1+=sHousrs1+optionHours1+selectEnd+sMinutes1+optionMinutes+selectEnd;
                domRow1+='至';
                domRow1+=eHousrs1+optionHours2+selectEnd+eMinutes1+optionMinutes+selectEnd;
                domRow1+='<a class="btn btn-default" onclick="addRow1()"><i class="icon-plus"></i></a>';
                domRow1+='</div>';
                $("#timeList1").append($(domRow1));
                bindList();
                bindList1();
            }
        })
    }
    
    /* 绑定单位信息数据 */
    function bindUnit(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindUnit',
            data : {
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addUnitRow();
                }
                for(var  i=0;i<data.length;i++){
                    var name=data[i].name;
                    $("input[name=unitName]").eq(i).val(name);
                }
            }
        })
    }
    /* 绑定活动信息数据 */
    function bindSales(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/bindSales',
            data : {
                businessinfoId:$("#businessinfoId").val()
             },
            dataType : 'json',
            success : function(data) {
                for(var  i=0;i<data.length-1;i++){
                    addSalesRow();
                }
                for(var  i=0;i<data.length;i++){
                    var money=data[i].money;
                    var benefitMoney=data[i].benefitMoney;
                    $("input[name=money]").eq(i).val(money);
                    $("input[name=benefitMoney]").eq(i).val(benefitMoney);
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
            </div>
        </div>
        <div class="control-group" style="width: 700px">
            <label class="control-label">暂停营业起始时间：</label>
            <div class="controls">
                <input name="stopBusinessBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${businessInfo.stopBusinessBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <div class="control-group" style="width: 700px">
            <label class="control-label">暂停营业结束时间：</label>
            <div class="controls">
                <input name="stopBusinessEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${businessInfo.stopBusinessEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
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
                <label class="control-label"><span class="servicesLable">上门</span>服务时间间隔：</label>
                <div class="controls">
                    <form:select path="serviceTimeInterval" class="input-mini">
                        <form:options items="${fns:getDictList('servicetimeinterval')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>分钟
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><span class="servicesLable">上门</span>服务时段：</label>
                <div id="timeList">
                
                </div>
            </div>
            <div id="shangmen">
                <div class="control-group">
                    <label class="control-label">上门服务费用：</label>
                    <div class="controls">
                        <form:input path="serviceCharge" htmlEscape="false" class="input-large required number" />
                        元 <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">最短上门时间：</label>
                    <div class="controls">
                        <form:input path="shortestTime" htmlEscape="false" maxlength="11" class="input-large required number" />
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
            <div class="control-group">
                <label class="control-label">上门配送时段：</label>
                <div id="timeList1">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">配送时间间隔：</label>
                <div class="controls">
                    <!-- <select  name="distributeTimeInterval">
                        <option value="30">30</option>
                        <option value="60">60</option>
                    </select> -->
                    <form:select path="distributeTimeInterval" class="timelist">
                        <form:options items="${fns:getDictList('servicetimeinterval')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>分钟                
                    <span class="help-inline">指：取送时间间隔 </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">最短送达时间：</label>
                <div class="controls">
                    <select name="shortestArriveTime">
                        <option value="30">30</option>
                        <option value="60">60</option>
                    </select> <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group" id="distribute">
                <label class="control-label">配送费用：</label>
                <div class="controls">
                    <form:input path="distributeCharge" htmlEscape="false" class="input-large required number" />
                    <span class="help-inline"><font color="red"></font></span>
                </div>
                <div class="controls" style="margin-top: 5px">
                    <!--  满额起配金额-->
                    
                    <input name="fullDistributeFlag" id="fullDistributeFlag" value="${businessInfo.fullDistributeFlag}" type="checkbox" />
                    满<form:input path="fullDistributeMoney" disabled="ture" htmlEscape="false" class="input-small required number"/>元起送
                    <span class="help-inline"><font color="red"></font></span>
                </div>
                <div class="controls" style="margin-top: 5px">
                    <!--满额免运费的金额  -->
                    <input id="freeDistributeFlag" name="freeDistributeFlag" type="checkbox" value="${businessInfo.freeDistributeFlag}"/>
                    满<form:input path="freeDistributeMoney" htmlEscape="false" class="input-small required number"/>元免运费
                    <span class="help-inline"><font color="red"></font></span>
                </div>
            </div>
        </div>
        <div class="control-group"  style="background: #F0F0F0;width: 700px">

            <label style="font-size: 14px;font-weight: bold;margin: 10px">促销设置</label>
            <br>
            <div class="controls"  style="margin-left: 20px">
            <input id="promotionFlag" name="promotionFlag"  type="checkbox" value="${businessInfo.promotionFlag}" />启用满减活动
            </div>
            <div class="controls"  id="prom" style="margin-top: 10px;margin-left: 30px">
                <!--  满额起配金额-->
                满<input name="money" type="text"  maxlength="5" class="input-small required number"/>元
                减<input name="benefitMoney"  maxlength="5"  type="text" class="input-small required number"/>元
                <a class="btn btn-default" onclick="addSalesRow()"><i class="icon-plus"></i></a>
                <span class="help-inline"><font color="red"></font></span>
            </div>
        </div>
        <div class="control-group"  style="background: #F0F0F0;width: 700px">
            <label style="font-size: 14px;font-weight: bold;margin: 10px">单位设置</label>
            <br>
            <div class="controls"   style="margin-left: 20px">
                <input name="customUnitFlag" id="customUnitFlag" value="${businessInfo.customUnitFlag}" type="checkbox" />启用自定义单位
            </div>
            <div id="unitList" class="controls" style="margin-top: 10px;margin-left: 30px" >
                <!-- 单位设置-->
                单位名称：<input name="unitName" type="text" maxlength="10"  class="input-small required"/>
                <a class="btn btn-default" onclick="addUnitRow()"><i class="icon-plus"></i></a>
            </div>
            
            <span class="help-inline">商家可根据营业场景需要自定义商品、服务产品模式的单位。</span>    
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="setup:businessInfo:services">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
    <script type="text/javascript">
    function addRow(){
        var domRow='<div class="controls">';
        domRow+=$('#item').html();
        domRow+='<a class="btn btn-default" onclick="removeRow()"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        $("#timeList").append($(domRow))
    }
    function removeRow(){
        var total=$("#timeList").children().size();//目前添加的总科室数目
        $("#timeList").children().eq(total-1).remove();
    }
    function addRow1(){
        var domRow='<div class="controls">';
        domRow+=$('#item1').html();
        domRow+='<a class="btn btn-default" onclick="removeRow1()"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        $("#timeList1").append($(domRow))
    }
    function removeRow1(){
        var total=$("#timeList1").children().size();//目前添加的总科室数目
        $("#timeList1").children().eq(total-1).remove();
    }
    /*添加满减活动*/
    function addSalesRow(){
        var domRow='<div class="controls" style="margin-top: 10px;margin-left: 30px" >';
        domRow+=$('#prom').html();
        domRow+='<a class="btn btn-default" onclick="removeSalesRow()"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        $("#prom").after($(domRow))
    }
    /*移除满减活动*/
    function removeSalesRow(){
        var total=$("#prom").nextAll().size();//目前添加的总科室数目
        console.log(total);
        $("#prom").nextAll().eq(total-1).remove();
    }
    
    /*添加单位名称*/
    function addUnitRow(){
        var domRow='<div class="controls" style="margin-top: 10px;margin-left: 30px" >';
        domRow+=$('#unitList').html();
        domRow+='<a class="btn btn-default" onclick="removeUnitRow()"><i class="icon-remove"></i></a>';
        domRow+='</div>';
        $("#unitList").after($(domRow))
    }
    /*移除单位名称*/
    function removeUnitRow(){
        var total=$("#unitList").nextAll().size();//目前添加的总科室数目
        console.log(total);
        $("#unitList").nextAll().eq(total-2).remove();
    }
    /* 满额起送 */
    $("#fullDistributeFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $(this).val("1");
            $("#fullDistributeMoney").prop("disabled", "");
        }else{
            $(this).val("0");
            $("#fullDistributeMoney").prop("disabled", true);
        }
    })
    /* 满额起送 */
    $("#freeDistributeFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $(this).val("1");
            $("#freeDistributeMoney").prop("disabled", "");
        }else{
            $(this).val("0");
            $("#freeDistributeMoney").prop("disabled", true);
        }
    })
    /*是否启用满减活动  */
    $("#promotionFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $(this).val("1");
            $("input[name='money']").prop("disabled", "");
            $("input[name='benefitMoney']").prop("disabled", "");
        }else{
            $(this).val("0");
            $("input[name='money']").prop("disabled", true);
            $("input[name='benefitMoney']").prop("disabled", true);
        }
    })
    /*是否启用自定义单位  */
    $("#customUnitFlag").click(function(){
        if($(this).attr("checked")=="checked"){
            $(this).val("1");
            $("input[name='unitName']").prop("disabled", "");
        }else{
            $(this).val("0");
            $("input[name='unitName']").prop("disabled", true);
        }
    })
    
    </script>
</body>
</html>