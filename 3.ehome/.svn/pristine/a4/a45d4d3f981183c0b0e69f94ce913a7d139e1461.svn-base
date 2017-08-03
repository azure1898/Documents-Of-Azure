var vm = new Vue({
	el: "#app",
	data: {
		urlList: {
			list: "servicelist.html?id=",
			detail: "servicedetail.html?id=",
			index: "serviceindex.html?id="
		},
		lists: []
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			this.cartView();
		})
	},
	methods: {
		// 渲染页面
		cartView: function() {
			var _this = this;
			this.$http.get(interfaceUrl+"/live/getServiceList",
			{   userID:userInfo.userID,
				buildingID:userInfo.buildingID,
				sort: 1
			}).then(function(res) {
				if(res.data.code == 1000){
					_this.lists = res.data.data;
				}
			})

		},
		
		sort: function() {
			$(".sort_xiala").stop().slideToggle(400);
			$("#bg").stop().fadeToggle(200);
		},
		
		changeSort:function(method){
			$(".sort_xiala > ul > li.selected span").remove();
			$(".sort_xiala > ul > li.selected").removeClass("selected");
			$(event.target).append("<span class='green_dh'></span>");
			$(event.target).addClass("selected");
			this.sort();
				
			var path = interfaceUrl+"/live/getServiceList";
			var _this = this;
				
			this.$http.get(path, {
				   userID:userInfo.userID,
					buildingID:userInfo.buildingID,
					sort: method
				}).then(function(res){
				if(res.data.code == 1000){
					_this.lists = res.data.data;					
					}
				});
		}
	}

});