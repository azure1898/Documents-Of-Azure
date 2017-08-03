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
import com.its.modules.app.bean.GoodsSkuBean;
import com.its.modules.app.common.AppUtils;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.BusinessSales;
import com.its.modules.app.entity.GoodsInfo;
import com.its.modules.app.entity.ShoppingCart;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GoodsInfoService;
import com.its.modules.app.service.MyCollectService;
import com.its.modules.app.service.ShoppingCartService;

import net.sf.json.JSONObject;

/**
 * 商品信息Controller
 * 
 * @author like
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class GoodsInfoController extends BaseController {
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private MyCollectService myCollectService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 商家分类商品信息
	 * 
	 * @param userID
	 *            会员ID(可空)
	 * @param businessID
	 *            商家ID(不可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param categoryID
	 *            分类ID(可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommoditiesByCategory")
	public String getCommoditiesByCategory(String userID, String businessID, String buildingID, String categoryID, HttpServletRequest request) {
		long start = new Date().getTime();
		try {
			if (StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
			}
			List<GoodsInfo> list = goodsInfoService.getGoodsInfoBySortList(categoryID, businessID);
			if (list.size() == 0) {
				return "{\"code\":" + Global.CODE_SUCCESS + ",\"data\":[],\"message\":\"该分类暂无商品\"}";
			}
			List<ShoppingCart> cartList = shoppingCartService.getShoppingCartInfoList(userID, buildingID, businessID);
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			for (GoodsInfo g : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("commodityID", g.getId());
				map.put("commodityName", g.getName());
				map.put("commodityImage", goodsInfoService.getGoodsPicUrl(g, request));
				map.put("originalPrice", g.getBasePrice() != null ? g.getBasePrice() : 0);
				map.put("discountedPrice", g.getBenefitPrice() != null ? g.getBenefitPrice() : 0);
				int cartNum = 0;// 该商品加入购物车的数量
				for (ShoppingCart cart : cartList) {
					if (g.getId().equals(cart.getGoodsInfoId())) {
						cartNum += cart.getNumber();
					}
				}
				map.put("commodityNumber", cartNum);
				// 商品多规格
				List<GoodsSkuBean> gsbList = goodsInfoService.getGoodsSkuList(g.getId());
				map.put("isMoreSpe", gsbList.size() > 0 ? 1 : 0);
				map.put("speCategory", gsbList.size() > 0 ? gsbList.get(0).getSkuKeyName() : "");
				map.put("currentSpeID", gsbList.size() > 0 ? gsbList.get(0).getId() : "");
				List<Map<String, Object>> specifications = new ArrayList<Map<String, Object>>();
				for (GoodsSkuBean bean : gsbList) {
					Map<String, Object> beanMap = new HashMap<>();
					beanMap.put("specificationID", bean.getId());
					beanMap.put("specificationName", bean.getSkuValueName());
					beanMap.put("specificationPrice", bean.getBasePrice() != null ? bean.getBasePrice() : 0);
					beanMap.put("specificationdisPrice", bean.getBenefitPrice() != null ? bean.getBenefitPrice() : 0);
					int cartSkuNum = 0;
					if (StringUtils.isNotBlank(userID)) {
						for (ShoppingCart cart : cartList) {
							if (g.getId().equals(cart.getGoodsInfoId()) && bean.getId().equals(cart.getGoodsSkuPriceId())) {
								cartSkuNum = cart.getNumber();
							}
						}
					}
					beanMap.put("specificationNumber", cartSkuNum);
					specifications.add(beanMap);
				}
				map.put("specifications", specifications);
				map.put("commodityUrl", "");
				data.add(map);
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("code", Global.CODE_SUCCESS);
			json.put("data", data);
			json.put("message", "成功");
			return JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"" + e.getMessage() + "\"}";
			}
			return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"系统错误\"}";
		} finally {
			if (AppUtils.DEBUG_MODEL) {
				System.out.println("/live/getCommoditiesByCategory()运行时间：" + (new Date().getTime() - start) + "ms");
			}
		}
	}

	/**
	 * 商品详情
	 * 
	 * @param userID
	 *            用户ID(可空)
	 * @param buildingID
	 *            楼盘ID(不可空)
	 * @param businessID
	 *            商家ID(不可空)
	 * @param commodityID
	 *            商品ID(不可空)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommodityDetail")
	public String getCommodityDetail(String userID, String buildingID, String businessID, String commodityID, HttpServletRequest request) {
		if (StringUtils.isBlank(buildingID) && StringUtils.isBlank(businessID) && StringUtils.isBlank(commodityID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		BusinessInfo business = businessInfoService.get(businessID);
		if (business == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，该商家不存在\"}";
		}
		GoodsInfo goods = goodsInfoService.get(commodityID);
		if (goods == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误，该商品不存在\"}";
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("commodityID", goods.getId());
		data.put("commodityName", goodsInfoService.getGoodsInfoName(goods));
		data.put("commodityImage", goodsInfoService.getAllGoodsPicUrlMap(goods, request));
		data.put("originalPrice", goods.getBasePrice() != null ? goods.getBasePrice() : 0);
		data.put("discountedPrice", goods.getBenefitPrice() != null ? goods.getBenefitPrice() : 0);
		data.put("businessID", business.getId());
		data.put("businessName", business.getBusinessName());
		data.put("businessPhone", business.getPhoneNum());
		data.put("isNormal", businessInfoService.isBusinessNormal(business));
		data.put("isCollection", myCollectService.isCollect(userID, buildingID, businessID));
		List<BusinessSales> bsList = businessInfoService.getBusinessSales(businessID);
		List<Map<String, Object>> activities = new ArrayList<Map<String, Object>>();
		for (BusinessSales s : bsList) {
			Map<String, Object> sales = new HashMap<String, Object>();
			sales.put("label", "满" + s.getMoney() + "减" + s.getBenefitMoney());
			sales.put("desc", "满" + s.getMoney() + "元减" + s.getBenefitMoney() + "元");
			activities.add(sales);
		}
		data.put("activities", activities);
		data.put("commodityDesc", goods.getContent());
		List<ShoppingCart> cartList = new ArrayList<>();
		if (StringUtils.isBlank(userID)) {
			data.put("commodityNumber", 0);
		} else {
			cartList = shoppingCartService.getShoppingCartInfoList(userID, buildingID, businessID);
			int cartNum = 0;// 该商品加入购物车的数量
			for (ShoppingCart cart : cartList) {
				if (goods.getId().equals(cart.getGoodsInfoId())) {
					cartNum += cart.getNumber();
				}
			}
			data.put("commodityNumber", cartNum);
		}
		// 商品多规格
		List<GoodsSkuBean> gsbList = goodsInfoService.getGoodsSkuList(goods.getId());
		data.put("isMoreSpe", gsbList.size() > 0 ? 1 : 0);
		data.put("speCategory", gsbList.size() > 0 ? gsbList.get(0).getSkuKeyName() : "");
		data.put("currentSpeID", gsbList.size() > 0 ? gsbList.get(0).getId() : "");
		List<Map<String, Object>> specifications = new ArrayList<Map<String, Object>>();
		for (GoodsSkuBean bean : gsbList) {
			Map<String, Object> beanMap = new HashMap<>();
			beanMap.put("specificationID", bean.getId());
			beanMap.put("specificationName", bean.getSkuValueName());
			beanMap.put("specificationPrice", bean.getBasePrice() != null ? bean.getBasePrice() : 0);
			beanMap.put("specificationdisPrice", bean.getBenefitPrice() != null ? bean.getBenefitPrice() : 0);
			int cartSkuNum = 0;
			if (StringUtils.isNotBlank(userID)) {
				for (ShoppingCart cart : cartList) {
					if (goods.getId().equals(cart.getGoodsInfoId()) && bean.getId().equals(cart.getGoodsSkuPriceId())) {
						cartSkuNum = cart.getNumber();
					}
				}
			}
			beanMap.put("specificationNumber", cartSkuNum);
			specifications.add(beanMap);
		}
		data.put("specifications", specifications);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 获取商品规格
	 * 
	 * @param userID
	 * @param commodityID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSpecifications")
	public String getSpecifications(String userID, String commodityID) {
		if (StringUtils.isBlank(userID) && StringUtils.isBlank(commodityID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		List<GoodsSkuBean> list = goodsInfoService.getGoodsSkuList(commodityID);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (GoodsSkuBean bean : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("specificationID", bean.getId());
			map.put("specificationName", bean.getSkuValueName());
			listMap.add(map);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", listMap);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

}