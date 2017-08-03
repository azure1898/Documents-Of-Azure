/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.dao;

import java.util.List;

import com.its.common.persistence.TreeDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * 
 * @author Jetty
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

	/**
	 * 根据城市名查询区域数据
	 * 
	 * @author zhujiao
	 * @param name
	 * @return
	 */
	public List<Area> findDistrictByCity(String name);

	/**
	 * 根据用户查找区域
	 * 
	 * @Title
	 * @Description
	 * @author zhujiao
	 * @CreateDate:2016年9月18日 下午5:07:59
	 * @param id
	 * @return
	 */
	public Area findAreaByUser(String id);

	/**
	 * @Title
	 * @Description
	 * @author zhujiao
	 * @CreateDate:2016年10月27日 上午9:32:16
	 * @param area
	 * @return
	 */
	public List<Area> findCityAreaByUserCode(Area area);

	/**
	 * @Title
	 * @Description
	 * @author zhujiao
	 * @CreateDate:2016年10月27日 上午9:32:32
	 * @param area
	 * @return
	 */
	public List<Area> findCountrAreaByUserCode(Area area);

}
