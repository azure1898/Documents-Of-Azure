package com.its.modules.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.bean.FieldPartitionBean;
import com.its.modules.app.bean.OrderFieldBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.dao.OrderFieldDao;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.entity.OrderFieldList;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 场地订单Service
 * 
 * @author like
 * 
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class OrderFieldService extends CrudService<OrderFieldDao, OrderField> {

	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private CouponManageService couponManageService;
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	@Autowired
	private ModuleManageService moduleManageService;
	@Autowired
	private OrderFieldListService orderFieldListService;
	@Autowired
	private OrderTrackService orderTrackService;
	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	public OrderField get(String id) {
		return super.get(id);
	}

	public List<OrderField> findList(OrderField orderField) {
		return super.findList(orderField);
	}

	public Page<OrderField> findPage(Page<OrderField> page, OrderField orderField) {
		return super.findPage(page, orderField);
	}

	@Transactional(readOnly = false)
	public void save(OrderField orderField) {
		super.save(orderField);
	}

	@Transactional(readOnly = false)
	public int update(OrderField orderField) {
		return dao.update(orderField);
	}

	@Transactional(readOnly = false)
	public void delete(OrderField orderField) {
		super.delete(orderField);
	}

	/**
	 * 生成场地预约订单
	 * 
	 * @param order
	 * @param business
	 * @param accountID
	 * @param villageInfoID
	 * @param moduleID
	 * @param couponID
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Map<String, String> createOrderField(OrderField order, BusinessInfo business, List<FieldPartitionBean> list, String accountID, String villageInfoID, CouponManageBean couponManage) {
		order.setBusinessInfoId(business.getId());
		order.setProdType(AppGlobal.MODEL_FIELD);// 产品模式
		order.setOrderState(AppGlobal.ORDER_APPOINTMENT_STATE_WAITING);// 订单状态:0待预约、1预约成功、2已取消
		order.setCheckOrderState(AppGlobal.ORDER_CHECK_STATE_UNDO);// 对账状态
		order.setCheckState(AppGlobal.ORDER_SETTLE_STATE_UNDO);// 结算状态
		order.setPayState(AppGlobal.ORDER_PAY_STATE_UNDO);// 支付状态：未支付
		// 模块ID
		String moduleID = moduleManageService.getModuleId(AppGlobal.MODEL_FIELD, business.getId());
		order.setModuleManageId(moduleID);// 模块管理ID
		FieldPartitionBean first = list.get(0);
		order.setFieldInfoId(first.getFieldInfoId());
		order.setName(first.getFieldName());
		double sumMoney = 0;
		for (FieldPartitionBean bean : list) {
			sumMoney += bean.getSumMoney();
		}
		// 处理商家满减活动
		double businessBenefit = businessInfoService.getCutDownMoney(business, sumMoney);
		// 处理优惠券
		double couponBenefit = 0;
		if (couponManage != null) {
			couponBenefit = couponManageService.calCouponMoney(couponManage, sumMoney);
			// 更改优惠券使用状态
			couponManageService.updateUserState(CommonGlobal.DISCOUNT_USE_STATE_USED, order.getId(), couponManage.getMemberDiscountId());
		}

		order.setSumMoney(sumMoney);// 订单金额
		order.setBenefitMoney(businessBenefit);// 商家优惠
		order.setCouponMoney(couponBenefit);// 优惠券减免
		// 实际支付 = 商品总金额 - 商家优惠-优惠券减免
		order.setPayMoney(sumMoney - businessBenefit - couponBenefit);
		/************************* 存入数据库 ***********************/
		Map<String, String> result = new HashMap<String, String>();
		String orderNo = sysCodeMaxService.getOrdNo(villageInfoID, moduleID);
		order.setOrderNo(orderNo);
		order.preInsert();
		dao.insert(order);
		// order = this.getByOrderNo(orderNo);
		for (FieldPartitionBean bean : list) {
			OrderFieldList detail = new OrderFieldList();
			detail.setBusinessInfoId(business.getId());
			detail.setFieldInfoId(bean.getFieldInfoId());
			detail.setFieldPartitionPriceId(bean.getId());
			detail.setOrderFieldId(order.getId());
			detail.setOrderNo(orderNo);
			detail.setName(bean.getFieldName());
			detail.setAppointmentTime(bean.getAppointmentTime());
			detail.setStartTime(bean.getStartTime());
			detail.setEndTime(bean.getEndTime());
			detail.setBasePrice(bean.getBasePrice());
			detail.setSumMoney(bean.getSumMoney());
			detail.preInsert();
			orderFieldListService.insert(detail);
			// 修改场地预约状态
			int record = fieldPartitionPriceService.updateFieldPartitionState(bean.getId(), AppGlobal.FIELD_APPOINTMENT_STATE_WAITING, AppGlobal.FIELD_APPOINTMENT_STATE_ALREADY);
			if (record == 0) {// 如果没有更新到数据，则说明该记录场地时段已被预约
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚事务
				result.clear();
				result.put("code", Global.CODE_PROMOT);
				result.put("message", "您选择的场地时段已被预约，请重新选择！");
				return result;
			}
		}
		// 生成订单跟踪状态
		orderTrackService.createTrackSubmit(OrderGlobal.ORDER_FIELD, order.getId(), orderNo);
		result.clear();
		result.put("code", Global.CODE_SUCCESS);
		result.put("message", order.getId());
		return result;
	}

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderFieldBean
	 */
	public OrderFieldBean getOrderFieldByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.getOrderFieldByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderField
	 */
	public OrderField judgeOrderFieldCancelAble(String orderId, String accountId) {
		return dao.judgeOrderFieldCancelAble(orderId, accountId);
	}

	/**
	 * 取消场地订单
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
		OrderField orderField = this.judgeOrderFieldCancelAble(orderId, accountId);
		if (orderField == null) {
			return false;
		}

		// 订单状态"待支付"——取消订单——订单状态"已取消"
		orderField.setOrderState(OrderGlobal.ORDER_FIELD_CANCELED);
		List<OrderFieldList> orderFieldLists = orderFieldListService.getOrderFieldList(orderField.getId());
		if (orderFieldLists == null || orderFieldLists.size() == 0) {
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		for (OrderFieldList orderFieldList : orderFieldLists) {
			// 更新场地分段信息的场地状态
			int row = fieldPartitionPriceService.updateFieldPartitionState(orderFieldList.getFieldPartitionPriceId(), AppGlobal.FIELD_APPOINTMENT_STATE_ALREADY, AppGlobal.FIELD_APPOINTMENT_STATE_WAITING);
			if (row == 0) {
				// 事务回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		}

		// 更新订单主表
		this.update(orderField);
		// 插入订单追踪
		orderTrackService.createTrackCanceled(OrderGlobal.ORDER_FIELD, orderField.getId(), orderField.getOrderNo(), cancelType);
		return true;
	}
}