package com.its.modules.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.bean.OrderLessonBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.OrderLessonDao;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.LessonInfo;
import com.its.modules.app.entity.OrderLesson;
import com.its.modules.app.entity.OrderLessonList;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 订单-课程培训类Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class OrderLessonService extends CrudService<OrderLessonDao, OrderLesson> {

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private LessonInfoService lessonInfoService;

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	@Autowired
	private VillageInfoService villageInfoService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private OrderLessonListService orderLessonListService;

	@Autowired
	private OrderTrackService orderTrackService;

	public OrderLesson get(String id) {
		return super.get(id);
	}

	public List<OrderLesson> findList(OrderLesson orderLesson) {
		return super.findList(orderLesson);
	}

	public Page<OrderLesson> findPage(Page<OrderLesson> page, OrderLesson orderLesson) {
		return super.findPage(page, orderLesson);
	}

	@Transactional(readOnly = false)
	public void save(OrderLesson orderLesson) {
		super.save(orderLesson);
	}

	@Transactional(readOnly = false)
	public int update(OrderLesson orderLesson) {
		return dao.update(orderLesson);
	}

	@Transactional(readOnly = false)
	public void delete(OrderLesson orderLesson) {
		super.delete(orderLesson);
	}

	/**
	 * 获取某课程培训用户已购数量
	 * 
	 * @param accountId
	 *            用户ID
	 * @param lessonInfoId
	 *            课程ID
	 * @return 某课程培训用户已购数量
	 */
	public int getCountByLessonInfoIdAndAccountId(String accountId, String lessonInfoId) {
		return dao.getCountByLessonInfoIdAndAccountId(accountId, lessonInfoId);
	}

	/**
	 * 根据订单号获取课程购买订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderLesson
	 */
	public OrderLesson getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}

	/**
	 * 保存课程购买订单和课程购买清单
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createOrderLesson(Account account, String contactPerson, String contactPhone, LessonInfo lessonInfo, CouponManageBean couponManageBean, String leaveMessage) {
		BusinessInfo businessInfo = businessInfoService.get(lessonInfo.getBusinessInfoId());
		VillageInfo villageInfo = villageInfoService.get(account.getVillageInfoId());
		double totalMoney = lessonInfoService.getLessonPrice(lessonInfo);

		// 优惠券优惠金额
		double couponMoney = 0;
		if (couponManageBean != null) {
			couponMoney = couponManageService.calCouponMoney(couponManageBean, totalMoney);
		}
		// 实际支付金额
		double payMoney = totalMoney - couponMoney;

		/* 生成订单开始 */
		OrderLesson orderLesson = new OrderLesson();
		String moduleManageId = moduleManageService.getModuleId(OrderGlobal.MODEL_LESSON, businessInfo.getId());
		String orderNo = sysCodeMaxService.getOrdNo(villageInfo.getId(), moduleManageId);
		orderLesson.setBusinessInfoId(businessInfo.getId());
		orderLesson.setOrderNo(orderNo);
		orderLesson.setModuleManageId(moduleManageId);
		// 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
		orderLesson.setProdType(OrderGlobal.MODEL_LESSON);
		// 终端类型(0 Android 1 IOS 2 商家后台)
		orderLesson.setType(null);
		orderLesson.setVillageInfoId(villageInfo.getId());
		orderLesson.setProvinceId(villageInfo.getAddrPro());
		orderLesson.setCityId(villageInfo.getAddrCity());
		orderLesson.setLessonInfoId(lessonInfo.getId());
		orderLesson.setName(lessonInfo.getName());
		orderLesson.setSumMoney(totalMoney);
		orderLesson.setCouponMoney(couponMoney);
		orderLesson.setPayMoney(payMoney);
		// 订单状态:0待预约、1预约成功、2已取消
		orderLesson.setOrderState(OrderGlobal.ORDER_LESSON_UNAPPOINT);
		// 支付对账状态:0未对账1正常2异常
		orderLesson.setCheckOrderState(OrderGlobal.ORDER_CHECK_STATE_UNDO);
		// 结算状态:0未结算1已结算
		orderLesson.setCheckState(OrderGlobal.ORDER_SETTLE_STATE_UNDO);
		orderLesson.setAccountId(account.getId());
		orderLesson.setAccountName(contactPerson);
		orderLesson.setAccountPhoneNumber(contactPhone);
		orderLesson.setAccountMsg(leaveMessage);
		// 支付状态:0未支付1已支付2退款中3已退款
		orderLesson.setPayState(OrderGlobal.ORDER_PAY_STATE_UNPAY);
		this.save(orderLesson);

		String orderLessonId = this.getByOrderNo(orderNo).getId();
		OrderLessonList orderLessonList = new OrderLessonList();
		orderLessonList.setBusinessInfoId(businessInfo.getId());
		orderLessonList.setLessonInfoId(lessonInfo.getId());
		orderLessonList.setOrderLessonId(orderLessonId);
		orderLessonList.setOrderNo(orderNo);
		orderLessonList.setName(lessonInfo.getName());
		orderLessonList.setImgs(lessonInfo.getImgs());
		orderLessonList.setPeopleLimit(lessonInfo.getPeopleLimit());
		orderLessonList.setLessonCount(lessonInfo.getLessonCount());
		orderLessonList.setStartTime(lessonInfo.getStartTime());
		orderLessonList.setEndTime(lessonInfo.getEndTime());
		orderLessonList.setAddress(lessonInfo.getAddress());
		orderLessonList.setContent(lessonInfo.getContent());
		orderLessonList.setBasePrice(lessonInfo.getBasePrice());
		orderLessonList.setBenefitPrice(lessonInfo.getBenefitPrice());
		orderLessonList.setPaySumMoney(totalMoney);
		orderLessonListService.save(orderLessonList);
		/* 生成订单结束 */

		// 修改会员的优惠券使用状态
		if (couponManageBean != null) {
			couponManageService.updateUserState(CommonGlobal.DISCOUNT_USE_STATE_USED, orderLesson.getId(), couponManageBean.getMemberDiscountId());
		}
		// 更新课程库存、已购数量
		lessonInfo.setPeopleLimit(ValidateUtil.validateInteger(lessonInfo.getPeopleLimit()) - 1);
		lessonInfo.setSellCount(ValidateUtil.validateInteger(lessonInfo.getSellCount()) + 1);
		lessonInfoService.update(lessonInfo);
		// 插入订单追踪
		orderTrackService.createTrackSubmit(OrderGlobal.ORDER_LESSON, orderLessonId, orderNo);
		return orderLessonId;
	}

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderLessonBean
	 */
	public OrderLessonBean getOrderLessonByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.getOrderLessonByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderLesson
	 */
	public OrderLesson judgeOrderLessonCancelAble(String orderId, String accountId) {
		return dao.judgeOrderLessonCancelAble(orderId, accountId);
	}

	/**
	 * 取消课程订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @param cancelType
	 *            取消类型：0超时取消 1用户取消
	 * @return 取消成功返回true，失败返回false
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean cancelOrder(String orderId, String accountId, String cancelType) {
		// 判断订单是否可取消
		OrderLesson orderLesson = this.judgeOrderLessonCancelAble(orderId, accountId);
		if (orderLesson == null) {
			return false;
		}

		// 订单状态"待支付"——取消订单——订单状态"已取消"
		orderLesson.setOrderState(OrderGlobal.ORDER_LESSON_CANCELED);
		// 更新课程已售数量
		int row = lessonInfoService.updateSellCount(orderLesson.getLessonInfoId());
		if (row == 0) {
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		// 更新订单主表
		this.update(orderLesson);
		// 插入订单追踪
		orderTrackService.createTrackCanceled(OrderGlobal.ORDER_LESSON, orderLesson.getId(), orderLesson.getOrderNo(), cancelType);
		return true;
	}
}