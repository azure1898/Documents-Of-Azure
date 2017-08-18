/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.its.common.utils.DateUtils;
import com.its.common.utils.HttpUtils;
import com.its.common.utils.NumberUtil;
import com.its.common.utils.WXUtils;
import com.its.common.utils.WXUtilsConfig;
import com.its.modules.field.dao.FieldPartitionPriceDao;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.service.SysCodeMaxService;
import com.its.modules.sys.utils.UserUtils;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.Account;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.dao.AccountDao;
import com.its.modules.order.dao.OrderFieldDao;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.entity.WalletDetail;
import com.its.modules.setup.dao.BusinessInfoDao;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.order.dao.OrderFieldListDao;
import com.its.modules.order.dao.WalletDetailDao;

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

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /** 钱包明细Dao */
    @Autowired
    private WalletDetailDao walletDetailDao;

    /**
     * 会员信息DAO
     */
    @Autowired
    private AccountDao accountDao;

    /** 商户信息Dao */
    @Autowired
    private BusinessInfoDao businessInfoDao;

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
        page.setOrderBy("a.order_no desc");
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
        orderField.setProdType("3");
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
        orderTrack.setCreateName("商家账号");
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
        try {
            // 订单信息取得（根据画面传来的信息取得全部数据）
            OrderField orderField = super.get(orderFieldFromJSP.getId());

            // 订单-场地预约类子表-场地清单信息
            List<OrderFieldList> orderFieldLists = orderFieldListDao
                    .getOrderFieldListByOrderNo(orderField.getOrderNo());
            // 订单-场地预约类子表-场地清单(Order_Field_List)订单状态更新
            // 订单状态变更为：已取消
            // 执行更新处理，并返回影响条数
            int result = orderFieldListDao.cancelOrderFieldListByOrderNo(orderField.getOrderNo());
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

            // 如果订单支付状态为1：已支付且是用户在线支付的话，执行退款处理
            if ("0".equals(orderField.getPayType()) && "1".equals(orderField.getPayState())) {
                // 如果是支付宝的话，生成退款信息，交由总后台进行退款
                if ("1".equals(orderField.getPayOrg())) {
                    // 订单状态更新（订单状态变更为：已取消，支付状态变为：退款中）
                    // 订单状态变更为：已取消
                    orderField.setOrderState("2");
                    // 支付状态变为：退款中
                    orderField.setPayState("2");
                    orderField.preUpdate();

                    // 执行更新处理，并返回影响条数
                    result = this.dao.update(orderField);

                    // 若影响条数不是1
                    if (1 != result) {
                        // 手动回滚，并终止操作
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return 0;
                    }

                    OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
                    orderRefundInfo.setOrderId(orderField.getId());
                    orderRefundInfo.setOrderNo(orderField.getOrderNo());
                    // 支付宝交易号
                    orderRefundInfo.setTransactionId(orderField.getTransactionID());
                    // 因为是商品订单发生退款，所以固定为3：场地类
                    orderRefundInfo.setOrderType("3");
                    orderRefundInfo.setPayType(orderField.getPayType());
                    orderRefundInfo.setOrderMoney(orderField.getPayMoney());
                    // 终端类型固定为商家后台
                    orderRefundInfo.setType("2");
                    orderRefundInfo.setModuleManageId(orderField.getModuleManageId());
                    // 产品模式固定为3:场地预约
                    orderRefundInfo.setProdType("3");
                    orderRefundInfo.setRefundMoney(orderField.getPayMoney());
                    orderRefundInfo.setRefundType(orderField.getPayOrg());
                    // 退款状态：待退款
                    orderRefundInfo.setRefundState("0");
                    orderRefundInfoService.save(orderRefundInfo);
                    // 如果是微信的话，直接调用接口进行退款
                } else if ("0".equals(orderField.getPayOrg())) {
                    // 以订单号为退款号
                    Map<String, String> refund_result = WXUtils.doRefund(orderField.getOrderNo(),
                            orderField.getOrderNo(),
                            // 微信退款是以分为单位
                            NumberUtil.yuanToFen(orderField.getPayMoney()),
                            // 微信退款是以分为单位
                            NumberUtil.yuanToFen(orderField.getPayMoney()),
                            // 退款原因
                            orderFieldFromJSP.getCancelRemarks());
                    // 订单支付成功的话
                    if (StringUtils.isNotBlank(refund_result.get("result_code"))
                            && WXUtilsConfig.SUCCESS.equals(refund_result.get("result_code"))) {
                        // 更新订单表
                        orderField.preUpdate();
                        // 订单状态变更为：已取消
                        orderField.setOrderState("2");
                        // 将该订单的支付状态改为3：已退款
                        orderField.setPayState("3");
                        // 执行更新处理，并返回影响条数
                        result = this.dao.update(orderField);

                        // 若影响条数不是1
                        if (1 != result) {
                            // 手动回滚，并终止操作
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return 0;
                        }

                        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
                        orderRefundInfo.setOrderId(orderField.getId());
                        orderRefundInfo.setOrderNo(orderField.getOrderNo());
                        // 微信交易号
                        orderRefundInfo.setTransactionId(orderField.getTransactionID());
                        // 因为是商品订单发生退款，所以固定为3：场地类
                        orderRefundInfo.setOrderType("3");
                        orderRefundInfo.setPayType(orderField.getPayType());
                        orderRefundInfo.setOrderMoney(orderField.getPayMoney());
                        // 终端类型固定为商家后台
                        orderRefundInfo.setType("2");
                        orderRefundInfo.setModuleManageId(orderField.getModuleManageId());
                        // 产品模式固定为2:场地预约
                        orderRefundInfo.setProdType("3");
                        orderRefundInfo.setRefundMoney(orderField.getPayMoney());

                        orderRefundInfo.setRefundNo(orderField.getOrderNo());
                        // 微信退款单号
                        orderRefundInfo.setRefundTransactionId(refund_result.get("refund_id"));
                        orderRefundInfo.setRefundType(orderField.getPayOrg());

                        // 退款状态：退款成功
                        orderRefundInfo.setRefundState("2");

                        // 退款完成时间
                        orderRefundInfo.setRefundOverTime(new Date());

                        // 退款原因
                        orderRefundInfo.setRefundReason(orderFieldFromJSP.getCancelRemarks());

                        orderRefundInfoService.save(orderRefundInfo);
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                    // 如果是用户钱包支付的话
                } else if ("2".equals(orderField.getPayOrg())) {
                    // 取得用户信息,并施加行级锁
                    Account userInfo = accountDao.getForUpdate(orderField.getAccountId());
                    if (userInfo == null) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                    // 根据订单ID取得支付明细（即本金支付多少，赠送金额支付多少）
                    WalletDetail payInfo = walletDetailDao.getByOrderId(orderField.getId());

                    // 钱包本金追加
                    userInfo.setWalletPrincipal(userInfo.getWalletPrincipal() + payInfo.getWalletPrincipal());
                    // 钱包赠送金额追加
                    userInfo.setWalletPresent(userInfo.getWalletPresent() + payInfo.getWalletPresent());
                    // 钱包余额追加(当前余额 + 本金支付金额 + 赠送支付金额)
                    userInfo.setWalletBalance(
                            userInfo.getWalletBalance() + payInfo.getWalletPrincipal() + payInfo.getWalletPresent());

                    // 执行更新处理
                    userInfo.preUpdate();
                    result = accountDao.update(userInfo);
                    // 若更新失败则回滚事务
                    if (result == 0) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }

                    // 钱包明细追加一条退款记录
                    WalletDetail walletDetail = new WalletDetail();
                    walletDetail.preInsert();
                    // 会员ID
                    walletDetail.setAccountId(orderField.getAccountId());
                    // 楼盘ID
                    walletDetail.setVillageInfoId(orderField.getVillageInfoId());
                    // 订单ID
                    walletDetail.setOrderId(orderField.getId());
                    // 交易类型：5-退款(订单取消)
                    walletDetail.setTradeType("5");
                    // 本金退款金额
                    walletDetail.setWalletPrincipal(payInfo.getWalletPrincipal());
                    // 赠送金退款金额
                    walletDetail.setWalletPresent(payInfo.getWalletPresent());
                    // 退款时间
                    walletDetail.setTradeDate(new Date());
                    // 支付类型
                    walletDetail.setPayType("0");
                    // 执行插入操作
                    walletDetailDao.insert(walletDetail);
                }
            } else {
                // 订单状态更新（订单状态变更为：已取消，支付状态变为：退款中）
                // 订单状态变更为：已取消
                orderField.setOrderState("2");
                if ("2".equals(orderField.getType())) {
                    // 将该订单的支付状态改为3：已退款
                    orderField.setPayState("3");
                }
                orderField.preUpdate();

                // 执行更新处理，并返回影响条数
                result = this.dao.update(orderField);

                // 若影响条数不是1
                if (1 != result) {
                    // 手动回滚，并终止操作
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return 0;
                }
            }

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
            orderTrack.setCreateName("商家账号");
            orderTrack.setRemarks(orderFieldFromJSP.getCancelRemarks());
            orderTrackService.save(orderTrack);

            orderTrack = new OrderTrack();
            orderTrack.setOrderNo(orderField.getOrderNo());
            orderTrack.setBusinessInfoId(orderField.getBusinessInfoId());
            orderTrack.setOrderId(orderField.getId());
            orderTrack.setOrderType(orderField.getProdType());
            orderTrack.setStateMsgPhone("退款中");
            orderTrack.setHandleMsgPhone("订单开始退款，等待退款");
            orderTrack.setCreateName("商家账号");
            orderTrackService.save(orderTrack);
            
            // 从SESSION中取得商家信息
            User user = UserUtils.getUser();
            // 向用户推送订单取消信息
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("businessId", user.getBusinessinfoId());
            BusinessInfo businessInfo = businessInfoDao.get(user.getBusinessinfoId());
            paramMap.put("businessName", (businessInfo != null) ? businessInfo.getBusinessName() : "");
            paramMap.put("cancelReason", orderFieldFromJSP.getCancelRemarks());
            paramMap.put("orderId", orderField.getId());
            paramMap.put("toUserId", orderField.getAccountId());
            paramMap.put("sendType", "2.2");

            JSONObject msg_result = HttpUtils.sendPost("/rongCloudMsg/cancelOrderMsg", paramMap);
            // 若信息发送失败则回滚
            if (!"1000".equals(String.valueOf(msg_result.get("code")))) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
}