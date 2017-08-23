function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            items: {},
            urlList: {
                detail: "groupbuydetail.html?id=",
                module: "modulelist.html?id=",
                order: "groupbuyorder.html?id="
            },
            leaveWords: "",
            affirmOrder: {}
        },
        mounted: function () { //页面加载之后自动调用，常用于页面渲染
            this.$nextTick(function () { //在2.0版本中，加mounted的$nextTick方法，才能使用vm
                this.cartView();
            })
        },
        methods: {
            cartView: function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    businessID: getQueryString("businessID"),
                    groupBuyID: getQueryString("id")
                };
                this.getData(_this, "/live/confirmGroupBuyOrder", data, function (resData) {
                    _this.items = resData;
                })
            },
            //增加团购数量
            addNumber: function (items) {
                var _this = this;
                if (items.limitPurchase > 0) {
                    if (items.groupBuyNumber < (items.limitPurchase - items.purchasedNumber)) {
                        items.groupBuyNumber += 1;
                        $(".num_jian").css("background-image", "url(../../images/service/num_jian2.png)");
                        
                        items.totalMoney = items.groupBuyNumber * items.groupBuyPrice
                    }
                } else {
                    items.groupBuyNumber += 1;
                        $(".num_jian").css("background-image", "url(../../images/service/num_jian2.png)");
                    
                    items.totalMoney = items.groupBuyNumber * items.groupBuyPrice
                }
            },
            //减少团购数量
            reduceNumber: function (items) {
                var _this = this;
                if (items.groupBuyNumber > 1) {
                    items.groupBuyNumber -= 1;
                    if(items.groupBuyNumber==1){
                        $(".num_jian").css("background-image", "url(../../images/num_jian.png)");
                }
                    items.totalMoney = items.groupBuyNumber * items.groupBuyPrice
                }
                
            },
            //生成订单
            affirm: function () {
                var phone = $.trim($(".phone").val());
                if (phone == '') {
                    layer.open({
                        content: '请填写手机号',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else if (!(/^1[34578]\d{9}$/.test(phone))) {
                    layer.open({
                        content: '请输入正确手机号',
                        btn: '确定',
                        shadeClose: false,
                    });
                } else {
                    //手机号正确是提交订单
                    var _this = this;
                    var orderData = {
                        userID: userInfo.userID,
                        buildingID: userInfo.buildingID,
                        businessID: getQueryString("businessID"),
                        groupBuyID: getQueryString("id"),
                        contactPhone: _this.items.contactPhone, //用户电话
                        groupBuyNumber: _this.items.groupBuyNumber, //团购数量
                        leaveMessage: _this.leaveWords
                    }
                    this.postData(_this, "/live/submitGroupBuyOrder", orderData, function (resData) {
                        _this.affirmOrder = resData;

                    })

                }
            }
        }
    })
}