<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单-商品类管理</title>
    <meta name="decorator" content="default"/>
    <%-- <script type="text/javascript" src="${ctx }/modules/cms/front/themes/weixin/lyb/jquery-1.7.1.min.js"></script> --%>
    <style type="text/css">
		.field_red{
			border-radius: 5px;
			cursor: pointer;
			display: inline-block;
			padding: 5px;
			margin: 2px 0 2px 0;
			border: solid thin #aaaaaa;
			background-color:orangered;
			color: white;
		}
		.field_silver{
			border-radius: 5px;
			cursor: pointer;
			display: inline-block;
			padding: 5px;
			margin: 2px 0 2px 0;
			border: solid thin #aaaaaa;
			background-color:silver;
			color: white;
		}
		.field_green{
			border-radius: 5px;
			cursor: pointer;
			display: inline-block;
			padding: 5px;
			margin: 2px 0 2px 0;
			border: solid thin #aaaaaa;
			background-color:green;
			color: white;
		}
		.time_left{
			cursor: pointer;
			display: inline-block;
			padding: 4px 10px 4px 10px ;
			margin-left: 20px;
			line-height: 20px;
			height: auto;
			border: thin solid #aaaaaa;
			border-radius: 5px;
		}
		.time_right{
			cursor: pointer;
			display: inline-block;
			padding: 4px 10px 4px 10px ;
			margin-right: 20px;
			line-height: 20px;
			height: auto;
			border: thin solid #aaaaaa;
			border-radius: 5px;
		}
		.closeField{
			background-color: red;
		}

	</style>
    <script type="text/javascript">
    $(document).ready(function() {
        //$("#name").focus();
        top.$.jBox.tip.mess = null;
        $("#inputForm").validate({
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
    });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        /*根据画面选择的取消原因传递给后台，并控制取消原因输入框显示非现实
        */
        function setRemarks(remarks,index) {
            $("#cancelRemarks" + index)[0].value = remarks;
            // 当选择是其他原因时显示输入框
            if ("其他原因" == remarks) {
                $("#cancelRemarks" + index)[0].value = "";
                $("#cancelRemarks" + index).css('display','');
            // 其他的时候非显示
            } else {
                $("#cancelRemarks" + index).css('display','none');
            }
        }
        // 根据导航下拉菜单的值跳转至不同窗口
        function hrefByProdType(value) {
            if ('0' == value) {
                // 迁移至商品订单列表页面
                window.location.href="${ctx}/order/orderGoods";
            } else if ('1' == value) {
                // 迁移至服务订单列表页面
                window.location.href="${ctx}/order/orderService";
            } else if ('2' == value) {
                // 迁移至课程培训订单列表页面
                window.location.href="${ctx}/order/orderLesson";
            } else if ('3' == value) {
                // 迁移至场地预约订单列表页面
                window.location.href="${ctx}/order/orderField";
            } else {
                // 迁移至团购订单列表页面
                window.location.href="${ctx}/order/orderGroupPurc";
            }
        }
        // 根据按钮不同改变searchForm的ACTION
        function changeAction(type) {
            // 检索按钮按下的时候
            if (type=='0') {
                $("#searchForm")[0].action = "${ctx}/order/orderGoods/";
                return true;
            // 导出按钮按下的时候
            } else if (type=='1') {
                $("#searchForm")[0].action = "${ctx}/order/orderGoods/export";
                return true;
            }
            return false;
        }
        // 取消原因的必须CHECK
        function cancelRemarksCheck(cancleReasonId) {
            if ($(cancleReasonId).val() == "") {
                alertx("请填写取消原因");
                return false;
            }
            
            return true;
        }
        //打开新页签
        function openedit(title,url){
        	window.parent.addTabByMy(title,url);
        }
        
        
        /* 场地中的js开始 */
        function alertAddField(name,year,hour,price,outTimeState,fieldPartitionPriceId,fieldInfoId,fieldState) {
		    if(fieldState!="0"){
                top.$.jBox.tip("该场地已经暂停预约，不能进行预约！");
                return false;
			}
		    if(!outTimeState){
		        alertx("该时段已经过去，不能进行预约！");
		        return false;
			}
            var msg="将预约<span style='color:red;'>"+name+"——"+year+hour+"</span>的时段，该时段价格为<span style='color:red;'>"+price+"元/小时</span>。请收到款后，点击“<span style='color:red;'>确定</span>”进行预约。";
			var html = "<div style='padding-left:12px;'>"+msg+"</div><div style='padding-left:12px;'>顾客姓名：<input maxlength='10' type='text' style='width: 80px;' placeholder=\"请输入姓名\" id='u_name' name='u_name' />" +
				"&nbsp;&nbsp;&nbsp;&nbsp;价格：<input type='text' style='width: 40px;' id='y_price'  placeholder=\"价格\" value='"+price+"' name='y_price' />元/小时</div>" +
				"<div style='padding-left:12px;'>联系方式：<input type='text'  placeholder=\"请输入联系方式\" id='u_phone' name='u_phone' /></div>";
			var submit = function (v, h, f) {
                if (v == false) {
                    top.$.jBox.close();
                    return true;
                } else {

                    if (f.u_name == '') {
					top.$.jBox.tip("请输入顾客姓名。", 'error', { focusId: "u_name" }); // 关闭设置 yourname 为焦点
					return false;
					}
					if (f.y_price == '') {
						top.$.jBox.tip("请输入价格。", 'error', { focusId: "y_price" }); // 关闭设置 yourname 为焦点
						return false;
					}
					if (f.u_phone == '') {
                        top.$.jBox.tip("请输入联系方式。", 'error', { focusId: "u_phone" }); // 关闭设置 yourname 为焦点
						return false;
					}
                    var tel = /(^0[1-9]{1}\d{9,10}$)|(^1[3,5,7,8]\d{9}$)/g;
                    if (!tel.test(f.u_phone)){
                        top.$.jBox.tip("联系方式格式为:固话为区号(3-4位)号码(7-9位),手机为:13,15,17,18号段", 'error', { focusId: "u_phone" }); // 关闭设置 yourname 为焦点
                        return false;
                    }

                }
				var msg="";
                $.ajax({
                    type: 'POST',
                    url: "${ctx}/order/orderField/addOrderFieldList" ,
                    data: {
                        "fieldInfoId":fieldInfoId,
                        "payMoney": f.y_price,
                        "accountName": f.u_name,
                        "accountPhoneNumber": f.u_phone,
                         "fieldPartitionPriceId":fieldPartitionPriceId 
					} ,
/*                        "orderFieldLists[0].fieldPartitionPriceId":fieldPartitionPriceId */
                    async : false, //默认为true 异步
                    dataType: "text",
                    success: function (data) {
                        msg=data;
                        top.$.jBox.tip(data);
                    } ,error:function (data) {
                        //alert(data);
                    }

                });
                top.$.jBox.close();
                $("#searchForm").submit();
            	return false;
			};

			top.$.jBox(html, { title: "预约场地", submit: submit , buttons: { '确定':true,'取消':false }});
		}
		function alertCancelField(name,year,hour,price,id) {
            $.ajax({
                type: 'POST',
                url: "${ctx}/order/orderFieldList/loadOrderFieldListByOrderField.json" ,
                data: {
                    "id": id,
                } ,
                async : false, //默认为true 异步
                success: function (data) {
                    var msg_1="<span style='color:red;'>"+name+"——"+year+hour+"</span>";
                    var html_1 = "<div style='padding-left:12px;'>"+msg_1+"</div>" +
                        "<div style='padding-left:12px;'>顾客姓名："+data.orderField.accountName+"</div>" +
                        "<div style='padding-left:12px;'>联系方式："+data.orderField.accountPhoneNumber+"</div>" +
                        "<div style='padding-left:12px;'>预约订单："+data.orderField.typeStr+"  "+data.orderField.orderNo+"</div>" +
                        "<div style='padding-left:12px;'>预约价格：<span style='color:red;'>"+data.benefitPrice+"元/小时</span></div>";
                   $.jBox.defaults.title='查看预约';
                    var content = {
                        state1: {
                            content: html_1,
                           /*  buttons: { '取消预约': 1, '返回': 0 }, */
                             buttons: { '返回': 0 }, 
                            buttonsFocus: 0,
                            submit: function (v, h, f) {
                                if (v == 0) {
                                    return true; // close the window
                                }
                                else {
                                    $.jBox.nextState(); //go forward
                                    // 或 $.jBox.goToState('state2')
                                }
                                return false;
                            }
                        },
                        state2: {
                            content: '确定取消<span style=\'color:red;\'>“2号观影小厅”“斌斌”</span>的预约吗？点击“确定”，我们将取消该场地的预约，未完成的预约将自动退款。为避免纠纷，请先行联系已预订的客户进行告知。',
                            buttons: { '确定': 1, '返回': 0 },
                            buttonsFocus: 0,
                            submit: function (v, h, f) {
                                if (v == 0) {
                                    return true; // close the window
                                } else {
                                    //$.jBox.prevState() //go back
                                    // 或 $.jBox.goToState('state1');

                                    $.ajax({
                                        type: 'POST',
                                        url: "${ctx}/order/orderFieldList/cancelOrderFieldList.json" ,
                                        data: {
                                            id:id,
                                        } ,
                                        async : false, //默认为true 异步
                                        success: function (data) {

                                        } ,
                                        dataType: "text"

                                    });
                                }

                                $.jBox.close();
                                return page();
                            }
                        }
                    };

                    $.jBox(content);

                } ,
                dataType: "json"

            });

		}

        function alertGetField(name,year,hour,price,id) {

            $.ajax({
                        type: 'POST',
                        url: "${ctx}/order/orderFieldList/loadOrderFieldListByOrderField.json" ,
                        data: {
                            "id": id,
                        } ,
                        async : false, //默认为true 异步
                        success: function (data) {
                            var msg="<span style='color:red;'>"+name+"——"+year+hour+"</span>";
                            var html = "<div style='padding-left:12px;'>"+msg+"</div>" +
                                "<div style='padding-left:12px;'>顾客姓名："+data.orderField.accountName+"</div>" +
                                "<div style='padding-left:12px;'>联系方式："+data.orderField.accountPhoneNumber+"</div>" +
                                "<div style='padding-left:12px;'>预约订单："+data.orderField.typeStr+"  "+data.orderField.orderNo+"</div>" +
                                "<div style='padding-left:12px;'>预约价格：<span style='color:red;'>"+data.benefitPrice+"元/小时</span></div>";
                            top.$.jBox(html, { title: "查看已消费",  buttons: { '返回':false }});
                        } ,
                        dataType: "json"

                    });
        }


        $(document).ready(function() {
            //处理状态
            var sCount=$("td[name='fState']");
            $.each(sCount, function(i, item){
                $(item).html("余"+$(item).parent().find("span.field_green").length+"场");
            });

            var date= $("#appointmentTime").val();
            //date = date.replace(/-0/,"-");
            //if (addDate(date,0)==getMaxDate()){
             //  $("span.time_right").removeAttr("onclick");
            //}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        //日期加减法  date参数为计算开始的日期，days为需要加的天数
        //格式:addDate('2017-1-11',20)
        function addDate(date,days){
            var d=new Date(date);
            d.setDate(d.getDate()+days);
            var m=d.getMonth()+1;
            return d.getFullYear()+'-'+m+'-'+d.getDate();
        }
        function clickTimeSelect(days) {
           var date= $("#appointmentTime").val();
           if (date==getMaxDate()){
               return;
		   }
            $("#appointmentTime").val(addDate(date,days));
            $("#searchForm").submit();
            return false;
        }
        function getMaxDate(){
            var now = new Date();
            return addDate(now,6);
        }
        
     // 确认对话框
        function confirmx1(mess, href, closed,title){
    	 top.$.jBox.messagerDefaults={  icon: 'none'};
        	top.$.jBox.confirm(mess,title,function(v,h,f){
        		if(v=='ok'){
        			if (typeof href == 'function') {
        				href();
        			}else{
        				resetTip(); //loading();
        				location = href;
        			}
        		}
        	},{buttonsFocus:1, closed:function(){
        		if (typeof closed == 'function') {
        			closed();
        		}
        	}});
        	//top.$('.jbox-body .jbox-icon').css('top','55px');
           	top.$('.jbox-body .jbox-icon').css('display','none');
           	top.$('.jbox-content').children().css('padding-left','0px');
        	return false;
        }
    	 /* 场地中的js结束 */
 </script>
<style type="text/css">
	a{
		cursor: pointer;
	}
	.block{
		background-color: #FFFFFF;
		border: 1px solid #ddd;
		margin: 10px 20px 0px 20px;
		float:left;
		width: 97%;
		/* box-shadow: 0 3px 5px #ddd; */
		box-shadow: -3px 2px 2px #ddd ;
		padding: 10px 0px 0px 5px;
		 box-sizing: border-box;
	}
	.head-border{
		border-right: 1px solid #ddd;
		width: 30%;
		float:left;
		margin-left: 15px;
		margin-bottom: 15px;
	}
	.head-border:last-child{
		border-right-style: none;
	}
	.ul li{
		list-style-type: none;
	}
	.ul li span{
		display:block;
		width: 70px;
		float:left;
		color: #5B7B9A;
	}
	.ul li a{
		color: #60C1F7;
	}
	.case{
		width: 99.8%;
		height: 100%;
		border: 1px solid #ddd;
		margin: 0px 0px 0px 0px;
		box-sizing: border-box;
	}
	.head-border p{
		margin-left:25px;
	}
</style>
</head>
<body>
<div style="background-color: #EEF4F9;width: 100%;height: 100%;float: left;">
	<div class="block">
	<p style="font-weight:bold;font-size:20px;color: #FF5F5F;margin-left: 15px;">商家概况</p>
	<hr style="border-style:solid;"/>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s0.png" >&nbsp;&nbsp;产品统计</p>
		<ul class="ul">
			<c:if test="${ptlist.indexOf('0')>-1 }">
				<li><span>商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</span>${goodsCount}<c:if test="${goodsbzCount>0}">（库存不足：<a onclick="openedit('商品管理','/goods/goodsInfo?warnNum=${warnNum}')">${goodsbzCount}</a>）</c:if></li>
			</c:if>
		 	<c:if test="${ptlist.indexOf('1')>-1 }">
				<li><span>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</span>${serviceCount}<c:if test="${servicesbzCount>0}">（库存不足：<a onclick="openedit('服务管理','/service/serviceInfo?warnNum=${warnNum}')">${servicesbzCount}</a>）</c:if></li>
			</c:if>
			<c:if test="${ptlist.indexOf('2')>-1 }">
				<li><span>课程培训：</span>${lessonCount} <c:if test="${lessonbzCount>0}">（约满：<a onclick="openedit('课程培训管理','/lesson/lessonInfo?isfull=1')">${lessonbzCount}</a>）</c:if></li>
			 </c:if>
			<c:if test="${ptlist.indexOf('3')>-1 }">
				<li><span>场地预约：</span>${fieldCount}<c:if test="${fieldbzCount>0}">（约满：<a onclick="openedit('场地预约管理','/field/fieldInfo?isfull=1')">${fieldbzCount}</a>）</c:if></li>
			</c:if>
		</ul>
	</div>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s1.png" >&nbsp;&nbsp;本周订单</p>
		<ul class="ul">
			 <c:if test="${ptlist.indexOf('0')>-1 }">
				<li><span>商品订单：</span><a onclick="openedit('商品订单','/order/orderGoods?beginCreateDate=${beginCreateDate}&endCreateDate=${endCreateDate }&orderState=3')">${orderGoodsCount}</a></li>
			 </c:if>
		 	 <c:if test="${ptlist.indexOf('1')>-1 }">
				<li><span>服务订单：</span><a onclick="openedit('服务订单','/order/orderService?beginCreateDate=${beginCreateDate}&endCreateDate=${endCreateDate }&orderState=2')">${orderServiceCount}</a></li>
			 </c:if>
			 <c:if test="${ptlist.indexOf('2')>-1 }"> 
				<li><span>课程培训：</span><a onclick="openedit('课程培训订单','/order/orderLesson?beginCreateDate=${beginCreateDate}&endCreateDate=${endCreateDate }&orderState=1')">${orderLessonCount}</a></li>
			 </c:if>
			 <c:if test="${ptlist.indexOf('3')>-1 }">
				<li><span>场地预约：</span><a onclick="openedit('场地预约订单','/order/orderField?beginCreateDate=${beginCreateDate}&endCreateDate=${endCreateDate }&orderState=1')">${orderFieldCount}</a></li>
			 </c:if>
		</ul>
	</div>
	<div class="head-border">
		<p style="font-weight:bold;font-size:15px;"><img src="${ctxStatic}/images/s2.png" >&nbsp;&nbsp;本周收入</p>
		<ul class="ul">
			<c:if test="${ptlist.indexOf('0')>-1 }">
				<li><span>商品订单：</span>￥${fns:doubleFormat(goodsInclu)}</li>
			</c:if>
		 	<c:if test="${ptlist.indexOf('1')>-1 }">
				<li><span>服务订单：</span>￥${fns:doubleFormat(serviceInclu)}</li>
			</c:if>
			<c:if test="${ptlist.indexOf('2')>-1 }">
				<li><span>课程培训：</span>￥${fns:doubleFormat(lessonInclu)}</li>
			</c:if> 
			<c:if test="${ptlist.indexOf('3')>-1 }">
				<li><span>场地预约：</span>￥${fns:doubleFormat(fieldInclu)}</li>
			</c:if>
		</ul>
	</div>
</div>
<div class="block">
	<p style="font-weight:bold;font-size:20px;color: #FF5F5F;margin-left: 15px;">待办事宜</p>
	<sys:message content="${message}"/>
	<%--商品订单 开始--%>
	 <c:if test="${ptlist.indexOf('0')>-1 }">
	<div class="case">
	    <ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;margin-top: 10px;">
	        <li><p style="font-weight:bold;font-size:14px;">商品订单</p></li>
	        <li style="width: 100%;">
	            <div style="float: left;width: 180px;">待付款商品订单：<a href="#" onclick="openedit('商品订单','/order/orderGoods?payState=0&pending=1')" style="color: #60C1F7;">${pendingPayGoods}</a></div>
	            <div style="float: left;width: 180px;">待受理商品订单：<a href="#" onclick="openedit('商品订单','/order/orderGoods?orderState=0&pending=1')" style="color: #60C1F7;">${pendingHandleGoods}</a></div>
	            <div style="float: left;width: 180px;">待配送商品订单：<a href="#" onclick="openedit('商品订单','/order/orderGoods?orderState=1&pending=1')" style="color: #60C1F7;">${pendingDisGoods}</a></div>
	            <div style="float: left;width: 180px;">待完成商品订单：<a href="#" onclick="openedit('商品订单','/order/orderGoods?orderState=2&pending=1')" style="color: #60C1F7;">${pendingSuccessGoods}</a></div>
	        </li>
	    </ul>
   		<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 99%;">
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>地址</th>
                <th>金额</th>
                <th>配送方式</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>时间</th>
                <shiro:hasPermission name="order:orderGoods:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${newOrderGoodsList}" var="orderGoods" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderGoods/form?id=${orderGoods.id}">
                    ${orderGoods.orderNo}
                </a></td>
                <td>
                    ${orderGoods.accountName}
                </td>
                <td>
                    ${orderGoods.address}
                </td>
                <td>
                    ${orderGoods.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderGoods.addressType, 'address_goods_type', "")}
                </td>
                <td <c:if test="${orderGoods.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderGoods.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderGoods.orderState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderGoods.orderState, 'order_goods_state', "")}
                </td>
                <td>
                    <c:if test="${orderGoods.createDate !=null && orderGoods.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderGoods.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderGoods.payTime !=null && orderGoods.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderGoods.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <td>
                    <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderGoods/form?id=${orderGoods.id}'">
                    <div style="margin:0 auto;height:3px;"></div>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '0'}">
                        <input id="btuElemAccept" type="button" class="commonsmallbtn" value="受理" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderGoods/accept?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}&redirectUrl=/sys/index/?repage'">
                        <div style="margin:0 auto;height:3px;"></div>
                    </c:if>
                    <c:if test="${orderGoods.payState == '1' && orderGoods.orderState ==  '2'}">
                        <input id="btuElemComplete" type="button" class="commonsmallbtn" value="完成" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderGoods/complete?id=${orderGoods.id}&updateDate=${orderGoods.updateDateString}&redirectUrl=/sys/index/?repage'">
                        <div style="margin:0 auto;height:3px;"></div>
                    </c:if>
                    <c:if test="${orderGoods.orderState !=  '3' && orderGoods.orderState !=  '4'}">
                        <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;"  data-toggle="modal" data-target="#myModal${status.index}">
                    </c:if>
    <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" style="color: #000000"id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderGoods" action="${ctx}/order/orderGoods/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
                <div class="modal-body">
                    <select id="cancelRemarksSelect" class="required" name="cancelRemarksSelect" onchange="setRemarks(this.value,'${status.index}')" style="width:200px;">
                      <option selected value="">请选择订单取消原因</option>
                      <option value="客户联系取消">客户联系取消</option>
                      <option value="无法联系客户">无法联系客户</option>
                      <option value="商品无法送达">商品无法送达</option>
                      <option value="客户未付款，释放库存">客户未付款，释放库存</option>
                      <option value="其他原因">其他原因</option>
                    </select>
                    <br/>
                    <br/>
                    <input type="hidden" value="/sys/index/?repage" name="redirectUrl">
                    <input type="hidden" value="${orderGoods.id}" name="id">
                    <input type="hidden" value="${orderGoods.orderNo}" name="orderNo">
                    <input type="hidden" value="${orderGoods.updateDateString}" name="updateDateString">
                    <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge required" value="" maxlength="81" type="text">
                    <p>订单取消后系统将自动退款。</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" style="width:60px;" class="commonbtn">确定</button>
                    <button type="button" class="commonbtn" data-dismiss="modal">取消</button>
                </div>
            </form:form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<div class="pagination" style="margin-left: 5%;width: 94%;">${page}</div>--%>
<c:if test="${goodsbzCount>0}">
    <ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;">
        <li><p style="font-weight:bold;font-size:14px;">库存紧张商品</p></li>
    </ul>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:99%;">
        <thead>
        <tr>
            <th>名称</th>
            <th>分类</th>
            <th>价格</th>
            <th>总库存</th>
            <th>图片</th>
            <th>推荐</th>
            <th>状态</th>
            <th width="100px;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goodsStock}" var="goodsInfo">
            <tr>
                <td>
                        ${goodsInfo.name}
                </td>
                <td>
                    <c:if test="${goodsInfo.sortInfoName == null || goodsInfo.sortInfoName == ''}">
                        -
                    </c:if>
                        ${goodsInfo.sortInfoName}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${goodsInfo.benefitPrice != null && goodsInfo.benefitPrice!= ''}">
                            <s><font color="#CCCCCC">${fns:doubleFormat(goodsInfo.basePrice)}</font></s><br/>
                            ${fns:doubleFormat(goodsInfo.benefitPrice)}
                        </c:when>
                        <c:otherwise>
                            ${fns:doubleFormat(goodsInfo.basePrice)}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${goodsInfo.stock != null && goodsInfo.stock!= ''}">
                            ${goodsInfo.stock}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach items="${goodsInfo.imageUrls}" var="imgUrl">
		                    <img src="${imgUrl}" style="width:53px;height:40px;"/>
		           </c:forEach>
                </td>
                <td>
                    <c:if test="${goodsInfo.recommend == null || goodsInfo.recommend == '0'}">
                        否
                    </c:if>
                    <c:if test="${goodsInfo.recommend != null && goodsInfo.recommend == '1'}">
                        是
                    </c:if>
                </td>
                <td>
                    <c:if test="${goodsInfo.state == null || goodsInfo.state == '0'}">
                        下架
                    </c:if>
                    <c:if test="${goodsInfo.state != null && goodsInfo.state == '1'}">
                        上架
                    </c:if>
                </td>
                <shiro:hasPermission name="goods:goodsInfo:edit">
                <td>
               		 <input type="button" class="commonsmallbtn" value="编辑" style="width:40px;margin-top: 2px;" onclick="window.parent.addTabByMy('商品编辑','/goods/goodsInfo/form?id=${goodsInfo.id}&mode=1');">
               		 
                </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
	</div>
	</c:if>
<%--商品订单 结束--%>
		<%--服务订单 开始--%>
		 <c:if test="${ptlist.indexOf('1')>-1 }">
	<div class="case" style="margin-top: 20px;">
		
<ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;margin-top: 10px;">
    <li><p style="font-weight:bold;font-size:14px;">服务订单</p></li>
    <li style="width: 100%;">
        <div style="float: left;width: 180px;">待付款服务订单：<a href="" onclick="openedit('服务订单','/order/orderService?payState=0&pending=1')" style="color: #60C1F7;">${pendingPayService}</a></div>
        <div style="float: left;width: 180px;">待受理服务订单：<a href="" onclick="openedit('服务订单','/order/orderService?orderState=0&pending=1')" style="color: #60C1F7;">${pendingHandleService}</a></div>
        <div style="float: left;width: 180px">待完成服务订单：<a href="" onclick="openedit('服务订单','/order/orderService?orderState=1&pending=1')" style="color: #60C1F7;">${pendingSuccessService}</a></div>
    </li>
</ul>
 <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:99%">
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>地址</th>
                <th>金额</th>
                <th>服务方式</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>时间</th>
                <shiro:hasPermission name="order:orderService:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${newOrderServiceList}" var="orderService" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderService/form?id=${orderService.id}">
                    ${orderService.orderNo}
                </a></td>
                <td>
                    ${orderService.accountName}
                </td>
                <td>
                    ${orderService.serviceAddress}
                </td>
                <td>
                    ${orderService.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderService.serviceType, 'order_service_type', "")}
                </td>
                <td <c:if test="${orderService.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderService.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderService.orderState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderService.orderState, 'order_service_state', "")}
                </td>
                <td>
                    <c:if test="${orderService.createDate !=null && orderService.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderService.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderService.payTime !=null && orderService.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderService.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <td>
                    <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderService/form?id=${orderService.id}'">
                    <div style="margin:0 auto;height:3px;"></div>
                    <c:if test="${orderService.payState == '1' && orderService.orderState ==  '0'}">
                        <input id="btuElemAccept" type="button" class="commonsmallbtn" value="受理" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderService/accept?id=${orderService.id}&updateDate=${orderService.updateDateString}&redirectUrl=/sys/index/?repage'">
                        <div style="margin:0 auto;height:3px;"></div>
                    </c:if>
                    <c:if test="${orderService.payState == '1' && orderService.orderState ==  '1'}">
                        <input id="btuElemComplete" type="button" class="commonsmallbtn" value="完成" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderService/complete?id=${orderService.id}&updateDate=${orderService.updateDateString}&redirectUrl=/sys/index/?repage'">
                        <div style="margin:0 auto;height:3px;"></div>
                    </c:if>
                    <c:if test="${orderService.orderState !=  '2' && orderService.orderState !=  '3'}">
                        <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;"  data-toggle="modal" data-target="#myModalService${status.index}">
                    </c:if>
    <div class="modal fade" id="myModalService${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="display: none;">
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderService" action="${ctx}/order/orderService/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
                <div class="modal-body">
                    <select id="cancelRemarksSelect" class="required" name="cancelRemarksSelect" onchange="setRemarks(this.value,'${status.index}')" style="width:200px;">
                      <option selected value="">请选择订单取消原因</option>
                      <option value="客户联系取消">客户联系取消</option>
                      <option value="无法联系客户">无法联系客户</option>
                      <option value="商品无法送达">商品无法送达</option>
                      <option value="客户未付款，释放库存">客户未付款，释放库存</option>
                      <option value="其他原因">其他原因</option>
                    </select>
                    <br/>
                    <br/>
                    
                    <input type="hidden" value="/sys/index/?repage" name="redirectUrl">
                    <input type="hidden" value="${orderService.id}" name="id">
                    <input type="hidden" value="${orderService.orderNo}" name="orderNo">
                    <input type="hidden" value="${orderService.updateDateString}" name="updateDateString">
                    <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge required" value="" maxlength="81" type="text">
                    <p>订单取消后系统将自动退款。</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" style="width:60px;" class="commonbtn">确定</button>
                    <button type="button" style="width:60px;" class="commonbtn" data-dismiss="modal">取消</button>
                </div>
            </form:form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<c:if test="${servicesbzCount>0}">
    <ul class="nav nav-tabs" style="width: 100%;border-bottom-style: none;">
        <li><p style="font-weight:bold;font-size:14px;">库存紧张服务</p></li>
    </ul>
    <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 99%;">
        <thead>
        <tr>
            <th >编号 </th>
            <th >服务名称</th>
            <th >分类</th>
            <th >价格</th>
            <th >库存</th>
            <th >图片</th>
            <th >推荐</th>
            <th >状态</th>
            <shiro:hasPermission name="service:serviceInfo:edit"><th width="100px;">操作</th></shiro:hasPermission>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${serviceStock}" var="serviceInfo">
            <tr>
                <td>
                        ${serviceInfo.serialNumbers}
                </td>
                <td>
                        ${serviceInfo.name}
                </td>
                <td>
                    <c:if test="${serviceInfo.sortInfoName == null || serviceInfo.sortInfoName == ''}">
                        -
                    </c:if>
                        ${serviceInfo.sortInfoName}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${serviceInfo.benefitPrice != null && serviceInfo.benefitPrice!= ''}">
                            <s><font color="#CCCCCC">${fns:doubleFormat(serviceInfo.basePrice)}</font></s><br/>
                            ${fns:doubleFormat(serviceInfo.benefitPrice)}
                        </c:when>
                        <c:otherwise>
                            ${serviceInfo.basePrice}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${serviceInfo.stock != null && serviceInfo.stock!= ''}">
                            ${serviceInfo.stock}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach items="${serviceInfo.imageUrls}" var="imgUrl">
                        <img src="${imgUrl}" style="width:53px;height:40px;"/>
                    </c:forEach>
                </td>
                <td>
                        ${fns:getDictLabel(serviceInfo.recommend, 'Quota', '否')}
                </td>
                <td>
                        ${fns:getDictLabel(serviceInfo.state, 'service_state', '下架')}
                </td>
                <shiro:hasPermission name="service:serviceInfo:edit">
                    <td>
                    <input type="button" class="commonsmallbtn" value="编辑" style="width:40px;margin-top: 2px;" onclick="openserviceedit('${serviceInfo.id}')">
                   	<script>
                   		function openserviceedit(id){
                   			window.parent.addTabByMy('服务编辑','/service/serviceInfo/form?id='+id);
                   		}
                   	</script>
                    </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</div>
