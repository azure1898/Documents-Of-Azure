$(function() {
    var delParent;
    var idFile;
    var defaults = {
        fileType : [ "jpg", "png", "bmp", "jpeg" ], // 上传文件的类型
        fileSize : 1024 * 1024 * 1
    // 上传文件的大小 1M
    };
    var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
    if (userAgent.indexOf("Chrome") > -1) {
        $(".file").addClass("fileForChrome");
        $(".file").removeClass("file");
    }
    /* 点击图片的文本框 */
    $(".fileForChrome").change(function() {
        idFile = $(this).attr("id");
        var file = document.getElementById(idFile);
        var imgContainer = $(this).parents(".z_photo"); // 存放图片的父亲元素
        var fileList = file.files; // 获取的图片文件
        var input = $(this).parent();// 文本框的父亲元素
        // 遍历得到的图片文件
        var numUp = imgContainer.find(".up-section").length;
        var totalNum = numUp + fileList.length; // 总的数量
        if (fileList.length = 0) {
            alertx("未选择任何文件；");
            return;
        } else if (numUp < 1) {
            var img = new Image();
            img.onload = function() {
                $(obj).show();
                console.log(this.width + '\n' + this.height);
                if (fileList[0].size > 1024 * 1024) {
                    alertx('您这个"' + fileList[0].name + '"图片大小超过1M。');
                } else if (this.height > 640) {
                    alertx('您这个"' + fileList[0].name + '"图片高度超过640像素。');
                } else if (this.width >= 400) {
                    alertx('您这个"' + fileList[0].name + '"图片宽度超过400像素。');
                } else {
                    var $section = $("<section class='up-section fl loading'>");
                    imgContainer.prepend($section);
                    var $span = $("<span class='up-span'>");
                    $span.appendTo($section);
                    var $img0 = $("<img class='close-upimg'>").on("click", function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                        $(".works-mask").show();
                        delParent = $(this).parent();
                    });
                    $img0.attr("src", "/ehomemanagement/static/images/a7.png").appendTo($section);
                    var $img = $("<img class='up-img up-opcity' + name='imgName" + 0 + "'>");
                    $img.attr("src", "");
                    $img.appendTo($section);
                    var reader = new FileReader();
                    reader.readAsDataURL(fileList[0]);
                    console.log();
                    reader.onload = function(e) {
                        document.getElementsByName("imgName0")[0].src = this.result;
                    }
                    setTimeout(function() {
                        $(".up-section").removeClass("loading");
                        $(".up-img").removeClass("up-opcity");
                    }, 450);
                    numUp = imgContainer.find(".up-section").length;
                    if (numUp >= 1) {
                        $(".fileForChrome").parent().hide();
                        $(".file").parent().hide();
                    }
                }
            }
            img.src = window.URL.createObjectURL(fileList[0]);
        }
    });
    /* 点击图片的文本框 */
    $(".file").change(function() {
        idFile = $(this).attr("id");
        var file = document.getElementById(idFile);
        var imgContainer = $(this).parents(".z_photo"); // 存放图片的父亲元素
        var fileList = file.files; // 获取的图片文件
        var input = $(this).parent();// 文本框的父亲元素
        // 遍历得到的图片文件
        var numUp = imgContainer.find(".up-section").length;
        var totalNum = numUp + fileList.length; // 总的数量
        if (fileList.length = 0) {
            alertx("未选择任何文件；");
            return;
        } else if (numUp < 1) {
            var img = new Image();
            img.onload = function() {
                $(obj).show();
                console.log(this.width + '\n' + this.height);
                if (fileList[0].size > 1024 * 1024) {
                    alertx('您这个"' + fileList[0].name + '"图片大小超过1M。');
                } else if (this.height > 640) {
                    alertx('您这个"' + fileList[0].name + '"图片高度超过640像素。');
                } else if (this.width >= 400) {
                    alertx('您这个"' + fileList[0].name + '"图片宽度超过400像素。');
                } else {
                    var $section = $("<section class='up-section fl loading'>");
                    imgContainer.prepend($section);
                    var $span = $("<span class='up-span'>");
                    $span.appendTo($section);
                    var $img0 = $("<img class='close-upimg'>").on("click", function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                        $(".works-mask").show();
                        delParent = $(this).parent();
                    });
                    $img0.attr("src", "/ehomemanagement/static/images/a7.png").appendTo($section);
                    var $img = $("<img class='up-img up-opcity'  name='imgName0'>");
                    $img.attr("src", "");
                    $img.appendTo($section);
                    var reader = new FileReader();
                    reader.readAsDataURL(fileList[0]);
                    console.log();
                    reader.onload = function(e) {
                        document.getElementsByName("imgName0")[0].src = this.result;
                    }
                    setTimeout(function() {
                        $(".up-section").removeClass("loading");
                        $(".up-img").removeClass("up-opcity");
                    }, 450);
                    numUp = imgContainer.find(".up-section").length;
                    if (numUp >= 1) {
                        $(".fileForChrome").parent().hide();
                        $(".file").parent().hide();
                    }
                }
            }
            img.src = window.URL.createObjectURL(fileList[0]);
        }
    });
    $(".z_photo").delegate(".close-upimg", "click", function() {
        $(".works-mask").show();
        delParent = $(this).parent();
    });
    $(".wsdel-ok").click(function() {
        $(".works-mask").hide();
        var numUp = delParent.siblings().length;
        if (numUp < 2) {
            delParent.parent().find(".z_file").css('display', 'inline-block').css('vertical-align', 'top');
            delParent.parent().find(".z_file").show();
        }
        delParent.remove();
        if (idFile) {
            var file = document.getElementById(idFile);
            file.value = "";
        }
    });

    $(".wsdel-no").click(function() {
        $(".works-mask").hide();
    });
})
