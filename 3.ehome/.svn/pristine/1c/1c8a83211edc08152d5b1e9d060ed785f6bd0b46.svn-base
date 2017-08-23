var vm=new Vue({
	el:"#app",
	data:{
		praiseDetail:{},
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
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
//			this.$http.get("../../data/mypraisedetails.json").then(function(res){
//				_this.praiseDetail = res.data;
//			});	
            this.$http.post(interfaceUrl+"/myHome/myPraiseList",{
            	userId:userInfo.userID,
            	pageIndex:0
            },{emulateJSON:true}).then(function(res){
            	_this.praiseDetail = res.data;
            })
		},
	}
})
