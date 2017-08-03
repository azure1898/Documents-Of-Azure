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
import com.its.modules.app.bean.MyOrderBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.MyOrderService;
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
	private MyOrderService myOrderService;
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
			if(rooms.size() > 0){
				data.put("userIdentity", rooms.get(0).getCustomerType());
			}else{
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
					Map<String, Object> order = new HashMap<>();
					order.put("moduleName", "我的订单");
					order.put("moduleIcon", "");
					order.put("moduleUrl", "");
					order.put("moduleType", 1);
					List<Map<String, Object>> orders = new ArrayList<>();
					if (account != null) {
						List<MyOrderBean> beanList = myOrderService.getMyOrderBean(userID, buildingID, "0", request);
						for (int k = 0; k < beanList.size(); k++) {
							if (k == 2) {
								break;
							}
							MyOrderBean bean = beanList.get(k);
							Map<String, Object> m = new HashMap<>();
							m.put("orderID", bean.getOrderId());
							m.put("orderType", bean.getOrderType());
							// m.put("orderCode", bean.get);
							m.put("orderTime", bean.getCreateDate());
							m.put("orderMoney", bean.getOrderMoney());
							m.put("orderStatus", bean.getOrderStatus());
							orders.add(m);
						}
					}
					order.put("orders", orders);
					modules.add(order);
					// 我的购物车
					Map<String, Object> cart = new HashMap<>();
					cart.put("appName", "我的购物车");
					myApplications.add(cart);
					// 商家收藏
					Map<String, Object> collect = new HashMap<>();
					collect.put("appName", "商家收藏");
					myApplications.add(collect);
				}
			}
		}
		String communityModule = villageLine.getCommunityModule();
		if (StringUtils.isNoneBlank(communityModule)) {
			String[] community = communityModule.split(",");
			for (int i = 0; i < community.length; i++) {
				if (StringUtils.isBlank(community[i]))
					continue;
				ModuleManage module = moduleManageService.get(community[i]);
				if (AppGlobal.MODULE_BRACELET.equals(community[i])) {// 致加手环
					Map<String, Object> bracelet = new HashMap<>();
					bracelet.put("moduleName", module != null ? module.getModuleName() : "致加手环");
					bracelet.put("moduleIcon", (module != null && StringUtils.isNotBlank(module.getModuleIcon()))
							? MyFDFSClientUtils.get_fdfs_file_url(request, module.getModuleIcon()) : "");
					bracelet.put("moduleUrl", "");
					bracelet.put("moduleType", 2);
					List<Map<String, Object>> bands = new ArrayList<>();
//					Map<String, Object> m1 = new HashMap<>();
//					m1.put("bandName", "致加手环");
//					m1.put("bandIcon", "");
//					m1.put("bandUrl", "");
//					bands.add(m1);
					Map<String, Object> m2 = new HashMap<>();
					m2.put("bandName", "运动成绩");
					m2.put("bandIcon", "");
					m2.put("bandUrl", "");
					bands.add(m2);
					Map<String, Object> m3 = new HashMap<>();
					m3.put("bandName", "睡眠结果");
					m3.put("bandIcon", "");
					m3.put("bandUrl", "");
					bands.add(m3);
					Map<String, Object> m4 = new HashMap<>();
					m4.put("bandName", "羊城通");
					m4.put("bandIcon", "");
					m4.put("bandUrl", "");
					bands.add(m4);
					bracelet.put("bands", bands);
					modules.add(bracelet);
				} else if (AppGlobal.MODULE_COMPLAINT.equals(community[i])) {// 我的投诉
					Map<String, Object> complain = new HashMap<>();
					complain.put("appName", module != null ? module.getModuleName() : "我的投诉");
					// complain.put("moduleIcon", (module != null &&
					// StringUtils.isNotBlank(module.getModuleIcon()))
					// ? MyFDFSClientUtils.get_fdfs_file_url(request,
					// module.getModuleIcon()) : "");
					// complain.put("moduleUrl", "");
					// complain.put("moduleType", 0);
					myApplications.add(complain);
				} else if (AppGlobal.MODULE_REPAIRS.equals(community[i])) {// 我的报修
					Map<String, Object> repair = new HashMap<>();
					repair.put("appName", module != null ? module.getModuleName() : "我的报修");
					// repair.put("moduleIcon", (module != null &&
					// StringUtils.isNotBlank(module.getModuleIcon()))
					// ? MyFDFSClientUtils.get_fdfs_file_url(request,
					// module.getModuleIcon()) : "");
					// repair.put("moduleUrl", "");
					// repair.put("moduleType", 0);
					myApplications.add(repair);
				} else if (AppGlobal.MODULE_PHONE_OPEN_DOOR.equals(community[i])) {// 访客邀请
					Map<String, Object> invite = new HashMap<>();
					invite.put("appName", module != null ? module.getModuleName() : "访客邀请");
					// invite.put("moduleIcon", (module != null &&
					// StringUtils.isNotBlank(module.getModuleIcon()))
					// ? MyFDFSClientUtils.get_fdfs_file_url(request,
					// module.getModuleIcon()) : "");
					// invite.put("moduleUrl", "");
					// invite.put("moduleType", 0);
					myApplications.add(invite);
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