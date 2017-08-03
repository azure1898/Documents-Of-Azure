/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.web;

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
import com.its.modules.operation.entity.CouponManageUsers;
import com.its.modules.operation.service.CouponManageUsersService;

/**
 * 优惠券导入的用户Controller
 * @author liuqi
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/couponManageUsers")
public class CouponManageUsersController extends BaseController {

	@Autowired
	private CouponManageUsersService couponManageUsersService;
	
	@ModelAttribute
	public CouponManageUsers get(@RequestParam(required=false) String id) {
		CouponManageUsers entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = couponManageUsersService.get(id);
		}
		if (entity == null){
			entity = new CouponManageUsers();
		}
		return entity;
	}
	
	@RequiresPermissions("operation:couponManageUsers:view")
	@RequestMapping(value = {"list", ""})
	public String list(CouponManageUsers couponManageUsers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CouponManageUsers> page = couponManageUsersService.findPage(new Page<CouponManageUsers>(request, response), couponManageUsers); 
		model.addAttribute("page", page);
		return "modules/operation/couponManageUsersList";
	}

	@RequiresPermissions("operation:couponManageUsers:view")
	@RequestMapping(value = "form")
	public String form(CouponManageUsers couponManageUsers, Model model) {
		model.addAttribute("couponManageUsers", couponManageUsers);
		return "modules/operation/couponManageUsersForm";
	}

	@RequiresPermissions("operation:couponManageUsers:edit")
	@RequestMapping(value = "save")
	public String save(CouponManageUsers couponManageUsers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, couponManageUsers)){
			return form(couponManageUsers, model);
		}
		couponManageUsersService.save(couponManageUsers);
		addMessage(redirectAttributes, "保存优惠券导入的用户成功");
		return "redirect:"+Global.getAdminPath()+"/operation/couponManageUsers/?repage";
	}
	
	@RequiresPermissions("operation:couponManageUsers:edit")
	@RequestMapping(value = "delete")
	public String delete(CouponManageUsers couponManageUsers, RedirectAttributes redirectAttributes) {
		couponManageUsersService.delete(couponManageUsers);
		addMessage(redirectAttributes, "删除优惠券导入的用户成功");
		return "redirect:"+Global.getAdminPath()+"/operation/couponManageUsers/?repage";
	}

}