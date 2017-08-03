/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.recharge.entity.WalletDetail;
import com.its.modules.recharge.dao.WalletDetailDao;

/**
 * 充值记录Service
 * 
 * @author ChenXiangyu
 * @version 2017-07-05
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

}