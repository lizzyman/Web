package com.melon.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.album.vo.AlbumVO;
import com.melon.artist.vo.ArtistVO;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class MusicDaoImpl implements MusicDao {

	@Override
	public int insertNewMusic(MusicVO musicVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "Melon", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT  INTO    MSC     ( "                                                                      );
			query.append("                             MSC_ID "                                                             );
			query.append("                             , ALBM_ID "                                                           );
			query.append("                             , TTL "                                                              );
			query.append("                             , LK_CNT "                                                           );
			query.append("                             , MP3_FL "                                                           );
			query.append("                             , MSCN "                                                             );
			query.append("                             , DRTR "                                                             );
			query.append("                             , LRCS "                                                             );
			query.append("                         ) "                                                                      );
			query.append("                 VALUES  ( "                                                                      );
			query.append("            'MS-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(MSC_ID_SEQ.NEXTVAL, 6, '0') "); 
			query.append("                             , ? "                                                                );
			query.append("                             , ? "                                                                );
			query.append("                             , 0 "                                                                );
			query.append("                             , ? "                                                                );
			query.append("                             , ? "                                                                );
			query.append("                             , ? "                                                                );
			query.append("                             , ? "                                                                );
			query.append("                         ) "                                                                      );

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicVO.getAlbumId());
			stmt.setString(2, musicVO.getTitle());
			stmt.setString(3, musicVO.getMp3File());
			stmt.setString(4, musicVO.getMusician());
			stmt.setString(5, musicVO.getDirector());
			stmt.setString(6, musicVO.getLyrics());
			
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null) {
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
	public int selectAllMusicCount(MusicSearchVO musicSearchVO) {
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
			conn = DriverManager.getConnection(url, "Melon", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  COUNT(1) CNT "                );
			query.append(" FROM    MSC M "                       );
			query.append("         , ALBM AL "                   );
			query.append("         , ARTST AR "                  );
			query.append(" WHERE   M.ALBM_ID = AL.ALBM_ID "      );
			query.append(" AND     AL.ARTST_ID = AR.ARTST_ID "   );
			query.append(" AND     AL.ALBM_ID = ? "   );
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicSearchVO.getAlbumId());
			
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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
			}
			try {
				if (stmt != null) {
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
	public List<MusicVO> selectAllMusic(MusicSearchVO musicSearchVO) {
		
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
			conn = DriverManager.getConnection(url, "Melon", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  * "                                                     );
			query.append(" FROM    ( "                                                     );
			query.append("             SELECT  ROWNUM AS RNUM "                            );
			query.append("                     , A.* "                                     );
			query.append("             FROM    ( "                                         );
			query.append("                         SELECT  M.MSC_ID "                      );
			query.append("                                 , M.ALBM_ID AS ALBM_ID "        );
			query.append("                                 , M.TTL "                       );
			query.append("                                 , M.LK_CNT "                    );
			query.append("                                 , M.MP3_FL "                    );
			query.append("                                 , M.MSCN "                      );
			query.append("                                 , M.DRTR "                      );
			query.append("                                 , M.LRCS "                      );
			query.append("                                 , AL.ALBM_ID AS AL_ALBM_ID "    );
			query.append("                                 , AL.ARTST_ID AS ARTST_ID "     );
			query.append("                                 , AL.RLS_DT "                   );
			query.append("                                 , AL.PBLSHR "                   );
			query.append("                                 , AL.ENTMNT "                   );
			query.append("                                 , AL.GNR "                      );
			query.append("                                 , AL.PST "                      );
			query.append("                                 , AL.ALBM_NM "                  );
			query.append("                                 , AR.ARTST_ID AS AR_ARTST_ID "  );
			query.append("                                 , AR.MBR "                      );
			query.append("                                 , AR.DEBUT_DT "                 );
			query.append("                                 , AR.DEBUT_TTL "                );
			query.append("                         FROM    MSC M "                         );
			query.append("                                 , ALBM AL "                     );
			query.append("                                 , ARTST AR "                    );
			query.append("                         WHERE   M.ALBM_ID = AL.ALBM_ID "        );
			query.append("                         AND     AL.ARTST_ID = AR.ARTST_ID "     );
			query.append("                         AND     M.ALBM_ID = ? "                 );
			query.append("                         AND     AL.ARTST_ID = ? "               );
			query.append("                         ORDER   BY	MSC_ID	DESC "             );
			query.append("                     ) A "                                       );
			query.append("             WHERE ROWNUM <= ? "                                 );
			query.append("         ) "                                                     );
			query.append(" WHERE   RNUM >= ? "                                             );
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicSearchVO.getAlbumId());
			stmt.setString(2, musicSearchVO.getArtistId());
			stmt.setInt(3, musicSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(4, musicSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<MusicVO> musicList = new ArrayList<MusicVO>();
			MusicVO musicVO = null;
			AlbumVO albumVO = null;
			ArtistVO artistVO = null;
			
			while (rs.next()) {
				musicVO = new MusicVO();
				
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector(rs.getString("DRTR"));
				musicVO.setLyrics(rs.getString("LRCS"));
				
				albumVO = musicVO.getAlbumVO();
				albumVO.setAlbumId(rs.getString("ALBM_ID"));
				albumVO.setArtistId(rs.getString("ARTST_ID"));
				albumVO.setReleaseDate(rs.getString("RLS_DT"));
				albumVO.setPublisher(rs.getString("PBLSHR"));
				albumVO.setEntertainment(rs.getString("ENTMNT"));
				albumVO.setGenre(rs.getString("GNR"));
				albumVO.setPost(rs.getString("PST"));
				albumVO.setAlbumName(rs.getString("ALBM_NM"));
				
				artistVO = albumVO.getArtistVO();
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
				
				musicList.add(musicVO);
			}
			
			return musicList;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
			}
			try {
				if (stmt != null) {
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
	public MusicVO selectOneMusic(String musicId) {
		
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
			conn = DriverManager.getConnection(url, "Melon", "melon");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT  M.MSC_ID "                    );
			query.append("         , M.ALBM_ID AS ALBM_ID "      );
			query.append("         , M.TTL "                     );
			query.append("         , M.LK_CNT "                  );
			query.append("         , M.MP3_FL "                  );
			query.append("         , M.MSCN "                    );
			query.append("         , M.DRTR "                    );
			query.append("         , M.LRCS "                    );
			query.append("         , AL.ALBM_ID AS AL_ALBM_ID "  );
			query.append("         , AL.ARTST_ID AS ARTST_ID "   );
			query.append("         , AL.RLS_DT "                 );
			query.append("         , AL.PBLSHR "                 );
			query.append("         , AL.ENTMNT "                 );
			query.append("         , AL.GNR "                    );
			query.append("         , AL.PST "                    );
			query.append("         , AL.ALBM_NM "                );
			query.append("         , AR.ARTST_ID AS AR_ARTST_ID "); 
			query.append("         , AR.MBR "                    );
			query.append("         , AR.DEBUT_DT "               );
			query.append("         , AR.DEBUT_TTL "              );
			query.append(" FROM    MSC M "                       );
			query.append("         , ALBM AL "                   );
			query.append("         , ARTST AR "                  );
			query.append(" WHERE   M.ALBM_ID = AL.ALBM_ID "      );
			query.append(" AND     AL.ARTST_ID = AR.ARTST_ID "   );
			query.append(" AND     M.MSC_ID = ? "                );

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicId);
			
			rs = stmt.executeQuery();
			
			MusicVO musicVO = null;
			AlbumVO albumVO = null;
			ArtistVO artistVO = null;
			
			if (rs.next()) {
				musicVO = new MusicVO();
				
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector(rs.getString("DRTR"));
				musicVO.setLyrics(rs.getString("LRCS"));
				
				albumVO = musicVO.getAlbumVO();
				albumVO.setAlbumId(rs.getString("ALBM_ID"));
				albumVO.setArtistId(rs.getString("ARTST_ID"));
				albumVO.setReleaseDate(rs.getString("RLS_DT"));
				albumVO.setPublisher(rs.getString("PBLSHR"));
				albumVO.setEntertainment(rs.getString("ENTMNT"));
				albumVO.setGenre(rs.getString("GNR"));
				albumVO.setPost(rs.getString("PST"));
				albumVO.setAlbumName(rs.getString("ALBM_NM"));
				
				artistVO = albumVO.getArtistVO();
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
			}
			
			return musicVO;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
			}
			try {
				if (stmt != null) {
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
	public int deleteOneMusic(String musicId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "Melon", "melon");
			
			StringBuffer query = new StringBuffer();
			query.append(" DELETE   "          );
			query.append(" FROM    MSC "       );
			query.append(" WHERE   MSC_ID = ? "); 
			
			stmt = conn.prepareStatement(query.toString());
			
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (stmt != null) {
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
