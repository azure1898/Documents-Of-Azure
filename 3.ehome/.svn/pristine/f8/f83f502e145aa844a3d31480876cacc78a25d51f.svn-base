/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.service.ServiceException;
import com.its.common.utils.StringUtils;
import com.its.modules.adver.dao.AdverManageDao;
import com.its.modules.adver.entity.AdverManage;

/**
 * 广告管理-发布管理Service
 * 
 * @author zhujiao
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class AdverManageService extends CrudService<AdverManageDao, AdverManage> {

    @Autowired
    private AdverManageDao AdverManageDao;

    public AdverManage get(String id) {
        return super.get(id);
    }

    public List<AdverManage> findList(AdverManage adverManage) {
        return super.findList(adverManage);
    }

    public Page<AdverManage> findPage(Page<AdverManage> page, AdverManage adverManage) {
        return super.findPage(page, adverManage);
    }

    @Transactional(readOnly = false)
    public void save(AdverManage adverManage) {
        super.save(adverManage);
    }

    @Transactional(readOnly = false)
    public void delete(AdverManage adverManage) {
        super.delete(adverManage);
    }

    /**
     * 根据广告名称获取广告对象
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午5:56:52
     * @return AdverManage
     */
    @Transactional(readOnly = false)
    public AdverManage getModelByName(String name) {
        AdverManage r = new AdverManage();
        r.setAdvertTitle(name);
        return AdverManageDao.getModelByName(r);
    }

    /**
     * 添加广告信息
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午5:59:41
     * @return AdverManage
     */
    @Transactional(readOnly = false)
    public void saveAdverManage(AdverManage adverManage) {
        // 图文广告内容的二次处理
        if (StringUtils.isNotBlank(adverManage.getAdverContent())) {
            adverManage.setAdverContent(StringEscapeUtils.unescapeHtml4(adverManage.getAdverContent()));
        }
        if (StringUtils.isBlank(adverManage.getId())) {
            // 添加之前准备
            adverManage.preInsert();
            // 插入主表信息
            AdverManageDao.insert(adverManage);
            // 插入投放楼盘关联数据
            if (adverManage.getVillageList() != null && adverManage.getVillageList().size() > 0) {
                AdverManageDao.insertAdverBuilding(adverManage);
            } else {
                throw new ServiceException(adverManage.getAdvertTitle() + "没有设置投放楼盘！");
            }
        } else {
            // 更新之前准备
            adverManage.preUpdate();
            // 更新主表信息
            AdverManageDao.update(adverManage);
            // 删除投放楼盘关联数据
            AdverManageDao.deleteAdverBuilding(adverManage);
            // 插入投放楼盘关联数据
            if (adverManage.getVillageList() != null && adverManage.getVillageList().size() > 0) {
                AdverManageDao.insertAdverBuilding(adverManage);
            } else {
                throw new ServiceException(adverManage.getAdvertTitle() + "没有设置投放楼盘！");
            }
        }
    }

}