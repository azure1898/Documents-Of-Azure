package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.its.modules.app.bean.GoodsInfoBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.Address;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.AddressService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.GoodsInfoService;
import com.its.modules.app.service.OrderGoodsService;
import com.its.modules.app.service.ShoppingCartService;
import com.its.modules.app.service.VillageInfoService;

import net.sf.json.JSONObject;

/**
 * 订单-商品类Controller
 * 
 * @author like
 * 
 * @version 2017-07-10
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderGoodsController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private OrderGoodsService orderGoodsService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private VillageInfoService villageInfoService;

	/**
	 * 确认订单
	 * 
	 * @param userID
	 *            会员ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID(不可空)
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmBusinessOrder")
	public Map<String, Object> confirmBusinessOrder(String userID, String buildingID, String businessID, HttpServletRequest request) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		long start = new Date().getTime();
		try {
			if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID)) {
				return toJson;
			}
			Account account = accountService.get(userID);
			if (account == null) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "用户不存在");
				return toJson;
			}
			BusinessInfo business = businessInfoService.get(businessID);
			if (business == null) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "商家不存在");
				return toJson;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", business.getId());
			data.put("businessName", business.getBusinessName());
			Address address = addressService.getDefaultAddress(userID, buildingID);
			if (address != null) {
				data.put("contactPerson", address.getContact());
				data.put("contactPhone", address.getPhoneNum());
				if ("0".equals(address.getAddressType())) {
					data.put("contactAddress", addressService.getAddressDetail(address));
				} else if ("1".equals(address.getAddressType())) {
					data.put("contactAddress", address.getAddress());
				}
			} else {
				data.put("contactPerson", "");
				data.put("contactPhone", "");
				data.put("contactAddress", "");
			}
			// 处理购物车中的商品信息
			List<GoodsInfoBean> list = shoppingCartService.getShoppingCartList(userID, buildingID, businessID);
			List<Map<String, Object>> listJson = new ArrayList<Map<String, Object>>();
			double totalMoney = 0;
			boolean isBenefitPrice = false;
			for (GoodsInfoBean bean : list) {
				Map<String, Object> goods = new HashMap<String, Object>();
				goods.put("commodityID", bean.getId());
				goods.put("commodityName", goodsInfoService.getGoodsInfoName(bean));
				goods.put("commodityImage", goodsInfoService.getGoodsPicUrl(bean, request));
				if (StringUtils.isNotBlank(bean.getGoodsSkuPriceID())) {
					double price = bean.getSkuBasePrice();
					if (bean.getSkuBenefitPrice() != null && bean.getSkuBenefitPrice() != 0) {
						price = bean.getSkuBenefitPrice();
						isBenefitPrice = true;
					}
					goods.put("commodityPrice", price * bean.getCartNumber());
					totalMoney += price * bean.getCartNumber();
				} else {
					double price = bean.getBasePrice();
					if (bean.getBenefitPrice() != null && bean.getBenefitPrice() != 0) {
						price = bean.getBenefitPrice();
						isBenefitPrice = true;
					}
					goods.put("commodityPrice", price * bean.getCartNumber());
					totalMoney += price * bean.getCartNumber();
				}
				goods.put("commodityNumber", bean.getCartNumber());
				listJson.add(goods);
			}
			data.put("commodities", listJson);
			// 配送时段
			data.put("deliveryDate", businessInfoService.getDeliveryDate(business, CommonGlobal.BUSINESS_TIME_TYPE_DELIVERY));
			// 优惠金额
			double discountMoney = businessInfoService.getCutDownMoney(businessID, totalMoney);
			// 配送费
			double distributeCharge = business.getDistributeCharge() != null ? business.getDistributeCharge() : 0;
			if (CommonGlobal.YES.equals(business.getFullDistributeFlag()) && business.getFullDistributeMoney() >= totalMoney) {
				distributeCharge = 0;
			}
			data.put("deliveryFee", distributeCharge);// 配送费
			data.put("activityDiscounted", discountMoney);
			data.put("totalMoney", totalMoney + distributeCharge);
			/* 优惠券开始 */
			List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
			List<CouponManageBean> couponManageBeans = new ArrayList<CouponManageBean>();
			if (!isBenefitPrice) {
				couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, Integer.parseInt(AppGlobal.MODEL_GOODS), business.getId(), "0,1", totalMoney);
			} else {
				couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, Integer.parseInt(AppGlobal.MODEL_GOODS), business.getId(), "1", totalMoney);
			}
			for (CouponManageBean couponManageBean : couponManageBeans) {
				Map<String, Object> coupon = new HashMap<String, Object>();
				coupon.put("couponID", couponManageBean.getMemberDiscountId());
				coupon.put("couponType", couponManageBean.getCouponType());
				coupon.put("couponMoney", ValidateUtil.validateDouble(couponManageBean.getCouponMoney()));
				coupon.put("couponName", couponManageService.getCouponName(couponManageBean));
				coupon.put("couponCondition", couponManageService.getCouponCondition(couponManageBean));
				coupon.put("limitUser", account.getPhoneNum());
				coupon.put("couponCap", ValidateUtil.validateDouble(couponManageBean.getUpperLimitMoney()));
				coupon.put("couponStart", DateFormatUtils.format(couponManageBean.getValidStart(), "yyyy-MM-dd"));
				coupon.put("couponEnd", DateFormatUtils.format(couponManageBean.getValidEnd(), "yyyy-MM-dd"));
				coupon.put("couponStatus", couponManageBean.getUseState());

				coupons.add(coupon);
			}
			data.put("coupons", coupons);
			/* 优惠券结束 */
			/* 配送时间 */

			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("data", data);
			toJson.put("message", "成功");
			return toJson;
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "");
				return toJson;
			}
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "系统错误");
			return toJson;
		} finally {
			if (AppUtils.DEBUG_MODEL) {
				System.out.println("/live/confirmBusinessOrder()运行时间：" + (new Date().getTime() - start) + "ms");
			}
		}
	}

	/**
	 * 提交商品订单
	 * 
	 * @param userID
	 *            会员ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID(不可空)
	 * @param contactPerson
	 *            联系人(不可空)
	 * @param contactPhone
	 *            联系电话(不可空)
	 * @param contactAddress
	 *            联系地址(不可空)
	 * @param isImmediate
	 *            是否立即配送(不可空)
	 * @param deliveryStart
	 *            配送时间段开始时间(可空)
	 * @param deliveryEnd
	 *            配送时间段结束时间 配送时间(可空)
	 * @param couponID
	 *            优惠券ID(可空)
	 * @param leaveMessage
	 *            留言(可空)
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/submitBusinessOrder", method = RequestMethod.POST)
	public String submitBusinessOrder(String userID, String buildingID, String businessID, String contactPerson, String contactPhone, String contactAddress, int isImmediate, String deliveryStart, String deliveryEnd, String couponID, String leaveMessage) {
		long start = new Date().getTime();
		try {
			if (StringUtils.isBlank(userID) && StringUtils.isBlank(buildingID) && StringUtils.isBlank(businessID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
			}
			BusinessInfo business = businessInfoService.get(businessID);
			if (business == null) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，该商家不存在\"}";
			}
			VillageInfo village = villageInfoService.get(buildingID);
			if (village == null) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，该楼盘不存在\"}";
			}
			// 如果使用了优惠券，判断优惠券是否可用
			CouponManageBean couponManage = null;
			if (StringUtils.isNotBlank(couponID)) {
				couponManage = couponManageService.judgeCoupon(buildingID, userID, couponID);
				if (couponManage == null) {
					return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"优惠券不可使用\"}";
				}
			}
			// 终端类型
			OrderGoods order = new OrderGoods();
			order.setAccountId(userID);
			order.setAccountName(contactPerson);
			order.setAccountPhoneNumber(contactPhone);
			order.setProvinceId(village.getAddrPro());
			order.setCityId(village.getAddrCity());
			order.setAddressType(business.getDistributeModel());// 配送方式
			order.setAddress(contactAddress);
			order.setAccountMsg(leaveMessage); // 买家留言
			order.setVillageInfoId(buildingID);
			order.setIsStart(String.valueOf(isImmediate));
			order.setStartTime(deliveryStart);
			order.setEndTime(deliveryEnd);
			Map<String, String> result = orderGoodsService.createOderGoods(order, business, userID, buildingID, couponManage);
			Map<String, Object> json = new HashMap<String, Object>();
			if (Global.CODE_SUCCESS.equals(result.get("code"))) {
				json.put("code", Global.CODE_SUCCESS);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("orderID", result.get("message"));
				json.put("data", data);
				json.put("message", "成功");
			} else {
				json.putAll(result);
			}
			return JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"" + e.getMessage() + "\"}";
			}
			return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"系统错误\"}";
		} finally {
			if (AppUtils.DEBUG_MODEL) {
				System.out.println("/live/submitBusinessOrder()运行时间：" + (new Date().getTime() - start) + "ms");
			}
		}
	}

}