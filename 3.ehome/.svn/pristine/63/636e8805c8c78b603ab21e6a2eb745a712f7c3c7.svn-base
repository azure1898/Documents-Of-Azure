<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>订单-团购类管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function home(){
		if("${tag}"=="1"){
			window.location.href="${ctx}/order/orderGroupPurc/";
		}
		history.go(-1);
	}
</script>
</head>
<body>
	<div style="text-align: right; margin: 0px 50px -10px 0px;">
			<input id="btnBack" class="commonbtn" style="width: 150px;" type="button"  onclick="home()" value="返回"/>
	</div>
	<div class="form-actions">
		<p style="font-weight: bold;">团购订单</p>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>订单号</th>
					<th>团购名称</th>
					<th>团购价</th>
					<th>数量</th>
					<th>实付金额</th>
					<th>订单状态</th>
					<th>手机号</th>
					<th>下单时间</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${orderGroupPurc.orderNo}</td>
					<td>${orderGroupPurc.name}</td>
					<td>${orderGroupPurc.groupPurcPrice}</td>
					<td>${orderGroupPurc.payNumber}</td>
					<td>${orderGroupPurc.payMoney}</td>
					<td
						<c:if test="${orderGroupPurc.orderState == 0}">style="color:red"</c:if>>
						${fns:getDictLabel(orderGroupPurc.orderState, 'order_group_purc_state', "")}
					</td>
					<td>${orderGroupPurc.accountPhoneNumber}</td>
					<td><c:if
							test="${orderGroupPurc.createDate !=null && orderGroupPurc.createDate !=''}">
							<span>下单：<fmt:formatDate
									value="${orderGroupPurc.createDate}" pattern="yyyy-MM-dd HH:mm" /></span>
							<br />
						</c:if> <c:if
							test="${orderGroupPurc.payTime !=null && orderGroupPurc.payTime !=''}">
							<span>支付：<fmt:formatDate value="${orderGroupPurc.payTime}"
									pattern="yyyy-MM-dd HH:mm" /></span>
						</c:if></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="form-actions">
		<p style="font-weight: bold;">团购券</p>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>团购券</th>
					<th>状态</th>
					<th>处理时间</th>
					<th>备注</th>
					<th>操作账户</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${OrderGroupPurc.orderGroupPurcList}"
					var="orderGroupPurcList" varStatus="status">
					<tr>
						<td>${orderGroupPurcList.groupPurcNumber }</td>
						<td
							<c:if test="${orderGroupPurcList.groupPurcState == 0}">style="color:red"</c:if>>
							${fns:getDictLabel(orderGroupPurcList.groupPurcState, 'order_group_purc_state', "")}
						</td>
						<td><c:if
								test="${orderGroupPurcList.updateDate !=null && orderGroupPurcList.updateDate !=''}">
								<fmt:formatDate value="${orderGroupPurcList.updateDate}"
									pattern="yyyy-MM-dd HH:mm" />
							</c:if></td>
						<td>${orderGroupPurcList.remarks }</td>
						<td> <c:choose>
                        <c:when test="${not empty orderTrack.businessInfoId}">
                            <span>商家账号</span>
                        </c:when>
                        <c:when test="${'system' == orderTrack.createBy}">
                            <span>系统</span>
                        </c:when>
                        <c:otherwise>
                            <span>--</span>
                        </c:otherwise>
                    </c:choose></td>
						<td><c:if test="${orderGroupPurcList.groupPurcState == 0}">
							 <input id="btuElemComplete" class="commonsmallbtn" type="submit" style="width: 100px;height: 30px;font-size: 14px;"  onclick="showModal('${orderGroupPurcList.id }')" value="验券消费"/>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="form-actions">
		<p style="font-weight: bold;">买家信息</p>
		<table style="border: 0; ">
			<tr>
				<td style="width: 10%">
					<p style="padding-left: 20px;">联系人姓名：</p>
				</td>
				<td style="width: 40%">
					<p>${orderGroupPurc.accountName}</p>
				</td>
				<td style="width: 10%">
					<p style="padding-left: 20px;">联系电话：</p>
				</td>
				<td style="width: 40%">
					<p>${orderGroupPurc.accountPhoneNumber}</p>
				</td>
			</tr>
			<tr>
				<td>
					<p style="padding-left: 20px;">配送方式：</p>
				</td>
				<td>
					<p>到店自提</p>
				</td>
				<td>
					<p style="padding-left: 20px;">配送地址：</p>
				</td>
				<td>
					<p>--</p>
				</td>
			</tr>
		</table>
	</div>
	
	<div class="form-actions">
		<p style="font-weight: bold;">团购详情</p>
		<div style="padding: 0px 0px 0px 80px;">
			${groupPurc.groupPurcDetail }
		</div>
	</div>
	
	<div class="form-actions">
		<p style="font-weight: bold;">
			团购券有效期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<fmt:formatDate value="${groupPurc.validityStartTime}"
				pattern="yyyy-MM-dd HH:mm:00" />
			点&nbsp;&nbsp;至&nbsp;&nbsp;
			<fmt:formatDate value="${groupPurc.validityEndTime}"
				pattern="yyyy-MM-dd HH:mm:00" />
			点
		</p>
	</div>
	<div class="form-actions">
		<p style="font-weight: bold;">支付信息</p>
		<table style="border: 0; ">
			<tr>
				<td style="width: 25%">
					<p style="padding-left: 20px;">支付方式：${fns:getDictLabel(orderGroupPurc.payType, 'pay_type', "")}</p>
				</td>
				<td style="width: 25%">
					<p>支付机构：${fns:getDictLabel(orderGroupPurc.payOrg, 'pay_org', "")}</p>
				</td>
				<td style="width: 25%">
					<p>
						支付时间：
						<fmt:formatDate value="${orderGroupPurc.payTime}" pattern="yyyy-MM-dd HH:mm" />
					</p>
				</td>
			</tr>
		</table>
	</div>
	
	<div style="text-align: right; margin: 20px 50px 20px 0px;">
		<input id="btnBack" class="commonbtn" style="width: 150px;" type="button"  onclick="home()" value="返回"/>
	</div>
	
	
<!-- Modal -->
<div class="modal fade modal-md" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="width: 800px;">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content" >
     	<div class="modal-body" style="max-height: 600px;height:600px;overflow:hidden;">
     		<!-- 遮挡页头 -->
     		<div style="width: 800px;height: 24px;position:absolute;z-index:5; background-color: #fff;">
     			<h4>验券消费</h4> 
     		</div>
     		<iframe id="mbi" style="border: 0px;width: 100%;height: 100%;" src="${ctx}/coupon/testCoupon/"></iframe>
     	 </div>
   	 	<div class="modal-footer">
       	 <button type="button" class="btn btn-default" data-dismiss="modal">完成</button>
      </div>
    </div>
  </div>
</div>
<script>
	//弹出窗口 加载页面 
	function showModal(id){
		$("#mbi").attr("src","${ctx}/coupon/testCoupon/?id="+id);
		$('#myModal').modal('toggle');
	}
	//一波操作后要刷新的页面
	$('#myModal').on('hidden.bs.modal', function (e) {
		window.location.href = '${ctx}/order/orderGroupPurc/form?id=${orderGroupPurc.id}'
	})
</script>
	
</body>
</html>