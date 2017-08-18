function getQueryString(name) { 
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
   var r = window.location.search.substr(1).match(reg); 
   if (r != null) return unescape(r[2]); return null; 
}
var interfaceUrl = "http://192.168.1.8:8080/ehomeapp/app";

var userInfo = {
	userID:1,
};

