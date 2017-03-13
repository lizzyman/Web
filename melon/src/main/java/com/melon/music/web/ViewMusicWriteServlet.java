package com.melon.music.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicVO;

public class ViewMusicWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MusicBiz musicBiz;
	
	public ViewMusicWriteServlet() {
		musicBiz = new MusicBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String albumId = request.getParameter("albumId");
		
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		String title = multipart.getParameter("title");
		String musician = multipart.getParameter("musician");
		String director = multipart.getParameter("director");
		String lyrics = multipart.getParameter("lyrics");
		lyrics.replaceAll("\n", "<br/>");
		lyrics.replaceAll("\r", "");
		
		MultipartFile mp3File = multipart.getFile("mp3File");
		
		if (mp3File != null && mp3File.getFileSize() > 0) {
			String path = "C:\\Users\\Admin\\Documents\\Web\\melon\\src\\main\\webapp\\mp3\\";
			path += albumId;
			
			File dir = new File(path);
			dir.mkdirs();
			
			mp3File.write(path + File.separator + mp3File.getFileName());
		}
		
		MusicVO musicVO = new MusicVO();
		musicVO.setTitle(title);
		musicVO.setAlbumId(albumId);
		musicVO.setMusician(musician);
		musicVO.setLyrics(lyrics);
		musicVO.setMp3File(mp3File.getFileName());
		musicVO.setDirector(director);
		
		if (musicBiz.addNewMusic(musicVO)) {
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
			response.sendRedirect("/melon/music/write?albumId=" + albumId);
		}
		
	}

}
