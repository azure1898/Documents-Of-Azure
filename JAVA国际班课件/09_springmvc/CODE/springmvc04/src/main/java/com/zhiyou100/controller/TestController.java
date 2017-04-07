package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lijike 2016/12/9.
 */
@Controller("/test")
public class TestController {
    @RequestMapping
    public String test(){
        System.out.println("准备跳转到试图");
        return "test/index";
    }
}
