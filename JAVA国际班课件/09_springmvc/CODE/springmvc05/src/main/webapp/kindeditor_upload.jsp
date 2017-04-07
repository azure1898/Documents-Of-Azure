<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>kindedit文件上传</title>
    <link href="${ctx}/static/bootstrap-3.3.0/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/kindeditor-4.1.11/themes/default/default.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <form role="form" class="form-horizontal">
        <div class="form-group">
            <button type="button" id="uploadButton" value="选择图片"/>
        </div>
        <div class="row" id="imgs">
            <div class="img-template" style="display: none">
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <img/>
                    </a>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="${ctx}/static/jquery-1.11.1/jquery.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap.js"></script>
<script src="${ctx}/static/kindeditor-4.1.11/kindeditor.js"></script>
<script>
    $(document).ready(function () {
        KindEditor.ready(function (K) {
            var uploadbutton = K.uploadbutton({
                button: K('#uploadButton')[0],
                fieldName: 'uploadFile',
                url: '${ctx}/imgupload',
                afterUpload: function (data) {
                    if (data.success) {
                        for (var i in data.url) {
                            var template = $(".img-template").clone();
                            template.find("img").attr("src", data.url[i]);
                            //回显图片信息
                            $("#imgs").append(template.html());
                            // $("#imgs").append('<img src="' + data.url[i] + '" class="img-thumbnail">');
                        }
                    } else {
                        alert("上传图片出错");
                    }
                },
                afterError: function (e) {
                    alert('自定义错误信息: ' + e);
                }
            });
            uploadbutton.fileBox.change(function () {
                uploadbutton.submit();
            });
            //重新定义上传按钮的样式
            $(".ke-upload-area")
                    .attr("style", "")
                    .find("span.ke-button-common")
                    .before("<button class='btn btn-primary' type='button'>选择图片</button>")
                    .remove();
        });
    });
</script>
</body>
</html>
