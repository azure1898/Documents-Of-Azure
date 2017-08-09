package com.its.modules.app.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.common.payUtil.XMLUtilJdom;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderLesson;
import com.its.modules.app.entity.OrderService;
import com.its.modules.app.service.OrderFieldService;
import com.its.modules.app.service.OrderGoodsService;
import com.its.modules.app.service.OrderGroupPurcService;
import com.its.modules.app.service.OrderLessonService;
import com.its.modules.app.service.OrderServiceService;
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
	private OrderGoodsService orderGoodsService;

	@Autowired
	private OrderServiceService orderServiceService;

	@Autowired
	private OrderLessonService orderLessonService;

	@Autowired
	private OrderFieldService orderFieldService;

	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	@Autowired
	private OrderTrackService orderTrackService;

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
	@RequestMapping(value = "wechartAdvanceOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> wechartAdvanceOrder(String appid, String mch_id, String nonce_str, String body, String out_trade_no, String spbill_create_ip, String trade_type, HttpServletRequest request, HttpServletResponse response) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, appid, mch_id, nonce_str, body, out_trade_no, spbill_create_ip, trade_type)) {
			return toJson;
		}
		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		/* Data数据结束 */
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 微信支付成功通知（回调接口）
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "wechartOrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> wechartOrderNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> toJson = new HashMap<String, Object>();

		// 解析Request获取XML字符串
		InputStream inputStream = request.getInputStream();
		StringBuffer xmlStr = new StringBuffer();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((line = reader.readLine()) != null) {
			xmlStr.append(line);
		}
		reader.close();
		inputStream.close();

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

		// 账号信息
		// String key = PayConfigUtil.API_KEY;

		// 判断签名是否正确
		// if (PayToolUtil.isTenpaySign("UTF-8", packageParams, key)) {
			String result = "";
			if ("SUCCESS".equals(packageParams.get("result_code"))) {
				// 微信支付订单号
				String transaction_id = packageParams.get("transaction_id");
				// 商户订单号
				String out_trade_no = packageParams.get("out_trade_no");

				/* 业务处理开始 */
				MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(out_trade_no);
				int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
				switch (orderType) {
				case 1:
					OrderGoods orderGoods = orderGoodsService.get(out_trade_no);
					orderGoods.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
					orderGoodsService.update(orderGoods);
					break;
				case 2:
					OrderService orderService = orderServiceService.get(out_trade_no);
					orderService.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
					orderServiceService.update(orderService);
					break;
				case 3:
					OrderLesson orderLesson = orderLessonService.get(out_trade_no);
					orderLesson.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
					orderLessonService.update(orderLesson);
					break;
				case 4:
					OrderField orderField = orderFieldService.get(out_trade_no);
					orderField.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
					orderFieldService.update(orderField);
					break;
				case 5:
					OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(out_trade_no);
					orderGroupPurc.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
					orderGroupPurcService.update(orderGroupPurc);
					break;
				default:
					toJson.put("code", Global.CODE_PROMOT);
					toJson.put("message", "订单类型有误");
					return toJson;
				}
				/* 业务处理结束 */

				// 通知微信异步确认成功
				result = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				// 通知微信异步确认失败
				result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(result.getBytes());
			out.flush();
			out.close();
		/*} else {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "签名验证失败");
		}*/
		
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "信息已获取");
		return toJson;
	}	
}