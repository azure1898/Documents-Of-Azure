var vm = new Vue({
	el: "#app",
	data: {
		speechLists: {},
		urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id="
		}
	},
	mounted: function() {
		this.$nextTick(function() {
			this.cartView();
		})
	},
	methods: {
		cartView: function() {
			var _this = this;
//			this.$http.get("../../data/myspeech2.json").then(function(res) {
//				_this.speechLists = res.data.data;
//			});
			this.$http.post(interfaceUrl+"/myHome/mySpeak",{
				 	userId:userInfo.userID,    
				 	villageInfoId:addressId,   //被赞人 id  _this.personalpage.id
				 	pageIndex:0
				 },{emulateJSON: true}).then(function(res){
						_this.speechLists = res.data.data;		
			});
		},
		praiseOne:function(obj,type){
			var _this = this;
			if(obj.isPraise==1){//取消点赞
				
				 this.$http.post(interfaceUrl+"/praise/savePraise",{
				 	pid:obj.speakId,//发言id  obj.id
				 	type:type,   
				 	userId:userInfo.userID,    
				 	toUserId:obj.userId,   //被赞人 id  _this.personalpage.id
				 	state:0
				 },{emulateJSON: true}).then(function(res){
				 	if(res.data.code==1000){
				 		obj.isPraise=0;
						obj.countPraise-=1;
				 	}
					});
			}else{//点赞
				
				 this.$http.post( interfaceUrl + "/praise/savePraise",{
				 	pid:obj.speakId,
				 	type:type,
				 	userId:userInfo.userID,
				 	toUserId:obj.userId,
				 	state:1
				 },{emulateJSON: true}).then(function(res){
				 	if(res.data.code==1000){
				 		obj.isPraise=1;
						obj.countPraise+=1;	
				 	}
					});
			}
	},
		deleteSpeech:function(obj,index){
			var _this = this;
			layer.open({
		    content: '确认删除此发言？'
		    ,btn: ['确认', '取消']
		    ,yes: function(indextan){
		    	_this.$http.post("/myHome/speakDel",{
			 	speakId:obj.speakId,
			 	}).then(function(res){
				if(res.data.code==1000){
				  _this.speechLists.splice(index, 1);
		      		layer.close(indextan);
					}
				});
//					_this.speechLists.splice(index, 1);
//		      		layer.close(indextan);
		    }
		  });

		
		}
	}
})