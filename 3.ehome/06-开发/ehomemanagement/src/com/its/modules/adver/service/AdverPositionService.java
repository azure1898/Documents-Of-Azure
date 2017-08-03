/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.adver.dao.AdverPositionDao;
import com.its.modules.adver.entity.AdverPosition;

/**
 * 位置管理Service
 * 
 * @author ChenXiangyu
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class AdverPositionService extends CrudService<AdverPositionDao, AdverPosition> {

    @Autowired
    private AdverPositionDao adverPositionDao;

    public AdverPosition get(String id) {
        return super.get(id);
    }

    public List<AdverPosition> findList(AdverPosition adverPosition) {
        return super.findList(adverPosition);
    }

    public Page<AdverPosition> findPage(Page<AdverPosition> page, AdverPosition adverPosition) {
        return super.findPage(page, adverPosition);
    }

    @Transactional(readOnly = false)
    public void save(AdverPosition adverPosition) {
        super.save(adverPosition);
    }

    @Transactional(readOnly = false)
    public void delete(AdverPosition adverPosition) {
        super.delete(adverPosition);
    }

    /**
     * 根据产品线返回所有位置信息
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午7:29:09
     * @return List<AdverPosition>
     */
    public List<AdverPosition> findAllList(String moduleCode) {
        AdverPosition adverPosition = new AdverPosition();
        adverPosition.setModuleCode(moduleCode);
        return adverPositionDao.findAllList(adverPosition);
    }

    /**
     * 查询相同产品线的”开屏“位置的个数
     * @return 相同产品线的”开屏“位置的个数
     */
    public int getOpenScreenOfModuleCount(AdverPosition adverPosition) {
        Integer openSrceenCount = adverPositionDao.getOpenScreenOfModuleCount(adverPosition);
        if (openSrceenCount == null) {
            return 0;
        }
        return openSrceenCount;
    }
    
    /**
     * 根据位置名称查找对应产品线的广告位置记录
     * 
     * @param positionName 位置名称
     * @param moduleCode 产品线
     * @return 广告位置记录，若没有查到则返回null
     */
    public List<AdverPosition> getPositionByPositionName(String positionName, String moduleCode) {
        List<AdverPosition> adverPositionList = adverPositionDao.getPositionByPositionName(positionName, moduleCode);
        if (adverPositionList == null || adverPositionList.isEmpty()) {
            return null;
        }
        return adverPositionList;
    }
}