<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>位置信息管理</title>
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
        <li class="active"><a href="${ctx}/adver/adverPosition/">位置列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="adverPosition" action="${ctx}/adver/adverPosition/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input id="selectElemId" type="hidden" value=""/>
        <ul class="ul-form">
            <li>
                    <form:select path="moduleCode" class="input-xlarge product-line">
                        <form:option value="" label="产品线" />
                        <form:options items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
            </li>
            <li class="btns"><a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
     <ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="adver:adverPosition:add">
            <a href="${ctx}/adver/adverPosition/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="adver:adverPosition:edit">
            <a id="btuElemEdit" href="${ctx}/adver/adverPosition/form?id=" class="btn btn-primary" onclick="return elemEdit('位置')"><i class="icon-edit icon-custom"></i>编辑</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="adver:adverPosition:delete">
            <a id="btuElemDelete" href="${ctx}/adver/adverPosition/delete?id=" class="btn btn-primary" onclick="return elemDelete('位置')"><i class="icon-trash icon-custom"></i>删除</a>
         </shiro:hasPermission>
      </li>
     </ul>
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr>
                <th style="width:25%">序号</th>
                <th style="width:35%">位置名称</th>
                <th style="width:40%">产品线</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="adverPosition" varStatus="status">
            <tr onClick="selectElem(this)">
                <td>
                    ${status.count}
                    <input id="elemId" type="hidden" value="${adverPosition.id}"/>
                </td>
                <td>
                    ${adverPosition.positionName}
                <td>
                    ${fns:getDictLabel(adverPosition.moduleCode, 'product_line', '')}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>