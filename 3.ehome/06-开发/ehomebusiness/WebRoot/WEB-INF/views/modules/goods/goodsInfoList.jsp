<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		/* 商品信息表排序
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
		/*
		 * 商品信息全选
		 */
		function allCheck(obj) {
			var checked = obj.checked;
			$("#checkedGoodsId").val("");
			var checkBoxs = $('input[name="itemCheckbox"]');
			for (var i = 0;i < checkBoxs.length; i++) {
				checkBoxs[i].checked=checked;
				if (checkBoxs[i].checked) {
		            var checkedGoodsId = $("#checkedGoodsId").val() + checkBoxs[i].value +",";
		            $("#checkedGoodsId").val(checkedGoodsId);
				}

			}
		}
	    /*
         * 商品信息单选
         */
        function itemCheck(obj) {
        	$("#checkedGoodsId").val("");
            var checkBoxs = $('input[name="itemCheckbox"]');
            for (var i = 0;i < checkBoxs.length; i++) {
            	// 如果被按下则拼接商品ID
            	if (checkBoxs[i].checked) {
                    var checkedGoodsId = $("#checkedGoodsId").val() + checkBoxs[i].value +",";
                    $("#checkedGoodsId").val(checkedGoodsId);
            	}
            }
		}
	    
        /*
         * 批量删除
         */
	    function muliDelete() {
	        var goodsid = $("#checkedGoodsId").val();
	        if (goodsid == "") {
	            alertx("请选择要删除的商品");
	            return;
	        } else {
	        	var href = "${ctx}/goods/goodsInfo/muliDelete?goodsid=" + goodsid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
	        	return confirmx('确认要删除此商品？删除后该商品的相应已受理订单依然需要完成', href);
	        }
	    }
        /*
         * 单个删除
         */
        function singledelete(href) {
            return confirmx('确认要删除此商品？删除后该商品的相应已受理订单依然需要完成', href);
        }
        
        /*
         * 批量上架
         */
        function muliGrounding() {
        	top.$.jBox.tip.mess = null;
            var goodsid = $("#checkedGoodsId").val();
            if (goodsid == "") {
            	alertx("请选择要上架的商品");
                return;
            } else {
                var href = "${ctx}/goods/goodsInfo/muliGrounding?goodsid=" + goodsid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                window.location.href=href;
            }
        }
        
        /*
         * 批量下架
         */
        function muliUndercarriage() {
            top.$.jBox.tip.mess = null;
            var goodsid = $("#checkedGoodsId").val();
            if (goodsid == "") {
            	alertx("请选择要下架的商品");
                return;
            } else {
                var href = "${ctx}/goods/goodsInfo/muliUndercarriage?goodsid=" + goodsid + "&sortItem=" + $("#sortItem").val() + "&sort=" + $("#sort").val();
                window.location.href=href;
            }
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="goodsInfo" action="${ctx}/goods/goodsInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
                <form:select path="sortInfoId"  style="width:110px;height:30px;" >
                    <form:option value="" label="所有分类"/>
                    <form:options items="${sortInfoList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                </form:select>
                <form:select path="recommend" style="width:110px;height:30px;">
                    <form:option value="" label="推荐状态"/>
                    <form:options items="${fns:getDictList('Quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <form:select path="state" style="width:110px;height:30px;">
                    <form:option value="" label="商品状态"/>
                    <form:options items="${fns:getDictList('goods_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			<input id="btnSubmit" class="commonbtn" type="submit" value="查询" style="width:60px"/>
            <input id="btnAddGoods" class="commonbtn" style="width:80px" type="button" onclick="window.location.href='${ctx}/goods/goodsInfo/form?sortItem=${goodsInfo.sortItem}&sort=${goodsInfo.sort}'" value="新增商品"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<input type="hidden" id="checkedGoodsId" value="${goodsid}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98%">
		<thead>
			<tr>
			    
			    <th><input type="checkbox" id="allCheck" onclick="allCheck(this)">全选</th>
			    <th>编号</th>
				<th>商品名称</th>
				<th>分类
                <c:choose> 
                    <c:when test="${goodsInfo.sortItem == 'b.sort_order' && goodsInfo.sort == 'DESC'}">
                        <img id="sortInfoSortImg" src="${ctxStatic}/images/u766.png" onclick="dataTableSort('sortInfo',this.src)">
                    </c:when>
                    <c:otherwise>
                        <img id="sortInfoSortImg" src="${ctxStatic}/images/u765.png" onclick="dataTableSort('sortInfo',this.src)">
                    </c:otherwise>
                </c:choose>
                </th>
				<th>价格</th>
				<th>总库存</th>
				<th>图片</th>
				<th>推荐</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goodsInfoTemp" varStatus="status">
			<tr>
			    <td>
			    <input type="checkbox" <c:if test="${fns:indexOf(goodsid,goodsInfoTemp.id) != -1}">checked="checked"</c:if>name="itemCheckbox" onclick="itemCheck(this)" value="${goodsInfoTemp.id}">
			    </td>
                <td>
                    ${(status.index + 1) + ((page.pageNo - 1) * (page.pageSize))}
                </td>
				<td>
					${goodsInfoTemp.name}
                </td>
				<td>
				    <c:if test="${goodsInfoTemp.sortInfoName == null || goodsInfoTemp.sortInfoName == ''}">
				    -
				    </c:if>
				    ${goodsInfoTemp.sortInfoName}
				</td>
				<td>
				<c:choose> 
				    <c:when test="${goodsInfoTemp.benefitPrice != null && goodsInfoTemp.benefitPrice!= ''}">
					<s><font color="#CCCCCC">${fns:doubleFormat(goodsInfoTemp.basePrice)}</font></s><br/>
					${fns:doubleFormat(goodsInfoTemp.benefitPrice)}
					</c:when>
					<c:otherwise>
					${fns:doubleFormat(goodsInfoTemp.basePrice)}
					</c:otherwise>
			    </c:choose>
				</td>
				<td>
                <c:choose> 
                    <c:when test="${goodsInfoTemp.stock != null && goodsInfoTemp.stock!= ''}">
                    ${goodsInfoTemp.stock}
                    </c:when>
                    <c:otherwise>
                    0
                    </c:otherwise>
                </c:choose>
				</td>
                <td>
           <c:forEach items="${goodsInfoTemp.imageUrls}" var="imgUrl">
                    <img src="${imgUrl}" style="width:53px;height:40px;"/>
           </c:forEach>
                </td>
				<td>
				    <c:if test="${goodsInfoTemp.recommend == null || goodsInfoTemp.recommend == '0'}">
				    否
				    </c:if>
                    <c:if test="${goodsInfoTemp.recommend != null && goodsInfoTemp.recommend == '1'}">
                    是
                    </c:if>
				</td>
				<td>
                    <c:if test="${goodsInfoTemp.state == null || goodsInfoTemp.state == '0'}">
                    下架
                    </c:if>
                    <c:if test="${goodsInfoTemp.state != null && goodsInfoTemp.state == '1'}">
                    上架
                    </c:if>
				</td>
				<td>
				    <input type="button" class="commonsmallbtn" value="编辑" style="width:40px;" onclick="window.location.href='${ctx}/goods/goodsInfo/form?id=${goodsInfoTemp.id}&sortItem=${goodsInfo.sortItem}&sort=${goodsInfo.sort}'">
				    <div style="margin:0 auto;height:3px;"></div>
				    <input type="button" class="commonsmallbtn" value="删除" style="width:40px;" onclick="singledelete('${ctx}/goods/goodsInfo/delete?id=${goodsInfoTemp.id}&sortItem=${goodsInfo.sortItem}&sort=${goodsInfo.sort}')">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>