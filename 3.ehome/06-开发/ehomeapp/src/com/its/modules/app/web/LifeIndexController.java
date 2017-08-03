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
import com.its.common.web.BaseController;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.bean.VillageLineRecomBusiTypeBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.AdverManage;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.VillageLineRecomBusiTypeDetail;
import com.its.modules.app.service.AdverManageService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GroupPurchaseService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.VillageLineRecomBusiTypeService;

/**
 * 生活首页Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class LifeIndexController extends BaseController {

	@Autowired
	private AdverManageService adverManageService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private VillageLineRecomBusiTypeService villageLineRecomBusiTypeService;

	@Autowired
	private BusinessInfoService businessInfoService;

	/**
	 * 生活首页顶部广告
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getTopAdSlot")
	@ResponseBody
	public Map<String, Object> getTopAdSlot(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<AdverManage> adverManages = adverManageService.getAdverManageByPositionId("固定值");
		if (adverManages == null || adverManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (AdverManage adverManage : adverManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("adID", adverManage.getId());
			data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
			data.put("adUrl", adverManage.getLinkUrl());

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 生活首页模块推荐
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getTopModule")
	@ResponseBody
	public Map<String, Object> getTopModule(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<ModuleManage> moduleManages = moduleManageService.getModuleList(CommonGlobal.MAIN_NAVIGATION_LIFE, buildingID);
		if (moduleManages == null || moduleManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (ModuleManage moduleManage : moduleManages) {
			Map<String, Object> data = new HashMap<String, Object>();
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
	 * 生活首页顶部商家推荐
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getTopBusiness")
	@ResponseBody
	public Map<String, Object> getTopBusiness(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<Map<String, Object>> businessList = moduleManageService.getRecommendBusinessList(CommonGlobal.RECOMMEND_LIFE, buildingID);
		if (businessList == null || businessList.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : businessList) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", map.get("businessID"));
			data.put("businessName", map.get("businessName"));
			data.put("businessDesc", map.get("recommendDes"));
			data.put("businessImage", ValidateUtil.getImageUrl((String) map.get("recommendPic"), ValidateUtil.ZERO, request));
			data.put("businessUrl", null);

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 生活首页团购显示
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getGroupBuy")
	@ResponseBody
	public Map<String, Object> getGroupBuy(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<GroupPurchaseBean> groupPurchaseBeans = groupPurchaseService.getByVillageInfoId(buildingID);
		if (groupPurchaseBeans == null || groupPurchaseBeans.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (GroupPurchaseBean groupPurchaseBean : groupPurchaseBeans) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("groupBuyImage", groupPurchaseService.formatGroupPurchaseImg(groupPurchaseBean.getGroupPurcPic(), request));
			data.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
			data.put("groupBuyPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
			data.put("marketPrice", ValidateUtil.validateDouble(groupPurchaseBean.getMarketMoney()));
			data.put("groupBuyUrl", null);

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 生活首页中部商家推荐
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getMiddleBusiness")
	@ResponseBody
	public Map<String, Object> getMiddleBusiness(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<VillageLineRecomBusiTypeBean> businessList = villageLineRecomBusiTypeService.getRecommendBusinessList(CommonGlobal.RECOMMEND_LIFE, buildingID);
		if (businessList == null || businessList.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (VillageLineRecomBusiTypeBean bean : businessList) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", bean.getBusinessInfo().getId());
			data.put("businessName", bean.getBusinessInfo().getBusinessName());

			/* 产品模式集合开始 */
			List<Map<String, Object>> modes = new ArrayList<Map<String, Object>>();
			for (VillageLineRecomBusiTypeDetail detail : bean.getVillageLineRecomBusiTypeDetails()) {
				Map<String, Object> mode = new HashMap<String, Object>();
				data.put("modeImage", ValidateUtil.getImageUrl(detail.getPicUrl(), ValidateUtil.ZERO, request));
				data.put("modeType", detail.getProdType());

				modes.add(mode);
			}
			/* 产品模式集合结束 */

			data.put("modes", modes);
			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 生活首页底部推荐商家
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getFooterBusiness")
	@ResponseBody
	public Map<String, Object> getFooterBusiness(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<BusinessInfo> businessInfos = businessInfoService.getNormalBusinessList(buildingID);
		if (businessInfos == null || businessInfos.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (BusinessInfo businessInfo : businessInfos) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", businessInfo.getId());
			data.put("businessName", businessInfo.getBusinessName());
			data.put("businessImage", businessInfoService.formatBusinessPic(businessInfo.getBusinessPic(), request));
			data.put("businessLabels", businessInfoService.getBusinessLabelList(businessInfo));
			data.put("isFeeActivity", "1".equals(businessInfo.getFreeDistributeFlag()) ? 1 : 0);
			data.put("deliveryFee", ValidateUtil.validateDouble(businessInfo.getDistributeCharge()));
			if ("1".equals(businessInfo.getFreeDistributeFlag())) {
				data.put("deliveryFeeActivity", "满" + businessInfo.getFreeDistributeMoney() + "元免" + (businessInfo.getDistributeCharge() != null ? businessInfo.getDistributeCharge() : 0) + "元运费");// 配送费活动内容
			}
			data.put("activities", null);
			data.put("businessUrl", null);

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}