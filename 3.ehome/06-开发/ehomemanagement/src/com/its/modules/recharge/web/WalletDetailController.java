/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.its.common.utils.NumberUtil;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ExportExcel;
import com.its.common.web.BaseController;
import com.its.modules.recharge.entity.WalletDetail;
import com.its.modules.recharge.service.WalletDetailService;
import com.its.modules.sys.utils.DictUtils;

/**
 * 充值记录Controller
 * 
 * @author ChenXiangyu
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/recharge/walletDetail")
public class WalletDetailController extends BaseController {

	@Autowired
	private WalletDetailService walletDetailService;

	@ModelAttribute
	public WalletDetail get(@RequestParam(required = false) String id) {
		WalletDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = walletDetailService.get(id);
		}
		if (entity == null) {
			entity = new WalletDetail();
		}
		return entity;
	}

	@RequiresPermissions("recharge:walletDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(WalletDetail walletDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<WalletDetail> page = walletDetailService.findPage(new Page<WalletDetail>(request, response), walletDetail);
		if (page == null) {
			page = new Page<WalletDetail>();
		}

		Double accountRecharge = NumberUtils.DOUBLE_ZERO;// 账号充值
		Double rechargeGift = NumberUtils.DOUBLE_ZERO;// 充值赠送
		Double orderConsume = NumberUtils.DOUBLE_ZERO;// 订单消费
		Double orderCancel = NumberUtils.DOUBLE_ZERO;// 订单取消
		Double rechargeBalance = NumberUtils.DOUBLE_ZERO;// 充值余额
		// 计算前台统计数据
		for (WalletDetail tempWalletDetail : page.getList()) {
			// 金额 = 本金金额 + 赠送金额
			Double walletPrincipal = tempWalletDetail.getWalletPrincipal() != null ? tempWalletDetail.getWalletPrincipal() : Double.valueOf(0);
			Double walletPresent = tempWalletDetail.getWalletPresent() != null ? tempWalletDetail.getWalletPresent() : Double.valueOf(0);
			tempWalletDetail.setMoney(walletPrincipal + walletPresent);
			
			if (DictUtils.getDictValue(WalletDetail.LABEL_ACCOUNT_RECHARGE, WalletDetail.DICT_TYPE_TRADETYPE, "")
					.equals(tempWalletDetail.getTradeType())) {
				accountRecharge += tempWalletDetail.getMoney();
			} else if (DictUtils.getDictValue(WalletDetail.LABEL_RECHARGE_GIFT, WalletDetail.DICT_TYPE_TRADETYPE, "")
					.equals(tempWalletDetail.getTradeType())) {
				rechargeGift += tempWalletDetail.getMoney();
			} else if (DictUtils.getDictValue(WalletDetail.LABEL_ORDER_CONSUME, WalletDetail.DICT_TYPE_TRADETYPE, "")
					.equals(tempWalletDetail.getTradeType())) {
				orderConsume += tempWalletDetail.getMoney();
			} else if (DictUtils.getDictValue(WalletDetail.LABEL_ORDER_CANCEL, WalletDetail.DICT_TYPE_TRADETYPE, "")
					.equals(tempWalletDetail.getTradeType())) {
				orderCancel += tempWalletDetail.getMoney();
			}
		}
		// 余额=账号充值总额-订单消费总额+订单取消总额
		rechargeBalance = accountRecharge - orderConsume + orderCancel;

		// 交易方式下拉列表内容设置
		Map<String, String> tradeTypeMap = new HashMap<String, String>();
		tradeTypeMap.put(
				DictUtils.getDictValue(WalletDetail.LABEL_ACCOUNT_RECHARGE, WalletDetail.DICT_TYPE_TRADETYPE, ""),
				WalletDetail.LABEL_REMARKS_ACCOUNT_RECHARGE);
		tradeTypeMap.put(DictUtils.getDictValue(WalletDetail.LABEL_RECHARGE_GIFT, WalletDetail.DICT_TYPE_TRADETYPE, ""),
				WalletDetail.LABEL_REMARKS_RECHARGE_GIFT);
		tradeTypeMap.put(DictUtils.getDictValue(WalletDetail.LABEL_ORDER_CONSUME, WalletDetail.DICT_TYPE_TRADETYPE, ""),
				WalletDetail.LABEL_REMARKS_ORDER_CONSUME);
		tradeTypeMap.put(DictUtils.getDictValue(WalletDetail.LABEL_ORDER_CANCEL, WalletDetail.DICT_TYPE_TRADETYPE, ""),
				WalletDetail.LABEL_REMARKS_ORDER_CANCEL);

		model.addAttribute("page", page);
		model.addAttribute("accountRecharge", NumberUtil.doubleFormat(accountRecharge));
		model.addAttribute("rechargeGift", NumberUtil.doubleFormat(rechargeGift));
		model.addAttribute("orderConsume", NumberUtil.doubleFormat(orderConsume));
		model.addAttribute("orderCancel", NumberUtil.doubleFormat(orderCancel));
		model.addAttribute("rechargeBalance", NumberUtil.doubleFormat(rechargeBalance));
		model.addAttribute("tradeTypeMap", tradeTypeMap);
		if (walletDetail != null) {
			System.out.println(walletDetail.getVillageInfoId());
			model.addAttribute("villageInfoId", walletDetail.getVillageInfoId());
		}
		return "modules/recharge/walletDetailList";
	}

	/**
	 * 导出明细
	 * 
	 * @param walletDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("recharge:walletDetail:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportDetails(WalletDetail walletDetail, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "充值记录数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<WalletDetail> walletDetailList = walletDetailService.findList(walletDetail);

			// 交易方式Label转换
			for (WalletDetail tempWalletDetail : walletDetailList) {
				// 金额 = 本金金额 + 赠送金额
				Double walletPrincipal = tempWalletDetail.getWalletPrincipal() != null ? tempWalletDetail.getWalletPrincipal() : Double.valueOf(0);
				Double walletPresent = tempWalletDetail.getWalletPresent() != null ? tempWalletDetail.getWalletPresent() : Double.valueOf(0);
				tempWalletDetail.setMoney(walletPrincipal + walletPresent);
				
				if (DictUtils.getDictValue(WalletDetail.LABEL_ACCOUNT_RECHARGE, WalletDetail.DICT_TYPE_TRADETYPE, "")
						.equals(tempWalletDetail.getTradeType())) {
					tempWalletDetail.setTradeType(WalletDetail.LABEL_REMARKS_ACCOUNT_RECHARGE);
				} else if (DictUtils
						.getDictValue(WalletDetail.LABEL_RECHARGE_GIFT, WalletDetail.DICT_TYPE_TRADETYPE, "")
						.equals(tempWalletDetail.getTradeType())) {
					tempWalletDetail.setTradeType(WalletDetail.LABEL_REMARKS_RECHARGE_GIFT);
				} else if (DictUtils
						.getDictValue(WalletDetail.LABEL_ORDER_CONSUME, WalletDetail.DICT_TYPE_TRADETYPE, "")
						.equals(tempWalletDetail.getTradeType())) {
					tempWalletDetail.setTradeType(WalletDetail.LABEL_REMARKS_ORDER_CONSUME);
				} else if (DictUtils.getDictValue(WalletDetail.LABEL_ORDER_CANCEL, WalletDetail.DICT_TYPE_TRADETYPE, "")
						.equals(tempWalletDetail.getTradeType())) {
					tempWalletDetail.setTradeType(WalletDetail.LABEL_REMARKS_ORDER_CANCEL);
				}
			}
			
			// 序号添加
			for (int i = 1; i <= walletDetailList.size(); i++) {
				if (walletDetailList.get(i - 1) != null) {
					walletDetailList.get(i - 1).setSerialNumber(String.valueOf(i));
				}
			}

			new ExportExcel("充值记录数据", WalletDetail.class).setDataList(walletDetailList).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出充值记录明细失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/recharge/walletDetail/?repage";
	}
}