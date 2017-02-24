package com.ktds.lizzy.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;

public class ViewDetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    private BoardBiz boardBiz;
    
    public ViewDetailServlet() {
    	boardBiz = new BoardBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setCharacterEncoding("UTF-8");
		
		String boardIdString = request.getParameter("boardId");
		// getParameter()는 문자로 밖에 받을 수 없다.
		int boardId = 0;
		try {
			boardId = Integer.parseInt(boardIdString);
		} 
		catch (NumberFormatException e) {
			throw new RuntimeException("존재하지 않는 게시물입니다.");
		}
		
		BoardVO board = boardBiz.getOneArticle(boardId);
		
		request.setAttribute("board", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/detail.jsp");
		dispatcher.forward(request, response);		
	}

}
