package com.its.modules.websocket.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.its.modules.websocket.WebSocket;
import com.its.modules.websocket.entity.Order;

/**
 * @author lq
 *socket 消息推送
 */
@Controller
@RequestMapping(value = "/websocket")
public class WebSocketController {
	
	/**
	 * 请求连接:http://localhost:8080/ehomebusiness/websocket/remind
	 * shangjia1商品订单提示测试:http://localhost:8080/ehomebusiness/websocket/remind?businessInfoId=2ee44bde50084d899a70c83a59918bad&type=10&orderNo=14584156156&sumMoney=998
	 * shangjia1库存不足提示测试:http://localhost:8080/ehomebusiness/websocket/remind?businessInfoId=2ee44bde50084d899a70c83a59918bad&type=0&name=红烧牛肉面&skuKey=包装类型&skuValue=10袋/包&stock=6&id=008c8146613e47f0967eb92f57f84e4a
	 * @param order
	 * @return
	 */
	@RequestMapping("remind")
	@ResponseBody
	public String remind(Order order){
		Map<String,String> map = new HashMap<String, String>();
		try {
			Vector<String> list = WebSocket.businessUsers.get(order.getBusinessInfoId());//用户列表
			ConcurrentHashMap<String, WebSocket> webScoket = WebSocket.connections;//WebSocket列表
			for(String userid:list){
				webScoket.get(userid).sendMessage(order.toString());
			}
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "error");
			map.put("请将异常信息转发给管理员:Exception", e.getMessage());
		}
		return JSONUtils.toJSONString(map);
	}
}
