package com.its.modules.app.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.common.payUtil.wechatpay.XMLUtilJdom;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.OrderPayService;
import com.its.modules.app.service.OrderTrackService;

/**
 * 订单支付Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class OrderPayController extends BaseController {

	@Autowired
	private OrderPayService orderPayService;

	@Autowired
	private OrderTrackService orderTrackService;

	@Autowired
	private AccountService accountService;

	/**
	 * 微信支付预下单
	 * 
	 * @param appid
	 *            应用ID（不可空）
	 * @param mch_id
	 *            商户号（不可空）
	 * @param nonce_str
	 *            随机字符串（不可空）
	 * @param body
	 *            商品描述（不可空）
	 * @param out_trade_no
	 *            商户订单号（不可空）
	 * @param spbill_create_ip
	 *            终端ip（不可空）
	 * @param trade_type
	 *            交易类型（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "wechatAdvanceOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> wechatAdvanceOrder(String appid, String mch_id, String nonce_str, String body, String out_trade_no, String spbill_create_ip, String trade_type) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, appid, mch_id, nonce_str, body, out_trade_no, spbill_create_ip, trade_type)) {
			return toJson;
		}
		MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(out_trade_no);
		// 1、证该通知数据中的out_trade_no是否为商户系统中创建的订单号
		if (myOrderViewBean == null) {
			logger.warn("微信支付预下单==========>Order Not Exist");
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "订单不存在");
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("prepayid", null);
		data.put("appkey", null);
		data.put("nonceStr", nonce_str);
		data.put("timeStamp", new Date().getTime());
		data.put("sign", orderPayService.createSign(appid, mch_id, nonce_str, body, out_trade_no, spbill_create_ip, trade_type));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 支付宝支付加签
	 * 
	 * @param appid
	 *            应用ID（不可空）
	 * @param mch_id
	 *            商户号（不可空）
	 * @param nonce_str
	 *            随机字符串（不可空）
	 * @param body
	 *            商品描述（不可空）
	 * @param out_trade_no
	 *            商户订单号（不可空）
	 * @param spbill_create_ip
	 *            终端ip（不可空）
	 * @param trade_type
	 *            交易类型（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "alipayAdvanceOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alipayAdvanceOrder(String appid, String mch_id, String nonce_str, String body, String out_trade_no, String spbill_create_ip, String trade_type) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, appid, mch_id, nonce_str, body, out_trade_no, spbill_create_ip, trade_type)) {
			return toJson;
		}
		MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(out_trade_no);
		// 1、证该通知数据中的out_trade_no是否为商户系统中创建的订单号
		if (myOrderViewBean == null) {
			logger.warn("支付宝支付加签==========>Order Not Exist");
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "订单不存在");
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appkey", null);
		data.put("nonceStr", nonce_str);
		data.put("timeStamp", new Date().getTime());
		data.put("sign", orderPayService.createSign(appid, mch_id, nonce_str, body, out_trade_no, spbill_create_ip, trade_type));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 微信支付成功通知（回调接口）
	 */
	@RequestMapping(value = "wechatOrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public void wechatOrderNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析Request获取XML字符串
		logger.warn("微信支付回调接口==========>开始解析Request");
		InputStream inputStream = request.getInputStream();
		StringBuffer xmlStr = new StringBuffer();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((line = reader.readLine()) != null) {
			xmlStr.append(line);
		}
		reader.close();
		inputStream.close();
		logger.warn("微信支付回调接口XML文件==========>" + xmlStr);

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
		out.write(orderPayService.wechatOrderNotify(packageParams).getBytes());
		out.flush();
		out.close();
	}

	/**
	 * 支付宝支付成功通知（回调接口）
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "alipayOrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public String alipayOrderNotify(HttpServletRequest request, HttpServletResponse response) {
		// 获取支付宝POST过来的反馈信息
		logger.warn("支付宝支付回调接口==========>开始解析Request");
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
		return orderPayService.alipayOrderNotify(request, params);
	}

	/**
	 * 余额支付
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param orderID
	 *            订单ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "walletPaidOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> walletPaidOrder(String userID, String buildingID, String orderID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, orderID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(orderID);
		if (myOrderViewBean == null) {
			logger.warn("余额支付==========>Order Not Exist");
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "订单不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("isSuccess", orderPayService.walletPaidOrder(myOrderViewBean, account));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 判断订单是否支付成功
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param orderID
	 *            订单ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "isPaidSuccessOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPaidSuccessOrder(String userID, String buildingID, String orderID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, orderID)) {
			return toJson;
		}
		MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(orderID);
		if (myOrderViewBean == null) {
			logger.warn("判断订单是否支付成功==========>Order Not Exist");
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "订单不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", orderID);
		data.put("isSuccess", orderPayService.isPaidSuccessOrder(myOrderViewBean));
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}