/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.DateUtils;
import com.its.modules.sys.dao.LogDao;
import com.its.modules.sys.entity.Log;

/**
 * 日志Service
 * 
 * @author Jetty
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

    public Page<Log> findPage(Page<Log> page, Log log) {

        return super.findPage(page, log);

    }

}
