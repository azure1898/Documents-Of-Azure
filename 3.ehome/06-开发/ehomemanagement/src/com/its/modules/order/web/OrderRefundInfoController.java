/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

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
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.service.OrderRefundInfoService;

/**
 * 退款信息Controller
 * 
 * @author zhujiao
 * @version 2017-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderRefundInfo")
public class OrderRefundInfoController extends BaseController {

    @Autowired
    private OrderRefundInfoService orderRefundInfoService;
    @Autowired
    private ModuleManageService moduleManageService;

    @ModelAttribute
    public OrderRefundInfo get(@RequestParam(required = false) String id) {
        OrderRefundInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orderRefundInfoService.get(id);
        }
        if (entity == null) {
            entity = new OrderRefundInfo();
        }
        return entity;
    }

    @RequiresPermissions("order:orderRefundInfo:view")
    @RequestMapping(value = { "list", "" })
    public String list(OrderRefundInfo orderRefundInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OrderRefundInfo> page = orderRefundInfoService.findPage(new Page<OrderRefundInfo>(request, response), orderRefundInfo);
        model.addAttribute("page", page);
        model.addAttribute("allModule", moduleManageService.findAllList());
        return "modules/order/orderRefundInfoList";
    }

    @RequiresPermissions("order:orderRefundInfo:view")
    @RequestMapping(value = "form")
    public String form(OrderRefundInfo orderRefundInfo, Model model) {
        model.addAttribute("orderRefundInfo", orderRefundInfo);
        return "modules/order/orderRefundInfoForm";
    }

    @RequiresPermissions("order:orderRefundInfo:edit")
    @RequestMapping(value = "save")
    public String save(OrderRefundInfo orderRefundInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, orderRefundInfo)) {
            return form(orderRefundInfo, model);
        }
        orderRefundInfoService.save(orderRefundInfo);
        addMessage(redirectAttributes, "保存退款信息成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderRefundInfo/?repage";
    }

    @RequiresPermissions("order:orderRefundInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(OrderRefundInfo orderRefundInfo, RedirectAttributes redirectAttributes) {
        orderRefundInfoService.delete(orderRefundInfo);
        addMessage(redirectAttributes, "删除退款信息成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderRefundInfo/?repage";
    }

}