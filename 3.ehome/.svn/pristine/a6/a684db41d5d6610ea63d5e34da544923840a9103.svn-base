/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.GoodsSkuPrice;
import com.its.modules.app.dao.GoodsSkuPriceDao;

/**
 * 商品规格价格表Service
 * 
 * @author like
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class GoodsSkuPriceService extends CrudService<GoodsSkuPriceDao, GoodsSkuPrice> {

	public GoodsSkuPrice get(String id) {
		return super.get(id);
	}

	public List<GoodsSkuPrice> findList(GoodsSkuPrice goodsSkuPrice) {
		return super.findList(goodsSkuPrice);
	}

	public Page<GoodsSkuPrice> findPage(Page<GoodsSkuPrice> page, GoodsSkuPrice goodsSkuPrice) {
		return super.findPage(page, goodsSkuPrice);
	}

	@Transactional(readOnly = false)
	public void save(GoodsSkuPrice goodsSkuPrice) {
		super.save(goodsSkuPrice);
	}

	@Transactional(readOnly = false)
	public void delete(GoodsSkuPrice goodsSkuPrice) {
		super.delete(goodsSkuPrice);
	}
	
	/**
	 * 根据商品ID，规格名称ID，规格列表ID更新商品规格库存
	 * 
	 * @param count
	 *            操作数量
	 * @param goodsInfoId
	 *            商品ID
	 * @param skuKeyId
	 *            规格名称ID
	 * @param skuValueId
	 *            规格列表ID
	 * @return 操作的行数
	 */
	public int updateStock(int count, String goodsInfoId, String skuKeyId, String skuValueId) {
		return dao.updateStock(count, goodsInfoId, skuKeyId, skuValueId);
	}
}