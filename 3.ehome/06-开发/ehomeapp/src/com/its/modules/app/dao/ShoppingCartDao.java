package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.bean.GoodsInfoBean;
import com.its.modules.app.bean.ShoppingCartBean;
import com.its.modules.app.entity.ShoppingCart;

/**
 * 购物车DAO接口
 * 
 * @author like
 * @version 2017-07-06
 */
@MyBatisDao
public interface ShoppingCartDao extends CrudDao<ShoppingCart> {

	/**
	 * 获取购物车总金额和数量
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @return ShoppingCartBean
	 */
	public ShoppingCartBean getShoppingCartTotal(String accountID, String villageInfoID, String businessInfoId);

	/**
	 * 获取购物车中已添加的商品
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
	 *            规格价格表ID(没有规格时传null)
	 * @return ShoppingCart
	 */
	public ShoppingCart getGoodsOfShoopingCart(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, @Param("goodsSkuPriceId") String goodsSkuPriceId);

	/**
	 * 购物车中的数量+1
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @param goodsInfoId
	 *            商品ID
	 * @param goodsSkuPriceId
	 *            规格价格表ID(没有规格时传null)
	 */
	public void addShoppingCartNumber(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, @Param("goodsSkuPriceId") String goodsSkuPriceId);

	/**
	 * 购物车中的数量-1
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @param goodsInfoId
	 *            商品ID
	 * @param goodsSkuPriceId
	 *            规格价格表ID(没有规格时传null)
	 */
	public void reduceShoppingCart(String accountID, String villageInfoID, String businessInfoId, String goodsInfoId, @Param("goodsSkuPriceId") String goodsSkuPriceId);

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
	public void emptyShoppingCart(String accountID, String villageInfoID, String businessInfoId);

	/**
	 * 获取购物车商品信息
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @return List<GoodsInfoBean>
	 */
	public List<GoodsInfoBean> getShoppingCartList(String accountID, String villageInfoID, String businessInfoId);

	/**
	 * 查询会员当前楼盘某商家，加入购物车的商品
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 * @return List<ShoppingCart>
	 */
	public List<ShoppingCart> getShoppingCartInfoList(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("businessInfoId") String businessInfoId);

	/**
	 * 提交订单后，修改购物车中商品的状态
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @param businessInfoId
	 *            商家ID
	 */
	public void updateOrderState(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("businessInfoId") String businessInfoId);

	/**
	 * 获取某用户在某楼盘下的购物车中的商家列表
	 * 
	 * @param accountID
	 *            会员ID
	 * @param villageInfoID
	 *            楼盘ID
	 * @return List<String>
	 */
	public List<String> getBusinessInfoIds(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);
}