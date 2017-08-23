var vm = new Vue({
	el:"#app",
	data:{
		releaseDetails:[],
	    searchValue:"",
	    subName:"",
	    urlList: {
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"../myspeech.html?id=",
			mypraise:"../mypraise.html?id=",
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
			_this.$http.post(interfaceUrl+"/subject/listSubject",{
				addressId:userInfo.addressId,
				subjectName:getQueryString("id"),//这个话题来自前一个页面  getQueryString("id")
				userId:userInfo.userID,
				pageIndex:0,
			},{emulateJSON: true}).then(function(res){
				    _this.subName= res.data.subjectName
				_this.releaseDetails = res.data.data;
			});
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
				 	userId:userInfo.userID,    
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
				 	userId:userInfo.userID,    
				 	toUserId:obj.speakUserId,   //被赞人 id  _this.personalpage.id
				 	state:1
				 },{emulateJSON: true}).then(function(res){
						_this.praiseMessage = res.data.message;		
					});
			}
	},
		show: function (ev) {//当点击回车时
			var _this = this;
            if(ev.keyCode==13){
               var sent="";
               if(_this.searchValue.substr(-1,1)=="#"&&_this.searchValue.substr(1,1)=="#"){
               		sent=_this.searchValue;
               }else if(_this.searchValue.substr(-1,1)!="#"&&_this.searchValue.substr(1,1)!="#"){
               		sent= "#"+_this.searchValue+"#";
               }else if(_this.searchValue.substr(-1,1)!="#"&&_this.searchValue.substr(1,1)=="#"){
               			sent= _this.searchValue+"#";
               }else{
               		sent="#" +_this.searchValue;
               }
               _this.$http.post(interfaceUrl+"/subject/listSubject",{
					addressId:userInfo.addressId,
					subjectName:sent,
					userId:userInfo.userID,
					pageIndex:0,
				},{emulateJSON: true}).then(function(res){
					_this.releaseDetails = res.data.data;
				});
               
               
            }
          }
		
	}
});