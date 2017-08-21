<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>发言管理</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
        fillPro();
        $(document).ready(function() {});

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function changeDelFlag() {
            if (!$("#selectElemId").val()) {
                alertx("请选择要删除的发言");
                return false;
            } else {
                var editFlag = $(selectedElem).children().find("#speak_delflag").val();
                //团购活动：待开始之外的状态
                if (editFlag == '0') {
                    alertx("该发言已删除不能编辑");
                    return false;
                }
                var id = $("#selectElemId").val();
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/speak/socialSpeak/changeDelFlag',
                    data: {
                        id: id
                    },
                    dataType: 'json',
                    success: function(data) {
                        if (data == "1") {
                            $.jBox.tip('删除发言成功！');
                            //延迟一秒再跳转
                            setTimeout(function() {
                                window.location.href = "${ctx}/speak/socialSpeak/list";
                            }, 1000);
                        } else {
                            $.jBox.tip('删除发言失败！');
                        }
                    }
                });
            }
        }

        function isTop() {
            if (!$("#selectElemId").val()) {
                alertx("请选择要置顶的发言");
                return false;
            } else {
                var editFlag = $(selectedElem).children().find("#istop").val();
                //团购活动：待开始之外的状态
                if (editFlag == '1') {
                    alertx("该发言已置顶不能编辑");
                    return false;
                }
                var id = $("#selectElemId").val();
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/speak/socialSpeak/changeTop',
                    data: {
                        id: id,
                        istop: '1'
                    },
                    dataType: 'json',
                    success: function(data) {
                        if (data == "1") {
                            $.jBox.tip('发言置顶成功！');
                            //延迟一秒再跳转
                            setTimeout(function() {
                                window.location.href = "${ctx}/speak/socialSpeak/list";
                            }, 1000);
                        } else {
                            $.jBox.tip('发言置顶失败！');
                        }
                    }
                });
            }
        }

        function notTop() {
            if (!$("#selectElemId").val()) {
                alertx("请选择要取消置顶的发言");
                return false;
            } else {
                var editFlag = $(selectedElem).children().find("#istop").val();
                //团购活动：待开始之外的状态
                if (editFlag == '0') {
                    alertx("该发言已取消置顶不能编辑");
                    return false;
                }
                var id = $("#selectElemId").val();
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/speak/socialSpeak/changeTop',
                    data: {
                        id: id,
                        istop: '0'
                    },
                    dataType: 'json',
                    success: function(data) {
                        if (data == "1") {
                            $.jBox.tip('发言取消置顶成功！');
                            //延迟一秒再跳转
                            setTimeout(function() {
                                window.location.href = "${ctx}/speak/socialSpeak/list";
                            }, 1000);
                        } else {
                            $.jBox.tip('发言取消置顶失败！');
                        }
                    }
                });
            }
        }
         //以select的形式展示楼盘列表
        function changeVillageInfoSelect() {
            $.ajax({
                type: "POST",
                url: ctx + "/village/villageInfo/findListAllState",
                data: {
                    provinceId: $("#addrpro").val(),
                    cityId: $("#addrcity").val()
                },
                dataType: "JSON",
                success: function(data) {
                    var hidVillageId = $("#hidVillageId").val();
                    $("#addrVillageInfo").empty();
                    var option = "<option value=''>全部楼盘</option>";
                    $.each(data, function(indx, item) {
                        option += "<option value='" + item.id + "'>" + item.villageName + "</option>";
                    })
                    console.log(option);
                    $("#addrVillageInfo").append(option);
                    $("#addrVillageInfo").val(hidVillageId).trigger("change"); //修改初始时，带值选中
                }
            })
        }
    </script>
</head>

