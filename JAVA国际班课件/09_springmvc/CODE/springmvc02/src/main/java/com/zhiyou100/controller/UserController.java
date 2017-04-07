package com.zhiyou100.controller;

import com.zhiyou100.model.User;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lijike 2016/12/6.
 * 1.查询用户列表
 * 2.新增用户
 * 3.修改用户
 * 4.删除用户
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 跳到到用户列表
     * @return
     * http://127.0.0.1:8080/user/list
     * /WEB-INF/view/user/list.jsp
     */
    @RequestMapping("/list")
    //public String list(HttpServletRequest request){
    public String list(Model model){
        //request.setAttribute("hello","hello springmvc");
        //model.addAttribute("hello","hello springmvc!!!");
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan","张三",28,new Date()));
        list.add(new User("lisi","李四",18, new Date()));
        list.add(new User("wangwu","王五",38,new Date()));
        list.add(new User("zhaoliu","赵六",58,new Date()));
        model.addAttribute("list",list);
        return "user/list";//请求转发
    }

    /**
     * 跳转到新增用户
     * /user/add
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        //System.out.println("add geaaaat");
        return "user/add";
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user,HttpServletRequest request){
        //System.out.println("add post");
        System.out.println(user.getBirthday());
        //重定向
        //在同一个controller中跳转
        //return "redirect:list";
        //不同的类进行跳转
        return "redirect:/user/list";
    }
}
