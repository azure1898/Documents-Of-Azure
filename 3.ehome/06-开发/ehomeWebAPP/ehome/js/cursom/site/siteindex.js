var vm = new Vue({
	el:"#app",
	data:{
		urlList:{
			list:"modulelist.html?id=",
			order:"siteorder.html?id=",
			index:"siteindex.html?id=",
			groupbuy:"../groupbuy/groupbuydetail.html?id=",
			phoneicon:"../../images/telphone.png",
			goicon:"../../images/grey_go.png"
		},
		site:{},
		n:0
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){
		var _this = this;
			this.$http.get(interfaceUrl+"/live/getSiteIndex",{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:getQueryString("id")}).then(function(res){
				_this.site = res.data.data;	
				_this.collection(_this.site.isCollection,_this.site.businessID);
//	alert(res.status)
			},function(res){
				alert(res.status)
			});
		})					
	},
	methods:{
    openMore:function(){
				 var _this = this;
			_this.n++;
			if (_this.n%2==1) {
					this.$http.get(interfaceUrl+"/live/getMoreGroupBuy",{
						userID:userInfo.userID,
						buildingID: userInfo.buildingID,
						businessID:getQueryString("id")
					}).then(function(res) {
						if(res.data.code == 1000){
							_this.site.groupBuy=res.data.data
						}
					});
					$(event.target).html("收起");
			}
			else if(_this.n%2==0){
				this.$http.get(interfaceUrl+"/live/getSiteIndex",{
					userID:userInfo.userID,businessID:getQueryString("id"),buildingID:userInfo.buildingID
					}).then(function(res) {
						if(res.data.code == 1000){
							_this.site = res.data.data;
							
						}
					});
					$(event.target).html("查看更多团购");
			}
				
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
