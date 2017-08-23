/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.entity.DownloadBill;
import com.its.modules.balance.dao.DownloadBillDao;

/**
 * 结算对账单Service
 * @author Liuqi
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class DownloadBillService extends CrudService<DownloadBillDao, DownloadBill> {

	public DownloadBill get(String id) {
		return super.get(id);
	}
	
	public List<DownloadBill> findList(DownloadBill downloadBill) {
		return super.findList(downloadBill);
	}
	
	public Page<DownloadBill> findPage(Page<DownloadBill> page, DownloadBill downloadBill) {
		return super.findPage(page, downloadBill);
	}
	
	@Transactional(readOnly = false)
	public void save(DownloadBill downloadBill) {
		super.save(downloadBill);
	}
	
	/**
	 * 根据结算对账单列表批量保存记录
	 * 
	 * @param downloadBillList
	 */
	@Transactional(readOnly = false)
	public void saveDownloadBillList(List<DownloadBill> downloadBillList) {
		String dupOutTradeNo = new String();
		for(DownloadBill downloadBill: downloadBillList){
			try{
				dupOutTradeNo = downloadBill.getOutTradeNo();
				super.save(downloadBill);
			} catch(DuplicateKeyException e){
				logger.error("当前记录(商户订单号="+dupOutTradeNo+")与库表download_bill中记录重复，将被跳过，处理下面的记录");
				//e.printStackTrace();
			} finally {
				logger.info("当前记录处理出错，继续处理下条记录");
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DownloadBill downloadBill) {
		super.delete(downloadBill);
	}
	
}