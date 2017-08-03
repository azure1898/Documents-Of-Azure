/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.app.entity.BuildingInfo;
import com.its.modules.app.service.BuildingInfoService;

/**
 * 楼栋信息Controller
 * @author sushipeng
 * @version 2017-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/app/buildingInfo")
public class BuildingInfoController extends BaseController {

	@Autowired
	private BuildingInfoService buildingInfoService;
	
	@ModelAttribute
	public BuildingInfo get(@RequestParam(required=false) String id) {
		BuildingInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = buildingInfoService.get(id);
		}
		if (entity == null){
			entity = new BuildingInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("app:buildingInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BuildingInfo buildingInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BuildingInfo> page = buildingInfoService.findPage(new Page<BuildingInfo>(request, response), buildingInfo); 
		model.addAttribute("page", page);
		return "modules/app/buildingInfoList";
	}

	@RequiresPermissions("app:buildingInfo:view")
	@RequestMapping(value = "form")
	public String form(BuildingInfo buildingInfo, Model model) {
		model.addAttribute("buildingInfo", buildingInfo);
		return "modules/app/buildingInfoForm";
	}

	@RequiresPermissions("app:buildingInfo:edit")
	@RequestMapping(value = "save")
	public String save(BuildingInfo buildingInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, buildingInfo)){
			return form(buildingInfo, model);
		}
		buildingInfoService.save(buildingInfo);
		addMessage(redirectAttributes, "保存楼栋信息成功");
		return "redirect:"+Global.getAdminPath()+"/app/buildingInfo/?repage";
	}
	
	@RequiresPermissions("app:buildingInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BuildingInfo buildingInfo, RedirectAttributes redirectAttributes) {
		buildingInfoService.delete(buildingInfo);
		addMessage(redirectAttributes, "删除楼栋信息成功");
		return "redirect:"+Global.getAdminPath()+"/app/buildingInfo/?repage";
	}

}