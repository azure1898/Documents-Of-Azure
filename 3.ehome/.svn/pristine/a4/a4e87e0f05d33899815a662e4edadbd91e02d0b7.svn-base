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
import com.its.modules.app.dao.BraceletExerciseRecordDao;
import com.its.modules.app.entity.BraceletExerciseRecord;

/**
 * 每日运动记录Service
 * 
 * @author like
 * @version 2017-07-20
 */
@Service
@Transactional(readOnly = true)
public class BraceletExerciseRecordService extends CrudService<BraceletExerciseRecordDao, BraceletExerciseRecord> {

	public BraceletExerciseRecord get(String id) {
		return super.get(id);
	}

	public List<BraceletExerciseRecord> findList(BraceletExerciseRecord braceletExerciseRecord) {
		return super.findList(braceletExerciseRecord);
	}

	public Page<BraceletExerciseRecord> findPage(Page<BraceletExerciseRecord> page, BraceletExerciseRecord braceletExerciseRecord) {
		return super.findPage(page, braceletExerciseRecord);
	}

	@Transactional(readOnly = false)
	public void save(BraceletExerciseRecord braceletExerciseRecord) {
		super.save(braceletExerciseRecord);
	}
	
	@Transactional(readOnly = false)
	public int update(BraceletExerciseRecord braceletExerciseRecord) {
		return dao.update(braceletExerciseRecord);
	}

	@Transactional(readOnly = false)
	public void delete(BraceletExerciseRecord braceletExerciseRecord) {
		super.delete(braceletExerciseRecord);
	}

	/**
	 * 根据手环ID获取用户某一时段的运动记录
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
	public  List<BraceletExerciseRecord> getAccountPeriodExerciseByBraceletId(String accountId, String villageinfoId, String braceletId, Date startDate, Date endDate) {
		return dao.getAccountPeriodExerciseByBraceletId(accountId, villageinfoId, braceletId,startDate,endDate);
	}
	
	
	/**
	 * 根据mac获取用户某一时段的运动记录
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
	public  List<BraceletExerciseRecord> getAccountPeriodExerciseByMac(String accountId, String villageinfoId, String braceletMac, Date startDate, Date endDate) {
		return dao.getAccountPeriodExerciseByMac(accountId, villageinfoId, braceletMac,startDate,endDate);
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
	public BraceletExerciseRecord getSpeAccountDateExercise(String accountId, String villageinfoId, String braceletId, Date recordDate) {
		return dao.getSpeAccountDateExercise(accountId, villageinfoId, braceletId, recordDate);
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
	public List<BraceletExerciseRecord> getWeekExercise(String accountId, String villageinfoId, String braceletId, Date startDate, Date endDate) {
		return dao.getWeekExercise(accountId, villageinfoId, braceletId, startDate, endDate);
	}
}