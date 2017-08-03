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
import com.its.modules.balance.entity.BusinessBalanceDetail;
import com.its.modules.balance.service.BusinessBalanceDetailService;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.service.BusinessInfoService;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;

/**
 * 商家结算明细信息Controller
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/businessBalanceDetail")
public class BusinessBalanceDetailController extends BaseController {

	@Autowired
	private BusinessBalanceDetailService businessBalanceDetailService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private ModuleManageService moduleManageService;

	@ModelAttribute
	public BusinessBalanceDetail get(@RequestParam(required = false) String id) {
		BusinessBalanceDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = businessBalanceDetailService.get(id);
		}
		if (entity == null) {
			entity = new BusinessBalanceDetail();
		}
		return entity;
	}

	@RequiresPermissions("balance:businessBalanceDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(BusinessBalanceDetail businessBalanceDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<BusinessBalanceDetail> page = businessBalanceDetailService
				.findPage(new Page<BusinessBalanceDetail>(request, response), businessBalanceDetail);
		
		// 生成合计金额
		Double sumOrderMoney = 0.0;
		Double sumCouponMoney = 0.0;
		Double sumDeductionMoney = 0.0;
		Double sumPayMoney = 0.0;
		List<BusinessBalanceDetail> listBusinessBalanceDetail = businessBalanceDetailService.findAllList(businessBalanceDetail);

		for (BusinessBalanceDetail bb : listBusinessBalanceDetail) {
			if (bb.getOrderMoney() != null) {
				sumOrderMoney += bb.getOrderMoney();
			}
			if (bb.getCouponMoney() != null) {
				sumCouponMoney += bb.getCouponMoney();
			}
			if (bb.getDeductionMoney() != null) {
				sumDeductionMoney += bb.getDeductionMoney();
			}
			if (bb.getPayMoney() != null) {
				sumPayMoney += bb.getPayMoney();
			}
		}
		model.addAttribute("sumOrderMoney", sumOrderMoney);
		model.addAttribute("sumCouponMoney", sumCouponMoney);
		model.addAttribute("sumDeductionMoney", sumDeductionMoney);
		model.addAttribute("sumPayMoney", sumPayMoney);

		// 商家名称、模块名称
		if (page.getList() != null && page.getList().size() > 0) {
			for (BusinessBalanceDetail bbd : page.getList()) {
				BusinessInfo businessInfo = null;
				try {
					businessInfo = businessInfoService.get(bbd.getBusinessInfoId());
					if (businessInfo != null) {
						bbd.setBusinessInfoId(businessInfo.getBusinessName());
					}
				} catch (NullPointerException e) {
					// businessInfo中没有要查找的商家信息，则商家名称不转换
					e.printStackTrace();
				}
				ModuleManage moduleManage = moduleManageService.get(bbd.getModuleId());
				if (moduleManage != null) {
					bbd.setModuleId(moduleManage.getModuleName());
				}
			}
		}

		model.addAttribute(businessBalanceDetail);
		model.addAttribute("page", page);
		return "modules/balance/businessBalanceDetailList";
	}

	@RequiresPermissions("balance:businessBalanceDetail:view")
	@RequestMapping(value = "form")
	public String form(BusinessBalanceDetail businessBalanceDetail, Model model) {
		model.addAttribute("businessBalanceDetail", businessBalanceDetail);
		return "modules/balance/businessBalanceDetailForm";
	}

	@RequiresPermissions("balance:businessBalanceDetail:edit")
	@RequestMapping(value = "save")
	public String save(BusinessBalanceDetail businessBalanceDetail, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessBalanceDetail)) {
			return form(businessBalanceDetail, model);
		}
		businessBalanceDetailService.save(businessBalanceDetail);
		addMessage(redirectAttributes, "保存商家结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalanceDetail/?repage";
	}

	@RequiresPermissions("balance:businessBalanceDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessBalanceDetail businessBalanceDetail, RedirectAttributes redirectAttributes) {
		businessBalanceDetailService.delete(businessBalanceDetail);
		addMessage(redirectAttributes, "删除商家结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalanceDetail/?repage";
	}

	@RequiresPermissions("balance:businessBalanceDetail:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String export(BusinessBalanceDetail businessBalanceDetail, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "结算申请单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<BusinessBalanceDetail> businessBalanceDetailList = businessBalanceDetailService
					.findExportList(businessBalanceDetail);

			String title = "结算明细";
			new ExportExcel(title, BusinessBalanceDetail.class).setDataList(businessBalanceDetailList)
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出结算单明细失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/balance/businessBalanceDetailList?repage";
	}

}