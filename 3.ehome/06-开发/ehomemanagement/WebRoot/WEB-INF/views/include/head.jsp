<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://its111.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/custom.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/font/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/font/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/itssite.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/itssite.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/fileUpload.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/listJspCommon.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/address.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.0/lib/jquery.form.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/validateUtils.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
<script type=text/javascript src="${ctxStatic}/common/xheditor-1.2.2.min.js"></script>
<script type=text/javascript src="${ctxStatic}/common/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/lang/zh-CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/code/prettify.js"></script>
<link href="${ctxStatic}/common/common.css" type="text/css" rel="stylesheet" />
<!--可以在地址栏中显示出图标-->
<link rel="icon" href="${ctxStatic}/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctxStatic}/images/favicon.ico" type="image/x-icon" />
<!--可以在收藏夹中显示出图标-->
<link rel="bookmark" href="${ctxStatic}/images/favicon.ico" type="image/x-icon" />
<script>
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//校验电话号码（手机、固定电话）
jQuery.validator.addMethod("isPhoneNumber", function(value, element) {
    return validatePhoneNumber(value);
}, "请正确填写电话号码");
</script>