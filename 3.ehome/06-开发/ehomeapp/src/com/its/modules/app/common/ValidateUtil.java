package com.its.modules.app.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;

/**
 * 参数验证工具类类
 * 
 * @author sushipeng
 * 
 * @version 2017-07-25
 */
public class ValidateUtil {
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String TWO = "2";

	/**
	 * 验证参数是否为空
	 * 
	 * @param parameters
	 *            参数列表
	 * @return 验证失败返回true
	 */
	public static boolean validateParams(Map<String, Object> toJson, String... parameters) {
		boolean flag = false;
		for (String string : parameters) {
			if (StringUtils.isBlank(string)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "参数有误");
		}
		return flag;
	}

	/**
	 * 对Double类型参数进行验证并转换
	 * 
	 * @param param
	 *            Double类型参数
	 * @return 若为NULL返回0
	 */
	public static Double validateDouble(Double param) {
		return param == null ? 0 : param;
	}

	/**
	 * 对Integer类型参数进行验证并转换
	 * 
	 * @param param
	 *            Integer类型参数
	 * @return 若为NULL返回0
	 */
	public static Integer validateInteger(Integer param) {
		return param == null ? 0 : param;
	}

	/**
	 * 获取图片服务器中图片完整路径
	 * 
	 * @param fileName
	 *            图片名称
	 * @param compass
	 *            0 文件ID 1文件ID_COMPASS1 2文件ID_COMPASS2
	 * @param request
	 *            request
	 * @return 图片服务器中图片完整路径
	 */
	public static String getImageUrl(String fileName, String compass, HttpServletRequest request) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		String compassName = "";
		if (ONE.equals(compass)) {
			compassName = "_compass1";
		} else if (TWO.equals(compass)) {
			compassName = "_compass2";
		}
		return MyFDFSClientUtils.get_fdfs_file_url(request, fileName + compassName);
	}
}