<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/speak/socialSpeak/">发言管理</a>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="socialSpeak" action="${ctx}/speak/socialSpeak/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <input type="text" class="hide" id="hidProId" value="${proId }">
        <input type="text" class="hide" id="hidCityId" value="${cityId }">
        <input type="text" class="hide" id="hidAreaId" value="">
        <input type="text" class="hide" id="hidVillageId" value="${villageId }">
        <input type="text" class="hide" id="hidDelFlag" value="${delflagId }">
        <ul class="ul-form">
            <li>
                <input id="beginCreatetime" name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${socialSpeak.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
                <input id="endCreatetime" name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${socialSpeak.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </li>
            <li class="btns">
                <select id="addrpro" name="addrpro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrcity" name="addrcity" style="width: 120px" onchange="changeVillageInfoSelect()">
                    <option value="">全部城市</option>
                </select>
            </li>
            <li class="btns" style="display:none;">
                <select id="addrarea" name="addrArea" style="width: 120px;display:none;">
                    <option value="">全部区域</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrVillageInfo" name="villageInfoId" style="width: 120px">
                    <option value="">全部楼盘${socialSpeak.delflag }</option>
                </select>
            </li>
            
             <li class="btns">
                <form:select path="delflagId" class="input-small">
                    <form:option value="" label="发言状态" />
                    <form:options items="${fns:getDictList('social_delflag')}" itemLabel="label" itemValue="value" htmlEscape="false" /></form:select>
          </li>
            <li><span>&nbsp;</span>
                <form:input path="content" htmlEscape="false" class="input-medium" placeholder="内容关键字搜索" />
            </li>
            <li class="btns">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <ul style="margin:10px;">
        <li style="list-style-type:none;">
            <shiro:hasPermission name="village:villageInfo:edit">   <a id="btuElemAdd" href="${ctx}/speak/socialSpeak/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i>我要发言</a>
                <a id="btuElemDelete" class="btn btn-primary" onclick="changeDelFlag()"><i class="icon-trash icon-custom"></i>删除</a>
                <a id="isTop" class="btn btn-primary" onclick="isTop()">置顶</a>
                <a id="notTop" class="btn btn-primary" onclick="notTop()">取消置顶</a>
            </shiro:hasPermission>
        </li>
    </ul>
    <input id="selectElemId" type="hidden" value="" />
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr>
                <th>序号</th>
                <th>楼盘名称</th>
                <th>发言内容</th>
                <th>图片</th>
                <th>可见范围</th>
                <th>阅/评/转</th>
                <th>来源</th>
                <th>发布人</th>
                <th>发言状态</th>
                <th>发布时间</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="socialSpeak" varStatus="status">
                <tr onClick="selectElem(this)">
                    <td>${status.count}
                        <input type="text" id="elemId" value="${socialSpeak.id }" style="display:none" />
                        <input type="text" id="istop" value="${socialSpeak.istop }" style="display:none" />
                    </td>
                    <td>${socialSpeak.villageinfoname}</td>
                    <td style="text-align:left">
                        <a href="${ctx}/speak/socialSpeak/detail?id=${socialSpeak.id}">
                            <c:if test="${socialSpeak.istop =='1'}">
                                <font color="red">【置顶】</font>
                            </c:if>
                            <c:if test="${socialSpeak.subList != '' && socialSpeak.subList != null}">
                                <c:forEach items="${socialSpeak.subList}" var="subList">
                                    <font color="blue">#${subList.subname}#</font>
                                </c:forEach>
                            </c:if>
                            <c:if test="${socialSpeak.tag != '' && socialSpeak.tag != null}">
                                <font color="blue">#${socialSpeak.tag}#</font>
                            </c:if> <font color="black">${socialSpeak.content}</font>
                        </a>
                    </td>
                    <td>
                        <c:forEach items="${socialSpeak.imageUrls}" var="imgUrl">
                            <img src="${imgUrl}" />
                        </c:forEach>
                        <c:if test="${socialSpeak.picSize > 0}">
                        	<br>
                            <span>${socialSpeak.picSize}张</span>
                        </c:if>
                        <c:if test="${socialSpeak.picSize <= 0}">
                            <span>\</span>
                        </c:if>
                        <%-- <img id="preview" src="${socialSpeak.images}" style="width:45px; height:45;" />--%></td>
                    <td>${fns:getDictLabel(socialSpeak.visrange, 'visRange', '')}</td>
                    <td>${socialSpeak.readnum}/${socialSpeak.sumcomment}/${socialSpeak.sumforward}</td>
                    <td>
                    	<c:if test="${socialSpeak.source > 0}">
                    		公号
                    	</c:if>
                    	<c:if test="${socialSpeak.source <= 0}">
                    		会员
                    	</c:if>
                    </td>
                    <td>
                    	<input type="hidden" value="${socialSpeak.auserid }"/>
                    	${socialSpeak.ausername}
                    </td>
                    <td>
                    	<c:if test="${socialSpeak.delFlag == 1 }">
                    		正常
                    	</c:if>
                    	<c:if test="${socialSpeak.delFlag == 0 }">
                    		已删除
                    	</c:if>
                        <input type="text" id="speak_delflag" value="${socialSpeak.delFlag }" style="display:none" />
                    </td>
                    <td>
                        <fmt:formatDate value="${socialSpeak.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>

</html>