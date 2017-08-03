package com.its.modules.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.common.web.BaseController;

import com.its.modules.sys.entity.User;
import com.its.modules.sys.service.OfficeService;
import com.its.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "/interfaces/appImp")
public class APPImp extends BaseController {

	@Autowired
	private OfficeService officeService;

	@Autowired
	private SystemService systemService;

	/**
	 * 审核
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"review"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String review(@RequestParam String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		User user = systemService.getUser(userId);
		
		List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();	
		
	    Map<String, Object> hm = new HashMap<String, Object>();
				
		hm.put("test1", "APP");
		hm.put("test2", "测试");
		hmList.add(hm);
		hm.put("test1", "IOS");
		hm.put("test2", "测试");
		hmList.add(hm);
		hm.put("test1", "user");
		hm.put("test2", "caojing");
		hmList.add(hm);
		
		map.put("status", "1");
		map.put("msg", "查询成功");
		map.put("testList", hmList);

		try {
			return o.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			map.put("status", "-1");
			map.put("msg", "查询异常");
			try {
				return o.writeValueAsString(map);
			} catch (JsonProcessingException e1) {
			}
		}
		return "";
	}

}