</c:if>
	<%--服务订单 结束--%>
	
	
	
	
 <!-- 课程预约订单开始 -->
  <c:if test="${ptlist.indexOf('2')>-1 }">
<div class="case" style="margin-top: 20px;">
<ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;margin-top: 10px;">
    <li><p style="font-weight:bold;font-size:14px;">课程预约订单</p></li>
    <li style="width: 100%;">
        <div style="float: left;width: 180px;">待付款课程预约订单：<a href="" onclick="openedit('课程预约订单','/order/orderLesson?payState=0&pending=1')" style="color: #60C1F7;">${pendingPayLesson}</a></div>
        <%-- <div style="float: left;width: 180px;">待预约课程预约订单：<a href="" onclick="openedit('课程预约订单','/order/orderLesson?orderState=0&pending=1')" style="color: #60C1F7;">${pendingHandleLesson}</a></div> --%>
    </li>
</ul>
 <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 99%;">
        <thead>
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>课程名称</th>
                <th>金额</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>时间</th>
                <th width="100px;">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${newOrderLessonList }" var="orderLesson" varStatus="status">
                <tr>
                    <td><a href="${ctx}/order/orderLesson/form?id=${orderLesson.id}"> ${orderLesson.orderNo} </a></td>
                    <td>${orderLesson.accountName}</td>
                    <td>${orderLesson.accountPhoneNumber}</td>
                    <td>${orderLesson.name}</td>
                    <td>${orderLesson.payMoney}</td>
                    <td <c:if test="${orderLesson.payState == 0}">style="color:red"</c:if>>${fns:getDictLabel(orderLesson.payState, 'pay_goods_state', "")}</td>
                    <td <c:if test="${orderLesson.orderState == 0}">style="color:red"</c:if>>${fns:getDictLabel(orderLesson.orderState, 'order_lesson_state', "")}
                    </td>
                    <td><c:if test="${orderLesson.createDate !=null && orderLesson.createDate !=''}">
                            <span>下单：<fmt:formatDate value="${orderLesson.createDate}" pattern="yyyy-MM-dd HH:mm" /></span>
                            <br />
                        </c:if> <c:if test="${orderLesson.payTime !=null && orderLesson.payTime !=''}">
                            <span>支付：<fmt:formatDate value="${orderLesson.payTime}" pattern="yyyy-MM-dd HH:mm" /></span>
                        </c:if></td>
                    <td>
                        <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderLesson/form?id=${orderLesson.id}'">
                        <div style="margin:0 auto;height:3px;"></div>
                        <c:if test="${orderLesson.payState ==  '0' && orderLesson.orderState ==  '0'}">
                            <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;"  data-toggle="modal" data-target="#myModalLesson${status.index}">
                        </c:if>
                        <div class="modal fade" id="myModalLesson${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
                            style="display: none;">
                            <div class="modal-dialog" style="text-align: left;">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                                    </div>
                                    <form:form id="inputForm${status.index}" modelAttribute="orderLesson" action="${ctx}/order/orderLesson/cancel" method="post"
                                        onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
                                        <div class="modal-body">
                                            <select id="cancelRemarksSelect" class="required" name="cancelRemarksSelect" onchange="setRemarks(this.value,'${status.index}')"
                                                style="width: 200px;">
                                                <option selected value="">请选择订单取消原因</option>
                                                <option value="客户未付款，释放库存">客户未付款，释放库存</option>
                                                <option value="其他原因">其他原因</option>
                                            </select> <br /> <br />
                                             <input type="hidden" value="/sys/index/?repage" name="redirectUrl">
                                            <input type="hidden" value="${orderLesson.id}" name="id">
                                             <input type="hidden" value="${orderLesson.orderNo}" name="orderNo">
                                              <input type="hidden" value="${orderLesson.updateDateString}" name="updateDateString">
                                            <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width: 200px; display: none;" placeholder="请填写取消原因"
                                                class="input-xlarge required" value="" maxlength="81" type="text">
                                            <p>订单取消后系统将自动退款。</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="commonbtn" style="width:60px;">确定</button>
                                            <button type="button" class="commonbtn" style="width:60px;" data-dismiss="modal">取消</button>
                                        </div>
                                    </form:form>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal -->
                        </div></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<c:if test="${lessonbzCount>0}">
     <ul class="nav nav-tabs" style="width: 100%;border-bottom-style: none;">
        <li><p style="font-weight:bold;font-size:14px;">约满</p></li>
    </ul>
    <table id="contentTable" class="table table-striped table-bordered table-condensed"  style="width: 99%;">
		<thead>
			<tr>
				<th >名称</th>
				<th >上课时间</th>
				<th >价格</th>
				<th >限制人数</th>
				<th >图片</th>
				<th >推荐</th>
				<th >状态</th>
				<th width="100px;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${lessonStock}" var="lessonInfoTemp" varStatus="status">
			<tr>
				<td>
					${lessonInfoTemp.name}
                </td>
				<td>
				    <span><fmt:formatDate value="${lessonInfoTemp.startTime}" pattern="yyyy-MM-dd"/>~</span>
				    <span><fmt:formatDate value="${lessonInfoTemp.endTime}" pattern="yyyy-MM-dd"/></span>
				</td>
				<td>
					<c:choose> 
					    <c:when test="${lessonInfoTemp.benefitPrice != null && lessonInfoTemp.benefitPrice!= ''}">
						<s><font color="#CCCCCC">${fns:doubleFormat(lessonInfoTemp.basePrice)}</font></s><br/>
							${fns:doubleFormat(lessonInfoTemp.benefitPrice)}
						</c:when>
						<c:otherwise>
							${fns:doubleFormat(lessonInfoTemp.basePrice)}
						</c:otherwise>
				    </c:choose>
				</td>
				<td>
	                <c:choose> 
	                    <c:when test="${lessonInfoTemp.peopleLimit != null && lessonInfoTemp.peopleLimit!= ''}">
	                    	${lessonInfoTemp.peopleLimit}
	                    </c:when>
	                    <c:otherwise>
	                    	0
	                    </c:otherwise>
	                </c:choose>
				</td>
                <td>
		           <c:forEach items="${lessonInfoTemp.imageUrls}" var="imgUrl">
		               <img src="${imgUrl}" style="width:53px;height:40px;"/>
		           </c:forEach>
                </td>
				<td>
					${fns:getDictLabel(lessonInfoTemp.recommend, 'Quota', '否')}
				</td>
				<td>
					${fns:getDictLabel(lessonInfoTemp.state, 'lesson_state', '下架')}
				</td>
				<td>
					<input type="button" class="commonsmallbtn" value="编辑" style="width:40px;" onclick="window.location.href='${ctx}/lesson/lessonInfo/form?id=${lessonInfoTemp.id}&sortItem=${lessonInfo.sortItem}&sort=${lessonInfo.sort}'">
				    <div style="margin:0 auto;height:3px;"></div>
				    <input type="button" class="commonsmallbtn" value="删除" style="width:40px;" onclick="return confirmx('确认删除此课程/培训？删除后该课程/培训的相应已受理订单依然需要完成。', '${ctx}/lesson/lessonInfo/delete?id=${lessonInfoTemp.id}&sortItem=${lessonInfo.sortItem}&sort=${lessonInfo.sort}')">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>
