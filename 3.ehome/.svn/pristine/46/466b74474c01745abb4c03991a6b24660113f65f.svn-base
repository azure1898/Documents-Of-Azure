/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.lesson.service;

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
import com.its.modules.lesson.dao.LessonInfoDao;
import com.its.modules.lesson.entity.LessonInfo;

/**
 * 课程培训Service
 * @author sushipeng
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class LessonInfoService extends CrudService<LessonInfoDao, LessonInfo> {

	 /**
     * 根据ID查询课程培训信息
     * 
     * @param id 课程培训ID
     * @return 课程培训信息
     */
	public LessonInfo get(String id) {
		return super.get(id);
	}
	
	/**
	 * 课程培训信息列表取得
	 * @param lessonInfo 查询条件
	 */
	public List<LessonInfo> findList(LessonInfo lessonInfo) {
		return super.findList(lessonInfo);
	}
	
	/**
	 * 课程培训信息分页列表取得
	 * @author ChenXiangyu
	 * @param page 分页信息
	 * @param lessonInfo 查询条件
	 */
	public Page<LessonInfo> findPage(Page<LessonInfo> page, LessonInfo lessonInfo) {
		StringBuffer orderBy = new StringBuffer();

        // 根据画面选择的条件来进行排序
        if (StringUtils.isNotBlank(lessonInfo.getSortItem())) {
            orderBy.append(lessonInfo.getSortItem());
            orderBy.append(" ");
            orderBy.append(lessonInfo.getSort());
            orderBy.append(",");
        } else {
            // 默认根据创建时间升序排序
            orderBy.append("a.create_date ASC,");
        }

        // 去掉末尾逗号
        page.setOrderBy(StringUtils.removeEnd(orderBy.toString(), ","));
		return super.findPage(page, lessonInfo);
	}
	
	 /**
     * 课程培训信息保存
     * @author ChenXiangyu
     * @param lessonInfo 课程培训信息
     */
	@Transactional(readOnly = false)
	public void save(LessonInfo lessonInfo) {
		if (StringUtils.isNotBlank(lessonInfo.getContent())) {
			lessonInfo.setContent(StringEscapeUtils.unescapeHtml4(lessonInfo.getContent()));
        }
		super.save(lessonInfo);
	}
	
	/**
	 * 删除课程培训信息
	 */
	@Transactional(readOnly = false)
	public void delete(LessonInfo lessonInfo) {
		super.delete(lessonInfo);
	}
	
	 /**
     * 复数删除课程信息
     * @author ChenXiangyu
     * @param lessonId 课程ID
     */
    @Transactional(readOnly = false)
    public void muliDelete(String lessonId) {

        String[] lessonIdArr = lessonId.split(",");
        for (String tempLessonId : lessonIdArr) {
            LessonInfo lessonInfo = new LessonInfo();
            lessonInfo.setId(tempLessonId);
            super.delete(lessonInfo);
        }
    }

    /**
     * 复数上架课程信息
     * @author ChenXiangyu
     * @param lessonId 课程ID
     * @return 总库存为0不能上架的课程
     */
    @Transactional(readOnly = false)
    public List<String> muliGrounding(String lessonId) {

        List<String> cannotGroundingLesson = new ArrayList<String>();
        String[] lessonIdArr = lessonId.split(",");
        for (String tempLessonId : lessonIdArr) {
            LessonInfo lessonInfo = this.dao.selectZeroStockLesson(tempLessonId);
            if (lessonInfo != null && StringUtils.isNotBlank(lessonInfo.getId())) {
                cannotGroundingLesson.add(lessonInfo.getName());
                continue;
            }
            this.dao.groundingByLessonId(tempLessonId);
        }
        return cannotGroundingLesson;
    }

    /**
     * 复数下架课程信息
     * @author ChenXiangyu
     * @param lessonId
     */
    @Transactional(readOnly = false)
    public void muliUndercarriage(String lessonId) {

        String[] lessonIdArr = lessonId.split(",");
        for (String tempLessonId : lessonIdArr) {
            this.dao.undercarriageByLessonId(tempLessonId);
        }
    }

    /**
     * 图片地址更新 地址由 根目录+save+商家ID+课程ID 构成
     * @author ChenXiangyu
     * @param lessonInfo
     *            课程信息ID
     * @param imgName
     *            图片名
     */
    @Transactional(readOnly = false)
    public void imgNameUpdate(LessonInfo lessonInfo, List<String> imgUrlList) {

        // 图片地址更新用MAP
        Map<String, String> imgUrlUpdateMap = new HashMap<String, String>();
        // 所有图片服务器文件ID集合
        StringBuffer imgUrls = new StringBuffer();
        if (StringUtils.isNotBlank(lessonInfo.getImgs())) {
            imgUrls.append(lessonInfo.getImgs());
            imgUrls.append(",");
        }
        for (String imgUrl : imgUrlList) {
            imgUrls.append(imgUrl);
            imgUrls.append(",");
        }
        String imgUrlsForUpdate = imgUrls.toString();
        // 将画面上删除的文件ID从DB中跟新掉
        if (StringUtils.isNotBlank(lessonInfo.getDelImageName())){
        	String[] delImgName = lessonInfo.getDelImageName().split(",");
        	for (String imgName : delImgName) {
        		imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName + ",", "");
        		// 避免该文件ID在最后一个
        		imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName, "");
        	}
        }
        imgUrlUpdateMap.put("id", lessonInfo.getId());
        // 将拼接好的文件ID存入DB（DB中只存放图片服务器MY_FILE_ID，显示时拼接好URL再放到画面上）
        imgUrlUpdateMap.put("imgUrl", StringUtils.removeEnd(imgUrlsForUpdate, ","));
        this.dao.imgNameUpdate(imgUrlUpdateMap);
    }
    
    /**
     * 根据课程ID取得课程信息并将该行锁定
     * @author ChenXiangyu
     * @param lessonId
     *            课程ID
     * @return 课程信息
     */
    public List<LessonInfo> findLessonInfoListForUpdate(List<String> lessonId) {
        return this.dao.findLessonInfoListForUpdate(lessonId);
    }

	/**
	 * 查询课程个数
	 * @param lessonInfo_where
	 * @return
	 */
	public Integer findAllListCount(LessonInfo lessonInfo_where) {
		return this.dao.findAllListCount( lessonInfo_where);
	}
}