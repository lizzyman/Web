package com.ktds.lizzy.department.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.department.biz.*;
import com.ktds.lizzy.department.vo.DepartmentVO;

public class DepartmentsBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz;

    public DepartmentsBoardServlet() {
    	departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		List<DepartmentVO> departmentList = departmentBiz.showAllDepts();
		
		request.setAttribute("departments", departmentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/departmentsBoard.jsp");
		dispatcher.forward(request, response);
	}

}
