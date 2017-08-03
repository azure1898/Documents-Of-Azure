package com.its.modules.task;

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
import com.its.modules.account.entity.RoomCertify;
import com.its.modules.account.service.RoomCertifyService;
import com.its.modules.village.entity.BuildingInfo;
import com.its.modules.village.service.BuildingInfoService;


@Component("roomTask")
public class RoomTask extends BaseController {

	@Autowired
	private BuildingInfoService buildingInfoService;
	
	@Autowired
	private RoomCertifyService roomCertifyService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
	 * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */

	/**
	 * 定时卡点计算。每天凌晨 02:00 执行一次
	 */ 
	@Scheduled(cron = "0 0 2 * * *")
	public void createRoomTask() {
		logger.info("---------查询房间信息接口定时任务开始-----------");
		//1.查询楼栋信息；
		BuildingInfo buildingInfo = new BuildingInfo();
		List<BuildingInfo> buildingInfoList= buildingInfoService.findList(buildingInfo);
		
		//2.依据楼栋信息，调用查询楼栋信息接口
		for(BuildingInfo building : buildingInfoList){
			
			StringBuilder builder = new StringBuilder();
			builder.append("json={");
			builder.append("\"buildingId\" :").append("\"").append(building.getId()).append("\",");
			builder.append("\"updateTime\" :").append("\"").append("00000000000000000").append("\"}");
			
			//查询房间信息接口的传入参数
			String buildingPara = builder.toString();
			logger.info("---------查询房间信息接口的传入参数：buildingPara-----------");
			logger.info(buildingPara);
			
			//请求的url设定
			String url = Global.getConfig("roomPath");
			logger.info("url:"+url);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			try {
			 	HttpPost req = new HttpPost(url);
				req.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
				StringEntity content = new StringEntity(buildingPara,"UTF-8");
				req.setEntity(content);	
				CloseableHttpResponse response = httpClient.execute(req);
				
				HttpEntity entity = response.getEntity();
				String results = EntityUtils.toString(entity, "UTF-8");
				logger.info("---------查询房间信息接口的返回result列表：results-----------");
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
		        
		        //查询房间接口成功
		        if(isSuccess && "2000".equals(code)){
		        	 JSONArray jsonList = JSONArray.fromObject(data);
		        	 //3.从接口查询出的房间数据信息，插入到本系统数据库
		        	 this.saveBulding(jsonList,building.getId());
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
	 * 从接口查询出的房间数据信息，插入到本系统数据库
	 * @param jsonArray
	 */
	private void saveBulding(JSONArray jsonList,String buildingId){
		
		BuildingInfo building = new BuildingInfo();
		BuildingInfo buildingInfo =null;
		int updateSuccessCount = 0;
		int insertSuccessCount = 0;
		int updateErrorCount = 0;
		int insertErrorCount = 0;
		
		//查询楼栋信息
		building.setId(buildingId);
		buildingInfo = buildingInfoService.getListById(building);
		
		//设置楼栋信息
		for(int i=0;i<jsonList.size();i++){
			
			RoomCertify room = new RoomCertify();
			JSONObject jsonObject = jsonList.getJSONObject(i);
			//房间id
			String id = jsonObject.getString("roomId");
			room.setId(id);
			
			//房间编号
			String code = jsonObject.getString("roomNO");
			room.setRoomName(code);
			
			//楼层id
			String floorCode = jsonObject.getString("floorCode");
			room.setFloorCode(floorCode);
			
			//楼栋信息不为空
			if(buildingInfo !=null){
				//楼盘ID
				room.setVillageInfoId(buildingInfo.getVillageInfoId());
				//楼栋
				room.setBuildingId(buildingInfo.getId());
				//楼栋名称
				room.setBuildingName(buildingInfo.getBuildingName());
			}
			
			//房间id不为空			
			if(StringUtils.isNotBlank(id)){
				RoomCertify roomCertify = new RoomCertify();
				roomCertify.setId(id);
				int roomCount =  roomCertifyService.countRoom(roomCertify);
				
				//在本系统房间信息存在，把物管的房间编号，楼层Id更新过来
				if(roomCount > 0){
					room.preUpdate();
					int updateResult =  roomCertifyService.updateRoom(room);
					
					if(updateResult > 0){
						//logger.info("更新房间信息成功！");
						updateSuccessCount++;
					}else{
						//logger.warn("更新房间信息失败！");
						updateErrorCount++;
					}
				//在本系统房间信息不存在，把物管的房间信息插入本系统
				}else{					
					room.preInsert();
					room.setId(id);
					int insertResult = roomCertifyService.saveRoom(room);
					if(insertResult > 0){
						//logger.info("插入房间信息成功！");
						insertSuccessCount++;						
					}else{
						//logger.warn("插入房间信息失败！");
						insertErrorCount++;
					}
				}
			}					
		}
		logger.info("插入房间信息成功！插入成功条数为:" + insertSuccessCount+"条");
		logger.info("更新房间信息成功！更新成功条数为:" + updateSuccessCount+"条");
		logger.info("插入房间信息失败条数为:" + insertErrorCount+"条");
		logger.info("更新房间信息失败数为:" + updateErrorCount+"条");
	}
}
