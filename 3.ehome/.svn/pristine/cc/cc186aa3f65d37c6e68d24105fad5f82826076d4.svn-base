/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.web;

import java.util.Arrays;
import java.util.List;

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
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.entity.BusinessServicescope;
import com.its.modules.business.service.BusinessInfoService;
import com.its.modules.business.service.BusinessServicescopeService;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.VillageInfoService;

/**
 * 商户服务范围Controller
 * @author liuqi
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/business/businessServicescope")
public class BusinessServicescopeController extends BaseController {

	@Autowired
	private BusinessServicescopeService businessServicescopeService;
	
	@Autowired
	private BusinessInfoService businessInfoService;
	
	@Autowired
	private VillageInfoService villageInfoService;
	
	@ModelAttribute
	public BusinessServicescope get(@RequestParam(required=false) String id) {
		BusinessServicescope entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessServicescopeService.get(id);
		}
		if (entity == null){
			entity = new BusinessServicescope();
		}
		return entity;
	}
	
	@RequiresPermissions("business:businessServicescope:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessServicescope businessServicescope, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessServicescope> page = businessServicescopeService.findPage(new Page<BusinessServicescope>(request, response), businessServicescope); 
		model.addAttribute("page", page);
		
		// 查询所有商家信息
		List<BusinessInfo> businessInfoList = businessInfoService.findList(new BusinessInfo());
		model.addAttribute("allBusinessInfo", businessInfoList);
		
		// 查询所有楼盘信息
		List<VillageInfo > villageInfoList  = villageInfoService.findList(new VillageInfo());
		model.addAttribute("allVillageInfo", villageInfoList);
		
		return "modules/business/businessServicescopeList";
	}

	@RequiresPermissions("business:businessServicescope:view")
	@RequestMapping(value = "form")
	public String form(BusinessServicescope businessServicescope, Model model) {
		model.addAttribute("businessServicescope", businessServicescope);
		// 查询所有商家信息
		List<BusinessInfo> businessInfoList = businessInfoService.findList(new BusinessInfo());
		model.addAttribute("allBusinessInfo", businessInfoList);
		
		// 查询所有楼盘信息
		List<VillageInfo > villageInfoList  = villageInfoService.findList(new VillageInfo());
		model.addAttribute("allVillageInfo", villageInfoList);
		return "modules/business/businessServicescopeForm";
	}

	@RequiresPermissions("business:businessServicescope:edit")
	@RequestMapping(value = "save")
	public String save(BusinessServicescope businessServicescope, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessServicescope)){
			return form(businessServicescope, model);
		}
		businessServicescopeService.save(businessServicescope);
		addMessage(redirectAttributes, "保存商户服务范围成功");
		return "redirect:"+Global.getAdminPath()+"/business/businessServicescope/?repage";
	}
	
	@RequiresPermissions("business:businessServicescope:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessServicescope businessServicescope, RedirectAttributes redirectAttributes) {
		businessServicescopeService.delete(businessServicescope);
		addMessage(redirectAttributes, "删除商户服务范围成功");
		return "redirect:"+Global.getAdminPath()+"/business/businessServicescope/?repage";
	}

}