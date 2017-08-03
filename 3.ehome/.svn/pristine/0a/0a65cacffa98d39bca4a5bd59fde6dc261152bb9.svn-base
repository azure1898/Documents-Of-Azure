/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.lesson.dao;

import java.util.List;
import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.lesson.entity.LessonInfo;

/**
 * 课程培训DAO接口
 * @author sushipeng
 * @version 2017-07-19
 */
@MyBatisDao
public interface LessonInfoDao extends CrudDao<LessonInfo> {

    /**
     * 根据课程培训分类ID取得课程培训信息并将该行锁定
     * 
     * @param lessonId
     *            课程培训ID
     * @return 课程培训信息
     */
    List<LessonInfo> findLessonInfoListForUpdate(List<String> lessonId);

    /**
     * 查询课程个数
     * @param lessonInfo
     * @return
     */
    Integer findAllListCount(LessonInfo lessonInfo);
    
    /**
	 * 图片地址更新
	 * @author ChenXiangyu
	 * @param imgUrlUpdateMap 更新条件
	 */
	void imgNameUpdate(Map<String, String> imgUrlUpdateMap);
	
	/**
	 * 复数上架
	 * @author ChenXiangyu
	 * @param lessonId 课程ID
	 */
	int groundingByLessonId(String lessonId);
	
	/**
	 * 复数下架
	 * @author ChenXiangyu
	 * @param lessonId 课程ID
	 */
	int undercarriageByLessonId(String lessonId);
	
	/**
	 * 复数下架-获取库存为零的课程
	 * @author ChenXiangyu
	 * @param lessonId 课程ID
	 */
	LessonInfo selectZeroStockLesson(String lessonId);
}