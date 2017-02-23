package com.ktds.lizzy.department.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.department.biz.DepartmentBiz;
import com.ktds.lizzy.department.biz.DepartmentBizImpl;
import com.ktds.lizzy.department.vo.DepartmentVO;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz; 
	
    public ViewModifyServlet() {
    	departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		int departmentId;
		
		try {
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		DepartmentVO departmentVO = departmentBiz.showOneDept(departmentId);
		request.setAttribute("department", departmentVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/modify.jsp");
		dispatcher.forward(request, response);
	}

}
