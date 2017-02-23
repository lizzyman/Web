package com.ktds.lizzy.department.biz;

import java.util.List;

import com.ktds.lizzy.department.dao.DepartmentDao;
import com.ktds.lizzy.department.dao.DepartmentDaoImpl;
import com.ktds.lizzy.department.vo.DepartmentVO;

public class DepartmentBizImpl implements DepartmentBiz {
	
	private DepartmentDao departmentDao;
	
	public DepartmentBizImpl() {
		departmentDao = new DepartmentDaoImpl();
	}

	@Override
	public List<DepartmentVO> showAllDepts() {
		List<DepartmentVO> departments = departmentDao.selectAllDepts();
		return departments;
	}

	@Override
	public DepartmentVO showOneDept(int departmentId) {
		DepartmentVO department = departmentDao.selectOneDept(departmentId);
		return department;
	}

	@Override
	public boolean addDept(DepartmentVO departmentVO) {
		return departmentDao.insertDept(departmentVO) > 0;
	}

	@Override
	public boolean modifyDept(DepartmentVO departmentVO) {
		return departmentDao.updateDept(departmentVO) > 0;
	}

	@Override
	public boolean removeDept(int departmentId) {
		return departmentDao.deleteDept(departmentId) > 0;
	}

}
