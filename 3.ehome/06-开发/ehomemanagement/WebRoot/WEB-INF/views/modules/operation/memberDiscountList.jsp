<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>会员的优惠券管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	    $("#btnExport").click(function() {
	        top.$.jBox.confirm("确认要导出优惠券数据吗？", "系统提示", function(v,h,f){
	            if(v == 'ok'){
	                $("#searchForm").prop("action", "${ctx}/operation/memberDiscount/export?discountId=${memberDiscount.discountId}");
	                $("#searchForm").submit();
	            }
	        }, {
	            buttonsFocus : 1
	        });
	        top.$('.jbox-body .jbox-icon').css('top', '55px');
	    });
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/memberDiscount/">会员的优惠券列表</a></li>
		<shiro:hasPermission name="operation:memberDiscount:edit">
			<li><a href="${ctx}/operation/memberDiscount/form">会员的优惠券添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberDiscount"
		action="${ctx}/operation/memberDiscount/" method="post"
		class="breadcrumb form-search">
		<input id="discountId" name="discountId" type="hidden" value="${memberDiscount.discountId}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><form:select path="useState"
					class="input-medium">
					<form:option value="" label="全部状态" />
					<form:options
						items="${fns:getDictList('use_state_memberdiscount')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><form:input path="discountNum"
					htmlEscape="false" maxlength="32" class="input-large" placeholder="请输入优惠券号、领取手机号"  /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查找" /></li>
			<shiro:hasPermission name="operation:couponManage:edit">
				<li style="float: right"><a class="btn btn-primary" id="btnExport"
					href="#"><i
						class="icon-eye-open icon-custom"></i> 导出数据</a></li>
			</shiro:hasPermission>
			<li style="float: right"><a class="btn btn-primary" 
                    href="${ctx}/operation/couponManage/" ><i
                        class="icon-eye-open icon-custom"></i> 返回</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>券号</th>
				<th>优惠券名称</th>
				<th>优惠券内容</th>
                <th>领取人</th>
                <th>领取时间</th>
                <th>订单号</th>
                <th>使用状态</th>
                <th>使用时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="memberDiscount" varStatus="status">
				<tr onClick="selectElem(this)">
					<td>${status.count}<input id="elemId" type="hidden"
						value="${memberDiscount.id}" />
					</td>
					<td>${memberDiscount.discountNum}</td>
					<td>${memberDiscount.discountId}</td>
					<td>${memberDiscount.remarks}</td>
                    <td>${memberDiscount.accountId}</td>
                    <td><fmt:formatDate value="${memberDiscount.obtainDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
                    </td>
                    <td>${memberDiscount.orderId}</td>
                    <td>${fns:getDictLabel(memberDiscount.useState, 'use_state_memberdiscount', '')}
                    </td>
					<td><fmt:formatDate value="${memberDiscount.useDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>