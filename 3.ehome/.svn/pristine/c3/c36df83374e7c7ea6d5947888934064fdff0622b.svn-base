/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.its.common.utils.Collections3;
import com.its.common.utils.SpringContextHolder;
import com.its.common.utils.StringUtils;
import com.its.modules.setup.entity.BusinessCategorydict;
import com.its.modules.setup.service.BusinessCategorydictService;

/**
 * 字段类型转换
 * @author zhujiao
 * @version 2017-06-28
 */
public class CategoryListType {

	private static BusinessCategorydictService systemService = SpringContextHolder.getBean(BusinessCategorydictService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<BusinessCategorydict> categoryList = Lists.newArrayList();
		List<BusinessCategorydict> allcategoryList = systemService.findAllList();
		for (String s : StringUtils.split(val, ",")){
			for (BusinessCategorydict e : allcategoryList){
				if (StringUtils.trimToEmpty(s).equals(e.getCategoryName())){
					categoryList.add(e);
				}
			}
		}
		return categoryList.size()>0?categoryList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<BusinessCategorydict> categoryList = (List<BusinessCategorydict>)val;
			return Collections3.extractToString(categoryList, "categoryName", ", ");
		}
		return "";
	}
	
}
