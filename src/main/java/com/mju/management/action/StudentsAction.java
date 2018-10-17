package com.mju.management.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mju.management.entity.Students;
import com.mju.management.service.StudentsDao;
import com.mju.management.service.impl.StudentsDaoImpl;
import com.opensymphony.xwork2.ModelDriven;

//学生Action类
public class StudentsAction extends SuperAction implements ModelDriven<Students> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Students stu = new Students();
	public Students getModel() {
		// TODO Auto-generated method stub
		return this.stu;
	}
	//查询所有学生的动作
	public String query() {
		StudentsDao sdao = new StudentsDaoImpl();
		List<Students> list = sdao.queryAllStudents();
		//放进session中
		if(list.size()!=0&&list!=null) {
			session.setAttribute("students_list",list);
			return "query_success";
		}
		session.setAttribute("students_list", null);
		return "query_success";
		
	}
	
	//删除学生
	public String delete() {
		StudentsDao sdao = new StudentsDaoImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
		
	}
	
	//添加学生
	public String add() {
		StudentsDao sdao = new StudentsDaoImpl();
		sdao.addStudents(stu);
		return "add_success";
	}
	//修改学生资料的动作
	public String modify() {
		//获得传递过来的学号
		String sid = request.getParameter("sid");
		StudentsDao sdao = new StudentsDaoImpl();
		Students s = sdao.queryStudentsBySid(sid);
		//保存在会话中
		session.setAttribute("modify_students",s);
		return "modify_success";
	}
	//保存修改后的学生资料动作
	public String save() {
		StudentsDao sdao = new StudentsDaoImpl();
		sdao.updateStudents(stu);
		return "save_success";
	}
	
	//根据id查找学生
	public String search() {
		Students s = (Students)session.getAttribute("search_students");
		if(s!=null) {
			session.removeAttribute("search_students");
		}
		String sid = request.getParameter("sid");
		StudentsDao sdao = new StudentsDaoImpl();
		s = sdao.queryStudentsBySid(sid);
		session.setAttribute("search_students",s);
		return "search_success";

	}

}
