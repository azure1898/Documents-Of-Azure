/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.entity.MemberNews;
import com.its.modules.app.service.MemberNewsService;

import net.sf.json.JSONObject;

/**
 * 提醒消息Controller
 * 
 * @author like
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class MemberNewsController extends BaseController {

	@Autowired
	private MemberNewsService memberNewsService;

	@ResponseBody
	@RequestMapping(value = "/getMessageList")
	public String getMessageList(String userID, String buildingID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<MemberNews> list = memberNewsService.selectLatestNewsEveryType(userID, buildingID);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		MemberNews n0 = null;
		MemberNews n1 = null;
		MemberNews n2 = null;
		for (MemberNews news : list) {
			if (AppGlobal.NEWS_TYPE_CARING.equals(news.getNewsType())) {
				n0 = news;
				continue;
			} else if (AppGlobal.NEWS_TYPE_ORDER.equals(news.getNewsType())) {
				n1 = news;
				continue;
			} else if (AppGlobal.NEWS_TYPE_SYSTEM.equals(news.getNewsType())) {
				n2 = news;
				continue;
			}
		}
		if (n0 != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", n0.getNewsType());// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", n0.getContent());
			map.put("messageTime", DateFormatUtils.format(n0.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", AppGlobal.NEWS_TYPE_CARING);// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", "暂无消息");
			map.put("messageTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		}
		if (n1 != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", n1.getNewsType());// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", n1.getContent());
			map.put("messageTime", DateFormatUtils.format(n1.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", AppGlobal.NEWS_TYPE_ORDER);// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", "暂无消息");
			map.put("messageTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		}
		if (n2 != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", n2.getNewsType());// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", n2.getContent());
			map.put("messageTime", DateFormatUtils.format(n2.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageType", AppGlobal.NEWS_TYPE_SYSTEM);// 0业主关怀；1订单提醒；2系统消息
			map.put("messageContent", "暂无消息");
			map.put("messageTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			data.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderRemind")
	public String getOrderRemind(String userID, String buildingID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<MemberNews> list = memberNewsService.selectNewsByType(AppGlobal.NEWS_TYPE_ORDER, userID, buildingID);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (MemberNews news : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("messageImage", "");
			map.put("messageContent", news.getContent());
			map.put("messageTime", DateFormatUtils.format(news.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			map.put("orderID", news.getOrderId());
			data.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}