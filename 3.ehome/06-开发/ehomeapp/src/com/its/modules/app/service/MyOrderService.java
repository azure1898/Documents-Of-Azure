package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.modules.app.bean.MyOrderBean;
import com.its.modules.app.bean.OrderFieldBean;
import com.its.modules.app.bean.OrderGoodsBean;
import com.its.modules.app.bean.OrderGroupPurcBean;
import com.its.modules.app.bean.OrderLessonBean;
import com.its.modules.app.bean.OrderServiceBean;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;

@Service
@Transactional(readOnly = true)
public class MyOrderService {

	@Autowired
	private OrderGoodsService orderGoodsService;

	@Autowired
	private OrderServiceService orderServiceService;

	@Autowired
	private OrderLessonService orderLessonService;

	@Autowired
	private OrderFieldService orderFieldService;

	@Autowired
	private OrderGroupPurcService orderGroupPurcService;

	/**
	 * 获取我的订单信息（包括:全部订单OR关联在产品模式下的模块名称OR精品团购）
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param moudleID
	 *            模块ID
	 * @param request
	 *            request
	 * @return List<MyOrderBean>
	 */
	public List<MyOrderBean> getMyOrderBean(String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<MyOrderBean> myOrderBeans = new ArrayList<MyOrderBean>();
		// 全部订单、关联在产品模式下的模块名称
		if (!CommonGlobal.GROUP_PURCHASE_MODULEID.equals(moudleID)) {
			this.orderGoodsToMyOrder(myOrderBeans, userID, buildingID, moudleID, request);
			this.orderServiceToMyOrder(myOrderBeans, userID, buildingID, moudleID, request);
			this.orderLessonToMyOrder(myOrderBeans, userID, buildingID, moudleID, request);
			this.orderFieldToMyOrder(myOrderBeans, userID, buildingID, moudleID, request);
		}
		// 精品团购
		this.orderGroupPurcToMyOrder(myOrderBeans, userID, buildingID, moudleID, request);
		Collections.sort(myOrderBeans);
		return myOrderBeans;
	}

	// 商品类订单
	public List<MyOrderBean> orderGoodsToMyOrder(List<MyOrderBean> myOrderBeans, String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<OrderGoodsBean> orderGoodsBeans = orderGoodsService.getOrderGoodsList(buildingID, userID, moudleID);
		for (OrderGoodsBean orderGoodsBean : orderGoodsBeans) {
			MyOrderBean myOrderBean = new MyOrderBean();
			myOrderBean.setOrderId(orderGoodsBean.getId());
			myOrderBean.setName(orderGoodsBean.getBusinessInfo().getBusinessName());
			myOrderBean.setBusinessImage(ValidateUtil.getImageUrl(orderGoodsBean.getBusinessInfo().getBusinessPic(), ValidateUtil.ZERO, request));
			myOrderBean.setOrderType(OrderGlobal.ORDER_GOODS);
			myOrderBean.setTimeLabel(OrderGlobal.ORDER_GOODS_TIME_LABEL);
			if ("1".equals(orderGoodsBean.getIsStart())) {
				myOrderBean.setTime(orderGoodsBean.getEndTime());
			} else {
				myOrderBean.setTime(orderGoodsBean.getStartTime() + "~" + orderGoodsBean.getEndTime());
			}
			myOrderBean.setOrderMoney(ValidateUtil.validateDouble(orderGoodsBean.getPayMoney()));
			myOrderBean.setOrderStatus(orderGoodsBean.getOrderState());
			myOrderBean.setCreateDate(orderGoodsBean.getCreateDate());
			myOrderBeans.add(myOrderBean);
		}
		return myOrderBeans;
	}

	// 服务类订单
	public List<MyOrderBean> orderServiceToMyOrder(List<MyOrderBean> myOrderBeans, String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<OrderServiceBean> orderServiceBeans = orderServiceService.getOrderServiceList(buildingID, userID, moudleID);
		for (OrderServiceBean orderServiceBean : orderServiceBeans) {
			MyOrderBean myOrderBean = new MyOrderBean();
			myOrderBean.setOrderId(orderServiceBean.getId());
			myOrderBean.setName(orderServiceBean.getBusinessInfo().getBusinessName());
			myOrderBean.setBusinessImage(ValidateUtil.getImageUrl(orderServiceBean.getBusinessInfo().getBusinessPic(), ValidateUtil.ZERO, request));
			myOrderBean.setOrderType(OrderGlobal.ORDER_SERVICE);
			myOrderBean.setTimeLabel(OrderGlobal.ORDER_SERVICE_TIME_LABEL);
			if ("1".equals(orderServiceBean.getIsStart())) {
				myOrderBean.setTime(orderServiceBean.getEndTime());
			} else {
				myOrderBean.setTime(orderServiceBean.getStartTime() + "~" + orderServiceBean.getEndTime());
			}
			myOrderBean.setOrderMoney(ValidateUtil.validateDouble(orderServiceBean.getPayMoney()));
			myOrderBean.setOrderStatus(orderServiceBean.getOrderState());
			myOrderBean.setCreateDate(orderServiceBean.getCreateDate());
			myOrderBeans.add(myOrderBean);
		}
		return myOrderBeans;
	}

