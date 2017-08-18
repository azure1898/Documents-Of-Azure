package com.its.modules.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.DateUtils;
import com.its.modules.app.common.payUtil.alipay.AlipayConfig;
import com.its.modules.app.common.payUtil.alipay.AlipayNotify;
import com.its.modules.app.common.payUtil.wechatpay.PayConfigUtil;
import com.its.modules.app.common.payUtil.wechatpay.PayToolUtil;
import com.its.modules.app.dao.RechargeManageDao;
import com.its.modules.app.entity.RechargeManage;

/**
 * 充值管理Service
 * 
 * @author like
 * 
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class RechargeManageService extends CrudService<RechargeManageDao, RechargeManage> {
	public RechargeManage get(String id) {
		return super.get(id);
	}

	public List<RechargeManage> findList(RechargeManage rechargeManage) {
		return super.findList(rechargeManage);
	}

	public Page<RechargeManage> findPage(Page<RechargeManage> page, RechargeManage rechargeManage) {
		return super.findPage(page, rechargeManage);
	}

	@Transactional(readOnly = false)
	public void save(RechargeManage rechargeManage) {
		super.save(rechargeManage);
	}

	@Transactional(readOnly = false)
	public void delete(RechargeManage rechargeManage) {
		super.delete(rechargeManage);
	}

	/**
	 * 获取楼盘下的充值计划集合
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<RechargeManage>
	 */
	public List<RechargeManage> getVillageRechargeList(String villageInfoId) {
		return dao.getVillageRechargeList(villageInfoId);
	}

	/**
	 * 钱包充值微信支付回调业务处理
	 * 
	 * @return 处理结果
	 */
	@Transactional(readOnly = false)
	public String wechatRechargeNotify(SortedMap<String, String> packageParams) {
		try {
			// 验证签名是否匹配
			if (!PayToolUtil.isTenpaySign("UTF-8", packageParams, PayConfigUtil.API_KEY)) { // 通知微信异步确认失败
				logger.warn("钱包充值微信支付回调接口==========>Sign Confirm Fail");
				return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[Sign Confirm Fail]]></return_msg>" + "</xml> ";
			}
			// 判断买家是否付款成功
			if (!"SUCCESS".equals(packageParams.get("result_code"))) {
				// 通知微信异步确认失败
				logger.warn("钱包充值微信支付回调接口==========>Synchronizing Confirm Fail");
				return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[Synchronizing Confirm Fail]]></return_msg>" + "</xml> ";
			}

			logger.warn("钱包充值微信支付回调接口==========>执行业务逻辑");
			// 商户订单号（这里指userID）
			String out_trade_no = packageParams.get("out_trade_no");
			// 支付完成时间
			String time_end = packageParams.get("time_end");
			Date payTime = DateUtils.parseDate(time_end);
			// 用户标识
			String openid = packageParams.get("openid");
			// 微信支付订单号
			String transaction_id = packageParams.get("transaction_id");

			// 通知微信异步确认成功
			logger.warn("钱包充值微信支付回调接口==========>Synchronizing Confirm Success");
			return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[Synchronizing Confirm Success]]></return_msg>" + "</xml> ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("钱包充值微信支付回调接口==========>" + e.getMessage());
			return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[系统错误]]></return_msg>" + "</xml> ";
		}
	}

	/**
	 * 钱包充值支付宝支付回调业务处理
	 * 
	 * @return 处理结果
	 */
	@Transactional(readOnly = false)
	public String alipayRechargeNotify(HttpServletRequest request, Map<String, String> params) {
		try {
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			// 签名
			String sign = request.getParameter("sign");
			// 验证消息是否是支付宝发出的合法消息
			if (!AlipayNotify.verify(params)) {
				logger.warn("钱包充值支付宝支付回调接口==========>Illegal Message");
				return "Illegal Message";
			}
			// 根据反馈回来的信息，生成签名结果
			if (!AlipayNotify.getSignVerify(params, sign)) {
				logger.warn("钱包充值支付宝支付回调接口==========>Sign Confirm Fail");
				return "Sign Confirm Fail";
			}
			// 只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功
			if (!trade_status.equals("TRADE_SUCCESS") && !trade_status.equals("TRADE_FINISHED")) {
				logger.warn("钱包充值支付宝支付回调接口==========>Synchronizing Confirm Fail");
				return "Synchronizing Confirm Fail";
			}

			logger.warn("钱包充值支付宝支付回调接口==========>执行业务逻辑");
			// // 商户订单号（这里指userID）
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			// 交易付款时间
			String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			Date payTime = DateUtils.parseDate(gmt_payment);
			// 买家支付宝账号
			String buyer_logon_id = new String(request.getParameter("buyer_logon_id").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), AlipayConfig.INPUT_CHARSET);

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("钱包充值支付宝支付回调接口==========>" + e.getMessage());
			return "System Error";
		}
		return "success";
	}
}