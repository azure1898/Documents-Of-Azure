/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.service.dao;

import java.util.List;
import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.service.entity.ServiceInfo;

/**
 * 服务管理DAO接口
 * @author admin
 * @version 2017-07-11
 */
@MyBatisDao
public interface ServiceInfoDao extends CrudDao<ServiceInfo> {

	/**
	 * 查询服务ID对应的库存为0的服务
	 * @param serviceId 服务ID
	 * @return
	 */
	public ServiceInfo selectZeroStockService(String serviceId);

	/**
	 * 复数上架
	 * @param serviceId 服务ID
	 * @return
	 */
	public int groundingByServiceId(String serviceId);

	/**
	 * 复数下架
	 * @param serviceId 服务ID
	 * @return
	 */
	public int undercarriageByServiceId(String serviceId);

	/**
	 * 图片地址更新
	 * @param imgUrlUpdateMap 更新条件
	 */
	public void imgNameUpdate(Map<String, String> imgUrlUpdateMap);

	/**
	 * 根据服务ID取得服务信息并将该行锁定
	 * @param serviceIds 服务ID
	 * @return
	 */
	public List<ServiceInfo> findServiceInfoListForUpdate(List<String> serviceIds);

	/**
	 *服务总数量
	 * @param serviceInfo_where
	 * @return
	 */
    Integer findAllListCount(ServiceInfo serviceInfo_where);
}