function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            course: {},
            urlList: {
                list: "courselist.html?id=",
                detail: "coursedetails.html?id=",
                index: "courseindex.html?id=",
                order: "courseorder.html?id="
            },
            Change: true
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染

            this.$nextTick(function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    courseID: getQueryString("id")
                };
                this.getData(_this, '/live/getCourseItemDetail', data, function (resData) {
                    _this.course = resData;
                    _this.init(_this.course.stockNumber, _this.course.isNormal);
                    if (_this.course.isNormal == 0) {
                        toast2("商家休息中，暂时不接受订单")
                    }
                });

            })
        },
        updated: function () {
            var mySwiper = new Swiper('.swiper-container', {
                direction: 'horizontal',
                loop: true,
                speed: 200,
            })

        },
        methods: {
            init: function (number, status) {
                if (status == 1) {
                    if (number > 0) {
                        $("#change_coursedetail > a").text("立即预约");
                        this.Change = true;
                    } else {
                        this.Change = false;
                        $(".business_bottom>div").removeClass().addClass("ser_zbtn2");
                        $("#change_coursedetail > a").text("报名已满");
                    }
                }
                else if (status == 0) {


                    $(".business_bottom>div").removeClass().addClass("ser_zbtn2");
                    $("#change_coursedetail > a").text("立即预约");
                    this.Change = false;

                }
            },
            //收藏
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    });
}