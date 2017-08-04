package com.its.modules.app.service;

import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.bean.OrderTrackViewBean;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.OrderTrackDao;
import com.its.modules.app.entity.OrderTrack;

/**
 * 订单跟踪Service
 * 
 * @author sushipeng
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class OrderTrackService extends CrudService<OrderTrackDao, OrderTrack> {

	public OrderTrack get(String id) {
		return super.get(id);
	}

	public List<OrderTrack> findList(OrderTrack orderTrack) {
		return super.findList(orderTrack);
	}

	public Page<OrderTrack> findPage(Page<OrderTrack> page, OrderTrack orderTrack) {
		return super.findPage(page, orderTrack);
	}

	@Transactional(readOnly = false)
	public void save(OrderTrack orderTrack) {
		super.save(orderTrack);
	}

	@Transactional(readOnly = false)
	public void delete(OrderTrack orderTrack) {
		super.delete(orderTrack);
	}

	/**
	 * 获取某用户某楼盘某模块下的订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID：0全部订单 -1精品团购 else产品模式下的模块
	 * @return List<MyOrderViewBean>
	 */
	public List<MyOrderViewBean> getMyOrderViewList(String villageInfoId, String accountId, String moduleManageId) {
		return dao.getMyOrderViewList(villageInfoId, accountId, moduleManageId);
	}
	
	/**
	 * 获取某用户某楼盘下的最新两条订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<MyOrderViewBean>
	 */
	public List<MyOrderViewBean> getRecentMyOrderView(String villageInfoId, String accountId) {
		return dao.getRecentMyOrderView(villageInfoId, accountId);
	}

	/**
	 * 获取订单的展示时间
	 * 
	 * @param myOrderViewBean
	 *            我的订单信息
	 * @return 订单的展示时间
	 */
	public String getOrderTime(MyOrderViewBean myOrderViewBean) {
		String orderTime = myOrderViewBean.getOrderTime();
		if (StringUtils.isBlank(orderTime)) {
			return "";
		}
		int orderType = ValidateUtil.validateInteger(myOrderViewBean.getOrderType());
		String[] orderTimes = orderTime.replaceAll("，", ",").split(",");
		if (orderType == 1 || orderType == 2) {
			if (orderTimes.length == 3) {
				if ("1".equals(orderTimes[0])) {
					return orderTimes[orderTimes.length - 1];
				} else {
					return orderTimes[orderTimes.length - 2] + "~" + orderTimes[orderTimes.length - 1];
				}

			} else {
				return "";
			}
		} else if (orderType == 3) {
			if (orderTimes.length == 2) {
				return orderTimes[0] + "至" + orderTimes[1];
			} else {
				return "";
			}
		} else if (orderType == 4) {
			return DateFormatUtils.format(DateUtils.parseDate(orderTime), "yyyy-MM-dd");
		} else {
			return orderTime;
		}
	}

	/**
	 * 获取某订单最新的状态信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @return OrderTrack
	 */
	public OrderTrack getRecentOrderStatus(String orderId) {
		return dao.getRecentOrderStatus(orderId);
	}

	/**
	 * 获取某订单的订单追踪信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @return OrderTrackViewBean
	 */
	public OrderTrackViewBean getOrderTrackViewBean(String orderId) {
		return dao.getOrderTrackViewBean(orderId);
	}

	/**
	 * 提交订单时插入订单跟踪状态
	 * 
	 * @param orderType
	 *            订单类型：0-商品；1-服务；2-课程；3场地预约；4-团购
	 * @param orderId
	 *            订单ID
	 * @param orderNo
	 *            订单号
	 */
	@Transactional(readOnly = false)
	public void createTrackSubmit(String orderType, String orderId, String orderNo) {
		OrderTrack orderTrack = new OrderTrack();
		orderTrack.setOrderId(orderId);
		orderTrack.setOrderNo(orderNo);
		orderTrack.setOrderType(orderType);
		orderTrack.setStateMsg(OrderGlobal.BACK_UNPAY);
		orderTrack.setHandleMsg(OrderGlobal.BACK_UNPAY_DESC);
		orderTrack.setStateMsgPhone(OrderGlobal.FRONT_UNPAY);
		orderTrack.setHandleMsgPhone(OrderGlobal.FRONT_UNPAY_DESC);
		orderTrack.setCreateName(OrderGlobal.CREATE_NAME_ACCOUNT);
		this.save(orderTrack);
	}
}