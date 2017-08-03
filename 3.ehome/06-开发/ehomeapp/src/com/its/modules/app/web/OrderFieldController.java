package com.its.modules.app.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.its.modules.app.bean.FieldPartitionBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.entity.Address;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.service.AddressService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.CouponManageService;
import com.its.modules.app.service.FieldPartitionPriceService;
import com.its.modules.app.service.OrderFieldService;

import net.sf.json.JSONObject;

/**
 * 场地订单Controller
 * 
 * @author like
 * 
 * @version 2017-07-12
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderFieldController extends BaseController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private CouponManageService couponManageService;
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	@Autowired
	private OrderFieldService orderFieldService;

	/**
	 * 确认订单
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 * @param siteReservationID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmSiteOrder")
	public String confirmSiteOrder(String userID, String buildingID, String businessID, String siteReservationID) {
		long start = new Date().getTime();
		try {
			if (StringUtils.isBlank(userID) || StringUtils.isBlank(businessID) || StringUtils.isBlank(siteReservationID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
			}
			BusinessInfo business = businessInfoService.get(businessID);
			if (business == null) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"商家不存在\"}";
			}
			Address address = addressService.getDefaultAddress(userID, buildingID);
			List<FieldPartitionBean> list = fieldPartitionPriceService.findSelectedFieldPartition(siteReservationID);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("businessID", business.getId());
			data.put("businessName", business.getBusinessName());
			data.put("contactPerson", address != null ? address.getContact() : "");
			data.put("contactPhone", address != null ? address.getPhoneNum() : "");
			// Map<String, Map<String, Object>> mapList = new HashMap<>();
			// for (FieldPartitionBean bean : list) {
			// String appointmentTime =
			// DateFormatUtils.format(bean.getAppointmentTime(), "yyyy-MM-dd");
			// Map<String, Object> listMap = mapList.get(bean.getFieldInfoId());
			// if (listMap == null) {
			// listMap = new HashMap<>();
			// listMap.put("siteID", bean.getFieldInfoId());
			// listMap.put("siteName", bean.getFieldName());
			// List<Map<String, Object>> days = new ArrayList<>();
			// Map<String, Object> dayMap = new HashMap<>();
			// dayMap.put("date", appointmentTime + " " +
			// AppUtils.formatDateWeek(appointmentTime));
			// List<Map<String, Object>> detail = new ArrayList<>();
			// Map<String, Object> detailMap = new HashMap<>();
			// detailMap.put("timePeriod",
			// DateFormatUtils.format(bean.getStartTime(), "HH:mm") + "~"
			// + DateFormatUtils.format(bean.getEndTime(), "HH:mm"));
			// detailMap.put("price", bean.getBasePrice());
			// detail.add(detailMap);
			// dayMap.put("detail", detail);
			// days.add(dayMap);
			// listMap.put("reservation", days);
			// } else {
			// List<Map<String, Object>> days = (List<Map<String, Object>>)
			// listMap.get("reservation");
			// Map<String, Object> day = null;
			// for (Map<String, Object> d : days) {
			// if (appointmentTime.equals(String.valueOf(d.get("date")).split("
			// ")[0])) {
			// day = d;
			// }
			// }
			// if (day == null) {
			// Map<String, Object> dayMap = new HashMap<>();
			// dayMap.put("date", appointmentTime + " " +
			// AppUtils.formatDateWeek(appointmentTime));
			// List<Map<String, Object>> detail = new ArrayList<>();
			// Map<String, Object> detailMap = new HashMap<>();
			// detailMap.put("timePeriod",
			// DateFormatUtils.format(bean.getStartTime(), "HH:mm") + "~"
			// + DateFormatUtils.format(bean.getEndTime(), "HH:mm"));
			// detailMap.put("price", bean.getBasePrice());
			// detail.add(detailMap);
			// dayMap.put("detail", detail);
			// days.add(dayMap);
			// } else {
			// List<Map<String, Object>> detail = (List<Map<String, Object>>)
			// day.get("detail");
			// Map<String, Object> detailMap = new HashMap<>();
			// detailMap.put("timePeriod",
			// DateFormatUtils.format(bean.getStartTime(), "HH:mm") + "~"
			// + DateFormatUtils.format(bean.getEndTime(), "HH:mm"));
			// detailMap.put("price", bean.getBasePrice());
			// detail.add(detailMap);
			// day.put("detail", detail);
			// }
			// listMap.put("reservation", days);
			// }
			// mapList.put(bean.getFieldInfoId(), listMap);
			// }
			// data.put("sites", mapList.values());
			List<Map<String, Object>> details = new ArrayList<>();
			double totalMoney = 0;
			for (int i = 0; i < list.size(); i++) {
				FieldPartitionBean bean = list.get(i);
				if (i == 0) {
					data.put("siteID", bean.getFieldInfoId());
					data.put("siteName", bean.getFieldName());
					String appointmentTime = DateFormatUtils.format(bean.getAppointmentTime(), "yyyy-MM-dd");
					data.put("reservationDate", appointmentTime + " " + AppUtils.formatDateWeek(appointmentTime));
				}
				Map<String, Object> detail = new HashMap<>();
				detail.put("timePeriod", DateFormatUtils.format(bean.getStartTime(), "HH:mm") + "~" + DateFormatUtils.format(bean.getEndTime(), "HH:mm"));
				detail.put("price", bean.getSumMoney());
				details.add(detail);
				totalMoney += bean.getSumMoney();
			}
			data.put("detail", details);
			data.put("totalMoney", totalMoney);
			/* 优惠券开始 */
			List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
			List<CouponManageBean> couponManageBeans = couponManageService.getEnableCoupons(buildingID, userID, Integer.parseInt(AppGlobal.MODEL_FIELD),
					business.getId(), "0,1", totalMoney);
			for (CouponManageBean couponManageBean : couponManageBeans) {
				Map<String, Object> coupon = new HashMap<String, Object>();
				coupon.put("couponID", couponManageBean.getMemberDiscountId());
				coupon.put("couponMoney", couponManageService.getCouponMoney(couponManageBean));
				coupon.put("couponName", couponManageService.getCouponName(couponManageBean));
				coupon.put("couponCondition", ("0".equals(couponManageBean.getUseRule()) ? "无限制" : "满" + couponManageBean.getFullUseMoney() + "元可用"));
				coupon.put("couponStatus", null);
				coupons.add(coupon);
			}
			data.put("coupons", coupons);
			/* 优惠券结束 */
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("code", Global.CODE_SUCCESS);
			json.put("data", data);
			json.put("message", "成功");
			return JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"" + e.getMessage() + "\"}";
			}
			return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"系统错误\"}";
		} finally {
			if (AppUtils.DEBUG_MODEL) {
				System.out.println("/live/confirmSiteOrder()运行时间：" + (new Date().getTime() - start) + "ms");
			}
		}
	}

	/**
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 * @param siteReservationID
	 * @param contactPerson
	 * @param contactPhone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitSiteOrder", method = RequestMethod.POST)
	public String submitSiteOrder(String userID, String buildingID, String businessID, String siteReservationID, String contactPerson, String contactPhone,
			String leaveMessage, String couponID) {
		long start = new Date().getTime();
		try {
			if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID) || StringUtils.isBlank(siteReservationID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
			}
			if (StringUtils.isBlank(contactPerson)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"请输入联系人\"}";
			}
			if (StringUtils.isBlank(contactPhone)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"请输入联系电话\"}";
			}
			BusinessInfo business = businessInfoService.get(businessID);
			if (business == null) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，该商家不存在\"}";
			}
			List<FieldPartitionBean> list = fieldPartitionPriceService.findSelectedFieldPartition(siteReservationID);
			if (list == null || list.size() == 0) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，预定时段不存在\"}";
			}
			// 如果使用了优惠券，判断优惠券是否可用
			CouponManageBean couponManage = null;
			if (StringUtils.isNotBlank(couponID)) {
				couponManage = couponManageService.judgeCoupon(buildingID, userID, couponID);
				if (couponManage == null) {
					return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"优惠券不可使用\"}";
				}
			}
			contactPerson = StringUtils.isNotBlank(contactPerson) ? URLDecoder.decode(contactPerson, "UTF-8") : "";
			contactPhone = StringUtils.isNotBlank(contactPhone) ? URLDecoder.decode(contactPhone, "UTF-8") : "";
			leaveMessage = StringUtils.isNotBlank(leaveMessage) ? URLDecoder.decode(leaveMessage, "UTF-8") : "";
			OrderField order = new OrderField();
			order.setVillageInfoId(buildingID);
			order.setAccountId(userID);
			order.setAccountName(contactPerson);
			order.setAccountPhoneNumber(contactPhone);
			order.setAccountMsg(leaveMessage);// 留言
			// 生成订单
			Map<String, String> result = orderFieldService.createOrderField(order, business, list, userID, businessID, couponManage);
			// 返回接口运行结果
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
				System.out.println("/live/submitSiteOrder()运行时间：" + (new Date().getTime() - start) + "ms");
			}
		}
	}
}