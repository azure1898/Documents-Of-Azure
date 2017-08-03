package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.BuildingInfo;
import com.its.modules.app.bean.BuildingInfoBean;
import com.its.modules.app.dao.BuildingInfoDao;

/**
 * 楼栋信息Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-21
 */
@Service
@Transactional(readOnly = true)
public class BuildingInfoService extends CrudService<BuildingInfoDao, BuildingInfo> {

	public BuildingInfo get(String id) {
		return super.get(id);
	}

	public List<BuildingInfo> findList(BuildingInfo buildingInfo) {
		return super.findList(buildingInfo);
	}

	public Page<BuildingInfo> findPage(Page<BuildingInfo> page, BuildingInfo buildingInfo) {
		return super.findPage(page, buildingInfo);
	}

	@Transactional(readOnly = false)
	public void save(BuildingInfo buildingInfo) {
		super.save(buildingInfo);
	}

	@Transactional(readOnly = false)
	public void delete(BuildingInfo buildingInfo) {
		super.delete(buildingInfo);
	}

	/**
	 * 获取某楼盘下的楼栋和房间信息
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<BuildingInfoBean>
	 */
	public List<BuildingInfoBean> getBuildingAndRoomList(String villageInfoId) {
		return dao.getBuildingAndRoomList(villageInfoId);
	}
}