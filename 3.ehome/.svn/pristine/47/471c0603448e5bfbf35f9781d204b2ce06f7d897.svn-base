/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.entity.LogPropertyBalance;
import com.its.modules.balance.dao.LogPropertyBalanceDao;

/**
 * 物业结算操作日志Service
 * @author Liuqi
 * @version 2017-08-01
 */
@Service
@Transactional(readOnly = true)
public class LogPropertyBalanceService extends CrudService<LogPropertyBalanceDao, LogPropertyBalance> {

	public LogPropertyBalance get(String id) {
		return super.get(id);
	}
	
	public List<LogPropertyBalance> findList(LogPropertyBalance logPropertyBalance) {
		return super.findList(logPropertyBalance);
	}
	
	public Page<LogPropertyBalance> findPage(Page<LogPropertyBalance> page, LogPropertyBalance logPropertyBalance) {
		return super.findPage(page, logPropertyBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(LogPropertyBalance logPropertyBalance) {
		super.save(logPropertyBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(LogPropertyBalance logPropertyBalance) {
		super.delete(logPropertyBalance);
	}
	
}