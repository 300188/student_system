package com.mju.management.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MyHibernateSessionFactory {
	private static SessionFactory sessionFactory;//会话工厂属性
	
	//构造方法私有化，保证单例模式
	private MyHibernateSessionFactory() {
		
	}
	
	//共有的静态方法来获取会话工厂
	public static SessionFactory getSessionFactory() {
		if(null==sessionFactory) {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}
		return sessionFactory;
	}
	
}
