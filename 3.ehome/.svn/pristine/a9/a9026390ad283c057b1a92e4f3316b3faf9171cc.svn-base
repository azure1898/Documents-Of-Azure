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
import com.its.modules.app.bean.BusinessInfoBean;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.bean.ModuleManageBean;
import com.its.modules.app.bean.VillageLineRecomBusiTypeBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.entity.VillageLineRecomBusiTypeDetail;
import com.its.modules.app.entity.VillageLineRecomSpecial;
import com.its.modules.app.entity.VillageLineRecomSpecialDetail;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GroupPurchaseService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.VillageLineRecomBusiTypeService;
import com.its.modules.app.service.VillageLineRecomSpecialDetailService;
import com.its.modules.app.service.VillageLineRecomSpecialService;
import com.its.modules.app.service.VillageLineService;

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
	private ModuleManageService moduleManageService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private VillageLineRecomBusiTypeService villageLineRecomBusiTypeService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private VillageLineRecomSpecialService villageLineRecomSpecialService;

	@Autowired
	private VillageLineRecomSpecialDetailService villageLineRecomSpecialDetailService;

	@Autowired
	private VillageLineService villageLineService;

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
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘产品线不存在");
			return toJson;
		}
		List<ModuleManageBean> moduleManages = moduleManageService.getModuleList(villageLine.getLifeRecomModule(), buildingID);
		if (moduleManages == null || moduleManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (ModuleManageBean moduleManageBean : moduleManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("moduleID", moduleManageBean.getId());
			data.put("modeID", moduleManageBean.getProdType());
			data.put("moduleIcon", ValidateUtil.getImageUrl(moduleManageBean.getModuleIcon(), ValidateUtil.ZERO, request));
			data.put("moduleName", moduleManageBean.getModuleName());
			data.put("moduleUrl", moduleManageBean.getModuleUrl());

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
			data.put("businessID", map.get("businessId"));
			data.put("modeID", map.get("prodType"));
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
				mode.put("modeImage", ValidateUtil.getImageUrl(detail.getPicUrl(), ValidateUtil.ZERO, request));
				mode.put("modeType", detail.getProdType());

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
		List<BusinessInfoBean> businessInfoBeans = businessInfoService.getNormalBusinessList(buildingID);
		if (businessInfoBeans == null || businessInfoBeans.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (BusinessInfoBean businessInfoBean : businessInfoBeans) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", businessInfoBean.getId());
			data.put("modeID", businessInfoBean.getProdType());
			data.put("businessName", businessInfoBean.getBusinessName());
			data.put("businessImage", businessInfoService.formatBusinessPic(businessInfoBean.getBusinessPic(), request));
			data.put("businessLabels", businessInfoService.getBusinessLabelList(businessInfoBean));
			data.put("isFeeActivity", "1".equals(businessInfoBean.getFreeDistributeFlag()) ? 1 : 0);
			data.put("deliveryFee", ValidateUtil.validateDouble(businessInfoBean.getDistributeCharge()));
			if (CommonGlobal.YES.equals(businessInfoBean.getFreeDistributeFlag())) {
				data.put("deliveryFeeActivity", "满" + businessInfoBean.getFreeDistributeMoney() + "元免" + (businessInfoBean.getDistributeCharge() != null ? businessInfoBean.getDistributeCharge() : 0) + "元运费");// 配送费活动内容
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

	/**
	 * 生活首页专题推荐
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getTopic")
	@ResponseBody
	public Map<String, Object> getTopic(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		VillageLineRecomSpecial villageLineRecomSpecial = villageLineRecomSpecialService.getVillageLineRecomSpecial(CommonGlobal.RECOMMEND_LIFE, buildingID);
		if (villageLineRecomSpecial == null) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无专题推荐");
			return toJson;
		}
		List<VillageLineRecomSpecialDetail> villageLinerecomspecialdetails = villageLineRecomSpecialDetailService.getRecomSpecialDetailList(villageLineRecomSpecial.getId());

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("topicName", villageLineRecomSpecial.getSpecialName());

		/* 专题推荐明细开始 */
		List<Map<String, Object>> topicDatas = new ArrayList<Map<String, Object>>();
		for (VillageLineRecomSpecialDetail villageLineRecomSpecialDetail : villageLinerecomspecialdetails) {
			Map<String, Object> topicData = new HashMap<String, Object>();
			if (CommonGlobal.RECOMMEND_TYPE_BUSINESS.equals(villageLineRecomSpecialDetail.getRecomType())) {
				// 推荐商家
				String recomBusinessId = villageLineRecomSpecialDetail.getRecomBusinessId();
				String businessCategoryDictID=villageLineRecomSpecialDetail.getBusinessCategoryDictId();
				if (recomBusinessId != null && businessCategoryDictID!=null) {
					topicData.put("recommendID", recomBusinessId);
					BusinessInfo businessInfo = businessInfoService.get(recomBusinessId);
					topicData.put("recommendName", businessInfo == null ? "" : businessInfo.getBusinessName());
					topicData.put("recommendImage", ValidateUtil.getImageUrl(villageLineRecomSpecialDetail.getPicUrl(), ValidateUtil.ZERO, request));
					topicData.put("recommendType", CommonGlobal.RECOMMEND_TYPE_BUSINESS.equals(villageLineRecomSpecialDetail.getRecomType()) ? 2 : 1);
					//产品类型
					topicData.put("recommendMode", businessInfoService.getProdTypeByCategoryDictId(businessCategoryDictID));
				}
			} else {
				// 推荐模块
				String recomModuleId = villageLineRecomSpecialDetail.getRecomModuleId();
				if (recomModuleId != null) {
					topicData.put("recommendID", recomModuleId);
					ModuleManage moduleManage = moduleManageService.get(recomModuleId);
					topicData.put("recommendName", moduleManage == null ? "" : moduleManage.getModuleName());
					topicData.put("recommendImage", ValidateUtil.getImageUrl(villageLineRecomSpecialDetail.getPicUrl(), ValidateUtil.ZERO, request));
					topicData.put("recommendType", CommonGlobal.RECOMMEND_TYPE_BUSINESS.equals(villageLineRecomSpecialDetail.getRecomType()) ? 2 : 1);
					//产品类型
					topicData.put("recommendMode", moduleManageService.getProdType(recomModuleId));
				}
			}

			topicDatas.add(topicData);
		}
		data.put("topicData", topicDatas);
		/* 专题推荐明细结束 */

		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}