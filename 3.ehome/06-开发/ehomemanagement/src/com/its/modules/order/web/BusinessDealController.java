/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.operation.entity.GroupPurchase;
import com.its.modules.operation.service.GroupPurchaseService;
import com.its.modules.order.entity.BusinessDeal;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderGoodsList;
import com.its.modules.order.entity.OrderGroupPurc;
import com.its.modules.order.entity.OrderGroupPurcList;
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.entity.OrderServiceList;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.service.BusinessDealService;
import com.its.modules.order.service.OrderFieldService;
import com.its.modules.order.service.OrderGoodsService;
import com.its.modules.order.service.OrderGroupPurcListService;
import com.its.modules.order.service.OrderGroupPurcService;
import com.its.modules.order.service.OrderLessonService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderServiceListService;
import com.its.modules.order.service.OrderServiceService;
import com.its.modules.order.service.OrderTrackService;

/**
 * 商户交易管理Controller
 * @author lq
 */
@Controller
@RequestMapping(value = "${adminPath}/order/businessDeal")
public class BusinessDealController extends BaseController {

    @Autowired
    private BusinessDealService businessDealService;

    @Autowired
    private ModuleManageService moduleManageService;
    

    /** 商品订单表Service */
    @Autowired
    private OrderGoodsService orderGoodsService;
    
    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;
    

    /** 服务表Service */
    @Autowired
    private OrderServiceService orderServiceService;
    
    /** 服务订单明细表表Service */
    @Autowired
    private OrderServiceListService orderServiceListService;
    
    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;
    
    /**
     * 订单-课程培训类Service
     */
    @Autowired
    private OrderLessonService orderLessonService;
    
    /**
     * 场地预约订单表Service
     */
    @Autowired
    private OrderFieldService orderFieldService;
    
    
    /** 订单-团购类 */
	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	/** 订单-团购类子表-团购券清单Service */
	@Autowired
	private OrderGroupPurcListService orderGroupPurcListService;
	
	/** 团购信息Service */
	@Autowired
	private GroupPurchaseService groupPurchaseService;
	

    @RequiresPermissions("order:OrderBusinessDeal:view")
    @RequestMapping(value = { "list", "" })
    public String list(BusinessDeal businessDeal, HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	 /* 总计*/
        model.addAttribute("total",businessDealService.getTotal(businessDeal));
        
        
        Page<BusinessDeal> page = businessDealService.findPage(new Page<BusinessDeal>(request, response), businessDeal);
        model.addAttribute("page", page);
        
        /*模块下拉列表*/
        model.addAttribute("allModule", moduleManageService.findAllList());
        
        return "modules/order/businessDealList";
    }
    
