package com.ktds.lizzy.department.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.lizzy.department.vo.DepartmentVO;

import oracle.net.aso.q;

import com.ktds.lizzy.dao.support.JDBCDaoSupport;
import com.ktds.lizzy.dao.support.QueryHandler;
import com.ktds.lizzy.dao.support.annotation.BindData;

public class DepartmentDaoImpl extends JDBCDaoSupport implements DepartmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentVO> selectAllDepts() {
		return selectList(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				                                         
				query.append(" SELECT  DEPARTMENT_ID, "   );
				query.append("         DEPARTMENT_NAME, " );
				query.append("         MANAGER_ID, "      );
				query.append("         LOCATION_ID "      );
				query.append(" FROM    DEPARTMENTS "      );
				query.append(" ORDER   BY	DEPARTMENT_ID ");

				
				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				
			}
			
			@Override
			public Object getData(ResultSet rs) {
				DepartmentVO departmentVO = new DepartmentVO();
				BindData.bindData(rs, departmentVO);
				return departmentVO;
			}
		});
	}

	@Override
	public DepartmentVO selectOneDept(int departmentId) {
		return (DepartmentVO) selectOne(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT  DEPARTMENT_ID, "      );
				query.append("         DEPARTMENT_NAME, "    );
				query.append("         MANAGER_ID, "         );
				query.append("         LOCATION_ID "         );
				query.append(" FROM    DEPARTMENTS "         );
				query.append(" WHERE   DEPARTMENT_ID = ? "   );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, departmentId);;
			}
			
			@Override
			public Object getData(ResultSet rs) {
				DepartmentVO departmentVo = new DepartmentVO();
				BindData.bindData(rs, departmentVo);
				return departmentVo;
			}
		});
	}

	@Override
	public int insertDept(DepartmentVO departmentVO) {
		return update(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				                                                             
				query.append(" INSERT  INTO    DEPARTMENTS ( "                   );
				query.append("                                 DEPARTMENT_ID, "  );
				query.append("                                 DEPARTMENT_NAME, "); 
				query.append("                                 MANAGER_ID, "     );
				query.append("                                 LOCATION_ID "     );
				query.append(" )               VALUES      ( "                   );
				query.append("                                 ?, "              );
				query.append("                                 ?, "              );
				query.append("                                 ?, "              );
				query.append("                                 ? "               );
				query.append(" ) "                                               );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, departmentVO.getDepartmentId());
				stmt.setString(2, departmentVO.getDepartmentName());
				stmt.setInt(3, departmentVO.getManagerId());
				stmt.setInt(4, departmentVO.getLocationId());
			}
			
			@Override
			public Object getData(ResultSet rs) {
				
				return null;
			}
		});
	}

	@Override
	public int updateDept(DepartmentVO departmentVO) {
		return update(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE  DEPARTMENTS "           );
				query.append(" SET     DEPARTMENT_NAME = ?, "  );
				query.append("         MANAGER_ID = ?, "       );
				query.append("         LOCATION_ID = ? "       );
				query.append(" WHERE   DEPARTMENT_ID = ? "     );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, departmentVO.getDepartmentName());
				stmt.setInt(2, departmentVO.getManagerId());
				stmt.setInt(3, departmentVO.getLocationId());
				stmt.setInt(4, departmentVO.getDepartmentId());
			}
			
			@Override
			public Object getData(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	@Override
	public int deleteDept(int departmentId) {
		return update(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE  FROM    DEPARTMENTS "  );
				query.append(" WHERE   DEPARTMENT_ID = ? "    );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, departmentId);
			}
			
			@Override
			public Object getData(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

}
