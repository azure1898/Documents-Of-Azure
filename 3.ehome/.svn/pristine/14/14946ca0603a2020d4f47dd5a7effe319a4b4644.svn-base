/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.account.dao.AccountDao;
import com.its.modules.account.entity.Account;

/**
 * 用户注册Service
 * 
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class AccountService extends CrudService<AccountDao, Account> {

	@Autowired
	private AccountDao accountDao;

	public Account get(String id) {
		return super.get(id);
	}

	public List<Account> findList(Account account) {
		return super.findList(account);
	}

	public Page<Account> findPage(Page<Account> page, Account account) {
		return super.findPage(page, account);
	}

	@Transactional(readOnly = false)
	public void save(Account account) {
		super.save(account);
	}

	@Transactional(readOnly = false)
	public void delete(Account account) {
		super.delete(account);
	}

	/**
	 * 更新会员使用状态
	 * 
	 * @param account
	 *            会员信息
	 */
	@Transactional(readOnly = false)
	public void updateUseState(Account account) {
		accountDao.updateUseState(account);
	}

	/**
	 * 根据下单情况获取会员列表
	 * 
	 * @param account
	 * @return
	 */
	public List<Account> findListByOrder(Account account) {
		return dao.findListByOrder(account);
	}

}