var vm = new Vue({
	el:"#app",
	data:{
		commentmes:[]
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
			this.$http.get("../../data/commentme.json").then(function(res){
				_this.commentmes = res.data.data;
				
			});
		}
	}
});