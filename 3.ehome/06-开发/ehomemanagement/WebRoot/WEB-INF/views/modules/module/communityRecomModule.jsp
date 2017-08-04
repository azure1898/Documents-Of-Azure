<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
<title>楼盘信息产品线管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
        //添加主导航验证
        $.validator.addMethod("checkMaxSize", function(value, element, params) {
            var num = $("input[name='communityRecomModuleIds']:checked").size();
            console.log(num);
            if (num > 4) {
                return false;
            } else {
                return true;
            }
        }, "最多只能勾选4个模块");
        $.validator.addMethod("checkMinSize", function(value, element, params) {
            var num = $("input[name='communityRecomModuleIds']:checked").size();
            console.log(num);
            if (num < 4) {
                return false;
            } else {
                return true;
            }
        }, "社区推荐模块推荐个数不足4个");
        $("#inputForm").validate({
            rules : {
                communityRecomModuleIds : {
                    checkMaxSize : "param",
                    checkMinSize : "param"
                }
            },
            messages : {
                communityRecomModuleIds : {
                    checkMinSize : "首页推荐模块推荐个数不足4个",
                    checkMaxSize : "最多只能勾选4个模块"
                },
            },
            submitHandler : function(form) {
                var communityRecomIds="";
                $("#addCommunityRecomModule").find(".communityRecom").each(function(i,dom){
                    communityRecomIds+= $(dom).attr("id")+",";
                })
                $("#communityRecomModule").val(communityRecomIds);
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

        $("input[name='communityRecomModuleIds']").click(function() {
            $("#addCommunityRecomModule").show();
            var id = $(this).val();
            var total = $("#addCommunityRecomModule").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span class="communityRecom" id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addCommunityRecomModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").hide().removeClass('communityRecom');
            }
        })
        var alist=${fns:toJson(getCommunityList)};
        for (var i=0; i<alist.length; i++){
            var domRow = '<span class="communityRecom" id="' +alist[i].id + '"><lable>' +alist[i].moduleName + '</lable><lable class="lable-num">' + (i+1) + '</lable></span>';
            $("#addCommunityRecomModule").append($(domRow));
        }
        
        var len=$("#addCommunityRecomModule").children().size();
        if(len>0){
            $("#addCommunityRecomModule").show();
        }else{
            $("#addCommunityRecomModule").hide();
        }
    });
</script>
</head>

<body>
    <ul class="nav nav-tabs">
        <li>
            <span><a href="${ctx}/module/villageLine/recommendList">模块管理 </a>> <a href="${ctx}/module/villageLine/recommendList">推荐管理 > </a><a>设置管理</a></span>
        </li>
    </ul>
    <ul class="nav nav-tabs">
         <li>
            <a href="${ctx}/module/villageLine/mainRecomFrom?id=${villageLine.id}">首页推荐</a>
        </li>
        <li class="active">
            <a href="${ctx}/module/villageLine/communityRecomFrom?id=${villageLine.id}">社区推荐</a>
        </li>
        <li >
            <a href="${ctx}/module/villageLine/lifeRecomFrom?id=${villageLine.id}">生活推荐 </a>
        </li>
    </ul>
    <form:form id="inputForm" modelAttribute="villageLine" action="${ctx}/module/villageLine/updateCommunityRecomModule" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <input id="communityRecomModule" type="hidden" name="communityRecomModule" value="${villageLine.communityRecomModule}">
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">楼盘名称</label>
            <div class="controls">
                <label>${villageLine.villageInfo.villageName }</label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">社区推荐模块</label>
            <div class="controls" style="border: 1px solid #ccc; padding: 5px;">
                <div style="border: 1px solid #ccc; padding: 20px" id="addCommunityRecomModule"></div>
                <div style="border: 1px solid #ccc; padding: 20px; margin-top: 20px;">
                    <form:checkboxes items="${moduleList}" path="communityRecomModuleIds" itemLabel="moduleName" itemValue="id" class="required" />
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;
            </shiro:hasPermission>
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="" class="btn btn-success" type="button" value="预览" />&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>

</html>