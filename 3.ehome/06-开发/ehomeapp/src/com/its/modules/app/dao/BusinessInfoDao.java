package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.BusinessSales;

/**
 * 商家信息管理DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
@MyBatisDao
public interface BusinessInfoDao extends CrudDao<BusinessInfo> {

	/**
	 * 获取商家满减优惠列表
	 * 
	 * @param businessInfoID
	 * @return
	 */
	public List<BusinessSales> getBusinessSales(String businessInfoID);

	/**
	 * 获取一楼盘下一种模式的商家集合
	 * 
	 * @param prodType
	 *            产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	 * @param villageInfoID
	 *            楼盘ID
	 * @return
	 */
	public List<BusinessInfo> getBusinessList(@Param("prodType") int prodType, @Param("villageInfoID") String villageInfoID, @Param("sort") int sort);

	/**
	 * 获取商家某自定义单位
	 * 
	 * @param businessUnit
	 *            单位ID
	 * @return 单位名称
	 */
	public String getCustomUnit(String businessUnit);

	/**
	 * 获取字典表中某单位
	 * 
	 * @param value
	 *            值
	 * @return 单位名称
	 */
	public String getDictUnit(String value);

	/**
	 * 获取某楼盘下所有正常的商家
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<BusinessInfo>
	 */
	public List<BusinessInfo> getNormalBusinessList(String villageInfoId);
}