<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>楼盘信息管理</title>
	<meta name="decorator" content="default"/>
		<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			fillPro();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        
		//以select的形式展示楼盘列表
		function changeVillageInfoSelect(){
			$.ajax({
				type: "POST",
				url: ctx+"/village/villageInfo/findListAllState",
				data: {
					provinceId: $("#addrpro").val(),
					cityId: $("#addrcity").val()
				},
				dataType: "JSON",
				success: function(data){
					var hidVillageId=$("#hidVillageId").val();
					$("#addrVillageInfo").empty();
					var option = "<option value=''>全部楼盘</option>";
					$.each(data,function(indx,item){
						option += "<option value='"+item.id+"'>"+item.villageName+"</option>";
					})
					$("#addrVillageInfo").append(option);
					$("#addrVillageInfo").val(hidVillageId).trigger("change");//修改初始时，带值选中
				}
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/village/villageInfo/">楼盘信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="villageInfo" action="${ctx}/village/villageInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<input type="text" class="hide" id="hidProId"  value="${proId}">
		<input type="text" class="hide" id="hidCityId" value="${cityId}">
		<input type="text" class="hide" id="hidAreaId" value="">
		<input type="text" class="hide" id="hidVillageId" value="${villageId}">
		<ul class="ul-form">
			<li class="btns">
				<form:select path="propertyCompanyId" class="input-small">
					<form:option value="" label="全部物业"/>
					<form:options items="${propertyCompanyMap}" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
		    <select id="addrpro" name="addrPro" style="width: 120px" onchange="changeCity()">
		     <option value="">全部省份</option>
		    </select>
		   </li>
		   <li class="btns">
		    <select id="addrcity" name="addrCity" style="width: 120px" onchange="changeVillageInfoSelect()">
		     <option value="">全部城市</option>
		    </select>
		   </li>
		   <li class="btns" style="display:none;">
		    <select id="addrarea" name="addrArea" style="width: 120px;display:none;">
		     <option value="">全部区域</option>
		    </select>
		   </li>
		   <li class="btns">
		    <select id="addrVillageInfo" name="id" style="width: 120px">
		     <option value="">全部楼盘</option>
		    </select>
		   </li>
		   <li class="btns">
				<form:select path="state" class="input-small">
					<form:option value="" label="全部状态"/>
					<form:options items="${fns:getDictList('use_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:input path="villageName" placeholder="楼盘名称" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="village:villageInfo:edit">
         	<a id="btuElemEdit" href="${ctx}/village/villageInfo/form?id=" class="btn btn-primary" onclick="return elemEdit()"><i class="icon-edit icon-custom"></i>编辑</a>
         	<a id="btuElemProductLine" href="${ctx}/village/villageInfo/formProductLine?id=" class="btn btn-primary" onclick="return elemEditProductLine()">关联产品线</a>
         	<a id="btuElemFozen" href="${ctx}/village/villageInfo/updateState?id=" class="btn btn-primary" onclick="return elemFrozen('state', '', 'frozen', true)">冻结</a>
     		<a id="btuElemFozen" href="${ctx}/village/villageInfo/updateState?id=" class="btn btn-primary" onclick="return elemFrozen('state', '', 'unFrozen', true)">取消冻结</a>
         </shiro:hasPermission>
      </li>
 	 </ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>楼盘名称</th>
				<th>城市</th>
				<th>所属开发商</th>
				<th>所属物业</th>
				<th>产品线</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="villageInfo" varStatus="status">
			<tr onClick="selectElem(this)">
				<td>
					${(page.pageNo - 1) * page.pageSize + status.count}
					<input id="elemId" type="hidden" value="${villageInfo.id}"/>
					<input id="oldFrozen" type="hidden" value="${villageInfo.state}" />
				</td>
				<td>
					${villageInfo.villageName}
				</td>
				<td>
					${villageInfo.addrCityName}
				</td>
				<td>
					${villageInfo.companyInfoName}
				</td>
				<td>
					${villageInfo.propertyCompanyName}
				</td>
				<td>
					<c:forEach items="${villageInfo.villageLine}" var="villageLine">
						${fns:getDictLabel(villageLine.productLine, 'product_line', '')}
						<br/>
					</c:forEach>
				</td>
				<td>
					${fns:getDictLabel(villageInfo.state, 'use_state', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>