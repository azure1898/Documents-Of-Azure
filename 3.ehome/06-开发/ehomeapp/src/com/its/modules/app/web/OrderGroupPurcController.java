package com.its.modules.app.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GroupPurchaseService;
import com.its.modules.app.service.OrderGroupPurcService;

/**
 * 订单-团购类Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderGroupPurcController extends BaseController {

	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	public AccountService accountService;

	/**
	 * 确认订单
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 * @param groupBuyID
	 *            团购子表ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "confirmGroupBuyOrder")
	@ResponseBody
	public Map<String, Object> confirmGroupBuyOrder(String userID, String buildingID, String businessID, String groupBuyID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, groupBuyID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
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
		data.put("contactPhone", account.getPhoneNum());
		data.put("totalMoney", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
		data.put("businessName", businessInfo.getBusinessName());
		data.put("groupBuyID", groupPurchaseBean.getGroupPurchaseTimeId());
		data.put("groupBuyName", groupPurchaseBean.getGroupPurcName());
		data.put("groupBuyPrice", ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney()));
		data.put("groupBuyNumber", 1);
		data.put("purchasedNumber", orderGroupPurcService.getCountByGroupPurcIdAndAccountId(account.getId(), groupPurchaseBean.getId()));
		data.put("stockNumber", ValidateUtil.validateInteger(groupPurchaseBean.getStockNum()));
		data.put("limitPurchase", ValidateUtil.validateInteger(groupPurchaseBean.getRestrictionNumber()));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 生成订单
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 * @param contactPhone
	 *            联系电话（不可空）
	 * @param groupBuyID
	 *            团购子表ID（不可空）
	 * @param groupBuyNumber
	 *            团购数量（不可空）
	 * @param leaveMessage
	 *            留言（可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "submitGroupBuyOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitGroupBuyOrder(String userID, String buildingID, String businessID, String contactPhone, String groupBuyID, String groupBuyNumber, String leaveMessage) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, contactPhone, groupBuyID, groupBuyNumber)) {
			return toJson;
		}
		int payNumber = 0;
		try {
			payNumber = Integer.parseInt(groupBuyNumber);
		} catch (NumberFormatException e) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "数值类型不符");
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		GroupPurchaseBean groupPurchaseBean = groupPurchaseService.getGroupPurchaseBean(groupBuyID);

		int quotaNum = ValidateUtil.validateInteger(groupPurchaseBean.getRestrictionNumber());
		// 用户限购数小于或等于0，按无限制购买处理
		if (quotaNum > 0) {
			// 判断购买数量是否超过限购数
			int buyNum = orderGroupPurcService.getCountByGroupPurcIdAndAccountId(account.getId(), groupPurchaseBean.getId());
			if (payNumber > (quotaNum - buyNum)) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "预约数量超过限购数，无法预约");
				return toJson;
			}
		}
		// 判断购买数量是否超过剩余库存
		if (payNumber > (ValidateUtil.validateInteger(groupPurchaseBean.getStockNum()) - ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum()))) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "团购库存不足，无法预约");
			return toJson;
		}

		// 生成订单
		String orderGroupPurcId = orderGroupPurcService.createOrderGroupPurc(account, contactPhone, groupPurchaseBean, payNumber, leaveMessage);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", orderGroupPurcId);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "订单已生成");
		return toJson;
	}
}