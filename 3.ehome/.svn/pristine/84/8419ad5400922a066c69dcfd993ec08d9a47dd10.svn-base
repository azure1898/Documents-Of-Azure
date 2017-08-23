<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>广告信息管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" />
<script type="text/javascript">
    $(document).ready(function() {
        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="adverContent"]', {
                cssPath : '${ctxStatic}/plugins/code/prettify.css',
                uploadJson : "${ctx}/UploadFile.do",
                allowFileManager : false,
                themeType : 'simple',
                allowImageUpload : true,//允许上传图片
                afterChange : function() {
                    var limitNum = 6500;
                    if (this.count() > limitNum) {
                        var str=this.html().substring(0, 6400);
                        this.html(str);
                    }
                    if (this.count() > limitNum) {
                        $(".word_message").show();
                    } else {
                        $(".word_message").hide();
                    }
                },
                afterBlur : function() {
                    this.sync();
                },
                items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|', 'emoticons', 'image', 'link' ]
            });
            prettyPrint();
        });
        //处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.  
        function Substring(s) {
            return s.substring(s.substring(0, s.lastIndexOf("/")).lastIndexOf("/"), s.length);
        }
        //默认产品线0
        var prodectLine = 0;
        if ('${adverManage.adverPosition.id}' != '') {
            prodectLine = '${adverManage.adverPosition.id}';
        } else {
            $("input[name='adverPosition']").eq(0).prop("checked", "checked");
        }
        //绑定位置
        bindPosition(prodectLine);
        
        bindTree(prodectLine);
        hideAdver('${adverManage.adverType}');
        if ('${adverManage.adverType}' == '4') {
            /* 绑定分类下拉值  */
            changeBusiness($("#HidCategoryId").val());
            /* 绑定商品下拉值 */
            changeGoods($("#HidGoodsId").val());
        }
        getCategory();
        if ($("input[name='displayType']:checked").val() != 1) {
            $("#displayTimeInterval").prop("disabled", true);
        }
        $.validator.addMethod("checkVillage", function(value, element, params) {
            var treeObj = $.fn.zTree.getZTreeObj("tree");
            var nodes = treeObj.getCheckedNodes(true);
            var ids = [];
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].isParent != true) {
                    ids.push(nodes[i].id);
                }
            }
            if (ids.length == 0) {
                return false;
            } else {
                $("#villageIds").val(ids);
                return true;
            }
        }, "请选择投放楼盘");
        $.validator.addMethod("checkPositionId", function(value, element, params) {
            var positionIdList = '';
            $("input[name='positionId']").each(function() {
                if ($(this).attr("checked") == 'checked') {
                    positionIdList += $(this).val();
                }
            });
            if (positionIdList == '') {
                return false;
            } else {
                return true;
            }
        }, "请选择广告位置");
        jQuery.validator.addMethod("checkImg", function(value, element, params) {
            var imgArea = $("#imgArea").children().find(".up-section").val();
            if (imgArea != '') {
                return false;
            } else {
                return true;
            }
        }, "请上传广告图片");
        $.validator.addMethod("compareValiTime", function(value, element) {
            var startTime = $("#starttime").val();
            var endTime = $("#endTime").val();
            var reg = new RegExp('-', 'g');
            startTime = startTime.replace(reg, '/');//正则替换
            endTime = endTime.replace(reg, '/');
            startTime = new Date(parseInt(Date.parse(startTime), 10));
            endTime = new Date(parseInt(Date.parse(endTime), 10));
            if (startTime > endTime) {
                return false;
            } else {
                return true;
            }
        }, "投放结束时间应该大于等于投放结束时间");
        $("#inputForm").validate({
            rules : {
                starttime : {
                    required : true,
                },
                endTime : {
                    required : true
                },
                villageIds : {
                    checkVillage : "param",
                },
                checkImg : {
                    checkImg : "params"
                },
                skipTime : {
                    required : true,
                    min : 1,
                    digits : true
                },
                displayTimeInterval : {
                    required : true,
                    min : 1,
                    digits : true
                },
                displayType : {
                    required : true,
                }
            },
            messages : {
                adverPosition : {
                    required : "请选择投放位置"
                },
                positionId : {
                    required : "请选择广告位置"
                },
                advertTitle : {
                    required : "请输入广告名称"
                },
                adverType : {
                    required : "请选择广告类型"
                },
                starttime : {
                    required : "请选择投放起始时间"
                },
                endTime : {
                    required : "请选择投放结束时间"
                },
                villageIds : {
                    checkVillage : "请选择投放楼盘",
                },
                checkImg : {
                    checkImg : "请上传广告图片"
                },
                skipTime : {
                    required : "请输入页面跳过时间",
                    min : "页面跳过时间必须大于0",
                },
                displayTimeInterval : {
                    required : "请输间隔时间",
                    min : "间隔必须大于0",
                },
                displayType : {
                    required : "请选择用户显示频次",
                }
            },
            submitHandler : function(form) {
                if (KindEditor.instances[0].html().length > 6500) {
                    $(".word_message").show();
                    return;
                }
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer : "#messageBox",
            errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        /* 根据产品线显示不同的位置信息*/
        $("input[name='adverPosition']").click(function() {
            bindPosition($(this).val());
            bindTree($(this).val());
        });
        /* 根据产品线显示不同的位置信息*/
        $("input[name='displayType']").click(function() {
            $("#displayTimeInterval").prop("disabled", false);
            if ($(this).val() == 1) {
                $("#displayTimeInterval").prop("readonly", "");
            } else {
                $("#displayTimeInterval").val("");
                $("#displayTimeInterval").next().remove();
                $("#displayTimeInterval").removeClass("required");
                $("#displayTimeInterval").prop("readonly", true);
            }
        });
        /* 根据广告位置确定是否显示开屏跳过时间 */
        $("input[name='positionId']").click(function() {
            //console.log($(this).val())
            if ($(this).val() == "") {
                $("#skipTime").show();
            } else {
                $("#skipTime").show();
            }
        });
        /* 根据广告类型显示不同的广告类型数据 */
        $("input[name='adverType']").click(function() {
            hideAdver($(this).val());
        });
        /* 根据商品分类查询商家列表 */
        $('#categoryId').change(function() {
            console.log($("#categoryId").find("option:selected").attr("prodType"))
        });
        /* 根据商家信息查询商品列表 */
        $('#businessinfoId').change(function() {
            changeGoods($(this).val());
        });
    });
    /*根据广告类型值显示不同的广告类型数据*/
    function hideAdver(adverType) {
        $("#tuwen").hide();
        $("#lianjie").hide();
        $("#mokuai").hide();
        $("#shangjia").hide();
        $("#shangpin").hide();
        if (adverType == 0) {
            $("#tuwen").show();
        } else if (adverType == 1) {
            $("#lianjie").show();
        } else if (adverType == 2) {
            $("#mokuai").show();
        } else if (adverType == 3) {
            $("#shangjia").show();
        } else if (adverType == 4) {
            $("#shangjia").show();
            $("#shangpin").show();
        }
    }
    var setting = {
        check : {
            enable : true,
            nocheckInherit : true
        },
        view : {
            selectedMulti : false
        },
        data : {
            key : {
                name : "villageName"
            },
            simpleData : {
                enable : true
            }
        },
        view : {
            showIcon : false,
            showLine : false
        },
        callback : {
            beforeClick : function(id, node) {
                tree.checkNode(node, !node.checked, true, true);
                return false;
            }
        }
    };
    function openNode(event, treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var isOpen = treeNode.open;
        if (isOpen) {
            treeObj.expandNode(treeNode, false, false, false);
        } else {
            treeObj.expandNode(treeNode, true, false, false);
        }
    };
    /* 初始化楼盘树结构 */
    function bindTree(product_line) {
        $.ajax({
            url : '${ctx }/village/villageInfo/getVillageTree?villageLineId=' + product_line,
            type : 'GET', //GET
            async : true, //或false,是否异步
            dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
            success : function(data) {
                var tree = $.fn.zTree.init($("#tree"), setting, data);
                // 默认选择节点
                var ids = "${adverManage.villageIds}".split(",");
                for (var i = 0; i < ids.length; i++) {
                    var node = tree.getNodeByParam("id", ids[i]);
                    try {
                        tree.checkNode(node, true, true);
                    } catch (e) {
                    }
                }
                // 选择父节点
                tree.setting.check.chkboxType = {
                    "Y" : "ps",
                    "N" : "ps"
                };
                // 默认展开全部节点
                tree.expandAll(true);
            }
        });
    }
    //以select的形式 初始化商家信息列表
    function changeBusiness() {
        var categorydict=$("#categoryId").val();
        $.ajax({
            type : "POST",
            url : ctx + "/business/businessInfo/bindBusinessList",
            data : {
                categorydict : categorydict,
            },
            dataType : "JSON",
            success : function(data) {
                $("#businessinfoId").empty();
                var option = "<option value=''>商家名称</option>";
                $.each(data, function(indx, item) {
                    option += "<option value='"+item.id+"'>" + item.businessName + "</option>";
                })
                $("#businessinfoId").append(option);
                $("#businessinfoId").val($("#HidBusinessinfoId").val()).trigger("change");//修改初始时，带值选中
            }
        })
    }
    //以select的形式初始化商品信息列表
    function changeGoods(businessInfoId) {
        console.log($("#categoryId").find("option:selected").attr("prodType"))
        var prodType=$("#categoryId").find("option:selected").attr("prodType");
        var url="";
        if(prodType=="0"){
            url=ctx + "/goods/goodsInfo/bindGoodsList"
        }else if(prodType=="1"){
            url=ctx + "/adver/adverManage/getServiceInfoList"
        }else if(prodType=="2"){
            url=ctx + "/adver/adverManage/getLessonInfoList"
        }else if(prodType=="3"){
            url=ctx + "/adver/adverManage/getFieldInfoList"
        }
        console.log(url);
        $.ajax({
            type : "POST",
            url : url,
            data : {
                businessInfoId : businessInfoId,
            },
            dataType : "JSON",
            success : function(data) {
                console.log(data);
                $("#goodsId").empty();
                var option = "<option value=''>商品名称</option>";
                $.each(data, function(indx, item) {
                    option += "<option value='"+item.id+"'>" + item.name + "</option>";
                })
                $("#goodsId").append(option);
                $("#goodsId").val($("#HidGoodsId").val()).trigger("change");//修改初始时，带值选中
            }
        })
    }
    /* 绑定广告位置数据 */
    var positionId = '${adverManage.positionId}';
    function bindPosition(product_line) {
        $.ajax({
            url : '${ctx }/adver/adverPosition/getPositonList',
            type : 'POST',
            data : {
                moduleCode : product_line,
            },
            dataType : 'json',
            success : function(data) {
                //console.log(data);
                $("#positionId").html('');
                var domRow = '';
                for (var i = 0; i < data.length; i++) {
                    var positionName = data[i].positionName;
                    var id = data[i].id;
                    var openScreenFlag = data[i].openScreenFlag;
                    if (positionId == id) {
                        //点击文字也可触发选中事件的html
                        domRow += '<span onclick="showSkip(' + openScreenFlag + ')">' + '   <input checked="ture" id="positionId'+i+'" name="positionId" class="required" type="radio" value="' + id + '">' + '   <label for="positionId'+i+'">' + positionName + '</label></span>';
                        //domRow += '<input class="required"  onclick="showSkip(' + openScreenFlag + ')" checked="ture" type="radio" name="positionId" value="' + id + '" ><label>' + positionName + '</label> &nbsp';
                        if (openScreenFlag == 1) {
                            $("#skipTime").show();
                        }
                    } else {
                        //点击文字也可触发选中事件的html
                        domRow += '<span onclick="showSkip(' + openScreenFlag + ')">' + '   <input id="positionId'+i+'" name="positionId" class="required" type="radio" value="' + id + '">' + '   <label for="positionId'+i+'">' + positionName + '</label></span>';
                        // domRow += '<input class="required" onclick="showSkip(' + openScreenFlag + ')"type="radio" name="positionId" value="' + id + '" ><label>' + positionName + '</label> &nbsp';
                    }
                }
                $("#positionId").append($(domRow))
            }
        })
    }
    function showSkip(openScreenFlag) {
        if (openScreenFlag == "1") {
            $("#skipTime").show();
        } else {
            $("#skipTime").hide();
        }
    }
    function synchroModuleicon(elem) {
        setImagePreview();
        $("#selectedFile").val($(elem).val());
    }
    //初始化商家分类的列表
    var allCategory=${fns:toJson(allCategory)};
    function getCategory(){
        var hidCategoryId=$("#HidCategoryId").val();
		$("#categoryId").empty();
		var option = "<option value=''>分类名称</option>";
		$.each(allCategory,function(indx,item){
			option += "<option value='"+item.id+"' prodType="+item.prodType+">"+item.categoryName+"</option>";
		})
		$("#categoryId").append(option);
		$("#categoryId").val(hidCategoryId).trigger("change");//修改初始时，带值选中
    }
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <a href="${ctx}/adver/adverManage/">广告信息列表</a>
        </li>
        <li class="active">
            <a href="${ctx}/adver/adverManage/form?id=${adverManage.id}">广告信息<shiro:hasPermission name="adver:adverManage:edit">${not empty adverManage.id?'修改':'添加'}</shiro:hasPermission> <shiro:lacksPermission name="adver:adverManage:edit">查看</shiro:lacksPermission></a>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="adverManage" action="${ctx}/adver/adverManage/save" enctype="multipart/form-data" method="post" class="form-horizontal">
        <input value="${adverManage.categoryId}" type="hidden" id="HidCategoryId" />
        <input value="${adverManage.businessinfoId}" type="hidden" id="HidBusinessinfoId" />
        <input value="${adverManage.goodsId}" type="hidden" id="HidGoodsId" />

        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">产品线：</label>
            <div class="controls">
                <form:radiobuttons path="adverPosition" items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">投放位置：</label>
            <div class="controls">
                <span id="positionId"></span>
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div id="skipTime" class="hide">
            <div class="control-group">
                <div class="controls">
                    <label class="">页面跳过时间</label>
                    <form:input path="skipTime" htmlEscape="false" maxlength="2" class="input-mini required number min:1 digits" />
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="">用户显示频次 </label>
                    <form:radiobuttons path="displayType" items="${fns:getDictList('displayType')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                    <form:input path="displayTimeInterval" maxlength="2" htmlEscape="false" class="input-mini required number" />
                    小时显示1次
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告名称：</label>
            <div class="controls">
                <form:input path="advertTitle" htmlEscape="false" maxlength="64" class="input-large required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告图片：</label>
            <div id="localImag">
                <div class="controls">
                    <div class="img-box full" id="imgArea">
                        <section class=" img-section">
                            <div class="z_photo upimg-div clear" style="width: 150px;">
                                <c:if test="${adverManage.adverPic != '' && adverManage.adverPic !=null}">
                                    <section class="up-section fl">
                                        <span class="up-span"></span>
                                        <img src="${ctxStatic}/images/a7.png" class="close-upimg"> <img src="${adverManage.adverPic}" class="up-img">
                                    </section>
                                </c:if>
                                <section class="z_file fl" <c:if test="${adverManage.adverPic != '' && adverManage.adverPic !=null}">style="display: none;"</c:if>>
                                    <img src="${ctxStatic}/images/a10.png" class="add-img">
                                    <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" />
                                </section>
                            </div>
                        </section>
                    </div>
                    <aside class="mask works-mask">
                        <div class="mask-content" id="imgAside">
                            <p class="del-p ">您确定要删除图片吗？</p>
                            <p class="check-p">
                                <span class="del-com wsdel-ok">确定</span>
                                <span class="wsdel-no">取消</span>
                            </p>
                        </div>
                    </aside>
                    <input id="checkImg" name="checkImg" value="" style="width: 0px; height: 0px; border: 0px;">
                    <span class="help-inline">
                        <font color="red">*</font>
                    </span>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告类型：</label>
            <div class="controls">
                <form:radiobuttons path="adverType" items="${fns:getDictList('adverType')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <!-- 广告类型-图文 -->
        <div id="tuwen">
            <div class="control-group">
                <label class="control-label">图文广告内容：</label>
                <div class="controls">
                    <form:textarea id="adverContent" path="adverContent" rows="4" style="width:1000px;height:300px" />
                    <label for="adverContent" class="error word_message" style="display: none;">超出最大长度，请适当缩减内容</label>
                </div>
            </div>
        </div>
        <!-- 广告类型-外联广告 -->
        <div id="lianjie">
            <div class="control-group">
                <div class="controls">
                    <label>外链地址：</label>
                    <form:input path="linkUrl" htmlEscape="false" maxlength="200" class="input-xlarge " />
                </div>
            </div>
        </div>
        <!-- 广告类型-模块链接 -->
        <div id="mokuai">
            <div class="control-group">
                <div class="controls">
                    <form:select path="moduleId" class="input-medium">
                        <form:option value="" label="模块名称" />
                        <form:options items="${allModule}" itemLabel="moduleName" itemValue="id" htmlEscape="false" />
                    </form:select>
                </div>
            </div>
        </div>

        <div class="control-group">
            <div class="controls" id="shangjia">
                <select id="categoryId" name="categoryId" style="width: 170px" onchange="changeBusiness()">
                <select>
                <!--广告类型-商家链接  -->
                <select id="businessinfoId" name="businessinfoId" style="width: 170px">
                <option value=''>商家名称</option>
                </select>
                <shangpin id="shangpin">
                 <!-- 广告类型-商品链接  -->
                <select id="goodsId" name="goodsId" style="width: 170px">
                <option value=''>商品名称</option>
                </select> </shangpin>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">投放起始时间：</label>
            <div class="controls">
                <input id="starttime" name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="<fmt:formatDate value="${adverManage.starttime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">投放结束时间：</label>
            <div class="controls">
                <input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required compareValiTime" value="<fmt:formatDate value="${adverManage.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">投放范围：</label>
            <div class="controls">
                <ul id="tree" class="ztree" style="border: 1px solid #ccc; padding: 10px; width: 200px;"></ul>
                <form:input path="villageIds" style="width: 0px; height: 0px; border: 0px" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="adver:adverManage:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
    <script src="${ctxStatic}/common/singlefileUpload.js"></script>
</body>
</html>