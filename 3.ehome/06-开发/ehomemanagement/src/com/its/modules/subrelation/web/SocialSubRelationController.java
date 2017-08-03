/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.subrelation.web;

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
import com.its.modules.subrelation.entity.SocialSubRelation;
import com.its.modules.subrelation.service.SocialSubRelationService;

/**
 * 发言和话题关联Controller
 * @author wmm
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/subrelation/socialSubRelation")
public class SocialSubRelationController extends BaseController {

	@Autowired
	private SocialSubRelationService socialSubRelationService;
	
	@ModelAttribute
	public SocialSubRelation get(@RequestParam(required=false) String id) {
		SocialSubRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = socialSubRelationService.get(id);
		}
		if (entity == null){
			entity = new SocialSubRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("subrelation:socialSubRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SocialSubRelation socialSubRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialSubRelation> page = socialSubRelationService.findPage(new Page<SocialSubRelation>(request, response), socialSubRelation); 
		model.addAttribute("page", page);
		return "modules/subrelation/socialSubRelationList";
	}

	@RequiresPermissions("subrelation:socialSubRelation:view")
	@RequestMapping(value = "form")
	public String form(SocialSubRelation socialSubRelation, Model model) {
		model.addAttribute("socialSubRelation", socialSubRelation);
		return "modules/subrelation/socialSubRelationForm";
	}

	@RequiresPermissions("subrelation:socialSubRelation:edit")
	@RequestMapping(value = "save")
	public String save(SocialSubRelation socialSubRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, socialSubRelation)){
			return form(socialSubRelation, model);
		}
		socialSubRelationService.save(socialSubRelation);
		addMessage(redirectAttributes, "保存发言成功");
		return "redirect:"+Global.getAdminPath()+"/subrelation/socialSubRelation/?repage";
	}
	
	@RequiresPermissions("subrelation:socialSubRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialSubRelation socialSubRelation, RedirectAttributes redirectAttributes) {
		socialSubRelationService.delete(socialSubRelation);
		addMessage(redirectAttributes, "删除发言成功");
		return "redirect:"+Global.getAdminPath()+"/subrelation/socialSubRelation/?repage";
	}

}