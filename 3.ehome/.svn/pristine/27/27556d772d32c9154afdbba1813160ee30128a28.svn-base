/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.setup.entity.BusinessInfo;

/**
 * 商家信息管理DAO接口
 * 
 * @author zhujiao
 * @version 2017-06-26
 */
@MyBatisDao
public interface BusinessInfoDao extends CrudDao<BusinessInfo> {
    /**
     * 验证商家名字是否有效
     * 
     * @author zhujiao
     * @date 2017年7月6日 下午3:52:00
     * @return AdverManage
     */
    public BusinessInfo getModelByName(BusinessInfo businessInfo);

    /**
     * 插入商家分类关联数据 add by zhujiao
     * 
     * @param user
     * @return
     */
    public int insertInfoCategory(BusinessInfo businessInfo);

    /**
     * 管理商家银行账户信息
     * 
     * @author 朱姣
     * @param businessInfo
     * @return
     */
    public int editBank(BusinessInfo businessInfo);

    /**
     * 修改商家营业状态
     * @param businessInfo
     * @return
     * @return int
     * @author zhujiao   
     * @date 2017年7月13日 下午7:07:26
     */
    public int updateState(BusinessInfo businessInfo);

    /**
     * 插入商家楼盘关联数据
     * 
     * @author zhujiao
     * @date 2017年7月5日 下午7:26:34
     * @return int
     */
    public int insertInfoServiceScope(BusinessInfo businessInfo);

    /**
     * 删除商家楼盘关联数据
     * 
     * @author zhujiao
     * @date 2017年7月5日 下午7:26:34
     * @return int
     */
    public int deleteInfoServiceScope(BusinessInfo businessInfo);
    
    /**
     * 商家资料修改
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月12日 下午6:10:40
     */
    public void updateBaseInfo(BusinessInfo businessInfo);

    /**
     * 商家营业设置
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月12日 下午6:10:48
     */
    public void updateBusiness(BusinessInfo businessInfo);


    /**
     * 获取商家信息
     * @param businessinfoId
     * @return
     */
    BusinessInfo getJoinArea(String businessinfoId);
    
    
    /**
     * 修改商家提醒状态
     * @param businessInfo
     * @return
     * @return int
     * @author zhujiao   
     * @date 2017年7月18日 下午6:36:41
     */
    public int updateSetting(BusinessInfo businessInfo);
}