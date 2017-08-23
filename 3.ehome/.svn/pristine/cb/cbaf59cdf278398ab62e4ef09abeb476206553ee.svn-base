var vm = new Vue({
	el:"#app",
	data:{
		commentmes:[],
		type:0,//用于显示回复还是删除
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
			
//			this.$http.get("../../data/commentme.json").then(function(res){
//				_this.commentmes = res.data.data;
//			});
			this.$http.post(interfaceUrl + "/message/commentMe",{
				userId:userInfo.userID,
			},{emulateJSON: true}).then(function(res){
				_this.commentmes = res.data.data;
			});
			
			
		},
		switchs:function(index){
			var _this = this;
			if(index==1){//请求评论我的
			 	$(event.target).addClass("active");
			 	$(event.target).parent().siblings().children("a").removeClass("active");
//				this.$http.get("../../data/commentme.json").then(function(res){
//				_this.commentmes = res.data.data;
//				});
			this.$http.post(interfaceUrl + "/message/commentMe",{
				userId:userInfo.userID,
			},{emulateJSON: true}).then(function(res){
				_this.commentmes = res.data.data;
			});
				_this.type=0
			}else{//请求我的评论
				$(event.target).addClass("active");
			 	 $(event.target).parent().siblings().children("a").removeClass("active");
//				this.$http.get("../../data/mecomment.json").then(function(res){
//				_this.commentmes = res.data.data;
//				});
			this.$http.post(interfaceUrl + "/message/myComment",{
				userId:userInfo.userID,
			},{emulateJSON: true}).then(function(res){
				_this.commentmes = res.data.data;
			});
				
				_this.type=1
			}
		},
		deleteComments:function(obj,index){
			var _this = this;
			layer.open({
		    content: '确定删除评论吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(indext){
//		    	_this.commentmes.splice(index, 1)
//		     	 	layer.close(indext);
		    _this.$http.post(interfaceUrl + "/message/commentDel",{
				commentId: obj.id  //_this.commentmes[index].commontList[0].id
			},{emulateJSON: true}).then(function(res){
				if(res.data.code==1000){
					_this.commentmes.splice(index, 1)
		     	 	layer.close(indext);
				}
			});
		    },
		    no: function(indext){
				layer.close(indext);
				}    
		  });
		},
		reply:function(obj){//点击回复吧回复人的id和名字带过去
			var _this=this;
			window.location.href=_this.urlList.comments+obj.commontList[0].id+"&name="+escape( obj.commontList[0].userName);
		}
	}
});