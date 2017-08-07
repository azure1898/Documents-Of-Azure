/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.praise.web;

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
import com.its.modules.praise.entity.SocialPraise;
import com.its.modules.praise.service.SocialPraiseService;

/**
 * 点赞Controller
 * @author wmm
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/praise/socialPraise")
public class SocialPraiseController extends BaseController {

	@Autowired
	private SocialPraiseService socialPraiseService;
	
	@ModelAttribute
	public SocialPraise get(@RequestParam(required=false) String id) {
		SocialPraise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = socialPraiseService.get(id);
		}
		if (entity == null){
			entity = new SocialPraise();
		}
		return entity;
	}
	
	@RequiresPermissions("praise:socialPraise:view")
	@RequestMapping(value = {"list", ""})
	public String list(SocialPraise socialPraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialPraise> page = socialPraiseService.findPage(new Page<SocialPraise>(request, response), socialPraise); 
		model.addAttribute("page", page);
		return "modules/praise/socialPraiseList";
	}

	@RequiresPermissions("praise:socialPraise:view")
	@RequestMapping(value = "form")
	public String form(SocialPraise socialPraise, Model model) {
		model.addAttribute("socialPraise", socialPraise);
		return "modules/praise/socialPraiseForm";
	}

	@RequiresPermissions("praise:socialPraise:edit")
	@RequestMapping(value = "save")
	public String save(SocialPraise socialPraise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, socialPraise)){
			return form(socialPraise, model);
		}
		socialPraiseService.save(socialPraise);
		addMessage(redirectAttributes, "保存点赞成功");
		return "redirect:"+Global.getAdminPath()+"/praise/socialPraise/?repage";
	}
	
	@RequiresPermissions("praise:socialPraise:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialPraise socialPraise, RedirectAttributes redirectAttributes) {
		socialPraiseService.delete(socialPraise);
		addMessage(redirectAttributes, "删除点赞成功");
		return "redirect:"+Global.getAdminPath()+"/praise/socialPraise/?repage";
	}

}