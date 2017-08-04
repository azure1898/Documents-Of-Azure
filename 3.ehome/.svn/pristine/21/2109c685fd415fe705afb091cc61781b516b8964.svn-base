var vm = new Vue({
	el: "#app",
	data: {
		order: {},
		timePeriod: [],
		way:1,
		method:"",
		serviceDate: {
			label: "",
			date: "",
			desc: "",
			isImmediate: 1,
			start: "",
			end: ""
		},
		leaveWords:"",
		affirmOrder:{},
		errorMessage:"",
		i:0
	},
	mounted: function() {
		this.$nextTick(function() {
			this.cartView();
		})
	},
	methods: {
		cartView: function() {
			var _this = this;
			var path = interfaceUrl+"/live/confirmServiceOrder";

			this.$http.get(path,{	
				userID:userInfo.userID,
      			buildingID:userInfo.buildingID,
      			businessID:getQueryString("businessID"),
				serviceID:getQueryString("id")
			}).then(function(res) {
				if(res.data.code == 1000){
					_this.order = res.data.data;
					_this.way=_this.order.serviceWay
				}
				if(_this.way==0){
						_this.method="立即上门"
					}else{
						_this.method="到店时间"
					}


				if (res.data.data.serviceDate && res.data.data.serviceDate.length > 0) {
					var delivery = res.data.data.serviceDate[0];
					var delivery2 = res.data.data.serviceDate[1];
					
					_this.serviceDate.date = delivery.date;
					_this.serviceDate.date = delivery2.date;

					if (delivery.timePeriod && delivery.timePeriod.length > 0) {
						_this.serviceDate.isImmediate = delivery.timePeriod[0].isImmediate;
						_this.serviceDate.start = delivery.timePeriod[0].start;
						_this.serviceDate.end = delivery.timePeriod[0].end;
						if (delivery.timePeriod[0].isImmediate == 1) {
							_this.serviceDate.label =_this.method +"(预计" + delivery.timePeriod[0].end + "到达）";
						} else {
							_this.serviceDate.label = "预计" + this.serviceDate.desc + " " + delivery.timePeriod[0].start + '~' + delivery.timePeriod[0].end + "到达";
						}
					}else{
//							_this.serviceDate.label ="几天已满";
                       _this.serviceDate.isImmediate = delivery2.timePeriod[0].isImmediate;
						_this.serviceDate.start = delivery2.timePeriod[0].start;
						_this.serviceDate.end = delivery2.timePeriod[0].end;
						if (delivery2.timePeriod[0].isImmediate == 1) {
							//上门
							_this.serviceDate.label =_this.method +"(预计111明天" + delivery2.timePeriod[0].end + "到达）";
						} else {
							//到店
							_this.serviceDate.label = "预计明天" + this.serviceDate.desc + " " + delivery2.timePeriod[0].start + '~' + delivery2.timePeriod[0].end + "到达";
						}
					}
				}
				
				_this.timePeriod = res.data.data.serviceDate[0].timePeriod;
			})
			
		},
		changeDelivery: function(delivery) {
			var _this = this;
			n=0;
			$("#timeModal .business_mainmenu2 li.selected").removeClass("selected");
			$(event.target).closest("li").addClass("selected");

			_this.timePeriod = delivery.timePeriod;
			_this.serviceDate.date = delivery.date;
			_this.serviceDate.desc = delivery.desc;
			console.log(_this.serviceDate.date);
		},
		timeSelected: function(period) {
			var _this = this;
			if(_this.way==0){
				_this.method="上门时间"
			}else{
				_this.method="到店时间"
			}
			_this.serviceDate.isImmediate = period.isImmediate;
			_this.serviceDate.start = period.start;
			_this.serviceDate.end = period.end;

			if (period.isImmediate == 1) {
				_this.serviceDate.label = _this.method +"（预计" + period.end + "到达）";
			} else {
				_this.serviceDate.label = _this.method + "(预计" + this.serviceDate.desc + " " + period.start + '~' + period.end + "到达)";
			}

			$("#timeModal").modal('toggle');
		},
		//优惠劵选择
		changeStatus1:function(i){
			var _this = this;
			this.i+=1;
			if(_this.i%2==1){
				$(event.target).attr("src","../../images/01.png")
				$(".choose_coup_main .choose_coup_main_right img").attr("src","../../images/02.png")
			     $("#coups").text("不使用优惠券");
			}
			else{
				$(event.target).attr("src","../../images/02.png")
				 $("#coups").text("");
			}
		},
		changeStatus2:function(item){
			console.log(item);
				$(".details_spgm_right_2 img").attr("src","../../images/02.png")
				$(".choose_coup_main .choose_coup_main_right img").attr("src","../../images/02.png")
				$(event.target).closest("img").attr("src","../../images/01.png")
		      
		      $("#coups").text("-￥"+item.couponMoney);
		}
		,
		//改变数量
		changeMoney_add: function(order) {	
			if(order.serviceNumber<order.stockNumber){
				order.serviceNumber++;
				if(order.serviceNumber>1){
						$("#num_jian").css("background-image","url(../../images/service/num_jian2.png)");
					if (order.serviceNumber == order.stockNumber) {
						$(".order_tip").css("display", "block");
					}
				}
			}
		}
		,
		changeMoney_reduce: function(order) {
			if(order.serviceNumber>1){
				order.serviceNumber--;
				$(".order_tip").css("display", "none");
				 if(order.serviceNumber==1){

						$("#num_jian").css("background-image","url(../../images/num_jian.png)");
          	
          }
			}
         
			
			
			
		},
		//生成订单
		affirm:function(){
			var _this = this;
			var path = interfaceUrl+"/live/submitServiceOrder";
			//如果为到店服务
			if(_this.way==1){
				var name = $.trim($(".inut-name").val());
	            var phone =$.trim($(".inut-phone").val());
	            var reg=/^[\u4e00-\u9fa5]{0,}$/;
				if(name==""){
	            	layer.open({
							    content: '请填写联系人',
							     btn: '确定',
						  		shadeClose: false,
							  });
	            }else if(!(reg.exec(name))){
					layer.open({
							    content: '请填写正确联系人姓名',
							    btn: '确定',
						  		shadeClose: false,
							  });
	            }else if(phone==""){
	            	layer.open({
							    content: '请填写联系电话',
							     btn: '确定',
						  		shadeClose: false,
							  });
	            }else if( !(/^1[34578]\d{9}$/.test(phone))){
						layer.open({
							    content: '请填写正确联系电话',
							     btn: '确定',
						  		shadeClose: false,
							  });
	                }else{ 
						this.$http.post(path,{
							userID:userInfo.userID,
			      			buildingID:userInfo.buildingID,
			      			businessID:_this.order.businessID,
							contactPerson:_this.order.contactPerson,
							contactPhone:_this.order.contactPhone,
							contactAddress:_this.order.contactAddress,
							serviceID:_this.order.serviceID,
							servicePrice:_this.order.servicePrice,
							serviceNumber:_this.order.serviceNumber,
							isImmediate:_this.serviceDate.isImmediate,
							serviceStart:_this.serviceDate.date+_this.serviceDate.start,
							serviceEnd:_this.serviceDate.date+_this.serviceDate.end,
//							serviceDate:_this.serviceDate.date,
//							couponID:_this.order.couponID,
							leaveMessage:_this.leaveWords
							
						},{emulateJSON: true}).then(function(res) {
							if(res.data.code == 1000){
								_this.affirmOrder = res.data.data;
							}else{
								_this.errorMessage = res.data.message;
								layer.open({
								 	content: _this.errorMessage,
									btn: '确定',
									shadeClose: false,
								});
							}
						})
	                }
				
			}else{
				//如果为上门服务
				this.$http.post(path,{	
							userID:userInfo.userID,
			      			buildingID:userInfo.buildingID,
			      			businessID:_this.order.businessID,
							contactPerson:_this.order.contactPerson,
							contactPhone:_this.order.contactPhone,
							contactAddress:_this.order.contactAddress,
							serviceID:_this.order.serviceID,
							servicePrice:_this.order.servicePrice,
							serviceNumber:_this.order.serviceNumber,
							isImmediate:_this.serviceDate.isImmediate,
							serviceStart:_this.serviceDate.date+_this.serviceDate.start,
							serviceEnd:_this.serviceDate.date+_this.serviceDate.end,
//							serviceDate:_this.serviceDate.date,
			//				couponID:_this.order.couponID,
							leaveMessage:_this.leaveWords
						},{emulateJSON: true}).then(function(res) {
							if(res.data.code == 1000){
								_this.affirmOrder = res.data.data;
							}else{
								_this.errorMessage = res.data.message;
								layer.open({
								 	content: _this.errorMessage,
									btn: '确定',
									shadeClose: false,
								});
							}
						})
			}
			
			
		}
		
		
	}
})

//$("#timeModal").on("show.bs.modal",function(){
//	vm.timePeriod=[];
//	$("#timeModal .business_mainmenu2 li:eq(1)").click();
//});