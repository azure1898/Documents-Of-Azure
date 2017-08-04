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
			{ userID:userInfo.userID,buildingID: userInfo.buildingID, businessID: getQueryString("id")}).then(function(res){
				_this.business = res.data.data;
				_this.collection(_this.business.isCollection,_this.business.businessID)
			});	
		},
		getMore:function(business){
			this.$http.get(interfaceUrl + "/live/getMoreGroupBuy",
			{ buildingID: userInfo.buildingID, businessID: getQueryString("id")}).then(function(res){
				business.groupBuy = res.data.data;
				$(".order_whitebox").hide();
			});
		},
			collection:function(status,id){
					var _this = this;
					var businessid=id;
					 if(status==0){
					 	  $("#collection").removeClass().addClass("collection_btn");
					$("#collection").bind("click",function(){
					  
					    layer.open({
					    content: '收藏成功'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
					   _this.add_collections(path_add,businessid);	
					  $(this).removeClass().addClass("collection_btn2");
					  
					}) 
					  						
						}
					  else if(status==1){	
					  	$("#collection").removeClass().addClass("collection_btn2");
					  	$("#collection").bind("click",function(){
					    layer.open({
					    content: '取消收藏'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
					   _this.cancel_collections(path_cancle,businessid);			
					  $(this).removeClass().addClass("collection_btn");
					   });					    				
						}                 		
		},
		add_collections:function(path,id){
			var _this = this;			
			this.$http.post(path,
				{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:id},
				{emulateJSON: true}).then(function(res){
                  _this.item = res.data.data;
                        console.log(res.status);})
			
		},
		cancel_collections:function(path,id){
			var _this = this;
			this.$http.post(path,
				{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:id},
				{emulateJSON: true}).then(function(res){
                  _this.item = res.data.data;
                        console.log(res.status);})
			
		}	
    }
});