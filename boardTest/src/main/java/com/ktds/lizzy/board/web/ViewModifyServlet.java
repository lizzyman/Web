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

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;

    public ViewModifyServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String boardIdString = request.getParameter("boardId");
		int boardId = 0;
		
		try{
			boardId = Integer.parseInt(boardIdString);
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("�������� �ʴ� �Խñ��̰ų� �߸��� �����Դϴ�.");
		}
		
		BoardVO boardVO = boardBiz.getOneArticle(boardId);
		String contents = boardVO.getContents();
		contents = contents.replaceAll("<br/>", "\n");
		boardVO.setContents(contents);
		
		request.setAttribute("board", boardVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/modify.jsp");
		dispatcher.forward(request, response);
	}

}
