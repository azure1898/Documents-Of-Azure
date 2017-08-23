var vm = new Vue({
	el:"#app",
	data:{
		customer:{},
		comments:[],
		praises:[],
		forwardings:[],
		praiseIcon:"../../images/zan_01.png",
		praiseIcon2:"../../images/zan_02.png",
		praiseInformation:"",
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
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		});
	},
	methods:{
		// 渲染页面
		cartView:function(){
			var _this = this;
			//interfaceUrl + "/speak/speakDetail" 调话题详情
			this.$http.post(interfaceUrl + "/speak/speakDetail",{
				userID:userInfo.userID,
				speakId:getQueryString("id"),   // getQueryString("id") 来自上一个页面的话题id
			},{emulateJSON: true}).then(function(res){
				_this.customer = res.data;
			});
			//interfaceUrl + "/comment/commentList" 调评论
			this.$http.post(interfaceUrl + "/comment/commentList",{
				userID:userInfo.userID,
				speakId:getQueryString("id"),     // getQueryString("id") 来自上一个页面的话题id
				pageIndex:0
				
			},{emulateJSON: true}).then(function(res){
				_this.comments = res.data.data;		
			});
			 $("#discuss").show();
			 $("#praise").hide();
			 $("#forwarding").hide();
		},
		
		//转发列表
		forwardingList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").hide();
			 $("#praise").hide();
			 $("#forwarding").show();
			//interfaceUrl + "/speak/forwardList" 
   	         this.$http.post(interfaceUrl + "/speak/forwardList",{
				speakId:getQueryString("id"), // getQueryString("id") 来自上一个页面的话题id
				pageIndex:0
				
   	         },{emulateJSON: true}).then(function(res){
				_this.forwardings = res.data.data;		
			});
		},
		//评论列表
		commentList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").show();
			 $("#praise").hide();
			 $("#forwarding").hide();
			//interfaceUrl + "/comment/commentList" 
			this.$http.post(interfaceUrl + "/comment/commentList",{
				userId:userInfo.userID,
				speakId:getQueryString("id"),  // getQueryString("id") 来自上一个页面的话题id
				pageIndex:0
				
			},{emulateJSON: true}).then(function(res){
				_this.comments = res.data.data;		
			});
		},
		//赞列表
		praiseList:function(){
			var _this = this;
			 $(event.target).addClass("active");
			 $(event.target).parent().siblings().children("a").removeClass("active");
			 $("#discuss").hide();
			 $("#praise").show();
			 $("#forwarding").hide();
			//interfaceUrl + "/praise/praiseList" 
			 this.$http.post(interfaceUrl + "/praise/praiseList",{
				speakId:getQueryString("id"), // getQueryString("id") 来自上一个页面的话题id
				pageIndex:0
				
			 },{emulateJSON: true}).then(function(res){
				_this.praises = res.data.data;		
			});
			 
		},
		//评论赞一个
		praiseOne:function(comment,type){
			var _this = this;
			if(type==1){//发言点赞
				if(comment.isPraise==1){//取消点赞
					comment.isPraise=0;
					comment.spCountPraise-=1;
					
					 this.$http.post( interfaceUrl + "/praise/savePraise",{
					 	pid:comment.id,
					 	type:type,
					 	userId:userInfo.userID,
					 	toUserId:comment.userId,  //这个接口那缺少
					 	state:0
					 },{emulateJSON: true}).then(function(res){
							_this.praiseInformation = res.data.data;		
						});
						
				}else{//点赞
					comment.isPraise=1;
					comment.spCountPraise+=1;
				 this.$http.post( interfaceUrl + "/praise/savePraise",{
					 	pid:comment.id,
					 	type:type,
					 	userId:userInfo.userID,
					 	toUserId:comment.userId,
					 	state:1
					 },{emulateJSON: true}).then(function(res){
							_this.praiseInformation = res.data.data;		
						});
				}
		}else if(type==2){//评论点赞
				if(comment.isPraise==1){//取消点赞
					comment.isPraise=0;
					comment.countPraise-=1;
					 this.$http.post( interfaceUrl + "/praise/savePraise",{
					 	pid:comment.cmtId,
					 	type:type,
					 	userId:userInfo.userID,
					 	toUserId:comment.userId,
					 	state:0
					 },{emulateJSON: true}).then(function(res){
							_this.praiseInformation = res.data.data;		
						});
				}else{//点赞
					comment.isPraise=1;
					comment.countPraise+=1;
					 this.$http.post( interfaceUrl + "/praise/savePraise",{
					 	pid:comment.cmtId,
					 	type:type,
					 	userId:userInfo.userID,
					 	toUserId:comment.userId,
					 	state:1
					 },{emulateJSON: true}).then(function(res){
							_this.praiseInformation = res.data.data;		
						});
				}
			}
			
	},
	
	//从底部弹出选择框
	  selective:function(){
	  	$("#bg").fadeIn(0.1);
	  	$("#type").slideDown();
	  },
       //关闭弹窗
       closePopups:function(){
	       	$("#bg").fadeOut(0.1);
		  	$("#type").slideUp(0.1);
       },
       //关注
       attention:function(customer){
       		customer.isFocus=1;
       },
       //取消关注
	  cancelAttention:function(customer){
	  	layer.open({
		    content: '您确定要取消关注吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
       			customer.isFocus=0
		      layer.close(index);
		      vm.closePopups();
		    },
		    no: function(index){
				layer.close(index);
				vm.closePopups();
				}    
		  });
	  	
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
				subUserId:obj.userId      //_this.indexdata[_this.speechIndex].speakUserId
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
	  },
	  
	  
	  reply:function(obj){//对评论 评论
			var _this=this;
			window.location.href=_this.urlList.comments+obj.cmtId+"&name="+escape( obj.userName);
		}
       
		
	}
});