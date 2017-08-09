// 判定当前浏览器类型
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

function openBrowse2(index) {
    document.getElementById("file_" + index).click();
    // $("#preview").attr("src","");
}

function setImagePreview2(btn_file, imgPreview, localImag,lable_size,lable_lable) {
    // 可以上传的文件类型
    var array = new Array('gif', 'jpeg', 'png', 'jpg', 'bmp');
    var docObj = $(btn_file);
    var docObjFile = $(btn_file)[0];// document.getElementById("btn_file");
    // 文件正则截取
    var fileContentType = "";
    if (docObj.val()) {
        fileContentType = docObj.val().match(/^(.*)(\.)(.{1,8})$/)[3];
    }
    var fileTypeFlg = false;

    var imgObjPreview = $(imgPreview)[0];// document.getElementById("imgPreview");
    // var imgObjPreview = document.getElementById("imgPreview_0")
    for ( var i in array) {
        if (!fileContentType || fileContentType.toLowerCase() == array[i].toLowerCase()) {
            if (docObjFile.files && docObjFile.files[0]) {
                // 火狐下，直接设img属性
                if (imgObjPreview) {
                    imgObjPreview.style.display = "";
                    imgObjPreview.style.width = "45px";
                    imgObjPreview.style.height = "45px";
                    // imgObjPreview.src = docObj.files[0].getAsDataURL();
                    // 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                    imgObjPreview.src = window.URL.createObjectURL(docObjFile.files[0]);
                    $(lable_lable).hide();
                    $(lable_size).show();
                    var img = new Image();
                    img.onload = function () {
                        $(obj).show();
                        console.log(this.width + '\n' + this.height);
                        $(lable_size).html("图片尺寸("+this.width+"X"+this.height+")");
                    }
                    img.src=imgObjPreview.src;
                    //$(lable_size).html("图片尺寸("+img.width+"X"+img.height+")");
                }
            } else if (Sys.ie) {
                // IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                var localImagId = $(localImag);// document.getElementById("localImag");
                // 必须设置初始大小
                localImagId.style.width = "45px";
                localImagId.style.height = "45px";
                // 图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.Pixelate(maxSquare=20)";
                    localImagId.filters.item("DXImageTransform.Microsoft.Pixelate").src = imgSrc;
                } catch (e) {
                    alertx("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                if (imgObjPreview) {
                    imgObjPreview.style.display = "none";
                }
                document.selection.empty();
            } else if (!Sys.ie) {
                // 取消选择图片
                if (imgObjPreview) {
                    imgObjPreview.style.display = "none";
                    imgObjPreview.style.width = "45px";
                    imgObjPreview.style.height = "45px";
                    imgObjPreview.src = "";
                    $(lable_lable).show();
                    $(lable_size).hide();
                }
            }
            fileTypeFlg = true;
            break;
        }
    }

    if (fileTypeFlg == false) {
        alertx("您上传的图片格式不正确，请重新选择!");
        if (imgObjPreview) {
            imgObjPreview.style.display = "none";
            imgObjPreview.style.width = "45px";
            imgObjPreview.style.height = "45px";
            imgObjPreview.src = "";
            $(lable_lable).show();
            $(lable_size).hide();
        }
        return false;
    }
    return true;
}
