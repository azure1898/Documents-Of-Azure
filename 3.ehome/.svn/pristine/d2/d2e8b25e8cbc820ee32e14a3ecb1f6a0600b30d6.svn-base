package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.Address;

/**
 * 收货地址DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-06
 */
@MyBatisDao
public interface AddressDao extends CrudDao<Address> {

	/**
	 * 获取某用户某楼盘下的默认地址
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return Address
	 */
	public Address getDefaultAddress(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);

	/**
	 * 获取某用户某楼盘下的地址列表
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Address>
	 */
	public List<Address> getAddressListByAccountIdAndVillageInfoId(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);

	/**
	 * 判断某用户某楼盘下的某地址是否存在
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param addressId
	 *            地址ID
	 * @return 存在返回地址信息，不存在返回NULL
	 */
	public Address judgeAddressEnabled(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("addressId") String addressId);
}