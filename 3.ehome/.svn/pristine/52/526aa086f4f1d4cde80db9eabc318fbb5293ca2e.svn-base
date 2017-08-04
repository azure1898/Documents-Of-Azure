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
                mainNavigationIds : {
                    checkSize : "主导航勾选数量只能为2个"
                },
                lifeModuleIds : {
                    required : "请选择生活模块"
                },
                communityModuleIds : {
                    required : "请选择社区模块"
                }
            },
            submitHandler : function(form) {
              //给社区模块赋值
                var communtyIds="";
                $("#addCommunityModule").find(".community").each(function(i,dom){
                    communtyIds+= $(dom).attr("id")+",";
                })
                $("#communityModule").val(communtyIds);
                //给生活模块赋值
                var lifeIds="";
                $("#addLifeModule").find(".life").each(function(i,dom){
                    lifeIds+= $(dom).attr("id")+",";
                })
                $("#lifeModule").val(lifeIds);
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
            var total = $("#addCommunityModule").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span class="community" id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addCommunityModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").hide().removeClass('community');
            }
            var communtyIds="";
            $("#addCommunityModule").find(".community").each(function(i,dom){
                communtyIds+= $(dom).attr("id")+",";
            })
            console.log(communtyIds);
        })
        var alist=${fns:toJson(getCommunityModuleList)};
        console.log(alist);
        for (var i=0; i<alist.length; i++){
            var domRow = '<span class="community" id="' +alist[i].id + '"><lable>' +alist[i].moduleName + '</lable><lable class="lable-num">' + (i+1) + '</lable></span>';
            $("#addCommunityModule").append($(domRow));
        }
        
        $("input[name='lifeModuleIds']").click(function() {
            console.log();
            var id = $(this).val();
            var total = $("#addLifeModule").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span class="life" id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addLifeModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").hide().removeClass('life');
            }
        })

        var blist=${fns:toJson(getLifeModuleList)};
        console.log(alist);
        for (var i=0; i<blist.length; i++){
            var domRow = '<span class="life" id="' +blist[i].id + '"><lable>' +blist[i].moduleName + '</lable><lable class="lable-num">' + (i+1) + '</lable></span>';
            $("#addLifeModule").append($(domRow));
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
    <form:form id="inputForm" modelAttribute="villageLine" action="${ctx}/module/villageLine/setModule" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <input id="mainNavigation" type="hidden" value="${villageLine.mainNavigation}">
        <input id="communityModule" type="hidden" name="communityModule" value="${villageLine.communityModule}">
        <input id="lifeModule" type="hidden" name="lifeModule" value="${villageLine.lifeModule}">
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">主导航</label>
            <div class="controls">
                <label>首页【1】 <input type="hidden" value="0">
                </label>
                <form:checkboxes items="${fns:getDictList('mainNavigation')}" path="mainNavigationIds" itemLabel="label" itemValue="value" />
                <input class="maiSize" style="width: 0px; height: 0px; border: 0px">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模块设置</label>
            <div class="controls">
                <table style="margin: 0px" class="table table-bordered">
                    <tr>
                        <td  style="width: 15%;">社区</td>
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
        <div class="form-actions">
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
</body>

</html>