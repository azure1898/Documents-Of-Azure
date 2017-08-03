/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.entity.LogBusinessBalance;
import com.its.modules.balance.dao.LogBusinessBalanceDao;

/**
 * 商户结算操作日志Service
 * @author Liuqi
 * @version 2017-08-01
 */
@Service
@Transactional(readOnly = true)
public class LogBusinessBalanceService extends CrudService<LogBusinessBalanceDao, LogBusinessBalance> {

	public LogBusinessBalance get(String id) {
		return super.get(id);
	}
	
	public List<LogBusinessBalance> findList(LogBusinessBalance logBusinessBalance) {
		return super.findList(logBusinessBalance);
	}
	
	public Page<LogBusinessBalance> findPage(Page<LogBusinessBalance> page, LogBusinessBalance logBusinessBalance) {
		return super.findPage(page, logBusinessBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(LogBusinessBalance logBusinessBalance) {
		super.save(logBusinessBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(LogBusinessBalance logBusinessBalance) {
		super.delete(logBusinessBalance);
	}
	
}