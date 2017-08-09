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
import com.its.modules.app.entity.VillageLinerecomspecialdetail;
import com.its.modules.app.service.VillageLinerecomspecialdetailService;

/**
 * 楼盘产品线专题推荐明细Controller
 * @author sushipeng
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/app/villageLinerecomspecialdetail")
public class VillageLinerecomspecialdetailController extends BaseController {

	@Autowired
	private VillageLinerecomspecialdetailService villageLinerecomspecialdetailService;
	
	@ModelAttribute
	public VillageLinerecomspecialdetail get(@RequestParam(required=false) String id) {
		VillageLinerecomspecialdetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = villageLinerecomspecialdetailService.get(id);
		}
		if (entity == null){
			entity = new VillageLinerecomspecialdetail();
		}
		return entity;
	}
	
	@RequiresPermissions("app:villageLinerecomspecialdetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(VillageLinerecomspecialdetail villageLinerecomspecialdetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VillageLinerecomspecialdetail> page = villageLinerecomspecialdetailService.findPage(new Page<VillageLinerecomspecialdetail>(request, response), villageLinerecomspecialdetail); 
		model.addAttribute("page", page);
		return "modules/app/villageLinerecomspecialdetailList";
	}

	@RequiresPermissions("app:villageLinerecomspecialdetail:view")
	@RequestMapping(value = "form")
	public String form(VillageLinerecomspecialdetail villageLinerecomspecialdetail, Model model) {
		model.addAttribute("villageLinerecomspecialdetail", villageLinerecomspecialdetail);
		return "modules/app/villageLinerecomspecialdetailForm";
	}

	@RequiresPermissions("app:villageLinerecomspecialdetail:edit")
	@RequestMapping(value = "save")
	public String save(VillageLinerecomspecialdetail villageLinerecomspecialdetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, villageLinerecomspecialdetail)){
			return form(villageLinerecomspecialdetail, model);
		}
		villageLinerecomspecialdetailService.save(villageLinerecomspecialdetail);
		addMessage(redirectAttributes, "保存楼盘产品线专题推荐明细成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLinerecomspecialdetail/?repage";
	}
	
	@RequiresPermissions("app:villageLinerecomspecialdetail:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageLinerecomspecialdetail villageLinerecomspecialdetail, RedirectAttributes redirectAttributes) {
		villageLinerecomspecialdetailService.delete(villageLinerecomspecialdetail);
		addMessage(redirectAttributes, "删除楼盘产品线专题推荐明细成功");
		return "redirect:"+Global.getAdminPath()+"/app/villageLinerecomspecialdetail/?repage";
	}

}