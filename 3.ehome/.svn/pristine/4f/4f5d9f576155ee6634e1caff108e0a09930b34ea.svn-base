<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" />
<script type="text/javascript">
    $(document).ready(function() {
        if ($("#soundWarn").val() == 1) {
            $("#soundWarn").attr("checked", "checked")
        }
        if ($("#stockWarn").val() == 1) {
            $("#stockWarn").attr("checked", "checked")
        } else {
            $("select[name='stockWarnNum']").prop("disabled", true);
        }
        $("#inputForm").validate({
            rules : {},
            messages : {
                confirmNewPassword : {
                    equalTo : "输入与上面相同的密码"
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
        /*是否启用自定义单位  */
        $("#soundWarn").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(this).val("1");
                console.log( $(this).val())
            } else {
                $(this).val("0");
                console.log( $(this).val())
            }
            updateSetting();
           console.log( $(this).val())
        })
        $("#stockWarn").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(this).val("1");
                $("select[name='stockWarnNum']").prop("disabled", "");
            } else {
                $(this).val("0");
                $("select[name='stockWarnNum']").prop("disabled", true);
            }
           updateSetting();
        })
        $("#stockWarnNum").change(function() {
            console.log( $("#stockWarnNum").val());
            updateSetting();
        })
    });
    
    function updateSetting() {
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/updateSetting',
            data : {
                soundWarn:$("#soundWarn").val(),
                stockWarn:$("#stockWarn").val(),
                stockWarnNum:$("#stockWarnNum").val()
            },
            success : function(data) {
                if (data == '') {
                    loading('正在提交，请稍等...');
                    form.submit();
                } else {
                    top.$.jBox.tip(data, 'success');
                }
            }
        })
    }
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><span><a href="${ctx}/setup/businessInfo/">商家设置 ></a>系统设置</span></li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="businessInfo" action="${ctx}/setup/businessInfo/modifyPwd" method="post" class="form-horizontal">
        <sys:message content="${message}" />
        <div class="control-group" style="background: #F0F0F0; width: 700px; padding-top: 20px">
            <label class="control-label" style="font-size: 14px; font-weight: bold; text-align: left; margin-left: 20px;">修改密码</label>
            <div class="controls">
                <input id="newPassword" placeholder="请输入新密码" name="newPassword" type="password" value="" maxlength="12" minlength="3" class="input-medium required" /> <input id="confirmNewPassword" placeholder="再次输入新密码" name="confirmNewPassword" type="password" value="" maxlength="12" minlength="3" class="input-medium required" equalTo="#newPassword" />
                <shiro:hasPermission name="setup:businessInfo:modifyPwd">
                    <input id="btnSubmit" class="btn btn-success" type="submit" value=" 修改密码 " />
                </shiro:hasPermission>
                <span class="help-inline">密码可输入英文或数字，不超过12个字。</span>
            </div>

        </div>
    </form:form>
    <form:form id="" modelAttribute="businessInfo" action="" method="post" class="form-horizontal">
        <div class="control-group" style="background: #F0F0F0; width: 700px; padding-top: 20px">
            <label class="control-label" style="font-size: 14px; font-weight: bold; text-align: left; margin-left: 20px;">提醒设置</label>
            <div class="controls">
                <input name="soundWarn" id="soundWarn" value="${businessInfo.soundWarn}" type="checkbox" />声音提醒 <span class="help-inline">（有新订单时、库存告罄时将有声音提醒）</span>
            </div>
            <div class="controls" style="margin-top: 10px">
                <input name="stockWarn" id="stockWarn" value="${businessInfo.stockWarn}" type="checkbox" />库存提醒
                <form:select path="stockWarnNum" class="input-medium">
                    <form:options items="${fns:getDictList('stock_warn_num')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </div>
        </div>
    </form:form>
</body>
</html>