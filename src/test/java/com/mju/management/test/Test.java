package com.mju.management.test;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.mju.management.entity.Users;
import com.mju.management.service.UsersDao;
import com.mju.management.service.impl.UsersDaoImpl;

import junit.framework.Assert;

public class Test {

	@org.junit.Test
	public void createTabletest() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);
	}

	@org.junit.Test
	public void logintest() {
		Users u = new Users(1,"张三","123456");
		UsersDao usersDao = new UsersDaoImpl();
		Assert.assertEquals(true,usersDao.usersLogin(u));
	}
	
}
