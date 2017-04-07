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
    <title>webuploader文件上传</title>
    <link href="${ctx}/static/bootstrap-3.3.0/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/webuploader-0.1.5/css/webuploader.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <!--用来存放文件信息-->
    <div id="div-file-list"></div>
    <div class="row">
        <!--用来存放文件信息-->
        <div class="col-md-1">
            <button id="btn-upload-file" class="btn btn-primary">开始上传</button>
        </div>
        <div class="col-md-11">
            <h5 style="margin-top: 0px"><div id="btn-select-file">添加文件</div></h5>
        </div>
    </div>
</div>
<script src="${ctx}/static/jquery-1.11.1/jquery.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap.js"></script>
<script src="${ctx}/static/webuploader-0.1.5/js/webuploader.js"></script>
<script>
    $(document).ready(function () {
        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 不压缩image
            resize: false,
            // swf文件路径
            swf: '${ctx}/static/webuploader-0.1.5/Uploader.swf',
            // 文件接收服务端。
            server: '${ctx}/upload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#btn-select-file'
        });
        // 当有文件被添加进队列的时候
        uploader.on('fileQueued', function (file) {
            $("#div-file-list").append(
                    '<div id="' + file.id + '">' +
                    '<h4>' +
                    '<span class="label label-primary">准备上传</span>' + ' ' + file.name + '' +
                    '</h4>' +
                    '</div>');
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            var $percent = $('#' + file.id).find('.progress .progress-bar');
            // 避免重复创建
            if (!$percent.length) {
                $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo($('#' + file.id));
                $percent = $('#' + file.id).find('.progress .progress-bar');
            }
            $percent.css('width', percentage * 100 + '%');
            $('#' + file.id).find('span').addClass("label-info").html('上传中');
        });

        uploader.on('uploadSuccess', function (file) {
            $('#' + file.id).find('span').addClass("label-success").html('上传成功');
        });

        uploader.on('uploadError', function (file) {
            $('#' + file.id).find('span').addClass("label-danger").html('上传成功');
        });

        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });

        $("#btn-upload-file").on('click', function () {
            uploader.upload();
        });
    });
</script>
</body>
</html>
