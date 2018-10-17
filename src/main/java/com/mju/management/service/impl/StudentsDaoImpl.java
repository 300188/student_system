package com.mju.management.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mju.management.entity.Students;
import com.mju.management.service.StudentsDao;
import com.mju.management.utils.MyHibernateSessionFactory;

//学生业务接口的实现类
public class StudentsDaoImpl implements StudentsDao {

	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Students> list = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			hql = "from Students";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return list;
	}

	public Students queryStudentsBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Students s = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Students)session.get(Students.class, sid);
			tx.commit();
			return s;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return s;
	}

	public boolean addStudents(Students s) {
		// TODO Auto-generated method stub
		s.setSid(getNewSid());//设置学生学号
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.commit();
		}finally {
			if (tx != null) {
				tx = null;
			}
		}
		
		return false;
	}

	public boolean updateStudents(Students s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();

		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return false;
	}

	public boolean deleteStudents(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s = (Students) session.get(Students.class, sid);
			session.delete(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();

		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return false;

	}

	// 生成学生的学号
	public String getNewSid() {
		Transaction tx = null;
		try {
			String hql = "select max(sid) from Students";
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			String sid = (String) query.uniqueResult();
			if(sid==null||"".equals(sid)) {
				sid = "S0000001";
			}else {
				String temp = sid.substring(1);//取后七位
				int i = Integer.parseInt(temp);//转成数字
				i++;
				temp = String.valueOf(i);
				int len = temp.length();
				//凑够七位
				for(int j = 0;j<7-len;j++) {
					temp="0"+temp;
				}
				sid = "S"+temp;
			}
			tx.commit();
			return sid;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return null;
	}

	


}
