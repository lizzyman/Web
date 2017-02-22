package com.ktds.lizzy.board.biz;

import com.ktds.lizzy.board.vo.BoardVO;

import java.util.List;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardBiz {

    public boolean writeArticle(BoardVO boardVO);

    public List<BoardVO> getAllArticles();

    public BoardVO getOneArticle(int boardId);

    public boolean deleteArticle(int boardId);

}
