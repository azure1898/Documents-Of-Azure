package com.zhiyou100.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.model.ExecuteResult;
import com.zhiyou100.model.User;

/**
 * Created by lijike 2016/12/6. 1.查询用户列表 2.新增用户 3.修改用户 4.删除用户
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 跳转到UserList页面
	 * 
	 * @return
	 */
	@RequestMapping
	public String index() {
		return "user/list";
	}


	/**
	 * @return 用户列表 JSON格式数据返回
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list() {
		List<User> list = new ArrayList<>();
		list.add(new User("zhangsan", "张三", 28, new Date()));
		list.add(new User("lisi", "李四", 28, new Date()));
		list.add(new User("wangwu", "王五", 28, new Date()));
		list.add(new User("zhaoliu", "赵六", 28, new Date()));
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "user/add";
	}

	/**
	 * 请求json数据 返回json结果
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",  method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, Object> add(@RequestBody User user) {
		System.out.println(user);
		return ExecuteResult.jsonReturn("Y", "操作成功!");
	}
}
