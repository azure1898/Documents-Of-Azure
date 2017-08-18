var vm = new Vue({
    el: "#app",
    data: {
        item: [],
        items: {},
        isOK: false
    },
    mounted: function () {
        this.$nextTick(function () {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID
            };
            this.getData(_this, "/home/getCoupon", data, function (resData) {
                _this.item = resData;            
            })
        })
    },
    methods: {
        selectCoupon: function (item) {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                couponID: item.couponID,
                buildingID: userInfo.buildingID
            }
            this.postData(_this, "/home/receiveCoupon", data, function (resData) {
                _this.items = resData;
                _this.isOK = true;
                _this.item.couponStatus = "0";
            });
            if (this.isOK) {
                $(event.target).attr("src", "../images/coupon/coupon_s3.png");
                $('#couponModal').modal('show');
                this.isOK = false;
            }
            else {
                $('#couponModal').modal('hide');
            }
        }
    }
})