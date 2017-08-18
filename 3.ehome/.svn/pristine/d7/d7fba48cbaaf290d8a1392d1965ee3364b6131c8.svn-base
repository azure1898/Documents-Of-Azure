package com.its.modules.app.web;

import java.text.NumberFormat;
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
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BraceletExerciseRecord;
import com.its.modules.app.entity.BraceletInfo;
import com.its.modules.app.service.BraceletExerciseRecordService;
import com.its.modules.app.service.BraceletInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 每日运动记录Controller
 * 
 * @author like
 * 
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
	 * @param bandMac
	 *            手环ID(不可空)
	 * @param data
	 *            Json运动数据(不可空)
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/submitMotionData", method = RequestMethod.POST)
	public Map<String, Object> submitMotionData(String userID, String buildingID, String bandMac, String data) {
		data = data.replaceAll("&quot;", "\"");
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, bandMac, data)) {
			return toJson;
		}
		BraceletInfo braceletInfo = braceletInfoService.getAccountBraceletSpe(userID, buildingID, bandMac);
		if (braceletInfo == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "手环尚未绑定");
			return toJson;
		}
		// 替换成类中的属性
		data = data.replaceAll("\"allNumData\":", "\"totalNumber\":")
				.replaceAll("\"date\":", "\"recordDate\":")
				.replaceAll("\"distance\":", "\"totalMileage\":")
				.replaceAll("\"distanceRun\":", "\"runMileage\":")
				.replaceAll("\"distanceWalk\":", "\"walkMileage\":")
				.replaceAll("\"kCal\":", "\"totalCalorie\":")
				.replaceAll("\"kCalRun\":", "\"runCalorie\":")
				.replaceAll("\"kCalWalk\":", "\"walkCalorie\":")
				.replaceAll("\"runNumData\":", "\"runNumber\":")
				.replaceAll("\"type\":", "\"type\":")
				.replaceAll("\"walkNumData\":", "\"walkNumber\":");
		
		
		JSONArray jsonArray = JSONArray.fromObject(data);
		if (jsonArray != null && jsonArray.size() != 0) {
			for (Object object : jsonArray) {
				JSONObject jsonobject = JSONObject.fromObject(object);
				Date recordDate = DateUtils.parseDate(jsonobject.get("recordDate"));
				// 查询当天已经上传的数据
				BraceletExerciseRecord record = braceletExerciseRecordService.getSpeAccountDateExercise(userID, buildingID, braceletInfo.getId(), recordDate);
				BraceletExerciseRecord exerciseRecord= (BraceletExerciseRecord) JSONObject.toBean(jsonobject, BraceletExerciseRecord.class);
				exerciseRecord.setAccountId(userID);
				exerciseRecord.setVillageinfoId(buildingID);
				exerciseRecord.setBraceletId(braceletInfo.getId());
				exerciseRecord.setRecordDate(recordDate);
				if (record == null) {
					braceletExerciseRecordService.save(exerciseRecord);
				} else {
					exerciseRecord.setId(record.getId());
					int row = braceletExerciseRecordService.update(exerciseRecord);
					System.out.println(row);
				}
			}
			
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "成功");
		return toJson;
	}

	/**
	 * 获取运动数据
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
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getMotionData")
	public String getMotionData(String userID, String buildingID, String bandMac, String startDate, String endDate) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandMac) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo info = braceletInfoService.getAccountBraceletSpe(userID, buildingID, bandMac);
		if (info == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手环尚未绑定");
			return JSONObject.fromObject(json).toString();
		}
		Date sDate= DateUtils.parseDate(startDate);
		Date eDate=DateUtils.parseDate(endDate);
		List<BraceletExerciseRecord> recordList = braceletExerciseRecordService.getAccountPeriodExerciseByBraceletId(userID, buildingID, info.getId(), sDate, eDate);
		List<BraceletExerciseRecord> recordListcpy=new ArrayList<BraceletExerciseRecord>();
		List<Map<String, Object>> details = new ArrayList<>();
		
		//找出没有数据的日期集合
		Map<BraceletExerciseRecord, Date> objDateMap=new HashMap<BraceletExerciseRecord, Date>();
		for(BraceletExerciseRecord b:recordList){
			objDateMap.put(b, b.getRecordDate());
		}
		int tianshu=AppUtils.dateSpan(sDate,eDate);
		String strDate="";
		Date dateDate=null;
		for(int i=0;i<=tianshu;i++){
			boolean flag=true;
			strDate=AppUtils.getSpecifiedDayAfter(sDate,i);
			dateDate=DateUtils.parseDate(strDate);
			for (Map.Entry<BraceletExerciseRecord, Date> entry : objDateMap.entrySet()) { 
				  if(entry.getValue().equals(dateDate)){
					  flag=false;
					//数据库有
					  recordListcpy.add(entry.getKey());
				  }
				}
			if(flag){
				//数据库无数据
				Map<String, Object> detail = new HashMap<>();
				detail.put("distance", 0);
				detail.put("kCal", 0);
				detail.put("allNumData", 0);
				detail.put("runNumData", 0);
				detail.put("distanceRun", 0);
				detail.put("kCalRun", 0);
				detail.put("walkNumData", 0);
				detail.put("distanceWalk", 0);
				detail.put("kCalWalk", 0);
				detail.put("targetStep", 10000);
				detail.put("targetPercent", "0%");
				detail.put("exercise", "较少");
				detail.put("date", DateFormatUtils.format(dateDate, "yyyy-MM-dd"));
				details.add(detail);
			}
		}
		//数据库数据处理
		Integer targets = info.getTargetStep();
		if(targets==null || targets==0){
			targets=10000;
		}
		int steps = 0;
		String exercise = "";
		for (BraceletExerciseRecord record : recordListcpy) {
			Map<String, Object> detail = new HashMap<>();
			steps = record.getTotalNumber();
			detail.put("distance", record.getTotalMileage());
			detail.put("kCal", record.getTotalCalorie());
			detail.put("allNumData", steps);
			detail.put("runNumData", record.getRunNumber());
			detail.put("distanceRun", record.getRunMileage());
			detail.put("kCalRun", record.getRunCalorie());
			detail.put("walkNumData", record.getWalkNumber());
			detail.put("distanceWalk", record.getWalkMileage());
			detail.put("kCalWalk", record.getWalkCalorie());

			detail.put("targetStep", targets);
			double percent = 0.00;
			if (targets > 0) {
				percent = steps * 1.00 / targets;
			}
			NumberFormat fmt = NumberFormat.getPercentInstance();
			String rates = fmt.format(percent);
			detail.put("targetPercent", rates);

			if (percent <= 0.50) {
				exercise = "较少";
			} else if (percent > 0.50 && percent <= 1.00) {
				exercise = "良好";
			} else {
				exercise = "过量";
			}
			detail.put("exercise", exercise);
			detail.put("date", DateFormatUtils.format(record.getRecordDate(), "yyyy-MM-dd"));
			details.add(detail);
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", details);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}