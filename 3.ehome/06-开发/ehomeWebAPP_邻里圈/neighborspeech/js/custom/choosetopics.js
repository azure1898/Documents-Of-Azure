var vm = new Vue({
	el:"#app",
	data:{
		Htopics:[],
		topics:[],
		theme:""
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
			this.$http.get("../../data/choosetopics.json").then(function(res){
				_this.Htopics = res.data.data;
			});
		},
		themechange:function(theme){
			var _this = this;
			if(_this.theme==""){
				this.$http.get("../../data/choosetopics.json").then(function(res){
				_this.Htopics = res.data.data;
			});
			}else{
				this.$http.get("../../data/choosetopics2.json",{
				vaule:_this.theme
				}).then(function(res){
					_this.topics = res.data.data;
				});
			}
		}
	}
});
