package com.its.modules.app.web;

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
import com.its.common.web.BaseController;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.CouponManage;
import com.its.modules.app.entity.MemberDiscount;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.MemberDiscountService;

/**
 * 优惠券管理Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = { "${appPath}/home", "${appPath}/my" })
public class CouponManageController extends BaseController {

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private MemberDiscountService memberDiscountService;

	/**
	 * 我的有效优惠劵
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getValidCoupon")
	@ResponseBody
	public Map<String, Object> getValidCoupon(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		List<CouponManageBean> couponManageBeans = couponManageService.getValidCoupons(buildingID, userID);
		if (couponManageBeans == null || couponManageBeans.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (CouponManageBean couponManageBean : couponManageBeans) {
			Map<String, Object> data = new HashMap<String, Object>();
			// 传递会员的优惠券ID（不传递优惠券ID）
			data.put("couponID", couponManageBean.getMemberDiscountId());
			data.put("couponType", couponManageBean.getCouponType());
			data.put("couponMoney", ValidateUtil.validateDouble(couponManageBean.getCouponMoney()));
			data.put("couponName", couponManageService.getCouponName(couponManageBean));
			data.put("couponCondition", couponManageService.getCouponCondition(couponManageBean));
			data.put("limitUser", account.getPhoneNum());
			data.put("couponCap", ValidateUtil.validateDouble(couponManageBean.getUpperLimitMoney()));
			data.put("couponStart", DateFormatUtils.format(couponManageBean.getValidStart(), "yyyy-MM-dd"));
			data.put("couponEnd", DateFormatUtils.format(couponManageBean.getValidEnd(), "yyyy-MM-dd"));
			data.put("couponStatus", couponManageBean.getUseState());

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 我的无效优惠劵
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getInvalidCoupon")
	@ResponseBody
	public Map<String, Object> getInvalidCoupon(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		List<CouponManageBean> couponManageBeans = couponManageService.getInvalidCoupons(buildingID, userID);
		if (couponManageBeans == null || couponManageBeans.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (CouponManageBean couponManageBean : couponManageBeans) {
			Map<String, Object> data = new HashMap<String, Object>();
			// 传递会员的优惠券ID（不传递优惠券ID）
			data.put("couponID", couponManageBean.getMemberDiscountId());
			data.put("couponType", couponManageBean.getCouponType());
			data.put("couponMoney", ValidateUtil.validateDouble(couponManageBean.getCouponMoney()));
			data.put("couponName", couponManageService.getCouponName(couponManageBean));
			data.put("couponCondition", couponManageService.getCouponCondition(couponManageBean));
			data.put("limitUser", account.getPhoneNum());
			data.put("couponCap", ValidateUtil.validateDouble(couponManageBean.getUpperLimitMoney()));
			data.put("couponStart", DateFormatUtils.format(couponManageBean.getValidStart(), "yyyy-MM-dd"));
			data.put("couponEnd", DateFormatUtils.format(couponManageBean.getValidEnd(), "yyyy-MM-dd"));
			data.put("couponStatus", couponManageBean.getUseState());

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 获取优惠券信息（领券中心）
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getCoupon")
	@ResponseBody
	public Map<String, Object> getCoupon(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		List<CouponManage> couponManages = couponManageService.getCouponsOfReceiveType(CommonGlobal.COUPON_RECEIVE_TYPE_RECEIVE, buildingID);
		if (couponManages == null || couponManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (CouponManage couponManage : couponManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			// 传递优惠券ID（不传递会员的优惠券ID）
			data.put("couponID", couponManage.getId());
			data.put("couponType", couponManage.getCouponType());
			data.put("couponMoney", ValidateUtil.validateDouble(couponManage.getCouponMoney()));
			data.put("couponName", couponManageService.getCouponName(couponManage));
			data.put("couponCondition", couponManageService.getCouponCondition(couponManage));
			data.put("couponCap", ValidateUtil.validateDouble(couponManage.getUpperLimitMoney()));
			int receiveNum = 0;
			// 买家领取规则：0无限制 1每人每日限领1张 2每人限领1张
			if (CommonGlobal.COUPON_RECEIVE_RULE_LIMITONE.equals(couponManage.getReceiveRule())) {
				receiveNum = memberDiscountService.getTodayReceiveCount(buildingID, userID, couponManage.getId());
			} else if (CommonGlobal.COUPON_RECEIVE_RULE_ONLYONE.equals(couponManage.getReceiveRule())) {
				receiveNum = memberDiscountService.getReceiveCount(buildingID, userID, couponManage.getId());
			}
			data.put("couponStatus", couponManageService.getCouponStatus(couponManage, receiveNum));

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 领取优惠券（买家领取）
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param conponID
	 *            优惠劵ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "receiveCoupon", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> receiveCoupon(String userID, String couponID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, couponID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		CouponManage couponManage = couponManageService.get(couponID);
		if (couponManage == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "优惠券不存在");
			return toJson;
		}
		if (CommonGlobal.COUPON_GRANT_TYPE_LIMIT.equals(couponManage.getGrantType())) {
			if (ValidateUtil.validateInteger(couponManage.getLimitedNum()) <= ValidateUtil.validateInteger(couponManage.getReceiveNum())) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "优惠券库存不足");
				return toJson;
			}
		}
		int receiveNum = 0;
		// 买家领取规则：0无限制 1每人每日限领1张 2每人限领1张
		if (CommonGlobal.COUPON_RECEIVE_RULE_LIMITONE.equals(couponManage.getReceiveRule())) {
			receiveNum = memberDiscountService.getTodayReceiveCount(couponManage.getVillageInfoId(), userID, couponManage.getId());
		} else if (CommonGlobal.COUPON_RECEIVE_RULE_ONLYONE.equals(couponManage.getReceiveRule())) {
			receiveNum = memberDiscountService.getReceiveCount(couponManage.getVillageInfoId(), userID, couponManage.getId());
		}
		// 如果优惠券不可领取，则返回优惠券无法领取
		if (couponManageService.getCouponStatus(couponManage, receiveNum) != 1) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "优惠券无法领取");
			return toJson;
		}

		MemberDiscount memberDiscount = couponManageService.createMemberDiscount(couponManage, userID);
		if (memberDiscount == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "数据更新失败");
			return toJson;
		}
		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("couponID", memberDiscount.getId());
		data.put("couponType", couponManage.getCouponType());
		data.put("couponMoney", ValidateUtil.validateDouble(couponManage.getCouponMoney()));
		data.put("couponName", couponManageService.getCouponName(couponManage));
		data.put("couponCondition", couponManageService.getCouponCondition(couponManage));
		data.put("limitUser", account.getPhoneNum());
		data.put("couponCap", ValidateUtil.validateDouble(couponManage.getUpperLimitMoney()));
		data.put("couponStart", DateFormatUtils.format(memberDiscount.getValidStart(), "yyyy-MM-dd"));
		data.put("couponEnd", DateFormatUtils.format(memberDiscount.getValidEnd(), "yyyy-MM-dd"));
		data.put("couponStatus", memberDiscount.getUseState());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "领取成功");
		return toJson;
	}
}