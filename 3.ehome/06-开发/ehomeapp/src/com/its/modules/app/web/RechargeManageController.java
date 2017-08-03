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
import com.its.modules.app.entity.RechargeManage;
import com.its.modules.app.service.RechargeManageService;

import net.sf.json.JSONObject;

/**
 * 充值管理Controller
 * 
 * @author like
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class RechargeManageController extends BaseController {

	@Autowired
	private RechargeManageService rechargeManageService;

	@ResponseBody
	@RequestMapping(value = "/getRechargeData")
	public String getRechargeData(String userID, String buildingID) {
		if (StringUtils.isBlank(buildingID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<RechargeManage> list = rechargeManageService.getVillageRechargeList(buildingID);
		List<Map<String, Object>> data = new ArrayList<>();
		for (RechargeManage rm : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fixedMoney", rm.getRechargeMoney());
			map.put("giftMoney", rm.getGiveMoney());
			data.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

}