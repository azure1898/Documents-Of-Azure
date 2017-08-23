<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			fillPro(); // 加载全部省市区数据
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li> 
           <span class="common-breadcrumb">运营管理&nbsp;>&nbsp;<a href="${ctx}/operation/groupPurchase/">团购管理 </a></span>
        </li>
    </ul>
	<form:form id="searchForm" modelAttribute="groupPurchase" action="${ctx}/operation/groupPurchase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectElemId" type="hidden" value=""/>
		<input id="listSize" type="hidden" value="${listSize}"/>
		<input type="text" class="hide" id="hidProId" value=""/> 
		<input type="text" class="hide" id="hidCityId" value=""/>
	    <input type="text" class="hide" id="hidAreaId" value=""/>
	    <input type="text" class="hide" id="hidVillageId" value="${groupPurchase.villageInfoId}"/>		
		<ul class="ul-form">
			<li class="btns">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${groupPurchase.startTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${groupPurchase.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			 <li class="btns">
			    <select id="addrpro" name="addrPro" style="width: 120px; display:none;" onchange="changeCity()">
			     <option value="">全部省份</option>
			    </select>
			 </li>
			 <li class="btns">
			    <select id="addrcity" name="addrCity" style="width: 120px; display:none;" onchange="changeVillage()">
			     <option value="">全部城市</option>
			    </select>
			 </li>
			 <li class="btns">
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
				<select id="businessinfoId" name="businessinfoId" class="input-medium">
						<c:forEach items="${groupBusinessInfoList}" var="business">
							<option value="${business.businessinfoId}" ${groupPurchase.businessinfoId eq business.businessinfoId ? 'selected="selected"' : ''}>${business.businessName}</option>
						</c:forEach>
				</select>
			 </li>
			 <li class="btns">
				<form:select path="groupPurcState" class="input-mini">
					<form:option value="1" label="活动中"/>
					<form:option value="0" label="待开始"/>
					<form:option value="2" label="已结束"/>
					<form:option value="3" label="已撤消"/>
				</form:select>	
			 </li>
			 <li class="btns">
				<form:input path="groupPurcName" placeholder="请输入团购名、商家名" htmlEscape="false" maxlength="100" class="input-medium"/>
			 </li>
			 <li class="btns"><input id="btnSubmit" class="btn btn-success" type="submit" value="查询"/></li>
			 <li class="clearfix"></li>
		</ul>
	</form:form>
	<div style="margin: 10px">
		<shiro:hasPermission name="operation:groupPurchase:add">
			<a href="${ctx}/operation/groupPurchase/form" class="btn btn-primary"><i class="icon-plus icon-custom"></i>添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="operation:groupPurchase:edit">
			<a id="btuElemEdit" href="${ctx}/operation/groupPurchase/form?id=" onclick="return elemEdit()" class="btn btn-primary"><i class="icon-edit icon-custom"></i> 编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="operation:groupPurchase:delete">
			<a id="btuElemDelete" href="${ctx}/operation/groupPurchase/delete?id=" onclick="return elemDelete('团购活动')" class="btn btn-primary"><i class="icon-trash icon-custom"></i> 删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="operation:groupPurchase:sort">
			<a id="btuElemSort" href="#" onclick="elemSort()" class="btn btn-primary">保存排序</a>
		</shiro:hasPermission>
		
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>商家名称</th>
				<th>团购名称</th>
				<th>团购图片</th>
				<th>市场价（元）</th>
				<th>团购价（元）</th>
				<th>总库存量</th>
				<th>团购开始时间</th>
				<th>团购结束时间</th>
				<th>团购状态</th>
				<c:if test="${groupPurchase.groupPurcState == 1}">
					<shiro:hasPermission name="operation:groupPurchase:edit">
					<th>操作</th>
					</shiro:hasPermission>
				</c:if>
				<th class="hide"></th>
				<th class="hide"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${groupList}" var="groupPurchase" varStatus="status">
			<tr onClick="selectElem(this)">
			    <td>
			        ${status.count}
			        <input id="elemId" type="hidden" value="${groupPurchase.id}" />
			    </td>
				<td>
					${groupPurchase.businessName}
				</td>
				<td><a href="${ctx}/operation/groupPurchase/detail?id=${groupPurchase.id}">
					${groupPurchase.groupPurcName}</a>
				</td>
				<td>
					<img id="preview" src="${groupPurchase.groupPurcPic}" style="width:45px;height:45px;"/>
				</td>
				<td>
					${groupPurchase.marketMoney}
				</td>
				<td>
					${groupPurchase.groupPurcMoney}
				</td>
				<td>
					${groupPurchase.stockNum}
				</td>
				<td>
					<fmt:formatDate value="${groupPurchase.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${groupPurchase.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(groupPurchase.groupPurcState, 'grouppurcstate', '')}
					<input id="stateHidden" type="hidden" value="${groupPurchase.groupPurcState}" />
				</td>				
				<shiro:hasPermission name="operation:groupPurchase:offLine">
					<c:if test="${groupPurchase.groupPurcState == 1}">
						<td>
		    				<a href="${ctx}/operation/groupPurchase/offLine?id=${groupPurchase.id}" onclick="return confirmx('活动还没有结束，确认下线吗？下线的团购无法再进行上线', this.href)">下线</a>
				    		<a href="#" class="up" id="up${status.count}"><i class="icon-arrow-up"></i></a>
				    		<label id="upHid${status.count}" class="upHid" style="width:15px;display:none;"></label>
				    		<label id="downHid${status.count}" class="downHid" style="width:15px;display:none;"></label>
		                    <a href="#" class="down" id="down${status.count}"><i class="icon-arrow-down"></i></a>    				
						</td>
					</c:if>				
				</shiro:hasPermission>
				<td class="hide">
					<input id="groupId" name="groupId" type="hidden" value="${groupPurchase.id}"/>
				</td>
				<td class="hide">
					<input id="editFlag" name="editFlag" type="hidden" value="${groupPurchase.editFlag}"/>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!--<div class="pagination">${page}</div>  -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#up1").css("display","none");
			$("#upHid1").css("display","");
			$("#down"+'${listSize}').css("display","none");
			$("#downHid"+ '${listSize}').css("display","");
			
			//上移
			$('.up').click(function(){
				var trLength = '${listSize}'; 
		        $(this).each(function(){
		            var $tr = $(this).parents("tr");
		            if($tr.index() == trLength-1){
		            	var $aDown=$tr.find(".down");
		            	var $aDownHid=$tr.find(".downHid");
		            	var $aPrevDown=$tr.prev().find(".down");
		            	var $aPrevDownHid=$tr.prev().find(".downHid");
		            	$("#"+$aDown.attr("id")).css("display","");
		            	$("#"+$aDownHid.attr("id")).css("display","none");
		            	$("#"+$aPrevDown.attr("id")).css("display","none");
		            	$("#"+$aPrevDownHid.attr("id")).css("display","");
		            }
		            if($tr.index() == 1){
		            	var $aUp=$tr.find(".up");
		            	var $aUpHid=$tr.find(".upHid");
		            	var $aPrevUp=$tr.prev().find(".up");
		            	var $aPrevUpHid=$tr.prev().find(".upHid");
		            	$("#" + $aUp.attr("id")).css("display","none");
		            	$("#" + $aUpHid.attr("id")).css("display","");
		            	$("#" + $aPrevUp.attr("id")).css("display","");
		            	$("#" + $aPrevUpHid.attr("id")).css("display","none");	
		            }
		            if($tr.index() != 0){
		            	$tr.fadeOut().fadeIn();
		                $tr.prev().before($tr);
		            }
		        });
		    });

			//下移
		    $('.down').click(function(){
		        var trLength = '${listSize}'; 
		        $(this).each(function(){
		            var $tr = $(this).parents("tr"); 
                    if($tr.index() == 0 && '${listSize}' > 1){
                    	var $aUp=$tr.find(".up");
                    	var $aUpHid=$tr.find(".upHid");
		            	var $aNextUp=$tr.next().find(".up");
		            	var $aNextUpHid=$tr.next().find(".upHid");
		            	$("#" + $aUp.attr("id")).css("display","");
		            	$("#" + $aUpHid.attr("id")).css("display","none");
		            	$("#" + $aNextUp.attr("id")).css("display","none");
		            	$("#" + $aNextUpHid.attr("id")).css("display","");
		            }
                    if(trLength > 1 && $tr.index() == trLength-2){
		            	var $aDown=$tr.find(".down");
		            	var $aDownHid=$tr.find(".downHid");
		            	var $aNextDown=$tr.next().find(".down");
		            	var $aNextDownHid=$tr.next().find(".downHid");
		            	$("#"+$aDown.attr("id")).css("display","none");
		            	$("#"+$aDownHid.attr("id")).css("display","");
		            	$("#"+$aNextDown.attr("id")).css("display","");
		            	$("#"+$aNextDownHid.attr("id")).css("display","none");
		            }
		            if ($tr.index() != trLength-1) {
		            	$tr.fadeOut().fadeIn();
		                $tr.next().after($tr);
		            }
		            
		            
		        });
		    });
			
		});
		
		//删除的点击事件
		function elemDelete(moduleName){
			if (!$("#selectElemId").val()) {
				alertx("请选择要删除的团购活动");
				return false;			
			}else {
				var editFlag = $(selectedElem).children().find("#editFlag").val();
				
				//团购活动：待开始之外的状态
				if(editFlag == 'false'){
					alertx("团购活动已开始不能删除");
					return false;
				}
				var elemId = $("#selectElemId").val();
				var tempHref = $("#btuElemDelete").attr("href") + elemId;
							
				if (confirmx("确定删除此"+ moduleName +"？",tempHref)) {
					return true;
				} else {
					return false;
				}
			}
        }
		
		//编辑的点击事件
		function elemEdit(){
			if (!$("#selectElemId").val()) {
				alertx("请选择要编辑的团购活动");
				return false;
			} else {
				
				var editFlag = $(selectedElem).children().find("#editFlag").val();
				//团购活动：待开始之外的状态
				if(editFlag == 'false'){
					alertx("团购活动已开始不能编辑");
					return false;
				}
				var elemId = $("#selectElemId").val();
				$("#btuElemEdit").attr("href",$("#btuElemEdit").attr("href") + elemId);
				return true;
			}
		}
		
		//保存排序的点击事件
		function elemSort(){
			var groupId= '';
			var sortNum= '';
			var i=1;
			$("input[name='groupId']").each(function(){
				groupId+=','+this.value;
				sortNum+=','+i;
				i++;
		   	});
			
			$.ajax({
				type : 'POST',
				url : '${ctx}/operation/groupPurchase/saveNumSort',
				data : {
					groupId:groupId,
					sortNum:sortNum
	    		 },
				dataType : 'json',
				success : function(data) {					
					if(data == "1"){
						$.jBox.tip('保存排序成功！');
						//延迟一秒再跳转
	            		setTimeout(function() {
	            			window.location.href="${ctx}/operation/groupPurchase/list";
	            	    }, 1000);
	        			
					}else{
						$.jBox.tip('保存排序失败！');
					}					
			    }
				
			})
			
		}
	</script> 
	
</body>
</html>