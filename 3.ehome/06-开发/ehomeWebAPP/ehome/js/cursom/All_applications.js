var vm = new Vue({
    el: "#app",
    data: {
        allApp: [],
        addApp: {},
        deleApp: {},
        n: 0,
        status: true

    },
    mounted: function () {
        this.$nextTick(function () {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID
            }
            this.getData(_this, "/home/getAllApplication", data, function (resData) {
                _this.allApp = resData;

            });
        })
    },
    methods: {
        changeS: function () {
            this.n = this.n + 1;
            if (this.n % 2 == 1) {
                $("#click").text("完成");
                this.status = false;
            }
            else {
                $("#click").text("编辑");
                this.status = true;
            }

        }
        , deleApps: function (item, num) {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                moduleID: item.moduleID
            };
            this.postData(_this, "/home/deleteMyApplication", data, function (resData) {
                _this.deleApp = resData;
            })

            _this.allApp.forEach(function (app, i) {
                //当前点击的要删除的 图标 ，在该集合中删除  判断属于哪一个集合 加到对应集合里
                if (app.appType == 0) {
                    app.apps.splice(num, 1)
                    if (app.s == 1) {

                    }
                    else if (app.s == 2) {

                    }
                }
                //				else if(app.appType == 1){
                //					
                //						app.apps.push(item);
                //					
                //				}
                //				else if(app.appType == 2){					
                //					
                //						app.apps.push(item);
                //					
                //					
                //				}
            });
        }
        , addApps: function (item, num, type) {
            var _this = this;
            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID,
                moduleID: item.moduleID
            };
            this.postData(_this, "/home/addMyApplication", data, function (resData) {
                _this.addApp = resData;
            });

            if (type == 2) {
                _this.allApp.forEach(function (app, i) {
                    if (app.appType == 1) {
                        app.apps.splice(num, 1);
                    }
                })
            }
            if (type == 2) {
                _this.allApp.forEach(function (app, i) {
                    if (app.appType == 1) {
                        app.apps.splice(num, 1);
                    }
                })
            }
            //			_this.allApp.forEach(function(app,i){
            //				if(type == app.appType ){					
            //					app.apps.push(item);
            //				   
            //				}
            //				else if(app.appType == 1){
            //					app.apps.splice(num,1);
            //					app.s=1;
            //					
            //				}
            //				else if(app.appType == 2){
            //					app.apps.splice(num,1);
            //					app.s=2;
            //					 
            //				}
            //			});
            //判断当前点击的  item 属于  哪一个  ，让那一个删除该  item  并添加到 常用里面
        }
    }
})