var vm = new Vue({
	el:"#app",
	data:{
		customer:{},
		comments:[],
		praises:[],
		forwardings:[],
		praiseIcon:"../../images/zan_01.png",
		praiseIcon2:"../../images/zan_02.png",
		praiseInformation:""
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
			this.$http.get("../../data/customer.json").then(function(res){
				_this.customer = res.data;
			});
			
			this.$http.get("../../data/comment.json").then(function(res){
				_this.comments = res.data.data;		
			});
			 $("#discuss").show();
			 $("#praise").hide();
			 $("#forwarding").hide();
		},
		
		//转发列表
		forwardingList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").hide();
			 $("#praise").hide();
			 $("#forwarding").show();
			 
   	         this.$http.get("../../data/forwardings.json").then(function(res){
				_this.forwardings = res.data.data;		
			});
		},
		//评论列表
		commentList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").show();
			 $("#praise").hide();
			 $("#forwarding").hide();
			 
			this.$http.get("../../data/comment.json").then(function(res){
				_this.comments = res.data.data;		
			});
		},
		//赞列表
		praiseList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").hide();
			 $("#praise").show();
			 $("#forwarding").hide();
			 
			 this.$http.get("../../data/praises.json").then(function(res){
				_this.praises = res.data.data;		
			});
			 
		},
		//赞一个
		praiseOne:function(comment,type){
			var _this = this;
			if(comment.isPraise==1){//取消点赞
				comment.isPraise=0;
				comment.countPraise-=1;
//				 this.$http.post("/speak/praise"{
//				 	id:comment.id,
//				 	type:type
//				 }).then(function(res){
//						_this.praiseInformation = res.data.data;		
//					});
			}else{//点赞
				comment.isPraise=1;
				comment.countPraise+=1;
//				this.$http.post("/speak/praise"{
//				 	id:comment.id,
//				 	type:type
//				 }).then(function(res){
//						_this.praiseInformation = res.data.data;		
//					});
			}
			
			
		}
		
	}
});