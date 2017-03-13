package com.melon.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.melon.user.vo.UserVO;

public class UserDaoImpl implements UserDao{

	@Override
	public int insertNewUser(UserVO userVO) {
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
			query.append(" INSERT  INTO    USR     (    "             );
			query.append("                             USR_ID "       );
			query.append("                             , USR_NM "     );
			query.append("                             , USR_PWD "    );
			query.append("                             , USR_PNT "    );
			query.append("                         ) "                );
			query.append("                 VALUES  ( "                );
			query.append("                             ? "            );
			query.append("                             , ? "          );
			query.append("                             , ? "          );
			query.append("                             , 0 "        );
			query.append("                         ) "                );

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());
			
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
	public UserVO selectOneUser(UserVO userVO) {
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
			query.append(" SELECT	USR_ID      ");
			query.append(" 			, USR_NM        ");
			query.append(" 			, USR_PWD       ");
			query.append(" 			, USR_PNT       ");
			query.append(" FROM		USR             ");
			query.append(" WHERE	USR_ID = ?  ");
			query.append(" AND		USR_PWD = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());
			
			rs = stmt.executeQuery();
			UserVO loginUserVO = null;
			if (rs.next()) {
				loginUserVO = new UserVO();
				loginUserVO.setUserId(rs.getString("USR_ID"));
				loginUserVO.setUserName(rs.getString("USR_NM"));
				loginUserVO.setUserPassword(rs.getString("USR_PWD"));
				loginUserVO.setUserPoint(rs.getInt("USR_PNT"));
			}
			
			return loginUserVO;
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
	public int updatePoint(String userId, int point) {
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
			query.append(" UPDATE	USR                     ");
			query.append(" SET		USR_PNT = USR_PNT + ?   ");
			query.append(" WHERE	USR_ID = ?              ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, point);
			stmt.setString(2, userId);
			
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
	public int selectCountByUserId(String userId) {
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
			query.append(" SELECT	COUNT(1) CNT      ");
			query.append(" FROM		USR             ");
			query.append(" WHERE	USR_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);
			
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

}
