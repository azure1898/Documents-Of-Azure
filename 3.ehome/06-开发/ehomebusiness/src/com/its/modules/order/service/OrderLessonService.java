/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.HttpUtils;
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.entity.OrderLessonList;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.setup.dao.BusinessInfoDao;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

import com.its.modules.lesson.dao.LessonInfoDao;
import com.its.modules.lesson.entity.LessonInfo;
import com.its.modules.order.dao.OrderLessonDao;

/**
 * 订单-课程培训类Service
 * 
 * @author liuhl
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class OrderLessonService extends CrudService<OrderLessonDao, OrderLesson> {

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;

    /** 订单-课程培训类子表-课程培训清单Service */
    @Autowired
    private OrderLessonListService orderLessonListService;

    /** 课程培训DAO接口 */
    @Autowired
    private LessonInfoDao lessonInfoDao;

    /** 商户信息Dao */
    @Autowired
    private BusinessInfoDao businessInfoDao;

    public OrderLesson get(String id) {
        return super.get(id);
    }

    public List<OrderLesson> findList(OrderLesson orderLesson) {
        return super.findList(orderLesson);
    }

    public Page<OrderLesson> findPage(Page<OrderLesson> page, OrderLesson orderLesson) {
        page.setOrderBy("a.create_date desc,a.order_no desc");
        return super.findPage(page, orderLesson);
    }

    @Transactional(readOnly = false)
    public void save(OrderLesson orderLesson) {
        super.save(orderLesson);
    }

    @Transactional(readOnly = false)
    public void delete(OrderLesson orderLesson) {
        super.delete(orderLesson);
    }

    /**
     * 更新前的CHECK
     * 
     * @param orderNo
     *            订单号
     * @param updateDate
     *            更新日时
     * @return
     */
    public boolean check(String id, String updateDate) {
        Map<String, String> paramer = new HashMap<String, String>();
        paramer.put("id", id);
        paramer.put("updateDate", updateDate);
        int result = this.dao.check(paramer);
        // 更新日时已经发生变化
        if (0 == result) {
            return false;
        }
        return true;
    }

    /**
     * 订单取消
     * 
     * @param orderService
     *            订单取消信息
     * @return 更新结果
     */
    @Transactional(readOnly = false)
    public int cancel(OrderLesson orderLesson) {
        orderLesson.preUpdate();
        // 订单状态更新为：已取消
        int result = this.dao.cancel(orderLesson);
        // 影响条数为0，更新失败
        if (0 == result) {
            return result;
        }
        // 课程培训订单明细取得
        OrderLessonList orderLessonList = new OrderLessonList();
        // 根据订单号检索
        orderLessonList.setOrderNo(orderLesson.getOrderNo());
        // 取得该订单对应的信息
        List<OrderLessonList> orderLessonInfoList = orderLessonListService.findList(orderLessonList);

        // 课程信息IDLIST
        List<String> lessonId = new ArrayList<String>();
        // 课程培训数量信息MAP
        Map<String, Integer> serviceStock = new HashMap<String, Integer>();
        for (OrderLessonList orderLessonListTemp : orderLessonInfoList) {
            lessonId.add(orderLessonListTemp.getLessonInfoId());
            // 库存回退（课程限制人数+1）
            int stock = Integer.valueOf(1) + nullToZero(serviceStock.get(orderLessonListTemp.getLessonInfoId()));
            serviceStock.put(orderLessonListTemp.getLessonInfoId(), stock);
        }
        // 虽然逻辑上不会有课程培训为空的订单，为了程序的健壮性依然增加了该判断
        if (lessonId != null & lessonId.size() > 0) {
            // 对课程培训信息添加行级锁
            List<LessonInfo> lessonInfoList = lessonInfoDao.findLessonInfoListForUpdate(lessonId);

            // 库存回退
            for (LessonInfo lessonInfo : lessonInfoList) {
                lessonInfo.preUpdate();
                lessonInfo.setPeopleLimit(
                        nullToZero(lessonInfo.getPeopleLimit()) + nullToZero(serviceStock.get(lessonInfo.getId())));
                lessonInfo.preUpdate();
                result = lessonInfoDao.update(lessonInfo);
                // 若更新失败则回滚事务
                if (0 == result) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }

        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderId(orderLesson.getId());
        orderTrack.setOrderNo(orderLesson.getOrderNo());
        orderTrack.setOrderType(orderLesson.getProdType());
        orderTrack.setStateMsg("已取消");
        orderTrack.setHandleMsg("商家取消订单（自动退款）");
        orderTrack.setStateMsgPhone("已取消");
        orderTrack.setHandleMsgPhone("订单已成功取消");
        orderTrack.setRemarks(orderLesson.getCancelRemarks());
        orderTrackService.save(orderTrack);

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 向用户推送订单取消信息
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("businessId", user.getBusinessinfoId());
        BusinessInfo businessInfo = businessInfoDao.get(user.getBusinessinfoId());
        paramMap.put("businessName", (businessInfo != null) ? businessInfo.getBusinessName() : "");
        paramMap.put("cancelReason", orderLesson.getCancelRemarks());
        paramMap.put("orderId", orderLesson.getId());
        paramMap.put("toUserId", orderLesson.getAccountId());
        paramMap.put("sendType", "2.2");

        JSONObject msg_result = HttpUtils.sendPost("/rongCloudMsg/cancelOrderMsg", paramMap);
        // 若信息发送失败则回滚
        if (!"1000".equals(String.valueOf(msg_result.get("code")))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        return result;
    }

    /**
     * 如果为NULL则变为0
     * 
     * @param number
     *            待转换数字
     * @return
     */
    private Integer nullToZero(Integer number) {
        if (number == null) {
            return NumberUtils.INTEGER_ZERO;
        }
        return number;
    }

    /**
     * 本周订单个数
     * 
     * @return
     */
    public Integer findAllListCount() {
        return this.dao.findAllListCount();
    }

    /**
     * 本周订单金额
     * 
     * @return
     */
    public Double findAllListMoney() {
        return this.dao.findAllListMoney();
    }
}