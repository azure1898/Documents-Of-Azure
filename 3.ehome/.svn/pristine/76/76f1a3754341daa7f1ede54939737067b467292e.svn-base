<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图标管理</title>
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
		<li class="active"><a href="${ctx}/module/moduleManage/">图标列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="moduleManage" action="${ctx}/module/moduleManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<ul class="ul-form">
			<li>
				<form:input path="moduleName" placeholder="模块名称" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>		
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
		     <li style="list-style-type:none;">
		     	<shiro:hasPermission name="module:moduleManage:add">
					<a href="${ctx}/module/moduleManage/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="module:moduleManage:edit">
					<a id="btuElemEdit" href="${ctx}/module/moduleManage/form?id=" onclick="return elemEdit()" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="module:moduleManage:delete">
					<a id="btuElemDelete" href="${ctx}/module/moduleManage/delete?id=" onclick="return elemDelete('')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
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
	<table id="contentTable" class="table table-bordered table-condensed" >
		<thead>
			<tr>
				<th style="width:10%">序号</th>
				<th style="width:30%">模块名称</th>
				<th style="width:30%">模块类别</th>
				<th style="width:30%">图标</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="moduleManage" varStatus="status">
			<tr  onClick="selectElem(this)">
				<td>
					${(page.pageNo - 1) * page.pageSize + status.count}
					<input id="elemId" type="hidden" value="${moduleManage.id}"/>
				</td>
				<td>${moduleManage.moduleName}</td>
				<td>
					${fns:getDictLabel(moduleManage.moduleType, 'module_type', '')}
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty moduleManage.moduleIcon}">
							<img id="imgPreview" src="${moduleManage.moduleIcon}" alt="图标" style="width : 45px; height : 45px;" onclick="openBrowse();" />
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>