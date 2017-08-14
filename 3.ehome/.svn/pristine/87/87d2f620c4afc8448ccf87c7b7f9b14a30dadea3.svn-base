package com.its.modules.app.service;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.service.BaseService;
import com.its.common.utils.DateUtils;
import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.common.payUtil.alipay.AlipayConfig;
import com.its.modules.app.common.payUtil.alipay.AlipayNotify;
import com.its.modules.app.common.payUtil.wechatpay.PayConfigUtil;
import com.its.modules.app.common.payUtil.wechatpay.PayToolUtil;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderLesson;
import com.its.modules.app.entity.OrderService;

/**
 * 订单支付Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-08
 */
@Service
@Transactional(readOnly = true)
public class OrderPayService extends BaseService {

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
	 * 微信支付回调业务处理
	 * 
	 * @return 处理结果
	 */
	@Transactional(readOnly = false)
	public String wechatNotify(SortedMap<String, String> packageParams) {
		try {
			// 验证签名是否匹配
			/*if (!PayToolUtil.isTenpaySign("UTF-8", packageParams, PayConfigUtil.API_KEY)) {
				// 通知微信异步确认失败
				logger.warn("微信支付回调接口==========>Sign Confirm Fail");
				return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[Sign Confirm Fail]]></return_msg>" + "</xml> ";
			}*/
			// 判断买家是否付款成功
			if (!"SUCCESS".equals(packageParams.get("result_code"))) {
				// 通知微信异步确认失败
				logger.warn("微信支付回调接口==========>Synchronizing Confirm Fail");
				return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[Synchronizing Confirm Fail]]></return_msg>" + "</xml> ";
			}

			logger.warn("微信支付回调接口==========>执行业务逻辑");
			// 商户订单号
			String out_trade_no = packageParams.get("out_trade_no");
			MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(out_trade_no);
			// 证该通知数据中的out_trade_no是否为商户系统中创建的订单号
			if (myOrderViewBean == null) {
				logger.warn("微信支付回调接口==========>Order Not Exist");
				return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[Order Not Exist]]></return_msg></xml> ";
			}

