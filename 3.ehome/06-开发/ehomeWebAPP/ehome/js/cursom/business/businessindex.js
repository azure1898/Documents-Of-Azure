var vm = new Vue({
    el: "#app",
    data: {
        urlList: {
            list: "commoditylist.html?id=",
            groupbuy: "../groupbuy/groupbuydetail.html?id=",
            phoneicon: "../../images/telphone.png",
            goicon: "../../images/grey_go.png"
        },
        business: {}
    },
    mounted: function () {//页面加载之后自动调用，常用于页面渲染
        this.$nextTick(function () {//在2.0版本中，加mounted的$nextTick方法，才能使用vm
            this.cartView();
        })
    },
    methods: {
        // 渲染页面
        cartView: function () {
            var _this = this;

            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                businessID: getQueryString("id")
            };

            _this.getData(_this, "/live/getBusinessIndex", data, function (resData) {
                _this.business = resData;
            });
        },
        getMore: function (business) {
            var _this = this;

            var data = {
                buildingID: userInfo.buildingID,
                businessID: getQueryString("id")
            };

            _this.getData(_this, "/live/getMoreGroupBuy", data, function (resData) {
                business.groupBuy = resData;
                $(".order_whitebox").hide();
            });
        },
        collection: function (business) {
            var _this = this;

            _this.myCollection(_this, business);
        }
    }
})