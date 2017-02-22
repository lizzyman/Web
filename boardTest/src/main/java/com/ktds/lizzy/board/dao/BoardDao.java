package com.ktds.lizzy.board.dao;

import com.ktds.lizzy.board.vo.BoardVO;

import java.util.List;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardDao {

    public int insertArticle(BoardVO boardVO);

    public List<BoardVO> selectAllArticles();

    public BoardVO selectOneArticle(int boardId);

    public int removeArticle(int boardId);

}
