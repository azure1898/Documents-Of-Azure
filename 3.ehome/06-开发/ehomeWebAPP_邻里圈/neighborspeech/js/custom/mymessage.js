var vm = new Vue({
	el:"#app",
	data:{
		mymessages:{},
		unread:0,//未读标示
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id="
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
			this.$http.post(interfaceUrl + "/message/countMsg",{
				userID:1
			},{emulateJSON: true}).then(function(res){
				_this.mymessages = res.data.data[0];
				for(obj in _this.mymessages){
					if(_this.mymessages[obj]!=0){
						_this.unread=0
					}else{
						_this.unread=1
					}
					
				}
			});
		}
	}
});