package com.zhiyou.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhiyou.entity.Student;

public class BeanPostProcessorTest {
	@Test
	public void testBean() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
        Student stu = (Student) ac.getBean("stuBean");  
        System.out.println(stu.toString()); 
	}
}
