package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.ServiceInfoDao;
import com.its.modules.app.entity.ServiceInfo;

/**
 * 服务管理Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class ServiceInfoService extends CrudService<ServiceInfoDao, ServiceInfo> {

	@Autowired
	private BusinessInfoService businessInfoService;

	public ServiceInfo get(String id) {
		return super.get(id);
	}

	public List<ServiceInfo> findList(ServiceInfo serviceInfo) {
		return super.findList(serviceInfo);
	}

	public Page<ServiceInfo> findPage(Page<ServiceInfo> page, ServiceInfo serviceInfo) {
		return super.findPage(page, serviceInfo);
	}

	@Transactional(readOnly = false)
	public void save(ServiceInfo serviceInfo) {
		super.save(serviceInfo);
	}

	@Transactional(readOnly = false)
	public void update(ServiceInfo serviceInfo) {
		dao.update(serviceInfo);
	}

	@Transactional(readOnly = false)
	public void delete(ServiceInfo serviceInfo) {
		super.delete(serviceInfo);
	}

	/**
	 * 根据商户信息ID获取商家服务项目
	 * 
	 * @param businessInfoId
	 *            商户信息ID
	 * @param showCount
	 *            查询数量
	 * @return List<ServiceInfo>
	 */
	public List<ServiceInfo> getByBusinessId(String businessInfoId, int showCount) {
		return dao.getByBusinessId(businessInfoId, showCount);
	}

	/**
	 * 根据商家ID和服务分类ID获取商家服务项目（不包含下架或删除的服务）
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @return List<ServiceInfo>
	 */
	public List<ServiceInfo> getByBusinessIdAndSortInfoId(ServiceInfo serviceInfo,int pageIndex,int numPerPage) {
		String  businessInfoId=serviceInfo.getBusinessInfoId();
		String sortInfoId=serviceInfo.getSortInfoId();
		return dao.getByBusinessIdAndSortInfoId(businessInfoId,sortInfoId,pageIndex*numPerPage,numPerPage);
	}

	/**
	 * 获取服务价格
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @return 服务价格
	 */
	public Double getServicePrice(ServiceInfo serviceInfo) {
		// 服务有原价和优惠价：服务价格=优惠价，服务有原价无优惠价：服务价格=原价
		if (serviceInfo.getBenefitPrice() == null) {
			return ValidateUtil.validateDouble(serviceInfo.getBasePrice());
		} else {
			return ValidateUtil.validateDouble(serviceInfo.getBenefitPrice());
		}
	}

	/**
	 * 获取拆分后的图片列表
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @return 拆分后的图片列表
	 */
	public List<Map<String, Object>> getImageList(ServiceInfo serviceInfo, HttpServletRequest request) {
		List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
		String serviceImgs = serviceInfo.getImgs();
		if (StringUtils.isNotBlank(serviceImgs)) {
			String[] imageArray = serviceImgs.replaceAll("，", ",").split(",");
			for (int i = 0; i < imageArray.length; i++) {
				Map<String, Object> image = new HashMap<String, Object>();
				image.put("url", this.formatServiceImg(imageArray[i], request));

				images.add(image);
			}
		}
		return images;
	}

	/**
	 * 获取第一个服务图片
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @return 第一个服务图片
	 */
	public String getFirstServiceImg(ServiceInfo serviceInfo, HttpServletRequest request) {
		String serviceImgs = serviceInfo.getImgs();
		if (StringUtils.isBlank(serviceImgs)) {
			return "";
		}
		return formatServiceImg(serviceImgs.replace("，", ",").split(",")[0], request);
	}

	/**
	 * 获取服务图片的完整路径
	 * 
	 * @param serviceImg
	 *            服务图片
	 * @return 服务图片的完整路径
	 */
	public String formatServiceImg(String serviceImg, HttpServletRequest request) {
		if (StringUtils.isBlank(serviceImg)) {
			return "";
		}
		return MyFDFSClientUtils.get_fdfs_file_url(request, serviceImg + "_compress2");
	}

	/**
	 * 获取服务名称
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @return 服务名称
	 */
	public String getServiceName(ServiceInfo serviceInfo) {
		// 服务名称=服务名称+限购数（服务无限购则不显示）
		if ("0".equals(serviceInfo.getQuota())) {
			return serviceInfo.getName();
		} else {
			String unit = businessInfoService.getUnit(serviceInfo.getBaseUnit());
			return serviceInfo.getName() + "（限购" + ValidateUtil.validateInteger(serviceInfo.getQuotaNum()) + unit + "）";
		}
	}

	/**
	 * 更新服务已售数量
	 * 
	 * @param count
	 *            数量
	 * @param serviceInfoId
	 *            服务ID
	 * @return 操作的行数
	 */
	public int updateSellCount(int count, String serviceInfoId) {
		return dao.updateSellCount(count, serviceInfoId);
	}
	
	/**
	 * 根据服务ID获取商家服务项目
	 * @param id
	 * 			服务id
	 * @return serviceInfo
	 * 			服务信息
	 */
	public ServiceInfo getValidateById(String id) {
		return dao.getValidateById(id);
	}
}