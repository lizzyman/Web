package com.melon.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;

	public ViewSignUpServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword= request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		
		// 서버에서의 Validation Check
		if (userId == null || userId.length() == 0) {
			response.sendRedirect("/melon/user/signUp?errorCode=0"); // 이 Servlet의 doGet으로 전송됨
			return;
		}
		if (userPassword == null || userPassword.length() == 0) {
			response.sendRedirect("/melon/user/signUp?errorCode=1");
			return;
		}
		if (userName == null || userName.length() == 0) {
			response.sendRedirect("/melon/user/signUp?errorCode=2");
			return;
		}
		if (userService.isDuplicateUserId(userId)) {
			response.sendRedirect("/melon/user/signUp?errorCode=3");
			return;
		}
		
		UserVO newUserVO = new UserVO();
		newUserVO.setUserId(userId);
		newUserVO.setUserPassword(userPassword);
		newUserVO.setUserName(userName);
		
		if (userService.registNewUser(newUserVO)) {
			PrintWriter writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("		opener.location.reload();");
			sb.append("		self.close();");
			sb.append("</script>");
			
			writer.write(sb.toString());
			writer.flush();
			writer.close();
		}
		else {
			response.sendRedirect("/melon/user/signUp");
		}
	}
}
