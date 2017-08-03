var vm = new Vue({
	el: "#app",
	data: {
		particulars: {},
		urlList: {
			detail: "groupbuydetail.html?id=",
			module: "modulelist.html?id=",
			order: "groupbuyorder.html?id="
		},
		isOK: true
		
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
				var _this=this;
      this.$http.get(interfaceUrl+'/live/getGroupBuyDetail',{
      	userID:userInfo.userID,
      	groupBuyID:getQueryString("id")
      	
      	}).then(function(res) {
                  _this.particulars = res.data.data;               
                 _this.init(_this.particulars.stockNumber,_this.particulars.TimeDiff,_this.particulars.groupBuyStatus);
//               _this.judge(_this.particulars.isAnyTimeCancel,_this.particulars.isExpiredCancel,_this.particulars.isFreeReservation);
	
      	
      	},function(res){
			alert(res.status);
		})
    })

		
	},
	methods: {
		init:function(num,time,status){
			var date =new Date();
			date.setSeconds(time);			
			if(status==1){
				this.isOK=false;
				 $("#fnTimeCountDown").fnTimeCountDown(date);
					 $("#timeStatus>.word_tip").text("距离开始：");
					var days=Math.floor(time/60/60/24);
					$("#status_tip").addClass("business_price_box3").html("<a href='#'><span id='Days'></span>开始抢</a>");
				 $("#Days").text(days+"天后");
				 $(".business_bottom>div").removeClass().addClass("ser_zbtn2");
				$("#change_groupdeatil").text("立即抢购");
			}				
			else if(status==2){
				 $("#fnTimeCountDown").fnTimeCountDown(date);
				 $("#timeStatus>.word_tip").text("距离结束：");
				if(num>0){
					$(".business_bottom>div").removeClass().addClass("ser_zbtn");
				$("#change_groupdeatil").text("立即抢购");
					
				}
				else{
					this.isOK=false;
			  $(".business_bottom>div").removeClass().addClass("ser_zbtn2");
				$("#change_groupdeatil").text("商品已抢光");
				}
				
			}
       else if(status==3){
       	this.isOK=false;
//				//结束
				$(".business_bottom>div").removeClass().addClass("ser_zbtn2");
				$("#change").text("抢购已结束");
       	
       	
       }
		}
//	,judge:function(cancel_1,cancel_2,reservation){
//		
//	   if(cancel_1==1){
//	   	$("#cancle_1").removeClass().addClass("sc_business2")
//	   }
//	else if(cancel_1==0){		
//	   	$("#cancle_1").removeClass().addClass("sc_business2_2")
//	}
//	else if(cancel_2==1){
//	   	$("#cancle_2").removeClass().addClass("sc_business2")	
//	}
//	else if(cancel_2==0){
//	   	$("#cancle_2").removeClass().addClass("sc_business2_2")	
//	}
//	else if(reservation==1){
//	   	$("#reservation").removeClass().addClass("sc_business2")	
//	}
//	else if(reservation==0){
//	   	$("#reservation").removeClass().addClass("sc_business2")
//	}
//	
//	}
	}
});