			// 支付完成时间
			String time_end = packageParams.get("time_end");
			Date payTime = DateUtils.parseDate(time_end);
			// 用户标识
			String openid = packageParams.get("openid");
			// 微信支付订单号
			String transaction_id = packageParams.get("transaction_id");
			int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
			int row = 0;
			switch (orderType) {
			case 1:
				OrderGoods orderGoods = orderGoodsService.get(out_trade_no);
				orderGoods.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderGoods.setPayOrg(OrderGlobal.PAY_PLATFORM_WECHAT);
				orderGoods.setPayTime(payTime);
				orderGoods.setPayUserName(openid);
				orderGoods.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderGoods.setTransactionId(transaction_id);
				row = orderGoodsService.update(orderGoods);
				break;
			case 2:
				OrderService orderService = orderServiceService.get(out_trade_no);
				orderService.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderService.setPayOrg(OrderGlobal.PAY_PLATFORM_WECHAT);
				orderService.setPayTime(payTime);
				orderService.setPayUserName(openid);
				orderService.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderService.setTransactionId(transaction_id);
				row = orderServiceService.update(orderService);
				break;
			case 3:
				OrderLesson orderLesson = orderLessonService.get(out_trade_no);
				orderLesson.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderLesson.setPayOrg(OrderGlobal.PAY_PLATFORM_WECHAT);
				orderLesson.setPayTime(payTime);
				orderLesson.setPayUserName(openid);
				orderLesson.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderLesson.setTransactionId(transaction_id);
				row = orderLessonService.update(orderLesson);
				break;
			case 4:
				OrderField orderField = orderFieldService.get(out_trade_no);
				orderField.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderField.setPayOrg(OrderGlobal.PAY_PLATFORM_WECHAT);
				orderField.setPayTime(payTime);
				orderField.setPayUserName(openid);
				orderField.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderField.setTransactionId(transaction_id);
				row = orderFieldService.update(orderField);
				break;
			case 5:
				OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(out_trade_no);
				orderGroupPurc.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderGroupPurc.setPayOrg(OrderGlobal.PAY_PLATFORM_WECHAT);
				orderGroupPurc.setPayTime(payTime);
				orderGroupPurc.setPayUserName(openid);
				orderGroupPurc.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderGroupPurc.setTransactionId(transaction_id);
				row = orderGroupPurcService.update(orderGroupPurc);
			}
			if (row == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("微信支付回调接口==========>Order Update Fail");
				return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[Order Update Fail]]></return_msg></xml> ";
			}
			// 通知微信异步确认成功
			logger.warn("微信支付回调接口==========>Synchronizing Confirm Success");
			return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[Synchronizing Confirm Success]]></return_msg>" + "</xml> ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信支付回调接口==========>" + e.getMessage());
			return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[系统错误]]></return_msg>" + "</xml> ";
		}
	}

	/**
	 * 支付宝支付回调业务处理
	 * 
	 * @return 处理结果
	 */
	@Transactional(readOnly = false)
	public String alipayNotify(HttpServletRequest request, Map<String, String> params) {
		try {
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			// 签名
			/*String sign = request.getParameter("sign");
			// 验证消息是否是支付宝发出的合法消息
			if (!AlipayNotify.verify(params)) {
				logger.warn("支付宝支付回调接口==========>Illegal Message");
				return "Illegal Message";
			}
			// 根据反馈回来的信息，生成签名结果
			if (!AlipayNotify.getSignVerify(params, sign)) {
				logger.warn("支付宝支付回调接口==========>Sign Confirm Fail");
				return "Sign Confirm Fail";
			}*/
			// 只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功
			if (!trade_status.equals("TRADE_SUCCESS") && !trade_status.equals("TRADE_FINISHED")) {
				logger.warn("支付宝支付回调接口==========>Synchronizing Confirm Fail");
				return "Synchronizing Confirm Fail";
			}

			logger.warn("支付宝支付回调接口==========>执行业务逻辑");
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			MyOrderViewBean myOrderViewBean = orderTrackService.getMyOrderView(out_trade_no);
			// 1、证该通知数据中的out_trade_no是否为商户系统中创建的订单号
			if (myOrderViewBean == null) {
				logger.warn("支付宝支付回调接口==========>Order Not Exist");
				return "Order Not Exist";
			}

			// 订单金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			double orderMoney = ValidateUtil.parseDouble(total_amount);
			// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
			if (orderMoney != myOrderViewBean.getPayMoney()) {
				logger.warn("支付宝支付回调接口==========>Order Money Changed");
				return "Order Money Changed";
			}

			// 交易付款时间
			String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			Date payTime = DateUtils.parseDate(gmt_payment);
			// 买家支付宝账号
			String buyer_logon_id = new String(request.getParameter("buyer_logon_id").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);

			// 更新订单主表
			int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
			int row = 0;
			switch (orderType) {
			case 1:
				OrderGoods orderGoods = orderGoodsService.get(out_trade_no);
				orderGoods.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderGoods.setPayOrg(OrderGlobal.PAY_PLATFORM_ALIPAY);
				orderGoods.setPayTime(payTime);
				orderGoods.setPayUserName(buyer_logon_id);
				orderGoods.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderGoods.setTransactionId(trade_no);
				row = orderGoodsService.update(orderGoods);
				break;
			case 2:
				OrderService orderService = orderServiceService.get(out_trade_no);
				orderService.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderService.setPayOrg(OrderGlobal.PAY_PLATFORM_ALIPAY);
				orderService.setPayTime(payTime);
				orderService.setPayUserName(buyer_logon_id);
				orderService.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderService.setTransactionId(trade_no);
				row = orderServiceService.update(orderService);
				break;
			case 3:
				OrderLesson orderLesson = orderLessonService.get(out_trade_no);
				orderLesson.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderLesson.setPayOrg(OrderGlobal.PAY_PLATFORM_ALIPAY);
				orderLesson.setPayTime(payTime);
				orderLesson.setPayUserName(buyer_logon_id);
				orderLesson.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderLesson.setTransactionId(trade_no);
				row = orderLessonService.update(orderLesson);
				break;
			case 4:
				OrderField orderField = orderFieldService.get(out_trade_no);
				orderField.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderField.setPayOrg(OrderGlobal.PAY_PLATFORM_ALIPAY);
				orderField.setPayTime(payTime);
				orderField.setPayUserName(buyer_logon_id);
				orderField.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderField.setTransactionId(trade_no);
				row = orderFieldService.update(orderField);
				break;
			case 5:
				OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(out_trade_no);
				orderGroupPurc.setPayType(OrderGlobal.ORDER_PAY_TYPE);
				orderGroupPurc.setPayOrg(OrderGlobal.PAY_PLATFORM_ALIPAY);
				orderGroupPurc.setPayTime(payTime);
				orderGroupPurc.setPayUserName(buyer_logon_id);
				orderGroupPurc.setPayState(OrderGlobal.ORDER_PAY_STATE_PAYED);
				orderGroupPurc.setTransactionId(trade_no);
				row = orderGroupPurcService.update(orderGroupPurc);
			}
			// 判断是否更新成功
			if (row == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("支付宝支付回调接口==========>Order Update Fail");
				return "Order Update Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("支付宝支付回调接口==========>" + e.getMessage());
			return "System Error";
		}
		return "success";
	}
}