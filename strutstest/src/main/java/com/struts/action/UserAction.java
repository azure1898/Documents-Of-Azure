package com.struts.action;

import java.util.List;

import org.hibernate.Session;

import com.struts.entity.UserAccount;
import com.struts.util.DBTools;
import com.struts.util.Page;

public class UserAction {
	private UserAccount userAccount;
	private List<UserAccount> ul;
	private String userId;
	private Page page;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public List<UserAccount> getUl() {
		return ul;
	}
	public void setUl(List<UserAccount> ul) {
		this.ul = ul;
	}

	public String listUser(){
		Session sess = DBTools.getSession();
		ul = sess.createQuery("from UserAccount").getResultList();
		return "success";
	}
	public String findUser(){
		String hql = "from UserAccount where 1=1 ";
		if(userAccount!=null){
			if(userAccount.getUserId()!=0&&!"".equals(userAccount.getUserId())){
				hql += " and userId="+userAccount.getUserId();
			}
			if(userAccount.getAccountNo()!=null&&!"".equals(userAccount.getAccountNo())){
				hql += " and accountNo='"+userAccount.getAccountNo()+"'";
			}
			if(userAccount.getPassword()!=null&&!"".equals(userAccount.getPassword())){
				hql += " and password='"+userAccount.getPassword()+"'";
			}
		}
		Session sess = DBTools.getSession();
		//String hql1 = "from UserAccount where userId=? and accountNo=?";
		//sess.createQuery(hql).setInteger(1, 111).setString(2, "ddd");
		ul = sess.createQuery(hql).getResultList();
		//String sqlStr = "select * from user_account limit 2 offset 2";
		//sess.createSQLQuery("select * from user_account limit 2 offset 2");
		//sess.createNativeQuery(sqlStr);
		return "success";
	}
	public String editUser(){
		if(userId!=null && !"".equals(userId)){
			Session sess = DBTools.getSession();
			userAccount = sess.get(UserAccount.class, Integer.valueOf(userId));
		}
		return "toedit";
	}
	public String saveUser(){
		Session sess = DBTools.getSession();
		if(userAccount!=null){
			sess.beginTransaction();
			sess.saveOrUpdate(userAccount);
			sess.getTransaction().commit();
			sess.close();
		}
		return listUser();
	}
}
