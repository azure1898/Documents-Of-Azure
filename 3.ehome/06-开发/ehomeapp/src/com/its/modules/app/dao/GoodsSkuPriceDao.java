package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.GoodsSkuBean;
import com.its.modules.app.entity.GoodsSkuPrice;

/**
 * 商品规格价格DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-05
 */
@MyBatisDao
public interface GoodsSkuPriceDao extends CrudDao<GoodsSkuPrice> {

	/**
	 * 返回商品规格集合
	 * 
	 * @param goodsInfoID
	 *            商品ID
	 * @return List<GoodsSkuBean>
	 */
	public List<GoodsSkuBean> getGoodsSkuList(String goodsInfoID);

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
	public int updateStock(@Param("count") int count, @Param("goodsInfoId") String goodsInfoId, @Param("skuKeyId") String skuKeyId, @Param("skuValueId") String skuValueId);
}