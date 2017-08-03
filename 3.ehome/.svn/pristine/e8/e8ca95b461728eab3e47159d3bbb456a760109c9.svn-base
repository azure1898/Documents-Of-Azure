/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.BraceletSleepRecordDao;
import com.its.modules.app.entity.BraceletSleepRecord;

/**
 * 手环睡眠记录Service
 * 
 * @author like
 * @version 2017-07-20
 */
@Service
@Transactional(readOnly = true)
public class BraceletSleepRecordService extends CrudService<BraceletSleepRecordDao, BraceletSleepRecord> {

	public BraceletSleepRecord get(String id) {
		return super.get(id);
	}

	public List<BraceletSleepRecord> findList(BraceletSleepRecord braceletSleepRecord) {
		return super.findList(braceletSleepRecord);
	}

	public Page<BraceletSleepRecord> findPage(Page<BraceletSleepRecord> page, BraceletSleepRecord braceletSleepRecord) {
		return super.findPage(page, braceletSleepRecord);
	}

	@Transactional(readOnly = false)
	public void save(BraceletSleepRecord braceletSleepRecord) {
		super.save(braceletSleepRecord);
	}

	@Transactional(readOnly = false)
	public void delete(BraceletSleepRecord braceletSleepRecord) {
		super.delete(braceletSleepRecord);
	}

	/**
	 * 获取用户某一天的运动记录
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageinfoId
	 *            楼盘ID
	 * @param braceletId
	 *            手环ID
	 * @param recordDate
	 *            日期
	 * @return
	 */
	public BraceletSleepRecord getAccountDayRecord(String accountId, String villageinfoId, String braceletId, Date recordDate) {
		return dao.getAccountDayRecord(accountId, villageinfoId, braceletId, recordDate);
	}

	/**
	 * 获取周运动数据
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageinfoId
	 *            楼盘ID
	 * @param braceletId
	 *            手环ID
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public List<BraceletSleepRecord> getWeekSleepRecord(String accountId, String villageinfoId, String braceletId, Date startDate, Date endDate) {
		return dao.getWeekSleepRecord(accountId, villageinfoId, braceletId, startDate, endDate);
	}
}