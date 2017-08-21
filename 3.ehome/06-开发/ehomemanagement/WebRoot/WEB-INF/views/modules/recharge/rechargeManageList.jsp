<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			fillPro();//加载全部省市区数据；
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function rechargeAdd(){
			var villageInfoId = $("#addrVillage").val();
			$("#btuElemAdd").attr("href",$("#btuElemAdd").attr("href") + villageInfoId);
			return true;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recharge/rechargeManage/">充值计划列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="rechargeManage" action="${ctx}/recharge/rechargeManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<input type="text" class="hide" id="hidProId"  value="${selecttedAddrPro}">
		<input type="text" class="hide" id="hidCityId"  value="${selecttedAddrCity}">
		<input type="text" class="hide" id="hidAreaId"  value="">
		<input type="text" class="hide" id="hidVillageId" value="${selecttedVillageInfoId}">
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
			<li class="btns" style="display:none;">
				<select id="addrarea" name="villageInfo.addrArea" style="width: 120px;display:none;">
					<option value="">全部区域</option>
				</select>
			</li>
			<li class="btns">
				<select id="addrVillage" name="villageInfo.id" style="width: 120px">
					<option value="">全部楼盘</option>
				</select>
			</li>
			<li class="btns">
				<input type="text" id="villageName" name="villageInfo.villageName" placeholder="请输入楼盘名" maxlength="128" class="input-medium" value="${rechargeManage.villageInfo.villageName}"/>
			</li>
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>		
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="recharge:rechargeManage:edit">
         	<a id="btuElemAdd" href="${ctx}/recharge/rechargeManage/form?option=add&villageInfoId=" class="btn btn-primary" onclick="return rechargeAdd()"><i class="icon-plus icon-custom"></i> 添加</a>
         	<a id="btuElemEdit" href="${ctx}/recharge/rechargeManage/form?option=edit&villageInfoId=" class="btn btn-primary" onclick="return elemEdit('充值计划')"><i class="icon-edit icon-custom"></i>编辑</a>
         	<a id="btuElemDelete" href="${ctx}/recharge/rechargeManage/delete?villageInfo.Id=" class="btn btn-primary" onclick="return elemDelete('充值计划')"><i class="icon-trash icon-custom"></i>删除</a>
         </shiro:hasPermission>
      </li>
  	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width : 5%">序号</th>
				<th style="width : 10%">楼盘名称</th>
				<th style="width : 35%">充值面值</th>
				<th style="width : 25%">赠送金额</th>
				<th style="width : 15%">发布时间</th>
				<shiro:hasPermission name="recharge:walletDetail:view"><th  style="width : 10%">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rechargePlan" varStatus="status">
			<tr onClick="selectElem(this)">
				<td>
					${(page.pageNo - 1) * page.pageSize + status.count}
					<input id="elemId" type="hidden" value="${rechargePlan.villageInfo.id}"/>
				</td>
				<td>
					${rechargePlan.villageInfo.villageName}
				</td>
				<td>
					${rechargePlan.rechargeMoney}
				</td>
				<td>
					<c:forEach items="${rechargePlan.giveMoney}" var="giveMoney" varStatus="status">
						${giveMoney}
						<br/>
					</c:forEach>
				</td>
				<td>
					<fmt:formatDate value="${rechargePlan.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="recharge:walletDetail:view"><td>
					<a href="${ctx}/recharge/walletDetail/list?villageInfoId=${rechargePlan.villageInfo.id}">查看充值记录</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>