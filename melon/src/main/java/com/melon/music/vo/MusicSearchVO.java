package com.melon.music.vo;

import com.melon.common.web.pager.Pager;
import com.melon.common.web.pager.PagerFactory;

public class MusicSearchVO {
	
	private Pager pager;
	
	private String albumId; // 검색하기 위해 쓰이는 파라미터
	private String artistId;

	public Pager getPager() {
		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE, 20, 10); // 1 page(상위)에 20개씩을 보여주고 하위 페이지에선 10개씩 보여준다
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	
}
