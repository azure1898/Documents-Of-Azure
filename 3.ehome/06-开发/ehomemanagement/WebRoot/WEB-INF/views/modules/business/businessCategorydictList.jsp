<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户分类管理</title>
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
        
		/**
		 * 删除商家分类
		 * 
		 * @returns {Boolean}
		 */
		function elemDeleteBusinessCategory() {
		    if (!$("#selectElemId").val()) {
		        alertx("请选择要删除的行");
		        return false;
		    } else {
		        var elemId = $("#selectElemId").val();
		        var tempHref = $("#btuElemDelete").attr("href") + elemId;
		        if (confirmx("确实删除此商家分类？", tempHref)) {
		            return true;
		        } else {
		            return false;
		        }
		    }
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/business/businessCategorydict/">商户分类列表</a></li>
	</ul>
	<ul style="margin:10px;">
		     <li style="list-style-type:none;">
				<shiro:hasPermission name="business:businessCategorydict:add">
					<a href="${ctx}/business/businessCategorydict/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="business:businessCategorydict:edit">
					<a id="btuElemEdit" href="${ctx}/business/businessCategorydict/form?id=" onclick="return elemEdit()" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="business:businessCategorydict:delete">
					<a id="btuElemDelete" href="${ctx}/business/businessCategorydict/delete?id=" onclick="return elemDeleteBusinessCategory()" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
				</shiro:hasPermission>
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="businessCategorydict" action="${ctx}/business/businessCategorydict/" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:20%">序号</th>
				<th style="width:20%">分类名称</th>
				<th style="width:20%">产品模式</th>
				<th style="width:30%">分类介绍</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessCategorydict" varStatus="status">
			<tr onClick="selectElem(this)">
				<td>
					${(page.pageNo - 1) * page.pageSize + status.count}
					<input id="elemId" type="hidden" value="${businessCategorydict.id}"/>
				</td>
				<td>
					${businessCategorydict.categoryName}
				</td>
				<td>
					${fns:getDictLabel(businessCategorydict.prodType, 'prod_type', '')}
				</td>
				<td>
					${businessCategorydict.categoryIntroduce}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>