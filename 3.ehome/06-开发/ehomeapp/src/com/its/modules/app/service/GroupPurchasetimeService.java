package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.GroupPurchasetime;
import com.its.modules.app.dao.GroupPurchasetimeDao;

/**
 * 团购管理子表－团购时间Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class GroupPurchasetimeService extends CrudService<GroupPurchasetimeDao, GroupPurchasetime> {

	public GroupPurchasetime get(String id) {
		return super.get(id);
	}

	public List<GroupPurchasetime> findList(GroupPurchasetime groupPurchasetime) {
		return super.findList(groupPurchasetime);
	}

	public Page<GroupPurchasetime> findPage(Page<GroupPurchasetime> page, GroupPurchasetime groupPurchasetime) {
		return super.findPage(page, groupPurchasetime);
	}

	@Transactional(readOnly = false)
	public void save(GroupPurchasetime groupPurchasetime) {
		super.save(groupPurchasetime);
	}

	@Transactional(readOnly = false)
	public void delete(GroupPurchasetime groupPurchasetime) {
		super.delete(groupPurchasetime);
	}

	/**
	 * 更新团购子表已售数量
	 * 
	 * @param count
	 *            变动的数值
	 * @param groupPurchaseTimeId
	 *            团购子表ID
	 * @return 操作的行数
	 */
	public int updateSaleNum(int count, String groupPurchaseTimeId) {
		return dao.updateSaleNum(count, groupPurchaseTimeId);
	}

}