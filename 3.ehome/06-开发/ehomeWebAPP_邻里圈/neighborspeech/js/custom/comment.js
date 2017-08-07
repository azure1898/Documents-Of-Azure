var vm = new Vue({
	el: "#app",
	data: {
		criticism: {},
		commentContent: "",
		meanwhileIcon: "../../images/gx_01.png",
		meanwhileIcon2: "../../images/gx_02.png",
		ismeanwhile: 0,
		showPopup: false,
		topic: {
			id: 0,
			name: ""
		},
		atwho:{
			id:0,
			name:""
		}
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		});
	},
	computed: {
		textnum: function() {
			var _this = this;
			return 200 - _this.commentContent.length
		}
	},
	methods: {
		// 渲染页面
		cartView: function() {
			var _this = this;
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
		sendOut: function() {
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
			$('#results').html(sHtml);
			$('#results .atlink').each(function(i, e) {
				$(e).attr('href', 'http://www.baidu.com?id=' + $(e).attr('data-atid'));
			})
			//			var _this = this;
			//			if($.trim(_this.commentContent)==""){
			//				layer.open({
			//					    content: '内容不能为空'
			//					    ,skin: 'msg'
			//					    ,time: 2 //2秒后自动关闭
			//				  });
			//			}

		},
		seltopic: function() {
			$('#popupPage').empty()
			$.get('../operate/partialtopic.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
		},
		selwho:function(){
			$('#popupPage').empty()
			$.get('../operate/choosecontacts.html', function(res) {
				$('#popupPage').html(res);
			})
		},
		appendTopic: function() {
			$('#editable').append('&nbsp;<a href="#" data-atid="' + this.topic.id + '" class="atlink" contentEditable="false">' + this.topic.name + '</a>&nbsp;');
		},
		appendAt:function(){
			$('#editable').append('&nbsp;<a href="#" data-atid="' + this.atwho.id + '" class="atlink" contentEditable="false">' + this.atwho.name + '</a>&nbsp;');
		}
	}
});