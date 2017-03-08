package com.struts.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBTools {
	public static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	public static final String DB_URL="jdbc:mysql://localhost:3306/strutstest";
	public static final String DB_USERNAME="root";
	public static final String DB_PASSWORD="123456";
	//hiberbate的静态对象sessionFactory
	public static final StandardServiceRegistry registry=
			new StandardServiceRegistryBuilder().configure().build();
	public static final SessionFactory sessionFactory = 
			new MetadataSources(registry).buildMetadata()
			.buildSessionFactory();
	
	public static Connection getConnection() throws Exception{
		Class.forName(DRIVER_CLASS);
		return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	}
	
	public static void closeConnection(Connection conn) throws Exception{
		if(conn!=null && !conn.isClosed()){
			conn.close();
		}
	}
	
	public static ResultSet execSql(Connection conn,String sql) throws Exception{
		Statement stat = conn.createStatement();
		return stat.executeQuery(sql);
	}
	
	//创建获取hibernatesession的静态方法
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void main(String[] args) throws Exception{
		Connection c = getConnection();
		ResultSet rs = execSql(c,"select * from user_account");
		while(rs.next()){
			System.out.println(rs.getString("account_no"));
		}
	}
}
