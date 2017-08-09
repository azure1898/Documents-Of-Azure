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
import com.its.modules.app.entity.VillageLinerecomspecial;
import com.its.modules.app.service.VillageLinerecomspecialService;

/**
 * 楼盘产品线专题推荐Controller
 * @author sushipeng
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/app/villageLinerecomspecial")
public class VillageLinerecomspecialController extends BaseController {

	@Autowired
	private VillageLinerecomspecialService villageLinerecomspecialService;
	
	@ModelAttribute
	public VillageLinerecomspecial get(@RequestParam(required=false) String id) {
		VillageLinerecomspecial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = villageLinerecomspecialService.get(id);
		}
		if (entity == null){
			entity = new VillageLinerecomspecial();
		}
		return entity;
	}
	
	@RequiresPermissions("app:villageLinerecomspecial:view")
	@RequestMapping(value = {"list", ""})
	public String list(VillageLinerecomspecial villageLinerecomspecial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VillageLinerecomspecial> page = villageLinerecomspecialService.findPage(new Page<VillageLinerecomspecial>(request, response), villageLinerecomspecial); 
		model.addAttribute("page", page);
		return "modules/app/villageLinerecomspecialList";
	}

	@RequiresPermissions("app:villageLinerecomspecial:view")
	@RequestMapping(value = "form")
	public String form(VillageLinerecomspecial villageLinerecomspecial, Model model) {
		model.addAttribute("villageLinerecomspecial", villageLinerecomspecial);
		return "modules/app/villageLinerecomspecialForm";
	}

	@RequiresPermissions("app:villageLinerecomspecial:edit")
	@RequestMapping(value = "save")
	public String save(VillageLinerecomspecial villageLinerecomspecial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, villageLinerecomspecial)){
			return form(villageLinerecomspecial, model);
		}
		villageLinerecomspecialService.save(villageLinerecomspecial);
		addMessage(redirectAttributes, "保存楼盘产品线专题推荐成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLinerecomspecial/?repage";
	}
	
	@RequiresPermissions("app:villageLinerecomspecial:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageLinerecomspecial villageLinerecomspecial, RedirectAttributes redirectAttributes) {
		villageLinerecomspecialService.delete(villageLinerecomspecial);
		addMessage(redirectAttributes, "删除楼盘产品线专题推荐成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLinerecomspecial/?repage";
	}

}