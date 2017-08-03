	var vm = new Vue({
		el: "#app",
		data: {

			items: {},
			urlList: {
				list: "courselist.html?id=",
				detail: "coursedetails.html?id=",
				index: "courseindex.html?id=",
				order: "courseorder.html?id="
			},
			Change: true
		},
		mounted: function() { //页面加载之后自动调用，常用于页面渲染
				var _this = this;
			this.$http.get(interfaceUrl+'/live/getCourseItemDetail',{userID:userInfo.userID,buildingID:userInfo.buildingID,courseID:getQueryString("id")}).then(function(res){
                  _this.items = res.data.data; 
                    console.log( _this.items.stockNumber);
                  _this.init(_this.items.stockNumber,_this.items.isNormal);
                  _this.collection(_this.items.isCollection,_this.items.businessID);
			},function(res){
			alert(res.status)});
			
		},
		methods: {
			init:function(number,status){
				if(status==1){

				if(number>0){
					$("#change_coursedetail > a").text("立即预约");
				this.Change=true;
				}
				else{
					this.Change=false;
					$(".business_bottom>div").removeClass().addClass("ser_zbtn2");
					$("#change_coursedetail > a").text("报名已满");
				}
				
				}
				else if(status==0){
					
					if(number>0){
					$(".business_bottom>div").removeClass().addClass("ser_zbtn2");
					$("#change_coursedetail > a").text("立即预约");
				this.Change=false;
				}
				else{
					this.Change=false;
					$(".business_bottom>div").removeClass().addClass("ser_zbtn2");
					$("#change_coursedetail > a").text("报名已满");
				}
					
					
					
				}
				
			}
		//收藏
		,collection:function(status,id){
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

