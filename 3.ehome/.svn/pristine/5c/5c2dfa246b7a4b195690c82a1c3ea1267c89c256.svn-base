var vm=new Vue({
	el:"#app",
	data:{
		ordermessages:[]
	},
	mounted:function(){
		this.$nextTick(function(){
			this.cartView();
		})				
	},
	methods:{
		cartView:function(){
			var _this = this;
//			this.$http.get("../../data/ordermessage.json").then(function(res){
//				_this.ordermessages = res.data.data;
//			});	
			this.$http.post(interfaceUrl + "/message/orderMsg",{
				userID:1,  //用户id
				pageIndex:0
			},{emulateJSON: true}).then(function(res){
				_this.praiseDetail = res.data;
			});
		},
	}
})
