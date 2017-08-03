/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.its.common.utils.DateUtils;
import com.its.modules.cms.dao.ArticleDao;
import com.its.modules.field.dao.FieldInfoPriceDao;
import com.its.modules.field.dao.FieldPartitionPriceDao;
import com.its.modules.field.entity.FieldInfoPrice;
import com.its.modules.field.entity.FieldInfoPriceList;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.dao.FieldInfoDao;

/**
 * 场地预约Service
 * @author xzc
 * @version 2017-06-29
 */
@Service
@Transactional(readOnly = true)
public class FieldInfoService extends CrudService<FieldInfoDao, FieldInfo> {
	/**
	 * 场地预约子表_分段编辑临时表Service
	 */
	@Autowired
	private FieldInfoPriceService fieldInfoPriceService;
	/**
	 * 场地预约子表-场地分段信息Service
	 */
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	/**
	 * 场地预约子表-场地分段信息DAO接口
	 */
	@Autowired
	private FieldPartitionPriceDao fieldPartitionPriceDao;
	/**
	 * 场地预约DAO接口
	 */
	@Autowired
	private FieldInfoDao fieldInfoDao;

	public FieldInfo get(String id) {
		return super.get(id);
	}
	
	public List<FieldInfo> findList(FieldInfo fieldInfo) {
		return super.findList(fieldInfo);
	}
	
