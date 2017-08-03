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
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.service.OrderLessonService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderTrackService;

/**
 * 订单-课程培训类Controller
 * 
 * @author liuhl
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderLesson")
public class OrderLessonController extends BaseController {

    /**
     * 订单-课程培训类Service
     */
    @Autowired
    private OrderLessonService orderLessonService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;


    @ModelAttribute
    public OrderLesson get(@RequestParam(required = false) String id) {
        OrderLesson entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orderLessonService.get(id);
        }
        if (entity == null) {
            entity = new OrderLesson();
        }
        return entity;
    }

//
//    @RequiresPermissions("order:orderLesson:edit")
//    @RequestMapping(value = "save")
//    public String save(OrderLesson orderLesson, Model model, RedirectAttributes redirectAttributes) {
//        if (!beanValidator(model, orderLesson)) {
//            return form(orderLesson, model);
//        }
//        orderLessonService.save(orderLesson);
//        addMessage(redirectAttributes, "保存订单-课程培训类成功");
//        return "redirect:" + Global.getAdminPath() + "/order/orderLesson/?repage";
//    }

    @RequiresPermissions("order:orderLesson:edit")
    @RequestMapping(value = "delete")
    public String delete(OrderLesson orderLesson, RedirectAttributes redirectAttributes) {
        orderLessonService.delete(orderLesson);
        addMessage(redirectAttributes, "删除订单-课程培训类成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderLesson/?repage";
    }

    /**
     * 课程培训订单取消
     * 
     * @param orderTrack
     *            订单取消信息
     * @return
     */
    @RequiresPermissions("order:orderLesson:edit")
    @RequestMapping(value = "cancel")
    public String cancel(OrderLesson orderLesson, Model model, RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderLessonService.check(orderLesson.getOrderNo(), orderLesson.getUpdateDateString())) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            return "redirect:" + Global.getAdminPath() + "/order/orderLesson/?repage";
        }
        // 返回影响条数
        int result = orderLessonService.cancel(orderLesson);
        // 若没更新则显示操作
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            // 迁移至课程培训订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/orderLesson/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至课程培训订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/orderLesson/?repage";
        }
    }

}