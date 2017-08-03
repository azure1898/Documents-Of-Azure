<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">

    $(document).ready(function() {
        $("#shangpin").hide();//0如果选择的商家分类所对应的产品模式为“商品配买”，那么关联显示出此块内容
        $("#fuwu").hide();//1如果选择的商家分类所对应的产品模式为“服务预约”，那么关联显示出此块内容。
        fillPro();//加载全部省市区数据；
        getTimeList($("#serviceTimeInterval").val());
        var name=$("#businessName").val();
        $("#inputForm").validate({
            rules : {
                businessName : {
                    remote : {
                       url:"${ctx}/business/businessInfo/checkName",
                       data:{
                           name:name,
                           oldName:'${businessInfo.businessName}'
                       } 
                     } 
                 },
                 phoneNum : {
                    required : true,
                    minlength : 11,
                    isMobile : true
                  },
            },
            messages : {
                businessName : {
                    remote : "商家名称已存在"
                },
                phoneNum : {
                    required : "请输入手机号",
                    minlength : "确认手机不能小于11个字符",
                    isMobile : "请正确填写您的手机号码"
                 },
            },
            submitHandler : function(form) {
                $.ajax({
                    type : 'POST',
                    url : '${ctx}/business/businessInfo/getResult',
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
                                url : '${ctx}/business/businessInfo/getResult',
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
        for(var i=0;i<arr.length;i++){
            $("input[name='categoryIdList']").each(function(){
                if($(this).val()==arr[i]){
                    $(this).attr("checked","ture");
                    var pattren=$(this).attr("pattren");
                    if(pattren==0){
                        $("#shangpin").show();
                    }else if(pattren==1){
                        $("#fuwu").show();
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
    });
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
    
    function bindList(){
        $.ajax({
            type : 'POST',
            url : '${ctx}/business/businessInfo/bindList',
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
            url : '${ctx}/business/businessInfo/bindList',
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
            url : '${ctx}/business/businessInfo/getTimeList',
            data : {"interval" : interval},
            dataType : 'json',
            success : function(data) {
                var i = 0;
                var sHousrs='<select class="timelist" name="sHours">';
                var eHousrs='<select class="timelist" name="eHours">';
                var sHousrs1='<select class="timelist" name="sHours1">';
                var eHousrs1='<select class="timelist" name="eHours1">';
                var optionHours='';
                for ( var each in data[0]) {
                    if(data[0][each]=="10"){
                        optionHours+='<option selected="ture"  value="'+data[0][each]+'">'+data[0][each]+'</option>';
                    }else{
                        optionHours+='<option value="'+data[0][each]+'">'+data[0][each]+'</option>';
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
                domRow+=sHousrs+optionHours+selectEnd+sMinutes+optionMinutes+selectEnd;
                domRow+='至';
                domRow+=eHousrs+optionHours+selectEnd+eMinutes+optionMinutes+selectEnd;
                domRow+='<a class="btn btn-default" onclick="addRow()"><i class="icon-plus"></i></a>';
                domRow+='</div>';
                $("#timeList").append($(domRow))
                
                var domRow1='<div class="controls" id="item1">';
                domRow1+=sHousrs1+optionHours+selectEnd+sMinutes1+optionMinutes+selectEnd;
                domRow1+='至';
                domRow1+=eHousrs1+optionHours+selectEnd+eMinutes1+optionMinutes+selectEnd;
                domRow1+='<a class="btn btn-default" onclick="addRow1()"><i class="icon-plus"></i></a>';
                domRow1+='</div>';
                $("#timeList1").append($(domRow1));
                
                bindList();
                bindList1();
            }
        })
    }

</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <a href="${ctx}/business/businessInfo/">商家信息列表</a>
        </li>
        <li class="active">
            <a href="${ctx}/business/businessInfo/form?id=${businessInfo.id}">商家信息<shiro:hasPermission name="business:businessInfo:edit">${not empty businessInfo.id?'修改':'添加'}</shiro:hasPermission> <shiro:lacksPermission name="business:businessInfo:edit">查看</shiro:lacksPermission></a>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="businessInfo" action="${ctx}/business/businessInfo/save" method="post" enctype="multipart/form-data" class="form-horizontal">
        <form:hidden id="businessinfoId" path="id" />
        <input id="serviceModel" type="hidden" value="${businessInfo.serviceModel}" />
        <input id="villageIds" type="hidden" value="${businessInfo.villageIds}" />
        <input id="categoryIds" type="hidden" value="${businessInfo.categoryIdList}" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <form:input path="businessName" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">商家图片：</label>
            <div class="controls">
                <input type="file" name="file" id="btn_file" onchange="javascript:setImagePreview();">
                <c:choose>
                    <c:when test="${not empty businessInfo.businessPic}">
                        <img id="preview" src="${contextPath}/${businessInfo.businessPic}"width="45" height="45" onclick="openBrowse();" />
                    </c:when>
                    <c:otherwise>
                        <img id="preview" src="" width="45" height="45" onclick="openBrowse();" />
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系人：</label>
            <div class="controls">
                <form:input path="contactPerson" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系电话：</label>
            <div class="controls">
                <form:input path="phoneNum" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label">商家分类:</label>
            <div class="controls">
                <%-- <form:checkboxes path="categoryIdList" disabled="true"  items="${allCategory}" itemLabel="categoryName" itemValue="id" htmlEscape="false" class="required" /> --%> 
                <c:forEach items="${allCategory}" var="category"  varStatus="status">
                    <input disabled="disabled"  name="categoryIdList"  type="checkbox" pattren="${category.prodType}"  value="${category.id}" >${category.categoryName}</input>
                </c:forEach>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">城市：</label>
            <div class="controls">
                
                <input type="text" class="hide" id="hidProId"  value="${businessInfo.addrPro }">
                <input type="text" class="hide" id="hidCityId" value="${businessInfo.addrCity }">
                <input type="text" class="hide" id="hidAreaId" value="${businessInfo.addrArea }">
                <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
                <select id="addrcity" name="addrCity" style="width: 120px" onchange="changeArea()">
                    <option value="">全部城市</option>
                </select>
                <select id="addrarea" name="addrArea" style="width: 120px">
                    <option value="">全部区域</option>
                </select>
                <form:input path="addrDetail" placeholder="详细地址" htmlEscape="false" maxlength="200" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">经度：</label>
            <div class="controls">
                <form:input path="longitude" htmlEscape="false" class="input-xlarge required number" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">纬度：</label>
            <div class="controls">
                <form:input path="latitude" htmlEscape="false" class="input-xlarge required number" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">商家标签：</label>
            <div class="controls">
                <form:input path="businessLabel" htmlEscape="false" maxlength="128" class="input-xlarge required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">营业时间：</label>
            <div class="controls">
                <form:input path="businessHours" htmlEscape="false" maxlength="256" class="input-xlarge" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">服务范围：</label>
            <div class="controls" id="villageIdList">
                
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否推荐：</label>
            <div class="controls">
                <form:radiobuttons path="recomFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否在线交易：</label>
            <div class="controls">
                <form:radiobuttons path="onlineFlag" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否支持团购：</label>
            <div class="controls">
                <form:radiobuttons path="groupPurchaseFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">结算模式：</label>
            <div class="controls">
                <form:radiobuttons path="balanceModel" items="${fns:getDictList('balancemodel')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"></label>
            <div class="controls" id="rate">
                <label id="lable">每笔订单收取订单金额</label>
                <form:input path="collectFees"  htmlEscape="false" class="input-mini required number" />
                <label id="unit">%</label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">结算周期：</label>
            <div class="controls">
                <form:select path="balanceCycle" class="input-medium">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('balancecycle')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">商家介绍描述：</label>
            <div class="controls">
                <form:textarea path="businessIntroduce" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">营业状态：</label>
            <div class="controls">
                <form:radiobuttons path="businessState" items="${fns:getDictList('businessstate')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">暂停营业起始时间：</label>
            <div class="controls">
                <input name="stopBusinessBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${businessInfo.stopBusinessBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">暂停营业结束时间：</label>
            <div class="controls">
                <input name="stopBusinessEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${businessInfo.stopBusinessEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </div>
        </div>
        <div id="fuwu">
            <div class="control-group">
                <label class="control-label">预约服务方式：</label>
                <div class="controls">
                    <form:radiobuttons path="serviceModel" items="${fns:getDictList('servicemodel')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">服务时间间隔：</label>
                <div class="controls">
                    <form:select path="serviceTimeInterval" class="input-mini">
                        <form:options items="${fns:getDictList('servicetimeinterval')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>分钟
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">服务时段：</label>
                <div id="timeList">
                
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
        <div id="shangpin">
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
                <label class="control-label">时间片显示区间：</label>
                <div class="controls">
                    <select  name="distributeTimeInterval">
                        <option value="30">30</option>
                        <option value="60">60</option>
                    </select>                
                    <span class="help-inline">指：取送时间间隔 </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">最短送达时间：</label>
                <div class="controls">
                    <select  name="shortestArriveTime">
                        <option value="30">30</option>
                        <option value="60">60</option>
                    </select>    
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
        </div>
        <div class="form-actions">
                <shiro:hasPermission name="business:businessInfo:edit">
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
    </script>
</body>
</html>