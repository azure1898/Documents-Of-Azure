var vm = new Vue({
	el:"#app",
	data:{
		forwardData:{},
		forwardingReason:"",
		commentIcon:"../../images/gx_01.png",
		commentIcon2:"../../images/gx_02.png",
		isComment:0,
		choose:1,
		chooseUrl:"../../images/gx.png",
		chooseUrl2:"../../images/gx02.png"

	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		});
	},
	computed:{
		textnum:function(){
			var _this = this;
			return 200-_this.forwardingReason.length
		}
	},
	methods:{
		// 渲染页面
		cartView:function(){
			var _this = this;
			this.$http.get("../../data/forward.json").then(function(res){
				_this.forwardData = res.data;
			});
		},
		meanwhile:function(){
			var _this = this;
			if(_this.isComment==0){
				_this.isComment=1;
			}else{
				_this.isComment=0;
			}
			
		},
		choice:function(type){//1公开、2、粉丝可见 3、好友可见
			var _this = this;
			_this.choose=type;
		},
		yema:function(ye){
			if(ye==1){
				$("#ye1").hide()
				$("#ye2").show()
			}else{
				$("#ye2").hide()
				$("#ye1").show()
			}
		}
	}
});