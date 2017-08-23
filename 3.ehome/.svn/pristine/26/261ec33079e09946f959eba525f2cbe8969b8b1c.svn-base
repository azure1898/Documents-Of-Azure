function dealData() {
    var vm = new Vue({
        el: "#app",
        data: {
            urlList: {
                add: "../images/jia.png",
                dele: "../images/jian.png"
            },
            userApps: [],
            homeApps: [],
            commApps: [],
            status: false
        },
        mounted: function () {
            this.$nextTick(function () {
                var _this = this;
                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID
                }
                this.getData(_this, "/home/getAllApplication", data, function (resData) {
                    resData.forEach(function (allApp, i) {
                        switch (allApp.appType) {
                            case 0:
                                _this.userApps = allApp.apps;
                                break;
                            case 1:
                                _this.homeApps = allApp.apps;
                                break;
                            case 2:
                                _this.commApps = allApp.apps;
                                break;
                        }
                    });
                });
            })
        },
        methods: {
            changeStatus: function () {
                if (!this.status) {
                    this.status = true;
                }
                else {
                    this.status = false;

                    this.submitApp();
                }
            },
            submitApp: function () {
                var _this = this;

                var confirmApp = [];

                _this.userApps.forEach(function (userApp, index) {
                    confirmApp.push({ moduleID: userApp.moduleID, sort: (index + 1) });
                });

                var data = {
                    userID: userInfo.userID,
                    buildingID: userInfo.buildingID,
                    modules: JSON.stringify(confirmApp)
                };

                _this.postData(_this, "/home/editMyApplication", data, function (resData) {
                });
            },
            addApp: function (index, app) {
                if (this.userApps.length < 5) {
                    this.userApps.push(app);

                    if (app.moduleType == 1) {
                        this.commApps.splice(index, 1);
                    }
                    else {
                        this.homeApps.splice(index, 1);
                    }
                }
                else {
                    toast("����Ƽ�5��");
                }
            },
            deleApp: function (index, app) {
                this.userApps.splice(index, 1);

                if (app.moduleType == 1) {
                    this.commApps.push(app);
                }
                else {
                    this.homeApps.push(app);
                }
            }
        }
    })
}