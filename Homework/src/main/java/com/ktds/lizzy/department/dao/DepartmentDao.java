package com.ktds.lizzy.department.dao;

import java.util.List;

import com.ktds.lizzy.department.vo.DepartmentVO;

public interface DepartmentDao {
	
	public List<DepartmentVO> selectAllDepts();
	public DepartmentVO selectOneDept(int departmentId);
	public int insertDept(DepartmentVO departmentVO);
	public int updateDept(DepartmentVO departmentVO);
	public int deleteDept(int departmentId);

}
