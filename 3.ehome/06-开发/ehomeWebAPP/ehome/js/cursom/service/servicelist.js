var vm = new Vue({
	el:"#app",
	data:{
		urlList:{
			list:"servicelist.html?id=",
			detail:"servicedetail.html?id=",
			index:"serviceindex.html?id=",
			bg:"../../images/top_bg3.jpg"
			},
		business:{},
		serviceLists:[],
		collect:1,
		item:{}
			
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		});
	},
	methods:{
		// 渲染页面
		cartView:function(){
			var _this = this;
			this.$http.get(interfaceUrl+"/live/getServiceCategory",{
				userID:userInfo.userID,	
				buildingID:userInfo.buildingID,
				businessID:getQueryString("id")
			}).then(function(res){
				if(res.data.code == 1000){
					_this.business = res.data.data;
					_this.collection(_this.business.isCollection,_this.business.businessID);
					if(_this.business.serviceCategory&&_this.business.serviceCategory.length>0){
						this.$http.get(interfaceUrl+"/live/getServiceItems",{
							userID:userInfo.userID,						
							buildingID:userInfo.buildingID,
							businessID:getQueryString("id"),
							categoryID:_this.business.serviceCategory[0].cagegoryID
						}).then(function(res) {
							if(res.data.code == 1000){
								_this.serviceLists = res.data.data;
								 
							}
						});
					}else{
						this.$http.get(interfaceUrl+"/live/getServiceItems",{
							userID:userInfo.userID,
							buildingID:userInfo.buildingID,
							businessID:getQueryString("id"),
						}).then(function(res) {
							if(res.data.code == 1000){
								_this.serviceLists = res.data.data;
								
							}
						});
						
					}
					
					
				}
			});
				
			
			

		},
		
		//改变分类
		changeCategory:function(category){
			var _this = this;
   	        $("#categoryName").html(category.categoryName);   
   	        $("#menu_category > li.selected").removeClass("selected");
            $(event.target).closest("li").addClass("selected");
            
            var path=interfaceUrl+"/live/getServiceItems";
            
            this.$http.get(path,{
            	userID:userInfo.userID,
            	buildingID:userInfo.buildingID,
				businessID:getQueryString("id"),
				categoryID:category.cagegoryID
            }).then(function(res){
				_this.serviceLists = res.data.data;
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
                        console.log(res.status);},function(res){
			alert(res.status)})
		},
		cancel_collections:function(path,id){
			var _this = this;
			this.$http.post(path,
				{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:id},
				{emulateJSON: true}).then(function(res){
                  _this.item = res.data.data;
                        console.log(res.status);},function(res){
			alert(res.status)})  
		}	
	
	}
});