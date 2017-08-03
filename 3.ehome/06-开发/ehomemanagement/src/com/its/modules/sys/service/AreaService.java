/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.service;

import java.util.List;

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
    private SysCodeMaxService sysCodeMaxService;
    /** 流水类型编号：城市类型 */
    private static final String ID_IN = "003";
    /** 初始化最小值 */
    private static final int SMALL_IN = 0;
    /** 模块ID最小位数 */
    private static final int MIN_LENGTH = 3;

    public List<Area> findAll() {
        return UserUtils.getAreaList();
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
