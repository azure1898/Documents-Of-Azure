var vm = new Vue({
    el: "#app",
    data: {
        urlList: {
            list: "commoditylist.html?id=",
            detail: "commoditydetail.html?id=",
            index: "businessindex.html?id=",
            order: "commodityorder.html?id=",
            shoppingcarticon: "../../images/shoopping_chart.png",
            business: "../../images/dian.png",
            contactbusiness: "../../images/phone.png"
        },
        commodity: {},
        currentSpecification: {},
        shoppingcart: {
            num: 0,
            totalMoney: 0,
            lessMoney: 0,
            commodities: []
        }
    },
    mounted: function () {//页面加载之后自动调用，常用于页面渲染
        this.$nextTick(function () {//在2.0版本中，加mounted的$nextTick方法，才能使用vm
            var _this = this;

            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                businessID: getQueryString("bid"),
                commodityID: getQueryString("id")
            };

            _this.getData(_this, "/live/getCommodityDetail", data, function (resData) {
                _this.commodity = resData;
                 if(_this.commodity.isNormal==0){
					toast2("商家休息中，暂时不接受订单")
				}
                _this.getShoppingCart();
            });
          })
    },
//  updated:function(){
//  	  $('#myCarousel').carousel({
//              //自动4秒播放
//              interval: 3000,
//          });
//          var myElement = document.getElementById('myCarousel')
//          var hm = new Hammer(myElement);
//          hm.on("swipeleft", function () {
//              $('#myCarousel').carousel('next')
//          })
//          hm.on("swiperight", function () {
//              $('#myCarousel').carousel('prev')
//          })
//
//      
//  },
    methods: {
        
        collection: function (item) {
            var _this = this;
            _this.myCollection(_this,item);
        },
        openMoreSpe: function (commodity) {
            $("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
        },
        changeSpecification: function (specification) {
            $("#moreSpeModal .tanchukuang_fenlei a.active").removeClass("active");
            $(event.target).closest("a").addClass("active");
            this.currentSpecification = specification;
        },
        confirmSpecification: function () {
            this.commodity.originalPrice = this.currentSpecification.specificationPrice;
            this.commodity.discountedPrice = this.currentSpecification.specificationdisPrice;
            this.commodity.commodityNumber = this.currentSpecification.specificationNumber;
            this.commodity.currentSpeID = this.currentSpecification.specificationID;

            $("#moreSpeModal").modal('toggle');
        },
        getShoppingCart: function () {
            var _this = this;
  
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                businessID: getQueryString("bid")
            };

            _this.getData(_this, "/live/getShoppingCart", data, function (resData) {
                _this.shoppingcart.commodities = resData.commodities;
                _this.shoppingcart.totalMoney = resData.totalMoney;
                _this.shoppingcart.num = resData.totalNumber;

                if (_this.commodity.sendMoney > 0) {
                    if (_this.commodity.sendMoney > _this.shoppingcart.totalMoney) {
                        _this.shoppingcart.lessMoney = _this.commodity.sendMoney - _this.shoppingcart.totalMoney;
                    }
                    else {
                        _this.shoppingcart.lessMoney = 0;
                    }
                }
                else {
                    _this.shoppingcart.lessMoney = 0;
                }
            });
        },
        openShoppingCart: function () {
            var _this = this;

            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                businessID: getQueryString("bid")
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
                businessID: getQueryString("bid"),
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
                businessID: getQueryString("bid"),
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
                    })
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
                        businessID: getQueryString("bid")
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

    vm.commodity.specifications.forEach(function (specification, index) {
        if (specification.specificationID == vm.commodity.currentSpeID) {
            $("#moreSpeModal .tanchukuang_fenlei a:eq(" + index + ") > span").click();
        }
    });
});
