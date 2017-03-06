package com.ktds.lizzy.board.board.vo;

import com.ktds.lizzy.board.user.vo.UserVO;
import com.ktds.lizzy.dao.support.annotation.Types;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardVO {
	@Types
	private int rnum;
	@Types
	private int boardId;
	@Types
	private String subject;
	@Types(alias = "CONT")
	private String contents;
	@Types(alias = "WRTR")
	private String writer;
	@Types(alias = "LIKE_CNT")
	private int likeCount;
	@Types(alias = "WRT_DATE")
	private String writeDate;
	@Types(alias = "IP")
	private String ip;
	private UserVO userVO;

	public int getrnum() {
		return rnum;
	}

	public void setrnum(int rNum) {
		this.rnum = rNum;
	}

	public UserVO getUserVO() {
		if (userVO == null) {
			userVO = new UserVO();
		}
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
