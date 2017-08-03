<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>公告管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
                fillPro();
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            if ($("#endCreateDate").val() < $("#beginCreateDate").val()) {
                alertx("您输入的开始时间在结束时间之后，请重新输入！");
            } else {
             	$("#searchForm").submit();
            }
            return false;
        }
        
        /**
         * 删除社区公告信息
         */
        function elemDeleteNotice(moduleName) {
            if (!$("#selectElemId").val()) {
                 alertx("请选择要删除的社区公告信息");
                return false;
            } else {
                var elemId = $("#selectElemId").val();
                var tempHref = $("#btuElemDelete").attr("href") + elemId;
                if (confirmx("确认删除此条公告信息吗？", tempHref)) {
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
        <li class="active"><a href="${ctx}/notice/noticeManage/">公告列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="noticeManage" action="${ctx}/notice/noticeManage/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input id="selectElemId" type="hidden" value=""/>
        <input type="text" class="hide" id="hidProId"  value="${addrpro}">
        <input type="text" class="hide" id="hidCityId" value="${addrcity}">
        <input type="text" class="hide" id="hidAreaId" value="">
        <input type="text" class="hide" id="hidVillageId" value="${addrVillage}">
        <ul class="ul-form">
            <li>
                <input id="beginCreateDate" name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${noticeManage.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
                <input id="endCreateDate" name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${noticeManage.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </li>
            <li class="btns">
                <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
                 <option value="">全部省份</option>
                </select>
               </li>
               <li class="btns">
                <select id="addrcity" name="addrCity" style="width: 120px" onchange="changeVillage()">
                 <option value="">全部城市</option>
                </select>
               </li>
               <li class="btns" style="display:none;">
                <select id="addrarea" name="addrArea" style="width: 120px;display:none;">
                 <option value="">全部区域</option>
                </select>
               </li>
               <li class="btns">
                <select id="addrVillage" name="villageInfoId" style="width: 120px">
                 <option value="">全部楼盘</option>
                </select>
            </li>
            <li class="btns">
                <form:input path="noticeTitle" htmlEscape="false" placeholder="社区公告名称" maxlength="25" class="input-medium"/>
            </li>
            <li class="btns">
                <a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>        
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="notice:noticeManage:edit">
             <a href="${ctx}/notice/noticeManage/form" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
             <a id="btuElemEdit" href="${ctx}/notice/noticeManage/form?id=" class="btn btn-primary" onclick="return elemEdit('社区公告信息')"><i class="icon-edit icon-custom"></i>编辑</a>
             <a id="btuElemDelete" href="${ctx}/notice/noticeManage/delete?id=" class="btn btn-primary" onclick="return elemDeleteNotice()"><i class="icon-trash icon-custom"></i>删除</a>
         </shiro:hasPermission>
      </li>
     </ul>
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr>
                <th style="width : 15%">序号</th>
                <th style="width : 50%">公告标题</th>
                <th style="width : 35%">发布时间</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="noticeManage" varStatus="status">
            <tr onClick="selectElem(this)">
                <td>
                    ${status.count}
                    <input id="elemId" type="hidden" value="${noticeManage.id}"/>
                </td>
                <td>
                    ${noticeManage.noticeTitle}
                </td>
                <td>
                    <fmt:formatDate value="${noticeManage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>