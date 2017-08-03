/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.company.service.CompanyInfoService;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderGoodsList;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.entity.PropertyDeal;
import com.its.modules.order.service.PropertyDealService;
import com.its.modules.village.service.VillageInfoService;

/**
 * 商户交易管理Controller
 * @author lq
 */
@Controller
@RequestMapping(value = "${adminPath}/order/propertyDeal")
public class PropertyDealController extends BaseController {

    @Autowired
    private PropertyDealService propertyDealService;

    @Autowired
    private ModuleManageService moduleManageService;
    

    @Autowired
    private CompanyInfoService companyInfoService;
	
    @Autowired
    private VillageInfoService cillageInfoService;
    

    @ModelAttribute
    public PropertyDeal get(@RequestParam(required = false) String id) {
    	PropertyDeal entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = propertyDealService.get(id);
        }
        if (entity == null) {
            entity = new PropertyDeal();
        }
        return entity;
    }
    
    @RequiresPermissions("order:propertyDeal:view")
    @RequestMapping(value = { "list", "" })
    public String list(PropertyDeal propertyDeal, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PropertyDeal> page = propertyDealService.findPage(new Page<PropertyDeal>(request, response), propertyDeal);
        model.addAttribute("page", page);
        
        /*模块下拉列表*/
        model.addAttribute("allModule", moduleManageService.findAllList());
        
        /*物业下拉列表*/
        model.addAttribute("allCompany", companyInfoService.findAllList());
        
        /*楼盘下拉列表*/
        model.addAttribute("allVillage", cillageInfoService.findAllList());
        
       /* 总计*/
        model.addAttribute("total",propertyDealService.getTotal(propertyDeal));
        
        return "modules/order/PropertyDealList";
    }
    
    @RequiresPermissions("order:propertyDeal:view")
	@RequestMapping(value = { "export" })
    public String export(PropertyDeal propertyDeal,  HttpServletResponse response, Model model) {
     		try {
     			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
     			List<PropertyDeal> PropertyDealList = propertyDealService.findList(propertyDeal);

     			new ExportExcel("物业交易数据", PropertyDeal.class).setDataList(PropertyDealList).write(response, fileName).dispose();
     			// 迁移至服务订单列表页面
     			return null;
     		}
     		catch (Exception e) {
     			addMessage(model, "导出商户交易数据！失败信息：" + e.getMessage());
     			model.addAttribute("type", "error");
     		}
        
        return "modules/order/PropertyDealList";
    }
    
    
    @RequiresPermissions("order:propertyDeal:view")
    @RequestMapping(value = "form")
    public String form(String id,HttpServletRequest request,Model model) {
    	PropertyDeal propertyDeal = propertyDealService.getAll(id);
    	model.addAttribute("propertyDeal",propertyDeal);
    	return "modules/order/propertyDealForm";
    }
}