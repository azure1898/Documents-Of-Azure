<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单-商品类管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
    $(document).ready(function() {
        //$("#name").focus();
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
                $("#searchForm")[0].action = "${ctx}/order/orderGoods/";
                return true;
            // 导出按钮按下的时候
            } else if (type=='1') {
                $("#searchForm")[0].action = "${ctx}/order/orderGoods/export";
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
        <li><p style="font-weight:bold;font-size:18px;">商家概况</p></li>
        <li style="width: 95%;padding-left: 5%;text-align: center;">
            <div style="font-weight:bold;font-size:16px;float: left;width: 33%;">产品统计</div>
            <div style="font-weight:bold;font-size:16px;float: left;width: 33%;">本周订单</div>
            <div style="font-weight:bold;font-size:16px;float: left;width: 33%;">本周收入</div>
            <shiro:hasPermission name="goods:goodsInfo:view">
            <div style="float: left;width: 33%;">商品：${goodsCount}（库存不足：${goodsCountStock}）</div>
            <div style="float: left;width: 33%;">商品订单：${goodsOrderCount}</div>
            <div style="float: left;width: 33%;">商品订单：￥${goodsMoney}</div>
            </shiro:hasPermission>
            <shiro:hasPermission name="service:serviceInfo:view">
            <div style="float: left;width: 33%;">服务：${serviceCount}（库存不足：${serviceCountStock}）</div>
            <div style="float: left;width: 33%;">服务订单：${serviceOrderCount}</div>
            <div style="float: left;width: 33%;">服务订单：￥${serviceMoney}</div>
            </shiro:hasPermission>
            <shiro:hasPermission name="lesson:lessonInfo:view">
            <div style="float: left;width: 33%;">课程培训：${lessonCount}（约满：${lessonCountStock}）</div>
            <div style="float: left;width: 33%;">课程培训：${lessonOrderCount}</div>
            <div style="float: left;width: 33%;">课程培训：￥${lessonMoney}</div>
            </shiro:hasPermission>
            <shiro:hasPermission name="field:fieldInfo:view">
            <div style="float: left;width: 33%;">场地预约：${fieldCount}（约满：${fieldCountStock}）</div>
            <div style="float: left;width: 33%;">场地预约：${fieldOrderCount}</div>
            <div style="float: left;width: 33%;">场地预约：￥${fieldMoney}</div>
            </shiro:hasPermission>
        </li>
    </ul>
    <ul class="nav nav-tabs">
        <li><p style="font-weight:bold;font-size:18px;">待办事宜</p></li>
    </ul>
    <sys:message content="${message}"/>

<%--商品订单 开始--%>
<shiro:hasPermission name="goods:goodsInfo:view">

    <ul class="nav nav-tabs" style="margin-left: 5%;width: 94%;text-align: center;">
        <li><p style="font-weight:bold;font-size:14px;">商品订单</p></li>
        <li style="width: 100%;">
            <div style="float: left;width: 22%;">待付款商品订单：<a href="">${goodsSum1}</a></div>
            <div style="float: left;width: 22%;">待受理商品订单：<a href="">${goodsSum2}</a></div>
            <div style="float: left;width: 22%;">待配送商品订单：<a href="">${goodsSum3}</a></div>
            <div style="float: left;width: 22%;">待完成商品订单：<a href="">${goodsSum4}</a></div>
        </li>
    </ul>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-left: 5%;width: 94%;">
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>地址</th>
                <th>金额</th>
                <th>配送方式</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>时间</th>
                <shiro:hasPermission name="order:orderGoods:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageGoods.list}" var="orderGoods" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderGoods/form?id=${orderGoods.id}">
                    ${orderGoods.orderNo}
                </a></td>
                <td>
                    ${orderGoods.accountName}
                </td>
                <td>
                    ${orderGoods.address}
                </td>
                <td>
                    ${orderGoods.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderGoods.addressType, 'address_goods_type', "")}
                </td>
                <td <c:if test="${orderGoods.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderGoods.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderGoods.orderState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderGoods.orderState, 'order_goods_state', "")}
                </td>
                <td>
                    <c:if test="${orderGoods.createDate !=null && orderGoods.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderGoods.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderGoods.payTime !=null && orderGoods.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderGoods.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <shiro:hasPermission name="order:orderGoods:edit"><td>
                    <a id="btuElemView" class="btn btn-primary" href="${ctx}/order/orderGoods/form?id=${orderGoods.id}"><i class="icon-align-justify icon-custom"></i>查看</a>
                    <br/>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
                        <a id="btuElemAccept" class="btn btn-primary" href="${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}"><i class="icon-ok icon-custom"></i>受理</a>
                        <br/>
                    </c:if>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
                        <a id="btuElemComplete" class="btn btn-primary" href="${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}"><i class="icon-ok-circle icon-custom"></i>完成</a>
                        <br/>
                    </c:if>
                    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
                        <a id="btuElemCancel" class="btn btn-primary" data-toggle="modal" data-target="#myModal${status.index}"><i class="icon-remove icon-custom"></i>取消</a><br/>
                    </c:if>
    <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderGoods" action="${ctx}/order/orderGoods/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
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
                    <form:hidden path="id" value="${orderGoods.id}"/>
                    <form:hidden path="orderNo" value="${orderGoods.orderNo}"/>
                    <form:hidden path="updateDateString" value="${orderGoods.updateDateString}"/>
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
                </td></shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<div class="pagination" style="margin-left: 5%;width: 94%;">${page}</div>--%>
<c:if test="${businessInfo.stockWarn=='1'}">
    <ul class="nav nav-tabs" style="margin-left: 5%;width: 94%;text-align: center;">
        <li><p style="font-weight:bold;font-size:14px;">库存紧张商品</p></li>
    </ul>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:94%;margin-left: 5%;">
        <thead>
        <tr>
            <th>名称</th>
            <th>分类</th>
            <th>价格</th>
            <th>总库存</th>
            <th>图片</th>
            <th>推荐</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageGoodsStock.list}" var="goodsInfo">
            <tr>
                <td>
                        ${goodsInfo.name}
                </td>
                <td>
                    <c:if test="${goodsInfo.sortInfoName == null || goodsInfo.sortInfoName == ''}">
                        -
                    </c:if>
                        ${goodsInfo.sortInfoName}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${goodsInfo.benefitPrice != null && goodsInfo.benefitPrice!= ''}">
                            <s><font color="#CCCCCC">${fns:doubleFormat(goodsInfo.basePrice)}</font></s><br/>
                            ${fns:doubleFormat(goodsInfo.benefitPrice)}
                        </c:when>
                        <c:otherwise>
                            ${fns:doubleFormat(goodsInfo.basePrice)}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${goodsInfo.stock != null && goodsInfo.stock!= ''}">
                            ${goodsInfo.stock}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach items="${goodsInfo.imageUrls}" var="imgUrl">
                        <img src="${imgUrl}"/>
                    </c:forEach>
                </td>
                <td>
                    <c:if test="${goodsInfo.recommend == null || goodsInfo.recommend == '0'}">
                        否
                    </c:if>
                    <c:if test="${goodsInfo.recommend != null && goodsInfo.recommend == '1'}">
                        是
                    </c:if>
                </td>
                <td>
                    <c:if test="${goodsInfo.state == null || goodsInfo.state == '0'}">
                        下架
                    </c:if>
                    <c:if test="${goodsInfo.state != null && goodsInfo.state == '1'}">
                        上架
                    </c:if>
                </td>
                <shiro:hasPermission name="goods:goodsInfo:edit">
                <td>
                    <a href="javascript:void(0);" onclick="window.parent.addTabByMy('商品编辑','/goods/goodsInfo/form?id=${goodsInfo.id}&mode=1');">编辑</a>
                </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</shiro:hasPermission>

