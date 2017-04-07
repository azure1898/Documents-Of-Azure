package com.zhiyou100.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyou100.model.ExecuteResult;
import com.zhiyou100.model.User;

/**
 * Created by lijike 2016/12/6. 1.查询用户列表 2.新增用户 3.修改用户 4.删除用户
 *  用于和第三方系统交互数据
 */
@RestController
@RequestMapping("/rest")
public class RestUserController {
	
	/**
	 * @return 用户列表 JSON格式数据返回
	 */
	@RequestMapping("/list")
	public List<User> list() {
		List<User> list = new ArrayList<>();
		list.add(new User("zhangsan", "张三", 28, new Date()));
		list.add(new User("lisi", "李四", 28, new Date()));
		list.add(new User("wangwu", "王五", 28, new Date()));
		list.add(new User("zhaoliu", "赵六", 28, new Date()));
		System.out.println("android端调用 list()接口请求用户列表数据");
		return list;
	}

	/**
	 * 请求json数据 返回json结果
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Map<String, Object> add(@RequestBody User user) {
		System.out.println("android端调用 add()接口发送数据到服务器");
		System.out.println(user);
		return ExecuteResult.jsonReturn("Y", "操作成功!");
	}
}
