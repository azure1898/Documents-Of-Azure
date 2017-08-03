package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.GroupPurchase;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GroupPurchaseService;

/**
 * 团购管理Controller
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class GroupPurchaseController extends BaseController {

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private BusinessInfoService businessInfoService;

	/**
	 * 获取更多团购
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 */
	@RequestMapping(value = "/getMoreGroupBuy")
	@ResponseBody
	public Map<String, Object> getMoreGroupBuy(String userID, String buildingID, String businessID, HttpServletRequest request) {
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
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<GroupPurchaseBean> groupPurchaseBeans = groupPurchaseService.getBusinessGroupPurchase(businessInfo.getId());
		if (groupPurchaseBeans != null && groupPurchaseBeans.size() != 0) {
			for (GroupPurchaseBean groupPurchaseBean : groupPurchaseBeans) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("groupBuyID", groupPurchaseBean.getGroupPurchaseTimeId());
				data.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
				data.put("groupBuyImage", groupPurchaseService.formatGroupPurchaseImg(groupPurchaseBean.getGroupPurcPic(), request));
				data.put("groupBuyPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
				data.put("marketPrice", ValidateUtil.validateDouble(groupPurchaseBean.getMarketMoney()));
				data.put("soldNum", ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum()));
				data.put("groupBuyUrl", null);

				datas.add(data);
			}
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 模块活动列表
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param type
	 *            团购类型：1->进行中 2->即将开始
	 */
	@RequestMapping(value = "getGroupBuyList")
	@ResponseBody
	public Map<String, Object> getGroupBuyList(String userID, String buildingID, String type, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, type)) {
			return toJson;
		}
		GroupPurchase groupPurchase = new GroupPurchase();
		groupPurchase.setGroupPurcState(type);
		List<GroupPurchaseBean> groupPurchaseBeans = groupPurchaseService.getGroupPurchaseListOnOrComing(groupPurchase);
		if (groupPurchaseBeans == null || groupPurchaseBeans.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			if ("1".equals(type)) {
				toJson.put("message", "暂无团购活动上线");
			} else {
				toJson.put("message", "暂无新的团购准备上线");
			}
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (GroupPurchaseBean groupPurchaseBean : groupPurchaseBeans) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("groupBuyID", groupPurchaseBean.getGroupPurchaseTimeId());
			data.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
			data.put("groupBuyImage", groupPurchaseService.formatGroupPurchaseImg(groupPurchaseBean.getGroupPurcPic(), request));
			data.put("groupBuyStatus", groupPurchaseService.getGroupPurchaseStatus(groupPurchaseBean, type));
			data.put("groupBuyPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
			data.put("marketPrice", ValidateUtil.validateDouble(groupPurchaseBean.getMarketMoney()));
			data.put("soldNum", ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum()));
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
	 * 团购活动详情
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param groupBuyID
	 *            团购活动子表ID（不可空）
	 */
	@RequestMapping(value = "getGroupBuyDetail")
	@ResponseBody
	public Map<String, Object> getGroupBuyDetail(String userID, String groupBuyID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, groupBuyID)) {
			return toJson;
		}
		GroupPurchaseBean groupPurchaseBean = groupPurchaseService.getGroupPurchaseBean(groupBuyID);
		if (groupPurchaseBean == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "团购活动不存在");
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(groupPurchaseBean.getBusinessinfoId());

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("groupBuyID", groupPurchaseBean.getGroupPurchaseTimeId());
		data.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
		data.put("TimeDiff", groupPurchaseService.getTimeInterval(groupPurchaseBean));
		data.put("groupBuyStartTime", groupPurchaseService.getStartTimeDesc(groupPurchaseBean));
		data.put("groupBuyStatus", groupPurchaseService.getGroupPurchaseType(groupPurchaseBean));
		data.put("groupBuyImage", groupPurchaseService.formatGroupPurchaseImg(groupPurchaseBean.getGroupPurcPic(), request));
		data.put("originalPrice", ValidateUtil.validateDouble(groupPurchaseBean.getMarketMoney()));
		data.put("discountedPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
		data.put("soldNum", ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum()));
		data.put("stockNumber", ValidateUtil.validateInteger(groupPurchaseBean.getStockNum()));
		data.put("limitPurchase", ValidateUtil.validateInteger(groupPurchaseBean.getRestrictionNumber()));
		data.put("isAnyTimeCancel", groupPurchaseService.getSupportType(groupPurchaseBean.getSupportType())[0]);
		data.put("isExpiredCancel", groupPurchaseService.getSupportType(groupPurchaseBean.getSupportType())[1]);
		data.put("isFreeReservation", groupPurchaseService.getSupportType(groupPurchaseBean.getSupportType())[2]);
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("businessPhone", businessInfo.getPhoneNum());
		data.put("businessAddress", businessInfoService.getAddress(businessInfo));
		data.put("businessUrl", null);
		data.put("groupBuyDesc", groupPurchaseBean.getGroupPurcDetail());
		data.put("validPeriod", DateFormatUtils.format(groupPurchaseBean.getValidityStartTime(), "yyyy-MM-dd") + "至"
				+ DateFormatUtils.format(groupPurchaseBean.getValidityEndTime(), "yyyy-MM-dd"));
		data.put("usageTime", groupPurchaseBean.getUseTime());
		data.put("useRules", groupPurchaseBean.getUseRule());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}