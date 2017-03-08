package com.hibernate.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.struts.entity.UserAccount;

public class HibernateTest {
	public static void main(String[] args){
		final StandardServiceRegistry registry=
				new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = 
				new MetadataSources(registry).buildMetadata()
				.buildSessionFactory();
		
		Session sess = sessionFactory.openSession();
		List<UserAccount> ul = 
				sess.createQuery("from UserAccount").getResultList();
		for(UserAccount u:ul){
			System.out.println(u.getAccountNo()+"--"+u.getPassword());
		}
		sess.close();
		sessionFactory.close();
	}
}
