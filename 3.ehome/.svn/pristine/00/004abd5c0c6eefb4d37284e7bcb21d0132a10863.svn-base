/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.forward.web;

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
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.comment.entity.SocialComment;
import com.its.modules.forward.entity.SocialForward;
import com.its.modules.forward.service.SocialForwardService;

/**
 * 转发Controller
 * @author wmm
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/forward/socialForward")
public class SocialForwardController extends BaseController {

	@Autowired
	private SocialForwardService socialForwardService;
	
	@ModelAttribute
	public SocialForward get(@RequestParam(required=false) String id) {
		SocialForward entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = socialForwardService.get(id);
		}
		if (entity == null){
			entity = new SocialForward();
		}
		return entity;
	}
	
	@RequiresPermissions("forward:socialForward:view")
	@RequestMapping(value = {"list", ""})
	public String list(SocialForward socialForward, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialForward> page = socialForwardService.findPage(new Page<SocialForward>(request, response), socialForward); 
		model.addAttribute("page", page);
		return "modules/forward/socialForwardList";
	}

	@RequiresPermissions("forward:socialForward:view")
	@RequestMapping(value = "form")
	public String form(SocialForward socialForward, Model model) {
		model.addAttribute("socialForward", socialForward);
		return "modules/forward/socialForwardForm";
	}

	@RequiresPermissions("forward:socialForward:edit")
	@RequestMapping(value = "save")
	public String save(SocialForward socialForward, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, socialForward)){
			return form(socialForward, model);
		}
		socialForwardService.save(socialForward);
		addMessage(redirectAttributes, "保存转发成功");
		return "redirect:"+Global.getAdminPath()+"/forward/socialForward/?repage";
	}
	
	@RequiresPermissions("forward:socialForward:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialForward socialForward, RedirectAttributes redirectAttributes) {
		socialForwardService.delete(socialForward);
		addMessage(redirectAttributes, "删除转发成功");
		return "redirect:"+Global.getAdminPath()+"/forward/socialForward/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value="changeDelFlag", method={RequestMethod.POST,RequestMethod.GET})
	public String changeDelFlag(String id, RedirectAttributes redirectAttributes) {
		String result = "1";
		SocialForward socialForward = new SocialForward();
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/forward/socialForward/?repage";
		}
		socialForward.setId(id);
		socialForward.setDelflag("0");
		int resultNum = socialForwardService.changeDelFlag(socialForward);
		if(resultNum == 0) {
			result = "0";
		}
		return result;
	}

}