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
import com.its.modules.app.service.BraceletExerciseRecordService;
import com.its.modules.app.service.BraceletInfoService;

import net.sf.json.JSONObject;

/**
 * 每日运动记录Controller
 * 
 * @author like
 * @version 2017-07-24
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class BraceletExerciseRecordController extends BaseController {

	@Autowired
	private BraceletExerciseRecordService braceletExerciseRecordService;
	@Autowired
	private BraceletInfoService braceletInfoService;

	/**
	 * 提交运动数据
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandID
	 *            手环ID(不可空)
	 * @param date
	 *            日期(不可空)
	 * @param motionType
	 *            运动类型(不可空)
	 * @param stepCounts
	 *            步数(数字，不可空)
	 * @param kilometers
	 *            公里数(数字，不可空)
	 * @param calories
	 *            消耗卡路里(数字，不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitMotionData", method = RequestMethod.POST)
	public String submitMotionData(String userID, String buildingID, String bandID, String date, String motionType, String stepCounts, String kilometers,
			String calories) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandID) || StringUtils.isBlank(date)
				|| StringUtils.isBlank(motionType) || !AppUtils.isNumeric(stepCounts) || !AppUtils.isNumeric(kilometers) || !AppUtils.isNumeric(calories)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		// 查询当天已经上传的数据
		BraceletExerciseRecord record = braceletExerciseRecordService.getAccountDateExercise(userID, buildingID, bandID, DateUtils.parseDate(date));
		if (record == null) {
			record = new BraceletExerciseRecord();
			record.setAccountId(userID);
			record.setVillageinfoId(buildingID);
			record.setBraceletId(bandID);
			record.setRecordDate(DateUtils.parseDate(date));
		}
		if ("1".equals(motionType)) {
			record.setWalkNumber(Integer.parseInt(stepCounts));
			record.setWalkMileage(Double.parseDouble(kilometers));
			record.setWalkCalorie(Double.parseDouble(calories));
			record.setRunNumber(0);
			record.setRunMileage((double) 0);
			record.setRunCalorie((double) 0);
		} else if ("2".equals(motionType)) {
			record.setRunNumber(Integer.parseInt(stepCounts));
			record.setRunMileage(Double.parseDouble(kilometers));
			record.setRunCalorie(Double.parseDouble(calories));
			record.setWalkNumber(0);
			record.setWalkMileage((double) 0);
			record.setWalkCalorie((double) 0);
		}
		braceletExerciseRecordService.save(record);
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取运动数据
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandID
	 *            手环ID(不可空)
	 * @param dates
	 *            日期(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMotionData")
	public String getMotionData(String userID, String buildingID, String bandID, String date) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandID) || StringUtils.isBlank(date)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo info = braceletInfoService.get(bandID);
		if (info == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手环尚未绑定");
			return JSONObject.fromObject(json).toString();
		}
		BraceletExerciseRecord record = braceletExerciseRecordService.getAccountDateExercise(userID, buildingID, bandID, DateUtils.parseDate(date));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("totalStep", record.getWalkNumber() + record.getRunNumber());
		data.put("totalKilometer", record.getWalkMileage() + record.getRunMileage());
		data.put("totalCalories", record.getWalkCalorie() + record.getRunCalorie());
		data.put("motionTarget", info.getTargetStep());
		List<Map<String, Object>> details = new ArrayList<>();
		Map<String, Object> walk = new HashMap<>();
		walk.put("motionType", 1);// 走
		walk.put("stepCounts", record.getWalkNumber());
		walk.put("kilometers", record.getWalkMileage());
		walk.put("calories", record.getWalkCalorie());
		details.add(walk);
		Map<String, Object> run = new HashMap<>();
		run.put("motionType", 2);// 跑
		run.put("stepCounts", record.getRunNumber());
		run.put("kilometers", record.getRunMileage());
		run.put("calories", record.getRunCalorie());
		details.add(run);
		data.put("details", details);
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取运动统计信息
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param bandID
	 *            手环ID
	 * @param week
	 *            周数(0->上周;1->本周)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMotionStatistics")
	public String getMotionStatistics(String userID, String buildingID, String bandID, String week) {
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
		List<BraceletExerciseRecord> recordList = braceletExerciseRecordService.getWeekExercise(userID, buildingID, bandID, weekDate.get(0), weekDate.get(1));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("period", DateFormatUtils.format(weekDate.get(0), "yyyy-MM-dd") + " - " + DateFormatUtils.format(weekDate.get(1), "yyyy-MM-dd"));
		int totalStep = 0;
		double totalKilometer = 0;
		double totalCalories = 0;
		List<Map<String, Object>> details = new ArrayList<>();
		for (BraceletExerciseRecord record : recordList) {
			Map<String, Object> detail = new HashMap<>();
			detail.put("date", DateFormatUtils.format(record.getRecordDate(), "yyyy-MM-dd"));
			detail.put("stepCounts", record.getWalkNumber() + record.getRunNumber());
			details.add(detail);
			totalStep += (record.getWalkNumber() + record.getRunNumber());
			totalKilometer += (record.getWalkMileage() + record.getRunMileage());
			totalCalories += (record.getWalkCalorie() + record.getRunCalorie());
		}
		data.put("totalStep", totalStep);
		data.put("totalKilometer", totalKilometer);
		data.put("totalCalories", totalCalories);
		data.put("details", details);
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}