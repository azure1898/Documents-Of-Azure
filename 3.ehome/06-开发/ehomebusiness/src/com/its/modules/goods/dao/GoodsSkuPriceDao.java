/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.goods.entity.GoodsSkuPrice;
import com.its.modules.goods.entity.SkuKey;
import com.its.modules.goods.entity.SkuValue;

/**
 * 商品规格价格DAO接口
 * @author liuhl
 * @version 2017-07-04
 */
@MyBatisDao
public interface GoodsSkuPriceDao extends CrudDao<GoodsSkuPrice> {
	
	/**
	 * 根据规格名称id取得商品规格价格信息
	 * @param goodsSkuPrice 规格名称id
	 * @return 商品规格价格信息
	 */
	List<GoodsSkuPrice> getGoodsSkuPriceBySkuKeyID(SkuKey skuKey);
	
	/**
	 * 根据规格列表id取得商品规格价格信息
	 * @param goodsSkuPrice 规格列表id
	 * @return 商品规格价格信息
	 */
	List<GoodsSkuPrice> getGoodsSkuPriceBySkuValueID(SkuValue skuValue);
	
	/**
	 * 根据商品ID删除
	 * @param skuValue
	 * @return
	 */
	int deleteByGoodsId(GoodsSkuPrice goodsSkuPrice);

    /**
     * 根据商品分类ID取得商品分类信息并将该行锁定
     * 
     * @param goodsSkuPrice
     *            商品分类信息
     * @return 商品分类信息
     */
	List<GoodsSkuPrice> findGoodsSkuPriceListForUpdate(GoodsSkuPrice goodsSkuPrice);
}