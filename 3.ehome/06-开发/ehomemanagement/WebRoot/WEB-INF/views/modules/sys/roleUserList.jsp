<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>管理用户</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        
    });
    function page(n, s) {
        if (n)
            $("#pageNo").val(n);
        if (s)
            $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/sys/user/roleUserList");
        $("#searchForm").submit();
        return false;
    }
    function elemDeleteUser(moduleName) {
        if (!$("#selectElemId").val()) {
            alertx("请选择要删除的用户");
            return false;
        } else {
            var elemId = $("#selectElemId").val();
            var tempHref = $("#btuElemDelete").attr("href") + elemId;
            msgConfirm = "确认删除此用户？";
            if (confirmx(msgConfirm , tempHref)) {
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
        <li>          
            <span>系统管理 > <a href="${ctx}/sys/role/list">角色管理 > </a>查看用户 </span>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/roleUserList" method="post" class="breadcrumb form-search ">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <input id="roleId" name="role.id" type="hidden" value="${user.role.id}" />
        <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();" />
        <ul class="ul-form">
            <li class="btns">
                <form:select path="loginFlag" class="input-medium">
                    <form:options items="${fns:getDictList('userstate')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </li>
            <li class="btns">
                <form:input path="loginName" placeholder="请输入用户名" htmlEscape="false" maxlength="50" class="input-small" />
            </li>
            <li class="btns">
                <a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <ul style="margin: 10px;">
        <li style="list-style-type: none;">
            <shiro:hasPermission name="sys:user:edit">
                <a id="btuElemEdit" href="${ctx}/sys/user/form?roleId=${user.role.id}&id=" onclick="return elemEdit('用户')" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:delete">
                <a id="btuElemDelete" href="${ctx}/sys/user/delete?roleId=${user.role.id}&id=" onclick="return elemDeleteUser('用户')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:frozen">
                <a id="btuElemFozen" href="${ctx}/sys/user/updateLoginFlag?roleId=${user.role.id}&id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '用户', 'frozen', true)">冻结</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:unfrozen">
                <a id="btuElemFozen" href="${ctx}/sys/user/updateLoginFlag?roleId=${user.role.id}&id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '用户', 'unFrozen', true)">取消冻结</a>
            </shiro:hasPermission>
        </li>
    </ul>
    <input id="selectElemId" type="hidden" value="" />
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr align="center">
                <th align="center">序号</th>
                <th>用户名</th>
                <th>姓名</th>
                <th>状态</th>
                <th>添加时间</th>
                <th>最后登录时间</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="user" varStatus="status">
                <tr onClick="selectElem(this)">
                    <td>${(status.index + 1) + ((page.pageNo - 1) * (page.pageSize))}<!--使用elemFrozen方法预处理 -->
                     <c:if test="${user.loginFlag==1}">
                            <!-- 正常 -->
                            <input id="oldFrozen" type="hidden" value="0" />
                      </c:if> <c:if test="${user.loginFlag==0}">
                            <!-- 冻结 -->
                            <input id="oldFrozen" type="hidden" value="1" />
                      </c:if> 
                      <input id="elemId" type="hidden" value="${user.id}" />
                    </td>
                    <td>${user.loginName}</td>
                    <td>${user.name}</td>
                    <td>${fns:getDictLabel(user.loginFlag, 'userstate', '')}</td>
                    <td><fmt:formatDate value="${user.createDate}" type="both" /></td>
                    <td><fmt:formatDate value="${user.oldLoginDate}" type="both" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>