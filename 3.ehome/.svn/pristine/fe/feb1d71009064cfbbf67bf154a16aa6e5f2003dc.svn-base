package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.bean.CouponManageBean;
import com.its.modules.app.bean.GoodsInfoBean;
import com.its.modules.app.bean.OrderGoodsBean;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.dao.OrderGoodsDao;
import com.its.modules.app.dao.ShoppingCartDao;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.OrderGoodsList;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 订单-商品类Service
 * 
 * @author like
 * 
 * @version 2017-07-10
 */
@Service
@Transactional(readOnly = true)
public class OrderGoodsService extends CrudService<OrderGoodsDao, OrderGoods> {
	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	@Autowired
	private GoodsSkuPriceService goodsSkuPriceService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private OrderGoodsListService orderGoodsListService;

	@Autowired
	private OrderTrackService orderTrackService;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	public OrderGoods get(String id) {
		return super.get(id);
	}

	public List<OrderGoods> findList(OrderGoods orderGoods) {
		return super.findList(orderGoods);
	}

	public Page<OrderGoods> findPage(Page<OrderGoods> page, OrderGoods orderGoods) {
		return super.findPage(page, orderGoods);
	}

	@Transactional(readOnly = false)
	public void save(OrderGoods orderGoods) {
		super.save(orderGoods);
	}

	@Transactional(readOnly = false)
	public void update(OrderGoods orderGoods) {
		dao.update(orderGoods);
	}

	@Transactional(readOnly = false)
	public void delete(OrderGoods orderGoods) {
		super.delete(orderGoods);
	}

	/**
	 * 根据订单号获取订单信息
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderGoods getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}

	/**
	 * 生成订单
	 * 
	 * @param order
	 * @param business
	 * @param accountID
	 * @param villageInfoID
	 * @param moduleID
	 * @param couponID
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Map<String, String> createOderGoods(OrderGoods order, BusinessInfo business, String accountID, String villageInfoID, CouponManageBean couponManage) {
		Map<String, String> result = new HashMap<String, String>();
		order.setBusinessInfoId(business.getId());
		order.setProdType(AppGlobal.MODEL_GOODS);// 产品模式
		order.setOrderState(AppGlobal.ORDER_STATE_UNCHECK);// 订单状态
		order.setCheckOrderState(AppGlobal.ORDER_CHECK_STATE_UNDO);// 对账状态
		order.setCheckState(AppGlobal.ORDER_SETTLE_STATE_UNDO);// 结算状态
		order.setPayState(AppGlobal.ORDER_PAY_STATE_UNDO);// 支付状态：未支付
		// 模块ID
		String moduleID = moduleManageService.getModuleId(AppGlobal.MODEL_GOODS, business.getId());
		order.setModuleManageId(moduleID);
		// 处理购物车中的商品信息
		List<GoodsInfoBean> list = shoppingCartDao.getShoppingCartList(accountID, villageInfoID, business.getId());
		if (list.size() == 0) {
			result.put("code", Global.CODE_PROMOT);
			result.put("message", "购物车中无商品");
			return result;
		}
		List<OrderGoodsList> orderGoodsList = new ArrayList<OrderGoodsList>();
		double totalMoney = 0;// 订单金额
		for (GoodsInfoBean bean : list) {
			double price = 0;
			if (StringUtils.isNotBlank(bean.getGoodsSkuPriceID())) {// 与确认订单时的计算保持一致
				price = bean.getSkuBasePrice();
				if (bean.getSkuBenefitPrice() != null && bean.getSkuBenefitPrice() != 0) {
					price = bean.getSkuBenefitPrice();
				}
			} else {
				price = bean.getBasePrice();
				if (bean.getBenefitPrice() != null && bean.getBenefitPrice() != 0) {
					price = bean.getBenefitPrice();
				}
			}
			totalMoney += price * bean.getCartNumber();
			OrderGoodsList goods = new OrderGoodsList();
			goods.setBusinessInfoId(business.getId());
			goods.setGoodsInfoId(bean.getId());
			goods.setSerialNumbers(bean.getSerialNumbers());
			goods.setName(bean.getName());
			goods.setImgs(bean.getImgs());
			goods.setContent(bean.getContent());
			goods.setSkuKeyId(bean.getSkuKeyID());
			goods.setSkuValueId(bean.getSkuValueID());
			goods.setGoodsSkuPriceId(bean.getGoodsSkuPriceID());
			if (StringUtils.isNotBlank(bean.getGoodsSkuPriceID())) {
				goods.setBasePrice(bean.getSkuBasePrice());
				goods.setBenefitPrice(bean.getSkuBenefitPrice());
			} else {
				goods.setBasePrice(bean.getBasePrice());
				goods.setBenefitPrice(bean.getBenefitPrice());
			}
			goods.setGoodsSum(bean.getCartNumber());
			goods.setPaySumMoney(price * bean.getCartNumber());
			orderGoodsList.add(goods);
		}
		// 运费
		double distributeCharge = business.getDistributeCharge() != null ? business.getDistributeCharge() : 0;
		double distributeBenefit = 0;
		if ("1".equals(business.getFullDistributeFlag()) && business.getFullDistributeMoney() >= totalMoney) {
			distributeBenefit = business.getDistributeCharge();
		}
		order.setAddressMoney(distributeCharge);
		order.setAddressBenefit(distributeBenefit);
		// 处理商家满减活动
		double businessBenefit = businessInfoService.getCutDownMoney(business, totalMoney);
		// 处理优惠券
		double couponBenefit = 0;
		if (couponManage != null) {
			couponBenefit = couponManageService.calCouponMoney(couponManage, totalMoney);
			// 更改优惠券使用状态
			couponManageService.updateUserState(couponManage.getMemberDiscountId(), AppGlobal.DISCOUNT_USER_STATE_USED);
		}
		// 商品总金额 + 配送费 - 配送费减免
		order.setSumMoney(totalMoney + distributeCharge - distributeBenefit);
		order.setBenefitMoney(businessBenefit);// 商家优惠
		order.setCouponMoney(couponBenefit);// 优惠券减免
		// 商品总金额 + 配送费 - 配送费减免 - 商家优惠-优惠券减免
		order.setPayMoney(totalMoney + distributeCharge - distributeBenefit - businessBenefit - couponBenefit);
		/********************* 存入数据库 ***********************/

