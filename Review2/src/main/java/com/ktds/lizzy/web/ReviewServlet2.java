package com.ktds.lizzy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.vo.IntroduceVO;

/**
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet2() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		IntroduceVO introduceVO = new IntroduceVO();
		
		introduceVO.setName("Lizzy");
		introduceVO.setAge(24);
		introduceVO.setAlias("대현동 불주먹");
		request.setAttribute("introduce",introduceVO);
		
		List<IntroduceVO> introduceList = new ArrayList<IntroduceVO>();
		introduceList.add(introduceVO);
		
		request.setAttribute("introduceList", introduceList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/review2.jsp");
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
