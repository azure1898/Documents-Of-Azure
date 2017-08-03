package com.its.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.service.TreeService;
import com.its.modules.sys.dao.AreaDao;
import com.its.modules.sys.entity.Area;
import com.its.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * 
 * @author Jetty
 * 
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll() {
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	/**
	 * 取得省市区
	 * 
	 * @param province
	 *            省ID
	 * @param city
	 *            市ID
	 * @param region
	 *            区ID
	 * @return 返回省市区
	 */
	public String getProvinceCityAndRegion(String provinceId, String cityId, String regionId) {
		// 取得省信息
		Area province = this.get(provinceId);
		// 取得市信息
		Area city = this.get(cityId);
		// 取得区信息
		Area region = this.get(regionId);
		StringBuffer address = new StringBuffer();
		if (province != null) {
			address.append(province.getName());
		}
		if (city != null) {
			address.append(city.getName());
		}
		if (region != null) {
			address.append(region.getName());
		}
		return address.toString();
	}
}