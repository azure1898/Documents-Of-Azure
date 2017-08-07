<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>广告信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            fillPro();//加载全部省市区数据；
            console.log($("#hidVillageId").val());
            bindPosition($("#hidVillageId").val());
           // $("#positionId").val($("#hidPositionId").val())
            $("#villageLineId").trigger("change");
            
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        
        function bindPosition(obj){
            console.log($(obj).val())
            var product_line=$(obj).val();
            $.ajax({
                url:'${ctx }/adver/adverPosition/getPositonList',
                type : 'POST',
                data : {
                    moduleCode:product_line,
                 },
                dataType : 'json',
                success:function(data){
                    console.log(data);
                    var domRow='';
                    $("#positionId").empty();
                    for(var i=0;i<data.length;i++){
                        var name=data[i].positionName;
                        var id=data[i].id;
                       if(id==$("#hidPositionId").val()){
                            domRow+="<option  selected='selected' value='"+id+"'>"+name+"</option>";
                            $("#s2id_positionId").find(".select2-chosen").html(name);
                        }else{
                            domRow+="<option  value='"+id+"'>"+name+"</option>";    
                        } 
                    }
                    $("#positionId").append($(domRow));
                    $("#positionId").val($("#hidPositionId").val());
                }
            })
            
        }
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/adver/adverManage/">广告信息列表</a></li>
        <shiro:hasPermission name="adver:adverManage:edit"><li><a href="${ctx}/adver/adverManage/form">广告信息添加</a></li></shiro:hasPermission>
    </ul>
    <form:form id="searchForm" modelAttribute="adverManage" action="${ctx}/adver/adverManage/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input type="text" class="hide" id="hidProId"  value="${adverManage.villageInfo.addrPro }">
        <input type="text" class="hide" id="hidCityId" value="${adverManage.villageInfo.addrCity }">
        <input type="text" class="hide" id="hidVillageId" value="${adverManage.villageLineId }">
        <input type="text" class="hide" id="hidPositionId" value="${adverManage.positionId}">
        <ul class="ul-form">
            <li class="btns">
                <select id="addrpro" name="villageInfo.addrPro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrcity" name="villageInfo.addrCity" style="width: 120px" onchange="changeVillage()">
                    <option value="">全部城市</option>
                </select>
            </li>
            <li class="btns">
                <select id="addrVillage" name="villageInfo.id" style="width: 120px">
                    <option value="">全部楼盘</option>
                </select>
            </li>
            <li class="btns">
                <form:select path="villageLineId" class="input-medium" onchange="bindPosition(this)">
                    <form:option value="" label="产品线"/>
                    <form:options items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            
            <li class="btns">
                <select id="positionId" name="positionId" style="width: 120px" >
                    <option value="">广告位置</option>
                </select>
                
            </li>
            <li class="btns"><a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <ul style="margin:10px;">
      <li>
         <shiro:hasPermission name="adver:adverManage:add">
            <a href="${ctx}/adver/adverManage/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i> 添加</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="adver:adverManage:edit">
            <a id="btuElemEdit"  href="${ctx}/adver/adverManage/form?id=" class="btn btn-primary" onclick="return elemEdit()"><i class="icon-edit icon-custom"></i>编辑</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="adver:adverManage:delete">
            <a id="btuElemDelete" href="${ctx}/adver/adverManage/delete?id=" class="btn btn-primary" onclick="return elemDelete('位置信息')"><i class="icon-trash icon-custom"></i>删除</a>
         </shiro:hasPermission>
      </li>
     </ul>
    <input id="selectElemId" type="hidden" value=""/>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead>
            <tr>
                <th>序号</th>
                <th>广告图片</th>
                <th>广告名称</th>
                <th>广告位置</th>
                <th>省份</th>
                <th>城市</th>
                <th>投放楼盘</th>
                <th>状态</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="adverManage" varStatus="status">
            <tr onClick="selectElem(this)">
                <td>
                    ${status.count}
                    <input id="elemId" type="hidden" value="${adverManage.id}"/>
                </td>
                <td><img id="preview" src="${adverManage.adverPic}" style="width:45px; height:45;" /></td>
                <td>
                    ${adverManage.advertTitle}
                </td>
                <td>
                    ${adverManage.adverPosition.positionName}
                </td>
                <td>
                    ${fn:substringBefore(adverManage.villageInfo.addrPro, ",")}   
                </td>
                <td>
                   ${fn:substringBefore(adverManage.villageInfo.addrCity, ",")}   
                </td>
                <td>
                    ${adverManage.villageInfo.villageName}
                </td>
                <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
                <c:choose>
                    <c:when test="${nowDate-adverManage.starttime.getTime() < 0}">
                        <td class="am-hide-sm-only">未开始</td>
                    </c:when>
                    <c:when test="${nowDate-adverManage.starttime.getTime() > 0&&nowDate-adverManage.endTime.getTime() < 0}">
                        <td class="am-hide-sm-only">上线</td>
                    </c:when>
                    <c:when test="${nowDate-adverManage.endTime.getTime()> 0}">
                        <td class="am-hide-sm-only">下线</td>
                    </c:when>
                    <c:otherwise>
                        <td class="am-hide-sm-only">未知</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>