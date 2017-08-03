<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        $("#btnExport").click(function() {
            top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/sys/user/export");
                    $("#searchForm").submit();
                }
            }, {
                buttonsFocus : 1
            });
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });
        $("#btnImport").click(function() {
            $.jBox($("#importBox").html(), {
                title : "导入数据",
                buttons : {
                    "关闭" : true
                },
                bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
            });
        });
    });
    function page(n, s) {
        if (n)
            $("#pageNo").val(n);
        if (s)
            $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/sys/user/list");
        $("#searchForm").submit();
        return false;
    }
</script>
</head>
<body>
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left: 20px; text-align: center;" onsubmit="loading('正在导入，请稍等...');">
            <br />
            <input id="uploadFile" name="file" type="file" style="width: 330px" />
            <br /> <br />
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   " />
            <a href="${ctx}/sys/user/import/template">下载模板</a>
        </form>
    </div>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="${ctx}/sys/user/list">用户列表</a>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/role/selectUserToRole" method="post" class="breadcrumb form-search ">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();" />
        <ul class="ul-form">
            <li>
                <form:select path="loginFlag" class="input-medium">
                    <form:option value="" label="全部类型" />
                    <form:options items="${fns:getDictList('useable')}" itemLabel="label" itemValue="value" htmlEscape="false" />
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
            <shiro:hasPermission name="sys:user:add">
                <a href="${ctx}/sys/user/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:edit">
                <a id="btuElemEdit" href="${ctx}/sys/user/form?id=" onclick="return elemEdit()" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:delete">
                <a id="btuElemDelete" href="${ctx}/sys/user/delete?id=" onclick="return elemDelete('用户')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:frozen">
                <a id="btuElemFozen" href="${ctx}/sys/user/updateLoginFlag?id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '用户', 'frozen', true)">冻结</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:unfrozen">
                <a id="btuElemFozen" href="${ctx}/sys/user/updateLoginFlag?id=" class="btn btn-primary" onclick="return elemFrozen('loginFlag', '用户', 'unFrozen', true)">解冻</a>
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
                    <td>${status.index+1} <!--                      使用elemFrozen方法预处理 --> <c:if test="${user.loginFlag==1}">
                            <!--                            正常 -->
                            <input id="oldFrozen" type="hidden" value="0" />
                        </c:if> <c:if test="${user.loginFlag==0}">
                            <!--                            冻结 -->
                            <input id="oldFrozen" type="hidden" value="1" />
                        </c:if> <input id="elemId" type="hidden" value="${user.id}" />
                    </td>
                    <td>${user.loginName}</td>
                    <td>${user.name}</td>
                    <td><c:if test="${user.loginFlag==1}">
                            正常
                        </c:if> <c:if test="${user.loginFlag==0}">
                            冻结
                        </c:if></td>
                    <td><fmt:formatDate value="${user.createDate}" type="both" /></td>
                    <td><fmt:formatDate value="${user.oldLoginDate}" type="both" /></td>
                  
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>