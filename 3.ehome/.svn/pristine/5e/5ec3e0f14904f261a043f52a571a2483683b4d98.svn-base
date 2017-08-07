package com.its.modules.app.web;

import java.util.ArrayList;
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
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.AdverManage;
import com.its.modules.app.entity.ModuleManage;
import com.its.modules.app.service.AdverManageService;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.ModuleManageService;

/**
 * 广告管理Controller
 * 
 * @author like
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = { "${appPath}" })
public class AdverManageController extends BaseController {

	@Autowired
	private AdverManageService adverManageService;
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private ModuleManageService moduleManageService;

	/**
	 * 获取开屏广告
	 * 
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/common/getOpenAd")
	public Map<String, Object> getOpenAd(String buildingID) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}

		Map<String, Object> data = new HashMap<String, Object>();

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 首页广告位：根据普及综合管理平台发布的“首页中部”广告对应显示（只显示上线广告）
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/home/getAdSlot")
	public Map<String, Object> getAdSlot(String userID, String buildingID, HttpServletRequest request) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<AdverManage> adverManages = adverManageService.getAdverManageList(Global.getConfig("adver_position_index_middle"), buildingID, 5);
		if (adverManages == null || adverManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", toJsonListMap(adverManages, request));
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 社区首页顶部广告位
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/community/getTopAdSlot")
	public Map<String, Object> getTopAdSlot(String userID, String buildingID, HttpServletRequest request) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<AdverManage> adverManages = adverManageService.getAdverManageList(Global.getConfig("adver_position_community_index_top"), buildingID, 5);
		if (adverManages == null || adverManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", toJsonListMap(adverManages, request));
		toJson.put("message", "信息已获取");
		return toJson;
	}
	
	/**
	 *生活首页顶部广告位
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/live/getTopAdSlot")
	public Map<String, Object> getLiveTopAdSlot(String userID, String buildingID, HttpServletRequest request) {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		List<AdverManage> adverManages = adverManageService.getAdverManageList(Global.getConfig("adver_position_live_index_top"), buildingID, 5);
		if (adverManages == null || adverManages.size() == 0) {
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "暂无数据");
			return toJson;
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", toJsonListMap(adverManages, request));
		toJson.put("message", "信息已获取");
		return toJson;
	}

	private List<Map<String, Object>> toJsonListMap(List<AdverManage> adverManages, HttpServletRequest request) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (AdverManage adverManage : adverManages) {
			Map<String, Object> data = new HashMap<String, Object>();
			if (AppGlobal.ADVER_TYPE_IMAGE_TEXT.equals(adverManage.getAdverType())) {// 0->图文广告
				data.put("adType", adverManage.getAdverType());
				data.put("adID", adverManage.getId());
				data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
			} else if (AppGlobal.ADVER_TYPE_OUT_LINK.equals(adverManage.getAdverType())) {// 1->外链广告
				data.put("adType", adverManage.getAdverType());
				data.put("adID", adverManage.getId());
				data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
			} else if (AppGlobal.ADVER_TYPE_MODULE_LINK.equals(adverManage.getAdverType())) {// 2->模块链接
				data.put("adType", adverManage.getAdverType());
				data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
				ModuleManage module = moduleManageService.get(adverManage.getModuleId());
				data.put("moduleID", adverManage.getModuleId());
				data.put("moduleName", (module != null && StringUtils.isNotBlank(module.getModuleName())) ? module.getModuleName() : "");
			} else if (AppGlobal.ADVER_TYPE_BUSINESS_LINK.equals(adverManage.getAdverType())) {// 3->商家链接
				data.put("adType", adverManage.getAdverType());
				data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
				data.put("businessCategory", adverManage.getCategoryId());
				data.put("businessID", adverManage.getBusinessinfoId());
				data.put("productMode", businessInfoService.getProdTypeByCategoryDictId(adverManage.getCategoryId()));
			} else if (AppGlobal.ADVER_TYPE_GOODS_LINK.equals(adverManage.getAdverType())) {// 4->产品链接
				data.put("adType", adverManage.getAdverType());
				data.put("adImage", ValidateUtil.getImageUrl(adverManage.getAdverPic(), ValidateUtil.ZERO, request));
				data.put("businessCategory", adverManage.getCategoryId());
				data.put("businessID", adverManage.getBusinessinfoId());
				data.put("productMode", businessInfoService.getProdTypeByCategoryDictId(adverManage.getCategoryId()));
				data.put("productID", adverManage.getGoodsId());
			}
			datas.add(data);
		}
		return datas;
	}
}