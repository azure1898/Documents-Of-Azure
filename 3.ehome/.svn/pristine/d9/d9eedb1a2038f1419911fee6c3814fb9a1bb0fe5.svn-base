var vm = new Vue({
	el: "#app",
	data: {
		urlList: {
			list: "servicelist.html?id=",
			detail: "servicedetail.html?id=",
			index: "serviceindex.html?id=",
			order: "serviceorder.html?id=",
		},
		detail: {},
		collect: 1,
		item: {}
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			var _this = this;
			var data = {
				userID: userInfo.userID,
				serviceID: getQueryString("id"),
				buildingID: userInfo.buildingID
			};
			this.getData(_this, "/live/getServiceItemDetail", data, function(resData) {
				_this.detail = resData;
				if(_this.detail.isNormal==0){
					toast2("商家休息中，暂时不接受订单")}
			})
			$('#myCarousel').carousel({
				//自动4秒播放
				interval: 3000,
			});
			var myElement = document.getElementById('myCarousel')
			var hm = new Hammer(myElement);
			hm.on("swipeleft", function() {
				$('#myCarousel').carousel('next')
			})
			hm.on("swiperight", function() {
				$('#myCarousel').carousel('prev')
			})
		})
	},
	methods: {
		
		collection: function(item) {
			var _this = this;
			_this.myCollection(_this, item);
		}
	}
});