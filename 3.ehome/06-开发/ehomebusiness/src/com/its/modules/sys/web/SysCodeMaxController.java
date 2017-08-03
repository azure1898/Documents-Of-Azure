/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.web;

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
import com.its.modules.sys.entity.SysCodeMax;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 最大编码表Controller
 * @author xzc
 * @version 2017-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysCodeMax")
public class SysCodeMaxController extends BaseController {

	@Autowired
	private SysCodeMaxService sysCodeMaxService;
	
	@ModelAttribute
	public SysCodeMax get(@RequestParam(required=false) String id) {
		SysCodeMax entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysCodeMaxService.get(id);
		}
		if (entity == null){
			entity = new SysCodeMax();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysCodeMax:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysCodeMax sysCodeMax, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysCodeMax> page = sysCodeMaxService.findPage(new Page<SysCodeMax>(request, response), sysCodeMax); 
		model.addAttribute("page", page);
		return "modules/sys/sysCodeMaxList";
	}

	@RequiresPermissions("sys:sysCodeMax:view")
	@RequestMapping(value = "form")
	public String form(SysCodeMax sysCodeMax, Model model) {
		model.addAttribute("sysCodeMax", sysCodeMax);
		return "modules/sys/sysCodeMaxForm";
	}

	@RequiresPermissions("sys:sysCodeMax:edit")
	@RequestMapping(value = "save")
	public String save(SysCodeMax sysCodeMax, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysCodeMax)){
			return form(sysCodeMax, model);
		}
		sysCodeMaxService.save(sysCodeMax);
		addMessage(redirectAttributes, "保存最大编码表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodeMax/?repage";
	}
	
	@RequiresPermissions("sys:sysCodeMax:edit")
	@RequestMapping(value = "delete")
	public String delete(SysCodeMax sysCodeMax, RedirectAttributes redirectAttributes) {
		sysCodeMaxService.delete(sysCodeMax);
		addMessage(redirectAttributes, "删除最大编码表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodeMax/?repage";
	}

}