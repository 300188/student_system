package com.mju.management.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mju.management.entity.Users;
import com.mju.management.service.UsersDao;
import com.mju.management.service.impl.UsersDaoImpl;
import com.opensymphony.xwork2.ModelDriven;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Users user = new Users();
	public Users getModel() {
		return this.user;
	}
	//用户登录动作
	public String login() {
		UsersDao usersDao =  new UsersDaoImpl();
		if(usersDao.usersLogin(user)) {
			this.session.setAttribute("loginUserName",user.getUsername());
			return "login_success";
		}
		return "login_failure";
	}
	
	//用户注销方法
	@SkipValidation
	public String logout() {
		
		if(session.getAttribute("loginUserName")!=null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	//表单验证
	@Override
	public void validate() {

		//用户名不能为空
		if("".equals(user.getUsername().trim())) {
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if(user.getPassword().length()<6) {
			this.addFieldError("passwordError", "密码不能小于六位");
		}
	}
	

}
