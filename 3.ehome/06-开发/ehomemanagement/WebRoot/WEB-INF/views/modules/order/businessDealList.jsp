<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>交易管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
    //    fillPro();//加载全部省市区数据；
    });
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
    
    
    
    /*
     * 商品信息单选
     */
    function itemCheck(obj) {
        $("#checkeIds").val("");
        var checkBoxs = $('input[name="itemCheckbox"]');
        for (var i = 0; i < checkBoxs.length; i++) {
            // 如果被按下则拼接商品ID
            if (checkBoxs[i].checked) {
                var checkeIds = $("#checkeIds").val() + checkBoxs[i].value + ",";
                $("#checkeIds").val(checkeIds);
            }
        }
    }
    
    // 根据按钮不同改变searchForm的ACTION
    function changeAction(type) {
        // 检索按钮按下的时候
        if (type=='0') {
            $("#searchForm")[0].action = "${ctx}/order/businessDeal/";
            return true;
        // 导出按钮按下的时候
        } else if (type=='1') {
            $("#searchForm")[0].action = "${ctx}/order/businessDeal/export";
            $("#searchForm")[0].submit();
            return true;
        }
        return false;
    }
    function allCheck(obj) {
        var checked = obj.checked;
        // 如果是非选中的状态清除隐藏域的商品ID信息
        if (!checked) {
            $("#checkeIds").val("");
        }
        var checkBoxs = $('input[name="itemCheckbox"]');
        for (var i = 0; i < checkBoxs.length; i++) {
            checkBoxs[i].checked = checked;
            if (checkBoxs[i].checked) {
                var checkeIds = $("#checkeIds").val() + checkBoxs[i].value + ",";
                $("#checkeIds").val(checkeIds);
            }

        }
    }
    
    
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="${ctx}/order/businessDeal/">商户交易管理</a>
        </li>
         <li>
             <a href="${ctx}/order/propertyDeal">物业交易管理</a>
         </li>
    </ul>
    <form:form id="searchForm" modelAttribute="businessDeal" action="${ctx}/order/businessDeal/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <input type="hidden" id="checkeIds" value=""/>
        <ul class="ul-form">
            <li>
                <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${businessDeal.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                -
                <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${businessDeal.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </li>
            <li class="btns">
            	<input type="hidden" value="${businessDeal.provinceId }" id="provinceId">
                <select id="addrpro" name="provinceId" style="width: 120px;" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
            </li>
            <li class="btns">
	                <select id="addrcity" name="cityId" style="width: 120px;" onchange="changeVillage()">
	                    <option value="">全部城市</option>
	                </select>
            </li>
            <li class="btns">
                <select id="addrVillage" name="villageInfoId" style="width: 120px">
                    <option value="">全部楼盘</option>
                </select>
            </li>
             <li class="btns">
                 <form:select path="moduleManageId" class="input-medium">
                    <form:option value="" label="模块名称" />
                    <form:options items="${allModule}" itemLabel="moduleName" itemValue="id" htmlEscape="false" />
                </form:select>
            </li>
             <li class="btns">
                <form:select path="type" class="input-medium">
                    <form:option value="" label="终端来源"/>
                    <form:options items="${fns:getDictList('terminal_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li class="btns">
               <form:select path="payType" class="input-medium">
                    <form:option value="" label="付款方式"/>
                    <form:options items="${fns:getDictList('payType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
             <li class="btns">
                <form:input path="filter" htmlEscape="false" maxlength="64" placeholder="商户名称/订单号" class="input-xlarge "/>
            </li>
            
            <li class="btns">
                <input id="btnSubmit" class="btn btn-success" type="submit"  onclick="changeAction('0')" value="查询" />
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
   <div style="text-align: left;margin:-12px 0px -12px 0px;">
   		
		<ol class="breadcrumb" style="background-color: #FFF;border: 0px none #fff;">
		  	<li >
		  		<input type="checkbox" id="allCheck" onclick="allCheck(this)"> 全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input id="btnBack" class="btn" type="button" value="导出交易明细" style="width: 200px;" onclick="changeAction('1')" />
			</li>
		  	<li style="margin-left: 10%;width: 20%;color: red;">订单总额:${total.orderAmount } 元</li>
			<li style="width: 20%;color: red;">平台优惠:${total.couponMoney } 元</li>
			<li style="width: 20%;color: red;">商家优惠:${total.benefitMoney } 元</li>
		</ol>
	</div>
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-bordered table-condensed"> 	
        <thead>
            <tr>
                <th>序号</th>
                <th>商家名称</th>
                <th>模块类别</th>
                <th>产品模式</th>
                <th>订单号  </th>
                <th>订单金额</th>
                <th>产品金额</th>
                <th>配送/上门费</th>
                <th>平台优惠</th>
                <th>商家优惠</th>
                <th>付款方式</th>
                <th>订单状态</th>
                <th>终端来源</th>
                <th>下单时间</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="businessDeal" varStatus="status">
                <tr>
                  <td><input type="checkbox" name="itemCheckbox" onclick="itemCheck(this)" value="${businessDeal.id}"> ${status.count}</td>
               	  <td>${businessDeal.businessName}</td>
                	<td>${businessDeal.moduleName}
                		<c:if test="${businessDeal.prodType ==4 }">--</c:if>
                	</td>
                  <td>
                 	 <c:if test="${businessDeal.prodType != 4}">
						${fns:getDictLabel(businessDeal.prodType, 'prod_type', "")}
					</c:if>
                  	<c:if test="${businessDeal.prodType ==4 }">团购</c:if>
                  </td>
                  	 <td>
                  	 	<a href="${ctx}/order/businessDeal/form?id=${businessDeal.id}&prodType=${businessDeal.prodType}">${businessDeal.orderNo}</a>
                  	 </td>
                  	<td>${businessDeal.orderAmount}</td>
                  <td>${businessDeal.sumMoney}</td>
                  <td>${businessDeal.deliveryFee}</td>
                    <td>${businessDeal.couponMoney}</td>
                    <td>${businessDeal.benefitMoney}</td>
                   <td> ${fns:getDictLabel(businessDeal.payType, 'payType', "")}</td>
                   <td>
                   <c:if test="${businessDeal.prodType == 0 }">
                   	 ${fns:getDictLabel(businessDeal.orderState, 'order_goods_state', "")}
                   </c:if>
                  <c:if test="${businessDeal.prodType == 1 }">
                   	 ${fns:getDictLabel(businessDeal.orderState, 'order_service_state', "")}
                   </c:if>
                  <c:if test="${businessDeal.prodType == 2 }">
                   	 ${fns:getDictLabel(businessDeal.orderState, 'order_lesson_state', "")}
                   </c:if>
                   <c:if test="${businessDeal.prodType == 3 }">
                   	 ${fns:getDictLabel(businessDeal.orderState, 'order_field_state', "")}
                   </c:if>
                   <c:if test="${businessDeal.prodType == 4 }">
                   	 ${fns:getDictLabel(businessDeal.orderState, 'ordergrouppurcstate', "")}
                   </c:if>
                   </td>
                    <td> ${fns:getDictLabel(businessDeal.type, 'order_type', "")}</td>
                     <td><fmt:formatDate value="${businessDeal.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>