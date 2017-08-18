var vm = new Vue({
	el:"#app",
	data:{
		personalpage:{},
		releaseDetails:[],
		praiseInformation:"",
		followInformation:"",
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id="
		}
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
			this.$http.post(interfaceUrl+"/person/personalInfo",{
				subUserId:1,  //用户id   _this.personalpage.id
				userId:1,    //当前用户id  
				pageIndex:0
			},{emulateJSON: true}).then(function(res){
				_this.personalpage = res.data;
                _this.releaseDetails=_this.personalpage.data
//				console.log(_this.releasePictures);
			});
		},
		//赞一个
		praiseOne:function(releaseDetail,type){
			var _this = this;
			if(releaseDetail.isPraise==1){//取消点赞
				releaseDetail.isPraise=0;
				releaseDetail.countPraise-=1;
				 this.$http.post(interfaceUrl+"/praise/savePraise",{
				 	pid:releaseDetail.id,//发言id  releaseDetail.id
				 	type:type,   
				 	userId:"",    
				 	toUserId:_this.personalpage.id,   //被赞人 id  _this.personalpage.id
				 	state:0
				 },{emulateJSON: true}).then(function(res){
						_this.praiseInformation = res.data.message;		
					});
			}else{//点赞
				releaseDetail.isPraise=1;
				releaseDetail.countPraise+=1;
				 this.$http.post( interfaceUrl + "/praise/savePraise",{
				 	pid:releaseDetail.id,
				 	type:type,
				 	userId:"",
				 	toUserId:"",
				 	state:1
				 },{emulateJSON: true}).then(function(res){
						_this.praiseInformation = res.data.message;		
					});
			}
	},
	follow:function(personalpage){
			var _this = this;
		if(personalpage.isFocus==1){//取消关注
			layer.open({
			    content: '您确定要取消关注吗？'
			    ,btn: ['确定', '取消']
			    ,yes: function(index){
			    	_this.$http.post( interfaceUrl + "/focus/saveFocus",{
					 	userId:1,   //当前用户id
					 	subUserId:1,  //被关注人的id    _this.personalpage.id
					 	type:0
					 },{emulateJSON: true}).then(function(res){
							_this.followInformation = res.data.message;		
						});
					personalpage.isFocus=0;
			    	  layer.close(index);
			    },
			    no: function(index){
					layer.close(index);
					}    
				  });

			
		}else{
			personalpage.isFocus=1;
				this.$http.post( interfaceUrl + "/focus/saveFocus",{
				 	userId:1,   //当前用户id
				 	subUserId:1,  //被关注人的id    _this.personalpage.id
				 	type:1
				 },{emulateJSON: true}).then(function(res){
						_this.followInformation = res.data.message;		
					});
		}
	}
		
	}
});