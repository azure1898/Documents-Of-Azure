    var vm=new Vue({
    		el:"#app",
    		data:{
    			totalMoney:0,
    			coupMoney:0,
    		items:{},
    		totalMoney:0,
    		leaveWords:"",
		   courseOrder:{},
		   contactPerson:"11",
		   contactPhone:"13140628521"
    		},
			
  	mounted: function() { //页面加载之后自动调用，常用于页面渲染	
				this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
						var _this = this;
						n=0;
			this.$http.get(interfaceUrl+'/live/confirmCourseOrder',{userID:userInfo.userID,buildingID:userInfo.buildingID,businessID:getQueryString("businessID"),courseID:getQueryString("id")}).then(function(res){
                  _this.items = res.data.data; 
                  
			},function(res){
			alert(res.status)});
				})},
		methods:{	
			
		changeStatus1:function(){
			n++;
			if(n%2==1){
				$(event.target).attr("src","../../images/01.png")
				$(".choose_coup_main .choose_coup_main_right img").attr("src","../../images/02.png")
			     $("#coups").text("不使用优惠券");
			}
			else{
				$(event.target).attr("src","../../images/02.png")
				 $("#coups").text("");
			}
             
          

		},changeStatus2:function(item){
			console.log(item);
				$(".details_spgm_right_2 img").attr("src","../../images/02.png")
				$(".choose_coup_main .choose_coup_main_right img").attr("src","../../images/02.png")
				$(event.target).closest("img").attr("src","../../images/01.png")
		      
		      $("#coups").text("-￥"+item.couponMoney);
		}
		,
		affirm:function(){
		var name=$(".name").val();	
	     var phone = $(".phone").val();
         var xx = /\S/;
         var reg = /^[\w\u4e00-\u9fa5\-_][\s\w\u4e00-\u9fa5\-_]*[\w\u4e00-\u9fa5\-_]$/;
          if(!xx.test(name)){
         	layer.open({
						   content: '请输入联系人',
						  btn: '确定',
						  shadeClose: false,
						  });
         	 
         }
          else if(!xx.test(phone)){
         	layer.open({
						
						  content: '请输入手机号',
						  btn: '确定',
						  shadeClose: false,
						  });
         	
           
         }
          else if(xx.test(name)&&!reg.test(name)){
                  layer.open({
						
						   content: '请输入正确联系人',
						  btn: '确定',
						  shadeClose: false,
						  });     		
        	
        	  
        }
          else if(xx.test(phone)&&!(/^1[34578]\d{9}$/.test(phone))){
          	
						layer.open({
						    content: '请输入正确的手机号',
						  btn: '确定',
						  shadeClose: false,
						 });  
          }
          else{
        			var _this = this;
						var path = interfaceUrl+"/live/submitCourseOrder";
						this.$http.post(path,{	
							userID:userInfo.userID,
			      			buildingID:userInfo.buildingID,
			      			coursePrice:_this.items.coursePrice,
			      			businessID:_this.items.businessID,
							courseID:_this.items.courseID,
							contactPerson:_this.items.contactPerson,//联系人名称
							contactPhone:_this.items.contactPhone,//联系人电话
							leaveMessage:_this.leaveWords
						
						},{emulateJSON: true}).then(function(res) {
						_this.courseOrder = res.data.data;
						})
        		
        		
        		
        	}
			}

	     	}	
    	})
   
   
  
    	