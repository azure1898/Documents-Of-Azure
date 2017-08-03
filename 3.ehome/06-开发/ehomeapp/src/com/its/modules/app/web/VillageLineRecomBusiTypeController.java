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
import com.its.modules.app.entity.VillageLineRecomBusiType;
import com.its.modules.app.service.VillageLineRecomBusiTypeService;

/**
 * 楼盘产品线推荐商家模式Controller
 * @author sushipeng
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/app/villageLineRecomBusiType")
public class VillageLineRecomBusiTypeController extends BaseController {

	@Autowired
	private VillageLineRecomBusiTypeService villageLineRecomBusiTypeService;
	
	@ModelAttribute
	public VillageLineRecomBusiType get(@RequestParam(required=false) String id) {
		VillageLineRecomBusiType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = villageLineRecomBusiTypeService.get(id);
		}
		if (entity == null){
			entity = new VillageLineRecomBusiType();
		}
		return entity;
	}
	
	@RequiresPermissions("app:villageLineRecomBusiType:view")
	@RequestMapping(value = {"list", ""})
	public String list(VillageLineRecomBusiType villageLineRecomBusiType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VillageLineRecomBusiType> page = villageLineRecomBusiTypeService.findPage(new Page<VillageLineRecomBusiType>(request, response), villageLineRecomBusiType); 
		model.addAttribute("page", page);
		return "modules/app/villageLineRecomBusiTypeList";
	}

	@RequiresPermissions("app:villageLineRecomBusiType:view")
	@RequestMapping(value = "form")
	public String form(VillageLineRecomBusiType villageLineRecomBusiType, Model model) {
		model.addAttribute("villageLineRecomBusiType", villageLineRecomBusiType);
		return "modules/app/villageLineRecomBusiTypeForm";
	}

	@RequiresPermissions("app:villageLineRecomBusiType:edit")
	@RequestMapping(value = "save")
	public String save(VillageLineRecomBusiType villageLineRecomBusiType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, villageLineRecomBusiType)){
			return form(villageLineRecomBusiType, model);
		}
		villageLineRecomBusiTypeService.save(villageLineRecomBusiType);
		addMessage(redirectAttributes, "保存楼盘产品线推荐商家模式成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLineRecomBusiType/?repage";
	}
	
	@RequiresPermissions("app:villageLineRecomBusiType:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageLineRecomBusiType villageLineRecomBusiType, RedirectAttributes redirectAttributes) {
		villageLineRecomBusiTypeService.delete(villageLineRecomBusiType);
		addMessage(redirectAttributes, "删除楼盘产品线推荐商家模式成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLineRecomBusiType/?repage";
	}

}