package com.mju.management.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mju.management.entity.Users;
import com.mju.management.service.UsersDao;
import com.mju.management.utils.MyHibernateSessionFactory;

public class UsersDaoImpl implements UsersDao {

	public boolean usersLogin(Users u) {
		Transaction tx = null;//事务对象
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=? and password=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List<Users> list = query.list();
			tx.commit();//提交事务
			if(list.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if(tx!=null) {
				tx=null;
			}
		}
	}

}
