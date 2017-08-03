/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.entity.OrderLessonList;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderTrack;
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

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;

    /** 订单-课程培训类子表-课程培训清单Service */
    @Autowired
    private OrderLessonListService orderLessonListService;

    /** 课程培训DAO接口 */
    @Autowired
    private LessonInfoDao lessonInfoDao;

    public OrderLesson get(String id) {
        return super.get(id);
    }

    public List<OrderLesson> findList(OrderLesson orderLesson) {
        return super.findList(orderLesson);
    }

    public Page<OrderLesson> findPage(Page<OrderLesson> page, OrderLesson orderLesson) {
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
    public boolean check(String orderNo, String updateDate) {
        Map<String, String> paramer = new HashMap<String, String>();
        paramer.put("orderNo", orderNo);
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

        // 数据库中订单数据（参数是画面传递过来的信息）
        OrderLesson orderLessonInfo = super.get(orderLesson.getId());
        // 如果订单支付状态为1：已支付的话，执行退款处理
        if ("1".equals(orderLessonInfo.getPayState())) {
            orderLessonInfo.preUpdate();
            // 将该订单的支付状态改为2：退款中
            orderLessonInfo.setPayState("2");
            super.save(orderLessonInfo);

            // 生成退款信息，交由总后台进行退款
            OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
            orderRefundInfo.setBusinessInfoId(orderLessonInfo.getBusinessInfoId());
            orderRefundInfo.setOrderNo(orderLessonInfo.getOrderNo());
            // 因为是课程培训订单发生退款，所以固定为0：课程培训类
            orderRefundInfo.setOrderType("0");
            orderRefundInfo.setPayType(orderLessonInfo.getPayType());
            orderRefundInfo.setOrderMoney(orderLessonInfo.getPayMoney().toString());
            // 终端类型固定为商家后台
            orderRefundInfo.setType("2");
            orderRefundInfo.setModuleManageId(orderLessonInfo.getModuleManageId());
            // 产品模式固定为0:课程培训购买
            orderRefundInfo.setProdType("0");
            orderRefundInfo.setRefundMoney(orderLessonInfo.getPayMoney().toString());
            orderRefundInfo.setRefundType(orderLessonInfo.getPayOrg());
            orderRefundInfoService.save(orderRefundInfo);
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
            // 算出该订单每种课程培训一共多少库存
            int stock = nullToZero(orderLessonListTemp.getPeopleLimit())
                    + nullToZero(serviceStock.get(orderLessonListTemp.getLessonInfoId()));
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
        orderTrack.setOrderNo(orderLesson.getOrderNo());
        orderTrack.setStateMsg("已取消");
        orderTrack.setHandleMsg("商家取消订单（自动退款）");
        orderTrack.setRemarks(orderLesson.getCancelRemarks());
        orderTrackService.save(orderTrack);
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
     * @return
     */
    public Integer findAllListCount() {
        return this.dao.findAllListCount();
    }

    /**
     * 本周订单金额
     * @return
     */
    public Double findAllListMoney() {
        return this.dao.findAllListMoney();
    }
}