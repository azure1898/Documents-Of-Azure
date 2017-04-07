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
    <div class="row" id="imgs">
        <div class="img-template" style="display: none">
            <div class="col-sm-3 col-md-3">
                <div class="thumbnail">
                    <img/>
                    <div class="caption text-right">
                        <span class="label label-primary"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h5 style="margin-top: 0px">
        <div id="btn-select-file">上传文件</div>
    </h5>
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
            // 自动上传。
            auto: true,
            // swf文件路径
            swf: '${ctx}/static/webuploader-0.1.5/Uploader.swf',
            // 文件接收服务端。
            server: '${ctx}/upload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#btn-select-file',
            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 当有文件添加进来的时候
        uploader.on('fileQueued', function (file) {
            var template = $(".img-template").clone();
            template.find(".thumbnail").attr("id", file.id);
            //template.find("label").html(file.name+" ");
            // 创建缩略图
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    //template.replaceWith('<span>不能预览</span>');
                    //return;
                }
                template.find("img").attr("src", src);
                //回显图片信息
                $("#imgs").append(template.html());
            }, 1, 1);
        });

        //发生错误
        uploader.on('error', function (handler) {
            //alert(handler);
            if (handler == 'Q_TYPE_DENIED') {
                alert("上传的图片格式不正确！");
            }
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

        uploader.on('uploadSuccess', function (file, data) {
            $('#' + file.id).find('span').addClass("label-success").html('上传成功');
            $('#' + file.id).find('.caption').append('<a href="#" class="btn btn-danger btn-xs">删除</a>');
            $('#' + file.id).find("img").attr("src", data.url);
        });

        uploader.on('uploadError', function (file) {
            $('#' + file.id).find('span').addClass("label-danger").html('上传失败');
        });

        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
    });
</script>
</body>
</html>
