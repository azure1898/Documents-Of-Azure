var vm = new Vue({
	el:"#app",
	data:{
		indexdata:[],
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"../myspeech.html?id=",
			mypraise:"../mypraise.html?id=",
			personalpage:"../main/personalpage.html?id="
		},
		userid:"",
		speechIndex:0,   //用于判断点击的是哪一个
		screens:"" ,//屏蔽的信息
		obj:{},//承接单个发言对象
		praiseMessage:"",//点赞的信息
		Range:1
		
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
			_this.userid=userInfo.userID
//			this.$http.get("../../data/index.json").then(function(res){
//				_this.userid = res.data.userId;
//				_this.indexdata = res.data.data;
//			});
			this.$http.post(interfaceUrl + "/speak/speakList",{
				userId: userInfo.userID,
				isAll:_this.Range,
				pageIndex:0,
				villageInfoId:userInfo.addressId
			},{emulateJSON: true}).then(function(res){
				_this.indexdata = res.data.data;
				
			});
		},
		//改变范围
		changeRange:function(type){
			var _this = this;
			_this.Range=type;
			this.$http.post(interfaceUrl + "/speak/speakList",{
				userID : userInfo.userID,
				isAll:_this.Range,
				pageIndex:0,
				villageInfoId:userInfo.addressId
			},{emulateJSON: true}).then(function(res){
				_this.userid = res.data.userId;
				_this.indexdata = res.data.data;
			});
		},
		//赞一个
		praiseOne:function(obj,type){
			var _this = this;
			if(obj.isPraise==1){//取消点赞
				
				 this.$http.post(interfaceUrl+"/praise/savePraise",{
				 	pid:obj.speakId,//发言id  obj.id
				 	type:type,   
				 	userId:userInfo.userID,    
				 	toUserId:obj.speakUserId,   //被赞人 id  _this.personalpage.id
				 	state:0
				 },{emulateJSON: true}).then(function(res){
					 	if(res.data.code==1000){
					 		_this.praiseMessage = res.data.message;	
							obj.isPraise=0;
							obj.countPraise-=1;
					 	}
					});
			}else{//点赞
				
				 this.$http.post( interfaceUrl + "/praise/savePraise",{
				 	pid:obj.speakId,//发言id  obj.id
				 	type:type,   
				 	userId:userInfo.userID,    
				 	toUserId:obj.speakUserId,   //被赞人 id  _this.personalpage.id
				 	state:1
				 },{emulateJSON: true}).then(function(res){
						if(res.data.code==1000){
					 		_this.praiseMessage = res.data.message;	
							obj.isPraise=1;
							obj.countPraise+=1;
					 	}	
					});
			}
	},
			//从底部弹出选择框
	  selective:function(obj,index){
		var _this = this;
		_this.obj=obj
	  	_this.speechIndex=index;
	  	$("#bg").fadeIn(0.1);
	  	$("#type").slideDown()

	  },
       //关闭弹窗
       closePopups:function(){
	       	$("#bg").fadeOut(0.1);
		  	$("#type").slideUp(0.1)
       },
       //取消关注
	  cancelAttention:function(obj){
		var _this = this;
	  	
	  	layer.open({
		    content: '您确定要取消关注吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	_this.$http.post(interfaceUrl + "/focus/saveFocus",{
				 userId:userInfo.userID,
		      	subUserId:obj.speakUserId,     //_this.indexdata[_this.speechIndex].speakUserId
				 type:0
			},{emulateJSON: true}).then(function(res){
//				_this.commentmes = res.data.data;
				obj.isFocus=0
		      	layer.close(index);
		      	vm.closePopups();
			});
			if(_this,Range==0){ //当取消关注切用户选择只看我关注的页面时 从新请求数据
						this.$http.post(interfaceUrl + "/speak/speakList",{
						userID : userInfo.userID,
						isAll:_this.Range,
						pageIndex:0,
						villageInfoId:userInfo.addressId
					},{emulateJSON: true}).then(function(res){
						_this.userid = res.data.userId;
						_this.indexdata = res.data.data;
						
					});
				}
			},
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  	
	  },
	 
	  
	  
	  attention:function(item){
		var _this = this;
	  	_this.$http.post(interfaceUrl + "/focus/saveFocus",{
				userId:userInfo.userID,
				type:1,
		      	subUserId:item.speakId     //fans.subUserId
			},{emulateJSON:true}).then(function(res){
				if(res.data.code==1000){
					item.isFocus=1;
				}
			})
       },
       //删除自己的发言
       deleteComments:function(index,ietm){
			var _this = this;
			layer.open({
		    content: '确定删除评论吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(indext){
		    	
		    _this.$http.post(interfaceUrl + "/myHome/speakDel",{
				speakId: ietm.speakId  //ietm.speakId
			},{emulateJSON: true}).then(function(res){
				_this.commentmes = res.data.data;
			});
		    	_this.indexdata.splice(index, 1)
		     	 layer.close(indext);
		    },
		    no: function(indext){
				layer.close(indext);
				}    
		  });
		},
	  //没关注屏蔽
	  shield:function(obj){
		var _this = this;
	  	layer.open({
		    content: '您确定要屏蔽TA发言吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
			_this.$http.post(interfaceUrl + "/message/black",{
				userId:userInfo.userID,
				subUserId:obj.speakUserId      //_this.indexdata[_this.speechIndex].speakUserId
			},{emulateJSON: true}).then(function(res){
				if(res.data.code==1000){
					layer.close(index);
		     		 vm.closePopups();
				}
					
			});
		    	
//		      layer.close(index);
//		      vm.closePopups();
		    },
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  }
	}
});
