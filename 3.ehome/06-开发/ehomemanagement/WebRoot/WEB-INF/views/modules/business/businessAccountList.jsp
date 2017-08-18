<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    request.setCharacterEncoding("UTF-8");
            String businessinfoId = request.getParameter("businessinfoId");
            String prodType = request.getParameter("prodType");
%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
    });
    function page(n, s) {
        if (n)
            $("#pageNo").val(n);
        if (s)
            $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/business/businessAccount/list?businessinfoId=<%=businessinfoId%>");
        $("#searchForm").submit();
        return false;
    }
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <span><a href="${ctx}/business/businessInfo">商户管理 </a>> 
            <a href="${ctx}/business/businessAccount?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>">管理账户</a>
            </span>
        </li>
    </ul>
     <form:form id="searchForm" style="opacity: 0;padding: 0px;" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search ">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();" />
    </form:form>
    <ul style="margin: 10px;">
        <li style="list-style-type: none;">
            <shiro:hasPermission name="business:businessAccount:add">
                <a href="${ctx}/business/businessAccount/form?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>&id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="business:businessAccount:edit">
                <a id="btuElemEdit" href="${ctx}/business/businessAccount/form?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>&id=" onclick="return elemEdit('商户账号')" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="business:businessAccount:frozen">
                <a id="btuElemFozen" href="${ctx}/business/businessAccount/updateLoginFlag?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>&id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '商户账号', 'frozen', true)">冻结</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="business:businessAccount:unfrozen">
                <a id="btuElemFozen" href="${ctx}/business/businessAccount/updateLoginFlag?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>&id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '商户账号', 'unfrozen', true)">取消冻结</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="business:businessAccount:delete">
                <a id="btuElemDelete" href="${ctx}/business/businessAccount/delete?businessinfoId=<%=businessinfoId %>&prodType=<%=prodType %>&id=" onclick="return elemDelete('商户账号')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
            </shiro:hasPermission>
        </li>
    </ul>
    <input id="selectElemId" type="hidden" value="" />
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr align="center">
                <th align="center">序号</th>
                <th>账户名</th>
                <th>添加时间</th>
                <th>状态</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="user" varStatus="status">
                <tr onClick="selectElem(this)">
                    <td>${status.index+1}<c:if test="${user.loginFlag==1}">
                            <!-- 正常 -->
                            <input id="oldFrozen" type="hidden" value="0" />
                      </c:if> <c:if test="${user.loginFlag==0}">
                            <!--                              冻结 -->
                            <input id="oldFrozen" type="hidden" value="1" />
                      </c:if> 
                      <input id="elemId" type="hidden" value="${user.id}" />
                    </td>
                    <td>${user.loginName}</td>
                    <td><fmt:formatDate value="${user.createDate}" type="both" /></td>
                    <td><c:if test="${user.loginFlag==1}">
                             正常
                         </c:if> <c:if test="${user.loginFlag==0}">
                             冻结
                         </c:if></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>