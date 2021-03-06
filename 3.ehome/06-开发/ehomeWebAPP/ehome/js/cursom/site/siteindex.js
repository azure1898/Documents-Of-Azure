function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "modulelist.html?id=",
                order: "siteorder.html?id=",
                index: "../business/businessindex.html?id=",
                groupbuy: "../groupbuy/groupbuydetail.html?id=",
                phoneicon: "../../images/telphone.png",
                goicon: "../../images/grey_go.png"
            },
            site: {},
            n: 0
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id")
                };
                this.getData(_this, "/live/getSiteIndex", data, function (resData) {
                    _this.site = resData;
                })
            })
        },
        methods: {
            openMore: function () {
                var _this = this;
                _this.n++;
                if (_this.n % 2 == 1) {
                    var data = {
                        userID: userInfo.userID,
                        buildingID: userInfo.buildingID,
                        businessID: getQueryString("id")
                    };
                    this.getData(_this, "/live/getMoreGroupBuy", data, function (resData) {
                        _this.site.groupBuy = resData;
                    })
                    $(event.target).html("收起");
                } else if (_this.n % 2 == 0) {
                    var data = {
                        userID: userInfo.userID,
                        businessID: getQueryString("id"),
                        buildingID: userInfo.buildingID
                    };
                    this.getData(_this, "/live/getSiteIndex", data, function (resData) {
                        _this.site = resData;
                    })
                    $(event.target).html("查看更多团购");
                }
            },
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    });
}