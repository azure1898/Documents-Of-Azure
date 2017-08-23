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
//			this.$http.get("../../data/personalpage.json").then(function(res){
//				_this.personalpage = res.data;
//              _this.releaseDetails=_this.personalpage.data
//			});	
			this.$http.post(interfaceUrl+"/person/personalInfo",{
				subUserId:getQueryString("id"),  //用户id   从上个页面来
				userId:userInfo.userID,    //当前用户id  
				pageIndex:0
			},{emulateJSON: true}).then(function(res){
				_this.personalpage = res.data;
                _this.releaseDetails=_this.personalpage.data
//				console.log(_this.releasePictures);
			});
		},
		//赞一个
		praiseOne:function(obj,type){
				var _this = this;
				if(obj.isPraise==1){//取消点赞
					
					 this.$http.post(interfaceUrl+"/praise/savePraise",{
					 	pid:obj.id,//发言id  obj.id
					 	type:type,   
					 	userId:userInfo.userID,    
					 	toUserId:personalpage.id,   //被赞人 id  _this.personalpage.id
					 	state:0
					 },{emulateJSON: true}).then(function(res){
					 	if(res.data.code==1000){
						 		obj.isPraise=0;
								obj.countPraise-=1;
						 	}
						});
				}else{//点赞
					
					 this.$http.post( interfaceUrl + "/praise/savePraise",{
					 	pid:obj.speakId,//发言id  obj.id
					 	type:type,   
					 	userId:userInfo.userID,    
					 	toUserId:_this.personalpage.id,   //被赞人 id  _this.personalpage.id
					 	state:1
					 },{emulateJSON: true}).then(function(res){
							if(res.data.code==1000){
								obj.isPraise=1;
								obj.countPraise+=1;
							}
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
					 	userId:userInfo.userID,   //当前用户id
					 	subUserId:_this.personalpage.id,  //被关注人的id    _this.personalpage.id
					 	type:0
					 },{emulateJSON: true}).then(function(res){
							if(res.data.code==1000){
								personalpage.isFocus=0;
				    	  		layer.close(index);
							}
						});
			    },
			    no: function(index){
					layer.close(index);
					}    
				  });

			
		}else{
				_this.$http.post( interfaceUrl + "/focus/saveFocus",{
				 	userId:userInfo.userID,   //当前用户id
					 subUserId:_this.personalpage.id,  //被关注人的id    _this.personalpage.id
					 type:0
				},{emulateJSON: true}).then(function(res){
					if(res.data.code==1000){
						personalpage.isFocus=1;
				   		layer.close(index);
						}
			});
		}
	}
		
	}
});