var vm=new Vue({
	el:"#app",
	data:{
		praiseDetail:{}
	},
	mounted:function(){
		this.$nextTick(function(){
			this.cartView();
		})				
	},
	methods:{
		cartView:function(){
			var _this = this;
			this.$http.get("../../data/mypraisedetails.json").then(function(res){
				_this.praiseDetail = res.data;
			});	
		},
	}
})
