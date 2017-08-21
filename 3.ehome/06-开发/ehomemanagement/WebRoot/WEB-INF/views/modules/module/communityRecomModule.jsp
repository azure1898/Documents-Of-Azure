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
                    required : "请选择社区推荐模块",
                    checkMinSize : "社区推荐模块推荐个数不足4个",
                    checkMaxSize : "最多只能勾选4个模块"
                },
            },
            submitHandler : function(form) {
                var communityRecomIds="";
                $("#addCommunityRecomModule").find(".communityRecom").each(function(i,dom){
                    communityRecomIds+= $(dom).attr("id")+",";
                })
                $("#communityRecomModule").val(communityRecomIds);
                var communityRecomSort="";
                $("#addCommunityRecomModule").find(".communityRecom").each(function(i,dom){
                    communityRecomSort+= $(dom).find("input").val()+",";
                })
                $("#communityRecomModuleSort").val(communityRecomSort);
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
            var total = getMaxNum($("#addCommunityRecomModule"));
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span class="communityRecom" id="' + $(this).val() + '">'
                +'  <lable>' + $(this).next().text() 
                +'  </lable><input style="width:20px;" class="required number" maxlength="2" value="'+total+'"></span>';
                $("#addCommunityRecomModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").hide().removeClass('communityRecom');
            }
        })
        var alist=${fns:toJson(getCommunityList)};
        var arrCommunity=$("#communityRecomModuleSort").val().split(",")
        for (var i=0; i<alist.length; i++){
            var domRow = '<span class="communityRecom" id="' +alist[i].id + '">'
            + '  <lable>' +alist[i].moduleName + '</lable>'
            + '  <input style="width:20px;" class="required number" maxlength="2" value="'+arrCommunity[i]+'"></span>';
            $("#addCommunityRecomModule").append($(domRow));
        }
        
        var len=$("#addCommunityRecomModule").children().size();
        if(len>0){
            $("#addCommunityRecomModule").show();
        }else{
            $("#addCommunityRecomModule").hide();
        }
    });
    //获取以选取的最大值
    function getMaxNum(obj){
        var maxNum=0;
        $(obj).find(".number").each(function(i,dom){
            var newNum=Number($(this).val());
            if(newNum>=maxNum){
               maxNum=newNum;
           }
        })
        return (maxNum+1);
    }
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
        <input id="communityRecomModuleSort" type="hidden" name="communityRecomModuleSort" value="${villageLine.communityRecomModuleSort}">
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
                     <c:choose>
                        <c:when test="${moduleList.size()>0}">
                            <form:checkboxes items="${moduleList}" path="communityRecomModuleIds" itemLabel="moduleName" itemValue="id" class="required" />
                        </c:when>
                        <c:otherwise>
                            <font color="red">没有可选择的模块，请在"模块管理"-"设置管理" 进行楼盘模块设置 </font>
                            <form:input path="communityRecomModuleIds" style="width: 0px; height: 0px; border: 0px;opacity: 0;"  class="required" />
                        </c:otherwise>
                    </c:choose>
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="保 存" />&nbsp;
            </shiro:hasPermission>
            <shiro:hasPermission name="module:villageLine:batchSetModule">
                <input id="" onclick="showPage()" class="btn btn-success" type="button" value="预览" />&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn btn-success" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
        <script src="${ctxStatic}/common/layer.js"></script>
        <script type="text/javascript">
            function showPage(){
                layer.open({
                    type: 2,
                    title:'社区推荐预览',
                    area: ['414px', '736px'],
                    scrollbar: true,
                    maxmin: true,
                    content: '//',
                    zIndex: layer.zIndex, //重点1
                    success: function(layero){
                        layer.setTop(layero); //重点2
                    },
                });
            }
        </script>
    </form:form>
</body>

</html>