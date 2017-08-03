/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.business.entity.BusinessCategorydict;
import com.its.modules.business.service.BusinessCategorydictService;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;

/**
 * 商户分类Controller
 * 
 * @author ChenXiangyu
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/business/businessCategorydict")
public class BusinessCategorydictController extends BaseController {

	@Autowired
	private BusinessCategorydictService businessCategorydictService;
	@Autowired
	private ModuleManageService moduleManageService;

	@ModelAttribute
	public BusinessCategorydict get(@RequestParam(required = false) String id) {
		BusinessCategorydict entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = businessCategorydictService.get(id);
		}
		if (entity == null) {
			entity = new BusinessCategorydict();
		}
		return entity;
	}

	@RequiresPermissions("business:businessCategorydict:view")
	@RequestMapping(value = { "list", "" })
	public String list(BusinessCategorydict businessCategorydict, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<BusinessCategorydict> page = businessCategorydictService
				.findPage(new Page<BusinessCategorydict>(request, response), businessCategorydict);
		model.addAttribute("page", page);
		return "modules/business/businessCategorydictList";
	}

	@RequiresPermissions("business:businessCategorydict:view")
	@RequestMapping(value = "form")
	public String form(BusinessCategorydict businessCategorydict, Model model) {
		model.addAttribute("businessCategorydict", businessCategorydict);
		return "modules/business/businessCategorydictForm";
	}

	@RequiresPermissions(value = { "business:businessCategorydict:add",
			"business:businessCategorydict:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(BusinessCategorydict businessCategorydict, Model model, RedirectAttributes redirectAttributes) {
		// HTML转义特殊字符禁止转义
		businessCategorydict.setCategoryName(StringEscapeUtils.unescapeHtml4(businessCategorydict.getCategoryName()));
		if (businessCategorydict.getCategoryIntroduce() != null && businessCategorydict.getCategoryIntroduce() != "") {
			businessCategorydict.setCategoryIntroduce(StringEscapeUtils.unescapeHtml4(businessCategorydict.getCategoryIntroduce()));
		}
		
		if (!beanValidator(model, businessCategorydict)) {
			return form(businessCategorydict, model);
		}

		// 检测是否有模块名称与此商户分类名称相同的模块
		List<ModuleManage> moduleManageList = moduleManageService
				.getModuleByModuleName(businessCategorydict.getCategoryName());
		if (moduleManageList != null && !moduleManageList.isEmpty()) {
			if (!businessCategorydict.getIsNewRecord()) {
				// 编辑操作
				// 没有由该商户分类生成的模块信息 或 同名模块不是由该商户分类生成
				if (Global.NO.equals(moduleManageList.get(0).getBusinessCategoryDictFlag())
						|| !businessCategorydict.getId().equals(moduleManageList.get(0).getBusinessCategoryDictId())) {
					addMessage(model, "模块管理中已存在模块名称与此商户分类名称相同的模块，请重新命名该商户分类名");
					return form(businessCategorydict, model);
				}
			} else {
				// 新增操作
				addMessage(model, "模块管理中已存在模块名称与此商户分类名称相同的模块，请重新命名该商户分类名");
				return form(businessCategorydict, model);
			}
		}

		businessCategorydictService.save(businessCategorydict);
		addMessage(redirectAttributes, "保存商户分类成功");
		return "redirect:" + Global.getAdminPath() + "/business/businessCategorydict/?repage";
	}

	@RequiresPermissions("business:businessCategorydict:delete")
	@RequestMapping(value = "delete")
	public String delete(BusinessCategorydict businessCategorydict, RedirectAttributes redirectAttributes) {
		businessCategorydictService.delete(businessCategorydict);
		addMessage(redirectAttributes, "删除商户分类成功");
		return "redirect:" + Global.getAdminPath() + "/business/businessCategorydict/?repage";
	}

	/**
	 * 验证商户分类名是否有效
	 * 
	 * @param categoryname
	 *            商户分类名
	 * @return 是否有效
	 */
	@ResponseBody
	@RequiresPermissions("business:businessCategorydict:view")
	@RequestMapping(value = "checkCategoryname")
	public String checkCategoryname(String oldCategoryname, String categoryname) {
		if (categoryname != null && categoryname.equals(oldCategoryname)) {
			return "true";
		} else if (categoryname != null
				&& businessCategorydictService.getBusinessCategorydictByCategoryName(categoryname) == null) {
			return "true";
		}
		return "false";
	}
}