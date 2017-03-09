package com.melon.album.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.album.biz.AlbumBiz;
import com.melon.album.biz.AlbumBizImpl;
import com.melon.album.vo.AlbumVO;
import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;

public class ViewAlbumWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AlbumBiz albumBiz;
  
    public ViewAlbumWriteServlet() {
    	albumBiz = new AlbumBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/album/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// multipart form을 전송하기 위해선 반드시 MultipartHttpServletRequest 사용
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
		String albumName = multipart.getParameter("albumName");
		String genre = multipart.getParameter("genre");
		String entertainment= multipart.getParameter("entertainment");
		String publisher = multipart.getParameter("publisher");
		String releaseDate = multipart.getParameter("releaseDate");
		String artistId = request.getParameter("artistId");
		String postFileName ="";
		
		// 
		MultipartFile post = multipart.getFile("post");
		
		if (post != null && post.getFileSize() > 0) {
			
			postFileName = post.getFileName();
			
			// 파일을 올린 경우 (동적으로 폴더 생성)
			// 1. 각 아티스트의 앨범 마다 폴더 생성
			File dir = new File("D:\\uploadFiles\\post\\" + artistId + File.separator + albumName);
			dir.mkdirs();
			// 2. 그 폴더에 파일 저장
			post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
		}
		
		AlbumVO albumVO = new AlbumVO();
		
		albumVO.setArtistId(artistId);
		albumVO.setReleaseDate(releaseDate);
		albumVO.setEntertainment(entertainment);
		albumVO.setGenre(genre);
		albumVO.setPublisher(publisher);
		albumVO.setAlbumName(albumName);
		albumVO.setPost(postFileName);
		
		if (albumBiz.addNewAlbum(albumVO)){
			StringBuffer script = new StringBuffer();
			script.append("<script type='text/javascript'>");
			script.append("    opener.location.reload();");
			script.append("    self.close();"); // 스스로 창을 닫아버림
			script.append("</script>");
			
			PrintWriter writer = response.getWriter();
			writer.write(script.toString());
			writer.flush();
			writer.close();
		}
		else {
			response.sendRedirect("/melon/album/write");
		}
	}

}
