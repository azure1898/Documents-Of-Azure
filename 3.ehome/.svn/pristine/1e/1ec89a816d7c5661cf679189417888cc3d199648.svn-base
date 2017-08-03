/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.service.dao.ServiceInfoDao;
import com.its.modules.service.entity.ServiceInfo;

/**
 * 服务管理Service
 * @author liuhl
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class ServiceInfoService extends CrudService<ServiceInfoDao, ServiceInfo> {

	/** 符号：空格 */
	private static final String SYMBOL_SPACE = " ";
	/** 符号：逗号 */
	private static final String SYMBOL_COMMA = ",";
	
	public ServiceInfo get(String id) {
		return super.get(id);
	}
	
	public List<ServiceInfo> findList(ServiceInfo serviceInfo) {
		return super.findList(serviceInfo);
	}
	
	public Page<ServiceInfo> findPage(Page<ServiceInfo> page, ServiceInfo serviceInfo) {
		StringBuffer orderBy = new StringBuffer();

        // 根据画面选择的条件来进行排序
        if (StringUtils.isNotBlank(serviceInfo.getSortItem())) {
            orderBy.append(serviceInfo.getSortItem());
            orderBy.append(SYMBOL_SPACE);
            orderBy.append(serviceInfo.getSort());
            orderBy.append(SYMBOL_COMMA);
        } else {
            // 默认根据创建时间升序排序
            orderBy.append("a.create_date ASC,");
        }

        // 去掉末尾逗号
        page.setOrderBy(StringUtils.removeEnd(orderBy.toString(), ","));
		return super.findPage(page, serviceInfo);
	}
	
	/**
	 * 服务信息保存
	 */
	@Transactional(readOnly = false)
	public void save(ServiceInfo serviceInfo) {
		if (StringUtils.isNotBlank(serviceInfo.getContent())) {
			serviceInfo.setContent(StringEscapeUtils.unescapeHtml4(serviceInfo.getContent()));
        }
		super.save(serviceInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ServiceInfo serviceInfo) {
		super.delete(serviceInfo);
	}

    /**
     * 根据服务分类ID取得服务信息
     * 
     * @param sortInfoID
     *            服务分类ID
     * @return 服务信息
     */
	public List<ServiceInfo> findServiceInfoListBySortInfo(String sortInfoID) {
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setSortInfoId(sortInfoID);
        return super.findList(serviceInfo);
	}
	
    /**
     * 复数删除服务信息
     * 
     * @param serviceIds
     */
    @Transactional(readOnly = false)
    public void muliDelete(String serviceIds) {
        String[] serviceIdArr = serviceIds.split(SYMBOL_COMMA);
        for (String serviceId : serviceIdArr) {
        	ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setId(serviceId);
            super.delete(serviceInfo);
        }
    }

    /**
     * 复数上架服务信息
     * 
     * @param serviceIds
     * @return 总库存为0不能上架的服务
     */
    @Transactional(readOnly = false)
    public List<String> muliGrounding(String serviceIds) {
        List<String> cannotGroundingService = new ArrayList<String>();
        String[] serviceIdArr = serviceIds.split(SYMBOL_COMMA);
        for (String serviceId : serviceIdArr) {
        	ServiceInfo serviceInfo = this.dao.selectZeroStockService(serviceId);
            if (serviceInfo != null && StringUtils.isNotBlank(serviceInfo.getId())) {
                cannotGroundingService.add(serviceInfo.getName());
                continue;
            }
            this.dao.groundingByServiceId(serviceId);
        }
        return cannotGroundingService;
    }

    /**
     * 复数下架服务信息
     * 
     * @param serviceIds
     */
    @Transactional(readOnly = false)
    public void muliUndercarriage(String serviceIds) {

        String[] serviceIdArr = serviceIds.split(SYMBOL_COMMA);
        for (String serviceId : serviceIdArr) {
            this.dao.undercarriageByServiceId(serviceId);
        }
    }

    /**
     * 图片地址更新 地址由 根目录+save+商家ID+服务ID 构成
     * 
     * @param serviceInfo
     *            服务信息ID
     * @param imgName
     *            图片名
     */
    @Transactional(readOnly = false)
    public void imgNameUpdate(ServiceInfo serviceInfo, List<String> imgUrlList) {

        // 图片地址更新用MAP
        Map<String, String> imgUrlUpdateMap = new HashMap<String, String>();
        // 所有图片地址集合
        StringBuffer imgUrls = new StringBuffer();
        if (StringUtils.isNotBlank(serviceInfo.getImgs())) {
            imgUrls.append(serviceInfo.getImgs());
            imgUrls.append(SYMBOL_COMMA);
        }
        for (String imgUrl : imgUrlList) {
            imgUrls.append(imgUrl);
            imgUrls.append(SYMBOL_COMMA);
        }
        String imgUrlsForUpdate = imgUrls.toString();
        if (StringUtils.isNotBlank(serviceInfo.getDelImageName())){
        	String[] delImgName = serviceInfo.getDelImageName().split(SYMBOL_COMMA);
        	for (String imgName : delImgName) {
        		imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName + SYMBOL_COMMA, StringUtils.EMPTY);
        		// 避免该文件在最后一个
        		imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName, StringUtils.EMPTY);
        	}
        }
        imgUrlUpdateMap.put("id", serviceInfo.getId());
        imgUrlUpdateMap.put("imgUrl", StringUtils.removeEnd(imgUrlsForUpdate, SYMBOL_COMMA));
        this.dao.imgNameUpdate(imgUrlUpdateMap);
    }
    
    /**
     * 根据服务ID取得服务信息并将该行锁定
     * 
     * @param serviceIds
     *            服务ID
     * @return 服务信息
     */
    public List<ServiceInfo> findServiceInfoListForUpdate(List<String> serviceIds) {
        return this.dao.findServiceInfoListForUpdate(serviceIds);
    }

    /**
     *服务总数量
     * @param serviceInfo_where
     * @return
     */
    public Integer findAllListCount(ServiceInfo serviceInfo) {
        return this.dao.findAllListCount(serviceInfo);
    }
}