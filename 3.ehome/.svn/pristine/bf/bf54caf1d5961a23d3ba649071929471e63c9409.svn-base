package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.BuildingInfoBean;
import com.its.modules.app.entity.BuildingInfo;

/**
 * 楼栋信息DAO接口
 * 
 * @author sushipeng
 * @version 2017-07-21
 */
@MyBatisDao
public interface BuildingInfoDao extends CrudDao<BuildingInfo> {

	/**
	 * 获取某楼盘下的楼栋和房间信息
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<BuildingInfoBean>
	 */
	public List<BuildingInfoBean> getBuildingAndRoomList(String villageInfoId);
}