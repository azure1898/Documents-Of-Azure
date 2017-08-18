/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.BraceletInfoDao;
import com.its.modules.app.entity.BraceletInfo;

/**
 * 手环信息Service
 * 
 * @author like
 * @version 2017-07-20
 */
@Service
@Transactional(readOnly = true)
public class BraceletInfoService extends CrudService<BraceletInfoDao, BraceletInfo> {

	public BraceletInfo get(String id) {
		return super.get(id);
	}

	public List<BraceletInfo> findList(BraceletInfo braceletInfo) {
		return super.findList(braceletInfo);
	}

	public Page<BraceletInfo> findPage(Page<BraceletInfo> page, BraceletInfo braceletInfo) {
		return super.findPage(page, braceletInfo);
	}

	@Transactional(readOnly = false)
	public void save(BraceletInfo braceletInfo) {
		super.save(braceletInfo);
	}

	@Transactional(readOnly = false)
	public void delete(BraceletInfo braceletInfo) {
		super.delete(braceletInfo);
	}

	/**
	 * 获取用户绑定某个mac地址的手环信息
	 * 
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public BraceletInfo getAccountBraceletSpe(String accountId, String villageInfoId,String bandMac) {
		return dao.getAccountBraceletSpe(accountId, villageInfoId,bandMac);
	}
	
	/**
	 * 获取用户绑定的所有手环
	 * 
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public List<BraceletInfo> getAccountBracelets(String accountId, String villageInfoId) {
		return dao.getAccountBracelets(accountId, villageInfoId);
	}

	/**
	 * 修改手环名称
	 * 
	 * @param id
	 *            手环ID
	 * @param name
	 *            新名称
	 */
	@Transactional(readOnly = false)
	public void updateName(String id, String name) {
		dao.updateName(id, name);
	}

	/**
	 * 修改目标步数
	 * @param accountId
	 * @param villageInfoId
	 * @param targetStep
	 */
	@Transactional(readOnly = false)
	public void updateTarget(String accountId, String villageInfoId, int targetStep) {
		dao.updateTarget(accountId, villageInfoId, targetStep);
	}
}