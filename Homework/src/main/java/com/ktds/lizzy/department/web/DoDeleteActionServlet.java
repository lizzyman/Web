package com.ktds.lizzy.department.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.department.biz.DepartmentBiz;
import com.ktds.lizzy.department.biz.DepartmentBizImpl;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz;

    public DoDeleteActionServlet() {
    	departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int departmentId;
		
		try {
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		if (departmentBiz.removeDept(departmentId)) {
			response.sendRedirect("/Homework/departmentsBoard");
		}
		else {
			response.sendRedirect("/Homework/detail");
		}
	}

}
