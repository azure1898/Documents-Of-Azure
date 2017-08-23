var vm = new Vue({
	el: "#app",
	data: {
		criticism: {},
		commentContent: "",
		meanwhileIcon: "../../images/gx_01.png",
		meanwhileIcon2: "../../images/gx_02.png",
		ismeanwhile: 0,  //是否同时转发
		showPopup: false,
		topic: {
			id: 0,
			name: ""
		},
		atwhos:[],
		speakData:[],
		uploadImageNum:0,
		
		textnum:200,
		
		prompt:"写下您的评论吧…",
		
		isReply:0   //判断是回复还是评论 0是回复
		
		
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
			this.loademoji();
			
		});
	},
	methods: {
		// 渲染页面
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
				//oDiv.id="faceid"					
				oDiv.innerHTML = html;
			      			
		},
		
		
		
		cartView: function() {
			var _this = this;
			if(getQueryString("name")!=null){
				_this.prompt= "回复@"+getQueryString("name");
			}
			console.log(getQueryString('name'))

			//			this.$http.get("/speak/toComment"{
			//				speakId:123,
			//			}).then(function(res){
			//				_this.criticism = res.data;
			//			});
		},
		meanwhile: function() {
			var _this = this;
			if(_this.ismeanwhile == 0) {
				_this.ismeanwhile = 1;
			} else {
				_this.ismeanwhile = 0;
			}

		},
		//保存评论
		sendOut: function() {
			var _this = this;
			var sTest = $('#editable').text();
			var sHtml = $('#editable').html();
			if($.trim(sTest).length < 5) {
				layer.open({
					content: '内容不能少于五个字符',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				return;
			}
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
            _this.commentContent =  $('#results').html()
            
            if(getQueryString("name")==null){//根据name判断是回复还是评论     空为评论
            	_this.isReply=1
            }else{
            	_this.isReply=0
            }
            this.$http.post(interfaceUrl+"/comment/saveComment",{
					pid : getQueryString("id"), //来自上一个页面的id  可能是发言id 可能是评论id
					content :  _this.commentContent,//评论内容 
					isForward : _this.ismeanwhile,
					isComment:_this.isReply
				},{emulateJSON: true}).then(function(res){
				   if(res.data.code==1000){
				   		window.history.go(-2);//这了目前不清楚应该-1 但是前进两个才实现
				   }else{
				   	layer.open({
					    content: res.data.message
					    ,btn: '确定'
					  });
				   }
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
		//删除选择的图片
		deleteImg:function(image){
			var _this = this;
			image.selected=0;
			image.isactive=0;
			_this.uploadImageNum-=1;
		},
		seltopic: function() {
			$('#popupPage').empty()
			$.get('../operate/partialtopic.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
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
		
		selwho:function(){
			$('#popupPage').empty()
			$.get('../operate/partialcontact.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
		},
		
		//点击相机跳转的页面
		openUploading:function(){
			$('#popupPage').empty()
			$.get('../operate/partialuploading.html',function(res){
				$('#popupPage').html(res);
			})
			this.showPopup=true;
	},
		appendTopic: function() {
			$('#editable').append('&nbsp;<a href="#" data-atid="' + this.topic.id + '" class="atlink" contentEditable="false">' + this.topic.name + '</a>&nbsp;');
		    vm.calculation();//插入标签后触发input事件
		},
		appendAt:function(){
			this.atwhos.forEach(function(obj,index){
				$('#editable').append('&nbsp;<a href="#" data-atid="' + obj.friendId + '" class="atlink" contentEditable="false">' + obj.friendName + '</a>&nbsp;');
				vm.calculation();//插入标签后触发input事件
			});
			
		}
	}
});

//点击笑脸表情获取当前笑脸img
		function addemoji(obj){
         	var aimg=document.getElementById(obj).src;
         	var adiv=document.getElementById("editable");
            insertHtmlAtCaret('<img src="'+aimg+'">');         	
         }