<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			if ($("#endRegistDate").val() < $("#beginRegistDate").val()) {
    			alertx("您输入的开始时间在结束时间之后，请重新输入！");
			} else {
				$("#searchForm").submit();
			}
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/account/account/">用户列表</a></li>
<%-- 		<shiro:hasPermission name="account:account:edit"><li><a href="${ctx}/account/account/form">用户添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="account" action="${ctx}/account/account/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<ul class="ul-form">
		    <li>
				<input id="beginRegistDate" name="beginRegistDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${account.beginRegistDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input id="endRegistDate" name="endRegistDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${account.endRegistDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
				<form:select path="terminalSource" class="input-mini">
					<form:option value="" label="终端来源"/>
					<form:options items="${fns:getDictList('terminal_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:select path="certifyState" class="input-mini">
					<form:option value="" label="认证状态"/>
					<form:options items="${fns:getDictList('certify_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:select path="useState" class="input-mini">
					<form:option value="" label="使用状态"/>
					<form:options items="${fns:getDictList('use_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:select path="bindBraceletFlag" class="input-small">
					<form:option value="" label="手环绑定状态"/>
					<form:options items="${fns:getDictList('bind_bracelet_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:select path="faceRecognitionFlag" class="input-medium">
					<form:option value="" label="人脸识别采集状态"/>
					<form:options items="${fns:getDictList('face_recognition_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<form:input path="nickname" placeholder="用户名" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
				<a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a>		
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	 <ul style="margin:10px;">
      <li style="list-style-type:none;">
         <shiro:hasPermission name="account:account:frozen">
         	<a id="btuElemFozen" href="${ctx}/account/account/updateUseState?id=" class="btn btn-primary" onclick="return elemFrozen('useState', '用户', 'frozen', true)">冻结</a>
         </shiro:hasPermission>
         <shiro:hasPermission name="account:account:unfrozen">
         	<a id="btuElemFozen" href="${ctx}/account/account/updateUseState?id=" class="btn btn-primary" onclick="return elemFrozen('useState', '用户', 'unFrozen', true)">取消冻结</a>
         </shiro:hasPermission>
      </li>
    </ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>账号/手机号</th>
				<th>姓名/昵称</th>
				<th>终端来源</th>
				<th>认证状态</th>
				<th>注册时间</th>
				<th>是否配置手环</th>
				<th>已绑定手环信息</th>
				<th>人脸识别采集状态</th>
				<th>使用状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="account" varStatus="status">
			<tr onClick="selectElem(this)">
			    <td>
					${status.count}
					<input id="oldFrozen" type="hidden" value="${account.useState}" />
					<input id="elemId" type="hidden" value="${account.id}"/>
				</td>
				<td>
					${account.phoneNum}
				</td>
				<td>
					${account.nickname}
				</td>
				<td>
					${fns:getDictLabel(account.terminalSource, 'terminal_source', '')}
				</td>
				<td>
					${fns:getDictLabel(account.certifyState, 'certify_state', '')}
				</td>
				<td>
					<fmt:formatDate value="${account.registDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(account.bindBraceletFlag, 'bind_bracelet_flag', '')}
				</td>
				<td>
					${account.braceletName}
				</td>
				<td>
					${fns:getDictLabel(account.faceRecognitionFlag, 'face_recognition_flag', '')}
				</td>
				<td>
					${fns:getDictLabel(account.useState, 'use_state', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>