var vm = new Vue({
	el:"#app",
	data:{
		groups:[],
		urlList:{
			detail:"groupbuydetail.html?id=",
			module:"modulelist.html?id=",
			order:"groupbuyorder.html?id="
		},
		currentType:1,
		status_1:'已抢光',
		status_2:'抢购中',
		status_3:'即将开始',
		
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
				this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
					var _this=this;
      this.$http.get(interfaceUrl+'/live/getGroupBuyList',{userID:userInfo.userID,buildingID:userInfo.buildingID,type:1}).then(function(response) {
                  _this.groups= response.data.data;
                        console.log(response.status);
		},function(response){
			alert(response.status)
		})
//this.cartView();
				})
			},
	methods:{
		changeActivity:function(id){
			var _this = this;
			if(id=="first"){
			
      this.$http.get(interfaceUrl+'/live/getGroupBuyList',{userID:userInfo.userID,buildingID:userInfo.buildingID,type:1}).then(function(response) {
                  _this.groups= response.data.data;
                        console.log(response.status);
		},function(response){
			alert(response.status)
		})			
				 $(".Boutique_buy_title>.Boutique_buy_title_left>a.active").removeClass("active");
                 $(event.target).addClass("active");
			}
			else if(id=="second"){
				this.currentType = 2;
				var _this=this;
                this.$http.get(interfaceUrl+'/live/getGroupBuyList',{userID:userInfo.userID,buildingID:userInfo.buildingID,type:2}).then(function(response) {
                        _this.groups= response.data.data;
                        console.log(response.status);     
		},function(response){
			alert(response.status)
		})      
		  
		  if(_this.groups&&_this.groups.length>0){
			
			 $("#second").text("暂无新的团购准备上线")
		}
                
				 $(".Boutique_buy_title>.Boutique_buy_title_left>a.active").removeClass("active");
                 $(event.target).addClass("active");		
			}
			else{
				alert("出错！！")
			}
			
			
		}
		}
});
