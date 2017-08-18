var vm = new Vue({
    el: "#app",
    data: {
        order: {},
        timePeriod: [],
        way: 1,
        method: "",
        serviceDate: {
            label: "",
            date: "",
            desc: "",
            isImmediate: 1,
            start: "",
            end: ""
        },
        items2: [],
        items3: [],
        couponDiscounted: 0,
        leaveWords: "",
        affirmOrder: {},
//      i: 0,
        urlList: {
            checkicon: "../../images/02.png",
            checkedicon: "../../images/01.png"
        },
        couponID:"",
        isOK:true,
        num:0
    },
    mounted: function () {
        this.$nextTick(function () {
           var _this = this;

            var addressData = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID
            };

            _this.getData(_this, "/live/choiceAddress", addressData, function (resData) {
                resData.forEach(function (address, index) {
                    if (address.address == _this.order.contactAddress) {
                        address.isSelected = 1;
                    }
                    else {
                        address.isSelected = 0;
                    }
                });

                _this.items2 = resData;
            });
            var serviceData = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                businessID: getQueryString("businessID"),
                serviceID: getQueryString("id")
            };

            this.getData(_this, "/live/confirmServiceOrder",serviceData, function (resData) {
                resData.coupons.forEach(function (coupon, index) {
                    coupon.isSelected = 0;
                });
                _this.order = resData;
                _this.items3 = _this.order.coupons;
                _this.way = _this.order.serviceWay
               _this.init(_this.order,_this.items3);
                if (_this.way == 0) {
                    _this.method = "立即上门"
                } else {
                    _this.method = "到店时间"
                }

                if (resData.serviceDate && resData.serviceDate.length > 0) {
                    var delivery = resData.serviceDate[0];
                    var delivery2 = resData.serviceDate[1];

                    _this.serviceDate.date = delivery.date;
                    _this.serviceDate.date = delivery2.date;

                    if (delivery.timePeriod && delivery.timePeriod.length > 0) {
                        _this.serviceDate.isImmediate = delivery.timePeriod[0].isImmediate;
                        _this.serviceDate.start = delivery.timePeriod[0].start;
                        _this.serviceDate.end = delivery.timePeriod[0].end;
                        if (delivery.timePeriod[0].isImmediate == 1) {
                            _this.serviceDate.label = _this.method + "(预计" + delivery.timePeriod[0].end + "到达）";
                        } else {
                            _this.serviceDate.label = "预计" + this.serviceDate.desc + " " + delivery.timePeriod[0].start + '~' + delivery.timePeriod[0].end + "到达";
                        }
                    } else {
                        //							_this.serviceDate.label ="几天已满";
                        _this.serviceDate.isImmediate = delivery2.timePeriod[0].isImmediate;
                        _this.serviceDate.start = delivery2.timePeriod[0].start;
                        _this.serviceDate.end = delivery2.timePeriod[0].end;
                        if (delivery2.timePeriod[0].isImmediate == 1) {
                            //上门
                            _this.serviceDate.label = _this.method + "(预计111明天" + delivery2.timePeriod[0].end + "到达）";
                        } else {
                            //到店
                            _this.serviceDate.label = "预计明天" + _this.serviceDate.desc + " " + delivery2.timePeriod[0].start + '~' + delivery2.timePeriod[0].end + "到达";
                        }
                    }
                }

                _this.timePeriod = resData.serviceDate[0].timePeriod;
                _this.totalMoney = _this.order.servicePrice;
            })
        })
    },
    methods: {
    	init:function(item,coup){
        	var arr=new Arrey();
        	coup.foreach(function(e,i){
        		if(item.totalMoney >= e.couponCondition){
        		arr.push(e);
        	}
           })
        	if(arr.length>0){
        		this.isOK=false;
        		this.num=arr.length;
        	}
        	else{
        		this.isOK=true;
        	}
        },
        changeDelivery: function (delivery) {
            var _this = this;
            n = 0;
            $("#timeModal .business_mainmenu2 li.selected").removeClass("selected");
            $(event.target).closest("li").addClass("selected");

            _this.timePeriod = delivery.timePeriod;
            _this.serviceDate.date = delivery.date;
            _this.serviceDate.desc = delivery.desc;
            console.log(_this.serviceDate.date);
        },
        timeSelected: function (period) {
            var _this = this;
            if (_this.way == 0) {
                _this.method = "上门时间"
            } else {
                _this.method = "到店时间"
            }
            _this.serviceDate.isImmediate = period.isImmediate;
            _this.serviceDate.start = period.start;
            _this.serviceDate.end = period.end;

            if (period.isImmediate == 1) {
                _this.serviceDate.label = _this.method + "（预计" + period.end + "到达）";
            } else {
                _this.serviceDate.label = _this.method + "(预计" + _this.serviceDate.desc + " " + period.start + '~' + period.end + "到达)";
            }

            $("#timeModal").modal('toggle');
        },
        //地址选择
        changeAddress: function (currentAddress) {
            this.items2.forEach(function (address, index) {
                address.isSelected = 0;
            });
            currentAddress.isSelected = 1;
            this.order.contactPerson = currentAddress.contactPerson;
            this.order.contactPhone = currentAddress.contactPhone;
            this.order.contactAddress = currentAddress.address;

            $("#myModal_address").modal('toggle');
        },
        //优惠劵选择
        changeCoupon: function (coupon) {
            this.items3.forEach(function (coupon, index) {
                coupon.isSelected = 0;
            });
            if (coupon) {
                coupon.isSelected = 1;
                this.couponID=coupon.couponID;
                if (coupon.couponType == 0) {
                    this.couponDiscounted = coupon.couponMoney;
                }
                else {
                    this.couponDiscounted = this.order.totalMoney * (100 - coupon.couponMoney) / 100;
                }
            }
            else {
                this.couponDiscounted = 0;
            }
            $("#couponModal").modal('toggle');
        },
        //改变数量
        changeMoney_add: function (order) {
            if (order.serviceNumber < order.stockNumber) {
                order.serviceNumber++;

                if (order.serviceNumber > 1) {
                    $("#num_jian").css("background-image", "url(../../images/service/num_jian2.png)");
                    if (order.serviceNumber == order.stockNumber) {
                        $(".order_tip").css("display", "block");
                    }
                }
            }
            if (order.serviceCharge > 0) {
                order.totalMoney = order.servicePrice * order.serviceNumber + order.serviceCharge;
            }
            else {
                order.totalMoney = order.servicePrice * order.serviceNumber;
            }
        },
        changeMoney_reduce: function (order) {
            if (order.serviceNumber > 1) {
                order.serviceNumber--;

                $(".order_tip").css("display", "none");
                if (order.serviceNumber == 1) {

                    $("#num_jian").css("background-image", "url(../../images/num_jian.png)");

                }
                this.calcMoney(order);
            }
        },
        //生成订单
        affirm: function () {
            //如果为到店服务
            var _this=this;
            if (_this.way == 1) {
                var name = $.trim($(".inut-name").val());
                var phone = $.trim($(".inut-phone").val());
                var reg = /^[\u4e00-\u9fa5]{0,}$/;
                if (name == "") {
                    layer.open({
                        content: '请填写联系人',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else if (!(reg.exec(name))) {
                    layer.open({
                        content: '请填写正确联系人姓名',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else if (phone == "") {
                    layer.open({
                        content: '请填写联系电话',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else if (!(/^1[34578]\d{9}$/.test(phone))) {
                    layer.open({
                        content: '请填写正确联系电话',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else {
                	
                	var orderData={
                        userID: userInfo.userID,
                        buildingID: userInfo.buildingID,
                        businessID: _this.order.businessID,
                        contactPerson: _this.order.contactPerson,
                        contactPhone: _this.order.contactPhone,
                        contactAddress: _this.order.contactAddress,
                        serviceID: _this.order.serviceID,
                        servicePrice: _this.order.servicePrice,
                        serviceNumber: _this.order.serviceNumber,
                        isImmediate: _this.serviceDate.isImmediate,
                        serviceStart: _this.serviceDate.date + " " + _this.serviceDate.start,
                        serviceEnd: _this.serviceDate.date + " " + _this.serviceDate.end,                  
                        leaveMessage: _this.leaveWords,
                        couponID:_this.couponID
                   };
                    this.postData(_this,'/live/submitServiceOrder',orderData,function(resData){
                    	 _this.affirmOrder = resData;
                    })
                }
            } else {
                //如果为上门服务
           
                var orderData={
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: _this.order.businessID,
                    contactPerson: _this.order.contactPerson,
                    contactPhone: _this.order.contactPhone,
                    contactAddress: _this.order.contactAddress,
                    serviceID: _this.order.serviceID,
                    servicePrice: _this.order.servicePrice,
                    serviceNumber: _this.order.serviceNumber,
                    isImmediate: _this.serviceDate.isImmediate,
                    serviceStart: _this.serviceDate.date + _this.serviceDate.start,
                    serviceEnd: _this.serviceDate.date + _this.serviceDate.end,
                    leaveMessage: _this.leaveWords
                };
                this.postData(_this,'/live/submitServiceOrder',orderData,function(resData){
                    	 _this.affirmOrder = resData;
                    })
            }
        }
    }
})