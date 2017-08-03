package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.bean.OrderGroupPurcBean;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.OrderGroupPurcDao;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderGroupPurcList;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 订单-团购类Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
@Service
@Transactional(readOnly = true)
public class OrderGroupPurcService extends CrudService<OrderGroupPurcDao, OrderGroupPurc> {

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private VillageInfoService villageInfoService;

	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	@Autowired
	private OrderGroupPurcListService orderGroupPurcListService;

	@Autowired
	private OrderTrackService orderTrackService;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private GroupPurchasetimeService groupPurchasetimeService;

	public OrderGroupPurc get(String id) {
		return super.get(id);
	}

	public List<OrderGroupPurc> findList(OrderGroupPurc orderGroupPurc) {
		return super.findList(orderGroupPurc);
	}

	public Page<OrderGroupPurc> findPage(Page<OrderGroupPurc> page, OrderGroupPurc orderGroupPurc) {
		return super.findPage(page, orderGroupPurc);
	}

	@Transactional(readOnly = false)
	public void save(OrderGroupPurc orderGroupPurc) {
		super.save(orderGroupPurc);
	}

	@Transactional(readOnly = false)
	public void update(OrderGroupPurc orderGroupPurc) {
		dao.update(orderGroupPurc);
	}

	@Transactional(readOnly = false)
	public void delete(OrderGroupPurc orderGroupPurc) {
		super.delete(orderGroupPurc);
	}

	/**
	 * 获取某精品团购用户已购数量
	 * 
	 * @param accountId
	 *            用户ID
	 * @param groupPurchaseId
	 *            团购ID
	 * @return 某精品团购用户已购数量
	 */
	public int getCountByGroupPurcIdAndAccountId(String accountId, String groupPurchaseId) {
		return dao.getCountByGroupPurcIdAndAccountId(accountId, groupPurchaseId);
	}

