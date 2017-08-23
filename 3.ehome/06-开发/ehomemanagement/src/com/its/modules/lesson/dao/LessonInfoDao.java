/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.lesson.dao;

import java.util.List;

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
     * @param lessonInfo_where
     * @return
     */
    Integer findAllListCount(LessonInfo lessonInfo_where);
    
    /**
     *  通过商家ID获取课程列表 -广告发布
     * @param buinessInfoId
     * @return
     * @return List<FieldInfo>
     * @author zhujiao   
     * @date 2017年8月21日 下午5:24:30
     */
    List<LessonInfo> getLessonInfoList(String businessInfoId);
}