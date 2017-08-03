package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.Address;
import com.its.modules.app.dao.AddressDao;

/**
 * 收货地址Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class AddressService extends CrudService<AddressDao, Address> {

	public Address get(String id) {
		return super.get(id);
	}

	public List<Address> findList(Address address) {
		return super.findList(address);
	}

	public Page<Address> findPage(Page<Address> page, Address address) {
		return super.findPage(page, address);
	}

	@Transactional(readOnly = false)
	public void save(Address address) {
		super.save(address);
	}

	@Transactional(readOnly = false)
	public void update(Address address) {
		dao.update(address);
	}

	@Transactional(readOnly = false)
	public void delete(Address address) {
		super.delete(address);
	}

	/**
	 * 获取某用户某楼盘下的默认地址
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return Address
	 */
	public Address getDefaultAddress(String accountId, String villageInfoId) {
		return dao.getDefaultAddress(accountId, villageInfoId);
	}

	/**
	 * 获取某用户某楼盘下的地址列表
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Address>
	 */
	public List<Address> getAddressListByAccountIdAndVillageInfoId(String accountId, String villageInfoId) {
		return dao.getAddressListByAccountIdAndVillageInfoId(accountId, villageInfoId);
	}

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
	public Address judgeAddressEnabled(String accountId, String villageInfoId, String addressId) {
		return dao.judgeAddressEnabled(accountId, villageInfoId, addressId);
	}
}