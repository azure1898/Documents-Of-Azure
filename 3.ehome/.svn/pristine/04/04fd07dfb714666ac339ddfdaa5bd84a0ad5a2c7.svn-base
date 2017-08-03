/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.its.common.utils.DateUtils;
import com.its.modules.field.dao.FieldPartitionPriceDao;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.sys.service.SysCodeMaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.dao.OrderFieldDao;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.dao.OrderFieldListDao;

/**
 * 场地预约订单表Service
 * 
 * @author xzc
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class OrderFieldService extends CrudService<OrderFieldDao, OrderField> {
    /**
     * 场地预约订单表DAO接口
     */
    @Autowired
    private OrderFieldListDao orderFieldListDao;
    /**
     * 最大编码表Service
     */
    @Autowired
    private SysCodeMaxService sysCodeMaxService;

    /**
     * 场地预约子表-场地分段信息DAO接口
     */
    @Autowired
    private FieldPartitionPriceDao fieldPartitionPriceDao;
    /**
     * 场地预约Service
     */
    @Autowired
    private FieldInfoService fieldInfoService;
    /**
     * 订单跟踪表Service
     */
    @Autowired
    private OrderTrackService orderTrackService;

    /**
     * 获取单个对象
     * 
     * @param id
     * @return
     */
    public OrderField get(String id) {
        OrderField orderField = super.get(id);
        List<OrderFieldList> orderFieldList = orderFieldListDao.findList(new OrderFieldList(orderField));
        if (orderFieldList != null && orderFieldList.size() > 0) {
            orderField.setOrderFieldList(orderFieldList.get(0));
        }
        return orderField;
    }

    /**
     * 查询集合
     * 
     * @param orderField
     * @return
     */
    public List<OrderField> findList(OrderField orderField) {
        return super.findList(orderField);
    }

    /**
     * 查询带分页的集合
     * 
     * @param page
     *            分页对象
     * @param orderField
     * @return
     */
    public Page<OrderField> findPage(Page<OrderField> page, OrderField orderField) {
        return super.findPage(page, orderField);
    }

    /**
     * 保存
     * 
     * @param orderField
     */
    @Transactional(readOnly = false)
    public void save(OrderField orderField, FieldPartitionPrice fieldPartitionPrice, String cityId,
            String businessInfoId) {

        FieldInfo fieldInfo = fieldInfoService.get(fieldPartitionPrice.getFieldInfoId());
        orderField.preInsert();
        orderField.setBusinessInfoId(businessInfoId);
        orderField.setOrderNo(sysCodeMaxService.getFieldOrdNo(cityId, businessInfoId));
        orderField.setType("2");
        orderField.setName(fieldInfo.getName());
        orderField.setFieldInfoId(fieldInfo.getId());
        Double payMoney = orderField.getPayMoney();
        Double baseMoney = fieldPartitionPrice.getBasePrice()
                * DateUtils.pastHour(fieldPartitionPrice.getStartTime(), fieldPartitionPrice.getEndTime());
        Double payAllMoney = payMoney
                * DateUtils.pastHour(fieldPartitionPrice.getStartTime(), fieldPartitionPrice.getEndTime());
        orderField.setSumMoney(baseMoney);
        orderField.setPayMoney(payAllMoney);
        orderField.setOrderState("1");
        orderField.setCheckOrderState("0");
        orderField.setCheckState("0");
        orderField.setPayType("1");
        orderField.setPayOrg("3");
        orderField.setPayState("1");
        orderField.setPayTime(new Date());
        // 保存订单
        dao.insert(orderField);
        // 保存订单明细
        OrderFieldList orderFieldList = new OrderFieldList();
        orderFieldList.preInsert();
        orderFieldList.setOrderField(orderField);
        orderFieldList.setBusinessInfoId(fieldPartitionPrice.getBusinessInfoId());
        orderFieldList.setFieldInfoId(fieldPartitionPrice.getFieldInfoId());
        orderFieldList.setFieldPartitionPriceId(fieldPartitionPrice.getId());
        orderFieldList.setOrderNo(orderField.getOrderNo());
        orderFieldList.setName(orderField.getName());
        orderFieldList.setAppointmentTime(fieldPartitionPrice.getAppointmentTime());
        orderFieldList.setStartTime(fieldPartitionPrice.getStartTime());
        orderFieldList.setEndTime(fieldPartitionPrice.getEndTime());
        orderFieldList.setBasePrice(fieldPartitionPrice.getBasePrice());
        orderFieldList.setBenefitPrice(payMoney);
        orderFieldList.setSumMoney(payAllMoney);
        orderFieldList.setOrderState("1");
        orderFieldListDao.insert(orderFieldList);

        fieldPartitionPrice.setState("1");
        fieldPartitionPriceDao.updateState(fieldPartitionPrice);

        // 跟踪表要插入信息
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderField.getOrderNo());
        orderTrack.setBusinessInfoId(orderField.getBusinessInfoId());
        orderTrack.setOrderId(orderField.getId());
        orderTrack.setOrderType(orderField.getProdType());
        orderTrack.setStateMsg("预约成功");
        orderTrack.setHandleMsg("场地预约成功（商家后台预约）");
        orderTrack.setStateMsgPhone("预约成功");
        orderTrack.setHandleMsgPhone("付款成功，等待消费");
        orderTrackService.save(orderTrack);
    }

    /**
     * 删除
     * 
     * @param orderField
     */
    @Transactional(readOnly = false)
    public void delete(OrderField orderField) {
        super.delete(orderField);
        orderFieldListDao.delete(new OrderFieldList(orderField));
    }

    /**
     * 通过场地预约清单id获取场地预约清单对象
     * 
     * @param id
     *            场地预约清单id
     * @return
     */
    @Transactional(readOnly = false)
    public OrderFieldList getOrderFieldListByFieldPartitionPriceId(String id) {

        return orderFieldListDao.getOrderFieldListByFieldPartitionPriceId(id);
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

    /**
     * 更新前的CHECK
     * 
     * @param id
     *            订单号
     * @param updateDate
     *            更新日时
     * @return
     */
    public boolean check(String id, String updateDateString) {
        Map<String, String> paramer = new HashMap<String, String>();
        paramer.put("id", id);
        paramer.put("updateDate", updateDateString);
        int result = this.dao.check(paramer);
        // 更新日时已经发生变化
        if (0 == result) {
            return false;
        }
        return true;
    }

    /**
     * 取消订单，恢复场地粉短信息状态，并生成订单跟踪信息
     * 
     * @param orderField
     *            画面信息
     * @return
     */
    @Transactional(readOnly = false)
    public int cancel(OrderField orderFieldFromJSP) {
        // 订单信息取得（根据画面传来的信息取得全部数据）
        OrderField orderField = super.get(orderFieldFromJSP.getId());
        // 订单状态更新（订单状态变更为：已取消，支付状态变为：退款中）
        // 订单状态变更为：已取消
        orderField.setOrderState("2");
        // 支付状态变为：退款中
        orderField.setPayState("2");
        orderField.preUpdate();
        // 执行更新处理，并返回影响条数
        int result = this.dao.update(orderField);

        // 若影响条数不是1
        if (1 != result) {
            // 手动回滚，并终止操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

        // 订单-场地预约类子表-场地清单信息
        List<OrderFieldList> orderFieldLists = orderFieldListDao.getOrderFieldListByOrderNo(orderField.getOrderNo());
        // 订单-场地预约类子表-场地清单(Order_Field_List)订单状态更新
        // 订单状态变更为：已取消
        // 执行更新处理，并返回影响条数
        result = orderFieldListDao.cancelOrderFieldListByOrderNo(orderField.getOrderNo());
        // 若影响条数不是1
        if (1 != result) {
            // 手动回滚，并终止操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

        for (OrderFieldList orderFieldListTemp : orderFieldLists) {
            // 场地预约子表-场地分段信息状态更新
            // 取得场地分段信息并添加行级锁
            FieldPartitionPrice fieldPartitionPrice = fieldPartitionPriceDao
                    .getForUpdate(orderFieldListTemp.getFieldPartitionPriceId());
            if (fieldPartitionPrice != null) {
                // 将该分段信息状态变为可预约
                fieldPartitionPrice.setState("0");
                result = fieldPartitionPriceDao.update(fieldPartitionPrice);
                // 若影响条数不是1
                if (1 != result) {
                    // 手动回滚，并终止操作
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return 0;
                }
            }
        }

        // TODO:退款操作

        // 添加订单跟踪信息
        // 跟踪表要插入信息
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderField.getOrderNo());
        orderTrack.setBusinessInfoId(orderField.getBusinessInfoId());
        orderTrack.setOrderId(orderField.getId());
        orderTrack.setOrderType(orderField.getProdType());
        orderTrack.setStateMsg("已取消");
        orderTrack.setHandleMsg("商家取消预订（自动退款）");
        orderTrack.setStateMsgPhone("已取消");
        orderTrack.setHandleMsgPhone("订单已成功取消");
        orderTrack.setRemarks(orderFieldFromJSP.getCancelRemarks());
        orderTrackService.save(orderTrack);

        orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderField.getOrderNo());
        orderTrack.setBusinessInfoId(orderField.getBusinessInfoId());
        orderTrack.setOrderId(orderField.getId());
        orderTrack.setOrderType(orderField.getProdType());
        orderTrack.setStateMsgPhone("退款中");
        orderTrack.setHandleMsgPhone("订单开始退款，等待退款");
        orderTrack.setRemarks(orderFieldFromJSP.getCancelRemarks());
        orderTrackService.save(orderTrack);
        return 1;
    }
}