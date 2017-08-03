/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.edition.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.edition.entity.EditionManage;
import com.its.modules.edition.service.EditionManageService;
import com.its.modules.sys.entity.Dict;
import com.its.modules.sys.utils.DictUtils;

/**
 * 版本管理Controller
 * 
 * @author ChenXiangyu
 * @version 2017-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/edition/editionManage")
public class EditionManageController extends BaseController {
	
	private static final String HYPHEN = "-";
	private static final String APP_STRING = "APP";
	private static final float FILE_SIZE_ONE_KB = 1024F;
	/** 消息类型：error */
	private static final String MSG_TYPE_ERROR = "error";

	@Autowired
	private EditionManageService editionManageService;

	@ModelAttribute
	public EditionManage get(@RequestParam(required = false) String id) {
		EditionManage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = editionManageService.get(id);
		}
		if (entity == null) {
			entity = new EditionManage();
		}
		return entity;
	}

	@RequiresPermissions("edition:editionManage:view")
	@RequestMapping(value = { "list", "" })
	public String list(EditionManage editionManage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 处理产品类型下拉选中的值
		if (editionManage.getProductType() != null && !editionManage.getProductType().isEmpty()) {
			String[] tempSplit = editionManage.getProductType().split(HYPHEN);
			editionManage.setProductLine(tempSplit[0]);
			if (tempSplit.length > 1) {
				editionManage.setSystemType(editionManage.getProductType().split(HYPHEN)[1]);
			} else {
				editionManage.setSystemType(StringUtils.EMPTY);
			}
		}
		
		// 数据查询操作
		Page<EditionManage> page = editionManageService.findPage(new Page<EditionManage>(request, response),
				editionManage);
		model.addAttribute("page", page);
		
		// 产品类型下拉列表内容设置
		Map<String, String> productTypeMap = new HashMap<String, String>();
		// 产品线字典值
		List<Dict> productLineDicts = DictUtils.getDictList("product_line");
		// 系统类型字典值
		List<Dict> prodSysTypeDicts = DictUtils.getDictList("prod_sys_type");
		for (Dict productLineDict : productLineDicts) {
			// 当产品线名称以“APP”结尾时，拼接系统类型
			if (productLineDict.getLabel().endsWith(APP_STRING)) {
				for (Dict prodSysTypeDict : prodSysTypeDicts) {
					productTypeMap.put(productLineDict.getValue() + HYPHEN + prodSysTypeDict.getValue(),
							productLineDict.getLabel() + HYPHEN + prodSysTypeDict.getLabel());
				}
			} else {
				productTypeMap.put(productLineDict.getValue() + HYPHEN , productLineDict.getLabel());
			}
		}
		model.addAttribute("productTypeMap", productTypeMap);
		return "modules/edition/editionManageList";
	}

	@RequiresPermissions("edition:editionManage:view")
	@RequestMapping(value = "form")
	public String form(EditionManage editionManage, Model model, HttpServletRequest request) {
		// 已上传的文件名
		model.addAttribute("fileName", editionManage.getRemarks());
		// 单位转换为MB的文件大小
		if (editionManage.getFileSize() != null && !editionManage.getFileSize().isEmpty()) {
			float tempSize = Float.parseFloat(editionManage.getFileSize()) / FILE_SIZE_ONE_KB / FILE_SIZE_ONE_KB;
			// 保留1位小数
			BigDecimal tempDecimal = new BigDecimal(tempSize);  
			float uploadedfileSize =   tempDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();  
			model.addAttribute("fileSize", uploadedfileSize);
		}
		// 文件路径
		// FOR TEST
//		try {
//			String fileUrl = MyFDFSClientUtils.get_fdfs_file_url(request, editionManage.getFileUrl());
//			System.out.println("软件上传路径：" + fileUrl);
//		} catch (Exception e) {
//		}
		model.addAttribute("editionManage", editionManage);
		return "modules/edition/editionManageForm";
	}

	@RequiresPermissions(value={"edition:editionManage:add","edition:editionManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(EditionManage editionManage, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
		// 版本名称特殊字符转义
		if (StringUtils.isNotBlank(editionManage.getEditionName())) {
			editionManage.setEditionName(StringEscapeUtils.unescapeHtml4(editionManage.getEditionName()));
		}
		// 版本号特殊字符转义
		if (StringUtils.isNotBlank(editionManage.getEditionNo())) {
			editionManage.setEditionNo(StringEscapeUtils.unescapeHtml4(editionManage.getEditionNo()));
		}
		// 版本说明特殊字符转义
		if (StringUtils.isNotBlank(editionManage.getEditionInstruction())) {
			editionManage.setEditionInstruction(StringEscapeUtils.unescapeHtml4(editionManage.getEditionInstruction()));
		}
		
		if (!beanValidator(model, editionManage)) {
			return form(editionManage, model, request);
		}
		
		// 产品线名称处理
		if (!DictUtils.getDictLabel(editionManage.getProductLine(), "product_line", StringUtils.EMPTY).endsWith(APP_STRING)) {
			editionManage.setSystemType(StringUtils.EMPTY);
		}
		
		// 软件上传
		if (!(!editionManage.getIsNewRecord() && (file == null || file.getSize() == 0))) {
			try {
				String myFileId = MyFDFSClientUtils.uploadFile(request, file);
				editionManage.setFileUrl(myFileId);
				editionManage.setFileSize(String.valueOf(file.getSize()));
				// 使用备注字段存文件原名(最长255位，过长时截取前255位)
				editionManage.setRemarks(file.getOriginalFilename().length() <= 255 ? file.getOriginalFilename() : file.getOriginalFilename().substring(0, 255));
			} catch (IOException e) {
				addMessage(redirectAttributes, "文件保存失败");
				redirectAttributes.addFlashAttribute("msgType", MSG_TYPE_ERROR);
                // 返回列表页面
                return "redirect:" + Global.getAdminPath() + "/edition/editionManage/?repage";
			} catch (MyException e) {
				addMessage(redirectAttributes, "文件保存失败");
				redirectAttributes.addFlashAttribute("msgType", MSG_TYPE_ERROR);
                // 返回列表页面
                return "redirect:" + Global.getAdminPath() + "/edition/editionManage/?repage";
			}
		}
		
		editionManageService.save(editionManage);
		addMessage(redirectAttributes, "保存版本成功");
		return "redirect:" + Global.getAdminPath() + "/edition/editionManage/?repage";
	}

	@RequiresPermissions("edition:editionManage:delete")
	@RequestMapping(value = "delete")
	public String delete(EditionManage editionManage, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			MyFDFSClientUtils.delete_file(request, editionManage.getFileUrl());
		} catch (FileNotFoundException e) {
			StringBuilder msg = new StringBuilder("删除文件失败：文件");
			msg.append(editionManage.getFileUrl());
			msg.append("未找到");
			logger.warn(msg.toString());
		} catch (IOException e) {
			StringBuilder msg = new StringBuilder("删除文件失败：");
			msg.append(e.getMessage());
			logger.warn(msg.toString());
		} catch (MyException e) {
			StringBuilder msg = new StringBuilder("删除文件失败：");
			msg.append(e.getMessage());
			logger.warn(msg.toString());
		}
		editionManageService.delete(editionManage);
		addMessage(redirectAttributes, "删除版本成功");
		return "redirect:" + Global.getAdminPath() + "/edition/editionManage/?repage";
	}
	
	/**
	 * 校验版本是否重复
	 * @param id 版本ID
	 * @param productLine 产品线 
	 * @param systemType 系统类型
	 * @param editionName 版本名称
	 * @param editionNo 版本号
	 * @return 重复则返回“false”，不重复则返回“true”
	 */
	@RequestMapping(value = "checkEdition")
	@ResponseBody
	public String checkEdition(String id, String productLine, String systemType,String editionName,String editionNo){
		EditionManage editionManage = new EditionManage();
		editionManage.setProductLine(productLine);
		editionManage.setSystemType(systemType);
		editionManage.setEditionName(editionName);
		editionManage.setEditionNo(editionNo);
		List<EditionManage> editionManageList = editionManageService.findList(editionManage);
		if (id == null || id.isEmpty()) {
			// 新增数据时DB不存在相同的版本信息的记录
			if (editionManageList != null && !editionManageList.isEmpty()) {
				return Boolean.FALSE.toString();
			}
		} else {
			// 编辑数据时DB除自己外不存在相同的版本信息的记录
			if (editionManageList != null 
					&& !editionManageList.isEmpty() 
					&& !id.equals(editionManageList.get(0).getId())) {
				return Boolean.FALSE.toString();
			}
		}
		return Boolean.TRUE.toString();
	}

}