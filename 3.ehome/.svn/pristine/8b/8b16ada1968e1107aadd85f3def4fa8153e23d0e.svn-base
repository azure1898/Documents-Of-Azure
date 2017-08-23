var vm = new Vue({
    el: "#app",
    data: {
        topAd: [],
        topModule: [],
        topBusiness: [],
        groupBuy: [],
        middleBusiness: [],
        footerBusiness: [],
        topic: {},
        topicClass: 1,
        urlList: {
            business: "../business/modulelist.html?mid=",
            course: "../course/modulelist.html?mid=",
            groupbuy: "../groupbuy/modulelist.html?mid=",
            site: "../site/modulelist.html?id=",
            service: "../service/modulelist.html?mid=",
            businessIndex: "../business/businessindex.html?id=",
            businessDetail: "../business/commoditydetail.html?id=",
            courseDetail: "../course/coursedetail.html?id=",
            serviceDetail: "../service/servicedetail.html?id=",              
            businessList: "../business/commoditylist.html?id=",
            courseList: "../course/courselist.html?id=",
            serviceList: "../service/servicelist.html?id="
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            var _this = this;

            var data = {
                userID: userInfo.userID,
                buildingID: userInfo.buildingID
            };

            this.getData(_this, '/live/getTopAdSlot', data, function (resData) {
                _this.topAd = resData;
            });

            this.getData(_this, '/live/getTopModule', data, function (resData) {
                _this.topModule = resData;
            });

            this.getData(_this, '/live/getTopBusiness', data, function (resData) {
                _this.topBusiness = resData;
            });

            this.getData(_this, '/live/getGroupBuy', data, function (resData) {
                _this.groupBuy = resData;
            });

            this.getData(_this, '/live/getMiddleBusiness', data, function (resData) {
                _this.middleBusiness = resData;
            });

            this.getData(_this, '/live/getFooterBusiness', data, function (resData) {
                _this.footerBusiness = resData;
            });

            this.getData(_this, '/live/getTopic', data, function (resData) {
                _this.topic = resData;

                if (_this.topic.topicData) {
                    switch (_this.topic.topicData.length) {
                        case 2:
                            _this.topicClass = 1;
                            break;
                        case 3:
                            _this.topicClass = 2;
                            break;
                        case 4:
                            _this.topicClass = 1;
                            break;
                        case 5:
                            _this.topicClass = 3;
                            break;
                        case 6:
                            _this.topicClass = 2;
                            break;
                    }
                }
            });
        });
    },
    updated: function () {
      
        var mySwiper = new Swiper('.swiper-container_group', {
            direction: 'vertical',
            speed: 500,
            loop: true,
            height: 70,
            autoplay: 5000,

        });
        var mySwiper = new Swiper('.swiper-container_topAd', {
            direction: 'horizontal',
            speed: 500,
            loop: true,
            height: 90,
            autoplay: 5000,

        });
         var mySwiper = new Swiper('.swiper-middle', {
            direction: 'horizontal',
            speed: 500,
            loop: true,
            height:205,
           

        });

    },
    methods: {
        topAdClick: function (ad) {
            var _this = this;

            switch (ad.adType) {
                case "0":
                    break;
                case "1":
                    break;
                case "2":
                    _this.moduleAdClick(ad);
                    break;
                case "3":
                    _this.businessAdClick(ad);
                    break;
                case "4":
                    _this.productAdClick(ad);
                    break;
            }
        },
        moduleAdClick: function (ad) {
            switch (ad.productMode) {
                case "0":
                    location.href = this.urlList.business + ad.moduleID;
                    break;
                case "1":
                    location.href = this.urlList.service + ad.moduleID;
                    break;
                case "2":
                    location.href = this.urlList.course + ad.moduleID;
                    break;
            }
        },
        businessAdClick: function (ad) {
            location.href = this.urlList.businessIndex + ad.businessID;
        },
        productAdClick: function (ad) {
            switch (ad.productMode) {
                case "0":
                    location.href = this.urlList.businessDetail + ad.productID + '&bid=' + ad.businessID;
                    break;
                case "1":
                    location.href = this.urlList.serviceDetail + ad.productID;
                    break;
                case "2":
                    location.href = this.urlList.courseDetail + ad.productID;
                    break;
                case "3":
                    location.href = this.urlList.site + ad.businessID;
                    break;
            }
        },
        moduleClick_top: function (module) {
            var _this = this;
            switch (module.moduleID) {
                case "01":
                    location.href = "#";
                    break;
                case "02":
                    location.href = _this.urlList.business + module.moduleID;
                    break;
                case "03":
                    location.href = _this.urlList.service + module.moduleID;
                    break;
                case "04":
                    location.href = _this.urlList.course + module.moduleID;
                    break;
            }
        },
        moduleClick: function (module) {
            var _this = this;

            switch (module.modeID) {
                case "0":
                    location.href = _this.urlList.business + module.moduleID;
                    break;
                case "1":
                    location.href = _this.urlList.service + module.moduleID;
                    break;
                case "2":
                    location.href = _this.urlList.course + module.moduleID;
                    break;
                case "3":
                    location.href = _this.urlList.site + module.moduleID;
                    break;
            }
        },
        topBusinessClick: function (business) {
            location.href = this.urlList.businessIndex + business.businessID;
        },
        topicClick: function (topic) {
            if (topic.recommendType == 1) {
            }
            else {
                location.href = this.urlList.businessIndex + topic.recommendID;
            }
        },
        middleBusinessClick: function (businessID, mode) {
            var _this = this;

            switch (mode.modeType) {
                case "0":
                    location.href = _this.urlList.businessList +businessID;
                    break;
                case "1":
                    location.href = _this.urlList.serviceList + businessID;
                    break;
                case "2":
                    location.href = _this.urlList.courseList + businessID;
                    break;
                case "3":
                    location.href = this.urlList.site + businessID;
                    break;
            }
        },
        footBusinessClick: function (business) {
            location.href = this.urlList.businessIndex + business.businessID;
        }
    }
})