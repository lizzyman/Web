package com.ktds.lizzy.board.board.dao;

import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.board.user.vo.UserVO;
import com.ktds.lizzy.dao.support.JDBCDaoSupport;
import com.ktds.lizzy.dao.support.QueryHandler;
import com.ktds.lizzy.dao.support.annotation.BindData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardDaoImpl extends JDBCDaoSupport implements BoardDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> selectAllArticles(BoardSearchVO searchVO) {
		return selectList(new QueryHandler() {
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM RNUM ");
				query.append(" 						, RST.* ");
				query.append(" 				FROM	( ");
				
				query.append("   SELECT    B.BOARD_ID ");
				query.append("             , B.SUBJECT ");
				query.append("             , B.WRTR ");
				query.append("             , B.LIKE_CNT ");
				query.append("             , B.WRT_DATE ");
				query.append("             , B.IP ");
				query.append("             , U.USR_ID ");
				query.append("             , U.USR_NM ");
				query.append("             , U.JOIN_DT ");
				query.append("   FROM      BOARD B ");
				query.append("             , USERS U ");
				query.append("   WHERE	   B.WRTR = U.USR_ID ");
				query.append("   ORDER	   BY	BOARD_ID DESC ");
				
				query.append(" 						) RST ");
				query.append(" 				WHERE	ROWNUM <= ? ");
				query.append(" 			) ");
				query.append(" WHERE	RNUM >= ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, searchVO.getPager().getEndArticleNumber());
				stmt.setInt(2, searchVO.getPager().getStartArticleNumber());
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
				UserVO userVO = boardVO.getUserVO();

				BindData.startBind(rs, boardVO);
				BindData.bindData(rs, userVO);

				return boardVO;
			}
		});
	}

	@Override
	public int selectAllArticlesCount(BoardSearchVO searchVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "BOARD", "chzhqhf486");
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	COUNT(B.BOARD_ID) CNT ");
			query.append(" FROM		BOARD B               ");
			query.append(" 			, USERS U              ");
			query.append(" WHERE	B.WRTR = U.USR_ID     ");

			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("CNT");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		return 0;
	}

	@Override
	public int insertArticle(BoardVO boardVO) {
		return update(new QueryHandler() {
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				query.append("   INSERT INTO    BOARD   ( ");
				query.append("                              BOARD_ID, ");
				query.append("                              SUBJECT, ");
				query.append("                              CONT, ");
				query.append("                              WRTR, ");
				query.append("                              LIKE_CNT, ");
				query.append("                              WRT_DATE, ");
				query.append("                              IP ");
				query.append("                          ) ");
				query.append("                  VALUES  (");
				query.append("                              BOARD_ID_SEQ.NEXTVAL, ");
				query.append("                              ?, ");
				query.append("                              ?, ");
				query.append("                              ?, ");
				query.append("                              0, ");
				query.append("                              SYSDATE, ");
				query.append("                              ? ");
				query.append("                          ) ");

				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, boardVO.getSubject());
				stmt.setString(2, boardVO.getContents());
				stmt.setString(3, boardVO.getWriter());
				stmt.setString(4, boardVO.getIp());
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public BoardVO selectOneArticle(int boardId) {
		return (BoardVO) selectOne(new QueryHandler() {
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				query.append("   SELECT    B.BOARD_ID ");
				query.append("             , B.SUBJECT ");
				query.append("             , B.CONT ");
				query.append("             , B.WRTR ");
				query.append("             , B.LIKE_CNT ");
				query.append("             , B.WRT_DATE ");
				query.append("             , B.IP ");
				query.append("             , U.USR_ID ");
				query.append("             , U.USR_NM ");
				query.append("             , U.JOIN_DT ");
				query.append("   FROM      BOARD B ");
				query.append("             , USERS U ");
				query.append("   WHERE	   B.WRTR = U.USR_ID ");
				query.append("   AND       BOARD_ID = ?");

				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, boardId);
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
				UserVO userVO = boardVO.getUserVO();

				BindData.startBind(rs, boardVO);
				BindData.bindData(rs, userVO);

				return boardVO;
			}
		});
	}

	@Override
	public int removeArticle(int boardId) {
		return update(new QueryHandler() {
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();

				query.append("   DELETE ");
				query.append("   FROM   BOARD ");
				query.append("   WHERE  BOARD_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, boardId);
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public int updateArticle(BoardVO boardVO) {
		return update(new QueryHandler() {

			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();

				query.append(" UPDATE  BOARD ");
				query.append(" SET     WRTR = ? ");
				query.append("         ,SUBJECT = ? ");
				query.append("         ,CONT = ? ");
				query.append(" WHERE   BOARD_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, boardVO.getWriter());
				stmt.setString(2, boardVO.getSubject());
				stmt.setString(3, boardVO.getContents());
				stmt.setInt(4, boardVO.getBoardId());
			}

			@Override
			public Object getData(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

}
