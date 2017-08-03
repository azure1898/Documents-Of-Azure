package com.its.modules.order.entity;

import java.util.Date;
import java.util.List;

import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;
/**
 * @author lq
 *  物业交易管理
 */
public class PropertyDeal extends DataEntity<PropertyDeal>{
	
	private static final long serialVersionUID = 1L;
 
	private String propertyCompanyId;//物業ID
	private String companyName;//物業名稱
	private String moduleManageId;//模塊id
	private String moduleName;//模塊類別
	private String villageInfoId;//楼盘ID
	private String orderNo;//订单号
	private String orderMoney;//订单金额
	private String discountMoney;//优惠金额
	private String payType;//支付方式(0在线支付)
	private String payState;//支付状态:0未支付1已支付2退款中3已退款
	private String terminalSource;//终端来源：0-Android；1-IOS
	private Date createDate;//下单时间
	private Date beginCreateDate;//下单时间开始
	private Date endCreateDate;//下单时间结束
	private List<PropertyBillDetail> propertyBillDetails;//物业缴费详情
	
	private String nickName;//会员姓名
	private String phoneNum;//会员电话
	private String payUserName;//支付账户
	private String discountId;//优惠券号
	private String payMoney;// 实付金额
	private Date payTime;// 支付时间
	 
	public PropertyDeal() {
		super();
	}
	
	public PropertyDeal(String id) {
	    super(id);
	}

	@ExcelField(title = "物业名称", type = 1, align = 2, sort = 1)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@ExcelField(title = "模块类别", type = 1, align = 2, sort = 2)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(String propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}


	public String getModuleManageId() {
		return moduleManageId;
	}

	public void setModuleManageId(String moduleManageId) {
		this.moduleManageId = moduleManageId;
	}

	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@ExcelField(title = "订单号", type = 1, align = 2, sort = 3)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@ExcelField(title = "订单金额", type = 1, align = 2, sort = 4)
	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	@ExcelField(title = "平台优惠", type = 1, align = 2, sort = 5)
	public String getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

	@ExcelField(title = "付款方式", type = 1, align = 2, sort = 6, dictType = "payType")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@ExcelField(title = "支付状态", type = 1, align = 2, sort = 7, dictType = "Pay_State")
	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	@ExcelField(title = "终端来源", type = 1, align = 2, sort = 8, dictType = "terminal_source")
	public String getTerminalSource() {
		return terminalSource;
	}

	public void setTerminalSource(String terminalSource) {
		this.terminalSource = terminalSource;
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

	@ExcelField(title = "下单时间", type = 1, align = 2, sort = 9)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<PropertyBillDetail> getPropertyBillDetails() {
		return propertyBillDetails;
	}

	public void setPropertyBillDetails(List<PropertyBillDetail> propertyBillDetails) {
		this.propertyBillDetails = propertyBillDetails;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	
}
