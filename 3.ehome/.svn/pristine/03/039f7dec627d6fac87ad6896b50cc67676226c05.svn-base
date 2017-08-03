/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.oa.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.oa.entity.TestAudit;

/**
 * 审批DAO接口
 * @author thinkgem
 * @version 2014-05-16
 */
@MyBatisDao
public interface TestAuditDao extends CrudDao<TestAudit> {

	public TestAudit getByProcInsId(String procInsId);
	
	public int updateInsId(TestAudit testAudit);
	
	public int updateHrText(TestAudit testAudit);
	
	public int updateLeadText(TestAudit testAudit);
	
	public int updateMainLeadText(TestAudit testAudit);
	
}
