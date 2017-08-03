/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.FieldInfoBean;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.FieldPartitionPrice;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.FieldPartitionPriceService;
import com.its.modules.app.service.MyCollectService;

import net.sf.json.JSONObject;

/**
 * 场地分段预约表Controller
 * 
 * @author like
 * @version 2017-07-11
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class FieldPartitionPriceController extends BaseController {
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	@Autowired
	private MyCollectService myCollectService;

	/**
	 * 获取商家场地信息
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID（不可空）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSites")
	public Map<String, Object> getSites(String userID, String buildingID, String businessID, HttpServletRequest request) {
		long start = new Date().getTime();
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
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
		data.put("businessImage", MyFDFSClientUtils.get_fdfs_file_url(request, business.getBusinessPic()));
		data.put("businessPhone", business.getPhoneNum());
		data.put("businessLabels", businessInfoService.getBusinessLabelList(business));
		data.put("isNormal", businessInfoService.isBusinessNormal(business));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessID) : 0);
		data.put("businessHours", business.getBusinessHours());
		List<String> dateList = fieldPartitionPriceService.findAppointmentDateList(AppUtils.getTodayBegin(), businessID);
		List<Map<String, Object>> dateJson = new ArrayList<Map<String, Object>>();
		for (String d : dateList) {
			Map<String, Object> dj = new HashMap<String, Object>();
			dj.put("date", d);
			dj.put("week", AppUtils.formatDateWeek(d));
			dateJson.add(dj);
		}
		data.put("reservationDate", dateJson);
		List<FieldInfoBean> siteList = fieldPartitionPriceService.findAppointmentSiteList(AppUtils.getTodayBegin(), businessID);
		List<Map<String, Object>> siteJson = new ArrayList<Map<String, Object>>();
		for (FieldInfoBean bean : siteList) {
			Map<String, Object> sj = new HashMap<String, Object>();
			sj.put("siteID", bean.getFieldInfoID());
			sj.put("siteName", bean.getFieldInfoName());
			siteJson.add(sj);
		}
		data.put("sites", siteJson);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getSites()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return toJson;
	}

	/**
	 * 返回某一商家某一天的某个场地的时段预约情况
	 * 
	 * @param userID
	 * @param businessID
	 * @param date
	 *            预约日期 （yyyy-MM-dd）
	 * @param siteID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSiteReservation")
	public String getSiteReservation(String userID, String businessID, String date, String siteID) {
		long start = new Date().getTime();
		if (StringUtils.isBlank(businessID) || StringUtils.isBlank(date) || StringUtils.isBlank(siteID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<FieldPartitionPrice> list = fieldPartitionPriceService.findFieldPartition(businessID, date, siteID);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (FieldPartitionPrice f : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("siteReservationID", f.getId());
			m.put("siteID", f.getFieldInfoId());
			m.put("reservationDate", DateFormatUtils.format(f.getAppointmentTime(), "yyyy-MM-dd"));
			m.put("timePeriod", DateFormatUtils.format(f.getStartTime(), "HH:mm") + "~" + DateFormatUtils.format(f.getEndTime(), "HH:mm"));
			m.put("price", f.getSumMoney());
			m.put("isBooked", f.getState());
			data.add(m);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getSiteReservation()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return JSONObject.fromObject(json).toString();
	}

}