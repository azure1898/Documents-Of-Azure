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
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldPartitionPriceService;

/**
 * 场地预约子表-场地分段信息Controller
 * @author xzc
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/field/fieldPartitionPrice")
public class FieldPartitionPriceController extends BaseController {
	/**
	 * 场地预约子表-场地分段信息Service
	 */
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	
	@ModelAttribute
	public FieldPartitionPrice get(@RequestParam(required=false) String id) {
		FieldPartitionPrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fieldPartitionPriceService.get(id);
		}
		if (entity == null){
			entity = new FieldPartitionPrice();
		}
		return entity;
	}
	
	@RequiresPermissions("field:fieldPartitionPrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(FieldPartitionPrice fieldPartitionPrice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FieldPartitionPrice> page = fieldPartitionPriceService.findPage(new Page<FieldPartitionPrice>(request, response), fieldPartitionPrice); 
		model.addAttribute("page", page);
		return "modules/field/fieldPartitionPriceList";
	}

	@RequiresPermissions("field:fieldPartitionPrice:view")
	@RequestMapping(value = "form")
	public String form(FieldPartitionPrice fieldPartitionPrice, Model model) {
		model.addAttribute("fieldPartitionPrice", fieldPartitionPrice);
		return "modules/field/fieldPartitionPriceForm";
	}

	@RequiresPermissions("field:fieldPartitionPrice:edit")
	@RequestMapping(value = "save")
	public String save(FieldPartitionPrice fieldPartitionPrice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fieldPartitionPrice)){
			return form(fieldPartitionPrice, model);
		}
		fieldPartitionPriceService.save(fieldPartitionPrice);
		addMessage(redirectAttributes, "保存场地预约子表-场地分段信息成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldPartitionPrice/?repage";
	}
	
	@RequiresPermissions("field:fieldPartitionPrice:edit")
	@RequestMapping(value = "delete")
	public String delete(FieldPartitionPrice fieldPartitionPrice, RedirectAttributes redirectAttributes) {
		fieldPartitionPriceService.delete(fieldPartitionPrice);
		addMessage(redirectAttributes, "删除场地预约子表-场地分段信息成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldPartitionPrice/?repage";
	}

}