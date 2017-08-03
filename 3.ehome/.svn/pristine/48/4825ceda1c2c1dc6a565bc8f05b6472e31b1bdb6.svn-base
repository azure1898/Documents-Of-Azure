/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.village.entity.VillageLine;

/**
 * 楼盘信息产品线DAO接口
 * 
 * @author ChenXiangyu
 * @version 2017-07-11
 */
@MyBatisDao
public interface VillageLineDao extends CrudDao<VillageLine> {

    /**
     * 根据楼盘ID获取所有楼盘产品线记录
     * 
     * @param villageLine
     * @return 所有楼盘产品线记录（包括被逻辑删除的）
     */
    public List<VillageLine> findListByVillageId(VillageLine villageLine);

    /**
     * 获取所有已设置模块的楼盘产品线信息
     * 
     * @return
     */
    public List<VillageLine> findSettedList();

    /**
     * 获取楼盘线关联的的所有楼盘信息列表
     * 
     * @return
     * @return List<VillageLine>
     * @author zhujiao
     * @date 2017年7月24日 下午8:47:33
     */
    public List<VillageLine> findLineList(VillageLine villageLine);
    /**
     * 设置模块
     * @param villageLine
     * @return
     * @return List<VillageLine>
     * @author zhujiao   
     * @date 2017年7月25日 上午10:33:50
     */
    public void setModule(VillageLine villageLine);
    /**
     * 批量设置模块（根据楼盘信息设置模块数据）
     * @param villageLine
     * @return
     * @return List<VillageLine>
     * @author zhujiao   
     * @date 2017年7月25日 上午10:33:50
     */
    public void batchSetModule(VillageLine villageLine);
    /**
     * 推荐设置 - 设置首页推荐
     * @param villageLine
     * @return void
     * @author zhujiao   
     * @date 2017年7月26日 上午11:20:02
     */
    public void updateMaintRecomModule(VillageLine villageLine);
    /**
     * 推荐设置 - 社区首页推荐
     * @param villageLine
     * @return void
     * @author zhujiao   
     * @date 2017年8月2日 下午7:02:30
     */
    public void updateCommunityRecomModule(VillageLine villageLine);
    /**
     * 推荐设置 - 设置优家推荐（生活）
     * @param villageLine
     * @return void
     * @author zhujiao   
     * @date 2017年7月26日 上午11:20:02
     */
    public void updateLifeRecomModule(VillageLine villageLine);
}