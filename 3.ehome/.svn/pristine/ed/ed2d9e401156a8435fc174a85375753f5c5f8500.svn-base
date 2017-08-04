package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.bean.OrderServiceBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.OrderServiceDao;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.BusinessSales;
import com.its.modules.app.entity.OrderService;
import com.its.modules.app.entity.OrderServiceList;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.entity.ServiceInfo;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 订单-服务类Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceService extends CrudService<OrderServiceDao, OrderService> {

	@Autowired
	private ServiceInfoService serviceInfoService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private OrderServiceListService orderServiceListService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private VillageInfoService villageInfoService;

	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	@Autowired
	private OrderTrackService orderTrackService;

	public OrderService get(String id) {
		return super.get(id);
	}

	public List<OrderService> findList(OrderService orderService) {
		return super.findList(orderService);
	}

	public Page<OrderService> findPage(Page<OrderService> page, OrderService orderService) {
		return super.findPage(page, orderService);
	}

	@Transactional(readOnly = false)
	public void save(OrderService orderService) {
		super.save(orderService);
	}

	@Transactional(readOnly = false)
	public void update(OrderService orderService) {
		dao.update(orderService);
	}

	@Transactional(readOnly = false)
	public void delete(OrderService orderService) {
		super.delete(orderService);
	}

	/**
	 * 获取某预约服务已购数量
	 * 
	 * @param serviceInfoId
	 *            服务ID
	 */
	public int getCountByServiceInfoId(String serviceInfoId) {
		return ValidateUtil.validateInteger(dao.getCountByServiceInfoId(serviceInfoId));
	}

	/**
	 * * 获取某预约服务用户已购数量
	 * 
	 * @param serviceInfoId
	 *            服务ID
	 * @param accountId
	 *            用户ID
	 * @return 已购数量
	 */
	public int getCountByServiceInfoIdAndAccountId(String serviceInfoId, String accountId) {
		return ValidateUtil.validateInteger(dao.getCountByServiceInfoIdAndAccountId(serviceInfoId, accountId));
	}

	/**
	 * 根据订单号获取预约服务订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderService
	 */
	public OrderService getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}

	/**
	 * 保存预约服务订单和预约服务清单
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createOrderService(Account account, String contactPerson, String contactPhone, String contactAddress, ServiceInfo serviceInfo, int appointNum, int isImmediate, String serviceStart, String serviceEnd, CouponManageBean couponManageBean, String leaveMessage) {
		BusinessInfo businessInfo = businessInfoService.get(serviceInfo.getBusinessInfoId());
		VillageInfo villageInfo = villageInfoService.get(account.getVillageInfoId());
		double totalMoney = appointNum * serviceInfoService.getServicePrice(serviceInfo);

		/* 商家优惠金额开始 */
		double benefitMoney = 0;
		List<BusinessSales> businessSales = new ArrayList<BusinessSales>();
		// 是否启用满减活动：0否 1是
		if ("1".equals(businessInfo.getPromotionFlag())) {
			businessSales = businessInfoService.getBusinessSales(businessInfo.getId());
			if (businessSales.size() != 0) {
				for (int i = businessSales.size() - 1; i >= 0; i--) {
					if (ValidateUtil.validateDouble(businessSales.get(i).getMoney()) <= totalMoney) {
						benefitMoney = ValidateUtil.validateDouble(businessSales.get(i).getBenefitMoney());
						break;
					}
				}
			}
		}
		/* 商家优惠金额结束 */

		// 优惠券优惠金额
		double couponMoney = 0;
		if (couponManageBean != null) {
			couponMoney = couponManageService.calCouponMoney(couponManageBean, totalMoney);
		}
		// 实际支付金额
		double payMoney = totalMoney - benefitMoney - couponMoney;

		/* 生成订单开始 */
		OrderService orderService = new OrderService();
		String moduleManageId = moduleManageService.getModuleId(OrderGlobal.MODEL_SERVICE, businessInfo.getId());
		String orderNo = sysCodeMaxService.getOrdNo(villageInfo.getId(), moduleManageId);
		orderService.setBusinessInfoId(businessInfo.getId());
		orderService.setOrderNo(orderNo);
		orderService.setModuleManageId(moduleManageId);
		// 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
		orderService.setProdType(OrderGlobal.MODEL_SERVICE);
		// 终端类型(0 Android 1 IOS 2 商家后台)
		orderService.setType(null);
		orderService.setVillageInfoId(villageInfo.getId());
		orderService.setProvinceId(villageInfo.getAddrPro());
		orderService.setCityId(villageInfo.getAddrCity());
		orderService.setSumMoney(totalMoney);
		orderService.setBenefitMoney(benefitMoney);
		orderService.setCouponMoney(couponMoney);
		orderService.setPayMoney(payMoney);
		// 订单状态:0待受理、1已受理、2已完成、3已取消
		orderService.setOrderState(OrderGlobal.ORDER_SERVICE_UNCHECK);
		// 支付对账状态:0未对账1正常2异常
		orderService.setCheckOrderState(OrderGlobal.ORDER_CHECK_STATE_UNDO);
		// 结算状态:0未结算1已结算
		orderService.setCheckState(OrderGlobal.ORDER_SETTLE_STATE_UNDO);
		orderService.setAccountId(account.getId());
		orderService.setAccountName(contactPerson);
		orderService.setAccountPhoneNumber(contactPhone);
		orderService.setAccountMsg(leaveMessage);
		orderService.setServiceType(businessInfo.getServiceModel());
		orderService.setServiceAddress(contactAddress);
		orderService.setServiceMoney(businessInfo.getServiceCharge());
		if (CommonGlobal.APPOINT_SERVICE_TYPE_VISIT.equals(businessInfo.getServiceModel())) {
			orderService.setIsStart(isImmediate == 0 ? "0" : "1");
			// 是否立即上门：0否 1是
			if (isImmediate == 0) {
				orderService.setStartTime(serviceStart);
				orderService.setEndTime(serviceEnd);
			} else {
				orderService.setEndTime(serviceEnd);
			}
		} else {
			orderService.setStartTime(serviceStart);
			orderService.setEndTime(serviceEnd);
		}
		// 支付状态:0未支付1已支付2退款中3已退款
		orderService.setPayState(OrderGlobal.ORDER_PAY_STATE_UNPAY);
		this.save(orderService);

		String orderServiceId = this.getByOrderNo(orderNo).getId();
		OrderServiceList orderServiceList = new OrderServiceList();
		orderServiceList.setBusinessInfoId(businessInfo.getId());
		orderServiceList.setServiceInfoId(serviceInfo.getId());
		orderServiceList.setOrderServiceId(orderServiceId);
		orderServiceList.setOrderNo(orderNo);
		orderServiceList.setSerialNumbers(serviceInfo.getSerialNumbers());
		orderServiceList.setName(serviceInfo.getName());
		orderServiceList.setImgs(serviceInfo.getImgs());
		orderServiceList.setBaseUnit(serviceInfo.getBaseUnit());
		orderServiceList.setPayCount(appointNum);
		orderServiceList.setContent(serviceInfo.getContent());
		orderServiceList.setBasePrice(serviceInfo.getBasePrice());
		orderServiceList.setBenefitPrice(serviceInfo.getBenefitPrice());
		orderServiceList.setPaySumMoney(totalMoney);
		orderServiceListService.save(orderServiceList);
		/* 生成订单结束 */

		// 修改会员的优惠券使用状态
		if (couponManageBean != null) {
			couponManageService.updateUserState(couponManageBean.getMemberDiscountId(), CommonGlobal.DISCOUNT_USE_STATE_USED);
		}
		// 更新预约服务已购数量
		serviceInfo.setSellCount(ValidateUtil.validateInteger(serviceInfo.getSellCount()) + appointNum);
		serviceInfoService.update(serviceInfo);
		// 插入订单追踪
		orderTrackService.createTrackSubmit(OrderGlobal.ORDER_SERVICE, orderServiceId, orderNo);

		return orderServiceId;
	}

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderServiceBean
	 */
	public OrderServiceBean getOrderServiceByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.getOrderServiceByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderService
	 */
	public OrderService judgeOrderServiceByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.judgeOrderServiceByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 取消服务订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return 取消成功返回true，失败返回false
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean cancelOrder(String orderId, String accountId) {
		// 判断订单是否可取消
		OrderService orderService = this.judgeOrderServiceByOrderIdAndAccountId(orderId, accountId);
		if (orderService == null) {
			return false;
		}

		int state = 0;
		if (OrderGlobal.ORDER_PAY_STATE_UNPAY.equals(orderService.getPayState())) {
			// 待支付——取消订单——已取消
			state = 0;
			orderService.setOrderState(OrderGlobal.ORDER_GOODS_CANCELED);
		} else {
			// 待处理——取消订单——退款中
			state = 1;
			orderService.setPayState(OrderGlobal.ORDER_PAY_STATE_REFUNDING);
		}

		// 更新订单主表
		this.update(orderService);
		// 恢复服务库存
		if (state == 0) {

		}
		// 插入订单追踪
		OrderTrack orderTrack = new OrderTrack();
		orderTrackService.save(orderTrack);
		return true;
	}
}