	public Page<FieldInfo> findPage(Page<FieldInfo> page, FieldInfo fieldInfo) {
		//return super.findPage(page, fieldInfo);
		fieldInfo.setPage(page);
		List<FieldInfo> fieldInfoList=fieldInfoDao.queryFieldPartitionPriceListByFieldInfo(fieldInfo);
		page.setCount(fieldInfoList.size());
		page.setList(fieldInfoList);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(FieldInfo fieldInfo) {
		super.save(fieldInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(FieldInfo fieldInfo) {
		super.delete(fieldInfo);
	}

	/**
	 * 创建场地
	 * @param fieldInfo
	 * @param fieldInfoPriceList
	 * @param isAll 是否删除所有的分段价格
	 * @param delId 删除的分段价格ID
	 */
	@Transactional(readOnly = false)
	public void save(FieldInfo fieldInfo, FieldInfoPriceList fieldInfoPriceList,boolean isAll,String[] delId) {
		if (fieldInfo.getState()==null){
			fieldInfo.setState("0"); ;
		}
		super.save(fieldInfo);
		//初始化分段价格编辑数据
		List<FieldInfoPrice> fieldInfoPrices=fieldInfoPriceList.getFieldInfoPrices()!=null?fieldInfoPriceList.getFieldInfoPrices():new ArrayList<FieldInfoPrice>();

		FieldInfoPrice fieldInfoPrice=new FieldInfoPrice();
		if (delId!=null&&delId.length>0&&isAll){//删除某些分段价格编辑表
			fieldInfoPrice.setFieldInfoId(delId[0]);
			fieldInfoPriceService.deleteAll(fieldInfoPrice,isAll);
		}else if (delId!=null&&!isAll){//删除所有的分段分段价格编辑表
			for (int i=0;i<delId.length;i++){
				fieldInfoPrice.setId(delId[i]);
				fieldInfoPriceService.deleteAll(fieldInfoPrice,isAll);
			}
		}
		//存储分段价格编辑表
		for (FieldInfoPrice fieldInfoPrice_:fieldInfoPrices){
			fieldInfoPrice_.setFieldInfoId(fieldInfo.getId());
			fieldInfoPrice_.setBusinessInfoId(fieldInfo.getBusinessInfoId());
			fieldInfoPriceService.save(fieldInfoPrice_);
		}
		//如果不生成则直接跳出
		if(!fieldInfo.getCreateState().equals("1")){
			return;
		}

		//产生分段数据信息表数据
		FieldPartitionPrice fieldPartitionPrice=new FieldPartitionPrice();
		fieldPartitionPrice.setFieldInfoId(fieldInfo.getId());
		//获取分段价格集合
		List<FieldPartitionPrice> fieldPartitionPriceServiceList=fieldPartitionPriceService.findList(fieldPartitionPrice);
		if (fieldPartitionPriceServiceList==null||fieldPartitionPriceServiceList.size()==0){
			for (int i=0;i<7;i++){
				this.createFieldPartitionPrice(fieldInfo,fieldInfoPrices,DateUtils.addDays(new Date(),i));
			}
		}



	}

	/**
	 * 产生恢复场地时候分段数据信息表数据
	 * @param fieldInfo 场地信息对象
	 * @param fieldInfoPrices 场地分段价格编辑对象
	 */
	@Transactional(readOnly = false)
	private void createRecoveryFieldPartitionPrice(FieldInfo fieldInfo, List<FieldInfoPrice> fieldInfoPrices) {
		if (!fieldInfo.getState().equals("0")){
			return;
		}
		//产生分段数据信息表数据
		FieldPartitionPrice fieldPartitionPrice=new FieldPartitionPrice();
		fieldPartitionPrice.setFieldInfoId(fieldInfo.getId());
		for (int i=0;i<7;i++){
			fieldPartitionPrice.setAppointmentTime(DateUtils.addDays(new Date(),i));
			List<FieldPartitionPrice> fieldPartitionPriceServiceList=fieldPartitionPriceService.findListByAppointmentTime(fieldPartitionPrice);
			//判断某一天是否生成,如果生成，则不继续生成
			if (fieldPartitionPriceServiceList==null||fieldPartitionPriceServiceList.size()==0){
				this.createFieldPartitionPrice(fieldInfo,fieldInfoPrices,DateUtils.addDays(new Date(),i));
			}
		}
	}


	/**
	 * 创建分段价格信息数据
	 * @param fieldInfo 场地表
	 * @param fieldInfoPrices 分段价格编辑表
	 * @param date 创建日期
	 */
	@Transactional(readOnly = false)
	public void createFieldPartitionPrice(FieldInfo fieldInfo,List<FieldInfoPrice> fieldInfoPrices,Date date){
		if(fieldInfo.getIsChild().equals("1")){//有分段价格的，按照分段价格的参数进行创建分段信息数据
			for (FieldInfoPrice fieldInfoPrice:fieldInfoPrices){

				if(fieldInfoPrice.getMonday().equals("1")&& DateUtils.getWeek(date).equals("星期一")){//星期一
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getTuesday().equals("1")&& DateUtils.getWeek(date).equals("星期二")){//星期二
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getWednesday().equals("1")&& DateUtils.getWeek(date).equals("星期三")){//星期三
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getThursday().equals("1")&& DateUtils.getWeek(date).equals("星期四")){//星期四
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getFriday().equals("1")&& DateUtils.getWeek(date).equals("星期五")){//星期五
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getSaturday().equals("1")&& DateUtils.getWeek(date).equals("星期六")){//星期六
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
				if(fieldInfoPrice.getSunday().equals("1")&& DateUtils.getWeek(date).equals("星期日")){//星期日
					createFieldPartitionPriceByFieldInfoPrice(fieldInfo, date, fieldInfoPrice);
				}
			}
		}else {//没有分段信息，按照场地的基本价格，起始时段 ，结束时段 ，最短预约时间进行创建分段信息数据

			if(fieldInfo.getMonday().equals("1")&& DateUtils.getWeek(date).equals("星期一")){//星期一
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getTuesday().equals("1")&& DateUtils.getWeek(date).equals("星期二")){//星期二
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getWednesday().equals("1")&& DateUtils.getWeek(date).equals("星期三")){//星期三
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getThursday().equals("1")&& DateUtils.getWeek(date).equals("星期四")){//星期四
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getFriday().equals("1")&& DateUtils.getWeek(date).equals("星期五")){//星期五
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getSaturday().equals("1")&& DateUtils.getWeek(date).equals("星期六")){//星期六
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
			if(fieldInfo.getSunday().equals("1")&& DateUtils.getWeek(date).equals("星期日")){//星期日
				createFieldPartitionPriceByFieldInfo(fieldInfo, date);
			}
		}

	}

	/**
	 * 没有分段信息，按照场地的基本价格，起始时段 ，结束时段 ，最短预约时间进行创建分段信息数据
	 * @param fieldInfo 场地信息数据
	 * @param date 时间
	 */
	@Transactional(readOnly = false)
	private void createFieldPartitionPriceByFieldInfo(FieldInfo fieldInfo, Date date) {
		Integer indexNum=1;
		while (Integer.parseInt(fieldInfo.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*indexNum
                <=Integer.parseInt(fieldInfo.getEndOpenTime().split(":")[0])){
            Date sTime= DateUtils.parseDate(DateUtils.formatDate(date)+" "
                    +(Integer.parseInt(fieldInfo.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*(indexNum-1))+":"
                    +(Integer.parseInt(fieldInfo.getStartOpenTime().split(":")[1])));
            Date eTime=DateUtils.parseDate(DateUtils.formatDate(date)+" "
                    +(Integer.parseInt(fieldInfo.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*(indexNum))+":"
                    +(Integer.parseInt(fieldInfo.getStartOpenTime().split(":")[1])));
            FieldPartitionPrice fieldPartitionPrice=new FieldPartitionPrice();
            fieldPartitionPrice.preInsert();
            fieldPartitionPrice.setState("0");
            fieldPartitionPrice.setFieldInfoId(fieldInfo.getId());
            fieldPartitionPrice.setAppointmentTime(date);
            fieldPartitionPrice.setStartTime(sTime);
            fieldPartitionPrice.setEndTime(eTime);
            fieldPartitionPrice.setBasePrice(fieldInfo.getBasePrice());
			fieldPartitionPrice.setSumMoney(DateUtils.pastHour(sTime,eTime)*fieldPartitionPrice.getBasePrice());
            fieldPartitionPrice.setBusinessInfoId(fieldInfo.getBusinessInfoId());
            fieldPartitionPriceDao.insert(fieldPartitionPrice);
            indexNum++;
        }
	}

	/**
	 *
	 * 有分段价格的，按照分段价格的参数进行创建分段信息数据
	 * @param fieldInfo 场地信息
	 * @param date 时间
	 * @param fieldInfoPrice 场地分段编辑数据
	 */
	@Transactional(readOnly = false)
	private void createFieldPartitionPriceByFieldInfoPrice(FieldInfo fieldInfo, Date date, FieldInfoPrice fieldInfoPrice) {
		Integer indexNum=1;
		while (Integer.parseInt(fieldInfoPrice.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*indexNum
                <=Integer.parseInt(fieldInfoPrice.getEndOpenTime().split(":")[0])){
            Date sTime= DateUtils.parseDate(DateUtils.formatDate(date)+" "
                    +(Integer.parseInt(fieldInfoPrice.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*(indexNum-1))+":"
                    +(Integer.parseInt(fieldInfoPrice.getStartOpenTime().split(":")[1])));
            Date eTime=DateUtils.parseDate(DateUtils.formatDate(date)+" "
                    +(Integer.parseInt(fieldInfoPrice.getStartOpenTime().split(":")[0])+fieldInfo.getShortTime()*(indexNum))+":"
                    +(Integer.parseInt(fieldInfoPrice.getStartOpenTime().split(":")[1])));
            FieldPartitionPrice fieldPartitionPrice=new FieldPartitionPrice();
            fieldPartitionPrice.preInsert();
            fieldPartitionPrice.setState("0");
            fieldPartitionPrice.setFieldInfoId(fieldInfo.getId());
            fieldPartitionPrice.setAppointmentTime(date);
            fieldPartitionPrice.setStartTime(sTime);
            fieldPartitionPrice.setEndTime(eTime);
            fieldPartitionPrice.setBasePrice(fieldInfoPrice.getBasePrice());
            fieldPartitionPrice.setSumMoney(DateUtils.pastHour(sTime,eTime)*fieldPartitionPrice.getBasePrice());
            fieldPartitionPrice.setBusinessInfoId(fieldInfoPrice.getBusinessInfoId());
            fieldPartitionPriceDao.insert(fieldPartitionPrice);
            indexNum++;
        }
	}

	/**
	 * 暂停场地
	 * @param fieldInfo 场地信息对象
	 * @return
	 */
	@Transactional(readOnly = false)
	public void updateSuspend(FieldInfo fieldInfo) {
		fieldInfo.setState("1");
		fieldInfoDao.update(fieldInfo);
	}

	/**
	 * 恢复场地
	 * @param fieldInfo 场地信息对象
	 * @return
	 */
	@Transactional(readOnly = false)
	public void updateRecovery(FieldInfo fieldInfo) {
		fieldInfo.setState("0");
		fieldInfoDao.update(fieldInfo);
		//产生分段数据信息表数据
		FieldInfoPrice fieldInfoPrice=new FieldInfoPrice();
		fieldInfoPrice.setFieldInfoId(fieldInfo.getId());
		List<FieldInfoPrice> fieldInfoPrices=fieldInfoPriceService.findList(fieldInfoPrice);
		createRecoveryFieldPartitionPrice(fieldInfo, fieldInfoPrices);
	}

	/**
	 * 查询可以生成分段场地信息的场地
	 * @return
	 */
	public List<FieldInfo> queryOpenList(){
		return fieldInfoDao.queryOpenList();
	}

	/**
	 * 场地个数
	 * @param fieldInfo_where
	 * @return
	 */
	public Integer findAllListCount(FieldInfo fieldInfo_where) {
		if(fieldInfo_where.getStock()!=null&&fieldInfo_where.getStock().equals("1")){
			return this.dao.findAllListCountFull( fieldInfo_where);
		}else {
			return this.dao.findAllListCount( fieldInfo_where);
		}
	}
}

