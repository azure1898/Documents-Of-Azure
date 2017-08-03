<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" />
	<script type="text/javascript">
	    //页面初始加载
		$(document).ready(function() {
			var id = '${id}';
			//修改的场合
			if(id != null && id !=''){
				//绑定团购时间详情
				bindList();
			}
		});
	    
		//团购时间详情绑定
		function bindList(){
			$.ajax({
				type : 'POST',
				url : '${ctx}/operation/groupPurchase/bindList',
				data : {
					groupPurchaseId:'${id}'
	    		 },
				dataType : 'json',
				success : function(data) {
					for(var  i=0;i<data.length-1;i++){
						addRow(i+1);
					}
					for(var  i=0;i<data.length;i++){					
						var startTimes=data[i].startTime;
						var endTimes=data[i].endTime;
						var stockNums=data[i].stockNum;
						var saleNums=data[i].saleNum;

						$("#startTime"+i).text(startTimes.substr(0,13));
						$("#endTime"+i).text(endTimes.substr(0,13));
						$("#stockNum"+i).text(stockNums);
						
						if(saleNums ==null || saleNums=='' || saleNums=='undefined'){
							$("#saleNum"+i).val("已出售 0 件");
						}else{
							$("#saleNum"+i).val("已出售 "+saleNums+" 件");
						}						
					}
				}
			})
		}
		
		//团购开始时间-增加一行
		function addRow(i){
			var domRow='<div class="control-group">';
			var item='<div class="control">'+'<label class="control-label" >团购开始时间：</label>'+
			'<label id="startTime'+i+'" style="margin-left: 20px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;团购结束时间：&nbsp;&nbsp;'+
			'<label id="endTime'+i+'"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存量&nbsp;&nbsp;'+'<label id="stockNum'+i+
			'" ></label>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+'<input style="border:1px solid #EEEE00;background-color:#FFEE99;color:red;text-align:center;" class="input-medium" disabled="disabled" id="saleNum'+i+'"value="" ></input>'+'</div>'

			domRow+=item;
			domRow+='</div>';
			$("#timeList").append($(domRow))
		}
	</script>
</head>
<body>
	
	<ul class="nav nav-tabs">
        <li style="height:30px"> 运营管理><span><a href="${ctx}/operation/groupPurchase/">团购管理列表 </a>
        > 团购详情页
        </span>
        </li>
    </ul>

	<form:form id="inputForm" modelAttribute="groupPurchase" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table style="width:100%">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">所属商家：</label>
						<div class="controls">
							${groupPurchase.businessName}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">团购名称：</label>
						<div class="controls">
							${groupPurchase.groupPurcName}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">团购图片：</label>
						<div class="controls">
							<img id="preview" src="${groupPurchase.groupPurcPic}" width="45" height="45" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">市场价：</label>
						<div class="controls">
							${groupPurchase.marketMoney}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">团购价：</label>
						<div class="controls">
							${groupPurchase.groupPurcMoney}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="timeList">
						<div class="control-group">
							<div class="control">
							    <label class="control-label">团购开始时间：</label>
							    <label id="startTime0" style="margin-left: 20px;">${groupPurchase.startTimes}</label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;团购结束时间：
								<label id="endTime0">${groupPurchase.endTimes}</label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存量
								<label id="stockNum0" >${groupPurchase.stockNums}</label>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="saleNum0" style="border:1px solid #EEEE00;background-color:#FFEE99;color:red;text-align:center;"  class="input-medium" disabled="disabled"></input>
							</div>
						</div>	
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">用户限购数：</label>
						<div class="controls">
							${groupPurchase.restrictionNumber}&nbsp;件
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">商家支持：</label>
						<div class="controls">						
							<c:forEach items="${supportTypeList}" var="supportType">
								${fns:getDictLabel(supportType, 'supportType', '')}
							</c:forEach>
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">团购详情：</label>
						<div class="controls">
							${groupPurchase.groupPurcDetail}
							
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">团购券有效期：</label>
						<div class="controls">
							<fmt:formatDate value="${groupPurchase.validityStartTime}" pattern="yyyy-MM-dd"/>&nbsp;至
							<fmt:formatDate value="${groupPurchase.validityEndTime}" pattern="yyyy-MM-dd"/>
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">使用时间：</label>
						<div class="controls">
							${groupPurchase.useTime}
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<td>
					<div class="control-group">
						<label class="control-label">使用规则：</label>
						<div class="controls">
							${groupPurchase.useRule}
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
	</form:form>
	<script src="${ctxStatic}/common/singlefileUpload.js"></script>

</body>
</html>