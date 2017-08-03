/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.BraceletInfo;
import com.its.modules.app.service.BraceletInfoService;

import net.sf.json.JSONObject;

/**
 * 手环信息Controller
 * 
 * @author like
 * @version 2017-07-20
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class BraceletInfoController extends BaseController {

	@Autowired
	private BraceletInfoService braceletInfoService;

	/**
	 * 判断手环模块是否开放
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isOpenBand")
	public String isOpenBand(String userID, String buildingID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		Map<String, Object> data = new HashMap<String, Object>();

		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 判断有无添加手环
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isBindBand")
	public String isBindBand(String userID, String buildingID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		List<BraceletInfo> list = braceletInfoService.getAccountBracelets(userID, buildingID);
		Map<String, Object> data = new HashMap<String, Object>();
		if (list.size() > 0) {
			data.put("isBind", 1);
		} else {
			data.put("isBind", 0);
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取手环设备列表
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBandList")
	public String getBandList(String userID, String buildingID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		List<BraceletInfo> list = braceletInfoService.getAccountBracelets(userID, buildingID);
		List<Map<String, Object>> data = new ArrayList<>();
		for (BraceletInfo info : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("bandID", info.getId());
			map.put("bandName", info.getName());
			map.put("bandType", info.getModel());
			map.put("bandMac", info.getMac());
			data.add(map);
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 修改手环名称
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandID
	 *            手环ID(不可空)
	 * @param bandName
	 *            手环名称(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateBandName", method = RequestMethod.POST)
	public String updateBandName(String userID, String buildingID, String bandID, String bandName) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandID) || StringUtils.isBlank(bandName)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		braceletInfoService.updateName(bandID, bandName);
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 上传手环信息
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandName
	 *            手环名称(需经过Encode、不可空)
	 * @param bandType
	 *            手环类型(不可空)
	 * @param bandMac
	 *            手环mac地址(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadBand", method = RequestMethod.POST)
	public String uploadBand(String userID, String buildingID, String bandName, String bandType, String bandMac) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandName) || StringUtils.isBlank(bandType)
				|| StringUtils.isBlank(bandMac)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		// 判断此手环是否已绑定
		boolean exist = false;
		List<BraceletInfo> list = braceletInfoService.getAccountBracelets(userID, buildingID);
		for (BraceletInfo info : list) {
			if (bandMac.equals(info.getMac())) {
				exist = true;
				break;
			}
		}
		if (exist) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "此手环已绑定");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo info = new BraceletInfo();
		info.setAccountId(userID);
		info.setVillageinfoId(buildingID);
		info.setName(bandName);
		info.setTargetStep(0);
		info.setModel(bandType);
		info.setMac(bandMac);
		braceletInfoService.save(info);
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "绑定成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 删除手环设备
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param bandID
	 *            手环ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBand")
	public String deleteBand(String userID, String buildingID, String bandID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(bandID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		BraceletInfo info = braceletInfoService.get(bandID);
		if (info == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手环不存在");
			return JSONObject.fromObject(json).toString();
		}
		braceletInfoService.delete(info);
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 提交运动目标
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param target
	 *            目标数目(数字、不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitMotionTarget", method = RequestMethod.POST)
	public String submitMotionTarget(String userID, String buildingID, String target) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || !StringUtils.isNumeric(target)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		braceletInfoService.updateTarget(userID, buildingID, Integer.parseInt(target));
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

}