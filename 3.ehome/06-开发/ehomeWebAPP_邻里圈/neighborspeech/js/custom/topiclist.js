var vm = new Vue({
	el:"#app",
	data:{
		releaseDetails:[]
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
			this.$http.get("../../data/topiclist.json").then(function(res){
				_this.releaseDetails = res.data.data;
//				console.log(_this.releaseDetails)
			});
		},
		//赞一个
		praiseOne:function(releaseDetail,type){
			var _this = this;
			if(releaseDetail.isPraise==1){//取消点赞
				releaseDetail.isPraise=2;
				releaseDetail.countPraise-=1;
//				 this.$http.post("/speak/saveComment"{
//				 	id:releaseDetail.id,
//				 	type:type
//				 }).then(function(res){
//						_this.praiseInformation = res.data.message;		
//					});
			}else{//点赞
				releaseDetail.isPraise=1;
				releaseDetail.countPraise+=1;
//				this.$http.post("/speak/saveComment"{
//				 	id:releaseDetail.id,
//				 	type:type
//				 }).then(function(res){
//						_this.praiseInformation = res.data.message;		
//					});
			}
		}
		
	}
});