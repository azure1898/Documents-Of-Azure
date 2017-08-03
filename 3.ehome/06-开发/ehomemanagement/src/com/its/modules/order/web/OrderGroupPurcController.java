/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.operation.entity.GroupPurchase;
import com.its.modules.operation.service.GroupPurchaseService;
import com.its.modules.order.entity.OrderGroupPurc;
import com.its.modules.order.entity.OrderGroupPurcList;
import com.its.modules.order.service.OrderGroupPurcListService;
import com.its.modules.order.service.OrderGroupPurcService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 订单-团购类Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderGroupPurc")
public class OrderGroupPurcController extends BaseController {

	/** 订单-团购类 */
	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	/** 订单-团购类子表-团购券清单Service */
	@Autowired
	private OrderGroupPurcListService orderGroupPurcListService;


	/** 团购信息Service */
	@Autowired
	private GroupPurchaseService groupPurchaseService;





	/**
	 * 订单详情取得
	 * 
	 * @param id
	 *            订单ID
	 * @return
	 */
	@ModelAttribute
	public OrderGroupPurc get(@RequestParam(required = false) String id) {
		OrderGroupPurc entity = null;
		// 不为空的场合为订单详情页面
		if (StringUtils.isNotBlank(id)) {
			entity = orderGroupPurcService.get(id);
		}
		if (entity == null) {
			entity = new OrderGroupPurc();
		}
		return entity;
	}

	/**
	 * 订单详情显示预处理
	 * 
	 * @param OrderGroupPurc
	 * @param model
	 * @return
	 */
	@RequiresPermissions("order:OrderGroupPurc:view")
	@RequestMapping(value = "form")
	public String form(OrderGroupPurc orderGroupPurc, Model model) {

		model.addAttribute("OrderGroupPurc", orderGroupPurc);
		// 商品订单明细取得
		OrderGroupPurcList orderGroupPurcList = new OrderGroupPurcList();
		// 根据团购订单ID检索
		orderGroupPurcList.setOrderGroupPurcId(orderGroupPurc.getId());
		orderGroupPurc.setOrderGroupPurcList(orderGroupPurcListService.findList(orderGroupPurcList));
		// 团购详情查询
		GroupPurchase groupPurc = new GroupPurchase();
		groupPurc.setId(orderGroupPurc.getGroupPurchaseId());
		groupPurc = groupPurchaseService.get(groupPurc);
		model.addAttribute("groupPurc", groupPurc);

		// 迁移至商品订单详细页面
		return "modules/order/orderGroupPurcForm";
	}

	/**
	 * 验券消费
	 * 
	 * @param OrderGroupPurc
	 * @param model
	 * @return
	 */
	// @RequiresPermissions("order:OrderGroupPurc:checkout")
	@RequestMapping(value = "checkout")
	public String checkout(OrderGroupPurcList orderGroupPurcList, Model model) {
		// 迁移至验券页面
		return "modules/order/orderGroupPurcCheckout";
	}

	@RequiresPermissions("order:OrderGroupPurc:view")
	@RequestMapping(value = { "export" })
	public String export(OrderGroupPurc orderGroupPurc, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 从SESSION中取得商家信息
		User user = UserUtils.getUser();
		// 只显示当前商家对应的订单
		orderGroupPurc.setBusinessInfoId(user.getBusinessinfoId());
		// 只显当前商家商品的订单
		orderGroupPurc.setProdType("4");

		// EXCEL导出
		try {
			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<OrderGroupPurc> orderGroupPurcList = orderGroupPurcService.findList(orderGroupPurc);
			if (orderGroupPurcList == null || orderGroupPurcList.size() == 0) {
				addMessage(model, "当前检索条件下订单未找到");
				model.addAttribute("type", "error");
				// 迁移至服务订单列表页面
				return "modules/order/orderGroupPurcList";
			}
			// 数据编辑
			for (OrderGroupPurc orderGroupPurcExcelData : orderGroupPurcList) {
				// 时间
				StringBuffer time = new StringBuffer();
				if (StringUtils
						.isNotBlank(
								DateUtils.formatDate(orderGroupPurcExcelData.getCreateDate(), "yyyy-MM-dd HH:mm"))) {
					time.append("下单：");
					time.append(DateUtils.formatDate(orderGroupPurcExcelData.getCreateDate(), "yyyy-MM-dd HH:mm"));
				}
				if (StringUtils
						.isNotBlank(DateUtils.formatDate(orderGroupPurcExcelData.getPayTime(), "yyyy-MM-dd HH:mm"))) {
					time.append((char) 10);
					time.append("支付：");
					time.append(DateUtils.formatDate(orderGroupPurcExcelData.getPayTime(), "yyyy-MM-dd HH:mm"));
				}
				orderGroupPurcExcelData.setTimeForExcel(time.toString());
			}

			new ExportExcel("团购订单数据", OrderGroupPurc.class).setDataList(orderGroupPurcList).write(response, fileName)
					.dispose();
			// 迁移至服务订单列表页面
			return null;
		}
		catch (Exception e) {
			addMessage(model, "导出服务订单数据！失败信息：" + e.getMessage());
			model.addAttribute("type", "error");
		}
		// 迁移至服务订单列表页面
		return "modules/order/orderServiceList";
	}
}
