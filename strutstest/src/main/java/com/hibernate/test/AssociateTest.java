package com.hibernate.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.struts.entity.UserAddress;
import com.struts.util.DBTools;

public class AssociateTest {
	public static void main(String[] args) {
		Session sess = DBTools.getSession();
		List<UserAddress> list = sess.createQuery("from UserAddress").getResultList();
		for (UserAddress ua : list) {
			System.out.println(ua.getUserAccount().getAccountNo());
		}
	}
}
