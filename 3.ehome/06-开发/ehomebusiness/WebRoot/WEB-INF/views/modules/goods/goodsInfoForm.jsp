<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商品管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/multiplefileUpload.css" type="text/css" />
<script type="text/javascript">
		$(document).ready(function() {
	        jQuery.validator.addMethod("goodsName", function(value, element,params) {
	            var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
	            return this.optional(element) || (patrn.test(value));
	    }, "请输入中文，英文或数字");
        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="content"]', {
                cssPath : '${ctxStatic}/plugins/code/prettify.css',
                uploadJson :  "${ctx}/UploadFile.do",
                allowFileManager : false,
                themeType : 'simple',
                allowImageUpload:true,//允许上传图片
                afterChange : function() {
                    var limitNum = 65000;
                    if(this.count() > limitNum) {
                        $(".word_message").show();
                    } else {
                    	$(".word_message").hide();
                    }
                },
                afterBlur: function(){this.sync();},
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
            });
            prettyPrint();
        });
			$("#inputForm").validate({
	            rules: {
	                name: {
	                    required: true,
	                    maxlength: 20,
	                    goodsName:true
	                },
			        sortInfoId: {
			        	required: true
			        },
	                basePrice: {
	                	required: true,
	                	number: true,
	                	maxlength: 10,
                        min:0.01
	                },
	                benefitPrice: {
                        number: true,
                        maxlength: 10,
                        min:0.01
                    },
                    stock: {
                        number: true,
                        maxlength: 9,
                        min:0,
                        digits:true 
                    },
                    quotaNum: {
                    	number: true,
                        maxlength: 9,
                        min:1,
                        digits:true 
                    }
	            },
	            messages: {
	                name: {
	                    required: "请输入商品名称（建议在20个字以内）",
	                    maxlength: "商品名称在20个字以内",
	                    name : "商品名称必须是汉字英文数字"
	                },
                    sortInfoId: {
                        required: "请选择商品分类"
                    },
                    basePrice: {
                        required: "请填写商品价格",
                        number: "请填写数字",
                        maxlength: "商品价格长度不能超过10"
                    },
                    benefitPrice: {
                        number: "请填写数字",
                        maxlength: "优惠价长度不能超过10"
                    },
                    stock: {
                        number: "请填写数字",
                        maxlength: "库存长度不能超过9"
                    },
                    quotaNum: {
                    	number: "请填写数字",
                        maxlength: "限购数量长度不能超过9",
                        min: "限购数量必须大于0"
                    }
	            },
				submitHandler: function(form){
	                if ($(".up-section").size() == 0) {
			            alertx("未选择任何图片");
			            return;
			        }
	                if (KindEditor.instances[0].html().length > 65000) {
	                    $(".word_message").show();
	                    return;
	                }
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
        window.onload = function() {
        	// 如果浏览器不支持HTML5则提示无法上传图片
            if (!window.applicationCache) {
            	$("#imgArea").remove();
            	$("#imgAside").remove();
            	alertx("您当前的浏览器不支持图片上传");
            }
        }
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).parent().parent().addClass("hide");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).parent().parent().removeClass("hide");
			}
		}
		// 根据点击的多选框选择是否追加新的商品分类库存信息
		function skuKeyClick(obj,skuKeyName) {
			if (obj.checked) {
				document.getElementById("skuKeyName").innerHTML = skuKeyName;
	            var row = new Object();
	            row.delFlag = '0';
	            row.skuKeyName = skuKeyName;
	            row.skuKeyId = $('#skuKey_id')[0].value;
	            row.skuValueId = obj.value;
	            row.sortOrder = goodsSkuPriceRowIdx;
	            addRow('#goodsSkuPriceList', goodsSkuPriceRowIdx, goodsSkuPriceTpl, row);
	            $('#goodsSkuPriceList'+ goodsSkuPriceRowIdx +'_skuValueName')[0].innerHTML=$("[for='"+ obj.id +"']")[0].innerHTML + ':';
	            obj.setAttribute("goodsskupricerowidx", goodsSkuPriceRowIdx);
	            goodsSkuPriceRowIdx = goodsSkuPriceRowIdx + 1;
			} else {
				// 火狐只支持getAttribute
				var goodsskupricerowidx = obj.getAttribute("goodsskupricerowidx");
                if (typeof(goodsskupricerowidx)=="undefined") {
                    // IE支持.属性名的方式
                    goodsskupricerowidx = obj.goodsskupricerowidx;
                }
				var id_Input = $("#goodsSkuPriceList"+ goodsskupricerowidx +"_id")[0];
				delRow(id_Input, "#goodsSkuPriceList" + goodsskupricerowidx);
			}
            var ids = document.getElementsByName("checkedSkuValue");
            var flag = false ;               
            for(var i=0;i<ids.length;i++){
                if(ids[i].checked){
                    flag = true ;
                    break ;
                }
            }
            if (flag) {
                $('#stock')[0].disabled=true;
                $('#benefitPrice')[0].disabled=true;
                $('#stock')[0].value="";
                $('#benefitPrice')[0].value="";
            } else {
                $('#stock')[0].disabled=false;
                $('#benefitPrice')[0].disabled=false;
            }
		}
		
		// 根据选择的单位不同设定不同的限购单位
		function chooseUnit(obj) {
			for (var i=0; i< obj.options.length; i++) {
				if (obj.options[i].selected) {
					$('#Quota_unit')[0].innerHTML = obj.options[i].text;
					return;
				}
			}
		}
		
	    // 根据选择的限购设定来决定限购数量的活性
        function quotaNumDisabledSet(obj) {
	    	// 1为限购，限购数量为活性哦那个
            if ("1" == obj.value) {
            	$("#quotaNum")[0].disabled=false;
            } else {
            	$("#quotaNum")[0].value="";
            	$("#quotaNum")[0].disabled=true;
            }
        }
	    function openSortInfo(obj,refresh) {
	    	var $obj = $("a[href='${ctx}/goods/sortInfo']", window.parent.document);
	    	if ($obj == undefined) {
	    		$obj = $(this);
	    	}
	    	addTabPage('商品分类','${ctx}/goods/sortInfo',undefined,$obj);
	        $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left>div.tab_close>a', window.parent.document).html('<i class="fa fa-remove"></i>');
	        $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left', window.parent.document).css('width','100px');
	        $('#jerichotab .tab_pages>div.tabs>ul>li>div.tab_left>div.tab_text', window.parent.document).css('width','87px');
            return false;
	    }
	    // 下拉菜单刷新
	    function sortInfoRefresh() {
	    	alert("1");
	    	var nowValue = $("#sortInfoId").val();
	    	$.ajax({
	    		type:'post',
	    		url: "${ctx}/goods/goodsInfo/sortInfoRefresh",
	    		data:{},
	    		dataType: "json",
	    		success: function (data) {
	    			//清空
	    			$('#sortInfoId').html("");
	    			$('#sortInfoId').append("<option value='' ></option>");
	    			//读取json数据
	    			for (var i=0;i<data.length;i++){
	    				$('#sortInfoId').append("<option value='"+data.id+"' >"+data.name+"</option>");
	    			}
	    			$('#sortInfoId').val(nowValue);
	    		},
	    		error:function(data){
	    			
	    		}
	    	});
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>商品管理 > 添加商品</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="goodsInfo"
		action="${ctx}/goods/goodsInfo/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" />
		<form:hidden id="sortItem" path="sortItem" htmlEscape="false" />
		<form:hidden id="sort" path="sort" htmlEscape="false" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20"
					placeholder="请输入商品名称（建议在20个字以内）" class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${sortInfoList != null && sortInfoList.size() > 0}">
			<div class="control-group">
				<label class="control-label">商品分类：</label>
				<div class="controls">
					<form:select path="sortInfoId" class="input-xlarge required" onkeydown="sortInfoRefresh()" onmousedown = "sortInfoRefresh()">
						<form:option value="" label="" />
						<form:options items="${sortInfoList}" itemLabel="name"
							itemValue="id" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
					<a onclick="openSortInfo(this)" style="cursor:pointer">分类添加</a>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">轮播图片：</label> <span id="warnning"></span>
		</div>
		<div class="img-box full" id="imgArea">
			<form:hidden path="delImageName" />
			<section class=" img-section">
				<div class="z_photo upimg-div clear">
					<c:forEach items="${imgUrls}" var="imgTemp">
						<section class="up-section fl">
							<span class="up-span"></span> <img
								src="${ctxStatic}/images/a7.png" class="close-upimg"> <img
								src="${imgTemp.imgUrl}" class="up-img"> <input
								type="hidden" class="filename" value="${imgTemp.myfileid}">
						</section>
					</c:forEach>
					<section class="z_file fl"
						<c:if test="${fn:length(imgUrls) gt 4}">style="display: none;"</c:if>>
						<img src="${ctxStatic}/images/a11.png" class="add-img"> <input
							type="file" name="file" id="file" class="file" value=""
							accept="image/jpg,image/jpeg,image/png,image/bmp"
							multiple="multiple" />
					</section>
				</div>
			</section>
		</div>
		<aside class="mask works-mask">
			<div class="mask-content" id="imgAside">
				<p class="del-p ">您确定要删除图片吗？</p>
				<p class="check-p">
					<span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
				</p>
			</div>
		</aside>

		<div class="control-group">
			<label class="control-label">商品规格：</label>
			<div class="controls">
				<c:forEach items="${skuKeyList}" var="skuKey">
					<input id="skuKey_id" type="hidden" value="${skuKey.id}" />
					<span>${skuKey.name}:</span>
					<form:checkboxes items="${skuKey.skuValueList}"
						path="checkedSkuValue" itemLabel="name"
						goodsskupricerowidx="${sortOrder}" itemValue="id"
						onClick="skuKeyClick(this,'${skuKey.name}')" />
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品价格：</label>
			<div class="controls">
				<form:input path="basePrice" placeholder="请填写商品价格"
					htmlEscape="false" maxlength="10"
					value="${fns:doubleFormatForInput(goodsInfo.basePrice)}"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span> <span>优惠价：</span>
				<form:input path="benefitPrice" htmlEscape="false"
					class="input-xlarge "
					value="${fns:doubleFormatForInput(goodsInfo.benefitPrice)}"
					maxlength="10" />
				<span>库存：</span>
				<form:input path="stock" htmlEscape="false" maxlength="9"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格价格：</label> <br /> <span
				id="skuKeyName"></span>
			<div class="controls">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="width: 900px">
					<tbody id="goodsSkuPriceList">
					</tbody>
				</table>
				<script type="text/template" id="goodsSkuPriceTpl">//<!--
                        <tr id="goodsSkuPriceList{{idx}}">
                            <td class="hide">
                                <input id="goodsSkuPriceList{{idx}}_id" name="goodsSkuPriceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="goodsSkuPriceList{{idx}}_delFlag" name="goodsSkuPriceList[{{idx}}].delFlag" type="hidden" value="0"/>
                                <input id="goodsSkuPriceList{{idx}}_skuKeyId" name="goodsSkuPriceList[{{idx}}].skuKeyId" type="hidden" value="{{row.skuKeyId}}" maxlength="64" class="input-small"/>
                                <input id="goodsSkuPriceList{{idx}}_skuValueId" name="goodsSkuPriceList[{{idx}}].skuValueId" type="hidden" value="{{row.skuValueId}}" maxlength="64" class="input-small"/>
                                <input id="goodsSkuPriceList{{idx}}_sortOrder" name="goodsSkuPriceList[{{idx}}].sortOrder" type="hidden" value="{{row.sortOrder}}" maxlength="11" class="input-small "/>
                            </td>
                            <td >
                                <span id="goodsSkuPriceList{{idx}}_skuValueName">{{row.skuValueName}}</span>
                            </td>
                            <td >
                                <span>原价：</span>
                                <input id="goodsSkuPriceList{{idx}}_basePrice" name="goodsSkuPriceList[{{idx}}].basePrice" type="text" placeholder="请填写规格价格" value="{{row.basePrice}}"  maxlength="10" class="min:0 input-small required number"/>
                                <span class="help-inline"><font color="red">*</font> </span>
                            </td>
                            <td >
                                <span>优惠价：</span>
                                <input id="goodsSkuPriceList{{idx}}_benefitPrice" name="goodsSkuPriceList[{{idx}}].benefitPrice" type="text" value="{{row.benefitPrice}}"  maxlength="10" class="input-small number"/>
                            </td>
                            <td >
                                <span>库存：</span>
                                <input id="goodsSkuPriceList{{idx}}_stock" name="goodsSkuPriceList[{{idx}}].stock" type="text" placeholder="请填写规格库存" value="{{row.stock}}" maxlength="9" class="min:0 digits:true input-small required  number"/>
                                <span class="help-inline"><font color="red">*</font> </span>
                            </td>
                        </tr>//-->
                    </script>
				<script type="text/javascript">
                        var goodsSkuPriceRowIdx = 0, goodsSkuPriceTpl = $("#goodsSkuPriceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                        $(document).ready(function() {
                            var data = ${fns:toJson(goodsInfo.goodsSkuPriceList)};
                            for (var i=0; i<data.length; i++){
                                for (var j=0; j< $("input[name='checkedSkuValue']").size(); j++) {
                                    if ($("input[name='checkedSkuValue']")[j].value == data[i].skuValueId) {
                                        var obj = $("input[name='checkedSkuValue']")[j];
                                        obj.setAttribute("goodsskupricerowidx", goodsSkuPriceRowIdx);
                                    }
                                }
                                addRow('#goodsSkuPriceList', goodsSkuPriceRowIdx, goodsSkuPriceTpl, data[i]);
                                goodsSkuPriceRowIdx = goodsSkuPriceRowIdx + 1;
                            }
                            if ($("#goodsSkuPriceList")[0].rows.length != 0) {
                                $('#stock')[0].disabled=true;
                                $('#benefitPrice')[0].disabled=true;
                                $('#stock')[0].value="";
                                $('#benefitPrice')[0].value="";
                            } else {
                                $('#stock')[0].disabled=false;
                                $('#benefitPrice')[0].disabled=false;
                            }
                            // 1为限购，限购数量为活性哦那个
                            if ("1" == document.getElementById("quota").value) {
                                $("#quotaNum")[0].disabled=false;
                            } else {
                                $("#quotaNum")[0].value="";
                                $("#quotaNum")[0].disabled=true;
                            }
                        });
                    </script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品单位：</label>
			<div class="controls">
				<c:choose>
					<c:when
						test="${businessUnitList != null && businessUnitList.size() > 0}">
						<form:select path="baseUnitId" class="input-xlarge "
							onchange="chooseUnit(this)">
							<form:options items="${businessUnitList}" itemLabel="name"
								itemValue="id" htmlEscape="false" />
						</form:select>
					</c:when>
					<c:otherwise>
						<form:select path="baseUnitId" class="input-xlarge "
							onchange="chooseUnit(this)">
							<form:options items="${fns:getDictList('goods_units')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图文详情：</label>
			<div class="controls">
				<form:textarea id="content" path="content" rows="4" maxlength="200"
					style="width:1000px;height:400px" class="input-xlarge" />
				<label for="content" class="custom-error word_message"
					style="display: none;">超出最大长度，请适当缩减内容</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限购:</label>
			<div class="controls">
				<form:select path="quota" class="input-xlarge "
					onchange="quotaNumDisabledSet(this)">
					<form:options items="${fns:getDictList('purchasing_limitations')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span>限购数量：</span>
				<form:input path="quotaNum" htmlEscape="false" maxlength="9"
					class="input-xlarge number" disabled="true" />
				<span id="Quota_unit">件</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐:</label>
			<div class="controls">
				<form:select path="recommend" class="input-xlarge ">
					<form:options items="${fns:getDictList('Quota')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn" type="button"
				value="取 消"
				onclick="window.location.href = '${ctx}/goods/goodsInfo/list?sortItem=${goodsInfo.sortItem}&sort=${goodsInfo.sort}'" />
		</div>
	</form:form>
	<script src="${ctxStatic}/common/multiplefileUpload.js"></script>
</body>
</html>