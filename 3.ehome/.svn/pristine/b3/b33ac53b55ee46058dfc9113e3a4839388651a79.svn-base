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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.operation.entity.CouponManage;
import com.its.modules.operation.entity.MemberDiscount;
import com.its.modules.operation.service.CouponManageService;
import com.its.modules.operation.service.MemberDiscountService;

/**
 * 会员的优惠券Controller
 * 
 * @author liuqi
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/memberDiscount")
public class MemberDiscountController extends BaseController {

	@Autowired
	private MemberDiscountService memberDiscountService;

	@Autowired
	private CouponManageService couponManageService;
	
	@Autowired
	private AccountService accountService;

	@ModelAttribute
	public MemberDiscount get(@RequestParam(required = false) String id) {
		MemberDiscount entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = memberDiscountService.get(id);
		}
		if (entity == null) {
			entity = new MemberDiscount();
		}
		return entity;
	}

	@RequiresPermissions("operation:memberDiscount:view")
	@RequestMapping(value = { "list", "" })
	public String list(MemberDiscount memberDiscount, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<MemberDiscount> page = memberDiscountService.findPage(new Page<MemberDiscount>(request, response),
				memberDiscount);
		
		// 列表内容编码转名称
		for (MemberDiscount md : page.getList()) {
			CouponManage couponManage = couponManageService.get(md.getDiscountId());
			if (couponManage != null) {
				// 转换优惠券名称
				String  couponName = couponManage.getCouponName();
				if (!couponName.isEmpty()) {
					md.setDiscountId(couponName);
				}
				//转换优惠券内容
				String couponType = couponManage.getCouponType(); // 优惠券类型
				Double couponMoney = couponManage.getCouponMoney(); // 优惠数量：金额或折扣数
				Double upperLimitMoney = couponManage.getUpperLimitMoney(); // 优惠上限
				if(couponType.equals(CouponManage.COUPON_TYPE_FIXMONEY)){
					md.setRemarks(String.valueOf(couponMoney)+"元券");
				} else if(couponType.equals(CouponManage.COUPON_TYPE_DISCOUNT)){
					if(couponMoney!=null){
						md.setRemarks(String.valueOf(couponMoney/10)+"折券<br/>(上限"+String.valueOf(upperLimitMoney)+"元)");
					} else {
						md.setRemarks(null);
					}
				}
				
				// 领取人
				String accountId = md.getAccountId();
				if(accountId!=null){
					Account account = accountService.get(accountId);
					if(account!=null){
						md.setAccountId(account.getPhoneNum());
					}
				}
			}
		}
		
		model.addAttribute("page", page);
		return "modules/operation/memberDiscountList";
	}

	@RequiresPermissions("operation:memberDiscount:view")
	@RequestMapping(value = "form")
	public String form(MemberDiscount memberDiscount, Model model) {
		model.addAttribute("memberDiscount", memberDiscount);
		return "modules/operation/memberDiscountForm";
	}

	@RequiresPermissions("operation:memberDiscount:edit")
	@RequestMapping(value = "save")
	public String save(MemberDiscount memberDiscount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberDiscount)) {
			return form(memberDiscount, model);
		}
		memberDiscountService.save(memberDiscount);
		addMessage(redirectAttributes, "保存会员的优惠券成功");
		return "redirect:" + Global.getAdminPath() + "/operation/memberDiscount/?repage";
	}

	@RequiresPermissions("operation:memberDiscount:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberDiscount memberDiscount, RedirectAttributes redirectAttributes) {
		memberDiscountService.delete(memberDiscount);
		addMessage(redirectAttributes, "删除会员的优惠券成功");
		return "redirect:" + Global.getAdminPath() + "/operation/memberDiscount/?repage";
	}

	@RequiresPermissions("operation:memberDiscount:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MemberDiscount memberDiscount, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户的优惠券数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MemberDiscount> page = memberDiscountService.findPage(new Page<MemberDiscount>(request, response),
    				memberDiscount);
            
            // 列表内容编码转名称
    		for (MemberDiscount md : page.getList()) {
    			CouponManage couponManage = couponManageService.get(md.getDiscountId());
    			if (couponManage != null) {
    				// 转换优惠券名称
    				String  couponName = couponManage.getCouponName();
    				if (!couponName.isEmpty()) {
    					md.setDiscountId(couponName);
    				}
    				//转换优惠券内容
    				String couponType = couponManage.getCouponType(); // 优惠券类型
    				Double couponMoney = couponManage.getCouponMoney(); // 优惠数量：金额或折扣数
    				Double upperLimitMoney = couponManage.getUpperLimitMoney(); // 优惠上限
    				if(couponType.equals(CouponManage.COUPON_TYPE_FIXMONEY)){
    					md.setVillageInfoId(String.valueOf(couponMoney)+"元券");
    				} else if(couponType.equals(CouponManage.COUPON_TYPE_DISCOUNT)){
    					if(couponMoney!=null){
    						md.setVillageInfoId(String.valueOf(couponMoney/10)+"折券(上限"+String.valueOf(upperLimitMoney)+"元)");
    					} else {
    						md.setVillageInfoId(null);
    					}
    				}
    			}
    		}
            
    		new ExportExcel("用户的优惠券数据", MemberDiscount.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户的优惠券失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/operation/memberDiscountList?repage";
    }
}