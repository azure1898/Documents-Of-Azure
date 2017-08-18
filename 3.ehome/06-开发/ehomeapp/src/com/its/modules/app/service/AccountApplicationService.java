package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.AccountApplicationDao;
import com.its.modules.app.entity.AccountApplication;

/**
 * 会员的应用Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class AccountApplicationService extends CrudService<AccountApplicationDao, AccountApplication> {

	public AccountApplication get(String id) {
		return super.get(id);
	}

	public List<AccountApplication> findList(AccountApplication accountApplication) {
		return super.findList(accountApplication);
	}

	public Page<AccountApplication> findPage(Page<AccountApplication> page, AccountApplication accountApplication) {
		return super.findPage(page, accountApplication);
	}

	@Transactional(readOnly = false)
	public void save(AccountApplication accountApplication) {
		super.save(accountApplication);
	}

	@Transactional(readOnly = false)
	public void delete(AccountApplication accountApplication) {
		super.delete(accountApplication);
	}

	/**
	 * 获取某用户在某楼盘下的常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<String>
	 */
	public List<String> getAccountApplicationList(String accountId, String villageInfoId) {
		return dao.getAccountApplicationList(accountId, villageInfoId);
	}

	/**
	 * 获取某用户在某楼盘下的某常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param moduleManageId
	 *            模块管理ID
	 * @return AccountApplication
	 */
	public AccountApplication getAccountApplication(String accountId, String villageInfoId, String moduleManageId) {
		return dao.getAccountApplication(accountId, villageInfoId, moduleManageId);
	}
}