	// 课程类订单
	public List<MyOrderBean> orderLessonToMyOrder(List<MyOrderBean> myOrderBeans, String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<OrderLessonBean> orderLessonBeans = orderLessonService.getOrderLessonList(buildingID, userID, moudleID);
		for (OrderLessonBean orderLessonBean : orderLessonBeans) {
			MyOrderBean myOrderBean = new MyOrderBean();
			myOrderBean.setOrderId(orderLessonBean.getId());
			myOrderBean.setName(orderLessonBean.getBusinessInfo().getBusinessName());
			myOrderBean.setBusinessImage(ValidateUtil.getImageUrl(orderLessonBean.getBusinessInfo().getBusinessPic(), ValidateUtil.ZERO, request));
			myOrderBean.setOrderType(OrderGlobal.ORDER_LESSON);
			myOrderBean.setTimeLabel(OrderGlobal.ORDER_LESSON_TIME_LABEL);
			Date startTime = orderLessonBean.getLessonInfo().getStartTime();
			Date endTime = orderLessonBean.getLessonInfo().getEndTime();
			myOrderBean.setTime(DateFormatUtils.format(startTime, "yyyy-MM-dd") + "至" + DateFormatUtils.format(endTime, "MM-dd") + " 每天" + DateFormatUtils.format(startTime, "HH:mm") + "-" + DateFormatUtils.format(endTime, "HH:mm"));
			myOrderBean.setOrderMoney(ValidateUtil.validateDouble(orderLessonBean.getPayMoney()));
			myOrderBean.setOrderStatus(orderLessonBean.getOrderState());
			myOrderBean.setCreateDate(orderLessonBean.getCreateDate());
			myOrderBeans.add(myOrderBean);
		}
		return myOrderBeans;
	}

	// 场地类订单
	public List<MyOrderBean> orderFieldToMyOrder(List<MyOrderBean> myOrderBeans, String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<OrderFieldBean> orderFieldBeans = orderFieldService.getOrderFieldList(buildingID, userID, moudleID);
		for (OrderFieldBean orderFieldBean : orderFieldBeans) {
			MyOrderBean myOrderBean = new MyOrderBean();
			myOrderBean.setOrderId(orderFieldBean.getId());
			myOrderBean.setName(orderFieldBean.getBusinessInfo().getBusinessName());
			myOrderBean.setBusinessImage(ValidateUtil.getImageUrl(orderFieldBean.getBusinessInfo().getBusinessPic(), ValidateUtil.ZERO, request));
			myOrderBean.setOrderType(OrderGlobal.ORDER_FIELD);
			myOrderBean.setTimeLabel(OrderGlobal.ORDER_FIELD_TIME_LABEL);
			String appointTime = DateFormatUtils.format(orderFieldBean.getOrderFieldLists().get(0).getAppointmentTime(), "yyyy-MM-dd");
			myOrderBean.setTime(appointTime + " " + AppUtils.formatDateWeek(appointTime));
			myOrderBean.setOrderMoney(ValidateUtil.validateDouble(orderFieldBean.getPayMoney()));
			myOrderBean.setOrderStatus(orderFieldBean.getOrderState());
			myOrderBean.setCreateDate(orderFieldBean.getCreateDate());
			myOrderBeans.add(myOrderBean);
		}
		return myOrderBeans;
	}

	// 团购类订单
	public List<MyOrderBean> orderGroupPurcToMyOrder(List<MyOrderBean> myOrderBeans, String userID, String buildingID, String moudleID, HttpServletRequest request) {
		List<OrderGroupPurcBean> orderGroupPurcBeans = orderGroupPurcService.getOrderGroupPurcList(buildingID, userID, moudleID);
		for (OrderGroupPurcBean orderGroupPurcBean : orderGroupPurcBeans) {
			MyOrderBean myOrderBean = new MyOrderBean();
			myOrderBean.setOrderId(orderGroupPurcBean.getId());
			myOrderBean.setName(orderGroupPurcBean.getBusinessInfo().getBusinessName());
			myOrderBean.setBusinessImage(ValidateUtil.getImageUrl(orderGroupPurcBean.getBusinessInfo().getBusinessPic(), ValidateUtil.ZERO, request));
			myOrderBean.setOrderType(OrderGlobal.ORDER_GROUP_PURCHASE);
			myOrderBean.setTimeLabel(OrderGlobal.ORDER_GROUP_PURCHASE_TIME_LABEL);
			myOrderBean.setTime(DateFormatUtils.format(orderGroupPurcBean.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			myOrderBean.setOrderMoney(ValidateUtil.validateDouble(orderGroupPurcBean.getPayMoney()));
			myOrderBean.setOrderStatus(orderGroupPurcBean.getOrderState());
			myOrderBean.setCreateDate(orderGroupPurcBean.getCreateDate());
			myOrderBeans.add(myOrderBean);
		}
		return myOrderBeans;
	}
}