<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>发言管理</title>
    <meta name="decorator" content="default" />
    <link rel="stylesheet" href="${ctxStatic}/common/multiplefileUpload.css" type="text/css" />
    <%@include file="/WEB-INF/views/include/treeview.jsp"%>
    <script type="text/javascript">
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
	    
	    $.ajax({
	        url : '${ctx }/village/villageInfo/getUserVillageTree',
	        type : 'GET', //GET
	        async : true, //或false,是否异步
	        dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
	        success : function(data) {
	            console.log(data)
	            var tree = $.fn.zTree.init($("#tree"), setting, data);
	            // 不选择父节点
	            tree.setting.check.chkboxType = {
	                "Y" : "ps",
	                "N" : "s"
	            };
	            // 默认选择节点
	            console.log('${user.villageInfoIds}');
	            var ids = "${user.villageInfoIds}".split(",");
	            for (var i = 0; i < ids.length; i++) {
	                var node = tree.getNodeByParam("id", ids[i]);
	                try {
	                    tree.checkNode(node, true, false);
	                } catch (e) {
	                }
	            }
	            // 默认展开全部节点
	            tree.expandAll(true);
	        }
	    });
		
        fillSubject(); // 加载全部话题
        $(document).ready(function() {
        	$.validator.addMethod("checkVillage", function(value, element, params) {
                var treeObj = $.fn.zTree.getZTreeObj("tree");
                var nodes = treeObj.getCheckedNodes(true);
                var ids = [];
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].isParent != true) {
                        ids.push(nodes[i].id);
                    }
                }
                console.log(ids);
                if (ids.length == 0) {
                    return false;
                } else {
                    $("#villageinfoid").val(ids);
                    return true;
                }
            }, "请选择楼盘");
            $("#inputForm").validate({
            	rules : {
            		content : {
            			required : true
            		},
            		villageinfoid : {
                        checkVillage : "param",
                    }
            	},
            	message : {
            		content : {
            			required : "请输入发言内容"
            		},
            		villageInfoIds : {
                        checkVillage : "请选择楼盘",
                    }
            	},
                submitHandler: function(form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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

        function fillSubject() {
            $.ajax({
                type: "POST",
                url: ctx + "/subject/socialSubject/loadSubject",
                data: null,
                dataType: "JSON",
                success: function(data) {
                    $("#subjectId").empty();
                    var option = "<option value=''>选择话题</option>";
                    $.each(data, function(indx, item) {
                        option += "<option value='" + item.id + "'>" + item.subname + "</option>";
                    })
                    $("#subjectId").append(option);
                }
            });
        }

        function changeSubject() {
            var subjectId = $("#subjectId").val();
            if (subjectId == null || subjectId == "") {
                $("#tag").show();
            } else {
                $("#tag").hide();
            }
        }
         //以select的形式展示楼盘列表
        function changeVillageInfoSelect() {
            $.ajax({
                type: "POST",
                url: ctx + "/village/villageInfo/findListAllState",
                data: {
                    provinceId: $("#addrpro").val(),
                    cityId: $("#addrcity").val()
                },
                dataType: "JSON",
                success: function(data) {
                    var hidVillageId = $("#hidVillageId").val();
                    $("#addrVillageInfo").empty();
                    var option = "<option value=''>全部楼盘</option>";
                    $.each(data, function(indx, item) {
                        option += "<option value='" + item.id + "'>" + item.villageName + "</option>";
                    })
                    console.log(option);
                    $("#addrVillageInfo").append(option);
                    $("#addrVillageInfo").val(hidVillageId).trigger("change"); //修改初始时，带值选中
                }
            })
        }
    </script>
</head>

<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/speak/socialSpeak/">我来发言</a>
        </li>
    </ul>
    <br/>
    <form:form id="inputForm" modelAttribute="socialSpeak" enctype="multipart/form-data" action="${ctx}/speak/socialSpeak/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">公号名称：</label>
            <div class="controls">
                <form:hidden path="auserid" value="${auserid}" />
                ${ausername}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">话题标签：</label>
            <div class="controls">
                <form:select path="subjectid" id="subjectId" name="subjectId" style="width: 120px" onchange="changeSubject();">
                    <option value="">选择话题</option>
                </form:select>
                <form:input id="tag" path="tag" htmlEscape="false" maxlength="200" class="input-xlarge required" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">文字内容：</label>
            <div class="controls">
                <form:textarea style="height:100px; width:300px;" path="content" htmlEscape="false" maxlength="500" class="input-xlarge required" />    <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">图片内容：</label>
            <div class="img-box full" id="imgArea">
                <form:hidden path="delImgName" />
                <section class=" img-section">
                    <div class="z_photo upimg-div clear">
                        <section class="z_file fl" <c:if test="${fn:length(imgUrls) gt 9}">style="display: none;"</c:if>>
                            <img src="${ctxStatic}/images/a11.png" class="add-img">
                            <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple="multiple" style="font-size:12px;" />
                        </section>
                    </div>
                </section>
            </div>
            <aside class="mask works-mask">
                <div class="mask-content" id="imgAside">
                    <p class="del-p ">您确定要删除图片吗？</p>
                    <p class="check-p"> <span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
                    </p>
                </div>
            </aside>
        </div>
        <div class="control-group">
            <label class="control-label">可见范围：</label>
            <div class="controls">
                <form:select path="visrange" class="input-small">
                	<form:options items="${fns:getDictList('visRange')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
                <font color="red">*</font>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">禁止评论：</label>
            <div class="controls">
                <form:radiobuttons path="forbitcomment" items="${fns:getDictList('forbitComment')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" /> <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">禁止转发：</label>
            <div class="controls">
                <form:radiobuttons path="forbidforward" items="${fns:getDictList('forbidForward')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" /> <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">发布楼盘：</label>
            <div class="controls">
            	<ul id="tree" class="ztree" style="border: 1px solid #ccc; padding: 10px; width: 200px;" ></ul>
            	<form:input path="villageinfoid" style="width: 0px; height: 0px; border: 0px"/>
            	<span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="speak:socialSpeak:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
    <script src="${ctxStatic}/common/multiplefileUpload.js"></script>
</body>

</html>