package com.ktds.lizzy.board.vo;

import com.ktds.lizzy.dao.support.annotation.Types;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardVO {

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

}
