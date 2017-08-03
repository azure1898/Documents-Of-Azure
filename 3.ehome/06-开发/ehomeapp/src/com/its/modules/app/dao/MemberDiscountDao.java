package com.its.modules.app.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.MemberDiscount;

/**
 * 会员的优惠券DAO接口
 * 
 * @author admin
 * 
 * @version 2017-07-04
 */
@MyBatisDao
public interface MemberDiscountDao extends CrudDao<MemberDiscount> {

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
	public int getReceiveCount(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("couponId") String couponId);

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
	public int getTodayReceiveCount(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("couponId") String couponId);
}