<%--商品订单 结束--%>


<%--服务订单 开始--%>
<shiro:hasPermission name="service:serviceInfo:view">
<ul class="nav nav-tabs" style="margin-left: 5%;width: 94%;text-align: center;">
    <li><p style="font-weight:bold;font-size:14px;">服务订单</p></li>
    <li style="width: 100%;">
        <div style="float: left;width: 22%;">待付款服务订单：<a href="">${serviceSum1}</a></div>
        <div style="float: left;width: 22%;">待受理服务订单：<a href="">${serviceSum2}</a></div>
        <div style="float: left;width: 22%;">待完成服务订单：<a href="">${serviceSum3}</a></div>
    </li>
</ul>
<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-left: 5%;width: 94%;">
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
    <c:forEach items="${pageService.list}" var="orderService" varStatus="status">
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
                        <a id="btuElemAccept" class="btn btn-primary" href="${ctx}/order/orderService/accept?orderNo=${orderService.orderNo}&updateDate=${orderService.updateDateString}"><i class="icon-ok icon-custom"></i>受理</a>
                        <br/>
                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:orderService:complete">
                    <c:if test="${orderService.payState == '1' && orderService.orderState ==  '1'}">
                        <a id="btuElemComplete" class="btn btn-primary" href="${ctx}/order/orderService/complete?orderNo=${orderService.orderNo}&updateDate=${orderService.updateDateString}"><i class="icon-ok-circle icon-custom"></i>完成</a>
                        <br/>
                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:orderService:cancel">
                    <c:if test="${orderService.orderState !=  '2' && orderService.orderState !=  '3'}">
                        <a id="btuElemCancel" class="btn btn-primary" data-toggle="modal" data-target="#myModal${status.index}"><i class="icon-remove icon-custom"></i>取消</a><br/>
                    </c:if>
                </shiro:hasPermission>
                <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
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

