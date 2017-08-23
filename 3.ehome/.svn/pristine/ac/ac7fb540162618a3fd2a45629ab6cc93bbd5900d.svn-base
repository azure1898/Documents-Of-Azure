<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			if ($("#createDateEnd").val() != null && $("#createDateEnd").val() != "" 
				    && $("#createDateStart").val() != null && $("#createDateStart").val() != "" 
				    && $("#createDateEnd").val() < $("#createDateStart").val()) {
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
		<li class="active"><a href="${ctx}/edition/editionManage/">版本列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="editionManage" action="${ctx}/edition/editionManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<ul class="ul-form">
		     <li>
				<input id="createDateStart" name="createDateStart" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${editionManage.createDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li>-
				<input id="createDateEnd" name="createDateEnd" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${editionManage.createDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li>&nbsp;&nbsp;&nbsp;
				<form:select path="productType" class="input-xlarge">
					<form:option value="" label="全部产品"/>
					<form:options items="${productTypeMap}" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a> 
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="edition:editionManage:add">
              <a href="${ctx}/edition/editionManage/form" class="btn btn-primary"><i class="icon-plus icon-custom"></i>上传版本</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="edition:editionManage:edit">
              <a id="btuElemEdit" href="${ctx}/edition/editionManage/form?id=" class="btn btn-primary" onclick="return elemEdit('版本')"><i class="icon-edit icon-custom"></i>编辑</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="edition:editionManage:delete">
              <a id="btuElemDelete" href="${ctx}/edition/editionManage/delete?id=" class="btn btn-primary" onclick="return elemDelete('版本')"><i class="icon-trash icon-custom"></i>删除</a>
         </shiro:hasPermission>
      </li>
    </ul>
	<c:choose>
		<c:when test="${msgType != null and msgType != ''}">
    		<sys:message content="${message}" type="${msgType}"/>
		</c:when>
		<c:otherwise>
		    <sys:message content="${message}"/>
		</c:otherwise>
	</c:choose>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:20%">序号</th>
				<th style="width:30%">产品类型</th>
				<th style="width:20%">版本名称</th>
				<th style="width:30%">发布时期</th>
<%-- 				<shiro:hasPermission name="edition:editionManage:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="editionManage" varStatus="status">
			<tr onClick="selectElem(this)">
				<td>
					${(page.pageNo - 1) * page.pageSize + status.count}
					<input id="elemId" type="hidden" value="${editionManage.id}"/>
				</td>
				<td>
					${fns:getDictLabel(editionManage.productLine, 'product_line', '')}
					<c:choose>
						<c:when test="${not empty editionManage.systemType}">
							- ${fns:getDictLabel(editionManage.systemType, 'prod_sys_type', '')}
						</c:when>
					</c:choose>
				</td>
				<td>
					${editionManage.editionName}
				</td>
				<td>
					<fmt:formatDate value="${editionManage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<%-- 				<shiro:hasPermission name="edition:editionManage:edit"><td> --%>
<%--     				<a href="${ctx}/edition/editionManage/form?id=${editionManage.id}">修改</a> --%>
<%-- 					<a href="${ctx}/edition/editionManage/delete?id=${editionManage.id}" onclick="return confirmx('确认要删除该版本吗？', this.href)">删除</a> --%>
<%-- 				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>