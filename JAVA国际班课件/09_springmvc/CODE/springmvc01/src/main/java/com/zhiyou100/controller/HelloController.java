package com.zhiyou100.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lijike 2016/12/6.
 */
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        //1.收集参数、验证参数
        //2.绑定参数到命令对象
        //3.将命令对象传入业务对象进行处理
        //4.选择视图返回
        ModelAndView mv = new ModelAndView();
        //request.setAttribute("hello","Hello SpringMVC !!!");
        //添加模型数据，那么这个数据可以是任意的POJO对象。
        mv.addObject("hello","Hello SpringMVC!!!");
        //request.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(request,response);
        //设置逻辑视图路径
        //mv.setViewName("/WEB-INF/view/hello.jsp");
        mv.setViewName("hello");
        //返回模型和视图
        return mv;
    }
}
