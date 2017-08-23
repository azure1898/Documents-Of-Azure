function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            course: {},
            length: 0,
            urlList: {
                list: "courselist.html?id=",
                detail: "coursedetails.html?id=",
                index: "courseindex.html?id=",
                order: "courseorder.html?id=",
                groupdetail: "../groupbuy/groupbuydetail.html?id="
            },
            n: 0
        },
        mounted: function () {
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    businessID: getQueryString("id"),
                    buildingID: userInfo.buildingID
                };
                this.getData(_this, '/live/getCourseIndex', data, function (resData) {
                    _this.course = resData;
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
                        _this.course.groupBuy = resData
                    })
                    $(event.target).html("收起");
                } else if (_this.n % 2 == 0) {
                    var data = {
                        userID: userInfo.userID,
                        businessID: getQueryString("id"),
                        buildingID: userInfo.buildingID
                    };
                    this.getData(_this, "/live/getCourseIndex", data, function (resData) {
                        _this.course = resData;
                    })
                    $(event.target).html("查看更多团购");
                }
            },
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    })
}