package com.ktds.lizzy.department.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.department.biz.DepartmentBiz;
import com.ktds.lizzy.department.biz.DepartmentBizImpl;
import com.ktds.lizzy.department.vo.DepartmentVO;

public class DoAddActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz;

    public DoAddActionServlet() {
      departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int departmentId;
		int managerId;
		int locationId;
		try {
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
			managerId = Integer.parseInt(request.getParameter("managerId"));
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 형식입니다.");
		}
		String departmentName = request.getParameter("departmentName");
		
		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setDepartmentId(departmentId);
		departmentVO.setDepartmentName(departmentName);
		departmentVO.setManagerId(managerId);
		departmentVO.setLocationId(locationId);
		
		if (departmentBiz.addDept(departmentVO)) {
			response.sendRedirect("/Homework/departmentsBoard");
		}
		else {
			response.sendRedirect("/Homework/add");
		}
	}

}