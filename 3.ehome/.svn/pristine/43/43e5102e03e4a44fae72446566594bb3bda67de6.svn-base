/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.MyCollectBean;
import com.its.modules.app.entity.MyCollect;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.MyCollectService;

import net.sf.json.JSONObject;

/**
 * 我的收藏Controller
 * 
 * @author like
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = { "${appPath}/live", "${appPath}/my" })
public class MyCollectController extends BaseController {
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private MyCollectService myCollectService;

	/**
	 * 收藏商家
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addBusinessCollection")
	public String addBusinessCollection(String userID, String buildingID, String businessID) {
		try {
			if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数错误\"}";
			}
			MyCollect my = myCollectService.hasCollect(userID, buildingID, businessID);
			if (my == null) {
				my = new MyCollect();
				my.setAccountId(userID);
				my.setVillageInfoId(buildingID);
				my.setBusinessInfoId(businessID);
				my.setCollectDate(new Date());
				myCollectService.save(my);
			}
			return "{\"code\":" + Global.CODE_SUCCESS + ",\"message\":\"收藏成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"" + e.getMessage() + "\"}";
			}
			return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"系统错误\"}";
		}
	}

	/**
	 * 取消收藏
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cancelBusinessCollection")
	public String cancelBusinessCollection(String userID, String buildingID, String businessID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数错误\"}";
		}
		myCollectService.cancelCollection(userID, buildingID, businessID);
		return "{\"code\":" + Global.CODE_SUCCESS + ",\"message\":\"取消收藏\"}";
	}

	@ResponseBody
	@RequestMapping(value = "getCollectionList")
	public String getCollectionList(String userID, String buildingID, HttpServletRequest request) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数错误\"}";
		}
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<MyCollectBean> list = myCollectService.findMyCollectOfAccount(userID, buildingID);
		for (MyCollectBean my : list) {
			Map<String, Object> jn = new HashMap<String, Object>();
			jn.put("businessID", my.getBusinessInfoId());
			jn.put("businessName", my.getBusinessName());
			jn.put("businessImage", businessInfoService.formatBusinessPic(my.getBusinessPic(), request));
			jn.put("businessLabels", businessInfoService.getBusinessLabelList(my.getBusinessLabel()));
			jn.put("isFeeActivity", "1".equals(my.getFreeDistributeFlag()) ? true : false);
			jn.put("deliveryFee", my.getDistributeCharge());
			data.add(jn);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}