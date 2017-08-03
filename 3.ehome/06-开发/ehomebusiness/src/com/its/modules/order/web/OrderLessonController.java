/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.service.OrderLessonService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderTrackService;
import com.its.modules.setup.entity.BusinessCategorydict;
import com.its.modules.setup.service.BusinessCategorydictService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

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

    /**
     * 商户分类Service
     */
    @Autowired
    private BusinessCategorydictService businessCategorydictService;

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

    /**
     * 一览显示
     * 
     * @param orderLesson
     *            页面数据
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("order:orderLesson:view")
    @RequestMapping(value = { "list", "" })
    public String list(OrderLesson orderLesson, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 只显示当前商家对应的订单
        orderLesson.setBusinessInfoId(user.getBusinessinfoId());
        // 只显当前商家课程培训的订单
        orderLesson.setProdType("2");
        Page<OrderLesson> page = orderLessonService.findPage(new Page<OrderLesson>(request, response), orderLesson);
        // 商家分类信息检索条件
        Map<String, String> paramer = new HashMap<String, String>();
        // 根据当前登陆者的商家ID进行检索
        paramer.put("businessInfoId", user.getBusinessinfoId());
        List<BusinessCategorydict> businessCategorydictList = businessCategorydictService
                .findCategoryListByBusinessId(paramer);
        // 将检索的信息放在画面中
        model.addAttribute("businessCategorydictList", businessCategorydictList);
        // 将上方导航下拉菜单默认选中为课程培训订单
        model.addAttribute("nowProdType", "2");

        // 当画面输入检索条件，并检索时
        if (StringUtils.isNotBlank(orderLesson.getSearchFlg())
                && (page.getList() == null || page.getList().size() == 0)) {
            if (StringUtils.isNotBlank(orderLesson.getOrderNo())) {
                addMessage(model, "请确定您输入的订单号是否正确");
                model.addAttribute("type", "error");
            } else {
                addMessage(model, "您查询的订单未找到");
                model.addAttribute("type", "error");
            }
            // 迁移至课程培训订单列表页面
            return "modules/order/orderLessonList";
        }

        for (OrderLesson orderLessonTemp : page.getList()) {
            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderLessonTemp.setUpdateDateString(sdf.format(orderLessonTemp.getUpdateDate()));
        }
        model.addAttribute("page", page);
        // 迁移至课程培训订单列表页面
        return "modules/order/orderLessonList";
    }

    @RequiresPermissions("order:orderLesson:view")
    @RequestMapping(value = "form")
    public String form(OrderLesson orderLesson, Model model) {
        model.addAttribute("orderLesson", orderLesson);
        // 为了排他处理这里使用乐观锁以更新日时控制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderLesson.setUpdateDateString(sdf.format(orderLesson.getUpdateDate()));

        // 订单跟踪表信息取得
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderLesson.getOrderNo());
        orderLesson.setOrderTrackList(orderTrackService.findList(orderTrack));

        // 退款信息检索条件
        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        // 根据订单号检索信息
        orderRefundInfo.setOrderNo(orderLesson.getOrderNo());
        orderRefundInfoService.findOrderRefundInfoByOrderNo(orderRefundInfo);
        // 将退款完成时间添加到画面中
        model.addAttribute("refundOverTime", orderRefundInfo.getRefundOverTime());
        return "modules/order/orderLessonForm";
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
        if (!orderLessonService.check(orderLesson.getId(), orderLesson.getUpdateDateString())) {
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

    @RequiresPermissions("order:orderLesson:view")
    @RequestMapping(value = { "export" })
    public String export(OrderLesson orderLesson, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 只显示当前商家对应的订单
        orderLesson.setBusinessInfoId(user.getBusinessinfoId());
        // 只显当前商家课程的订单
        orderLesson.setProdType("2");

        // EXCEL导出
        try {
            String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<OrderLesson> orderLessonList = orderLessonService.findList(orderLesson);
            if (orderLessonList == null || orderLessonList.size() == 0) {
                addMessage(model, "当前检索条件下订单未找到");
                model.addAttribute("type", "error");
                // 迁移至服务订单列表页面
                return "modules/order/orderGoodsList";
            }
            // 数据编辑
            for (OrderLesson orderLessonExcelData : orderLessonList) {
                // 时间
                StringBuffer time = new StringBuffer();
                if (StringUtils
                        .isNotBlank(DateUtils.formatDate(orderLessonExcelData.getCreateDate(), "yyyy-MM-dd HH:mm"))) {
                    time.append("下单：");
                    time.append(DateUtils.formatDate(orderLessonExcelData.getCreateDate(), "yyyy-MM-dd HH:mm"));
                }
                if (StringUtils
                        .isNotBlank(DateUtils.formatDate(orderLessonExcelData.getPayTime(), "yyyy-MM-dd HH:mm"))) {
                    time.append((char) 10);
                    time.append("支付：");
                    time.append(DateUtils.formatDate(orderLessonExcelData.getPayTime(), "yyyy-MM-dd HH:mm"));
                }
                orderLessonExcelData.setTimeForExcel(time.toString());
            }

            new ExportExcel("课程订单数据", OrderLesson.class).setDataList(orderLessonList).write(response, fileName)
                    .dispose();
            // 迁移至服务订单列表页面
            return null;
        } catch (Exception e) {
            addMessage(model, "导出服务订单数据！失败信息：" + e.getMessage());
            model.addAttribute("type", "error");
        }
        // 迁移至服务订单列表页面
        return "modules/order/orderServiceList";
    }
}