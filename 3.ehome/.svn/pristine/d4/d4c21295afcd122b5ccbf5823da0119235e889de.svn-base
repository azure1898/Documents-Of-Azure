package com.its.modules.task;

import com.its.common.utils.DateUtils;
import com.its.common.web.BaseController;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldInfoPrice;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.field.service.FieldPartitionPriceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class TaskWork extends BaseController {

    @Autowired
    private FieldInfoService fieldInfoService;
    @Autowired
    private FieldPartitionPriceService fieldPartitionPriceService;
    /**
     * 创建场地定时任务
     */
    public void createFieldTask(){
        logger.warn("---------开始场地生成定时任务-----------");
        //查询所有可以进行定时分段的场地表
        List<FieldInfo> fieldInfoList= fieldInfoService.queryOpenList();
        for (FieldInfo fieldInfo: fieldInfoList) {
        logger.warn("---------开始商家ID为"+fieldInfo.getBusinessInfoId()+"场地名称为"+fieldInfo.getName()+"的场地生成定时任务-----------");
            try {
                //判断是否暂停
                if(fieldInfo.getState()==null||!fieldInfo.getState().equals("0")){
                    logger.warn("---------商家ID为"+fieldInfo.getBusinessInfoId()+"场地名称为"+fieldInfo.getName()+"的场地已经暂停，不进行生成-----------");
                    continue;
                }
                //判断是否生成
                if(fieldInfo.getCreateState()==null||!fieldInfo.getCreateState().equals("1")){
                    logger.warn("---------商家ID为"+fieldInfo.getBusinessInfoId()+"场地名称为"+fieldInfo.getName()+"的场地非生成状态，不进行生成-----------");
                    continue;
                }

                //产生分段数据信息表数据
                List<FieldInfoPrice> fieldInfoPrices=fieldInfo.getFieldInfoPriceList();

                FieldPartitionPrice fieldPartitionPrice=new FieldPartitionPrice();
                fieldPartitionPrice.setFieldInfoId(fieldInfo.getId());
                fieldPartitionPrice.setAppointmentTime(DateUtils.addDays(new Date(),7));

                List<FieldPartitionPrice> fieldPartitionPriceServiceList=fieldPartitionPriceService.findListByAppointmentTime(fieldPartitionPrice);
                if (fieldPartitionPriceServiceList==null||fieldPartitionPriceServiceList.size()==0){
                        fieldInfoService.createFieldPartitionPrice(fieldInfo,fieldInfoPrices, DateUtils.addDays(new Date(),7));
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.warn("---------商家ID为"+fieldInfo.getBusinessInfoId()+"场地名称为"+fieldInfo.getName()+"的场地生成定时任务,异常-----------");
            }
            logger.warn("---------结束商家ID为"+fieldInfo.getBusinessInfoId()+"场地名称为"+fieldInfo.getName()+"的场地生成定时任务-----------");
        }

        logger.warn("---------结束场地生成定时任务-----------");

    }
}
