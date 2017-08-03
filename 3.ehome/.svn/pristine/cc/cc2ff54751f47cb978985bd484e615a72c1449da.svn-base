var vm = new Vue({
	el: "#app",
	data: {
         n:0,
		items:{},
		urlList: {
			list: "courselist.html?id=",
			detail: "coursedetails.html?id=",
			index: "courseindex.html?id=",
			order: "courseorder.html?id=",
			bg: "../../images/top_bg5.jpg"
		},
		item:{},
		x:1

	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){ //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			var _this = this;
			this.$http.get(interfaceUrl+'/live/getCourseItems',
			{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:getQueryString("id")}).then(function(res){
                  _this.items = res.data.data;
                  _this.collection(_this.items.isCollection,_this.items.businessID);
                    console.log(res.status);},
                    function(res){alert(res.status)})
		})
			},
	methods:{
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