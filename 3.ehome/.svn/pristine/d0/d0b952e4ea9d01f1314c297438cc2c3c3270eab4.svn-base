function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "servicelist.html?id=",
                detail: "servicedetail.html?id=",
                index: "serviceindex.html?id="
            },
            lists: []
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
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
                    sort: 1,
                    moduleID: getQueryString("mid")
                };
                this.getData(_this, "/live/getServiceList", data, function (resData) {
                    _this.lists = resData;
                })
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
                this.sort();
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    sort: method,
                    moduleID: getQueryString("mid")
                };
                this.getData(_this, "/live/getServiceList", data, function (resData) {
                    _this.lists = resData;
                })
            }
        }
    });
}