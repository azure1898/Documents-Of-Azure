/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.account.dao.RoomCertifyDao;
import com.its.modules.account.entity.RoomCertify;

/**
 * 房间认证Service
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class RoomCertifyService extends CrudService<RoomCertifyDao, RoomCertify> {
	
	@Autowired
	private RoomCertifyDao roomCertifyDao;
	
	public RoomCertify get(String id) {
		return super.get(id);
	}
	
	public List<RoomCertify> findList(RoomCertify roomCertify) {
		return super.findList(roomCertify);
	}
	
	public Page<RoomCertify> findPage(Page<RoomCertify> page, RoomCertify roomCertify) {
		return super.findPage(page, roomCertify);
	}
	
	/**
	 * 依据房间id，查询房间信息
	 * @param buildingList
	 * @return
	 */
	public int countRoom(RoomCertify roomCertify){
		return roomCertifyDao.countRoom(roomCertify);
	}
	
	/**
	 * 在本系统房间信息存在，把物管的房间编号，楼层Id更新过来
	 * @param roomCertify
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateRoom(RoomCertify roomCertify){
		return roomCertifyDao.updateRoom(roomCertify);
	}

	/**
	 * 在本系统房间信息不存在，把物管的房间信息插入本系统
	 */
	@Transactional(readOnly = false)
	public int saveRoom(RoomCertify roomCertify) {
		return roomCertifyDao.insert(roomCertify);
	}
	
//	@Transactional(readOnly = false)
//	public void save(RoomCertify roomCertify) {
//		super.save(roomCertify);
//	}
//	
//	@Transactional(readOnly = false)
//	public void frozen(RoomCertify roomCertify) {
//		super.delete(roomCertify);
//	}
	
}