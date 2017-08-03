<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>退款信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        fillPro();//加载全部省市区数据；
    });
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
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
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="${ctx}/order/orderRefundInfo/">退款信息列表</a>
        </li>
        <shiro:hasPermission name="order:orderRefundInfo:edit">
            <li>
                <a href="${ctx}/order/orderRefundInfo/form">退款信息添加</a>
            </li>
        </shiro:hasPermission>
    </ul>
    <form:form id="searchForm" modelAttribute="orderRefundInfo" action="${ctx}/order/orderRefundInfo/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <input type="text" class="hide" id="hidProId" value="" />
        <input type="text" class="hide" id="hidCityId" value="" />
        <input type="text" class="hide" id="hidAreaId" value="" />
        <input type="text" class="hide" id="hidVillageId" value="${orderRefundInfo.villageId }" />
        <ul class="ul-form">
            <li>
                <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${orderRefundInfo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                -
                <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${orderRefundInfo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
            </li>
            <li class="btns">
                <select id="addrpro" name="addrPro" style="width: 120px; display: none;" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrcity" name="addrCity" style="width: 120px; display: none;" onchange="changeVillage()">
                    <option value="">全部城市</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrarea" name="addrArea" style="width: 120px; display: none;">
                    <option value="">全部区域</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrVillage" name="villageId" style="width: 120px">
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
                <form:select path="refundState" class="input-medium">
                    <form:option value="" label="退款状态" />
                    <form:options items="${fns:getDictList('refundState')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </li>
            <li class="btns">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="查询" />
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}" />
    <input type="hidden" id="checkeIds" />
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th><input type="checkbox" id="allCheck" onclick="allCheck(this)">序号</th>
                <th>下单时间</th>
                <th>商户名称</th>
                <th>模块类别</th>
                <th>订单号</th>
                <th>付款方式</th>
                <th>订单金额</th>
                <th>退款金额</th>
                <th>订单状态</th>
                <th>退款状态</th>
                <shiro:hasPermission name="order:orderRefundInfo:refund">
                    <th>操作</th>
                </shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="orderRefundInfo" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="itemCheckbox" onclick="itemCheck(this)" value="${orderRefundInfo.id}"> ${status.count}</td>
                    <td><fmt:formatDate value="${orderRefundInfo.createDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
                    <td>${orderRefundInfo.businessInfoId}</td>
                    <td>${orderRefundInfo.moduleManageId}</td>
                    <td>${orderRefundInfo.orderNo}</td>
                    <td>${orderRefundInfo.orderType}</td>
                    <td>${orderRefundInfo.orderMoney}</td>
                    <td>${orderRefundInfo.refundMoney}</td>
                    <td>${orderRefundInfo.remarks}</td>
                    <!--订单状态  -->
                    <td>${orderRefundInfo.refundState}</td>
                    <shiro:hasPermission name="order:orderRefundInfo:refund">
                        <td><a href="${ctx}/order/orderRefundInfo/form?id=${orderRefundInfo.id}">申请退款</a></td>
                    </shiro:hasPermission>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>