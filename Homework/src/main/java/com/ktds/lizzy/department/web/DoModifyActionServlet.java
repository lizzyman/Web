package com.ktds.lizzy.department.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.department.biz.DepartmentBiz;
import com.ktds.lizzy.department.biz.DepartmentBizImpl;
import com.ktds.lizzy.department.vo.DepartmentVO;

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz;

    public DoModifyActionServlet() {
    	departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int managerId;
		int locationId;
		int departmentId;
		
		try {
			managerId = Integer.parseInt(request.getParameter("managerId"));
			locationId = Integer.parseInt(request.getParameter("locationId"));
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		DepartmentVO departmentVO = new DepartmentVO();
		
		departmentVO.setDepartmentId(departmentId);
		departmentVO.setDepartmentName(request.getParameter("departmentName"));
		departmentVO.setManagerId(managerId);
		departmentVO.setLocationId(locationId);
		
		if (departmentBiz.modifyDept(departmentVO)) {
			response.sendRedirect("/Homework/departmentsBoard");
		}
		else {
			response.sendRedirect("/Homework/detail");
		}
	}

}
