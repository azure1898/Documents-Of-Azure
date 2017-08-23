<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>预约场地管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
        	top.$.jBox.tip.mess = null;
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    top.$.jBox.tip.mess = null;
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
        /*根据画面选择的取消原因传递给后台，并控制取消原因输入框显示非现实*/
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
            if (type == '0') {
                $("#searchForm")[0].action = "${ctx}/order/orderField/";
                return true;
                // 导出按钮按下的时候
            } else if (type == '1') {
                $("#searchForm")[0].action = "${ctx}/order/orderField/export";
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
    <form:form id="searchForm" modelAttribute="orderField" action="${ctx}/order/orderField/" method="post" class="breadcrumb form-search" style="width:95.5%">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li><label>时间：</label>
                <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                    value="<fmt:formatDate value="${orderField.beginCreateDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
                <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                    value="<fmt:formatDate value="${orderField.endCreateDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({minDate:beginCreateDate.value,dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </li>
            <li><label>订单号：</label>
                <form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
            </li>
            <li>
                <form:select path="fieldInfoId" class="input-medium" style="width:173px;">
                    <form:option value="" label="全部场地"/>
                    <form:options items="${fieldInfoOptionList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
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
                    <form:options items="${fns:getDictList('order_lesson_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <form:hidden path="searchFlg" value="1"/>
            </li>
            <li class="btns"><input id="btnSubmit" class="commonbtn" style="width:60px;" type="submit"   onclick="changeAction('0')" value="查询"/></li>
            <li class="btns"><input id="btnExport" class="commonbtn" type="submit"  style="width:60px;"  onclick="changeAction('1')" value="导出"/></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98.3%">
        <thead>
            <tr>
                <th style="width:13%;">订单号</th>
                <th style="width:11.5%;">姓名</th>
                <th style="width:13%;">电话</th>
                <th style="width:13%;">预约场地</th>
                <th style="width:6%;">金额</th>
                <th style="width:6.5%;">终端类型</th>
                <th style="width:6.5%;">支付状态</th>
                <th style="width:6.5%;">订单状态</th>
                <th style="width:15%;">时间</th>
                <th style="width:9%;">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="orderField" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderField/form?id=${orderField.id}">
                    ${orderField.orderNo}
                </a></td>
                <td>
                    ${orderField.accountName}
                </td>
                <td>
                    ${orderField.accountPhoneNumber}
                </td>
                <td>
                    ${orderField.name}
                    <c:if test="${orderField.orderFieldList != null && fn:length(orderField.orderFieldList) gt 0}">
                        <br/>
                        <fmt:formatDate value="${orderField.orderFieldList[0].appointmentTime}" pattern="yyyy年M月d日"/>
                        <c:forEach items="${orderField.orderFieldList}" var="orderFieldList" varStatus="status1">
                            <br/>
                            <fmt:formatDate value="${orderFieldList.startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${orderFieldList.endTime}" pattern="HH:mm"/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    ${orderField.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderField.type, 'order_type', "")}
                </td>
                <td <c:if test="${orderField.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderField.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderField.orderState == 0}">style="color:red"</c:if>>
                    <c:choose>
                        <c:when test="${orderField.outTimeState && orderField.orderState == '1'}">
                            <span>已完成</span>
                        </c:when>
                        <c:otherwise>
                            ${fns:getDictLabel(orderField.orderState, 'order_lesson_state', "")}
                        </c:otherwise>
                    </c:choose>
                    
                </td>
                <td>
                    <c:if test="${orderField.createDate !=null && orderField.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderField.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderField.payTime !=null && orderField.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderField.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <td>
                    <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderField/form?id=${orderField.id}'">
                    <div style="margin:0 auto;height:3px;"></div>
                    <c:choose>
                        <c:when test="${orderField.outTimeState && orderField.orderState == '1'}">
                        </c:when>
                        <c:when test="${orderField.orderState == '2'}">
                        </c:when>
                        <c:otherwise>
                            <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;" data-toggle="modal" data-target="#myModal${status.index}">
                        </c:otherwise>
                    </c:choose>
    <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="display: none;">
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderField" action="${ctx}/order/orderField/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
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
                    <form:hidden path="id" value="${orderField.id}"/>
                    <form:hidden path="orderNo" value="${orderField.orderNo}"/>
                    <form:hidden path="updateDateString" value="${orderField.updateDateString}"/>
                    <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge required" value="" maxlength="81" type="text">
                    <p>订单取消后系统将自动退款。</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="commonbtn" style="width:60px;">确定</button>
                    <button type="button" class="commonbtn" style="width:60px;" data-dismiss="modal">取消</button>
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