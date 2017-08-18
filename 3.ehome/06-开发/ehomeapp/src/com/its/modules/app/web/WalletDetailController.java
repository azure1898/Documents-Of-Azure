package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.WalletDetail;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.WalletDetailService;

import net.sf.json.JSONObject;

/**
 * 钱包明细Controller
 * 
 * @author like
 * 
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class WalletDetailController extends BaseController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private WalletDetailService walletDetailService;

	/**
	 * 钱包信息
	 * 
	 * @param userID
	 *            用户ID
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getWallet")
	public String getWallet(String userID) {
		if (StringUtils.isBlank(userID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		Account account = accountService.get(userID);
		if (account == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"用戶不存在\"}";
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("totalMoney", account.getWalletBalance());
		data.put("principal", account.getWalletPrincipal());
		data.put("giftMoney", account.getWalletPresent());
		List<WalletDetail> list = walletDetailService.getWalletDetail(userID, 0);
		List<Map<String, Object>> listJson = new ArrayList<Map<String, Object>>();
		for (WalletDetail d : list) {
			Map<String, Object> dj = new HashMap<String, Object>();
			dj.put("transactionName", walletDetailService.getTradeTypeString(d.getTradeType()));
			dj.put("transactionTime", DateFormatUtils.format(d.getTradeDate(), "yyyy-MM-dd HH:mm"));
			dj.put("transactionMoney", ValidateUtil.validateDouble(d.getWalletPrincipal()) + ValidateUtil.validateDouble(d.getWalletPresent()));
			listJson.add(dj);
		}
		data.put("details", listJson);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 余额明细
	 * 
	 * @param userID
	 *            用户ID
	 * @param pageIndex
	 *            分页页码
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getTransactionDetail")
	public String getTransactionDetail(String userID, String pageIndex) {
		if (StringUtils.isBlank(userID) || !StringUtils.isNumeric(pageIndex)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<WalletDetail> list = walletDetailService.getWalletDetail(userID, Integer.parseInt(pageIndex));
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (WalletDetail d : list) {
			Map<String, Object> dj = new HashMap<String, Object>();
			dj.put("transactionName", walletDetailService.getTradeTypeString(d.getTradeType()));
			dj.put("transactionTime", DateFormatUtils.format(d.getTradeDate(), "yyyy-MM-dd HH:mm"));
			dj.put("transactionMoney", ValidateUtil.validateDouble(d.getWalletPrincipal()) + ValidateUtil.validateDouble(d.getWalletPresent()));
			data.add(dj);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

}