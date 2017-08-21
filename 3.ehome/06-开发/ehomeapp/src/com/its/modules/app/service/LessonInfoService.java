package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.LessonInfoDao;
import com.its.modules.app.entity.LessonInfo;

/**
 * 课程培训Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-10
 */
@Service
@Transactional(readOnly = true)
public class LessonInfoService extends CrudService<LessonInfoDao, LessonInfo> {

	public LessonInfo get(String id) {
		return super.get(id);
	}

	public List<LessonInfo> findList(LessonInfo lessonInfo) {
		return super.findList(lessonInfo);
	}

	public Page<LessonInfo> findPage(Page<LessonInfo> page, LessonInfo lessonInfo) {
		return super.findPage(page, lessonInfo);
	}

	@Transactional(readOnly = false)
	public void save(LessonInfo lessonInfo) {
		super.save(lessonInfo);
	}

	@Transactional(readOnly = false)
	public void update(LessonInfo lessonInfo) {
		dao.update(lessonInfo);
	}

	@Transactional(readOnly = false)
	public void delete(LessonInfo lessonInfo) {
		super.delete(lessonInfo);
	}

	/**
	 * 根据商户信息ID获取商家课程项目
	 * 
	 * @param businessInfoId
	 *            商户信息ID
	 * @param showCount
	 *            查询数量
	 * @return List<LessonInfo>
	 */
	public List<LessonInfo> getByBusinessId(String businessInfoId, Integer showCount) {
		return dao.getByBusinessId(businessInfoId, showCount);
	}

	/**
	 * 根据商户信息ID获取商家课程项目
	 * 
	 * @param businessInfoId
	 *            商户信息ID
	 * @return List<LessonInfo>
	 */
	public List<LessonInfo> getNoLimitList(String businessInfoId, int pageIndex, int numPerPage) {
		return dao.getNoLimitList(businessInfoId, pageIndex * numPerPage, numPerPage);
	}

	/**
	 * 获取课程价格
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @return 课程价格
	 */
	public Double getLessonPrice(LessonInfo lessonInfo) {
		// 课程有原价和优惠价：课程价格=优惠价，课程有原价无优惠价：课程价格=原价
		if (lessonInfo.getBenefitPrice() == null) {
			return ValidateUtil.validateDouble(lessonInfo.getBasePrice());
		} else {
			return ValidateUtil.validateDouble(lessonInfo.getBenefitPrice());
		}
	}

	/**
	 * 获取拆分后的图片列表
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @return 拆分后的图片列表
	 */
	public List<Map<String, Object>> getImageList(LessonInfo lessonInfo, HttpServletRequest request) {
		List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
		String lessonImgs = lessonInfo.getImgs();
		if (StringUtils.isNotBlank(lessonImgs)) {
			String[] imageArray = lessonImgs.replaceAll("，", ",").split(",");
			for (int i = 0; i < imageArray.length; i++) {
				Map<String, Object> image = new HashMap<String, Object>();
				image.put("url", this.formatLessonImg(imageArray[i], request));

				images.add(image);
			}
		}
		return images;
	}

	/**
	 * 获取第一个课程图片
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @return 第一个课程图片
	 */
	public String getFirstLessonImg(LessonInfo lessonInfo, HttpServletRequest request) {
		String lessonImgs = lessonInfo.getImgs();
		if (StringUtils.isBlank(lessonImgs)) {
			return "";
		}
		return formatLessonImg(lessonImgs.replace("，", ",").split(",")[0], request);
	}

	/**
	 * 获取课程图片的完整路径
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @param lessonImg
	 *            课程图片
	 * @return 课程图片的完整路径
	 */
	public String formatLessonImg(String lessonImg, HttpServletRequest request) {
		if (StringUtils.isBlank(lessonImg)) {
			return "";
		}
		return MyFDFSClientUtils.get_fdfs_file_url(request, lessonImg + "_compress2");
	}

	/**
	 * 获取课程名称
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @return 课程名称
	 */
	public String getLessonName(LessonInfo lessonInfo) {
		// 课程名称=课程名称+限购数（课程无限购则不显示）
		if ("0".equals(lessonInfo.getLessonQuota())) {
			return lessonInfo.getName();
		} else {
			return lessonInfo.getName() + "（限购" + ValidateUtil.validateInteger(lessonInfo.getQuotaNum()) + "件）";
		}
	}

	/**
	 * 更新课程库存、已购数量
	 * 
	 * @param lessonInfoId
	 *            课程ID
	 * @return 操作的行数
	 */
	public int updateSellCount(String lessonInfoId) {
		return dao.updateSellCount(lessonInfoId);
	}
}