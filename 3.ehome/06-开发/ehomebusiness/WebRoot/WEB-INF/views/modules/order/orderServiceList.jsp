<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单-服务类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
    //$("#name").focus();
    $(document).ready(function() {
    	top.$.jBox.tip.mess = null;
        $("#inputForm").validate({
            submitHandler: function(form){
                loading('正在提交，请稍等...');
                form.submit();
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
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
    /*根据画面选择的取消原因传递给后台，并控制取消原因输入框显示非现实
    */
    function setRemarks(remarks,index) {
        $("#cancelRemarks" + index)[0].value = remarks;
        // 当选择是其他原因时显示输入框
        if ("其他原因" == remarks) {
            $("#cancelRemarks" + index)[0].value = "";
            $("#cancelRemarks" + index).css('display','');
        // 其他的时候非显示
        } else {
            $("#cancelRemarks" + index).css('display','none');
        }
    }
    // 根据导航下拉菜单的值跳转至不同窗口
    function hrefByProdType(value) {
        if ('0' == value) {
            // 迁移至商品订单列表页面
            window.location.href="${ctx}/order/orderGoods";
        } else if ('1' == value) {
            // 迁移至服务订单列表页面
            window.location.href="${ctx}/order/orderService";
        } else if ('2' == value) {
            // 迁移至课程培训订单列表页面
            window.location.href="${ctx}/order/orderLesson";
        } else if ('3' == value) {
            // 迁移至场地预约订单列表页面
            window.location.href="${ctx}/order/orderField";
        } else {
            // 迁移至团购订单列表页面
            window.location.href="${ctx}/order/orderGroupPurc";
        }
    }
    // 根据按钮不同改变searchForm的ACTION
    function changeAction(type) {
    	// 检索按钮按下的时候
    	if (type=='0') {
    		$("#searchForm")[0].action = "${ctx}/order/orderService/";
    		return true;
    	// 导出按钮按下的时候
    	} else if (type=='1') {
    		$("#searchForm")[0].action = "${ctx}/order/orderService/export";
    		return true;
    	}
    	return false;
    }
    // 取消原因的必须CHECK
    function cancelRemarksCheck(cancleReasonId) {
        if ($(cancleReasonId).val() == "") {
            alertx("请填写取消原因");
            return false;
        }
        
        return true;
    }
	</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><p style="font-weight:bold;font-size:18px;">订单管理</p></li>
        <li>
            <select id="nowProdType" name="nowProdType" style="width:200px;margin-left:15px;" onchange="hrefByProdType(this.value)">
                <c:forEach items="${businessCategorydictList}" var="businessCategorydict" varStatus="status">
                    <c:choose>
                        <c:when test="${businessCategorydict.prodType == '0'}">
                            <option value="0" <c:if test="${nowProdType == '0'}">selected</c:if>>商品订单</option>
                        </c:when>
                        <c:when test="${businessCategorydict.prodType == '1'}">
                            <option value="1" <c:if test="${nowProdType == '1'}">selected</c:if>>服务订单</option>
                        </c:when>
                        <c:when test="${businessCategorydict.prodType == '2'}">
                            <option value="2" <c:if test="${nowProdType == '2'}">selected</c:if>>课程培训</option>
                        </c:when>
                        <c:when test="${businessCategorydict.prodType == '3'}">
                            <option value="3" <c:if test="${nowProdType == '3'}">selected</c:if>>场地预约</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <option value="4" <c:if test="${nowProdType == '4'}">selected</c:if>>团购订单</option>
            </select>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="orderService" action="${ctx}/order/orderService/" method="post" class="breadcrumb form-search" style="width:95.5%">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li><label>时间：</label>
                <input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                    value="<fmt:formatDate value="${orderService.beginCreateDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
                <input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                    value="<fmt:formatDate value="${orderService.endCreateDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({minDate:beginCreateDate.value,dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </li>
            <li><label>订单号：</label>
                <form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
            </li>
            <li>
                <form:select path="serviceType" class="input-mini">
                    <form:option value="" label="服务方式"/>
                    <form:options items="${fns:getDictList('order_service_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li>
                <form:select path="payState" class="input-mini">
                    <form:option value="" label="支付状态"/>
                    <form:options items="${fns:getDictList('pay_goods_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li>
                <form:select path="orderState" class="input-mini">
                    <form:option value="" label="订单状态"/>
                    <form:options items="${fns:getDictList('order_service_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <form:hidden path="searchFlg" value="1"/>
            </li>
            <li class="btns"><i class="icon-search icon-white"></i><input id="btnSubmit"  onclick="changeAction('0')" class="btn btn-success" type="submit" value="查询"/></li>
            <shiro:hasPermission name="order:orderService:view">
            <li class="btns"><input id="btnExport" class="btn" type="submit" onclick="changeAction('1')" value="导出"/></li>
            </shiro:hasPermission>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98.3%">
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>地址</th>
                <th>金额</th>
                <th>服务方式</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>时间</th>
                <shiro:hasPermission name="order:orderService:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="orderService" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderService/form?id=${orderService.id}">
                    ${orderService.orderNo}
                </a></td>
                <td>
                    ${orderService.accountName}
                </td>
                <td>
                    ${orderService.serviceAddress}
                </td>
                <td>
                    ${orderService.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderService.serviceType, 'order_service_type', "")}
                </td>
                <td <c:if test="${orderService.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderService.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderService.orderState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderService.orderState, 'order_service_state', "")}
                </td>
                <td>
                    <c:if test="${orderService.createDate !=null && orderService.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderService.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderService.payTime !=null && orderService.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderService.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <td><shiro:hasPermission name="order:orderService:view">
                    <a id="btuElemView" class="btn btn-primary" href="${ctx}/order/orderService/form?id=${orderService.id}"><i class="icon-align-justify icon-custom"></i>查看</a>
                    <br/></shiro:hasPermission>
                    <shiro:hasPermission name="order:orderService:accept">
                    <c:if test="${orderService.payState == '1' && orderService.orderState ==  '0'}">
                        <a id="btuElemAccept" class="btn btn-primary" href="${ctx}/order/orderService/accept?id=${orderService.id}&updateDate=${orderService.updateDateString}"><i class="icon-ok icon-custom"></i>受理</a>
                        <br/>
                    </c:if>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:orderService:complete">
                    <c:if test="${orderService.payState == '1' && orderService.orderState ==  '1'}">
                        <a id="btuElemComplete" class="btn btn-primary" href="${ctx}/order/orderService/complete?id=${orderService.id}&updateDate=${orderService.updateDateString}"><i class="icon-ok-circle icon-custom"></i>完成</a><br/>
                    </c:if>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:orderService:cancel">
                    <c:if test="${orderService.orderState !=  '2' && orderService.orderState !=  '3'}">
                        <a id="btuElemCancel" class="btn btn-primary" data-toggle="modal" data-target="#myModal${status.index}"><i class="icon-remove icon-custom"></i>取消</a><br/>
                    </c:if>
                    </shiro:hasPermission>
    <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="display: none;">
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderService" action="${ctx}/order/orderService/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
                <div class="modal-body">
                    <select id="cancelRemarksSelect" class="required" name="cancelRemarksSelect" onchange="setRemarks(this.value,'${status.index}')" style="width:200px;">
                      <option selected value="">请选择订单取消原因</option>
                      <option value="客户联系取消">客户联系取消</option>
                      <option value="无法联系客户">无法联系客户</option>
                      <option value="商品无法送达">商品无法送达</option>
                      <option value="客户未付款，释放库存">客户未付款，释放库存</option>
                      <option value="其他原因">其他原因</option>
                    </select>
                    <br/>
                    <br/>
                    <form:hidden path="id" value="${orderService.id}"/>
                    <form:hidden path="orderNo" value="${orderService.orderNo}"/>
                    <form:hidden path="updateDateString" value="${orderService.updateDateString}"/>
                    <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge required" value="" maxlength="81" type="text">
                    <p>订单取消后系统将自动退款。</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form:form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>