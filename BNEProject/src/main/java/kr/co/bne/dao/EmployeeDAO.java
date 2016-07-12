package kr.co.bne.dao;

import java.util.HashMap;

import kr.co.bne.dto.EmployeeDTO;

public interface EmployeeDAO {

	public EmployeeDTO selectEmployee(String id);
	public void updatePassword(HashMap<String,String> info);
	public void updateFile(HashMap<String, String> info);
	public void updateFile(String id);
	public EmployeeDTO selectImage(String employee_id);
		
	
}
