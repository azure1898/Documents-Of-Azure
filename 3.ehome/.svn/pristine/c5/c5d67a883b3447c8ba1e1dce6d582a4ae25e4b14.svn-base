package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.VisitorInviteDao;
import com.its.modules.app.entity.VisitorInvite;

/**
 * 访客邀请Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class VisitorInviteService extends CrudService<VisitorInviteDao, VisitorInvite> {

	public VisitorInvite get(String id) {
		return super.get(id);
	}

	public List<VisitorInvite> findList(VisitorInvite visitorInvite) {
		return super.findList(visitorInvite);
	}

	public Page<VisitorInvite> findPage(Page<VisitorInvite> page, VisitorInvite visitorInvite) {
		return super.findPage(page, visitorInvite);
	}

	@Transactional(readOnly = false)
	public void save(VisitorInvite visitorInvite) {
		super.save(visitorInvite);
	}

	@Transactional(readOnly = false)
	public void update(VisitorInvite visitorInvite) {
		dao.update(visitorInvite);
	}

	@Transactional(readOnly = false)
	public void delete(VisitorInvite visitorInvite) {
		super.delete(visitorInvite);
	}

	/**
	 * 获取某用户某楼盘下的访客列表（正常）
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<VisitorInvite>
	 */
	public List<VisitorInvite> getNormalVisitorInviteList(String accountId, String villageInfoId) {
		return dao.getNormalVisitorInviteList(accountId, villageInfoId);
	}

	/**
	 * 获取某用户某楼盘下的访客列表（失效）
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param count
	 *            数量
	 * @return List<VisitorInvite>
	 */
	public List<VisitorInvite> getInvalidVisitorInviteList(String accountId, String villageInfoId, int count) {
		return dao.getInvalidVisitorInviteList(accountId, villageInfoId, count);
	}

	/**
	 * 判断某用户某楼盘下是否存在某访客邀请
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param visitorInviteId
	 *            访客邀请ID
	 * @return VisitorInvite
	 */
	public VisitorInvite judgeVisitorInvite(String accountId, String villageInfoId, String visitorInviteId) {
		return dao.judgeVisitorInvite(accountId, villageInfoId, visitorInviteId);
	}
}