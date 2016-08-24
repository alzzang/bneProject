package kr.co.bne.service;

import java.util.List;

import kr.co.bne.dto.EmployeeDTO;

public interface UserService {
	public EmployeeDTO validCheck(String id, String rawPassword);
	public void modifyPassword(String id, String rawPassword);
	public void modifyFilePosition(String id, String filePosition);
	public void modifyFilePosition(String id);
	public List<EmployeeDTO> getTeamMember(String position, int departmentId);
	public List<EmployeeDTO> getEmpSearch(String empSearch);
	public EmployeeDTO selectEmployee(String employee_id);
	List<EmployeeDTO> selectTeamMember_menu(String employee_id);
	public List<EmployeeDTO> getEmpOfDept(int departmentId);
}
