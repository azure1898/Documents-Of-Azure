<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
	<title>话题管理</title>
	<meta name="decorator" content="default"/>
		<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			fillPro();
			
			$('.upbtn').on('click',function(){
		        $(this).each(function(){
		            var $tr = $(this).parents("tr");
		            if($tr.index() != 0){
		                $tr.prev().before($tr);
		            }
		        });
		    });

			//下移
		    $('.downbtn').on('click',function(){
		        var trLength = '${listSize}'; 
		        $(this).each(function(){
		            var $tr = $(this).parents("tr"); 		            
		            if ($tr.index() != trLength) { 
		                $tr.next().after($tr);
		            }
		        });
		    });
			
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
					console.log(option);
					$("#addrVillageInfo").append(option);
					$("#addrVillageInfo").val(hidVillageId).trigger("change");//修改初始时，带值选中
				}
			})
		}
		
		//保存排序的点击事件
		function elemSort(){
			var groupId= '';
			var ordernum= '';
			var i=1;
			$("input[name='groupId']").each(function(){
				groupId+=','+this.value;
				ordernum+=','+i;
				i++;
		   	});
			
			$.ajax({
				type : 'POST',
				url : '${ctx}/subject/socialSubject/saveOrderNum',
				data : {
					groupId:groupId,
					ordernum:ordernum
				 },
				dataType : 'json',
				success : function(data) {					
					if(data == "1"){
						$.jBox.tip('保存顺序成功！');
						//延迟一秒再跳转
		        		setTimeout(function() {
		        			window.location.href="${ctx}/subject/socialSubject/list";
		        	    }, 1000);
		    			
					}else{
						$.jBox.tip('保存顺序失败！');
					}					
			    }
			})
		}
		
		function isRecommend() {
			if (!$("#selectElemId").val()) {
				alertx("请选择要编辑的话题");
				return false;
			} else {
				
				var editFlag = $(selectedElem).children().find("#isrecommend").val();
				//团购活动：待开始之外的状态
				if(editFlag == '1'){
					alertx("该话题已推荐不能编辑");
					return false;
				}
				var elemId = $("#selectElemId").val();
				$.ajax({
					type : 'POST',
					url : '${ctx}/subject/socialSubject/updateRecommend',
					data : {
						elemId:elemId,
						isrecommend:"1"
					 },
					dataType : 'json',
					success : function(data) {
						if(data == "1"){
							$.jBox.tip('推荐话题成功！');
							//延迟一秒再跳转
			        		setTimeout(function() {
			        			window.location.href="${ctx}/subject/socialSubject/list";
			        	    }, 1000);
			    			
						}else{
							$.jBox.tip('推荐话题失败！');
						}					
				    }
				});
			}
		}
		
		function notRecommend() {
			if (!$("#selectElemId").val()) {
				alertx("请选择要编辑的话题");
				return false;
			} else {
				
				var editFlag = $(selectedElem).children().find("#isrecommend").val();
				//团购活动：待开始之外的状态
				if(editFlag == '0'){
					alertx("该话题已设置为不推荐不能编辑");
					return false;
				}
				var elemId = $("#selectElemId").val();
				$.ajax({
					type : 'POST',
					url : '${ctx}/subject/socialSubject/updateRecommend',
					data : {
						elemId:elemId,
						isrecommend:"0"
					 },
					dataType : 'json',
					success : function(data) {
						if(data == "1"){
							$.jBox.tip('取消推荐话题成功！');
							//延迟一秒再跳转
			        		setTimeout(function() {
			        			window.location.href="${ctx}/subject/socialSubject/list";
			        	    }, 1000);
			    			
						}else{
							$.jBox.tip('取消推荐话题失败！');
						}					
				    }
				});
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/subject/socialSubject/">话题信息列表</a></li>
<%-- 		<shiro:hasPermission name="subject:socialSubject:edit"><li><a href="${ctx}/subject/socialSubject/form">话题添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="socialSubject" action="${ctx}/subject/socialSubject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="listSize" type="hidden" value="${listSize}"/>
		<input type="text" class="hide" id="hidProId"  value="${proId }">
		<input type="text" class="hide" id="hidCityId" value="${cityId }">
		<input type="text" class="hide" id="hidAreaId" value="">
		<input type="text" class="hide" id="hidVillageId" value="${villageId }">
		<ul class="ul-form">
			<li>
				<input id="beginCreatetime" name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${socialSubject.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input id="endCreatetime" name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${socialSubject.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
					<option value="">全部楼盘</option>
				</select>
			</li>
			<li class="btns">
				<form:select path="isrecommend" class="input-small">
					<form:option value="" label="是否推荐"/>
					<form:options items="${fns:getDictList('Quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><span>&nbsp;</span>
				<form:input path="subname" htmlEscape="false" maxlength="200" class="input-medium" placeholder="话题名称"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul style="margin:10px;">
		<li style="list-style-type:none;">
			<shiro:hasPermission name="subject:socialSubject:edit">
				<a id="btuElemAdd" href="${ctx}/subject/socialSubject/form?id=" class="btn btn-primary"><i class="icon-plus icon-custom"></i>添加</a>
				<a id="btuElemEdit" href="${ctx}/subject/socialSubject/form?id=" class="btn btn-primary" onclick="return elemEdit()"><i class="icon-edit icon-custom"></i>编辑</a>
				<a id="btuElemDelete" href="${ctx}/subject/socialSubject/delete?id=" class="btn btn-primary" onclick="return elemDelete()"><i class="icon-trash icon-custom"></i>删除</a>
				<a id="isRecommend" class="btn btn-primary" onclick="isRecommend()">推荐</a>
				<a id="notRecommend" class="btn btn-primary" onclick="notRecommend()">取消推荐</a>
				<a id="btuElemSort" href="javacript:void(0);" onclick="elemSort()" class="btn btn-primary">保存顺序</a>
			</shiro:hasPermission>
		</li>
 	</ul>
 	<input id="selectElemId" type="hidden" value=""/>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>话题名称</th>
				<th>楼盘名称</th>
				<th>是否推荐</th>
				<th>创建人</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="socialSubject" varStatus="status">
			<tr onClick="selectElem(this)">
				<td>
					${status.count}
					<input type="text" id="elemId" value="${socialSubject.id }" style="display:none"/>
				</td>
				<td>
					${socialSubject.subname}
				</td>
				<td>
					${socialSubject.villageInfoName}
				</td>
				<td>
					${fns:getDictLabel(socialSubject.isrecommend, 'Quota', '')}
					<input type="text" id="isrecommend" value="${socialSubject.isrecommend }" style="display:none" />
				</td>
				<td>
					${socialSubject.creatername}
				</td>
				<td>
					<fmt:formatDate value="${socialSubject.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="javacript:void(0);" class="upbtn">上移</a>
    				<a href="javacript:void(0);" class="downbtn">下移</a>	
    				<input type="text" id="groupId" name="groupId" value="${socialSubject.id }" style="display:none"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>