var vm = new Vue({
	el: "#app",
	data: {
		items: {},
		item: {},
		items3: [],
		leaveMessage: "",
		urlList: {
			checkicon: "../../images/02.png",
			checkedicon: "../../images/01.png"
		},
		couponDiscounted: 0,
		couponID:"",
        isOK:true,
        num:0
	},
	mounted: function() {
		this.$nextTick(function() {
			var _this = this;
			var data = {
				userID: userInfo.userID,
				buildingID: userInfo.buildingID,
				businessID: getQueryString("businessID"),
				siteReservationID: getQueryString("id")
			};
			this.getData(_this, "/live/confirmSiteOrder", data, function(resData) {
				resData.coupons.forEach(function(coupon, index) {
					coupon.isSelected = 0;
				});
				_this.items = resData;
				_this.items3 = _this.items.coupons;
				_this.init(_this.items,_this.items3)
			})
		})
	},
	methods: {
		init:function(item,coup){
        	var arr=new Arrey();
        	coup.foreach(function(e,i){
        		if(item.totalMoney >= e.couponCondition){
        		arr.push(e);
        	}
           })
        	if(arr.length>0){
        		this.isOK=false;
        		this.num=arr.length;
        	}
        	else{
        		this.isOK=true;
        	}
        },
		changeCoupon: function(coupon) {
			this.items3.forEach(function(coupon, index) {
				coupon.isSelected = 0;
			});
			if (coupon) {
				coupon.isSelected = 1;
				this.couponID=coupon.couponID;
				if (coupon.couponType == 0) {
					this.couponDiscounted = coupon.couponMoney;
				} else {
					this.couponDiscounted = this.items.totalMoney * (100 - coupon.couponMoney) / 100;
				}
			} else {
				this.couponDiscounted = 0;
			}
			$("#couponModal").modal('toggle');
		},
		affirm: function() {
			var name = $(".name").val();
			var phone = $(".phone").val();
			var xx = /\S/;
			var reg = /^[\w\u4e00-\u9fa5\-_][\s\w\u4e00-\u9fa5\-_]*[\w\u4e00-\u9fa5\-_]$/;
			if (!xx.test(name)) {
				layer.open({
					content: '请输入联系人',
					btn: '确定',
					shadeClose: false,
				});
			} else if (!xx.test(phone)) {
				layer.open({

					content: '请输入手机号',
					btn: '确定',
					shadeClose: false,
				});
			} else if (xx.test(name) && !reg.test(name)) {

				layer.open({

					content: '请输入正确联系人',
					btn: '确定',
					shadeClose: false,
				});
			} else if (xx.test(phone) && !(/^1[34578]\d{9}$/.test(phone))) {
				layer.open({
					content: '请输入正确的手机号',
					btn: '确定',
					shadeClose: false,
				});
			} else {
				var _this = this;
				var orderData = {
					userID: userInfo.userID,
					buildingID: userInfo.buildingID,
					businessID: _this.items.businessID,
					contactPerson: _this.items.contactPerson, //联系人名称
					contactPhone: _this.items.contactPhone, //联系人电话
					siteReservationID: getQueryString("id"),
					leaveMessage: _this.leaveWords,
					couponID:_this.couponID
				}
				this.postData(_this, "/live/submitSiteOrder", orderData, function(resData) {
					_this.item = resData;

				})
			}
		}
	}
})