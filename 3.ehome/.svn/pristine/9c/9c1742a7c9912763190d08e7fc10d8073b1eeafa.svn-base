/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.SysArea;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.VillageInfoService;

import net.sf.json.JSONObject;

/**
 * 楼盘信息Controller
 * 
 * @author like
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${appPath}")
public class VillageInfoController extends BaseController {

	@Autowired
	private VillageInfoService villageInfoService;
	@Autowired
	private AccountService accountService;

	/**
	 * 获取所有有楼盘的城市列表
	 * 
	 * @param userID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/common/getCities")
	public String getCities(String userID) {
		List<SysArea> list = villageInfoService.findCities();
		String checkId = villageInfoService.getAccountCityID(userID);
		List<Map<String, Object>> data = new ArrayList<>();
		for (SysArea sa : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("cityID", sa.getId());
			map.put("cityName", sa.getName());
			if (StringUtils.isNotBlank(checkId)) {
				map.put("isChecked", 1);
			} else {
				map.put("isChecked", 0);
			}
			data.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取某一城市的楼盘列表
	 * 
	 * @param userID
	 * @param cityID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/common/getBuildings")
	public String list(String userID, String cityID) {
		// Account account = accountService.get(userID);
		// if (account == null) {
		// return "{\"code\":" + Global.CODE_PROMOT + ",message:'用户不存在'}";
		// }
		List<VillageInfo> list = villageInfoService.findCityVillageList(cityID);
		List<Map<String, Object>> data = new ArrayList<>();
		for (VillageInfo v : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("buildingID", v.getId());
			map.put("buildingName", v.getVillageName());
			map.put("isChecked", 0);
			data.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 保存用户的楼盘选择
	 * 
	 * @param userID
	 * @param buildingID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/common/saveUserBuilding")
	public String saveUserBuilding(String userID, String buildingID) {
		if (StringUtils.isBlank(buildingID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数错误\"}";
		}
		accountService.saveAccountVillageID(userID, buildingID);
		return "{\"code\":" + Global.CODE_SUCCESS + ",\"message\":\"成功\"}";
	}

}