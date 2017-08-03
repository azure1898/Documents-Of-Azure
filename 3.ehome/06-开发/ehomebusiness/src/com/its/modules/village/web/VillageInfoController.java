/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.VillageInfoService;

/**
 * 楼盘信息Controller
 * 
 * @author zhujiao
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/village/villageInfo")
public class VillageInfoController extends BaseController {

	@Autowired
	private VillageInfoService villageInfoService;
	/** 状态：正常 */
	private static final String STATE_NOMAL = "0";

	@ModelAttribute
	public VillageInfo get(@RequestParam(required = false) String id) {
		VillageInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = villageInfoService.get(id);
		}
		if (entity == null) {
			entity = new VillageInfo();
		}
		return entity;
	}

	@RequiresPermissions("village:villageInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(VillageInfo villageInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VillageInfo> page = villageInfoService.findPage(new Page<VillageInfo>(request, response), villageInfo);
		model.addAttribute("page", page);
		return "modules/village/villageInfoList";
	}

	@RequiresPermissions("village:villageInfo:view")
	@RequestMapping(value = "form")
	public String form(VillageInfo villageInfo, Model model) {
		model.addAttribute("villageInfo", villageInfo);
		return "modules/village/villageInfoForm";
	}

	@RequiresPermissions("village:villageInfo:edit")
	@RequestMapping(value = "save")
	public String save(VillageInfo villageInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, villageInfo)) {
			return form(villageInfo, model);
		}
		villageInfoService.save(villageInfo);
		addMessage(redirectAttributes, "保存楼盘信息成功");
		return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
	}

	@RequiresPermissions("village:villageInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		villageInfoService.delete(villageInfo);
		addMessage(redirectAttributes, "删除楼盘信息成功");
		return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
	}

	@ResponseBody
	@RequiresPermissions("business:businessInfo:view")
	@RequestMapping(value = "getVillageTree")
	public List<VillageInfo> getVillageTree(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		List<VillageInfo> list = new ArrayList<>();

		list = villageInfoService.getVillageTree(villageInfo);
	
		return list;
	}
	
	/**
	 * 获取当前用户权限下的楼盘信息
	 * @param request
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findList", method={RequestMethod.POST,RequestMethod.GET})
	public List<VillageInfo> findList(HttpServletRequest request, @RequestParam String provinceId, @RequestParam String cityId){
		// 获取当前用户权限下的楼盘ID
		String villageInfoIdsStr = UserUtils.getUser().getVillageInfoIds();
		List<String> villageInfoIdList = new ArrayList<String>();
		if (villageInfoIdsStr != null && !villageInfoIdsStr.isEmpty()) {
			String[] villageInfoIds = villageInfoIdsStr.split(",");
			for (String villageInfoId : villageInfoIds) {
				if (villageInfoId != null && !villageInfoId.isEmpty()) {
					villageInfoIdList.add(villageInfoId);
				}
			}
		}
		
		// 根据省、市、用户权限下的楼盘ID、正常状态进行查询
		VillageInfo villageInfo = new VillageInfo();
		villageInfo.setAddrPro(provinceId);
		villageInfo.setAddrCity(cityId);
		villageInfo.setUserVillageIds(villageInfoIdList);
		villageInfo.setState(STATE_NOMAL);
		List<VillageInfo> VillageInfoList = villageInfoService.findList(villageInfo);
		return VillageInfoList;
	}
}