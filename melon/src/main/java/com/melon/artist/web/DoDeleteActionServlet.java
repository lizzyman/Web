package com.melon.artist.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.artist.biz.ArtistBiz;
import com.melon.artist.biz.ArtistBizImpl;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArtistBiz artistBiz;
	
	public DoDeleteActionServlet() {
		artistBiz = new ArtistBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/artist/delete.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String artistId = request.getParameter("artistId");
		
		if (artistBiz.deleteOneArtist(artistId)){
			PrintWriter out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		}
		else {
			PrintWriter out = response.getWriter();
			out.write("FAIL");
			out.flush();
			out.close();
		}
	}

}
