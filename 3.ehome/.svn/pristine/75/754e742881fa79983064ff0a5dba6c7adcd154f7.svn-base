package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.dao.OrderRefundInfoDao;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderRefundInfo;
import com.its.modules.app.entity.WalletDetail;

/**
 * 订单-退款信息Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class OrderRefundInfoService extends CrudService<OrderRefundInfoDao, OrderRefundInfo> {

	@Autowired
	private AccountService accountService;

	@Autowired
	private WalletDetailService walletDetailService;

	public OrderRefundInfo get(String id) {
		return super.get(id);
	}

	public List<OrderRefundInfo> findList(OrderRefundInfo orderRefundInfo) {
		return super.findList(orderRefundInfo);
	}

	public Page<OrderRefundInfo> findPage(Page<OrderRefundInfo> page, OrderRefundInfo orderRefundInfo) {
		return super.findPage(page, orderRefundInfo);
	}

	@Transactional(readOnly = false)
	public void save(OrderRefundInfo orderRefundInfo) {
		super.save(orderRefundInfo);
	}

	@Transactional(readOnly = false)
	public void delete(OrderRefundInfo orderRefundInfo) {
		super.delete(orderRefundInfo);
	}

	/**
	 * 团购券申请退款插入订单退款信息
	 */
	@Transactional(readOnly = false)
	public void groupPurchaseRefund(OrderGroupPurc orderGroupPurc, String groupVouchers, String refundReason, String refundMessage) {
		OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
		orderRefundInfo.setOrderId(orderGroupPurc.getId());
		orderRefundInfo.setOrderNo(orderGroupPurc.getOrderNo());
		orderRefundInfo.setTransactionId(orderGroupPurc.getTransactionId());
		orderRefundInfo.setOrderType(OrderGlobal.ORDER_GROUP_PURCHASE);
		orderRefundInfo.setPayType(orderGroupPurc.getPayType());
		orderRefundInfo.setOrderMoney(orderGroupPurc.getPayMoney());
		orderRefundInfo.setType(orderGroupPurc.getType());
		orderRefundInfo.setModuleManageId(orderGroupPurc.getModuleManageId());
		orderRefundInfo.setProdType(orderGroupPurc.getProdType());
		orderRefundInfo.setRefundMoney(null);
		orderRefundInfo.setRefundNo(null);
		orderRefundInfo.setRefundTransactionId(null);
		orderRefundInfo.setRefundType(orderGroupPurc.getPayOrg());
		orderRefundInfo.setRefundState(OrderGlobal.ORDER_REFUND_STATE_UNREFUND);
		orderRefundInfo.setRefundOverTime(null);
		orderRefundInfo.setRefundOrderGroupPurcListIds(groupVouchers);
		orderRefundInfo.setRefundReason(refundReason);
		orderRefundInfo.setRefundDescribe(refundMessage);
		this.save(orderRefundInfo);
	}

	// 退款方式为原路返回
	public void refund(String accountId, String orderId, String payOrg) {
		Account account = accountService.get(accountId);
		// 平台钱包退款
		if (OrderGlobal.PAY_PLATFORM_WALLET.equals(payOrg)) {
			// 获取钱包支付明细
			WalletDetail walletDetailPay = walletDetailService.getWalletDetailOfOrder(orderId, CommonGlobal.WALLET_TRADE_TYPE_ORDER_PAY);
			// 更新会员钱包信息
			account.setWalletBalance(account.getWalletBalance() + walletDetailPay.getWalletPrincipal() + account.getWalletPresent());
			account.setWalletPrincipal(account.getWalletPrincipal() + walletDetailPay.getWalletPrincipal());
			account.setWalletPresent(account.getWalletPresent() + walletDetailPay.getWalletPresent());
			int accountRow = accountService.update(account);
			if (accountRow == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.warn("钱包订单退款==========>Refund Fail! Because Account Wallet Update Fail!");
			}
			// 插入钱包明细信息
			WalletDetail walletDetailRefund = new WalletDetail();
			walletDetailRefund.setAccountId(account.getId());
			walletDetailRefund.setVillageInfoId(account.getVillageInfoId());
			walletDetailRefund.setTradeType(CommonGlobal.WALLET_TRADE_TYPE_REFUND);
			walletDetailRefund.setOrderId(orderId);
			walletDetailRefund.setWalletPrincipal(walletDetailPay.getWalletPrincipal());
			walletDetailRefund.setWalletPresent(account.getWalletPresent());
			walletDetailRefund.setTradeDate(new Date());
			walletDetailRefund.setTerminalSource(null);
			walletDetailRefund.setMobileUniqueCode(null);
			walletDetailRefund.setPayType(null);
			walletDetailService.save(walletDetailRefund);
		}
		// 微信支付退款
		// 支付宝退款
	}
}