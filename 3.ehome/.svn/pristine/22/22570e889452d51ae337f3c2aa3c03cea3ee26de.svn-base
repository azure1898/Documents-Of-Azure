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
            var num = $("input[name='lifeRecomModuleIds']:checked").size();
            console.log(num);
            if (num > 4) {
                return false;
            } else {
                return true;
            }
        }, "最多只能勾选4个模块");
        $("#inputForm").validate({
            rules : {
                lifeRecomModuleIds : {
                    checkMaxSize : "param"
                }
            },
            messages : {
                lifeRecomModuleIds : {
                    required : "请选择模块",
                    checkMaxSize : "最多只能勾选4个模块"
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

        $("input[name='lifeRecomModuleIds']").click(function() {
            $("#addLifeRecomModule").show();
            var id = $(this).val();
            var total = $("#addLifeRecomModule").children().size() + 1;
            if ($(this).attr('checked') == "checked") {
                var domRow = '<span id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
                $("#addLifeRecomModule").append($(domRow));
            } else {
                $("span[id=" + id + "]").remove();
            }
        })

        $("input[name='lifeRecomModuleIds']:checked").each(function() {
            // 默认选中的社区模块 
            var total = $("#addLifeRecomModule").children().size() + 1;
            var domRow = '<span id="' + $(this).val() + '"><lable>' + $(this).next().text() + '</lable><lable class="lable-num">' + total + '</lable></span>';
            $("#addLifeRecomModule").append($(domRow));
        })
        var len = $("#addLifeRecomModule").children().size();
        if (len > 0) {
            $("#addLifeRecomModule").show();
        } else {
            $("#addLifeRecomModule").hide();
        }
        

    });
    //模块的change事件
    function getBuslist(obj,count) {
        var moduleId=$(obj).val();
        $.ajax({
            type : "POST",
            url : ctx + "/business/businessInfo/getBusListByModule",
            data : {
                moduleId : moduleId
            },
            dataType : "JSON",
            success : function(data) {
                $("#businessinfoId"+count).empty();
                var option = "<option value=''>商家名称</option>";
                $.each(data, function(indx, item) {
                    option += "<option value='"+item.id+"'>" + item.businessName + "</option>";
                })
                $("#businessinfoId"+count).append(option);
                $("#businessinfoId"+count).val($("#HidBusinessinfoId"+count).val());//修改初始时，带值选中
            }
        })
    };
    //添加商家推荐1
    function addRowModule(){
        var domRow='';
        var index=0;
        var total=$("#recomOne").find(".controls").size()-1;
        if(total==0){
            index=0;
        }else{
            index=total*2;
        }
        var describes1="";
        var describes2="";
        domRow+='<div class="controls"  id="moduleRow'+total+'" style="border: 1px solid #ccc;margin-top:10px;  padding: 20px;">';
        domRow+='<div id="recomModuleList'+index+'" style="padding-top: 10px;">'
            +'    <lable>推荐一</lable>'
            +'     <select id="module'+index+'" onchange="getBuslist(this,'+index+')"  name="recomModuleList['+index+'].recomModuleId" class="input-medium">'
            +'         <c:forEach items="${moduleList}" var="module">'
            +'             <option value="${module.id}" >${module.moduleName}</option>'
            +'         </c:forEach>'
            +'     </select>'
            +'     <select id="businessinfoId'+index+'" name="recomModuleList['+index+'].recomBusinessId" class="input-medium">'
            +'             <option value="">商家名称</option>'
            +'     </select> <input id="HidBusinessinfoId'+(index)+'" type="hidden"/>'
            +'     <input id="describes'+index+'" name="recomModuleList['+index+'].describes" type="text"  value="'+describes1+'"  maxlength="8" class="min:0 input-small required"/>'
            +'     <span> 不超过8个字 </span><input type="file" name="file'+index+'"  />'
            +'     <img id="moduleImg'+(index)+'" src="" style="width:45px; height:45;">'
            +'     <input id="recomModuleFlag'+index+'" type="hidden" name="recomModuleList['+index+'].delFlag"  />'
            +'     <input id="recomModuleId'+index+'" type="hidden" name="recomModuleList['+index+'].id"  />'
            +'  </div>';
         domRow+='<div id="recomModuleList'+(index+1)+'" style="padding-top: 10px;">'
            +'    <lable>推荐二</lable>'
            +'     <select id="module'+(index+1)+'" onchange="getBuslist(this,'+(index+1)+')"  name="recomModuleList['+(index+1)+'].recomModuleId" class="input-medium">'
            +'         <c:forEach items="${moduleList}" var="module">'
            +'             <option value="${module.id}" >${module.moduleName}</option>'
            +'         </c:forEach>'
            +'     </select>'
            +'     <select id="businessinfoId'+(index+1)+'" name="recomModuleList['+(index+1)+'].recomBusinessId" class="input-medium">'
            +'             <option value="">商家名称</option>'
            +'     </select><input id="HidBusinessinfoId'+(index+1)+'" type="hidden"/>'
            +'     <input id="describes'+(index+1)+'" name="recomModuleList['+(index+1)+'].describes" type="text"  value="'+describes2+'"  maxlength="8" class="min:0 input-small required"/>'
            +'     <span> 不超过8个字 </span><input type="file" name="file'+(index+1)+'"  /> '
            +'     <img id="moduleImg'+(index+1)+'" src="" style="width:45px; height:45;">'
            +'     <input id="recomModuleFlag'+(index+1)+'" type="hidden" name="recomModuleList['+(index+1)+'].delFlag"  />'
            +'     <input id="recomModuleId'+(index+1)+'" type="hidden" name="recomModuleList['+(index+1)+'].id"  />'
            +'  </div>';
            domRow+='</div>';
            domRow+='<div class="add-remove" style="float: right; margin-top: -20px; margin-right: -60px;"><a onclick="addRowModule()">添加</a>  <a onclick="removeRowModule(this,'+total+')">删除</a></div>';
            $("#recomOne").append($(domRow));
           
           
    }
    //商家推荐1 移除
    function removeRowModule(obj,index_num) {
        var index=0;
        if(index_num==0){
            index=0;
        }else{
            index=index_num*2;
        }
        var total = $("#recomOne").find(".controls").size()-1;//
        console.log($("#recomModuleId"+index).val());
        if($("#recomModuleId"+index).val()!=""&&$("#recomModuleId"+index_num).val()!=null){
            $(obj).parent().addClass("hide");
            $("#moduleRow"+index_num).addClass("hide");
            $("#recomModuleFlag"+index).val("1");
            $("#recomModuleFlag"+(index+1)).val("1")
        }else{
            $(obj).parent().remove();
            $("#moduleRow"+index_num).remove();
        }
    }
  //绑定商家推荐1
    function initRecomModule(){
        var data= ${fns:toJson(villageLine.recomModuleList)};
        console.log(data)
        for (var i=0; i<data.length/2-1; i++){
            addRowModule();
        }
        for (var i=0; i<data.length; i++){
            $("#recomModuleFlag"+i).val(data[i].delFlag);//给删除标记赋值
            $("#recomModuleId"+i).val(data[i].id);//给删除标记赋值
            $("#HidBusinessinfoId"+i).val(data[i].recomBusinessId);
            $("#module"+i).val(data[i].recomModuleId).trigger("change");
            $("#businessinfoId"+i).val(data[i].recomBusinessId);
            $("#describes"+i).val(data[i].describes);
            $("#moduleImg"+i).attr("src",data[i].picUrl);//给推荐详情的图片赋值
        }
    }
    //添加专题推荐1
    function addSpecial(){
        var total=$("#recomTwo").find(".controls").size();
        var domRow='<div class="controls" id="recomSpecialList'+total+'" style="border: 1px solid #ccc; margin-top: 10px; padding: 20px;">'
                +'  <label>专题名称 </label>'
                +'     <input id="recomSpecialId'+total+'" type="hidden" name="recomSpecialList['+(total)+'].id"  />'
                +'        <input id="specialName'+total+'" name="recomSpecialList['+total+'].specialName" value="" class="input-medium" maxlength="5" />'
                +'        <label>不超过5个字 </label>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐一: </label>'
                +'          <input id="recomSpecialDetailId'+total+'0" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[0].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'0\')" name="recomSpecialList['+total+'].recomSpecialDetailList[0].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'0\')" name="recomSpecialList['+total+'].recomSpecialDetailList[0].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select id="recomSpecialModule'+total+0+'"  name="recomSpecialList['+total+'].recomSpecialDetailList[0].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select> <input id="HidRecomBusinessModuleId'+total+'0" type="hidden"/>'
                +'          <input id="specialDescribes0" name="recomSpecialList['+total+'].recomSpecialDetailList[0].describes" class="input-small" type="text" />'
                +'          <span> 不超过8个字 </span> <input type="file" name="specialFile_'+total+'_0" /> '
                +'          <img id="specialImg'+total+'0" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐二: </label>'
                +'          <input id="recomSpecialDetailId'+total+'1" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[1].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'1\')" name="recomSpecialList['+total+'].recomSpecialDetailList[1].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'1\')" name="recomSpecialList['+total+'].recomSpecialDetailList[1].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select  id="recomSpecialModule'+total+1+'" name="recomSpecialList['+total+'].recomSpecialDetailList[1].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select>  <input id="HidRecomBusinessModuleId'+total+'1" type="hidden"/>'
                +'          <input id="specialDescribes1" name="recomSpecialList['+total+'].recomSpecialDetailList[1].describes" class="input-small" type="text" />'
                +'          <span> 不超过8个字 </span> <input type="file" name="specialFile_'+total+'_1" /> '
                +'          <img id="specialImg'+total+'1" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐三: </label>'
                +'          <input id="recomSpecialDetailId'+total+'2" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[2].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'2\')" name="recomSpecialList['+total+'].recomSpecialDetailList[2].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'2\')" name="recomSpecialList['+total+'].recomSpecialDetailList[2].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select  id="recomSpecialModule'+total+2+'" name="recomSpecialList['+total+'].recomSpecialDetailList[2].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select> <input id="HidRecomBusinessModuleId'+total+'2" type="hidden"/>'
                +'          <input id="specialDescribes2" name="recomSpecialList['+total+'].recomSpecialDetailList[2].describes" class="input-small" type="text" />'
                +'          <span> 不超过8个字 </span>  <input type="file" name="specialFile_'+total+'_2" /> '
                +'          <img id="specialImg'+total+'2" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐四: </label>'
                +'          <input id="recomSpecialDetailId'+total+'3" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[3].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'3\')" name="recomSpecialList['+total+'].recomSpecialDetailList[3].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'3\')" name="recomSpecialList['+total+'].recomSpecialDetailList[3].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select  id="recomSpecialModule'+total+3+'" name="recomSpecialList['+total+'].recomSpecialDetailList[3].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select>  <input id="HidRecomBusinessModuleId'+total+'3" type="hidden"/>'
                +'          <input id="specialDescribes3" name="recomSpecialList['+total+'].recomSpecialDetailList[3].describes" class="input-small"  type="text" />'
                +'          <span> 不超过8个字 </span> <input type="file" name="specialFile_'+total+'_3" />'
                +'          <img id="specialImg'+total+'3" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐五: </label>'
                +'          <input id="recomSpecialDetailId'+total+'4" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[4].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'4\')" name="recomSpecialList['+total+'].recomSpecialDetailList[4].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'4\')" name="recomSpecialList['+total+'].recomSpecialDetailList[4].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select  id="recomSpecialModule'+total+4+'" name="recomSpecialList['+total+'].recomSpecialDetailList[4].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select>  <input id="HidRecomBusinessModuleId'+total+'4" type="hidden"/>'
                +'          <input id="specialDescribes4" name="recomSpecialList['+total+'].recomSpecialDetailList[4].describes" class="input-small"  type="text" />'
                +'          <span> 不超过8个字 </span>  <input type="file" name="specialFile_'+total+'_4" />'
                +'          <img id="specialImg'+total+'4" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'      <div style="margin-top: 10px">'
                +'          <label> 推荐六: </label>'
                +'          <input id="recomSpecialDetailId'+total+'5" type="hidden" name="recomSpecialList['+(total)+'].recomSpecialDetailList[5].id" />'
                +'          <input onchange="getSpecialModule(this,0,\''+total+'5\')" name="recomSpecialList['+total+'].recomSpecialDetailList[5].recomType" value="0" type="radio" />'
                +'          <span>商家列表</span>'
                +'          <input onchange="getSpecialModule(this,1,\''+total+'5\')" name="recomSpecialList['+total+'].recomSpecialDetailList[5].recomType" value="1" type="radio" />'
                +'          <span>模块列表 </span>'
                +'          <select  id="recomSpecialModule'+total+5+'" name="recomSpecialList['+total+'].recomSpecialDetailList[5].recomBusinessModuleId" class="input-medium">'
                +'             <option value="">模块/商家</option>'
                +'          </select> <input id="HidRecomBusinessModuleId'+total+'5" type="hidden"/>'
                +'          <input id="specialDescribes5" name="recomSpecialList['+total+'].recomSpecialDetailList[5].describes" class="input-small"  type="text" />'
                +'          <span> 不超过8个字 </span>  <input type="file" name="specialFile_'+total+'_5" />'
                +'          <img id="specialImg'+total+'5" src="" style="width:45px; height:45;">'
                +'      </div>'
                +'     <div style="float: left;"></div><input id="recomSpecialFlag'+total+'" type="hidden"  value="0" name="recomSpecialList['+total+'].delFlag"  />'
                +'  </div>';
         domRow+='<div class="add-remove" style="float: right; margin-top: -20px; margin-right: -60px;"><a onclick="addSpecial()">添加</a>  <a onclick="removeRowSpecial(this,'+total+')">删除</a></div>';
        $("#recomTwo").append($(domRow));
    }
    //专题推荐 移除
    function removeRowSpecial(obj,index_num) {
        var total = $("#recomOne").find(".controls").size();//
        console.log($("#recomSpecialFlag"+index_num).val());
        if($("#recomSpecialFlag"+index_num).val()!=""&&$("#recomSpecialFlag"+index_num).val()!=null){
            $(obj).parent().addClass("hide");
            $("#recomSpecialList"+index_num).addClass("hide");
            $("#recomSpecialFlag"+index_num).val("1");
        }else{
            $(obj).parent().remove();
            $("#recomSpecialList"+index_num).remove();
        }
    }
  //绑定专题推荐
    function initRecomSpecial(){
        var data= ${fns:toJson(villageLine.recomSpecialList)};
        for (var i=0; i<data.length-1; i++){
            addSpecial();
        }
        for (var i=0; i<data.length; i++){
           $("#specialName"+i).val(data[i].specialName);//给专题名称赋值
           $("#recomSpecialFlag"+i).val(data[i].delFlag);//给删除标记赋值
           $("#recomSpecialId"+i).val(data[i].id);//给删除标记赋值
            for (var j=0; j<data[i].recomSpecialDetailList.length; j++){
               $("#specialDescribes"+j).val(data[i].recomSpecialDetailList[j].describes);
               $("#HidRecomBusinessModuleId"+i+j).val(data[i].recomSpecialDetailList[j].recomBusinessModuleId);
               $("#recomSpecialDetailId"+i+j).val(data[i].recomSpecialDetailList[j].id);//给推荐详情的ID赋值
               $("#specialImg"+i+j).attr("src",data[i].recomSpecialDetailList[j].picUrl);//给推荐详情的图片赋值
               var recomType=data[i].recomSpecialDetailList[j].recomType;
               $(":radio[name='recomSpecialList["+i+"].recomSpecialDetailList["+j+"].recomType'][value='" + recomType + "']").prop("checked", "checked").trigger("change");
            }
        }
    }
    //模块的change事件 
    function getSpecialModule(obj,type,count) {
        $("#recomSpecialModule"+count).empty();
        if(type==0){
            var businessList= ${fns:toJson(allBusList)};
            var option = "<option value=''>商家名称</option>";
            for (var i=0; i<businessList.length; i++){
                option += "<option value='"+businessList[i].id+"'>" + businessList[i].businessName + "</option>";
               
            }
            $("#recomSpecialModule"+count).append(option);
            $("#recomSpecialModule"+count).val($("#HidRecomBusinessModuleId"+count).val());
        }else{
            var moduleList= businessList= ${fns:toJson(moduleList)};
            var option = "<option value=''>模块名称</option>";
            for (var i=0; i<moduleList.length; i++){
                option += "<option value='"+moduleList[i].id+"'>" + moduleList[i].moduleName + "</option>";
            }
            $("#recomSpecialModule"+count).append(option);
            $("#recomSpecialModule"+count).val($("#HidRecomBusinessModuleId"+count).val());
        }
        
    };

    
    $(function(){
        //初始化推荐商家1的模块
        addRowModule();
        //初始化推荐商家1的数据
        initRecomModule();
        
        //初始话专题推荐1的模块
        addSpecial();
        //初始话专题推荐1的数据
        initRecomSpecial();
        
       
    })
</script>
</head>

<body>
    <ul class="nav nav-tabs">
        <li>
            <span>
                <a href="${ctx}/module/villageLine/recommendList">模块管理 </a>> <a href="${ctx}/module/villageLine/recommendList">推荐管理 > </a><a>设置管理</a>
            </span>
        </li>
    </ul>
    <ul class="nav nav-tabs">
        <li>
            <a href="${ctx}/module/villageLine/mainRecomFrom?id=${villageLine.id}">首页推荐</a>
        </li>
        <li>
            <a href="${ctx}/module/villageLine/communityRecomFrom?id=${villageLine.id}">社区推荐</a>
        </li>
        <li class="active">
            <a href="${ctx}/module/villageLine/lifeRecomFrom?id=${villageLine.id}">生活推荐 </a>
        </li>
    </ul>
    <form:form id="inputForm" style="margin: 0 50px;" modelAttribute="villageLine" action="${ctx}/module/villageLine/updateLifeRecomModule" method="post" class="form-horizontal" enctype="multipart/form-data">
        <form:hidden path="id" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">楼盘名称</label>
            <div class="controls">
                <label>${villageLine.villageInfo.villageName }</label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">首页推荐模块<br>可推荐4个
            </label>
            <div class="controls" style="border: 1px solid #ccc; padding: 20px" id="addLifeRecomModule"></div>
            <div class="controls" style="border: 1px solid #ccc; padding: 20px; margin-top: 20px;">
                <form:checkboxes items="${lifeModuleList}" path="lifeRecomModuleIds" itemLabel="moduleName" itemValue="id" class="required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <!-- 商家推荐1 -->
        <div class="control-group" style="margin-right: 60px" id="recomOne">
            <label class="control-label">商家推荐1</label>
            <div class="controls">
                <label>（建议推荐2个或4个商家） </label>
            </div>
        </div>
        <!-- 专题推荐 -->
        <div class="control-group" style="margin-right: 60px" id="recomTwo">
            <label class="control-label">专题推荐</label>
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
<script type="text/javascript">
$("#path").change(function () {
    $("#docPath").val($(":file").val());
});
</script>

</html>