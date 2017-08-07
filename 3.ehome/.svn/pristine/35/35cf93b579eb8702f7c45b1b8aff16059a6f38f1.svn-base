<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>场地预约管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.addPriceSpan{
			border: solid thin #666;font-size: 25px; vertical-align: middle;border-radius:5px;margin: 1px 0 0 30px;cursor: pointer;
		}
		.delPriceSpan{
			border: solid thin #666;font-size: 25px; vertical-align: middle;border-radius:5px;margin: 1px 0 0 30px;cursor: pointer;
		}
	</style>
	<%--<script src="/static/business/field.js"></script>--%>
	<script type="text/javascript">
		var subPriceEelment= new Array("0", "1", "2", "3", "4");//剩余的分段价格
		var  index_count=${fn:length(fieldInfoPrices)};
		if(index_count>0){
			for(var i=0;i<index_count;i++){
			    subPriceEelment[i]="";
			}
		}
		//添加分段价格
		function addFieldPrice(e) {

		    if (subPriceEelment[4]==""){
		        alertx("上限为5条!");
		        return;
			}
			//取剩余价格ID值
            var index_num=0;
		    for (var i=0;i<5;i++){
		        if(subPriceEelment[i]!=""){
		            index_num=subPriceEelment[i];
		            break;
				}
			}

			var html="<div id=\""+index_num+"\" class=\"controls\" style=\"border: solid thin #eeeeee;margin: 15px 0 15px 180px;padding-left: 15px;padding-bottom: 15px;\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<div>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice1"+index_num+"\" wk=\"1\"  name=\"fieldInfoPrices["+index_num+"].monday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice1"+index_num+"\">星期一</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice2"+index_num+"\" wk=\"2\"  name=\"fieldInfoPrices["+index_num+"].tuesday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice2"+index_num+"\">星期二</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice3"+index_num+"\" wk=\"3\"  name=\"fieldInfoPrices["+index_num+"].wednesday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice3"+index_num+"\">星期三</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice4"+index_num+"\" wk=\"4\"  name=\"fieldInfoPrices["+index_num+"].thursday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice4"+index_num+"\">星期四</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice5"+index_num+"\" wk=\"5\"  name=\"fieldInfoPrices["+index_num+"].friday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice5"+index_num+"\">星期五</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice6"+index_num+"\" wk=\"6\"  name=\"fieldInfoPrices["+index_num+"].saturday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice6"+index_num+"\">星期六</label>\n" +
                "\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t<span>\n" +
                "\t\t\t\t\t\t<input id=\"weekPrice7"+index_num+"\" wk=\"7\"  name=\"fieldInfoPrices["+index_num+"].sunday\" class=\"input-xlarge click\" type=\"checkbox\" value=\"1\"><label for=\"weekPrice7"+index_num+"\">星期日</label>\n" +
                "\t\t\t\t\t</span>\n" +
				"\t<input type=\"text\" style=\"border: 0px solid #ffffff; color: #ffffff;width:0px;height:0px;\" id=\"strRequired"+index_num+"\"  name=\"strRequired"+index_num+"\" value=\"\" class=\"input-xlarge required\">" +
                "\t\t\t\t\t<span class=\"help-inline\"><font color=\"red\">*</font> </span>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t时段：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "\t\t\t\t<select id=\"fieldInfoPrices"+index_num+"_startOpenTime\" name=\"fieldInfoPrices["+index_num+"].startOpenTime\" class=\"input-xlarge required\">\n" +
                "\t\t\t\t\t<option value=\"\"></option>\n";
			//生成分段价格 预约时段
			var minHour,minMinute,maxHour,maxMinute;
			minHour=parseInt($("#startOpenTime").val().split(":")[0]);
            minMinute=parseInt($("#startOpenTime").val().split(":")[1]);
            maxHour=parseInt($("#endOpenTime").val().split(":")[0]);
            maxMinute=parseInt($("#endOpenTime").val().split(":")[1]);
            for (var j=minHour;j<=maxHour;j++){
                if(j==minHour&&minMinute=="30"){
                    html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
				}else if(j==maxHour&&maxMinute=="00"){
                    html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
				}else {
					html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
					html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
				}
			}


                html+="\t\t\t\t</select>\n" +
                "\t\t\t\t至\n" +
                "\t\t\t\t<select id=\"fieldInfoPrices"+index_num+"_endOpenTime\" name=\"fieldInfoPrices["+index_num+"].endOpenTime\" class=\"input-xlarge required\">\n" +
                "\n" +
                "\t\t\t\t</select>\n" +
                "\t\t\t\t<span class=\"help-inline\"> <font color=\"red\">*</font></span>\n" +
                "\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t时段价格：\n" +
                "\t\t\t\t<input maxlength=\"10\" name=\"fieldInfoPrices["+index_num+"].basePrice\" class=\"input-xlarge required number\" type=\"text\" value=\"\">\n" +
                "\t\t\t\t<span class=\"help-inline\"> 元/小时 <font color=\"red\">*</font></span>\n" +
                "\t\t\t\t<span class=\"addPriceSpan\" >＋</span>\n" +
                "\t\t\t\t<span class=\"delPriceSpan\" >－</span>\n" +"\t\t\t</div>";

		    if (index_num==0){
                /*$(e).parent().after(html);*/
                $("#files_price").append(html);
                $("span.delPriceSpan").hide();
			}else {
                /*$(e).parent().after(html);*/
                $(e).parent().parent().append(html);
                $("span.delPriceSpan").show();
			}
            subPriceEelment[index_num]="";
    	}

    	//获取营业时间
        function getCheckboxsValues(name){
            obj = document.getElementsByName(name);
            var check_val = "";
            for(k in obj){
                if(obj[k].checked)
                    check_val+=obj[k].value+",";
        }
			return check_val;
        }

        //更改最短预约时间 初始化已经设置的分段时间
    	function onChangeShortTime() {
            $("select[name=startOpenTime]").val("");
            $("select[name=startOpenTime]").prev().find(".select2-chosen").empty();
            $("select[name=endOpenTime]").empty();
            $("select[name=endOpenTime]").prev().find(".select2-chosen").empty();
            for (var i=0;i<5;i++){
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").val("");
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").prev().find(".select2-chosen").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").prev().find(".select2-chosen").empty();
			}

        }

        //校验是否选择最短预约时间
		function onCheckStartOpenTime(e) {
			var checkVal=$(e).val();
			if ($("select[name=shortTime]").val()==""){
			    alertx("请先选择最短预约时间！");
			    return;
			}
            changeEndTime(checkVal,e);
        }
        //生成开放预约时段后面的时间选项
        function changeEndTime(checkVal,e) {
		    var endTime=$(e).nextAll("select");
            var shortTime=parseInt($("select[name=shortTime]").val());
            if (checkVal!=null&&checkVal!=""){
                endTime.empty();
                endTime.prev().find(".select2-chosen").empty();
				var hNum = parseInt(checkVal.split(":")[0]);
				var mNum = parseInt(checkVal.split(":")[1]);

                var maxHour=24;
                //alert($(e).attr("id"));
                if($(e).attr("id")!="endOpenTime"&&$(e).attr("id")!="startOpenTime"){
                	maxHour=parseInt($("#endOpenTime").val().split(":")[0]);
				}

                endTime.append("<option value=\"\"></option>");
				for (var i = hNum + shortTime; i <= maxHour; i += shortTime) {
					if (mNum != 30) {
                        endTime.append("<option value=\"" + i + ":00\">" + i + ":00</option>");
					} else if (i != 24 && mNum == 30 && i < maxHour) {
                        endTime.append("<option value=\"" + i + ":30\">" + i + ":30</option>");
					}
				}
            }
        }

        //营业时间全选
    function checkWeekAll(e) {
			if($(e).attr('checked')=="checked"||$(e).attr('checked')=="true"){
				$("input[name=weekStr]").attr("checked","true");
                $("input[name=weekStr][value='6']").removeAttr("checked");
                $("input[name=weekStr][value='7']").removeAttr("checked");
            }else {
				$("input[name=weekStr]").removeAttr("checked");
			}
    	}

    	//判断时间段是否重复(点击星期和选择时间段 进行的校验)
		//sta_index 分段价格的ID值
    	function checkWeekTime(sta_index) {
		    var start_time=$("#fieldInfoPrices"+sta_index+"_startOpenTime").val();//当前操作的时段 开始时间
		    var end_time=$("#fieldInfoPrices"+sta_index+"_endOpenTime").val();//当前操作的时段 结束时间
            if (start_time==undefined||end_time==undefined){
                return true;
            }
			if($("#weekPrice1"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,1)){
                return false;
			}
			if($("#weekPrice2"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,2)){
                return false;
			}
			if($("#weekPrice3"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,3)){
                return false;
			}
			if($("#weekPrice4"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,4)){
                return false;
			}
			if($("#weekPrice5"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,5)){
                return false;
			}
			if($("#weekPrice6"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,6)){
                return false;
			}
			if($("#weekPrice7"+sta_index).attr("checked")=="checked"&&!checkHourTime(sta_index,start_time,end_time,7)){
                return false;
			}

			return true;
        }


        //校验某个星期的时间段是否重复
		//sta_index 某个分段价格的值ID
		//start_time 开始时间
		//end_time 结束时间
		//week_index 星期几
        function checkHourTime(sta_index,start_time,end_time,week_index) {
		    var ck_s_time,ck_e_time;
			for (var i=0;i<5;i++){//循环每个分段价格的 星期是 week_index
			    ck_s_time=$("#fieldInfoPrices"+i+"_startOpenTime").val();
			    ck_e_time=$("#fieldInfoPrices"+i+"_endOpenTime").val();
			    if (ck_s_time==undefined||ck_e_time==undefined){
			        continue;
				}
			    ck_s_time=ck_s_time.length!=5?"0"+ck_s_time:ck_s_time;
                ck_e_time=ck_e_time.length!=5?"0"+ck_e_time:ck_e_time;
                start_time=start_time.length!=5?"0"+start_time:start_time;
                end_time=end_time.length!=5?"0"+end_time:end_time;
			    if(sta_index!=i&&$("#weekPrice"+week_index+""+i).attr("checked")=="checked"
					&&((start_time>ck_s_time&&start_time<ck_e_time)
					||(end_time>ck_s_time&&end_time<ck_e_time)
					||(start_time<ck_s_time&&end_time>ck_e_time)||(start_time==ck_s_time&&end_time==ck_e_time))){
					return false;
				}
			}
			return true;
        }

    $(document).ready(function() {
        jQuery.validator.addMethod("fieldname", function (value, element) {
            var patrn = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/;
            return this.optional(element) || (patrn.test(value));
        }, "请输入中文，英文或数字");
			//$("#name").focus();
		$("#startOpenTime").val("${fieldInfo.startOpenTime}");
        $("#startOpenTime").prev().find(".select2-chosen").html("${fieldInfo.startOpenTime}");
		changeEndTime("${fieldInfo.startOpenTime}",$("#startOpenTime"));
		$("#endOpenTime").val("${fieldInfo.endOpenTime}");
        $("#endOpenTime").prev().find(".select2-chosen").html("${fieldInfo.endOpenTime}");
			$("#inputForm").validate({
                messages: {
                    name: {
                        required: "请输入场地预约名称（最多不超过10个字）",
                    },
                    weekStr: {
                        required: "请选择营业时间（星期一至星期日）",
                    },
                    shortTime: {
                        required: "请选择最短预约时间整倍数时间段作为营业时间段",
                    },
                    startOpenTime: {
                        required: "请选择开放预约时段",
                    },
                    endOpenTime: {
                        required: "请选择开放预约时段",
                    },
                    basePrice: {
                        required: "请填写场地预约价格",
                    },
                    strRequired0: {
                        required: "您还没有勾选日期",
                    },
                    strRequired1: {
                        required: "您还没有勾选日期",
                    },
                    strRequired2: {
                        required: "您还没有勾选日期",
                    },
                    strRequired3: {
                        required: "您还没有勾选日期",
                    },
                    strRequired4: {
                        required: "您还没有勾选日期",
                    },
                    'fieldInfoPrices[0].startOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[0].endOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[1].startOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[1].endOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[2].startOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[2].endOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[3].startOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[3].endOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[4].startOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[4].endOpenTime': {
                        required: "您还没有选择时段",
                    },
                    'fieldInfoPrices[0].basePrice': {
                        required: "您还没有填写分时段价格",
                    },
                    'fieldInfoPrices[1].basePrice': {
                        required: "您还没有填写分时段价格",
                    },
                    'fieldInfoPrices[2].basePrice': {
                        required: "您还没有填写分时段价格",
                    },
                    'fieldInfoPrices[3].basePrice': {
                        required: "您还没有填写分时段价格",
                    },
                    'fieldInfoPrices[4].basePrice': {
                        required: "您还没有填写分时段价格",
                    }
                },
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			$('span.addPriceSpan').live('click', function() {
                var id=parseInt($(this).parent().attr("id"));
				addFieldPrice(this);
			});

			$('span.delPriceSpan').live('click', function() {
                var id=parseInt($(this).parent().attr("id"));
                $("input[name=id]").after("<input type=\"hidden\" name=\"isAll\" value=\"false\" />");
                $("input[name=id]").after("<input type=\"hidden\" name=\"delId\" value=\""+$(this).parent().find("input[type=hidden]:first").val()+"\" />");
                subPriceEelment[id]=id+"";
				$(this).parent().remove();
				if ($("#files_price").find("div.controls").length==1){
                    $("span.delPriceSpan").hide();
				}
			});

        $("select[name=startOpenTime]").live("change",function () {
            onCheckStartOpenTime(this);
            for (var i=0;i<5;i++){
                var minHour,minMinute,maxHour,maxMinute;
                var html="\t\t\t\t\t<option value=\"\"></option>\n";
                minHour=parseInt($("#startOpenTime").val().split(":")[0]);
                minMinute=parseInt($("#startOpenTime").val().split(":")[1]);
                maxHour=parseInt($("#endOpenTime").val().split(":")[0]);
                maxMinute=parseInt($("#endOpenTime").val().split(":")[1]);
                for (var j=minHour;j<=maxHour;j++){
                    if(j==minHour&&minMinute=="30"){
                        html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
                    }else if(j==maxHour&&maxMinute=="00"){
                        html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
                    }else {
                        html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
                        html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
                    }
                }
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").empty();
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").append(html);
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").prev().find(".select2-chosen").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").prev().find(".select2-chosen").empty();
            }
        });

        //绑定事件
        $("select[name=endOpenTime]").live("change",function () {
            for (var i=0;i<5;i++){
                var minHour,minMinute,maxHour,maxMinute;
                var html="\t\t\t\t\t<option value=\"\"></option>\n";
                minHour=parseInt($("#startOpenTime").val().split(":")[0]);
                minMinute=parseInt($("#startOpenTime").val().split(":")[1]);
                maxHour=parseInt($("#endOpenTime").val().split(":")[0]);
                maxMinute=parseInt($("#endOpenTime").val().split(":")[1]);
                for (var j=minHour;j<=maxHour;j++){
                    if(j==minHour&&minMinute=="30"){
                        html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
                    }else if(j==maxHour&&maxMinute=="00"){
                        html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
                    }else {
                        html+="\t\t\t\t\t<option value=\""+j+":00\">"+j+":00</option>\n" ;
                        html+="\t\t\t\t\t<option value=\""+j+":30\">"+j+":30</option>\n" ;
                    }
                }
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").empty();
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").append(html);
                $("select[name='fieldInfoPrices["+i+"].startOpenTime']").prev().find(".select2-chosen").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").empty();
                $("select[name='fieldInfoPrices["+i+"].endOpenTime']").prev().find(".select2-chosen").empty();
            }
        });

        //绑定事件 分段价格ID值为0的时段 开始时间
        $("select[name='fieldInfoPrices[0].startOpenTime']").live("change",function () {
            onCheckStartOpenTime(this);
            $("select[name='fieldInfoPrices[0].endOpenTime']").trigger("change");
        });
        //绑定事件 分段价格ID值为1的时段 开始时间
        $("select[name='fieldInfoPrices[1].startOpenTime']").live("change",function () {
            onCheckStartOpenTime(this);
            $("select[name='fieldInfoPrices[1].endOpenTime']").trigger("change");
        });
		//绑定事件 分段价格ID值为2的时段 开始时间
        $("select[name='fieldInfoPrices[2].startOpenTime']").live("change",function () {
            onCheckStartOpenTime(this);
            $("select[name='fieldInfoPrices[2].endOpenTime']").trigger("change");
        });
	//绑定事件 分段价格ID值为3的时段 开始时间
        $("select[name='fieldInfoPrices[3].startOpenTime']").live("change",function () {
            onCheckStartOpenTime(this);
            $("select[name='fieldInfoPrices[3].endOpenTime']").trigger("change");
        });
//绑定事件 分段价格ID值为4的时段 开始时间
        $("select[name='fieldInfoPrices[4].startOpenTime']").live("change",function () {
            onCheckStartOpenTime(this);
            $("select[name='fieldInfoPrices[4].endOpenTime']").trigger("change");
        });

        //绑定事件 分段价格ID值为0的时段 结束时间
        $("select[name='fieldInfoPrices[0].endOpenTime']").live("change",function () {
            if(!checkWeekTime(0)){
                $(this).val("");
                $("#fieldInfoPrices0_startOpenTime").val("");
                $(this).empty();
                $(this).prev().find(".select2-chosen").empty();
                alertx("您设置的时段有重复，请仔细检查");
			}
        });

        $("select[name='fieldInfoPrices[1].endOpenTime']").live("change",function () {
            if(!checkWeekTime(1)){
            	$(this).val("");
            	$("#fieldInfoPrices1_startOpenTime").val("");
                $(this).empty();
                $(this).prev().find(".select2-chosen").empty();
                alertx("您设置的时段有重复，请仔细检查");
            }
        });

        $("select[name='fieldInfoPrices[2].endOpenTime']").live("change",function () {
            if(!checkWeekTime(2)){
                $(this).val("");
                $("#fieldInfoPrices2_startOpenTime").val("");
                $(this).empty();
                $(this).prev().find(".select2-chosen").empty();
                alertx("您设置的时段有重复，请仔细检查");
            }
        });

        $("select[name='fieldInfoPrices[3].endOpenTime']").live("change",function () {
            if(!checkWeekTime(3)){
                $(this).val("");
                $("#fieldInfoPrices3_startOpenTime").val("");
                $(this).empty();
                $(this).prev().find(".select2-chosen").empty();
                alertx("您设置的时段有重复，请仔细检查");
            }
        });

        $("select[name='fieldInfoPrices[4].endOpenTime']").live("change",function () {
            if(!checkWeekTime(4)){
                $(this).val("");
                $("#fieldInfoPrices4_startOpenTime").val("");
                $(this).empty();
                $(this).prev().find(".select2-chosen").empty();
                alertx("您设置的时段有重复，请仔细检查");
            }
        });

        //绑定全选复选框事件 用于全选实现
        $("input[type=checkbox].click").live("change",function () {
            var id=parseInt($(this).parent().parent().parent().attr("id"));
            var ck_num=parseInt($(this).attr("wk"));
            if(getCheckboxsValues("weekStr").indexOf(ck_num)<0){
                alertx("您设置的星期不在营业时间内！");
                $(this).removeAttr("checked");
			}
            if(!checkWeekTime(id)){
                alertx("您设置的时段有重复，请仔细检查");
                $(this).removeAttr("checked");
            }
            var checked_val=$(this).parent().parent().parent().find("input[type=checkbox]:checked").val();
            if(checked_val!=undefined||checked_val!="undefined"){
                $("#strRequired"+id).val(checked_val);
                $("#strRequired"+id).trigger("keyup");
			}else {
            	$("#strRequired"+id).val("");
			}

        });



        //绑定分时段价格复选框的事件
        $("input[name=isChild]").live("change",function () {

            if($("#shortTime").val()==""||$("#startOpenTime").val()==""||$("#endOpenTime").val()==""){
				alertx("请先完善最短预约时间、开放预约时段！");
                $(this).removeAttr('checked');
				return;
			}

            
            if($(this).attr('checked')=="checked"||$(this).attr('checked')=="true"){
            	addFieldPrice(this);
            }else {
                var ck_box= $(this);
                top.$.jBox.confirm("已有时段设置将删除是否取消？", "提示",function (v, h, f) {
                    if(v == 'ok'){
                        $("#isAll").remove();
                        $("#delId").remove();
                        subPriceEelment= new Array("0", "1", "2", "3", "4");
                        $("#files_price").empty();
                        $("input[name=id]").after("<input type=\"hidden\" id=\"isAll\" name=\"isAll\" value=\"true\" />");
                        $("input[name=id]").after("<input type=\"hidden\" id=\"delId\" name=\"delId\" value=\"${fieldInfo.id}\" />");
                    }else if(v == 'cancel') {
                        ck_box.attr('checked','checked');
                   }
                } );
            }
        });
/*

			$(document).on('click','select[name=startOpenTime]', function(e) {
				alert(1);
				onCheckStartOpenTime(e);
			});

			$(document).on('click','select[name=startOpenTime]', function(e) {
				onCheckStartOpenTime(e);
			});
*/
        //编辑页的回显
        $("select[name='fieldInfoPrices[0].startOpenTime']").trigger("change");
        $("select[name='fieldInfoPrices[0].endOpenTime']").val("${fieldInfoPrices[0].endOpenTime}");
        $("select[name='fieldInfoPrices[0].endOpenTime']").prev().find(".select2-chosen").html("${fieldInfoPrices[0].endOpenTime}");
        $("select[name='fieldInfoPrices[1].startOpenTime']").trigger("change");
        $("select[name='fieldInfoPrices[1].endOpenTime']").val("${fieldInfoPrices[1].endOpenTime}");
        $("select[name='fieldInfoPrices[1].endOpenTime']").prev().find(".select2-chosen").html("${fieldInfoPrices[1].endOpenTime}");
        $("select[name='fieldInfoPrices[2].startOpenTime']").trigger("change");
        $("select[name='fieldInfoPrices[2].endOpenTime']").val("${fieldInfoPrices[2].endOpenTime}");
        $("select[name='fieldInfoPrices[2].endOpenTime']").prev().find(".select2-chosen").html("${fieldInfoPrices[2].endOpenTime}");
        $("select[name='fieldInfoPrices[3].startOpenTime']").trigger("change");
        $("select[name='fieldInfoPrices[3].endOpenTime']").val("${fieldInfoPrices[3].endOpenTime}");
        $("select[name='fieldInfoPrices[3].endOpenTime']").prev().find(".select2-chosen").html("${fieldInfoPrices[3].endOpenTime}");
        $("select[name='fieldInfoPrices[4].startOpenTime']").trigger("change");
        $("select[name='fieldInfoPrices[4].endOpenTime']").val("${fieldInfoPrices[4].endOpenTime}");
        $("select[name='fieldInfoPrices[4].endOpenTime']").prev().find(".select2-chosen").html("${fieldInfoPrices[4].endOpenTime}");

		});

        //提交事件
		function fieldSubmit() {
			$("#createState").val("1");
			$("#inputForm").submit();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%--<li><a href="${ctx}/field/fieldInfo/">场地预约列表</a></li>--%>
		<li class="active"><a href="${ctx}/field/fieldInfo/form?id=${fieldInfo.id}">场地预约<shiro:hasPermission name="field:fieldInfo:edit">${not empty fieldInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="field:fieldInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fieldInfo" action="${ctx}/field/fieldInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="createState"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">场地名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="10" class="input-xlarge required fieldname"/>
				<span class="help-inline">(最多不超过10个字) <font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业时间：<label class="control-label"><input type="checkbox" onclick="checkWeekAll(this)" >全选</label></label>
			<div class="controls">
				<form:checkboxes path="weekStr" items="${fns:getDictList('week')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最短预约时间：</label>
			<div class="controls">
				<form:select path="shortTime" onchange="onChangeShortTime()" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('field_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开放预约时段：</label>
			<div class="controls">
				<select id="startOpenTime" name="startOpenTime"  class="input-xlarge required">
					<option value=""></option>
					<option value="0:00">0:00</option>
					<option value="0:30">0:30</option>
					<option value="1:00">1:00</option>
					<option value="1:30">1:30</option>
					<option value="2:00">2:00</option>
					<option value="2:30">2:30</option>
					<option value="3:00">3:00</option>
					<option value="3:30">2:30</option>
					<option value="4:00">4:00</option>
					<option value="4:30">4:30</option>
					<option value="5:00">5:00</option>
					<option value="5:30">5:30</option>
					<option value="6:00">6:00</option>
					<option value="6:30">6:30</option>
					<option value="7:00">7:00</option>
					<option value="7:30">7:30</option>
					<option value="8:00">8:00</option>
					<option value="8:30">8:30</option>
					<option value="9:00">9:00</option>
					<option value="9:30">9:30</option>
					<option value="10:00">10:00</option>
					<option value="10:30">10:30</option>
					<option value="11:00">11:00</option>
					<option value="11:30">11:30</option>
					<option value="12:00">12:00</option>
					<option value="12:30">12:30</option>
					<option value="13:00">13:00</option>
					<option value="13:30">13:30</option>
					<option value="14:00">14:00</option>
					<option value="14:30">14:30</option>
					<option value="15:00">15:00</option>
					<option value="15:30">15:30</option>
					<option value="16:00">16:00</option>
					<option value="16:30">16:30</option>
					<option value="17:00">17:00</option>
					<option value="17:30">17:30</option>
					<option value="18:00">18:00</option>
					<option value="18:30">18:30</option>
					<option value="19:00">19:00</option>
					<option value="19:30">19:30</option>
					<option value="20:00">20:00</option>
					<option value="20:30">20:30</option>
					<option value="21:00">21:00</option>
					<option value="21:30">21:30</option>
					<option value="22:00">22:00</option>
					<option value="22:30">22:30</option>
					<option value="23:00">23:00</option>
					<option value="23:30">23:30</option>
					<option value="24:00">24:00</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
				至
				<select name="endOpenTime" id="endOpenTime" class="input-xlarge required">

				</select>
				<span class="help-inline">(请选择最短预约时间整倍数时间段作为营业时间段) <font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">场地基础价格：</label>
			<div class="controls">
				<form:input path="basePrice" htmlEscape="false" maxlength="10" class="input-xlarge required number"/>
				<span class="help-inline" > 元/小时 <font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分时段价格:</label>
			<div class="controls">
				<form:checkboxes path="isChild" items="${fns:getDictList('checkboxs_one')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div id="files_price" class="control-group">
			<c:forEach items="${fieldInfoPrices}" var="fieldInfoPrice" varStatus="sta">
				<div id="${sta.index}" class="controls" style="border: solid thin #eeeeee;margin: 15px 0 15px 180px;padding-left: 15px;padding-bottom: 15px;">
					<input type="hidden" name="fieldInfoPrices[${sta.index}].id" value="${fieldInfoPrice.id}" />
					<br>
					<div>
					<span>
						<input id="weekPrice1${sta.index}" wk="1" name="fieldInfoPrices[${sta.index}].monday" <c:if test="${fieldInfoPrice.monday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice1${sta.index}">星期一</label>
					</span>
						<span>
						<input id="weekPrice2${sta.index}" wk="2"  name="fieldInfoPrices[${sta.index}].tuesday" <c:if test="${fieldInfoPrice.tuesday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice2${sta.index}">星期二</label>
					</span>
						<span>
						<input id="weekPrice3${sta.index}" wk="3"  name="fieldInfoPrices[${sta.index}].wednesday" <c:if test="${fieldInfoPrice.wednesday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice3${sta.index}">星期三</label>
					</span>
						<span>
						<input id="weekPrice4${sta.index}" wk="4"  name="fieldInfoPrices[${sta.index}].thursday" <c:if test="${fieldInfoPrice.thursday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice4${sta.index}">星期四</label>
					</span>
						<span>
						<input id="weekPrice5${sta.index}" wk="5"  name="fieldInfoPrices[${sta.index}].friday" <c:if test="${fieldInfoPrice.friday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice5${sta.index}">星期五</label>
					</span>
						<span>
						<input id="weekPrice6${sta.index}" wk="6"  name="fieldInfoPrices[${sta.index}].saturday" <c:if test="${fieldInfoPrice.saturday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice6${sta.index}">星期六</label>
					</span>
						<span>
						<input id="weekPrice7${sta.index}" wk="7"  name="fieldInfoPrices[${sta.index}].sunday" <c:if test="${fieldInfoPrice.sunday=='1'}"> checked="checked" </c:if> class="input-xlarge click" type="checkbox" value="1"><label for="weekPrice7${sta.index}">星期日</label>
					</span>
						<input type="text" id="strRequired${sta.index}" name="strRequired${sta.index}" style="border: 0px solid #ffffff; color: #ffffff; width:0px;height:0px;" value="1" class="input-xlarge required">
						<span class="help-inline">  <font color="red">*</font></span>
					</div>
					<br>
					<br>
					时段：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="fieldInfoPrices${sta.index}_startOpenTime" name="fieldInfoPrices[${sta.index}].startOpenTime" class="input-xlarge required">
						<option value=""></option>
						<c:if test="${minTime<=0&&maxTime>=0}">
							<option <c:if test="${fieldInfoPrice.startOpenTime=='0:00'}"> selected="selected" </c:if> value="0:00">0:00</option>
						</c:if>
						<c:if test="${minTime<=0.3&&maxTime>=0.3}">
							<option <c:if test="${fieldInfoPrice.startOpenTime=='0:30'}"> selected="selected" </c:if> value="0:30">0:30</option>
						</c:if>
						<c:if test="${minTime<=1.0&&maxTime>=1.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='1:00'}"> selected="selected" </c:if> value="1:00">1:00</option>
						</c:if>
						<c:if test="${minTime<=1.3&&maxTime>=1.3}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='1:30'}"> selected="selected" </c:if> value="1:30">1:30</option>
						</c:if>
						<c:if test="${minTime<=2.0&&maxTime>=2.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='2:00'}"> selected="selected" </c:if> value="2:00">2:00</option>
						</c:if>
						<c:if test="${minTime<=2.30&&maxTime>=2.3}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='2:30'}"> selected="selected" </c:if> value="2:30">2:30</option>
						</c:if>
						<c:if test="${minTime<=3.0&&maxTime>=3.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='3:00'}"> selected="selected" </c:if> value="3:00">3:00</option>
						</c:if>
						<c:if test="${minTime<=3.30&&maxTime>=3.3}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='3:30'}"> selected="selected" </c:if> value="3:30">3:30</option>
						</c:if>
						<c:if test="${minTime<=4.0&&maxTime>=4.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='4:00'}"> selected="selected" </c:if> value="4:00">4:00</option>
						</c:if>
						<c:if test="${minTime<=4.30&&maxTime>=4.3}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='4:30'}"> selected="selected" </c:if> value="4:30">4:30</option>
						</c:if>
						<c:if test="${minTime<=5.0&&maxTime>=5.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='5:00'}"> selected="selected" </c:if> value="5:00">5:00</option>
						</c:if>
						<c:if test="${minTime<=5.30&&maxTime>=5.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='5:30'}"> selected="selected" </c:if> value="5:30">5:30</option>
						</c:if>
						<c:if test="${minTime<=6.0&&maxTime>=6.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='6:00'}"> selected="selected" </c:if> value="6:00">6:00</option>
						</c:if>
						<c:if test="${minTime<=6.30&&maxTime>=6.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='6:30'}"> selected="selected" </c:if> value="6:30">6:30</option>
						</c:if>
						<c:if test="${minTime<=7.0&&maxTime>=7.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='7:00'}"> selected="selected" </c:if> value="7:00">7:00</option>
						</c:if>
						<c:if test="${minTime<=7.30&&maxTime>=7.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='7:30'}"> selected="selected" </c:if> value="7:30">7:30</option>
						</c:if>
						<c:if test="${minTime<=8.0&&maxTime>=8.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='8:00'}"> selected="selected" </c:if> value="8:00">8:00</option>
						</c:if>
						<c:if test="${minTime<=8.30&&maxTime>=8.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='8:30'}"> selected="selected" </c:if> value="8:30">8:30</option>
						</c:if>
						<c:if test="${minTime<=9.0&&maxTime>=9.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='9:00'}"> selected="selected" </c:if> value="9:00">9:00</option>
						</c:if>
						<c:if test="${minTime<=9.30&&maxTime>=9.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='9:30'}"> selected="selected" </c:if> value="9:30">9:30</option>
						</c:if>
						<c:if test="${minTime<=10.0&&maxTime>=10}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='10:00'}"> selected="selected" </c:if> value="10:00">10:00</option>
						</c:if>
						<c:if test="${minTime<=10.30&&maxTime>=10.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='10:30'}"> selected="selected" </c:if> value="10:30">10:30</option>
						</c:if>
						<c:if test="${minTime<=11.0&&maxTime>=11.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='11:00'}"> selected="selected" </c:if> value="11:00">11:00</option>
						</c:if>
						<c:if test="${minTime<=11.30&&maxTime>=11.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='11:30'}"> selected="selected" </c:if> value="11:30">11:30</option>
						</c:if>
						<c:if test="${minTime<=12.0&&maxTime>=12.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='12:00'}"> selected="selected" </c:if> value="12:00">12:00</option>
						</c:if>
						<c:if test="${minTime<=12.30&&maxTime>=12.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='12:30'}"> selected="selected" </c:if> value="12:30">12:30</option>
						</c:if>
						<c:if test="${minTime<=13.0&&maxTime>=13.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='13:00'}"> selected="selected" </c:if> value="13:00">13:00</option>
						</c:if>
						<c:if test="${minTime<=13.30&&maxTime>=13.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='13:30'}"> selected="selected" </c:if> value="13:30">13:30</option>
						</c:if>
						<c:if test="${minTime<=14.0&&maxTime>=14.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='14:00'}"> selected="selected" </c:if> value="14:00">14:00</option>
						</c:if>
						<c:if test="${minTime<=14.30&&maxTime>=14.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='14:30'}"> selected="selected" </c:if> value="14:30">14:30</option>
						</c:if>
						<c:if test="${minTime<=15.0&&maxTime>=15.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='15:00'}"> selected="selected" </c:if> value="15:00">15:00</option>
						</c:if>
						<c:if test="${minTime<=15.30&&maxTime>=15.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='15:30'}"> selected="selected" </c:if> value="15:30">15:30</option>
						</c:if>
						<c:if test="${minTime<=16.00&&maxTime>=16.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='16:00'}"> selected="selected" </c:if> value="16:00">16:00</option>
						</c:if>
						<c:if test="${minTime<=16.30&&maxTime>=16.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='16:30'}"> selected="selected" </c:if> value="16:30">16:30</option>
						</c:if>
						<c:if test="${minTime<=17.0&&maxTime>=17.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='17:00'}"> selected="selected" </c:if> value="17:00">17:00</option>
						</c:if>
						<c:if test="${minTime<=17.30&&maxTime>=17.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='17:30'}"> selected="selected" </c:if> value="17:30">17:30</option>
						</c:if>
						<c:if test="${minTime<=18.0&&maxTime>=18.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='18:00'}"> selected="selected" </c:if> value="18:00">18:00</option>
						</c:if>
						<c:if test="${minTime<=18.30&&maxTime>=18.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='18:30'}"> selected="selected" </c:if> value="18:30">18:30</option>
						</c:if>
						<c:if test="${minTime<=19.0&&maxTime>=19.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='19:00'}"> selected="selected" </c:if> value="19:00">19:00</option>
						</c:if>
						<c:if test="${minTime<=19.30&&maxTime>=19.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='19:30'}"> selected="selected" </c:if> value="19:30">19:30</option>
						</c:if>
						<c:if test="${minTime<=20.0&&maxTime>=20.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='20:00'}"> selected="selected" </c:if> value="20:00">20:00</option>
						</c:if>
						<c:if test="${minTime<=20.30&&maxTime>=20.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='20:30'}"> selected="selected" </c:if> value="20:30">20:30</option>
						</c:if>
						<c:if test="${minTime<=21.0&&maxTime>=21.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='21:00'}"> selected="selected" </c:if> value="21:00">21:00</option>
						</c:if>
						<c:if test="${minTime<=21.30&&maxTime>=21.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='21:30'}"> selected="selected" </c:if> value="21:30">21:30</option>
						</c:if>
						<c:if test="${minTime<=22.0&&maxTime>=22.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='22:00'}"> selected="selected" </c:if> value="22:00">22:00</option>
						</c:if>
						<c:if test="${minTime<=22.30&&maxTime>=22.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='22:30'}"> selected="selected" </c:if> value="22:30">22:30</option>
						</c:if>
						<c:if test="${minTime<=23.0&&maxTime>=23.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='23:00'}"> selected="selected" </c:if> value="23:00">23:00</option>
						</c:if>
						<c:if test="${minTime<=23.30&&maxTime>=23.30}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='23:30'}"> selected="selected" </c:if> value="23:30">23:30</option>
						</c:if>
						<c:if test="${minTime<=24.0&&maxTime>=24.0}">
						<option <c:if test="${fieldInfoPrice.startOpenTime=='24:00'}"> selected="selected" </c:if> value="24:00">24:00</option>
						</c:if>
					</select>
					至
					<select id="fieldInfoPrices${sta.index}_endOpenTime" name="fieldInfoPrices[${sta.index}].endOpenTime" class="input-xlarge required ">

					</select>
					<span class="help-inline"> 元/小时 <font color="red">*</font></span>
					<br>
					<br>
					时段价格：
					<input maxlength="10" name="fieldInfoPrices[${sta.index}].basePrice" class="input-xlarge required number" type="text" value="${fieldInfoPrice.basePrice}">
					<span class="help-inline"> 元/小时 <font color="red">*</font></span>
					<span class="addPriceSpan" >＋</span>
					<span class="delPriceSpan" <c:if test="${fn:length(fieldInfoPrices)==1}"> style="display: none;" </c:if> >－</span>

				</div>
			</c:forEach>


		</div>
		<div class="form-actions">
			<shiro:hasPermission name="field:fieldInfo:edit"><input id="btnSubmit" class="btn btn-success" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="field:fieldInfo:edit"><input id="btnSubmit" class="btn btn-success" type="button" onclick="fieldSubmit()" value="保存并生成预约"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"  onclick="window.location.href = '${ctx}/field/fieldInfo/list'"/>
		</div>
	</form:form>
</body>
</html>