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
	int getPagingNum_EmployeeList(int perContentNum, HashMap<String, String> params);
	List<EmployeeDTO> getEmployeeList(int startIdx, int perContentNum, HashMap<String, String> params);
	boolean deleteEmployee(String employee_id);
	boolean insertEmployeeList(List<EmployeeDTO> employeeList);
	boolean updateEmployee(EmployeeDTO employeeDTO);
	public List<EmployeeDTO> selectEmployeeListA(int department_id);
}
