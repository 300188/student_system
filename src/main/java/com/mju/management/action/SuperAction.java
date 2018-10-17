package com.mju.management.action;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 使用IOC方式
 * 耦合实现
 * @author 谢增程
 *
 */
public class SuperAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletResponse response;//相应对象
	protected HttpServletRequest request;//请求对象
	protected ServletContext application;//全局对象
	protected HttpSession session;//会话对像
 	
	public void setServletContext(ServletContext application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.session = request.getSession();
	}

}
