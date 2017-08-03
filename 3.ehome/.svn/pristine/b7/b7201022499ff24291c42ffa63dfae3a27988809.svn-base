/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.lesson.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.its.modules.lesson.entity.LessonInfo;
import com.its.modules.lesson.entity.LessonInfoPic;
import com.its.modules.lesson.service.LessonInfoService;
import com.its.modules.service.entity.ServiceInfoPic;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.DictUtils;
import com.its.modules.sys.utils.UserUtils;

/**
 * 课程/培训Controller
 * @author ChenXiangyu
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/lesson/lessonInfo")
public class LessonInfoController extends BaseController {

	@Autowired
	private LessonInfoService lessonInfoService;
	/** 字典类型：课程状态 */
	private static final String DICT_TYPE_LESSON_STATE = "lesson_state";
	/** 字典值：下架 */
	private static final String DICT_VALUE_PULL_OFF_SHELVES = "0";
	/** 字典值：上架 */
	private static final String DICT_VALUE_PUT_ON_SALE = "1";
	/** 消息类型：error */
	private static final String MSG_TYPE_ERROR = "error";
	
	/**
	 * 新增、修改时课程信息的预处理
	 * @param id 课程ID
	 * @return 
	 */
	@ModelAttribute
	public LessonInfo get(@RequestParam(required=false) String id) {
		LessonInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			// 修改的场合
			entity = lessonInfoService.get(id);
		}
		if (entity == null){
			entity = new LessonInfo();
		}
		return entity;
	}
	
	/**
	 * 课程信息列表页数据准备
	 * @param lessonInfo 课程信息
	 * @param sortItem 排序的项目
	 * @param sort 升序还是降序
	 * @param request
	 * @param response
	 * @param model
	 * @return 列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(LessonInfo lessonInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
            Model model) {
		// 取得当前登录的商家信息
		User user = UserUtils.getUser();

		// 只显示属于当前商家的课程
		lessonInfo.setBusinessInfoId(user.getBusinessinfoId());
		lessonInfo.setSort(sort);
		lessonInfo.setSortItem(sortItem);
		// 取得列表数据
		Page<LessonInfo> page = lessonInfoService.findPage(new Page<LessonInfo>(request, response), lessonInfo); 
		
		// 图片显示编辑
		for (LessonInfo lessonItem : page.getList()) {
			if (StringUtils.isNotBlank(lessonItem.getImgs())) {
				String[] imageNames = lessonItem.getImgs().split(",");
				// 图片url集合
				List<String> imageUrls = new ArrayList<String>();
				try {
					imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
				} catch (IOException | MyException e) {
				}
				lessonItem.setImageUrls(imageUrls);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("lessonInfo", lessonInfo);
		return "modules/lesson/lessonInfoList";
	}

	/**
	 * 跳转新增或修改页面
	 * @param lessonInfo 课程信息
	 * @param sortItem 排序的项目
	 * @param sort 升序还是降序
	 * @param request
	 * @param model
	 * @return 新增或修改页面
	 */
	@RequestMapping(value = "form")
	public String form(LessonInfo lessonInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, HttpServletRequest request, Model model) {
		// 如果该课程有图片的信息
		if (StringUtils.isNotBlank(lessonInfo.getImgs())) {
			String[] imageNames = lessonInfo.getImgs().split(",");
			// 保存画面显示用URL
			List<LessonInfoPic> imageUrls = new ArrayList<LessonInfoPic>();
			for (int i = 0; i < imageNames.length; i++) {
				try {
					// 根据DB中文件ID取得图片URL
					LessonInfoPic lessonInfoPic = new LessonInfoPic();
					lessonInfoPic.setImgUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[i]));
					lessonInfoPic.setMyfileid(imageNames[i]);
					imageUrls.add(lessonInfoPic);
				} catch (IOException | MyException e) {
					StringBuffer errorMsg = new StringBuffer();
					errorMsg.append("课程/培训管理-from->");
					errorMsg.append(e.getMessage());
					logger.error(errorMsg.toString());
				}
			}
			model.addAttribute("imgUrls", imageUrls);
		}
		
		// 将LIST画面的排序信息暂时存放在FORM画面中
		lessonInfo.setSort(sort);
		lessonInfo.setSortItem(sortItem);
		model.addAttribute("lessonInfo", lessonInfo);
		return "modules/lesson/lessonInfoForm";
	}

	/**
	 * 执行新增或修改操作
	  * @param lessonInfo 课程信息
	 * @param sortItem 排序的项目
	 * @param sort 升序还是降序
	 * @param request
	 * @param response
	 * @param model
	 * @return 新增或修改页面
	 */
	@RequestMapping(value = "save")
	public String save(LessonInfo lessonInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 特殊字符转换
		// 课程名称
		if (StringUtils.isBlank(lessonInfo.getName())) {
			lessonInfo.setName(StringEscapeUtils.unescapeHtml4(lessonInfo.getName()));
		}
		// 上课地点
		if (StringUtils.isBlank(lessonInfo.getAddress())) {
			lessonInfo.setAddress(StringEscapeUtils.unescapeHtml4(lessonInfo.getAddress()));
		}
		// 课程介绍
		if (StringUtils.isBlank(lessonInfo.getContent())) {
			lessonInfo.setContent(StringEscapeUtils.unescapeHtml4(lessonInfo.getContent()));
		}
		
		// 商家ID设定为当前登录的商家的ID
		lessonInfo.setBusinessInfoId(UserUtils.getUser().getBusinessinfoId());
		
		// 图片校验
		// 本身没有图片，且画面上没有添加
		if (StringUtils.isBlank(lessonInfo.getImgs())
				&& (lessonInfo.getPicList() == null || lessonInfo.getPicList().size() == 0)) {
			addMessage(model, "未选择任何图片");
			model.addAttribute("type", MSG_TYPE_ERROR);
			return form(lessonInfo, lessonInfo.getSortItem(), lessonInfo.getSort(), request, model);
		}
		// 删除后的图片名称
		String imgUrlsForUpdate = lessonInfo.getImgs();
		if (StringUtils.isNotBlank(lessonInfo.getDelImageName())) {
			String[] delImgName = lessonInfo.getDelImageName().split(",");
			for (String imgName : delImgName) {
				imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName + ",", "");
				// 避免该文件在最后一个
				imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName, "");
			}
		}
		// 本身有图片，但全部删除场合
		if (StringUtils.isBlank(imgUrlsForUpdate)
				&& (lessonInfo.getPicList() == null || lessonInfo.getPicList().size() == 0)) {
			addMessage(model, "未选择任何图片");
			model.addAttribute("type", MSG_TYPE_ERROR);
			return form(lessonInfo, lessonInfo.getSortItem(), lessonInfo.getSort(), request, model);
		}
		
		// 字段校验
		if (!beanValidator(model, lessonInfo)){
			return form(lessonInfo, lessonInfo.getSortItem(), lessonInfo.getSort(), request, model);
		}
		
		// 限购数量设定
		if (StringUtils.isNotBlank(lessonInfo.getLessonQuota())) {
			// 不限购的场合
			if ("0".equals(lessonInfo.getLessonQuota())) {
				lessonInfo.setQuotaNum(new Integer(0));
			}
		}
		
		// 判断是否上架
		// 新增商品后若总库存等于0为下架状态
		if (lessonInfo.getPeopleLimit() == null || lessonInfo.getPeopleLimit().intValue() == 0) {
			lessonInfo.setState(
					DictUtils.getDictValue("下架", DICT_TYPE_LESSON_STATE, DICT_VALUE_PULL_OFF_SHELVES));
			lessonInfo.setPeopleLimit(NumberUtils.INTEGER_ZERO);
		} else {
			// 新增商品后若总库存大于0为上架状态
			lessonInfo
					.setState(DictUtils.getDictValue("上架", DICT_TYPE_LESSON_STATE, DICT_VALUE_PUT_ON_SALE));
		}
		
		// 保存课程信息
		lessonInfoService.save(lessonInfo);
		
		// 保存上传的图片
		try {
			lessonInfoService.imgNameUpdate(lessonInfo, saveImgs(lessonInfo, request));
		} catch (IOException | MyException e) {
			addMessage(model, "图片保存失败");
            model.addAttribute("msgType", MSG_TYPE_ERROR);
			return list(new LessonInfo(), sortItem, sort, request, response, model);
		}
		addMessage(model, "保存课程/培训成功");
		return list(new LessonInfo(), sortItem, sort, request, response, model);
	}
	
	/**
	 * 单个课程删除
	 * @param lessonInfo 课程ID
	 * @param sortItem 排序的项目
	 * @param sort 升序还是降序
	 * @param request
	 * @param response
	 * @param model
	 * @return 列表页面
	 */
	@RequestMapping(value = "delete")
	public String delete(LessonInfo lessonInfo, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		lessonInfoService.delete(lessonInfo);
		addMessage(model, "删除课程/培训成功");
		return list(new LessonInfo(), sortItem, sort, request, response, model);
	}
	
	/**
	 * 复数删除
	 * 
	 * @param lessonIds
	 *            要处理的课程ID
	 * @param sortItem
     *            排序的项目
     * @param sort
     *  		   升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliDelete")
	public String muliDelete(@RequestParam(required = true) String lessonIds, @RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		lessonInfoService.muliDelete(lessonIds);
		addMessage(model, "勾选课程/培训删除成功");
		return list(new LessonInfo(), sortItem, sort, request, response, model);
	}

	/**
	 * 复数上架
	 * 
	 * @param lessonIds
	 *            要处理的课程ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliGrounding")
	public String muliGrounding(@RequestParam(required = true) String lessonIds,@RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		// 复数上架课程
		List<String> cannotGroundingLesson = lessonInfoService.muliGrounding(lessonIds);

		// 根据上面返回来的总库存为0的课程弹出信息
		if (cannotGroundingLesson != null && cannotGroundingLesson.size() > 0) {
			StringBuffer lessonName = new StringBuffer();
			for (String cannotGroundingLessonName : cannotGroundingLesson) {
				lessonName.append(cannotGroundingLessonName);
				lessonName.append("、");
			}
			// 去掉最后一个顿号
			String lessonNameStr = lessonName.lastIndexOf("、") == lessonName.length() - 1 ? lessonName.substring(0, lessonName.lastIndexOf("、")) : lessonName.toString();
			model.addAttribute("msgType", MSG_TYPE_ERROR);
			addMessage(model, lessonNameStr + "课程/培训限制人数为0，无法上架，其他勾选课程/培训已上架");
		} else {
			addMessage(model, "勾选课程/培训已上架");
		}
		return list(new LessonInfo(), sortItem, sort, request, response, model);
	}

	/**
	 * 复数下架
	 * 
	 * @param lessonIds
	 *            要处理的课程ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
	 * @return
	 */
	@RequestMapping(value = "muliUndercarriage")
	public String muliUndercarriage(@RequestParam(required = true) String lessonIds,@RequestParam(required = false) String sortItem,
			@RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		lessonInfoService.muliUndercarriage(lessonIds);
		addMessage(model, "勾选课程/培训已下架");
		return list(new LessonInfo(), sortItem, sort, request, response, model);
	}
	
	/**
	 * 保存复数图片
	 * 
	 * @param lessonInfo
	 *            课程信息
	 * @param request
	 * @return 复数图片在文件服务器上的ID
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MyException
	 */
	private List<String> saveImgs(LessonInfo lessonInfo, HttpServletRequest request)
			throws FileNotFoundException, IOException, MyException {
		// 文件删除
		if (StringUtils.isNotBlank(lessonInfo.getDelImageName())) {
			String[] delImgName = lessonInfo.getDelImageName().split(",");
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
		if (lessonInfo.getPicList() != null) {

			for (ServiceInfoPic serviceInfoPic : lessonInfo.getPicList()) {
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