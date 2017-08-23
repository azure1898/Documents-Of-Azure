/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.service.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.persistence.Page;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.goods.entity.SortInfo;
import com.its.modules.goods.service.SortInfoService;
import com.its.modules.service.entity.ServiceInfo;
import com.its.modules.service.entity.ServiceInfoPic;
import com.its.modules.service.service.ServiceInfoService;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.entity.BusinessUnit;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.setup.service.BusinessUnitService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.DictUtils;
import com.its.modules.sys.utils.UserUtils;

/**
 * 服务管理Controller
 * 
 * @author admin
 * @version 2017-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/service/serviceInfo")
public class ServiceInfoController extends BaseController {

	@Autowired
	private ServiceInfoService serviceInfoService;
	@Autowired
	private SortInfoService sortInfoService;
	@Autowired
	private BusinessUnitService businessUnitService;
	@Autowired
	private BusinessInfoService businessInfoService;
	/** 字典类型：服务状态 */
	private static final String DICT_TYPE_SERVICE_STATE = "service_state";
	/** 字典值：下架 */
	private static final String DICT_VALUE_PULL_OFF_SHELVES = "0";
	/** 字典值：上架 */
	private static final String DICT_VALUE_PUT_ON_SALE = "1";
	/** 信息提示：下架 */
	private static final String MSG_PULL_OFF_SHELVES = "下架";
	/** 信息提示：上架 */
	private static final String MSG_PUT_ON_SALE = "上架";
	/** 分类种类：服务 */
	private static final String SORT_TYPE_SERVICE = "1";
	/** 消息类型：error */
	private static final String MSG_TYPE_ERROR = "error";

	@ModelAttribute
	public ServiceInfo get(@RequestParam(required = false) String id) {
		ServiceInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = serviceInfoService.get(id);
		}
		if (entity == null) {
			entity = new ServiceInfo();
		}
		return entity;
	}

	/**
	 * 商家服务一览显示
	 * 
	 * @param serviceInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(ServiceInfo serviceInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String warnNum, 
			HttpServletRequest request, HttpServletResponse response,	Model model) {
		// 取得当前登录的商家信息
		User user = UserUtils.getUser();

		// 根据商家ID取得显示信息
		SortInfo sortInfo = new SortInfo();
		sortInfo.setBusinessInfoId(user.getBusinessinfoId());
		// 应该显示服务分类
		sortInfo.setType(SORT_TYPE_SERVICE);
		// 根据登录的商家ID取得分类信息
		List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo);
		model.addAttribute("sortInfoList", sortInfoList);

		// 只显示属于当前商家的服务
		serviceInfo.setBusinessInfoId(user.getBusinessinfoId());
		serviceInfo.setSort(sort);
		serviceInfo.setSortItem(sortItem);
		
		// 设置预警数
		if (StringUtils.isNotBlank(warnNum)) {
			serviceInfo.setStock(Integer.parseInt(warnNum));
		}
		
		// 取得一览数据
		Page<ServiceInfo> page = serviceInfoService.findPage(new Page<ServiceInfo>(request, response), serviceInfo);

		// 图片显示编辑
		for (ServiceInfo serviceItem : page.getList()) {
			if (StringUtils.isNotBlank(serviceItem.getImgs())) {
				String[] imageNames = serviceItem.getImgs().split(",");
				// 图片url集合
				List<String> imageUrls = new ArrayList<String>();
				try {
					imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
				} catch (IOException | MyException e) {
				}
				serviceItem.setImageUrls(imageUrls);
			}
		}

		model.addAttribute("page", page);
		model.addAttribute("serviceInfo", serviceInfo);
		return "modules/service/serviceInfoList";
	}

	/**
	 * 跳转新增或修改页面
	 * 
	 * @param serviceInfo
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @param requestSrc 请求源
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(ServiceInfo serviceInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String requestSrc, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 取得当前登录的商家ID
		User user = UserUtils.getUser();
		SortInfo sortInfo = new SortInfo();
		// 根据商家ID取得显示信息
		sortInfo.setBusinessInfoId(user.getBusinessinfoId());

		// 分类应该显示服务分类
		sortInfo.setType(SORT_TYPE_SERVICE);
		// 根据登录的商家ID取得分类信息
		List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo);
		model.addAttribute("sortInfoList", sortInfoList);
		
		// 取得当前登录的商家信息
		BusinessInfo businessInfo = businessInfoService.get(user.getBusinessinfoId());
		// 若启用自定义单位则获取自定义单位
		if (businessInfo != null
				&& DictUtils.getDictValue("是", "yes_no", "1").equals(businessInfo.getCustomUnitFlag())) {
			// 根据商家ID取得自定义分类
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setBusinessInfoId(user.getBusinessinfoId());
			// 商家自定义单位
			 List<BusinessUnit> businessUnitList = businessUnitService.findList(businessUnit);
			model.addAttribute("businessUnitList", businessUnitList);
		}

		// 如果该服务有图片的信息
		if (StringUtils.isNotBlank(serviceInfo.getImgs())) {
			String[] imageNames = serviceInfo.getImgs().split(",");
			// 保存画面显示用URL
			List<ServiceInfoPic> imageUrls = new ArrayList<ServiceInfoPic>();
			for (int i = 0; i < imageNames.length; i++) {
				try {
					// 根据DB中文件ID取得图片URL
					ServiceInfoPic serviceInfoPic = new ServiceInfoPic();
					serviceInfoPic.setImgUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[i]));
					serviceInfoPic.setMyfileid(imageNames[i]);
					imageUrls.add(serviceInfoPic);
				} catch (IOException | MyException e) {
					logger.error(e.getMessage());
				}
			}
			model.addAttribute("imgUrls", imageUrls);
		}

		// 将LIST画面的排序信息暂时存放在FORM画面中
        serviceInfo.setSort(sort);
        serviceInfo.setSortItem(sortItem);
		model.addAttribute("serviceInfo", serviceInfo);
		
		// 将请求源传到jsp页面
		model.addAttribute("requestSrc", requestSrc != null ? requestSrc : StringUtils.EMPTY);
		return "modules/service/serviceInfoForm";
	}

	/**
	 * 执行新增或修改操作
	 * 
	 * @param serviceInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(ServiceInfo serviceInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 特殊字符转换
		// 服务名称
		if (StringUtils.isNotBlank(serviceInfo.getName())) {
			serviceInfo.setName(StringEscapeUtils.unescapeHtml4(serviceInfo.getName()));
		}
		// 服务介绍
		if (StringUtils.isNotBlank(serviceInfo.getContent())) {
			// 特殊字符转义
			serviceInfo.setContent(StringEscapeUtils.unescapeHtml4(serviceInfo.getContent()));
			// "&"等特殊字符转义
			serviceInfo.setContent(StringEscapeUtils.unescapeHtml4(serviceInfo.getContent()));
		}

		// 商家ID设定为当前登录的商家的ID
		serviceInfo.setBusinessInfoId(UserUtils.getUser().getBusinessinfoId());
		
		// 删除后的图片名称
		String imgUrlsForUpdate = serviceInfo.getImgs();
		if (StringUtils.isNotBlank(serviceInfo.getDelImageName())) {
			String[] delImgName = serviceInfo.getDelImageName().split(",");
			for (String imgName : delImgName) {
				imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName + ",", "");
				// 避免该文件在最后一个
				imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName, "");
			}
		}
		
		// 字段校验
		if (!beanValidator(model, serviceInfo)) {
				return form(serviceInfo, serviceInfo.getSortItem(), serviceInfo.getSort(), null, request, response, model);
		}

		// 限购数量设定
		if (StringUtils.isNotBlank(serviceInfo.getQuota())) {
			// 不限购的场合
			if ("0".equals(serviceInfo.getQuota())) {
				serviceInfo.setQuotaNum(new Integer(0));
			}
		}

		// 判断是否上架
		// 新增商品后若总库存等于0为下架状态
		if (serviceInfo.getStock() == null || serviceInfo.getStock().intValue() == 0) {
			serviceInfo.setState(
					DictUtils.getDictValue(MSG_PULL_OFF_SHELVES, DICT_TYPE_SERVICE_STATE, DICT_VALUE_PULL_OFF_SHELVES));
			serviceInfo.setStock(NumberUtils.INTEGER_ZERO);
		} else {
			// 新增商品后若总库存大于0为上架状态
			serviceInfo
					.setState(DictUtils.getDictValue(MSG_PUT_ON_SALE, DICT_TYPE_SERVICE_STATE, DICT_VALUE_PUT_ON_SALE));
		}

		serviceInfoService.save(serviceInfo);
		try {
			serviceInfoService.imgNameUpdate(serviceInfo, saveImgs(serviceInfo, request));
		} catch (IOException | MyException e) {
			addMessage(model, "图片保存失败");
            model.addAttribute("msgType", MSG_TYPE_ERROR);
			return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
		}
		addMessage(model, "保存服务成功");
		return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
	}

	/**
	 * 删除单个服务
	 * @param serviceInfo
	 * @param sortItem 排序的项目
	 * @param sort 升序还是降序
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(ServiceInfo serviceInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		serviceInfoService.delete(serviceInfo);
		addMessage(model, "删除服务成功");
		return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
	}

	/**
	 * 复数删除
	 * 
	 * @param serviceIds
	 *            要处理的服务ID
	 * @param sortItem
     *            排序的项目
     * @param sort
     *  		   升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliDelete")
	public String muliDelete(@RequestParam(required = true) String serviceIds, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		serviceInfoService.muliDelete(serviceIds);
		addMessage(model, "勾选服务删除成功");
		return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
	}

	/**
	 * 复数上架
	 * 
	 * @param serviceIds
	 *            要处理的服务ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliGrounding")
	public String muliGrounding(@RequestParam(required = true) String serviceIds,@RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		// 复数上架服务
		List<String> cannotGroundingService = serviceInfoService.muliGrounding(serviceIds);

		// 根据上面返回来的总库存为0的服务弹出信息
		if (cannotGroundingService != null && cannotGroundingService.size() > 0) {
			StringBuffer serviceName = new StringBuffer();
			for (String cannotGroundingServiceName : cannotGroundingService) {
				serviceName.append(cannotGroundingServiceName);
				serviceName.append("、");
			}
			// 去掉最后一个顿号
			String serviceNameStr = serviceName.lastIndexOf("、") == serviceName.length() - 1 ? serviceName.substring(0, serviceName.lastIndexOf("、")) : serviceName.toString();
			model.addAttribute("msgType", MSG_TYPE_ERROR);
			addMessage(model, serviceNameStr + "服务库存为0，无法上架，其他勾选服务已上架");
		} else {
			addMessage(model, "勾选服务已上架");
		}

		return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
	}

	/**
	 * 复数下架
	 * 
	 * @param serviceIds
	 *            要处理的服务ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliUndercarriage")
	public String muliUndercarriage(@RequestParam(required = true) String serviceIds,@RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		serviceInfoService.muliUndercarriage(serviceIds);
		addMessage(model, "勾选服务下架成功");
		return list(new ServiceInfo(), sortItem, sort, null, request, response, model);
	}
	
	/**
     * 服务分类下拉菜单数据更新
     * 
     * @param serviceid
     *            要处理的服务ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @return
     */
    @RequestMapping(value = "sortInfoRefresh")
    public String sortInfoRefresh() {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();

        // 根据商家ID取得显示信息
        SortInfo sortInfo_where = new SortInfo();
        sortInfo_where.setBusinessInfoId(user.getBusinessinfoId());
        // 应该显示服务分类
        sortInfo_where.setType(SORT_TYPE_SERVICE);
        // 根据登录的商家ID取得分类信息
        List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo_where);
        // JSON格式返回值
        JSONObject json = new JSONObject();
        for (SortInfo sortInfo : sortInfoList) {
            json.put("id", sortInfo.getId());
            json.put("name", sortInfo.getName());
        }
        return json.toString();
    }

	/**
	 * 保存复数图片
	 * 
	 * @param serviceInfo
	 *            服务信息
	 * @param request
	 * @return 复数图片在文件服务器上的ID
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MyException
	 */
	private List<String> saveImgs(ServiceInfo serviceInfo, HttpServletRequest request)
			throws FileNotFoundException, IOException, MyException {
		// 文件删除
		if (StringUtils.isNotBlank(serviceInfo.getDelImageName())) {
			String[] delImgName = serviceInfo.getDelImageName().split(",");
			for (String imgName : delImgName) {
				// 750X563压缩版文件删除
				MyFDFSClientUtils.delete_file(request, imgName);
				// 220 * 165压缩版文件删除
				MyFDFSClientUtils.delete_file(request, imgName + "_compress1");
				// 134 * 100压缩版文件删除
				MyFDFSClientUtils.delete_file(request, imgName + "_compress2");
			}
		}

		// 图片上传
		List<String> img_file_id_list = new ArrayList<String>();
		if (serviceInfo.getPicList() != null) {

			for (ServiceInfoPic serviceInfoPic : serviceInfo.getPicList()) {
				if (StringUtils.isEmpty(serviceInfoPic.getImgBase64())) {
					continue;
				}
				String img_file_id;
				// 取得文件类型
				String fileType = serviceInfoPic.getType().split("/")[1];
				// 将图片上传至工具类，并压缩图片
				img_file_id = MyFDFSClientUtils.uploadFile(serviceInfoPic.getImgBase64(), fileType, true, request);
				img_file_id_list.add(img_file_id);
			}
		}
		return img_file_id_list;
	}
}