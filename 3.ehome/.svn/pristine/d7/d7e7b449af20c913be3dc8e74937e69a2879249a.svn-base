/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldInfoPrice;
import com.its.modules.field.entity.FieldInfoPriceList;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoPriceService;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.field.service.FieldPartitionPriceService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 场地预约Controller
 * @author xzc
 * @version 2017-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/field/fieldInfo")
public class FieldInfoController extends BaseController {
	/**
	 * 场地预约Service
	 */
	@Autowired
	private FieldInfoService fieldInfoService;
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
	
	@ModelAttribute
	public FieldInfo get(@RequestParam(required=false) String id) {
		FieldInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fieldInfoService.get(id);
		}
		if (entity == null){
			entity = new FieldInfo();
		}
		return entity;
	}

	/**
	 * 场地列表
	 * @param fieldInfo 场地信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("field:fieldInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FieldInfo fieldInfo, HttpServletRequest request, HttpServletResponse response, Model model,String isfull) {
		if (fieldInfo.getPartitionPrice()==null){
			fieldInfo.setPartitionPrice(new FieldPartitionPrice());
			fieldInfo.getPartitionPrice().setAppointmentTime(new Date());
		}
//		Page<FieldInfo> page = fieldInfoService.findPage(new Page<FieldInfo>(request, response), fieldInfo);
		List<FieldInfo> list = fieldInfoService.findList(fieldInfo);
		
		boolean _b = true;
		
		if((isfull==null?"0":isfull).equals("1")){//只查询约满的场地
			for(int i=0;i<list.size();i++){
				List<FieldPartitionPrice> fppList =  list.get(i).getFieldPartitionPriceList();
				if(fppList==null || fppList.size()<1){
					list.remove(i);
					i--;
					continue;
				}
				_b=true;
				for(FieldPartitionPrice fpp:fppList){
					if(fpp.getState().equals("0")){//可预约
						_b=false;break;
					}
				}
				if(!_b){
					list.remove(i);
					i--;
				}
			}
		}
		model.addAttribute("list", list);
		return "modules/field/fieldInfoList";
	}

	/**
	 * 查看
	 * @param fieldInfo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("field:fieldInfo:view")
	@RequestMapping(value = "form")
	public String form(FieldInfo fieldInfo, Model model,String tag) {
		model.addAttribute("fieldInfo", fieldInfo);
		List<FieldInfoPrice> fieldInfoPrices=new ArrayList<FieldInfoPrice>();
		FieldInfoPrice fieldInfoPrice=new FieldInfoPrice();
		fieldInfoPrice.setFieldInfoId(fieldInfo.getId()==null||fieldInfo.getId().equals("")?"-1":fieldInfo.getId());
		fieldInfoPrices=fieldInfoPriceService.findList(fieldInfoPrice);
		model.addAttribute("fieldInfoPrices", fieldInfoPrices);
		String minTime = fieldInfo.getStartOpenTime()==null?"0":fieldInfo.getStartOpenTime();
		String maxTime = fieldInfo.getEndOpenTime()==null?"0":fieldInfo.getEndOpenTime();
		model.addAttribute("minTime", Double.parseDouble(minTime.replace(":",".")));
		model.addAttribute("maxTime", Double.parseDouble(maxTime.replace(":",".")));
		model.addAttribute("tag", tag);
		//System.out.printf(fieldInfo.getWeekStr().toString());
		return "modules/field/fieldInfoForm";
	}

	/**
	 * 添加或者修改场地
	 * @param fieldInfo 场地对象
	 * @param weekStr 星期组合
	 * @param fieldInfoPriceList
	 * @param isAll 是否全部删除分段价格
	 * @param delId 删除的分段价格ID
	 * @param model model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("field:fieldInfo:edit")
	@RequestMapping(value = "save")
	public String save(String tag,FieldInfo fieldInfo, String[] weekStr, FieldInfoPriceList fieldInfoPriceList,boolean isAll,String[] delId, Model model, RedirectAttributes redirectAttributes) {
		User user= UserUtils.getUser();

		if (!beanValidator(model, fieldInfo)){
			return form(fieldInfo, model,tag);
		}
		fieldInfo.setBusinessInfoId(user.getBusinessinfoId());

		if(fieldInfo.getCreateState()==null||fieldInfo.getCreateState().equals("")){
			fieldInfo.setCreateState("0");
		}
		//处理星期问题
		fieldInfo.setMonday("0");
		fieldInfo.setTuesday("0");
		fieldInfo.setWednesday("0");
		fieldInfo.setThursday("0");
		fieldInfo.setFriday("0");
		fieldInfo.setSaturday("0");
		fieldInfo.setSunday("0");
		for (int  i=0;i<weekStr.length;i++){
			if (weekStr[i].equals("1")){
				fieldInfo.setMonday("1");
			}else if(weekStr[i].equals("2")){
				fieldInfo.setTuesday("1");
			}else if(weekStr[i].equals("3")){
				fieldInfo.setWednesday("1");
			}else if(weekStr[i].equals("4")){
				fieldInfo.setThursday("1");
			}else if(weekStr[i].equals("5")){
				fieldInfo.setFriday("1");
			}else if(weekStr[i].equals("6")){
				fieldInfo.setSaturday("1");
			}else if(weekStr[i].equals("7")){
				fieldInfo.setSunday("1");

			}
		}
		//保存场地
		fieldInfoService.save(fieldInfo,fieldInfoPriceList,isAll,delId);
		addMessage(redirectAttributes, "保存场地预约成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfo/?repage";
	}

	/**
	 *删除场地
	 * @param fieldInfo 场地信息对象
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequiresPermissions("field:fieldInfo:delete")
	@RequestMapping(value = "delete")
	public String delete(FieldInfo fieldInfo, RedirectAttributes redirectAttributes) throws ParseException {
		//检查是否存在已预约的场地
		/* 场地预约 */
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
		List<FieldInfo> fieldInfoList = fieldInfoService.findList(fieldInfo); //该场地最近8天场地预约信息
		List<FieldPartitionPrice> fppList = fieldInfoList.get(0).getFieldPartitionPriceList();
		boolean _b = true;
		for(FieldPartitionPrice fpp:fppList){
			if(fpp.getState().equals("1")){//已预约
				_b=false;break;
			}
		}
		if(!_b){//存在已预约项 将标记删除
			fieldInfo.setDelFlag("3");
			fieldInfoService.save(fieldInfo);
			addMessage(redirectAttributes, "将在已预约场地消费后自动删除");
		}else{
			fieldInfoService.deleteAll(fieldInfo);
			addMessage(redirectAttributes, "删除场地预约成功");
		}
		
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfo/?repage";
	}

	/**
	 * 删除场地分段信息
	 * @param fieldInfoPrice 场地分段价格编辑对象
	 * @param isAll 是否全部删除
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("field:fieldInfo:edit")
	@RequestMapping(value = "deleteFieldInfoPrice")
	public String deleteFieldInfoPrice(FieldInfoPrice fieldInfoPrice,boolean isAll, RedirectAttributes redirectAttributes) {
		fieldInfoPriceService.deleteAll(fieldInfoPrice,isAll);
		return "删除场地预约分段价格成功";
	}

	/**
	 * 暂停场地
	 * @param fieldInfo 场地信息对象
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("field:fieldInfo:suspend")
	@RequestMapping(value = "suspend")
	public String suspend(FieldInfo fieldInfo,  RedirectAttributes redirectAttributes){
		fieldInfoService.updateSuspend(fieldInfo);
		addMessage(redirectAttributes, "暂停场地预约成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfo/?repage";
	}

	/**
	 * 恢复场地
	 * @param fieldInfo 场地信息对象
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("field:fieldInfo:recovery")
	@RequestMapping(value = "recovery")
	public String recovery(FieldInfo fieldInfo,  RedirectAttributes redirectAttributes){
		fieldInfoService.updateRecovery(fieldInfo);
		addMessage(redirectAttributes, "恢复场地预约成功");
		return "redirect:"+Global.getAdminPath()+"/field/fieldInfo/?repage";
	}


}