package com.its.common.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.its.modules.sys.entity.Area;
import com.its.modules.sys.service.AreaService;

/**
 * 地址工具类
 * 
 * @author liuhl
 */
public class AdressUtils {

    /** 商品规格名称Service */
    @Autowired
    private AreaService areaService;

    /**
     * 取得省市
     * 
     * @param province
     *            省ID
     * @param city
     *            市ID
     * @return 省市
     */
    public String getProvinceAndCity(String provinceId, String cityId) {
        return getProvinceCityAndRegion(provinceId, cityId, null);
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
     * @return 省市区
     */
    public String getProvinceCityAndRegion(String provinceId, String cityId, String regionId) {
        // 取得省信息
        Area province = areaService.get(provinceId);
        // 取得市信息
        Area city = areaService.get(cityId);
        // 取得区信息
        Area region = areaService.get(regionId);
        StringBuffer sb = new StringBuffer();
        if (province != null) {
            sb.append(province.getName());
        }
        if (city != null) {
            sb.append(city.getName());
        }
        if (region != null) {
            sb.append(region.getName());
        }
        return sb.toString();
    }
}
