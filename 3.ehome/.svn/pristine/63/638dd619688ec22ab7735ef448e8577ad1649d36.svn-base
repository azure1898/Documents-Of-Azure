package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;

import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.OrderTrackService;
import com.its.modules.app.service.RoomCertifyService;
import com.its.modules.app.service.VillageInfoService;
import com.its.modules.app.service.VillageLineService;

/**
 * 模块管理Controller
 * 
 * @author like
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class ModuleManageController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private CouponManageService couponManageService;
	@Autowired
	private ModuleManageService moduleManageService;
	@Autowired
	private OrderTrackService orderTrackService;
	@Autowired
	private RoomCertifyService roomCertifyService;
	@Autowired
	private VillageInfoService villageInfoService;
	@Autowired
	private VillageLineService villageLineService;

	/**
	 * 
	 * @param userID
	 * @param buildingID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getUserHomePage")
	@ResponseBody
	public Map<String, Object> getUserHomePage(String userID, String buildingID, HttpServletRequest request) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		VillageInfo village = villageInfoService.get(buildingID);
		if (village == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘不存在");
		}
		Map<String, Object> data = new HashMap<>();
		Account account = accountService.get(userID);
		if (account != null) {
			data.put("userName", account.getPhoneNum());
			data.put("userImage", MyFDFSClientUtils.get_fdfs_file_url(request, account.getPhoto()));
			data.put("nickname", account.getNickname());
			List<RoomCertify> rooms = roomCertifyService.getAccountRoomCertify(account.getId(), account.getVillageInfoId());
			if (rooms.size() > 0) {
				data.put("userIdentity", rooms.get(0).getCustomerType());
			} else {
				data.put("userIdentity", 0);
			}
			data.put("myWallet", account.getWalletBalance() != null ? account.getWalletBalance() : 0);
			data.put("myCoupon", couponManageService.getValidCoupons(buildingID, userID).size());
		} else {
			data.put("userName", "");
			data.put("userImage", "");
			data.put("nickname", "");
			data.put("userIdentity", 0);
			data.put("myWallet", 0);
			data.put("myCoupon", 0);
		}
		List<Map<String, Object>> modules = new ArrayList<>();
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			data.put("modules", modules);
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("data", data);
			toJson.put("message", "信息已获取");
			return toJson;
		}
		List<Map<String, Object>> myApplications = new ArrayList<>();
		String mainavigation = villageLine.getMainNavigation();
		if (StringUtils.isNoneBlank(mainavigation)) {
			String[] main = mainavigation.split(",");
			for (int i = 0; i < main.length; i++) {
				if (StringUtils.isNoneBlank(main[i]) && AppGlobal.MODULE_LIVE.equals(main[i])) {// 有生活模块
					// 我的订单
					ModuleManage orderModule = moduleManageService.getModuleByPhoneCode(AppGlobal.MODULE_MY_ORDER);
					Map<String, Object> order = new HashMap<>();
					if (orderModule != null) {
						order.put("moduleName", StringUtils.value(orderModule.getModuleName()));
						order.put("moduleIcon", StringUtils.isNoneBlank(orderModule.getModuleIcon())
								? MyFDFSClientUtils.get_fdfs_file_url(request, orderModule.getModuleIcon()) : "");
						order.put("moduleUrl", StringUtils.value(orderModule.getModuleUrl()));
					} else {
						order.put("moduleName", "我的订单");
						order.put("moduleIcon", "");
						order.put("moduleUrl", "");
					}
					order.put("moduleType", 1);
					List<Map<String, Object>> orders = new ArrayList<>();
					if (account != null) {
						List<MyOrderViewBean> myOrderViewBeans = orderTrackService.getRecentMyOrderView(buildingID, userID);
						for (MyOrderViewBean myOrderViewBean : myOrderViewBeans) {
							Map<String, Object> m = new HashMap<>();
							m.put("orderID", myOrderViewBean.getOrderId());
							m.put("orderType", myOrderViewBean.getOrderType());
							m.put("orderCode", myOrderViewBean.getOrderNo());
							m.put("orderTime", myOrderViewBean.getCreateDate());
							m.put("orderMoney", ValidateUtil.validateDouble(myOrderViewBean.getPayMoney()));
							OrderTrack orderTrack = orderTrackService.getRecentOrderStatus(myOrderViewBean.getOrderId());
							m.put("orderStatus", orderTrack == null ? "" : orderTrack.getStateMsgPhone());
							orders.add(m);
						}
					}
					order.put("orders", orders);
					modules.add(order);
					// 我的购物车
					ModuleManage cartModule = moduleManageService.getModuleByPhoneCode(AppGlobal.MODULE_MY_CART);
					Map<String, Object> cart = new HashMap<>();
					if (cartModule != null) {
						cart.put("appName", StringUtils.value(cartModule.getModuleName()));
					} else {
						cart.put("appName", "我的购物车");
					}
					cart.put("appCode", AppGlobal.MODULE_MY_CART);
					myApplications.add(cart);
					// 商家收藏
					ModuleManage collectModule = moduleManageService.getModuleByPhoneCode(AppGlobal.MODULE_MY_COLLECT);
					Map<String, Object> collect = new HashMap<>();
					if (collectModule != null) {
						collect.put("appName", StringUtils.value(collectModule.getModuleName()));
					} else {
						collect.put("appName", "商家收藏");
					}
					collect.put("appCode", AppGlobal.MODULE_MY_COLLECT);
					myApplications.add(collect);
					break;
				}
			}
		}
		String lifeModule = villageLine.getLifeModule();//生活模块
		if(StringUtils.isNoneBlank(lifeModule)){
			String[] lifes = lifeModule.split(",");
			for (int i = 0; i < lifes.length; i++) {
				if (StringUtils.isBlank(lifes[i]))
					continue;
				ModuleManage module = moduleManageService.get(lifes[i]);
				if (module == null) {
					continue;
				}
				if (AppGlobal.MODULE_NEIGHBORHOOD.equals(module.getPhoneModuleCode())) {// 邻里圈
					Map<String, Object> circle = new HashMap<>();
					circle.put("moduleName", StringUtils.value(module.getModuleName()));
					circle.put("moduleIcon",
							StringUtils.isNotBlank(module.getModuleIcon()) ? MyFDFSClientUtils.get_fdfs_file_url(request, module.getModuleIcon()) : "");
					circle.put("moduleUrl", StringUtils.value(module.getModuleUrl()));
					circle.put("moduleType", 3);
					List<Map<String,Object>> circles = new ArrayList<>();
					Map<String, Object> m1 = new HashMap<>();
					m1.put("circleName", "粉丝");
					m1.put("circleNumber", 0);
					m1.put("circleUrl", "");
					circles.add(m1);
					Map<String, Object> m2 = new HashMap<>();
					m2.put("circleName", "关注");
					m2.put("circleNumber", 0);
					m2.put("circleUrl", "");
					circles.add(m2);
					Map<String, Object> m3 = new HashMap<>();
					m3.put("circleName", "点赞");
					m3.put("circleNumber", 0);
					m3.put("circleUrl", "");
					circles.add(m3);
					Map<String, Object> m4 = new HashMap<>();
					m4.put("circleName", "发言");
					m4.put("circleNumber", 0);
					m4.put("circleUrl", "");
					circles.add(m4);
					modules.add(circle);
				}
			}
		}
		String communityModule = villageLine.getCommunityModule();//社区模块
		if (StringUtils.isNoneBlank(communityModule)) {
			String[] community = communityModule.split(",");
			for (int i = 0; i < community.length; i++) {
				if (StringUtils.isBlank(community[i]))
					continue;
				ModuleManage module = moduleManageService.get(community[i]);
				if (module == null) {
					continue;
				}
				if (AppGlobal.MODULE_BRACELET.equals(module.getPhoneModuleCode())) {// 致加手环
					Map<String, Object> bracelet = new HashMap<>();
					bracelet.put("moduleName", StringUtils.value(module.getModuleName()));
					bracelet.put("moduleIcon",
							StringUtils.isNotBlank(module.getModuleIcon()) ? MyFDFSClientUtils.get_fdfs_file_url(request, module.getModuleIcon()) : "");
					bracelet.put("moduleUrl", StringUtils.value(module.getModuleUrl()));
					bracelet.put("moduleType", 2);
					// List<Map<String, Object>> bands = new ArrayList<>();
					// // Map<String, Object> m1 = new HashMap<>();
					// // m1.put("bandName", "致加手环");
					// // m1.put("bandIcon", "");
					// // m1.put("bandUrl", "");
					// // bands.add(m1);
					// Map<String, Object> m2 = new HashMap<>();
					// m2.put("bandName", "运动成绩");
					// m2.put("bandIcon", "");
					// m2.put("bandUrl", "");
					// bands.add(m2);
					// Map<String, Object> m3 = new HashMap<>();
					// m3.put("bandName", "睡眠结果");
					// m3.put("bandIcon", "");
					// m3.put("bandUrl", "");
					// bands.add(m3);
					// Map<String, Object> m4 = new HashMap<>();
					// m4.put("bandName", "羊城通");
					// m4.put("bandIcon", "");
					// m4.put("bandUrl", "");
					// bands.add(m4);
					// bracelet.put("bands", bands);
					modules.add(bracelet);
				} else if (AppGlobal.MODULE_COMPLAINT.equals(module.getPhoneModuleCode()) || AppGlobal.MODULE_REPAIRS.equals(module.getPhoneModuleCode())
						|| AppGlobal.MODULE_PHONE_OPEN_DOOR.equals(module.getPhoneModuleCode())) {// 我的投诉、我的报修、访客邀请
					Map<String, Object> complain = new HashMap<>();
					complain.put("appName", StringUtils.value(module.getModuleName()));
					complain.put("appCode", module.getPhoneModuleCode());
					// complain.put("moduleIcon", (module != null &&
					// StringUtils.isNotBlank(module.getModuleIcon()))
					// ? MyFDFSClientUtils.get_fdfs_file_url(request,
					// module.getModuleIcon()) : "");
					// complain.put("moduleUrl", "");
					// complain.put("moduleType", 0);
					myApplications.add(complain);
				}
			}
		}
		Map<String, Object> app = new HashMap<>();
		app.put("moduleName", "我的应用");
		app.put("moduleIcon", "");
		app.put("moduleUrl", "");
		app.put("moduleType", 0);
		app.put("myApplications", myApplications);
		modules.add(app);
		data.put("modules", modules);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}