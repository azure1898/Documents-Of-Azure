package com.zhiyou100.controller;

import com.zhiyou100.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lijike 2016/12/9.
 */
@Controller("/login")
public class LoginController {
    //用户登录
    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        request.getSession().setAttribute("user_session",user);
        return "/main";
    }
}
