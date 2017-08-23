function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "servicelist.html?id=",
                detail: "servicedetail.html?id=",
                index: "../business/businessindex.html?id=",
                bg: "../../images/top_bg3.jpg"
            },
            business: {},
            serviceLists: [],
            item: {}
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id")
                };
                this.getData(_this, "/live/getServiceCategory", data, function (resData) {
                    _this.business = resData;
                    if (_this.business.isNormal == 0) {
                        toast2("商家休息中，暂时不接受订单")
                    }
                    if (_this.business.serviceCategory && _this.business.serviceCategory.length > 0) {
                        var data = {
                            userID: userInfo.userID,
                            buildingID: userInfo.buildingID,
                            businessID: getQueryString("id"),
                            categoryID: _this.business.serviceCategory[0].cagegoryID,
                            pageIndex: 0
                        };
                        _this.getData(_this, "/live/getServiceItems", data, function (resData) {
                            _this.serviceLists = resData;


                        })
                    } else {
                        var data = {
                            userID: userInfo.userID,
                            buildingID: userInfo.buildingID,
                            businessID: getQueryString("id"),
                            pageIndex: 0
                        }
                        _this.getData(_this, "/live/getServiceItems", data, function (resData) {
                            _this.serviceLists = resData;
                        })

                    }
                })
            });
        },
        methods: {
            //改变分类
            changeCategory: function (category) {
                var _this = this;
                $("#categoryName").html(category.categoryName);
                $("#menu_category > li.selected").removeClass("selected");
                $(event.target).closest("li").addClass("selected");
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    categoryID: category.cagegoryID
                };
                this.getData(_this, "/live/getServiceItems", data, function (resData) {
                    _this.serviceLists = resData;
                })
            },
            collection: function (item) {
                var _this = this;
                _this.myCollection(_this, item);
            }
        }
    });
}