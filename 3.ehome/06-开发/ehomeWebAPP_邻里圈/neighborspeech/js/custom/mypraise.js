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
//			this.$http.get("../../data/praiseDetail.json").then(function(res){
//				_this.praiseDetail = res.data;
//			});	
			this.$http.post(interfaceUrl + "/message/praiseMeList",{
				userId:userInfo.userID, //用户id
//				pageIndex:0
			},{emulateJSON: true}).then(function(res){
				_this.praiseDetail = res.data;
			});
		},
	}
})
