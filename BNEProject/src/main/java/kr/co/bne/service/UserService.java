package kr.co.bne.service;

import kr.co.bne.dto.EmployeeDTO;

public interface UserService {
	public EmployeeDTO validCheck(String id, String rawPassword);
	public void modifyPassword(String id, String rawPassword);
	public void modifyFilePosition(String id, String filePosition);
	public void modifyFilePosition(String id);
	public EmployeeDTO selectEmployee(String id);
}
