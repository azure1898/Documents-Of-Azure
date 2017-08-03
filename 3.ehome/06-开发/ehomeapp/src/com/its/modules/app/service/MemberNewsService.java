/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.dao.MemberNewsDao;
import com.its.modules.app.entity.MemberNews;

/**
 * 提醒消息Service
 * 
 * @author like
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class MemberNewsService extends CrudService<MemberNewsDao, MemberNews> {

	public MemberNews get(String id) {
		return super.get(id);
	}

	public List<MemberNews> findList(MemberNews memberNews) {
		return super.findList(memberNews);
	}

	public Page<MemberNews> findPage(Page<MemberNews> page, MemberNews memberNews) {
		return super.findPage(page, memberNews);
	}

	@Transactional(readOnly = false)
	public void save(MemberNews memberNews) {
		super.save(memberNews);
	}

	@Transactional(readOnly = false)
	public void delete(MemberNews memberNews) {
		super.delete(memberNews);
	}

	/**
	 * 获取每种消息的最新消息
	 * 
	 * @param accountId
	 * @return
	 */
	public List<MemberNews> selectLatestNewsEveryType(String accountId, String villageInfoId) {
		return dao.selectLatestNewsEveryType(accountId, villageInfoId);
	}

	/**
	 * 
	 * @param newsType
	 *            0业主关怀；1订单提醒；2系统消息
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public List<MemberNews> selectNewsByType(String newsType, String accountId, String villageInfoId) {
		return dao.selectNewsByType(newsType, accountId, villageInfoId);
	}
	
	/**
	 * 新增订单类型的提醒消息
	 * @param accountId	用户ID
	 * @param villageInfoId	楼盘ID
	 * @param orderType 订单类型
	 * @param orderId	订单ID
	 * @param orderNo	订单号
	 */
	@Transactional(readOnly = false)
	public void createOrderNews(String accountId,String villageInfoId,String orderType,String orderId,String orderNo){
		MemberNews news = new MemberNews();
		news.setAccountId(accountId);
		news.setVillageInfoId(villageInfoId);
		news.setNewsType(AppGlobal.NEWS_TYPE_ORDER);
		news.setContent("");
		news.setOrderType(orderType);
		news.setOrderId(orderId);
		this.save(news);
	}
}