package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;

import com.its.modules.app.dao.RoomCertifyDao;
import com.its.modules.app.entity.RoomCertify;

/**
 * 房间认证Service
 * 
 * @author like
 * 
 * @version 2017-07-21
 */
@Service
@Transactional(readOnly = true)
public class RoomCertifyService extends CrudService<RoomCertifyDao, RoomCertify> {

	public RoomCertify get(String id) {
		return super.get(id);
	}

	public List<RoomCertify> findList(RoomCertify roomCertify) {
		return super.findList(roomCertify);
	}

	public Page<RoomCertify> findPage(Page<RoomCertify> page, RoomCertify roomCertify) {
		return super.findPage(page, roomCertify);
	}

	@Transactional(readOnly = false)
	public void save(RoomCertify roomCertify) {
		super.save(roomCertify);
	}

	@Transactional(readOnly = false)
	public void delete(RoomCertify roomCertify) {
		super.delete(roomCertify);
	}

	/**
	 * 查询用户当前楼盘下认证的房间
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<RoomCertify>
	 */
	public List<RoomCertify> getAccountRoomCertify(String accountId, String villageInfoId) {
		return dao.getAccountRoomCertify(accountId, villageInfoId);
	}
}