/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.web;

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
import com.its.modules.balance.entity.LogBusinessBalance;
import com.its.modules.balance.service.LogBusinessBalanceService;

/**
 * 商户结算操作日志Controller
 * @author Liuqi
 * @version 2017-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/logBusinessBalance")
public class LogBusinessBalanceController extends BaseController {

	@Autowired
	private LogBusinessBalanceService logBusinessBalanceService;
	
	@ModelAttribute
	public LogBusinessBalance get(@RequestParam(required=false) String id) {
		LogBusinessBalance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = logBusinessBalanceService.get(id);
		}
		if (entity == null){
			entity = new LogBusinessBalance();
		}
		return entity;
	}
	
	@RequiresPermissions("balance:logBusinessBalance:view")
	@RequestMapping(value = {"list", ""})
	public String list(LogBusinessBalance logBusinessBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LogBusinessBalance> page = logBusinessBalanceService.findPage(new Page<LogBusinessBalance>(request, response), logBusinessBalance); 
		model.addAttribute("page", page);
		return "modules/balance/logBusinessBalanceList";
	}

	@RequiresPermissions("balance:logBusinessBalance:view")
	@RequestMapping(value = "form")
	public String form(LogBusinessBalance logBusinessBalance, Model model) {
		model.addAttribute("logBusinessBalance", logBusinessBalance);
		return "modules/balance/logBusinessBalanceForm";
	}

	@RequiresPermissions("balance:logBusinessBalance:edit")
	@RequestMapping(value = "save")
	public String save(LogBusinessBalance logBusinessBalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, logBusinessBalance)){
			return form(logBusinessBalance, model);
		}
		logBusinessBalanceService.save(logBusinessBalance);
		addMessage(redirectAttributes, "保存商户结算操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/balance/logBusinessBalance/?repage";
	}
	
	@RequiresPermissions("balance:logBusinessBalance:edit")
	@RequestMapping(value = "delete")
	public String delete(LogBusinessBalance logBusinessBalance, RedirectAttributes redirectAttributes) {
		logBusinessBalanceService.delete(logBusinessBalance);
		addMessage(redirectAttributes, "删除商户结算操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/balance/logBusinessBalance/?repage";
	}

}