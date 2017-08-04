/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.FamilyInfo;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.FamilyInfoService;
import com.its.modules.app.service.RoomCertifyService;

import net.sf.json.JSONObject;

/**
 * 家属成员信息Controller
 * 
 * @author like
 * @version 2017-07-21
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class FamilyInfoController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private RoomCertifyService roomCertifyService;

	/**
	 * 添加成员
	 * 
	 * @param userID
	 *            用户ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param roomID
	 *            房间ID(不可空)
	 * @param memberName
	 *            成员名称(不可空)
	 * @param memberPhone
	 *            成员电话(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addRoomMember", method = RequestMethod.POST)
	public String addRoomMember(String userID, String buildingID, String roomID, String memberName, String memberPhone) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(roomID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		if (StringUtils.isBlank(memberName)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"请填写真实姓名\"}";
		}
		if (StringUtils.isBlank(memberPhone)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"请填写注册手机号\"}";
		}
		if (!StringUtils.checkPhoneNum(memberPhone)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"请填写11位有效手机号\"}";
		}
		Account account = accountService.get(userID);
		if (account == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"用戶不存在\"}";
		}
		RoomCertify room = roomCertifyService.get(roomID);
		if (room == null || !userID.equals(room.getAccountId()) || !buildingID.equals(room.getVillageInfoId())) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		FamilyInfo info = new FamilyInfo();
		info.setVillageInfoId(buildingID);
		info.setRoomCertifyId(roomID);
		info.setName(memberName);
		info.setPhoneNum(memberPhone);
		info.setCertifyState("0");
		familyInfoService.save(info);
		// 调用新增家庭成员接口
		familyInfoService.submitRemoteFamily(memberPhone, memberName, room.getCustomerId(), roomID);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "添加成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 删除程成员
	 * 
	 * @param userID
	 * @param buildingID
	 * @param roomID
	 * @param memberID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRoomMember")
	public String deleteRoomMember(String userID, String buildingID, String roomID, String memberID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(roomID) || StringUtils.isBlank(memberID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		// 判断业主是否已认证
		RoomCertify room = roomCertifyService.get(roomID);
		if (room == null || !userID.equals(room.getAccountId()) || !buildingID.equals(room.getVillageInfoId()) || !"0".equals(room.getCustomerType())) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"仅房间业主有操作权限\"}";
		}
		FamilyInfo info = familyInfoService.get(memberID);
		if (info != null) {
			familyInfoService.delete(info);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "删除成功");
		return JSONObject.fromObject(json).toString();
	}

}