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
import com.its.modules.balance.entity.LogPropertyBalance;
import com.its.modules.balance.service.LogPropertyBalanceService;

/**
 * 物业结算操作日志Controller
 * @author Liuqi
 * @version 2017-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/logPropertyBalance")
public class LogPropertyBalanceController extends BaseController {

	@Autowired
	private LogPropertyBalanceService logPropertyBalanceService;
	
	@ModelAttribute
	public LogPropertyBalance get(@RequestParam(required=false) String id) {
		LogPropertyBalance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = logPropertyBalanceService.get(id);
		}
		if (entity == null){
			entity = new LogPropertyBalance();
		}
		return entity;
	}
	
	@RequiresPermissions("balance:logPropertyBalance:view")
	@RequestMapping(value = {"list", ""})
	public String list(LogPropertyBalance logPropertyBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LogPropertyBalance> page = logPropertyBalanceService.findPage(new Page<LogPropertyBalance>(request, response), logPropertyBalance); 
		model.addAttribute("page", page);
		return "modules/balance/logPropertyBalanceList";
	}

	@RequiresPermissions("balance:logPropertyBalance:view")
	@RequestMapping(value = "form")
	public String form(LogPropertyBalance logPropertyBalance, Model model) {
		model.addAttribute("logPropertyBalance", logPropertyBalance);
		return "modules/balance/logPropertyBalanceForm";
	}

	@RequiresPermissions("balance:logPropertyBalance:edit")
	@RequestMapping(value = "save")
	public String save(LogPropertyBalance logPropertyBalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, logPropertyBalance)){
			return form(logPropertyBalance, model);
		}
		logPropertyBalanceService.save(logPropertyBalance);
		addMessage(redirectAttributes, "保存物业结算操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/balance/logPropertyBalance/?repage";
	}
	
	@RequiresPermissions("balance:logPropertyBalance:edit")
	@RequestMapping(value = "delete")
	public String delete(LogPropertyBalance logPropertyBalance, RedirectAttributes redirectAttributes) {
		logPropertyBalanceService.delete(logPropertyBalance);
		addMessage(redirectAttributes, "删除物业结算操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/balance/logPropertyBalance/?repage";
	}

}