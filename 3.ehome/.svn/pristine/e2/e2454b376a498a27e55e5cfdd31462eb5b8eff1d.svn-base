package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.VillageLineRecomBusiTypeBean;
import com.its.modules.app.dao.VillageLineRecomBusiTypeDao;
import com.its.modules.app.entity.VillageLineRecomBusiType;

/**
 * 楼盘产品线推荐商家模式Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class VillageLineRecomBusiTypeService extends CrudService<VillageLineRecomBusiTypeDao, VillageLineRecomBusiType> {

	public VillageLineRecomBusiType get(String id) {
		return super.get(id);
	}

	public List<VillageLineRecomBusiType> findList(VillageLineRecomBusiType villageLineRecomBusiType) {
		return super.findList(villageLineRecomBusiType);
	}

	public Page<VillageLineRecomBusiType> findPage(Page<VillageLineRecomBusiType> page, VillageLineRecomBusiType villageLineRecomBusiType) {
		return super.findPage(page, villageLineRecomBusiType);
	}

	@Transactional(readOnly = false)
	public void save(VillageLineRecomBusiType villageLineRecomBusiType) {
		super.save(villageLineRecomBusiType);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLineRecomBusiType villageLineRecomBusiType) {
		super.delete(villageLineRecomBusiType);
	}

	/**
	 * 获取某楼盘下某推荐位置的商家列表
	 * 
	 * @param recommendPosition
	 *            推荐位置：00 首页推荐 10 社区推荐 20 生活商家推荐2
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Map<String, Object>>
	 */
	public List<VillageLineRecomBusiTypeBean> getRecommendBusinessList(String recommendPosition, String villageInfoId) {
		return dao.getRecommendBusinessList(recommendPosition, villageInfoId);
	}
}