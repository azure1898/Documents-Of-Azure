package com.its.modules.app.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;

import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.common.payUtil.wechatpay.XMLUtilJdom;
import com.its.modules.app.entity.RechargeManage;
import com.its.modules.app.service.RechargeManageService;

/**
 * 充值管理Controller
 * 
 * @author like
 * 
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class RechargeManageController extends BaseController {

	@Autowired
	private RechargeManageService rechargeManageService;

	/**
	 * 钱包充值
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getRechargeData")
	@ResponseBody
	public Map<String, Object> getRechargeData(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}

		List<RechargeManage> list = rechargeManageService.getVillageRechargeList(buildingID);
		List<Map<String, Object>> data = new ArrayList<>();
		for (RechargeManage rm : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fixedMoney", rm.getRechargeMoney());
			map.put("giftMoney", rm.getGiveMoney());
			data.add(map);
		}

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "成功");
		return toJson;
	}

	/**
	 * 钱包充值微信支付成功通知（回调接口）
	 */
	@RequestMapping(value = "wechatRechargeNotify", method = RequestMethod.POST)
	@ResponseBody
	public void wechatRechargeNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析Request获取XML字符串
		logger.warn("钱包充值微信支付回调接口==========>开始解析Request");
		InputStream inputStream = request.getInputStream();
		StringBuffer xmlStr = new StringBuffer();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((line = reader.readLine()) != null) {
			xmlStr.append(line);
		}
		reader.close();
		inputStream.close();
		logger.warn("钱包充值微信支付回调接口XML文件==========>" + xmlStr);

		// 解析XML成Map
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtilJdom.doXMLParse(xmlStr.toString());
		// 过滤VALUE中的空格
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String parameterValue = map.get(key);
			String value = "";
			if (null != parameterValue) {
				value = parameterValue.trim();
			}
			packageParams.put(key, value);
		}
		logger.warn("packageParams==========>" + packageParams.toString());

		// 执行业务逻辑
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(rechargeManageService.wechatRechargeNotify(packageParams).getBytes());
		out.flush();
		out.close();
	}

	/**
	 * 钱包充值支付宝支付成功通知（回调接口）
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "alipayRechargeNotify", method = RequestMethod.POST)
	@ResponseBody
	public String alipayRechargeNotify(HttpServletRequest request, HttpServletResponse response) {
		// 获取支付宝POST过来的反馈信息
		logger.warn("钱包充值支付宝支付回调接口==========>开始解析Request");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		Iterator<String> it = requestParams.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String[] values = requestParams.get(key);
			String value = "";
			for (int i = 0; i < values.length; i++) {
				value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
			}
			params.put(key, value);
		}
		logger.warn("params==========>" + params.toString());

		// 执行业务逻辑
		return rechargeManageService.alipayRechargeNotify(request, params);
	}
}