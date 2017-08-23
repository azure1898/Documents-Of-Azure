var vm = new Vue({
	el:"#app",
	data:{
		myfansData:[],
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
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
//			this.$http.get("../../data/myfans.json").then(function(res){
//				_this.myfansData = res.data.data;
//			});
 			this.$http.post(interfaceUrl+"/myHome/myFansList",{
 				userId:userInfo.userID,
 				pageIndex:0
 			},{emulateJSON: true}).then(function(res){
 				_this.myfansData = res.data.data;
 			})
		},
	follow:function(fans){
		var _this = this;
		if(fans.relation==2){//取消关注
			layer.open({
			 title: [
			    '提示',
			  ],
		    content: '确定取消关注？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		      _this.$http.post(interfaceUrl + "/focus/saveFocus",{
		      	userId:userInfo.userID,
		      	subUserId:fans.subUserId,     //fans.subUserId
		      	type:0
		      },{emulataJSON:true}).then(function(res){
		      	 if(res.data.code==1000){
		      	 	fans.relation=1;
		      		layer.close(index);
		      	 }
		      })
		   	
		    }
		  });
		}else{//关注
			_this.$http.post(interfaceUrl + "/focus/saveFocus",{
				userId:userInfo.userID,
		      	subUserId:1 ,     //fans.subUserId
		      	type:0
			},{emulataJSON:true}).then(function(res){
				if(res.data.code==1000){
					fans.relation=2;
				}
			})
	
		}
	}
		
	}
});