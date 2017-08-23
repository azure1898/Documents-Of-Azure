var vm = new Vue({
	el:"#app",
	data:{
		forwardData:{},
		speakData:[], //用于装选择的照片
		uploadImageNum:0,//删除图片用
		forwardingReason:"",//转发的内容
		prompt:"写下您的评论吧…",//提示内容
		commentIcon:"../../images/gx_01.png",//是否同时转发
		commentIcon2:"../../images/gx_02.png",
		isComment:0,//是否同时转发标示
		choiceIcon:"../../images/gx_no.png",//选择范围用的图标
		choiceIcon2:"../../images/gx.png",
		rangeValue:{//用来装选择范围
					type:1,
					name: "公开",
					remark: "所有人可见",
					isSelected: true
				},
		topic: {//用于装话题
			id: 0,
			name: ""
		},
		urlList: {
			index:"../home/index.html",
			speechdetail: "../main/speechdetails.html?id=",
			commentdetail: "../main/commentdetails.html?id=",
			comments:"../main/comment.html?id=",
			forward:"../main/forward.html?id=",
			myspeech:"myspeech.html?id=",
			mypraise:"mypraise.html?id=",
			personalpage:"../main/personalpage.html?id="
		},
		uploadImageNum:0,//选择照片的数量
		showPopup: false,//显示隐藏标示
		textnum:200,//限制字数用
		atwhos:[],//用于装@对象
		top:"",//向后台传的话题id
		fd:""//向后台传的朋友id.

	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
			this.loademoji();
		});
	},
	
	methods:{
	//页面加载时导入emoji表情包
	    loademoji:function(){
                var emos = getEmojiList()[0];//此处按需是否生成所有emoji
	            var html = '<div >常用表情</div><ul>';
	            for (var j = 0; j < emos.length; j++) {
	            	if (j<=38) {
                    var emo = emos[j];
	                var data = 'data:image/png;base64,' + emo[2];	                
	                html += "<img style='display: inline;vertical-align: middle;'  id=img"+j.toString()+" src=" + data + "  unicode16=" + emo[1] + " onclick=\"addemoji('img"+j.toString()+"')\"  /></li>";
	               };
	            }
			    var oDiv = document.getElementById("faceid");				
				oDiv.innerHTML = html;			      			
		},
				
		//点击笑脸图片显示表情
		faceidshow:function(){			
			var node=$('#faceid');
			if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
			    document.getElementById("editable").focus();
			　　node.show();　
			}else{
			　　node.hide();
			}
		},		
		// 渲染页面
		cartView:function(){
			var _this = this;
			this.$http.post(interfaceUrl+"/forward/toForward",{
				speakId: getQueryString("id")   //这个发言的地址应该来自上一个页面getQueryString("id")
			},{emulateJSON: true}).then(function(res){
				_this.forwardData = res.data;
			});
		},
		meanwhile:function(){
			var _this = this;
			if(_this.isComment==0){
				_this.isComment=1;
			}else{
				_this.isComment=0;
			}
			
		},
		//确认转发
		sendOut: function() {
			var _this = this;
			
			var sTest = $('#editable').text();
			var sHtml = $('#editable').html();
			//转发可以不加这个判断
//			if($.trim(sTest).length < 5) {
//				layer.open({
//					content: '内容不能少于五个字符',
//					skin: 'msg',
//					time: 2 //2秒后自动关闭
//				});
//				return;
//			}
			var topiclists=[];
			var friend=[];
			
			$('#results').html(sHtml);
			$('#results .atlink').each(function(i, e) {
				$(e).attr('href', '../home/topiclist.html?id=' + $(e).attr('data-atid'));
				topiclists.push($(e).attr('data-atid'));
			});
			$('#results .atlinkobj').each(function(i, e) {
				$(e).attr('href', '../home/personalpage.html?id=' + $(e).attr('data-atid'));
				friend.push($(e).attr('data-atid'));
			})
			_this.top = topiclists.join()
  			_this.fd = friend.join()
           _this.forwardingReason=$('#results').html();
			
			this.$http.post(interfaceUrl+"/forward/saveForward",{
				speakId:getQueryString("id"),  //发言的id 应该来自上一个页面
				reason:_this.forwardingReason,    //转发是输入的内容 
				isComment:_this.isComment,//是否同时评论
				visible: _this.rangeValue.type,         //可见范围
				imageUrl:_this.speakData,        //选择图片
				toUsersId:_this.fd			//@用户id
			},{emulateJSON: true}).then(function(res){
				if(res.data.code==1000){window.location.href=_this.urlList.index}
			});
			
		},
		//变下面的数字
		calculation:function(){//没加两端去空格    加上有问题
			var _this = this;
			var sTest = $('#editable').text();
			console.log($.trim(sTest).length);
			if(sTest.length <200) {
				_this.textnum=200-sTest.length;
			}else if(sTest.length == 200){
				_this.commentContent = $('#editable').html();
				_this.textnum=0;
			}else{
				$('#editable').html(_this.commentContent)
				layer.open({
					content: '发言超过限制长度',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
		},
		//删除图片
		deleteImg:function(image){
			var _this = this;
//			_this.uploadImage.splice(index, 1);	
			image.selected=0;
			image.isactive=0;
			_this.uploadImageNum-=1;
			vm.uploadImageNum = _this.uploadImageNum;
		},
		//打开选择范围页
		openRange:function(){
			$('#popupPage').empty()
				$.get('../operate/partialrange.html',function(res){
				$('#popupPage').html(res);
			})
			this.showPopup=true;
		},
		//打开上传照片页
		openUploading:function(){
			$('#popupPage').empty()
			$.get('../operate/partialuploading.html',function(res){
				$('#popupPage').html(res);
			})
			this.showPopup=true;
		},
		//打开话题
		
		seltopic: function() {
			$('#popupPage').empty()
			$.get('../operate/partialtopic.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
		},
		//打开@对象
		
		selwho:function(){
			$('#popupPage').empty()
			$.get('../operate/partialcontact.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
		},
		//插入话题
		appendTopic: function() {
			$('#editable').append('&nbsp;<a href="#" data-atid="' + this.topic.id + '" class="atlink" contentEditable="false">' + this.topic.name + '</a>&nbsp;');
		    vm.calculation();//插入标签后触发input事件
		},
		//插入@对象
		appendAt:function(){
			this.atwhos.forEach(function(obj,index){
				$('#editable').append('&nbsp;<a href="#" data-atid="' + obj.frendId + '" class="atlinkobj" contentEditable="false">'+'@' + obj.frendName + '</a>&nbsp;');
				vm.calculation();//插入标签后触发input事件
			});
			
		}
		
	}
});