	/**
	 * 根据订单号获取团购券订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderGroupPurc
	 */
	public OrderGroupPurc getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}

	/**
	 * 保存团购订单和团购清单
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createOrderGroupPurc(Account account, String contactPhone, GroupPurchaseBean groupPurchaseBean, int payNumber, String leaveMessage) {
		BusinessInfo businessInfo = businessInfoService.get(groupPurchaseBean.getBusinessinfoId());
		VillageInfo villageInfo = villageInfoService.get(account.getVillageInfoId());
		double totalMoney = payNumber * ValidateUtil.validateDouble(groupPurchaseBean.getGroupPurcMoney());

		/* 生成订单开始 */
		OrderGroupPurc orderGroupPurc = new OrderGroupPurc();
		String moduleManageId = groupPurchaseBean.getModuleId();
		String orderNo = sysCodeMaxService.getOrdNo(villageInfo.getId(), moduleManageId);
		orderGroupPurc.setBusinessInfoId(businessInfo.getId());
		orderGroupPurc.setOrderNo(orderNo);
		orderGroupPurc.setModuleManageId(moduleManageId);
		// 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
		orderGroupPurc.setProdType(OrderGlobal.MODEL_LESSON);
		// 终端类型(0 Android 1 IOS 2 商家后台)
		orderGroupPurc.setType(null);
		orderGroupPurc.setVillageInfoId(villageInfo.getId());
		orderGroupPurc.setProvinceId(villageInfo.getAddrPro());
		orderGroupPurc.setCityId(villageInfo.getAddrCity());
		// 团购订单主表存团购主表ID
		orderGroupPurc.setGroupPurchaseId(groupPurchaseBean.getId());
		orderGroupPurc.setName(groupPurchaseBean.getGroupPurcName());
		orderGroupPurc.setBasePrice(groupPurchaseBean.getMarketMoney());
		orderGroupPurc.setGroupPurcPrice(groupPurchaseBean.getGroupPurcMoney());
		orderGroupPurc.setPayNumber(payNumber);
		orderGroupPurc.setSumMoney(totalMoney);
		orderGroupPurc.setPayMoney(totalMoney);
		// 订单状态:0未消费、1已消费、2退款处理中、3已退款
		orderGroupPurc.setOrderState(OrderGlobal.ORDER_GROUP_PURHCASE_UNCONSUME);
		// 支付对账状态:0未对账1正常2异常
		orderGroupPurc.setCheckOrderState(OrderGlobal.ORDER_CHECK_STATE_UNDO);
		// 结算状态:0未结算1已结算
		orderGroupPurc.setCheckState(OrderGlobal.ORDER_SETTLE_STATE_UNDO);
		orderGroupPurc.setAccountId(account.getId());
		orderGroupPurc.setAccountName(account.getNickname());
		orderGroupPurc.setAccountPhoneNumber(contactPhone);
		orderGroupPurc.setAccountMsg(leaveMessage);
		// 支付状态:0未支付1已支付2退款中3已退款
		orderGroupPurc.setPayState(OrderGlobal.ORDER_PAY_STATE_UNPAY);
		this.save(orderGroupPurc);

		String orderGroupPurcId = this.getByOrderNo(orderNo).getId();
		// 每张团购券生成一条子表数据
		for (int i = 0; i < payNumber; i++) {
			OrderGroupPurcList orderGroupPurcList = new OrderGroupPurcList();
			orderGroupPurcList.setBusinessInfoId(businessInfo.getId());
			// 团购订单子表存团购子表ID
			orderGroupPurcList.setGroupPurchaseId(groupPurchaseBean.getGroupPurchaseTimeId());
			orderGroupPurcList.setOrderGroupPurcId(orderGroupPurcId);
			orderGroupPurcList.setOrderNo(orderNo);
			orderGroupPurcList.setName(groupPurchaseBean.getGroupPurcName());
			orderGroupPurcList.setImgs(groupPurchaseBean.getGroupPurcPic());
			orderGroupPurcList.setBasePrice(groupPurchaseBean.getMarketMoney());
			orderGroupPurcList.setGroupPurcPrice(groupPurchaseBean.getGroupPurcMoney());
			orderGroupPurcList.setStartTime(groupPurchaseBean.getValidityStartTime());
			orderGroupPurcList.setEndTime(groupPurchaseBean.getValidityEndTime());
			orderGroupPurcList.setContent(groupPurchaseBean.getGroupPurcDetail());
			orderGroupPurcList.setUseTime(groupPurchaseBean.getUseTime());
			orderGroupPurcList.setUseContent(groupPurchaseBean.getUseRule());
			orderGroupPurcList.setPaySumMoney(groupPurchaseBean.getGroupPurcMoney());
			orderGroupPurcList.setGroupPurcNumber(this.getGroupPurcNumber());
			orderGroupPurcList.setGroupPurcState(OrderGlobal.GROUP_PURHCASE_STATE_UNCONSUME);
			orderGroupPurcListService.save(orderGroupPurcList);
		}
		/* 生成订单结束 */

		// 修改团购已购数量
		groupPurchasetimeService.updateSaleNum(payNumber, groupPurchaseBean.getGroupPurchaseTimeId());
		// 插入订单追踪
		OrderTrack orderTrack = new OrderTrack();

		return orderGroupPurcId;
	}

	/**
	 * 生成12位数字随机数（团购券号）
	 * 
	 * @return 团购券号
	 */
	public String getGroupPurcNumber() {
		return DateFormatUtils.format(new Date(), "MMddHH") + ((int) (Math.random() * 900000) + 100000);
	}

	/**
	 * 获取某用户某楼盘下的团购类订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID
	 * @return List<OrderFieldBean>
	 */
	public List<OrderGroupPurcBean> getOrderGroupPurcList(String villageInfoId, String accountId, String moduleManageId) {
		return dao.getOrderGroupPurcList(villageInfoId, accountId, moduleManageId);
	}

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGroupPurcBean
	 */
	public OrderGroupPurcBean getOrderGroupPurcByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.getOrderGroupPurcByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGroupPurc
	 */
	public OrderGroupPurc judgeOrderGroupPurcByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.judgeOrderGroupPurcByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 取消团购订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return 取消成功返回true，失败返回false
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean cancelOrder(String orderId, String accountId) {
		// 判断订单是否可取消
		OrderGroupPurc orderGroupPurc = this.judgeOrderGroupPurcByOrderIdAndAccountId(orderId, accountId);
		if (orderGroupPurc == null) {
			return false;
		}

		// 待支付——取消订单——已取消
		orderGroupPurc.setOrderState(OrderGlobal.ORDER_GOODS_CANCELED);

		// 更新订单主表
		this.update(orderGroupPurc);
		// 恢复团购库存
		GroupPurchaseBean groupPurchaseBean = groupPurchaseService.getGroupPurchaseBean(orderGroupPurc.getGroupPurchaseId());
		groupPurchaseBean.setStockNum(groupPurchaseBean.getStockNum() + orderGroupPurc.getPayNumber());
		groupPurchaseBean.setSaleNum(groupPurchaseBean.getSaleNum() - orderGroupPurc.getPayNumber());
		groupPurchaseService.update(groupPurchaseBean);
		// 插入订单追踪
		OrderTrack orderTrack = new OrderTrack();
		orderTrackService.save(orderTrack);

		return true;
	}
}