<c:if test="${businessInfo.stockWarn=='1'}">
    <ul class="nav nav-tabs" style="margin-left: 5%;width: 94%;text-align: center;">
        <li><p style="font-weight:bold;font-size:14px;">库存紧张服务</p></li>
    </ul>
    <table id="contentTable" class="table table-bordered table-condensed" style="margin-left: 5%;width: 94%;">
        <thead>
        <tr>
            <th style="width : 5%">编号
            </th>
            <th style="width : 20%">服务名称</th>
            <th style="width : 10%">分类</th>
            <th style="width : 10%">价格</th>
            <th style="width : 5%">库存</th>
            <th style="width : 20%">图片</th>
            <th style="width : 5%">推荐</th>
            <th style="width : 10%">状态</th>
            <shiro:hasPermission name="service:serviceInfo:edit"><th style="width : 10%">操作</th></shiro:hasPermission>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageServiceStock.list}" var="serviceInfo">
            <tr>
                <td>
                        ${serviceInfo.serialNumbers}
                </td>
                <td>
                        ${serviceInfo.name}
                </td>
                <td>
                    <c:if test="${serviceInfo.sortInfoName == null || serviceInfo.sortInfoName == ''}">
                        -
                    </c:if>
                        ${serviceInfo.sortInfoName}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${serviceInfo.benefitPrice != null && serviceInfo.benefitPrice!= ''}">
                            <s><font color="#CCCCCC">${fns:doubleFormat(serviceInfo.basePrice)}</font></s><br/>
                            ${fns:doubleFormat(serviceInfo.benefitPrice)}
                        </c:when>
                        <c:otherwise>
                            ${serviceInfo.basePrice}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${serviceInfo.stock != null && serviceInfo.stock!= ''}">
                            ${serviceInfo.stock}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach items="${serviceInfo.imageUrls}" var="imgUrl">
                        <img src="${imgUrl}"/>
                    </c:forEach>
                </td>
                <td>
                        ${fns:getDictLabel(serviceInfo.recommend, 'Quota', '否')}
                </td>
                <td>
                        ${fns:getDictLabel(serviceInfo.state, 'service_state', '下架')}
                </td>
                <shiro:hasPermission name="service:serviceInfo:edit">
                    <td>
                    <a href="javascript:void(0);" onclick="window.parent.addTabByMy('服务编辑','/service/serviceInfo/form?id=${serviceInfo.id}');">编辑</a>
                    </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</shiro:hasPermission>
<%--服务订单 结束--%>


</body>
</html>