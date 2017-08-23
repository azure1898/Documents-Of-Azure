package com.its.modules.websocket.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.its.common.utils.SpringContextHolder;
import com.its.modules.order.dao.OrderGoodsDao;
import com.its.modules.order.dao.OrderServiceDao;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.websocket.WebSocket;
import com.its.modules.websocket.entity.Order;
import com.its.modules.websocket.entity.OrderInfo;

/**
 * @author lq
 *socket 消息推送
 */
@Controller
@RequestMapping(value = "/websocket")
public class WebSocketController {
	
	/**
	 * 商品订单Dao
	 */
	private static OrderGoodsDao orderGoodsDao = SpringContextHolder.getBean(OrderGoodsDao.class);
	
	/**
	 * 服务订单Dao
	 */
	private static OrderServiceDao orderServiceDao = SpringContextHolder.getBean(OrderServiceDao.class);
	/**
	 * 请求连接:http://localhost:8080/ehomebusiness/websocket/remind
	 * shangjia1商品订单提示测试:http://localhost:8080/ehomebusiness/websocket/remind?businessInfoId=2ee44bde50084d899a70c83a59918bad&type=10
	 * shangjia1库存不足提示测试:http://localhost:8080/ehomebusiness/websocket/remind?businessInfoId=2ee44bde50084d899a70c83a59918bad&type=0&name=红烧牛肉面&skuKey=包装类型&skuValue=10袋/包&stock=6&id=008c8146613e47f0967eb92f57f84e4a
	 * @param order
	 * @return
	 */
	@RequestMapping("remind")
	@ResponseBody
	public String remind(Order order){
		Map<String,String> map = new HashMap<String, String>();
		
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		if(order.getType()==10){//商品订单
			//查询最新五条未处理订单
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setOrderState("0");
			orderGoods.setBusinessInfoId(order.getBusinessInfoId());
			List<OrderGoods> OrderGoodsList = orderGoodsDao.findAllList(orderGoods);
			OrderGoodsList=OrderGoodsList.subList(0, 5);
			for(OrderGoods og:OrderGoodsList){
				OrderInfo oi = new OrderInfo();
				oi.setOrderNo(og.getOrderNo());
				oi.setSumMoney(og.getSumMoney()==null?"0":og.getSumMoney().toString());
				list.add(oi);
			}
		}
		if(order.getType()==11){//服务订单
			//查询最新五条未处理订单
			OrderService orderService = new OrderService();
			orderService.setOrderState("0");
			orderService.setBusinessInfoId(order.getBusinessInfoId());
			List<OrderService> OrderServiceList = orderServiceDao.findAllList(orderService);
			OrderServiceList=OrderServiceList.subList(0, 5);
			for(OrderService og:OrderServiceList){
				OrderInfo oi = new OrderInfo();
				oi.setOrderNo(og.getOrderNo());
				oi.setSumMoney(og.getSumMoney()==null?"0":og.getSumMoney().toString());
				list.add(oi);
			}
		}
		if(order.getType()>=10 & list.size()<=0){
			map.put("result", "error");
			map.put("msg", "暂无新订单.");
			return JSONUtils.toJSONString(map);
		}
		order.setOrder(list);
		try {
			Vector<String> Userlist = WebSocket.businessUsers.get(order.getBusinessInfoId());//用户列表
			ConcurrentHashMap<String, WebSocket> webScoket = WebSocket.connections;//WebSocket列表
			for(String userid:Userlist){
				webScoket.get(userid).sendMessage(order.toString());
			}
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "error");
			map.put("msg", e.getMessage());
		}
		return JSONUtils.toJSONString(map);
	}
}
