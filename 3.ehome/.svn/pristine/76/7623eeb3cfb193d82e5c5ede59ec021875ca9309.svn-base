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
import com.its.modules.balance.entity.DownloadBill;
import com.its.modules.balance.service.DownloadBillService;

/**
 * 结算对账单Controller
 * @author Liuqi
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/downloadBill")
public class DownloadBillController extends BaseController {

	@Autowired
	private DownloadBillService downloadBillService;
	
	@ModelAttribute
	public DownloadBill get(@RequestParam(required=false) String id) {
		DownloadBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = downloadBillService.get(id);
		}
		if (entity == null){
			entity = new DownloadBill();
		}
		return entity;
	}
	
	@RequiresPermissions("balance:downloadBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(DownloadBill downloadBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DownloadBill> page = downloadBillService.findPage(new Page<DownloadBill>(request, response), downloadBill); 
		model.addAttribute("page", page);
		return "modules/balance/downloadBillList";
	}

	@RequiresPermissions("balance:downloadBill:view")
	@RequestMapping(value = "form")
	public String form(DownloadBill downloadBill, Model model) {
		model.addAttribute("downloadBill", downloadBill);
		return "modules/balance/downloadBillForm";
	}

	@RequiresPermissions("balance:downloadBill:edit")
	@RequestMapping(value = "save")
	public String save(DownloadBill downloadBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, downloadBill)){
			return form(downloadBill, model);
		}
		downloadBillService.save(downloadBill);
		addMessage(redirectAttributes, "保存对账单成功");
		return "redirect:"+Global.getAdminPath()+"/balance/downloadBill/?repage";
	}
	
	@RequiresPermissions("balance:downloadBill:edit")
	@RequestMapping(value = "delete")
	public String delete(DownloadBill downloadBill, RedirectAttributes redirectAttributes) {
		downloadBillService.delete(downloadBill);
		addMessage(redirectAttributes, "删除对账单成功");
		return "redirect:"+Global.getAdminPath()+"/balance/downloadBill/?repage";
	}

}