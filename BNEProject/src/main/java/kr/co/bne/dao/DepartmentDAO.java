package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.common.DepartmentTeamList;
import java.util.List;

import kr.co.bne.dto.DepartmentDTO;

public interface DepartmentDAO {
	List<DepartmentTeamList> getDepartmentList(int startIdx, int perContentNum, HashMap<String, String> params);
	int getPagingNum_DepartmentList(int perContentNum, HashMap<String, String> params);
	List<DepartmentDTO> getDepartmentList();
	boolean deleteDepartment(int department_id);
	boolean updateDepartment(DepartmentTeamList deptlist);
	int selectDeptCount(String department_name);
	boolean updateManager(DepartmentTeamList deptlist);
	int getManagerCount(String  manager_id);
	boolean addDepartment(DepartmentDTO ddto);

}
