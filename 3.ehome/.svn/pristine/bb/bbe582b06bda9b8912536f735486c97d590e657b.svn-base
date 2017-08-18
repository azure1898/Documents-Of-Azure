package com.its.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.common.PropertiesUtil;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.NoticeManage;
import com.its.modules.app.service.NoticeManageService;

/**
 * 公告管理Controller
 * 
 * @author like
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${appPath}/community")
public class NoticeManageController extends BaseController {

	@Autowired
	private NoticeManageService noticeManageService;

	/**
	 * 最新公告展示
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getHomeBulletin")
	@ResponseBody
	public Map<String, Object> getHomeBulletin(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		NoticeManage notice = noticeManageService.getLatestNotice(buildingID);
		Map<String, Object> data = new HashMap<String, Object>();
		if (notice != null) {
			data.put("bulletinID", notice.getId());
			data.put("bulletinTitle", notice.getNoticeTitle());
			data.put("bulletinContent", notice.getNoticeContent());
			data.put("publishDate", DateFormatUtils.format(notice.getCreateDate(), "MM-dd HH:mm"));
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 公告列表
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getBulletinList")
	@ResponseBody
	public Map<String, Object> getBulletinList(String userID, String buildingID, @RequestParam(value = "pageIndex", required = true, defaultValue = "0") int pageIndex) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<NoticeManage> list = noticeManageService.getNoticeList(buildingID, pageIndex, PropertiesUtil.getInt("numPerPage"));
		List<Map<String, Object>> data = new ArrayList<>();
		for (NoticeManage notice : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("bulletinID", notice.getId());
			map.put("bulletinTitle", notice.getNoticeTitle());
			map.put("publishDate", DateFormatUtils.format(notice.getCreateDate(), "yyyy-MM-dd"));
			data.add(map);
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 公告详细
	 * 
	 * @param userID
	 *            用户ID
	 * @param bulletinID
	 *            公告ID
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getBulletinDetail")
	@ResponseBody
	public Map<String, Object> getBulletinDetail(String userID, String bulletinID) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, bulletinID)) {
			return toJson;
		}
		NoticeManage notice = noticeManageService.get(bulletinID);
		if (notice == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "公告不存在");
			return toJson;
		}
		Map<String, Object> data = new HashMap<>();
		data.put("bulletinTitle", notice.getNoticeTitle());
		data.put("bulletinContent", notice.getNoticeContent());
		data.put("publishDate", DateFormatUtils.format(notice.getCreateDate(), "yyyy-MM-dd"));
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}