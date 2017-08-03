package com.its.modules.order.entity;

import java.util.Date;

import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;
import com.its.modules.sys.utils.DictUtils;
/**
 * @author lq
 *  商户交易管理
 */
public class BusinessDeal extends DataEntity<BusinessDeal>{
	
	private static final long serialVersionUID = 1L;
 
	private String businessInfoId; //商家ID
	private String businessName; //商家名称
	private String moduleManageId;//模块类别ID
	private String moduleName;//模块类别名称
	private String prodType; //产品模式：0商品购买  1服务预约  2课程购买  3场地预约
	private String orderNo; //订单号
	private String sumMoney;//产品金额
	private String couponMoney;//优惠券优惠金额
	private String benefitMoney;//商家优惠金额
	private String payType;//支付方式(0在线支付)
	private String orderState;//订单状态:0待受理、1已受理、2配送中、3已完成、4已取消
	private String type;//终端类型(0 Android 1 IOS 2 商家后台)
	private Date createDate;//下单时间
	private Date beginCreateDate;//下单时间开始
	private Date endCreateDate;//下单时间结束
	private String provinceId;//省ID
	private String cityId;//城市ID
	private String villageInfoId;//楼盘ID
	
	private String filter;// 订单号 | 商户名称的过滤
	
	private String deliveryFee;//配送费
	private String orderAmount;//订单金额
	private String orderStateString;//订单状态 excel用
	
	
	 
	public BusinessDeal() {
		super();
	}
	
	public BusinessDeal(String id) {
	    super(id);
	}

	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	@ExcelField(title = "商家名称", type = 1, align = 2, sort = 1)
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getModuleManageId() {
		return moduleManageId;
	}

	public void setModuleManageId(String moduleManageId) {
		this.moduleManageId = moduleManageId;
	}

	@ExcelField(title = "模块类别", type = 1, align = 2, sort = 2)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@ExcelField(title = "产品模式", type = 1, align = 2, sort = 3 ,dictType = "prod_type")
	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	@ExcelField(title = "订单号", type = 1, align = 2, sort = 4)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@ExcelField(title = "产品金额", type = 1, align = 2, sort = 6)
	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}

	@ExcelField(title = "平台优惠", type = 1, align = 2, sort = 8)
	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	@ExcelField(title = "商家优惠", type = 1, align = 2, sort = 9)
	public String getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(String benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	@ExcelField(title = "付款方式", type = 1, align = 2, sort = 10, dictType = "payType")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@ExcelField(title = "终端来源", type = 1, align = 2, sort = 12, dictType = "terminal_source")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title = "下单时间", type = 1, align = 2, sort = 13)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@ExcelField(title = "配送/上门费", type = 1, align = 2, sort = 7)
	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	@ExcelField(title = "订单金额", type = 1, align = 2, sort = 5)
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	@ExcelField(title = "订单状态", type = 1, align = 2, sort = 11)
	public String getOrderStateString() {
		if(this.getProdType().equals("0")){//商品
			return DictUtils.getDictLabel(this.getOrderState(), "order_goods_state", "");
		}
		if(this.getProdType().equals("1")){//服务
			return DictUtils.getDictLabel(this.getOrderState(), "order_service_state", "");		
		}
		if(this.getProdType().equals("2")){//课程
			return DictUtils.getDictLabel(this.getOrderState(), "order_lesson_state", "");
		}
		if(this.getProdType().equals("3")){//场地
			return DictUtils.getDictLabel(this.getOrderState(), "order_field_state", "");
		}
		return this.getOrderState();
	}

	public void setOrderStateString(String orderStateString) {
		this.orderStateString = orderStateString;
	}
	
	
}
