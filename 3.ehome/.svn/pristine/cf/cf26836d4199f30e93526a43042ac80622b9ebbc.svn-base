var vm = new Vue({
	el: "#app",
	data: {
		groups: [],
		urlList: {
			detail: "groupbuydetail.html?id=",
			module: "modulelist.html?id=",
			order: "groupbuyorder.html?id="
		},
		currentType: 1,
		status_1: '已抢光',
		status_2: '抢购中',
		status_3: '即将开始',
	},
	mounted: function() { //页面加载之后自动调用，常用于页面渲染
		this.$nextTick(function() { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
			var _this = this;
			var data = {
				userID: userInfo.userID,
				buildingID: userInfo.buildingID,
				type: 1
			};
			this.getData(_this, '/live/getGroupBuyList', data, function(resData) {
				_this.groups = resData;
			})
		})
	},
	methods: {
		changeActivity: function(id) {
			var _this = this;
			if (id == "first") {
				var data = {
					userID: userInfo.userID,
					buildingID: userInfo.buildingID,
					type: 1
				};
				this.getData(_this, '/live/getGroupBuyList', data, function(resData) {
					_this.groups = resData;
				})
				$(".Boutique_buy_title>.Boutique_buy_title_left>a.active").removeClass("active");
				$(event.target).addClass("active");
			} else if (id == "second") {
				this.currentType = 2;
				var _this = this;
				var data = {
					userID: userInfo.userID,
					buildingID: userInfo.buildingID,
					type: 2
				};
				this.getData(_this, '/live/getGroupBuyList', data, function(resData) {
					_this.groups = resData;
				})
				if (_this.groups && _this.groups.length > 0) {
					$("#second").text("暂无新的团购准备上线")
				}
				$(".Boutique_buy_title>.Boutique_buy_title_left>a.active").removeClass("active");
				$(event.target).addClass("active");
			} else {
				alert("出错！！")
			}
		}
	}
});