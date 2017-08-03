var vm = new Vue({
	el:"#app",
	data:{
			urlList:{
				list:"commoditylist.html?id=",
				detail:"commoditydetail.html?id=",
				index:"businessindex.html?id=",
				order:"commodityorder.html?id=",
				shoppingcarticon:"../../images/shoopping_chart.png",
				business:"../../images/dian.png",
				contactbusiness:"../../images/phone.png"
			},
			commodity:{},
			currentSpecification:{},
			shoppingcart:{
				num:0,
				totalMoney:0,
				lessMoney:0,
				commodities:[]
			}
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		})
	},
	methods:{
		cartView:function(){
			var _this = this;
					
			this.$http.get(interfaceUrl + "/live/getCommodityDetail",
			{ buildingID: userInfo.buildingID, businessID: getQueryString("bid"),commodityID: getQueryString("id")}).then(function(res){
				_this.commodity = res.data.data;
				
				_this.getShoppingCart();
			})
		},
		getShoppingCart:function(){
			var _this = this;
			
			this.$http.get(
				interfaceUrl + "/live/getShoppingCart",
				{
					userID: userInfo.userID,
					buildingID: userInfo.buildingID,
					businessID:getQueryString("bid")
				}).then(function(res){
					_this.shoppingcart.commodities = res.data.data.commodities;
					_this.shoppingcart.totalMoney = res.data.data.totalMoney;
					_this.shoppingcart.num = res.data.data.totalNumber;

					if(_this.commodity.sendMoney > 0){
						if(_this.commodity.sendMoney > _this.shoppingcart.totalMoney){
							_this.shoppingcart.lessMoney = _this.commodity.sendMoney - _this.shoppingcart.totalMoney;
						}
						else{
							_this.shoppingcart.lessMoney = 0;
						}
					}
					else{
						_this.shoppingcart.lessMoney = 0;
					}
				});
		},
		openShoppingCart:function(){
			var _this = this;
			
			this.$http.get(interfaceUrl + "/live/getShoppingCart",
			{ userID: userInfo.userID,buildingID: userInfo.buildingID,
				businessID:getQueryString("bid")}).then(function(res){
				_this.shoppingcart.commodities = res.data.data.commodities;
				_this.shoppingcart.totalMoney = res.data.data.totalMoney;
			});
			
			$(".gwcView").fadeIn("3000");
		},
		closeShoppingCart:function(){
			$(".gwcView").fadeOut("3000");
		},
		addShoppingCart:function(commodity){
			var _this = this;
			
			this.$http.get(interfaceUrl + "/live/addShoppingCart",
			{ userID: userInfo.userID,buildingID: userInfo.buildingID,
				businessID:getQueryString("bid"),
				commodityID:commodity.commodityID,
				specificationID:commodity.currentSpeID
			}).then(function(res){
				_this.shoppingcart.totalMoney = res.data.data.totalMoney;
				_this.shoppingcart.num = res.data.data.commodityNumber;
				commodity.commodityNumber += 1;
				
				if(commodity.isMoreSpe == 1){
					commodity.specifications.forEach(function(specification,index){
						if(specification.specificationID == commodity.currentSpeID){
							specification.specificationNumber = commodity.commodityNumber;
						}
					})
				}
				
				_this.getShoppingCart();
			});
		},
		reduceShoppingCart:function(type,commodity){
			var _this = this;
			
			if(type==1){
				this.$http.get(interfaceUrl + "/live/reduceShoppingCart",
				{ userID: userInfo.userID,buildingID: userInfo.buildingID,
					businessID:getQueryString("bid"),
					commodityID:commodity.commodityID,
					specificationID:commodity.currentSpeID
				}).then(function(res){
					_this.shoppingcart.totalMoney = res.data.data.totalMoney;
					_this.shoppingcart.num = res.data.data.commodityNumber;
					commodity.commodityNumber -= 1;
				
					_this.getShoppingCart();
				});
			}
			else{
				this.$http.get(interfaceUrl + "/live/reduceShoppingCart",
				{ userID: userInfo.userID,buildingID: userInfo.buildingID,
					businessID:getQueryString("id"),
					commodityID:commodity.commodityID,
					specificationID:commodity.specificationID
				}).then(function(res){
					_this.shoppingcart.totalMoney = res.data.data.totalMoney;
					_this.shoppingcart.num = res.data.data.commodityNumber;
					commodity.commodityNumber -= 1;
				
					_this.getShoppingCart();
				});
			}
			
		},
		clearShoppingCart:function(){
			var _this = this;
			
			this.$http.get(interfaceUrl + "/live/emptiedShoppingCart",
			{ userID: userInfo.userID,buildingID: userInfo.buildingID,
				businessID:getQueryString("id")}).then(function(res){
				_this.shoppingcart.totalMoney = 0;
				_this.shoppingcart.num = 0;
				
				for(var commodity in _this.commodityList)
				{
					_this.commodityList[commodity].commodityNumber = 0;
				}
				
				_this.getShoppingCart();
			});
		},
		openMoreSpe:function(commodity){
			$("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
		},
		changeSpecification:function(specification){
			$("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
			$(event.target).closest("a").addClass("active");
			this.currentSpecification = specification;
		},
		confirmSpecification:function(){
			this.commodity.originalPrice = this.currentSpecification.specificationPrice;
			this.commodity.discountedPrice = this.currentSpecification.specificationdisPrice;
			this.commodity.commodityNumber = this.currentSpecification.specificationNumber;
			this.commodity.currentSpeID = this.currentSpecification.specificationID;
			
			$("#moreSpeModal").modal('toggle');
		}
	}
});

$("#moreSpeModal").on("show.bs.modal",function(){
	vm.currentSpecification={};
	$("#moreSpeModal .tanchukuang_fenlei a:eq(0) > span").click();
});
				