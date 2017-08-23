var sid=1;
var userid=1;

var vm=new Vue({
	el:"#app",
	data:{
		commentDetail:{},
		commentList:[],
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
			this.$http.get("../../data/commentDetail.json").then(function(res){
				_this.commentDetail = res.data;
				_this.commentList = res.data.data;
			});	
//			 this.$http.post(interfaceUrl+"/praise/savePraise",{
//				 	commentId:"",   //来自上一个页面的评论id   getQueryString("id")
//				 	userId:userInfo.userID
//				 },{emulateJSON: true}).then(function(res){
//						_this.praiseInformation = res.data.message;		
//			});
		},
		praiseOne:function(obj,type){
			var _this = this;
			if(obj.isPraise==1){//取消点赞
				obj.isPraise=0;
				obj.countPraise-=1;
//				 this.$http.post(interfaceUrl+"/praise/savePraise",{
//				 	pid:obj.id,//发言id  obj.id
//				 	type:type,   
//				 	userId:"",    
//				 	toUserId:_this.personalpage.id,   //被赞人 id  _this.personalpage.id
//				 	state:0
//				 },{emulateJSON: true}).then(function(res){
//						_this.praiseInformation = res.data.message;		
//					});
			}else{//点赞
				obj.isPraise=1;
				obj.countPraise+=1;
//				 this.$http.post( interfaceUrl + "/praise/savePraise",{
//				 	pid:obj.id,
//				 	type:type,
//				 	userId:"",
//				 	toUserId:"",
//				 	state:1
//				 },{emulateJSON: true}).then(function(res){
//						_this.praiseInformation = res.data.message;		
//					});
			}
	},
		//回复评论
	reply:function(obj){//点击回复吧回复人的id和名字带过去
			var _this=this;
			window.location.href=_this.urlList.comments+obj.id+"&name="+escape( obj.userName);
		}
    }
})
$(document).ready(function(){
	
})

