package com.its.modules.app.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.its.modules.app.entity.LessonInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.AddressService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.LessonInfoService;
import com.its.modules.app.service.OrderLessonService;

/**
 * 订单-课程培训类Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-12
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderLessonController extends BaseController {

	@Autowired
	private OrderLessonService orderLessonService;

	@Autowired
	private LessonInfoService lessonInfoService;

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
	 * @param courseID
	 *            课程ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "confirmCourseOrder")
	@ResponseBody
	public Map<String, Object> confirmCourseOrder(String userID, String buildingID, String businessID, String courseID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, courseID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		LessonInfo lessonInfo = lessonInfoService.get(courseID);
		if (lessonInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "课程不存在");
			return toJson;
		}
		BusinessInfo businessInfo = businessInfoService.get(lessonInfo.getBusinessInfoId());
		Address address = addressService.getDefaultAddress(account.getId(), buildingID);

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		Double lessonPrice = lessonInfoService.getLessonPrice(lessonInfo);
		data.put("totalMoney", lessonPrice);
		data.put("businessID", businessInfo.getId());
		data.put("businessName", businessInfo.getBusinessName());
		data.put("contactPerson", address != null ? address.getContact() : null);
		data.put("contactPhone", address != null ? address.getPhoneNum() : null);
		data.put("courseID", lessonInfo.getId());
		data.put("courseName", lessonInfo.getName());
		data.put("coursePrice", lessonPrice);
		data.put("classNumber", ValidateUtil.validateInteger(lessonInfo.getLessonCount()));
		data.put("classTime", DateFormatUtils.format(lessonInfo.getStartTime(), "yyyy-MM-dd") + "至" + DateFormatUtils.format(lessonInfo.getEndTime(), "MM-dd"));
		data.put("classLocation", lessonInfo.getAddress());

		/* 优惠券开始 */
		List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
		List<CouponManageBean> couponManageBeans = new ArrayList<CouponManageBean>();
		// 判断服务是否有优惠价
		if (lessonInfo.getBenefitPrice() == null || lessonInfo.getBenefitPrice() == 0) {
			couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, 1, businessInfo.getId(), "0,1", lessonPrice);
		} else {
			couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, 1, businessInfo.getId(), "1", lessonPrice);
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
	 * @param contactPerson
	 *            联系人（不可空）
	 * @param contactPhone
	 *            联系电话（不可空）
	 * @param courseID
	 *            课程ID（不可空）
	 * @param coursePrice
	 *            课程价格（不可空）
	 * @param couponID
	 *            优惠劵ID（可空）
	 * @param leaveMessage
	 *            用户留言（可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "submitCourseOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitCourseOrder(String userID, String buildingID, String businessID, String contactPerson, String contactPhone, String courseID, String coursePrice, String couponID, String leaveMessage) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID, contactPerson, contactPhone, courseID, coursePrice)) {
			return toJson;
		}
		double price = 0.0;
		try {
			price = Double.parseDouble(coursePrice);
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
		LessonInfo lessonInfo = lessonInfoService.get(courseID);
		if (lessonInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "课程不存在");
			return toJson;
		}

		// 课程限购，判断是否超过限购数
		if ("1".equals(lessonInfo.getLessonQuota())) {
			int quotaNum = ValidateUtil.validateInteger(lessonInfo.getQuotaNum());
			int buyNum = orderLessonService.getCountByLessonInfoIdAndAccountId(account.getId(), lessonInfo.getId());
			if (quotaNum - buyNum < 1) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "预约数量超过限购数，无法预约");
				return toJson;
			}
		}
		// 无论课程是否限购，判断是否超过库存
		if (ValidateUtil.validateInteger(lessonInfo.getPeopleLimit()) - ValidateUtil.validateInteger(lessonInfo.getSellCount()) <= 0) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "课程库存不足，无法购买");
			return toJson;
		}
		// 判断课程价格是否发生改变
		if (price != lessonInfoService.getLessonPrice(lessonInfo)) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "课程价格发生变更，请重新下单");
			return toJson;
		}
		// 如果使用了优惠券，判断优惠券是否可用
		CouponManageBean couponManageBean = null;
		if (StringUtils.isNotBlank(couponID)) {
			couponManageBean = couponManageService.judgeCoupon(buildingID, userID, couponID);
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
			leaveMessage = StringUtils.isNotBlank(leaveMessage) ? URLDecoder.decode(leaveMessage, "UTF-8") : leaveMessage;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				toJson.put("code", Global.CODE_ERROR);
				toJson.put("message", e.getMessage());
			} else {
				toJson.put("code", Global.CODE_ERROR);
				toJson.put("message", "系统错误");
			}
			return toJson;
		}

		String orderLessonId = orderLessonService.createOrderLesson(account, contactPerson, contactPhone, lessonInfo, couponManageBean, leaveMessage);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", orderLessonId);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "订单已生成");
		return toJson;
	}
}