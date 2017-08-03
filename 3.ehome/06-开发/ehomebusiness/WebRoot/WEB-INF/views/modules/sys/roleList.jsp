<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" import="com.its.modules.sys.utils.UserUtils"%>
<html>
<head>
<title>角色管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function roleEdit() {
		if (elemEdit()) {
			var roleSysData = $(selectedElem).children().find("#roleSysData").val();
			var yesDictValue = $("#yesDictValue").val();
			var isAdmin = $("#isAdminUser").val();
			if ((roleSysData == yesDictValue && "true" == isAdmin) || roleSysData != yesDictValue) {
				return true;
			} else {
				alertx("越权操作，只有超级管理员才能修改此数据！");
				var editHref = $("#btuElemEdit").attr("href");
				// 撤销方法elemEdit()中对href进行的操作
				$("#btuElemEdit").attr("href",editHref.substring(0,editHref.lastIndexOf("=") +1));
				return false;
			}
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
	</ul>
		<ul style="margin:10px;">
		     <li style="list-style-type:none;">
			     	<shiro:hasPermission name="sys:role:edit">
						<td>
							<a href="${ctx}/sys/role/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
							<a id="btuElemAuthorization" href="${ctx}/sys/role/assign?id=" class="btn btn-primary" onclick="return elemAuthorization()">授权</a>
							<a id="btuElemEdit" href="${ctx}/sys/role/form?id=" class="btn btn-primary" onclick="return roleEdit()"><i class="icon-edit icon-custom"></i>修改</a>
							<a id="btuElemDelete" href="${ctx}/sys/role/delete?id=" class="btn btn-primary" onclick="return elemDelete('角色')"><i class="icon-trash icon-custom"></i>删除</a>
						</td>
					</shiro:hasPermission>
			     </li>
		</ul>
	<input id="selectElemId" type="hidden" value=""/>
	<input id="yesDictValue" type="hidden" value="${fns:getDictValue('是', 'yes_no', '1')}"/>
	<input id="isAdminUser" type="hidden" value="<%= UserUtils.getUser().isAdmin()%>"/>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-bordered table-condensed">
		<tr>
			<th>序号</th>
			<th>角色名称</th>
			<th>描述</th>
		</tr>
		<c:forEach items="${list}" var="role" varStatus="status">
			<tr  onClick="selectElem(this)">
				<td>
					${status.index+1}
					<input id="elemId" type="hidden" value="${role.id}"/>
					<input id="roleSysData" type="hidden" value="${role.sysData}"/>
				</td>
				<td>${role.name}</td>
				<td>${role.remarks}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>