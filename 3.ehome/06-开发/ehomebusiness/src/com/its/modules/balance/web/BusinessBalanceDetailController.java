/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.balance.entity.BusinessBalanceDetail;
import com.its.modules.balance.service.BusinessBalanceDetailService;
import com.its.modules.order.service.OrderFieldService;
import com.its.modules.order.service.OrderGoodsService;
import com.its.modules.order.service.OrderGroupPurcService;
import com.its.modules.order.service.OrderLessonService;
import com.its.modules.order.service.OrderServiceService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 商家结算明细信息Controller
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/balance/businessBalanceDetail")
public class BusinessBalanceDetailController extends BaseController {

	@Autowired
	private BusinessBalanceDetailService businessBalanceDetailService;	

	@ModelAttribute
	public BusinessBalanceDetail get(@RequestParam(required = false) String id) {
		BusinessBalanceDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = businessBalanceDetailService.get(id);
		}
		if (entity == null) {
			entity = new BusinessBalanceDetail();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(BusinessBalanceDetail businessBalanceDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		// 从SESSION中取得商家信息
		User user = UserUtils.getUser();

		// 只显示属于当前商家的结算单
		String businessInfoId = user.getBusinessinfoId();
		businessBalanceDetail.setBusinessInfoId(businessInfoId);
		Page<BusinessBalanceDetail> page = businessBalanceDetailService
				.findPage(new Page<BusinessBalanceDetail>(request, response), businessBalanceDetail);
		
		model.addAttribute(businessBalanceDetail);
		model.addAttribute("page", page);
		return "modules/balance/businessBalanceDetailList";
	}

	@RequestMapping(value = "form")
	public String form(BusinessBalanceDetail businessBalanceDetail, Model model) {
		model.addAttribute("businessBalanceDetail", businessBalanceDetail);
		return "modules/balance/businessBalanceDetailForm";
	}

	@RequestMapping(value = "save")
	public String save(BusinessBalanceDetail businessBalanceDetail, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessBalanceDetail)) {
			return form(businessBalanceDetail, model);
		}
		businessBalanceDetailService.save(businessBalanceDetail);
		addMessage(redirectAttributes, "保存商家结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalanceDetail/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(BusinessBalanceDetail businessBalanceDetail, RedirectAttributes redirectAttributes) {
		businessBalanceDetailService.delete(businessBalanceDetail);
		addMessage(redirectAttributes, "删除商家结算明细信息成功");
		return "redirect:" + Global.getAdminPath() + "/balance/businessBalanceDetail/?repage";
	}

	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String export(BusinessBalanceDetail businessBalanceDetail, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "结算单明细" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<BusinessBalanceDetail> businessBalanceDetailList = businessBalanceDetailService
					.findExportList(businessBalanceDetail);

			String title = "结算单明细";
			new ExportExcel(title, BusinessBalanceDetail.class).setDataList(businessBalanceDetailList)
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出结算单明细失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/balance/businessBalanceDetail?repage";
	}

}