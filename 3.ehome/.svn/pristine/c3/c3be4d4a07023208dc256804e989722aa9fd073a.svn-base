package com.its.modules.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.field.service.FieldPartitionPriceService;

@Component
public class FieldInfoTask {
	@Autowired
	private FieldPartitionPriceService fieldPartitionPriceService;
	
	
	@Autowired
	private FieldInfoService fieldInfoService;

	/**
	 * 1.如果时间过了,自动将已预约更改为已消费.
	 * 2.将标记预约完删除的场地  删除.
	 * 间隔30分钟的5秒执行 
	 * @throws ParseException 
	 */
	@Scheduled(cron = "1 0/30 * * * ?")
	public void checkFieldPartitionPriceState() throws ParseException {
		/*修改消费状态*/
		fieldPartitionPriceService.taskUpdateState();
		
		
		/*删除标记预约完删除项*/
		/* 场地预约 */
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setDf(1);//预约完成删除标记
		
		Date date = new Date();
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.DAY_OF_WEEK, 7); // 目前的時間加7天    
		Date enddate =calendar.getTime();//7天后
		//今天开始时间
		Date startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(date));
		Date endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").parse(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(enddate));
		if (fieldInfo.getPartitionPrice()==null){
			fieldInfo.setPartitionPrice(new FieldPartitionPrice());
			fieldInfo.getPartitionPrice().setStartTime(startTime);
			fieldInfo.getPartitionPrice().setEndTime(endTime);
		}
		List<FieldInfo> list = fieldInfoService.findList(fieldInfo); //该场地最近8天场地预约信息
		
		boolean _b = true;
		for(FieldInfo fi:list){
			List<FieldPartitionPrice> fppList =  fi.getFieldPartitionPriceList();
			if(fppList==null || fppList.size()<1){
				fieldInfoService.deleteAll(fieldInfo);
			}
			_b=true;
			for(FieldPartitionPrice fpp:fppList){
				if(fpp.getState().equals("1")){//已预约
					_b=false;break;
				}
			}
			if(_b){
				fieldInfoService.deleteAll(fi);
			}
		}
	}
}
