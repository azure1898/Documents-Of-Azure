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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.DateUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.entity.VisitorInvite;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.RoomCertifyService;
import com.its.modules.app.service.VillageInfoService;
import com.its.modules.app.service.VisitorInviteService;

/**
 * 访客邀请Controller
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class VisitorInviteController extends BaseController {

	@Autowired
	private VisitorInviteService visitorInviteService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private VillageInfoService villageInfoService;

	@Autowired
	private RoomCertifyService roomCertifyService;

	/**
	 * 访客列表
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getVisitorList")
	@ResponseBody
	public Map<String, Object> getVisitorList(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		// 正常的访客列表
		List<VisitorInvite> normalVisitorInvites = visitorInviteService.getNormalVisitorInviteList(userID, buildingID);
		// 失效的访客列表
		List<VisitorInvite> invalidVisitorInvites = new ArrayList<VisitorInvite>();
		// 展示数量
		int showCount = 20;
		if (normalVisitorInvites.size() < showCount) {
			invalidVisitorInvites = visitorInviteService.getInvalidVisitorInviteList(userID, buildingID, showCount - normalVisitorInvites.size());
		}
		int totalCount = normalVisitorInvites.size() + invalidVisitorInvites.size();
		if (totalCount == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无邀请访客记录！");
			return toJson;
		}

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		// 列表堆积
		normalVisitorInvites.addAll(invalidVisitorInvites);
		for (VisitorInvite visitorInvite : normalVisitorInvites) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("visitID", visitorInvite.getId());
			data.put("visitorName", visitorInvite.getVisitorName());
			data.put("genderSaid", CommonGlobal.MAN.equals(visitorInvite.getSex()) ? CommonGlobal.VISITOR_INVITE_SEX_MAN : CommonGlobal.VISITOR_INVITE_SEX_WOMAN);
			data.put("validStatus", visitorInvite.getInviteState());
			data.put("visitingTime", DateFormatUtils.format(visitorInvite.getVisitDate(), "MM-dd"));

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 添加访客
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param roomID
	 *            房间ID（不可空）
	 * @param visitorName
	 *            访客姓名（不可空）
	 * @param gender
	 *            性别（不可空）
	 * @param visitingTime
	 *            到访时间（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "addVisitor", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addVisitor(String userID, String buildingID, String roomID, String visitorName, String gender, String visitingTime) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, roomID, visitorName, gender, visitingTime)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}

		VisitorInvite visitorInvite = new VisitorInvite();
		visitorInvite.setAccountId(userID);
		visitorInvite.setVillageInfoId(buildingID);
		visitorInvite.setApartmentCertifyId(roomID);
		visitorInvite.setVisitorName(visitorName);
		visitorInvite.setSex(gender);
		visitorInvite.setVisitDate(DateUtils.parseDate(visitingTime));
		visitorInvite.setQrCode(null);
		visitorInvite.setInviteState(CommonGlobal.VISITOR_INVITE_STATE_NOMAL);
		visitorInvite.setInvalidDate(null);
		visitorInviteService.save(visitorInvite);

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("visitID", visitorInvite.getId());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 访客详情
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param visitID
	 *            来访信息ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getVisitorDetail")
	@ResponseBody
	public Map<String, Object> getVisitorDetail(String userID, String buildingID, String visitID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, visitID)) {
			return toJson;
		}
		VisitorInvite visitorInvite = visitorInviteService.judgeVisitorInvite(userID, buildingID, visitID);
		if (visitorInvite == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "邀请不存在");
			return toJson;
		}
		VillageInfo village = villageInfoService.get(buildingID);
		if (village == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘不存在");
			return toJson;
		}
		RoomCertify roomCertify = roomCertifyService.judgeRoomCertify(userID, buildingID, visitorInvite.getApartmentCertifyId());
		if (roomCertify == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "房间认证不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("visitID", visitorInvite.getId());
		data.put("address", village.getVillageName() + roomCertify.getBuildingName() + roomCertify.getRoomName());
		data.put("visitorName", visitorInvite.getVisitorName());
		data.put("genderSaid", CommonGlobal.MAN.equals(visitorInvite.getSex()) ? CommonGlobal.VISITOR_INVITE_SEX_MAN : CommonGlobal.VISITOR_INVITE_SEX_WOMAN);
		data.put("visitingTime", DateFormatUtils.format(visitorInvite.getVisitDate(), "MM-dd"));
		data.put("qrCode", visitorInvite.getQrCode());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 一键作废
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param visitID
	 *            来访信息ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "invalidVisit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> invalidVisit(String userID, String buildingID, String visitID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, visitID)) {
			return toJson;
		}
		VisitorInvite visitorInvite = visitorInviteService.judgeVisitorInvite(userID, buildingID, visitID);
		if (visitorInvite == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "邀请不存在");
			return toJson;
		}

		visitorInvite.setInviteState(CommonGlobal.VISITOR_INVITE_STATE_ABOLISHED);
		visitorInvite.setInvalidDate(new Date());
		visitorInviteService.update(visitorInvite);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "邀请已作废");
		return toJson;
	}

	/**
	 * 选择房间
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getUserRoom")
	@ResponseBody
	public Map<String, Object> getUserRoom(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		VillageInfo village = villageInfoService.get(buildingID);
		if (village == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘不存在");
			return toJson;
		}

		// 房间认证
		account.setVillageInfoId(buildingID);
		accountService.save(account);
		accountService.certifyCustomer(account);

		/* Data数据开始 */
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<RoomCertify> roomCertifies = roomCertifyService.getAccountRoomCertify(userID, buildingID);
		if (roomCertifies == null || roomCertifies.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "您没有在该小区认证房间");
			return toJson;
		}
		for (RoomCertify roomCertify : roomCertifies) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("roomID", roomCertify.getId());
			String roomName = village.getVillageName() + roomCertify.getBuildingName() + roomCertify.getRoomName();
			data.put("roomName", roomName);

			datas.add(data);
		}
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}