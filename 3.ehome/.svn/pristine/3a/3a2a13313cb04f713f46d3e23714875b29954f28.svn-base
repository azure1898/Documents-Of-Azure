var vm = new Vue({
	el:"#app",
	data:{
		urlList:{
			list:"commoditylist.html?id=",
			groupbuy:"../groupbuy/groupbuydetail.html?id=",
			phoneicon:"../../images/telphone.png",
			goicon:"../../images/grey_go.png"
		},
		order:{},
		timePeriod:[],
		deliveryDate:{
			label:"",
			date:"",
			desc:"",
			isImmediate:1,
			start:"",
			end:""
		}
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		})					
	},
	methods:{
		// 渲染页面
		cartView:function(){
			var _this = this;
			
			this.$http.get(interfaceUrl + "/live/confirmBusinessOrder",
			{ userID: userInfo.userID,buildingID: userInfo.buildingID,
				businessID:getQueryString("id")}).then(function(res){
				_this.order = res.data.data;
				
				if(res.data.data.deliveryDate && res.data.data.deliveryDate.length > 0){
					var delivery = res.data.data.deliveryDate[0];
					_this.deliveryDate.date = delivery.date;
					
					if(delivery.timePeriod && delivery.timePeriod.length >0){
//							if(delivery.timePeriod && delivery.timePeriod.length >=0){
						_this.deliveryDate.isImmediate = delivery.timePeriod[0].isImmediate;
						_this.deliveryDate.start = delivery.timePeriod[0].start;
						_this.deliveryDate.end = delivery.timePeriod[0].end;
					
						if(delivery.timePeriod[0].isImmediate == 1){
							_this.deliveryDate.label = "立即配送（预计"+delivery.timePeriod[0].end+"送达）";
						}
						else{
							_this.deliveryDate.label = "预计"+this.deliveryDate.desc+" "+delivery.timePeriod[0].start+'~'+delivery.timePeriod[0].end+"送达";
						}	
					}		
				}
				_this.timePeriod = res.data.data.deliveryDate[0].timePeriod;
			})
		},
		changeDelivery:function(delivery){
			$("#timeModal .business_mainmenu2 li.selected").removeClass("selected");
			$(event.target).closest("li").addClass("selected");
			
			this.timePeriod = delivery.timePeriod;
			this.deliveryDate.date = delivery.date;
			this.deliveryDate.desc = delivery.desc;
		},
		timeSelected:function(period){
			this.deliveryDate.isImmediate = period.isImmediate;
			this.deliveryDate.start = period.start;
			this.deliveryDate.end = period.end;
				
			if(period.isImmediate == 1){
				this.deliveryDate.label = "立即配送（预计"+period.end+"送达）";
			}
			else{
				this.deliveryDate.label = "预计"+this.deliveryDate.desc+" "+period.start+'~'+period.end+"送达";
			}
					
			$("#timeModal").modal('toggle');
		},
		submitOrder:function(){
			var _this = this;
			
			this.$http.post(interfaceUrl + "/live/submitBusinessOrder",
			{ 
				userID: userInfo.userID,
				buildingID: userInfo.buildingID,
				businessID:getQueryString("id"),
				contactPerson:_this.order.contactPerson,
				contactPhone:_this.order.contactPhone,
				contactAddress:_this.order.contactAddress,
				isImmediate:0,
				deliveryStart:"2017/8/3 19:00",
				deliveryEnd:"2017/8/3 20:00",
				leaveMessage:""
			},{emulateJSON: true}).then(function(res){
				//alert(res);
			})
		}
	}
});

$("#timeModal").on("show.bs.modal",function(){
	vm.timePeriod=[];
	$("#timeModal .business_mainmenu2 li:eq(0)").click();
});
