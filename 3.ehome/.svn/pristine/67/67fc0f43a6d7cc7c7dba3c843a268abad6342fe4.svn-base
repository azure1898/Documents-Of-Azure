package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.entity.VillageLine;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.dao.VillageLineDao;

/**
 * 楼盘产品线及产品线设置管理Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class VillageLineService extends CrudService<VillageLineDao, VillageLine> {
	public VillageLine get(String id) {
		return super.get(id);
	}

	public List<VillageLine> findList(VillageLine villageLine) {
		return super.findList(villageLine);
	}

	public Page<VillageLine> findPage(Page<VillageLine> page, VillageLine villageLine) {
		return super.findPage(page, villageLine);
	}

	@Transactional(readOnly = false)
	public void save(VillageLine villageLine) {
		super.save(villageLine);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLine villageLine) {
		super.delete(villageLine);
	}

	/**
	 * 获取某楼盘下已设置的业主APP产品线信息
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return VillageLine
	 */
	public VillageLine getByVillageInfoId(String villageInfoId) {
		return dao.getByVillageInfoId(villageInfoId);
	}

	/**
	 * 获取楼盘产品线主导航
	 * 
	 * @param villageLine
	 *            楼盘产品线信息
	 * @return 楼盘产品线主导航
	 */
	public List<Map<String, Object>> getMainNavigation(VillageLine villageLine) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		String mainNavigation = villageLine.getMainNavigation();
		if (StringUtils.isNotBlank(mainNavigation)) {
			String[] navigationArray = mainNavigation.replaceAll("，", ",").split(",");
			for (String navigation : navigationArray) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("navID", navigation);
				if (CommonGlobal.MAIN_NAVIGATION_MAINT.equals(navigation)) {
					data.put("navName", "首页");
				}
				if (CommonGlobal.MAIN_NAVIGATION_COMMUNITY.equals(navigation)) {
					data.put("navID", CommonGlobal.MAIN_NAVIGATION_MAINT);
					data.put("navName", "社区");
				}
				if (CommonGlobal.MAIN_NAVIGATION_LIFE.equals(navigation)) {
					data.put("navID", CommonGlobal.MAIN_NAVIGATION_MAINT);
					data.put("navName", "生活");
				}
				if (CommonGlobal.MAIN_NAVIGATION_NEIGHBOR.equals(navigation)) {
					data.put("navID", CommonGlobal.MAIN_NAVIGATION_MAINT);
					data.put("navName", "邻里圈");
				}
				datas.add(data);
			}
		}
		return datas;
	}
}