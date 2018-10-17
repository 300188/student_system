package com.mju.management.service;

import com.mju.management.entity.Users;

public interface UsersDao {
	//用户登录方法
	public boolean usersLogin(Users u);
}
