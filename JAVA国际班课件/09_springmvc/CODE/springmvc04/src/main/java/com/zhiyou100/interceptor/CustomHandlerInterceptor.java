package com.zhiyou100.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lijike 2016/12/9.
 */
public class CustomHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在控制器方法调用前执行
     * 返回值为是否中断
     * true,表示继续执行（下一个拦截器或处理器）
     * false则会中断后续的所有操作，所以我们需要使用response来继续响应后续请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        System.out.println("HandlerInterceptor preHandle ....");
        return true;
    }

    /**
     * 在控制器方法调用后，解析视图前调用，我们可以对视图和模型做进一步渲染或修改
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor postHandle ....");
    }

    /**
     * 整个请求完成，即视图渲染结束后调用，这个时候可以做些资源清理工作，或日志记录
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object object, Exception e) throws Exception {
        System.out.println("HandlerInterceptor afterCompletion ....");
    }
}
