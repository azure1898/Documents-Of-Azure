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
import com.its.modules.balance.entity.BusinessBalance;
import com.its.modules.balance.service.BusinessBalanceService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.service.AreaService;
import com.its.modules.sys.utils.UserUtils;

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

	@RequestMapping(value = { "list", "" })
	public String list(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		// 从SESSION中取得商家信息
		User user = UserUtils.getUser();

		// 只显示属于当前商家的结算单
		String businessInfoId = user.getBusinessinfoId();
		businessBalance.setBusinessInfoId(businessInfoId);
		Page<BusinessBalance> page = businessBalanceService.findPage(new Page<BusinessBalance>(request, response),
				businessBalance);
		
		model.addAttribute("page", page);
		return "modules/balance/businessBalanceList";
	}

	@RequestMapping(value = "form")
	public String form(BusinessBalance businessBalance, Model model) {
		model.addAttribute("businessBalance", businessBalance);
		return "modules/balance/businessBalanceForm";
	}

	@RequestMapping(value = "save")
	public String save(BusinessBalance businessBalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessBalance)) {
			return form(businessBalance, model);
		}
		businessBalanceService.save(businessBalance);
		addMessage(redirectAttributes, "保存商家结算信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalance/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(BusinessBalance businessBalance, RedirectAttributes redirectAttributes) {
		businessBalanceService.delete(businessBalance);
		addMessage(redirectAttributes, "删除商家结算信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalance/?repage";
	}
}