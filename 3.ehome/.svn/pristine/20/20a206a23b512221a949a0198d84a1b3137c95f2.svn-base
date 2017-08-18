/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.service;

import java.util.*;

import com.its.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.sys.entity.SysCodeMax;
import com.its.modules.sys.dao.SysCodeMaxDao;

/**
 * 最大编码表Service
 * @author xzc
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class SysCodeMaxService extends CrudService<SysCodeMaxDao, SysCodeMax> {

	public SysCodeMax get(String id) {
		return super.get(id);
	}
	
	public List<SysCodeMax> findList(SysCodeMax sysCodeMax) {
		return super.findList(sysCodeMax);
	}
	
	public Page<SysCodeMax> findPage(Page<SysCodeMax> page, SysCodeMax sysCodeMax) {
		return super.findPage(page, sysCodeMax);
	}
	
	@Transactional(readOnly = false)
	public void save(SysCodeMax sysCodeMax) {
		super.save(sysCodeMax);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysCodeMax sysCodeMax) {
		super.delete(sysCodeMax);
	}

	/**
	 * 获取流水号
	 * @param id_in 流水类型编号（001：订单类型，002：模块类型）
	 * @param small_in 最小值
	 * @param is_day_update_in 是否每天初始化（'true'：是 ，'false'：否）
	 */
	@Transactional(readOnly = false)
	public Integer getCodeNo(String id_in,int small_in,String is_day_update_in) {
		Map<String ,Object> map=new HashMap<String ,Object>();
		Integer result = 0;
		map.put("id_in",id_in);
		map.put("small_in",small_in);
		map.put("is_day_update_in",is_day_update_in);
		map.put("code_value_out",result);
		 dao.getCodeNo(map);
		result= Integer.parseInt(map.get("code_value_out").toString());
		return result;
	}

	/**
	 * 获取订单编号
	 * @param villageInfoId 楼盘(商家后台场地预约时候为城市编号)
	 * @param moduleManageId 模块
	 * @return
	 */
	@Transactional(readOnly = false)
	public String getOrdNo(String villageInfoId,String moduleManageId){
		Date date=new Date();
		String year=DateUtils.getYear();
		String month=DateUtils.getMonth();
		String day=DateUtils.getDay();
		String no=String.format("%04d", this.getCodeNo( "001", 1, "true"));
		return year.substring(2,4)+month+day+no;
	}
	
	/**
	 * 获取优惠券号
	 * 
	 * @return 优惠券号
	 */
	@Transactional(readOnly = false)
	public String getDiscountNum() {
		String year = DateUtils.getYear();
		String month = DateUtils.getMonth();
		String day = DateUtils.getDay();
		String no = String.format("%06d", this.getCodeNo("007", 1, "true"));
		return year.substring(2, 4) + month + day + no;
	}
	
}