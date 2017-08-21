/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.entity.BraceletExerciseRecord;
import com.its.modules.app.entity.BraceletInfo;
import com.its.modules.app.entity.BraceletSleepRecord;
import com.its.modules.app.service.BraceletInfoService;
import com.its.modules.app.service.BraceletSleepRecordService;

import net.sf.json.JSONObject;

/**
 * 手环睡眠记录Controller
 * 
 * @author like
 * @version 2017-07-24
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class BraceletSleepRecordController extends BaseController {

	@Autowired
	private BraceletSleepRecordService braceletSleepRecordService;
	@Autowired
	private BraceletInfoService braceletInfoService;

	/**
	 * 提交睡眠数据
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandMac
	 *            手环Mac(不可空)
	 * @param date
	 *            日期(不可空)
	 * @param sleepTime
	 *            睡眠总时长(数字类型、不可空)
	 * @param sleepStart
	 *            睡眠开始时间(日期类型、不可空)
	 * @param sleepEnd
	 *            睡眠结束时间(日期类型、不可空)
	 * @param sleepTimeDeep
	 *            深睡时长(数字类型、不可空)
	 * @param shallowTime
	 *            浅睡时长(数字类型、不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitSleepData", method = RequestMethod.POST)
	public String submitSleepData(String userID, String buildingID, String bandMac, String date, String sleepTimeTotal, String sleepStart, String sleepEnd, String sleepTimeDeep, String sleepTimeLight) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandMac) || StringUtils.isBlank(date) || !AppUtils.isNumeric(sleepTimeTotal) || StringUtils.isBlank(sleepStart) || StringUtils.isBlank(sleepEnd) || !AppUtils.isNumeric(sleepTimeDeep) || !AppUtils.isNumeric(sleepTimeLight)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo braceletInfo = braceletInfoService.getAccountBraceletSpe(userID, buildingID, bandMac);
		if (braceletInfo == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手环尚未绑定");
			return JSONObject.fromObject(json).toString();
		}
		//
		BraceletSleepRecord record = braceletSleepRecordService.getAccountDayRecord(userID, buildingID, braceletInfo.getId(), DateUtils.parseDate(date));
		if (record == null) {
			record = new BraceletSleepRecord();
			record.setAccountId(userID);
			record.setVillageinfoId(buildingID);
			record.setBraceletId(braceletInfo.getId());
			record.setRecordDate(DateUtils.parseDate(date));
			
			record.setSleepTime(Double.parseDouble(sleepTimeTotal));
			record.setDeepSleepTime(Double.parseDouble(sleepTimeDeep));
			record.setLightSleepTime(Double.parseDouble(sleepTimeLight));
			record.setSleepStart(sleepStart);
			record.setSleepEnd(sleepEnd);
			braceletSleepRecordService.save(record);
		}else{
			record.setSleepTime(Double.parseDouble(sleepTimeTotal));
			record.setDeepSleepTime(Double.parseDouble(sleepTimeDeep));
			record.setLightSleepTime(Double.parseDouble(sleepTimeLight));
			record.setSleepStart(sleepStart);
			record.setSleepEnd(sleepEnd);
			int row = braceletSleepRecordService.update(record);
			System.out.println(row);
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取睡眠数据
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandMac
	 *            手环mac(不可空)
	 * @param startDate
	 *            开始日期(不可空)
	 * @param endDate
	 *            结束日期(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSleepData")
	public String getSleepData(String userID, String buildingID, String bandMac, String startDate, String endDate) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandMac) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo braceletInfo = braceletInfoService.getAccountBraceletSpe(userID, buildingID, bandMac);
		if (braceletInfo == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手环尚未绑定");
			return JSONObject.fromObject(json).toString();
		}
		Date sDate = DateUtils.parseDate(startDate);
		Date eDate = DateUtils.parseDate(endDate);
		List<BraceletSleepRecord> recordList = braceletSleepRecordService.getPeriodSleepRecordByBraceletId(userID, buildingID, braceletInfo.getId(), sDate, eDate);

		List<BraceletSleepRecord> recordListcpy = new ArrayList<BraceletSleepRecord>();
		List<Map<String, Object>> details = new ArrayList<>();

		// 找出没有数据的日期集合
		Map<BraceletSleepRecord, Date> objDateMap = new HashMap<BraceletSleepRecord, Date>();
		for (BraceletSleepRecord b : recordList) {
			objDateMap.put(b, b.getRecordDate());
		}
		int tianshu = AppUtils.dateSpan(sDate, eDate);
		String strDate = "";
		Date dateDate = null;
		for (int i = 0; i <= tianshu; i++) {
			boolean flag = true;
			strDate = AppUtils.getSpecifiedDayAfter(sDate, i);
			dateDate = DateUtils.parseDate(strDate);
			for (Map.Entry<BraceletSleepRecord, Date> entry : objDateMap.entrySet()) {
				if (entry.getValue().equals(dateDate)) {
					flag = false;
					// 数据库有
					recordListcpy.add(entry.getKey());
				}
			}
			if (flag) {
				// 数据库无数据
				Map<String, Object> detail = new HashMap<>();
				detail.put("sleepTimeTotal", 0);
				detail.put("sleepTimeDeep", 0);
				detail.put("sleepTimeLight", 0);
				detail.put("sleepStart", "");
				detail.put("sleepEnd", "");
				detail.put("date", DateFormatUtils.format(dateDate, "yyyy-MM-dd"));
				details.add(detail);
			}

		}
		// 数据库数据处理
		for (BraceletSleepRecord record : recordList) {
			Map<String, Object> detail = new HashMap<>();
			detail.put("sleepTimeTotal", record.getSleepTime());
			detail.put("sleepTimeDeep", record.getDeepSleepTime());
			detail.put("sleepTimeLight", record.getLightSleepTime());
			detail.put("sleepStart",record.getSleepStart());
			detail.put("sleepEnd", record.getSleepEnd());
			detail.put("date", DateFormatUtils.format(record.getRecordDate(), "yyyy-MM-dd"));
			details.add(detail);
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", details);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取睡眠统计信息
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandID
	 *            手环ID(不可空)
	 * @param week
	 *            周数(0->上周;1->本周)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSleepStatistics")
	public String getSleepStatistics(String userID, String buildingID, String bandID, String week) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandID) || !AppUtils.isNumeric(week)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		List<Date> weekDate = null;
		if ("1".equals(week)) {
			weekDate = AppUtils.getWeek(new Date(), 1);
		} else if ("0".equals(week)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_YEAR, -7);
			weekDate = AppUtils.getWeek(cal.getTime(), 1);
		}
		if (weekDate == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		List<BraceletSleepRecord> recordList = braceletSleepRecordService.getWeekSleepRecord(userID, buildingID, bandID, weekDate.get(0), weekDate.get(1));
		data.put("period", DateFormatUtils.format(weekDate.get(0), "yyyy-MM-dd") + " - " + DateFormatUtils.format(weekDate.get(1), "yyyy-MM-dd"));
		double totalTime = 0;
		double totalDeep = 0;
		double totalShallow = 0;
		List<Map<String, Object>> details = new ArrayList<>();
		for (BraceletSleepRecord record : recordList) {
			Map<String, Object> detail = new HashMap<>();
			detail.put("sleepTime", record.getSleepTime());
			details.add(detail);
			totalTime += record.getSleepTime();
			totalDeep += record.getDeepSleepTime();
			totalShallow += record.getLightSleepTime();
		}
		data.put("totalTime", totalTime);
		double averageTime = totalTime / (AppUtils.dateSpan(weekDate.get(0), new Date()) + 1);
		data.put("averageTime", Double.parseDouble(new java.text.DecimalFormat("#.00").format(averageTime)));
		data.put("totalDeep", totalDeep);
		data.put("totalShallow", totalShallow);
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}