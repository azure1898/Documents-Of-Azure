/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.business.entity.BusinessInfo;

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
     * 插入商家分类关联数据 add by zhujiao
     * 
     * @param user
     * @return
     */
    public int deleteInfoCategory(BusinessInfo businessInfo);

    /**
     * 管理商家银行账户信息
     * 
     * @author 朱姣
     * @param businessInfo
     * @return
     */
    public int editBank(BusinessInfo businessInfo);

    /**
     * 冻结商家
     * 
     * @author zhujiao
     * @param user
     * @return
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
     * 根据模块获取商家列表信息  
     * @param moduleId
     * @return
     * @return List<BusinessInfo>
     * @author zhujiao   
     * @date 2017年7月27日 下午2:00:49
     */
    public List<BusinessInfo> getBusListByModule(String moduleId);
}