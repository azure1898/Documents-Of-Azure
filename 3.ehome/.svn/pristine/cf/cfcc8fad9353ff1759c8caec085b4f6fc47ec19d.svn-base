var vm = new Vue({
	el:"#app",
	data:{
		weathers:[],
		weather:{},
		//天气
		communityBulletin:{},
		//社区公告
		adSlot:[],
		slot1:{},
		slot2:{},
		//广告位
		recommendModule:{},
		//模块推举
		featuredTopics:{},
		//专题推举
		groupBuy:[],
		//团购推举
		recommendBusiness:[],
		//商家推举
		moreBusiness:[],
		//更多商家
		getCoupon:[],
		//获取优惠卷信息
		receiveCoupon:{},
		//领取优惠卷
		isClass:true
		
	},
	mounted:function(){//页面加载之后自动调用，常用于页面渲染
				this.$nextTick(function(){//在2.0版本中，加mounted的$nextTick方法，才能使用vm
					this.cartView();
				})
			},
	methods:{
		cartView:function(){
			var _this = this;
				_this.$http.get("../data/home/getWeather.json").then(function(res){
				_this.weathers = res.data.data;
				_this.weather=_this.weathers[0];
				});
				_this.$http.get("../data/home/getCommunityBulletin.json").then(function(res){
				_this.communityBulletin = res.data.data
				});
				_this.$http.get("../data/home/getAdSlot.json").then(function(res){
				_this.adSlot = res.data.data;
				if (_this.adSlot[0].adLevel==1) {
					_this.slot1=_this.adSlot[0].adList;
					_this.slot2=_this.adSlot[1].adList;
				} else{
					_this.slot1=_this.adSlot[1].adList;
					_this.slot2=_this.adSlot[0].adList;
				}
				
				});
				_this.$http.get("../data/home/getRecommendModule.json").then(function(res){
				_this.recommendModule = res.data.data
				});
				_this.$http.get("../data/home/getFeaturedTopics.json").then(function(res){
				_this.featuredTopics = res.data.data
				});
				_this.$http.get("../data/home/getGroupBuy.json").then(function(res){
				_this.groupBuy = res.data.data
				});
				_this.$http.get("../data/home/getRecommendBusiness.json").then(function(res){
				_this.recommendBusiness = res.data.data
				});
				_this.$http.get("../data/home/getMoreBusiness.json").then(function(res){
				_this.moreBusiness = res.data.data
				});
				_this.$http.get("../data/home/getCoupon.json").then(function(res){
				_this.getCoupon = res.data.data
				});
				_this.$http.get("../data/home/receiveCoupon.json").then(function(res){
				_this.receiveCoupon = res.data.data
				});
		}
		}
});