function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            items: {},
            items3: [],
            leaveWords: "",
            courseOrder: {},
            couponDiscounted: 0,
            urlList: {
                checkicon: "../../images/02.png",
                checkedicon: "../../images/01.png"
            },
            couponID: "",
            isOK: true,
            num: 0
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染	
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("businessID"),
                    courseID: getQueryString("id")
                };
                this.getData(_this, '/live/confirmCourseOrder', data, function (resData) {
                    resData.coupons.forEach(function (coupon, index) {
                        coupon.isSelected = 0;

                    });
                    _this.items = resData;
                    _this.items3 = resData.coupons;
                    _this.init(_this.items, _this.items3);

                })
            })
        },
        methods: {
            init: function (item, coup) {
                var arr = new Array();
                coup.forEach(function (e, i) {
                    if (item.totalMoney >= e.couponCondition) {
                        arr.push(e);
                    }
                })
                if (arr.length > 0) {
                    this.isOK = false;
                    this.num = arr.length;
                }
                else {
                    this.isOK = true;
                }
            },
            changeCoupon: function (coupon) {
                this.items3.forEach(function (coupon, index) {
                    coupon.isSelected = 0;
                });

                if (coupon) {
                    coupon.isSelected = 1;
                    this.couponID = coupon.couponID;
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
            affirm: function () {
                var name = $(".names").val();
                var phone = $(".phone").val();
                var xx = /\S/;
                //			var reg = /^[\w\u4e00-\u9fa5\-_][\s\w\u4e00-\u9fa5\-_]*[\w\u4e00-\u9fa5\-_]$/;
                var reg = /^[\u4e00-\u9fa5]{0,}$/;
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
                    var path = interfaceUrl + "/live/submitCourseOrder";
                    var data = {
                        userID: userInfo.userID,
                        buildingID: userInfo.buildingID,
                        coursePrice: _this.items.coursePrice,
                        businessID: _this.items.businessID,
                        courseID: _this.items.courseID,
                        contactPerson: _this.items.contactPerson, //联系人名称
                        contactPhone: _this.items.contactPhone, //联系人电话
                        leaveMessage: _this.leaveWords,
                        couponID: _this.couponID
                    }
                    this.postData(_this, '/live/submitCourseOrder', data, function (resData) {
                        _this.courseOrder = resData;
                    })
                }
            }
        }
    })
}