</c:if>
 <!-- 课程预约订单结束 -->
 
 
 
 <!-- 场地预约订单开始 -->
 <c:if test="${ptlist.indexOf('3')>-1 }">
<div class="case" style="margin-top: 20px;">
<ul class="nav nav-tabs" style="width: 94%;border-bottom-style: none;margin-top: 10px;">
    <li><p style="font-weight:bold;font-size:14px;">场地预约订单</p></li>
    <li style="width: 100%;">
        <div style="float: left;width: 180px;">待付款场地预约订单：<a href="" onclick="openedit('课程预约订单','/order/orderField?payState=0&pending=1')" style="color: #60C1F7;">${pendingPayField}</a></div>
        <%-- <div style="float: left;width: 180px;">待预约场地预约订单：<a href="" onclick="openedit('课程预约订单','/order/orderField?orderState=0&pending=1')" style="color: #60C1F7;">${pendingHandleField}</a></div> --%>
    </li>
</ul>
 <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:99%">
         <thead>
            <tr>
                <th style="width:13%;">订单号</th>
                <th style="width:11.5%;">姓名</th>
                <th style="width:13%;">电话</th>
                <th style="width:13%;">预约场地</th>
                <th style="width:6%;">金额</th>
                <th style="width:6.5%;">终端类型</th>
                <th style="width:6.5%;">支付状态</th>
                <th style="width:6.5%;">订单状态</th>
                <th style="width:15%;">时间</th>
                <th style="width:9%;">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${newOrderFieldList}" var="orderField" varStatus="status">
            <tr>
                <td><a href="${ctx}/order/orderField/form?id=${orderField.id}&backurl=/sys/index">
                    ${orderField.orderNo}
                </a></td>
                <td>
                    ${orderField.accountName}
                </td>
                <td>
                    ${orderField.accountPhoneNumber}
                </td>
                <td>
                    ${orderField.name}
                    <c:if test="${orderField.orderFieldList != null && fn:length(orderField.orderFieldList) gt 0}">
                        <br/>
                        <fmt:formatDate value="${orderField.orderFieldList[0].appointmentTime}" pattern="yyyy年M月d日"/>
                        <c:forEach items="${orderField.orderFieldList}" var="orderFieldList" varStatus="status1">
                            <br/>
                            <fmt:formatDate value="${orderFieldList.startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${orderFieldList.endTime}" pattern="HH:mm"/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    ${orderField.payMoney}
                </td>
                <td>
                    ${fns:getDictLabel(orderField.type, 'order_type', "")}
                </td>
                <td <c:if test="${orderField.payState == 0}">style="color:red"</c:if>>
                    ${fns:getDictLabel(orderField.payState, 'pay_goods_state', "")}
                </td>
                <td <c:if test="${orderField.orderState == 0}">style="color:red"</c:if>>
                    <c:choose>
                        <c:when test="${orderField.outTimeState && orderField.orderState == '1'}">
                            <span>已完成</span>
                        </c:when>
                        <c:otherwise>
                            ${fns:getDictLabel(orderField.orderState, 'order_lesson_state', "")}
                        </c:otherwise>
                    </c:choose>
                    
                </td>
                <td>
                    <c:if test="${orderField.createDate !=null && orderField.createDate !=''}">
                    <span>下单：<fmt:formatDate value="${orderField.createDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
                    </c:if>
                    <c:if test="${orderField.payTime !=null && orderField.payTime !=''}">
                    <span>支付：<fmt:formatDate value="${orderField.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </c:if>
                </td>
                <td>
                    <input id="btuElemView" type="button" class="commonsmallbtn" value="查看" style="width:40px;" onclick="top.$.jBox.tip.mess = null;window.location.href='${ctx}/order/orderField/form?id=${orderField.id}'">
                    <div style="margin:0 auto;height:3px;"></div>
                    <c:choose>
                        <c:when test="${orderField.outTimeState && orderField.orderState == '1'}">
                        </c:when>
                        <c:when test="${orderField.orderState == '2'}">
                        </c:when>
                        <c:otherwise>
                            <input id="btuElemCancel" type="button" class="commonsmallbtn" value="取消" style="width:40px;" data-toggle="modal" data-target="#myModal${status.index}">
                        </c:otherwise>
                    </c:choose>
    <div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="display: none;">
        <div class="modal-dialog"  style="text-align:left;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
            <form:form id="inputForm${status.index}" modelAttribute="orderField" action="${ctx}/order/orderField/cancel" method="post" onsubmit="return cancelRemarksCheck('#cancelRemarks${status.index}');">
                <div class="modal-body">
                    <select id="cancelRemarksSelect" class="required" name="cancelRemarksSelect" onchange="setRemarks(this.value,'${status.index}')" style="width:200px;">
                      <option selected value="">请选择订单取消原因</option>
                      <option value="客户联系取消">客户联系取消</option>
                      <option value="无法联系客户">无法联系客户</option>
                      <option value="商品无法送达">商品无法送达</option>
                      <option value="客户未付款，释放库存">客户未付款，释放库存</option>
                      <option value="其他原因">其他原因</option>
                    </select>
                    <br/>
                    <br/>
                    <input type="hidden" name="id" value="${orderField.id}">
                    <input type="hidden" name="orderNo" value="${orderField.orderNo}">
                    <input type="hidden" name="updateDateString" value="${orderField.updateDateString}">
                    <input id="cancelRemarks${status.index}" name="cancelRemarks" style="width:200px;display:none;" placeholder="请填写取消原因" class="input-xlarge required" value="" maxlength="81" type="text">
                    <p>订单取消后系统将自动退款。</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="commonbtn" style="width:60px;">确定</button>
                    <button type="button" class="commonbtn" style="width:60px;" data-dismiss="modal">取消</button>
                </div>
            </form:form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
  <c:if test="${fieldbzCount>0}">  
   <ul class="nav nav-tabs" style="width: 100%;border-bottom-style: none;">
        <li><p style="font-weight:bold;font-size:14px;">约满</p></li>
    </ul> 
 <table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:99%">
		<thead>
			<tr>
				<th width="50px;">编号</th>
				<th width="200px;">场地名称</th>
				<th>预约情况</th>
				<th width="100px;">预约时长</th>
				<th width="100px">状态</th>
				<shiro:hasPermission name="field:fieldInfo:edit"><th width="100px;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${fieldStock}" var="fieldInfo" varStatus="sta">
			<tr>
				<td <c:if test="${fieldInfo.state=='1'}">style="background-color:#C5E7F5;" </c:if>><a href="${ctx}/field/fieldInfo/form?id=${fieldInfo.id}">
					${sta.index+1}
				</a></td>
				<td <c:if test="${fieldInfo.state=='1'}">style="background-color:#C5E7F5;" </c:if>>
					${fieldInfo.name}
				</td>
				<td style="<c:if test="${fieldInfo.state=='1'}">background-color:#C5E7F5; </c:if>text-align:left;">
					<c:if test="${empty fieldInfo.fieldPartitionPriceList}">数据未生成</c:if>
					<c:forEach items="${fieldInfo.fieldPartitionPriceList}" var="fieldPartitionPrice" varStatus="status">
						<%-- <c:if test="${status.count%8==0 }"><br/></c:if> --%>
						<c:if test="${fieldPartitionPrice.state==0}">
							<span class="field_green"
								  onclick="alertAddField('${fieldInfo.name}',
										  '<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="yyyy年MM月dd日"/>',
										  '<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>',
										  '${fieldPartitionPrice.basePrice}',${fieldPartitionPrice.outTimeState},
										  '${fieldPartitionPrice.id}','${fieldInfo.id}','${fieldInfo.state}')">
								<input type="hidden"  name="fieldPartitionPriceId" value="${fieldPartitionPrice.id}"/>
								<input type="hidden" name="fieldPartitionPriceOutTimeState" value="${fieldPartitionPrice.outTimeState}"/>
								<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>
									~
								<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>
							</span>
						</c:if>
						<c:if test="${fieldPartitionPrice.state==1&&fieldPartitionPrice.outTimeState}">
							<span class="field_red" onclick="alertCancelField('${fieldInfo.name}',
									'<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="yyyy年MM月dd日"/>',
									'<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>',
									'${fieldPartitionPrice.basePrice}','${fieldPartitionPrice.id}')">
								<input type="hidden" name="fieldPartitionPriceId" value="${fieldPartitionPrice.id}"/>
								<input type="hidden" name="fieldPartitionPriceOutTimeState" value="${fieldPartitionPrice.outTimeState}"/>
								<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>
							</span>
						</c:if>
						<c:if test="${fieldPartitionPrice.state==2||(fieldPartitionPrice.state==1&&!fieldPartitionPrice.outTimeState)}">
							<span class="field_silver" onclick="alertGetField('${fieldInfo.name}',
									'<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="yyyy年MM月dd日"/>',
									'<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>',
									'${fieldPartitionPrice.basePrice}','${fieldPartitionPrice.id}')">
								<input type="hidden"  name="fieldPartitionPriceId" value="${fieldPartitionPrice.id}"/>
								<input type="hidden"  name="fieldPartitionPriceOutTimeState" value="${fieldPartitionPrice.outTimeState}"/>
								<fmt:formatDate value="${fieldPartitionPrice.startTime}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${fieldPartitionPrice.endTime}" pattern="HH:mm"/>
							</span>
						</c:if>

					</c:forEach>
				</td>
				<td <c:if test="${fieldInfo.state=='1'}">style="background-color:#C5E7F5;" </c:if>>
					${fieldInfo.shortTime}小时
				</td>
				<td name="fState" <c:if test="${fieldInfo.state=='1'}">style="background-color:#C5E7F5;" </c:if>>

				</td>
				<td <c:if test="${fieldInfo.state=='1'}">style="background-color:#C5E7F5;" </c:if>>
					<shiro:hasPermission name="field:fieldInfo:edit">
					<input id="btuElemView" type="button" class="commonsmallbtn" value="编辑" style="width:40px;" onclick="editfield('${fieldInfo.id}')">
					<c:if test="${not empty fieldInfo.fieldPartitionPriceList}">
						<input type="hidden" value="0" id="tag">
					</c:if>
						<br />
					</shiro:hasPermission>
						
						<%-- <c:if test="${fieldInfo.state=='0'}">
						<shiro:hasPermission name="field:fieldInfo:suspend">
						<c:if test="${not empty fieldInfo.fieldPartitionPriceList}">
							<a href="${ctx}/field/fieldInfo/suspend?id=${fieldInfo.id}" onclick="return confirmx1('确认暂停<span style=\'color:red;\'>“${fieldInfo.name}”</span>的预约吗？点击“确定”，我们将暂停该场地的预约，未完成的预约订单需要继续完成或手动取消预约，为避免纠纷，请先行联系已预订的客户进行告知。', this.href,'','暂停预约')">暂停预约</a>
							<br />
						</c:if>
						</shiro:hasPermission>
						</c:if>
				
					
					<c:if test="${fieldInfo.state=='1'}">
						<shiro:hasPermission name="field:fieldInfo:recovery">
							<a href="${ctx}/field/fieldInfo/recovery?id=${fieldInfo.id}" onclick="return confirmx1('确定恢复<span style=\'color:red;\'>“${fieldInfo.name}”</span>的预约吗？点击“确定”，我们将重新生成该场地可预约场次，前端应用将可以预约未来7日内场次', this.href,'','恢复预约')">恢复预约</a>
							<br />
						</shiro:hasPermission>
						<shiro:hasPermission name="field:fieldInfo:delete">
							<a href="${ctx}/field/fieldInfo/delete?id=${fieldInfo.id}" onclick="return confirmx1('确认删除<span style=\'color:red;\'>“${fieldInfo.name}”</span>？场地将在已成功预约的预约场次完成后，从场地预约列表删除。', this.href,'','删除预约')">删除预约</a>
								<br />
						</shiro:hasPermission>
					</c:if> --%>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>
<script>

function editfield(id){
	var tag=$('#tag').val();
	if(tag==undefined){tag=1;}
	window.parent.addTabByMy('场地编辑','/field/fieldInfo/form?id='+id+"&tag="+tag);
}
</script>
</c:if></div>
</div>



 
</body>
</html>



