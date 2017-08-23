function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            course: [],
            urlList: {
                list: "courselist.html?id=",
                detail: "coursedetails.html?id=",
                index: "courseindex.html?id=",
                order: "courseorder.html?id="
            }
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    sort: 1,
                    moduleID: getQueryString("mid")
                };
                this.getData(_this, '/live/getCourseList', data, function (resData) {
                    _this.course = resData;
                })
            })
        },
        methods: {
            // 渲染页面
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
                var courseData = {
                    buildingID: userInfo.buildingID,
                    sort: method,
                    moduleID: getQueryString("mid")
                };
                this.getData(_this, "/live/getCourseList", courseData, function (resData) {
                    _this.course = resData;
                })
            }
        }
    });
}