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
import com.its.modules.app.bean.GoodsInfoBean;
import com.its.modules.app.bean.ShoppingCartBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.GoodsInfo;
import com.its.modules.app.entity.GoodsSkuPrice;
import com.its.modules.app.entity.ShoppingCart;
import com.its.modules.app.service.BusinessInfoService;
import com.its.modules.app.service.GoodsInfoService;
import com.its.modules.app.service.GoodsSkuPriceService;
import com.its.modules.app.service.ShoppingCartService;

import net.sf.json.JSONObject;

/**
 * 购物车Controller
 * 
 * @author like
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${appPath}/live")
public class ShoppingCartController extends BaseController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private GoodsSkuPriceService goodsSkuPriceService;

	/**
	 * 加入购物车
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param businessID
	 *            商家ID
	 * @param commodityID
	 *            商品ID
	 * @param specificationID
	 *            规格价格表ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addShoppingCart")
	public String addShoppingCart(String userID, String buildingID, String businessID, String commodityID, String specificationID) {
		try {
			if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID) || StringUtils.isBlank(commodityID)) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
			}
			if (StringUtils.isBlank(specificationID)) {
				specificationID = null;
			}
			// 判断商品库存
			GoodsInfo goods = goodsInfoService.get(commodityID);
			if (goods.getStock() <= 0) {
				return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"库存不足\"}";
			}
			// 判断限购数量
			if ("1".equals(goods.getQuota())) {
				ShoppingCart sc = shoppingCartService.getGoodsOfShoopingCart(userID, buildingID, businessID, commodityID, specificationID);
				if (sc != null && sc.getNumber() >= goods.getQuotaNum()) {
					return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"已达限购数量\"}";
				}
			}
			// 加入购物车
			shoppingCartService.addShoppingCart(userID, buildingID, businessID, commodityID, specificationID);
			ShoppingCartBean bean = shoppingCartService.getShoppingCartTotal(userID, buildingID, businessID);
			Map<String, Object> data = new HashMap<String, Object>();
			if (bean != null) {
				data.put("totalMoney", bean.getTotalMoney());
				data.put("commodityNumber", bean.getSumNumber());
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
		}
	}

	/**
	 * 减少购物车商品的数量
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param businessID
	 *            商家ID
	 * @param commodityID
	 *            商品ID
	 * @param specificationID
	 *            规格价格表ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reduceShoppingCart")
	public String reduceShoppingCart(String userID, String buildingID, String businessID, String commodityID, String specificationID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID) || StringUtils.isBlank(commodityID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		if (StringUtils.isBlank(specificationID)) {
			specificationID = null;
		}
		try {
			shoppingCartService.reduceShoppingCart(userID, buildingID, businessID, commodityID, specificationID);
			ShoppingCartBean bean = shoppingCartService.getShoppingCartTotal(userID, buildingID, businessID);
			Map<String, Object> data = new HashMap<String, Object>();
			if (bean != null) {
				data.put("totalMoney", bean.getTotalMoney());
				data.put("commodityNumber", bean.getSumNumber());
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
		}
	}

	/**
	 * 清空购物车
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emptiedShoppingCart")
	public String emptiedShoppingCart(String userID, String buildingID, String businessID) {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(buildingID) || StringUtils.isBlank(businessID)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		try {
			shoppingCartService.emptyShoppingCart(userID, buildingID, businessID);
			return "{\"code\":" + Global.CODE_SUCCESS + ",\"message\":\"成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"" + e.getMessage() + "\"}";
			}
			return "{\"code\":" + Global.CODE_ERROR + ",\"message\":\"系统错误\"}";
		}
	}

	/**
	 * 获取购物车商品信息
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 * @param businessID
	 *            商家ID
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/getShoppingCart")
	public Map<String, Object> getShoppingCart(String userID, String buildingID, String businessID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID, businessID)) {
			return toJson;
		}
		try {
			List<GoodsInfoBean> list = shoppingCartService.getShoppingCartList(userID, buildingID, businessID);
			List<Map<String, Object>> listJson = new ArrayList<Map<String, Object>>();
			BusinessInfo businessInfo = businessInfoService.get(businessID);
			if (businessInfo == null) {
				toJson.put("code", Global.CODE_PROMOT);
				toJson.put("message", "商家不存在");
				return toJson;
			}
			double totalMoney = 0;
			int totalNumber = 0;
			for (GoodsInfoBean goodsInfoBean : list) {
				Map<String, Object> goods = new HashMap<String, Object>();
				goods.put("commodityID", goodsInfoBean.getId());
				goods.put("commodityName", goodsInfoBean.getName());
				goods.put("commodityImage", goodsInfoService.getGoodsPicUrl(goodsInfoBean, request));
				goods.put("specificationID", StringUtils.isNotBlank(goodsInfoBean.getGoodsSkuPriceID()) ? goodsInfoBean.getGoodsSkuPriceID() : "");
				if (StringUtils.isNotBlank(goodsInfoBean.getGoodsSkuPriceID())) {
					GoodsSkuPrice goodsSkuPrice = goodsSkuPriceService.get(goodsInfoBean.getGoodsSkuPriceID());
					goods.put("originalPrice", ValidateUtil.validateDouble(goodsSkuPrice.getBasePrice()));
					goods.put("discountedPrice", ValidateUtil.validateDouble(goodsSkuPrice.getBenefitPrice()));
					double price = ValidateUtil.validateDouble(goodsSkuPrice.getBasePrice());
					if (goodsSkuPrice.getBenefitPrice() != null) {
						price = ValidateUtil.validateDouble(goodsSkuPrice.getBenefitPrice());
					}
					totalMoney += price * goodsInfoBean.getCartNumber();
				} else {
					goods.put("originalPrice", ValidateUtil.validateDouble(goodsInfoBean.getBasePrice()));
					goods.put("discountedPrice", ValidateUtil.validateDouble(goodsInfoBean.getBenefitPrice()));
					double price = ValidateUtil.validateDouble(goodsInfoBean.getBasePrice());
					if (goodsInfoBean.getBenefitPrice() != null) {
						price = ValidateUtil.validateDouble(goodsInfoBean.getBenefitPrice());
					}
					totalMoney += price * goodsInfoBean.getCartNumber();
				}
				goods.put("commodityNumber", goodsInfoBean.getCartNumber());
				totalNumber += goodsInfoBean.getCartNumber();
				listJson.add(goods);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("totalMoney", totalMoney);
			data.put("totalNumber", totalNumber);
			// 差XX起送
			double fullDistributeMoney = 0;
			double lessSendMoney = 0;
			if (CommonGlobal.YES.equals(businessInfo.getFullDistributeFlag())) {
				fullDistributeMoney = businessInfo.getFullDistributeMoney();
				if (totalMoney < fullDistributeMoney) {
					lessSendMoney = fullDistributeMoney - totalMoney;
				}
				data.put("lessSendMoney", lessSendMoney);
			} else {
				data.put("lessSendMoney", 0);
			}
			// 是否有满减活动（0->没有 1->有）
			data.put("isActivity", businessInfo.getPromotionFlag());
			if (CommonGlobal.YES.equals(businessInfo.getPromotionFlag())) {
				String lessMoney = businessInfoService.getLessMoney(businessInfo.getId(), totalMoney);
				String[] moneyArr = lessMoney.split(",");
				if (moneyArr.length == 3) {
					data.put("fullMoney", ValidateUtil.parseDouble(moneyArr[0]));
					data.put("giftMoney", ValidateUtil.parseDouble(moneyArr[1]));
					data.put("lessMoney", ValidateUtil.parseDouble(moneyArr[2]));
				}
			} else {
				data.put("fullMoney", 0);
				data.put("giftMoney", 0);
				data.put("lessMoney", 0);
			}
			data.put("commodities", listJson);
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("data", data);
			toJson.put("message", "成功");
			return toJson;
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				toJson.put("code", Global.CODE_ERROR);
				toJson.put("message", e.getMessage());
				return toJson;
			}
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "系统错误");
			return toJson;
		}
	}
}