/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.village.dao.VillageInfoDao;
import com.its.modules.village.entity.VillageInfo;

/**
 * 楼盘信息Service
 * 
 * @author zhujiao
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class VillageInfoService extends CrudService<VillageInfoDao, VillageInfo> {

	@Autowired
	private VillageInfoDao villageInfoDao;

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

	@Transactional(readOnly = false)
	public List<VillageInfo> getVillageTree(VillageInfo villageInfo) {
		List<VillageInfo> list = new ArrayList<>();
		list = villageInfoDao.findPro(villageInfo);
		String villageInfoIdsStr = UserUtils.getUser().getVillageInfoIds();
		for (int i = 0; i < list.size(); i++) {
			List<VillageInfo> list1 = new ArrayList<>();
			list1 = villageInfoDao.findCity(list.get(i));
			list.get(i).setChildren(list1);
			for (int j = 0; j < list1.size(); j++) {
				List<VillageInfo> list2 = new ArrayList<>();
				VillageInfo newModel=new VillageInfo();
				newModel=list1.get(j);
				newModel.setVillageLineId(villageInfo.getVillageLineId());
				newModel.setVillageIds(villageInfoIdsStr);
				list2 = villageInfoDao.findVillage(newModel);
				list1.get(j).setChildren(list2);
			}
		}
		return list;

	}
}