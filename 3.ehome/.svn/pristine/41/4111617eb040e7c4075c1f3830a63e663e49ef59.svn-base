function getQueryString(name) { 
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
   var r = window.location.search.substr(1).match(reg); 
   if (r != null) return unescape(r[2]); return null; 
}

var interfaceUrl = "http://192.168.6.3:8081/ehomeapp/app";
var path_add = interfaceUrl+"/live/addBusinessCollection";
					var path_cancle = interfaceUrl+"/live/cancelBusinessCollection";
var userInfo = {
	userID:'ad46667a7c8a4ef9abc777da68783f4c',
	buildingID:'1'
};

Vue.filter("formatDecimal",function(value){
	var f = parseFloat(value);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.round(value*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
	return "¥" +s;
});
