<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
        fillPro();//加载全部省市区数据；
    });
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
    function elemDeleteBusniess(moduleName) {
        if (!$("#selectElemId").val()) {
            alertx("请选择要删除的商户信息");
            return false;
        } else {
            var elemId = $("#selectElemId").val();
            var tempHref = $("#btuElemDelete").attr("href") + elemId;
            if (confirmx("确定删除此" + moduleName + "？", tempHref)) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * 编辑
     * 
     * @returns {Boolean}
     */
    function elemEditBusniess(moduleName) {
        console.log(moduleName)
        if (!$("#selectElemId").val()) {
            if(moduleName==undefined||moduleName==''){
                alertx("请选择要编辑的行");
            }else{
                alertx("请选择要编辑的"+moduleName);
            }
            return false;
        } else {
            var elemId = $("#selectElemId").val();
            $("#btuElemEdit").attr("href", $("#btuElemEdit").attr("href") + elemId+"&prodType="+$("#selectElemId2").val());
            return true;
        }
    }
     
  // 已经被选中的行
     var selectedElem = null;
     function selectElem2(elem) {
         if (selectedElem) {
             // 撤销选中
             if (selectedElem === elem) {
                 $("#selectElemId").val("");
                 $(elem).attr("class", "listRowUnSelected");
                 selectedElem = null;
                 return;
             }
         }

         $("#selectElemId").val($(elem).children().find("#elemId").val());
         $("#selectElemId2").val($(elem).children().find("#HidProdType").val());
         $.each($(elem).parent().children(), function(index, value, array) {
             $(value).attr("class", "listRowUnSelected");
         });
         $(elem).attr("class", "listRowSelected");
         selectedElem = elem;
     }
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <span><a href="${ctx}/business/businessInfo">商户管理 </a>> 
            <a href="${ctx}/business/businessInfo">商户信息</a>
            </span>
        </li>
    </ul>
    <form:form id="searchForm" modelAttribute="businessInfo" action="${ctx}/business/businessInfo/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
        <input type="text" class="hide" id="hidProId"  value="${businessInfo.addrPro }">
        <input type="text" class="hide" id="hidCityId" value="${businessInfo.addrCity }">
        <input type="text" class="hide" id="hidVillageId" value="${businessInfo.villageInfo.id}">
        <ul class="ul-form">
            <li>
                <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${businessInfo.beginCreateDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> - <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${businessInfo.endCreateDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
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
            <li class="btns">
                <select id="addrVillage" name="villageInfo.id" style="width: 120px">
                    <option value="">全部楼盘</option>
                </select>
            </li>
            
            <li class="btns">
                <form:select path="businessCategorydict.id" class="input-medium">
                    <form:option value="" label="全部分类" />
                    <form:options items="${allCategory}" itemLabel="categoryName" itemValue="id" htmlEscape="false" />
                </form:select>
            </li>
            <li class="btns">
                <form:select path="useState" class="input-medium">
                    <form:option value="" label="全部状态" />
                    <form:options items="${fns:getDictList('useable')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </li>
            <li class="btns">
                <form:input path="businessName" placeholder="商户名称" htmlEscape="false" maxlength="64" class="input-small" />
            </li>
            <li class="btns">
                <a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>

    <div style="margin: 10px">
        <shiro:hasPermission name="business:businessInfo:add">
            <a class="btn btn-primary" href="${ctx}/business/businessInfo/edit"><i class="icon-plus icon-custom"></i> 添加</a> 
        </shiro:hasPermission>
        <shiro:hasPermission name="business:businessInfo:edit">
            <a id="btuElemEdit" href="${ctx}/business/businessInfo/edit?id=" onclick="return elemEditBusniess('商户')" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a> 
        </shiro:hasPermission>
        <shiro:hasPermission name="business:businessInfo:delete">
            <a id="btuElemDelete" href="${ctx}/business/businessInfo/delete?id=" onclick="return elemDeleteBusniess('商户信息')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="business:businessInfo:frozen">
            <a id="btuElemFozen" href="${ctx}/business/businessInfo/updateState?id=" class="btn btn-primary" onclick="return elemFrozen('useState', '商户', 'frozen', true)">冻结</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="business:businessInfo:unfrozen">
            <a id="btuElemFozen" href="${ctx}/business/businessInfo/updateState?id=" class="btn btn-primary" onclick="return elemFrozen('useState', '商户', 'unfrozen', true)">取消冻结</a>
        </shiro:hasPermission>
    </div>
    <input id="selectElemId" type="hidden" value="" />
    <input id="selectElemId2" type="hidden" value="" />
    <sys:message content="${message}" />
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr>
                <th>序号</th>
                <th>商家名称</th>
                <th>商家图片</th>
                <th>城市</th>
                <th>商家分类</th>
                 <th>联系人</th>
                <th>联系电话</th>
                <th>添加时间</th>
                <th>状态</th>
                <shiro:hasPermission name="business:businessInfo:edit">
                    <th>操作</th>
                </shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="businessInfo" varStatus="status">
                <tr onClick="selectElem2(this)">
                    <td> ${(status.index + 1) + ((page.pageNo - 1) * (page.pageSize))}
                    <input id="elemId" type="hidden" value="${businessInfo.id}" />
                    <input id="oldFrozen" type="hidden" value="${businessInfo.useState}"/>
                    <input id="HidProdType" type="hidden" value="${businessInfo.businessCategorydict.prodType}"/>
                    </td>
                    <td>${businessInfo.businessName}</td>
                    <td><img id="preview" src="${businessInfo.businessPic}" style="width:45px; height:45;" /></td>
                    <td>${businessInfo.addrCity}</td>
                    <td>${businessInfo.businessCategorydict.categoryName}</td>
                    <td>${businessInfo.contactPerson}</td>
                    <td>${businessInfo.phoneNum}</td>
                    <td><fmt:formatDate value="${businessInfo.createDate}" pattern="yyyy-MM-dd" /></td>
                    <td><c:if test="${businessInfo.useState==0}">
                             正常
                         </c:if> <c:if test="${businessInfo.useState==1}">
                             冻结
                         </c:if></td>
                    <td><shiro:hasPermission name="business:businessAccount:view">
                            <a href="${ctx}/business/businessAccount?businessinfoId=${businessInfo.id}&prodType=${businessInfo.businessCategorydict.prodType}">管理账户</a>
                        </shiro:hasPermission> 
                        <shiro:hasPermission name="business:businessInfo:editBank">
                            <a href="${ctx}/business/businessInfo/editBank?id=${businessInfo.id}">银行账号</a>
                        </shiro:hasPermission></td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>