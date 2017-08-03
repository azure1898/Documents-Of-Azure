package com.its.modules.app.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.service.VillageLineService;

/**
 * 楼盘产品线及产品线设置管理Controller
 * 
 * @author sushipeng
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = "${appPath}/common")
public class VillageLineController extends BaseController {

	@Autowired
	private VillageLineService villageLineService;

	/**
	 * 获取导航信息
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getNavigation")
	@ResponseBody
	public Map<String, Object> getNavigation(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "产品线不存在");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = villageLineService.getMainNavigation(villageLine);
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}