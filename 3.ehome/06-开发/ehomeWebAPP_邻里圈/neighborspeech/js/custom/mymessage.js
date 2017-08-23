var vm = new Vue({
	el:"#app",
	data:{
		mymessages:{},
		count:{},
		unread:0,//未读标示
		urlList: {
			index:"../home/index.html?unread=",
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id=",
			commentme:"commentme.html?id=",
			ordermessage:"ordermessage.html",
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
			this.$http.post(interfaceUrl + "/message/countMsg",{
				userId:userInfo.userID,
				villageInfoId:userInfo.addressId
			},{emulateJSON: true}).then(function(res){
				_this.mymessages = res.data.data;
				console.log(_this.mymessages)
				_this.count=_this.mymessages.Count
				for(obj in _this.count){
					if (obj==countPraise) {continue}
					if(_this.count[obj]==0){
						_this.unread=0
					}else{
						_this.unread=1
						return
					}
				}
				if(_this.mymessages.adminList.length > 0){
					_this.unread=1
				}
			});
//			indexunread=_this.unread
		}
	}
});