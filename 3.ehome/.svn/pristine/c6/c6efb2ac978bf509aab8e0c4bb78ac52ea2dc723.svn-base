var vm=new Vue({
	el:"#app",
	data:{
		praiseDetail:[],
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id=",
			personalpage:"../main/personalpage.html?id="
		}
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
//				_this.praiseDetail = res.data.data;
//			});	
			this.$http.post(interfaceUrl + "/message/praiseMeList",{
				userId:userInfo.userID, //用户id
				pageIndex:0
			},{emulateJSON: true}).then(function(res){
				_this.praiseDetail = res.data;
			});
		},
	}
})
