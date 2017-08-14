var vm = new Vue({
	el:"#app",
	data:{
		myfollowData:[]
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
			this.$http.get("../../data/myfollow.json").then(function(res){
				_this.myfollowData = res.data.data;
			});
		},
	follow:function(myfollow,index){
			var _this = this;
			layer.open({
				 title: [
				    '提示',
				  ],
			    content: '确定取消关注？'
			    ,btn: ['确定', '取消']
			    ,yes: function(indextan){
			   	  _this.myfollowData.splice(index, 1);
			      layer.close(indextan);
			    }
			});
		}
	
		
	}
});