package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;

import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.common.CommonGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.dao.GroupPurchaseDao;
import com.its.modules.app.entity.GroupPurchase;

/**
 * 团购管理Service
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class GroupPurchaseService extends CrudService<GroupPurchaseDao, GroupPurchase> {

	@Autowired
	private BusinessInfoService businessInfoService;

	public GroupPurchase get(String id) {
		return super.get(id);
	}

	public List<GroupPurchase> findList(GroupPurchase groupPurchase) {
		return super.findList(groupPurchase);
	}

	public Page<GroupPurchase> findPage(Page<GroupPurchase> page, GroupPurchase groupPurchase) {
		return super.findPage(page, groupPurchase);
	}

	@Transactional(readOnly = false)
	public void save(GroupPurchase groupPurchase) {
		super.save(groupPurchase);
	}

	@Transactional(readOnly = false)
	public void update(GroupPurchase groupPurchase) {
		dao.update(groupPurchase);
	}

	@Transactional(readOnly = false)
	public void delete(GroupPurchase groupPurchase) {
		super.delete(groupPurchase);
	}

	/**
	 * 获取某商家正在进行和即将进行的团购活动
	 * 
	 * @param businessInfoID
	 *            商家ID
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getBusinessGroupPurchase(String businessInfoID) {
		return dao.getBusinessGroupPurchase(businessInfoID);
	}

	/**
	 * 获取正在进行或即将进行的团购活动
	 * 
	 * @param groupPurchase
	 *            团购信息，用于传递团购类型 团购类型：1->进行中 2->即将开始
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getGroupPurchaseListOnOrComing(GroupPurchase groupPurchase) {
		return dao.getGroupPurchaseListOnOrComing(groupPurchase);
	}

	/**
	 * 根据团购活动子表ID获取该团购活动信息
	 * 
	 * @param groupPurchaseTimeId
	 *            团购活动子表ID
	 * @return GroupPurchaseBean
	 */
	public GroupPurchaseBean getGroupPurchaseBean(String groupPurchaseTimeId) {
		return dao.getGroupPurchaseBean(groupPurchaseTimeId);
	}

	/**
	 * 获取模块活动列表团购状态
	 * 
	 * @param groupPurchaseBean
	 *            团购信息
	 * @param type
	 *            团购类型：1->进行中 2->即将开始
	 * @return 团购状态（ 1->即将开始 2->抢购中 3->已抢光）
	 */
	public int getGroupPurchaseStatus(GroupPurchaseBean groupPurchaseBean, String type) {
		Integer stock = ValidateUtil.validateInteger(groupPurchaseBean.getStockNum()) - ValidateUtil.validateInteger(groupPurchaseBean.getSaleNum());
		if ("1".equals(type)) {
			if (stock <= 0) {
				// 团购状态：已抢光
				return 3;
			} else {
				// 团购状态：抢购中
				return 2;
			}
		} else {
			// 团购状态：即将开始
			return 1;
		}
	}

	/**
	 * 获取时间间隔（秒）
	 * 
	 * @param groupPurchaseBean
	 *            团购信息
	 * @return 时间间隔（秒）
	 */
	public long getTimeInterval(GroupPurchaseBean groupPurchaseBean) {
		int groupPurchaseType = this.getGroupPurchaseType(groupPurchaseBean);
		if (groupPurchaseType == 1) {
			return (groupPurchaseBean.getStartTime().getTime() - new Date().getTime()) / 1000;
		} else if (groupPurchaseType == 2) {
			return (groupPurchaseBean.getEndTime().getTime() - new Date().getTime()) / 1000;
		} else {
			return 0;
		}
	}

	/**
	 * 获取团购即将开始时开始时间描述
	 * 
	 * @param groupPurchaseBean
	 *            团购信息
	 * @return 团购即将开始时开始时间描述
	 */
	public String getStartTimeDesc(GroupPurchaseBean groupPurchaseBean) {
		int groupPurchaseType = this.getGroupPurchaseType(groupPurchaseBean);
		if (groupPurchaseType == 1) {
			String today = DateFormatUtils.format(businessInfoService.add(0), "yyyy-MM-dd");
			String tomorrow = DateFormatUtils.format(businessInfoService.add(1), "yyyy-MM-dd");
			String startTime = DateFormatUtils.format(groupPurchaseBean.getStartTime(), "yyyy-MM-dd");
			if (today.equals(startTime)) {
				return "今日" + DateFormatUtils.format(groupPurchaseBean.getStartTime(), "HH点") + "开抢";
			} else if (tomorrow.equals(startTime)) {
				return "明日" + DateFormatUtils.format(groupPurchaseBean.getStartTime(), "HH点") + "开抢";
			} else {
				return DateFormatUtils.format(groupPurchaseBean.getStartTime(), "MM月dd日HH点") + "开抢";
			}
		}
		return null;
	}

	/**
	 * 获取团购类型
	 * 
	 * @param groupPurchaseBean
	 *            团购信息
	 * @return 团购类型：-1->已撤销 1->即将开始 2->进行中 3->抢购已结束
	 */
	public int getGroupPurchaseType(GroupPurchaseBean groupPurchaseBean) {
		// 排除强制下线的团购活动
		if (!CommonGlobal.ACTIVITY_GROUP_PURCHASE_REVOKED.equals(groupPurchaseBean.getGroupPurcState())) {
			long startTime = groupPurchaseBean.getStartTime().getTime();
			long endTime = groupPurchaseBean.getEndTime().getTime();
			long nowTime = new Date().getTime();
			if (startTime > nowTime) {
				return 1;
			}
			if ((startTime <= nowTime) && (endTime > nowTime)) {
				return 2;
			}
			if (endTime <= nowTime) {
				return 3;
			}
		}
		return -1;
	}

	/**
	 * 返回商家支持
	 * 
	 * @param supportType
	 *            商家支持：0支持随时退 1支持过期退 2免预约
	 * @return 商家支持
	 */
	public int[] getSupportType(String supportType) {
		int[] types = new int[3];
		if (StringUtils.isBlank(supportType)) {
			String[] supportTypeArray = supportType.replace("，", ",").split(",");
			for (String string : supportTypeArray) {
				if (StringUtils.isNotBlank(string)) {
					types[Integer.parseInt(string)] = 1;
				}
			}
		}
		return types;
	}

	/**
	 * 获取团购图片的完整路径
	 * 
	 * @param GroupPurchaseImg
	 *            团购图片
	 * @return 团购图片的完整路径
	 */
	public String formatGroupPurchaseImg(String GroupPurchaseImg, HttpServletRequest request) {
		if (StringUtils.isBlank(GroupPurchaseImg)) {
			return "";
		}
		return MyFDFSClientUtils.get_fdfs_file_url(request, GroupPurchaseImg);
	}

	/**
	 * 获取某楼盘下正在进行中和即将进行的团购活动
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getByVillageInfoId(String villageInfoId) {
		return dao.getByVillageInfoId(villageInfoId);
	}
}