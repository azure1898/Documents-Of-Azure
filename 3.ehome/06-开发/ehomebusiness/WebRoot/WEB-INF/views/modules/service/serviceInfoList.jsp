<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务管理</title>
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
		/**
		 * 服务信息表排序
		 * sortItem ： 排序项目
		 * src ： 排序图标路径
		 */
		function dataTableSort(sortItem,src) {
			// 根据路径判断取得当前的排序图标
			var arr = src.split('/');
			var srcImgName = (arr[arr.length-1].split('.'))[0];
			// 根据当前图标路径判断升序还是降序
			if ("u765" == srcImgName) {
				if ("serialNumbers" == sortItem) {
					$("#sortItem").val("a.serial_numbers");
				} else {
					$("#sortItem").val("b.sort_order");
				}
				$("#sort").val("DESC");
			} else {
                if ("serialNumbers" == sortItem) {
                	$("#sortItem").val("a.serial_numbers");
                } else {
                	$("#sortItem").val("b.sort_order");
                }
                $("#sort").val("ASC");
			}
			$("#searchForm").submit();
		}
		/**
		 * 服务信息全选
		 */
		function allCheck(obj) {
			var checked = obj.checked;
			// 如果是非选中的状态清除隐藏域的服务ID信息
			if (!checked) {
				$("#checkedServiceId").val("");
			}
			var checkBoxs = $('input[name="itemCheckbox"]');
			for (var i = 0;i < checkBoxs.length; i++) {
				checkBoxs[i].checked=checked;
				if (checkBoxs[i].checked) {
		            var checkedServiceId = $("#checkedServiceId").val() + checkBoxs[i].value +",";
		            $("#checkedServiceId").val(checkedServiceId);
				}

			}
		}
		/**
         * 服务信息单选
         */
        function itemCheck(obj) {
        	$("#checkedServiceId").val("");
            var checkBoxs = $('input[name="itemCheckbox"]');
            for (var i = 0;i < checkBoxs.length; i++) {
            	// 如果被按下则拼接服务ID
            	if (checkBoxs[i].checked) {
                    var checkedServiceId = $("#checkedServiceId").val() + checkBoxs[i].value +",";
                    $("#checkedServiceId").val(checkedServiceId);
            	}
            }
		}
	    
        /**
         * 批量删除
         */
	    function muliDelete() {
	        var serviceIds = $("#checkedServiceId").val();
	        if (serviceIds == "") {
	            alertx("请选择要删除的服务");
	            return false;
	        } else {
	        	var href = "${ctx}/service/serviceInfo/muliDelete?serviceIds=" + serviceIds + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
	        	return confirmx('确定删除此服务？删除后该服务的相应已受理订单依然需要完成。', href);
	        }
	    }
        
	    /**
         * 批量上架
         */
        function muliGrounding(elem) {
            var serviceIds = $("#checkedServiceId").val();
            if (serviceIds == "") {
                alertx("请选择要上架的服务");
                return false;
            } else {
                var href = "${ctx}/service/serviceInfo/muliGrounding?serviceIds=" + serviceIds + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                resetTip();
                window.location.href=href;
            }
        }
        
        /**
         * 批量下架
         */
        function muliUndercarriage(elem) {
            var serviceIds = $("#checkedServiceId").val();
            if (serviceIds == "") {
           	 	alertx("请选择要下架的服务");
                return false;
            } else {
                var href = "${ctx}/service/serviceInfo/muliUndercarriage?serviceIds=" + serviceIds + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                resetTip();
                window.location.href=href;
            }
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/service/serviceInfo/">服务管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="serviceInfo" action="${ctx}/service/serviceInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li>
		    <form:hidden id="sortItem" path="sortItem" htmlEscape="false" />
		    <form:hidden id="sort" path="sort" htmlEscape="false" />
		    </li>
            <li class="btns">
            <a id="btnMuliDelete" href="" onclick="return muliDelete()" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
            </li>
            <li class="btns">
            <input id="btnMuliGrounding" class="btn btn-primary" type="button" onclick="muliGrounding()" value="上架"/>
            </li>
            <li class="btns">
            <input id="btnMuliUndercarriage" class="btn btn-primary" type="button" onclick="muliUndercarriage()" value="下架"/>
            </li>
		    <li>
			<li class="btns" style="float:right;padding-right:10px;">
                <form:select path="sortInfoId" class="input-medium">
                    <form:option value="" label="服务分类"/>
                    <form:options items="${sortInfoList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                </form:select>
                <form:select path="recommend" class="input-medium">
                    <form:option value="" label="推荐状态"/>
                    <form:options items="${fns:getDictList('Quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <form:select path="state" class="input-medium">
                    <form:option value="" label="服务状态"/>
                    <form:options items="${fns:getDictList('service_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			<a id="btnSubmit" onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a> 
            <input id="btnAddService" class="btn btn-success" type="button" onclick="window.location.href='${ctx}/service/serviceInfo/form?sortItem=${serviceInfo.sortItem}&sort=${serviceInfo.sort}'" value="新增服务"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<c:choose>
  <c:when test="${msgType != null and msgType != ''}">
      <sys:message content="${message}" type="${msgType}"/>
  </c:when>
  <c:otherwise>
      <sys:message content="${message}"/>
  </c:otherwise>
 </c:choose>
	<input type="hidden" id="checkedServiceId"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
			    <th style="width : 5%"><input type="checkbox" id="allCheck" onclick="allCheck(this)">全选</th>
			    <th style="width : 5%">编号
			    </th>
				<th style="width : 20%">服务名称</th>
				<th style="width : 10%">分类
                <c:choose> 
                    <c:when test="${serviceInfo.sortItem == 'b.sort_order' && serviceInfo.sort == 'DESC'}">
                        <img id="sortInfoSortImg" src="${ctxStatic}/images/u766.png" onclick="dataTableSort('sortInfo',this.src)">
                    </c:when>
                    <c:otherwise>
                        <img id="sortInfoSortImg" src="${ctxStatic}/images/u765.png" onclick="dataTableSort('sortInfo',this.src)">
                    </c:otherwise>
                </c:choose>
                </th>
				<th style="width : 10%">价格</th>
				<th style="width : 10%">库存</th>
				<th style="width : 20%">图片</th>
				<th style="width : 5%">推荐</th>
				<th style="width : 5%">状态</th>
				<th style="width : 10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="serviceInfoTemp" varStatus="status">
			<tr>
			    <td>
			    <input type="checkbox" name="itemCheckbox" onclick="itemCheck(this)" value="${serviceInfoTemp.id}">
			    </td>
                <td>${status.count}
                </td>
				<td>
					${serviceInfoTemp.name}
                </td>
				<td>
				    <c:if test="${serviceInfoTemp.sortInfoName == null || serviceInfoTemp.sortInfoName == ''}">
				    -
				    </c:if>
				    ${serviceInfoTemp.sortInfoName}
				</td>
				<td>
				<c:choose> 
				    <c:when test="${serviceInfoTemp.benefitPrice != null && serviceInfoTemp.benefitPrice!= ''}">
					<s><font color="#CCCCCC">${fns:doubleFormat(serviceInfoTemp.basePrice)}</font></s><br/>
					${fns:doubleFormat(serviceInfoTemp.benefitPrice)}
					</c:when>
					<c:otherwise>
					${fns:doubleFormat(serviceInfoTemp.basePrice)}
					</c:otherwise>
			    </c:choose>
				</td>
				<td>
                <c:choose> 
                    <c:when test="${serviceInfoTemp.stock != null && serviceInfoTemp.stock!= ''}">
                    ${serviceInfoTemp.stock}
                    </c:when>
                    <c:otherwise>
                    0
                    </c:otherwise>
                </c:choose>
				</td>
                <td>
           <c:forEach items="${serviceInfoTemp.imageUrls}" var="imgUrl">
                    <img src="${imgUrl}"/>
           </c:forEach>
                </td>
				<td>
					${fns:getDictLabel(serviceInfoTemp.recommend, 'Quota', '否')}
				</td>
				<td>
					${fns:getDictLabel(serviceInfoTemp.state, 'service_state', '下架')}
				</td>
				<td>
    				<a href="${ctx}/service/serviceInfo/form?id=${serviceInfoTemp.id}&sortItem=${serviceInfo.sortItem}&sort=${serviceInfo.sort}">编辑</a>
					<a href="${ctx}/service/serviceInfo/delete?id=${serviceInfoTemp.id}&sortItem=${serviceInfo.sortItem}&sort=${serviceInfo.sort}" onclick="return confirmx('确定删除此服务？删除后该服务的相应已受理订单依然需要完成。', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>