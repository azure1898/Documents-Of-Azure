<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
        $("#no").focus();
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
                $("#villageInfoIds").val(ids);
                return true;
            }
        }, "请选择楼盘权限");
        jQuery.validator.addMethod("checkName", function(value, element,params) {
	        var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
	        return this.optional(element) || (patrn.test(value));
	    }, "请输入中文，英文或数字");
        $("#inputForm").validate({
            rules : {
                loginName : {
                    remote : "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
                },
                mobile : {
                    required : true,
                    minlength : 11,
                    isMobile : true
                },
                villageInfoIds : {
                    checkVillage : "param",
                },
                name:{
                    checkName:"params"
                }
                
            },
            messages : {  
                loginName : {
                    required:"请输入用户名",
                    remote : "用户登录名已存在"
                },
                newPassword:{
                    required:"请输入密码",
                },
                name:{
                    required:"请输入姓名",
                },
                mobile:{
                    required:"请输入手机号",
                    minlength : "确认手机不能小于11个字符",
                    isMobile : "请正确填写您的手机号码"
                },
                roleIdList:{
                    required:"请选择角色权限",
                },
                villageInfoIds : {
                    checkVillage : "请选择楼盘权限",
                }
               
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
</script>
</head>
<body>

    <ul class="nav nav-tabs">
        <li>
             <span>系统管理 > <a href="${ctx}/sys/user/list">用户管理 > </a>${not empty user.id?'修改':'添加'}用户
            </span>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="userType" value="1" />
        <sys:message content="${message}" />

        <div class="control-group" style="display: none">
            <label class="control-label">归属公司:</label>
            <div class="controls">
                <sys:treeselect id="company" name="company.id" value="1" labelName="company.name" labelValue="${user.company.name}" title="公司" url="/sys/office/treeData?type=1" cssClass="required" />
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">归属部门:</label>
            <div class="controls">
                <sys:treeselect id="office" name="office.id" value="1" labelName="office.name" labelValue="${user.office.name}" title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true" />
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">工号:</label>
            <div class="controls">
                <form:input path="no" htmlEscape="false" value="0002" maxlength="50" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group" style="display: none">
            <label class="control-label">用户状态:</label>
            <div class="controls">
                <form:input path="loginFlag" value="1" htmlEscape="false" maxlength="100" />
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">用户名:</label>
            <div class="controls">
                <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                <form:input id="aaa" path="loginName" htmlEscape="false" maxlength="50" class="required userName" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">密码:</label>
            <div class="controls">
                <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}" />
                <c:if test="${empty user.id}">
                    <span class="help-inline"><font color="red">*</font> </span>
                </c:if>
                <c:if test="${not empty user.id}">
                    <span class="help-inline">若不修改密码，请留空。</span>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">姓名:</label> 
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="50" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group"> 
            <label class="control-label">手机号:</label>  
            <div class="controls">
                <form:input path="mobile" htmlEscape="false" class="required " maxlength="11" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">角色权限:</label>
            <div class="controls">
                <form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">楼盘权限：</label>
            <div class="controls">
                <ul id="tree" class="ztree" style="border: 1px solid #ccc; padding: 10px; width: 200px;"></ul>
                <form:input path="villageInfoIds" style="width: 0px; height: 0px; border: 0px"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="sys:user:edit">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>