package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.BusinessServicetime;

/**
 * 商户服务时段DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-06
 */
@MyBatisDao
public interface BusinessServicetimeDao extends CrudDao<BusinessServicetime> {

	/**
	 * 获取某商家某时段类型下的商家服务时段
	 * 
	 * @param businessInfoId
	 *            商家ID
	 * @param timeType
	 *            时段类型
	 * @return List<BusinessServicetime>
	 */
	public List<BusinessServicetime> getByBusinessInfoID(@Param("businessInfoId") String businessInfoId, @Param("timeType") String timeType);
}