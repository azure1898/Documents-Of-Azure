/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.operation.entity.CouponManageUsers;
import com.its.modules.operation.dao.CouponManageUsersDao;

/**
 * 优惠券导入的用户Service
 * @author liuqi
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class CouponManageUsersService extends CrudService<CouponManageUsersDao, CouponManageUsers> {

	public CouponManageUsers get(String id) {
		return super.get(id);
	}
	
	public List<CouponManageUsers> findList(CouponManageUsers couponManageUsers) {
		return super.findList(couponManageUsers);
	}
	
	public Page<CouponManageUsers> findPage(Page<CouponManageUsers> page, CouponManageUsers couponManageUsers) {
		return super.findPage(page, couponManageUsers);
	}
	
	@Transactional(readOnly = false)
	public void save(CouponManageUsers couponManageUsers) {
		super.save(couponManageUsers);
	}
	
	@Transactional(readOnly = false)
	public void delete(CouponManageUsers couponManageUsers) {
		super.delete(couponManageUsers);
	}

	@Transactional(readOnly = false)
	public void updateCouponManageId(CouponManageUsers couponManageUsers) {
		dao.updateCouponManageId(couponManageUsers);
	}
	
}