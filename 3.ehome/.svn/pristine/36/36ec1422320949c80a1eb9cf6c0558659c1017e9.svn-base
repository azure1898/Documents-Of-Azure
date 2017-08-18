var sid=1;
var userid=1;

var vm=new Vue({
	el:"#app",
	data:{
		commentDetail:{},
		commentList:{}
	},
	mounted:function(){
		this.$nextTick(function(){
			this.cartView();
		})				
	},
	methods:{
		cartView:function(){
			var _this = this;
			this.$http.get("../../data/commentDetail.json").then(function(res){
				_this.commentDetail = res.data;
				_this.commentList = res.data.data;
			});	
		},
		commentPraise:function(){
			if(this.commentDetail.isPraise==1){

				this.commentDetail.isPraise=0;
				if(this.commentDetail.countPraise>0){
					this.commentDetail.countPraise-=1;
				}
			}
			else{

				this.commentDetail.isPraise=1;
				this.commentDetail.countPraise+=1;
			}
		},
		listPraise:function(index){
			if(this.commentList[index].isPraise==1){

				this.commentList[index].isPraise=0;
				if(this.commentList[index].countPraise>0){
					this.commentList[index].countPraise-=1;
				}
			}
			else{

				this.commentList[index].isPraise=1;
				this.commentList[index].countPraise+=1;
			}
		}
    }
})
$(document).ready(function(){
	
})

