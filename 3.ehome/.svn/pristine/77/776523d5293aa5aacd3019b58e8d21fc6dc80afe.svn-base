/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

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
import com.its.modules.app.entity.OrderRefundInfo;
import com.its.modules.app.service.OrderRefundInfoService;

/**
 * 订单-退款信息Controller
 * @author sushipeng
 * @version 2017-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/app/orderRefundInfo")
public class OrderRefundInfoController extends BaseController {

	@Autowired
	private OrderRefundInfoService orderRefundInfoService;
	
	@ModelAttribute
	public OrderRefundInfo get(@RequestParam(required=false) String id) {
		OrderRefundInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderRefundInfoService.get(id);
		}
		if (entity == null){
			entity = new OrderRefundInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("app:orderRefundInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderRefundInfo orderRefundInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderRefundInfo> page = orderRefundInfoService.findPage(new Page<OrderRefundInfo>(request, response), orderRefundInfo); 
		model.addAttribute("page", page);
		return "modules/app/orderRefundInfoList";
	}

	@RequiresPermissions("app:orderRefundInfo:view")
	@RequestMapping(value = "form")
	public String form(OrderRefundInfo orderRefundInfo, Model model) {
		model.addAttribute("orderRefundInfo", orderRefundInfo);
		return "modules/app/orderRefundInfoForm";
	}

	@RequiresPermissions("app:orderRefundInfo:edit")
	@RequestMapping(value = "save")
	public String save(OrderRefundInfo orderRefundInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderRefundInfo)){
			return form(orderRefundInfo, model);
		}
		orderRefundInfoService.save(orderRefundInfo);
		addMessage(redirectAttributes, "保存订单-退款信息成功");
		return "redirect:"+Global.getAdminPath()+"/app/orderRefundInfo/?repage";
	}
	
	@RequiresPermissions("app:orderRefundInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderRefundInfo orderRefundInfo, RedirectAttributes redirectAttributes) {
		orderRefundInfoService.delete(orderRefundInfo);
		addMessage(redirectAttributes, "删除订单-退款信息成功");
		return "redirect:"+Global.getAdminPath()+"/app/orderRefundInfo/?repage";
	}

}