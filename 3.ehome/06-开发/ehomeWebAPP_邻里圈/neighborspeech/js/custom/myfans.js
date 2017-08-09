var vm = new Vue({
	el:"#app",
	data:{
		myfansData:[]
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
			this.$http.get("../../data/myfans.json").then(function(res){
				_this.myfansData = res.data.data;
			});
		},
	follow:function(fans){
			var _this = this;
		if(fans.relation==2){//取消关注
			layer.open({
			 title: [
			    '提示',
			  ],
		    content: '确定取消关注？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		   	  fans.relation=1;
		      layer.close(index);
		    }
		  });
			
//			this.$http.post("/speak/saveComment"{
//				 	userId:,
//					subUserId:personalpage.id,
//				 	type:0
//				 }).then(function(res){
//						_this.followInformation = res.data.message;		
//					});
			
		}else{
			fans.relation=2;
		//			this.$http.post("/speak/saveComment"{
//				 	userId:,
//					subUserId:personalpage.id,
//				 	type:1
//				 }).then(function(res){
//						_this.followInformation = res.data.message;		
//					});
		}
	}
		
	}
});