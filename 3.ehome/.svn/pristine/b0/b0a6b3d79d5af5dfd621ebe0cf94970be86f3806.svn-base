package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.OrderLessonBean;
import com.its.modules.app.entity.OrderLesson;

/**
 * 订单-课程培训类DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-12
 */
@MyBatisDao
public interface OrderLessonDao extends CrudDao<OrderLesson> {

	/**
	 * 获取某课程培训用户已购数量
	 * 
	 * @param accountId
	 *            用户ID
	 * @param lessonInfoId
	 *            课程ID
	 * @return 某课程培训用户已购数量
	 */
	public int getCountByLessonInfoIdAndAccountId(@Param("accountId") String accountId, @Param("lessonInfoId") String lessonInfoId);

	/**
	 * 根据订单号获取课程购买订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderLesson
	 */
	public OrderLesson getByOrderNo(String orderNo);

	/**
	 * 获取某用户某楼盘下的课程培训订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID
	 * @return List<OrderLessonBean>
	 */
	public List<OrderLessonBean> getOrderLessonList(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("moduleManageId") String moduleManageId);

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderLessonBean
	 */
	public OrderLessonBean getOrderLessonByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderLesson
	 */
	public OrderLesson judgeOrderLessonByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);
}