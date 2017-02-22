package com.ktds.lizzy.board.biz;

import com.ktds.lizzy.board.dao.BoardDao;
import com.ktds.lizzy.board.dao.BoardDaoImpl;
import com.ktds.lizzy.board.vo.BoardVO;

import java.util.List;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardBizImpl implements BoardBiz {

    private BoardDao boardDao;

    public BoardBizImpl() {
        boardDao = new BoardDaoImpl();
    }

    @Override
    public boolean writeArticle(BoardVO boardVO) {
        return boardDao.insertArticle(boardVO) > 0;
    }

    @Override
    public List<BoardVO> getAllArticles() {
        return boardDao.selectAllArticles();
    }

    @Override
    public BoardVO getOneArticle(int boardId) {
        return boardDao.selectOneArticle(boardId);
    }

    @Override
    public boolean deleteArticle(int boardId) {
        return boardDao.removeArticle(boardId) > 0;
    }

}
