/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.web;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.balance.entity.PropertyBalanceDetail;
import com.its.modules.balance.service.PropertyBalanceDetailService;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.property.entity.PropertyCompany;
import com.its.modules.property.service.PropertyCompanyService;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.VillageInfoService;

/**
 * 物业结算明细信息Controller
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/propertyBalanceDetail")
public class PropertyBalanceDetailController extends BaseController {

	@Autowired
	private PropertyBalanceDetailService propertyBalanceDetailService;

	@Autowired
	private PropertyCompanyService propertyCompanyService;

	@Autowired
	private ModuleManageService moduleManageService;
	
	@Autowired
	private VillageInfoService villageInfoService;

	@ModelAttribute
	public PropertyBalanceDetail get(@RequestParam(required = false) String id) {
		PropertyBalanceDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = propertyBalanceDetailService.get(id);
		}
		if (entity == null) {
			entity = new PropertyBalanceDetail();
		}
		return entity;
	}

	@RequiresPermissions("balance:propertyBalanceDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(PropertyBalanceDetail propertyBalanceDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<PropertyBalanceDetail> page = propertyBalanceDetailService
				.findPage(new Page<PropertyBalanceDetail>(request, response), propertyBalanceDetail);
		
		// 生成合计金额
		Double sumOrderMoney = 0.0;
		Double sumCouponMoney = 0.0;
		Double sumDeductionMoney = 0.0;
		Double sumPayMoney = 0.0;
		List<PropertyBalanceDetail> listPropertyBalanceDetail = propertyBalanceDetailService.findAllList(propertyBalanceDetail);

		for (PropertyBalanceDetail bd : listPropertyBalanceDetail) {
			if (bd.getOrderMoney() != null) {
				sumOrderMoney += bd.getOrderMoney();
			}
			if (bd.getCouponMoney() != null) {
				sumCouponMoney += bd.getCouponMoney();
			}
			if (bd.getDeductionMoney() != null) {
				sumDeductionMoney += bd.getDeductionMoney();
			}
			if (bd.getPayMoney() != null) {
				sumPayMoney += bd.getPayMoney();
			}
		}
		model.addAttribute("sumOrderMoney", sumOrderMoney);
		model.addAttribute("sumCouponMoney", sumCouponMoney);
		model.addAttribute("sumDeductionMoney", sumDeductionMoney);
		model.addAttribute("sumPayMoney", sumPayMoney);

		// 公司名称、模块名称、楼盘名称
		if (page.getList() != null && page.getList().size() > 0) {
			for (PropertyBalanceDetail bd : page.getList()) {
				PropertyCompany propertyCompany = null;
				propertyCompany = propertyCompanyService.get(bd.getPropertyCompanyId());
				if (propertyCompany != null) {
					bd.setPropertyCompanyId(propertyCompany.getCompanyName());
				}
				ModuleManage moduleManage = moduleManageService.get(bd.getModuleId());
				if (moduleManage != null) {
					bd.setModuleId(moduleManage.getModuleName());
				}
				VillageInfo villageInfo = villageInfoService.get(bd.getVillageInfoId());
				if(villageInfo != null) {
					bd.setVillageInfoId(villageInfo.getVillageName());
				}
			}
		}

		model.addAttribute(propertyBalanceDetail);
		model.addAttribute("page", page);
		return "modules/balance/propertyBalanceDetailList";
	}

	@RequiresPermissions("balance:propertyBalanceDetail:view")
	@RequestMapping(value = "form")
	public String form(PropertyBalanceDetail propertyBalanceDetail, Model model) {
		model.addAttribute("propertyBalanceDetail", propertyBalanceDetail);
		return "modules/balance/propertyBalanceDetailForm";
	}

	@RequiresPermissions("balance:propertyBalanceDetail:edit")
	@RequestMapping(value = "save")
	public String save(PropertyBalanceDetail propertyBalanceDetail, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, propertyBalanceDetail)) {
			return form(propertyBalanceDetail, model);
		}
		propertyBalanceDetailService.save(propertyBalanceDetail);
		addMessage(redirectAttributes, "保存物业结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/propertyBalanceDetail/?repage";
	}

	@RequiresPermissions("balance:propertyBalanceDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PropertyBalanceDetail propertyBalanceDetail, RedirectAttributes redirectAttributes) {
		propertyBalanceDetailService.delete(propertyBalanceDetail);
		addMessage(redirectAttributes, "删除物业结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/propertyBalanceDetail/?repage";
	}

	@RequiresPermissions("balance:propertyBalanceDetail:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String export(PropertyBalanceDetail propertyBalanceDetail, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "结算申请单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<PropertyBalanceDetail> propertyBalanceDetailList = propertyBalanceDetailService.findExportList(propertyBalanceDetail);

			String title = "结算明细";
			new ExportExcel(title, PropertyBalanceDetail.class).setDataList(propertyBalanceDetailList).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出结算单明细失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/balance/propertyBalanceDetailList?repage";
	}

}