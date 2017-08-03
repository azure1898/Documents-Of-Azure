package com.its.modules.app.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.Address;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.ServiceInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.AddressService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.OrderServiceService;
import com.its.modules.app.service.ServiceInfoService;

/**
 * 订单-服务类Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-10
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderServiceController extends BaseController {

	@Autowired
	private OrderServiceService orderServiceService;

	@Autowired
	private ServiceInfoService serviceInfoService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CouponManageService couponManageService;

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
	 * @param serviceID
	 *            服务ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "confirmServiceOrder")
	@ResponseBody
	public Map<String, Object> confirmServiceOrder(String userID, String buildingID, String businessID, String serviceID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, serviceID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		ServiceInfo serviceInfo = serviceInfoService.get(serviceID);
		if (serviceInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "服务不存在");
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(serviceInfo.getBusinessInfoId());
		Address address = addressService.getDefaultAddress(account.getId(), buildingID);

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		Double servicePrice = serviceInfoService.getServicePrice(serviceInfo);
		data.put("totalMoney", servicePrice);
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("serviceWay", businessInfo.getServiceModel());
		data.put("serviceCharge", ValidateUtil.validateDouble(businessInfo.getServiceCharge()));
		data.put("contactPerson", address != null ? address.getContact() : null);
		data.put("contactPhone", address != null ? address.getPhoneNum() : null);
		data.put("contactAddress", address != null ? address.getAddress() : null);
		data.put("serviceID", serviceInfo.getId());
		data.put("serviceName", serviceInfo.getName());
		data.put("unit", businessInfoService.getUnit(serviceInfo.getBaseUnit()));
		data.put("servicePrice", servicePrice);
		data.put("serviceNumber", 1);
		data.put("stockNumber", ValidateUtil.validateInteger(serviceInfo.getStock()) - orderServiceService.getCountByServiceInfoId(serviceInfo.getId()));
		data.put("limitPurchase", ValidateUtil.validateInteger(serviceInfo.getQuotaNum()));

		/* 优惠券开始 */
		List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
		List<CouponManageBean> couponManageBeans = new ArrayList<CouponManageBean>();
		// 获取可用的优惠券
		if (serviceInfo.getBenefitPrice() == null || serviceInfo.getBenefitPrice() == 0) {
			couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, 1, businessInfo.getId(), "0,1", servicePrice);
		} else {
			couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, 1, businessInfo.getId(), "1", servicePrice);
		}
		if (couponManageBeans != null && couponManageBeans.size() != 0) {
			for (CouponManageBean couponManageBean : couponManageBeans) {
				Map<String, Object> coupon = new HashMap<String, Object>();
				coupon.put("couponID", couponManageBean.getMemberDiscountId());
				coupon.put("couponMoney", couponManageService.getCouponMoney(couponManageBean));
				coupon.put("couponName", couponManageService.getCouponName(couponManageBean));
				coupon.put("couponCondition", couponManageService.getCouponCondition(couponManageBean));
				coupon.put("couponStatus", null);

				coupons.add(coupon);
			}
		}
		data.put("coupons", coupons);
		/* 优惠券结束 */

		data.put("serviceDate", businessInfoService.getDeliveryDate(businessInfo, businessInfo.getServiceModel()));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 
	 * 生成订单
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 * @param contactPerson
	 *            联系人（不可空）
	 * @param contactPhone
	 *            联系电话（不可空）
	 * @param contactAddress
	 *            联系地址（可空）
	 * @param serviceID
	 *            服务ID（不可空）
	 * @param servicePrice
	 *            服务价格（不可空）
	 * @param serviceNumber
	 *            预约数量（不可空）
	 * @param isImmediate
	 *            是否立即上门
	 * @param serviceStart
	 *            上门/到店开始时间
	 * @param serviceEnd
	 *            上门/到店结束时间
	 * @param couponID
	 *            优惠劵ID（可空）
	 * @param leaveMessage
	 *            用户留言（可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "submitServiceOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitServiceOrder(String userID, String buildingID, String businessID, String contactPerson, String contactPhone, String contactAddress, String serviceID, String servicePrice, String serviceNumber, int isImmediate, String serviceStart, String serviceEnd, String couponID, String leaveMessage) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, contactPerson, contactPhone, serviceID, servicePrice, serviceNumber, String.valueOf(isImmediate), serviceEnd)) {
			return toJson;
		}
		int appointNum = 0;
		double price = 0.0;
		try {
			appointNum = Integer.parseInt(serviceNumber);
			price = Double.parseDouble(servicePrice);
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
		ServiceInfo serviceInfo = serviceInfoService.get(serviceID);
		if (serviceInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "服务不存在");
			return toJson;
		}

		// 服务限购，判断是否超过限购数
		if ("1".equals(serviceInfo.getQuota())) {
			int quotaNum = ValidateUtil.validateInteger(serviceInfo.getQuotaNum());
			int buyNum = orderServiceService.getCountByServiceInfoIdAndAccountId(serviceInfo.getId(), account.getId());
			if (appointNum > (quotaNum - buyNum)) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "预约数量超过限购数，无法预约");
				return toJson;
			}
		}
		// 无论服务是否限购，判断是否超过库存
		if (appointNum > (ValidateUtil.validateInteger(serviceInfo.getStock()) - orderServiceService.getCountByServiceInfoId(serviceInfo.getId()))) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "服务库存不足，无法预约");
			return toJson;
		}
		// 判断服务价格是否发生改变
		if (price != serviceInfoService.getServicePrice(serviceInfo)) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "服务价格发生变更，请重新下单");
			return toJson;
		}
		// 如果使用了优惠券，判断优惠券是否可用
		CouponManageBean couponManageBean = null;
		if (StringUtils.isNotBlank(couponID)) {
			couponManageBean = couponManageService.judgeCoupon(buildingID, account.getId(), couponID);
			if (couponManageBean == null) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "优惠券不可使用");
				return toJson;
			}
		}
		// 解决中文乱码问题
		try {
			contactPerson = StringUtils.isNotBlank(contactPerson) ? URLDecoder.decode(contactPerson, "UTF-8") : contactPerson;
			contactPhone = StringUtils.isNotBlank(contactPhone) ? URLDecoder.decode(contactPhone, "UTF-8") : contactPhone;
			contactAddress = StringUtils.isNotBlank(contactAddress) ? URLDecoder.decode(contactAddress, "UTF-8") : contactAddress;
			leaveMessage = StringUtils.isNotBlank(leaveMessage) ? URLDecoder.decode(leaveMessage, "UTF-8") : leaveMessage;
		} catch (UnsupportedEncodingException e) {
			if (Global.isDebug()) {
				toJson.put("code", Global.CODE_ERROR);
				toJson.put("message", e.getMessage());
			} else {
				toJson.put("code", Global.CODE_ERROR);
				toJson.put("message", "系统错误");
			}
			return toJson;
		}

		// 生成订单
		String orderServiceId = orderServiceService.createOrderService(account, contactPerson, contactPhone, contactAddress, serviceInfo, appointNum, isImmediate, serviceStart, serviceEnd, couponManageBean, leaveMessage);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", orderServiceId);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "订单已生成");
		return toJson;
	}
}