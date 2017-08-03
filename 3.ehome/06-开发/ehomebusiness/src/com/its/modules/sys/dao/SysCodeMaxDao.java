/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.sys.entity.SysCodeMax;

import java.util.Map;

/**
 * 最大编码表DAO接口
 * @author xzc
 * @version 2017-07-11
 */
@MyBatisDao
public interface SysCodeMaxDao extends CrudDao<SysCodeMax> {

    /**
     * 获取流水号
     * @param map
     */
    void getCodeNo(Map<String ,Object> map);

    /**
     * 获取模块编号
     * @return
     */
    String getModuleManageId(String businessInfoId);


}