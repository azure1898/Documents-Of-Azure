<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>验券管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/coupon/groupPurchase/">优惠/验券管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="groupPurchase" action="${ctx}/coupon/groupPurchase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<sys:message content="${message}"/>
	<p style="font-weight:bold;font-size:18px;margin-left:10px;">团购活动</p>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98%">
		<thead>
			<tr>
			    <th>序号</th>
				<th>图片</th>
				<th>团购名称</th>
				<th>团购价（元）</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>销售量</th>
				<th>库存量</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="groupPurchase" varStatus="status">
			<tr>
			    <td>${status.count}</td>
				<td>
					<img id="preview" src="${groupPurchase.groupPurcPic}" style="width:45px;height:45px;"/>
				</td>
				<td>
					<a href="${ctx}/coupon/groupPurchase/detail?id=${groupPurchase.id}">
						${groupPurchase.groupPurcName}
					</a>
				</td>
				<td>
					${groupPurchase.groupPurcMoney}
				</td>
				<td>
					<fmt:formatDate value="${groupPurchase.startTime}" pattern="yyyy-MM-dd HH"/>
				</td>
				<td>
					<fmt:formatDate value="${groupPurchase.endTime}" pattern="yyyy-MM-dd HH"/>
				</td>
				<td>
					${groupPurchase.saleNum}
				</td>
				<td>
					${groupPurchase.stockNum}
				</td>
				<td>
					${fns:getDictLabel(groupPurchase.groupPurcState, 'grouppurcstate', '')}
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>