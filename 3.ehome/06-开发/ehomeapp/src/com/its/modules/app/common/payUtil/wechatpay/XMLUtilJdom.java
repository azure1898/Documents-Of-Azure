package com.its.modules.app.common.payUtil.wechatpay;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.its.common.utils.StringUtils;

public class XMLUtilJdom {

	/**
	 * 解析XML，返回第一级元素键值对，如果第一级元素有子节点，则此节点的值是子节点的XML数据
	 * 
	 * @param xmlStr
	 *            XML字符串
	 * @return Map<String, String>
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> doXMLParse(String xmlStr) throws Exception {
		xmlStr = xmlStr.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if (StringUtils.isBlank(xmlStr)) {
			return null;
		}

		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(inputStream);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element element = (Element) it.next();
			String key = element.getName();
			String value = "";
			List children = element.getChildren();
			if (children.isEmpty()) {
				value = element.getTextNormalize();
			} else {
				value = XMLUtilJdom.getChildrenText(children);
			}

			map.put(key, value);
		}

		// 关闭流
		inputStream.close();
		return map;
	}

	/**
	 * 获取子节点的XML数据
	 * 
	 * @param children
	 *            子节点
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer childrenStr = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				String name = element.getName();
				String value = element.getTextNormalize();
				List list = element.getChildren();
				childrenStr.append("<" + name + ">");
				if (!list.isEmpty()) {
					childrenStr.append(XMLUtilJdom.getChildrenText(list));
				}
				childrenStr.append(value);
				childrenStr.append("</" + name + ">");
			}
		}
		return childrenStr.toString();
	}
}