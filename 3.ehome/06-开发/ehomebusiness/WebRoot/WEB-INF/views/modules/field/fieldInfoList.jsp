<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>场地预约管理</title>
	<meta name="decorator" content="default"/>
	<style>
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
			var html = "<div style='padding-left:12px;'>"+msg+"</div><div style='padding-left:12px;'>顾客姓名：<input class=\"required\" maxlength='10' type='text' style='width: 80px;' placeholder=\"请输入姓名\" id='u_name' name='u_name' />" +
				"&nbsp;&nbsp;&nbsp;&nbsp;价格：<input class=\"required\" maxlength=\"9\"  type='text' style='width: 40px;' id='y_price'  placeholder=\"价格\" value='"+price+"' name='y_price' />元/小时</div>" +
				"<div style='padding-left:12px;'>联系方式：<input type='text'  placeholder=\"请输入联系方式\" id='u_phone' name='u_phone' /></div>";
			var submit = function (v, h, f) {
                if (v == false) {
                    top.$.jBox.close();
                    return true;
                } else {
						
                    if (f.u_name == '' || f.u_name.indexOf(" ")>-1) {
					top.$.jBox.tip("请正确输入顾客姓名。", 'error', { focusId: "u_name" }); // 关闭设置 yourname 为焦点
					return false;
					}
					if (f.y_price == '' || f.y_price.indexOf(" ")>-1 || isNaN(f.y_price)) {
						top.$.jBox.tip("请正确输入价格。", 'error', { focusId: "y_price" }); // 关闭设置 yourname 为焦点
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

            var date= $("#appointmentTime").val().replace("-0","-");
            if (addDate(date,0)==getMaxDate()){
                $("span.time_right").removeAttr("onclick");
            }
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/field/fieldInfo/">场地预约列表</a></li>
		<%--<shiro:hasPermission name="field:fieldInfo:edit"><li><a href="${ctx}/field/fieldInfo/form">场地预约添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="fieldInfo" action="${ctx}/field/fieldInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<%--	<li><label>场地名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>--%>
			<li><span class="time_left" onclick="clickTimeSelect(-1)">◀</span>
				<input id="appointmentTime" name="partitionPrice.appointmentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${fieldInfo.partitionPrice.appointmentTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:getMaxDate() ,onpicked:function(){return page();}});" />
				<span class="time_right" onclick="clickTimeSelect(1)">▶</span>
			</li>
			<li class="btns" style="float: right"><label></label>
			
			<input id="btnCancel" class="commonbtn" style="width: 100px;height: 30px;border-radius:10px;font-size: 14px;" type="button" value="新增场地" style="width: 50px;height: 30px;border-radius:10px;font-size: 14px;" onclick="window.location.href = '${ctx}/field/fieldInfo/form'"/>
			
			</li>
			<li style="float: right;line-height: 40px;"><label>图例：</label>
				<span class="field_green" style="display: inline;">可预约</span>
				<span class="field_red" style="display: inline;">已预约</span>
				<span class="field_silver" style="display: inline;">已消费</span>
			</li>
			<%--<li class="btns"><a onclick="return page();" class="btn btn-success"><i class="icon-search icon-white"></i> 查询</a></li>--%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:98.3%">
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
		<c:forEach items="${list}" var="fieldInfo" varStatus="sta">
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
				 <c:if test="${fieldInfo.delFlag=='3'}">将在已预约场地消费后自动删除</c:if> 
				<c:if test="${fieldInfo.delFlag!=3}">
					<shiro:hasPermission name="field:fieldInfo:edit">
					
						<a href="${ctx}/field/fieldInfo/form?id=${fieldInfo.id}<c:if test="${not empty fieldInfo.fieldPartitionPriceList}">&tag=0</c:if>">编辑场地</a>
						<br />
					</shiro:hasPermission>
						
						<c:if test="${fieldInfo.state=='0'}">
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
					</c:if>
					</c:if>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>