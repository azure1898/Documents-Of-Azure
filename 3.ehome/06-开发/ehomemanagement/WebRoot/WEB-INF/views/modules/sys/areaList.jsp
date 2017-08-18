<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "0";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});
			fillPro(); // 加载全部省市区数据
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];

				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_area_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
        <li style="height:30px"> 基础资料>
           <span><a href="${ctx}/sys/area/">城市管理 </a></span>
        </li>
    </ul>
	<form:form id="searchForm" modelAttribute="area"
		action="${ctx}/sys/area/" method="post"
		class="breadcrumb form-search">
		<input type="text" class="hide" id="hidProId" value="${proId}"/> 
		<input type="text" class="hide" id="hidCityId" value="${cityId}"/>
	    <input type="text" class="hide" id="hidAreaId" value="${areaId}"/>
		<ul class="ul-form">
		   <li class="btns">
			    <select id="addrpro" name="addrPro" style="width: 140px; " onchange="changeCity()">
			     <option value="">全部省、直辖市</option>
			    </select>
			 </li>
			 <li class="btns">
			    <select id="addrcity" name="addrCity" style="width: 120px;" onchange="changeArea()">
			     <option value="">全部城市</option>
			    </select>
			 </li>
			 <li class="btns">
			    <select id="addrarea" name="addrArea" style="width: 180px;">
			     <option value="">全部区域</option>
			    </select>
			 </li>
			 <li class="btns">
				<form:input path="name" placeholder="省名称、城市名称、区域名称" htmlEscape="false" maxlength="60" class="input-large"/>
			 </li>
			 <li class="btns"><input id="btnSubmit" class="btn btn-success" type="submit" value="查询"></li>
			 <li class="clearfix"></li>
		</ul>
		
	</form:form>
	<div style="margin: 10px">
		<shiro:hasPermission name="sys:area:edit">
			<a href="${ctx}/sys/area/form" class="btn btn-primary"><i class="icon-plus icon-custom"></i>添加</a>
		</shiro:hasPermission>
	</div>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>区域名称</th><th>区域编码</th><th>区域类型</th><th>备注</th><shiro:hasPermission name="sys:area:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/area/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.code}}</td>
			<td>{{dict.type}}</td>
			<td>{{row.remarks}}</td>
			<shiro:hasPermission name="sys:area:edit"><td>
				<a href="${ctx}/sys/area/form?id={{row.id}}">编辑</a>
				<a href="${ctx}/sys/area/delete?id={{row.id}}" onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/area/form?parent.id={{row.id}}&mode=subAdd">添加下级区域</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>