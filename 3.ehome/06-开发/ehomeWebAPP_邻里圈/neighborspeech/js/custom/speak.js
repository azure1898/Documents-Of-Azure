var vm = new Vue({
	el:"#app",
	data:{
		speakData:[],
		forwardingReason:"",
		choose:1,
		chooseUrl:"../../images/gx.png",
		chooseUrl2:"../../images/gx02.png",
		uploadImage:[],
		choiceIcon:"../../images/gx_no.png",
		choiceIcon2:"../../images/gx.png",
		uploadImageNum:0
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		});
	},
	computed:{
		textnum:function(){
			var _this = this;
			return 200-_this.forwardingReason.length
		}
	},
	methods:{
		// 渲染页面
		cartView:function(){
			var _this = this;
			this.$http.get("../../data/speak.json").then(function(res){

				var test =res.data.data;
					test.forEach(function(obj,index){
						obj.isactive=0;
					})
				_this.speakData = test;
				console.log(_this.speakData)
					
			});
		},
		choiceImg:function(img){
			var _this = this;
			if(img.isactive==0){
				img.isactive=1;
				_this.uploadImageNum+=1
//				_this.uploadImage.push(img);
//				console.log(_this.speakData);
			}else{
				img.isactive=0;
				_this.uploadImageNum-=1
				
//				_this.uploadImage.pop(img);
//				console.log(_this.uploadImage);
			}
		},
		//确认上传的普遍
		confirm:function(){
			var _this = this;
			_this.speakData.forEach(function(obj,index){
				if(obj.isactive==1){
				_this.uploadImage.push(obj);
				}
			});
			console.log(_this.uploadImage);
		},
		//取消
		cancel:function(){
			var _this = this;
			_this.speakData.forEach(function(obj,index){
				obj.isactive=0
			});
		},
		deleteImg:function(index){
			var _this = this;
			_this.uploadImage.splice(index, 1);	
			
		},
		choice:function(type){//1公开、2、粉丝可见 3、好友可见
			var _this = this;
			_this.choose=type;
		},
		yema:function(ye){
			if(ye==1){
				$("#ye1").show()
				$("#ye2").hide()
				$("#ye3").hide()
			}else if(ye==2){
				$("#ye2").show()
				$("#ye1").hide()
				$("#ye3").hide()
			}else{
				$("#ye2").hide()
				$("#ye1").hide()
				$("#ye3").show()
			}
		}
	}
});