package com.its.modules.app.service;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.service.BaseService;
import com.its.common.utils.DateUtils;
import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.common.payUtil.alipay.AlipayConfig;
import com.its.modules.app.common.payUtil.alipay.AlipayNotify;
import com.its.modules.app.common.payUtil.wechatpay.PayConfigUtil;
import com.its.modules.app.common.payUtil.wechatpay.PayToolUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderLesson;
import com.its.modules.app.entity.OrderService;
import com.its.modules.app.entity.WalletDetail;

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

	@Autowired
	private AccountService accountService;

	@Autowired
	private WalletDetailService walletDetailService;

	/**
	 * 创建签名
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
	 * @return 签名
	 */
	public String createSign(String appid, String mch_id, String nonce_str, String body, String out_trade_no, String spbill_create_ip, String trade_type) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("trade_type", trade_type);
		return PayToolUtil.createSign(PayConfigUtil.CHARSET, packageParams, PayConfigUtil.API_KEY);
	}

	/**
	 * 微信支付回调业务处理
	 * 
	 * @return 处理结果
	 */
	@Transactional(readOnly = false)
	public String wechatOrderNotify(SortedMap<String, String> packageParams) {
		try {
			// 验证签名是否匹配
			/*if (!PayToolUtil.isTenpaySign("UTF-8", packageParams, PayConfigUtil.API_KEY)) { // 通知微信异步确认失败
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
			// 验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
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
			int row = this.updateOrder(orderType, myOrderViewBean.getOrderId(), OrderGlobal.ORDER_PAY_TYPE, OrderGlobal.PAY_PLATFORM_WECHAT, payTime, openid, OrderGlobal.ORDER_PAY_STATE_PAYED, transaction_id);
			if (row == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("微信支付回调接口==========>Order Update Fail");
				return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[Order Update Fail]]></return_msg></xml> ";
			}
			logger.warn("微信支付回调接口==========>Order Update Success");

			// 插入订单跟踪
			orderTrackService.createTrackPayed(String.valueOf(orderType - 1), myOrderViewBean.getOrderId(), myOrderViewBean.getOrderNo());

			// 通知微信异步确认成功
			logger.warn("微信支付回调接口==========>Synchronizing Confirm Success");
			return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[Synchronizing Confirm Success]]></return_msg>" + "</xml> ";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	public String alipayOrderNotify(HttpServletRequest request, Map<String, String> params) {
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
			// 1、验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
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
			int row = this.updateOrder(orderType, myOrderViewBean.getOrderId(), OrderGlobal.ORDER_PAY_TYPE, OrderGlobal.PAY_PLATFORM_ALIPAY, payTime, buyer_logon_id, OrderGlobal.ORDER_PAY_STATE_PAYED, trade_no);
			// 判断是否更新成功
			if (row == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("支付宝支付回调接口==========>Order Update Fail");
				return "Order Update Fail";
			}
			logger.warn("支付宝支付回调接口==========>Order Update Success");

			// 插入订单跟踪
			orderTrackService.createTrackPayed(String.valueOf(orderType - 1), myOrderViewBean.getOrderId(), myOrderViewBean.getOrderNo());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("支付宝支付回调接口==========>" + e.getMessage());
			return "System Error";
		}
		return "success";
	}

	/**
	 * 余额支付
	 * 
	 * @param myOrderViewBean
	 *            订单信息
	 * @param account
	 *            用户信息
	 * @return 是否支付成功(0->失败 1->成功)
	 */
	@Transactional(readOnly = false)
	public int walletPaidOrder(MyOrderViewBean myOrderViewBean, Account account) {
		try {
			logger.warn("平台钱包支付==========>执行业务逻辑");
			Date payTime = new Date(); // 当前时间
			// 订单金额
			double payMoney = ValidateUtil.validateDouble(myOrderViewBean.getPayMoney());
			// 钱包余额
			double walletBalance = ValidateUtil.validateDouble(account.getWalletBalance());
			// 钱包本金
			double walletPrincipal = ValidateUtil.validateDouble(account.getWalletPrincipal());
			// 钱包赠送
			double walletPresent = ValidateUtil.validateDouble(account.getWalletPresent());

			// 判断钱包余额是否足以支付订单
			if (payMoney > walletBalance) {
				logger.warn("平台钱包支付==========>Wallet Balance Not Enough");
				return 0;
			}
			// 更新钱包余额
			account.setWalletBalance(walletBalance - payMoney);
			// 优先扣除钱包本金
			if (payMoney <= walletPrincipal) {
				account.setWalletPrincipal(walletPrincipal - payMoney);
			} else {
				account.setWalletPrincipal(0D);
				account.setWalletPresent(walletPresent - (payMoney - walletPrincipal));
			}
			int accountRow = accountService.update(account);
			// 判断是否更新成功
			if (accountRow == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("平台钱包支付==========>Wallet Update Fail");
				return 0;
			}
			logger.warn("平台钱包支付==========>Wallet Update Success");

			// 插入钱包明细
			WalletDetail walletDetail = new WalletDetail();
			walletDetail.setAccountId(account.getId());
			walletDetail.setVillageInfoId(account.getVillageInfoId());
			walletDetail.setTradeType(CommonGlobal.WALLET_TRADE_TYPE_ORDER_PAY);
			walletDetail.setOrderId(myOrderViewBean.getOrderId());
			walletDetail.setWalletPrincipal(walletPrincipal - account.getWalletPrincipal());
			walletDetail.setWalletPresent(walletPresent - account.getWalletPresent());
			walletDetail.setTradeDate(payTime);
			walletDetail.setTerminalSource(null);
			walletDetail.setMobileUniqueCode(null);
			walletDetail.setPayType(CommonGlobal.WALLET_PAY_TYPE_BALANCE);
			walletDetailService.save(walletDetail);
			logger.warn("平台钱包支付==========>WalletDetail Save Success");

			// 更新订单主表
			int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
			int orderRow = this.updateOrder(orderType, myOrderViewBean.getOrderId(), OrderGlobal.ORDER_PAY_TYPE, OrderGlobal.PAY_PLATFORM_WALLET, payTime, account.getId(), OrderGlobal.ORDER_PAY_STATE_PAYED, null);
			// 判断是否更新成功
			if (orderRow == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("平台钱包支付==========>Order Update Fail");
				return 0;
			}
			logger.warn("平台钱包支付==========>Order Update Success");

			// 插入订单跟踪
			orderTrackService.createTrackPayed(String.valueOf(orderType - 1), myOrderViewBean.getOrderId(), myOrderViewBean.getOrderNo());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("平台钱包支付==========>" + e.getMessage());
			return 0;
		}
		return 1;
	}

	/**
	 * 判断订单是否支付成功
	 * 
	 * @return 已支付返回1，其他返回0
	 */
	public int isPaidSuccessOrder(MyOrderViewBean myOrderViewBean) {
		int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
		String payState = "0";
		switch (orderType) {
		case 1:
			OrderGoods orderGoods = orderGoodsService.get(myOrderViewBean.getOrderId());
			payState = orderGoods.getPayState();
			break;
		case 2:
			OrderService orderService = orderServiceService.get(myOrderViewBean.getOrderId());
			payState = orderService.getPayState();
			break;
		case 3:
			OrderLesson orderLesson = orderLessonService.get(myOrderViewBean.getOrderId());
			payState = orderLesson.getPayState();
			break;
		case 4:
			OrderField orderField = orderFieldService.get(myOrderViewBean.getOrderId());
			payState = orderField.getPayState();
			break;
		case 5:
			OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(myOrderViewBean.getOrderId());
			payState = orderGroupPurc.getPayState();
		}
		return OrderGlobal.ORDER_PAY_STATE_PAYED.equals(payState) ? 1 : 0;
	}

	/**
	 * 更新订单主表
	 * 
	 * @param orderType
	 *            订单类型1->商品购买订单 2->服务预约订单 3->课程购买订单 4->场地预约订单 5->精品团购订单
	 * @param orderId
	 *            订单ID
	 * @param payType
	 *            支付方式(0在线支付)
	 * @param payOrg
	 *            支付机构(0微信1支付宝2平台钱包)
	 * @param payTime
	 *            支付时间
	 * @param payUserName
	 *            支付账号
	 * @param payState
	 *            支付状态:0未支付1已支付2退款中3已退款
	 * @param transactionId
	 *            微信或支付宝交易号
	 * @return 操作的行数
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int updateOrder(int orderType, String orderId, String payType, String payOrg, Date payTime, String payUserName, String payState, String transactionId) {
		int row = 0;
		switch (orderType) {
		case 1:
			OrderGoods orderGoods = orderGoodsService.get(orderId);
			orderGoods.setPayType(payType);
			orderGoods.setPayOrg(payOrg);
			orderGoods.setPayTime(payTime);
			orderGoods.setPayUserName(payUserName);
			orderGoods.setPayState(payState);
			orderGoods.setTransactionId(transactionId);
			row = orderGoodsService.update(orderGoods);
			break;
		case 2:
			OrderService orderService = orderServiceService.get(orderId);
			orderService.setPayType(payType);
			orderService.setPayOrg(payOrg);
			orderService.setPayTime(payTime);
			orderService.setPayUserName(payUserName);
			orderService.setPayState(payState);
			orderService.setTransactionId(transactionId);
			row = orderServiceService.update(orderService);
			break;
		case 3:
			OrderLesson orderLesson = orderLessonService.get(orderId);
			orderLesson.setPayType(payType);
			orderLesson.setPayOrg(payOrg);
			orderLesson.setPayTime(payTime);
			orderLesson.setPayUserName(payUserName);
			orderLesson.setPayState(payState);
			orderLesson.setTransactionId(transactionId);
			row = orderLessonService.update(orderLesson);
			break;
		case 4:
			OrderField orderField = orderFieldService.get(orderId);
			orderField.setPayType(payType);
			orderField.setPayOrg(payOrg);
			orderField.setPayTime(payTime);
			orderField.setPayUserName(payUserName);
			orderField.setPayState(payState);
			orderField.setTransactionId(transactionId);
			row = orderFieldService.update(orderField);
			break;
		case 5:
			OrderGroupPurc orderGroupPurc = orderGroupPurcService.get(orderId);
			orderGroupPurc.setPayType(payType);
			orderGroupPurc.setPayOrg(payOrg);
			orderGroupPurc.setPayTime(payTime);
			orderGroupPurc.setPayUserName(payUserName);
			orderGroupPurc.setPayState(payState);
			orderGroupPurc.setTransactionId(transactionId);
			row = orderGroupPurcService.update(orderGroupPurc);
		}
		return row;
	}
}