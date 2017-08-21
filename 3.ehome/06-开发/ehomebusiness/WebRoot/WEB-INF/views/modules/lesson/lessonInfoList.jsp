<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程培训管理</title>
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
		 * 课程/培训信息全选
		 */
		function allCheck(obj) {
			var checked = obj.checked;
			// 如果是非选中的状态清除隐藏域的课程/培训ID信息
			if (!checked) {
				$("#checkedLessonId").val("");
			}
			var checkBoxs = $('input[name="itemCheckbox"]');
			for (var i = 0;i < checkBoxs.length; i++) {
				checkBoxs[i].checked=checked;
				if (checkBoxs[i].checked) {
		            var checkedLessonId = $("#checkedLessonId").val() + checkBoxs[i].value +",";
		            $("#checkedLessonId").val(checkedLessonId);
				}
			}
		}
		
	    /**
         * 课程/培训信息单选
         */
        function itemCheck(obj) {
        	$("#checkedLessonId").val("");
            var checkBoxs = $('input[name="itemCheckbox"]');
            for (var i = 0;i < checkBoxs.length; i++) {
            	// 如果被按下则拼接课程/培训ID
            	if (checkBoxs[i].checked) {
                    var checkedLessonId = $("#checkedLessonId").val() + checkBoxs[i].value +",";
                    $("#checkedLessonId").val(checkedLessonId);
            	}
            }
		}
	    
        /**
         * 批量删除
         */
	    function muliDelete() {
	        var lessonid = $("#checkedLessonId").val();
	        if (lessonid == "") {
	            alertx("请选择要删除的课程/培训");
	            return;
	        } else {
	        	var href = "${ctx}/lesson/lessonInfo/muliDelete?lessonIds=" + lessonid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
	        	return confirmx('确认删除此课程/培训？删除后该课程/培训的相应已受理订单依然需要完成。', href);
	        }
	    }
        
        /**
         * 批量上架
         */
        function muliGrounding() {
            var lessonid = $("#checkedLessonId").val();
            if (lessonid == "") {
            	alertx("请选择要上架的课程/培训");
                return;
            } else {
                var href = "${ctx}/lesson/lessonInfo/muliGrounding?lessonIds=" + lessonid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                resetTip();
                window.location.href=href;
            }
        }
        
        /**
         * 批量下架
         */
        function muliUndercarriage() {
            var lessonid = $("#checkedLessonId").val();
            if (lessonid == "") {
            	alertx("请选择要下架的课程/培训");
                return;
            } else {
                var href = "${ctx}/lesson/lessonInfo/muliUndercarriage?lessonIds=" + lessonid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                resetTip();
                window.location.href=href;
            }
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="lessonInfo" action="${ctx}/lesson/lessonInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="checkedLessonId" type="hidden"/>
		<ul class="ul-form">
		    <li>
		    <form:hidden id="sortItem" path="sortItem" htmlEscape="false" />
		    <form:hidden id="sort" path="sort" htmlEscape="false" />
		    </li>
            <li class="btns"><input id="btnMuliDelete" class="commonbtn" style="width:60px" type="button" value="删除" onclick="muliDelete()"/></li>
            <li class="btns"><input id="btnMuliGrounding" class="commonbtn" style="width:60px" type="button" onclick="muliGrounding()" value="上架"/></li>
            <li class="btns"><input id="btnMuliUndercarriage" class="commonbtn" style="width:60px" type="button" onclick="muliUndercarriage()" value="下架"/></li>
		    <li>
			<li class="btns" style="float:right;padding-right:10px;">
                <form:select path="recommend" class="input-medium">
                    <form:option value="" label="推荐状态"/>
                    <form:options items="${fns:getDictList('Quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <form:select path="state" class="input-medium">
                    <form:option value="" label="课程状态"/>
                    <form:options items="${fns:getDictList('lesson_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
	            <input id="btnSubmit" onclick="return page();" class="commonbtn" type="submit" value="查询" style="width:60px"/>
	            <input id="btnAddLesson" class="commonbtn" style="width:100px;" type="button" onclick="window.location.href='${ctx}/lesson/lessonInfo/form?sortItem=${lessonInfo.sortItem}&sort=${lessonInfo.sort}'" value="新增课程培训"/>
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
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98%">
		<thead>
			<tr>
			    <th style="width : 5%"><input type="checkbox" id="allCheck" onclick="allCheck(this)">全选</th>
			    <th style="width : 5%">编号</th>
				<th style="width : 25%">名称</th>
				<th style="width : 15%">上课时间</th>
				<th style="width : 10%">价格</th>
				<th style="width : 10%">限制人数</th>
				<th style="width : 10%">图片</th>
				<th style="width : 5%">推荐</th>
				<th style="width : 5%">状态</th>
				<th style="width : 10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lessonInfoTemp" varStatus="status">
			<tr>
			    <td>
			    <input type="checkbox" name="itemCheckbox" onclick="itemCheck(this)" value="${lessonInfoTemp.id}">
			    </td>
                <td>${(page.pageNo - 1) * page.pageSize + status.count}
                </td>
				<td>
					${lessonInfoTemp.name}
                </td>
				<td>
				    <span><fmt:formatDate value="${lessonInfoTemp.startTime}" pattern="yyyy-MM-dd"/>~</span>
				    <br/>
				    <span><fmt:formatDate value="${lessonInfoTemp.endTime}" pattern="yyyy-MM-dd"/></span>
				</td>
				<td>
				<c:choose> 
				    <c:when test="${lessonInfoTemp.benefitPrice != null && lessonInfoTemp.benefitPrice!= ''}">
					<s><font color="#CCCCCC">${fns:doubleFormat(lessonInfoTemp.basePrice)}</font></s><br/>
						${fns:doubleFormat(lessonInfoTemp.benefitPrice)}
					</c:when>
					<c:otherwise>
						${fns:doubleFormat(lessonInfoTemp.basePrice)}
					</c:otherwise>
			    </c:choose>
				</td>
				<td>
                <c:choose> 
                    <c:when test="${lessonInfoTemp.peopleLimit != null && lessonInfoTemp.peopleLimit!= ''}">
                    	${lessonInfoTemp.peopleLimit}
                    </c:when>
                    <c:otherwise>
                    	0
                    </c:otherwise>
                </c:choose>
				</td>
                <td>
		           <c:forEach items="${lessonInfoTemp.imageUrls}" var="imgUrl">
		               <img src="${imgUrl}" style="width:53px;height:40px;"/>
		           </c:forEach>
                </td>
				<td>
					${fns:getDictLabel(lessonInfoTemp.recommend, 'Quota', '否')}
				</td>
				<td>
					${fns:getDictLabel(lessonInfoTemp.state, 'lesson_state', '下架')}
				</td>
				<td>
					<input type="button" class="commonsmallbtn" value="编辑" style="width:40px;" onclick="window.location.href='${ctx}/lesson/lessonInfo/form?id=${lessonInfoTemp.id}&sortItem=${lessonInfo.sortItem}&sort=${lessonInfo.sort}'">
				    <div style="margin:0 auto;height:3px;"></div>
				    <input type="button" class="commonsmallbtn" value="删除" style="width:40px;" onclick="return confirmx('确认删除此课程/培训？删除后该课程/培训的相应已受理订单依然需要完成。', '${ctx}/lesson/lessonInfo/delete?id=${lessonInfoTemp.id}&sortItem=${lessonInfo.sortItem}&sort=${lessonInfo.sort}')">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>