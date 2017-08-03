<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
<title>楼盘信息产品线管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
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
            console.log(ids.length);
            if (ids.length == 0) {
                return false;
            } else {
                $("#villageIdList").val(ids);
                return true;
            }
        }, "请选择楼盘");
        //添加主导航验证
        $.validator.addMethod("checkSize", function(value, element, params) {
            var num = $("input[name='mainNavigationIds']:checked").size();
            console.log(num);
            if (num > 2) {
                return false;
            } else if (num < 2) {
                return false;
            } else {
                return true;
            }
        }, "主导航勾选数量只能为2个");

        $("#inputForm").validate({
            rules : {
                valiVillage : {
                    checkVillage : "param"
                },
                mainNavigationIds : {
                    checkSize : "param"
                }
            },
            messages : {
                valiVillage : {
                    checkVillage : "请选择楼盘",
                },
                mainNavigationIds : {
                    checkSize : "主导航勾选数量只能为2个"
                },

                lifeModuleIds : {
                    required : "请选择生活模块"
                },
                communityModuleIds : {
                    required : "请选择社区模块"
                },
                productLine : {
                    required : "请选择产品线"
                },
            },
            submitHandler : function(form) {
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
        $("input[name='communityModuleIds']").click(function() {
            console.log();
            var id = $(this).val();
            var total = $("#addCommunityModuleIds").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addCommunityModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").remove();
            }
        })
        $("input[name='lifeModuleIds']").click(function() {
            console.log();
            var id = $(this).val();
            var total = $("#addLifeModule").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addLifeModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").remove();
            }
        })
    });
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
            //console.log(data)
            var tree = $.fn.zTree.init($("#tree"), setting, data);
            // 不选择父节点
            tree.setting.check.chkboxType = {
                "Y" : "ps",
                "N" : "s"
            };
            // 默认展开全部节点
            tree.expandAll(true);
        }
    });
</script>
</head>

<body>
    <ul class="nav nav-tabs">
        <li>
            <span><a href="${ctx}/module/villageLine/">产品管理 </a>> <a href="${ctx}/module/villageLine/">设置管理 > </a><a>设置模块</a></span>
        </li>
    </ul>
    <form:form id="inputForm" modelAttribute="villageLine" action="${ctx}/module/villageLine/batchSetModule" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">产品线：</label>
            <div class="controls">
                <form:radiobuttons path="productLine" items="${fns:getDictList('product_line')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">主导航</label>
            <div class="controls">
                <label>首页【1】 <input type="hidden" value="0">
                </label>
                <form:checkboxes items="${fns:getDictList('mainNavigation')}" path="mainNavigationIds" itemLabel="label" itemValue="value" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模块设置</label>
            <div class="controls">
                <table style="margin: 0px" class="table table-bordered">
                    <tr>
                        <td style="width: 15%;">社区</td>
                        <td>
                            <div style="padding: 10px; float: left;">
                                <form:checkboxes items="${communityModuleList}" path="communityModuleIds" itemLabel="moduleName" itemValue="id" class="required" />
                            </div>
                            <div style="float: left; width: 100%; height: 1px; background-color: #eee"></div> <!--选中的生活模块  -->
                            <div id="addCommunityModule" style="padding: 10px; float: left;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>生活</td>
                        <td>
                            <div style="padding: 10px; float: left;">
                                <form:checkboxes items="${lifeModuleList}" path="lifeModuleIds" itemLabel="moduleName" itemValue="id" class="required" />
                            </div>
                            <div style="float: left; width: 100%; height: 1px; background-color: #eee"></div> <!--选中的社区模块  -->
                            <div id="addLifeModule" style="padding: 10px; float: left;"></div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">选择楼盘：</label>
            <div class="controls">
                <ul id="tree" class="ztree" style="border: 1px solid #ccc; padding: 10px; width: 200px;"></ul>
                <form:hidden path="villageIdList" />
                <input class="valiVillage" name="valiVillage" style="width: 0px; height: 0px; border: 0px">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>

</html>