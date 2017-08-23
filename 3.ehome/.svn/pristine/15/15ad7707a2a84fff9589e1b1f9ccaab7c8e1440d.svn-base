/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.field.service.FieldPartitionPriceService;
import com.its.modules.setup.entity.BusinessCategorydict;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.service.BusinessCategorydictService;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

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
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.service.OrderFieldService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderTrackService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 场地预约订单表Controller
 * 
 * @author xzc
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderField")
public class OrderFieldController extends BaseController {
    /**
     * 场地预约订单表Service
     */
    @Autowired
    private OrderFieldService orderFieldService;
    /**
     * 场地预约Service
     */
    @Autowired
    private FieldInfoService fieldInfoService;
    /**
     * 商家信息管理Service
     */
    @Autowired
    private BusinessInfoService businessInfoService;
    /**
     * 场地预约子表-场地分段信息Service
     */
    @Autowired
    private FieldPartitionPriceService fieldPartitionPriceService;

    /** 商户分类Service */
    @Autowired
    private BusinessCategorydictService businessCategorydictService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /**
     * 如果参数中有id的话则取出Entity
     * 
     * @param id
     * @return
     */
    @ModelAttribute
    public OrderField get(@RequestParam(required = false) String id) {
        OrderField entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orderFieldService.get(id);
        }
        if (entity == null) {
            entity = new OrderField();
        }
        return entity;
    }

    @RequestMapping(value = { "list", "" })
    public String list(OrderField orderField, HttpServletRequest request, HttpServletResponse response, Model model) {

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();

        // 商家分类信息检索条件
        Map<String, String> paramer = new HashMap<String, String>();
        // 根据当前登陆者的商家ID进行检索
        paramer.put("businessInfoId", user.getBusinessinfoId());
        List<BusinessCategorydict> businessCategorydictList = businessCategorydictService
                .findCategoryListByBusinessId(paramer);
        // 将检索的信息放在画面中
        model.addAttribute("businessCategorydictList", businessCategorydictList);
        // 将上方导航下拉菜单默认选中为场地订单
        model.addAttribute("nowProdType", "3");

        // 下拉菜单（场地选择）
        List<FieldInfo> fieldInfoOptionList = fieldInfoService.findList(new FieldInfo());
        model.addAttribute("fieldInfoOptionList", fieldInfoOptionList);

        // 只显示当前商家订单
        orderField.setBusinessInfoId(user.getBusinessinfoId());
        Page<OrderField> page = orderFieldService.findPage(new Page<OrderField>(request, response), orderField);

        // 当画面输入检索条件，并检索时
        if (StringUtils.isNotBlank(orderField.getSearchFlg())
                && (page.getList() == null || page.getList().size() == 0)) {
            // 当检索条件只有订单号时
            if (StringUtils.isNotBlank(orderField.getOrderNo()) && null == orderField.getEndCreateDate()
                    && null == orderField.getBeginCreateDate() && StringUtils.isBlank(orderField.getOrderState())
                    && StringUtils.isBlank(orderField.getFieldInfoId())
                    && StringUtils.isBlank(orderField.getPayState())) {
                addMessage(model, "请确定您输入的订单号是否正确");
                model.addAttribute("type", "error");
                // 除了订单号作为检索条件，还存在别的条件时
            } else {
                addMessage(model, "您查询的订单未找到");
                model.addAttribute("type", "error");
            }

            // 迁移至场地订单列表页面
            return "modules/order/orderFieldList";
        }

        for (OrderField orderFieldTemp : page.getList()) {
            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderFieldTemp.setUpdateDateString(sdf.format(orderFieldTemp.getUpdateDate()));
        }
        model.addAttribute("page", page);

        return "modules/order/orderFieldList";
    }

    @RequestMapping(value = "form")
    public String form(OrderField orderField, Model model) {

        // 为了排他处理这里使用乐观锁以更新日时控制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderField.setUpdateDateString(sdf.format(orderField.getUpdateDate()));

        // 订单跟踪表信息取得
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderField.getOrderNo());
        orderField.setOrderTrackList(orderTrackService.findList(orderTrack));

        // 退款信息检索条件
        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        // 根据订单号检索信息
        orderRefundInfo.setOrderNo(orderField.getOrderNo());
        orderRefundInfo = orderRefundInfoService.findOrderRefundInfoByOrderNo(orderRefundInfo);
        if (null != orderRefundInfo) {
            // 将退款完成时间添加到画面中
            model.addAttribute("refundOverTime", orderRefundInfo.getRefundOverTime());
        }
        model.addAttribute("orderField", orderField);
        return "modules/order/orderFieldForm";
    }

    /**
     * 保存或者修改 场地预约订单表Entity
     * 
     * @param orderField
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save")
    public String save(OrderField orderField, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, orderField)) {
            return form(orderField, model);
        }
        orderFieldService.save(orderField);
        addMessage(redirectAttributes, "保存预约场地成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderField/?repage";
    }

    /**
     * 删除 场地预约订单表Entity
     * 
     * @param orderField
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "delete")
    public String delete(OrderField orderField, RedirectAttributes redirectAttributes) {
        orderFieldService.delete(orderField);
        addMessage(redirectAttributes, "删除预约场地成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderField/?repage";
    }

    /**
     * 预约场地
     * 
     * @param orderField
     *            场地预约订单表Entity
     * @param redirectAttributes
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addOrderFieldList")
    public String addOrderFieldList(OrderField orderField, RedirectAttributes redirectAttributes, Model model) {
        FieldInfo fieldInfo = fieldInfoService.get(orderField.getFieldInfoId());
        if (fieldInfo.getState().equals("1")) {
            return "该场地已经暂停预约！";
        }
        // FieldPartitionPrice fieldPartitionPrice =
        // fieldPartitionPriceService.get(orderField.getOrderFieldList().getFieldPartitionPriceId());
        FieldPartitionPrice fieldPartitionPrice = fieldPartitionPriceService.get(orderField.getFieldPartitionPriceId());
        if (!fieldPartitionPrice.getState().equals("0")) {
            return "该场地已经被预约，请刷新页面查看最新场地状态！";
        }
        User user = UserUtils.getUser();
        BusinessInfo businessInfo = businessInfoService.getJoinArea(user.getBusinessinfoId());
        orderField.setProvinceId(businessInfo.getAddrPro());
        orderField.setCityId(businessInfo.getAddrCity());
        orderFieldService.save(orderField, fieldPartitionPrice, businessInfo.getAddrCityCode(),
                user.getBusinessinfoId());
        return "预约成功!";
    }

    @RequestMapping(value = { "export" })
    public String export(OrderField orderField, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 只显示当前商家对应的订单
        orderField.setBusinessInfoId(user.getBusinessinfoId());
        // 只显当前商家场地的订单
        orderField.setProdType("3");

        // EXCEL导出
        try {
            String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<OrderField> orderFieldList = orderFieldService.findList(orderField);
            if (orderFieldList == null || orderFieldList.size() == 0) {
                addMessage(model, "当前检索条件下订单未找到");
                model.addAttribute("type", "error");
                // 迁移至服务订单列表页面
                return "modules/order/orderFieldList";
            }
            // 数据编辑
            for (OrderField orderFieldExcelData : orderFieldList) {
                // 时间
                StringBuffer time = new StringBuffer();
                if (null != orderFieldExcelData.getCreateDate()) {
                    time.append("下单：");
                    time.append(DateUtils.formatDate(orderFieldExcelData.getCreateDate(), "yyyy-MM-dd HH:mm"));
                }
                if (null != orderFieldExcelData.getPayTime()) {
                    time.append((char) 10);
                    time.append("支付：");
                    time.append(DateUtils.formatDate(orderFieldExcelData.getPayTime(), "yyyy-MM-dd HH:mm"));
                }
                orderFieldExcelData.setTimeForExcel(time.toString());

                // 预约场地
                StringBuffer fieldName = new StringBuffer();
                fieldName.append(orderFieldExcelData.getName());
                if (null != orderFieldExcelData.getOrderFieldList()
                        && orderFieldExcelData.getOrderFieldList().size() > 0) {
                    // 场地预约时间，一个订单只能预约一个场地，故取第一条的数据
                    if (orderFieldExcelData.getOrderFieldList().get(0).getAppointmentTime() != null) {
                        fieldName.append((char) 10);
                        fieldName.append(DateUtils.formatDate(
                                orderFieldExcelData.getOrderFieldList().get(0).getAppointmentTime(), "yyyy年M月d日"));
                    }
                    // 起始时间与结束时间
                    for (OrderFieldList fieldList : orderFieldExcelData.getOrderFieldList()) {
                        if (fieldList.getStartTime() != null) {
                            fieldName.append((char) 10);
                            fieldName.append(DateUtils.formatDate(fieldList.getStartTime(), "HH:mm"));
                        }
                        if (fieldList.getEndTime() != null) {
                            fieldName.append("~");
                            fieldName.append(DateUtils.formatDate(fieldList.getEndTime(), "HH:mm"));
                        }

                    }
                }
                orderFieldExcelData.setFieldNameForExcel(fieldName.toString());
            }

            new ExportExcel("场地订单数据", OrderField.class).setDataList(orderFieldList).write(response, fileName).dispose();
            // 迁移至服务订单列表页面
            return null;
        } catch (Exception e) {
            addMessage(model, "导出服务订单数据！失败信息：" + e.getMessage());
            model.addAttribute("type", "error");
        }
        // 迁移至服务订单列表页面
        return "modules/order/orderFieldList";
    }

    /**
     * 场地订单取消
     * 
     * @param orderTrack
     *            订单取消信息
     * @return
     */
    @RequestMapping(value = "cancel")
    public String cancel(OrderField orderField, Model model, RedirectAttributes redirectAttributes,
            String redirectUrl) {
        if (redirectUrl != null && redirectUrl != "")
            redirectUrl = "redirect:" + Global.getAdminPath() + redirectUrl;
        else
            redirectUrl = "redirect:" + Global.getAdminPath() + "/order/orderField/?repage";
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderFieldService.check(orderField.getId(), orderField.getUpdateDateString())) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            return redirectUrl;
        }
        // 返回影响条数
        int result = 0;
        try {
            result = orderFieldService.cancel(orderField);
        } catch (Exception e) {
            addMessage(redirectAttributes, "操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            // 迁移至场地订单列表页面
            return redirectUrl;
        }

        // 若没更新则显示操作
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            // 迁移至场地订单列表页面
            return redirectUrl;
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至场地订单列表页面
            return redirectUrl;
        }
    }
}