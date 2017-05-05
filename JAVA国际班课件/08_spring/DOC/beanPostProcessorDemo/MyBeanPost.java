package com.zhiyou.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.zhiyou.entity.Student;

public class MyBeanPost implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("前方法 ： " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("后方法 ： " + beanName);
		System.out.println("postProcessAfterInitialization start,param bean is:" + bean + ",beanName:" + beanName);  
        if (bean instanceof Student) {  
            Student stu = new Student();  
            stu.setId(101);  
            stu.setName("xy1");  
            return stu;  
        }  
        return bean; 
	}

}
