/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.SysArea;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.dao.VillageInfoDao;

/**
 * 楼盘信息Service
 * @author like
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class VillageInfoService extends CrudService<VillageInfoDao, VillageInfo> {

	public VillageInfo get(String id) {
		return super.get(id);
	}
	
	public List<VillageInfo> findList(VillageInfo villageInfo) {
		return super.findList(villageInfo);
	}
	
	public Page<VillageInfo> findPage(Page<VillageInfo> page, VillageInfo villageInfo) {
		return super.findPage(page, villageInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(VillageInfo villageInfo) {
		super.save(villageInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(VillageInfo villageInfo) {
		super.delete(villageInfo);
	}
	//-----------------------------分割线----------------------------
	
	public List<SysArea> findCities(){
		return dao.findCities();
	}
	
	public List<VillageInfo> findCityVillageList(String addrCity){
		return dao.findCityVillageList(addrCity);
	}
	
	public String getAccountCityID(String accountID){
		return dao.getAccountCityID(accountID);
	}
}