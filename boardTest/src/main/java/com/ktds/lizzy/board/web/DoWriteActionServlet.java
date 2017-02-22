package com.ktds.lizzy.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.biz.BoardBiz;
import com.ktds.lizzy.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.vo.BoardVO;
import com.ktds.lizzy.dao.support.annotation.BindData;

import oracle.net.aso.d;

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
		
		request.setCharacterEncoding("UTF-8");
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		
		System.out.println(writer);
		System.out.println(subject);
		System.out.println(contents);
		
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContents(contents);
		
		if (boardBiz.writeArticle(boardVO)) {
			//board 페이지로 이동
			// Redirect 방식
			response.sendRedirect("/boardTest/board");
		}
		else {
			//write 페이지로 이동
			// Redirect 방식
			response.sendRedirect("/boardTest/write");
		}
		
		
		
		//forward 방식		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/board");
		//dispatcher.forward(request, response);
		
	}

}
