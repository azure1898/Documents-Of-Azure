/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
import com.its.common.utils.excel.ImportExcel;
import com.its.common.web.BaseController;
import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.entity.BusinessServicescope;
import com.its.modules.business.service.BusinessInfoService;
import com.its.modules.business.service.BusinessServicescopeService;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.operation.entity.CouponManage;
import com.its.modules.operation.entity.CouponManageUsers;
import com.its.modules.operation.entity.MemberDiscount;
import com.its.modules.operation.entity.Mobile;
import com.its.modules.operation.service.CouponManageService;
import com.its.modules.operation.service.CouponManageUsersService;
import com.its.modules.operation.service.MemberDiscountService;
import com.its.modules.sys.service.SysCodeMaxService;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.VillageInfoService;

/**
 * 优惠券管理Controller
 * 
 * @author liuqi
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/couponManage")
public class CouponManageController extends BaseController {

	@Autowired
	private CouponManageService couponManageService;

	@Autowired
	private ModuleManageService moduleManageService;

	@Autowired
	private BusinessServicescopeService businessServicescopeService;

	@Autowired
	private BusinessInfoService businessInfoService;

	@Autowired
	private CouponManageUsersService couponManageUsersService;

	@Autowired
	private MemberDiscountService memberDiscountService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private VillageInfoService villageInfoService;
	
	@Autowired
	private SysCodeMaxService sysCodeMaxService;

	@ModelAttribute
	public CouponManage get(@RequestParam(required = false) String id) {
		CouponManage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = couponManageService.get(id);
		}
		if (entity == null) {
			entity = new CouponManage();
		}
		return entity;
	}

	@RequiresPermissions("operation:couponManage:view")
	@RequestMapping(value = { "list", "" })
	public String list(CouponManage couponManage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CouponManage> page = couponManageService.findPage(new Page<CouponManage>(request, response), couponManage);

		// 列表显示楼盘名称
		for (CouponManage c : page.getList()) {
			String villageInfoId = c.getVillageInfoId();
			VillageInfo villageInfo = villageInfoService.get(villageInfoId);
			if (villageInfo != null) {
				c.setVillageName(villageInfo.getVillageName());
			}

			// 根据查询的结果及活动的起始结束时间修改active_state
			String activeState = new String();
			if (c.getActiveStartTime().after(new Date())) {
				activeState = "0";
			} else if (c.getActiveStartTime().before(new Date()) && c.getActiveEndTime().after(new Date())) {
				// 活动已经开始
				activeState = "1";
			} else {
				activeState = "2";
			}
			// 若活动状态发生变更，则保存新的活动状态
			String currentActiveState = c.getActiveState();
			if (currentActiveState == null || !currentActiveState.equals(activeState)) {
				c.setActiveState(activeState);
				couponManageService.save(c);
			}

			// 添加领取总量
			MemberDiscount memberDiscount = new MemberDiscount();
			memberDiscount.setDiscountId(c.getId());
			int receiveCount = 0;
			List<MemberDiscount> memberDiscountList = memberDiscountService.findList(memberDiscount);
			if (memberDiscountList != null && memberDiscountList.size() > 0) {
				receiveCount = memberDiscountList.size();
			}
			c.setReceiveCount(receiveCount);
		}

		model.addAttribute("page", page);
		return "modules/operation/couponManageList";
	}

	@RequiresPermissions("operation:couponManage:view")
	@RequestMapping(value = "form")
	public String form(CouponManage couponManage, Model model) {
		model.addAttribute("couponManage", couponManage);

		// 添加服务品类
		List<ModuleManage> moduleManageList = moduleManageService.getLifeModule();
		model.addAttribute("allLifeModule", moduleManageList);

		// 添加商家信息
		BusinessServicescope businessServicescope = new BusinessServicescope();
		List<BusinessInfo> businessInfoList = new ArrayList<BusinessInfo>();
		String villageInfoId = couponManage.getVillageInfoId();
		if (villageInfoId != null) {// 修改时的时候，villageInfoId不为空
			businessServicescope.setVillageInfoId(villageInfoId);

			// 根据楼盘ID（villageInfoId）获取商家信息
			List<BusinessServicescope> businessServicescopeList = businessServicescopeService
					.findList(businessServicescope);
			for (BusinessServicescope bss : businessServicescopeList) {
				BusinessInfo businessInfo = businessInfoService.get(bss.getBusinessinfoId());
				if (businessInfo != null) {
					businessInfoList.add(businessInfo);
				}
			}
		} else { // 添加的时候，villageInfoId为空
			businessInfoList = businessInfoService.findList(new BusinessInfo());
		}
		model.addAttribute("allBusinessInfo", businessInfoList);

		return "modules/operation/couponManageForm";
	}

	@RequiresPermissions("operation:couponManage:edit")
	@RequestMapping(value = "save")
	public String save(CouponManage couponManage, Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value = "excelFile", required = false) MultipartFile excelFile) {
		if (!beanValidator(model, couponManage)) {
			return form(couponManage, model);
		}

		// 使用范围
		String userObject = couponManage.getUseObject();
		for (String s : userObject.split(",")) {
			if (StringUtils.isNotEmpty(s)) {
				userObject = s;
			}
		}
		couponManage.setUseObject(userObject);

		// 活动状态
		String activeState = new String();
		if (couponManage.getActiveStartTime().after(new Date())) {
			activeState = "0";
		} else if (couponManage.getActiveStartTime().before(new Date())
				&& couponManage.getActiveEndTime().after(new Date())) {
			// 活动已经开始
			activeState = "1";
		} else {
			activeState = "2";
		}
		couponManage.setActiveState(activeState);
		couponManageService.save(couponManage);

		// 获得新添加优惠券对象，并获得该条记录的id
		CouponManage lastCouponManage = couponManageService.get(couponManage);
		String couponManageId = lastCouponManage.getId();
		List<Account> accountList = new ArrayList<Account>();

		// 根据推送对象类型、时间范围、订单类型，以及上传的excel文件获取优惠券发放对象列表（mobileList）
		accountList = this.getAccountListByPushObjType(couponManage.getPushObjType(), couponManage.getTimeScope(),
				couponManage.getTimeScopeStartTime(), couponManage.getTimeScopeEndTime(), couponManage.getOrderType(),
				excelFile);

		// 如果是自定义推送方式，则先修改优惠券的导入用户(coupon_manage_users表)中的对应记录的couponManageId
		if (couponManage.getPushObjType().equals(CouponManage.PUSH_OBJ_TYPE_CUSTOMUSER)) {
			CouponManageUsers couponManageUsers = new CouponManageUsers();
			couponManageUsers.setCouponManageId(couponManageId);
			couponManageUsers.setLastId(couponManage.getLastId());
			couponManageUsersService.updateCouponManageId(couponManageUsers);
		}

		for (Account account : accountList) {
			/* 添加会员的优惠券 */
			MemberDiscount memberDiscount = new MemberDiscount();
			memberDiscount.setAccountId(account.getId()); // 会员ID
			memberDiscount.setDiscountId(couponManageId); // 优惠券ID

			// 生成优惠券号 券号生成规则为：年月日（170605）+券号（6位流水号）例如最终生成的券码为：
			// 170604000001
			// String discountNum = memberDiscountService.getNextDiscountId();
			String discountNum = sysCodeMaxService.getDiscountNum();
			memberDiscount.setDiscountNum(discountNum); // 优惠券号
			memberDiscount.setObtainDate(new Date()); // 获得时间
			// memberDiscount.setOrderId(orderId); // 哪个订单赠送的优惠券
			// memberDiscount.setOrderType(orderType); //
			// 赠送的订单类型：0-商品；1服务；2课程；3场地
			memberDiscount.setReceiveType(couponManage.getReceiveType()); // 领取方式：0买家领取
																			// 1下单赠送
																			// 2平台推送
			memberDiscount.setUseState("0"); // 使用状态：0未使用；1已使用；2已过期；3已冻结
			memberDiscount.setValidStart(couponManage.getValidityStartTime()); // 有效起始
			memberDiscount.setValidEnd(couponManage.getValidityEndTime()); // 有效结束
			memberDiscount.setVillageInfoId(couponManage.getVillageInfoId()); // 楼盘信息ID

			memberDiscountService.save(memberDiscount);
		}
		addMessage(redirectAttributes, "保存优惠券成功");

		return "redirect:" + Global.getAdminPath() + "/operation/couponManage/?repage";
	}

	@RequiresPermissions("operation:couponManage:edit")
	@RequestMapping(value = "delete")
	public String delete(CouponManage couponManage, RedirectAttributes redirectAttributes) {
		couponManageService.delete(couponManage);
		addMessage(redirectAttributes, "删除优惠券成功");
		return "redirect:" + Global.getAdminPath() + "/operation/couponManage/?repage";
	}

	@RequiresPermissions("operation:couponManage:edit")
	@RequestMapping(value = "close")
	public String close(CouponManage couponManage, RedirectAttributes redirectAttributes) {
		couponManageService.close(couponManage);
		addMessage(redirectAttributes, "关闭优惠券成功");
		return "redirect:" + Global.getAdminPath() + "/operation/couponManage/?repage";
	}

	/**
	 * 页面通过ajax方式上传文件 处理后，返回页面处理结果
	 * 
	 * @param excelFile
	 * @return
	 */
	@RequiresPermissions("operation:couponManage:edit")
	@ResponseBody
	@RequestMapping(value = "import")
	public Map<String, Object> upload(@RequestParam("excelFile") MultipartFile excelFile) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Mobile> mobileList = new ArrayList<Mobile>();

		// 生成uuid
		UUID uuid = UUID.randomUUID();
		String lastId = uuid.toString();

		if (excelFile != null && excelFile.getSize() != 0) {
			try {
				ImportExcel importExcel = new ImportExcel(excelFile, 0, 0);
				mobileList = importExcel.getDataList(Mobile.class);

				// 检查excel文件格式是否合法
				result = this.checkMobileList(mobileList);
				
				// 如果格式不合法，则返回
				if (!(Boolean) result.get("success")) {
					return result;
				} else {
					
					// 合法，则进行excel数据入库
					for (Mobile mobile : mobileList) {

						/* 添加优惠券的导入用户 */
						// 根据手机号查询会员信息
						boolean userExist = false;
						String phoneNum = mobile.getMobileNo().toString();
						Date registerTime = mobile.getRegisterDate();
						String accountId = new String();
						Account account = new Account();
						account.setPhoneNum(phoneNum);
						List<Account> accountList = accountService.findList(account);
						if (accountList != null && accountList.size() > 0) {
							userExist = true;
							accountId = accountList.get(0).getId();// 会员ID
						}

						CouponManageUsers couponManageUsers = new CouponManageUsers();
						couponManageUsers.setCouponManageId(lastId);// 优惠券ID
						couponManageUsers.setAccount(phoneNum);
						couponManageUsers.setRegisterTime(registerTime);
						couponManageUsers.setAccountId(accountId);
						couponManageUsers.setExistFlag(userExist ? "0" : "1");

						couponManageUsersService.save(couponManageUsers);

						result.put("success", Boolean.TRUE);
						result.put("lastId", lastId);
					}
				}
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
				result.put("success", Boolean.FALSE);
				result.put("msg", "导入的excel格式错误，请检查！");
			} catch (InstantiationException e) {
				e.printStackTrace();
				result.put("success", Boolean.FALSE);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				result.put("success", Boolean.FALSE);
			}

		} else {
			result.put("success", Boolean.FALSE);
			result.put("msg", "请检查导入的excel文件是否为空！");
		}
		
		return result;
	}

	/**
	 * 检查传入的Mobile集合对象是否合规
	 * 
	 * @param mobileList
	 * @return
	 */
	private Map<String, Object> checkMobileList(List<Mobile> mobileList) {
		Map<String, Object> result = new HashMap<String, Object>();

		// excel中，数据从第二行开始，设置lineNum的初始值为1
		int lineNum = 1;
		for (Mobile mobile : mobileList) {
			lineNum++;

			// 对导入的excel数据格式进行校验,第一列为手机号，第二列为注册日期
			Long mobileNo = mobile.getMobileNo();
			String mobileNoStr = String.valueOf(mobileNo);
			if (mobileNo == null) {

				// 导入的excel中，第**行的手机号为空
				result.put("success", Boolean.FALSE);
				result.put("msg", "导入的excel中，第" + lineNum + "行的手机号码为空，导入excel的第一列必须为手机号，且不能为空！");
			} else if (mobileNoStr.length() != 11) {

				// 导入的excel中，第**行的第一列手机号位数不足11位
				result.put("success", Boolean.FALSE);
				result.put("msg", "导入的excel中，第" + lineNum + "行的第一列手机号位数不足11位，导入excel的第一列必须为手机号！");
			} else {
				result.put("success", Boolean.TRUE);
			}
		}
		return result;
	}

	/**
	 * 根据推送对象类型、时间范围、订单类型，以及上传的excel文件获取优惠券发放对象列表（mobileList）
	 * 
	 * @param pushObjType
	 * @param timeScope
	 * @param timeScopeStartTime
	 * @param timeScopeEndTime
	 * @param excelFile
	 * @return
	 */
	private List<Account> getAccountListByPushObjType(String pushObjType, String timeScope, Date timeScopeStartTime,
			Date timeScopeEndTime, String orderType, MultipartFile excelFile) {
		Date beginDate = new Date();
		Date endDate = new Date();
		List<Account> accountList = new ArrayList<Account>();
		// TODO: 查找用户列表处添加日志
		logger.warn("pushObjType:" + pushObjType);
		logger.warn("timeScope:" + timeScope);
		logger.warn("timeScopeStartTime:" + timeScopeStartTime);
		logger.warn("timeScopeEndTime:" + timeScopeEndTime);
		logger.warn("orderType:" + orderType);
		// 根据推送对象类型的五种形式生成优惠券明细，推送对象类型：0平台注册用户 1平台登录用户 2未下单用户 3下单用户 4自定义用户
		// 当为0平台注册用户 1平台登录用户时，注册时间或登录时间选择用户，生成优惠券
		if (pushObjType.equals(CouponManage.PUSH_OBJ_TYPE_PLATREGUSER)) {

			switch (timeScope) {
			case "0":// 一个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -1);
				break;
			case "1": // 三个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -3);
				break;
			case "2": // 六个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -6);
				break;
			case "3": // 自定义
				beginDate = timeScopeStartTime;

				// 结束时间加一天
				endDate = DateUtils.addDays(timeScopeEndTime, 1);
				break;
			default:

			}
			Account account = new Account();
			account.setBeginRegistDate(beginDate);
			account.setEndRegistDate(endDate);
			accountList = accountService.findList(account);
		} else if (pushObjType.equals(CouponManage.PUSH_OBJ_TYPE_PLATLOGUSER)) {
			switch (timeScope) {
			case "0":// 一个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -1);
				break;
			case "1": // 三个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -3);
				break;
			case "2": // 六个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -6);
				break;
			case "3": // 自定义
				beginDate = timeScopeStartTime;

				// 结束时间加一天
				endDate = DateUtils.addDays(timeScopeEndTime, 1);
				break;
			default:

			}
			Account account = new Account();
			account.setBeginLastLoginTime(beginDate);
			account.setEndLastLoginTime(endDate);
			accountList = accountService.findList(account);
		}
		// 当为 2未下单用户 3下单用户时，根据下单时间频率及下单模块选择用户，生成优惠券
		else if (pushObjType.equals(CouponManage.PUSH_OBJ_TYPE_UNORDERUSER)
				|| pushObjType.equals(CouponManage.PUSH_OBJ_TYPE_ORDERUSER)) {
			// 订单涉及五张表，通过一条sql实现会员筛选
			// 商品类 Order_Goods
			// 服务类 Order_Service
			// 课程培训类 Order_Lesson
			// 场地预约类 Order_Field
			// 团购类 Order_Group_Purc
			switch (timeScope) {
			case "0":// 一个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -1);
				break;
			case "1": // 三个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -3);
				break;
			case "2": // 六个月
				endDate = new Date();
				beginDate = DateUtils.addMonths(beginDate, -6);
				break;
			case "3": // 自定义
				beginDate = timeScopeStartTime;

				// 结束时间加一天
				endDate = DateUtils.addDays(timeScopeEndTime, 1);
				break;
			default:

			}
			Account account = new Account();
			account.setBeginPayTime(beginDate);
			account.setEndPayTime(endDate);
			List<String> moduleManageIds = new ArrayList<String>();
			for (String moduleManageId : orderType.split(",")) {
				moduleManageIds.add(moduleManageId);
			}
			account.setModuleManageIds(moduleManageIds);
			// 未下单用户增加一个查询条件，即unOrder不为空
			if (pushObjType.equals(CouponManage.PUSH_OBJ_TYPE_UNORDERUSER)) {
				account.setUnOrder("unOrder");
			}
			accountList = accountService.findListByOrder(account);

		}
		// 当 4自定义用户，根据导入的excel文件选择用户，生成优惠券
		else {
			if (excelFile != null && excelFile.getSize() != 0) {
				try {
					ImportExcel importExcel = new ImportExcel(excelFile, 1, 0);
					List<Mobile> mobileList = importExcel.getDataList(Mobile.class);
					// 根据手机号查询会员信息
					for (Mobile mobile : mobileList) {
						// boolean userExist = false;
						String phoneNum = mobile.getMobileNo().toString();
						// Date registerTime = mobile.getRegisterDate();
						// String accountId = new String();
						Account account = new Account();
						account.setPhoneNum(phoneNum);
						accountList = accountService.findList(account);
						if (accountList != null && accountList.size() > 0) {
							// userExist = true;
							// accountId = accountList.get(0).getId();// 会员ID
							account = accountList.get(0);
							accountList.add(account);
						}
					}

				} catch (InvalidFormatException | IOException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return accountList;

	}
}