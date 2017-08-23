var vm = new Vue({
	el: "#app",
	data: {
		speechLists: [],
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id="
		},
		speechIndex:0,   //用于删除判断点击的是哪一个
		screens:"", //屏蔽的信息
		obj:{},//承接单个发言对象
	},
	mounted: function() {
		this.$nextTick(function() {
			this.cartView();
		})
	},
	methods: {
		cartView: function() {
			var _this = this;
//			this.$http.get("../../data/mySpeech.json").then(function(res) {
//				_this.speechLists = res.data.data;
//			});
			
			this.$http.post(interfaceUrl + "/message/myMsg",{
				userId:userInfo.userID
			},{emulateJSON: true}).then(function(res){
				_this.speechLists = res.data.data;
			});
		},
		//赞一个
		praiseOne:function(obj,type){
			var _this = this;
			if(obj.isPraise==1){//取消点赞
				obj.isPraise=0;
				obj.countPraise-=1;
				 this.$http.post(interfaceUrl+"/praise/savePraise",{
				 	pid:obj.speakId,//发言id  obj.id
				 	type:type,   
				 	userId:userInfo.userId,    
				 	toUserId:obj.userId,   //被赞人 id  _this.personalpage.id
				 	state:0
				 },{emulateJSON: true}).then(function(res){
						_this.praiseMessage = res.data.message;		
					});
			}else{//点赞
				obj.isPraise=1;
				obj.countPraise+=1;
				 this.$http.post( interfaceUrl + "/praise/savePraise",{
					pid:obj.speakId,//发言id  obj.id
				 	type:type,   
				 	userId:userInfo.userId,    
				 	toUserId:obj.userId, 
				 	state:1
				 },{emulateJSON: true}).then(function(res){
						_this.praiseMessage = res.data.message;		
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
//	  cancelAttention:function(){
//		var _this = this;
//	  	
//	  	layer.open({
//		    content: '您确定要取消关注吗？'
//		    ,btn: ['确定', '取消']
//		    ,yes: function(index){
//		    	_this.speechLists[_this.speechIndex].isFocus=0
//		      layer.close(index);
//		      vm.closePopups();
//		    },
//		    no: function(index){
//				layer.close(index);
//				vm.closePopups();
//				}    
//		  });
//	  	
//	  },
	  cancelAttention:function(obj){
		var _this = this;
	  	
	  	layer.open({
		    content: '您确定要取消关注吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	_this.$http.post(interfaceUrl + "/focus/saveFocus",{
				 userId:userInfo.userID,
		      	subUserId:obj.userId,     //_this.indexdata[_this.speechIndex].speakUserId
				 type:0
			},{emulateJSON: true}).then(function(res){
//				_this.commentmes = res.data.data;
				obj.isFocus=0
		      	layer.close(index);
		      	vm.closePopups();
			});
//		    	_this.indexdata[_this.speechIndex].isFocus=0
//		      	layer.close(index);
//		      	vm.closePopups();
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
		      	subUserId:item.speakId,   //fans.subUserId
				type:1,
			},{emulateJSON:true}).then(function(res){
				if(res.data.code==1000){
					item.isFocus=1;
				}
			})
       		item.isFocus=1;
       },
	  //屏蔽他的发言
	  shield:function(obj){
		var _this = this;
	  	layer.open({
		    content: '您确定要屏蔽TA发言吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
			_this.$http.post(interfaceUrl + "/message/black",{
				userId:userInfo.userID,
				subUserId:1                 //_this.speechLists[_this.speechIndex].userId
			},{emulateJSON: true}).then(function(res){
//				_this.screens = res.data.data;
			});
		    	
		      layer.close(index);
		      vm.closePopups();
		    },
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  },
	  
	  deleteStatement:function(obj){
		var _this = this;
	  	layer.open({
		    content: '你确定要删除此发言吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		      _this.speechLists.splice(_this.speechIndex, 1);
		      layer.close(index);
		      vm.closePopups();
		    },
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  }
	}
})