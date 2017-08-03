/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.coupon.entity.GroupPurchase;
import com.its.modules.coupon.entity.GroupPurchasetime;
import com.its.modules.coupon.dao.GroupPurchaseDao;

/**
 * 优惠验券管理Service
 * @author caojing
 * @version 2017-07-25
 */
@Service
@Transactional(readOnly = true)
public class GroupPurchaseService extends CrudService<GroupPurchaseDao, GroupPurchase> {
	
	@Autowired
	private GroupPurchaseDao groupPurchaseDao;
	
	@Autowired
	private GroupPurchasetimeService groupPurchasetimeService;
	
	public GroupPurchase get(String id) {
		return super.get(id);
	}
	
	public List<GroupPurchase> findList(GroupPurchase groupPurchase) {
		return super.findList(groupPurchase);
	}
	
	/**
	 * 团购管理列表页的取值
	 */
	public Page<GroupPurchase> findPage(Page<GroupPurchase> page, GroupPurchase groupPurchase) {
		//团购主表数据取值
		Page<GroupPurchase> pageList = super.findPage(page, groupPurchase);
		
		//团购时间信息表取值
		for(GroupPurchase group : pageList.getList()){
			GroupPurchasetime groupPurchasetime = new GroupPurchasetime();
			groupPurchasetime.setGroupPurchaseId(group.getId());
			List<GroupPurchasetime> groupTimeList = groupPurchasetimeService.findList(groupPurchasetime);
			
			//团购开始时间和结束时间的设置
			if(groupTimeList != null && groupTimeList.size() >0){
				group.setStartTime(groupTimeList.get(0).getStartTime());
				group.setEndTime(groupTimeList.get(groupTimeList.size()-1).getEndTime());			
			}
			
			//设置总库存量和销售量
			int stockNum = 0;
			int saleNum = 0;
			
			//团购时间信息数据放入团购列表页数据
			for(GroupPurchasetime groupTime : groupTimeList){
				stockNum=stockNum+groupTime.getStockNum();
				if(groupTime.getSaleNum() !=null){
					saleNum=saleNum+groupTime.getSaleNum();							
				}else{
					saleNum=saleNum+0;
				}						
			}
			//设置库存量和销售量
			group.setStockNum(stockNum);
			group.setSaleNum(saleNum);
			//************************************团购时间信息数据放入团购列表页数据************************
			//团购：已撤销
			if(group != null && "3".equals(group.getGroupPurcState())){
				
			//团购：进行中、待开始、已结束
			}else{
				if(groupTimeList != null && groupTimeList.size() >0){
										
					//最后团购时间段为已结束，表示此团购已结束
					if("2".equals(groupTimeList.get(groupTimeList.size()-1).getGroupState())){						
						//团购状态：已结束
						group.setGroupPurcState("2");
					//团购：进行中、待开始
					}else{
						//团购状态标识：1表示进行中
						String stateFlag = null;
						for(GroupPurchasetime groupTime : groupTimeList){
							//团购：进行中
							if(groupTime !=null && "1".equals(groupTime.getGroupState())){
								stateFlag = "1";
								break;
							}
						}
						//团购：进行中
						if("1".equals(stateFlag)){
							group.setGroupPurcState("1");
						//团购：待开始
						}else{
							int count = 0;
							Date startTime =null;
							for(GroupPurchasetime groupTime : groupTimeList){
								//团购时间信息：待开始
								if(groupTime !=null && "0".equals(groupTime.getGroupState())){									
									count++;
									if(count==1){
										startTime = groupTime.getStartTime();
									}
								}
							}
							
							//团购状态
							group.setGroupPurcState("0");
							//团购开始时间
							group.setStartTime(startTime);
							
						}
					}
				}
			}			
		}
		
		return pageList;
	}
	
	/**
	 * 团购管理中，依据团购ID，获取团购信息详情
	 * @return
	 */
	public GroupPurchase getDetail(String id){
		GroupPurchase groupPurchase = new GroupPurchase();
		groupPurchase.setId(id);
		return groupPurchaseDao.getDetail(groupPurchase);
	}
	
	@Transactional(readOnly = false)
	public void save(GroupPurchase groupPurchase) {
		super.save(groupPurchase);
	}
	
	@Transactional(readOnly = false)
	public void delete(GroupPurchase groupPurchase) {
		super.delete(groupPurchase);
	}
	
}