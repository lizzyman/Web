package com.ktds.lizzy.department.biz;

import java.util.List;

import com.ktds.lizzy.department.vo.DepartmentVO;

public interface DepartmentBiz {
	
	public List<DepartmentVO> showAllDepts();
	public DepartmentVO showOneDept(int departmentId);
	public boolean addDept(DepartmentVO departmentVO);
	public boolean modifyDept(DepartmentVO departmentVO);
	public boolean removeDept(int departmentId);

}
