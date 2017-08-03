/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.bean.GoodsInfoBean;
import com.its.modules.app.bean.ShoppingCartBean;
import com.its.modules.app.dao.GoodsInfoDao;
import com.its.modules.app.dao.GoodsSkuPriceDao;
import com.its.modules.app.dao.ShoppingCartDao;
import com.its.modules.app.entity.GoodsInfo;
import com.its.modules.app.entity.GoodsSkuPrice;
import com.its.modules.app.entity.ShoppingCart;

/**
 * 购物车Service
 * 
 * @author like
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class ShoppingCartService extends CrudService<ShoppingCartDao, ShoppingCart> {

	@Autowired
	private GoodsInfoDao goodsInfoDao;
	@Autowired
	private GoodsSkuPriceDao goodsSkuPriceDao;

	public ShoppingCart get(String id) {
		return super.get(id);
	}

	public List<ShoppingCart> findList(ShoppingCart shoppingCart) {
		return super.findList(shoppingCart);
	}

	public Page<ShoppingCart> findPage(Page<ShoppingCart> page, ShoppingCart shoppingCart) {
		return super.findPage(page, shoppingCart);
	}

	@Transactional(readOnly = false)
	public void save(ShoppingCart shoppingCart) {
		super.save(shoppingCart);
	}

	@Transactional(readOnly = false)
	public void delete(ShoppingCart shoppingCart) {
		super.delete(shoppingCart);
	}

	/**
	 * 获取购物车总金额和数量
	 * 
	 * @param accountID
	 * @param villageInfoID
	 * @param businessInfoId
	 * @return
	 */
	public ShoppingCartBean getShoppingCartTotal(String accountID, String villageInfoID, String businessInfoId) {
		return dao.getShoppingCartTotal(accountID, villageInfoID, businessInfoId);
	}

	/**
	 * 获取购物车中某个商品信息
	 * 
	 * @param accountID
	 * @param villageInfoID
	 * @param businessInfoId
	 * @param goodsInfoId
	 * @param goodsSkuPriceId
	 * @return
	 */
	public ShoppingCart getGoodsOfShoopingCart(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, String goodsSkuPriceId) {
		return dao.getGoodsOfShoopingCart(accountID, villageInfoID, businessInfoId, goodsInfoId, goodsSkuPriceId);
	}

	/**
	 * 加入购物车
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @param productId
	 *            商品ID
	 * @param goodsSkuPriceId
	 *            规格价格表ID(可选)
	 * @return
	 */
	@Transactional(readOnly = false)
	public void addShoppingCart(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, String goodsSkuPriceId) {
		ShoppingCart exist = dao.getGoodsOfShoopingCart(accountID, villageInfoID, businessInfoId, goodsInfoId, goodsSkuPriceId);
		if (exist != null) {// 此规格在购物车中已存在，数量+1
			dao.addShoppingCartNumber(accountID, villageInfoID, businessInfoId, goodsInfoId, goodsSkuPriceId);
		} else {
			// 在购物城中创建对象
			ShoppingCart sc = new ShoppingCart();
			sc.setAccountId(accountID);
			sc.setVillageInfoId(villageInfoID);
			sc.setBusinessInfoId(businessInfoId);
			sc.setGoodsInfoId(goodsInfoId);
			sc.setNumber(1);
			if (StringUtils.isNotBlank(goodsSkuPriceId)) {
				sc.setGoodsSkuPriceId(goodsSkuPriceId);
				GoodsSkuPrice gsp = goodsSkuPriceDao.get(goodsSkuPriceId);// 规格对应的价格
				if (gsp != null) {
					sc.setPrice((gsp.getBenefitPrice() != null && gsp.getBenefitPrice() != 0) ? gsp.getBenefitPrice() : gsp.getBasePrice());
				}
			} else {
				GoodsInfo goods = goodsInfoDao.get(goodsInfoId);// 没有规格时商品表的价格
				if (goods != null) {
					sc.setPrice((goods.getBenefitPrice() != null && goods.getBenefitPrice() != 0) ? goods.getBenefitPrice() : goods.getBasePrice());
				}
			}
			sc.setAddDate(new Date());
			sc.setOrderStatus("0");
			super.save(sc);
		}
	}

	/**
	 * 购物车商品数量-1
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @param productId
	 *            商品ID
	 * @param goodsSkuPriceId
	 *            规格价格表ID(可选)
	 * @return
	 */
	@Transactional(readOnly = false)
	public void reduceShoppingCart(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, String goodsSkuPriceId) {
		ShoppingCart exist = dao.getGoodsOfShoopingCart(accountID, villageInfoID, businessInfoId, goodsInfoId, goodsSkuPriceId);
		if (exist != null) {
			if (exist.getNumber() > 0) {
				dao.reduceShoppingCart(accountID, villageInfoID, businessInfoId, goodsInfoId, goodsSkuPriceId);
			}
		}
	}

	/**
	 * 清空购物车
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 */
	@Transactional(readOnly = false)
	public void emptyShoppingCart(String accountID, String villageInfoID, String businessInfoId) {
		dao.emptyShoppingCart(accountID, villageInfoID, businessInfoId);
	}

	/**
	 * 获取购物车商品信息
	 * 
	 * @param accountID
	 * @param villageInfoID
	 * @param businessInfoId
	 * @return
	 */
	public List<GoodsInfoBean> getShoppingCartList(String accountID, String villageInfoID, String businessInfoId) {
		return dao.getShoppingCartList(accountID, villageInfoID, businessInfoId);
	}
	/**
	 * 查询会员当前楼盘某商家，加入购物车的信息
	 * @param accountId
	 * @param villageInfoId
	 * @param businessInfoId
	 * @return
	 */
	public List<ShoppingCart> getShoppingCartInfoList(String accountId, String villageInfoId, String businessInfoId) {
		return dao.getShoppingCartInfoList(accountId, villageInfoId, businessInfoId);
	}
	/**
	 * 提交订单后，修改购物车中商品的状态
	 * @param accountId
	 * @param villageInfoId
	 * @param businessInfoId
	 */
	public void updateOrderState(String accountId, String villageInfoId, String businessInfoId){
		dao.updateOrderState(accountId, villageInfoId, businessInfoId);
	}
}