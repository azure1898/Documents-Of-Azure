/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.adver.entity.AdverManage;
import com.its.modules.module.entity.VillageLinerecommodule;
import com.its.modules.module.entity.VillageLinerecomspecial;
import com.its.modules.module.service.VillageLinerecommoduleService;
import com.its.modules.module.service.VillageLinerecomspecialService;
import com.its.modules.village.dao.VillageLineDao;
import com.its.modules.village.entity.VillageLine;

/**
 * 楼盘信息产品线Service
 * 
 * @author ChenXiangyu
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class VillageLineService extends CrudService<VillageLineDao, VillageLine> {

    @Autowired
    private VillageLineDao villageLineDao;
    @Autowired
    private VillageLinerecommoduleService recommoduleService;
    @Autowired
    private VillageLinerecomspecialService recomspecialService;

    public VillageLine get(String id) {
        return super.get(id);
    }

    public List<VillageLine> findList(VillageLine villageLine) {
        return super.findList(villageLine);
    }

    public Page<VillageLine> findPage(Page<VillageLine> page, VillageLine villageLine) {
        return super.findPage(page, villageLine);
    }

    @Transactional(readOnly = false)
    public void save(VillageLine villageLine) {
        super.save(villageLine);
    }

    @Transactional(readOnly = false)
    public void delete(VillageLine villageLine) {
        super.delete(villageLine);
    }

    /**
     * 根据楼盘ID获取所有楼盘产品线记录
     * 
     * @param villageLine
     * @return 所有楼盘产品线记录（包括被逻辑删除的）
     */
    public List<VillageLine> findListByVillageId(String villageInfoId) {
        VillageLine villageLine = new VillageLine();
        villageLine.setVillageInfoId(villageInfoId);
        List<VillageLine> villageLineList = villageLineDao.findListByVillageId(villageLine);
        return (villageLineList != null) ? villageLineList : new ArrayList<VillageLine>();
    }

    /**
     * 获取所有已设置模块的楼盘产品线信息
     * 
     * @return
     */
    public List<VillageLine> findSettedList() {
        List<VillageLine> villageLineList = villageLineDao.findSettedList();
        return (villageLineList != null) ? villageLineList : new ArrayList<VillageLine>();
    }

    /**
     * 获取楼盘线关联的的所有楼盘信息列表
     * 
     * @param page
     * @param entity
     * @return
     * @return Page<VillageLine>
     * @author zhujiao
     * @date 2017年7月24日 下午8:53:37
     */
    public Page<VillageLine> findLineList(Page<VillageLine> page, VillageLine entity) {
        entity.setPage(page);
        page.setList(villageLineDao.findLineList(entity));
        return page;
    }

    /**
     * 设置模块
     * 
     * @param page
     * @param entity
     * @return
     * @return Page<VillageLine>
     * @author zhujiao
     * @date 2017年7月25日 上午10:32:41
     */
    @Transactional(readOnly = false)
    public void setModule(VillageLine villageLine) {
        villageLineDao.setModule(villageLine);
    }

    /**
     * 批量设置模块（根据楼盘信息设置模块数据）
     * 
     * @param page
     * @param entity
     * @return
     * @return Page<VillageLine>
     * @author zhujiao
     * @date 2017年7月25日 上午10:32:41
     */
    @Transactional(readOnly = false)
    public void batchSetModule(VillageLine villageLine) {
        if (villageLine.getVillageIdList() != null) {
            for (int i = 0; i < villageLine.getVillageIdList().length; i++) {
                villageLine.setVillageInfoId(villageLine.getVillageIdList()[i]);
                villageLineDao.batchSetModule(villageLine);
            }
        }
    }

    /**
     * 推荐设置-设置首页推荐
     * 
     * @param villageLine
     * @return void
     * @author zhujiao
     * @date 2017年7月26日 上午11:19:11
     */
    @Transactional(readOnly = false)
    public void updateMaintRecomModule(VillageLine villageLine) {
        villageLineDao.updateMaintRecomModule(villageLine);
    }
    /**
     * 推荐设置-设置社区推荐
     * @param villageLine
     * @return void
     * @author zhujiao   
     * @date 2017年8月2日 下午7:02:48
     */
    @Transactional(readOnly = false)
    public void updateCommunityRecomModule(VillageLine villageLine) {
        villageLineDao.updateCommunityRecomModule(villageLine);
    }

    /**
     * 推荐设置-设置优家推荐（模块）
     * 
     * @param villageLine
     * @return void
     * @author zhujiao
     * @date 2017年7月26日 上午11:25:46
     */
    @Transactional(readOnly = false)
    public void updateLifeRecomModule(VillageLine villageLine) {
        try {
            // 保存主表
            villageLineDao.updateLifeRecomModule(villageLine);
            // 保存-修改-删除 推荐模块数据
            recommoduleService.saveRecomModule(villageLine);
            // 保存-修改-删除 推荐专题数据
            recomspecialService.saveSpecialModule(villageLine);
        } catch (Exception e) {
            System.out.println("保存生活推荐异常：" + e.getMessage());
        }
    }

    /**
     * 获取楼盘产品线信息-
     * 
     * @param id
     * @return
     * @return VillageLine
     * @author zhujiao
     * @date 2017年7月27日 上午11:05:08
     */
    public VillageLine getModel(String id) {
        VillageLine model = villageLineDao.get(id);
        try {
            List<VillageLinerecommodule> recomModuleList = recommoduleService.getRecomModuleList(model.getId());
            model.setRecomModuleList(recomModuleList);
        } catch (Exception e) {
            System.out.println("获取推荐模块异常：" + e.getMessage());
        }
        try {
            List<VillageLinerecomspecial> recomSpecialList = recomspecialService.getRecomSpecialList(model.getId());
            model.setRecomSpecialList(recomSpecialList);
        } catch (Exception e) {
            System.out.println("获取推荐专题异常：" + e.getMessage());
        }
        return model;
    }

}