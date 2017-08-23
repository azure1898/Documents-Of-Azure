/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.balance.entity.BusinessBalance;
import com.its.modules.balance.service.BusinessBalanceService;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.service.BusinessInfoService;
import com.its.modules.sys.entity.Area;
import com.its.modules.sys.service.AreaService;

/**
 * 商家结算信息Controller
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/businessBalance")
public class BusinessBalanceController extends BaseController {

	@Autowired
	private BusinessBalanceService businessBalanceService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private AreaService areaService;

	@ModelAttribute
	public BusinessBalance get(@RequestParam(required = false) String id) {
		BusinessBalance entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = businessBalanceService.get(id);
		}
		if (entity == null) {
			entity = new BusinessBalance();
		}
		return entity;
	}

	@RequiresPermissions("balance:businessBalance:view")
	@RequestMapping(value = { "list", "" })
	public String list(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<BusinessBalance> page = businessBalanceService.findPage(new Page<BusinessBalance>(request, response),
				businessBalance);

		// 生成合计金额
		Double sumOrderMoney = 0.0;
		Double sumCouponMoney = 0.0;
		Double sumDeductionMoney = 0.0;
		Double sumPayMoney = 0.0;
		List<BusinessBalance> listBusinessBalance = businessBalanceService.findAllList(new BusinessBalance());
		// logger.warn("listBusinessBalance.size:" +
		// listBusinessBalance.size());
		for (BusinessBalance bb : listBusinessBalance) {
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

		// 所在城市、商家名称
		if (page.getList() != null && page.getList().size() > 0) {
			for (BusinessBalance bb : page.getList()) {
				BusinessInfo businessInfo = businessInfoService.get(bb.getBusinessInfoId());
				if (businessInfo != null) {
					bb.setBusinessName(businessInfo.getBusinessName());
					String cityId = businessInfo.getAddrCity();
					if (StringUtils.isNotEmpty(cityId)) {
						Area area = areaService.get(cityId);
						bb.setCityName(area.getName());
					}
				}
			}
		}
		model.addAttribute("sumOrderMoney", sumOrderMoney);
		model.addAttribute("sumCouponMoney", sumCouponMoney);
		model.addAttribute("sumDeductionMoney", sumDeductionMoney);
		model.addAttribute("sumPayMoney", sumPayMoney);
		model.addAttribute("page", page);
		return "modules/balance/businessBalanceList";
	}

	@RequiresPermissions("balance:businessBalance:view")
	@RequestMapping(value = "form")
	public String form(BusinessBalance businessBalance, Model model) {
		model.addAttribute("businessBalance", businessBalance);
		return "modules/balance/businessBalanceForm";
	}

	@RequiresPermissions("balance:businessBalance:edit")
	@RequestMapping(value = "save")
	public String save(BusinessBalance businessBalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessBalance)) {
			return form(businessBalance, model);
		}
		businessBalanceService.save(businessBalance);
		addMessage(redirectAttributes, "保存商家结算信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalance/?repage";
	}

	@RequiresPermissions("balance:businessBalance:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessBalance businessBalance, RedirectAttributes redirectAttributes) {
		businessBalanceService.delete(businessBalance);
		addMessage(redirectAttributes, "删除商家结算信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalance/?repage";
	}

	@RequiresPermissions("balance:businessBalance:edit")
	@RequestMapping(value = "batchBalance")
	public String batchBalance(BusinessBalance businessBalance, RedirectAttributes redirectAttributes) {
		businessBalanceService.batchBalance(businessBalance);
		addMessage(redirectAttributes, "勾选结算单结算成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalance/?repage";
	}
	
	@RequiresPermissions("balance:businessBalance:edit")
	@RequestMapping(value = "check", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> check(BusinessBalance businessBalance, RedirectAttributes redirectAttributes) {
		Map<String,Object> result = new HashMap<String, Object>();
		businessBalanceService.check(businessBalance);
		result.put("success", Boolean.TRUE);
		result.put("msg", "结算单核对成功");
		
		return result;
	}

	@RequiresPermissions("balance:businessBalance:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String export(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			double sumPayMoney = 0.0;
			String fileName = "结算申请单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<BusinessBalance> businessBalanceList = businessBalanceService.findBalanceApply(businessBalance);
			Date balanceTimeMin = DateUtils.addYears(new Date(), 1000);
			Date balanceTimeMax = DateUtils.addYears(new Date(), -1000);
			for (BusinessBalance bb : businessBalanceList) {
				// 获取payMoney合计
				if(bb.getPayMoney()!=null){
					sumPayMoney += bb.getPayMoney();
				}
				
				if (balanceTimeMin.compareTo(bb.getBalanceStartTime()) > 0) {
					balanceTimeMin = bb.getBalanceStartTime();
				}
				if (balanceTimeMax.compareTo(bb.getBalanceEndTime()) < 0) {
					balanceTimeMax = bb.getBalanceEndTime();
				}

			}
			
			// 设置合计对象
			BusinessBalance businessBalanceSum = new BusinessBalance();
			businessBalanceSum.setSerialNum("合计");
			businessBalanceSum.setPayMoney(sumPayMoney);
			businessBalanceList.add(businessBalanceSum);

			String title = "结算申请单（" + DateUtils.formatDate(balanceTimeMin, "yyyy.M.d") + "-"
					+ DateUtils.formatDate(balanceTimeMax, "yyyy.M.d") + "）";
			new ExportExcel(title, BusinessBalance.class).setDataList(businessBalanceList).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出结算单失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/balance/businessBalanceList?repage";
	}

	@RequiresPermissions("balance:businessBalance:view")
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public String print(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		List<BusinessBalance> businessBalanceList = businessBalanceService.findBalanceApply(businessBalance);
		double sumPayMoney = 0.0;
		Date balanceTimeMin = DateUtils.addYears(new Date(), 1000);
		Date balanceTimeMax = DateUtils.addYears(new Date(), -1000);
		for (BusinessBalance bb : businessBalanceList) {
			if (balanceTimeMin.compareTo(bb.getBalanceStartTime()) > 0) {
				balanceTimeMin = bb.getBalanceStartTime();
			}
			if (balanceTimeMax.compareTo(bb.getBalanceEndTime()) < 0) {
				balanceTimeMax = bb.getBalanceEndTime();
			}
			if(bb.getPayMoney()!=null){
				sumPayMoney += bb.getPayMoney();
			}

		}
		String title = "结算申请单（" + DateUtils.formatDate(balanceTimeMin, "yyyy.M.d") + "-"
				
				+ DateUtils.formatDate(balanceTimeMax, "yyyy.M.d") + "）";
		model.addAttribute("sumPayMoney",sumPayMoney);
		model.addAttribute("title", title);
		model.addAttribute("businessBalanceList", businessBalanceList);

		return "modules/balance/businessBalancePrint";
	}

}