<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务分类管理</title>
	<meta name="decorator" content="default"/>
    <style>
    .commonbtn1 {
        font-family: MicrosoftYaHei;
        font-size: 14px;
        color: #60C1F7;
        background: #FFFFFF;
        border: 1px solid #60C1F7;
        border-radius: 6px;
        height:30px;
    }
    .commonbtn1:hover {
        font-family: MicrosoftYaHei;
        font-size: 14px;
        color: #60C1F7;
        background: #F1FAFF;
        border: 1px solid #60C1F7;
        border-radius: 6px;
        height:30px;
    }
    </style>
	<script type="text/javascript">
    $(document).ready(function() {
        //$("#name").focus();
        $.validator.addMethod("checkChEnNumFormat",function(value,element,params){
        	//中文、英文或数字
        	var patrn=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
			return value == patrn.exec(value);
		},"请输入中文、英文或数字");
        $("#inputForm").validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 6,
                    checkChEnNumFormat: true,
                    remote: {
                        url: "${ctx}/service/serviceSortInfo/sortNameCheck",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式   
                        data: {                     //要传递的数据
                            sortName: function() {
                                return $("#name").val();
                            },
                            id: function() {
                                return $("#id").val();
                            }
                        }
                    }
                },
                sortOrder: {
                    required: true,
                    range:[0,999],
                    digits: true
                }
            },
            messages: {
                name: {
                    required: "请输入分类名称",
                    maxlength: "分类名称在6个字以内",
                    remote: "分类名称有重复，请修改"
                },
                sortOrder: {
                    required: "请输入排序号",
                    range: "请输入0~999的自然数",
                    digits: "请输入0~999的自然数"
                }
            },
        submitHandler: function(form){
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
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        // 将要修改的分类信息显示在编辑栏
        function editSort(id,name,sortOrder) {
            $("#id").val(id);
            $("#name").val(name);
            $("#sortOrder").val(sortOrder);
            $("#name").removeData("previousValue");
            $("#sortOrder").removeData("previousValue");
            $("label[for='name']").remove();
            $("label[for='sortOrder']").remove();
        }
        // 分类删除二次弹框确认
        function delConfirm(href) {
            return confirmx('确认要删除此服务分类么？', href);
        }
	</script>
</head>
<body>
        <c:choose>
		  <c:when test="${msgType != null and msgType != ''}">
		      <sys:message content="${message}" type="${msgType}"/>
		  </c:when>
		  <c:otherwise>
		      <sys:message content="${message}"/>
		  </c:otherwise>
 		</c:choose>
        <div id="left" style="float:left ;  width:40%;  height:100%;">
            <div>
                <p><span>分类列表</span></p>
            </div>
            <div>
            <form:form id="searchForm" modelAttribute="" action="${ctx}/service/serviceSortInfo" method="post" class="">
                <table id="contentTable" class="table table-striped table-bordered table-condensed">
                    <tbody>
                        <c:forEach items="${list}" var="sortInfo">
                            <tr>
                            <td style="width:70%">
                                ${sortInfo.name}
                            </td>
                            <td style="width:30%">
                               <input id="btuElemEdit" type="button" class="commonsmallbtn" value="编辑" style="width:40px;" onclick="editSort('${sortInfo.id}','${sortInfo.name}','${sortInfo.sortOrder}')">
                               <div style="margin:0 auto;height:3px;"></div>
                               <input id="btuElemDelete" type="button" class="commonsmallbtn" value="删除" style="width:40px;" onclick="top.$.jBox.tip.mess = null;delConfirm('${ctx}/service/serviceSortInfo/delete?id=${sortInfo.id}')">
                            </td>
                            </tr>
                       </c:forEach>
                   </tbody>
               </table>
               <span>未建立分类时，新增商品时可不选分类，建立分类后，新增商品时必须选择分类。</span>
               </form:form>
            </div>
        </div>
        <div id="center" style="float:left ;  width:5%;  height:100%;">
            <p><span> </span></p>
        </div>
        <div id="right" style="float:left ;  width:45%; height:100%;">
            <div>
                <p><span>新增/编辑分类</span></p>
            </div>
            <div>
    <form:form id="inputForm" modelAttribute="sortInfo" action="${ctx}/service/serviceSortInfo/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <div class="control-group">
            <label class="control-label" style="width:60px"><span class="help-inline"><font color="red">*</font> </span>名称：</label>
            <div class="controls" style="margin-left:80px">
                <form:input path="name" placeholder="请输入分类名称" htmlEscape="false" maxlength="6" class="input-xlarge"/>
                <br/>
                <span>分类名称可以是中文、英文或数字，不超过6个字。
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"  style="width:60px"><span class="help-inline"><font color="red">*</font> </span>排序：</label>
            <div class="controls"  style="margin-left:80px">
                <form:input path="sortOrder" placeholder="请输入排序号" htmlEscape="false" maxlength="3" class="input-xlarge number"/>
                <br/>
                <span>排序号应为0~999的自然数若两个分类排序号相同，则按照分类建立时间排序。
                </span>
            </div>
        </div>
        <div class="form-actions" style="background:#FFFFFF;border-top:0;">
            <input id="btnSubmit" class="commonbtn1" type="submit" value="保存分类"/>&nbsp;
        </div>
    </form:form>
            </div>
        </div>
</body>
</html>