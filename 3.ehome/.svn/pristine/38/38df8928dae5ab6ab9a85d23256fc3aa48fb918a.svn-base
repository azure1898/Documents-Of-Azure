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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
import com.its.common.security.Digests;
import com.its.common.utils.Encodes;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.ValidateUtil;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.VerifyCodeRecordService;

import net.sf.json.JSONObject;

/**
 * 会员信息Controller
 * 
 * @author like
 * 
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = { "${appPath}/account", "${appPath}/my" })
public class AccountController extends BaseController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private VerifyCodeRecordService verifyCodeRecordService;

	@RequestMapping(value = "test")
	@ResponseBody
	public String test() {
		Account account = accountService.getByPhoneNum("13392790301");
		accountService.certifyCustomer(account);
		return "";
	}

	/**
	 * 获取个人资料
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "getUserProfile")
	@ResponseBody
	public Map<String, Object> getUserProfile(String userID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}

		/* Data数据开始 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userName", account.getPhoneNum());
		data.put("userImage", MyFDFSClientUtils.get_fdfs_file_url(request, account.getPhoto()));
		data.put("nickname", account.getNickname());
		data.put("gender", account.getSex());
		data.put("dateBirth", account.getBirthYear());
		data.put("height", account.getHeight());
		data.put("bodyWeight", account.getWeight());
		/* Data数据结束 */

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", data);
		toJson.put("message", "信息已获取");
		return toJson;
	}

	/**
	 * 上传头像
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "uploadUserImage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadUserImage(String userID, HttpServletRequest request) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID)) {
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
		toJson.put("message", "文件上传成功");
		return toJson;
	}

	/**
	 * 保存个人资料
	 * 
	 * @param userID
	 *            用户ID（不可空）
	 * @param nickname
	 *            昵称（不可空）
	 * @param gender
	 *            性别（不可空）
	 * @param dateBirth
	 *            出生年月（可空）
	 * @param height
	 *            身高（可空）
	 * @param bodyWeight
	 *            体重（可空）
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "saveUserProfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUserProfile(String userID, String nickname, String gender, String dateBirth, String height, String bodyWeight) {
		// 验证接收到的参数
		Map<String, Object> toJson = new HashMap<String, Object>();
		if (ValidateUtil.validateParams(toJson, userID, nickname, gender)) {
			return toJson;
		}
		Account account = accountService.get(userID);
		if (account == null) {
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户不存在");
			return toJson;
		}

		account.setNickname(nickname);
		account.setSex(gender);
		account.setBirthYear(dateBirth);
		account.setHeight(height);
		account.setWeight(bodyWeight);
		accountService.update(account);

		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "信息已更新");
		return toJson;
	}

	/**
	 * 会员登录
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param type
	 *            0-账号密码；1-短信验证码登录
	 * @param code
	 *            短信验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String phone, String pwd) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(pwd)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "请输入账号和密码");
			return JSONObject.fromObject(json).toString();
		}
		Account account = accountService.getByPhoneNum(phone);
		if (account == null || !validatePassword(pwd, account.getPwd())) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "手机号或密码错误");
			return JSONObject.fromObject(json).toString();
		}
		// 登录成功，刷新登录状态，页面进入登录前的最后一个页面
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		System.out.println(session.getId());
		// System.out.println(session.getHost());
		// System.out.println(session.getTimeout());
		accountService.certifyCustomer(account);//认证客户
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userID", account.getId());
		data.put("token", "");
		data.put("userName", account.getPhoneNum());
		data.put("userPhone", account.getPhoneNum());
		data.put("userImage", account.getPhoto());
		data.put("nickname", account.getNickname());
		data.put("gender", account.getSex());
		data.put("dateBirth", account.getBirthYear());
		data.put("height", account.getHeight());
		data.put("bodyWeight", account.getWeight());

		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "登录成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 手机号快速登录
	 * 
	 * @param phone
	 * @param verifiyCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "quickLogin", method = RequestMethod.POST)
	public String quickLogin(HttpServletRequest request, String phone, String verifiyCode) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(verifiyCode)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "请输入手机号和验证码");
			return JSONObject.fromObject(json).toString();
		}
		if (!StringUtils.checkPhoneNum(phone)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "请输入正确手机号");
			return JSONObject.fromObject(json).toString();
		}
		Account account = accountService.getByPhoneNum(phone);
		if (account == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "此手机号未注册");
			return JSONObject.fromObject(json).toString();
		}
		if (StringUtils.isBlank(verifiyCode) || !verifyCodeRecordService.checkVerifyCode(phone, verifiyCode, AppGlobal.VERIFY_CODE_TYPE_lOGIN)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "验证码不正确");
			return JSONObject.fromObject(json).toString();
		}
		// 保存SESSION
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// System.out.println(session.getId());
		accountService.certifyCustomer(account);//认证客户
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userID", account.getId());
		data.put("token", "");
		data.put("userName", account.getPhoneNum());
		data.put("userPhone", account.getPhoneNum());
		data.put("userImage", account.getPhoto());
		data.put("nickname", account.getNickname());
		data.put("gender", account.getSex());
		data.put("dateBirth", account.getBirthYear());
		data.put("height", account.getHeight());
		data.put("bodyWeight", account.getWeight());

		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "登录成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 用户注册
	 * 
	 * @param un
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param code
	 *            手机验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, String phone, String pwd, String verifiyCode) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(phone) || !StringUtils.checkPhoneNum(phone)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "请输入正确手机号");
				return JSONObject.fromObject(json).toString();
			}
			// 判断手机号是否已存在
			if (accountService.isExist(phone)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "此手机号已注册");
				return JSONObject.fromObject(json).toString();
			}
			// 判断验证码是否正确
			if (StringUtils.isBlank(verifiyCode) || !verifyCodeRecordService.checkVerifyCode(phone, verifiyCode, AppGlobal.VERIFY_CODE_TYPE_REGISTER)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "验证码不正确");
				return JSONObject.fromObject(json).toString();
			}
			// 判断密码格式是否符合要求
			if (StringUtils.isBlank(pwd) || !pwd.matches("^([A-Z]|[a-z]|[0-9]){6,15}$")) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "密码长度6~15位");
				return JSONObject.fromObject(json).toString();
			}
			// 添加新用户
			Account account = new Account();
			account.setPhoneNum(phone);
			account.setPwd(entryptPassword(pwd));
			accountService.save(account);
			//account = accountService.getByPhoneNum(phone);
			json.put("code", Global.CODE_SUCCESS);
			json.put("message", "注册成功");
			return JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				json.put("code", Global.CODE_ERROR);
				json.put("message", e.getMessage());
				return JSONObject.fromObject(json).toString();
			}
			json.put("code", Global.CODE_ERROR);
			json.put("message", "系统错误");
			return JSONObject.fromObject(json).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "forgetPWD")
	public String forgetPWD(HttpServletRequest request, String phone, String verifiyCode) {
		Map<String, Object> json = new HashMap<String, Object>();
		// 判断手机号是否已注册
		if (StringUtils.isBlank(phone) || !accountService.isExist(phone)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "此手机号未被注册");
			return JSONObject.fromObject(json).toString();
		}
		// 验证验证码是否正确
		if (StringUtils.isBlank(verifiyCode) || !verifyCodeRecordService.checkVerifyCode(phone, verifiyCode, AppGlobal.VERIFY_CODE_TYPE_FORGET)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "验证码不正确");
			return JSONObject.fromObject(json).toString();
		}
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "验证码正确");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 重置密码
	 * 
	 * @param phone
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "resetPWD")
	public String resetPWD(String phone, String pwd) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(phone)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		Account account = accountService.getByPhoneNum(phone);
		if (account == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "此手机号未被注册");
			return JSONObject.fromObject(json).toString();
		}
		if (StringUtils.isBlank(pwd) || !pwd.matches("^([A-Z]|[a-z]|[0-9]){6,15}$")) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "密码长度6~15位");
			return JSONObject.fromObject(json).toString();
		}
		account.setPwd(entryptPassword(pwd));
		accountService.save(account);
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "密码修改成功");
		return JSONObject.fromObject(json).toString();
	}

	@ResponseBody
	@RequestMapping(value = "logout")
	public String logout(String userID) {
		Map<String, Object> json = new HashMap<String, Object>();
		if (StringUtils.isBlank(userID)) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "参数错误");
			return JSONObject.fromObject(json).toString();
		}
		Account account = accountService.get(userID);
		if (account == null) {
			json.put("code", Global.CODE_PROMOT);
			json.put("message", "账号不存在");
			return JSONObject.fromObject(json).toString();
		}
		// 退出登录

		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "已退出");
		return JSONObject.fromObject(json).toString();
	}

	public static final String HASH_ALGORITHM = "SHA-1";
	private static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	private static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	private static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}
}