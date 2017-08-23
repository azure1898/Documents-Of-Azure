package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.MemberDiscountDao;
import com.its.modules.app.entity.MemberDiscount;

/**
 * 会员的优惠券Service
 * 
 * @author admin
 * 
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class MemberDiscountService extends CrudService<MemberDiscountDao, MemberDiscount> {

	public MemberDiscount get(String id) {
		return super.get(id);
	}

	public List<MemberDiscount> findList(MemberDiscount memberDiscount) {
		return super.findList(memberDiscount);
	}

	public Page<MemberDiscount> findPage(Page<MemberDiscount> page, MemberDiscount memberDiscount) {
		return super.findPage(page, memberDiscount);
	}

	@Transactional(readOnly = false)
	public void save(MemberDiscount memberDiscount) {
		super.save(memberDiscount);
	}

	@Transactional(readOnly = false)
	public void delete(MemberDiscount memberDiscount) {
		super.delete(memberDiscount);
	}

	/**
	 * 获取某用户某楼盘下的某种优惠券数量
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param couponId
	 *            优惠券ID
	 * @return 某用户某楼盘下的某种优惠券数量
	 */
	public int getReceiveCount(String villageInfoId, String accountId, String couponId) {
		return dao.getReceiveCount(villageInfoId, accountId, couponId);
	}

	/**
	 * 获取某用户某楼盘下的某种优惠券当天领取的数量
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param couponId
	 *            优惠券ID
	 * @return 某用户某楼盘下的某种优惠券当天领取的数量
	 */
	public int getTodayReceiveCount(String villageInfoId, String accountId, String couponId) {
		return dao.getTodayReceiveCount(villageInfoId, accountId, couponId);
	}

	/**
	 * 下单赠送优惠券时判断优惠券是否已赠送
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param couponId
	 *            优惠券ID
	 * @return MemberDiscount
	 */
	public MemberDiscount judgeMemberDiscount(String villageInfoId, String accountId, String couponId) {
		return dao.judgeMemberDiscount(villageInfoId, accountId, couponId);
	}
}