/**
 * 通过“YYYY-MM-DD hh:mm:ss”格式的字符串构造日期类型数据
 */
function getNewDate(str) {
    // 判定当前浏览器类型
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
            .match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
            .match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
            .match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
            .match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    if (!Sys.ie) {
        return new Date(str);
    } else {
        var strs = str.split(' ');
        var yearArray;
        var hourArray;
        // 只有年月日
        if (strs != null && strs.length == 1) {
            yearArray = strs[0].split('-');

            // 年月日时分秒
        } else if (strs != null && strs.length == 2) {
            yearArray = strs[0].split('-');
            hourArray = strs[1].split(':');
        }

        var date = new Date();
        if (yearArray != null && yearArray.length == 3) {
            date.setFullYear(yearArray[0], yearArray[1] - 1, yearArray[2]);
        }
        if (hourArray != null && hourArray.length == 3) {
            date.setHours(hourArray[0], hourArray[1], hourArray[2]);
        }
        // console.log("date = " + date);
        // console.log("yearArray = " + yearArray);
        // console.log("hourArray = " + hourArray);
        return date;
    }
}



//表单重置
function formReset(form){
	 $(form).find('input:text').each(function(){
		 $(this).val("");
	 });
	 $(form).find('.select2-chosen').each(function(){
		 $(this).html("");
	 });
	 $(form).find('select').each(function(){
		 $(this).prev().find('.select2-chosen').html($(this).find(":first-child").html())
	 });
}