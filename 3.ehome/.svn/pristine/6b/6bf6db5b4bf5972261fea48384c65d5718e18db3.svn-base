/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.entity.OrderServiceList;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.service.dao.ServiceInfoDao;
import com.its.modules.service.entity.ServiceInfo;
import com.its.modules.order.dao.OrderServiceDao;

/**
 * 订单-服务类Service
 * @author liuhl
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceService extends CrudService<OrderServiceDao, OrderService> {

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;
    
    /** 服务管理Service */
    @Autowired
    private ServiceInfoDao serviceInfoDao;
    
    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;
    
    /** 订单-服务类子表-服务清单Service */
    @Autowired
    private OrderServiceListService orderServiceListService;
    
    public OrderService get(String id) {
        return super.get(id);
    }
    
    public List<OrderService> findList(OrderService orderService) {
        return super.findList(orderService);
    }
    
    public Page<OrderService> findPage(Page<OrderService> page, OrderService orderService) {
        return super.findPage(page, orderService);
    }
    
    @Transactional(readOnly = false)
    public void save(OrderService orderService) {
        super.save(orderService);
    }
    
    @Transactional(readOnly = false)
    public void delete(OrderService orderService) {
        super.delete(orderService);
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
    public int cancel(OrderService orderService) {
        orderService.preUpdate();
        // 订单状态更新为：已取消
        int result = this.dao.cancel(orderService);
        // 影响条数为0，更新失败
        if (0 == result) {
            return result;
        }

        // 数据库中订单数据（参数是画面传递过来的信息）
        OrderService orderServiceInfo = super.get(orderService.getId());
        // 如果订单支付状态为1：已支付的话，执行退款处理
        if ("1".equals(orderServiceInfo.getPayState())) {
            orderServiceInfo.preUpdate();
            // 将该订单的支付状态改为2：退款中
            orderServiceInfo.setPayState("2");
            super.save(orderServiceInfo);

            // 生成退款信息，交由总后台进行退款
            OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
            orderRefundInfo.setBusinessInfoId(orderServiceInfo.getBusinessInfoId());
            orderRefundInfo.setOrderNo(orderServiceInfo.getOrderNo());
            // 因为是商品订单发生退款，所以固定为0：商品类
            orderRefundInfo.setOrderType("0");
            orderRefundInfo.setPayType(orderServiceInfo.getPayType());
            orderRefundInfo.setOrderMoney(orderServiceInfo.getPayMoney().toString());
            // 终端类型固定为商家后台
            orderRefundInfo.setType("2");
            orderRefundInfo.setModuleManageId(orderServiceInfo.getModuleManageId());
            // 产品模式固定为0:商品购买
            orderRefundInfo.setProdType("0");
            orderRefundInfo.setRefundMoney(orderServiceInfo.getPayMoney().toString());
            orderRefundInfo.setRefundType(orderServiceInfo.getPayOrg());
            orderRefundInfoService.save(orderRefundInfo);
        }

        // 商品订单明细取得
        OrderServiceList orderServiceList = new OrderServiceList();
        // 根据订单号检索
        orderServiceList.setOrderNo(orderService.getOrderNo());
        // 取得该订单对应的信息
        List<OrderServiceList> orderServiceInfoList = orderServiceListService.findList(orderServiceList);

        List<String> serviceId = new ArrayList<String>();
        // 商品数量信息MAP
        Map<String, Integer> serviceStock = new HashMap<String, Integer>();
        for (OrderServiceList orderServiceListTemp : orderServiceInfoList) {
            serviceId.add(orderServiceListTemp.getServiceInfoId());
            // 算出该订单每种商品一共多少库存
            int stock = nullToZero(orderServiceListTemp.getPayCount())
                    + nullToZero(serviceStock.get(orderServiceListTemp.getServiceInfoId()));
            serviceStock.put(orderServiceListTemp.getServiceInfoId(), stock);
        }
        // 虽然逻辑上不会有商品为空的订单，为了程序的健壮性依然增加了该判断
        if (serviceId != null & serviceId.size() > 0) {
            // 对商品信息添加行级锁
            List<ServiceInfo> serviceInfoList = serviceInfoDao.findServiceInfoListForUpdate(serviceId);

            // 库存回退
            for (ServiceInfo serviceInfo : serviceInfoList) {
                serviceInfo.preUpdate();
                serviceInfo.setStock(nullToZero(serviceInfo.getStock()) + nullToZero(serviceStock.get(serviceInfo.getId())));
                serviceInfo.preUpdate();
                result = serviceInfoDao.update(serviceInfo);
                // 若更新失败则回滚事务
                if (0 == result) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }

        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderNo(orderService.getOrderNo());
        orderTrack.setStateMsg("已取消");
        orderTrack.setHandleMsg("商家取消订单（自动退款）");
        orderTrack.setRemarks(orderService.getCancelRemarks());
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
     * 订单完成
     * 
     * @param orderNo
     *            订单号
     * @return 更新结果
     */
    @Transactional(readOnly = false)
    public int complete(String orderNo) {
        OrderService orderService = new OrderService();
        orderService.setOrderNo(orderNo);
        orderService.setOverTime(new Date());
        orderService.preUpdate();
        int result = this.dao.complete(orderService);
        if (result == 0) {
            return result;
        }
        orderTrackService.saveOrdMsg(orderNo, "已完成", "完成服务/送达/已自提");
        return result;

    }
    
    /**
     * 订单接受
     * 
     * @param orderNo
     *            订单号
     * @return 更新结果
     */
    @Transactional(readOnly = false)
    public int accept(String orderNo) {
        OrderService orderService = new OrderService();
        orderService.setOrderNo(orderNo);
        orderService.preUpdate();
        int result = this.dao.accept(orderService);
        if (result == 0) {
            return result;
        }
        orderTrackService.saveOrdMsg(orderNo, "已受理", "商家已受理，服务中");
        return result;

    }

    /**
     * 查询本周服务订单个数
     * @return
     */
    public Integer findAllListCount() {
        return this.dao.findAllListCount();
    }

    /**
     * 查询本周服务订单总金额
     * @return
     */
    public Double findAllListMoney() {
        return this.dao.findAllListMoney();
    }
}