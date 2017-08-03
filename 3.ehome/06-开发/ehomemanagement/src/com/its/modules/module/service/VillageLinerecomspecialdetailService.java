/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.module.dao.VillageLinerecomspecialdetailDao;
import com.its.modules.module.entity.VillageLinerecommodule;
import com.its.modules.module.entity.VillageLinerecomspecial;
import com.its.modules.module.entity.VillageLinerecomspecialdetail;
import com.its.modules.village.entity.VillageLine;

/**
 * 楼盘产品线专题推荐明细Service
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
@Service
@Transactional(readOnly = true)
public class VillageLinerecomspecialdetailService extends CrudService<VillageLinerecomspecialdetailDao, VillageLinerecomspecialdetail> {

    @Autowired
    private VillageLinerecomspecialdetailDao recomspecialdetailDao;

    public VillageLinerecomspecialdetail get(String id) {
        return super.get(id);
    }

    public List<VillageLinerecomspecialdetail> findList(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
        return super.findList(villageLinerecomspecialdetail);
    }

    public Page<VillageLinerecomspecialdetail> findPage(Page<VillageLinerecomspecialdetail> page, VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
        return super.findPage(page, villageLinerecomspecialdetail);
    }

    @Transactional(readOnly = false)
    public void save(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
        super.save(villageLinerecomspecialdetail);
    }

    @Transactional(readOnly = false)
    public void delete(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
        super.delete(villageLinerecomspecialdetail);
    }

    /**
     * 通过专题推荐的ID获取专题推荐明细List
     * 
     * @param villageLineId
     * @return
     * @return List<VillageLinerecommodule>
     * @author zhujiao
     * @date 2017年7月28日 上午10:03:50
     */
    public List<VillageLinerecomspecialdetail> getRecomSpecialDetailList(String specialId) {
        return recomspecialdetailDao.getRecomSpecialDetailList(specialId);
    };

    /**
     * 保存推荐专题详情信息
     * 
     * @param villageLine
     * @return void
     * @author zhujiao
     * @date 2017年7月28日 上午9:48:57
     */
    @Transactional(readOnly = false)
    public void saveRecomSpecialDetail(VillageLinerecomspecial recomSpecial) {
        if (recomSpecial.getRecomSpecialDetailList() != null) {
            for (int i = 0; i < recomSpecial.getRecomSpecialDetailList().size(); i++) {
                VillageLinerecomspecialdetail recomSpecialDetail = new VillageLinerecomspecialdetail();
                recomSpecialDetail = recomSpecial.getRecomSpecialDetailList().get(i);
                if ("0".equals(recomSpecialDetail.getDelFlag())) {
                    recomSpecialDetail.preInsert();
                    recomSpecialDetail.setSortNum(i + "");
                    recomSpecialDetail.setVillageLineRecomSpecialId(recomSpecial.getId());
                    recomspecialdetailDao.insert(recomSpecialDetail);

                }
            }
        }
    }

    /**
     * 保存推荐专题详情信息
     * 
     * @param villageLine
     * @return void
     * @author zhujiao
     * @date 2017年7月28日 上午9:48:57
     */
    @Transactional(readOnly = false)
    public void updateRecomSpecialDetail(VillageLinerecomspecial recomSpecial) {
        if (recomSpecial.getRecomSpecialDetailList() != null) {
            for (int i = 0; i < recomSpecial.getRecomSpecialDetailList().size(); i++) {
                VillageLinerecomspecialdetail recomSpecialDetail = new VillageLinerecomspecialdetail();
                recomSpecialDetail = recomSpecial.getRecomSpecialDetailList().get(i);
                if ("0".equals(recomSpecialDetail.getDelFlag())) {
                    recomSpecialDetail.setSortNum(i + "");
                    recomSpecialDetail.setVillageLineRecomSpecialId(recomSpecial.getId());
                    recomspecialdetailDao.update(recomSpecialDetail);

                }
            }
        }
    }

    /**
     * 通过专题推荐的ID删除专题推荐明细
     * 
     * @param villageLineId
     * @return void
     * @author zhujiao
     * @date 2017年7月28日 上午9:51:15
     */
    @Transactional(readOnly = false)
    public void deleteByLine(String specialId) {
        recomspecialdetailDao.deleteBySpecialId(specialId);
    };

}