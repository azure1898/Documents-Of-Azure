<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单-商品类管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
            	rules: {
            		cancelRemarks : "required",
            		cancelRemarksSelect : "required"
            	},
            	messages: {
            		cancelRemarks : "请填写取消原因",
            		cancelRemarksSelect : "请填写取消原因"
            	},
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
        function setRemarks(remarks) {
            $("#cancelRemarks")[0].value = remarks;
            if ("其他原因" == remarks) {
                $("#cancelRemarks")[0].value = "";
                $("#cancelRemarks").css('display','');
            } else {
            	$("#cancelRemarks").css('display','none');
            }
        }
    </script>
</head>
<body>
    <div class="form-actions" style="text-align:right;width:95.5%">
    <div style="margin-left:15px;">
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '1'}">
        <input id="btnElemDispatching" class="btn btn-success" type="button" value="去配送" onclick="window.location.href='${ctx}/order/orderGoods/dispatching?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
        <input id="btnElemAccept" class="btn btn-success" type="button" value="受理" onclick="window.location.href='${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
        <input id="btnElemComplete" class="btn btn-success" type="button" value="完成" onclick="window.location.href='${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
        <input id="btnElemCancel" class="btn btn-success" type="button" data-toggle="modal" data-target="#myModal" value="取消" />
    </c:if>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm" modelAttribute="orderGoods" action="${ctx}/order/orderGoods/cancel" method="post">
                <div class="modal-body">
                    <select id="cancelRemarksSelect" name="cancelRemarksSelect" onchange="setRemarks(this.value)" style="width:200px;">
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
                    <form:input path="cancelRemarks" htmlEscape="false" maxlength="81" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge"/>
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
    <input id="btnBack" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
    </div>
    </div>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98.3%">
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
            <tr>
                <td>
                    ${orderGoods.orderNo}
                </td>
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
                        下单：<fmt:formatDate value="${orderGoods.createDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
                    </c:if>
                    <c:if test="${orderGoods.payTime !=null && orderGoods.payTime !=''}">
                        支付：<fmt:formatDate value="${orderGoods.payTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </c:if>
                </td>
                <shiro:hasPermission name="order:orderGoods:edit"><td>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
                        <a id="btuElemAccept" class="btn btn-primary" href="${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}"><i class="icon-ok icon-custom"></i>受理</a>
                        <br/>
                    </c:if>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
                        <a id="btuElemComplete" class="btn btn-primary" href="${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}"><i class="icon-ok-circle icon-custom"></i>完成</a>
                        <br/>
                    </c:if>
                    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
                        <a id="btuElemCancel" class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="icon-remove icon-custom"></i>取消</a><br/>
                    </c:if>
                </td></shiro:hasPermission>
            </tr>
        </tbody>
    </table>
    <p style="font-weight:bold;">买家信息</p>
    <table style="border:0;width:98.3%;">
        <tr>
            <td style="width:10%">
                <p style="padding-left: 20px;">联系人姓名：</p>
            </td>
            <td style="width:40%">
                <p>${orderGoods.accountName}</p>
            </td>
            <td style="width:10%">
                <p style="padding-left: 20px;">联系电话：</p>
            </td>
            <td style="width:40%">
                <p>${orderGoods.accountPhoneNumber}</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="padding-left: 20px;">配送方式：</p>
            </td>
            <td>
                <p>${fns:getDictLabel(orderGoods.addressType, 'address_goods_type', "")}</p>
            </td>
            <td>
                <p style="padding-left: 20px;">配送地址：</p>
            </td>
            <td>
                <p>${orderGoods.address}</p>
            </td>
        </tr>
    </table>
    <p style="font-weight:bold;">商品清单</p>
    <table  class="table table-striped table-bordered table-condensed" style="width:98.3%">
        <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>规格</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
                <th>图片</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderGoods.orderGoodsList}" var="orderGoodsTemp">
            <tr>
                <td>
                    ${orderGoodsTemp.serialNumbers}
                </td>
                <td>
                    ${orderGoodsTemp.name}
                </td>
                <td>
                    ${orderGoodsTemp.skuContent}
                </td>
                <td>
                <c:choose> 
                    <c:when test="${orderGoodsTemp.benefitPrice != null && orderGoodsTemp.benefitPrice!= ''}">
                    <font color="#CCCCCC">${orderGoodsTemp.basePrice}</font><br/>
                    ${orderGoodsTemp.benefitPrice}
                    </c:when>
                    <c:otherwise>
                    ${orderGoodsTemp.basePrice}
                    </c:otherwise>
                </c:choose>
                </td>
                <td>
                    ${orderGoodsTemp.goodsSum}
                </td>
                <td>
                    ${orderGoodsTemp.paySumMoney}
                </td>
                <td>
                    <img src="${orderGoodsTemp.imageUrl}"/>
                </td>
        </c:forEach>
            <tr>
                <td style="border-right: 0px;">
                    <p>合计：<p>
                </td>
                <td colspan="6" style="text-align:right;border-left: 0px;">
                    ${orderGoods.sumMoney}元
                </td>
            </tr>
            <c:if test="${not empty orderGoods.couponMoney}">
            <tr>
                <td colspan="7" style="text-align:right">
                    <p>平台优惠 - ${orderGoods.couponMoney}元<p>
                </td>
            </tr>
            </c:if>
            <c:if test="${not empty orderGoods.benefitMoney}">
            <tr>
                <td colspan="7" style="text-align:right">
                    <p>商家优惠 - ${orderGoods.benefitMoney}元<p>
                </td>
            </tr>
            </c:if>
            <c:if test="${not empty orderGoods.addressMoney}">
            <tr>
                <td colspan="7" style="text-align:right">
                    <p>配送费用：${orderGoods.addressMoney}元<p>
                </td>
            </tr>
            </c:if>
            <c:if test="${not empty orderGoods.addressBenefit}">
            <tr>
                <td colspan="7" style="text-align:right">
                    <p>配送费减免 - ${orderGoods.addressBenefit}元<p>
                </td>
            </tr>
            </c:if>
            <c:if test="${not empty orderGoods.payMoney}">
            <tr>
                <td colspan="7" style="text-align:right">
                    <p style="font-weight:bold;">实际支付：${orderGoods.payMoney}元</p>
                </td>
            </tr>
            </c:if>
        </tbody>
    </table>
    <p style="font-weight:bold;">支付信息</p>
    <table style="border:0;width:98.3%;">
        <tr>
            <td style="width:25%">
                <p style="padding-left: 20px;">支付方式：${fns:getDictLabel(orderGoods.payType, 'pay_type', "")}</p>
            </td>
            <td style="width:25%">
                <p>支付机构：${fns:getDictLabel(orderGoods.payOrg, 'pay_org', "")}</p>
            </td>
            <td style="width:25%">
                <p>支付时间：<fmt:formatDate value="${orderGoods.payTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            </td>
            <td style="width:25%">
                <p>支付帐号：${orderGoods.payUserName}</p>
            </td>
        </tr>
        <c:if test="${orderGoods.payState == '2' || orderGoods.payState == '3'}">
        <tr>
            <td style="width:25%">
                <p style="padding-left: 20px;">退款状态：${fns:getDictLabel(orderGoods.payState, 'pay_goods_state', "")}</p>
            </td>
            <td style="width:25%">
            </td>
            <td style="width:25%">
                <p>退款时间：<fmt:formatDate value="${refundOverTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            </td>
            <td style="width:25%">
            </td>
        </tr>
        </c:if>
    </table>
    <p style="font-weight:bold;">配送信息</p>
    <table style="border:0;width:98.3%;">
        <tr>
            <td style="width:20%">
                <p style="padding-left: 20px;">配送方式：${fns:getDictLabel(orderGoods.addressType, 'address_goods_type', "")}</p>
            </td>
            <td style="width:20%">
                <p>配送状态：${fns:getDictLabel(orderGoods.addressState, 'address_goods_state', "")}</p>
            </td>
            <td style="width:20%">
                <p>配送费用：${orderGoods.addressMoney}<c:if test="${not empty orderGoods.addressMoney}"> 元</c:if></p>
            </td>
            <td style="width:20%">
                <p>配送费减免：${orderGoods.addressBenefit}<c:if test="${not empty orderGoods.addressBenefit}"> 元</c:if></p>
            </td>
            <td style="width:20%">
                <p>送达时间：<fmt:formatDate value="${orderGoods.overTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            </td>
        </tr>
    </table>
    <p style="font-weight:bold;">订单跟踪</p>
    <table  class="table table-striped table-bordered table-condensed" style="width:98.3%">
        <thead>
            <tr>
                <th>订单状态</th>
                <th>处理时间</th>
                <th>处理信息</th>
                <th>操作账户</th>
                <th>备注</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderGoods.orderTrackList}" var="orderTrack">
            <tr>
                <td style="width:12.5%">
                    ${orderTrack.stateMsg}
                </td>
                <td style="width:25%">
                    <fmt:formatDate value="${orderTrack.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td style="width:25%">
                    ${orderTrack.handleMsg}
                </td>
                <td style="width:12.5%">
                    ${orderTrack.createName}
                </td>
                <td style="width:25%">
                    ${orderTrack.remarks}
                </td>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-actions" style="text-align:right;width:95.5%">
    <div style=" margin-left:15px;">
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '1'}">
        <input id="btnElemDispatchinDown" class="btn btn-success" type="button" value="去配送" onclick="window.location.href='${ctx}/order/orderGoods/dispatching?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
        <input id="btnElemAcceptDown" class="btn btn-success" type="button" value="受理" onclick="window.location.href='${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
        <input id="btnElemCompleteDown" class="btn btn-success" type="button" value="完成" onclick="window.location.href='${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'"/>
    </c:if>
    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
        <input id="btnElemCancelDown" class="btn btn-success" type="button" value="取消" data-toggle="modal" data-target="#myModal"/>
    </c:if>
    <input id="btnBackDown" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
    </div>
    </div>
</body>
</html>