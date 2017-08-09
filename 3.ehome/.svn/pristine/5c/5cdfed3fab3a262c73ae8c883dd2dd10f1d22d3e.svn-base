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
			this.$http.get("../../data/myspeech2.json").then(function(res) {
				_this.speechLists = res.data.data;
			});
		},
		praiseOne: function(obj, type) {
			var _this = this;
			if(obj.isPraise == 1) { //取消点赞
				obj.isPraise = 0;
				obj.countPraise-=1;
//					 this.$http.post("/speak/saveComment"{
//					 	id:releaseDetail.id,
//					 	type:type
//					 }).then(function(res){
//							_this.praiseInformation = res.data.message;		
//						});
			} else { //点赞
				obj.isPraise = 1;
				obj.countPraise+=1;
	//				this.$http.post("/speak/saveComment"{
	//				 	id:releaseDetail.id,
	//				 	type:type
	//				 }).then(function(res){
	//						_this.praiseInformation = res.data.message;		
	//					});
			}
		},
		deleteSpeech:function(onj,index){
			var _this = this;
			layer.open({
		    content: '确认删除此发言？'
		    ,btn: ['确认', '取消']
		    ,yes: function(indextan){
         	  _this.speechLists.splice(index, 1);
		      layer.close(indextan);
		    }
		  });

//		this.$http.post("/myHome/speakDel",{
//			 	speakId:onj.speakId,
//			 }).then(function(res){
//				if(res.data.code==1000){
//				  _this.speechLists.splice(index, 1);
//				}
//			});
		}
	}
})