function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function toast(content) {
    layer.open({
        content : content,
        skin : 'msg',
        time : 2
    })
}
function toast2(content) {
    layer.open({
        content : content,
        btn : "确定",
        shadeClose : false
    })
}

function getUserInfo(user) {
    alert(user);
    userInfo = JSON.parse(user);
    alert(userInfo);

}

function judge(item, id) {
    if (item == 1) {
        $(id).removeClass().addClass('sc_business2')
    } else {
        $(id).removeClass().addClass('sc_business2_2')
    }
}
// var interfaceUrl = "http://218.28.28.186:9088/ehomeapp/app";
var interfaceUrl = "http://218.28.28.186:9088/ehomeapp/app";

var userInfo = {
    userID : 'ad46667a7c8a4ef9abc777da68783f4c',
    buildingID : '1',
    buildingName : "普罗旺世"
};

Vue.filter("formatDecimal", function(value) {
    var f = parseFloat(value);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(value * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return "¥" + s;
});

Vue.filter("spliceActivity", function(activities) {
    var activityDesc = "";
    if (activities) {
        activities.forEach(function(activity, index) {
            if (index == activities.length - 1) {
                activityDesc += activity.desc;
            } else {
                activityDesc += activity.desc + ',';
            }
        });
    }

    return activityDesc;
});

Vue.prototype.getData = function(vm, url, data, func) {
    vm.$http.get(interfaceUrl + url, data).then(function(response) {
        if (response.data.code == 1000) {
            func(response.data.data);
        } else if (response.data.code == 4000) {
            layer.open({
                content : response.data.message,
                btn : '确定',
                shadeClose : false,
            });
        } else if (response.data.code == 5000) {
            layer.open({
                content : response.data.message,
                btn : '确定',
                shadeClose : false,
            });
        }
    });
};

Vue.prototype.postData = function(vm, url, data, func) {
    vm.$http.post(interfaceUrl + url, data, {
        emulateJSON : true
    }).then(function(response) {
        if (response.data.code == 1000) {
            func(response.data.data);
        } else if (response.data.code == 4000) {
            layer.open({
                content : response.data.message,
                btn : '确定',
                shadeClose : false,
            });
        } else if (response.data.code == 5000) {
            layer.open({
                content : response.data.message,
                btn : '确定',
                shadeClose : false,
            });
        }
    });
};

Vue.prototype.myCollection = function(vm, business) {
    var data = {
        userID : userInfo.userID,
        buildingID : userInfo.buildingID,
        businessID : business.businessID
    };

    if (business.isCollection == 0) {
        vm.postData(vm, "/live/addBusinessCollection", data, function(resData) {
            vm.item = resData;
            business.isCollection = 1;
            toast('收藏成功');
        });
    } else if (business.isCollection == 1) {
        vm.postData(vm, "/live/cancelBusinessCollection", data, function(resData) {
            vm.item = resData;
            business.isCollection = 0;
            toast('取消收藏');
        });
    }
};

Vue.prototype.callPhone = function(phoneCode) {
    var json = {
        "phoneCode" : phoneCode
    };
    window.location.href = "protocol://android?code=callPhone&data=" + JSON.stringify(json);
};
Vue.prototype.orderPay = function(orderID, orderMoney) {
    var json = {
        "orderID" : orderID,
        "orderMoney" : orderMoney
    };
    window.location.href = "protocol://android?code=orderPay&data=" + JSON.stringify(json);
};

Vue.prototype.validateUser = function() {
    if (userInfo.userID && userInfo.userID.length > 0) {
        return true;
        alert("YES");
    } else {
        return false;
        alert("NO");
    }
};

Vue.prototype.goLogin = function() {
};