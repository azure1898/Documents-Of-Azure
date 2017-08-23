function getQueryString(name) { 
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
   var r = window.location.search.substr(1).match(reg); 
   if (r != null) return unescape(r[2]); return null; 
}
var interfaceUrl = "http://192.168.1.62:8090/ehomeapp/app";
var indexunread=0;
var userInfo = {
	userID:1,
	addressId:1
};
//超过一万用万为单位
Vue.filter("wan", function (value) {
	 var week=0
	if(value>10000){
		week = value.substr(0,1) + "万";
	}else{
		week = value
	}
    return week;
});

//点击笑脸表情获取当前笑脸img
		function addemoji(obj){
         	var aimg=document.getElementById(obj).src;
         	var adiv=document.getElementById("editable");
            insertHtmlAtCaret('<img src="'+aimg+'">');         	
         }
		
//插入选择表情
 //insertHtmlAtCaret:function(html) {
 function insertHtmlAtCaret(html) {  
	        var sel, range;  
	        if (window.getSelection) {  
	            // IE9 and non-IE  
	            sel = window.getSelection();  
	            if (sel.getRangeAt && sel.rangeCount) {  
	                range = sel.getRangeAt(0);  
	                range.deleteContents();  
	                // Range.createContextualFragment() would be useful here but is  
	                // non-standard and not supported in all browsers (IE9, for one)  
	                var el = document.createElement("div");  
	                el.innerHTML = html;  
	                var frag = document.createDocumentFragment(), node, lastNode;  
	                while ((node = el.firstChild)) {  
	                    lastNode = frag.appendChild(node);  
	                }  
	                range.insertNode(frag);  
	                // Preserve the selection  
	                if (lastNode) {  
	                    range = range.cloneRange();  
	                    range.setStartAfter(lastNode);  
	                    range.collapse(true);  
	                    sel.removeAllRanges();  
	                    sel.addRange(range);  
	                }  
	            }  
	        } else if (document.selection && document.selection.type != "Control") {  
	            // IE < 9  
	            document.selection.createRange().pasteHTML(html);  
	        }    
        }		

