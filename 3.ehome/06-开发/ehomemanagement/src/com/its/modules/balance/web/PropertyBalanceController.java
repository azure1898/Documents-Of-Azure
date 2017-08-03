/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.web;

import java.util.Date;
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
import com.its.modules.balance.entity.PropertyBalance;
import com.its.modules.balance.service.PropertyBalanceService;
import com.its.modules.property.entity.PropertyCompany;
import com.its.modules.property.service.PropertyCompanyService;
import com.its.modules.sys.entity.Area;
import com.its.modules.sys.service.AreaService;

/**
 * 物业结算信息Controller
 * @author LiuQi
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/propertyBalance")
public class PropertyBalanceController extends BaseController {

	@Autowired
	private PropertyBalanceService propertyBalanceService;
	
	@Autowired
	private PropertyCompanyService propertyCompanyService;

	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public PropertyBalance get(@RequestParam(required=false) String id) {
		PropertyBalance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = propertyBalanceService.get(id);
		}
		if (entity == null){
			entity = new PropertyBalance();
		}
		return entity;
	}
	
	@RequiresPermissions("balance:propertyBalance:view")
	@RequestMapping(value = {"list", ""})
	public String list(PropertyBalance propertyBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PropertyBalance> page = propertyBalanceService.findPage(new Page<PropertyBalance>(request, response), propertyBalance);

		// 生成合计金额
		Double sumOrderMoney = 0.0;
		Double sumCouponMoney = 0.0;
		Double sumDeductionMoney = 0.0;
		Double sumPayMoney = 0.0;
		List<PropertyBalance> listPropertyBalance = propertyBalanceService.findAllList(new PropertyBalance());
		// logger.warn("listPropertyBalance.size:" +
		// listPropertyBalance.size());
		for (PropertyBalance pb : listPropertyBalance) {
			if (pb.getOrderMoney() != null) {
				sumOrderMoney += pb.getOrderMoney();
			}
			if (pb.getCouponMoney() != null) {
				sumCouponMoney += pb.getCouponMoney();
			}
			if (pb.getDeductionMoney() != null) {
				sumDeductionMoney += pb.getDeductionMoney();
			}
			if (pb.getPayMoney() != null) {
				sumPayMoney += pb.getPayMoney();
			}
		}

		// 所在城市、商家名称
		if (page.getList() != null && page.getList().size() > 0) {
			for (PropertyBalance pb : page.getList()) {
				PropertyCompany propertyCompany = propertyCompanyService.get(pb.getPropertyCompanyId());
				if (propertyCompany != null) {
					pb.setCompanyName(propertyCompany.getCompanyName());
					String cityId = propertyCompany.getAddrCity();
					if (StringUtils.isNotEmpty(cityId)) {
						Area area = areaService.get(cityId);
						pb.setCityName(area.getName());
					}
				}
			}
		}
		model.addAttribute("sumOrderMoney", sumOrderMoney);
		model.addAttribute("sumCouponMoney", sumCouponMoney);
		model.addAttribute("sumDeductionMoney", sumDeductionMoney);
		model.addAttribute("sumPayMoney", sumPayMoney); 
		model.addAttribute("page", page);
		return "modules/balance/propertyBalanceList";
	}

	@RequiresPermissions("balance:propertyBalance:view")
	@RequestMapping(value = "form")
	public String form(PropertyBalance propertyBalance, Model model) {
		model.addAttribute("propertyBalance", propertyBalance);
		return "modules/balance/propertyBalanceForm";
	}

	@RequiresPermissions("balance:propertyBalance:edit")
	@RequestMapping(value = "save")
	public String save(PropertyBalance propertyBalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, propertyBalance)){
			return form(propertyBalance, model);
		}
		propertyBalanceService.save(propertyBalance);
		addMessage(redirectAttributes, "保存物业结算信息成功");
		return "redirect:"+Global.getAdminPath()+"/balance/propertyBalance/?repage";
	}
	
	@RequiresPermissions("balance:propertyBalance:edit")
	@RequestMapping(value = "delete")
	public String delete(PropertyBalance propertyBalance, RedirectAttributes redirectAttributes) {
		propertyBalanceService.delete(propertyBalance);
		addMessage(redirectAttributes, "删除物业结算信息成功");
		return "redirect:"+Global.getAdminPath()+"/balance/propertyBalance/?repage";
	}

	/**
	 * 批量结算
	 * 批量将未结算单的结算单修改为已结算
	 * 
	 * @param propertyBalance
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("balance:propertyBalance:edit")
	@RequestMapping(value = "batchBalance")
	public String batchBalance(PropertyBalance propertyBalance, RedirectAttributes redirectAttributes) {
		propertyBalanceService.batchBalance(propertyBalance);
		addMessage(redirectAttributes, "勾选结算单结算成功");
		return "redirect:" + Global.getAdminPath() + "/balance/propertyBalance/?repage";
	}

	/**
	 * 对异常或者未核对的结算记录进行核对
	 * 
	 * @param propertyBalance
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("balance:propertyBalance:edit")
	@RequestMapping(value = "check")
	public String check(PropertyBalance propertyBalance, RedirectAttributes redirectAttributes) {
		propertyBalanceService.check(propertyBalance);
		addMessage(redirectAttributes, "勾选结算单核对成功");
		return "redirect:" + Global.getAdminPath() + "/balance/propertyBalance/?repage";
	}
	
	@RequiresPermissions("balance:propertyBalance:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String export(PropertyBalance propertyBalance, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "结算申请单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<PropertyBalance> propertyBalanceList = propertyBalanceService.findBalanceApply(propertyBalance);
			Date balanceTimeMin = DateUtils.addYears(new Date(), 1000);
			Date balanceTimeMax = DateUtils.addYears(new Date(), -1000);
			for (PropertyBalance pb : propertyBalanceList) {
				if (balanceTimeMin.compareTo(pb.getBalanceStartTime()) > 0) {
					balanceTimeMin = pb.getBalanceStartTime();
				}
				if (balanceTimeMax.compareTo(pb.getBalanceEndTime()) < 0) {
					balanceTimeMax = pb.getBalanceEndTime();
				}

			}

			String title = "结算申请单（" + DateUtils.formatDate(balanceTimeMin, "yyyy.M.d") + "-"
					+ DateUtils.formatDate(balanceTimeMax, "yyyy.M.d") + "）";
			new ExportExcel(title, PropertyBalance.class).setDataList(propertyBalanceList).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出结算单失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/balance/propertyBalance/?repage";
	}

	@RequiresPermissions("balance:propertyBalance:view")
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public String print(PropertyBalance propertyBalance, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		List<PropertyBalance> propertyBalanceList = propertyBalanceService.findBalanceApply(propertyBalance);
		double sumPayMoney = 0.0;
		Date balanceTimeMin = DateUtils.addYears(new Date(), 1000);
		Date balanceTimeMax = DateUtils.addYears(new Date(), -1000);
		for (PropertyBalance pb : propertyBalanceList) {
			if (balanceTimeMin.compareTo(pb.getBalanceStartTime()) > 0) {
				balanceTimeMin = pb.getBalanceStartTime();
			}
			if (balanceTimeMax.compareTo(pb.getBalanceEndTime()) < 0) {
				balanceTimeMax = pb.getBalanceEndTime();
			}
			if(pb.getPayMoney()!=null){
				sumPayMoney += pb.getPayMoney();
			}

		}
		String title = "结算申请单（" + DateUtils.formatDate(balanceTimeMin, "yyyy.M.d") + "-"
				
				+ DateUtils.formatDate(balanceTimeMax, "yyyy.M.d") + "）";
		model.addAttribute("sumPayMoney",sumPayMoney);
		model.addAttribute("title", title);
		model.addAttribute("propertyBalanceList", propertyBalanceList);

		return "modules/balance/propertyBalancePrint";
	}

}