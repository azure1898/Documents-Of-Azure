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
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.service.AccountApplicationService;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.OrderGroupPurcService;
import com.its.modules.app.service.OrderTrackService;
import com.its.modules.app.service.RoomCertifyService;
import com.its.modules.app.service.VillageInfoService;
import com.its.modules.app.service.VillageLineService;

/**
 * 模块管理Controller
 * 
 * @author like
 * 
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${appPath}")
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

	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	@Autowired
	private AccountApplicationService accountApplicationService;

	/**
	 * 首页模块推荐
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "home/getRecommendModule")
	@ResponseBody
	public Map<String, Object> getRecommendModule(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘产品线不存在");
			return toJson;
		}
		List<ModuleManage> moduleManages = null;
		List<String> accountApplication = accountApplicationService.getAccountApplicationList(userID, buildingID);
		if (accountApplication == null || accountApplication.size() == 0) {
			// 如果用户没有设置常用应用，获取首页推荐模块数据
			moduleManages = moduleManageService.getModuleManageList(villageLine.getMaintRecomModule());
		} else {
			// 如果用户设置了常用应用，获取用户的常用应用数据
			moduleManages = moduleManageService.getModuleManageList(accountApplication);
		}
		if (moduleManages == null || moduleManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (ModuleManage moduleManage : moduleManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("moduleID", moduleManage.getId());
			data.put("moduleType", CommonGlobal.MODULE_TYPE_COMMUNITY.equals(moduleManage.getModuleType()) ? 2 : 1);
			data.put("moduleIcon", ValidateUtil.getImageUrl(moduleManage.getModuleIcon(), ValidateUtil.ZERO, request));
			data.put("moduleName", moduleManage.getModuleName());
			data.put("moduleUrl", moduleManage.getModuleUrl());

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 社区顶部模块推荐位
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "community/getTopModule")
	@ResponseBody
	public Map<String, Object> getTopModule(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘产品线不存在");
			return toJson;
		}
		List<ModuleManage> moduleManages = moduleManageService.getModuleManageList(villageLine.getCommunityRecomModule());
		if (moduleManages == null || moduleManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (ModuleManage moduleManage : moduleManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("moduleID", moduleManage.getId());
			data.put("moduleIcon", ValidateUtil.getImageUrl(moduleManage.getModuleIcon(), ValidateUtil.ZERO, request));
			data.put("moduleName", moduleManage.getModuleName());
			data.put("moduleUrl", moduleManage.getModuleUrl());

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 获取我的首页信息
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "my/getUserHomePage")
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
		Map<String, Object> rooms = new HashMap<>();
		rooms.put("appName", "房间绑定");
		rooms.put("appCode", AppGlobal.MODULE_ROOM_BINDING);
		myApplications.add(rooms);
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
						order.put("moduleIcon", StringUtils.isNoneBlank(orderModule.getModuleIcon()) ? MyFDFSClientUtils.get_fdfs_file_url(request, orderModule.getModuleIcon()) : "");
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
							Map<String, Object> map = new HashMap<>();
							map.put("orderID", myOrderViewBean.getOrderId());
							map.put("orderType", myOrderViewBean.getOrderType());
							ModuleManage moduleManage = moduleManageService.get(myOrderViewBean.getModuleManageId());
							map.put("orderModuleID", moduleManage == null ? "" : moduleManage.getId());
							map.put("orderModuleName", moduleManage == null ? "" : moduleManage.getModuleName());
							map.put("orderCode", myOrderViewBean.getOrderNo());
							map.put("orderTime", myOrderViewBean.getCreateDate());
							map.put("orderMoney", ValidateUtil.validateDouble(myOrderViewBean.getPayMoney()));
							// 团购订单状态额外处理
							if (myOrderViewBean.getOrderType() == 5) {
								OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(myOrderViewBean.getOrderId());
								map.put("orderStatus", orderGroupPurc == null ? "" : orderGroupPurcService.getOrderGroupPurStatus(orderGroupPurc));
							} else {
								OrderTrack orderTrack = orderTrackService.getRecentOrderStatus(myOrderViewBean.getOrderId());
								map.put("orderStatus", orderTrack == null ? "" : orderTrack.getStateMsgPhone());
							}
							map.put("orderUrl", null);
							orders.add(map);
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
		String lifeModule = villageLine.getLifeModule();// 生活模块
		if (StringUtils.isNoneBlank(lifeModule)) {
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
					circle.put("moduleIcon", StringUtils.isNotBlank(module.getModuleIcon()) ? MyFDFSClientUtils.get_fdfs_file_url(request, module.getModuleIcon()) : "");
					circle.put("moduleUrl", StringUtils.value(module.getModuleUrl()));
					circle.put("moduleType", 3);
					List<Map<String, Object>> circles = new ArrayList<>();
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
					circle.put("circles", circles);
					modules.add(circle);
				}
			}
		}
		String communityRecomModule = villageLine.getCommunityRecomModule();
		if (StringUtils.isNoneBlank(communityRecomModule)) {
			Map<String, Object> service = new HashMap<>();
			service.put("appName", "社区服务");
			service.put("appCode", AppGlobal.MODULE_VILLAGE_SERVICE);
			myApplications.add(service);
		}
		String communityModule = villageLine.getCommunityModule();// 社区模块
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
					bracelet.put("moduleIcon", StringUtils.isNotBlank(module.getModuleIcon()) ? MyFDFSClientUtils.get_fdfs_file_url(request, module.getModuleIcon()) : "");
					bracelet.put("moduleUrl", StringUtils.value(module.getModuleUrl()));
					bracelet.put("moduleType", 2);
					modules.add(bracelet);
				} else if (AppGlobal.MODULE_COMPLAINT.equals(module.getPhoneModuleCode()) || AppGlobal.MODULE_REPAIRS.equals(module.getPhoneModuleCode()) || AppGlobal.MODULE_PHONE_OPEN_DOOR.equals(module.getPhoneModuleCode())) {// 我的投诉、我的报修、访客邀请
					Map<String, Object> complain = new HashMap<>();
					complain.put("appName", StringUtils.value(module.getModuleName()));
					complain.put("appCode", module.getPhoneModuleCode());
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