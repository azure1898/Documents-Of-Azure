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
        //打开新页签
        function openedit(id){
        	window.parent.addTabByMy('商品编辑','/goods/goodsInfo/form?id='+id+'&mode=1');
        }
    </script>
<style type="text/css">
	.block{
		background-color: #FFFFFF;
		border: 1px solid #ddd;
		margin: 10px 20px 0px 20px;
		float:left;
		width: 97%;
		/* box-shadow: 0 3px 5px #ddd; */
		box-shadow: -3px 2px 2px #ddd ;
		padding: 10px 0px 0px 5px;
		 box-sizing: border-box;
	}
	.head-border{
		border-right: 1px solid #ddd;
		width: 30%;
		float:left;
		margin-left: 15px;
		margin-bottom: 15px;
	}
	.head-border:last-child{
		border-right-style: none;
	}
	.ul li span{
		display:block;
		width: 70px;
		float:left;
	}
	.ul li a{
		color: #60C1F7;
	}
	.case{
		width: 100%;
		height: 100%;
		border: 1px solid #ddd;
		margin: 0px 0px 0px 0px;
		 box-sizing: border-box;
	}
</style>
</head>
<body>
<div style="background-color: #EEF4F9;width: 100%;height: 100%;float: left;">
	<div class="block">
	<p style="font-weight:bold;font-size:20px;color: #FF5F5F;margin-left: 15px;">商家概况</p>
	<hr style="border-style:solid;"/>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s0.png" style="width: 30px;height: 20px;">   产品统计</p>
		<ul class="ul">
			<shiro:hasPermission name="goods:goodsInfo:view">
				<li><span>商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</span>${goodsCount}（库存不足：<a>${goodsbzCount}</a>）</li>
			</shiro:hasPermission>
		 	<shiro:hasPermission name="service:serviceInfo:view">
				<li><span>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</span>${serviceCount}（库存不足：<a>${servicesbzCount}</a>）</li>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="lesson:lessonInfo:view"> --%>
				<li><span>课程培训：</span>${lessonCount}（约满：<a>${lessonbzCount}</a>）</li>
			<%-- </shiro:hasPermission> --%>
			<shiro:hasPermission name="field:fieldInfo:view">
				<li><span>场地预约：</span>${fieldCount}（约满：<a>${lessonbzCount}</a>）</li>
			</shiro:hasPermission>
		</ul>
	</div>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s1.png" style="width: 30px;height: 20px;">本周订单</p>
		<ul class="ul">
			<shiro:hasPermission name="goods:goodsInfo:view">
				<li><span>商品订单：</span><a>${orderGoodsCount}</a></li>
			</shiro:hasPermission>
		 	<shiro:hasPermission name="service:serviceInfo:view">
				<li><span>服务订单：</span><a>${orderServiceCount}</a></li>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="lesson:lessonInfo:view"> --%>
				<li><span>课程培训：</span><a>${orderLessonCount}</a></li>
			<%-- </shiro:hasPermission> --%>
			<shiro:hasPermission name="field:fieldInfo:view">
				<li><span>场地预约：</span><a>${orderFieldCount}</a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s2.png" style="width: 30px;height: 20px;">本周收入</p>
		<ul class="ul">
			<shiro:hasPermission name="goods:goodsInfo:view">
				<li><span>商品订单：</span>￥${goodsInclu}</li>
			</shiro:hasPermission>
		 	<shiro:hasPermission name="service:serviceInfo:view">
				<li><span>服务订单：</span>￥${serviceInclu}</li>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="lesson:lessonInfo:view"> --%>
				<li><span>课程培训：</span>￥${lessonInclu}</li>
			<%-- </shiro:hasPermission> --%>
			<shiro:hasPermission name="field:fieldInfo:view">
				<li><span>场地预约：</span>￥${fieldInclu}</li>
			</shiro:hasPermission>
		</ul>
	</div>
	</div>
	
	<div class="block">
	<p style="font-weight:bold;font-size:20px;color: #FF5F5F;margin-left: 15px;">待办事宜</p>
	
	<sys:message content="${message}"/>
	<div class="case">
		<%--商品订单 开始--%>
			<shiro:hasPermission name="goods:goodsInfo:view">
			    <ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;margin-top: 10px;">
			        <li><p style="font-weight:bold;font-size:14px;">商品订单</p></li>
			        <li style="width: 100%;">
			            <div style="float: left;width: 120px;">待付款商品订单：<a href="#" style="color: #60C1F7;">${pendingPayGoods}</a></div>
			            <div style="float: left;width: 120px;">待受理商品订单：<a href="#" style="color: #60C1F7;">${pendingHandleGoods}</a></div>
			            <div style="float: left;width: 120px;">待配送商品订单：<a href="#" style="color: #60C1F7;">${pendingDisGoods}</a></div>
			            <div style="float: left;width: 120px;">待完成商品订单：<a href="#" style="color: #60C1F7;">${pendingSuccessGoods}</a></div>
			        </li>
			    </ul>
			    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 99%;">
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
			                <shiro:hasPermission name="order:orderGoods:edit"><th width="100px;">操作</th></shiro:hasPermission>
			            </tr>
			        </thead>
			        <tbody>
			        <c:forEach items="newOrderGoodsList" var="orderGoods" varStatus="status">
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
			                    <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;margin-top: 2px;" onclick="window.location.href='${ctx}/order/orderGoods/form?id=${orderGoods.id}'">
			                    <br/>
			                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
			                        <input id="btuElemAccept" type="button" class="commonsmallbtn" value="受理" style="width:40px;margin-top: 2px;" onclick="window.location.href='${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'">
			                        <br/>
			                    </c:if>
			                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
			                          <input id="btuElemComplete" type="button" class="commonsmallbtn" value="完成" style="width:40px;margin-top: 2px;" onclick="window.location.href='${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}'">
			                        <br/>
			                    </c:if>
			                    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
			                   	 <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;margin-top: 2px;" data-toggle="modal" data-target="#myModal${status.index}">
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
			    <ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;">
			        <li><p style="font-weight:bold;font-size:14px;">库存紧张</p></li>
			    </ul>
			    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:99%;">
			        <thead>
			        <tr>
			            <th>名称</th>
			            <th>分类</th>
			            <th>价格</th>
			            <th>总库存</th>
			            <th>图片</th>
			            <th>推荐</th>
			            <th>状态</th>
			            <th width="100px;">操作</th>
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
			               		 <input type="button" class="commonsmallbtn" value="编辑" style="width:40px;margin-top: 2px;" onclick="openedit('${goodsInfo.id}')">
			                </td>
			                </shiro:hasPermission>
			            </tr>
			        </c:forEach>
			        </tbody>
			    </table>
			</c:if>
			
			</shiro:hasPermission>
			
			<%--商品订单 结束--%>
	
	</div>
	</div>
</div>
</body>
</html>