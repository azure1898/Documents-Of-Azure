<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
// 			fillPro();//加载全部省市区数据；
			 $("#btnExport").click(function() {
			        top.$.jBox.confirm("确认要导出充值记录数据吗？", "系统提示", function(v,h,f){
			            if(v == 'ok'){
			                $("#searchForm").prop("action", "${ctx}/recharge/walletDetail/export");
			                $("#searchForm").submit(); 
			                $("#searchForm").prop("action", "${ctx}/recharge/walletDetail/");
			            }
			        }, {
			            buttonsFocus : 1
			        });
			        top.$('.jbox-body .jbox-icon').css('top', '55px');
			  });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			if ($("#endTradeDate").val() != null && $("#endTradeDate").val() != "" 
			    && $("#beginTradeDate").val() != null && $("#beginTradeDate").val() != "" 
			    && $("#endTradeDate").val() < $("#beginTradeDate").val()) {
				alertx("您输入的开始时间在结束时间之后，请重新输入！");
			} else {
			    $("#searchForm").submit();
			}
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recharge/walletDetail/list?villageInfoId=${villageInfoId}">充值记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="walletDetail" action="${ctx}/recharge/walletDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<input type="text" class="hide" id="hidProId"  value=""> -->
<!-- 		<input type="text" class="hide" id="hidCityId" value=""> -->
<!-- 		<input type="text" class="hide" id="hidAreaId" value=""> -->
<%-- 	<input type="text" class="hide" id="hidVillageId" value="${villageInfoId}"> --%>
			<input type="hidden" name="villageInfoId" value="${villageInfoId}">
		<ul class="ul-form">
			<li>
				<input id="beginTradeDate" name="beginTradeDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${walletDetail.beginTradeDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="endTradeDate" name="endTradeDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${walletDetail.endTradeDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
<!-- 			<li class="btns"> -->
<!-- 				<select id="addrpro" name="" style="width: 120px" onchange="changeCity()"> -->
<!-- 					<option value="">全部省份</option> -->
<!-- 				</select> -->
<!-- 			</li> -->
<!-- 			<li class="btns"> -->
<!-- 				<select id="addrcity" name="" style="width: 120px" onchange="changeVillage()"> -->
<!-- 					<option value="">全部城市</option> -->
<!-- 				</select> -->
<!-- 			</li> -->
<!-- 			<li class="btns" style="display:none;"> -->
<!-- 				<select id="addrarea" name="" style="width: 120px;display:none;"> -->
<!-- 					<option value="">全部区域</option> -->
<!-- 				</select> -->
<!-- 			</li> -->
<!-- 			<li class="btns"> -->
<!-- 				<select id="addrVillage" name="villageInfoId" style="width: 120px"> -->
<!-- 					<option value="">全部楼盘</option> -->
<!-- 				</select> -->
<!-- 			</li> -->
			<li class="btns">
				<form:select path="tradeType" class="input-medium">
					<form:option value="" label="交易方式"/>
					<form:options items="${tradeTypeMap}" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:input path="account.phoneNum" placeholder="请输入充值手机号" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>		
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="recharge:walletDetail:view">
         	<a class="btn btn-primary" id="btnExport" href="#" ><i class="icon-eye-open icon-custom"></i>导出明细</a>
         </shiro:hasPermission>
         <span style="float:right;"><font id="rechargeBalance" color="red">充值余额：${rechargeBalance}元</font></span>
         <span style="float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
         <span style="float:right;"><font id="orderCancel" color="red">订单取消：${orderCancel}元</font></span>
         <span style="float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
         <span style="float:right;"><font id="orderConsume" color="red">订单消费：${orderConsume}元</font></span>
         <span style="float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
         <span style="float:right;"><font id="rechargeGift" color="red">充值赠送：${rechargeGift}元</font></span>
         <span style="float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
         <span style="float:right;"><font id="accountRecharge" color="red">账号充值：${accountRecharge}元</font></span>
      </li>
  	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>交易方式</th>
				<th>交易时间</th>
				<th>金额</th>
				<th>终端来源</th>
				<th>手机唯一编码</th>
				<th>支付方式</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="walletDetail" varStatus="status">
			<tr>
				<td>
					${status.count}
				</td>
				<td>
					${walletDetail.account.phoneNum}
				</td>
				<td>
					<c:choose>
				    	<c:when test="${fns:getDictValue('充值', 'trade_type', '')==walletDetail.tradeType}">
				    		账号充值
						</c:when>
						<c:when test="${fns:getDictValue('充值赠送', 'trade_type', '')==walletDetail.tradeType}">
				    		${fns:getDictLabel(walletDetail.tradeType, 'trade_type', '')}
						</c:when>
						<c:when test="${fns:getDictValue('钱包支付', 'trade_type', '')==walletDetail.tradeType}">
				    		订单消费
						</c:when>
						<c:when test="${fns:getDictValue('退款', 'trade_type', '')==walletDetail.tradeType}">
				    		订单取消
						</c:when>
						<c:otherwise>
							其他
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${walletDetail.tradeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
				    	<c:when test="${fns:getDictValue('钱包支付', 'trade_type', '')==walletDetail.tradeType}">
				    		-
						</c:when>
						<c:otherwise>
							+
						</c:otherwise>
					</c:choose>
					${fns:doubleFormat(walletDetail.money)}
				</td>
				<td>
					${fns:getDictLabel(walletDetail.terminalSource, 'terminal_source', '')}
				</td>
				<td>
					${walletDetail.mobileUniqueCode}
				</td>
				<td>
					${fns:getDictLabel(walletDetail.payType, 'pay_type', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>