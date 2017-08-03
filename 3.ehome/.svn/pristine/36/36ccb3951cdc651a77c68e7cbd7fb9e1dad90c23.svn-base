var vm = new Vue({
	el: "#app",
	data: {
		urlList:{
			list:"servicelist.html?id=",
			detail:"servicedetail.html?id=",
			index:"serviceindex.html?id=",
			groupbuy:"../groupbuy/groupbuydetail.html?id="
		},
		item1: {},
		groupBuy:[],
		limitNum: 2,
		times: 1,
		collect:1,
		groupbuytype:1
		
	},

	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		})
	},
	methods: {
		cartView: function() {
			var _this = this;
			this.$http.get(interfaceUrl+"/live/getServiceIndex",{
					userID:userInfo.userID,
				businessID:getQueryString("id"),
				buildingID:userInfo.buildingID
			}).then(function(res) {
				if(res.data.code == 1000){
					_this.item1 = res.data.data;
					_this.collection(_this.item1.isCollection,_this.item1.businessID);
					_this.groupBuy=_this.item1.groupBuy;
				}
			})
		},
		//更多团购
		getMore:function(business){
			var _this = this;
			_this.times++
			if (_this.times%2==0) {
					this.$http.get(interfaceUrl+"/live/getMoreGroupBuy",{
						userID:userInfo.userID,
						buildingID: userInfo.buildingID,
						businessID:getQueryString("id")
					}).then(function(res) {
						if(res.data.code == 1000){
							_this.groupBuy=res.data.data
						}
					});
					$(event.target).html("收起");
			} else{
				this.$http.get(interfaceUrl+"/live/getServiceIndex",{
					userID:userInfo.userID,
					businessID:getQueryString("id")
					}).then(function(res) {
						if(res.data.code == 1000){
							_this.item1 = res.data.data;
							_this.groupBuy=_this.item1.groupBuy
						}
					});
					$(event.target).html("查看更多团购");
			}
		},
		//收藏商家
		 collection:function(status,id){
					var _this = this;
					var businessid=id;
					$("#collection").bind("click",function(){
					   if(status==0){
							layer.open({
					    content: '收藏成功'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
					    $(this).removeClass().addClass("collection_btn2");
					   _this.add_collections(path_add,businessid);							
						}
					  else if(status==1){							
					    layer.open({
					    content: '取消收藏'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
						$(this).removeClass().addClass("collection_btn");
					     _this.cancel_collections(path_cancle,businessid);
							
						}
					
                 })			
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
})