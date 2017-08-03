function openBrowse(){ 
	document.getElementById("btn_file").click();
	$("#preview").attr("src","");
} 
	
	function setImagePreview() {
		// 判定当前浏览器类型
		var Sys = {};  
	        var ua = navigator.userAgent.toLowerCase();  
	        var s;  
	        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :  
	        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :  
	        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :  
	        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :  
	        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;  
		// 可以上传的文件类型
		var array = new Array('gif', 'jpeg', 'png', 'jpg', 'bmp'); 
		var docObj = $("#btn_file");
		var docObjFile = document.getElementById("btn_file");
		// 文件正则截取
		var fileContentType = "";
		if (docObj.val()) {
			fileContentType = docObj.val().match(/^(.*)(\.)(.{1,8})$/)[3];
		}
		var fileTypeFlg = false; 

		var imgObjPreview = document.getElementById("imgPreview");
		for (var i in array) {
		if (!fileContentType || fileContentType.toLowerCase() == array[i].toLowerCase()) {
			if (docObjFile.files && docObjFile.files[0]) {
				//火狐下，直接设img属性
				if (imgObjPreview) {
					imgObjPreview.style.display = "";
					imgObjPreview.style.width = "45px";
					imgObjPreview.style.height = "45px";
					//imgObjPreview.src = docObj.files[0].getAsDataURL();
					
					//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
					imgObjPreview.src = window.URL.createObjectURL(docObjFile.files[0]);
				}
			} else if (Sys.ie) {
			//IE下，使用滤镜
	 		docObj.select();
	 		var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
	 		localImagId.style.width = "175px";
	 		localImagId.style.height = "208px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.Pixelate(maxSquare=20)";
				localImagId.filters
					.item("DXImageTransform.Microsoft.Pixelate").src = imgSrc;
			} catch (e) {
				alertx("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			if (imgObjPreview) {
				imgObjPreview.style.display = "none";
			}
	 		document.selection.empty();
			} else if(!Sys.ie) {
				// 取消选择图片
				if (imgObjPreview) {
					imgObjPreview.style.display = "none";
					imgObjPreview.style.width = "45px";
					imgObjPreview.style.height = "45px";
					imgObjPreview.src = "";
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
		 		}
	             return false;
	         }
	         return true;
	}