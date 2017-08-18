var vm = new Vue({
    el: "#app",
    data: {
        topAd: [],
        module: [],
        bulletin: {}
    },
    mounted: function () {
        this.$nextTick(function () {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID
            };
            this.getData(_this, '/home/getAdSlot', data, function (resData) {
                _this.topAd = resData;
            });
            this.getData(_this, '/home/getRecommendModule', data, function (resData) {
                _this.module = resData;
            });
            this.getData(_this, '/community/getHomeBulletin', data, function (resData) {
                _this.bulletin = resData;
            });
        })
    }
})