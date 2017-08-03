/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.company.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.company.entity.CompanyInfo;

/**
 * 开发商公司信息DAO接口
 * @author ChenXiangyu
 * @version 2017-07-11
 */
@MyBatisDao
public interface CompanyInfoDao extends CrudDao<CompanyInfo> {
	
}