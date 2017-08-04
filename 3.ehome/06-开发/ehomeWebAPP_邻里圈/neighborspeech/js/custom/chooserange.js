var vm = new Vue({
	el:"#app",
	data:{
		rangeInfo:{},
		choose:1,
		chooseUrl:"../../images/gx.png",
		chooseUrl2:"../../images/gx02.png"
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
//			this.$http.get("/speak/toComment"{
//				speakId:123,
//			}).then(function(res){
//				_this.rangeInfo = res.data;
//			});
		},
		choice:function(type){//1公开、2、粉丝可见 3、好友可见
			var _this = this;
			_this.choose=type;
		}
	
	}
});
