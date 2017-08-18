var vm = new Vue({
	el:"#app",
	data:{
		indexdata:[],
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id="
		},
		userid:"",
		speechIndex:0,   //用于判断点击的是哪一个
		screens:"" ,//屏蔽的信息
		praiseMessage:""
		
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
			this.$http.get("../../data/index.json").then(function(res){
				_this.userid = res.data.userId;
				_this.indexdata = res.data.data;
			});
//			this.$http.post(interfaceUrl + "/message/countMsg",{
//				userID:1
//			},{emulateJSON: true}).then(function(res){
//				_this.mymessages = res.data.data[0];
//				}
//			});
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
				 	userId:_this.userid,    
				 	toUserId:obj.speakUserId,   //被赞人 id  _this.personalpage.id
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
				 	userId:_this.userid,    
				 	toUserId:obj.speakUserId,   //被赞人 id  _this.personalpage.id
				 	state:1
				 },{emulateJSON: true}).then(function(res){
						_this.praiseMessage = res.data.message;		
					});
			}
	},
			//从底部弹出选择框
	  selective:function(index){
		var _this = this;
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
	  cancelAttention:function(){
		var _this = this;
	  	
	  	layer.open({
		    content: '您确定要取消关注吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	console.log(_this.speechIndex)
		    	_this.indexdata[_this.speechIndex].isFocus=0
		      layer.close(index);
		      vm.closePopups();
		    },
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  	
	  },
	  attention:function(item){
       		item.isFocus=1;
       },
       //删除自己的发言
       deleteComments:function(index,ietm){
			var _this = this;
			layer.open({
		    content: '确定删除评论吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(indext){
		    	
//		    _this.$http.post(interfaceUrl + "/myHome/speakDel",{
//				speakId: "plid0001"  //ietm.speakId
//			},{emulateJSON: true}).then(function(res){
//				_this.commentmes = res.data.data;
//			});
		    	_this.indexdata.splice(index, 1)
		     	 layer.close(indext);
		    },
		    no: function(indext){
				layer.close(indext);
				}    
		  });
		},
	  //屏蔽他的发言
	  shield:function(){
		var _this = this;
	  	layer.open({
		    content: '您确定要屏蔽TA发言吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
//			_this.$http.post(interfaceUrl + "/message/black",{
//				userID:1,
//				subUserId:1                 //_this.speechLists[_this.speechIndex].userId
//			},{emulateJSON: true}).then(function(res){
////				_this.screens = res.data.data;
//			});
		    	
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
});