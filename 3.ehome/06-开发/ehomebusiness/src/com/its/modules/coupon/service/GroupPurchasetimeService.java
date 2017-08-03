/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.coupon.entity.GroupPurchasetime;
import com.its.modules.coupon.dao.GroupPurchasetimeDao;

/**
 * 优惠验券管理Service
 * @author caojing
 * @version 2017-07-25
 */
@Service
@Transactional(readOnly = true)
public class GroupPurchasetimeService extends CrudService<GroupPurchasetimeDao, GroupPurchasetime> {

	public GroupPurchasetime get(String id) {
		return super.get(id);
	}
	
	public List<GroupPurchasetime> findList(GroupPurchasetime groupPurchasetime) {
		return super.findList(groupPurchasetime);
	}
	
	public Page<GroupPurchasetime> findPage(Page<GroupPurchasetime> page, GroupPurchasetime groupPurchasetime) {
		return super.findPage(page, groupPurchasetime);
	}
	
	@Transactional(readOnly = false)
	public void save(GroupPurchasetime groupPurchasetime) {
		super.save(groupPurchasetime);
	}
	
	@Transactional(readOnly = false)
	public void delete(GroupPurchasetime groupPurchasetime) {
		super.delete(groupPurchasetime);
	}
	
}