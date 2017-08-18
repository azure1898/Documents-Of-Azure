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
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.BusinessSales;
import com.its.modules.app.entity.GoodsInfo;
import com.its.modules.app.entity.SortInfo;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GoodsInfoService;
import com.its.modules.app.service.GroupPurchaseService;
import com.its.modules.app.service.MyCollectService;

import net.sf.json.JSONObject;

/**
 * 商家信息管理Controller
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class BusinessInfoController extends BaseController {

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private MyCollectService myCollectService;

	/**
	 * 商品模块商家列表
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param moduleID
	 * @param sort
	 *            1->默认排序;2->商家销量排序;3->商家发布时间排序
	 */
	@ResponseBody
	@RequestMapping(value = "/getBusinessList")
	public String getBusinessList(String userID, String buildingID, String moduleID, String sort, HttpServletRequest request) {
		long start = new Date().getTime();
		if (StringUtils.isBlank(buildingID) || StringUtils.isBlank(moduleID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<BusinessInfo> list = businessInfoService.getBusinessList(Integer.parseInt(AppGlobal.MODEL_GOODS), buildingID, moduleID, (StringUtils.isNoneBlank(sort) && StringUtils.isNumeric(sort)) ? Integer.parseInt(sort) : 1);
		if (list.size() == 0) {
			return "{\"code\":" + Global.CODE_SUCCESS + ",\"data\":[],\"message\":\"该模式暂无商家\"}";
		}
		List<GoodsInfo> goodsList = goodsInfoService.getBusinessRecomendGoodsList(list);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> businessJson = new HashMap<String, Object>();
			BusinessInfo info = list.get(i);
			// 商家推荐商品
			List<Map<String, Object>> goodsJson = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < goodsList.size(); j++) {
				if (!info.getId().equals(goodsList.get(j).getBusinessInfoId())) {
					continue;
				}
				GoodsInfo g = goodsList.get(j);
				Map<String, Object> goods = new HashMap<String, Object>();
				goods.put("commodityID", g.getId());
				goods.put("commodityName", g.getName());
				goods.put("commodityImage", goodsInfoService.getGoodsPicUrl(g, request));
				goods.put("commodityPrice", g.getBasePrice());
				goods.put("url", "");
				goodsJson.add(goods);
			}
			businessJson.putAll(getGoodsModelJson(info, request));
			businessJson.put("activities", getBusinessSalse(info.getId()));
			businessJson.put("commodities", goodsJson);
			data.add(businessJson);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getBusinessList()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取商家信息
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID(不可空)
	 */
	@ResponseBody
	@RequestMapping(value = "/getBusinessIndex")
	public Map<String, Object> getBusinessIndex(String userID, String buildingID, String businessID, HttpServletRequest request) {
		long start = new Date().getTime();
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		BusinessInfo info = businessInfoService.get(businessID);
		if (info == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "商家不存在");
			return toJson;
		}
		// 获取商家团购信息
		List<GroupPurchaseBean> gpl = groupPurchaseService.getBusinessGroupPurchase(businessID);
		List<Map<String, Object>> groupJson = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < gpl.size(); i++) {
			if (i == 2) {// 只显示2个
				break;
			}
			Map<String, Object> group = new HashMap<String, Object>();
			GroupPurchaseBean gp = gpl.get(i);
			group.put("groupBuyID", gp.getGroupPurchaseTimeId());
			group.put("groupBuyName", gp.getGroupPurcName());
			group.put("groupBuyImage", MyFDFSClientUtils.get_fdfs_file_url(request, gp.getGroupPurcPic()));
			group.put("groupBuyPrice", gp.getGroupPurcMoney());
			group.put("marketPrice", gp.getMarketMoney());
			group.put("soldNum", gp.getSaleNum() != null ? gp.getSaleNum() : 0);
			group.put("groupBuyUrl", "");
			groupJson.add(group);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(getGoodsModelJson(info, request));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessID) : 0);
		data.put("activities", getBusinessSalse(businessID));// 商家满减活动
		data.put("groupBuy", groupJson);// 商家团购活动
		data.put("businessDesc", info.getBusinessIntroduce());// 商家介绍[富文本内容，需要特殊处理]
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getBusinessIndex()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return toJson;
	}

	/**
	 * 获取场地预约模式商家信息
	 * 
	 * @param userID
	 *            用户ID（可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @param businessID
	 *            商家ID（不可空）
	 */
	@ResponseBody
	@RequestMapping(value = "/getSiteIndex")
	public Map<String, Object> getSiteIndex(String userID, String buildingID, String businessID, HttpServletRequest request) {
		long start = new Date().getTime();
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		BusinessInfo info = businessInfoService.get(businessID);
		if (info == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "商家不存在");
			return toJson;
		}
		// 获取商家团购信息
		List<GroupPurchaseBean> gpl = groupPurchaseService.getBusinessGroupPurchase(businessID);
		List<Map<String, Object>> groupJson = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < gpl.size(); i++) {
			if (i == 2) {// 只显示2个
				break;
			}
			Map<String, Object> group = new HashMap<String, Object>();
			GroupPurchaseBean gp = gpl.get(i);
			group.put("groupBuyID", gp.getGroupPurchaseTimeId());
			group.put("groupBuyName", gp.getGroupPurcName());
			group.put("groupBuyImage", MyFDFSClientUtils.get_fdfs_file_url(request, gp.getGroupPurcPic()));
			group.put("groupBuyPrice", gp.getGroupPurcMoney());
			group.put("marketPrice", gp.getMarketMoney());
			group.put("soldNum", gp.getSaleNum() != null ? gp.getSaleNum() : 0);
			group.put("groupBuyUrl", "");
			groupJson.add(group);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(getGoodsModelJson(info, request));
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessID) : 0);
		data.put("groupBuy", groupJson);// 商家团购活动
		data.put("businessDesc", info.getBusinessIntroduce());// 商家介绍[富文本内容，需要特殊处理]

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getBusinessIndex()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return toJson;
	}

	private Map<String, Object> getGoodsModelJson(BusinessInfo info, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessID", info.getId());// 商家ID
		data.put("businessName", info.getBusinessName());// 商户名称
		data.put("labelName", info.getBusinessLabel());// 商户标签
		data.put("businessImage", MyFDFSClientUtils.get_fdfs_file_url(request, info.getBusinessPic()));// 商家图片
		data.put("businessAddress", businessInfoService.getAddress(info));// 地址
		data.put("businessPhone", info.getPhoneNum());// 联系电话
		data.put("prodModes", businessInfoService.getBusinessProdTypeList(info.getId()));
		data.put("businessHours", info.getBusinessHours());// 营业时间
		data.put("isNormal", businessInfoService.isBusinessNormal(info));
		data.put("sendMoney", "1".equals(info.getFullDistributeFlag()) ? info.getFullDistributeMoney() : 0);
		data.put("deliveryFee", info.getDistributeCharge() != null ? info.getDistributeCharge() : 0);// 配送费用
		data.put("isFeeActivity", "1".equals(info.getFreeDistributeFlag()) ? 1 : 0);// 是否配送费活动
		if ("1".equals(info.getFreeDistributeFlag())) {
			data.put("deliveryFeeActivity", "满" + info.getFreeDistributeMoney() + "元免" + (info.getDistributeCharge() != null ? info.getDistributeCharge() : 0) + "元运费");// 配送费活动内容
		}
		data.put("businessLabels", businessInfoService.getBusinessLabelList(info));
		data.put("businessUrl", "");
		return data;
	}

	/**
	 * 商户满减活动
	 * 
	 * @param businessID
	 *            商家ID
	 */
	private List<Map<String, Object>> getBusinessSalse(String businessID) {
		List<BusinessSales> bsl = businessInfoService.getBusinessSales(businessID);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (BusinessSales bs : bsl) {
			if (bs.getMoney() != null && bs.getBenefitMoney() != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("label", "满" + bs.getMoney() + "减" + bs.getBenefitMoney());
				data.put("desc", "满" + bs.getMoney() + "减" + bs.getBenefitMoney());
				listMap.add(data);
			}
		}
		return listMap;
	}

	/**
	 * 获取商家商品分类
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            用户ID(不可空)
	 * @param businessID
	 *            商家ID(不可空)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommodityCategory")
	public Map<String, Object> getCommodityCategory(String userID, String buildingID, String businessID, HttpServletRequest request) {
		long start = new Date().getTime();
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID, businessID)) {
			return toJson;
		}
		BusinessInfo info = businessInfoService.get(businessID);
		if (info == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "商家不存在");
			return toJson;
		}
		List<SortInfo> sortList = goodsInfoService.getBusinessSortInfoList(businessID);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (SortInfo sort : sortList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryID", sort.getId());
			map.put("categoryName", sort.getName());
			listMap.add(map);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(getGoodsModelJson(info, request));// 商家基本信息
		data.put("isCollection", StringUtils.isNotBlank(userID) ? myCollectService.isCollect(userID, buildingID, businessID) : 0);
		data.put("activities", getBusinessSalse(businessID));// 商家满减活动
		data.put("commodityCategory", listMap);// 商品分类
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "成功");
		if (AppUtils.DEBUG_MODEL) {
			System.out.println("/live/getCommodityCategory()运行时间：" + (new Date().getTime() - start) + "ms");
		}
		return toJson;
	}
}