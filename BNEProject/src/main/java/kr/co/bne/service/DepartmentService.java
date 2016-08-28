package kr.co.bne.service;

import java.util.List;

import kr.co.bne.dto.DepartmentDTO;

public interface DepartmentService {

	List<DepartmentDTO> getDepartmentList();
	public boolean addDepartment(String deptName);
	int searchManager(String manager_id);
}
