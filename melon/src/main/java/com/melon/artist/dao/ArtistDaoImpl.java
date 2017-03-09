package com.melon.artist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.artist.vo.ArtistSearchVO;
import com.melon.artist.vo.ArtistVO;

import oracle.jdbc.proxy.annotation.Pre;

public class ArtistDaoImpl implements ArtistDao {
	
	@Override
	public int insertNewArtist(ArtistVO artistVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url,"MELON","melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO	ARTST	(                          ");
			query.append(" 							ARTST_ID                   ");
			query.append(" 							, MBR                      ");
			query.append(" 							, DEBUT_DT                 ");
			query.append(" 							, DEBUT_TTL                ");
			query.append(" 						)                              ");
			query.append(" VALUES					(                          ");
			query.append(" 							'AR-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ARTST_ID_SEQ.NEXTVAL, 6, '0') ");
			query.append(" 							, ?                        ");
			query.append(" 							, TO_DATE(?, 'YYYY-MM-DD') ");
			query.append(" 							,?                         ");
			query.append(" 						)                              ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, artistVO.getMember());
			stmt.setString(2, artistVO.getDebutDate());
			stmt.setString(3, artistVO.getDebutTitle());
			
			return stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

	@Override
	public int selectAllArtistCount(ArtistSearchVO artistSearchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
			query.append("	SELECT	COUNT(1) CNT     ");
			query.append("	FROM	ARTST        ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("CNT");
			}
			
			return 0;
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}
	
	@Override
	public List<ArtistVO> selectAllArtists(ArtistSearchVO artistSearchVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	*                                       ");
			query.append(" FROM	(                                           ");
			query.append(" 			SELECT	ROWNUM AS RNUM                  ");
			query.append(" 					, A.*                           ");
			query.append(" 			FROM	(                               ");
			query.append(" 						SELECT	ARTST_ID            ");
			query.append(" 								, MBR               ");
			query.append(" 								, TO_CHAR(DEBUT_DT,'YYYY-MM-DD') DEBUT_DT          ");
			query.append(" 								, DEBUT_TTL         ");
			query.append(" 						FROM	ARTST               ");
			query.append(" 						ORDER	BY	ARTST_ID DESC   ");
			query.append(" 					) A                             ");
			query.append(" 			WHERE	ROWNUM <= ?                     ");
			query.append(" 		)                                           ");
			query.append(" WHERE	RNUM >= ?                               ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setInt(1, artistSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, artistSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<ArtistVO> artistList = new ArrayList<ArtistVO>();
			ArtistVO artistVO = null;
			
			while (rs.next()) {
				artistVO = new ArtistVO();
				
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
				
				artistList.add(artistVO);
			}
			
			return artistList;
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

	@Override
	public ArtistVO selectOneArtist(String artistId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbd:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append("	SELECT	ARTST_ID     ");
			query.append(" 			, MBR        ");
			query.append("			, DEBUT_DT   ");
			query.append("			, DEBUT_TTL  ");
			query.append("	FROM	ARTST        ");
			query.append("	WHERE	ARTST_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, artistId);
			
			rs = stmt.executeQuery();
			
			ArtistVO artistVO = null;
			if (rs.next()) {
				artistVO = new ArtistVO();
				
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
			}
			
			return artistVO;
			
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

	@Override
	public int deleteOneArtist(String artistId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" DELETE	FROM	ARTST ");
			query.append(" WHERE	ARTST_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, artistId);
			
			return stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

}
