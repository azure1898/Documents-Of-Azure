<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    request.setCharacterEncoding("UTF-8");
    String type = request.getParameter("type");
%>
<html>
<head>
<title>日志管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    var type =<%=type%>;
    $("#logType li").eq(0).addClass("active");
    $('#logType li').eq(0).attr('class', 'active')
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
</head>
<body>
    <ul class="nav nav-tabs" id="logType">
        <li>
            <a href="${ctx}/sys/log?type=1">操作日志</a>
        </li>
        <li>
            <a href="${ctx}/sys/log?type=2">登录日志</a>
        </li>
        <li>
            <a href="${ctx}/sys/log?type=3">错误日志</a>
        </li>
        <li style="display: none;">
            <a href="${ctx}/sys/log?type=4">安全日志</a>
        </li>
    </ul>
    <script type="text/javascript">
        var type = <%=type%>;
        if(type==null){
          type='${log.type}'
        }
        $('#logType li').eq(type - 1).attr('class', 'active')
        
    </script>
    <form:form id="searchForm" action="${ctx}/sys/log/list?type=<%=type %>" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input name="type" type="hidden" value="${log.type}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <div style="margin-top: 8px;">
            <input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
            <label>&nbsp;&nbsp;</label>
            <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
            &nbsp;&nbsp;&nbsp;
            <input id="createBy.loginName" placeholder="用户名查询" name="createBy.loginName" type="text" maxlength="50" class="input-small" value="${log.createBy.loginName}" />
            &nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-success">
                <i class="icon-search icon-white"></i> 查询
            </button>
        </div>
    </form:form>
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>操作日期</th>
                <th>操作记录</th>
            </tr>
        </thead>
        <tbody>
            <%
                request.setAttribute("strEnter", "\n");
                request.setAttribute("strTab", "\t");
            %>
            <c:forEach items="${page.list}" var="log" varStatus="status">
                <tr>
                    <td>${(status.index + 1) + ((page.pageNo - 1) * (page.pageSize))}</td>
                    <td>${log.createBy.loginName}</td>
                    <td><fmt:formatDate value="${log.createDate}" type="both" /></td>
                    <td>${log.title}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>

</html>