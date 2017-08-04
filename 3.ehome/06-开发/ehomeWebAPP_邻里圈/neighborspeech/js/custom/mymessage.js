var vm = new Vue({
	el:"#app",
	data:{
		mymessages:{},
		unread:0
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
			this.$http.get("../../data/mymessages.json").then(function(res){
				_this.mymessages = res.data.data;
				for(obj in _this.mymessages){
					if(_this.mymessages[obj]==1){
						_this.unread=1
					}else{
						_this.unread=0
					}
					
				}
			});
		}
	}
});