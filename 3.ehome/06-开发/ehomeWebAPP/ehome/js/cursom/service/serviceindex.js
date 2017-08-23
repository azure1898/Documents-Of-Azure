function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "servicelist.html?id=",
                detail: "servicedetail.html?id=",
                index: "serviceindex.html?id=",
                groupbuy: "../groupbuy/groupbuydetail.html?id="
            },
            item1: {},
            groupBuy: [],
            times: 1,
            groupbuytype: 1
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                this.cartView();
            })
        },
        methods: {
            cartView: function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    businessID: getQueryString("id"),
                    buildingID: userInfo.buildingID
                };
                this.getData(_this, "/live/getServiceIndex", data, function (resData) {
                    _this.item1 = resData;
                    _this.groupBuy = _this.item1.groupBuy;
                })
            },
            //更多团购
            getMore: function (business) {
                var _this = this;
                _this.times++
                if (_this.times % 2 == 0) {
                    var data = {
                        userID: userInfo.userID,
                        buildingID: userInfo.buildingID,
                        businessID: getQueryString("id")
                    };
                    this.getData(_this, "/live/getMoreGroupBuy", data, function (resData) {
                        _this.groupBuy = resData;
                    })
                    $(event.target).html("收起");
                } else {
                    var data = {
                        userID: userInfo.userID,
                        businessID: getQueryString("id")
                    };
                    this.getData(_this, "/live/getServiceIndex", data, function (resData) {
                        _this.item1 = resData;
                        _this.groupBuy = _this.item1.groupBuy;
                    });
                    $(event.target).html("查看更多团购");
                }
            },
            //收藏商家
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    })
}