/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.BraceletSleepRecord;

/**
 * 手环睡眠记录DAO接口
 * 
 * @author like
 * @version 2017-07-20
 */
@MyBatisDao
public interface BraceletSleepRecordDao extends CrudDao<BraceletSleepRecord> {
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
	public BraceletSleepRecord getAccountDayRecord(@Param("accountId") String accountId, @Param("villageinfoId") String villageinfoId,
			@Param("braceletId") String braceletId, @Param("recordDate") Date recordDate);

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
	public List<BraceletSleepRecord> getWeekSleepRecord(@Param("accountId") String accountId, @Param("villageinfoId") String villageinfoId,
			@Param("braceletId") String braceletId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}