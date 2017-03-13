package com.melon.music.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.common.web.pager.ClassicPageExplorer;
import com.melon.common.web.pager.PageExplorer;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class ViewMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MusicBiz musicBiz;

	public ViewMusicListServlet() {
		musicBiz = new MusicBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String albumId = request.getParameter("albumId");
		String artistId = request.getParameter("artistId");
		String pageNo = request.getParameter("pageNo");
		
		MusicSearchVO musicSearchVO = new MusicSearchVO();
		musicSearchVO.setAlbumId(albumId);
		musicSearchVO.setArtistId(artistId);
		musicSearchVO.getPager().setPageNumber(pageNo);
		
		List<MusicVO> musicList = musicBiz.getAllMusic(musicSearchVO);
		
		PageExplorer pager = new ClassicPageExplorer(musicSearchVO.getPager());
		
		request.setAttribute("musicList", musicList);
		request.setAttribute("totalCount", musicSearchVO.getPager().getTotalArticleCount());
		//System.out.println(musicSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "prev", "next", "searchForm"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/list.jsp");
		dispatcher.forward(request, response);
	}

}
