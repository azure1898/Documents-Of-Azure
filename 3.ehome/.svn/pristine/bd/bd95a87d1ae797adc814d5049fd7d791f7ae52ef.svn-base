package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.CouponManageDao;
import com.its.modules.app.entity.CouponManage;
import com.its.modules.app.entity.MemberDiscount;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 优惠券管理Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class CouponManageService extends CrudService<CouponManageDao, CouponManage> {

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private MemberDiscountService memberDiscountService;

	@Autowired
	private SysCodeMaxService sysCodeMaxService;

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
	public int update(CouponManage couponManage) {
		return dao.update(couponManage);
	}

	/**
	 * 获取某用户某楼盘下的有效优惠券
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<CouponManageBean>
	 */
	public List<CouponManageBean> getValidCoupons(String villageInfoId, String accountId) {
		return dao.getValidCoupons(villageInfoId, accountId);
	}

	/**
	 * 获取某用户某楼盘下的无效优惠券（已使用、已过期、已冻结）
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<CouponManageBean>
	 */
	public List<CouponManageBean> getInvalidCoupons(String villageInfoId, String accountId) {
		return dao.getInvalidCoupons(villageInfoId, accountId);
	}

	/**
	 * 获取某用户某楼盘下的无效优惠券的使用状态（已使用、已过期、已冻结）
	 */
	public String getMemberDiscountStatus(CouponManageBean couponManageBean) {
		if ("0".equals(couponManageBean.getUseState())) {
			return "已过期";
		} else if ("1".equals(couponManageBean.getUseState())) {
			return "已使用";
		} else {
			return "已冻结";
		}
	}

	/**
	 * 获取某楼盘下买家可领取的优惠券
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<CouponManage>
	 */
	public List<CouponManage> getCanReceiveCoupons(String villageInfoId) {
		return dao.getCanReceiveCoupons(villageInfoId);
	}

	/**
	 * 获取某楼盘下买家可领取的优惠券的领取状态（已抢光、已领取、立即领取） 1->未领取 2->已抢光 3->已领取
	 */
	public int getCouponStatus(CouponManage couponManage, int receiveNum) {
		// 发放总量：0无限制 1限量发送
		if (CommonGlobal.COUPON_GRANT_TYPE_LIMIT.equals(couponManage.getGrantType())) {
			if (ValidateUtil.validateInteger(couponManage.getLimitedNum()) - ValidateUtil.validateInteger(couponManage.getReceiveNum()) == 0) {
				return 2;
			}
		}
		// 买家领取规则：0无限制 1每人每日限领1张 2每人限领1张
		if (CommonGlobal.COUPON_RECEIVE_RULE_UNLIMIT.equals(couponManage.getReceiveRule()) || receiveNum == 0) {
			return 1;
		} else {
			return 3;
		}
	}

	/**
	 * 根据优惠券ID修改优惠券领取总量
	 * 
	 * @param receiveNum
	 *            领取总量
	 * @param couponId
	 *            优惠券ID
	 */
	@Transactional(readOnly = false)
	public int updateReceiveNumById(@Param("receiveNum") Integer receiveNum, @Param("couponId") String couponId) {
		return dao.updateReceiveNumById(receiveNum, couponId);
	}

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
	public List<CouponManageBean> getEnableCoupons(String villageInfoId, String accountId, int prodType, String businessInfoId, String shareFlag, double totalMoney) {
		return dao.getEnableCoupons(villageInfoId, accountId, prodType, businessInfoId, shareFlag, totalMoney);
	}

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
	public CouponManageBean judgeCoupon(String villageInfoId, String accountId, String memberDiscountId) {
		return dao.judgeCoupon(villageInfoId, accountId, memberDiscountId);
	}

	/**
	 * 计算优惠券优惠金额
	 * 
	 * @param couponManage
	 *            优惠券信息
	 * @param totalMoney
	 *            总金额
	 * @return 优惠券优惠金额
	 */
	public double calCouponMoney(CouponManage couponManage, double totalMoney) {
		double couponMoneyNative = ValidateUtil.validateDouble(couponManage.getCouponMoney());
		double couponMoney = 0;
		// 优惠券类型：0固定金额券 1折扣券
		if (CommonGlobal.COUPON_TYPE_FIXED.equals(couponManage.getCouponType())) {
			couponMoney = couponMoneyNative;
		} else {
			couponMoney = couponMoneyNative / 100 * totalMoney;
			// 折扣券可设置优惠上限，判断折扣券是否存在优惠上限
			if (couponManage.getUpperLimitMoney() != null && couponManage.getUpperLimitMoney() != 0) {
				double upperLimitMoney = ValidateUtil.validateDouble(couponManage.getUpperLimitMoney());
				couponMoney = couponMoney > upperLimitMoney ? upperLimitMoney : couponMoney;
			}
		}
		return couponMoney;
	}

	/**
	 * 获取优惠券名称
	 * 
	 * @param couponManage
	 *            优惠券信息
	 * @return 优惠券名称
	 */
	public String getCouponName(CouponManage couponManage) {
		// 使用范围：0无限制 1服务品类专享 2商家专享
		if (CommonGlobal.COUPON_USE_SCOPE_UNLIMIT.equals(couponManage.getUseScope())) {
			return "通用券";
		} else if (CommonGlobal.COUPON_USE_SCOPE_SERVICE.equals(couponManage.getUseScope())) {
			return moduleManageService.get(couponManage.getUseObject()).getModuleName() + "专享券";
		} else {
			return businessInfoService.get(couponManage.getUseObject()).getBusinessName() + "专享券";
		}
	}

	/**
	 * 获取优惠券使用条件
	 * 
	 * @param couponManage
	 *            优惠券信息
	 * @return 优惠券使用条件
	 */
	public String getCouponCondition(CouponManage couponManage) {
		// 使用条件：0无限制 1满额使用
		if (CommonGlobal.COUPON_USE_RULE_UNLIMIT.equals(couponManage.getUseRule())) {
			return "无限制";
		} else {
			return "满" + ValidateUtil.validateDouble(couponManage.getFullUseMoney()) + "元可用";
		}
	}

	/**
	 * 生成会员的优惠券
	 * 
	 * @param couponManage
	 *            优惠券信息
	 * @param userId
	 *            用户ID
	 * @return MemberDiscount
	 */
	@Transactional(readOnly = false)
	public MemberDiscount createMemberDiscount(CouponManage couponManage, String userId) {
		MemberDiscount memberDiscount = new MemberDiscount();
		memberDiscount.setVillageInfoId(couponManage.getVillageInfoId());
		memberDiscount.setDiscountId(couponManage.getId());
		// 优惠券号（年16 + 月08 + 日28 + 六位流水号）
		memberDiscount.setDiscountNum(sysCodeMaxService.getDiscountNum());
		memberDiscount.setAccountId(userId);
		memberDiscount.setObtainDate(new Date());
		// 计算优惠券有效时间
		Date validStart = null;
		Date validEnd = null;
		// 优惠券有效期类型：0起止日期 1天
		if (CommonGlobal.COUPON_VALIDITY_TYPE_START_END.equals(couponManage.getValidityType())) {
			validStart = couponManage.getValidityStartTime();
			validEnd = couponManage.getValidityEndTime();
		} else {
			validStart = new Date();
			validEnd = businessInfoService.add(ValidateUtil.validateInteger(couponManage.getValidityDays())).getTime();
		}
		memberDiscount.setValidStart(validStart);
		memberDiscount.setValidEnd(validEnd);
		memberDiscount.setUseState(CommonGlobal.DISCOUNT_USE_STATE_UNUSED);
		memberDiscount.setReceiveType(CommonGlobal.COUPON_RECEIVE_TYPE_RECEIVE);
		// 新增会员的优惠券
		memberDiscountService.save(memberDiscount);
		// 修改优惠券领取总量
		int row = this.updateReceiveNumById(couponManage.getReceiveNum() + 1, couponManage.getId());
		if (row == 0) {
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return null;
		}
		return memberDiscount;
	}

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
	public int updateUserState(String useState, String orderId, String memberDiscountId) {
		return dao.updateUserState(useState, orderId, memberDiscountId);
	}
}