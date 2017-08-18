package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.entity.CouponManage;

/**
 * 优惠券管理DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-03
 */
@MyBatisDao
public interface CouponManageDao extends CrudDao<CouponManage> {

	/**
	 * 获取某用户某楼盘下的有效优惠券
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<CouponManageBean>
	 */
	public List<CouponManageBean> getValidCoupons(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId);

	/**
	 * 获取某用户某楼盘下的无效优惠券（已使用、已过期、已冻结）
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<CouponManageBean>
	 */
	public List<CouponManageBean> getInvalidCoupons(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId);

	/**
	 * 获取某楼盘下买家可领取的优惠券
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<CouponManage>
	 */
	public List<CouponManage> getCanReceiveCoupons(String villageInfoId);

	/**
	 * 根据优惠券ID修改优惠券领取总量
	 * 
	 * @param receiveNum
	 *            领取总量
	 * @param couponId
	 *            优惠券ID
	 */
	public int updateReceiveNumById(@Param("receiveNum") Integer receiveNum, @Param("couponId") String couponId);

	/**
	 * 获取可用的优惠券
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param prodType
	 *            产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	 * @param businessInfoId
	 *            商户ID
	 * @param shareFlag
	 *            是否与其它优惠同享：0否 1是 传参规则：（0），（1），（0,1）
	 * @param totalMoney
	 *            订单总金额
	 * @return List<CouponManageBean>
	 */
	public List<CouponManageBean> getEnableCoupons(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("prodType") int prodType, @Param("businessInfoId") String businessInfoId, @Param("shareFlag") String shareFlag, @Param("totalMoney") double totalMoney);

	/**
	 * 判断优惠券是否可用
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param memberDiscountId
	 *            会员的优惠券ID
	 * @return 不可用返回NULL，可用返回优惠券信息
	 */
	public CouponManageBean judgeCoupon(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("memberDiscountId") String memberDiscountId);

	/**
	 * 修改会员优惠券的使用状态
	 * 
	 * @param useState
	 *            使用状态：0未使用；1已使用；2已过期；3已冻结
	 * @param orderId
	 *            订单ID
	 * @param memberDiscountId
	 *            会员优惠券ID
	 * @return 操作的行数
	 */
	public int updateUserState(@Param("useState") String useState, @Param("orderId") String orderId, @Param("memberDiscountId") String memberDiscountId);
}