    @RequiresPermissions("order:OrderBusinessDeal:view")
	@RequestMapping(value = { "export" })
    public String export(BusinessDeal OrderBusinessDeal,  HttpServletResponse response, Model model) {
     		try {
     			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
     			List<BusinessDeal> businessDealList = businessDealService.findList(OrderBusinessDeal);

     			new ExportExcel("商户交易数据", BusinessDeal.class).setDataList(businessDealList).write(response, fileName).dispose();
     			// 迁移至服务订单列表页面
     			return null;
     		}
     		catch (Exception e) {
     			addMessage(model, "导出商户交易数据！失败信息：" + e.getMessage());
     			model.addAttribute("type", "error");
     		}
        
        return "modules/order/businessDealList";
    }
    
    
    @RequiresPermissions("order:OrderBusinessDeal:view")
    @RequestMapping(value = "form")
    public String form(String id,int prodType,HttpServletRequest request,  Model model) {
        /*商品*/
        if(prodType==0){
        	//商品订单详情 包含订单详情,物流跟踪
        	OrderGoods orderGoods = businessDealService.getAll(id);
            model.addAttribute("orderGoods", orderGoods);
            
            // 編輯取得的订单详情，将取得的文件名加上路径
            for (OrderGoodsList orderGoodsListTemp : orderGoods.getOrderGoodsList()) {
                if (StringUtils.isNotBlank(orderGoodsListTemp.getImgs())) {
                    String[] imageNames = orderGoodsListTemp.getImgs().split(",");
                    try {
                        // 为了页面大小，显示压缩后的图片
                        orderGoodsListTemp.setImageUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
                    } catch (IOException | MyException e) {
                    }
                }
            }

            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderGoods.setUpdateDateString(sdf.format(orderGoods.getUpdateDate()));


            // 退款信息检索条件
            OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
            // 根据订单号检索信息
            orderRefundInfo.setOrderNo(orderGoods.getOrderNo());
//            orderRefundInfoService.findOrderRefundInfoByOrderNo(orderRefundInfo);
            // 将退款完成时间添加到画面中
            model.addAttribute("refundOverTime", orderRefundInfo.getRefundOverTime());
        	return "modules/order/businessDealForm0";
        }
        /*服务*/
        if(prodType==1){
        	OrderService orderService = orderServiceService.get(id);
            model.addAttribute("orderService", orderService);
            // 商品订单明细取得
            OrderServiceList orderServiceList = new OrderServiceList();
            // 根据订单号检索
            orderServiceList.setOrderNo(orderService.getOrderNo());
            orderService.setOrderServiceList(orderServiceListService.findList(orderServiceList));
            // 編輯取得的订单详情，将取得的文件名加上路径
            for (OrderServiceList orderServiceListTemp : orderService.getOrderServiceList()) {
                if (StringUtils.isNotBlank(orderServiceListTemp.getImgs())) {
                    String[] imageNames = orderServiceListTemp.getImgs().split(",");
                    try {
                        // 为了页面大小，显示压缩后的图片
                        orderServiceListTemp
                                .setImageUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
                    } catch (IOException | MyException e) {
                    }
                }
            }

            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderService.setUpdateDateString(sdf.format(orderService.getUpdateDate()));

            // 订单跟踪表信息取得
            OrderTrack orderTrack = new OrderTrack();
            orderTrack.setOrderNo(orderService.getOrderNo());
            orderService.setOrderTrackList(orderTrackService.findList(orderTrack));

            // 退款信息检索条件
            OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
            // 根据订单号检索信息
            orderRefundInfo.setOrderNo(orderService.getOrderNo());
            orderRefundInfoService.findOrderRefundInfoByOrderNo(orderRefundInfo);
            // 将退款完成时间添加到画面中
            model.addAttribute("refundOverTime", orderRefundInfo.getRefundOverTime());
        	
        	return "modules/order/businessDealForm1";
        }
        /*课程购买*/
        if(prodType==2){
        	OrderLesson orderLesson =  orderLessonService.get(id);
        	 model.addAttribute("orderLesson", orderLesson);
             // 为了排他处理这里使用乐观锁以更新日时控制
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date ud = orderLesson.getUpdateDate()==null?new Date():orderLesson.getUpdateDate();
             orderLesson.setUpdateDateString(sdf.format(ud));

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
        	return "modules/order/businessDealForm2";
        }
        /*场地预约*/
        if(prodType==3){
        	OrderField orderField = orderFieldService.get(id);
        	
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
            orderRefundInfoService.findOrderRefundInfoByOrderNo(orderRefundInfo);
            // 将退款完成时间添加到画面中
            model.addAttribute("refundOverTime", orderRefundInfo.getRefundOverTime());
            model.addAttribute("orderField", orderField);
            
        	return "modules/order/businessDealForm3";
        }
        
        /*团购*/
        if(prodType==4){
        	OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(id);
        	model.addAttribute("orderGroupPurc", orderGroupPurc);
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
        	return "modules/order/businessDealForm4";
        }
        return "modules/order/businessDealList";
    }
    	
    
    
    
    /**
     * 商品订单完成
     * 
     * @param orderNo
     *            要处理的商品订单号
     * @param updateDate
     *            前回更新日时
     * @return
     */
    @RequiresPermissions("order:OrderBusinessDeal:edit")
    @RequestMapping(value = "complete")
    public String complete(@RequestParam(required = true) String id, @RequestParam(required = true) String updateDate,RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderGoodsService.check(id, updateDate)) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
        // 返回影响条数
        int result = orderGoodsService.complete(id);
        // 更新条数为0则更新失败
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
    }

    /**
     * 商品订单受理
     * 
     * @param id
     *            要处理的商品订单号
     * @return
     */
    @RequiresPermissions("order:OrderBusinessDeal:edit")
    @RequestMapping(value = "accept")
    public String accept(@RequestParam(required = true) String id, @RequestParam(required = true) String updateDate,
            RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
        if (!orderGoodsService.check(id, updateDate)) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
        // 返回影响条数
        int result = orderGoodsService.accept(id);
        // 若更新条数为0则更新失败
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/?repage";
        }
    }

    /**
     * 商品订单取消
     * 
     * @param orderTrack
     *            订单取消信息
     * @return
     */
    @RequiresPermissions("order:OrderBusinessDeal:edit")
    @RequestMapping(value = "cancel")
    public String cancel(OrderGoods orderGoods, Model model, RedirectAttributes redirectAttributes) {
        // 如果更新日时已经发生变化，则不再进行更新处理
    	
        if (!orderGoodsService.check(orderGoods.getId(), orderGoods.getUpdateDateString())) {
            addMessage(redirectAttributes, "订单信息已被他人修正，操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/form?id="+orderGoods.getId()+"&proType="+orderGoods.getProdType()+"&repage";
        }
        // 返回影响条数
        int result = orderGoodsService.cancel(orderGoods);
        // 若没更新则显示操作
        if (0 == result) {
            addMessage(redirectAttributes, "操作失败");
            redirectAttributes.addFlashAttribute("type", "error");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/form?id="+orderGoods.getId()+"&proType="+orderGoods.getProdType()+"repage";
        } else {
            addMessage(redirectAttributes, "操作成功");
            // 迁移至商品订单列表页面
            return "redirect:" + Global.getAdminPath() + "/order/businessDeal/form?id="+orderGoods.getId()+"&proType="+orderGoods.getProdType()+"repage";
        }
    }
    
}