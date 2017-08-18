/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.operation.dao.CouponManageDao;
import com.its.modules.operation.dao.MemberDiscountDao;
import com.its.modules.operation.entity.CouponManage;
import com.its.modules.operation.entity.MemberDiscount;

/**
 * 优惠券管理Service
 * 
 * @author liuqi
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class CouponManageService extends CrudService<CouponManageDao, CouponManage> {

	@Autowired
	MemberDiscountDao memberDiscountDao;
	
	public CouponManage get(String id) {
		return super.get(id);
	}

	public List<CouponManage> findList(CouponManage couponManage) {
		return super.findList(couponManage);
	}

	public Page<CouponManage> findPage(Page<CouponManage> page, CouponManage couponManage) {
		return super.findPage(page, couponManage);
	}

	@Transactional(readOnly = false)
	public void save(CouponManage couponManage) {
		super.save(couponManage);
	}

	@Transactional(readOnly = false)
	public void delete(CouponManage couponManage) {
		//先删除该优惠活动下的优惠券
		MemberDiscount memberDiscount = new MemberDiscount();
		memberDiscount.setDiscountId(couponManage.getId());
		int i = memberDiscountDao.deleteByDiscountId(memberDiscount);
		logger.warn("memberDiscount对象为："+memberDiscount.toString());
		logger.warn("Delete Record is:"+i);
		
		//再删除优惠券活动
		super.delete(couponManage);
	}
	
	/**
	 * 优惠券活动关闭，同时冻结相关的优惠券
	 * 
	 * @param couponManage
	 */
	@Transactional(readOnly = false)
	public void close(CouponManage couponManage) {
		//先冻结该优惠活动下的优惠券
		// modified by LiuQi,2017-08-16,关闭优惠券活动不再对会员的优惠券状态进行修改
		/*MemberDiscount memberDiscount = new MemberDiscount();
		memberDiscount.setDiscountId(couponManage.getId());
		memberDiscount.setUseState(MemberDiscount.USE_STATE_FROZEN);
		int i = memberDiscountDao.updateUseStateByDiscountId(memberDiscount);
		logger.warn("memberDiscount对象为："+memberDiscount.toString());
		logger.warn("Close Record is:"+i);*/
		
		//关闭优惠券活动
		couponManage.setActiveState(CouponManage.ACTIVE_STATE_CLOSED);
		dao.close(couponManage);
	}
}