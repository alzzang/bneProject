package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.EmployeeDTO;

public interface EmployeeDAO {

	public EmployeeDTO selectEmployee(String id);
	public List<EmployeeDTO> selectTeamMember(int id);
	public void updatePassword(HashMap<String,String> info);
	public void updateFile(HashMap<String, String> info);
	public void updateFile(String id);
	public EmployeeDTO selectImage(String employee_id);
	public List<EmployeeDTO> getEmpSearch(String empSearch);
	List<EmployeeDTO> selectTeamMember_menu(String employee_id);
	public List<EmployeeDTO> selectEmpOfDept(int departmentId);
}
