package com.ktds.lizzy.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;


/**
 * Servlet implementation class BoardServlet
 */
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardBiz boardBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        boardBiz = new BoardBizImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<BoardVO> articleList = boardBiz.getAllArticles();
		request.setAttribute("articles", articleList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/board.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
