var vm = new Vue({
	el:"#app",
	data:{
		urlList:{
			list:"commoditylist.html?id=",
			groupbuy:"../groupbuy/groupbuydetail.html?id=",
			phoneicon:"../../images/telphone.png",
			goicon:"../../images/grey_go.png"
		},
		business:{}
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
			
			this.$http.get(interfaceUrl + "/live/getBusinessIndex",
			{ buildingID: userInfo.buildingID, businessID: getQueryString("id")}).then(function(res){
				_this.business = res.data.data;
			});	
		},
		getMore:function(business){
			this.$http.get(interfaceUrl + "/live/getMoreGroupBuy",
			{ buildingID: userInfo.buildingID, businessID: getQueryString("id")}).then(function(res){
				business.groupBuy = res.data.data;
				$(".order_whitebox").hide();
			});
		}
    }
});