var vm = new Vue({
	el: "#app",
	data: {
		speechList: {}
	},
	mounted: function() {
		this.$nextTick(function() {
			this.cartView();
		})
	},
	methods: {
		cartView: function() {
			var _this = this;
			this.$http.get("../../data/mySpeech.json").then(function(res) {
				_this.speechList = res.data;
			});
		},
		praiseOne: function(releaseDetail, type) {
			var _this = this;
			if(releaseDetail.isPraise == 1) { //取消点赞
				releaseDetail.isPraise = 0;
				//				 this.$http.post("/speak/saveComment"{
				//				 	id:releaseDetail.id,
				//				 	type:type
				//				 }).then(function(res){
				//						_this.praiseInformation = res.data.message;		
				//					});
			} else { //点赞
				releaseDetail.isPraise = 1;
				//				this.$http.post("/speak/saveComment"{
				//				 	id:releaseDetail.id,
				//				 	type:type
				//				 }).then(function(res){
				//						_this.praiseInformation = res.data.message;		
				//					});
			}
		}
	}
})