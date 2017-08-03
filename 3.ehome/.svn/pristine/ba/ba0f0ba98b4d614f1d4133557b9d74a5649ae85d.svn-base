package com.its.modules.app.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.service.AdverManageService;

import net.sf.json.JSONObject;

/**
 * 广告管理Controller
 * 
 * @author like
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = { "${appPath}/common", "${appPath}/live" })
public class AdverManageController extends BaseController {

	@Autowired
	private AdverManageService adverManageService;

	@ResponseBody
	@RequestMapping(value = "getOpenAd")
	public String getOpenAd(String buildingID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(buildingID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}

		Map<String, Object> data = new HashMap<String, Object>();

		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "登录成功");
		return JSONObject.fromObject(json).toString();
	}
}