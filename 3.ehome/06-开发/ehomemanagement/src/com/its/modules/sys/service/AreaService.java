/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	@Autowired
	private AreaDao areaDao;
	
    @Autowired
    private SysCodeMaxService sysCodeMaxService;
    /** 流水类型编号：城市类型 */
    private static final String ID_IN = "003";
    /** 初始化最小值 */
    private static final int SMALL_IN = 0;
    /** 省，市，区域初始化最小值 */
    private static final int MIN_IN = 0;
    /** 流水类型编号：城市类型 */
    private static final String PROVINCE_IN = "004";
    /** 流水类型编号：城市类型 */
    private static final String CITY_IN = "005";
    /** 流水类型编号：城市类型 */
    private static final String AREA_IN = "006";
    /** 模块ID最小位数 */
    private static final int MIN_LENGTH = 3;
    /** 省ID最小位数 */
    private static final int PROVINCE_MIN_LENGTH = 2;
    /** 城市，区域ID最小位数 */
    private static final int CITY_MIN_LENGTH = 3;

    public List<Area> findAll() {
        return UserUtils.getAreaList();
    }
    
    /**
     * 查询城市列表页
     * @param area
     * @return
     */
    public List<Area> getAreaList(Area area) {
    	List<Area> idsList = new ArrayList<Area>();
 
    	//省份、直辖市
    	if(StringUtils.isNotBlank(area.getAddrPro())){
    		area.setId(area.getAddrPro());    		
    	}
    	
    	//城市
    	if(StringUtils.isNotBlank(area.getAddrCity())){
    		area.setId(area.getAddrCity());    		
    	}
    	
    	//区域
    	if(StringUtils.isNotBlank(area.getAddrArea())){
    		area.setId(area.getAddrArea());    		
    	}
    	
    	//查询城市的父级ids(为查询查询城市列表页做准备)
    	List<Area> areaList=areaDao.getParentIdList(area);
    	//检索条件有名称，无下拉列表
    	if(StringUtils.isNotBlank(area.getName()) && StringUtils.isBlank(area.getId())){   		
    		//返回城市列表页
            return idsList=getAreaListByName(areaList);
    	}
    	
    	//检索条件无名称，有下拉列表
    	if(StringUtils.isBlank(area.getName()) && StringUtils.isNotBlank(area.getId())){
    		List<Area> areaAllList = new ArrayList<Area>();
    		//依据页面的区域，设置子系区域数据
    		for(Area areaIds:areaList){
    			if(area.getId().equals(areaIds.getId()) || area.getId().equals(areaIds.getParentId()) ||
    					areaIds.getParentIds().contains(area.getId())){
    				areaAllList.add(areaIds);
    			}
    		}
    		//依据页面的区域，设置父系区域数据
            List<Area> areaSomeList = getAreaListById(area.getId());
            areaAllList.removeAll(areaSomeList);
            areaAllList.addAll(areaSomeList);
            
    		//返回城市列表页
            return areaAllList;
    	}
    	
    	//检索条件有名称，有下拉列表
    	if(StringUtils.isNotBlank(area.getName()) && StringUtils.isNotBlank(area.getId())){   		
    		//依据查询条件输入的名称，进行父级以及本身查询
    		idsList=getAreaListByName(areaList);
            List<Area> areaAllList = new ArrayList<Area>();
            if(idsList !=null && idsList.size() > 0){
	            //依据页面的区域，设置子系区域数据
	            for(Area areaIds:idsList){
	    			if(area.getId().equals(areaIds.getId()) || area.getId().equals(areaIds.getParentId()) ||
	    					areaIds.getParentIds().contains(area.getId())){
	    				areaAllList.add(areaIds);
	    			}
	    		}
	            if(areaAllList !=null && areaAllList.size() > 0){
		            //依据页面的区域，设置父系区域数据
		            List<Area> areaSomeList = getAreaListById(area.getId());
		            areaAllList.removeAll(areaSomeList);
		            areaAllList.addAll(areaSomeList);
	            }
            }
            //返回城市列表页
            return areaAllList;
    	}
    	
    	//返回城市列表页
        return areaList;
    }

    /**
     * 依据查询条件输入的名称，进行父级以及本身查询
     * @param areaList
     * @return
     */
    public List<Area> getAreaListByName(List<Area> areaList){
    	List<Area> idsList = new ArrayList<Area>();
    	for(Area areaIds:areaList){
			Area areaId = new Area();
			List<String> areaIdList = new ArrayList<String>();
			areaIdList.add(areaIds.getId());
			areaIdList.add(areaIds.getParentId());
			if (areaIds.getParentIds() != null && !areaIds.getParentIds().isEmpty()) {
				String[] areaParentIds = areaIds.getParentIds().split(",");
				for (String areaParentId : areaParentIds) {
					if (areaParentId != null && !areaParentId.isEmpty()) {
						areaIdList.add(areaParentId);
					}
				}
			}
    		areaId.setIds(areaIdList);
    		List<Area> IdsSomeList = areaDao.getAreaList(areaId); 
    		idsList.removeAll(IdsSomeList);
    		idsList.addAll(IdsSomeList);
    	}

    	return idsList;
    }
    
    /**
     * 依据查询条件输入的区域id，进行父级查询
     * @param areaList
     * @return
     */
    public List<Area> getAreaListById(String areaId){
    	Area areaIds = new Area(); 
		List<String> areaIdList = new ArrayList<String>();
		
		//依据页面的区域，设置父系区域数据
        Area arearParentId = new Area();
        arearParentId.setId(areaId);
        Area areaParent = areaDao.getPrentListById(arearParentId);
        
		//areaIdList.add(area.getId());
		areaIdList.add(areaParent.getParentId());
		if (areaParent.getParentIds() != null && !areaParent.getParentIds().isEmpty()) {
			String[] areaParentIds = areaParent.getParentIds().split(",");
			for (String areaParentId : areaParentIds) {
				if (areaParentId != null && !areaParentId.isEmpty()) {
					areaIdList.add(areaParentId);
				}
			}
		
			areaIds.setIds(areaIdList);
    		List<Area> IdsSomeList = areaDao.getAreaList(areaIds);
    		return IdsSomeList;
    	}
		return null;
    }
    @Transactional(readOnly = false)
    public void save(Area area) {
        if (area.getIsNewRecord()) {
            Integer codeNo = sysCodeMaxService.getCodeNo(ID_IN, SMALL_IN, Boolean.FALSE.toString());

            // 区域ID不足两位，前补零
            if (String.valueOf(codeNo).length() < MIN_LENGTH) {
                area.setId(String.format("%03d", codeNo));
            } else {
                area.setId(String.valueOf(codeNo));
            }
            Integer code =0;
            //省
            if("2".equals(area.getType())){
            	 code = sysCodeMaxService.getCodeNo(PROVINCE_IN, MIN_IN, Boolean.FALSE.toString());
            	 //code不足两位，前补零
                 if (String.valueOf(code).length() < PROVINCE_MIN_LENGTH) {
                     area.setCode(String.format("%02d", code));
                 } else {
                     area.setCode(String.valueOf(code));
                 }
            //市
            }else if("3".equals(area.getType())){
            	 code = sysCodeMaxService.getCodeNo(CITY_IN, MIN_IN, Boolean.FALSE.toString());
            	 //code不足两位，前补零
                 if (String.valueOf(code).length() < CITY_MIN_LENGTH) {
                     area.setCode(String.format("%03d", code));
                 } else {
                     area.setCode(String.valueOf(code));
                 }
            //区域
            }else if("4".equals(area.getType())){
           	     code = sysCodeMaxService.getCodeNo(AREA_IN, MIN_IN, Boolean.FALSE.toString());
           	  //code不足两位，前补零
                 if (String.valueOf(code).length() < CITY_MIN_LENGTH) {
                     area.setCode(String.format("%03d", code));
                 } else {
                     area.setCode(String.valueOf(code));
                 }
            }
            
            area.setIsNewRecord(true);
        }
        super.save(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    @Transactional(readOnly = false)
    public void delete(Area area) {
        super.delete(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

}
