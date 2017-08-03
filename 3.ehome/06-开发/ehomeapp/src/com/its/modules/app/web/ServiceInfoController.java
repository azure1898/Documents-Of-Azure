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
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;

import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.ServiceInfo;
import com.its.modules.app.entity.SortInfo;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GroupPurchaseService;
import com.its.modules.app.service.MyCollectService;
import com.its.modules.app.service.ServiceInfoService;
import com.its.modules.app.service.SortInfoService;

/**
 * 服务管理Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class ServiceInfoController extends BaseController {

	@Autowired
	private ServiceInfoService serviceInfoService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private SortInfoService sortInfoService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private MyCollectService myCollectService;

	/**
	 * 模块商家列表
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param sort
	 *            排序方式：1->默认排序 2->商家销量排序 3->商家发布时间排序
	 */
	@RequestMapping(value = "getServiceList")
	@ResponseBody
	public Map<String, Object> getServiceList(String userID, String buildingID, String sort, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, sort)) {
			return toJson;
		}
		List<BusinessInfo> businessInfos = businessInfoService.getBusinessList(1, buildingID,
				(StringUtils.isNoneBlank(sort) && StringUtils.isNumeric(sort)) ? Integer.parseInt(sort) : 1);
		if (businessInfos == null || businessInfos.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		int count = 0;
		if (businessInfos.size() == 1) {
			// 判断模块下只有一个商家时：自动显示商家6个服务信息（其他规则不变）
			count = 6;
		} else {
			// 默认显示商家推荐的3个服务，无推荐，依次显示最新发布的3个服务项目
			count = 3;
		}
		for (BusinessInfo businessInfo : businessInfos) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", businessInfo.getId());
			data.put("businessName", businessInfo.getBusinessName());
			data.put("businessImage", businessInfoService.formatBusinessPic(businessInfo.getBusinessPic(), request));
			data.put("isNormal", businessInfoService.isBusinessNormal(businessInfo));
			data.put("businessLabels", businessInfoService.getBusinessLabelList(businessInfo));
			data.put("serviceWay", businessInfo.getServiceModel());
			data.put("serviceCharge", ValidateUtil.validateDouble(businessInfo.getServiceCharge()));

			/* 商家服务项目开始 */
			List<Map<String, Object>> serviceItems = new ArrayList<Map<String, Object>>();
			List<ServiceInfo> serviceInfos = serviceInfoService.getByBusinessId(businessInfo.getId(), count);
			if (serviceInfos != null && serviceInfos.size() != 0) {
				for (ServiceInfo serviceInfo : serviceInfos) {
					Map<String, Object> serviceItem = new HashMap<String, Object>();
					serviceItem.put("ID", serviceInfo.getId());
					serviceItem.put("name", serviceInfoService.getServiceName(serviceInfo));
					serviceItem.put("image", serviceInfoService.getFirstServiceImg(serviceInfo, request));
					serviceItem.put("price", serviceInfoService.getServicePrice(serviceInfo));
					serviceItem.put("unit", businessInfoService.getUnit(serviceInfo.getBaseUnit()));
					serviceItem.put("url", null);

					serviceItems.add(serviceItem);
				}
			}
			data.put("serviceItems", serviceItems);
			/* 商家服务项目结束 */

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
	 * 商家服务分类
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 */
	@RequestMapping(value = "getServiceCategory")
	@ResponseBody
	public Map<String, Object> getServiceCategory(String userID, String buildingID, String businessID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(businessID);
		if (businessInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "商家不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("businessImage", businessInfoService.formatBusinessPic(businessInfo.getBusinessPic(), request));
		data.put("isNormal", businessInfoService.isBusinessNormal(businessInfo));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessID) : 0);
		data.put("businessLabels", businessInfoService.getBusinessLabelList(businessInfo));
		data.put("serviceWay", businessInfo.getServiceModel());
		data.put("serviceCharge", ValidateUtil.validateDouble(businessInfo.getServiceCharge()));
		data.put("businessHours", businessInfo.getBusinessHours());

		/* 服务分类开始 */
		List<Map<String, Object>> serviceCategories = new ArrayList<Map<String, Object>>();
		List<SortInfo> sortInfos = sortInfoService.getSortInfoListOfBusiness(businessID, "1");
		if (sortInfos != null && sortInfos.size() != 0) {
			for (SortInfo sortInfo : sortInfos) {
				Map<String, Object> serviceCategory = new HashMap<String, Object>();
				serviceCategory.put("cagegoryID", sortInfo.getId());
				serviceCategory.put("categoryName", sortInfo.getName());

				serviceCategories.add(serviceCategory);
			}
		}
		data.put("serviceCategory", serviceCategories);
		/* 服务分类结束 */

		data.put("businessUrl", null);
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 商家分类服务列表
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 * @param categoryID
	 *            服务分类ID（可空）
	 */
	@RequestMapping(value = "getServiceItems")
	@ResponseBody
	public Map<String, Object> getServiceItems(String userID, String buildingID, String businessID, String categoryID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		ServiceInfo param = new ServiceInfo();
		param.setBusinessInfoId(businessID);
		param.setSortInfoId(categoryID);
		List<ServiceInfo> serviceInfos = serviceInfoService.getByBusinessIdAndSortInfoId(param);
		if (serviceInfos == null || serviceInfos.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (ServiceInfo serviceInfo : serviceInfos) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("serviceID", serviceInfo.getId());
			data.put("serviceName", serviceInfoService.getServiceName(serviceInfo));
			data.put("serviceImage", serviceInfoService.getFirstServiceImg(serviceInfo, request));
			data.put("originalPrice", ValidateUtil.validateDouble(serviceInfo.getBasePrice()));
			data.put("discountedPrice", ValidateUtil.validateDouble(serviceInfo.getBenefitPrice()));
			data.put("serviceUnit", businessInfoService.getUnit(serviceInfo.getBaseUnit()));
			data.put("url", null);

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 服务详情
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param serviceID
	 *            服务ID（不可空）
	 */
	@RequestMapping(value = "getServiceItemDetail")
	@ResponseBody
	public Map<String, Object> getServiceItemDetail(String userID, String buildingID, String serviceID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, serviceID)) {
			return toJson;
		}
		ServiceInfo serviceInfo = serviceInfoService.get(serviceID);
		if (serviceInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "服务不存在");
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(serviceInfo.getBusinessInfoId());

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("serviceID", serviceInfo.getId());
		data.put("serviceName", serviceInfoService.getServiceName(serviceInfo));
		data.put("serviceImage", serviceInfoService.getImageList(serviceInfo, request));
		data.put("unit", businessInfoService.getUnit(serviceInfo.getBaseUnit()));
		data.put("originalPrice", ValidateUtil.validateDouble(serviceInfo.getBasePrice()));
		data.put("discountedPrice", ValidateUtil.validateDouble(serviceInfo.getBenefitPrice()));
		data.put("stockNumber", ValidateUtil.validateInteger(serviceInfo.getStock()));
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("isNormal", businessInfoService.isBusinessNormal(businessInfo));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, serviceInfo.getBusinessInfoId()) : 0);
		data.put("businessPhone", businessInfo.getPhoneNum());
		data.put("businessUrl", null);
		data.put("serviceDesc", serviceInfo.getContent());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 商家首页
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID（不可空）
	 */
	@RequestMapping(value = "getServiceIndex")
	@ResponseBody
	public Map<String, Object> getServiceIndex(String userID, String buildingID, String businessID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(businessID);
		if (businessInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "商家不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("businessImage", businessInfoService.formatBusinessPic(businessInfo.getBusinessPic(), request));
		data.put("isNormal", businessInfoService.isBusinessNormal(businessInfo));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessInfo.getId()) : 0);
		data.put("businessAddress", businessInfoService.getAddress(businessInfo));
		data.put("businessPhone", businessInfo.getPhoneNum());

		/* 团购活动开始 */
		List<Map<String, Object>> groupBuys = new ArrayList<Map<String, Object>>();
		List<GroupPurchaseBean> groupPurchaseBeans = groupPurchaseService.getBusinessGroupPurchase(businessID);
		if (groupPurchaseBeans != null && groupPurchaseBeans.size() != 0) {
			int count = 0;
			for (GroupPurchaseBean groupPurchaseBean : groupPurchaseBeans) {
				// 只显示两个团购活动
				if (count == 2) {
					break;
				}

				Map<String, Object> groupBuy = new HashMap<String, Object>();
				groupBuy.put("groupBuyID", groupPurchaseBean.getGroupPurchaseTimeId());
				groupBuy.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
				groupBuy.put("groupBuyImage", groupPurchaseService.formatGroupPurchaseImg(groupPurchaseBean.getGroupPurcPic(), request));
				groupBuy.put("groupBuyPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
				groupBuy.put("marketPrice", ValidateUtil.validateDouble(groupPurchaseBean.getMarketMoney()));
				groupBuy.put("soldNum", ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum()));
				groupBuy.put("groupBuyUrl", null);

				groupBuys.add(groupBuy);
				count++;
			}
		}
		data.put("groupBuy", groupBuys);
		/* 团购活动结束 */

		data.put("businessHours", businessInfo.getBusinessHours());
		data.put("businessLabels", businessInfoService.getBusinessLabelList(businessInfo));
		data.put("serviceWay", businessInfo.getServiceModel());
		data.put("serviceCharge", ValidateUtil.validateDouble(businessInfo.getServiceCharge()));
		data.put("businessDesc", businessInfo.getBusinessIntroduce());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}