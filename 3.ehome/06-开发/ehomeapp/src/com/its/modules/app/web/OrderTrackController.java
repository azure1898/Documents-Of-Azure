package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.OrderTrackViewBean;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.OrderGroupPurcList;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.service.OrderGroupPurcListService;
import com.its.modules.app.service.OrderTrackService;

/**
 * 订单跟踪Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class OrderTrackController extends BaseController {

	@Autowired
	private OrderTrackService orderTrackService;

	@Autowired
	private OrderGroupPurcListService orderGroupPurcListService;

	/**
	 * 订单状态跟踪
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param orderType
	 *            订单类型1->商品购买订单 2->服务预约订单 3->课程购买订单 4->场地预约订单 5->精品团购订单
	 * @param orderID
	 *            订单ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getOrderStatus")
	@ResponseBody
	public Map<String, Object> getOrderStatus(String userID, int orderType, String orderID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, orderID)) {
			return toJson;
		}
		OrderTrackViewBean orderTrackViewBean = orderTrackService.getOrderTrackViewBean(orderID);
		if (orderTrackViewBean == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "订单状态不明");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderCode", orderTrackViewBean.getMainOrderNo());
		data.put("businessPhone", orderTrackViewBean.getPhoneNum());
		List<Map<String, Object>> orderStatus = new ArrayList<Map<String, Object>>();
		List<OrderTrack> orderTracks = orderTrackViewBean.getOrderTracks();
		if (orderTracks != null && orderTracks.size() != 0) {
			for (OrderTrack orderTrack : orderTracks) {
				Map<String, Object> status = new HashMap<String, Object>();
				status.put("statusName", orderTrack.getStateMsgPhone());
				status.put("statusDesc", orderTrack.getHandleMsgPhone());
				status.put("statusTime", DateFormatUtils.format(orderTrack.getCreateDate(), "MM-dd HH:mm"));

				orderStatus.add(status);
			}
		}
		data.put("orderStatus", orderStatus);
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 获取团购劵编码
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param orderID
	 *            订单ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getGroupVoucherList")
	@ResponseBody
	public Map<String, Object> getGroupVoucherList(String userID, String orderID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, orderID)) {
			return toJson;
		}
		List<OrderGroupPurcList> orderGroupPurcLists = orderGroupPurcListService.getOrderGroupPurcList(orderID, OrderGlobal.GROUP_PURHCASE_STATE_UNCONSUME);
		if (orderGroupPurcLists == null || orderGroupPurcLists.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (OrderGroupPurcList orderGroupPurcList : orderGroupPurcLists) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("code", orderGroupPurcList.getGroupPurcNumber());
			data.put("price", ValidateUtil.validateDouble(orderGroupPurcList.getGroupPurcPrice()));

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}