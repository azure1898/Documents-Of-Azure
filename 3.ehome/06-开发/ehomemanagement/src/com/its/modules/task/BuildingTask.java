package com.its.modules.task;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.village.entity.BuildingInfo;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.BuildingInfoService;
import com.its.modules.village.service.VillageInfoService;


@Component("buildingTask")
public class BuildingTask extends BaseController {

	@Autowired
	private VillageInfoService villageInfoService;
	@Autowired
	private BuildingInfoService buildingInfoService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
	 * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */

	/**
	 * 定时卡点计算。每天凌晨 01:00 执行一次
	 */ 
	@Scheduled(cron = "0 0 1 * * *")
	public void createBuildingTask() {
		logger.info("---------查询楼栋信息接口定时任务开始-----------");
		//1.查询楼盘信息；
		VillageInfo villageInfo = new VillageInfo();
		List<VillageInfo> villageInfoList= villageInfoService.getVillageList(villageInfo);
		if(villageInfoList !=null && villageInfoList.size() > 0){
			logger.info("---------查询楼盘信息成功-----------");
		}else{
			logger.warn("---------查询楼盘信息失败-----------");
		}
		
		//2.依据楼盘信息，调用查询楼栋信息接口
		for(VillageInfo village : villageInfoList){
			StringBuilder builder = new StringBuilder();
			builder.append("json={");
			builder.append("\"projectId\" :").append("\"").append(village.getId()).append("\",");
			builder.append("\"updateTime\" :").append("\"").append("00000000000000000").append("\"}");
			
			//查询楼栋信息接口的传入参数
			String villagePara = builder.toString();
			logger.info("---------查询楼栋信息接口的传入参数：villagePara-----------");
			logger.info(villagePara);
			
			//请求的url设定"http://propertyapitest.pujitech.com/propertyAPI/building.do"
			String url = Global.getConfig("buildingPath");
			logger.info("url:"+url);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			try {
			 	HttpPost req = new HttpPost(url);
				req.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
				StringEntity content = new StringEntity(villagePara,"UTF-8");
				req.setEntity(content);
			 	
				CloseableHttpResponse response = httpClient.execute(req);
							 	
				HttpEntity entity = response.getEntity();
				String results = EntityUtils.toString(entity, "UTF-8");
				logger.info("---------查询楼栋信息接口的返回result列表：results-----------");
				logger.info(results);
				//使用JSONObject
		        JSONObject jsonResults = JSONObject.fromObject(results);
		        
		        //返回状态
		        boolean isSuccess=jsonResults.getBoolean("isSuccess");
		        //错误信息
		        String msg=jsonResults.getString("msg");		        
		        //状态码
		        String code=jsonResults.getString("code");
		        //数据信息字段
		        String data=jsonResults.getString("data");
		        
		        //查询楼栋接口成功
		        if(isSuccess && "2000".equals(code)){
		        	 JSONArray jsonList = JSONArray.fromObject(data);
		        	 //3.从接口查询出的楼栋数据信息，插入到本系统数据库
		        	 this.saveBulding(jsonList,village.getId());
		        	logger.info("查询楼栋接口成功");
		        //查询楼栋接口失败，写入log信息
		        }else{
		        	logger.warn("状态码:"+code+";错误信息:"+msg);
		        }
		        
			}catch(Exception e) {
				e.printStackTrace();				
			}finally {
				if(httpClient != null) {
					try {
						httpClient.close();
					}catch(Exception e) {
						e.printStackTrace();						
					}
				}
			}
		}

	}
    
	/**
	 * 从接口查询出的楼栋数据信息，插入到本系统数据库
	 * @param jsonArray
	 */
	private void saveBulding(JSONArray jsonList,String villageId){
		
		List<BuildingInfo> buldingList = new ArrayList<BuildingInfo>();
		
		//设置楼栋信息
		for(int i=0;i<jsonList.size();i++){
			
			BuildingInfo buildingInfo = new BuildingInfo();
			//插入前的准备
			buildingInfo.preInsert();
			
			JSONObject jsonObject = jsonList.getJSONObject(i);
			//楼栋id
			String id = jsonObject.getString("buildingId");
			buildingInfo.setId(id);
			
			//楼栋名称
			String name = jsonObject.getString("buildingName");
			buildingInfo.setBuildingName(name);
			
			//排序字段
			String sortNum = jsonObject.optString("sort");
			if( "null".equals(sortNum) || StringUtils.isBlank(sortNum)){
				buildingInfo.setSortNum(0);
			}else{
				buildingInfo.setSortNum(Integer.parseInt(sortNum));				
			}
			
			
			//楼盘ID
			buildingInfo.setVillageInfoId(villageId);

			buldingList.add(buildingInfo);	
			
		}
		
		//楼栋信息存在，把从接口获得的楼栋信息，插入到本数据库
		if(buldingList !=null && buldingList.size() > 0){
			//楼栋信息插入前，先删除楼栋信息
			buildingInfoService.deleteBulding(villageId);
			
			//楼栋信息删除之后，再插入楼栋信息
			int result = buildingInfoService.insertBatch(buldingList);
			//插入成功
			if(result > 0){
				logger.info("插入楼栋信息成功，插入条数为：" + result + "条");
			//插入失败
			}else{
				logger.warn("插入楼栋信息失败");
			}
		}
	}
}
