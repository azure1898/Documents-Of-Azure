<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>物业交易管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
	    function home(){
	    	window.location.href='${ctx}/order/propertyDeal';
	    }
    </script>
</head>
<body>
    <div style="text-align: right; margin: 0px 50px 0px 0px;">
		<input id="btnBack" class="btn" type="button" value="返回"
			style="width: 200px;" onclick="home()" />
	</div>
	<div class="form-actions">
		<p style="font-weight: bold;">物业缴费订单详情</p>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" >
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>物业缴费</th>
                <th>实付金额</th>
                <th>支付状态</th>
                <th>时间</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    ${propertyDeal.orderNo}
                </td>
                <td>
                    ${propertyDeal.nickName}
                </td>
                <td>
                    ${propertyDeal.phoneNum}
                </td>
                <td>
                    ${propertyDeal.remarks}
                </td>
                 <td>
                    ${propertyDeal.payMoney}
                </td>
                       <td> ${fns:getDictLabel(propertyDeal.payState, 'Pay_State', "")}</td>
                <td>
                    <c:if test="${propertyDeal.createDate !=null && propertyDeal.createDate !=''}">
                     	 下单：<fmt:formatDate value="${propertyDeal.createDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
                    </c:if>
                    <c:if test="${propertyDeal.payTime !=null && propertyDeal.payTime !=''}">
                 		  支付：<fmt:formatDate value="${propertyDeal.payTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </c:if>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
    
    
     <div class="form-actions">
	    <p style="font-weight:bold;">支付信息</p>
	    <table style="border:0;">
	        <tr>
	            <td style="width:25%">
	                <p style="padding-left: 20px;">付款方式：${fns:getDictLabel(propertyDeal.payType, 'pay_type', "")}</p>
	            </td>
	            <td style="width:25%">
	                <p>付款时间：<fmt:formatDate value="${propertyDeal.payTime}" pattern="yyyy-MM-dd HH:mm"/></p>
	            </td>
	            <td style="width:25%">
	                <p>支付帐号：${propertyDeal.payUserName}</p>
	            </td>
	        </tr>
	    </table>
    </div>
    
    	<c:if test="${not empty propertyDeal.discountMoney}">
     <div class="form-actions">
	    <p style="font-weight:bold;">优惠信息</p>
	    <table style="border:0;">
	        <tr>
	            <td style="width:25%">
	                <p>平台优惠：${propertyDeal.discountMoney}</p>
	            </td>
	            <td style="width:25%">
	                <p>优惠券号：${propertyDeal.discountId}</p>
	            </td>
	        </tr>
	    </table>
    </div>
    </c:if>
    
    <div class="form-actions">
		<p style="font-weight: bold;">订单明细</p>
    <table  class="table table-striped table-bordered table-condensed" >
        <thead>
            <tr>
                <th>房间</th>
                <th>月份</th>
                <th>缴费项目</th>
                <th>小计</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${propertyDeal.propertyBillDetails}" var="propertyBillDetails" varStatus="status">
            <tr>
            	<c:if test="${status.count == 1}">
	                <td rowspan="${fn:length(propertyDeal.propertyBillDetails)}">
	                    ${propertyBillDetails.roomCertifyId}
	                </td>
                </c:if>
                <td>
                <fmt:formatDate value="${propertyBillDetails.createDate}" pattern="yyyy-MM"/>
                </td>
                <td>
               		 ${fns:getDictLabel(propertyBillDetails.feeType, 'Fee_Type', "")}
                </td>
                <td>
                    ${propertyBillDetails.money}
                </td>
                </tr>
        </c:forEach>
            <tr>
                <td colspan="7" style="text-align:right;border-left: 0px;">
					<c:if test="${not empty propertyDeal.orderMoney}">
						<p>合计金额 :  ${propertyDeal.orderMoney}元<p>
					</c:if>
					<c:if test="${not empty propertyDeal.discountMoney}">
						<p>平台优惠 :  ${propertyDeal.discountMoney}元<p>
					</c:if>
					<c:if test="${not empty propertyDeal.payMoney}">
						<h4 style="font-weight:bold;">实付金额：${propertyDeal.payMoney}元</h4>
					</c:if>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
   
   <div style="text-align: right; margin: 0px 50px 20px 0px;">
		<input id="btnBack" class="btn" type="button" value="返回"
			style="width: 200px;" onclick="home()" />
	</div>
	
    
</body>
</html>