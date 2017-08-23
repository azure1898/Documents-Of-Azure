var vm = new Vue({
	el:"#app",
	data:{
		speakDetails:"",
		speakData:[],//用于装选择的照片
		upimg:[],
		uploadImageNum:0,//删除图片用
		imgurl:"",
		prompt:"分享社区的新鲜事!",//提示内容
		choose:1,
		chooseUrl:"../../images/gx.png",
		chooseUrl2:"../../images/gx02.png",
//		uploadImage:[],
		choiceIcon:"../../images/gx_no.png",//选择范围用的图标
		choiceIcon2:"../../images/gx.png",
		uploadImageNum:0,
		rangeValue:{//用来装选择范围
					type:1,
					name: "公开",
					remark: "所有人可见",
					isSelected: true
				},
		topic: {//用来保存话题
			id: 0,
			name: ""
		},
		uploadImageNum:0,//选择照片的数量
		textnum:200,//限制字数用
		atwhos:[],//用于装@对象
		
		top:"",//向后台传的话题id
		fd:"",//向后台传的朋友id.
		showPopup: false,
		speechInformation:{},
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
		amg:{}
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
				//oDiv.id="faceid"					
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
		
		
		cartView:function(){
			var _this = this;
			_this.$http.post(interfaceUrl + "/speak/toSpeak",{
				userId:userInfo.userID
			},{emulateJSON: true}).then(function(res){
				_this.speechInformation = res.data;
			});
		},
		//发送发言
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
           _this.speakDetails=$('#results').html();
           
//           测试
            let formData = new FormData();
            formData.append('userId', userInfo.userID);
            formData.append('plateId', this.speechInformation.plateId);
            formData.append('content', _this.speakDetails);
            formData.append(File, _this.amg);
            formData.append('visible',_this.rangeValue.type);
            formData.append('toUsersIds', _this.top);
            formData.append('subjectIds', 	_this.fd);
            formData.append('villageInfoId', userInfo.addressId);
            
            let config = {
              headers: {
                'Content-Type':'multipart/form-data' 
                /*'multipart/form-data'*/
              }
            }
            this.$http.post(interfaceUrl+"/speak/saveSpeak", formData, config).then(function (res) {
              if (res.data.code === '1000') {
              	window.location.href=_this.urlList.index
              }
            })



           
/*			this.$http.post(interfaceUrl+"/speak/saveSpeak",{
				userId:userInfo.userID,
				plateId:_this.speechInformation.plateId, 
				content:_this.speakDetails,     //_this.speakDetails
				imageUrl:_this.speakData,   //_this.speakData,        //选择图片
				visible: _this.rangeValue.type,         //可见范围
				toUsersIds: _this.top	,		// _this.top
				subjectIds:	_this.fd,			//_this.fd
				villageInfoId:userInfo.addressId        //楼盘id
			},{emulateJSON: true}).then(function(res){
				if(res.data.code==1000){window.location.href=_this.urlList.index}
			});*/

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
		
		deleteImg:function(index){
			var _this = this;
			_this.speakData.splice(index, 1);	
//			image.selected=0;
//			image.isactive=0;
//			_this.uploadImageNum-=1;
			vm.uploadImageNum = _this.uploadImageNum;
		},
		//打开选择范围
		openRange:function(){
			$('#popupPage').empty()
				$.get('../operate/partialrange.html',function(res){
				$('#popupPage').html(res);
			})
			
			this.showPopup=true;
		},
		//打开选取照片
//		openUploading:function(){
//			$('#popupPage').empty()
//			$.get('../operate/partialuploading.html',function(res){
//				$('#popupPage').html(res);
//			})
//			
//			this.showPopup=true;
//		},
		//打开选择话题
		seltopic: function() {
			$('#popupPage').empty()
			$.get('../operate/partialtopic.html', function(res) {
				$('#popupPage').html(res);
			})
			this.showPopup = true;
		},
		//打开选择联系人
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
		//插入联系人
		appendAt:function(){
			this.atwhos.forEach(function(obj,index){
				$('#editable').append('&nbsp;<a href="#" data-atid="' + obj.friendId + '" class="atlinkobj" contentEditable="false">' +'@'+ obj.friendName + '</a>&nbsp;');
				vm.calculation();//插入标签后触发input事件
			});
		},
		//******上传测试
		img:function(){
			var _this = this;
			
			　document.querySelector('input[type=file]').click();
		},
		creatImg:function(){
			var _this = this;
			_this.speakData.push(_this.imgurl)
			
		}
		
	}
});


function getImgURL(node){
			var imgURL = "";      
		    try{     
		        var file = null;  
		        if(node.files && node.files[0] ){  
		            file = node.files[0];   
		        }else if(node.files && node.files.item(0)) {                                  
		            file = node.files.item(0);     
		        }     
		        //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
		        try{  
		            //Firefox7.0   
		            imgURL =  file.getAsDataURL();    
		            //alert("//Firefox7.0"+imgRUL);                           
		        }catch(e){  
		            //Firefox8.0以上                                
		            imgURL = window.URL.createObjectURL(file);  
		            //alert("//Firefox8.0以上"+imgRUL);  
		        }  
		     }catch(e){      //这里不知道怎么处理了，如果是遨游的话会报这个异常                   
		        //支持html5的浏览器,比如高版本的firefox、chrome、ie10  
		        if (node.files && node.files[0]) {                            
			            var reader = new FileReader();   
			            reader.onload = function (e) {                                        
			                imgURL = e.target.result;    
			            };  
			            reader.readAsDataURL(node.files[0]);   
			        }  
		     }  
		    vm.imgurl = imgURL; 
		    
		    console.log(node.files);
		    vm.amg=node.files
		    vm.creatImg();  
//		    return imgURL;  
		}