/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldInfoPrice;

import java.util.List;

/**
 * 场地预约DAO接口
 * @author xzc
 * @version 2017-06-29
 */
@MyBatisDao
public interface FieldInfoDao extends CrudDao<FieldInfo> {

    /**
     * 查询场地列表
     * @param fieldInfo
     * @return
     */
    List<FieldInfo> queryFieldPartitionPriceListByFieldInfo(FieldInfo fieldInfo);

    /**
     * 查询可以生成分段场地信息的场地
     * @return
     */
    List<FieldInfo> queryOpenList();

    /**
     * 场地个数
     * @param fieldInfo_where
     * @return
     */
    int findAllListCount(FieldInfo fieldInfo_where);
    /**
     * 约满场地个数
     * @param fieldInfo_where
     * @return
     */
    Integer findAllListCountFull(FieldInfo fieldInfo_where);
    /**
     * 通过商家ID获取场地列表（综合管理平台--广告发布）
     * @return
     * @return List<FieldInfo>
     * @author zhujiao   
     * @date 2017年8月21日 下午4:44:47
     */
    List<FieldInfo> getFieldInfoList(String businessInfoId);
}