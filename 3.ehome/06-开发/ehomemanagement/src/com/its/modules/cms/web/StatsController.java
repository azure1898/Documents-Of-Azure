/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.cms.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.web.BaseController;
import com.its.modules.cms.entity.Category;
import com.its.modules.cms.service.StatsService;

/**
 * 统计Controller
 * @author Jetty
 * @version 2013-5-21
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/stats")
public class StatsController extends BaseController {

	@Autowired
	private StatsService statsService;
	
	/**
	 * 文章信息量
	 * @param paramMap
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:stats:article")
	@RequestMapping(value = "article")
	public String article(@RequestParam Map<String, Object> paramMap, Model model) {
		List<Category> list = statsService.article(paramMap);
		model.addAttribute("list", list);
		model.addAttribute("paramMap", paramMap);
		return "modules/cms/statsArticle";
	}

}
