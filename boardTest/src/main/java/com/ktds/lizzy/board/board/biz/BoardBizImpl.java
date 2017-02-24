package com.ktds.lizzy.board.board.biz;

import com.ktds.lizzy.board.board.dao.BoardDao;
import com.ktds.lizzy.board.board.dao.BoardDaoImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;

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

	@Override
	public boolean modifyArticle(BoardVO boardVO) {
		return boardDao.updateArticle(boardVO) > 0;
	}

}
