<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ajax文件上传</title>
    <link href="${ctx}/static/bootstrap-3.3.0/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form class="form-horizontal" role="form" id="form-upload" enctype="multipart/form-data">
        <div class="form-group">
            <label>请选择要上传的图片</label>
            <input type="file" name="uploadFile">
            <input type="file" name="uploadFile">
            <input type="file" name="uploadFile">
            <input type="file" name="uploadFile">
            <div class="row" id="imgs">
                <div class="col-xs-6 col-md-4">

                </div>
            </div>
        </div>
        <button type="button" class="btn btn-primary" id="btn-save">提交</button>
    </form>
</div>
<script src="${ctx}/static/jquery-1.11.1/jquery.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap.js"></script>
<script src="${ctx}/static/jquery-form-3.51/jquery.form.js"></script>
<script>
    $(document).ready(function () {
        $("#btn-save").click(function () {

            $("#form-upload").ajaxSubmit({
                type: 'post',
                url: "${ctx}/imgupload",
                success: function (data) {
                    if (data.success) {
                        //alert(data.url);
                        for (var i in data.url){
                            //回显图片信息
                            $("#imgs").append('<img src="' + data.url[i] + '" class="img-thumbnail">');
                        }
                    }
                }
            });
        });
    });
</script>
</body>
</html>
