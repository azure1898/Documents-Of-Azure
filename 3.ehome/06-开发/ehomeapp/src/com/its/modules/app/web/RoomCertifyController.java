package com.its.modules.app.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.Address;
import com.its.modules.app.entity.FamilyInfo;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.entity.VillageInfo;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.AddressService;
import com.its.modules.app.service.RoomCertifyService;
import com.its.modules.app.service.VillageInfoService;

/**
 * 房间认证Controller
 * 
 * @author like
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = { "${appPath}/my", "${appPath}/common" })
public class RoomCertifyController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoomCertifyService roomCertifyService;

	@Autowired
	private VillageInfoService villageInfoService;

	@Autowired
	private AddressService addressService;

	/**
	 * 用户绑定的房间列表
	 * 
	 * @param userID
	 *            用户ID
	 * @param buildingID
	 *            楼盘ID
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoomList")
	public Map<String, Object> getRoomList(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, buildingID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}
		VillageInfo village = villageInfoService.get(buildingID);
		if (village == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "楼盘不存在");
			return toJson;
		}

		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("customerService", village.getPhoneNum());
		List<Map<String, Object>> data = new ArrayList<>();
		List<RoomCertify> list = roomCertifyService.getAccountRoomCertify(userID, buildingID);
		for (RoomCertify room : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roomID", room.getId());
			map.put("buildingName", village.getVillageName());
			map.put("roomName", room.getBuildingName() + room.getRoomName());
			map.put("authentication", StringUtils.isNotBlank(room.getAccountId()) ? "1" : "0");
			map.put("isOwner", "0".equals(room.getCustomerType()) ? "1" : "0");
			map.put("userID", room.getAccountId());
			map.put("userName", room.getCustomerName());
			map.put("userPhone", room.getPhoneNum());
			List<FamilyInfo> families = new ArrayList<>();
			List<Map<String, Object>> fData = new ArrayList<>();
			for (FamilyInfo f : families) {
				Map<String, Object> fMap = new HashMap<String, Object>();
				fMap.put("memberID", f.getId());
				fMap.put("memberName", f.getName());
				fMap.put("memberPhone", f.getPhoneNum());
				fData.add(fMap);
			}
			map.put("member", fData);
			data.add(map);
		}
		datas.put("rooms", data);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "成功");
		return toJson;
	}

	/**
	 * 上传图片(http方式)
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param typeID
	 *            图片类型ID（不可空）1->房屋报修 2->投诉建议
	 * @param imageUrl
	 *            图片地址（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getUserRoom", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserRoom(String userID, int typeID, String imageUrl, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, String.valueOf(typeID))) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}

		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		ServletContext servletContext = request.getSession().getServletContext();
		// 解析上下文
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(servletContext);
		// 多部件上传
		if (resolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获取文件名称迭代器
			Iterator<String> it = multipartRequest.getFileNames();
			int index = 0;
			while (it.hasNext()) {
				MultipartFile multipartFile = multipartRequest.getFile(it.next());
				if (multipartFile != null) {
					// 获取原始文件名
					String originalFilename = multipartFile.getOriginalFilename();
					// 获取文件扩展名
					String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
					String dirName = "gif,jpg,jpeg,png,bmp";
					if (!Arrays.asList(dirName.split(",")).contains(suffix)) {
						toJson.put("code", Global.CODE_PROMOT);
						toJson.put("message", "文件上传失败，只允许上传" + dirName + "格式的文件");
						return toJson;
					}
					String fileName = null;
					try {
						fileName = MyFDFSClientUtils.uploadFile(request, multipartFile);
					} catch (IOException | MyException e1) {
						toJson.put("code", Global.CODE_PROMOT);
						toJson.put("message", "文件上传失败");
						return toJson;
					}
					String imagePath = MyFDFSClientUtils.get_fdfs_file_url(request, fileName);

					// 更新路径
					account.setPhoto(fileName);
					accountService.update(account);

					Map<String, Object> data = new HashMap<String, Object>();
					data.put("index", index++);
					data.put("imageName", fileName);
					data.put("imagePath", imagePath);
					datas.add(data);
				}
			}
		} else {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "无文件上传域");
			return toJson;
		}

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", datas);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 获取用户默认地址中的联系信息
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param buildingID
	 *            楼盘ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getUserContact")
	@ResponseBody
	public Map<String, Object> getUserContact(String userID, String buildingID) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, buildingID)) {
			return toJson;
		}
		Address address = addressService.getDefaultAddress(userID, buildingID);
		if (address == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "默认地址不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userName", address.getContact());
		data.put("userPhone", address.getPhoneNum());
		data.put("userAddress", address.getAddress());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}
}