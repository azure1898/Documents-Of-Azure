/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.goods.entity.GoodsSkuPrice;
import com.its.modules.goods.entity.SkuKey;
import com.its.modules.goods.entity.SkuValue;
import com.its.modules.goods.dao.GoodsSkuPriceDao;

/**
 * 商品规格价格Service
 * @author liuhl
 * @version 2017-07-04
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
	 * 根据规格名称id取得商品规格价格信息
	 * @param goodsSkuPrice 规格名称id
	 * @return 商品规格价格信息
	 */
	public List<GoodsSkuPrice> getGoodsSkuPriceBySkuKeyID(SkuKey skuKey) {
		return super.dao.getGoodsSkuPriceBySkuKeyID(skuKey);
	}
	
	/**
	 * 根据规格名称id取得商品规格价格信息
	 * @param goodsSkuPrice 规格名称id
	 * @return 商品规格价格信息
	 */
	public List<GoodsSkuPrice> getGoodsSkuPriceBySkuValueID(SkuValue skuValue) {
		return super.dao.getGoodsSkuPriceBySkuValueID(skuValue);
	}
	
    /**
     * 根据商品分类ID取得商品分类信息并将该行锁定
     * 
     * @param goodsSkuPrice
     *            商品分类信息
     * @return 商品分类信息
     */
    public List<GoodsSkuPrice> findGoodsSkuPriceListForUpdate(GoodsSkuPrice goodsSkuPrice) {
        return this.dao.findGoodsSkuPriceListForUpdate(goodsSkuPrice);
    }
}