package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.AccountApplication;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.service.AccountApplicationService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.VillageLineService;

/**
 * 会员的应用Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${appPath}/home")
public class AccountApplicationController extends BaseController {

	@Autowired
	private AccountApplicationService accountApplicationService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private VillageLineService villageLineService;

	/**
	 * 获取全部应用
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getAllApplication")
	@ResponseBody
	public Map<String, Object> getAllApplication(String userID, String buildingID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		VillageLine villageLine = villageLineService.getByVillageInfoId(buildingID);
		if (villageLine == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘产品线不存在");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

		/* ====================常用应用==================== */
		// 获取常用模块数据
		List<AccountApplication> commonApplications = accountApplicationService.getAccountApplicationList(userID, buildingID);
		List<String> commonStrs = new ArrayList<String>();
		for (AccountApplication accountApplication : commonApplications) {
			commonStrs.add(accountApplication.getModuleManageId());
		}
		List<ModuleManage> commonList = moduleManageService.getModuleManageList(commonStrs);

		Map<String, Object> commonApplication = new HashMap<String, Object>();
		List<Map<String, Object>> commonApps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < commonList.size(); i++) {
			ModuleManage moduleManage = commonList.get(i);
			Map<String, Object> app = new HashMap<String, Object>();
			app.put("moduleID", moduleManage.getId());
			app.put("moduleName", moduleManage.getModuleName());
			app.put("moduleType", moduleManage.getModuleType());
			app.put("moduleIcon", ValidateUtil.getImageUrl(moduleManage.getModuleIcon(), ValidateUtil.ZERO, request));
			app.put("sort", commonApplications.get(i).getSortNum());

			commonApps.add(app);
		}
		commonApplication.put("appType", 0);
		commonApplication.put("apps", commonApps);
		datas.add(commonApplication);

		/* ====================生活应用==================== */
		// 获取生活模块数据
		String lifeModule = villageLine.getLifeModule();
		List<String> lifeStrs = moduleManageService.getStrSplitList(lifeModule);
		// 去除用户常用应用中已设置的生活模块
		for (String string : commonStrs) {
			if (lifeStrs.contains(string)) {
				lifeStrs.remove(string);
			}
		}
		List<ModuleManage> lifeList = moduleManageService.getModuleManageList(lifeStrs);

		Map<String, Object> lifeApplication = new HashMap<String, Object>();
		List<Map<String, Object>> lifeApps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < lifeList.size(); i++) {
			ModuleManage moduleManage = lifeList.get(i);
			Map<String, Object> app = new HashMap<String, Object>();
			app.put("moduleID", moduleManage.getId());
			app.put("moduleName", moduleManage.getModuleName());
			app.put("moduleType", moduleManage.getModuleType());
			app.put("moduleIcon", ValidateUtil.getImageUrl(moduleManage.getModuleIcon(), ValidateUtil.ZERO, request));
			app.put("sort", (i + 1));

			lifeApps.add(app);
		}
		lifeApplication.put("appType", 1);
		lifeApplication.put("apps", lifeApps);
		datas.add(lifeApplication);

		/* ====================社区应用==================== */
		// 获取社区模块数据
		String communityModule = villageLine.getCommunityModule();
		List<String> communityStrs = moduleManageService.getStrSplitList(communityModule);
		// 去除用户常用应用中已设置的社区模块
		for (String string : commonStrs) {
			if (communityStrs.contains(string)) {
				communityStrs.remove(string);
			}
		}
		List<ModuleManage> communityList = moduleManageService.getModuleManageList(communityStrs);

		Map<String, Object> communityApplication = new HashMap<String, Object>();
		List<Map<String, Object>> communityApps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < communityList.size(); i++) {
			ModuleManage moduleManage = communityList.get(i);
			Map<String, Object> app = new HashMap<String, Object>();
			app.put("moduleID", moduleManage.getId());
			app.put("moduleName", moduleManage.getModuleName());
			app.put("moduleType", moduleManage.getModuleType());
			app.put("moduleIcon", ValidateUtil.getImageUrl(moduleManage.getModuleIcon(), ValidateUtil.ZERO, request));
			app.put("sort", (i + 1));

			communityApps.add(app);
		}
		communityApplication.put("appType", 2);
		communityApplication.put("apps", communityApps);
		datas.add(communityApplication);
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 编辑常用应用
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param modules
	 *            [{ "moduleID":"", "sort":1 }] moduleID：模块ID sort：排序
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "editMyApplication", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editMyApplication(String userID, String buildingID, String modules) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}

		// 判断是否更新成功
		if (!accountApplicationService.editMyApplication(userID, buildingID, modules)) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "应用更新失败");
			return toJson;
		}

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "应用更新成功");
		return toJson;
	}
}