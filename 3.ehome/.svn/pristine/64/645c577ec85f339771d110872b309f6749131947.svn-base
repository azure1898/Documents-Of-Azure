<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" import="com.its.modules.sys.utils.UserUtils"%>
<html>
<head>
<title>角色管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
   
    function page(n, s) {
    	$("#pageNo").val(n);
    	$("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/sys/role/list");
        $("#searchForm").submit();
        return false;
    }
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <span class="common-breadcrumb">
                系统管理&nbsp;>&nbsp;<a href="${ctx}/sys/role/list">角色管理 </a>
            </span>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="role" action="${ctx}/sys/role/list" method="post" class="breadcrumb form-search ">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li>
                <form:select path="roleType" class="input-medium">
                    <form:option value="">角色所属</form:option>
                    <form:option value="assignment">实地内部</form:option>
                    <form:option value="security-role">合作公司</form:option>
                </form:select>
            </li>
            <li class="btns">
                <form:input path="name" placeholder="请输入角色名称" htmlEscape="false" maxlength="50" class="input-small" />
            </li>

            <li class="btns">
                <a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <ul style="margin: 10px;">
        <li style="list-style-type: none;">
            <shiro:hasPermission name="sys:role:add">
                <a href="${ctx}/sys/role/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:edit">
                <a id="btuElemEdit" href="${ctx}/sys/role/form?id=" class="btn btn-primary" onclick="return elemEdit()"><i class="icon-edit icon-custom"></i>编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:delete">
                <a id="btuElemDelete" href="${ctx}/sys/role/delete?id=" class="btn btn-primary" onclick="return elemDelete('角色')"><i class="icon-trash icon-custom"></i>删除</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:view">
                <a id="btuElemAuthorization" href="${ctx}/sys/role/assign?id=" class="btn btn-primary" onclick="return elemAuthorization()">角色授权</a>
            </shiro:hasPermission>
        </li>
    </ul>
    <input id="selectElemId" type="hidden" value="" />
    <input id="yesDictValue" type="hidden" value="${fns:getDictValue('是', 'yes_no', '1')}" />
    <input id="isAdminUser" type="hidden" value="<%=UserUtils.getUser().isAdmin()%>" />
    <c:choose>
		<c:when test="${msgType != null and msgType != ''}">
    		<sys:message content="${message}" type="${msgType}"/>
		</c:when>
		<c:otherwise>
		    <sys:message content="${message}"/>
		</c:otherwise>
	</c:choose>
    <table id="contentTable" class="table table-bordered table-condensed">
        <tr>
            <th>序号</th>
            <th>角色</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.list}" var="role" varStatus="status">
            <tr onClick="selectElem(this)">
                <td>${(status.index + 1) + ((page.pageNo - 1) * (page.pageSize))}<input id="elemId" type="hidden" value="${role.id}" /> <input id="roleSysData" type="hidden" value="${role.sysData}" />
                </td>
                <td>${role.name}</td>
                <td>${role.remarks}</td>
                <shiro:hasPermission name="sys:role:edit">
                    <td><a href="${ctx}/sys/user/roleUserList?role.id=${role.id}">管理用户</a></td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
    </table>
    <div class="pagination">${page}</div>
    <script type="text/javascript">
    function elemEdit() {
    	if (!$("#selectElemId").val()) {
			alertx("请选择要编辑的角色");
			return false;
		} else {
       
            var roleSysData = $(selectedElem).children().find("#roleSysData").val();
            var yesDictValue = $("#yesDictValue").val();
            var isAdmin = $("#isAdminUser").val();
            if ((roleSysData == yesDictValue && "true" == isAdmin) || roleSysData != yesDictValue) {
            	var elemId = $("#selectElemId").val();
                $("#btuElemEdit").attr("href", $("#btuElemEdit").attr("href") + elemId);
                return true;
            } else {
                alertx("越权操作，只有超级管理员才能修改此数据！");
                var editHref = $("#btuElemEdit").attr("href");
                // 撤销方法elemEdit()中对href进行的操作
                $("#btuElemEdit").attr("href", editHref.substring(0, editHref.lastIndexOf("=") + 1));
                return false;
            }
        }
    }
    </script>
</body>
</html>