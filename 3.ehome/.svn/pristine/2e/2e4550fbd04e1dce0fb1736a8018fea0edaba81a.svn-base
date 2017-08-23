function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            course: {},
            urlList: {
                list: "courselist.html?id=",
                detail: "coursedetail.html?id=",
                index: "../business/businessindex.html?id=",
                order: "courseorder.html?id=",
                bg: "../../images/top_bg5.jpg"
            },
            item: {}
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    pageIndex: 0
                };
                this.getData(_this, '/live/getCourseItems', data, function (resData) {
                    _this.course = resData;
                    if (_this.course.isNormal == 0) {
                        toast2("商家休息中，暂时不接受订单")
                    }
                })
            })
        },
        methods: {
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    })
}