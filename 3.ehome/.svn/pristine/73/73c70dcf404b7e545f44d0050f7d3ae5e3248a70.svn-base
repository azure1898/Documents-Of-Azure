/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.service.CrudService;
import com.its.modules.goods.dao.GoodsInfoDao;
import com.its.modules.goods.entity.GoodsInfo;

/**
 * 商品信息Service
 * 
 * @author zhujiao
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class GoodsInfoService extends CrudService<GoodsInfoDao, GoodsInfo> {

    @Autowired
    private GoodsInfoDao goodsInfoDao;

    /**
     * 根据商品分类ID取得商品信息
     * 
     * @param sortInfoID
     *            商品分类ID
     * @return 商品信息
     */
    public List<GoodsInfo> findGoodsInfoList(String sortInfoID) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setSortInfoId(sortInfoID);
        return super.findList(goodsInfo);
    }

    /**
     * 通过商家ID获取全部商品信息列表
     * 
     * @return
     * @return List<BusinessCategorydict>
     * @author zhujiao
     * @date 2017年7月19日 下午3:58:06
     */
    public List<GoodsInfo> findAllList(String businessInfoId) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setBusinessInfoId(businessInfoId);
        return goodsInfoDao.findAllList(goodsInfo);
    }

}