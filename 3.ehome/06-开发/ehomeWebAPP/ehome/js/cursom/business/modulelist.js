function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "commoditylist.html?id=",
                detail: "commoditydetail.html?id=",
                index: "businessindex.html?id="
            },
            businessList: []
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
                    moduleID: getQueryString("mid"),
                    sort: 1
                };

                _this.getData(_this, "/live/getBusinessList", data, function (resData) {
                    _this.businessList = resData;
                    if (getQueryString("mid") == 01) {
                        $("#topTitle").text("餐饮美食");
                    }
                    else {
                        $("#topTitle").text("社区商城");
                    }
                });
            },
            sort: function () {
                $(".sort_xiala").stop().slideToggle(400);
                $("#bg").stop().fadeToggle(200);
            },
            changeSort: function (method) {
                $(".sort_xiala > ul > li.selected span").remove();
                $(".sort_xiala > ul > li.selected").removeClass("selected");

                $(event.target).append("<span class='green_dh'></span>");
                $(event.target).addClass("selected");

                var _this = this;

                _this.sort();

                var data = {
                    buildingID: userInfo.buildingID,
                    moduleID: getQueryString("mid"),
                    sort: method
                };

                _this.getData(_this, "/live/getBusinessList", data, function (resData) {
                    _this.businessList = resData;
                });
            }
        }
    });
}