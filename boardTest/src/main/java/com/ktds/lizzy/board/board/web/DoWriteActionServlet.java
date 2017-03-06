package com.ktds.lizzy.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.board.user.vo.UserVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
	
    public DoWriteActionServlet() {
       boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("_USER_"); // Session Container���� �� ������ �����´�.
		BoardVO boardVO = new BoardVO();
		
		boardVO.setWriter(user.getUserId());
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setSubject(request.getParameter("subject"));
		
		String contents = request.getParameter("contents");
		
		contents = contents.replaceAll("\n", "<br/>");
		contents = contents.replaceAll("\r", "");

		boardVO.setContents(contents);
		
		if (boardBiz.writeArticle(boardVO)) {
			//board �������� �̵�
			// Redirect ���
			response.sendRedirect("/boardTest/board");
		}
		else {
			//write �������� �̵�
			// Redirect ���
			response.sendRedirect("/boardTest/write");
		}

	}

}
