function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                list: "commoditylist.html?id=",
                detail: "commoditydetail.html?id=",
                index: "businessindex.html?id=",
                order: "commodityorder.html?id=",
                shoppingcarticon: "../../images/shoopping_chart.png"
            },
            business: {},
            commodityList: [],
            currentCommodity: {},
            currentSpecification: {},
            shoppingcart: {}
        },
        mounted: function () {//页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () {//在2.0版本中，加mounted的$nextTick方法，才能使用vm
                this.cartView();
            });
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

                _this.getData(_this, "/live/getCommodityCategory", data, function (resData) {

                    if (_this.validateUser()) {
                        _this.business = resData;
                        if (_this.business.isNormal == 0) {
                            toast2("商家休息中，暂时不接受订单")
                        }
                        _this.getShoppingCart();

                        if (resData.commodityCategory && resData.commodityCategory.length > 0) {
                            _this.getCommodityByCategory(resData.commodityCategory[0]);
                        }
                        else {
                            _this.getCommodity();
                        }
                    }

                });
            },
            getCommodity: function () {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    pageIndex: 0
                };

                _this.getData(_this, "/live/getCommoditiesByCategory", data, function (resData) {
                    _this.commodityList = resData;
                });
            },
            getCommodityByCategory: function (category) {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    categoryID: category.categoryID,
                    pageIndex: 0
                };

                _this.getData(_this, "/live/getCommoditiesByCategory", data, function (resData) {
                    _this.commodityList = resData;
                });
            },
            changeCategory: function (category) {
                $("#categoryName").html(category.categoryName);
                $("#menu_category > li.selected").removeClass("selected");
                $(event.target).closest("li").addClass("selected");

                this.getCommodityByCategory(category);
            },
            collection: function (business) {
                var _this = this;

                _this.myCollection(_this, business);
            },
            openMoreSpe: function (commodity) {
                this.currentCommodity = commodity;

                $("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
            },
            changeSpecification: function (specification) {
                //      	if(specification.specificationNumber>0){
                $("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
                $(event.target).closest("a").addClass("active");
                //      	}

                this.currentSpecification = specification;
            },
            confirmSpecification: function () {
                var _this = this;

                _this.commodityList.forEach(function (commodity, index) {
                    if (commodity.commodityID == _this.currentCommodity.commodityID) {
                        commodity.originalPrice = _this.currentSpecification.specificationPrice;
                        commodity.discountedPrice = _this.currentSpecification.specificationdisPrice;
                        commodity.commodityNumber = _this.currentSpecification.specificationNumber;
                        commodity.currentSpeID = _this.currentSpecification.specificationID;
                    }
                });

                $("#moreSpeModal").modal('toggle');
            },
            getShoppingCart: function () {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id")
                };

                _this.getData(_this, "/live/getShoppingCart", data, function (resData) {
                    _this.shoppingcart = resData;
                });
            },
            openShoppingCart: function () {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id")
                };

                _this.getData(_this, "/live/getShoppingCart", data, function (resData) {
                    _this.shoppingcart.commodities = resData.commodities;
                    _this.shoppingcart.totalMoney = resData.totalMoney;
                });

                $(".gwcView").fadeIn("3000");
            },
            closeShoppingCart: function () {
                $(".gwcView").fadeOut("3000");
            },
            addShoppingCart: function (type, commodity) {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    commodityID: commodity.commodityID,
                    specificationID: type == 1 ? commodity.currentSpeID : commodity.specificationID
                };

                _this.postData(_this, "/live/addShoppingCart", data, function (resData) {
                    _this.shoppingcart.totalMoney = resData.totalMoney;
                    _this.shoppingcart.num = resData.commodityNumber;
                    commodity.commodityNumber += 1;

                    if (commodity.isMoreSpe == 1) {
                        commodity.specifications.forEach(function (specification, index) {
                            if (specification.specificationID == commodity.currentSpeID) {
                                specification.specificationNumber = commodity.commodityNumber;
                            }
                        })
                    }

                    if (type == 2) {
                        _this.addCommodityNum(commodity);
                    }

                    _this.getShoppingCart();
                });
            },
            addCommodityNum: function (commodity) {
                var _this = this;

                _this.commodityList.forEach(function (currentCommodity, index) {
                    if (currentCommodity.commodityID == commodity.commodityID) {
                        if (currentCommodity.currentSpeID == commodity.specificationID) {
                            currentCommodity.commodityNumber = commodity.commodityNumber;
                        }

                        if (currentCommodity.isMoreSpe == 1) {
                            currentCommodity.specifications.forEach(function (specification, index) {
                                if (specification.specificationID == commodity.specificationID) {
                                    specification.specificationNumber = commodity.commodityNumber;
                                }
                            });
                        }
                        else {
                            currentCommodity.commodityNumber = commodity.commodityNumber;
                        }
                    }
                });
            },
            reduceShoppingCart: function (type, commodity) {
                var _this = this;

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("id"),
                    commodityID: commodity.commodityID,
                    specificationID: type == 1 ? commodity.currentSpeID : commodity.specificationID
                };

                _this.postData(_this, "/live/reduceShoppingCart", data, function (resData) {
                    _this.shoppingcart.totalMoney = resData.totalMoney;
                    _this.shoppingcart.num = resData.commodityNumber;
                    commodity.commodityNumber -= 1;

                    if (commodity.isMoreSpe == 1) {
                        commodity.specifications.forEach(function (specification, index) {
                            if (specification.specificationID == commodity.currentSpeID) {
                                specification.specificationNumber = commodity.commodityNumber;
                            }
                        });
                    }

                    if (type == 2) {
                        _this.reduceCommodityNum(commodity);
                    }

                    _this.getShoppingCart();
                });
            },
            reduceCommodityNum: function (commodity) {
                var _this = this;

                _this.commodityList.forEach(function (currentCommodity, index) {
                    if (currentCommodity.commodityID == commodity.commodityID) {
                        if (currentCommodity.currentSpeID == commodity.specificationID) {
                            currentCommodity.commodityNumber = commodity.commodityNumber;
                        }

                        if (currentCommodity.isMoreSpe == 1) {
                            currentCommodity.specifications.forEach(function (specification, index) {
                                if (specification.specificationID == commodity.specificationID) {
                                    specification.specificationNumber = commodity.commodityNumber;
                                }
                            });
                        }
                        else {
                            currentCommodity.commodityNumber = commodity.commodityNumber;
                        }
                    }
                });
            },
            clearShoppingCart: function () {
                var _this = this;

                layer.open({
                    content: '确定要清空购物车？',
                    btn: ['确定', '取消'],
                    time: 0, //2秒后自动关闭
                    yes: function (index) {
                        var data = {
                            userID: userInfo.userID,
                            buildingID: userInfo.buildingID,
                            businessID: getQueryString("id")
                        };

                        _this.postData(_this, "/live/emptiedShoppingCart", data, function (resData) {
                            _this.shoppingcart.totalMoney = 0;
                            _this.shoppingcart.num = 0;

                            for (var commodity in _this.commodityList) {
                                _this.commodityList[commodity].commodityNumber = 0;
                            }

                            _this.closeShoppingCart();
                        });

                        layer.close(index);
                    }
                });
            }
        }
    });

    $("#moreSpeModal").on("show.bs.modal", function () {
        vm.currentSpecification = {};

        vm.currentCommodity.specifications.forEach(function (specification, index) {
            if (specification.specificationID == vm.currentCommodity.currentSpeID) {
                $("#moreSpeModal .tanchukuang_fenlei a:eq(" + index + ") > span").click();
            }
        });
    });
}