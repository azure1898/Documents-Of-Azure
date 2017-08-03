/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderServiceListService;
import com.its.modules.order.service.OrderServiceService;
import com.its.modules.order.service.OrderTrackService;

/**
 * 订单-服务类Controller
 * 
 * @author liuhl
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderService")
public class OrderServiceController extends BaseController {

    @Autowired
    private OrderServiceService orderServiceService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;


    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /** 商品订单明细表表Service */
    @Autowired
    private OrderServiceListService orderServiceListService;

    @ModelAttribute
    public OrderService get(@RequestParam(required = false) String id) {
        OrderService entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orderServiceService.get(id);
        }
        if (entity == null) {
            entity = new OrderService();
        }
        return entity;
    }


    /**
     * 服务订单取消
     * 
     * @param orderTrack
     *            订单取消信息
     * @return
     */
    @RequiresPermissions("order:orderService:cancel")
    @RequestMapping(value = "cancel")
    public String cancel(OrderService orderService, Model model, RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderServiceService.check(orderService.getOrderNo(), orderService.getUpdateDateString())) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
        // 返回影响条数
        int result = orderServiceService.cancel(orderService);
        // 若没更新则显示操作
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
    }

    /**
     * 服务订单完成
     * 
     * @param orderNo
     *            要处理的服务订单号
     * @param updateDate
     *            前回更新日时
     * @return
     */
    @RequiresPermissions("order:orderService:complete")
    @RequestMapping(value = "complete")
    public String complete(@RequestParam(required = true) String orderNo,
            @RequestParam(required = true) String updateDate, RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderServiceService.check(orderNo, updateDate)) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
        // 返回影响条数
        int result = orderServiceService.complete(orderNo);
        // 更新条数为0则更新失败
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
    }

    /**
     * 服务订单受理
     * 
     * @param orderNo
     *            要处理的服务订单号
     * @return
     */
    @RequiresPermissions("order:orderService:accept")
    @RequestMapping(value = "accept")
    public String accept(@RequestParam(required = true) String orderNo,
            @RequestParam(required = true) String updateDate, RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderServiceService.check(orderNo, updateDate)) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
        // 返回影响条数
        int result = orderServiceService.accept(orderNo);
        // 若更新条数为0则更新失败
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至服务订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
    }
}