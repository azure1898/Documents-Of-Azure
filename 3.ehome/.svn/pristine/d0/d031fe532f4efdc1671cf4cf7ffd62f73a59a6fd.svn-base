/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.common.utils.JedisUtils;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldPartitionPriceService;
import com.its.modules.order.service.OrderFieldService;
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
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.order.service.OrderFieldListService;

/**
 * 场地预约订单表Controller
 * @author xzc
 * @version 2017-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderFieldList")
public class OrderFieldListController extends BaseController {
	/**
	 * 场地预约订单表Service
	 */
	@Autowired
	private OrderFieldService orderFieldService;
	/**
	 * 场地预约订单表Service
	 */
	@Autowired
	private OrderFieldListService orderFieldListService;
	/**
	 * 场地预约子表-场地分段信息Service
	 */
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	
	@ModelAttribute
	public OrderFieldList get(@RequestParam(required=false) String id) {
		OrderFieldList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderFieldListService.get(id);
		}
		if (entity == null){
			entity = new OrderFieldList();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderFieldList:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderFieldList orderFieldList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderFieldList> page = orderFieldListService.findPage(new Page<OrderFieldList>(request, response), orderFieldList); 
		model.addAttribute("page", page);
		return "modules/order/orderFieldListList";
	}

	@RequiresPermissions("order:orderFieldList:view")
	@RequestMapping(value = "form")
	public String form(OrderFieldList orderFieldList, Model model) {
		model.addAttribute("orderFieldList", orderFieldList);
		return "modules/order/orderFieldListForm";
	}

	@RequiresPermissions("order:orderFieldList:edit")
	@RequestMapping(value = "save")
	public String save(OrderFieldList orderFieldList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderFieldList)){
			return form(orderFieldList, model);
		}
		orderFieldListService.save(orderFieldList);
		addMessage(redirectAttributes, "保存预约场地成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderFieldList/?repage";
	}
	
	@RequiresPermissions("order:orderFieldList:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderFieldList orderFieldList, RedirectAttributes redirectAttributes) {
		orderFieldListService.delete(orderFieldList);
		addMessage(redirectAttributes, "删除预约场地成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderFieldList/?repage";
	}

	/**
	 * 获取 场地预约订单清单表
	 * @param id 场地分段信息ID
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("order:orderField:view")
	@RequestMapping(value = "loadOrderFieldListByOrderField")
	public String loadOrderFieldListByOrderField(String id, RedirectAttributes redirectAttributes){
		OrderFieldList orderFieldList=orderFieldService.getOrderFieldListByFieldPartitionPriceId(id);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(orderFieldList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取消预约
	 * @param id 场地预约订单表Entity
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("order:orderField:cancel")
	@RequestMapping(value = "cancelOrderFieldList")
	public String cancelOrderFieldList(String id, RedirectAttributes redirectAttributes){
		OrderFieldList orderFieldList=orderFieldService.getOrderFieldListByFieldPartitionPriceId(id);
		orderFieldListService.cancelOrderFieldList(orderFieldList.getOrderNo(),id);
		return "取消预约成功！";
	}
}