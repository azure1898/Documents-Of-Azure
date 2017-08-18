package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.dao.WalletDetailDao;
import com.its.modules.app.entity.WalletDetail;

/**
 * 钱包明细Service
 * 
 * @author like
 * 
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class WalletDetailService extends CrudService<WalletDetailDao, WalletDetail> {

	public WalletDetail get(String id) {
		return super.get(id);
	}

	public List<WalletDetail> findList(WalletDetail walletDetail) {
		return super.findList(walletDetail);
	}

	public Page<WalletDetail> findPage(Page<WalletDetail> page, WalletDetail walletDetail) {
		return super.findPage(page, walletDetail);
	}

	@Transactional(readOnly = false)
	public void save(WalletDetail walletDetail) {
		super.save(walletDetail);
	}

	@Transactional(readOnly = false)
	public void delete(WalletDetail walletDetail) {
		super.delete(walletDetail);
	}

	/**
	 * 获取钱包明细
	 * 
	 * @param accountId
	 *            用户ID
	 * @param pageNum
	 *            分页页码(从0开始为起始页)
	 * @return List<WalletDetail>
	 */
	public List<WalletDetail> getWalletDetail(String accountId, int pageNum) {
		int pageSize = AppGlobal.WALLET_PAGE_SIZE;
		return dao.getWalletDetail(accountId, pageSize * pageNum, pageSize);
	}

	/**
	 * 获取交易类型描述
	 * 
	 * @param type
	 *            交易类型：0-充值；1-充值赠送；2-钱包支付(订单消费)；3-手环支付；4-刷脸支付；5-退款(订单取消)
	 * @return String
	 */
	public String getTradeTypeString(String type) {
		String result = "";
		if (AppGlobal.TRADE_TYPE_RECHARGE.equals(type)) {
			result = "充值";
		} else if (AppGlobal.TRADE_TYPE_PRESENT.equals(type)) {
			result = "充值赠送";
		} else if (AppGlobal.TRADE_TYPE_ORDER_PAY.equals(type)) {
			result = "钱包支付";
		} else if (AppGlobal.TRADE_TYPE_BRACELET.equals(type)) {
			result = "手环支付";
		} else if (AppGlobal.TRADE_TYPE_FACE.equals(type)) {
			result = "刷脸支付";
		} else if (AppGlobal.TRADE_TYPE_REFUND.equals(type)) {
			result = "退款";
		}
		return result;
	}

	/**
	 * 获取钱包订单支付或退款的详情
	 * 
	 * @param orderId
	 *            订单ID
	 * @param tradeType
	 *            交易类型：0-充值；1-充值赠送；2-钱包支付(订单消费)；3-手环支付；4-刷脸支付；5-退款(订单取消)
	 * @return WalletDetail
	 */
	public WalletDetail getWalletDetailOfOrder(String orderId, String tradeType) {
		return dao.getWalletDetailOfOrder(orderId, tradeType);
	}
}