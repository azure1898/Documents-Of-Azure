/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.order.entity.OrderGroupPurcList;
import com.its.modules.order.service.OrderGroupPurcListService;
import com.its.modules.order.service.OrderGroupPurcService;

/**
 * 验券Controller
 * @author caojing
 * @version 2017-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/testCoupon")
public class TestCouponController extends BaseController {

	@Autowired
	private OrderGroupPurcService orderGroupPurcService;
	@Autowired
	private OrderGroupPurcListService orderGroupPurcListService;
	
	@ModelAttribute
	public OrderGroupPurcList get(@RequestParam(required=false) String id) {
		OrderGroupPurcList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderGroupPurcListService.get(id);
		}
		if (entity == null){
			entity = new OrderGroupPurcList();
		}
		return entity;
	}
	
	@RequiresPermissions("coupon:testCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderGroupPurcList orderGroupPurcList, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OrderGroupPurcList> orderGroupList = new ArrayList<OrderGroupPurcList>();
		//查询数据
		if(orderGroupPurcList !=null && StringUtils.isNotBlank(orderGroupPurcList.getGroupPurcNumber())){
		   orderGroupList = orderGroupPurcListService.getNumberList(orderGroupPurcList);
		}
		//数据存在
		if(orderGroupList !=null && orderGroupList.size() >0){
			model.addAttribute("data", "exist");
		}
		model.addAttribute("orderGroupList", orderGroupList);
		return "modules/coupon/testCouponList";
	}

	/**
	 * 确认消费
	 * 
	 * @param groupPurchaseId 团购ID
	 * @return
	 */
	@RequiresPermissions("coupon:testCoupon:edit")
	@RequestMapping(value = "confirm",method={RequestMethod.GET})
    @ResponseBody
	public List<OrderGroupPurcList> confirm(@RequestParam(required=false)String checkedNumberId) {
		
		//确认的团购券id集合
		if(StringUtils.isNotBlank(checkedNumberId)){
			//优惠/验券管理：更新团购券消费信息
			orderGroupPurcListService.updateGroupNumber(checkedNumberId);
			
			//页面选中的团购券号
			String[] groupNumber = checkedNumberId.split(",");
			if(groupNumber.length > 0){
				List<OrderGroupPurcList> orderGroupList = 
						orderGroupPurcListService.getNumberListById(groupNumber[0]);
				return orderGroupList;
			}
		}

		return null;
	}

}