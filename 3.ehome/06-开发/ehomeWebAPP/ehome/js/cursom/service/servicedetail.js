var vm = new Vue({
	el: "#app",
	data: {
		urlList:{
				list:"servicelist.html?id=",
				detail:"servicedetail.html?id=",
				index:"serviceindex.html?id=",
				order:"serviceorder.html?id=",
			},
		detail: {},
		collect:1,
		item:{}
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
			$('#myCarousel').carousel({
				//自动4秒播放
				interval: 3000,
			});
            var myElement= document.getElementById('myCarousel')
            var hm=new Hammer(myElement);
            hm.on("swipeleft",function(){
                $('#myCarousel').carousel('next')
            })
            hm.on("swiperight",function(){
                $('#myCarousel').carousel('prev')
            })

		})
	},
	methods: {
		cartView: function() {
			var _this = this;
			this.$http.get(interfaceUrl+"/live/getServiceItemDetail",{
					userID:userInfo.userID,
				serviceID:getQueryString("id"),
				buildingID:userInfo.buildingID
			}).then(function(res) {
				if(res.data.code == 1000){
					_this.detail = res.data.data;
					_this.collection(_this.detail.isCollection,_this.detail.businessID);
					
				}
			})
		},
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
});