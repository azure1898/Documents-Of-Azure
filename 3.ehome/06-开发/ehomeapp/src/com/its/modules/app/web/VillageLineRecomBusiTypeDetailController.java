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
import com.its.modules.app.entity.VillageLineRecomBusiTypeDetail;
import com.its.modules.app.service.VillageLineRecomBusiTypeDetailService;

/**
 * 楼盘产品线推荐商家模式Controller
 * @author sushipeng
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/app/villageLineRecomBusiTypeDetail")
public class VillageLineRecomBusiTypeDetailController extends BaseController {

	@Autowired
	private VillageLineRecomBusiTypeDetailService villageLineRecomBusiTypeDetailService;
	
	@ModelAttribute
	public VillageLineRecomBusiTypeDetail get(@RequestParam(required=false) String id) {
		VillageLineRecomBusiTypeDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = villageLineRecomBusiTypeDetailService.get(id);
		}
		if (entity == null){
			entity = new VillageLineRecomBusiTypeDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("app:villageLineRecomBusiTypeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VillageLineRecomBusiTypeDetail> page = villageLineRecomBusiTypeDetailService.findPage(new Page<VillageLineRecomBusiTypeDetail>(request, response), villageLineRecomBusiTypeDetail); 
		model.addAttribute("page", page);
		return "modules/app/villageLineRecomBusiTypeDetailList";
	}

	@RequiresPermissions("app:villageLineRecomBusiTypeDetail:view")
	@RequestMapping(value = "form")
	public String form(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail, Model model) {
		model.addAttribute("villageLineRecomBusiTypeDetail", villageLineRecomBusiTypeDetail);
		return "modules/app/villageLineRecomBusiTypeDetailForm";
	}

	@RequiresPermissions("app:villageLineRecomBusiTypeDetail:edit")
	@RequestMapping(value = "save")
	public String save(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, villageLineRecomBusiTypeDetail)){
			return form(villageLineRecomBusiTypeDetail, model);
		}
		villageLineRecomBusiTypeDetailService.save(villageLineRecomBusiTypeDetail);
		addMessage(redirectAttributes, "保存楼盘产品线推荐商家模式成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLineRecomBusiTypeDetail/?repage";
	}
	
	@RequiresPermissions("app:villageLineRecomBusiTypeDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail, RedirectAttributes redirectAttributes) {
		villageLineRecomBusiTypeDetailService.delete(villageLineRecomBusiTypeDetail);
		addMessage(redirectAttributes, "删除楼盘产品线推荐商家模式成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLineRecomBusiTypeDetail/?repage";
	}

}