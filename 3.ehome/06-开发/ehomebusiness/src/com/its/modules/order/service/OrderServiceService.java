/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.AlipayUtils;
import com.its.common.utils.HttpUtils;
import com.its.common.utils.NumberUtil;
import com.its.common.utils.WXUtils;
import com.its.common.utils.WXUtilsConfig;
import com.its.modules.order.entity.Account;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.entity.WalletDetail;
import com.its.modules.setup.dao.BusinessInfoDao;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

import com.its.modules.order.dao.AccountDao;
import com.its.modules.order.dao.OrderServiceDao;
import com.its.modules.order.dao.WalletDetailDao;

/**
 * 订单-服务类Service
 * 
 * @author liuhl
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceService extends CrudService<OrderServiceDao, OrderService> {

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;


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

    public OrderService get(String id) {
        return super.get(id);
    }

    public List<OrderService> findList(OrderService orderService) {
        return super.findList(orderService);
    }

    public Page<OrderService> findPage(Page<OrderService> page, OrderService orderService) {
        page.setOrderBy("a.create_date desc,a.order_no desc");
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
     * @param id
     *            订单id
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
            // 如果是支付宝的话
            if ("1".equals(orderServiceInfo.getPayOrg())) {
                // 更新订单表
                orderServiceInfo.preUpdate();
                // 将该订单的支付状态改为2：退款中
                orderServiceInfo.setPayState("2");
                super.save(orderServiceInfo);

                // 执行支付宝退款
                JSONObject refundResult = AlipayUtils.alipayRefundRequest(orderServiceInfo.getOrderNo(),
                        String.valueOf(orderServiceInfo.getPayMoney()), orderService.getCancelRemarks());
                if (refundResult != null && "10000".equals(refundResult.get("code"))) {
                    // 更新订单表
                    orderServiceInfo.preUpdate();
                    // 将该订单的支付状态改为3：已退款
                    orderServiceInfo.setPayState("3");
                    super.save(orderServiceInfo);
                    OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
                    orderRefundInfo.setOrderId(orderServiceInfo.getId());
                    orderRefundInfo.setOrderNo(orderServiceInfo.getOrderNo());
                    // 微信交易号
                    orderRefundInfo.setTransactionId(orderServiceInfo.getTransactionID());
                    // 因为是商品订单发生退款，所以固定为1：服务类
                    orderRefundInfo.setOrderType("1");
                    orderRefundInfo.setPayType(orderServiceInfo.getPayType());
                    orderRefundInfo.setOrderMoney(orderServiceInfo.getPayMoney());
                    // 终端类型固定为商家后台
                    orderRefundInfo.setType("2");
                    orderRefundInfo.setModuleManageId(orderServiceInfo.getModuleManageId());
                    // 产品模式固定为1:服务预约
                    orderRefundInfo.setProdType("1");
                    orderRefundInfo.setRefundMoney(orderServiceInfo.getPayMoney());

                    orderRefundInfo.setRefundNo(orderServiceInfo.getOrderNo());
                    // 微信退款单号
                    orderRefundInfo.setRefundTransactionId(refundResult.get("out_trade_no").toString());
                    orderRefundInfo.setRefundType(orderServiceInfo.getPayOrg());

                    // 退款状态：退款成功
                    orderRefundInfo.setRefundState("2");

                    // 退款完成时间
                    orderRefundInfo.setRefundOverTime(new Date());

                    // 退款原因
                    orderRefundInfo.setRefundReason(orderService.getCancelRemarks());

                    orderRefundInfoService.save(orderRefundInfo);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
                // 如果是微信的话，直接调用接口进行退款
            } else if ("0".equals(orderServiceInfo.getPayOrg())) {
                // 以订单号为退款号
                Map<String, String> refund_result = WXUtils.doRefund(orderServiceInfo.getOrderNo(),
                        orderServiceInfo.getOrderNo(),
                        // 微信退款是以分为单位
                        NumberUtil.yuanToFen(orderServiceInfo.getPayMoney()),
                        // 微信退款是以分为单位
                        NumberUtil.yuanToFen(orderServiceInfo.getPayMoney()),
                        // 退款原因
                        orderService.getCancelRemarks());
                // 订单支付成功的话
                if (StringUtils.isNotBlank(refund_result.get("result_code"))
                        && WXUtilsConfig.SUCCESS.equals(refund_result.get("result_code"))) {
                    // 更新订单表
                    orderServiceInfo.preUpdate();
                    // 将该订单的支付状态改为3：已退款
                    orderServiceInfo.setPayState("3");
                    super.save(orderServiceInfo);
                    OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
                    orderRefundInfo.setOrderId(orderServiceInfo.getId());
                    orderRefundInfo.setOrderNo(orderServiceInfo.getOrderNo());
                    // 微信交易号
                    orderRefundInfo.setTransactionId(orderServiceInfo.getTransactionID());
                    // 因为是商品订单发生退款，所以固定为1：服务类
                    orderRefundInfo.setOrderType("1");
                    orderRefundInfo.setPayType(orderServiceInfo.getPayType());
                    orderRefundInfo.setOrderMoney(orderServiceInfo.getPayMoney());
                    // 终端类型固定为商家后台
                    orderRefundInfo.setType("2");
                    orderRefundInfo.setModuleManageId(orderServiceInfo.getModuleManageId());
                    // 产品模式固定为0:服务预约
                    orderRefundInfo.setProdType("1");
                    orderRefundInfo.setRefundMoney(orderServiceInfo.getPayMoney());

                    orderRefundInfo.setRefundNo(orderServiceInfo.getOrderNo());
                    // 微信退款单号
                    orderRefundInfo.setRefundTransactionId(refund_result.get("refund_id"));
                    orderRefundInfo.setRefundType(orderServiceInfo.getPayOrg());

                    // 退款状态：退款成功
                    orderRefundInfo.setRefundState("2");

                    // 退款完成时间
                    orderRefundInfo.setRefundOverTime(new Date());

                    // 退款原因
                    orderRefundInfo.setRefundReason(orderService.getCancelRemarks());

                    orderRefundInfoService.save(orderRefundInfo);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                // 如果是用户钱包支付的话
            } else if ("2".equals(orderServiceInfo.getPayOrg())) {
                logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "退款开始");
                // 取得用户信息,并施加行级锁
                Account userInfo = accountDao.getForUpdate(orderService.getAccountId());
                if (userInfo == null) {
                    logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "，取得不到用户信息");
                    logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "退款异常结束");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                // 根据订单ID取得支付明细（即本金支付多少，赠送金额支付多少）
                WalletDetail payInfo = walletDetailDao.getByOrderId(orderService.getId());

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
                    logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "，钱包余额修改失败");
                    logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "退款异常结束");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return result;
                }

                // 钱包明细追加一条退款记录
                WalletDetail walletDetail = new WalletDetail();
                walletDetail.preInsert();
                // 会员ID
                walletDetail.setAccountId(orderService.getAccountId());
                // 楼盘ID
                walletDetail.setVillageInfoId(orderService.getVillageInfoId());
                // 订单ID
                walletDetail.setOrderId(orderService.getId());
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
                logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "，退款金额如下：");
                logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "，钱包本金：" + payInfo.getWalletPrincipal());
                logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "，钱包赠送金额：" + payInfo.getWalletPresent());
                logger.warn("用户钱包退款------------>订单号：" + orderServiceInfo.getOrderNo() + "退款正常结束");
            }
        }

        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderId(orderService.getId());
        orderTrack.setOrderNo(orderService.getOrderNo());
        orderTrack.setOrderType(orderService.getProdType());
        orderTrack.setStateMsg("已取消");
        orderTrack.setHandleMsg("商家已取消");
        orderTrack.setStateMsgPhone("已取消");
        orderTrack.setHandleMsgPhone("订单已成功取消");
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            orderTrack.setCreateName(user.getId());
        }
        orderTrack.setRemarks(orderService.getCancelRemarks());
        orderTrackService.save(orderTrack);

        // 向用户推送订单取消信息
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("businessId", user.getBusinessinfoId());
        BusinessInfo businessInfo = businessInfoDao.get(user.getBusinessinfoId());
        paramMap.put("businessName", (businessInfo != null) ? businessInfo.getBusinessName() : "");
        paramMap.put("cancelReason", orderService.getCancelRemarks());
        paramMap.put("orderId", orderServiceInfo.getId());
        paramMap.put("toUserId", orderServiceInfo.getAccountId());
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
     * 订单完成
     * 
     * @param ID
     *            订单ID
     * @return 更新结果
     */
    @Transactional(readOnly = false)
    public int complete(String id) {
        OrderService orderService = super.get(id);
        orderService.preUpdate();
        orderService.setOverTime(new Date());
        orderService.preUpdate();
        int result = this.dao.complete(orderService);
        if (result == 0) {
            return result;
        }
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderId(orderService.getId());
        orderTrack.setOrderNo(orderService.getOrderNo());
        orderTrack.setOrderType(orderService.getProdType());
        orderTrack.setStateMsg("已完成");
        orderTrack.setHandleMsg("完成服务/送达/已自提");
        orderTrack.setStateMsgPhone("已完成");
        orderTrack.setHandleMsgPhone("感谢您的订购");
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            orderTrack.setCreateName(user.getId());
        }
        orderTrack.setRemarks(orderService.getCancelRemarks());
        orderTrackService.save(orderTrack);
        return result;

    }

    /**
     * 订单接受
     * 
     * @param id
     *            订单ID
     * @return 更新结果
     */
    @Transactional(readOnly = false)
    public int accept(String id) {
        OrderService orderService = super.get(id);
        orderService.preUpdate();
        int result = this.dao.accept(orderService);
        if (result == 0) {
            return result;
        }
        OrderTrack orderTrack = new OrderTrack();
        orderTrack.setOrderId(orderService.getId());
        orderTrack.setOrderNo(orderService.getOrderNo());
        orderTrack.setOrderType(orderService.getProdType());
        orderTrack.setStateMsg("已受理");
        orderTrack.setHandleMsg("商家已受理，服务中");
        orderTrack.setStateMsgPhone("已受理");
        orderTrack.setHandleMsgPhone("商家已受理，服务中");
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            orderTrack.setCreateName(user.getId());
        }
        orderTrack.setRemarks(orderService.getCancelRemarks());
        orderTrackService.save(orderTrack);
        return result;

    }

    /**
     * 查询本周服务订单个数
     * 
     * @return
     */
    public Integer findAllListCount() {
        return this.dao.findAllListCount();
    }

    /**
     * 查询本周服务订单总金额
     * 
     * @return
     */
    public Double findAllListMoney() {
        return this.dao.findAllListMoney();
    }
}