		String orderNo = sysCodeMaxService.getOrdNo(villageInfoID, moduleID);
		order.setOrderNo(orderNo);
		order.preInsert();
		dao.insert(order);
		// order = this.getByOrderNo(orderNo);
		for (OrderGoodsList goods : orderGoodsList) {
			goods.setOrderGoodsId(order.getId());
			goods.setOrderNo(order.getOrderNo());
			orderGoodsListService.save(goods);
			// 减少商品库存、增加已售数量
			int record = goodsInfoService.reduceGoodsInfoStock(goods.getGoodsInfoId(), goods.getGoodsSkuPriceId(), goods.getGoodsSum());
			if (record == 0) {// 库存不足
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚事务
				result.clear();
				result.put("code", Global.CODE_PROMOT);
				result.put("message", "您选择的商品库存不足，请重新下单！");
				return result;
			}
		}
		// 修改商品在购物车中的状态
		shoppingCartDao.updateOrderState(accountID, villageInfoID, business.getId());
		// 生成订单跟踪状态
		orderTrackService.createTrackSubmit(OrderGlobal.ORDER_GOODS, order.getId(), orderNo);
		result.clear();
		result.put("code", Global.CODE_SUCCESS);
		result.put("message", order.getId());
		return result;
	}

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGoodsBean
	 */
	public OrderGoodsBean getOrderGoodsByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.getOrderGoodsByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGoodsBean
	 */
	public OrderGoodsBean judgeOrderGoodsByOrderIdAndAccountId(String orderId, String accountId) {
		return dao.judgeOrderGoodsByOrderIdAndAccountId(orderId, accountId);
	}

	/**
	 * 更新商品订单状态
	 * 
	 * @param orderGoodsBean
	 *            商品订单信息
	 */
	public void updateState(OrderGoodsBean orderGoodsBean) {
		dao.updateState(orderGoodsBean);
	}

	/**
	 * 取消商品订单
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
		OrderGoodsBean orderGoodsBean = this.judgeOrderGoodsByOrderIdAndAccountId(orderId, accountId);
		if (orderGoodsBean == null) {
			return false;
		}

		int state = 0;
		if (OrderGlobal.ORDER_PAY_STATE_UNPAY.equals(orderGoodsBean.getPayState())) {
			// 待支付——取消订单——已取消
			state = 0;
			orderGoodsBean.setOrderState(OrderGlobal.ORDER_GOODS_CANCELED);
		} else {
			// 待处理——取消订单——退款中
			state = 1;
			orderGoodsBean.setPayState(OrderGlobal.ORDER_PAY_STATE_REFUNDING);
		}

		// 更新订单主表
		this.updateState(orderGoodsBean);
		// 恢复商品库存
		if (state == 0) {
			List<OrderGoodsList> orderGoodsLists = orderGoodsBean.getOrderGoodsLists();
			if (orderGoodsLists != null && orderGoodsLists.size() != 0) {
				for (OrderGoodsList orderGoodsList : orderGoodsLists) {
					// 更新商品库存，已售数量
					goodsInfoService.updateStockAndSellCount(orderGoodsList.getGoodsSum(), orderGoodsList.getGoodsInfoId());
					// 如果商品有规格，更新对应规格库存
					if (orderGoodsList.getSkuKeyId() != null && orderGoodsList.getSkuValueId() != null) {
						goodsSkuPriceService.updateStock(orderGoodsList.getGoodsSum(), orderGoodsList.getGoodsInfoId(), orderGoodsList.getSkuKeyId(), orderGoodsList.getSkuValueId());
					}
				}
			}
		}
		// 插入订单追踪
		OrderTrack orderTrack = new OrderTrack();
		orderTrackService.save(orderTrack);

		return true;
	}
}