/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.web;

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
import com.its.modules.field.entity.FieldInfoPrice;
import com.its.modules.field.service.FieldInfoPriceService;

/**
 * 场地预约子表_分段编辑临时表Controller
 * @author xzc
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/field/fieldInfoPrice")
public class FieldInfoPriceController extends BaseController {
	/**
	 * 场地预约子表_分段编辑临时表Service
	 */
	@Autowired
	private FieldInfoPriceService fieldInfoPriceService;
	
	@ModelAttribute
	public FieldInfoPrice get(@RequestParam(required=false) String id) {
		FieldInfoPrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fieldInfoPriceService.get(id);
		}
		if (entity == null){
			entity = new FieldInfoPrice();
		}
		return entity;
	}
	
	@RequiresPermissions("field:fieldInfoPrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(FieldInfoPrice fieldInfoPrice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FieldInfoPrice> page = fieldInfoPriceService.findPage(new Page<FieldInfoPrice>(request, response), fieldInfoPrice); 
		model.addAttribute("page", page);
		return "modules/field/fieldInfoPriceList";
	}

	@RequiresPermissions("field:fieldInfoPrice:view")
	@RequestMapping(value = "form")
	public String form(FieldInfoPrice fieldInfoPrice, Model model) {
		model.addAttribute("fieldInfoPrice", fieldInfoPrice);
		return "modules/field/fieldInfoPriceForm";
	}

	@RequiresPermissions("field:fieldInfoPrice:edit")
	@RequestMapping(value = "save")
	public String save(FieldInfoPrice fieldInfoPrice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fieldInfoPrice)){
			return form(fieldInfoPrice, model);
		}
		fieldInfoPriceService.save(fieldInfoPrice);
		addMessage(redirectAttributes, "保存场地预约子表_分段编辑临时表成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfoPrice/?repage";
	}
	
	@RequiresPermissions("field:fieldInfoPrice:edit")
	@RequestMapping(value = "delete")
	public String delete(FieldInfoPrice fieldInfoPrice, RedirectAttributes redirectAttributes) {
		fieldInfoPriceService.delete(fieldInfoPrice);
		addMessage(redirectAttributes, "删除场地预约子表_分段编辑临时表成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfoPrice/?repage";
	}

}