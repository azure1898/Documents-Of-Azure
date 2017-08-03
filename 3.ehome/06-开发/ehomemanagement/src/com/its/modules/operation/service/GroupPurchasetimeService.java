/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.operation.entity.GroupPurchase;
import com.its.modules.operation.entity.GroupPurchasetime;
import com.its.modules.operation.dao.GroupPurchasetimeDao;

/**
 * 团购管理Service
 * @author caojing
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class GroupPurchasetimeService extends CrudService<GroupPurchasetimeDao, GroupPurchasetime> {
	
	@Autowired
	private GroupPurchasetimeDao groupPurchasetimeDao;
	
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
	 * 保存团购时间信息
	 * @param groupPurchasetime
	 * @throws ParseException 
	 */
	@Transactional(readOnly = false)
	public int saveGroupPurchasetime(GroupPurchase groupPurchase) throws ParseException {
		//保存结果
		int result = 0;
		//团购开始时间
		String[] startTimes = groupPurchase.getStartTimes().split(",");
		//团购结束时间
		String[] endTimes = groupPurchase.getEndTimes().split(",");		
		//库存量
		String[] stockNums = groupPurchase.getStockNums().split(",");
		
		GroupPurchasetime groupPurchasetime = new GroupPurchasetime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");  
		
		//团购时间信息数据
		for (int i = 0; i < startTimes.length; i++) {
			groupPurchasetime.setGroupPurchaseId(groupPurchase.getId());
			groupPurchasetime.setStartTime(sdf.parse(startTimes[i]));
			groupPurchasetime.setEndTime(sdf.parse(endTimes[i]));
			groupPurchasetime.setStockNum(stockNums[i]);
			groupPurchasetime.setSaleNum("0");
			groupPurchasetime.preInsert();
			result = groupPurchasetimeDao.insert(groupPurchasetime);
		}	
		
		return result;
	}
	
	/**
	 * 团购管理：团购活动删除用，取团购时间条数
	 * @param groupPurchase
	 */
	public int countTime(GroupPurchasetime groupPurchasetime){
		return groupPurchasetimeDao.countTime(groupPurchasetime);
	}
	
	/**
	 * 团购管理：删除团购信息
	 * @param groupPurchase
	 */
	@Transactional(readOnly = false)
	public void deleteGroupPurchasetime(GroupPurchasetime groupPurchasetime){
		groupPurchasetimeDao.deleteGroupPurchasetime(groupPurchasetime);
	}
	
}