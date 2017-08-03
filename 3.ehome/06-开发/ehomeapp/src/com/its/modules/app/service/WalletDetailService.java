/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
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
	 * @return
	 */
	public List<WalletDetail> getWalletDetail(String accountId, int pageNum) {
		int pageSize = AppGlobal.WALLET_PAGE_SIZE;
		return dao.getWalletDetail(accountId, pageSize * pageNum, pageSize);
	}

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
}