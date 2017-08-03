/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.goods.entity.GoodsInfo;

/**
 * 商品信息DAO接口
 * @author test
 * @version 2017-07-04
 */
@MyBatisDao
public interface GoodsInfoDao extends CrudDao<GoodsInfo> {
	
	/**
	 * 复数上架
	 * 
	 * @param goodsId 商品ID
	 */
	int groundingByGoodsId(String goodsId);
	
	/**
	 * 复数下架
	 * 
	 * @param goodsId 商品ID
	 */
	int undercarriageByGoodsId(String goodsId);
	
	/**
	 * 复数下架
	 * 
	 * @param goodsId 商品ID
	 */
	GoodsInfo selectZeroStockGoods(String goodsId);

	/**
	 * 图片地址更新
	 * 
	 * @param imgUrlUpdateMap 更新条件
	 */
	void imgNameUpdate(Map<String, String> imgUrlUpdateMap);

    /**
     * 根据商品分类ID取得商品信息并将该行锁定
     * 
     * @param goodsId
     *            商品ID
     * @return 商品信息
     */
	List<GoodsInfo> findGoodsInfoListForUpdate(List<String> goodsId);

	/**
	 * 查询商品个数
	 * @param goodsInfo
	 * @return
	 */
    Integer findAllListCount(GoodsInfo goodsInfo);
}